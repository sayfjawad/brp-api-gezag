package nl.rijksoverheid.mev.gezagsmodule.service.gezagmodule;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v1_3b
 *
 * @return "Ja" als is geadopteerd met nederlandse akte anders "Nee"
 */
public class IsGeadopteerdMetNlAkte extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(IsGeadopteerdMetNlAkte.class);

    private static final String V1_3B_JA = "Ja";
    private static final String V1_3B_NEE = "Nee";

    protected IsGeadopteerdMetNlAkte(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v1.3b";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        if (plPersoon.geadopteerdMetNlAkte()) {
            answer = V1_3B_JA;
        } else {
            answer = V1_3B_NEE;
        }

        logger.debug("""
            1.3b Is deze minderjarige geadopteerd met een NL-akte?
            {}""", answer);
        gezagBepaling.getArAntwoordenModel().setV0103B(answer);
    }
}
