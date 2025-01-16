package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import static nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst.isValideGeslachtsnaam;

import java.util.List;
import nl.rijksoverheid.mev.gezagsmodule.domain.HopRelatie;
import nl.rijksoverheid.mev.gezagsmodule.domain.HopRelaties;
import nl.rijksoverheid.mev.gezagsmodule.domain.HuwelijkOfPartnerschap;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder1;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder2;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v2b_1 "Ja" als kind staande huwelijk of partnerschap geboren is, anders "Nee".
 * <p>
 * Nu vastgesteld is dat het kind 1 (of 2) ouder(s) heeft, we controleren of deze ouder(s) een
 * relatie had(den) tijdens de geboorte van het kind. Als dat het geval is (en er geen ontkenning
 * van ouderschap door de ander is), dan is het antwoord "Ja".
 */
public class IsStaandeHuwelijkOfPartnerschapGeborenFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(
            IsStaandeHuwelijkOfPartnerschapGeborenFunction.class);
    private static final String QUESTION_ID = "v2b.1";
    private static final String V2B_1_JA = "Ja";
    private static final String V2B_1_NEE = "Nee";
    private static final String OUDER_1 = "ouder1";
    private static final String OUDER_2 = "ouder2";

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {
        // Lokaal 'answer' i.p.v. veld
        String answer = V2B_1_NEE;
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // Ofwel gooi je een exception, of je retourneert null
            throw new IllegalStateException("Persoonslijst (kind) ontbreekt in GezagsBepaling.");
        }
        // Haal data op
        String geboorteDatumKind = plPersoon.getPersoon().getGeboortedatum();
        Ouder1 lOuder1 = plPersoon.getOuder1();
        Ouder2 lOuder2 = plPersoon.getOuder2();
        // Logica om te checken: Is Ouder1 geregistreerd, heeft (actuele) relatie bij geboorte?
        if (lOuder1 != null && isValideGeslachtsnaam(lOuder1.getGeslachtsnaam())) {
            Persoonslijst plOuder1 = gezagsBepaling.getPlOuder1();
            preconditieCheckGeregistreerd(OUDER_1, plOuder1, gezagsBepaling);
            if (heeftOuderRelatieBijGeboorteKind(plOuder1, geboorteDatumKind)
                    && !plPersoon.ontkenningOuderschapDoorOuder2()) {
                answer = V2B_1_JA;
            }
        }
        // Logica om te checken: Is Ouder2 geregistreerd, heeft (actuele) relatie bij geboorte?
        if (lOuder2 != null && isValideGeslachtsnaam(lOuder2.getGeslachtsnaam())) {
            Persoonslijst plOuder2 = gezagsBepaling.getPlOuder2();
            preconditieCheckGeregistreerd(OUDER_2, plOuder2, gezagsBepaling);
            if (heeftOuderRelatieBijGeboorteKind(plOuder2, geboorteDatumKind)
                    && !plPersoon.ontkenningOuderschapDoorOuder1()) {
                answer = V2B_1_JA;
            }
        }
        // Logging
        logger.debug("2b.1 Is het kind staande huwelijk of partnerschap geboren? -> {}", answer);
        // (Optioneel) mutatie in je model
        gezagsBepaling.getArAntwoordenModel().setV02B01(answer);
        // Retourneer het functionele resultaat
        return new GezagVraagResult(QUESTION_ID, answer);
    }

    /**
     * Dezelfde logica als in de oude code, alleen is 'this' niet nodig.
     */
    private boolean heeftOuderRelatieBijGeboorteKind(final Persoonslijst plOuder,
            final String geboortedatum) {

        List<HuwelijkOfPartnerschap> hopPlOuder = plOuder.getHuwelijkOfPartnerschappen();
        if (!hopPlOuder.isEmpty() && (hopPlOuder.get(0).getBsnPartner() != null)) {
            HopRelaties hopRelaties = plOuder.getHopRelaties();
            HopRelatie geborenInRelatie = hopRelaties.geborenInRelatie(
                    Integer.parseInt(geboortedatum));
            return geborenInRelatie != null;
        }
        return false;
    }

    /**
     * Aangepaste versie van preconditie-check, want in de oude code deden we:
     * preconditieCheckGeregistreerd("ouder1", plOuder1); nu geven we ook 'gezagsBepaling' mee om
     * 'AfleidingsregelException' te kunnen gooien of 'missendeGegegevens' te registreren.
     */
    private void preconditieCheckGeregistreerd(String beschrijving, Persoonslijst plOuder,
            GezagsBepaling gezagsBepaling) {
        // In de oude code checkte je of plOuder != null, isNietIngeschrevenInRNI, isNietGeemigreerd, etc.
        if (plOuder == null || !plOuder.isNietIngeschrevenInRNI() || !plOuder.isNietGeemigreerd()) {
            // In de oude code gooide je misschien een 'AfleidingsregelException'
            // of 'gezagsBepaling.addMissendeGegegevens(...)'
            // Hier laten we het zien als (bijv.) een IllegalStateException:
            throw new IllegalStateException(
                    "Preconditie: " + beschrijving + " moet in BRP geregistreerd staan");
        }
    }
}

