package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * v4b1
 * <p>
 * OuderOfPartnerOverledenOfOnbevoegdTotGezag
 *
 * @return if ouder of partner overleden of onbevoegd tot gezag is
 */
public class OuderOfPartnerOverledenOfOnbevoegdTotGezag extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(OuderOfPartnerOverledenOfOnbevoegdTotGezag.class);

    private static final String V4B_1_NEE = "Nee";
    private static final String V4B_1_JA_BEIDEN = "Ja_beiden";
    private static final String V4B_1_JA_OUDER1 = "Ja_ouder1";
    private static final String V4B_1_JA_OUDER2 = "Ja_ouder2";
    private static final String V4B_1_JA_NIET_OUDER1 = "Ja_nietouder1";
    private static final String V4B_1_JA_NIET_OUDER2 = "Ja_nietouder2";
    /**
     * Beslissingstabel met drie inputs ouder, ouder overleden of onbevoegd, partner overleden of onbevoegd en
     * een output die teruggeven moet worden. Wordt gebruikt in v4b1OuderOfPartnerOverledenOfOnbevoegdTotGezag.
     */
    private static final Map<String, String> ouderOfPartnerOverledenOfOnbevoegdTotGezagMap = Map.of(
        "ouder1,true,true", V4B_1_JA_BEIDEN,
        "ouder1,true,false", V4B_1_JA_OUDER1,
        "ouder1,false,true", V4B_1_JA_NIET_OUDER1,
        "ouder1,false,false", V4B_1_NEE,
        "ouder2,true,true", V4B_1_JA_BEIDEN,
        "ouder2,true,false", V4B_1_JA_OUDER2,
        "ouder2,false,true", V4B_1_JA_NIET_OUDER2,
        "ouder2,false,false", V4B_1_NEE
    );

    protected OuderOfPartnerOverledenOfOnbevoegdTotGezag(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v4b.1";
    }

    @Override
    public void perform() {
        String key;
        // Preconditie: minimaal 1 ouder moet een BSN hebben
        Persoonslijst lplOuder1 = gezagBepaling.getPlOuder1();
        Persoonslijst lplOuder2 = gezagBepaling.getPlOuder2();
        Persoonslijst lplNietOuder = gezagBepaling.getPlNietOuder();
        // Preconditie: minimaal 1 ouder moet geregistreerd staan in BRP
        if (lplOuder1 == null && lplOuder2 == null) {
            throw new AfleidingsregelException("Preconditie: Minimaal 1 ouder moet geregistreerd staan in BRP", "voor de bevraagde persoon moet minimaal 1 ouder geregistreerd staan in BRP");
        }

        if (lplNietOuder == null) {
            throw new AfleidingsregelException("Preconditie: niet_ouder  moet geregistreerd staan in BRP", "voor de bevraagde persoon moet niet_ouder geregistreerd staan in BRP");
        }

        if (lplOuder1 != null) {
            key = "ouder1," + (lplOuder1.isOverledenOfOnbevoegd()) + "," + (lplNietOuder.isOverledenOfOnbevoegd());
            answer = ouderOfPartnerOverledenOfOnbevoegdTotGezagMap.get(key);
        }

        if (answer == null) {
            key = "ouder2," + (lplOuder2.isOverledenOfOnbevoegd()) + "," + (lplNietOuder.isOverledenOfOnbevoegd());
            answer = ouderOfPartnerOverledenOfOnbevoegdTotGezagMap.get(key);
        }

        logger.debug("""
            4b.1 Ouder, echtgenoot of partner overleden of onbevoegd tot gezag?
            {}""", answer);
        gezagBepaling.getArAntwoordenModel().setV04B01(answer);
    }
}