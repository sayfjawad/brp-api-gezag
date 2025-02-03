package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Gezagsverhouding;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(null);
        verify(gezagsBepaling).addMissendeGegegevens(MISSING_GEZAGSVERHOUDING);
    }

    @Test
    void indicatieGezagMinderjarigeEmptyGezagsverhouding() {
        persoonslijst.setGezagsverhouding(gezagsverhouding);

        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(null);
    }

    @Test
    void indicatieGezagMinderjarigeGezagsverhoudingWithEmptyIndicatieGezag() {
        when(gezagsverhouding.getIndicatieGezagMinderjarige()).thenReturn("");
        persoonslijst.setGezagsverhouding(gezagsverhouding);

        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo("");
    }

    @Test
    void indicatieGezagMinderjarigeGezagsverhoudingWithValue() {
        when(gezagsverhouding.getIndicatieGezagMinderjarige()).thenReturn(GEZAGSVERHOUDING_VALUE);
        persoonslijst.setGezagsverhouding(gezagsverhouding);

        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(GEZAGSVERHOUDING_VALUE);
    }
}