package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v1_2 Heeft 3 mogelijke antwoorden: - "Ja" (minderjarig en niet overleden), - "Nee_meerderjarig"
 * (geen minderjarige), - "Nee_overleden" (als minderjarige overleden).
 */
public class IsPersoonMinderjarigEnNietOverledenFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(
            IsPersoonMinderjarigEnNietOverledenFunction.class);
    private static final String QUESTION_ID = "v1.2";
    private static final String V1_2_JA = "Ja";
    private static final String V1_2_NEE_MEERDERJARIG = "Nee_meerderjarig";
    private static final String V1_2_NEE_OVERLEDEN = "Nee_overleden";

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {
        // Haal de Persoonslijst op
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // Kies je eigen manier van foutafhandeling: throwen of een "null"-antwoord teruggeven
            throw new IllegalStateException("Persoonslijst van bevraagde persoon ontbreekt.");
        }
        // Bepaal of de persoon minderjarig is, en/of als minderjarig is overleden
        boolean isMinderjarig = plPersoon.minderjarig();
        boolean isAlsMinderjarigOverleden = plPersoon.alsMinderjarigeOverleden();
        // Stel het antwoord vast
        String answer;
        if (isAlsMinderjarigOverleden) {
            answer = V1_2_NEE_OVERLEDEN;
        } else if (isMinderjarig) {
            answer = V1_2_JA; // Minderjarig en niet overleden
        } else {
            answer = V1_2_NEE_MEERDERJARIG;
        }
        // Logging
        logger.debug("1.2 Is persoon minderjarig en niet overleden? -> {}", answer);
        // (Optioneel) mutatie in je 'gezagsBepaling'
        gezagsBepaling.getArAntwoordenModel().setV0102(answer);
        // Retourneer een immutabel resultaat
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
