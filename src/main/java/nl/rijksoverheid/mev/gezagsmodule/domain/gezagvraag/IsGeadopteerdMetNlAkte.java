package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v1_3b
 * "Ja" als is geadopteerd met nederlandse akte anders "Nee"
 */
public class IsGeadopteerdMetNlAkte extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(IsGeadopteerdMetNlAkte.class);

    private static final String V1_3B_JA = "Ja";
    private static final String V1_3B_NEE = "Nee";

    protected IsGeadopteerdMetNlAkte(final GezagsBepaling gezagsBepaling) {
        super(gezagsBepaling);
        currentQuestion = "v1.3b";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();

        if (plPersoon.geadopteerdMetNlAkte()) {
            answer = V1_3B_JA;
        } else {
            answer = V1_3B_NEE;
        }

        logger.debug("""
            1.3b Is deze minderjarige geadopteerd met een NL-akte?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV0103B(answer);
    }
}
