package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * v1_3a "Ja" als de persoon in het buitenland is geboren, anders "Nee".
 */
@Component
public class IsGeborenInBuitenland implements GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(IsGeborenInBuitenland.class);
    private static final String QUESTION_ID = "v1.3a";
    private static final String GEBOORTELAND_CODE_NEDERLAND = "6030";
    private static final String V1_3A_JA = "Ja";
    private static final String V1_3A_NEE = "Nee";

    @Override
    public String getQuestionId() {
        return QUESTION_ID;
    }
    
    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        final var plPersoon = gezagsBepaling.getPlPersoon();
        final var geboorteland = plPersoon.getPersoon().getGeboorteland();
        if (geboorteland == null || geboorteland.isEmpty()) {
            gezagsBepaling.addMissendeGegegevens("Geboorteland van bevraagde persoon");
            return new GezagVraagResult(QUESTION_ID, null);
        }
        final var answer = geboorteland.equals(GEBOORTELAND_CODE_NEDERLAND)
            ? V1_3A_NEE
            : V1_3A_JA;

        logger.debug("""
            1.3a Is minderjarige geboren in het buitenland?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV0103A(answer);
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
