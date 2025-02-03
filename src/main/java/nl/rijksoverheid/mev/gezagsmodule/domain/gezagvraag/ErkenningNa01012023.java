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

    private static final Logger logger = LoggerFactory.getLogger(ErkenningNa01012023.class);
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
        Ouder1 persoonOuder1 = plPersoon.getOuder1();
        Ouder2 persoonOuder2 = plPersoon.getOuder2();

        final var persoonErkend = plPersoon.ongeborenVruchtErkendOfGerechtelijkeVaststelling();
        final var persoonOngeborenVruchtErkend = plPersoon.ongeborenVruchtErkend();
        final var isPersoonErkend = persoonErkend || persoonOngeborenVruchtErkend;
        if (!requirementsForRuleAreMet(persoonOuder1, persoonOuder2, isPersoonErkend, gezagsBepaling)) {
            return new GezagVraagResult(QUESTION_ID, answer);
        }

        answer = isPersoonErkendOpOfNa01012023(isPersoonErkend, persoonOuder1, persoonOuder2);
        if (answer == null) {
            answer = doorWelkeOuderErkend(plPersoon);
        }
        boolean persoonGeborenVoor01012023 = isPersoonGeborenVoor01012023(persoonErkend, persoonOngeborenVruchtErkend, plPersoon, answer);
        if (persoonGeborenVoor01012023) {
            answer = bepaalGezagOpBasisVanGeboortemoeder(persoonOuder1, persoonOuder2);
        }

        if (answer != null) {
            logger.debug("""
                2a.3 Erkenning voor of na 1-1-2023?
                {}""", answer);
            gezagsBepaling.getArAntwoordenModel().setV02A03(answer);
            return new GezagVraagResult(QUESTION_ID, answer);
        } else {
            throw new AfleidingsregelException(
                "Preconditie: vraag 2a.3 - Geboortemoeder niet te bepalen",
                "Geboortemoeder van bevraagde persoon niet te bepalen"
            );
        }
    }

    private boolean requirementsForRuleAreMet(
        final Ouder1 persoonOuder1,
        final Ouder2 persoonOuder2,
        final boolean isPersoonErkend,
        final GezagsBepaling gezagsBepaling) {
        if (persoonOuder1 == null || persoonOuder2 == null) {
            throw new AfleidingsregelException(
                "Preconditie: vraag 2a.3 - Geen twee ouders bij erkenning",
                persoonOuder1 == null && persoonOuder2 == null
                    ? "beide ouders van bevraagde persoon"
                    : "een ouder van de bevraagde persoon"
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

    private String isPersoonErkendOpOfNa01012023(
        final boolean isPersoonErkend,
        final Ouder1 persoonOuder1,
        final Ouder2 persoonOuder2) {
        if (isPersoonErkend) {
            boolean ouder1ErkendOpOfNa01012023 = Integer.parseInt(persoonOuder1.getDatumIngangFamiliebetrekking()) >= DATE_JAN_1_2023;
            boolean ouder2ErkendOpOfNa01012023 = Integer.parseInt(persoonOuder2.getDatumIngangFamiliebetrekking()) >= DATE_JAN_1_2023;
            if (ouder1ErkendOpOfNa01012023 || ouder2ErkendOpOfNa01012023) {
                return V2A_3_NA;
            }
        }

        return null;
    }

    private String doorWelkeOuderErkend(final Persoonslijst plPersoon) {
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

    private String bepaalGezagOpBasisVanGeboortemoeder(
        final Ouder1 persoonOuder1,
        final Ouder2 persoonOuder2) {
        boolean isOuder1Vrouw = isVrouw(persoonOuder1.getGeslachtsAanduiding());
        boolean isOuder2Vrouw = isVrouw(persoonOuder2.getGeslachtsAanduiding());

        if (eenVanDeOudersVrouw(isOuder1Vrouw, isOuder2Vrouw)) {
            return isOuder1Vrouw ? V2A_3_VOOR_OUDER1 : V2A_3_VOOR_OUDER2;
        } else {
            return V2A_3_VOOR;
        }
    }

    private boolean isVrouw(final String geslachtsAand) {
        return GESLACHTSAANDUIDING_VROUW.equals(geslachtsAand);
    }

    private boolean eenVanDeOudersVrouw(final boolean isOuder1Vrouw, final boolean isOuder2Vrouw) {
        return isOuder1Vrouw ^ isOuder2Vrouw;
    }
}
