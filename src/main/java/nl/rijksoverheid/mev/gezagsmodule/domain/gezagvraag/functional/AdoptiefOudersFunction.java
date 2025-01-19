package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdoptiefOudersFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(AdoptiefOudersFunction.class);
    private static final String QUESTION_ID = "v2a.2";
    private static final String V2A_2_JA = "Ja";
    private static final String V2A_2_NEE = "Nee";

    @Override
    public String getQuestionId() {

        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {
        // Haal de persoonslijst op
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // Kies je eigen manier van foutafhandeling:
            // throw exception, return null, of gebruik een ValidationResult e.d.
            throw new IllegalStateException("Persoonslijst van bevraagde persoon ontbreekt.");
        }
        // Bepaal het antwoord
        String answer = plPersoon.geadopteerdMetNlAkte() ? V2A_2_JA : V2A_2_NEE;
        logger.debug("""
                2a.2 Adoptiefouder(s)?
                Antwoord: {}
                """, answer);
        // Indien je *niet* verder wilt muteren in 'gezagsBepaling', laat je de volgende regel weg.
        // Als je die mutatie (voor legacy / compatibiliteit) wel nodig hebt, kan het uiteraard:
        gezagsBepaling.getArAntwoordenModel().setV02A02(answer);
        // Geef nu een *functioneel* resultaat terug zonder state te muteren
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}