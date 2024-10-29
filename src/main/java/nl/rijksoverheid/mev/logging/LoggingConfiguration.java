package nl.rijksoverheid.mev.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class LoggingConfiguration {

    @Bean
    @RequestScope
    public LoggingContext loggingContext() {
        return new LoggingContext();
    }

    @Bean
    public LoggingFilter loggingFilter(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        return new LoggingFilter(jackson2ObjectMapperBuilder);
    }
}
