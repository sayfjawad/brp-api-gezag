package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;


import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * v4a_2
 * Mogelijke antwoorden:
 * - Ja_ouder1
 * - Ja_ouder2
 * - Ja_beiden_onder_curatele, Ja_beiden_minderjarig, Ja_beiden_overleden, etc.
 * - Nee
 */
public class OudersOverledenOfOnbevoegdTotGezagFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(OudersOverledenOfOnbevoegdTotGezagFunction.class);

    private static final String QUESTION_ID        = "v4a.2";

    // Mogelijke antwoorden
    private static final String V4A_2_JA_OUDER1 = "Ja_ouder1";
    private static final String V4A_2_JA_OUDER2 = "Ja_ouder2";
    private static final String V4A_2_NEE       = "Nee";

    // Voor de situatie dat beide ouders overleden/onbevoegd zijn
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

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {
        // Je kunt (indien nodig) eerst een preconditiecheck doen:
        preconditieCheckOudersGeregistreerd(gezagsBepaling);

        // Lokaal 'answer'
        String answer;

        // Haal de (mogelijke) tokens op of check booleans
        var optionalIsOuder1OverledenOfOnbevoegdToken = gezagsBepaling.getPlOuder1().isOverledenOfOnbevoegdEncoded();
        var optionalIsOuder2OverledenOfOnbevoegdToken = gezagsBepaling.getPlOuder2().isOverledenOfOnbevoegdEncoded();
        var isOuder1OverledenOfOnbevoegd = optionalIsOuder1OverledenOfOnbevoegdToken.isPresent();
        var isOuder2OverledenOfOnbevoegd = optionalIsOuder2OverledenOfOnbevoegdToken.isPresent();

        if (isOuder1OverledenOfOnbevoegd && isOuder2OverledenOfOnbevoegd) {
            // Beide ouders
            var isOuder1OverledenOfOnbevoegdToken = optionalIsOuder1OverledenOfOnbevoegdToken.get();
            var isOuder2OverledenOfOnbevoegdToken = optionalIsOuder2OverledenOfOnbevoegdToken.get();
            var key = "%c%c".formatted(isOuder1OverledenOfOnbevoegdToken, isOuder2OverledenOfOnbevoegdToken);
            answer = JA_BEIDEN_ANTWOORDEN.get(key);
        } else if (isOuder1OverledenOfOnbevoegd) {
            // Alleen Ouder1
            answer = V4A_2_JA_OUDER1;
        } else if (isOuder2OverledenOfOnbevoegd) {
            // Alleen Ouder2
            answer = V4A_2_JA_OUDER2;
        } else {
            // Geen van beiden
            answer = V4A_2_NEE;
        }

        // Log
        logger.debug("4a.2 Ouders overleden of onbevoegd tot gezag? -> {}", answer);

        // (Optioneel) zet het antwoord in het ArAntwoordenModel
        gezagsBepaling.getArAntwoordenModel().setV04A02(answer);

        // Retourneer functioneel resultaat
        return new GezagVraagResult(QUESTION_ID, answer);
    }

    /**
     * Kopie van de oorspronkelijke 'preconditieCheckOudersGeregistreerd()'
     * (aangepast voor een functionele benadering).
     */
    private void preconditieCheckOudersGeregistreerd(GezagsBepaling gb) {
        var plPersoon = gb.getPlPersoon();
        if (!plPersoon.heeftTweeOuders()) {
            // Gooi exception of verwerk het op een andere manier
            throw new IllegalStateException("Preconditie: Kind moet twee ouders hebben");
        }
        preconditieCheckGeregistreerd("ouder1", gb.getPlOuder1());
        preconditieCheckGeregistreerd("ouder2", gb.getPlOuder2());
    }

    private void preconditieCheckGeregistreerd(String beschrijving, Persoonslijst plOuder) {
        boolean ouderGeregistreerdInBrp = plOuder != null
                && plOuder.isNietIngeschrevenInRNI()
                && plOuder.isNietGeemigreerd();
        if (!ouderGeregistreerdInBrp) {
            throw new IllegalStateException(
                    "Preconditie: " + beschrijving + " moet in BRP geregistreerd staan"
            );
        }
    }
}
