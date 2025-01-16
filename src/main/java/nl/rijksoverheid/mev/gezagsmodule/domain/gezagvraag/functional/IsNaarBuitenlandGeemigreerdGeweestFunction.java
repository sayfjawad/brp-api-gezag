package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.Verblijfplaats;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v1_3 "Ja" als de persoon (die in NL geboren is) ooit naar buitenland geëmigreerd is geweest,
 * anders "Nee".
 */
public class IsNaarBuitenlandGeemigreerdGeweestFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(
            IsNaarBuitenlandGeemigreerdGeweestFunction.class);
    private static final String QUESTION_ID = "v1.3";
    private static final String V1_3_JA = "Ja";
    private static final String V1_3_NEE = "Nee";

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {
        // Lokale variabele voor answer (in plaats van class-veld)
        String answer = null;
        // Haal de persoonslijst op
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // Kies zelf of je een exception gooit of een null-result teruggeeft
            throw new IllegalStateException("Persoonslijst van bevraagde persoon ontbreekt.");
        }
        // Check benodigde data
        String geboorteland = plPersoon.getPersoon().getGeboorteland();
        Verblijfplaats verblijfplaats = plPersoon.getVerblijfplaats();
        if (geboorteland == null || geboorteland.isEmpty()) {
            // Markeer als missend en geef eventueel null terug
            gezagsBepaling.addMissendeGegegevens("Geboorteland van bevraagde persoon");
            return new GezagVraagResult(QUESTION_ID, null);
        }
        if (verblijfplaats == null) {
            gezagsBepaling.addMissendeGegegevens("Verblijfplaats van bevraagde persoon");
            return new GezagVraagResult(QUESTION_ID, null);
        }
        // Bepaal het antwoord
        // Let op de logica in de originele code:
        // - Als het geboorteland "6030" (= Nederland) is,
        // - en de persoon heeft een (niet-lege) datumVestigingInNederland => "Ja"
        // - Anders => "Nee"
        if ("6030".equals(geboorteland)
                && verblijfplaats.getDatumVestigingInNederland() != null
                && !verblijfplaats.getDatumVestigingInNederland().isEmpty()) {
            answer = V1_3_JA;
        } else {
            answer = V1_3_NEE;
        }
        // Logging
        logger.debug("1.3 Is minderjarige naar het buitenland geëmigreerd geweest? -> {}", answer);
        // (Optioneel) mutatie in de gezagsBepaling
        gezagsBepaling.getArAntwoordenModel().setV0103(answer);
        // Return als (functionele) output
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
