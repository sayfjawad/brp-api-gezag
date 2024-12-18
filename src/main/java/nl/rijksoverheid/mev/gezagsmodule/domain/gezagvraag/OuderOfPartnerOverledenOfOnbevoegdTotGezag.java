package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * v4b1
 * OuderOfPartnerOverledenOfOnbevoegdTotGezag
 * if ouder of partner overleden of onbevoegd tot gezag is
 */
public class OuderOfPartnerOverledenOfOnbevoegdTotGezag extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(OuderOfPartnerOverledenOfOnbevoegdTotGezag.class);

    private static final String V4B_1_NEE = "Nee";
    private static final String V4B_1_JA_BEIDEN = "Ja_beiden";
    private static final String V4B_1_JA_OUDER1 = "Ja_ouder1";
    private static final String V4B_1_JA_OUDER2 = "Ja_ouder2";
    private static final String V4B_1_JA_NIET_OUDER1 = "Ja_nietouder1";
    private static final String V4B_1_JA_NIET_OUDER2 = "Ja_nietouder2";
    /**
     * Beslissingstabel met drie inputs ouder, ouder overleden of onbevoegd, partner overleden of onbevoegd en
     * een output die teruggeven moet worden. Wordt gebruikt in v4b1OuderOfPartnerOverledenOfOnbevoegdTotGezag.
     */
    private static final Map<String, String> ouderOfPartnerOverledenOfOnbevoegdTotGezagMap = Map.of(
        "ouder1,true,true", V4B_1_JA_BEIDEN,
        "ouder1,true,false", V4B_1_JA_OUDER1,
        "ouder1,false,true", V4B_1_JA_NIET_OUDER1,
        "ouder1,false,false", V4B_1_NEE,
        "ouder2,true,true", V4B_1_JA_BEIDEN,
        "ouder2,true,false", V4B_1_JA_OUDER2,
        "ouder2,false,true", V4B_1_JA_NIET_OUDER2,
        "ouder2,false,false", V4B_1_NEE
    );
    private static final Map<String, String> JA_BEIDEN_ANTWOORDEN = Map.of(
        "cc", "Ja_beiden_onder_curatele",
        "cm", "Ja_ouder_onder_curatele_en_niet_ouder_minderjarig",
        "co", "Ja_ouder_onder_curatele_en_niet_ouder_overleden",
        "mc", "Ja_ouder_minderjarig_en_niet_ouder_onder_curatele",
        "mm", "Ja_beiden_minderjarig",
        "mo", "Ja_ouder_minderjarig_en_niet_ouder_overleden",
        "oc", "Ja_ouder_overleden_en_niet_ouder_onder_curatele",
        "om", "Ja_ouder_overleden_en_niet_ouder_minderjarig",
        "oo", "Ja_beiden_overleden"
    );

    protected OuderOfPartnerOverledenOfOnbevoegdTotGezag(final GezagsBepaling gezagsBepaling) {
        super(gezagsBepaling);
        currentQuestion = "v4b.1";
    }

    @Override
    public void perform() {
        var persoonslijstOuder1 = gezagsBepaling.getPlOuder1();
        var persoonslijstOuder2 = gezagsBepaling.getPlOuder2();
        if (persoonslijstOuder1 == null && persoonslijstOuder2 == null) {
            throw new AfleidingsregelException("Preconditie: Minimaal 1 ouder moet geregistreerd staan in BRP", "voor de bevraagde persoon moet minimaal 1 ouder geregistreerd staan in BRP");
        }

        var persoonslijstNietOuder = gezagsBepaling.getPlNietOuder();
        if (persoonslijstNietOuder == null) {
            throw new AfleidingsregelException("Preconditie: niet_ouder moet geregistreerd staan in BRP", "voor de bevraagde persoon moet niet_ouder geregistreerd staan in BRP");
        }

        var optionalIsNietOuderOverledenOfOnbevoegdToken = persoonslijstNietOuder.isOverledenOfOnbevoegdEncoded();
        boolean isNietOuderOverledenOfOnbevoegd = optionalIsNietOuderOverledenOfOnbevoegdToken.isPresent();

        String key = persoonslijstOuder1 != null
            ? "ouder1," + persoonslijstOuder1.isOverledenOfOnbevoegd() + "," + isNietOuderOverledenOfOnbevoegd
            : "ouder2," + persoonslijstOuder2.isOverledenOfOnbevoegd() + "," + isNietOuderOverledenOfOnbevoegd;
        answer = ouderOfPartnerOverledenOfOnbevoegdTotGezagMap.get(key);

        if (answer.equals(V4B_1_JA_BEIDEN)) {
            var persoonslijstOuder = persoonslijstOuder1 == null ? persoonslijstOuder2 : persoonslijstOuder1;
            var isOuderOverledenOfOnbevoegdToken = persoonslijstOuder.isOverledenOfOnbevoegdEncoded().orElseThrow();
            var isNietOuderOverledenOfOnbevoegdToken = optionalIsNietOuderOverledenOfOnbevoegdToken.orElseThrow();
            var key2 = "%c%c".formatted(isOuderOverledenOfOnbevoegdToken, isNietOuderOverledenOfOnbevoegdToken);
            answer = JA_BEIDEN_ANTWOORDEN.get(key2);
        }

        logger.debug("""
            4b.1 Ouder, echtgenoot of partner overleden of onbevoegd tot gezag?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV04B01(answer);
    }
}
