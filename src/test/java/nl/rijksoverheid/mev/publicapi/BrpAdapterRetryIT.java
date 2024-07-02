package nl.rijksoverheid.mev.publicapi;

import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.stubbing.Scenario;
import nl.rijksoverheid.mev.brpadapter.service.BrpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.transaction.Transaction;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.mockito.Mock;

@SpringBootTest
@ActiveProfiles("brp-it")
@WireMockTest(httpPort = 8000)
class BrpAdapterRetryIT {

    private static final String BSN1 = "111111110";
    private static final String BSN2 = "222222220"; // Use different BSNs because of BRP Adapter caching
    private static final String BSN3 = "333333330";
    private static final String BSN4 = "444444440";
    private static final long AVG_RESPONSE_TIME_MS_BRP = 300;
    public static final String RETRY_SCENARIO = "Retry scenario";
    public static final String SECOND_TRY = "Second try";
    public static final String THIRD_TRY = "Third try";
    public static final int TWICE = 2;
    public static final int THRICE = 3;

    @Autowired
    private BrpService brpService;

    @Mock
    private Transaction transaction;
    
    @Test
    void failingRequestIsRetried() {
        // No matching stub causes Wiremock to return an HTTP 404 with text/plain matching info
        try {
            brpService.getPersoonslijst(BSN1, transaction);
        } catch (Exception e) {
            // ignore the exception so the test can reach verify statements
        }
        verifyRequestRetried(THRICE);
    }

    @Test
    void parsesResponseFromSuccessfulRetry() throws Exception {
        stubFor(post("/")
                .withHeader("Content-Type", containing("xml"))
                .withRequestBody(containing(BSN2))
                .inScenario(RETRY_SCENARIO)
                .whenScenarioStateIs(Scenario.STARTED)
                .willReturn(serviceUnavailable()
                        .withHeader("Content-Type", "text/html;charset=utf-8")
                        .withBody("<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\"><html><head><title>503 Service Unavailable</title></head><body><h1>Service Unavailable</h1><p>The server is temporarily unable to service your request due to maintenance downtime or capacity problems. Please try again later.</p></body></html>"))
                .willSetStateTo(SECOND_TRY));

        stubFor(post("/")
                .withHeader("Content-Type", containing("xml"))
                .withRequestBody(containing(BSN2))
                .inScenario(RETRY_SCENARIO)
                .whenScenarioStateIs(SECOND_TRY)
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml;charset=UTF-8")
                        .withBody(okResponseBody(BSN2))
                )
        );

        var persoonslijst = brpService.getPersoonslijst(BSN2, transaction);

