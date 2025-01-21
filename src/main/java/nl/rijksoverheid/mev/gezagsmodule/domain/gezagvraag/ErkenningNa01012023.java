package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder1;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder2;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v2a_3
 * VOOR of NA
 */
public class ErkenningNa01012023 extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(ErkenningNa01012023.class);

    private static final String V2A_3_VOOR = "Voor";
    private static final String V2A_3_VOOR_OUDER1 = "Voor_ouder1";
    private static final String V2A_3_VOOR_OUDER2 = "Voor_ouder2";
    private static final String V2A_3_NA = "Na";
    private static final String GESLACHTSAANDUIDING_VROUW = "V";
    private static final int DATE_JAN_1_2023 = 20230101;

    protected ErkenningNa01012023(final GezagsBepaling gezagsBepaling) {
        super(gezagsBepaling);
        currentQuestion = "v2a.3";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();
        Ouder1 persoonOuder1 = plPersoon.getOuder1();
        Ouder2 persoonOuder2 = plPersoon.getOuder2();

        boolean persoonErkend = plPersoon.ongeborenVruchtErkendOfGerechtelijkeVaststelling();
        boolean persoonOngeborenVruchtErkend = plPersoon.ongeborenVruchtErkend();
        boolean isPersoonErkend = persoonErkend || persoonOngeborenVruchtErkend;
        if (!requirementsForRuleAreMet(persoonOuder1, persoonOuder2, isPersoonErkend)) {
            return;
        }

        isPersoonErkendOpOfNa01012023(isPersoonErkend, persoonOuder1, persoonOuder2);
        doorWelkeOuderErkend(plPersoon);
        boolean persoonGeborenVoor01012023 = isPersoonGeborenVoor01012023(persoonErkend, persoonOngeborenVruchtErkend, plPersoon);
        if (persoonGeborenVoor01012023) {
            bepaalGezagOpBasisVanGeboortemoeder(persoonOuder1, persoonOuder2);
        }

        if (answer != null) {
            logger.debug("""
                2a.3 Erkenning voor of na 1-1-2023?
                {}""", answer);
            gezagsBepaling.getArAntwoordenModel().setV02A03(answer);
        } else {
            throw new AfleidingsregelException("Preconditie: vraag 2a.3 - Geboortemoeder niet te bepalen", "Geboortemoeder van bevraagde persoon niet te bepalen");
        }
    }

    private boolean requirementsForRuleAreMet(final Ouder1 persoonOuder1, final Ouder2 persoonOuder2, final boolean isPersoonErkend) {
        if (persoonOuder1 == null || persoonOuder2 == null) {
            String missendeGegeven = (persoonOuder1 == null && persoonOuder2 == null
                ? "beide ouders van bevraagde persoon" : "een ouder van de bevraagde persoon");
            throw new AfleidingsregelException("Preconditie: vraag 2a.3 - Geen twee ouders bij erkenning", missendeGegeven);
        }

        if (isPersoonErkend && persoonOuder1.getDatumIngangFamiliebetrekking() == null) {
            gezagsBepaling.addMissendeGegegevens("datum ingang familie betrekking van ouder 1");
            return false;
        } else if (isPersoonErkend && persoonOuder2.getDatumIngangFamiliebetrekking() == null) {
            gezagsBepaling.addMissendeGegegevens("datum ingang familie betrekking van ouder 2");
            return false;
        }

        return true;
    }

    private void isPersoonErkendOpOfNa01012023(final boolean isPersoonErkend, final Ouder1 persoonOuder1, final Ouder2 persoonOuder2) {
        if (isPersoonErkend) {
            boolean ouder1ErkendOpOfNa01012023 = Integer.parseInt(persoonOuder1.getDatumIngangFamiliebetrekking()) >= DATE_JAN_1_2023;
            boolean ouder2ErkendOpOfNa01012023 = Integer.parseInt(persoonOuder2.getDatumIngangFamiliebetrekking()) >= DATE_JAN_1_2023;
            if (ouder1ErkendOpOfNa01012023 || ouder2ErkendOpOfNa01012023) {
                answer = V2A_3_NA;
            }
        }
    }

    private void doorWelkeOuderErkend(final Persoonslijst plPersoon) {
        boolean ouder1Erkend = plPersoon.ongeborenVruchtDoorOuder1ErkendOfGerechtelijkeVaststelling();
        boolean ouder2Erkend = plPersoon.ongeborenVruchtDoorOuder2ErkendOfGerechtelijkeVaststelling();
        if (answer == null && ouder1Erkend) {
            answer = V2A_3_VOOR_OUDER2;
        }
        if (answer == null && ouder2Erkend) {
            answer = V2A_3_VOOR_OUDER1;
        }
    }

    private boolean isPersoonGeborenVoor01012023(final boolean persoonErkend, final boolean persoonOngeborenVruchtErkend, final Persoonslijst plPersoon) {
        if (answer == null && !persoonErkend && persoonOngeborenVruchtErkend) {
            return Integer.parseInt(plPersoon.getPersoon().getGeboortedatum()) < DATE_JAN_1_2023;
        }

        return false;
    }

    /**
     * Geboortemoeder bepaling:
     * - als 1 van de ouders vrouw is en de andere niet, is de vrouw de moeder
     *
     * @param persoonOuder1 ouder 1 van de persoon
     * @param persoonOuder2 ouder 2 van de persoon
     */
    private void bepaalGezagOpBasisVanGeboortemoeder(
        final Ouder1 persoonOuder1,
        final Ouder2 persoonOuder2) {
        boolean isOuder1Vrouw = isVrouw(persoonOuder1.getGeslachtsAanduiding());
        boolean isOuder2Vrouw = isVrouw(persoonOuder2.getGeslachtsAanduiding());

        if(eenVanDeOudersVrouw(isOuder1Vrouw, isOuder2Vrouw)) {
            answer = isOuder1Vrouw ? V2A_3_VOOR_OUDER1 : V2A_3_VOOR_OUDER2;
        } else {
            answer = V2A_3_VOOR;
        }
    }

    private boolean isVrouw(final String geslachtsAand) {
        return GESLACHTSAANDUIDING_VROUW.equals(geslachtsAand);
    }

    private boolean eenVanDeOudersVrouw(final boolean isOuder1Vrouw, final boolean isOuder2Vrouw) {
        return isOuder1Vrouw ^ isOuder2Vrouw;
    }
}