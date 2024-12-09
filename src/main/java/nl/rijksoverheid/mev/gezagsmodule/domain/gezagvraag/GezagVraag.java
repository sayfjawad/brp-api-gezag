package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

/**
 * Een vraag om uit te voeren bij het bepalen van gezag
 */
public abstract class GezagVraag {

    protected String currentQuestion;
    protected String answer;

    protected GezagsBepaling gezagsBepaling;

    protected GezagVraag(final GezagsBepaling gezagsBepaling) {
        this.gezagsBepaling = gezagsBepaling;
    }

    public void step() {
        perform();
        gezagsBepaling.next(currentQuestion, answer);
    }

    public abstract void perform();

    protected void preconditieCheckOudersGeregistreerd() throws GezagException {
        if (!gezagsBepaling.getPlPersoon().heeftTweeOuders()) {
            throw new AfleidingsregelException("Preconditie: Kind moet twee ouders hebben", "van de bevraagde persoon zijn geen twee ouders bekend");
        }
        preconditieCheckGeregistreerd("ouder1", gezagsBepaling.getPlOuder1());
        preconditieCheckGeregistreerd("ouder2", gezagsBepaling.getPlOuder2());
    }

    protected void preconditieCheckGeregistreerd(final String beschrijving, final Persoonslijst plOuder) throws GezagException {
        boolean ouderGeregistreerdInBrp
            = plOuder != null
            && plOuder.isNietIngeschrevenInRNI()
            && plOuder.isNietGeemigreerd();
        if (!ouderGeregistreerdInBrp) {
            throw new AfleidingsregelException("Preconditie: " + beschrijving + " moet in BRP geregistreerd staan", beschrijving + " van bevraagde persoon is niet in BRP geregistreerd");
        }
    }
}