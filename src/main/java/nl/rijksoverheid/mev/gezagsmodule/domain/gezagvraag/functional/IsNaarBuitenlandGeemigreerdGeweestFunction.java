package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.Verblijfplaats;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * v1_3 "Ja" als de persoon (die in NL geboren is) ooit naar buitenland geëmigreerd is geweest,
 * anders "Nee".
 */
@Component
@Scope("prototype")
public class IsNaarBuitenlandGeemigreerdGeweestFunction implements GezagVraagFunction {

    private static final Logger logger =
            LoggerFactory.getLogger(IsNaarBuitenlandGeemigreerdGeweestFunction.class);

    private static final String QUESTION_ID = "v1.3";
    private static final String V1_3_JA     = "Ja";
    private static final String V1_3_NEE    = "Nee";

    @Override
    public String getQuestionId() {
        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        // Haal de persoonslijst op
        final var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // Kies zelf of je een exception gooit of een null-result teruggeeft
            throw new IllegalStateException("Persoonslijst van bevraagde persoon ontbreekt.");
        }

        final var geboorteland = plPersoon.getPersoon().getGeboorteland();
        final var verblijfplaats = plPersoon.getVerblijfplaats();

        // Check benodigde data
        if (geboorteland == null || geboorteland.isEmpty()) {
            gezagsBepaling.getArAntwoordenModel().setV0103(null);
            gezagsBepaling.addMissendeGegegevens("Geboorteland van bevraagde persoon");
            return new GezagVraagResult(QUESTION_ID, null);
        }

        if (verblijfplaats == null) {
            gezagsBepaling.addMissendeGegegevens("Verblijfplaats van bevraagde persoon");
            gezagsBepaling.getArAntwoordenModel().setV0103(null);
            return new GezagVraagResult(QUESTION_ID, null);
        }

        // Bepaal het antwoord
        // Let op de logica in de oorspronkelijke code:
        // - Als het geboorteland "6030" (= Nederland) is,
        // - en de persoon heeft een (niet-lege) datumVestigingInNederland => "Ja"
        // - Anders => "Nee"
        final var answer = ("6030".equals(geboorteland)
                && verblijfplaats.getDatumVestigingInNederland() != null
                && !verblijfplaats.getDatumVestigingInNederland().isEmpty())
                ? V1_3_JA
                : V1_3_NEE;

        // Logging
        logger.debug("1.3 Is minderjarige naar het buitenland geëmigreerd geweest? -> {}", answer);

        // Mutatie in het gezagsBepaling-model
        gezagsBepaling.getArAntwoordenModel().setV0103(answer);

        // Retourneer het (functionele) resultaat
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
