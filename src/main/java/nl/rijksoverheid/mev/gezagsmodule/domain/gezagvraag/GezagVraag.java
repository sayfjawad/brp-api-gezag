package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

public interface GezagVraag {

    /**
     * Voert de 'vraag' uit op basis van de gegeven GezagsBepaling
     * en geeft een resultaat terug (bestaande uit bijv. de questionId en het antwoord).
     */
    GezagVraagResult perform(GezagsBepaling gezagsBepaling);

    String getQuestionId();
}
