package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * v1_2 3 mogelijke antwoorden: - "Ja" (minderjarig en niet overleden) - "Nee_meerderjarig" (geen
 * minderjarige) - "Nee_overleden" (als minderjarige overleden).
 */
@Component
public class IsPersoonMinderjarigEnNietOverleden implements GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(
            IsPersoonMinderjarigEnNietOverleden.class);
    private static final String QUESTION_ID = "v1.2";
    private static final String V1_2_JA = "Ja";
    private static final String V1_2_NEE_MEERDERJARIG = "Nee_meerderjarig";
    private static final String V1_2_NEE_OVERLEDEN = "Nee_overleden";

    @Override
    public String getQuestionId() {

        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        // Haal de Persoonslijst op
        final var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            throw new IllegalStateException("Persoonslijst van bevraagde persoon ontbreekt.");
        }
        // Bepaal of de persoon minderjarig is, en/of als minderjarig is overleden
        final var isMinderjarig = plPersoon.minderjarig();
        final var isAlsMinderjarigOverleden = plPersoon.alsMinderjarigeOverleden();
        // Stel het antwoord vast
        String answer;
        if (isAlsMinderjarigOverleden) {
            answer = V1_2_NEE_OVERLEDEN;
        } else if (isMinderjarig) {
            // Minderjarig en niet overleden
            answer = V1_2_JA;
        } else {
            // Geen minderjarig
            answer = V1_2_NEE_MEERDERJARIG;
        }
        // Logging
        logger.debug("1.2 Is persoon minderjarig en niet overleden? -> {}", answer);
        // (Optioneel) mutatie in je 'gezagsBepaling'
        gezagsBepaling.getArAntwoordenModel().setV0102(answer);
        // Retourneer het resultaat (immutable data holder)
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
