package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * v3_2 1/2/12/1D/2D/D/ONBEKEND
 */
@Component
public class IndicatieGezagMinderjarige implements GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(
            IndicatieGezagMinderjarige.class);
    private static final String QUESTION_ID = "v3.2";

    @Override
    public String getQuestionId() {

        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        final var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            throw new IllegalStateException(
                    "Persoonslijst (minderjarige) ontbreekt in GezagsBepaling.");
        }
        final var gezagsverhouding = plPersoon.getGezagsverhouding();
        String answer = null;
        if (gezagsverhouding != null) {
            answer = gezagsverhouding.getIndicatieGezagMinderjarige();
        } else {
            gezagsBepaling.addMissendeGegegevens("gezagsverhouding van bevraagde persoon");
        }
        logger.debug("3.2 Wat is de indicatie gezag minderjarige? -> {}", answer);
        gezagsBepaling.getArAntwoordenModel().setV0302(answer);
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
