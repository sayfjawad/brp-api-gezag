package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Gezagsverhouding;
import nl.rijksoverheid.mev.gezagsmodule.domain.HuwelijkOfPartnerschap;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IsErSprakeVanEenRecenteGebeurtenis extends GezagVraag {

    private static final String V3_1_JA = "Ja";
    private static final String V3_1_NEE = "Nee";
    private static final String INDICATIE_GEZAG_BEIDE_OUDERS = "12";
    private static final String INDICATIE_GEZAG_OUDER1 = "1";
    private static final String INDICATIE_GEZAG_OUDER2 = "2";
    private static final String STANDAARD_WAARDE_INGANGSDATUM_GELDIGHEID_GEZAG = "00000000";

    protected IsErSprakeVanEenRecenteGebeurtenis(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v3.1";
    }

    /**
     * v3.1
     *
     * @return Ja, als er sprake is van een recente gebeurtenis anders Nee
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();
        Gezagsverhouding gezagsverhouding = plPersoon.getGezagsverhouding();
        String indicatieGezagMinderjarige = gezagsverhouding.getIndicatieGezagMinderjarige();
        String ingangsdatumGeldigheidGezag = gezagsverhouding.getIngangsdatumGeldigheidGezag();
        // Check voor ontkenning erkenning, als er niet erkend is, dan is er sprake van ontkenning erkenning
        if ((indicatieGezagMinderjarige.equals(INDICATIE_GEZAG_BEIDE_OUDERS)) && (!plPersoon.heeftTweeOuders())) {
            answer = V3_1_JA;
        }
        // Preconditie ingangsdatum geldigheid gezag moet een geldige datum zijn en niet de standaard waarde
        if (Objects.equals(ingangsdatumGeldigheidGezag, STANDAARD_WAARDE_INGANGSDATUM_GELDIGHEID_GEZAG)) {
            throw new AfleidingsregelException("Preconditie: Ingangsdatum geldigheid gezag moet een valide datum bevatten");
        }
        // Controleer op adoptie na uitspraak gezag, als adoptie heeft plaatsgevonden na de uitspraak
        // dan is er sprake van een recente gebeurtenis
        if (plPersoon.adoptieNaIngangsGeldigheidsdatum()) {
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
        answer = V3_1_NEE;

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
        String hopOuder1Actueel = hopListOuder1.get(0).getDatumVoltrokken();
        String hopOuder2Actueel = hopListOuder2.get(0).getDatumVoltrokken();
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