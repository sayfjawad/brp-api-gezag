package nl.rijksoverheid.mev.gezagsmodule.service;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.openapitools.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GezagsrelatieService {

    private static final Logger logger = LoggerFactory.getLogger(GezagsrelatieService.class);
    private static final String TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG = "TweehoofdigOuderlijkGezag";
    private static final String TYPE_EENHOOFDIG_OUDERLIJK_GEZAG = "EenhoofdigOuderlijkGezag";
    private static final String TYPE_GEZAMELIJK_GEZAG = "GezamenlijkGezag";
    private static final String TYPE_VOOGDIJ = "Voogdij";
    private static final String TYPE_TIJDELIJK_GEEN_GEZAG = "TijdelijkGeenGezag";
    private static final String TYPE_GEZAG_NIET_TE_BEPALEN = "GezagNietTeBepalen";

    /**
     * Bepaal op basis van het antwoorden model (resultaat van een gezagsbepaling) wat de gezagsrelaties zijn voor de persoon
     *
     * @param arAntwoordenModel resultaat van de gezagsbepaling
     * @param gezagsBepaling    de (uitgevoerde gezags bepaling
     * @return de gezagsrelaties of leeg
     */
    public List<AbstractGezagsrelatie> bepaalGezagsrelaties(
        final ARAntwoordenModel arAntwoordenModel,
        final GezagsBepaling gezagsBepaling) {
        List<AbstractGezagsrelatie> gezagsrelaties = new ArrayList<>();
        if (arAntwoordenModel == null ||  gezagsBepaling == null) {
            return gezagsrelaties;
        }

        String burgerservicenummer = gezagsBepaling.getBurgerservicenummer();
        String burgerservicenummerPersoon = gezagsBepaling.getBurgerservicenummerPersoon();
        String burgerservicenummerOuder1 = gezagsBepaling.getBurgerservicenummerOuder1();
        String burgerservicenummerOuder2 = gezagsBepaling.getBurgerservicenummerOuder2();
        String burgerservicenummerNietOuder = gezagsBepaling.getBurgerservicenummerNietOuder();

        boolean bevraagdePersoonIsDeMinderjarige = burgerservicenummer.equals(burgerservicenummerPersoon);
        if (arAntwoordenModel != null &&
            (bevraagdePersoonIsDeMinderjarige || tenminsteEenRelatieMetPersoon(burgerservicenummerPersoon, burgerservicenummerOuder1, burgerservicenummerOuder2, burgerservicenummerNietOuder))) {
            String soortGezag = arAntwoordenModel.getSoortGezag();

            boolean ouder1Gezag = arAntwoordenModel.getGezagOuder1() != null && arAntwoordenModel.getGezagOuder1().equals("Ja")
                && (burgerservicenummerOuder1 != null);
            boolean ouder2Gezag = arAntwoordenModel.getGezagOuder2() != null && arAntwoordenModel.getGezagOuder2().equals("Ja")
                && (burgerservicenummerOuder2 != null);
            boolean nietOuderGezag = (((arAntwoordenModel.getGezagNietOuder1() != null && arAntwoordenModel.getGezagNietOuder1().equals("Ja")))
                && (burgerservicenummerNietOuder != null));


            switch (soortGezag) {
                case "OG1" -> {
                    if (ouder1Gezag && (bevraagdePersoonIsDeMinderjarige || burgerservicenummerPersoon.equals(burgerservicenummerOuder1))) {
                        AbstractGezagsrelatie gezag = new EenhoofdigOuderlijkGezag()
                            .ouder(new GezagOuder().burgerservicenummer(burgerservicenummerOuder1))
                            .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                            .type(TYPE_EENHOOFDIG_OUDERLIJK_GEZAG);

                        gezagsrelaties.add(gezag);
                    }

                    if (ouder2Gezag && (bevraagdePersoonIsDeMinderjarige || burgerservicenummerPersoon.equals(burgerservicenummerOuder2))) {
                        AbstractGezagsrelatie gezag = new EenhoofdigOuderlijkGezag()
                            .ouder(new GezagOuder().burgerservicenummer(burgerservicenummerOuder2))
                            .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                            .type(TYPE_EENHOOFDIG_OUDERLIJK_GEZAG);

                        gezagsrelaties.add(gezag);
                    }
                }
                case "OG2" -> {
                    AbstractGezagsrelatie gezag = new TweehoofdigOuderlijkGezag()
                        .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                        .addOudersItem(new GezagOuder().burgerservicenummer(burgerservicenummerOuder1))
                        .addOudersItem(new GezagOuder().burgerservicenummer(burgerservicenummerOuder2))
                        .type(TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG);

                    gezagsrelaties.add(gezag);
                }
                case "GG" -> {
                    GezamenlijkGezag gezag = new GezamenlijkGezag()
                        .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                        .derde(new Meerderjarige().burgerservicenummer(burgerservicenummerNietOuder))
                        .type(TYPE_GEZAMELIJK_GEZAG);

                    if (ouder1Gezag) {
                        gezag.ouder(new GezagOuder().burgerservicenummer(burgerservicenummerOuder1));
                    } else {
                        gezag.ouder(new GezagOuder().burgerservicenummer(burgerservicenummerOuder2));
                    }

                    gezagsrelaties.add(gezag);
                }
                case "V" -> {
                    if (bevraagdePersoonIsDeMinderjarige || burgerservicenummerPersoon.equals(burgerservicenummerNietOuder)) {
                        Voogdij gezag = new Voogdij()
                            .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                            .type(TYPE_VOOGDIJ);

                        if (nietOuderGezag && burgerservicenummerNietOuder != null) {
                            gezag.addDerdenItem(new Meerderjarige().burgerservicenummer(burgerservicenummerNietOuder));
                        }

                        gezagsrelaties.add(gezag);
                    }
                }
                case String s when s.equals("G") && bevraagdePersoonIsDeMinderjarige -> gezagsrelaties.add(
                    new TijdelijkGeenGezag()
                        .type(TYPE_TIJDELIJK_GEEN_GEZAG)
                        .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                );
                case String s when s.equals("N") && bevraagdePersoonIsDeMinderjarige -> gezagsrelaties.add(
                    new GezagNietTeBepalen()
                        .type(TYPE_GEZAG_NIET_TE_BEPALEN)
                        .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                        .toelichting(arAntwoordenModel.getUitleg())
                );
                case "NVT" -> logger.info("Gezag uitkomst is NVT");
                default -> logger.warn("Onverwachte gezagsoort ontvangen: {}", soortGezag);
            }
        }

        return gezagsrelaties;
    }

    private boolean tenminsteEenRelatieMetPersoon(
        final String burgerservicenummerPersoon,
        final String burgerservicenummerOuder1,
        final String burgerservicenummerOuder2,
        final String burgerservicenummerNietOuder) {
        return burgerservicenummerPersoon.equals(burgerservicenummerOuder1) ||
            burgerservicenummerPersoon.equals(burgerservicenummerOuder2) ||
            burgerservicenummerPersoon.equals(burgerservicenummerNietOuder);
    }
}
