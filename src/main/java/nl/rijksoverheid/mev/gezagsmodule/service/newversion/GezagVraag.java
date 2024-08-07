package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

import java.util.Map;

public abstract class GezagVraag {

    protected String currentQuestion;
    protected String answer;

    protected GezagBepaling gezagBepaling;

    protected GezagVraag(final GezagBepaling gezagBepaling) {
        this.gezagBepaling = gezagBepaling;
    }

    public void step() {
        perform();
        gezagBepaling.next(currentQuestion, answer);
    }

    public abstract void perform();

    protected void preconditieCheckOudersGeregistreerd() throws GezagException {
        if (!gezagBepaling.getPlPersoon().heeftTweeOuders()) {
            throw new AfleidingsregelException("Preconditie: Kind moet twee ouders hebben");
        }
        preconditieCheckGeregistreerd("ouder1", gezagBepaling.getPlOuder1());
        preconditieCheckGeregistreerd("ouder2", gezagBepaling.getPlOuder2());
    }

    protected void preconditieCheckGeregistreerd(final String beschrijving, final Persoonslijst plOuder) throws GezagException {
        boolean ouderGeregistreerdInBrp
            = plOuder != null
            && plOuder.isNietIngeschrevenInRNI()
            && plOuder.isNietGeemigreerd();
        if (!ouderGeregistreerdInBrp) {
            throw new AfleidingsregelException("Preconditie: " + beschrijving + " moet in BRP geregistreerd staan");
        }
    }

}