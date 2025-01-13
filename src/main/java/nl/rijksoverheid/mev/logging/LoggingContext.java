package nl.rijksoverheid.mev.logging;

import nl.rijksoverheid.mev.gezagsmodule.model.Burgerservicenummer;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LoggingContext {

    private final Instant eventStart;
    private final Map<Long, Long> plIdByBurgerservicenummer;

    public LoggingContext() {
        this.eventStart = Instant.now();
        this.plIdByBurgerservicenummer = new HashMap<>();
    }

    public void addPlId(long plId, Burgerservicenummer burgerservicenummer) {
        plIdByBurgerservicenummer.put(burgerservicenummer.value(), plId);
    }

    public Long getPlIdBy(String burgerservicenummerAsString) {
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
}
