package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * v1_4 Antwoord: "Ja" of "Nee" (of evt. "IN_ONDERZOEK" indien je dat in de toekomst wilt
 * toevoegen).
 */
@Component
@Scope("prototype")
public class IsUitspraakGezagAanwezig implements GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(
            IsUitspraakGezagAanwezig.class);

    private static final String QUESTION_ID = "v1.4";
    private static final String V1_4_JA     = "Ja";
    private static final String V1_4_NEE    = "Nee";

    @Override
    public String getQuestionId() {
        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {

        final var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // Kies je eigen manier van foutafhandeling (exception, null-result, etc.)
            throw new IllegalStateException("Persoonslijst ontbreekt in GezagsBepaling");
        }

        final var answer = plPersoon.heefIndicatieGezag() ? V1_4_JA : V1_4_NEE;

        // Logging
        logger.debug("1.4 Uitspraak gezag aanwezig? -> {}", answer);

        // (Optioneel) mutatie in het bestaande 'gezagsBepaling' model:
        gezagsBepaling.getArAntwoordenModel().setV0104(answer);

        // Retourneer een functioneel resultaat
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
