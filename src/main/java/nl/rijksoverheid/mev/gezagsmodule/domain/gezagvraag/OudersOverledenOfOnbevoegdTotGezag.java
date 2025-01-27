package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * v4a_2 Mogelijke antwoorden: - Ja_ouder1 - Ja_ouder2 - Ja_beiden_onder_curatele,
 * Ja_beiden_minderjarig, Ja_beiden_overleden, etc. - Nee
 */
@Component
public class OudersOverledenOfOnbevoegdTotGezag implements GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(
            OudersOverledenOfOnbevoegdTotGezag.class);
    private static final String QUESTION_ID = "v4a.2";
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

    @Override
    public String getQuestionId() {
        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        preconditieCheckOudersGeregistreerd(gezagsBepaling);
        String answer;
        final var optionalIsOuder1OverledenOfOnbevoegdToken
                = gezagsBepaling.getPlOuder1().isOverledenOfOnbevoegdEncoded();
        final var optionalIsOuder2OverledenOfOnbevoegdToken
                = gezagsBepaling.getPlOuder2().isOverledenOfOnbevoegdEncoded();
        final var isOuder1OverledenOfOnbevoegd = optionalIsOuder1OverledenOfOnbevoegdToken.isPresent();
        final var isOuder2OverledenOfOnbevoegd = optionalIsOuder2OverledenOfOnbevoegdToken.isPresent();
        if (isOuder1OverledenOfOnbevoegd && isOuder2OverledenOfOnbevoegd) {
            final var isOuder1OverledenOfOnbevoegdToken = optionalIsOuder1OverledenOfOnbevoegdToken.get();
            final var isOuder2OverledenOfOnbevoegdToken = optionalIsOuder2OverledenOfOnbevoegdToken.get();
            final var key = "%c%c".formatted(isOuder1OverledenOfOnbevoegdToken,
                    isOuder2OverledenOfOnbevoegdToken);
            answer = JA_BEIDEN_ANTWOORDEN.get(key);
        } else if (isOuder1OverledenOfOnbevoegd) {
            answer = V4A_2_JA_OUDER1;
        } else if (isOuder2OverledenOfOnbevoegd) {
            answer = V4A_2_JA_OUDER2;
        } else {
            answer = V4A_2_NEE;
        }
        logger.debug("4a.2 Ouders overleden of onbevoegd tot gezag? -> {}", answer);
        gezagsBepaling.getArAntwoordenModel().setV04A02(answer);
        return new GezagVraagResult(QUESTION_ID, answer);
    }

    /**
     * Kopie van de oorspronkelijke 'preconditieCheckOudersGeregistreerd()' (aangepast voor een
     * functionele benadering).
     */
    private void preconditieCheckOudersGeregistreerd(final GezagsBepaling gb) {
        final var plPersoon = gb.getPlPersoon();
        if (!plPersoon.heeftTweeOuders()) {
            throw new AfleidingsregelException(
                    "Preconditie: Kind moet twee ouders hebben",
                    "Van de bevraagde persoon zijn geen twee ouders bekend"
            );
        }
        preconditieCheckGeregistreerd("ouder1", gb.getPlOuder1());
        preconditieCheckGeregistreerd("ouder2", gb.getPlOuder2());
    }

    private void preconditieCheckGeregistreerd(final String beschrijving,
                                               final Persoonslijst plOuder) {
        final var ouderGeregistreerdInBrp = plOuder != null
                && plOuder.isNietIngeschrevenInRNI()
                && plOuder.isNietGeemigreerd();
        if (!ouderGeregistreerdInBrp) {
            throw new AfleidingsregelException(
                    "Preconditie: " + beschrijving + " moet in BRP geregistreerd staan",
                    beschrijving + " van bevraagde persoon is niet in BRP geregistreerd"
            );
        }
    }
}
