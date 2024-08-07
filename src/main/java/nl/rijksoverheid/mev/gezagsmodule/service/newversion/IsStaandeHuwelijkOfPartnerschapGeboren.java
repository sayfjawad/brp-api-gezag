package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import nl.rijksoverheid.mev.gezagsmodule.domain.*;

import java.util.List;

import static nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst.isValideGeslachtsnaam;

public class IsStaandeHuwelijkOfPartnerschapGeboren extends GezagVraag {

    private static final String V2B_1_JA = "Ja";
    private static final String V2B_1_NEE = "Nee";

    protected IsStaandeHuwelijkOfPartnerschapGeboren(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v2b.1";
    }

    /**
     * v2b_1
     *
     * @return "Ja" als is staande huwelijk of partnerschap geboren, anders
     * "Nee"
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

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
        if (lOuder1 != null && isValideGeslachtsnaam(lOuder1.getGeslachtsnaam())){
            Persoonslijst plOuder1 = gezagBepaling.getPlOuder1();
            preconditieCheckGeregistreerd("ouder1", plOuder1);
            if (heeftOuderRelatieBijGeboorteKind(plOuder1, geboorteDatumKind) && !plPersoon.ontkenningOuderschapDoorOuder2())
                answer = V2B_1_JA;
        }

        if (lOuder2!=null && isValideGeslachtsnaam(lOuder2.getGeslachtsnaam())){
            Persoonslijst plOuder2 = gezagBepaling.getPlOuder2();
            preconditieCheckGeregistreerd("ouder2", plOuder2);
            if (heeftOuderRelatieBijGeboorteKind(plOuder2, geboorteDatumKind) && !plPersoon.ontkenningOuderschapDoorOuder1())
                answer = V2B_1_JA;
        }
        answer = V2B_1_NEE;

        gezagBepaling.getArAntwoordenModel().setV02B01(answer);
    }

    public boolean heeftOuderRelatieBijGeboorteKind(Persoonslijst plOuder, String geboortedatum)
    {
        List<HuwelijkOfPartnerschap> hopPlOuder = plOuder.getHuwelijkOfPartnerschappen();
        if (!hopPlOuder.isEmpty() && (hopPlOuder.get(0).getBsnPartner() != null))
        {
            HopRelaties hopRelaties = plOuder.getHopRelaties();
            HopRelatie geborenInRelatie = hopRelaties.geborenInRelatie(Integer.parseInt(geboortedatum));
            return geborenInRelatie != null;
        }
        return false;
    }
}