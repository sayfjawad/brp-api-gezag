package nl.rijksoverheid.mev.logging;

import nl.rijksoverheid.mev.gezagsmodule.model.Burgerservicenummer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class LoggingContext {

    private static final Logger logger = LoggerFactory.getLogger(LoggingContext.class);

    private final Instant eventStart;
    private final Map<Long, Long> plIdByBurgerservicenummer;

    public LoggingContext() {
        this.eventStart = Instant.now();
        this.plIdByBurgerservicenummer = new HashMap<>();
    }

    public void addPlId(long plId, Burgerservicenummer burgerservicenummer) {
        plIdByBurgerservicenummer.put(burgerservicenummer.value(), plId);
    }

    public long getPlIdBy(String burgerservicenummerAsString) {
        var burgerservicenummerAsLong = Long.valueOf(burgerservicenummerAsString);
        return plIdByBurgerservicenummer.get(burgerservicenummerAsLong);
    }

    public Instant getEventStart() {
        return eventStart;
    }

    public Set<Long> getPlIds() {
        return plIdByBurgerservicenummer.values().stream()
            .collect(Collectors.toUnmodifiableSet());
    }

    private static String mapGezagType(String gezagType) {
        // temporary until GezagService returns typed responses
        return switch (gezagType) {
            case "OG1" -> "EenhoofdigOuderlijkGezag";
            case "OG2" -> "TweehoofdigOuderlijkGezag";
            case "GG" -> "GezamenlijkGezag";
            case "V" -> "Voogdij";
            case "G" -> "TijdelijkGeenGezag";
            case "N" -> "GezagNietTeBepalen";
            case "NVT" -> "GezagNietVanToepassing*";
            default -> { // should never happen
                logger.error("Unknown gezag type: {}", gezagType);
                yield "OnbekendGezagType";
            }
        };
    }
}
