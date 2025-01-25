package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * A simple Spring Boot test verifying that GezagsVragenMapFactory is in prototype scope. Each bean
 * request should yield a distinct instance.
 */
@SpringBootTest
class GezagsVragenMapFactoryScopeTest {

    @Autowired
    private ApplicationContext context;

    /**
     * Tests that the {@link GezagsVragenMapFactory} bean adheres to the "prototype" scope. Verifies
     * each retrieval from the {@link ApplicationContext} returns a distinct instance.
     * <p>
     * Assertions: - Ensures that two instances retrieved from the application context are not the
     * same. - Validates the correctness of the "prototype" scope applied to the bean.
     */
    @Test
    @DisplayName("Given a prototype-scoped GezagsVragenMapFactory when retrieving multiple beans then each instance should be distinct")
    void testPrototypeScope() {
        // Retrieve two instances from the context
        final var factory1 = context.getBean(GezagsVragenMapFactory.class);
        final var factory2 = context.getBean(GezagsVragenMapFactory.class);
        // They should be different instances if scope = "prototype"
        assertNotSame(factory1, factory2,
                "Expected two distinct instances due to prototype scope, but got the same instance.");
    }
}
