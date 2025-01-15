package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Gezagsverhouding;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IsUitspraakGezagAanwezigTest {

    @Mock
    private Gezagsverhouding gezagsverhouding;

    @Mock
    private GezagsBepaling gezagsBepaling;

    @Mock
    private ARAntwoordenModel arAntwoordenModel;

    private Persoonslijst persoonslijst;
    private IsUitspraakGezagAanwezig classUnderTest;

    private static final String V1_4_JA = "Ja";
    private static final String V1_4_NEE = "Nee";

    private static final String GEZAGINDICATIE_INVALID = "P";
    private static final String GEZAGINDICATIE_VALID = "D";

    @BeforeEach
    public void setup() {
        persoonslijst = new Persoonslijst();
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        classUnderTest = new IsUitspraakGezagAanwezig(gezagsBepaling);
    }

    @Test
    void isUitspraakGezagAanwezigWithoutValues() {
        classUnderTest.perform();

        verify(arAntwoordenModel).setV0104(V1_4_NEE);
    }

    @Test
    void isUitspraakGezagAanwezigWithEmptyGezagsverhouding() {
        persoonslijst.setGezagsverhouding(gezagsverhouding);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0104(V1_4_NEE);
    }

    @Test
    void isUitspraakGezagAanwezigWithGezagsverhoudingWithEmptyValueForGezag() {
        when(gezagsverhouding.getIndicatieGezagMinderjarige()).thenReturn("");
        persoonslijst.setGezagsverhouding(gezagsverhouding);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0104(V1_4_NEE);
    }

    @Test
    void isUitspraakGezagAanwezigWithGezagsverhoudingValueNotValidGezagsindicatie() {
        when(gezagsverhouding.getIndicatieGezagMinderjarige()).thenReturn(GEZAGINDICATIE_INVALID);
        persoonslijst.setGezagsverhouding(gezagsverhouding);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0104(V1_4_NEE);
    }

    @Test
    void isUitspraakGezagAanwezigWithGezagsverhoudingValueValidGezagsindicatie() {
        when(gezagsverhouding.getIndicatieGezagMinderjarige()).thenReturn(GEZAGINDICATIE_VALID);
        persoonslijst.setGezagsverhouding(gezagsverhouding);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0104(V1_4_JA);
    }
}