package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;


import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.*;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * v4a_3
 * "Ja" / "Nee" in verschillende varianten, afhankelijk van de status (overleden / onbevoegd) van
 * ouder1 / ouder2.
 */
public class OuderOverledenOfOnbevoegdTotGezagFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(OuderOverledenOfOnbevoegdTotGezagFunction.class);

    private static final String QUESTION_ID        = "v4a.3";

    private static final String V4A_3_NEE_OUDER1   = "Nee_ouder1";
    private static final String V4A_3_NEE_OUDER2   = "Nee_ouder2";
    private static final String V4A_3_NEE          = "Nee";

    private static final Map<String, String> JA_ANTWOORDEN = Map.of(
            "?c", "Ja_ouder_onder_curatele",
            "?m", "Ja_ouder_minderjarig",
            "?o", "Ja_ouder_overleden",
            "cc", "Ja_beiden_onder_curatele",
            "cm", "Ja_ouder_onder_curatele_en_andere_ouder_minderjarig",
            "co", "Ja_ouder_overleden_en_andere_ouder_onder_curatele",
            "mm", "Ja_beiden_minderjarig",
            "mo", "Ja_ouder_overleden_en_andere_ouder_minderjarig",
            "oo", "Ja_beiden_overleden"
    );

    @Override
    public String getQuestionId() {

        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {
        // Lokale variabele voor antwoord
        String answer = null;

        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            throw new IllegalStateException("Preconditie: Persoonslijst (kind) ontbreekt in GezagsBepaling.");
        }

        // Check: is er minstens één ouder met BSN in de persoonslijst van het kind?
        var heeftOuder1Burgerservicenummer =
                plPersoon.getOuder1AsOptional().map(Ouder1::getBurgerservicenummer).isPresent();
        var heeftOuder2Burgerservicenummer =
                plPersoon.getOuder2AsOptional().map(Ouder2::getBurgerservicenummer).isPresent();

        if (!heeftOuder1Burgerservicenummer && !heeftOuder2Burgerservicenummer) {
            throw new AfleidingsregelException(
                    "Preconditie: Ouder moet een BSN hebben",
                    "Ouder moet een BSN hebben"
            );
        }

        // Haal de bijbehorende ouder-PL op
        Persoonslijst persoonslijstOuder1 = gezagsBepaling.getPlOuder1();
        Persoonslijst persoonslijstOuder2 = gezagsBepaling.getPlOuder2();
        if (persoonslijstOuder1 == null && persoonslijstOuder2 == null) {
            throw new AfleidingsregelException(
                    "Preconditie: Ouder moet geregistreerd staan in het BRP",
                    "Minimaal 1 ouder van de bevraagde persoon moet geregistreerd staan in het BRP"
            );
        }

        // Als Ouder1 bestaat en níet is overleden of onbevoegd => "Nee_ouder1"
        if (persoonslijstOuder1 != null && !persoonslijstOuder1.isOverledenOfOnbevoegd()) {
            answer = V4A_3_NEE_OUDER1;
        }

        // Als Ouder2 bestaat en níet is overleden of onbevoegd
        //   - en er is al "Nee_ouder1" => antwoord = "Nee"
        //   - anders => "Nee_ouder2"
        if (persoonslijstOuder2 != null && !persoonslijstOuder2.isOverledenOfOnbevoegd()) {
            if (V4A_3_NEE_OUDER1.equals(answer)) {
                answer = V4A_3_NEE;
            } else {
                answer = V4A_3_NEE_OUDER2;
            }
        }
        // Anders als answer == null => beide ouders niet bekend of (één of beide) overleden/onbevoegd
        else if (answer == null) {
            // Bepaal tokens (bv. 'c', 'm', 'o', of '?')
            var isOuder1OverledenOfOnbevoegdToken = Optional.ofNullable(persoonslijstOuder1)
                    .flatMap(Persoonslijst::isOverledenOfOnbevoegdEncoded)
                    .orElse('?');
            var isOuder2OverledenOfOnbevoegdToken = Optional.ofNullable(persoonslijstOuder2)
                    .flatMap(Persoonslijst::isOverledenOfOnbevoegdEncoded)
                    .orElse('?');

            // Sorteer ze lexicografisch
            var tokenArray = new char[]{isOuder1OverledenOfOnbevoegdToken, isOuder2OverledenOfOnbevoegdToken};
            Arrays.sort(tokenArray);
            var key = new String(tokenArray);
            answer = JA_ANTWOORDEN.get(key);
        }

        // Logging
        logger.debug("4a.3 Ouder overleden of onbevoegd tot gezag? -> {}", answer);

        // Optionele mutatie in je (legacy) model
        gezagsBepaling.getArAntwoordenModel().setV04A03(answer);

        // Functioneel resultaat (id + antwoord)
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}

