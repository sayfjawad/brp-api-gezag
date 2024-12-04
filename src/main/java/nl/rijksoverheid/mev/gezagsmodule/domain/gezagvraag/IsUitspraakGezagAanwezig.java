package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * sv1_4
 * JA/NEE of IN_ONDERZOEK
 */
public class IsUitspraakGezagAanwezig extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(IsUitspraakGezagAanwezig.class);

    private static final String V1_4_JA = "Ja";
    private static final String V1_4_NEE = "Nee";

    protected IsUitspraakGezagAanwezig(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v1.4";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        if (plPersoon.heefIndicatieGezag()) {
            answer = V1_4_JA;
        } else {
            answer = V1_4_NEE;
        }

        logger.debug("""
            1.4 Uitspraak gezag aanwezig?
            {}""", answer);
        gezagBepaling.getArAntwoordenModel().setV0104(answer);
    }
}
