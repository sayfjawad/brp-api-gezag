package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import static nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst.isValideGeslachtsnaam;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.domain.HopRelatie;
import nl.rijksoverheid.mev.gezagsmodule.domain.HopRelaties;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * v2b_1 "Ja" als kind staande huwelijk of partnerschap geboren is, anders "Nee".
 * <p>
 * Nu vastgesteld is dat het kind 1 (of 2) ouder(s) heeft, we controleren of deze ouder(s) een
 * relatie had(den) tijdens de geboorte van het kind. Als dat het geval is (en er geen ontkenning
 * van ouderschap door de ander is), dan is het antwoord "Ja".
 */
@Component
@Scope("prototype")
public class IsStaandeHuwelijkOfPartnerschapGeborenFunction implements GezagVraagFunction {

    private static final Logger logger =
            LoggerFactory.getLogger(IsStaandeHuwelijkOfPartnerschapGeborenFunction.class);

    private static final String V2B_1_JA  = "Ja";
    private static final String V2B_1_NEE = "Nee";
    private static final String OUDER_1   = "ouder1";
    private static final String OUDER_2   = "ouder2";
    private static final String QUESTION_ID = "v2b.1";

    @Override
    public String getQuestionId() {
        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {

        final var plPersoon = gezagsBepaling.getPlPersoon();
        // Initialize answer; this may be changed to "Ja" if conditions are met.
        String answer = V2B_1_NEE;

        final var geboorteDatumKind = plPersoon.getPersoon().getGeboortedatum();
        final var lOuder1 = plPersoon.getOuder1();
        final var lOuder2 = plPersoon.getOuder2();

        if (lOuder1 != null && isValideGeslachtsnaam(lOuder1.getGeslachtsnaam())) {
            final var plOuder1 = gezagsBepaling.getPlOuder1();
            preconditieCheckGeregistreerd(OUDER_1, plOuder1);

            if (heeftOuderRelatieBijGeboorteKind(plOuder1, geboorteDatumKind)
                    && !plPersoon.ontkenningOuderschapDoorOuder2()) {
                answer = V2B_1_JA;
            }
        }

        if (lOuder2 != null && isValideGeslachtsnaam(lOuder2.getGeslachtsnaam())) {
            final var plOuder2 = gezagsBepaling.getPlOuder2();
            preconditieCheckGeregistreerd(OUDER_2, plOuder2);

            if (heeftOuderRelatieBijGeboorteKind(plOuder2, geboorteDatumKind)
                    && !plPersoon.ontkenningOuderschapDoorOuder1()) {
                answer = V2B_1_JA;
            }
        }

        logger.debug("""
                2b.1 Is het kind staande huwelijk of partnerschap geboren?
                {}""", answer);

        gezagsBepaling.getArAntwoordenModel().setV02B01(answer);

        return new GezagVraagResult(QUESTION_ID, answer);
    }

    public boolean heeftOuderRelatieBijGeboorteKind(final Persoonslijst plOuder,
            final String geboortedatum) {

        final var hopPlOuder = plOuder.getHuwelijkOfPartnerschappen();
        if (!hopPlOuder.isEmpty() && hopPlOuder.get(0).getBsnPartner() != null) {
            final var hopRelaties = plOuder.getHopRelaties();
            final var geborenInRelatie =
                    hopRelaties.geborenInRelatie(Integer.parseInt(geboortedatum));
            return geborenInRelatie != null;
        }
        return false;
    }

    public void preconditieCheckGeregistreerd(final String beschrijving,
            final Persoonslijst plOuder)
            throws GezagException {

        final var ouderGeregistreerdInBrp =
                plOuder != null
                        && plOuder.isNietIngeschrevenInRNI()
                        && plOuder.isNietGeemigreerd();

        if (!ouderGeregistreerdInBrp) {
            throw new AfleidingsregelException(
                    "Preconditie: " + beschrijving + " moet in BRP geregistreerd staan",
                    beschrijving + " van bevraagde persoon is niet in BRP geregistreerd");
        }
    }
}
