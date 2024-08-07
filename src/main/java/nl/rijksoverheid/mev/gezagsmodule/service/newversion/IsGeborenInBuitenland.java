package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

public class IsGeborenInBuitenland extends GezagVraag {

    private static final String V1_3A_JA = "Ja";
    private static final String V1_3A_NEE = "Nee";

    protected IsGeborenInBuitenland(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v1_3a";
    }

    /*
    /**
     * v1_3a
     *
     * @return "Ja" als is geboren in het buitenland anders "Nee"
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        if (plPersoon.isGeborenInBuitenland()) {
            answer = V1_3A_JA;
        }
        answer = V1_3A_NEE;

        gezagBepaling.getArAntwoordenModel().setV0103A(answer);
    }
}
