package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IsGeborenInBuitenlandTest {

    @Mock
    private GezagsBepaling gezagsBepaling;

    @Mock
    private Persoon persoon;

    @Mock
    private ARAntwoordenModel arAntwoordenModel;

    private IsGeborenInBuitenland classUnderTest;

    private static final String INDICATION_MISSING_GEBOORTELAND = "Geboorteland van bevraagde persoon";
    private static final String CODE_IS_NEDERLAND = "6030";
    private static final String CODE_IS_NOT_NEDERLAND = "7766";
    private static final String V1_3A_JA = "Ja";
    private static final String V1_3A_NEE = "Nee";

    @BeforeEach
    public void setup() {
        Persoonslijst persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        classUnderTest = new IsGeborenInBuitenland(gezagsBepaling);
    }

    @Test
    void isGeborenInBuitenlandWithoutValues() {
        String expectingMissendVeld = INDICATION_MISSING_GEBOORTELAND;

        classUnderTest.perform();

        verify(gezagsBepaling).addMissendeGegegevens(expectingMissendVeld);
    }

    @Test
    void isGeborenInBuitenlandWithEmptyGeboorteland() {
        String expectingMissendVeld = INDICATION_MISSING_GEBOORTELAND;
        when(persoon.getGeboorteland()).thenReturn("");

        classUnderTest.perform();

        verify(gezagsBepaling).addMissendeGegegevens(expectingMissendVeld);
    }

    @Test
    void isGeborenInBuitenlandWithGeboortelandNederland() {
        String expected = V1_3A_NEE;
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoon.getGeboorteland()).thenReturn(CODE_IS_NEDERLAND);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0103A(expected);
    }

    @Test
    void isGeborenInBuitenlandWithGeboortelandNotNederland() {
        String expected = V1_3A_JA;
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoon.getGeboorteland()).thenReturn(CODE_IS_NOT_NEDERLAND);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0103A(expected);
    }
}