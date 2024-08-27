package nl.rijksoverheid.mev;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.model.Burgerservicenummer;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.NullValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfiguration {

    @Bean
    JCacheManagerCustomizer jCacheManagerCustomizer() {
        return cacheManager -> {
            cacheManager.createCache("persoonslijsten", Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                    Burgerservicenummer.class,
                    Persoonslijst.class,
                    ResourcePoolsBuilder.heap(1000)
                ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(20L)))
            ));

            cacheManager.createCache("persoonslijstenNull", Eh107Configuration.fromEhcacheCacheConfiguration(
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                    Burgerservicenummer.class,
                    NullValue.class,
                    ResourcePoolsBuilder.heap(1000)
                ).withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofMinutes(20L)))
            ));
        };
    }
}
