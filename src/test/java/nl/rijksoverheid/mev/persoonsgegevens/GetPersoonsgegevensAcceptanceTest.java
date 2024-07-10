package nl.rijksoverheid.mev.persoonsgegevens;

import java.util.List;
import nl.rijksoverheid.mev.GezagApplication;
import org.junit.jupiter.api.Test;
import org.openapitools.OpenApiGeneratorApplication;
import org.openapitools.model.PersoonsgegevensRequest;
import org.openapitools.model.PersoonsgegevensResultaaat;
import org.openapitools.model.Identificatie;
import org.openapitools.model.Persoonsgegevens;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    classes = {GezagApplication.class, OpenApiGeneratorApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class GetPersoonsgegevensAcceptanceTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void ophalenPersoonsgegevensMetVertegenwoordigerLevertLijstMetPersoonsgegevensOp() {
        var vertegenwoordigder = "999998675";

        webTestClient.post().uri("/api/v1/getPersoonsgegevens")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(new PersoonsgegevensRequest()
                .vertegenwoordiger(new Identificatie()
                    .identificatieNummer(vertegenwoordigder)
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
                
                List<Persoonsgegevens> persons = persoonsgegevensResult.getPersoonsgegevensList();
                
                assertThat(!persons.isEmpty());
                assertThat(persons.size() == 3);
                
                Persoonsgegevens person = persons.get(0);
                
                assertThat(person).isNotNull();
                assertThat(person.getBsn()).isNotNull();
                assertThat(person.getGeboortedatum()).isNotNull();
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
                
                List<Persoonsgegevens> persons = persoonsgegevensResult.getPersoonsgegevensList();
                
                assertThat(!persons.isEmpty());
                assertThat(persons.size() == 1);
                
                Persoonsgegevens person = persons.get(0);
                
                assertThat(person).isNotNull();
                assertThat(person.getBsn()).isNotNull();
                assertThat(person.getGeboortedatum()).isNotNull();
            });
    }
}
