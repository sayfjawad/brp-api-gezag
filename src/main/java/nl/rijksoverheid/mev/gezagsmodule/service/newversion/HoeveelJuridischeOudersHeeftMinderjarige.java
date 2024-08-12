package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

public class HoeveelJuridischeOudersHeeftMinderjarige extends GezagVraag {

    protected HoeveelJuridischeOudersHeeftMinderjarige(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v2.1";
    }

    /**
     * sv2_1
     *
     * @return EEN OUDER/TWEE OUDERS/GEEN OUDERS/2 PUNTOUDERS/ONBEKEND of
     * IN_ONDERZOEK
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        answer = plPersoon.hoeveelJuridischeOuders();

        gezagBepaling.getArAntwoordenModel().setV0201(answer);
    }
}