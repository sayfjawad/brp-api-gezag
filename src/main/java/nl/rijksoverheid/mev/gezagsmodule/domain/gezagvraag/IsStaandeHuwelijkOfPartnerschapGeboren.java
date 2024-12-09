package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst.isValideGeslachtsnaam;

/**
 * v2b_1
 * "Ja" als is staande huwelijk of partnerschap geboren, anders "Nee"
 */
public class IsStaandeHuwelijkOfPartnerschapGeboren extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(IsStaandeHuwelijkOfPartnerschapGeboren.class);

    private static final String V2B_1_JA = "Ja";
    private static final String V2B_1_NEE = "Nee";
    private static final String OUDER_1 = "ouder1";
    private static final String OUDER_2 = "ouder2";

    protected IsStaandeHuwelijkOfPartnerschapGeboren(final GezagsBepaling gezagsBepaling) {
        super(gezagsBepaling);
        currentQuestion = "v2b.1";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();

        answer = V2B_1_NEE;
        String geboorteDatumKind = plPersoon.getPersoon().getGeboortedatum();
        Ouder1 lOuder1 = plPersoon.getOuder1();
        Ouder2 lOuder2 = plPersoon.getOuder2();
        /*
        Nu vastgesteld is dat het kind 1 ouder heeft kan gecontroleerd worden of deze ouder
        een relatie had tijdens de geboorte van het kind, indien dat het geval is, dan wordt gekeken
        of dit nog steeds de actuele relatie is. Het maakt daarbij niet uit of de ouder overleden is,
        de registratie in het BRP is in zo'n geval bij de ouder onveranderd, bij de partner daarentegen
        is de relatie ontbonden met als reden overlijden. Controleer of de BSN van de partner bij geboorte
        gelijk is aan de BSN van de actuele partner. Als dat het geval is, heeft deze partner ook gezag.
         */
        if (lOuder1 != null && isValideGeslachtsnaam(lOuder1.getGeslachtsnaam())) {
            Persoonslijst plOuder1 = gezagsBepaling.getPlOuder1();
            preconditieCheckGeregistreerd(OUDER_1, plOuder1);
            if (heeftOuderRelatieBijGeboorteKind(plOuder1, geboorteDatumKind) && !plPersoon.ontkenningOuderschapDoorOuder2()) {
                answer = V2B_1_JA;
            }
        }

        if (lOuder2 != null && isValideGeslachtsnaam(lOuder2.getGeslachtsnaam())) {
            Persoonslijst plOuder2 = gezagsBepaling.getPlOuder2();
            preconditieCheckGeregistreerd(OUDER_2, plOuder2);
            if (heeftOuderRelatieBijGeboorteKind(plOuder2, geboorteDatumKind) && !plPersoon.ontkenningOuderschapDoorOuder1()) {
                answer = V2B_1_JA;
            }
        }

        logger.debug("""
            2b.1 Is het kind staande huwelijk of partnerschap geboren?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV02B01(answer);
    }

    public boolean heeftOuderRelatieBijGeboorteKind(Persoonslijst plOuder, String geboortedatum) {
        List<HuwelijkOfPartnerschap> hopPlOuder = plOuder.getHuwelijkOfPartnerschappen();
        if (!hopPlOuder.isEmpty() && (hopPlOuder.get(0).getBsnPartner() != null)) {
            HopRelaties hopRelaties = plOuder.getHopRelaties();
            HopRelatie geborenInRelatie = hopRelaties.geborenInRelatie(Integer.parseInt(geboortedatum));
            return geborenInRelatie != null;
        }
        return false;
    }
}