package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

public class IndicatieGezagMinderjarige extends GezagVraag {

    protected IndicatieGezagMinderjarige(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v3.2";
    }

    /**
     * v3_2
     *
     * @return 1/2/12/1D/2D/D/ONBEKEND
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        gezagBepaling.getArAntwoordenModel().setV0302(plPersoon.getGezagsverhouding().getIndicatieGezagMinderjarige());
    }
}