package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

public class IsNaarBuitenlandGeemigreerdGeweest extends GezagVraag {

    private static final String V1_3_JA = "Ja";
    private static final String V1_3_NEE = "Nee";

    protected IsNaarBuitenlandGeemigreerdGeweest(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v1.3";
    }

    /**
     * v1_3
     *
     * @return "Ja" als is naar buitenland geëmigreerd geweest, anders "Nee"
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        if (plPersoon.naarBuitenlandGeemigreerdGeweest()) {
            answer = V1_3_JA;
        } else {
            answer = V1_3_NEE;
        }

        gezagBepaling.getArAntwoordenModel().setV0103(answer);
    }
}
