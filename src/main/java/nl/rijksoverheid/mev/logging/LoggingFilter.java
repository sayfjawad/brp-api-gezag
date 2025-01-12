package nl.rijksoverheid.mev.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

public class LoggingFilter extends OncePerRequestFilter implements ApplicationContextAware {

    /**
     * SLF4J logger named "log" instead of "logger" to prevent hiding Apache logger `GenericFilterBean.logger`.
     */
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    private final ObjectMapper objectMapper;

    private ApplicationContext applicationContext;

    public LoggingFilter(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        this.objectMapper = jackson2ObjectMapperBuilder.build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var isFirstRequest = !isAsyncDispatch(request);
        var cachedRequest = isFirstRequest && !(request instanceof ContentCachingRequestWrapper)
            ? new ContentCachingRequestWrapper(request)
            : request;
        var cachedResponse = isFirstRequest && !(response instanceof ContentCachingResponseWrapper)
            ? new ContentCachingResponseWrapper(response)
            : response;

        if (isFirstRequest) {
            beforeRequest(cachedRequest);
        }
        try {
            filterChain.doFilter(cachedRequest, cachedResponse);
        } finally {
            if (!isAsyncStarted(cachedRequest)) {
                afterRequest(cachedRequest, cachedResponse);
            }
            MDC.clear();
        }
    }

    private void beforeRequest(HttpServletRequest request) {
        var loggingContext = applicationContext.getBean(LoggingContext.class);

        var requestId = request.getHeader("Request-Id");
        var eventStart = loggingContext.getEventStart();

        MDC.put("trace.id", requestId);
        MDC.put("event.start", eventStart.toString());
    }

    private void afterRequest(HttpServletRequest request, HttpServletResponse response) {
        var event = processEvent();
        var http = processHttp(request, response);
        var url = processUrl(request);

        processMetadata(request, response);

        log.info(
            "HTTP {} {} responded {} in {} ms",
            http.requestMethod, url.path, http.responseStatusCode, event.duration.toMillis()
        );
    }

    private Event processEvent() {
        var loggingContext = applicationContext.getBean(LoggingContext.class);

        var eventEnd = Instant.now();
        var eventStart = loggingContext.getEventStart();
        var eventDuration = Duration.between(eventStart, eventEnd);

        MDC.put("event.kind", "event");
        MDC.put("event.category", "api");
        MDC.put("event.duration", String.valueOf(eventDuration.toNanos()));
        MDC.put("event.end", eventEnd.toString());

        return new Event(eventStart, eventEnd, eventDuration);
    }

    private record Event(Instant start, Instant end, Duration duration) {
    }

    private Http processHttp(HttpServletRequest request, HttpServletResponse response) {
        var requestMethod = request.getMethod();
        var requestContentType = request.getContentType();
        var responseContentType = response.getContentType();
        var responseStatusCode = response.getStatus();

        MDC.put("request.method", requestMethod);
        MDC.put("request.mime_type", requestContentType);
        MDC.put("response.mime_type", responseContentType);
        MDC.put("response.status_code", String.valueOf(responseStatusCode));

        return new Http(requestMethod, responseStatusCode);
    }

    private record Http(String requestMethod, int responseStatusCode) {
    }

    private Url processUrl(HttpServletRequest request) {
        var requestUri = request.getRequestURI();

        MDC.put("url.path", requestUri);

        return new Url(requestUri);
    }

    private record Url(String path) {
    }

    private void processMetadata(HttpServletRequest request, HttpServletResponse response) {
        var loggingContext = applicationContext.getBean(LoggingContext.class);

        var requestHeaders = headersToMap(Collections.list(request.getHeaderNames()), request::getHeader);
        var cachedRequest = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        var requestBodyAsJsonString = cachedRequest == null ? null : cachedRequest.getContentAsString();

        var cachedResponse = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        var responseBodyAsJsonString = cachedResponse == null ? null : new String(cachedResponse.getContentAsByteArray());
        if (cachedResponse != null) {
            try {
                cachedResponse.copyBodyToResponse();
            } catch (IOException e) {
                log.error("Failed to copy the complete cached body content", e);
            }
        }
        var responseHeaders = cachedResponse == null ? null : headersToMap(cachedResponse.getHeaderNames(), cachedResponse::getHeader);

        var metadata = new HashMap<String, Object>();
        metadata.put("gezag_resultaten", loggingContext.getGezagResultaten());
        metadata.put("pl_ids", loggingContext.getPlIds());
        metadata.put("response.body", responseBodyAsJsonString);
        metadata.put("response.headers", responseHeaders);
        metadata.put("request.body", requestBodyAsJsonString);
        metadata.put("request.headers", requestHeaders);

        try {
            var metadataAsJsonString = objectMapper.writeValueAsString(metadata);
            MDC.put("metadata", metadataAsJsonString);
        } catch (JsonProcessingException e) {
            log.error("Failed to serialize headers to JSON; fallback to writing headers as Java object", e);
            MDC.put("metadata", metadata.toString());
        }
    }

    private Map<String, String> headersToMap(Collection<String> headerNames, UnaryOperator<String> headerValueResolver) {
        return headerNames.stream()
            .map(headerName -> Map.entry(headerName, headerValueResolver.apply(headerName)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getRequestURI().startsWith("/actuator");
    }

    /**
     * The default value is "false" so that the filter may log a "before" message
     * at the start of request processing and an "after" message at the end from
     * when the last asynchronously dispatched thread is exiting.
     *
     * @see AbstractRequestLoggingFilter
     */
    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
