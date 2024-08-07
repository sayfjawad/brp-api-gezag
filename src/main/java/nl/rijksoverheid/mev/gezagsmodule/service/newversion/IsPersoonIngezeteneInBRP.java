package nl.rijksoverheid.mev.gezagsmodule.service.newversion;


import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

public class IsPersoonIngezeteneInBRP extends GezagVraag {

    private static final String V1_1_NEE = "Nee";
    private static final String V1_1_JA = "Ja";

    protected IsPersoonIngezeteneInBRP(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v1.1";
    }

    /**
     * v1_1
     *
     * @return "Ja" als isIngezeteneInBRP anders "Nee"
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();
        if (plPersoon != null && plPersoon.isIngezeteneInBRP()) {
            answer = V1_1_JA;
        } else if (plPersoon != null && !plPersoon.isIngezeteneInBRP()) {
            answer = V1_1_NEE;
        }

        gezagBepaling.getArAntwoordenModel().setV0101(answer);
    }
}
