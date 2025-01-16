package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;


import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder1;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder2;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v2a_3
 * VOOR of NA
 */
public class ErkenningNa01012023Function implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(ErkenningNa01012023Function.class);

    private static final String QUESTION_ID = "v2a.3";
    private static final String V2A_3_VOOR         = "Voor";
    private static final String V2A_3_VOOR_OUDER1  = "Voor_ouder1";
    private static final String V2A_3_VOOR_OUDER2  = "Voor_ouder2";
    private static final String V2A_3_NA           = "Na";

    private static final String GESLACHTSAANDUIDING_VROUW = "V";

    private static final int DATE_JAN_1_2023 = 20230101;

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {
        // Houd de 'answer' als een lokale variabele bij
        String answer;

        var plPersoon     = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // Of je hier een exception wilt gooien of iets anders, is aan jou.
            throw new AfleidingsregelException("Preconditie: persoon ontbreekt", "Er is geen persoonslijst in gezagsBepaling");
        }
        var persoonOuder1 = plPersoon.getOuder1();
        var persoonOuder2 = plPersoon.getOuder2();

        boolean persoonErkend               = plPersoon.ongeborenVruchtErkendOfGerechtelijkeVaststelling();
        boolean persoonOngeborenVruchtErkend= plPersoon.ongeborenVruchtErkend();
        boolean isPersoonErkend             = persoonErkend || persoonOngeborenVruchtErkend;

        // Check de precondities. Als niet voldaan: ofwel exception, ofwel "stoppen" met null-result.
        if (!requirementsForRuleAreMet(persoonOuder1, persoonOuder2, isPersoonErkend, gezagsBepaling)) {
            // In de originele code werd hier gewoon 'return;' gedaan (void).
            // In een functionele benadering kun je bv. een GezagVraagResult teruggeven met een null-antwoord
            // of een exception gooien. Hier doen we een "leeg" resultaat:
            return new GezagVraagResult(QUESTION_ID, null);
        }

        // 1. Bepaal of de erkenning (van Ouder1 of Ouder2) op of na 01-01-2023 is
        answer = isPersoonErkendOpOfNa01012023(isPersoonErkend, persoonOuder1, persoonOuder2);

        // 2. Check welke ouder erkend heeft (als 'answer' nog null is)
        answer = doorWelkeOuderErkend(plPersoon, answer);

        // 3. Check of persoon geboren is voor 01-01-2023
        boolean persoonGeborenVoor01012023 = isPersoonGeborenVoor01012023(
                persoonErkend,
                persoonOngeborenVruchtErkend,
                plPersoon,
                answer
        );
        if (persoonGeborenVoor01012023) {
            answer = bepaalGezagInCombinatieMetGeboortemoeder(plPersoon, persoonOuder1, persoonOuder2, answer);
        }

        // 4. Als we nu een definitief antwoord hebben, loggen we en zetten we het in het ArAntwoordenModel
        if (answer != null) {
            logger.debug("2a.3 Erkenning voor of na 1-1-2023? -> {}", answer);
            // Als je de mutatie in je "functionele" code wilt behouden:
            gezagsBepaling.getArAntwoordenModel().setV02A03(answer);

            // En we geven het als functioneel resultaat terug
            return new GezagVraagResult(QUESTION_ID, answer);
        } else {
            // Preconditie niet te bepalen -> gooi exception of valideer op andere manier
            throw new AfleidingsregelException(
                    "Preconditie: vraag 2a.3 - Geboortemoeder niet te bepalen",
                    "Geboortemoeder van bevraagde persoon niet te bepalen"
            );
        }
    }

    /**
     *  Controleer of alle minimaal vereiste data er is.
     *  Retourneert 'true' als je door mag gaan, of 'false' als je moet stoppen.
     *  Gooit (in sommige gevallen) een AfleidingsregelException.
     */
    private boolean requirementsForRuleAreMet(
            final Ouder1 persoonOuder1,
            final Ouder2 persoonOuder2,
            final boolean isPersoonErkend,
            final GezagsBepaling gezagsBepaling) {

        if (persoonOuder1 == null || persoonOuder2 == null) {
            String missendeGegeven = (persoonOuder1 == null && persoonOuder2 == null)
                    ? "beide ouders van bevraagde persoon"
                    : "een ouder van de bevraagde persoon";
            throw new AfleidingsregelException(
                    "Preconditie: vraag 2a.3 - Geen twee ouders bij erkenning",
                    missendeGegeven
            );
        }

        // Als de persoon is erkend, check of we een 'datumIngangFamiliebetrekking' hebben
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
     *  Bepaalt of erkenning door (ouder1 of ouder2) op of na 01-01-2023 is, en zet het antwoord op "Na".
     *  Als er geen erkenning is, of het is vóór 01-01-2023, laten we het antwoord op null.
     */
    private String isPersoonErkendOpOfNa01012023(
            final boolean isPersoonErkend,
            final Ouder1 persoonOuder1,
            final Ouder2 persoonOuder2) {

        String localAnswer = null;
        if (isPersoonErkend) {
            boolean ouder1ErkendOpOfNa01012023 =
                    Integer.parseInt(persoonOuder1.getDatumIngangFamiliebetrekking()) >= DATE_JAN_1_2023;
            boolean ouder2ErkendOpOfNa01012023 =
                    Integer.parseInt(persoonOuder2.getDatumIngangFamiliebetrekking()) >= DATE_JAN_1_2023;
            if (ouder1ErkendOpOfNa01012023 || ouder2ErkendOpOfNa01012023) {
                localAnswer = V2A_3_NA;
            }
        }
        return localAnswer;
    }

    /**
     *  Stel vast door welke ouder de ongeboren vrucht erkend is.
     *  Als answer nog niet gevuld is (= null), vullen we het op basis van de ouder die erkend heeft.
     */
    private String doorWelkeOuderErkend(final Persoonslijst plPersoon, final String currentAnswer) {
        // Als we al een answer hebben, laten we 'm staan.
        if (currentAnswer != null) {
            return currentAnswer;
        }

        // Anders checken we wie erkend heeft
        boolean ouder1Erkend = plPersoon.ongeborenVruchtDoorOuder1ErkendOfGerechtelijkeVaststelling();
        boolean ouder2Erkend = plPersoon.ongeborenVruchtDoorOuder2ErkendOfGerechtelijkeVaststelling();

        // In de originele code:
        //   if (answer == null && ouder1Erkend) => V2A_3_VOOR_OUDER2;
        //   if (answer == null && ouder2Erkend) => V2A_3_VOOR_OUDER1;
        // Let op: in de code is het omgedraaid (misschien om paternal/maternal redenen).
        // We handhaven het zoals het was.
        if (ouder1Erkend) {
            return V2A_3_VOOR_OUDER2;
        }
        if (ouder2Erkend) {
            return V2A_3_VOOR_OUDER1;
        }

        return null; // niemand erkend => null
    }

    /**
     *  Check of de persoon feitelijk vóór 01-01-2023 geboren is, in het geval
     *  er wel een ongeboren vrucht erkenning was, maar nog geen 'answer' is gezet.
     */
    private boolean isPersoonGeborenVoor01012023(
            final boolean persoonErkend,
            final boolean persoonOngeborenVruchtErkend,
            final Persoonslijst plPersoon,
            final String currentAnswer) {

        if (currentAnswer == null && !persoonErkend && persoonOngeborenVruchtErkend) {
            return Integer.parseInt(plPersoon.getPersoon().getGeboortedatum()) < DATE_JAN_1_2023;
        }
        return false;
    }

    /**
     *  Bepaal de geboortemoeder in combinatie met de andere ouder (welke vrouw is?),
     *  eventueel op basis van geslachtsnaam.
     */
    private String bepaalGezagInCombinatieMetGeboortemoeder(
            final Persoonslijst plPersoon,
            final Ouder1 persoonOuder1,
            final Ouder2 persoonOuder2,
            final String currentAnswer) {

        // Als we al antwoord hebben, laat het staan
        if (currentAnswer != null) {
            return currentAnswer;
        }

        boolean isOuder1Vrouw = isVrouw(persoonOuder1.getGeslachtsAanduiding());
        boolean isOuder2Vrouw = isVrouw(persoonOuder2.getGeslachtsAanduiding());

        // 1) Als exact één ouder vrouw is, is die de moeder
        if (isOuder1Vrouw ^ isOuder2Vrouw) {
            return isOuder1Vrouw ? V2A_3_VOOR_OUDER1 : V2A_3_VOOR_OUDER2;
        }
        // 2) Als beide ouders vrouw zijn, baseer het op de geslachtsnaam
        else if (isOuder1Vrouw && isOuder2Vrouw) {
            return bepaalOpBasisVanGeslachtsNaam(
                    plPersoon.getPersoon().getGeslachtsnaam(),
                    persoonOuder1.getGeslachtsnaam(),
                    persoonOuder2.getGeslachtsnaam()
            );
        }
        // 3) Als beide ouders géén vrouw zijn, is het "Voor"
        return V2A_3_VOOR;
    }

    private boolean isVrouw(final String geslachtsAand) {
        return GESLACHTSAANDUIDING_VROUW.equals(geslachtsAand);
    }

    /**
     *  Bepaalt op basis van de geslachtsnaam van het kind welke ouder de geboortemoeder is
     *  (indien beide ouders vrouw zijn).
     */
    private String bepaalOpBasisVanGeslachtsNaam(
            final String geslachtsnaamKind,
            final String geslachtsnaamOuder1,
            final String geslachtsnaamOuder2) {

        if (geslachtsnaamKind == null) {
            return null;
        }

        // Als kind dezelfde naam heeft als ouder1 (maar niet als ouder2) => ouder1
        if (geslachtsnaamKind.equals(geslachtsnaamOuder1)
                && !geslachtsnaamKind.equals(geslachtsnaamOuder2)) {
            return V2A_3_VOOR_OUDER1;
        }
        // Omgekeerd => ouder2
        else if (!geslachtsnaamKind.equals(geslachtsnaamOuder1)
                && geslachtsnaamKind.equals(geslachtsnaamOuder2)) {
            return V2A_3_VOOR_OUDER2;
        }
        // Anders kunnen we het niet eenduidig bepalen
        return null;
    }
}