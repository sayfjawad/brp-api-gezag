package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v1_3a
 * "Ja" als de persoon is in buitenland geboren is en anders "Nee".
 */
public class IsGeborenInBuitenland extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(IsGeborenInBuitenland.class);

    private static final String GEBOORTELAND_CODE_NEDERLAND = "6030";
    private static final String V1_3A_JA = "Ja";
    private static final String V1_3A_NEE = "Nee";

    protected IsGeborenInBuitenland(final GezagsBepaling gezagsBepaling) {
        super(gezagsBepaling);
        currentQuestion = "v1.3a";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();
        String geboorteland = plPersoon.getPersoon().getGeboorteland();
        if (geboorteland == null || geboorteland.isEmpty()) {
            gezagsBepaling.addMissendeGegegevens("Geboorteland van bevraagde persoon");
            return;
        }

        answer = geboorteland.equals(GEBOORTELAND_CODE_NEDERLAND) ? V1_3A_NEE : V1_3A_JA;

        logger.debug("""
            1.3a Is minderjarige geboren in het buitenland?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV0103A(answer);
    }
}
