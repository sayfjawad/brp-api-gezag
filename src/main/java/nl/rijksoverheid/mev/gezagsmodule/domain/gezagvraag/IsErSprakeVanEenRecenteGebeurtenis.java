package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Gezagsverhouding;
import nl.rijksoverheid.mev.gezagsmodule.domain.HuwelijkOfPartnerschap;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.*;

/**
 * v3.1
 * Ja, als er sprake is van een recente gebeurtenis anders Nee
 */
public class IsErSprakeVanEenRecenteGebeurtenis extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(IsErSprakeVanEenRecenteGebeurtenis.class);

    private static final String V3_1_JA = "Ja";
    private static final String V3_1_NEE = "Nee";
    private static final String INDICATIE_GEZAG_BEIDE_OUDERS = "12";
    private static final String INDICATIE_GEZAG_OUDER1 = "1";
    private static final String INDICATIE_GEZAG_OUDER2 = "2";

    protected IsErSprakeVanEenRecenteGebeurtenis(final GezagsBepaling gezagsBepaling) {
        super(gezagsBepaling);
        currentQuestion = "v3.1";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();
        Gezagsverhouding gezagsverhouding = plPersoon.getGezagsverhouding();
        if (gezagsverhouding == null) {
            gezagsBepaling.addMissendeGegegevens("gezagsverhouding van bevraagde persoon");
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
            Persoonslijst lplOuder1 = gezagsBepaling.getPlOuder1();
            Persoonslijst lplOuder2 = gezagsBepaling.getPlOuder2();
            if (nuGehuwdOudersNaGeldigheidsdatum(lplOuder1, lplOuder2, ingangsdatumGeldigheidGezag)) {
                answer = V3_1_JA;
            }
        }

        if (answer == null) {
            answer = V3_1_NEE;
        }

        logger.debug("""
            3.1 Is er door een recente gebeurtenis het gezag toch (weer) van rechtswege, ondanks dat er eerder een uitspraak is gedaan?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV0301(answer);
    }

    private boolean nuGehuwdOudersNaGeldigheidsdatum(final Persoonslijst plOuder1, final Persoonslijst plOuder2, final String geldigheidsdatum) {
        if ((plOuder1 == null) || (plOuder2 == null)) {
            return false;
        }
        List<HuwelijkOfPartnerschap> hopListOuder1 = getHuwelijkOfPartnerschap(plOuder1, plOuder2.getPersoon().getBurgerservicenummer());
        List<HuwelijkOfPartnerschap> hopListOuder2 = getHuwelijkOfPartnerschap(plOuder2, plOuder1.getPersoon().getBurgerservicenummer());

        if (hopListOuder1.isEmpty() || hopListOuder2.isEmpty()) {
            return false;
        }
        String hopOuder1Actueel = hopListOuder1.getLast().getDatumVoltrokken();
        String hopOuder2Actueel = hopListOuder2.getLast().getDatumVoltrokken();

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
        hopList.sort(comparing(HuwelijkOfPartnerschap::getDatumVoltrokken, nullsFirst(naturalOrder())));
        return hopList;
    }
}