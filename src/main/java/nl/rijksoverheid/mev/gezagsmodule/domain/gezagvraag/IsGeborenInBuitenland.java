package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * v1_3a "Ja" als de persoon in het buitenland is geboren, anders "Nee".
 */
@Component
@Scope("prototype")
public class IsGeborenInBuitenland implements GezagVraag {

    private static final Logger logger =
            LoggerFactory.getLogger(IsGeborenInBuitenland.class);

    private static final String QUESTION_ID                   = "v1.3a";
    private static final String GEBOORTELAND_CODE_NEDERLAND   = "6030";
    private static final String V1_3A_JA                      = "Ja";
    private static final String V1_3A_NEE                     = "Nee";

    @Override
    public String getQuestionId() {
        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        // Haal de persoonslijst op
        final var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // Ofwel throwen we een exception:
            throw new IllegalStateException("Persoonslijst van bevraagde persoon ontbreekt.");
            // Of je geeft een 'leeg' resultaat terug:
            // return new GezagVraagResult(QUESTION_ID, null);
        }

        // Check geboorteland
        final var geboorteland = plPersoon.getPersoon().getGeboorteland();
        if (geboorteland == null || geboorteland.isEmpty()) {
            // Missing data -> vul je missendeGegevens-lijst aan
            gezagsBepaling.addMissendeGegegevens("Geboorteland van bevraagde persoon");
            // Eventueel hier: return een 'null'-antwoord of throw
            return new GezagVraagResult(QUESTION_ID, null);
        }

        // Bepaal het antwoord
        final var answer = geboorteland.equals(GEBOORTELAND_CODE_NEDERLAND)
                ? V1_3A_NEE
                : V1_3A_JA;

        // Logging
        logger.debug("1.3a Is minderjarige geboren in het buitenland? -> {}", answer);

        // (Optioneel) mutatie in 'gezagsBepaling'
        gezagsBepaling.getArAntwoordenModel().setV0103A(answer);

        // Retourneer een immutable resultaat
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
