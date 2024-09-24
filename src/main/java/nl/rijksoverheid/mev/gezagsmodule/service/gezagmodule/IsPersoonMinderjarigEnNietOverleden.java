package nl.rijksoverheid.mev.gezagsmodule.service.gezagmodule;


import nl.rijksoverheid.mev.gezagsmodule.domain.ARVragenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

public class IsPersoonMinderjarigEnNietOverleden extends GezagVraag {

    private static final String V1_2_JA = "Ja";
    private static final String V1_2_NEE_MEERDERJARIG = "Nee_meerderjarig";
    private static final String V1_2_NEE_OVERLEDEN = "Nee_overleden";

    protected IsPersoonMinderjarigEnNietOverleden(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v1.2";
    }

    /**
     * v1_2
     * <p>
     * Vraag {@link ARVragenModel#v11isPersoonIngezeteneInBRP()} in combinatie met {@code isAlsMinderjarigOpgeschort}
     * leidt ertoe dat het enige mogelijke antwoord {@code V1_2_NEE_OVERLEDEN} is.
     *
     * @return of minderjarig en niet overleden is
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        boolean isMinderjarig = plPersoon.minderjarig();
        boolean isOpgeschort = plPersoon.isOpgeschort();
        boolean isAlsMinderjarigOpgeschort = plPersoon.alsMinderjarigeOpgeschort();

        if (isAlsMinderjarigOpgeschort) {
            answer = V1_2_NEE_OVERLEDEN;
        } else if (isOpgeschort) {
            answer = V1_2_NEE_MEERDERJARIG;
        } else if (isMinderjarig) {
            answer = V1_2_JA; // Minderjarig en niet overleden
        } else {
            answer = V1_2_NEE_MEERDERJARIG;
        }

        gezagBepaling.getArAntwoordenModel().setV0102(answer);
    }
}
