package nl.rijksoverheid.mev.gezagsmodule.service;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.openapitools.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (hasRequired(arAntwoordenModel, gezagsBepaling)) {
            return gezagsrelaties;
        }

        String burgerservicenummer = gezagsBepaling.getBurgerservicenummer();
        String burgerservicenummerPersoon = gezagsBepaling.getBurgerservicenummerPersoon();
        String burgerservicenummerOuder1 = gezagsBepaling.getBurgerservicenummerOuder1();
        String burgerservicenummerOuder2 = gezagsBepaling.getBurgerservicenummerOuder2();
        String burgerservicenummerNietOuder = gezagsBepaling.getBurgerservicenummerNietOuder();

        boolean bevraagdePersoonIsDeMinderjarige = burgerservicenummer.equals(burgerservicenummerPersoon);
        if (tenminsteEenRelatieMetPersoon(bevraagdePersoonIsDeMinderjarige, burgerservicenummerPersoon, burgerservicenummerOuder1, burgerservicenummerOuder2, burgerservicenummerNietOuder)) {
            String soortGezag = arAntwoordenModel.getSoortGezag();
            switch (soortGezag) {
                case "OG1" -> createEenhoofdelijkOuderlijkGezag(
                    arAntwoordenModel,
                    bevraagdePersoonIsDeMinderjarige,
                    burgerservicenummerOuder1,
                    burgerservicenummerOuder2,
                    burgerservicenummer,
                    burgerservicenummerPersoon,
                    gezagsrelaties);
                case "OG2" -> createTweehoofdigOuderlijkGezag(
                    burgerservicenummer,
                    burgerservicenummerOuder1,
                    burgerservicenummerOuder2,
                    gezagsrelaties);
                case "GG" -> createGezamenlijkGezag(
                    burgerservicenummer,
                    burgerservicenummerNietOuder,
                    arAntwoordenModel.hasOuder1Gezag(),
                    burgerservicenummerOuder1,
                    burgerservicenummerOuder2,
                    gezagsrelaties);
                case "V" -> createVoogdij(
                    bevraagdePersoonIsDeMinderjarige,
                    burgerservicenummerPersoon,
                    burgerservicenummerNietOuder,
                    burgerservicenummer,
                    arAntwoordenModel.hasNietOuderGezag(),
                    gezagsrelaties);
                case "G" ->
                    createTijdelijkeGeenGezag(bevraagdePersoonIsDeMinderjarige, burgerservicenummer, arAntwoordenModel.getUitleg(), gezagsrelaties);
                case "N" ->
                    createGezagNietTeBepalen(bevraagdePersoonIsDeMinderjarige, burgerservicenummer, arAntwoordenModel.getUitleg(), gezagsrelaties);
                case "NVT" -> logger.info("Gezag uitkomst is NVT");
                default -> logger.warn("Onverwachte gezagsoort ontvangen: {}", soortGezag);
            }
        }

        return gezagsrelaties;
    }

    private boolean hasRequired(final ARAntwoordenModel arAntwoordenModel, final GezagsBepaling gezagsBepaling) {
        return arAntwoordenModel == null ||
            gezagsBepaling == null ||
            gezagsBepaling.getBurgerservicenummer() == null ||
            gezagsBepaling.getBurgerservicenummerPersoon() == null;
    }

    private void createEenhoofdelijkOuderlijkGezag(
        final ARAntwoordenModel arAntwoordenModel,
        final boolean bevraagdePersoonIsDeMinderjarige,
        final String burgerservicenummerOuder1,
        final String burgerservicenummerOuder2,
        final String burgerservicenummer,
        final String burgerservicenummerPersoon,
        final List<AbstractGezagsrelatie> gezagsrelaties) {
        if (arAntwoordenModel.hasOuder1Gezag() && burgerservicenummerOuder1 != null && (bevraagdePersoonIsDeMinderjarige || burgerservicenummerPersoon.equals(burgerservicenummerOuder1))) {
            AbstractGezagsrelatie gezag = new EenhoofdigOuderlijkGezag()
                .ouder(new GezagOuder().burgerservicenummer(burgerservicenummerOuder1))
                .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                .type(TYPE_EENHOOFDIG_OUDERLIJK_GEZAG);
            gezagsrelaties.add(gezag);
        }
        if (arAntwoordenModel.hasOuder2Gezag() && burgerservicenummerOuder2 != null && (bevraagdePersoonIsDeMinderjarige || burgerservicenummerPersoon.equals(burgerservicenummerOuder2))) {
            AbstractGezagsrelatie gezag = new EenhoofdigOuderlijkGezag()
                .ouder(new GezagOuder().burgerservicenummer(burgerservicenummerOuder2))
                .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                .type(TYPE_EENHOOFDIG_OUDERLIJK_GEZAG);
            gezagsrelaties.add(gezag);
        }
    }

    private void createTweehoofdigOuderlijkGezag(
        final String burgerservicenummer,
        final String burgerservicenummerOuder1,
        final String burgerservicenummerOuder2,
        final List<AbstractGezagsrelatie> gezagsrelaties) {
        AbstractGezagsrelatie gezag = new TweehoofdigOuderlijkGezag()
            .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
            .addOudersItem(new GezagOuder().burgerservicenummer(burgerservicenummerOuder1))
            .addOudersItem(new GezagOuder().burgerservicenummer(burgerservicenummerOuder2))
            .type(TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG);

        gezagsrelaties.add(gezag);
    }

    private void createGezamenlijkGezag(
        final String burgerservicenummer,
        final String burgerservicenummerNietOuder,
        final boolean ouder1Gezag,
        final String burgerservicenummerOuder1,
        final String burgerservicenummerOuder2,
        final List<AbstractGezagsrelatie> gezagsrelaties) {
        GezamenlijkGezag gezag = new GezamenlijkGezag()
            .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
            .type(TYPE_GEZAMELIJK_GEZAG);

        if (burgerservicenummerNietOuder != null) {
            gezag.setDerde(Optional.of(new Meerderjarige().burgerservicenummer(burgerservicenummerNietOuder)));
        }

        if (ouder1Gezag && burgerservicenummerOuder1 != null) {
            gezag.ouder(new GezagOuder().burgerservicenummer(burgerservicenummerOuder1));
        } else {
            gezag.ouder(new GezagOuder().burgerservicenummer(burgerservicenummerOuder2));
        }

        gezagsrelaties.add(gezag);
    }

    private void createVoogdij(
        final boolean bevraagdePersoonIsDeMinderjarige,
        final String burgerservicenummerPersoon,
        final String burgerservicenummerNietOuder,
        final String burgerservicenummer,
        final boolean nietOuderGezag,
        final List<AbstractGezagsrelatie> gezagsrelaties) {
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

    private void createTijdelijkeGeenGezag(
        final boolean bevraagdePersoonIsDeMinderjarige,
        final String burgerservicenummer,
        final String uitleg,
        final List<AbstractGezagsrelatie> gezagsrelaties) {
        if (bevraagdePersoonIsDeMinderjarige) {
            gezagsrelaties.add(
                new TijdelijkGeenGezag()
                    .type(TYPE_TIJDELIJK_GEEN_GEZAG)
                    .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                    .toelichting(uitleg)
            );
        }
    }

    private void createGezagNietTeBepalen(
        final boolean bevraagdePersoonIsDeMinderjarige,
        final String burgerservicenummer,
        final String uitleg,
        final List<AbstractGezagsrelatie> gezagsrelaties) {
        if (bevraagdePersoonIsDeMinderjarige) {
            gezagsrelaties.add(
                new GezagNietTeBepalen()
                    .type(TYPE_GEZAG_NIET_TE_BEPALEN)
                    .minderjarige(new Minderjarige().burgerservicenummer(burgerservicenummer))
                    .toelichting(uitleg)
            );
        }
    }

    private boolean tenminsteEenRelatieMetPersoon(
        final boolean bevraagdePersoonIsDeMinderjarige,
        final String burgerservicenummerPersoon,
        final String burgerservicenummerOuder1,
        final String burgerservicenummerOuder2,
        final String burgerservicenummerNietOuder) {
        return bevraagdePersoonIsDeMinderjarige ||
            burgerservicenummerPersoon.equals(burgerservicenummerOuder1) ||
            burgerservicenummerPersoon.equals(burgerservicenummerOuder2) ||
            burgerservicenummerPersoon.equals(burgerservicenummerNietOuder);
    }
}
