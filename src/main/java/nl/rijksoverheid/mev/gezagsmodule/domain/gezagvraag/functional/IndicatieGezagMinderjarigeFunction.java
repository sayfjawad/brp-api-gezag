package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import nl.rijksoverheid.mev.gezagsmodule.domain.Gezagsverhouding;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * v3_2
 * 1/2/12/1D/2D/D/ONBEKEND
 */
@Component
@Scope("prototype")
public class IndicatieGezagMinderjarigeFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(
            IndicatieGezagMinderjarigeFunction.class);

    private static final String QUESTION_ID = "v3.2";

    @Override
    public String getQuestionId() {
        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        // Haal de persoonslijst op
        final var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            throw new IllegalStateException("Persoonslijst (minderjarige) ontbreekt in GezagsBepaling.");
        }

        // Bepaal de gezagsverhouding
        final var gezagsverhouding = plPersoon.getGezagsverhouding();
        String answer = null;

        if (gezagsverhouding != null) {
            answer = gezagsverhouding.getIndicatieGezagMinderjarige();
        } else {
            // Log dat de gegevens ontbreken
            gezagsBepaling.addMissendeGegegevens("gezagsverhouding van bevraagde persoon");
        }

        logger.debug("3.2 Wat is de indicatie gezag minderjarige? -> {}", answer);

        // Eventuele mutatie in de 'gezagsBepaling' (optioneel)
        gezagsBepaling.getArAntwoordenModel().setV0302(answer);

        // Geef een result terug
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
