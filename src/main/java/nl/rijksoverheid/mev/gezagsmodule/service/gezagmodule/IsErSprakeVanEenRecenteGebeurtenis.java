package nl.rijksoverheid.mev.gezagsmodule.service.gezagmodule;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Gezagsverhouding;
import nl.rijksoverheid.mev.gezagsmodule.domain.HuwelijkOfPartnerschap;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

import java.util.ArrayList;
import java.util.List;

public class IsErSprakeVanEenRecenteGebeurtenis extends GezagVraag {

    private static final String V3_1_JA = "Ja";
    private static final String V3_1_NEE = "Nee";
    private static final String INDICATIE_GEZAG_BEIDE_OUDERS = "12";
    private static final String INDICATIE_GEZAG_OUDER1 = "1";
    private static final String INDICATIE_GEZAG_OUDER2 = "2";

    protected IsErSprakeVanEenRecenteGebeurtenis(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v3.1";
    }

    /**
     * v3.1
     *<p>
     * Ja, als er sprake is van een recente gebeurtenis anders Nee
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();
        Gezagsverhouding gezagsverhouding = plPersoon.getGezagsverhouding();
        if (gezagsverhouding == null) {
            gezagBepaling.addMissendeGegegevens("gezagsverhouding van bevraagde persoon");
            return;
        }

        String indicatieGezagMinderjarige = gezagsverhouding.getIndicatieGezagMinderjarige();
        String ingangsdatumGeldigheidGezag = gezagsverhouding.getIngangsdatumGeldigheidGezag();
        // Check voor ontkenning erkenning, als er niet erkend is, dan is er sprake van ontkenning erkenning
        if ((indicatieGezagMinderjarige.equals(INDICATIE_GEZAG_BEIDE_OUDERS)) && (!plPersoon.heeftTweeOuders())) {
            answer = V3_1_JA;
        }

        if (!gezagsverhouding.hasIngangsdatumGeldigheidGezag()) {
            throw new AfleidingsregelException("Preconditie: Ingangsdatum geldigheid gezag moet een valide datum bevatten", "ingangsdatum geldigheid van gezagsverhouding");
        }
        // Controleer op adoptie na uitspraak gezag, als adoptie heeft plaatsgevonden na de uitspraak
        // dan is er sprake van een recente gebeurtenis
        if (plPersoon.adoptieNaIngangGeldigheidsdatum()) {
            answer = V3_1_JA;
        }
        // Controleer op reparatiehuwelijk alleen als gezag aan een van de ouders is toegewezen
        // In geval van een reparatiehuwelijk na ingangsdatum is er sprake van een recente gebeurtenis
        if ((indicatieGezagMinderjarige.equals(INDICATIE_GEZAG_OUDER1) || indicatieGezagMinderjarige.equals(INDICATIE_GEZAG_OUDER2))
            && plPersoon.heeftTweeOuders() && plPersoon.beideOudersHebbenEenBSN()) {
            Persoonslijst lplOuder1 = gezagBepaling.getPlOuder1();
            Persoonslijst lplOuder2 = gezagBepaling.getPlOuder2();
            if (nuGehuwdOudersNaGeldigheidsdatum(lplOuder1, lplOuder2, ingangsdatumGeldigheidGezag)) {
                answer = V3_1_JA;
            }
        }

        if (answer == null) {
            answer = V3_1_NEE;
        }

        gezagBepaling.getArAntwoordenModel().setV0301(answer);
    }

    private boolean nuGehuwdOudersNaGeldigheidsdatum(final Persoonslijst plOuder1, final Persoonslijst plOuder2, final String geldigheidsdatum) {
        if ((plOuder1 == null) || (plOuder2 == null)) {
            return false;
        }
        List<HuwelijkOfPartnerschap> hopListOuder1 = getHuwelijkOfPartnerschap(plOuder1, plOuder2.getPersoon().getBsn());
        List<HuwelijkOfPartnerschap> hopListOuder2 = getHuwelijkOfPartnerschap(plOuder2, plOuder1.getPersoon().getBsn());
        if (hopListOuder1.isEmpty() || hopListOuder2.isEmpty()) {
            return false;
        }
        String hopOuder1Actueel = hopListOuder1.getFirst().getDatumVoltrokken();
        String hopOuder2Actueel = hopListOuder2.getFirst().getDatumVoltrokken();
        return (org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder1Actueel) && org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder2Actueel))
            && (Integer.parseInt(geldigheidsdatum) <= Integer.parseInt(hopOuder1Actueel))
            && (Integer.parseInt(geldigheidsdatum) <= Integer.parseInt(hopOuder2Actueel))
            && (hopListOuder1.size() > 1) && (hopListOuder2.size() > 1);
    }

    private List<HuwelijkOfPartnerschap> getHuwelijkOfPartnerschap(final Persoonslijst plOuder, final String bsnPartner) {
        List<HuwelijkOfPartnerschap> hopList = new ArrayList<>();
        if (plOuder.getHuwelijkOfPartnerschappen() != null) {
            for (HuwelijkOfPartnerschap hop : plOuder.getHuwelijkOfPartnerschappen()) {
                if (bsnPartner.equals(hop.getBsnPartner())) {
                    hopList.add(hop);
                }
            }
        }
        return hopList;
    }
}