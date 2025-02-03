package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * v1_3 "Ja" als de persoon (die in NL geboren is) ooit naar buitenland geëmigreerd is geweest,
 * anders "Nee".
 */
@Component
public class IsNaarBuitenlandGeemigreerdGeweest implements GezagVraag {
    private static final Logger logger = LoggerFactory.getLogger(IsNaarBuitenlandGeemigreerdGeweest.class);
    private static final String QUESTION_ID = "v1.3";
    private static final String V1_3_JA = "Ja";
    private static final String V1_3_NEE = "Nee";

    @Override
    public String getQuestionId() {
        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        final var plPersoon = gezagsBepaling.getPlPersoon();
        final var geboorteland = plPersoon.getPersoon().getGeboorteland();
        final var verblijfplaats = plPersoon.getVerblijfplaats();

        String answer = null;
        if (geboorteland == null || geboorteland.isEmpty()) {
            gezagsBepaling.addMissendeGegegevens("Geboorteland van bevraagde persoon");
        } else if (verblijfplaats == null) {
            gezagsBepaling.addMissendeGegegevens("Verblijfplaats van bevraagde persoon");
        } else if (geboorteland.equals("6030")
            && verblijfplaats.getDatumVestigingInNederland() != null
            && !verblijfplaats.getDatumVestigingInNederland().isEmpty()) {
            answer = V1_3_JA;
        } else {
            answer = V1_3_NEE;
        }

        logger.debug("""
            1.3 Is minderjarige naar het buitenland geëmigreerd geweest?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV0103(answer);
        return new GezagVraagResult(QUESTION_ID, answer);
    }
}
