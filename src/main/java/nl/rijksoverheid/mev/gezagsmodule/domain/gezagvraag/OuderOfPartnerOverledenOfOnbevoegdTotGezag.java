package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * v4b1 "Ja_..." of "Nee" als ouder of partner overleden of onbevoegd is.
 */
@Component
public class OuderOfPartnerOverledenOfOnbevoegdTotGezag implements GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(
            OuderOfPartnerOverledenOfOnbevoegdTotGezag.class);
    private static final String QUESTION_ID = "v4b.1";
    private static final String V4B_1_NEE = "Nee";
    private static final String V4B_1_JA_BEIDEN = "Ja_beiden";
    private static final String V4B_1_JA_OUDER1 = "Ja_ouder1";
    private static final String V4B_1_JA_OUDER2 = "Ja_ouder2";
    private static final String V4B_1_JA_NIET_OUDER1 = "Ja_nietouder1";
    private static final String V4B_1_JA_NIET_OUDER2 = "Ja_nietouder2";
    /**
     * Beslissingstabel met drie inputs: (ouder1 of ouder2?), bool 'ouderOverledenOfOnbevoegd', bool
     * 'nietOuderOverledenOfOnbevoegd'
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
    /**
     * Als we 'Ja_beiden' hebben, checken we tokens (bv. 'cc', 'cm', 'mm', 'oo', etc.) en geven een
     * meer specifieke antwoord-string terug.
     */
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

    @Override
    public String getQuestionId() {
        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        String answer;
        final var persoonslijstOuder1 = gezagsBepaling.getPlOuder1();
        final var persoonslijstOuder2 = gezagsBepaling.getPlOuder2();
        if (persoonslijstOuder1 == null && persoonslijstOuder2 == null) {
            throw new AfleidingsregelException(
                    "Preconditie: Minimaal 1 ouder moet geregistreerd staan in BRP",
                    "Voor de bevraagde persoon moet minimaal 1 ouder geregistreerd staan in BRP");
        }
        final var persoonslijstNietOuder = gezagsBepaling.getPlNietOuder();
        if (persoonslijstNietOuder == null) {
            throw new AfleidingsregelException(
                    "Preconditie: niet_ouder moet geregistreerd staan in BRP",
                    "Voor de bevraagde persoon moet niet_ouder geregistreerd staan in BRP");
        }
        final var optionalIsNietOuderOverledenOfOnbevoegdToken =
                persoonslijstNietOuder.isOverledenOfOnbevoegdEncoded();
        final var isNietOuderOverledenOfOnbevoegd = optionalIsNietOuderOverledenOfOnbevoegdToken.isPresent();
        if (persoonslijstOuder1 != null) {
            final var key = "ouder1,"
                    + persoonslijstOuder1.isOverledenOfOnbevoegd() + ","
                    + isNietOuderOverledenOfOnbevoegd;
            answer = ouderOfPartnerOverledenOfOnbevoegdTotGezagMap.get(key);
        } else {
            final var key = "ouder2,"
                    + persoonslijstOuder2.isOverledenOfOnbevoegd() + ","
                    + isNietOuderOverledenOfOnbevoegd;
            answer = ouderOfPartnerOverledenOfOnbevoegdTotGezagMap.get(key);
        }
        if (V4B_1_JA_BEIDEN.equals(answer)) {
            final var persoonslijstOuder =
                    (persoonslijstOuder1 != null) ? persoonslijstOuder1 : persoonslijstOuder2;
            final var isOuderOverledenOfOnbevoegdToken = persoonslijstOuder
                    .isOverledenOfOnbevoegdEncoded()
                    .orElseThrow();
            final var isNietOuderOverledenOfOnbevoegdToken =
                    optionalIsNietOuderOverledenOfOnbevoegdToken.orElseThrow();
            final var key2 =
                    "" + isOuderOverledenOfOnbevoegdToken + isNietOuderOverledenOfOnbevoegdToken;
            answer = JA_BEIDEN_ANTWOORDEN.get(key2);
        }
        logger.debug("4b.1 Ouder, echtgenoot of partner overleden of onbevoegd tot gezag? -> {}",
                answer);
        gezagsBepaling.getArAntwoordenModel().setV04B01(answer);
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
