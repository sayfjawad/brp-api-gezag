package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import org.openapitools.model.*;

/**
 * Extends {@link org.openapitools.model.AbstractGezagsrelatie}.
 */
public class AbstractGezagsrelaties {

    private static final String TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG = "TweehoofdigOuderlijkGezag";
    private static final String TYPE_EENHOOFDIG_OUDERLIJK_GEZAG = "EenhoofdigOuderlijkGezag";
    private static final String TYPE_GEZAMENLIJK_GEZAG = "GezamenlijkGezag";
    private static final String TYPE_VOOGDIJ = "Voogdij";
    private static final String TYPE_TIJDELIJK_GEEN_GEZAG = "TijdelijkGeenGezag";
    private static final String TYPE_GEZAG_NIET_TE_BEPALEN = "GezagNietTeBepalen";

    private AbstractGezagsrelaties() {
    }

    /**
     * Transformeer gezag voor persoon
     * <br>
     * FUTURE_WORK: bij GG en bij Voogdij moet de ouder en niet ouder gescheiden
     * worden, hier is meer informatie voor nodig
     *
     * @param gezagsrelatie de gezagsrelatie
     */
    public static AbstractGezagsrelatie from(final Gezagsrelatie gezagsrelatie) {
        final String burgerservicenummerMeerderjarige = gezagsrelatie.getBsnMeerderjarige();
        final String burgerservicenummerMinderjarige = gezagsrelatie.getBsnMinderjarige();

        return switch (gezagsrelatie.getSoortGezag()) {
            case "OG1" -> new EenhoofdigOuderlijkGezag()
                .type(TYPE_EENHOOFDIG_OUDERLIJK_GEZAG)
                .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummerMinderjarige))
                .ouder(new GezagOuder().burgerservicenummer(burgerservicenummerMeerderjarige));
            case "OG2" -> new TweehoofdigOuderlijkGezag()
                .type(TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG)
                .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummerMinderjarige))
                .addOudersItem(new GezagOuder().burgerservicenummer(burgerservicenummerMeerderjarige));
            case "GG" -> gezagsrelatie.isDerde()
                // @formatter:off
                ? new GezamenlijkGezag()
                    .type(TYPE_GEZAMENLIJK_GEZAG)
                    .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummerMinderjarige))
                    .derde(new Meerderjarige().burgerservicenummer(burgerservicenummerMeerderjarige))
                : new GezamenlijkGezag()
                    .type(TYPE_GEZAMENLIJK_GEZAG)
                    .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummerMinderjarige))
                    .ouder(new GezagOuder().burgerservicenummer(burgerservicenummerMeerderjarige));
                // @formatter:on
            case "V" -> {
                var voogdij = new Voogdij()
                    .type(TYPE_VOOGDIJ)
                    .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummerMinderjarige));

                if (burgerservicenummerMeerderjarige != null && !burgerservicenummerMeerderjarige.isEmpty()) {
                    voogdij.addDerdenItem(new Meerderjarige().burgerservicenummer(burgerservicenummerMeerderjarige));
                }

                yield voogdij;
            }
            case "G" -> new TijdelijkGeenGezag()
                .type(TYPE_TIJDELIJK_GEEN_GEZAG);
            case "N" -> new GezagNietTeBepalen()
                .type(TYPE_GEZAG_NIET_TE_BEPALEN)
                .toelichting(gezagsrelatie.getToelichting());
            default -> throw new IllegalStateException("Unexpected value: " + gezagsrelatie.getSoortGezag());
        };
    }
}
