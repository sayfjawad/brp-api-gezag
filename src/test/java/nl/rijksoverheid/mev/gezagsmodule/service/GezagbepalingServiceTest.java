package nl.rijksoverheid.mev.gezagsmodule.service;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.openapitools.model.AbstractGezagsrelatie;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GezagbepalingServiceTest {

    @Mock
    private GezagsBepaling gezagsBepalingMock;

    private GezagbepalingService classUnderTest;

    @BeforeEach
    public void setup() {
        classUnderTest = new GezagbepalingService();
    }

    @Test
    void bepaalGezagsrelatiesWithoutAntwoordenModelAndGezagsbepaling() {
        ARAntwoordenModel arAntwoordenModel = null;
        GezagsBepaling gezagsBepaling = null;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertTrue(gezagsRelaties.isEmpty());
    }

    @Test
    void bepaalGezagsrelatiesWithGezagsuitkomstInAntwoordenModelAndHavingNoParents() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertTrue(gezagsRelaties.isEmpty());
    }
}