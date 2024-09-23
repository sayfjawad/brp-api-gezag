package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import org.openapitools.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Vertaal gezagrelaties naar Persoon met abstract gezagrelatie.
 * <br>
 * Deze transformer kan worden verwijderd wanneer `AbstractGezagsrelatie` direct wordt gebruikt en de gezagsrelatie
 * tussen stap is verwijderd
 */
@Component
public class GezagTransformer {

    private static final String TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG = "TweehoofdigOuderlijkGezag";
    private static final String TYPE_EENHOOFDIG_OUDERLIJK_GEZAG = "EenhoofdigOuderlijkGezag";
    private static final String TYPE_GEZAMELIJK_GEZAG = "GezamenlijkGezag";
    private static final String TYPE_VOOGDIJ = "Voogdij";
    private static final String TYPE_TIJDELIJK_GEEN_GEZAG = "TijdelijkGeenGezag";
    private static final String TYPE_GEZAG_NIET_TE_BEPALEN = "GezagNietTeBepalen";

    /**
     * Gegeven de gezagrelaties zoals deze nu uit de gezagmodule geleverd worden, geef de AbstractGezagsrelatie zoals in
     * de nieuwe situatie wordt verwacht
     *
     * @param gezagsrelaties de gezagsrelaties om te vertalen
     * @return de vertaalde gezagsrelaties
     */
    public List<AbstractGezagsrelatie> from(final List<Gezagsrelatie> gezagsrelaties) {
        List<AbstractGezagsrelatie> vertaaldeGezagsrelaties = new ArrayList<>();

        if (gezagsrelaties != null && !gezagsrelaties.isEmpty()) {
            gezagsrelaties.forEach(gezagsrelatie -> transformGezagrelatie(gezagsrelatie, vertaaldeGezagsrelaties));
        }

        return vertaaldeGezagsrelaties;
    }

    private void transformGezagrelatie(final Gezagsrelatie gezagsrelatie, final List<AbstractGezagsrelatie> vertaaldeGezagsrelaties) {
        String burgerservicenummerMeerderjarige = gezagsrelatie.getBsnMeerderjarige();
        String burgerservicenummerMinderjarige = gezagsrelatie.getBsnMinderjarige();
        String burgerservicenummerDerde = gezagsrelatie.getBsnDerde();

        switch (gezagsrelatie.getSoortGezag()) {
            case "OG1" -> {
                AbstractGezagsrelatie gezag = new EenhoofdigOuderlijkGezag()
                    .ouder(new GezagOuder().burgerservicenummer(burgerservicenummerMeerderjarige))
                    .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummerMinderjarige))
                    .type(TYPE_EENHOOFDIG_OUDERLIJK_GEZAG);

                vertaaldeGezagsrelaties.add(gezag);
            }
            case "OG2" -> {
                var optionalTweehoofdigOuderlijkGezag = getTweehoofdigOuderlijkGezagMinderjarige(burgerservicenummerMinderjarige, vertaaldeGezagsrelaties);
                optionalTweehoofdigOuderlijkGezag.ifPresentOrElse(
                    huidigeVoorMinderjarige -> huidigeVoorMinderjarige.addOudersItem(new GezagOuder().burgerservicenummer(burgerservicenummerMeerderjarige)),
                    () -> {
                        AbstractGezagsrelatie gezag = new TweehoofdigOuderlijkGezag()
                            .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummerMinderjarige))
                            .addOudersItem(new GezagOuder().burgerservicenummer(burgerservicenummerMeerderjarige))
                            .type(TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG);

                        vertaaldeGezagsrelaties.add(gezag);
                    }
                );
            }
            case "GG" -> {
                AbstractGezagsrelatie gezag = new GezamenlijkGezag()
                    .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummerMinderjarige))
                    .derde(new Meerderjarige().burgerservicenummer(burgerservicenummerDerde))
                    .ouder(new GezagOuder().burgerservicenummer(gezagsrelatie.getBsnMeerderjarige()))
                    .type(TYPE_GEZAMELIJK_GEZAG);

                vertaaldeGezagsrelaties.add(gezag);
            }
            case "V" -> {
                Voogdij gezag = new Voogdij()
                    .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummerMinderjarige))
                    .type(TYPE_VOOGDIJ);

                if (burgerservicenummerDerde != null && !burgerservicenummerDerde.isEmpty()) {
                    gezag.addDerdenItem(new Meerderjarige().burgerservicenummer(burgerservicenummerDerde));
                }

                vertaaldeGezagsrelaties.add(gezag);
            }
            case "G" -> vertaaldeGezagsrelaties.add(new TijdelijkGeenGezag().type(TYPE_TIJDELIJK_GEEN_GEZAG));
            case "N" ->
                vertaaldeGezagsrelaties.add(new GezagNietTeBepalen().type(TYPE_GEZAG_NIET_TE_BEPALEN).toelichting(gezagsrelatie.getToelichting()));
            default -> throw new IllegalStateException("Unexpected value: " + gezagsrelatie.getSoortGezag());
        }
    }

    private Optional<TweehoofdigOuderlijkGezag> getTweehoofdigOuderlijkGezagMinderjarige(
        final String burgerservicenummerMinderjarige,
        final List<AbstractGezagsrelatie> abstractGezagsrelaties
    ) {
        return abstractGezagsrelaties.stream()
            .filter(TweehoofdigOuderlijkGezag.class::isInstance)
            .map(TweehoofdigOuderlijkGezag.class::cast)
            .filter(tweehoofdigOuderlijkGezag -> tweehoofdigOuderlijkGezag.getMinderjarige().orElseThrow().getBurgerservicenummer() // not optional, should be fixed in OpenAPI Specgezagsrelatie.getBsnMinderjarige()
                .equals(burgerservicenummerMinderjarige))
            .findFirst();
    }
}