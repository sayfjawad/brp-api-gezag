package nl.rijksoverheid.mev.gezagsmodule.service.gezagmodule;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

/**
 * v1_3b
 *
 * @return "Ja" als is geadopteerd met nederlandse akte anders "Nee"
 */
public class IsGeadopteerdMetNlAkte extends GezagVraag {

    private static final String V1_3B_JA = "Ja";
    private static final String V1_3B_NEE = "Nee";

    protected IsGeadopteerdMetNlAkte(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v1.3b";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        if (plPersoon.geadopteerdMetNlAkte()) {
            answer = V1_3B_JA;
        } else {
            answer = V1_3B_NEE;
        }

        gezagBepaling.getArAntwoordenModel().setV0103B(answer);
    }
}
