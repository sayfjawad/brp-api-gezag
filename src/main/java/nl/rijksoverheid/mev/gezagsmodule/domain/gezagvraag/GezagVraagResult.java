package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

/**
 * Bevat het vraag / antwoord paar van een doorgelopen stap in de gezagsbepaling
 *
 * @param questionId de vraag id
 * @param answer     het antwoord of null
 */
public record GezagVraagResult(String questionId, String answer) {

}
