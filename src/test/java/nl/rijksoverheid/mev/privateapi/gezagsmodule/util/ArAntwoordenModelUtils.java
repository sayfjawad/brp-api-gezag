package nl.rijksoverheid.mev.privateapi.gezagsmodule.util;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Gebruikte afkortingen:
 * AR = Afleidingsregel
 **/
public class ArAntwoordenModelUtils {
    private static final String GEZAG_ONBEKEND = "N";
    private static final String EENOUDER_GEZAG = "OG1";
    private static final String TWEEOUDER_GEZAG = "OG2";
    private static final String GEEN_GEZAG = "G";
    private static final String GEMEENSCHAPPELIJK_GEZAG = "GG";
    private static final String VOOGDIJ = "V";
    private static final String NIET_VAN_TOEPASSING = "NVT";

    public static ARAntwoordenModel createARAntwoordenModel(String route) {
        return switch (route) {
            case "0", "-503i", "1i", "53iii" -> new ARAntwoordenModel(null, null, null, null, null, null
                , null, null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "2i" -> new ARAntwoordenModel("Ja", null, null, null, null, null
                , null, null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "2e" -> new ARAntwoordenModel("Ja", null, null, null, null, null
                , null, null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "2mi" -> new ARAntwoordenModel("Ja", null, null, null, null, null
                , null, null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "2o" -> new ARAntwoordenModel("Ja", "Nee_overleden", null, null, null, null
                , null, null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "2m" -> new ARAntwoordenModel("Ja", "Nee_meerderjarig", null, null, null, null
                , null, null, null, null, null, null
                , null, null, null, null, null, null,
                NIET_VAN_TOEPASSING, null, null, null, null, null, null);
            case "1" -> new ARAntwoordenModel("Nee", null, null, null, null, null
                , null, null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "3i" -> new ARAntwoordenModel("Ja", "Ja", null, null, null, null
                , null, null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "3" -> new ARAntwoordenModel("Ja", "Ja", "Ja", null, null, null
                , null, null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "3a" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Ja", "Nee", null
                , null, null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "3ai" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Ja", null, null
                , null, null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "4i" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, null, null, null, null,
                null, null, "Nee", "1D", null, null, null
                , null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "4ee" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "4" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "1D", null, null, null, null, null,
                VOOGDIJ, null, null, null, null, null, null);
            case "5" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja", null, null, null,
                null, null, "Nee", "2D", null, null, null
                , null, null,
                VOOGDIJ, null, null, null, null, null, null);
            case "6" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "D", null, null, null, null, null,
                VOOGDIJ, null, null, null, null, null, null);
            case "7e" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "12", null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "7i" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "12", null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "7" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "12", "Nee", null, null, null, null,
                TWEEOUDER_GEZAG, null, null, null, null, null, null);
            case "8" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "12", "Ja_ouder1", null, null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "9" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "12", "Ja_ouder2", null, null, null, null,
                "OG1", null, null, null, null, null, null);
            case "11" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "1", null, "Nee", null, null, null,
                "OG1", null, null, null, null, null, null);
            case "11o1" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "1", null, "Nee_ouder1", null, null, null,
                "OG1", null, null, null, null, null, null);
            case "11o2" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "1", null, "Nee_ouder2", null, null, null,
                "OG1", null, null, null, null, null, null);
            case "12" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "1", null, "Ja", null, null, null,
                GEEN_GEZAG, null, null, null, null, null, null);
            case "13" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "2", null, "Nee", null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "14" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja", null, null, null,
                null, null, "Nee", "2", null, "Ja", null
                , null, null,
                GEEN_GEZAG, null, null, null, null, null, null);
            case "13o1" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "2", null, "Nee_ouder1", null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "13o2" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , null, null, null, null, null, "Nee"
                , "2", null, "Nee_ouder2", null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "18" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , "Een_ouder", null, null, null, "Nee", "Ja"
                , null, null, "Nee", null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "18o1" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , "Een_ouder", null, null, null, "Nee", "Ja"
                , null, null, "Nee_ouder1", null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "18o2" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , "Een_ouder", null, null, null, "Nee", "Ja"
                , null, null, "Nee_ouder2", null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "27" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , "Twee_ouders", "Ja", null, null, null, "Ja"
                , null, "Nee", null, null, null, null,
                TWEEOUDER_GEZAG, null, null, null, null, null, null);
            case "29" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , "Twee_ouders", "Ja", null, null, null, "Ja"
                , null, "Ja_ouder2", null, null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "33" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Ja"
                , "Twee_ouders", "Nee_nooit", "Ja", null, null, "Ja"
                , null, "Nee", null, null, null, null,
                TWEEOUDER_GEZAG, null, null, null, null, null, null);
            case "37i" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , null, null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "37" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Geen_ouders", null, null, null, null, null
                , null, null, null, null, null, null,
                GEEN_GEZAG, null, null, null, null, null, null);
            case "38" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "1_of_2_puntouders", null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "39i" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Een_ouder", null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "39e" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, null
                , "Een_ouder", null, null, null, "Nee", null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "39ee" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Een_ouder", null, null, null, "Nee", null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "39" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Een_ouder", null, null, null, "Nee", null
                , null, null, "Ja", null, null, null,
                GEEN_GEZAG, null, null, null, null, null, null);
            case "40" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, null
                , "Een_ouder", null, null, null, "Nee", null
                , null, null, "Nee", null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "40o1" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Een_ouder", null, null, null, "Nee", null
                , null, null, "Nee_ouder1", null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "40o1i" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Een_ouder", null, null, null, "Nee", null
                , null, null, "Nee_ouder1", null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "40o2" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Een_ouder", null, null, null, "Nee", null
                , null, null, "Nee_ouder2", null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "40o2i" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Een_ouder", null, null, null, "Nee", null
                , null, null, "Nee_ouder2", null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "41" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Een_ouder", null, null, null, "Ja", null
                , null, null, null, "Nee", null, null,
                GEMEENSCHAPPELIJK_GEZAG, null, null, null, null, null, null);
            case "42no1" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Een_ouder", null, null, null, "Ja", null
                , null, null, null, "Ja_nietouder1", null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "42no2" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Een_ouder", null, null, null, "Ja", null
                , null, null, null, "Ja_nietouder2", null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "43o1" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Een_ouder", null, null, null, "Ja", null
                , null, null, null, "Ja_ouder1", null, null,
                "V", null, null, null, null, null, null);
            case "43o2" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Een_ouder", null, null, null, "Ja", null
                , null, null, null, "Ja_ouder2", null, null,
                "V", null, null, null, null, null, null);
            case "44" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Een_ouder", null, null, null, "Ja", null
                , null, null, null, "Ja_beiden", null, null,
                GEEN_GEZAG, null, null, null, null, null, null);
            case "49" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Ja", null, null, null, null
                , null, "Nee", null, null, null, null,
                TWEEOUDER_GEZAG, null, null, null, null, null, null);
            case "49x" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Nee_nietig_verklaard", null, null, null, null
                , null, "Nee", null, null, null, null,
                TWEEOUDER_GEZAG, null, null, null, null, null, null);
            case "49i" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Ja", null, null, null, null
                , null, "Nee", null, null, null, null,
                TWEEOUDER_GEZAG, null, null, null, null, null, null);
            case "49a" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Ja", "Ja", "Nee"
                , "Twee_ouders", "Ja", null, null, null, null
                , null, "Nee", null, null, null, null,
                TWEEOUDER_GEZAG, null, null, null, null, null, null);
            case "45" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Nee_gescheiden", null, null, null, null
                , null, "Nee", null, null, null, null,
                TWEEOUDER_GEZAG, null, null, null, null, null, null);
            case "45ai" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Ja", "Ja", "Nee"
                , "Twee_ouders", null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "45e" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", null, null, null, null, null
                , null, null, null, null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "50" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Ja", null, null, null, null
                , null, "Ja_ouder1", null, null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "51" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Ja", null, null, null, null
                , null, "Ja_ouder2", null, null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "52" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Ja", null, null, null, null
                , null, "Ja_beiden", null, null, null, null,
                GEEN_GEZAG, null, null, null, null, null, null);
            case "53n1" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Nee_nooit", "Nee", "Na", null, null
                , null, "Ja_ouder1", null, null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "53n2" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Nee_nooit", "Nee", "Na", null, null
                , null, "Ja_ouder2", null, null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "53ai" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Ja", "Ja", "Nee"
                , "Twee_ouders", "Nee_nooit", "Nee", "Na", null, null
                , null, "Ja_ouder2", null, null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "53eee" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Nee_nooit", "Nee", "Voor", null, null
                , null, null, "Nee", null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "53eeei" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Nee_nooit", "Nee", "Voor", null, null
                , null, null, "Nee", null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "54" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Nee_nooit", "Nee", "Voor", null, null
                , null, null, "Nee", null, null, null,
                GEZAG_ONBEKEND, null, null, null, null, null, null);
            case "54o1" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Nee_nooit", "Nee", "Voor", null, null
                , null, null, "Nee_ouder1", null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "54o2" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Nee_nooit", "Nee", "Voor", null, null
                , null, null, "Nee_ouder2", null, null, null,
                EENOUDER_GEZAG, null, null, null, null, null, null);
            case "54n" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Nee_nooit", "Nee", "Na", null, null
                , null, "Nee", null, null, null, null,
                TWEEOUDER_GEZAG, null, null, null, null, null, null);
            case "55" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Nee", null, "Nee"
                , "Twee_ouders", "Nee_nooit", "Ja", null, null, null
                , null, "Nee", null, null, null, null,
                TWEEOUDER_GEZAG, null, null, null, null, null, null);
            case "55a" -> new ARAntwoordenModel("Ja", "Ja", "Nee", "Ja", "Ja", "Nee"
                , "Twee_ouders", "Nee_nooit", "Ja", null, null, null
                , null, "Nee", null, null, null, null,
                TWEEOUDER_GEZAG, null, null, null, null, null, null);
            default -> throw new IllegalStateException("Unexpected value: " + route);
        };
    }

    public static HashMap<String, Boolean> arAntwoordenEquals(
        ARAntwoordenModel arAntwoordenModel,
        ARAntwoordenModel arAntwoordenModelVerwachting
    ) {
        HashMap<String, Boolean> arAntwoordEqualsMap = new HashMap<>();
        arAntwoordEqualsMap.put("0101", arAntwoordEquals(arAntwoordenModel.getV0101(), arAntwoordenModelVerwachting.getV0101()));
        arAntwoordEqualsMap.put("0102", arAntwoordEquals(arAntwoordenModel.getV0102(), arAntwoordenModelVerwachting.getV0102()));
        arAntwoordEqualsMap.put("0103", arAntwoordEquals(arAntwoordenModel.getV0103(), arAntwoordenModelVerwachting.getV0103()));

        arAntwoordEqualsMap.put("0103A", arAntwoordEquals(arAntwoordenModel.getV0103A(), arAntwoordenModelVerwachting.getV0103A()));
        arAntwoordEqualsMap.put("0103B", arAntwoordEquals(arAntwoordenModel.getV0103B(), arAntwoordenModelVerwachting.getV0103B()));

        arAntwoordEqualsMap.put("0104", arAntwoordEquals(arAntwoordenModel.getV0104(), arAntwoordenModelVerwachting.getV0104()));
        arAntwoordEqualsMap.put("0201", arAntwoordEquals(arAntwoordenModel.getV0201(), arAntwoordenModelVerwachting.getV0201()));
        arAntwoordEqualsMap.put("02A01", arAntwoordEquals(arAntwoordenModel.getV02A01(),
            arAntwoordenModelVerwachting.getV02A01()));
        arAntwoordEqualsMap.put("02A02", arAntwoordEquals(arAntwoordenModel.getV02A02(),
            arAntwoordenModelVerwachting.getV02A02()));
        arAntwoordEqualsMap.put("02A03", arAntwoordEquals(arAntwoordenModel.getV02A03(),
            arAntwoordenModelVerwachting.getV02A03()));
        arAntwoordEqualsMap.put("02B01", arAntwoordEquals(arAntwoordenModel.getV02B01(),
            arAntwoordenModelVerwachting.getV02B01()));

        arAntwoordEqualsMap.put("0301", arAntwoordEquals(arAntwoordenModel.getV0301(), arAntwoordenModelVerwachting.getV0301()));
        arAntwoordEqualsMap.put("0302", arAntwoordEquals(arAntwoordenModel.getV0302(), arAntwoordenModelVerwachting.getV0302()));

        arAntwoordEqualsMap.put("04A02", arAntwoordEquals(arAntwoordenModel.getV04A02(),
            arAntwoordenModelVerwachting.getV04A02()));
        arAntwoordEqualsMap.put("04A03", arAntwoordEquals(arAntwoordenModel.getV04A03(),
            arAntwoordenModelVerwachting.getV04A03()));
        arAntwoordEqualsMap.put("04B01", arAntwoordEquals(arAntwoordenModel.getV04B01(),
            arAntwoordenModelVerwachting.getV04B01()));

        return arAntwoordEqualsMap;
    }

    private static boolean arAntwoordEquals(Object antwoordActual, Object antwoordExpected) {
        boolean resultaat;
        if (antwoordActual == null && antwoordExpected == null) {
            resultaat = true;
        } else if (antwoordActual == null) {
            resultaat = false;
        } else {
            resultaat = antwoordActual.equals(antwoordExpected);
        }
        return resultaat;
    }

    public static ArAntwoordenAssertion assertThatArAntwoorden(ARAntwoordenModel arAntwoordenModel) {
        return new ArAntwoordenAssertion(arAntwoordenModel);
    }

    public record ArAntwoordenAssertion(ARAntwoordenModel arAntwoordenModel) {

        public void areEqualTo(ARAntwoordenModel arAntwoordenModel) {
            assertThat(arAntwoordenModel.getV0101()).as("Vraag 0101").isEqualTo(arAntwoordenModel.getV0101());
            assertThat(arAntwoordenModel.getV0102()).as("Vraag 0102").isEqualTo(arAntwoordenModel.getV0102());
            assertThat(arAntwoordenModel.getV0103()).as("Vraag 0103").isEqualTo(arAntwoordenModel.getV0103());
            assertThat(arAntwoordenModel.getV0103A()).as("Vraag 0103A").isEqualTo(arAntwoordenModel.getV0103A());
            assertThat(arAntwoordenModel.getV0103B()).as("Vraag 0103B").isEqualTo(arAntwoordenModel.getV0103B());
            assertThat(arAntwoordenModel.getV0104()).as("Vraag 0104").isEqualTo(arAntwoordenModel.getV0104());
            assertThat(arAntwoordenModel.getV0201()).as("Vraag 0201").isEqualTo(arAntwoordenModel.getV0201());
            assertThat(arAntwoordenModel.getV02A01()).as("Vraag 02A01").isEqualTo(arAntwoordenModel.getV02A01());
            assertThat(arAntwoordenModel.getV02A02()).as("Vraag 02A02").isEqualTo(arAntwoordenModel.getV02A02());
            assertThat(arAntwoordenModel.getV02A03()).as("Vraag 02A03").isEqualTo(arAntwoordenModel.getV02A03());
            assertThat(arAntwoordenModel.getV02B01()).as("Vraag 02B01").isEqualTo(arAntwoordenModel.getV02B01());
            assertThat(arAntwoordenModel.getV0301()).as("Vraag 0301").isEqualTo(arAntwoordenModel.getV0301());
            assertThat(arAntwoordenModel.getV0302()).as("Vraag 0302").isEqualTo(arAntwoordenModel.getV0302());
            assertThat(arAntwoordenModel.getV04A02()).as("Vraag 04A02").isEqualTo(arAntwoordenModel.getV04A02());
            assertThat(arAntwoordenModel.getV04A03()).as("Vraag 04A03").isEqualTo(arAntwoordenModel.getV04A03());
            assertThat(arAntwoordenModel.getV04B01()).as("Vraag 04B01").isEqualTo(arAntwoordenModel.getV04B01());
        }
    }
}
