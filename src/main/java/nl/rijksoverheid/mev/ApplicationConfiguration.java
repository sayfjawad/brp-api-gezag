package nl.rijksoverheid.mev;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

/**
 * Configuratie van de applicatie
 */
@Configuration
class ApplicationConfiguration {

    @Bean
    public Clock clock(@Nullable @Value("${app.clock:}") final Instant instant) {
        return instant == null
            ? Clock.systemDefaultZone()
            : Clock.fixed(instant, ZoneId.systemDefault());
    }
}
