package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import org.openapitools.model.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Vertaal gezagrelaties naar Persoon met abstract gezagrelatie.
 *
 * <p>
 * FUTURE_WORK: Voor het correct bepalen van de relaties is meer informatie
 * nodig dan op dit moment beschikbaar is in de gezagsmodule. In de huidige BRP
 * API wordt deze functionaliteit wel opgenomen en kan gebruikt worden als
 * referentie. Voor het correct bepalen van de relaties is meer informatie over
 * de personen nodig.
 * <br>
 * Zie: Rvig.HaalCentraalApi.Personen.Interfaces.GezagTransformer Zie ook:
 * Rvig.HaalCentraalApi.Personen.Services.GbaPersonenApiService
 * <br>
 * Deze aanvulling kan gemaakt worden wanneer de database communicatie
 * beschikbaar is
 * </p>
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
     * Gegeven de gezagrelaties zoals deze nu uit de gezagmodule geleverd worden
     *
     * @param gezagrelaties de gezagrelaties om te vertalen
     * @return de persoon objecten met de vertaalde gezag relaties
     */
    public List<Persoon> fromGezagrelaties(final List<Gezagsrelatie> gezagrelaties) {
        Map<String, Persoon> personen = new HashMap<>();

        if (gezagrelaties != null && !gezagrelaties.isEmpty()) {
            for (Gezagsrelatie gezagsrelatie : gezagrelaties) {
                Persoon persoon;
                String bsnBevraagdePersoon = gezagsrelatie.getBsnBevraagdePersoon();
                if (personen.containsKey(bsnBevraagdePersoon)) {
                    persoon = personen.get(bsnBevraagdePersoon);
                } else {
                    persoon = new Persoon().burgerservicenummer(gezagsrelatie.getBsnBevraagdePersoon());

                    personen.put(bsnBevraagdePersoon, persoon);
                }

                transformGezagForPersoon(persoon, gezagsrelatie);
            }

        }

        return new ArrayList<>(personen.values());
    }

    /**
     * Transformeer gezag voor persoon
     * <br>
     * FUTURE_WORK: bij GG en bij Voogdij moet de ouder en niet ouder gescheiden
     * worden, hier is meer informatie voor nodig
     *
     * @param persoon       de persoon
     * @param gezagsrelatie de gezagsrelatie
     */
    private void transformGezagForPersoon(final Persoon persoon, final Gezagsrelatie gezagsrelatie) {
        switch (gezagsrelatie.getSoortGezag()) {
            case "OG1" -> {
                AbstractGezagsrelatie gezag = new EenhoofdigOuderlijkGezag()
                    .ouder(new GezagOuder().burgerservicenummer(gezagsrelatie.getBsnMeerderjarige()))
                    .minderjarige(new Minderjarige().burgerservicenummer(gezagsrelatie.getBsnMinderjarige()))
                    .type(TYPE_EENHOOFDIG_OUDERLIJK_GEZAG);

                persoon.addGezagItem(gezag);
            }
            case "OG2" -> {
                TweehoofdigOuderlijkGezag huidigeVoorMinderjarige = getTweehoofdigOuderlijkGezagMinderjarige(persoon, gezagsrelatie);
                if (huidigeVoorMinderjarige != null) {
                    huidigeVoorMinderjarige.addOudersItem(new GezagOuder().burgerservicenummer(gezagsrelatie.getBsnMeerderjarige()));
                } else {
                    AbstractGezagsrelatie gezag = new TweehoofdigOuderlijkGezag()
                        .minderjarige(new Minderjarige().burgerservicenummer(gezagsrelatie.getBsnMinderjarige()))
                        .addOudersItem(new GezagOuder().burgerservicenummer(gezagsrelatie.getBsnMeerderjarige()))
                        .type(TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG);

                    persoon.addGezagItem(gezag);
                }
            }
            case "GG" -> {
                AbstractGezagsrelatie gezag;
                if (!gezagsrelatie.isDerde()) {
                    gezag = new GezamenlijkGezag()
                        .minderjarige(new Minderjarige().burgerservicenummer(gezagsrelatie.getBsnMinderjarige()))
                        .ouder(new GezagOuder().burgerservicenummer(gezagsrelatie.getBsnMeerderjarige()))
                        .type(TYPE_GEZAMELIJK_GEZAG);
                } else {
                    gezag = new GezamenlijkGezag()
                        .minderjarige(new Minderjarige().burgerservicenummer(gezagsrelatie.getBsnMinderjarige()))
                        .derde(new Meerderjarige().burgerservicenummer(gezagsrelatie.getBsnMeerderjarige()))
                        .type(TYPE_GEZAMELIJK_GEZAG);
                }

                persoon.addGezagItem(gezag);
            }
            case "V" -> {
                Voogdij gezag = new Voogdij()
                    .minderjarige(new Minderjarige().burgerservicenummer(gezagsrelatie.getBsnMinderjarige()))
                    .type(TYPE_VOOGDIJ);
                if(gezagsrelatie.getBsnMeerderjarige() != null && !gezagsrelatie.getBsnMeerderjarige().isEmpty()) {
                    gezag.addDerdenItem(new Meerderjarige().burgerservicenummer(gezagsrelatie.getBsnMeerderjarige()));
                }

                persoon.addGezagItem(gezag);
            }
            case "G" -> persoon.addGezagItem(new TijdelijkGeenGezag().type(TYPE_TIJDELIJK_GEEN_GEZAG));
            case "N" ->
                persoon.addGezagItem(new GezagNietTeBepalen().type(TYPE_GEZAG_NIET_TE_BEPALEN).toelichting(gezagsrelatie.getToelichting()));
        }
    }

    public TweehoofdigOuderlijkGezag getTweehoofdigOuderlijkGezagMinderjarige(final Persoon persoon, final Gezagsrelatie gezagsrelatie) {
        List<AbstractGezagsrelatie> currentGezagsrelaties = persoon.getGezag();
        if (currentGezagsrelaties != null && !currentGezagsrelaties.isEmpty()) {
            for (AbstractGezagsrelatie currentGezagsrelatie : currentGezagsrelaties) {
                if (TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG.equals(currentGezagsrelatie.getType())) {
                    TweehoofdigOuderlijkGezag gezag = (TweehoofdigOuderlijkGezag) currentGezagsrelatie;
                    if (gezagsrelatie.getBsnMinderjarige().equals(gezag.getMinderjarige().get().getBurgerservicenummer())) {
                        return gezag;
                    }
                }
            }
        }
        return null;
    }
}