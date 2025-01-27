package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import static java.util.Comparator.comparing;
import static java.util.Comparator.naturalOrder;
import static java.util.Comparator.nullsFirst;

import java.util.ArrayList;
import java.util.List;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.HuwelijkOfPartnerschap;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * v3.1 Ja, als er sprake is van een recente gebeurtenis, anders Nee
 */
@Component
public class IsErSprakeVanEenRecenteGebeurtenis implements GezagVraag {

    private static final Logger logger =
            LoggerFactory.getLogger(IsErSprakeVanEenRecenteGebeurtenis.class);
    private static final String QUESTION_ID = "v3.1";
    private static final String V3_1_JA = "Ja";
    private static final String V3_1_NEE = "Nee";
    private static final String INDICATIE_GEZAG_BEIDE_OUDERS = "12";
    private static final String INDICATIE_GEZAG_OUDER1 = "1";
    private static final String INDICATIE_GEZAG_OUDER2 = "2";

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {

        final var plPersoon = gezagsBepaling.getPlPersoon();
        final var gezagsverhouding = plPersoon.getGezagsverhouding();
        if (gezagsverhouding == null) {
            gezagsBepaling.addMissendeGegegevens("gezagsverhouding van bevraagde persoon");
            return new GezagVraagResult(QUESTION_ID, V3_1_NEE);
        }
        String answer = null;
        final var indicatieGezagMinderjarige = gezagsverhouding.getIndicatieGezagMinderjarige();
        final var ingangsdatumGeldigheidGezag = gezagsverhouding.getIngangsdatumGeldigheidGezag();
        // Check voor ontkenning erkenning: als indicatie = "12" (beide ouders) maar de persoon
        // heeft maar 1 ouder => recente gebeurtenis
        if (indicatieGezagMinderjarige != null
                && indicatieGezagMinderjarige.equals(INDICATIE_GEZAG_BEIDE_OUDERS)
                && !plPersoon.heeftTweeOuders()) {
            answer = V3_1_JA;
        }
        // Check geldigheidsdatum (moet aanwezig zijn)
        if (!gezagsverhouding.hasIngangsdatumGeldigheidGezag()) {
            throw new AfleidingsregelException(
                    "Preconditie: Ingangsdatum geldigheid gezag moet een valide datum bevatten",
                    "ingangsdatum geldigheid van gezagsverhouding");
        }
        // Controleer of adoptie na de ingangsdatum heeft plaatsgevonden => recente gebeurtenis
        if (plPersoon.adoptieNaIngangGeldigheidsdatum()) {
            answer = V3_1_JA;
        }
        // Controleer op 'reparatiehuwelijk' alleen als gezag maar aan 1 ouder is toegekend
        // en beide ouders een BSN hebben.
        if (indicatieGezagMinderjarige != null
                && (indicatieGezagMinderjarige.equals(INDICATIE_GEZAG_OUDER1)
                || indicatieGezagMinderjarige.equals(INDICATIE_GEZAG_OUDER2))
                && plPersoon.heeftTweeOuders()
                && plPersoon.beideOudersHebbenEenBSN()) {
            final var lplOuder1 = gezagsBepaling.getPlOuder1();
            final var lplOuder2 = gezagsBepaling.getPlOuder2();
            if (nuGehuwdOudersNaGeldigheidsdatum(lplOuder1, lplOuder2,
                    ingangsdatumGeldigheidGezag)) {
                answer = V3_1_JA;
            }
        }
        if (answer == null) {
            answer = V3_1_NEE;
        }
        logger.debug("""
                3.1 Is er door een recente gebeurtenis het gezag toch (weer) van rechtswege,
                ondanks dat er eerder een uitspraak is gedaan?
                {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV0301(answer);
        return new GezagVraagResult(QUESTION_ID, answer);
    }

    @Override
    public String getQuestionId() {

        return QUESTION_ID;
    }

    private boolean nuGehuwdOudersNaGeldigheidsdatum(final Persoonslijst plOuder1,
            final Persoonslijst plOuder2,
            final String geldigheidsdatum) {

        if (plOuder1 == null || plOuder2 == null) {
            return false;
        }
        final var hopListOuder1 = getHuwelijkOfPartnerschap(
                plOuder1, plOuder2.getPersoon().getBurgerservicenummer());
        final var hopListOuder2 = getHuwelijkOfPartnerschap(
                plOuder2, plOuder1.getPersoon().getBurgerservicenummer());
        if (hopListOuder1.isEmpty() || hopListOuder2.isEmpty()) {
            return false;
        }
        final var hopOuder1Actueel = hopListOuder1.get(hopListOuder1.size() - 1)
                .getDatumVoltrokken();
        final var hopOuder2Actueel = hopListOuder2.get(hopListOuder2.size() - 1)
                .getDatumVoltrokken();
        return (org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder1Actueel)
                && org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder2Actueel))
                && (Integer.parseInt(geldigheidsdatum) <= Integer.parseInt(hopOuder1Actueel))
                && (Integer.parseInt(geldigheidsdatum) <= Integer.parseInt(hopOuder2Actueel))
                && (hopListOuder1.size() > 1) && (hopListOuder2.size() > 1);
    }

    private List<HuwelijkOfPartnerschap> getHuwelijkOfPartnerschap(final Persoonslijst plOuder,
            final String bsnPartner) {

        final var hopList = new ArrayList<HuwelijkOfPartnerschap>();
        if (plOuder.getHuwelijkOfPartnerschappen() != null) {
            for (final var hop : plOuder.getHuwelijkOfPartnerschappen()) {
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
