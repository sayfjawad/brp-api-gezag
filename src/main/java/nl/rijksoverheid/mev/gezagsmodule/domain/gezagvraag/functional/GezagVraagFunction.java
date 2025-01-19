package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;

public interface GezagVraagFunction {

    /**
     * Voert de 'vraag' uit op basis van de gegeven GezagsBepaling
     * en geeft een resultaat terug (bestaande uit bijv. de questionId en het antwoord).
     */
    GezagVraagResult perform(GezagsBepaling gezagsBepaling);

    String getQuestionId();
}
