package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import nl.rijksoverheid.mev.gezagsmodule.domain.HuwelijkOfPartnerschap;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

public class ZijnJuridischeOudersNuMetElkaarGehuwdOfPartners extends GezagVraag {

    private static final String V2A_1_JA_GEHUWD_OF_PARTNERS = "Ja";
    private static final String V2A_1_NEE = "Nee";
    private static final String V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR = "Nee_nooit";

    protected ZijnJuridischeOudersNuMetElkaarGehuwdOfPartners(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v2a.1";
    }

    /**
     * v2a_1
     *
     * @return JA GEHUWD OF PARTNERS/NEE NA GEBOORTE NOOIT GEHUWD PARTNERS
     * GEWEEST MET ELKAAR / NEE GESCHEIDEN NA GEBOORTE
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        String geboortedatumKind = plPersoon.getPersoon().getGeboortedatum();
        preconditieCheckOudersGeregistreerd();

        Persoonslijst plOuder1 = gezagBepaling.getPlOuder1();
        Persoonslijst plOuder2 = gezagBepaling.getPlOuder2();
        HuwelijkOfPartnerschap hopOuder1 = ouderGetHetHuwelijkOfPartnerschap(plOuder1, plOuder2);
        HuwelijkOfPartnerschap hopOuder2 = ouderGetHetHuwelijkOfPartnerschap(plOuder2, plOuder1);
        // Als er geen huwelijk tussen de ouders gevonden wordt is het antwoord "Nee_nooit"
        if (hopOuder1 == null || hopOuder2 == null) {
            answer = V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR;
        } else if (!hopOuder1.getDatumVoltrokken().isEmpty() && hopOuder2.getDatumVoltrokken().isEmpty()) {
            if (!plOuder1.isOverleden() && !plOuder2.isOverleden()) {
                answer = V2A_1_JA_GEHUWD_OF_PARTNERS;
            } else {
                answer = V2A_1_NEE;
            }
        } else if (ouderGescheiden(hopOuder1, geboortedatumKind) || ouderGescheiden(hopOuder2, geboortedatumKind)) {
            answer = V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR;
        } else {
            answer = V2A_1_NEE;
        }

        gezagBepaling.getArAntwoordenModel().setV02A01(answer);
    }

    private HuwelijkOfPartnerschap ouderGetHetHuwelijkOfPartnerschap(Persoonslijst persoonslijst1, Persoonslijst persoonslijst2) {
        if ((persoonslijst1 == null) || (persoonslijst2 == null)) {
            return null;
        }
        for (HuwelijkOfPartnerschap huwelijkOfPartnerschap : persoonslijst1.getHuwelijkOfPartnerschappen()) {
            if (persoonslijst2.getPersoon() != null && persoonslijst2.getPersoon().getBsn().equals(huwelijkOfPartnerschap.getBsnPartner())) {
                return huwelijkOfPartnerschap;
            }
        }
        return null;
    }

    private boolean ouderGescheiden(HuwelijkOfPartnerschap hop, String geboortedatumKind) {
        return (hop.getRedenOntbinding() != null && hop.getRedenOntbinding().equals("S")
            && Integer.parseInt(hop.getDatumOntbinding()) < Integer.parseInt(geboortedatumKind));
    }
}