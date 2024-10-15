package nl.rijksoverheid.mev.logging;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

@Configuration
public class LoggingConfiguration {

    @Bean
    @RequestScope
    public LoggingContext loggingContext() {
        return new LoggingContext();
    }

    @Bean
    public LoggingFilter loggingFilter() {
        return new LoggingFilter();
    }
}
