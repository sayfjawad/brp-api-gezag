package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;


import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v2_1
 * EEN OUDER/TWEE OUDERS/GEEN OUDERS/2 PUNTOUDERS/ONBEKEND of
 * IN_ONDERZOEK
 */
public class HoeveelJuridischeOudersHeeftMinderjarigeFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(HoeveelJuridischeOudersHeeftMinderjarigeFunction.class);

    private static final String QUESTION_ID = "v2.1";

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {
        // Haal de benodigde data op
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // Of gooi een specifieke exception als je die al hebt (AfleidingsregelException of iets dergelijks)
            throw new IllegalStateException("Persoonslijst van bevraagde persoon ontbreekt.");
        }

        // Bepaal het antwoord
        String answer = plPersoon.hoeveelJuridischeOuders();

        // Logging
        logger.debug("2.1 Hoeveel juridische ouders heeft de minderjarige? -> {}", answer);

        // Mutatie in het bestaande model (optioneel)
        gezagsBepaling.getArAntwoordenModel().setV0201(answer);

        // Return als functioneel resultaat
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
