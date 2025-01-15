package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * v4a_2
 * JA_OUDER1/JA_OUDER2/NEE/BEIDEN/ONBEKEND
 */
public class OudersOverledenOfOnbevoegdTotGezag extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(OudersOverledenOfOnbevoegdTotGezag.class);

    private static final String V4A_2_JA_OUDER1 = "Ja_ouder1";
    private static final String V4A_2_JA_OUDER2 = "Ja_ouder2";
    private static final String V4A_2_NEE = "Nee";
    private static final Map<String, String> JA_BEIDEN_ANTWOORDEN = Map.of(
        "cc", "Ja_beiden_onder_curatele",
        "cm", "Ja_ouder_onder_curatele_en_andere_ouder_minderjarig",
        "mc", "Ja_ouder_onder_curatele_en_andere_ouder_minderjarig",
        "co", "Ja_ouder_overleden_en_andere_ouder_onder_curatele",
        "oc", "Ja_ouder_overleden_en_andere_ouder_onder_curatele",
        "mm", "Ja_beiden_minderjarig",
        "mo", "Ja_ouder_overleden_en_andere_ouder_minderjarig",
        "om", "Ja_ouder_overleden_en_andere_ouder_minderjarig",
        "oo", "Ja_beiden_overleden"
    );

    protected OudersOverledenOfOnbevoegdTotGezag(final GezagsBepaling gezagsBepaling) {
        super(gezagsBepaling);
        currentQuestion = "v4a.2";
    }

    @Override
    public void perform() {
        preconditieCheckOudersGeregistreerd();

        var optionalIsOuder1OverledenOfOnbevoegdToken = gezagsBepaling.getPlOuder1().isOverledenOfOnbevoegdEncoded();
        var optionalIsOuder2OverledenOfOnbevoegdToken = gezagsBepaling.getPlOuder2().isOverledenOfOnbevoegdEncoded();
        var isOuder1OverledenOfOnbevoegd = optionalIsOuder1OverledenOfOnbevoegdToken.isPresent();
        var isOuder2OverledenOfOnbevoegd = optionalIsOuder2OverledenOfOnbevoegdToken.isPresent();

        if (isOuder1OverledenOfOnbevoegd && isOuder2OverledenOfOnbevoegd) {
            var isOuder1OverledenOfOnbevoegdToken = optionalIsOuder1OverledenOfOnbevoegdToken.get();
            var isOuder2OverledenOfOnbevoegdToken = optionalIsOuder2OverledenOfOnbevoegdToken.get();
            var key = "%c%c".formatted(isOuder1OverledenOfOnbevoegdToken, isOuder2OverledenOfOnbevoegdToken);
            answer = JA_BEIDEN_ANTWOORDEN.get(key);
        } else if (isOuder1OverledenOfOnbevoegd) {
            answer = V4A_2_JA_OUDER1;
        } else if (isOuder2OverledenOfOnbevoegd) {
            answer = V4A_2_JA_OUDER2;
        } else {
            answer = V4A_2_NEE;
        }

        logger.debug("""
            4a.2 Ouders overleden of onbevoegd tot gezag?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV04A02(answer);
    }
}
