package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v1_4 Antwoord: "Ja" of "Nee" (of evt. "IN_ONDERZOEK" indien je dat in de toekomst wilt
 * toevoegen).
 */
public class IsUitspraakGezagAanwezigFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(
            IsUitspraakGezagAanwezigFunction.class);
    // Vaste waarden
    private static final String QUESTION_ID = "v1.4";
    private static final String V1_4_JA = "Ja";
    private static final String V1_4_NEE = "Nee";

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {

        var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // Kies je eigen manier van foutafhandeling (exception, null-result, etc.)
            throw new IllegalStateException("Persoonslijst ontbreekt in GezagsBepaling");
        }
        // Bepaal het antwoord
        String answer = plPersoon.heefIndicatieGezag() ? V1_4_JA : V1_4_NEE;
        // Logging
        logger.debug("1.4 Uitspraak gezag aanwezig? -> {}", answer);
        // (Optioneel) mutatie in het bestaande 'gezagsBepaling' model:
        gezagsBepaling.getArAntwoordenModel().setV0104(answer);
        // Retourneer een functioneel resultaat
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
