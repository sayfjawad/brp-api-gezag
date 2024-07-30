package nl.rijksoverheid.mev.privateapi.gezagsmodule;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.model.GezagAfleidingsResultaat;
import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagService;
import nl.rijksoverheid.mev.privateapi.gezagsmodule.util.ArAntwoordenModelUtils;
import nl.rijksoverheid.mev.privateapi.gezagsmodule.util.CsvTestResultWriter;
import nl.rijksoverheid.mev.transaction.Transaction;
import org.junit.jupiter.api.AfterAll;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Gebruikte afkortingen: AR = Afleidingsregel
 *
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, properties="app.clock=2023-02-01T00:00:00Z")
public abstract class GezagsmoduleTest {

    public record ResponseInfo(GezagAfleidingsResultaat gezagAfleidingsResultaat, long responseTime) {

    }

    public record RouteTestParameters(
            String testCase,
            String bsnKind,
            List<String> bsnsGezaghoudersExpected,
            String expectedRoute) {

    }

    @Mock
    private Transaction transaction;

    @Autowired
    private GezagService subject;

    @AfterAll
    public static void teardown() throws IOException {
        CsvTestResultWriter.tearDown();
    }

    protected void executeRouteTest(RouteTestParameters routeTestArguments) throws AfleidingsregelException {
        var responseInfo = queryGezagsmoduleInternalsAPI(routeTestArguments.bsnKind);
        var arAntwoordenExpected = ArAntwoordenModelUtils.createARAntwoordenModel(routeTestArguments.expectedRoute);

        CsvTestResultWriter.writeTestResultsAsCsvRow(routeTestArguments, responseInfo, arAntwoordenExpected);

        var gezagAfleidingsResultaat = responseInfo.gezagAfleidingsResultaat();
        assertThat(gezagAfleidingsResultaat.getRoute()).isEqualTo(routeTestArguments.expectedRoute);
        if (!gezagAfleidingsResultaat.getRoute().equals("2m") && (!gezagAfleidingsResultaat.getRoute().equals("2o"))){
            assertThat(getBsnsGezaghouders(gezagAfleidingsResultaat)).containsExactlyInAnyOrderElementsOf(
                        routeTestArguments.bsnsGezaghoudersExpected);
            assertThat(gezagAfleidingsResultaat.getGezagsrelaties().get(0).getSoortGezag()).isEqualTo(arAntwoordenExpected.getSoortGezag());
        }
        ArAntwoordenModelUtils.assertThatArAntwoorden(gezagAfleidingsResultaat.getArAntwoordenModel()).areEqualTo(arAntwoordenExpected);
    }

    private ResponseInfo queryGezagsmoduleInternalsAPI(String bsn) throws AfleidingsregelException {
        long startTime = System.nanoTime();
        GezagAfleidingsResultaat gezagAfleidingsResultaat = subject.getGezagAfleidingsResultaat(bsn, transaction);
        long durationInMs = (System.nanoTime() - startTime) / 1000000;
        return new ResponseInfo(gezagAfleidingsResultaat, durationInMs);
    }

    private static List<String> getBsnsGezaghouders(GezagAfleidingsResultaat gezagAfleidingsResultaat) {
        return gezagAfleidingsResultaat.getGezagsrelaties().stream()
                .map(Gezagsrelatie::getBsnMeerderjarige)
                .toList();
    }
}
