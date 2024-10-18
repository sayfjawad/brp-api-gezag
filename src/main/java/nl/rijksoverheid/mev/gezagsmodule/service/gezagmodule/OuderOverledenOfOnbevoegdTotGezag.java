package nl.rijksoverheid.mev.gezagsmodule.service.gezagmodule;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder1;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder2;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

/**
 * sv4a_3
 *
 * @return JA/NEE
 */
public class OuderOverledenOfOnbevoegdTotGezag extends GezagVraag {

    private static final String V4A_3_NEE_OUDER1 = "Nee_ouder1";
    private static final String V4A_3_NEE_OUDER2 = "Nee_ouder2";
    private static final String V4A_3_NEE = "Nee";
    private static final String V4A_3_JA = "Ja";

    protected OuderOverledenOfOnbevoegdTotGezag(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v4a.3";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();
        answer = V4A_3_JA;
        
        // Preconditie: minimaal 1 ouder moet een BSN hebben
        Ouder1 persoonOuder1 = plPersoon.getOuder1();
        Ouder2 persoonOuder2 = plPersoon.getOuder2();
        if ((persoonOuder1 == null || persoonOuder1.getBsn() == null)
            && (persoonOuder2 == null || persoonOuder2.getBsn() == null)) {
            throw new AfleidingsregelException("Preconditie: Ouder moet een BSN hebben", "Ouder moet een BSN hebben");
        }
        Persoonslijst lplOuder1 = gezagBepaling.getPlOuder1();
        Persoonslijst lplOuder2 = gezagBepaling.getPlOuder2();
        if ((lplOuder1 == null) && (lplOuder2 == null)) {
            throw new AfleidingsregelException("Preconditie: Ouder moet geregistreerd staan in het BRP", "minimaal 1 ouder van de bevraagde persoon moet geregistreerd staan in het BRP");
        }
        if (lplOuder1 != null
            && (!lplOuder1.isOverledenOfOnbevoegd())) {
            answer = V4A_3_NEE_OUDER1;
        }
        if (lplOuder2 != null
            && (!lplOuder2.isOverledenOfOnbevoegd())) {
            if (answer.equals(V4A_3_NEE_OUDER1)) {
                answer = V4A_3_NEE;
            } else {
                answer = V4A_3_NEE_OUDER2;
            }
        }

        gezagBepaling.getArAntwoordenModel().setV04A03(answer);
    }
}