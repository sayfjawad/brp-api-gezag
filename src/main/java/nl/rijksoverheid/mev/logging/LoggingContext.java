package nl.rijksoverheid.mev.logging;

import nl.rijksoverheid.mev.gezagsmodule.model.Burgerservicenummer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.*;

public class LoggingContext {

    private static final Logger logger = LoggerFactory.getLogger(LoggingContext.class);

    private final Instant eventStart;
    private final Set<Long> plIds;
    private final Map<String, GezagResultaat> gezagResultaten;

    public LoggingContext() {
        this.eventStart = Instant.now();
        this.plIds = new HashSet<>();
        this.gezagResultaten = new HashMap<>();
    }

    public void addBurgerservicenummers(Collection<String> burgerservicenummers) {
        burgerservicenummers.forEach(burgerservicenummer ->
            gezagResultaten.put(burgerservicenummer, new GezagResultaat())
        );
    }

    public void addPlId(long plId, Burgerservicenummer burgerservicenummer) {
        gezagResultaten.computeIfPresent(burgerservicenummer.asString(), (unused, gezagResultaat) -> {
            gezagResultaat.setPlId(plId);
            return gezagResultaat;
        });
        plIds.add(plId);
    }

    public void addGezagType(String gezagType, String burgerservicenummer) {
        gezagResultaten.computeIfPresent(burgerservicenummer, (unused, gezagResultaat) -> {
            var translatedGezagType = mapGezagType(gezagType);
            gezagResultaat.setType(translatedGezagType);
            return gezagResultaat;
        });
    }

    public void addRoute(String route, String burgerservicenummer) {
        gezagResultaten.computeIfPresent(burgerservicenummer, (unused, gezagResultaat) -> {
            gezagResultaat.setRoute(route);
            return gezagResultaat;
        });
    }

    public void addToelichting(String toelichting, String burgerservicenummer) {
        gezagResultaten.computeIfPresent(burgerservicenummer, (unused, gezagResultaat) -> {
            gezagResultaat.setToelichting(toelichting);
            return gezagResultaat;
        });
    }

    public Instant getEventStart() {
        return eventStart;
    }

    public Set<Long> getPlIds() {
        return Collections.unmodifiableSet(plIds);
    }

    public Collection<GezagResultaat> getGezagResultaten() {
        return Collections.unmodifiableCollection(gezagResultaten.values());
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
