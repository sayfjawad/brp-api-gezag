package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.GezagApplication;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openapitools.OpenApiGeneratorApplication;
import org.openapitools.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest(
    classes = {GezagApplication.class, OpenApiGeneratorApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class OpvragenBevoegdheidTotGezagMeerderjarigeAcceptanceTest {

    private static final String OIN = "00000004003214345001";

    @Autowired
    WebTestClient webTestClient;

    static Stream<Arguments> opvragenBevoegdheidTotGezag() {
        return Stream.of(
            // (input, expected)
            Arguments.of(
                "999998778", // meerderjarige
                new GezagResponse()
                    .personen(List.of(
                        new Persoon()
                            .burgerservicenummer("999998778")
                            .gezag(List.of(
                                new TweehoofdigOuderlijkGezag()
                                    .type("TweehoofdigOuderlijkGezag")
                                    .minderjarige(new Minderjarige().burgerservicenummer("999998316"))
                                    .ouders(List.of(
                                        new GezagOuder().burgerservicenummer("999998778"),
                                        new GezagOuder().burgerservicenummer("999998791")
                                    )),
                                new EenhoofdigOuderlijkGezag()
                                    .type("EenhoofdigOuderlijkGezag")
                                    .minderjarige(new Minderjarige().burgerservicenummer("999999746"))
                                    .ouder(new GezagOuder().burgerservicenummer("999998778")),
                                new TweehoofdigOuderlijkGezag()
                                    .type("TweehoofdigOuderlijkGezag")
                                    .minderjarige(new Minderjarige().burgerservicenummer("999998341"))
                                    .ouders(List.of(
                                        new GezagOuder().burgerservicenummer("999998778"),
                                        new GezagOuder().burgerservicenummer("999998791")
                                    )),
                                new TweehoofdigOuderlijkGezag()
                                    .type("TweehoofdigOuderlijkGezag")
                                    .minderjarige(new Minderjarige().burgerservicenummer("999998328"))
                                    .ouders(List.of(
                                        new GezagOuder().burgerservicenummer("999998778"),
                                        new GezagOuder().burgerservicenummer("999998791")
                                    ))
                            ))
                    ))
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void opvragenBevoegdheidTotGezag(final String input, final GezagResponse expected) {
        GezagRequest gezagRequest = new GezagRequest().burgerservicenummer(List.of(input));

        webTestClient.post().uri("/api/v1/opvragenBevoegdheidTotGezag")
            .contentType(MediaType.APPLICATION_JSON)
            .header("OIN", OIN)
            .bodyValue(gezagRequest)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(GezagResponse.class).isEqualTo(expected);
    }
}