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
            gezagsrelaties.stream()
                .forEach(gezagsrelatie -> transformGezagrelatie(gezagsrelatie, vertaaldeGezagsrelaties));
        }

        return vertaaldeGezagsrelaties;
    }

    private void transformGezagrelatie(final Gezagsrelatie gezagsrelatie, final List<AbstractGezagsrelatie> vertaaldeGezagsrelaties) {
        switch (gezagsrelatie.getSoortGezag()) {
            case "OG1" -> {
                AbstractGezagsrelatie gezag = new EenhoofdigOuderlijkGezag()
                    .ouder(new GezagOuder().burgerservicenummer(gezagsrelatie.getBsnMeerderjarige()))
                    .minderjarige(new Minderjarige().burgerservicenummer(gezagsrelatie.getBsnMinderjarige()))
                    .type(TYPE_EENHOOFDIG_OUDERLIJK_GEZAG);

                vertaaldeGezagsrelaties.add(gezag);
            }
            case "OG2" -> {
                TweehoofdigOuderlijkGezag huidigeVoorMinderjarige = getTweehoofdigOuderlijkGezagMinderjarige(gezagsrelatie, vertaaldeGezagsrelaties);
                if (huidigeVoorMinderjarige != null) {
                    huidigeVoorMinderjarige.addOudersItem(new GezagOuder().burgerservicenummer(gezagsrelatie.getBsnMeerderjarige()));
                } else {
                    AbstractGezagsrelatie gezag = new TweehoofdigOuderlijkGezag()
                        .minderjarige(new Minderjarige().burgerservicenummer(gezagsrelatie.getBsnMinderjarige()))
                        .addOudersItem(new GezagOuder().burgerservicenummer(gezagsrelatie.getBsnMeerderjarige()))
                        .type(TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG);

                    vertaaldeGezagsrelaties.add(gezag);
                }
            }
            case "GG" -> {
                GezamenlijkGezag gezag = getGezamenlijkGezagGezagMinderjarige(gezagsrelatie, vertaaldeGezagsrelaties);

                if (gezag == null) {
                    gezag = new GezamenlijkGezag()
                        .minderjarige(new Minderjarige().burgerservicenummer(gezagsrelatie.getBsnMinderjarige()))
                        .type(TYPE_GEZAMELIJK_GEZAG);

                    vertaaldeGezagsrelaties.add(gezag);
                }

                if (!gezagsrelatie.isDerde()) {
                    gezag.setOuder(Optional.of(new GezagOuder().burgerservicenummer(gezagsrelatie.getBsnMeerderjarige())));
                } else if (gezagsrelatie.isDerde()) {
                    gezag.setDerde(Optional.of(new Meerderjarige().burgerservicenummer(gezagsrelatie.getBsnMeerderjarige())));
                }
            }
            case "V" -> {
                Voogdij gezag = new Voogdij()
                    .minderjarige(new Minderjarige().burgerservicenummer(gezagsrelatie.getBsnMinderjarige()))
                    .type(TYPE_VOOGDIJ);
                String bsnMeerderjarige = gezagsrelatie.getBsnMeerderjarige();
                if (bsnMeerderjarige != null && !bsnMeerderjarige.isEmpty()) {
                    gezag.addDerdenItem(new Meerderjarige().burgerservicenummer(bsnMeerderjarige));
                }

                vertaaldeGezagsrelaties.add(gezag);
            }
            case "G" -> vertaaldeGezagsrelaties.add(new TijdelijkGeenGezag().type(TYPE_TIJDELIJK_GEEN_GEZAG));
            case "N" ->
                vertaaldeGezagsrelaties.add(new GezagNietTeBepalen().type(TYPE_GEZAG_NIET_TE_BEPALEN).toelichting(gezagsrelatie.getToelichting()));
        }
    }

    private TweehoofdigOuderlijkGezag getTweehoofdigOuderlijkGezagMinderjarige(final Gezagsrelatie gezagsrelatie, final List<AbstractGezagsrelatie> vertaaldeGezagsrelaties) {
        AbstractGezagsrelatie current = vertaaldeGezagsrelaties.stream()
            .filter(vertaaldeGezagsrelatie -> TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG.equals(vertaaldeGezagsrelatie.getType()) &&
                gezagsrelatie.getBsnMinderjarige().equals(
                    ((TweehoofdigOuderlijkGezag) vertaaldeGezagsrelatie).getMinderjarige().get().getBurgerservicenummer()))
            .findFirst()
            .orElse(null);

        return (TweehoofdigOuderlijkGezag) current;
    }

    private GezamenlijkGezag getGezamenlijkGezagGezagMinderjarige(final Gezagsrelatie gezagsrelatie, final List<AbstractGezagsrelatie> vertaaldeGezagsrelaties) {
        AbstractGezagsrelatie current = vertaaldeGezagsrelaties.stream()
            .filter(vertaaldeGezagsrelatie -> TYPE_GEZAMELIJK_GEZAG.equals(vertaaldeGezagsrelatie.getType()) &&
                gezagsrelatie.getBsnMinderjarige().equals(
                    ((GezamenlijkGezag) vertaaldeGezagsrelatie).getMinderjarige().get().getBurgerservicenummer()))
            .findFirst()
            .orElse(null);

        return (GezamenlijkGezag) current;
    }
}