package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import java.util.Objects;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.HuwelijkOfPartnerschap;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * v2a_1 Mogelijke antwoorden:
 *  - "Ja"        (Ja, gehuwd of partners)
 *  - "Nee"
 *  - "Nee_nooit" (Nee, na geboorte nooit gehuwd/partners geweest met elkaar)
 */
@Component
@Scope("prototype")
public class ZijnJuridischeOudersNuMetElkaarGehuwdOfPartnersFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(
            ZijnJuridischeOudersNuMetElkaarGehuwdOfPartnersFunction.class);

    private static final String QUESTION_ID = "v2a.1";

    private static final String V2A_1_JA_GEHUWD_OF_PARTNERS = "Ja";
    private static final String V2A_1_NEE = "Nee";
    private static final String V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR
            = "Nee_nooit";

    @Override
    public String getQuestionId() {
        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        // 1) Haal de benodigde gegevens op
        final var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            throw new IllegalStateException("Persoonslijst van bevraagde persoon ontbreekt.");
        }

        final var geboortedatumKind = plPersoon.getPersoon().getGeboortedatum();

        // 2) Preconditie-check: ouders geregistreerd?
        preconditieCheckOudersGeregistreerd(gezagsBepaling);

        // 3) Ophalen van PL's van de ouders
        final var plOuder1 = gezagsBepaling.getPlOuder1();
        final var plOuder2 = gezagsBepaling.getPlOuder2();

        // 4) Is er een huwelijk of partnerschap tussen de twee PL's?
        final var hopOuder1 = ouderGetHetHuwelijkOfPartnerschap(plOuder1, plOuder2);
        final var hopOuder2 = ouderGetHetHuwelijkOfPartnerschap(plOuder2, plOuder1);

        // 5) Bepaal het antwoord
        String answer;
        //   5a) Als er geen huwelijk tussen de ouders gevonden wordt => "Nee_nooit"
        if (hopOuder1 == null || hopOuder2 == null) {
            answer = V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR;
        }
        //   5b) Als het huwelijk/partnerschap nog actueel is bij beide ouders
        else if (isHuwelijkOfPartnerschapTussenOudersActueel(hopOuder1, hopOuder2)) {
            // Check of geen van beide ouders overleden is
            if (!plOuder1.isOverleden() && !plOuder2.isOverleden()) {
                answer = V2A_1_JA_GEHUWD_OF_PARTNERS;
            } else {
                answer = V2A_1_NEE;
            }
        }
        //   5c) Anders check of ze gescheiden zijn vóór geboortedatum kind
        else if (ouderGescheiden(hopOuder1, geboortedatumKind)
                || ouderGescheiden(hopOuder2, geboortedatumKind)) {
            answer = V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR;
        }
        //   5d) Restgeval => "Nee"
        else {
            answer = V2A_1_NEE;
        }

        // 6) Log en zet in ArAntwoordenModel
        logger.debug("2a.1 Zijn beide juridische ouders nu met elkaar gehuwd/partners? -> {}", answer);
        gezagsBepaling.getArAntwoordenModel().setV02A01(answer);

        // 7) Retourneer het functionele resultaat
        return new GezagVraagResult(QUESTION_ID, answer);
    }

    // ----------- Private Helpers ----------- //

    private void preconditieCheckOudersGeregistreerd(final GezagsBepaling gezagsBepaling) {
        final var plPersoon = gezagsBepaling.getPlPersoon();
        if (!plPersoon.heeftTweeOuders()) {
            throw new AfleidingsregelException(
                    "Preconditie: Kind moet twee ouders hebben",
                    "Van de bevraagde persoon zijn geen twee ouders bekend");
        }
        preconditieCheckGeregistreerd("ouder1", gezagsBepaling.getPlOuder1());
        preconditieCheckGeregistreerd("ouder2", gezagsBepaling.getPlOuder2());
    }

    private void preconditieCheckGeregistreerd(final String beschrijving, final Persoonslijst plOuder) {
        final var ouderGeregistreerdInBrp = plOuder != null
                && plOuder.isNietIngeschrevenInRNI()
                && plOuder.isNietGeemigreerd();

        if (!ouderGeregistreerdInBrp) {
            throw new AfleidingsregelException(
                    "Preconditie: " + beschrijving + " moet in BRP geregistreerd staan",
                    beschrijving + " van bevraagde persoon is niet in BRP geregistreerd");
        }
    }

    private HuwelijkOfPartnerschap ouderGetHetHuwelijkOfPartnerschap(
            final Persoonslijst persoonslijst1, final Persoonslijst persoonslijst2) {

        if (persoonslijst1 == null || persoonslijst2 == null) {
            return null;
        }
        for (final var hop : persoonslijst1.getHuwelijkOfPartnerschappen()) {
            if (persoonslijst2.getPersoon() != null
                    && Objects.equals(persoonslijst2.getPersoon().getBurgerservicenummer(),
                    hop.getBsnPartner())) {
                return hop;
            }
        }
        return null;
    }

    private boolean ouderGescheiden(final HuwelijkOfPartnerschap hop, final String geboortedatumKind) {
        if (hop == null) {
            return false;
        }
        if (hop.getRedenOntbinding() != null && "S".equals(hop.getRedenOntbinding())) {
            return Integer.parseInt(hop.getDatumOntbinding()) < Integer.parseInt(geboortedatumKind);
        }
        return false;
    }

    private boolean isHuwelijkOfPartnerschapTussenOudersActueel(
            final HuwelijkOfPartnerschap hopOuder1, final HuwelijkOfPartnerschap hopOuder2) {

        // Alleen als beide huwelijken/partnerschappen 'actueel' zijn (datumVoltrokken is notBlank),
        // dan is er sprake van een actueel huwelijk/partnerschap.
        return org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder1.getDatumVoltrokken())
                && org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder2.getDatumVoltrokken());
    }
}
