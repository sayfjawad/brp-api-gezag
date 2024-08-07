package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

public class IsGeadopteerdMetNlAkte extends GezagVraag {

    private static final String V1_3B_JA = "Ja";
    private static final String V1_3B_NEE = "Nee";

    protected IsGeadopteerdMetNlAkte(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v1.3b";
    }

    /**
     * v1_3b
     *
     * @return "Ja" als is geadopteerd met nederlandse akte anders "Nee"
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        if (plPersoon.geadopteerdMetNlAkte()) {
            answer = V1_3B_JA;
        }
        answer = V1_3B_NEE;

        gezagBepaling.getArAntwoordenModel().setV0103B(answer);
    }
}
