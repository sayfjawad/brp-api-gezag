package nl.rijksoverheid.mev.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
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

    private static final String TIMEZONE = ZoneId.systemDefault().getId();

    private ApplicationContext applicationContext;

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
            log.debug("{}", MDC.getMap());
            MDC.clear();
        }
    }

    private void beforeRequest(HttpServletRequest request) {
        var loggingContext = applicationContext.getBean(LoggingContext.class);

        var requestId = request.getHeader("Request-Id");
        var eventStart = loggingContext.getEventStart();

        MDC.put("trace.id", requestId);
        MDC.put("event.start", eventStart);
    }

    private void afterRequest(HttpServletRequest request, HttpServletResponse response) {
        var event = processEvent();
        var http = processHttp(request, response);
        var url = processUrl(request);
        processProcess();

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

        MDC.put("event", Map.of(
            "kind", "event",
            "category", "api",
            "duration", eventDuration.toNanos(),
            "end", eventEnd,
            "timezone", TIMEZONE
        ));

        return new Event(eventStart, eventEnd, eventDuration);
    }

    private record Event(Instant start, Instant end, Duration duration) {
    }

    private Http processHttp(HttpServletRequest request, HttpServletResponse response) {
        var requestMethod = request.getMethod();
        var requestContentType = request.getContentType();
        var responseContentType = response.getContentType();
        var responseStatusCode = response.getStatus();

        MDC.put("http", Map.of(
            "request.method", requestMethod,
            "request.mime_type", requestContentType,
            "response.mime_type", responseContentType,
            "response.status_code", responseStatusCode
        ));

        return new Http(requestMethod, responseStatusCode);
    }

    private record Http(String requestMethod, int responseStatusCode) {
    }

    private Url processUrl(HttpServletRequest request) {
        var requestUri = request.getRequestURI();

        MDC.put("url.path", Map.of(
            "path", requestUri
        ));

        return new Url(requestUri);
    }

    private record Url(String path) {
    }

    private void processProcess() {
        var pid = ProcessHandle.current().pid();

        var currentThread = Thread.currentThread();
        var threadId = currentThread.threadId();
        var threadName = currentThread.getName();

        MDC.put("process", Map.of(
            "pid", pid,
            "thread.id", threadId,
            "thread.name", threadName
        ));
    }

    private void processMetadata(HttpServletRequest request, HttpServletResponse response) {
        var loggingContext = applicationContext.getBean(LoggingContext.class);

        var requestHeaders = headersToMap(Collections.list(request.getHeaderNames()), request::getHeader);
        var cachedRequest = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        var requestBodyAsString = cachedRequest == null ? null : cachedRequest.getContentAsString();

        // 
        var cachedResponse = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
        var responseBodyAsString = cachedResponse == null ? null : new String(cachedResponse.getContentAsByteArray());
        if (cachedResponse != null) {
            try {
                cachedResponse.copyBodyToResponse();
            } catch (IOException e) {
                log.error("Failed to copy the complete cached body content", e);
            }
        }
        var responseHeaders = cachedResponse == null ? null : headersToMap(cachedResponse.getHeaderNames(), cachedResponse::getHeader);

        var isError = response.getStatus() >= 400;

        var metadata = new HashMap<String, Object>();
        metadata.put("gezag_resultaten", loggingContext.getGezagResultaten());
        metadata.put("pl_ids", loggingContext.getPlIds());
        if (isError) metadata.put("response.body", responseBodyAsString);
        metadata.put("response.headers", responseHeaders);
        metadata.put("request.body", requestBodyAsString);
        metadata.put("request.headers", requestHeaders);

        MDC.put("metadata", metadata);
    }

    private Map<String, String> headersToMap(Collection<String> headerNames, UnaryOperator<String> headerValueResolver) {
        return headerNames.stream()
            .map(headerName -> Map.entry(headerName, headerValueResolver.apply(headerName)))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        var requestUri = request.getRequestURI();
        return requestUri.startsWith("/actuator");
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
