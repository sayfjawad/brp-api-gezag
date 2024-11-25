package nl.rijksoverheid.mev.gezagsmodule.service.gezagmodule;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v1_2
 * <p>
 * Vraag {@link IsPersoonIngezeteneInBRP} in combinatie met {@code isAlsMinderjarigOpgeschort}
 * leidt ertoe dat het enige mogelijke antwoord {@code V1_2_NEE_OVERLEDEN} is.
 *
 * @return of minderjarig en niet overleden is
 */
public class IsPersoonMinderjarigEnNietOverleden extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(IsPersoonMinderjarigEnNietOverleden.class);

    private static final String V1_2_JA = "Ja";
    private static final String V1_2_NEE_MEERDERJARIG = "Nee_meerderjarig";
    private static final String V1_2_NEE_OVERLEDEN = "Nee_overleden";

    protected IsPersoonMinderjarigEnNietOverleden(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v1.2";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        boolean isMinderjarig = plPersoon.minderjarig();
        boolean isAlsMinderjarigOpgeschort = plPersoon.alsMinderjarigeOpgeschort();

        if (isAlsMinderjarigOpgeschort) {
            answer = V1_2_NEE_OVERLEDEN;
        } else if (isMinderjarig) {
            answer = V1_2_JA; // Minderjarig en niet overleden
        } else {
            answer = V1_2_NEE_MEERDERJARIG;
        }

        logger.debug("""
            1.2 Is persoon minderjarig en niet overleden?
            {}""", answer);
        gezagBepaling.getArAntwoordenModel().setV0102(answer);
    }
}
