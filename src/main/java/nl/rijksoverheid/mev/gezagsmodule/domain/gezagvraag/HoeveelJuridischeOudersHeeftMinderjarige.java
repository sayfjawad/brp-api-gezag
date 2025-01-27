package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * v2_1 EEN OUDER / TWEE OUDERS / GEEN OUDERS / 2 PUNTOUDERS / ONBEKEND of IN_ONDERZOEK
 */
@Component
public class HoeveelJuridischeOudersHeeftMinderjarige implements GezagVraag {

    private static final Logger logger =
            LoggerFactory.getLogger(HoeveelJuridischeOudersHeeftMinderjarige.class);
    private static final String QUESTION_ID = "v2.1";

    @Override
    public String getQuestionId() {
        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        final var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            throw new IllegalStateException("Persoonslijst van bevraagde persoon ontbreekt.");
        }

        final var answer = plPersoon.hoeveelJuridischeOuders();

        logger.debug("2.1 Hoeveel juridische ouders heeft de minderjarige? -> {}", answer);

        gezagsBepaling.getArAntwoordenModel().setV0201(answer);

        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
