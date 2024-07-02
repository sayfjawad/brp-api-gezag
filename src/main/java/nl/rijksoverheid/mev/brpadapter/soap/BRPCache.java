package nl.rijksoverheid.mev.brpadapter.soap;

import nl.bprbzk.gba.lrdplus.version1.VraagResponse;
import org.ehcache.Cache;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.stereotype.Component;
import java.time.Duration;

/**
 * Slaat antwoorden vanuit de BRP tijdelijk op
 * <p>
 * Aanvragen op BRP worden per aanvraag berekend. Door antwoorden tijdelijk te
 * cachen wordt het aantal aanvragen flink verminderd
 */
@Component
public class BRPCache {

    public record BrpResponse(VraagResponse vraagResponse) {

    }

    private final Cache<String, BrpResponse> cache;

    private static final int MAX_ENTRIES_LOCAL_HEAP = 1000;
    private static final int TIME_TO_LIVE_SECONDS = 1200;

    public BRPCache() {
        var cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        var configBuilder = CacheConfigurationBuilder.newCacheConfigurationBuilder(
                String.class,
                BrpResponse.class,
                ResourcePoolsBuilder.heap(MAX_ENTRIES_LOCAL_HEAP)
        ).withExpiry(
                ExpiryPolicyBuilder.timeToLiveExpiration(
                        Duration.ofSeconds(TIME_TO_LIVE_SECONDS)
                )
        );

        this.cache = cacheManager.createCache("vraagResponseCache", configBuilder);
    }

    /**
     * Geeft een brp aanvraag terug op basis van een bsn
     *
     * @param bsn bsn om aanvraag voor te zoeken
     * @return brp antwoord of null
     */
    public BrpResponse get(final String bsn) {
        return cache.get(bsn);
    }

    /**
     * Sla een antwoord uit de BRP op in de cache op basis van bsn
     *
     * @param bsn de bsn
     * @param value het antwoord uit de brp
     */
    public void put(final String bsn, final BrpResponse value) {
        cache.put(bsn, value);
    }
}
