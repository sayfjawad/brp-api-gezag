package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder1;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder2;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

/**
 * sv4a_3
 * JA/NEE
 */
public class OuderOverledenOfOnbevoegdTotGezag extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(OuderOverledenOfOnbevoegdTotGezag.class);

    private static final String V4A_3_NEE_OUDER1 = "Nee_ouder1";
    private static final String V4A_3_NEE_OUDER2 = "Nee_ouder2";
    private static final String V4A_3_NEE = "Nee";
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

    protected OuderOverledenOfOnbevoegdTotGezag(final GezagsBepaling gezagsBepaling) {
        super(gezagsBepaling);
        currentQuestion = "v4a.3";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();
        var heeftOuder1Burgerservicenummer = plPersoon.getOuder1AsOptional().map(Ouder1::getBurgerservicenummer).isPresent();
        var heeftOuder2Burgerservicenummer = plPersoon.getOuder2AsOptional().map(Ouder2::getBurgerservicenummer).isPresent();
        if (!heeftOuder1Burgerservicenummer && !heeftOuder2Burgerservicenummer) {
            throw new AfleidingsregelException("Preconditie: Ouder moet een BSN hebben", "Ouder moet een BSN hebben");
        }

        Persoonslijst persoonslijstOuder1 = gezagsBepaling.getPlOuder1();
        Persoonslijst persoonslijstOuder2 = gezagsBepaling.getPlOuder2();
        if (persoonslijstOuder1 == null && persoonslijstOuder2 == null) {
            throw new AfleidingsregelException("Preconditie: Ouder moet geregistreerd staan in het BRP", "minimaal 1 ouder van de bevraagde persoon moet geregistreerd staan in het BRP");
        }

        if (persoonslijstOuder1 != null && !persoonslijstOuder1.isOverledenOfOnbevoegd()) {
            answer = V4A_3_NEE_OUDER1;
        }
        if (persoonslijstOuder2 != null && !persoonslijstOuder2.isOverledenOfOnbevoegd()) {
            if (V4A_3_NEE_OUDER1.equals(answer)) {
                answer = V4A_3_NEE;
            } else {
                answer = V4A_3_NEE_OUDER2;
            }
        } else if (answer == null) { // een ouder is overleden of onbevoegd terwijl de ander onbekend is, of beide zijn overleden of onbevoegd
            var isOuder1OverledenOfOnbevoegdToken = Optional.ofNullable(persoonslijstOuder1)
                .flatMap(Persoonslijst::isOverledenOfOnbevoegdEncoded)
                .orElse('?');
            var isOuder2OverledenOfOnbevoegdToken = Optional.ofNullable(persoonslijstOuder2)
                .flatMap(Persoonslijst::isOverledenOfOnbevoegdEncoded)
                .orElse('?');
            var tokenArray = new char[]{isOuder1OverledenOfOnbevoegdToken, isOuder2OverledenOfOnbevoegdToken};
            Arrays.sort(tokenArray);
            var key = new String(tokenArray);
            answer = JA_ANTWOORDEN.get(key);
        }

        logger.debug("""
            4a.3 Ouder overleden of onbevoegd tot gezag?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV04A03(answer);
    }
}
