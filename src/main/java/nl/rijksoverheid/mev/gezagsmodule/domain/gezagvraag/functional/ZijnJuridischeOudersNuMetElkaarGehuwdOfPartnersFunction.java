package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import java.util.Objects;
import nl.rijksoverheid.mev.gezagsmodule.domain.HuwelijkOfPartnerschap;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v2a_1 Mogelijke antwoorden: - "Ja"  (Ja, gehuwd of partners) - "Nee" - "Nee_nooit" (Nee, na
 * geboorte nooit gehuwd/partners geweest met elkaar)
 */
public class ZijnJuridischeOudersNuMetElkaarGehuwdOfPartnersFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(
            ZijnJuridischeOudersNuMetElkaarGehuwdOfPartnersFunction.class);
    private static final String QUESTION_ID = "v2a.1";
    private static final String V2A_1_JA_GEHUWD_OF_PARTNERS = "Ja";
    private static final String V2A_1_NEE = "Nee";
    private static final String V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR
            = "Nee_nooit";

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {
        // 1) Haal de benodigde gegevens op
        var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // Kies zelf: throw exception, return error-result, etc.
            throw new IllegalStateException("Persoonslijst van bevraagde persoon ontbreekt.");
        }
        String geboortedatumKind = plPersoon.getPersoon().getGeboortedatum();
        // 2) Preconditie-check: ouders geregistreerd?
        preconditieCheckOudersGeregistreerd(gezagsBepaling);
        // 3) Ophalen van PL's van de ouders
        var plOuder1 = gezagsBepaling.getPlOuder1();
        var plOuder2 = gezagsBepaling.getPlOuder2();
        // 4) De (private) helper om te kijken of er een huwelijk of partnerschap is tussen de twee PL's
        HuwelijkOfPartnerschap hopOuder1 = ouderGetHetHuwelijkOfPartnerschap(plOuder1, plOuder2);
        HuwelijkOfPartnerschap hopOuder2 = ouderGetHetHuwelijkOfPartnerschap(plOuder2, plOuder1);
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
        else if (ouderGescheiden(hopOuder1, geboortedatumKind) || ouderGescheiden(hopOuder2,
                geboortedatumKind)) {
            answer = V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR;
        }
        //   5d) Restgeval => "Nee"
        else {
            answer = V2A_1_NEE;
        }
        // 6) Log en zet in ArAntwoordenModel
        logger.debug("2a.1 Zijn beide juridische ouders nu met elkaar gehuwd/partners? -> {}",
                answer);
        gezagsBepaling.getArAntwoordenModel().setV02A01(answer);
        // 7) Retourneer het functionele resultaat (questionId + answer)
        return new GezagVraagResult(QUESTION_ID, answer);
    }
    // -- Private helpers (verplaatst vanuit de oorspronkelijke code) -- //

    /**
     * Dezelfde preconditie-checks als in de originele code, maar nu als private helper.
     */
    private void preconditieCheckOudersGeregistreerd(GezagsBepaling gezagsBepaling) {
        // In de oude code deed je: preconditieCheckOudersGeregistreerd() in GezagVraag
        // Nu kun je het in een private method doen of in een utility-class.
        var plPersoon = gezagsBepaling.getPlPersoon();
        if (!plPersoon.heeftTweeOuders()) {
            throw new IllegalStateException("Preconditie: Kind moet twee ouders hebben");
        }
        preconditieCheckGeregistreerd("ouder1", gezagsBepaling.getPlOuder1());
        preconditieCheckGeregistreerd("ouder2", gezagsBepaling.getPlOuder2());
    }

    private void preconditieCheckGeregistreerd(final String beschrijving,
            final Persoonslijst plOuder) {

        boolean ouderGeregistreerdInBrp =
                plOuder != null
                        && plOuder.isNietIngeschrevenInRNI()
                        && plOuder.isNietGeemigreerd();
        if (!ouderGeregistreerdInBrp) {
            throw new IllegalStateException(
                    "Preconditie: " + beschrijving + " moet in BRP geregistreerd staan");
        }
    }

    private HuwelijkOfPartnerschap ouderGetHetHuwelijkOfPartnerschap(Persoonslijst persoonslijst1,
            Persoonslijst persoonslijst2) {

        if (persoonslijst1 == null || persoonslijst2 == null) {
            return null;
        }
        for (HuwelijkOfPartnerschap hop : persoonslijst1.getHuwelijkOfPartnerschappen()) {
            if (persoonslijst2.getPersoon() != null
                    && Objects.equals(persoonslijst2.getPersoon().getBurgerservicenummer(),
                    hop.getBsnPartner())) {
                return hop;
            }
        }
        return null;
    }

    private boolean ouderGescheiden(HuwelijkOfPartnerschap hop, String geboortedatumKind) {

        if (hop == null) {
            return false;
        }
        if (hop.getRedenOntbinding() != null && "S".equals(hop.getRedenOntbinding())) {
            return Integer.parseInt(hop.getDatumOntbinding()) < Integer.parseInt(geboortedatumKind);
        }
        return false;
    }

    private boolean isHuwelijkOfPartnerschapTussenOudersActueel(HuwelijkOfPartnerschap hopOuder1,
            HuwelijkOfPartnerschap hopOuder2) {
        // Alleen als beide huwelijken/partnerschappen 'actueel' zijn (datumVoltrokken is notBlank),
        // dan is er sprake van actueel huwelijk/partnerschap.
        return org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder1.getDatumVoltrokken())
                && org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder2.getDatumVoltrokken());
    }
}
