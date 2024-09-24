package nl.rijksoverheid.mev.gezagsmodule.service.gezagmodule;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

public class IsUitspraakGezagAanwezig extends GezagVraag {

    private static final String V1_4_JA = "Ja";
    private static final String V1_4_NEE = "Nee";

    protected IsUitspraakGezagAanwezig(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v1.4";
    }

    /**
     * sv1_4
     *
     * @return JA/NEE of IN_ONDERZOEK
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        if (plPersoon.heefIndicatieGezag()) {
            answer = V1_4_JA;
        } else {
            answer = V1_4_NEE;
        }

        gezagBepaling.getArAntwoordenModel().setV0104(answer);
    }
}
