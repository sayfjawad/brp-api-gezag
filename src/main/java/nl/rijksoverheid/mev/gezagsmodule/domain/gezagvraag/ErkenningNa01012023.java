package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder1;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder2;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.lang.Integer.parseInt;

/**
 * v2a_3 VOOR of NA
 */
@Component
public class ErkenningNa01012023 implements GezagVraag {

    private static final Logger logger =
            LoggerFactory.getLogger(ErkenningNa01012023.class);
    private static final String QUESTION_ID = "v2a.3";
    private static final String V2A_3_VOOR = "Voor";
    private static final String V2A_3_VOOR_OUDER1 = "Voor_ouder1";
    private static final String V2A_3_VOOR_OUDER2 = "Voor_ouder2";
    private static final String V2A_3_NA = "Na";
    private static final String GESLACHTSAANDUIDING_VROUW = "V";
    private static final int DATE_JAN_1_2023 = 20230101;

    @Override
    public String getQuestionId() {
        return QUESTION_ID;
    }

    @Override
    public GezagVraagResult perform(final GezagsBepaling gezagsBepaling) {
        String answer = null;
        final var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            throw new AfleidingsregelException(
                    "Preconditie: persoon ontbreekt",
                    "Er is geen persoonslijst in gezagsBepaling");
        }
        final var persoonOuder1 = plPersoon.getOuder1();
        final var persoonOuder2 = plPersoon.getOuder2();
        final var persoonErkend = plPersoon.ongeborenVruchtErkendOfGerechtelijkeVaststelling();
        final var persoonOngeborenVruchtErkend = plPersoon.ongeborenVruchtErkend();
        final var isPersoonErkend = persoonErkend || persoonOngeborenVruchtErkend;
        if (!requirementsForRuleAreMet(persoonOuder1, persoonOuder2, isPersoonErkend,
                gezagsBepaling)) {
            return new GezagVraagResult(QUESTION_ID, answer);
        }
        answer = isPersoonErkendOpOfNa01012023(isPersoonErkend, persoonOuder1, persoonOuder2);
        answer = doorWelkeOuderErkend(plPersoon, answer);
        final var persoonGeborenVoor01012023 = isPersoonGeborenVoor01012023(
                persoonErkend,
                persoonOngeborenVruchtErkend,
                plPersoon,
                answer
        );
        if (persoonGeborenVoor01012023) {
            answer = bepaalGezagInCombinatieMetGeboortemoeder(plPersoon, persoonOuder1,
                    persoonOuder2, answer);
        }
        if (answer != null) {
            logger.debug("2a.3 Erkenning voor of na 1-1-2023? -> {}", answer);
            gezagsBepaling.getArAntwoordenModel().setV02A03(answer);
            return new GezagVraagResult(QUESTION_ID, answer);
        } else {
            throw new AfleidingsregelException(
                    "Preconditie: vraag 2a.3 - Geboortemoeder niet te bepalen",
                    "Geboortemoeder van bevraagde persoon niet te bepalen"
            );
        }
    }

    /**
     * Controleer of alle minimaal vereiste data er is. Retourneert 'true' als je door mag gaan, of
     * 'false' als je moet stoppen.
     */
    private boolean requirementsForRuleAreMet(
            final Ouder1 persoonOuder1,
            final Ouder2 persoonOuder2,
            final boolean isPersoonErkend,
            final GezagsBepaling gezagsBepaling) {
        if (persoonOuder1 == null || persoonOuder2 == null) {
            final var missendeGegeven = (persoonOuder1 == null && persoonOuder2 == null)
                    ? "beide ouders van bevraagde persoon"
                    : "een ouder van de bevraagde persoon";
            throw new AfleidingsregelException(
                    "Preconditie: vraag 2a.3 - Geen twee ouders bij erkenning",
                    missendeGegeven
            );
        }
        if (isPersoonErkend && persoonOuder1.getDatumIngangFamiliebetrekking() == null) {
            gezagsBepaling.addMissendeGegegevens("datum ingang familiebetrekking van ouder 1");
            return false;
        } else if (isPersoonErkend && persoonOuder2.getDatumIngangFamiliebetrekking() == null) {
            gezagsBepaling.addMissendeGegegevens("datum ingang familiebetrekking van ouder 2");
            return false;
        }
        return true;
    }

    /**
     * Bepaalt of erkenning door (ouder1 of ouder2) op of na 01-01-2023 is, en zet het antwoord op
     * "Na". Als er geen erkenning is, of het is vóór 01-01-2023, return null.
     */
    private String isPersoonErkendOpOfNa01012023(
            final boolean isPersoonErkend,
            final Ouder1 persoonOuder1,
            final Ouder2 persoonOuder2) {
        String localAnswer = null;
        if (isPersoonErkend) {
            final var ouder1ErkendOpOfNa01012023 =
                    parseInt(persoonOuder1.getDatumIngangFamiliebetrekking()) >= DATE_JAN_1_2023;
            final var ouder2ErkendOpOfNa01012023 =
                    parseInt(persoonOuder2.getDatumIngangFamiliebetrekking()) >= DATE_JAN_1_2023;
            if (ouder1ErkendOpOfNa01012023 || ouder2ErkendOpOfNa01012023) {
                localAnswer = V2A_3_NA;
            }
        }
        return localAnswer;
    }

    /**
     * Stel vast door welke ouder de ongeboren vrucht erkend is. Als answer nog niet gevuld is (=
     * null), vullen we het op basis van de ouder die erkend heeft.
     */
    private String doorWelkeOuderErkend(final Persoonslijst plPersoon, final String currentAnswer) {
        if (currentAnswer != null) {
            return currentAnswer;
        }
        final var ouder1Erkend = plPersoon.ongeborenVruchtDoorOuder1ErkendOfGerechtelijkeVaststelling();
        final var ouder2Erkend = plPersoon.ongeborenVruchtDoorOuder2ErkendOfGerechtelijkeVaststelling();
        if (ouder1Erkend) {
            return V2A_3_VOOR_OUDER2;
        }
        if (ouder2Erkend) {
            return V2A_3_VOOR_OUDER1;
        }
        return null;
    }

    /**
     * Check of de persoon feitelijk vóór 01-01-2023 geboren is, in het geval er wel een ongeboren
     * vrucht erkenning was, maar nog geen 'answer' is gezet.
     */
    private boolean isPersoonGeborenVoor01012023(
            final boolean persoonErkend,
            final boolean persoonOngeborenVruchtErkend,
            final Persoonslijst plPersoon,
            final String currentAnswer) {
        if (currentAnswer == null && !persoonErkend && persoonOngeborenVruchtErkend) {
            return parseInt(plPersoon.getPersoon().getGeboortedatum()) < DATE_JAN_1_2023;
        }
        return false;
    }

    /**
     * Bepaal de geboortemoeder in combinatie met de andere ouder (welke vrouw is?), eventueel op
     * basis van geslachtsnaam.
     */
    private String bepaalGezagInCombinatieMetGeboortemoeder(
            final Persoonslijst plPersoon,
            final Ouder1 persoonOuder1,
            final Ouder2 persoonOuder2,
            final String currentAnswer) {
        if (currentAnswer != null) {
            return currentAnswer;
        }
        final var isOuder1Vrouw = isVrouw(persoonOuder1.getGeslachtsAanduiding());
        final var isOuder2Vrouw = isVrouw(persoonOuder2.getGeslachtsAanduiding());
        if (isOuder1Vrouw ^ isOuder2Vrouw) {
            return isOuder1Vrouw ? V2A_3_VOOR_OUDER1 : V2A_3_VOOR_OUDER2;
        }
        return V2A_3_VOOR;
    }

    private boolean isVrouw(final String geslachtsAand) {
        return GESLACHTSAANDUIDING_VROUW.equals(geslachtsAand);
    }
}
