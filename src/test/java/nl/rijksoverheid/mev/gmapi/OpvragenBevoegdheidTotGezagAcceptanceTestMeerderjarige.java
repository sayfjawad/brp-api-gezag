package nl.rijksoverheid.mev.gmapi;

import java.util.ArrayList;
import java.util.List;
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
import java.util.Set;
import java.util.stream.Stream;
import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(
        classes = {GezagApplication.class, OpenApiGeneratorApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class OpvragenBevoegdheidTotGezagAcceptanceTestMeerderjarige {

    private static final String OIN = "00000004003214345001";

    @Autowired
    WebTestClient webTestClient;

    static Stream<Arguments> opvragenBevoegdheidTotGezag() {
        return Stream.of(
                // (input, expected)
                Arguments.of(
                        "999998778", // meerderjarige
                        Set.of(
                                new Gezagsrelatie("999998778", "999998316", "OG2", "999998778", null),
                                new Gezagsrelatie("999998778", "999999746", "OG1", "999998778", null),
                                new Gezagsrelatie("999998778", "999998341", "OG2", "999998778", null),
                                new Gezagsrelatie("999998778", "999998328", "OG2", "999998778", null)
                        )
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void opvragenBevoegdheidTotGezag(final String input, final Set<Gezagsrelatie> expected) {
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
            List<Persoon> personen = new ArrayList<>();
            GezagTransformer transformer = new GezagTransformer();
            List<Persoon> expectedPersonen = transformer.fromGezagrelaties(new ArrayList<>(expected));

            Persoon expectedPerson = expectedPersonen.get(0);
            Persoon actualPerson = result.getPersonen().get(0);

            assertEquals(expectedPerson.getBurgerservicenummer(), actualPerson.getBurgerservicenummer());
            List<AbstractGezagsrelatie> actualGezagsrelaties = new ArrayList<>(actualPerson.getGezag());
            for (AbstractGezagsrelatie gezagsrelatie : actualPerson.getGezag()) {
                assertTrue(expectedPerson.getGezag().contains(gezagsrelatie));
                actualGezagsrelaties.remove(gezagsrelatie);
            }

            assertTrue(actualGezagsrelaties.isEmpty());
        });
    }
}
