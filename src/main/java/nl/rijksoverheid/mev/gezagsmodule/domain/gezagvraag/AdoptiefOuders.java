package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * v2a_2
 * "Ja" als is adoptief ouders, anders "Nee"
 */
@Component
public class AdoptiefOuders implements GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(AdoptiefOuders.class);
    private static final String QUESTION_ID = "v2a.2";
    private static final String V2A_2_JA = "Ja";
    private static final String V2A_2_NEE = "Nee";

    @Override
    public String getQuestionId() {
        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        final var plPersoon = gezagsBepaling.getPlPersoon();

        String answer = (plPersoon.geadopteerdMetNlAkte()) ? V2A_2_JA : V2A_2_NEE;

        logger.debug("""
            2a.2 Adoptiefouder(s)?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV02A02(answer);
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
