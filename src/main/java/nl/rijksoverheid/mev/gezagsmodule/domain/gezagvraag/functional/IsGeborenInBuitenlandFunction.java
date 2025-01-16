package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v1_3a "Ja" als de persoon in het buitenland is geboren, anders "Nee".
 */
public class IsGeborenInBuitenlandFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(
            IsGeborenInBuitenlandFunction.class);
    // Vaste waarden
    private static final String QUESTION_ID = "v1.3a";
    private static final String GEBOORTELAND_CODE_NEDERLAND = "6030";
    private static final String V1_3A_JA = "Ja";
    private static final String V1_3A_NEE = "Nee";

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {
        // Haal de persoonslijst op
        var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // Ofwel throwen we een exception:
            throw new IllegalStateException("Persoonslijst van bevraagde persoon ontbreekt.");
            // Of je geeft een 'leeg' resultaat terug:
            // return new GezagVraagResult(QUESTION_ID, null);
        }
        // Check geboorteland
        var geboorteland = plPersoon.getPersoon().getGeboorteland();
        if (geboorteland == null || geboorteland.isEmpty()) {
            // Missing data -> vul je missendeGegevens-lijst aan
            gezagsBepaling.addMissendeGegegevens("Geboorteland van bevraagde persoon");
            // Eventueel hier: return een 'null'-antwoord of throw
            return new GezagVraagResult(QUESTION_ID, null);
        }
        // Bepaal het antwoord
        String answer = geboorteland.equals(GEBOORTELAND_CODE_NEDERLAND) ? V1_3A_NEE : V1_3A_JA;
        // Logging
        logger.debug("1.3a Is minderjarige geboren in het buitenland? -> {}", answer);
        // (Optioneel) mutatie in 'gezagsBepaling'
        gezagsBepaling.getArAntwoordenModel().setV0103A(answer);
        // Retourneer een immutable resultaat
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}

