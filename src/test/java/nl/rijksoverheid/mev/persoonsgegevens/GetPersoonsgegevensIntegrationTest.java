package nl.rijksoverheid.mev.persoonsgegevens;

import nl.rijksoverheid.mev.GezagApplication;
import org.junit.jupiter.api.Test;
import org.openapitools.OpenApiGeneratorApplication;
import org.openapitools.model.PersoonsgegevensRequest;
import org.openapitools.model.PersoonsgegevensResultaaat;
import org.openapitools.model.Identificatie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    classes = {GezagApplication.class, OpenApiGeneratorApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class GetPersoonsgegevensIntegrationTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void foutmeldingWordtGegevenBijSturenRequestZonderGegevens() {
        webTestClient.post().uri("/api/v1/getPersoonsgegevens")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new PersoonsgegevensRequest()
            )
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @Test
    void foutmeldingWordtGegevenBijSturenRequestMetLegeGegevens() {
        webTestClient.post().uri("/api/v1/getPersoonsgegevens")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new PersoonsgegevensRequest()
                .vertegenwoordiger(new Identificatie()
                    .identificatieNummer("")
                    .identificatieSoort("bsn")
                )
                .vertegenwoordigde(new Identificatie()
                    .identificatieNummer("")
                    .identificatieSoort("bsn")
                )
            )
            .exchange()
            .expectStatus().isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY)
            .expectBody(ProblemDetail.class).consumeWith(response -> {
                var problemDetail = response.getResponseBody();
                assertThat(problemDetail).isNotNull();

                assertThat(problemDetail.getDetail()).isEqualTo("Burgerservicenummer voldoet niet aan de elfproef");
            });
    }

    @Test
    void ophalenPersoonsgegevensMetVertegenwoordigerLevertLijstMetPersoonsgegevensOp() {
        var vertegenwoordigder = "999998675";

        webTestClient.post().uri("/api/v1/getPersoonsgegevens")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new PersoonsgegevensRequest()
                .vertegenwoordiger(new Identificatie()
                    .identificatieNummer("999998675")
                    .identificatieSoort("bsn")
                )
                .vertegenwoordigde(new Identificatie()
                    .identificatieNummer("")
                    .identificatieSoort("bsn")
                )
            )
            .exchange()
            .expectStatus().isOk()
            .expectBody(PersoonsgegevensResultaaat.class).consumeWith(response -> {
                var persoonsgegevensResult = response.getResponseBody();
                assertThat(persoonsgegevensResult).isNotNull();
                assertThat(!persoonsgegevensResult.getPersoonsgegevensList().isEmpty());
            });
    }

    @Test
    void ophalenPersoonsgegevensMetVertegenwoordigdeEnVertegenwoordigerHeeftGezagEnDusResultaat() {
        var vertegenwoordiger = "999998675";
        var vertegenwoordigde = "999998699";

        webTestClient.post().uri("/api/v1/getPersoonsgegevens")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new PersoonsgegevensRequest()
                .vertegenwoordiger(new Identificatie()
                    .identificatieNummer(vertegenwoordiger)
                    .identificatieSoort("bsn")
                )
                .vertegenwoordigde(new Identificatie()
                    .identificatieNummer(vertegenwoordigde)
                    .identificatieSoort("bsn")
                )
            )
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(PersoonsgegevensResultaaat.class).consumeWith(response -> {
                var persoonsgegevensResult = response.getResponseBody();
                assertThat(persoonsgegevensResult).isNotNull();
                assertThat(!persoonsgegevensResult.getPersoonsgegevensList().isEmpty());
            });
    }

    @Test
    void ophalenPersoonsgegevensMetVertegenwoordigdeEnVertegenwoordigerHeeftGeenGezagEnDusGeenResultaat() {
        var vertegenwoordigde = "999970021";
        var vertegenwoordiger = "111111110";

        webTestClient.post().uri("/api/v1/getPersoonsgegevens")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new PersoonsgegevensRequest()
                .vertegenwoordiger(new Identificatie()
                    .identificatieNummer(vertegenwoordiger)
                    .identificatieSoort("bsn")
                )
                .vertegenwoordigde(new Identificatie()
                    .identificatieNummer(vertegenwoordigde)
                    .identificatieSoort("bsn")
                )
            )
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(PersoonsgegevensResultaaat.class).consumeWith(response -> {
                var persoonsgegevensResult = response.getResponseBody();
                assertThat(persoonsgegevensResult).isNotNull();
                assertThat(persoonsgegevensResult.getPersoonsgegevensList().isEmpty());
            });
    }
}
