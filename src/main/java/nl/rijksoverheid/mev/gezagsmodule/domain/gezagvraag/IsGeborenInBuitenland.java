package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * v1_3a "Ja" als de persoon in het buitenland is geboren, anders "Nee".
 */
@Component
public class IsGeborenInBuitenland implements GezagVraag {

    private static final Logger logger =
            LoggerFactory.getLogger(IsGeborenInBuitenland.class);
    private static final String QUESTION_ID = "v1.3a";
    private static final String GEBOORTELAND_CODE_NEDERLAND = "6030";
    private static final String V1_3A_JA = "Ja";
    private static final String V1_3A_NEE = "Nee";

    @Override
    public String getQuestionId() {

        return QUESTION_ID;
    }

    /**
     * Determines if a person is born outside of the Netherlands based on their birth country.
     * <p>
     * If the person is born outside of the Netherlands, the method returns an answer indicating "Ja" (Yes).
     * Otherwise, it returns "Nee" (No).
     * <p>
     * If required data is missing, the method logs the missing information and returns null as the answer.
     *
     * @param gezagsBepaling Object containing data about the person being queried. This includes their personal list data.
     *                       The object must not be null, and it must contain a valid person entry with a birth country.
     * @return A {@code GezagVraagResult} instance containing the question identifier and the result ("Ja" or "Nee").
     * Returns {@code null} as the answer if required data is missing.
     * @throws IllegalStateException if the person's personal list is missing.
     */
    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        final var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            throw new IllegalStateException("Persoonslijst van bevraagde persoon ontbreekt.");
        }
        final var geboorteland = plPersoon.getPersoon().getGeboorteland();
        if (geboorteland == null || geboorteland.isEmpty()) {
            gezagsBepaling.addMissendeGegegevens("Geboorteland van bevraagde persoon");
            return new GezagVraagResult(QUESTION_ID, null);
        }
        final var answer = geboorteland.equals(GEBOORTELAND_CODE_NEDERLAND)
                ? V1_3A_NEE
                : V1_3A_JA;
        logger.debug("1.3a Is minderjarige geboren in het buitenland? -> {}", answer);
        gezagsBepaling.getArAntwoordenModel().setV0103A(answer);
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
