package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.GezagApplication;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openapitools.OpenApiGeneratorApplication;
import org.openapitools.model.GezagRequest;
import org.openapitools.model.Gezagsrelatie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

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
                Set.of(
                    new Gezagsrelatie()
                        .bsnMinderjarige("999998316")
                        .soortGezag(Gezagsrelatie.SoortGezagEnum.OG2)
                        .bsnMeerderjarige("999998778"),
                    new Gezagsrelatie()
                        .bsnMinderjarige("999999746")
                        .soortGezag(Gezagsrelatie.SoortGezagEnum.OG1)
                        .bsnMeerderjarige("999998778"),
                    new Gezagsrelatie()
                        .bsnMinderjarige("999998341")
                        .soortGezag(Gezagsrelatie.SoortGezagEnum.OG2)
                        .bsnMeerderjarige("999998778"),
                    new Gezagsrelatie()
                        .bsnMinderjarige("999998328")
                        .soortGezag(Gezagsrelatie.SoortGezagEnum.OG2)
                        .bsnMeerderjarige("999998778")
                )
            )
        );
    }

    @ParameterizedTest
    @MethodSource
    void opvragenBevoegdheidTotGezag(String input, Set<Gezagsrelatie> expected) {
        var request = new GezagRequest().bsn(input);

        webTestClient.post().uri("/api/v1/opvragenBevoegdheidTotGezagMeerderjarige")
            .contentType(MediaType.APPLICATION_JSON)
            .header("OIN", OIN)
            .bodyValue(request)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBodyList(Gezagsrelatie.class).consumeWith(response -> {
                var results = response.getResponseBody();

                System.out.println(input);
                System.out.println(results);

                assertThat(results).containsExactlyInAnyOrderElementsOf(expected);
            });
    }
}
