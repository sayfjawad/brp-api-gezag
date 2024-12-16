package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v2a_2
 * "Ja" als is adoptief ouders, anders "Nee"
 */
public class AdoptiefOuders extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(AdoptiefOuders.class);

    private static final String V2A_2_JA = "Ja";
    private static final String V2A_2_NEE = "Nee";

    protected AdoptiefOuders(final GezagsBepaling gezagsBepaling) {
        super(gezagsBepaling);
        currentQuestion = "v2a.2";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();

        answer = (plPersoon.geadopteerdMetNlAkte()) ? V2A_2_JA : V2A_2_NEE;

        logger.debug("""
            2a.2 Adoptiefouder(s)?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV02A02(answer);
    }
}