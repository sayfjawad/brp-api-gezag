package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v4a_2
 * JA_OUDER1/JA_OUDER2/NEE/BEIDEN/ONBEKEND
 */
public class OudersOverledenOfOnbevoegdTotGezag extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(OudersOverledenOfOnbevoegdTotGezag.class);

    private static final String V4A_2_JA_BEIDEN = "Ja_beiden";
    private static final String V4A_2_JA_OUDER1 = "Ja_ouder1";
    private static final String V4A_2_JA_OUDER2 = "Ja_ouder2";
    private static final String V4A_2_NEE = "Nee";

    protected OudersOverledenOfOnbevoegdTotGezag(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v4a.2";
    }

    @Override
    public void perform() {
        preconditieCheckOudersGeregistreerd();
        boolean ouder1OverledenOfOnbevoegd = gezagBepaling.getPlOuder1().isOverledenOfOnbevoegd();
        boolean ouder2OverledenOfOnbevoegd = gezagBepaling.getPlOuder2().isOverledenOfOnbevoegd();
        if (ouder1OverledenOfOnbevoegd && ouder2OverledenOfOnbevoegd) {
            answer = V4A_2_JA_BEIDEN;
        } else if (ouder1OverledenOfOnbevoegd) {
            answer = V4A_2_JA_OUDER1;
        } else if (ouder2OverledenOfOnbevoegd) {
            answer = V4A_2_JA_OUDER2;
        } else {
            answer = V4A_2_NEE;
        }

        logger.debug("""
            4a.2 Ouders overleden of onbevoegd tot gezag?
            {}""", answer);
        gezagBepaling.getArAntwoordenModel().setV04A02(answer);
    }
}