        verifyBsnOccursIn(persoonslijst, BSN2);
        verifyRequestRetried(TWICE);
    }

    String okResponseBody(String bsn) {
        return "<?xml version='1.0' encoding='UTF-8'?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><vraagResponse xmlns:ns2=\"http://www.bprbzk.nl/GBA/LO3/version1.1\" xmlns=\"http://www.bprbzk.nl/GBA/LRDPlus/version1.1\"><vraagReturn><persoonslijsten><ns2:item><ns2:categoriestapels><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>1</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>120</ns2:nummer><ns2:waarde>"
                + bsn + "</ns2:waarde></ns2:item><ns2:item><ns2:nummer>210</ns2:nummer><ns2:waarde>Govert</ns2:waarde></ns2:item><ns2:item><ns2:nummer>240</ns2:nummer><ns2:waarde>Geus</ns2:waarde></ns2:item><ns2:item><ns2:nummer>310</ns2:nummer><ns2:waarde>19841002</ns2:waarde></ns2:item><ns2:item><ns2:nummer>330</ns2:nummer><ns2:waarde>6030</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8230</ns2:nummer><ns2:waarde>PKA</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8310</ns2:nummer><ns2:waarde>010300</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8320</ns2:nummer><ns2:waarde>20220825</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>2</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>210</ns2:nummer><ns2:waarde>Geurtje</ns2:waarde></ns2:item><ns2:item><ns2:nummer>240</ns2:nummer><ns2:waarde>Plas</ns2:waarde></ns2:item><ns2:item><ns2:nummer>6210</ns2:nummer><ns2:waarde>00000000</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8310</ns2:nummer><ns2:waarde>020000</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8320</ns2:nummer><ns2:waarde>20220825</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>3</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>210</ns2:nummer><ns2:waarde>Gerrit</ns2:waarde></ns2:item><ns2:item><ns2:nummer>240</ns2:nummer><ns2:waarde>Geus</ns2:waarde></ns2:item><ns2:item><ns2:nummer>6210</ns2:nummer><ns2:waarde>00000000</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>5</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>120</ns2:nummer><ns2:waarde>999970148</ns2:waarde></ns2:item><ns2:item><ns2:nummer>610</ns2:nummer><ns2:waarde>20051203</ns2:waarde></ns2:item><ns2:item><ns2:nummer>620</ns2:nummer><ns2:waarde>0518</ns2:waarde></ns2:item><ns2:item><ns2:nummer>630</ns2:nummer><ns2:waarde>6030</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8310</ns2:nummer><ns2:waarde>050610</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8320</ns2:nummer><ns2:waarde>20220825</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>8</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>910</ns2:nummer><ns2:waarde>0518</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8310</ns2:nummer><ns2:waarde>089999</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8320</ns2:nummer><ns2:waarde>20220825</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>9</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>120</ns2:nummer><ns2:waarde>999970422</ns2:waarde></ns2:item><ns2:item><ns2:nummer>210</ns2:nummer><ns2:waarde>Gloria</ns2:waarde></ns2:item><ns2:item><ns2:nummer>240</ns2:nummer><ns2:waarde>Geus</ns2:waarde></ns2:item><ns2:item><ns2:nummer>310</ns2:nummer><ns2:waarde>20211012</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8310</ns2:nummer><ns2:waarde>090000</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8320</ns2:nummer><ns2:waarde>20220825</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>9</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>120</ns2:nummer><ns2:waarde>999970331</ns2:waarde></ns2:item><ns2:item><ns2:nummer>210</ns2:nummer><ns2:waarde>Gijs</ns2:waarde></ns2:item><ns2:item><ns2:nummer>240</ns2:nummer><ns2:waarde>Geus</ns2:waarde></ns2:item><ns2:item><ns2:nummer>310</ns2:nummer><ns2:waarde>20191205</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8310</ns2:nummer><ns2:waarde>090300</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8320</ns2:nummer><ns2:waarde>20220825</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>9</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>120</ns2:nummer><ns2:waarde>999970288</ns2:waarde></ns2:item><ns2:item><ns2:nummer>210</ns2:nummer><ns2:waarde>Gerdien</ns2:waarde></ns2:item><ns2:item><ns2:nummer>240</ns2:nummer><ns2:waarde>Geus</ns2:waarde></ns2:item><ns2:item><ns2:nummer>310</ns2:nummer><ns2:waarde>20160607</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item></ns2:categoriestapels></ns2:item></persoonslijsten><resultaat><code>0</code><letter>A</letter><omschrijving>Aantal: 1.</omschrijving><referentie>ef97a8cf-c38d-4e06-b6ce-70f26078bd9e</referentie></resultaat></vraagReturn></vraagResponse></soap:Body></soap:Envelope>";
    }

    private static void verifyBsnOccursIn(Persoonslijst persoonslijst, String bsn) {
        assertThat(persoonslijst.getPersoon().getBsn()).isEqualTo(bsn);
    }

    private static void verifyRequestRetried(int numberOfRequests) {
        verify(numberOfRequests, postRequestedFor(urlEqualTo("/")));
    }

    @Test
    void retryDelayIsNoShorterThanTwiceTheAverageBrpResponseTime() {
        long startTimeMillis = System.currentTimeMillis();
        try {
            brpService.getPersoonslijst(BSN4, transaction);
        } catch (Exception e) {

        }
        long endTimeMillis = System.currentTimeMillis();

        verifyRequestRetried(THRICE);

        long durationMillis = endTimeMillis - startTimeMillis;
        var retryDelay = AVG_RESPONSE_TIME_MS_BRP * 2;
        assertThat(durationMillis).isGreaterThanOrEqualTo(retryDelay);
    }

    @Test
    void retryDelayIsNoShorterThanTwiceTheAverageBrpResponseTimeForLowLevelFault() throws Exception {
        stubFor(post("/")
                .withHeader("Content-Type", containing("xml"))
                .withRequestBody(containing(BSN3))
                .inScenario(RETRY_SCENARIO)
                .whenScenarioStateIs(Scenario.STARTED)
                .willReturn(aResponse().withFault(Fault.EMPTY_RESPONSE))
                .willSetStateTo(SECOND_TRY));

        stubFor(post("/")
                .withHeader("Content-Type", containing("xml"))
                .withRequestBody(containing(BSN3))
                .inScenario(RETRY_SCENARIO)
                .whenScenarioStateIs(SECOND_TRY)
                .willReturn(aResponse().withFault(Fault.EMPTY_RESPONSE))
                .willSetStateTo(THIRD_TRY));

        stubFor(post("/")
                .withHeader("Content-Type", containing("xml"))
                .withRequestBody(containing(BSN3))
                .inScenario(RETRY_SCENARIO)
                .whenScenarioStateIs(THIRD_TRY)
                .willReturn(ok()
                        .withHeader("Content-Type", "text/xml;charset=UTF-8")
                        .withBody(okResponseBody(BSN3))
                )
        );

        long startTimeMillis = System.currentTimeMillis();
        var persoonslijst = brpService.getPersoonslijst(BSN3, transaction);
        long endTimeMillis = System.currentTimeMillis();

        /* One quick retry performed by the SOAP library, because of low-level fault,
        another performed by our code, using our retry delay. */
        verifyRequestRetried(THRICE);
        verifyBsnOccursIn(persoonslijst, BSN3);

        long durationMillis = endTimeMillis - startTimeMillis;
        var retryDelay = AVG_RESPONSE_TIME_MS_BRP * 2;
        assertThat(durationMillis).isGreaterThanOrEqualTo(retryDelay);
    }
}
