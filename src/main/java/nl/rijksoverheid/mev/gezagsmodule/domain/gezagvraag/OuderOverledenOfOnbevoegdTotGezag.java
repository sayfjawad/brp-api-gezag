package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder1;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder2;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * sv4a_3
 * JA/NEE
 */
public class OuderOverledenOfOnbevoegdTotGezag extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(OuderOverledenOfOnbevoegdTotGezag.class);

    private static final String V4A_3_NEE_OUDER1 = "Nee_ouder1";
    private static final String V4A_3_NEE_OUDER2 = "Nee_ouder2";
    private static final String V4A_3_NEE = "Nee";
    private static final String V4A_3_JA = "Ja";

    protected OuderOverledenOfOnbevoegdTotGezag(final GezagsBepaling gezagsBepaling) {
        super(gezagsBepaling);
        currentQuestion = "v4a.3";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();
        answer = V4A_3_JA;
        
        // Preconditie: minimaal 1 ouder moet een BSN hebben
        Ouder1 persoonOuder1 = plPersoon.getOuder1();
        Ouder2 persoonOuder2 = plPersoon.getOuder2();
        if ((persoonOuder1 == null || persoonOuder1.getBurgerservicenummer() == null)
            && (persoonOuder2 == null || persoonOuder2.getBurgerservicenummer() == null)) {
            throw new AfleidingsregelException("Preconditie: Ouder moet een BSN hebben", "Ouder moet een BSN hebben");
        }
        Persoonslijst lplOuder1 = gezagsBepaling.getPlOuder1();
        Persoonslijst lplOuder2 = gezagsBepaling.getPlOuder2();
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

        logger.debug("""
            4a.3 Ouder overleden of onbevoegd tot gezag?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV04A03(answer);
    }
}