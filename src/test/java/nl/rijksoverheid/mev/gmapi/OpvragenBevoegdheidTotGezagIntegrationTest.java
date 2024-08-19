package nl.rijksoverheid.mev.gmapi;

import java.util.List;
import nl.rijksoverheid.mev.GezagApplication;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openapitools.OpenApiGeneratorApplication;
import org.openapitools.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    classes = {GezagApplication.class, OpenApiGeneratorApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class OpvragenBevoegdheidTotGezagIntegrationTest {

    private static final String OIN = "00000004003214345001";

    @Autowired
    WebTestClient webTestClient;

    @ParameterizedTest
    @ValueSource(strings = {
        "999998778",
        "999998316",
    })
    void opvragenBevoegdheidTotGezag(final String input) {
        GezagRequest gezagRequest = new GezagRequest().burgerservicenummer(List.of(input));

        webTestClient.post().uri("/api/v1/opvragenBevoegdheidTotGezag")
            .contentType(MediaType.APPLICATION_JSON)
            .header("OIN", OIN)
            .bodyValue(gezagRequest)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(GezagResponse.class).consumeWith(response -> {
                GezagResponse result = response.getResponseBody();
                assertThat(result.getPersonen()).isNotNull();
            });
    }
}
