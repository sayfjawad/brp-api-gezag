package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * v1_3b "Ja" als is geadopteerd met Nederlandse akte, anders "Nee"
 */
@Component
public class IsGeadopteerdMetNlAkte implements GezagVraag {

    private static final Logger logger =
            LoggerFactory.getLogger(IsGeadopteerdMetNlAkte.class);
    private static final String QUESTION_ID = "v1.3b";
    private static final String V1_3B_JA = "Ja";
    private static final String V1_3B_NEE = "Nee";

    @Override
    public String getQuestionId() {

        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {

        final var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            throw new IllegalStateException("Persoonslijst ontbreekt in gezagsBepaling");
        }
        // Bepaal of de persoon is geadopteerd met een NL-akte
        final var answer = plPersoon.geadopteerdMetNlAkte() ? V1_3B_JA : V1_3B_NEE;
        // Logging
        logger.debug("1.3b Is deze minderjarige geadopteerd met een NL-akte? -> {}", answer);
        // (Optioneel) mutatie in het oude model
        gezagsBepaling.getArAntwoordenModel().setV0103B(answer);
        // Geef een functioneel resultaat (vraag + antwoord)
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
