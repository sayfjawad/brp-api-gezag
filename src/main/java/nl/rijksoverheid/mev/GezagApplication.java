package nl.rijksoverheid.mev;

import org.openapitools.OpenApiGeneratorApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Springboot applicatie
 */
@ConfigurationPropertiesScan
@SpringBootApplication
public class GezagApplication {

    /**
     * Start de gezag applicatie als spring applicatie
     *
     * @param args argumenten voor de applicatie
     */
    public static void main(final String[] args) {
        SpringApplication.run(new Class[]{GezagApplication.class, OpenApiGeneratorApplication.class}, args);
    }
}
