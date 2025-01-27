package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Gezagsverhouding;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IndicatieGezagMinderjarigeTest {

    private static final String GEZAGSVERHOUDING_VALUE = "1";
    private static final String MISSING_GEZAGSVERHOUDING = "gezagsverhouding van bevraagde persoon";
    @Mock
    private Gezagsverhouding gezagsverhouding;
    @Mock
    private GezagsBepaling gezagsBepaling;
    @Mock
    private ARAntwoordenModel arAntwoordenModel;
    private Persoonslijst persoonslijst;
    private IndicatieGezagMinderjarige classUnderTest;

    @BeforeEach
    public void setup() {

        persoonslijst = new Persoonslijst();
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        classUnderTest = new IndicatieGezagMinderjarige();
    }

    @Test
    void indicatieGezagMinderjarigeNoGezagsverhouding() {

        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV0302(null);
        verify(gezagsBepaling).addMissendeGegegevens(MISSING_GEZAGSVERHOUDING);
    }

    @Test
    void indicatieGezagMinderjarigeEmptyGezagsverhouding() {

        persoonslijst.setGezagsverhouding(gezagsverhouding);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV0302(null);
    }

    @Test
    void indicatieGezagMinderjarigeGezagsverhoudingWithEmptyIndicatieGezag() {

        when(gezagsverhouding.getIndicatieGezagMinderjarige()).thenReturn("");
        persoonslijst.setGezagsverhouding(gezagsverhouding);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV0302("");
    }

    @Test
    void indicatieGezagMinderjarigeGezagsverhoudingWithValue() {

        when(gezagsverhouding.getIndicatieGezagMinderjarige()).thenReturn(GEZAGSVERHOUDING_VALUE);
        persoonslijst.setGezagsverhouding(gezagsverhouding);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV0302(GEZAGSVERHOUDING_VALUE);
    }
}