package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;

import java.util.ArrayList;
import java.util.List;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.HuwelijkOfPartnerschap;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v3.1 Ja, als er sprake is van een recente gebeurtenis anders Nee
 */
public class IsErSprakeVanEenRecenteGebeurtenisFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(
            IsErSprakeVanEenRecenteGebeurtenisFunction.class);
    private static final String QUESTION_ID = "v3.1";
    private static final String V3_1_JA = "Ja";
    private static final String V3_1_NEE = "Nee";
    private static final String INDICATIE_GEZAG_BEIDE_OUDERS = "12";
    private static final String INDICATIE_GEZAG_OUDER1 = "1";
    private static final String INDICATIE_GEZAG_OUDER2 = "2";

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {
        // Lokale variabele voor het antwoord (i.p.v. this.answer)
        String answer = null;
        var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // Kies voor exception of andere afhandeling
            throw new AfleidingsregelException("Preconditie: persoonslijst ontbreekt",
                    "Geen plPersoon in gezagsBepaling");
        }
        // Ophalen gezagsverhouding
        var gezagsverhouding = plPersoon.getGezagsverhouding();
        if (gezagsverhouding == null) {
            // In de oude code: gezagsBepaling.addMissendeGegegevens(...) en return;
            gezagsBepaling.addMissendeGegegevens("gezagsverhouding van bevraagde persoon");
            // Functioneel kun je hier evt. een null-answer teruggeven of exception gooien
            return new GezagVraagResult(QUESTION_ID, null);
        }
        // Data uit de gezagsverhouding
        String indicatieGezagMinderjarige = gezagsverhouding.getIndicatieGezagMinderjarige();
        String ingangsdatumGeldigheidGezag = gezagsverhouding.getIngangsdatumGeldigheidGezag();
        // 1) Check: als indicatie "12" (beide ouders) maar in werkelijkheid heeft het kind géén twee ouders
        //    => recent = "Ja"
        if (indicatieGezagMinderjarige != null
                && indicatieGezagMinderjarige.equals(INDICATIE_GEZAG_BEIDE_OUDERS)
                && !plPersoon.heeftTweeOuders()) {
            answer = V3_1_JA;
        }
        // 2) Check: is er überhaupt een geldigheid-datum?
        if (!gezagsverhouding.hasIngangsdatumGeldigheidGezag()) {
            throw new AfleidingsregelException(
                    "Preconditie: Ingangsdatum geldigheid gezag moet een valide datum bevatten",
                    "ingangsdatum gezagsverhouding"
            );
        }
        // 3) Check adoptie na ingangsdatum => 'Ja'
        if (plPersoon.adoptieNaIngangGeldigheidsdatum()) {
            answer = V3_1_JA;
        }
        // 4) Check reparatiehuwelijk als gezag aan 1 ouder is toegewezen, en er ondertussen 2 ouders met BSN zijn
        if (indicatieGezagMinderjarige != null
                && (indicatieGezagMinderjarige.equals(INDICATIE_GEZAG_OUDER1)
                || indicatieGezagMinderjarige.equals(INDICATIE_GEZAG_OUDER2))
                && plPersoon.heeftTweeOuders()
                && plPersoon.beideOudersHebbenEenBSN()) {
            var lplOuder1 = gezagsBepaling.getPlOuder1();
            var lplOuder2 = gezagsBepaling.getPlOuder2();
            if (nuGehuwdOudersNaGeldigheidsdatum(lplOuder1, lplOuder2,
                    ingangsdatumGeldigheidGezag)) {
                answer = V3_1_JA;
            }
        }
        // 5) Als answer nog null is, dan is het "Nee"
        if (answer == null) {
            answer = V3_1_NEE;
        }
        // Logging
        logger.debug("""
                3.1 Is er door een recente gebeurtenis het gezag toch (weer) van rechtswege, 
                    ondanks dat er eerder een uitspraak is gedaan?
                -> {}
                """, answer);
        // Mutatie in je model (optioneel, voor "functioneel" kun je ook overwegen dit weg te laten)
        gezagsBepaling.getArAntwoordenModel().setV0301(answer);
        // Uiteindelijk: input -> output
        return new GezagVraagResult(QUESTION_ID, answer);
    }

    /**
     * Checkt of de ouders pas ná de ingangsdatum gehuwd zijn (reparatiehuwelijk).
     */
    private boolean nuGehuwdOudersNaGeldigheidsdatum(
            Persoonslijst plOuder1,
            Persoonslijst plOuder2,
            String geldigheidsdatum) {

        if (plOuder1 == null || plOuder2 == null) {
            return false;
        }
        // Huwelijken en Partnerschappen van ouder1 waar 'bsnPartner' = bsn van ouder2
        List<HuwelijkOfPartnerschap> hopListOuder1 = getHuwelijkOfPartnerschap(plOuder1,
                plOuder2.getPersoon().getBurgerservicenummer());
        // Huwelijken en Partnerschappen van ouder2 waar 'bsnPartner' = bsn van ouder1
        List<HuwelijkOfPartnerschap> hopListOuder2 = getHuwelijkOfPartnerschap(plOuder2,
                plOuder1.getPersoon().getBurgerservicenummer());
        if (hopListOuder1.isEmpty() || hopListOuder2.isEmpty()) {
            return false;
        }
        // Haal de laatst (op datum gesorteerde) voltrokken huwelijk/partnerschap op
        String hopOuder1Actueel = hopListOuder1.get(hopListOuder1.size() - 1).getDatumVoltrokken();
        String hopOuder2Actueel = hopListOuder2.get(hopListOuder2.size() - 1).getDatumVoltrokken();
        // Check of de datumVoltrokken niet blank is
        boolean beideNietBlank =
                org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder1Actueel)
                        && org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder2Actueel);
        return beideNietBlank
                && (Integer.parseInt(geldigheidsdatum) <= Integer.parseInt(hopOuder1Actueel))
                && (Integer.parseInt(geldigheidsdatum) <= Integer.parseInt(hopOuder2Actueel))
                && (hopListOuder1.size() > 1)
                && (hopListOuder2.size() > 1);
    }

    /**
     * Ophalen van HuwelijkOfPartnerschap entries voor een bepaalde partner-BSN. En sorteren op
     * datumVoltrokken (oplopend).
     */
    private List<HuwelijkOfPartnerschap> getHuwelijkOfPartnerschap(
            Persoonslijst plOuder,
            String bsnPartner) {

        List<HuwelijkOfPartnerschap> hopList = new ArrayList<>();
        var huwelijken = plOuder.getHuwelijkOfPartnerschappen();
        if (huwelijken != null) {
            for (HuwelijkOfPartnerschap hop : huwelijken) {
                // We checken of er een voltrokken/ontbinding-datum is en het BSN van de partner matcht
                if ((hop.getDatumVoltrokken() != null || hop.getDatumOntbinding() != null)
                        && bsnPartner.equals(hop.getBsnPartner())) {
                    hopList.add(hop);
                }
            }
        }
        hopList.sort(
                comparing(HuwelijkOfPartnerschap::getDatumVoltrokken, nullsFirst(naturalOrder())));
        return hopList;
    }
}

