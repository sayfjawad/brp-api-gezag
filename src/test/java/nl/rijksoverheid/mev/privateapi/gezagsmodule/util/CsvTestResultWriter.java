package nl.rijksoverheid.mev.privateapi.gezagsmodule.util;

import com.opencsv.CSVWriter;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.model.GezagAfleidingsResultaat;
import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import nl.rijksoverheid.mev.privateapi.gezagsmodule.GezagsmoduleTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvTestResultWriter {

    private static CSVWriter csvWriter;

    private final GezagsmoduleTest.RouteTestParameters routeTestArguments;
    private final GezagsmoduleTest.ResponseInfo responseInfo;
    private final ARAntwoordenModel arAntwoordenExpected;
    private final boolean bsnsGezagHoudersEqual;
    private final HashMap<String, Boolean> arAntwoordenEqual;


    public static void setup(String fileName) throws IOException {
        csvWriter = new CSVWriter(new FileWriter("target/" + fileName));
        csvWriter.writeNext(getCsvHeaderRow());
    }

    public static void tearDown() throws IOException {
        csvWriter.close();
    }

    public static void writeTestResultsAsCsvRow(
            GezagsmoduleTest.RouteTestParameters routeTestArguments,
            GezagsmoduleTest.ResponseInfo responseInfo,
            ARAntwoordenModel arAntwoordenExpected
    ) {
        var testResultWriter = new CsvTestResultWriter(routeTestArguments, responseInfo, arAntwoordenExpected);
        testResultWriter.writeTestResultsAsCsvRow();
    }

    private CsvTestResultWriter(
            GezagsmoduleTest.RouteTestParameters routeTestArguments,
            GezagsmoduleTest.ResponseInfo responseInfo,
            ARAntwoordenModel arAntwoordenExpected
    ) {
        this.routeTestArguments = routeTestArguments;
        this.responseInfo = responseInfo;
        this.arAntwoordenExpected = arAntwoordenExpected;
        var gezagAfleidingsResultaat = responseInfo.gezagAfleidingsResultaat();
        this.bsnsGezagHoudersEqual = gezagHoudersKloppen(routeTestArguments.bsnsGezaghoudersExpected(),
                gezagAfleidingsResultaat);
        this.arAntwoordenEqual = ArAntwoordenModelUtils.arAntwoordenEquals(gezagAfleidingsResultaat.getArAntwoordenModel(),
                arAntwoordenExpected);
    }

    private static boolean gezagHoudersKloppen(
            List<String> bsnsGezaghoudersExpected,
            GezagAfleidingsResultaat gezagAfleidingsResultaat
    ) {
        if (gezagAfleidingsResultaat.getGezagsrelaties() != null) {
            List<String> bsnsGezaghouders = getBsnsGezaghouders(gezagAfleidingsResultaat);
            if (bsnsGezaghoudersExpected.size()==0) {
                if (bsnsGezaghouders.size() == 0) {
                    return true;
                }
            }
            else {
                return bsnsGezaghoudersExpected.size() == bsnsGezaghouders.size()
                        && bsnsGezaghoudersExpected.containsAll(bsnsGezaghouders)
                        && bsnsGezaghouders.containsAll(bsnsGezaghoudersExpected);
                }
        }
        return true;
    }

    private static List<String> getBsnsGezaghouders(GezagAfleidingsResultaat gezagAfleidingsResultaat) {
        if (gezagAfleidingsResultaat.getGezagsrelaties() != null) {
            return gezagAfleidingsResultaat.getGezagsrelaties().stream()
                    .map(Gezagsrelatie::bsnMeerderjarige)
                    .collect(Collectors.toList());
        }
        return null;
    }

    private void writeTestResultsAsCsvRow() {
        var gezagAfleidingsResultaat = responseInfo.gezagAfleidingsResultaat();
        String[] data = getCsvDataRow(
                routeTestArguments.testCase(),
                routeTestArguments.bsnKind(),
                routeTestArguments.bsnsGezaghoudersExpected(),
                getBsnsGezaghouders(gezagAfleidingsResultaat),
                bsnsGezagHoudersEqual,
                routeTestArguments.expectedRoute(),
                gezagAfleidingsResultaat.getRoute(),
                arAntwoordenExpected,
                gezagAfleidingsResultaat.getArAntwoordenModel(),
                arAntwoordenEqual,
                responseInfo.responseTime(),
                gezagAfleidingsResultaat.getArAntwoordenModel().getSoortGezag()
        );
        csvWriter.writeNext(data);
    }

    public static String[] getCsvHeaderRow() {
        String[] header = {
            "Test Case", "Bsn Kind", "Bsn Verwachte gezaghouders", "Terug gekregen bsn", "Resultaat",
            "route", "Verwachting route", "Gelijk route",
            "soortGezag", "Verwachting soortGezag", "Gelijk soortGezag",
            "0101", "Verwachting 0101", "Gelijk 0101",
            "0102", "Verwachting 0102", "Gelijk 0102",
            "0103", "Verwachting 0103", "Gelijk 0103",
            "0103A", "Verwachting 0103A", "Gelijk 0103A",
            "0103B", "Verwachting 0103B", "Gelijk 0103B",
            "0104", "Verwachting 0104", "Gelijk 0104",
            "0201", "Verwachting 0201", "Gelijk 0201",
            "02A01", "Verwachting 02A01", "Gelijk 02A01",
            "02A02", "Verwachting 02A02", "Gelijk 02A02",
            "02A03", "Verwachting 02A03", "Gelijk 02A03",
            "02B01", "Verwachting 02B01", "Gelijk 02B01",
            "0301", "Verwachting 0301", "Gelijk 0301",
            "0302", "Verwachting 0302", "Gelijk 0302",
            "04A02", "Verwachting 04A02", "Gelijk 04A02",
            "04A03", "Verwachting 04A03", "Gelijk 04A03",
            "04B01", "Verwachting 04B01", "Gelijk 04B01",
             "Lengte in ms", "Exception", "Uitleg"
        };
        return header;
    }

    public static String[] getCsvDataRow(
            String testCase,
            String bsnKind,
            List<String> bsnsGezaghoudersExpected,
            List<String> bsnsGezaghoudersActual,
            boolean bsnsGezagHoudersEqual,
            String expectedRoute,
            String actualRoute,
            ARAntwoordenModel arAntwoordenExpected,
            ARAntwoordenModel arAntwoordenActual,
            HashMap<String, Boolean> arAntwoordenEqual,
            long responseDuration,
            String soortGezagActual
    ) {
        String[] data = {
            testCase, bsnKind,
            String.valueOf(bsnsGezaghoudersExpected),
            String.valueOf(bsnsGezaghoudersActual),
            String.valueOf(bsnsGezagHoudersEqual),
            String.valueOf(actualRoute),
            String.valueOf(expectedRoute),
            String.valueOf(Objects.equals(actualRoute, expectedRoute)),
            String.valueOf(soortGezagActual),
            String.valueOf(arAntwoordenExpected.getSoortGezag()),
            String.valueOf((soortGezagActual != null ? soortGezagActual.equals(arAntwoordenExpected.getSoortGezag()) : "null")),
            String.valueOf(arAntwoordenActual.getV0101() != null ? arAntwoordenActual.getV0101() : ""),
            String.valueOf(arAntwoordenExpected.getV0101() != null ? arAntwoordenExpected.getV0101() : ""),
            String.valueOf(!(arAntwoordenActual.getV0101() == null && arAntwoordenExpected.getV0101() == null)
            ? arAntwoordenEqual.get("0101") : ""),
            String.valueOf(arAntwoordenActual.getV0102() != null ? arAntwoordenActual.getV0102() : ""),
            String.valueOf(arAntwoordenExpected.getV0102() != null ? arAntwoordenExpected.getV0102() : ""),
            String.valueOf(!(arAntwoordenActual.getV0102() == null && arAntwoordenExpected.getV0102() == null)
            ? arAntwoordenEqual.get("0102") : ""),
            String.valueOf(arAntwoordenActual.getV0103() != null ? arAntwoordenActual.getV0103() : ""),
            String.valueOf(arAntwoordenExpected.getV0103() != null ? arAntwoordenExpected.getV0103() : ""),
            String.valueOf(!(arAntwoordenActual.getV0103() == null && arAntwoordenExpected.getV0103() == null)
            ? arAntwoordenEqual.get("0103") : ""),
            String.valueOf(arAntwoordenActual.getV0103A() != null ? arAntwoordenActual.getV0103A() : ""),
            String.valueOf(arAntwoordenExpected.getV0103A() != null ? arAntwoordenExpected.getV0103A() : ""),
            String.valueOf(!(arAntwoordenActual.getV0103A() == null && arAntwoordenExpected.getV0103A() == null)
            ? arAntwoordenEqual.get("0103A") : ""),
            String.valueOf(arAntwoordenActual.getV0103B() != null ? arAntwoordenActual.getV0103B() : ""),
            String.valueOf(arAntwoordenExpected.getV0103B() != null ? arAntwoordenExpected.getV0103B() : ""),
            String.valueOf(!(arAntwoordenActual.getV0103B() == null && arAntwoordenExpected.getV0103B() == null)
            ? arAntwoordenEqual.get("0103B") : ""),
            String.valueOf(arAntwoordenActual.getV0104() != null ? arAntwoordenActual.getV0104() : ""),
            String.valueOf(arAntwoordenExpected.getV0104() != null ? arAntwoordenExpected.getV0104() : ""),
            String.valueOf(!(arAntwoordenActual.getV0104() == null && arAntwoordenExpected.getV0104() == null)
            ? arAntwoordenEqual.get("0104") : ""),
            String.valueOf(arAntwoordenActual.getV0201() != null ? arAntwoordenActual.getV0201() : ""),
            String.valueOf(arAntwoordenExpected.getV0201() != null ? arAntwoordenExpected.getV0201() : ""),
            String.valueOf(!(arAntwoordenActual.getV0201() == null && arAntwoordenExpected.getV0201() == null)
            ? arAntwoordenEqual.get("0201") : ""),
            String.valueOf(arAntwoordenActual.getV02A01() != null ? arAntwoordenActual.getV02A01() : ""),
            String.valueOf(arAntwoordenExpected.getV02A01() != null ? arAntwoordenExpected.getV02A01()
            : ""),
            String.valueOf(!(arAntwoordenActual.getV02A01() == null && arAntwoordenExpected.getV02A01() == null)
            ? arAntwoordenEqual.get("02A01") : ""),
            String.valueOf(arAntwoordenActual.getV02A02() != null ? arAntwoordenActual.getV02A02() : ""),
            String.valueOf(arAntwoordenExpected.getV02A02() != null ? arAntwoordenExpected.getV02A02()
            : ""),
            String.valueOf(!(arAntwoordenActual.getV02A02() == null && arAntwoordenExpected.getV02A02() == null)
            ? arAntwoordenEqual.get("02A02") : ""),
            String.valueOf(arAntwoordenActual.getV02A03() != null ? arAntwoordenActual.getV02A03() : ""),
            String.valueOf(arAntwoordenExpected.getV02A03() != null ? arAntwoordenExpected.getV02A03()
            : ""),
            String.valueOf(!(arAntwoordenActual.getV02A03() == null && arAntwoordenExpected.getV02A03() == null)
            ? arAntwoordenEqual.get("02A03") : ""),
            String.valueOf(arAntwoordenActual.getV02B01() != null ? arAntwoordenActual.getV02B01() : ""),
            String.valueOf(arAntwoordenExpected.getV02B01() != null ? arAntwoordenExpected.getV02B01()
            : ""),
            String.valueOf(!(arAntwoordenActual.getV02B01() == null && arAntwoordenExpected.getV02B01() == null)
            ? arAntwoordenEqual.get("02B01") : ""),
            String.valueOf(arAntwoordenActual.getV0301() != null ? arAntwoordenActual.getV0301() : ""),
            String.valueOf(arAntwoordenExpected.getV0301() != null ? arAntwoordenExpected.getV0301() : ""),
            String.valueOf(!(arAntwoordenActual.getV0301() == null && arAntwoordenExpected.getV0301() == null)
            ? arAntwoordenEqual.get("0301") : ""),
            String.valueOf(arAntwoordenActual.getV0302() != null ? arAntwoordenActual.getV0302() : ""),
            String.valueOf(arAntwoordenExpected.getV0302() != null ? arAntwoordenExpected.getV0302() : ""),
            String.valueOf(!(arAntwoordenActual.getV0302() == null && arAntwoordenExpected.getV0302() == null)
            ? arAntwoordenEqual.get("0302") : ""),
            String.valueOf(arAntwoordenActual.getV04A02() != null ? arAntwoordenActual.getV04A02() : ""),
            String.valueOf(arAntwoordenExpected.getV04A02() != null ? arAntwoordenExpected.getV04A02()
            : ""),
            String.valueOf(!(arAntwoordenActual.getV04A02() == null && arAntwoordenExpected.getV04A02() == null)
            ? arAntwoordenEqual.get("04A02") : ""),
            String.valueOf(arAntwoordenActual.getV04A03() != null ? arAntwoordenActual.getV04A03() : ""),
            String.valueOf(arAntwoordenExpected.getV04A03() != null ? arAntwoordenExpected.getV04A03()
            : ""),
            String.valueOf(!(arAntwoordenActual.getV04A03() == null && arAntwoordenExpected.getV04A03() == null)
            ? arAntwoordenEqual.get("04A03") : ""),
            String.valueOf(arAntwoordenActual.getV04B01() != null ? arAntwoordenActual.getV04B01() : ""),
            String.valueOf(arAntwoordenExpected.getV04B01() != null ? arAntwoordenExpected.getV04B01()
            : ""),
            String.valueOf(!(arAntwoordenActual.getV04B01() == null && arAntwoordenExpected.getV04B01() == null)
            ? arAntwoordenEqual.get("04B01") : ""),
            String.valueOf(responseDuration),
            String.valueOf((arAntwoordenActual.getException() != null) ? arAntwoordenActual.getException().getMessage() : ""),
            String.valueOf((arAntwoordenActual.getUitleg() != null) ? arAntwoordenActual.getUitleg() : "")
        };
        return data;
    }
}
