package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v1_4
 * JA/NEE of IN_ONDERZOEK
 */
public class IsUitspraakGezagAanwezig extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(IsUitspraakGezagAanwezig.class);

    private static final String V1_4_JA = "Ja";
    private static final String V1_4_NEE = "Nee";

    protected IsUitspraakGezagAanwezig(final GezagsBepaling gezagsBepaling) {
        super(gezagsBepaling);
        currentQuestion = "v1.4";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();

        if (plPersoon.heefIndicatieGezag()) {
            answer = V1_4_JA;
        } else {
            answer = V1_4_NEE;
        }

        logger.debug("""
            1.4 Uitspraak gezag aanwezig?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV0104(answer);
    }
}
