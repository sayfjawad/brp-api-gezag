package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IsGeborenInBuitenlandFunctionTest {

    private static final String INDICATION_MISSING_GEBOORTELAND = "Geboorteland van bevraagde persoon";
    private static final String CODE_IS_NEDERLAND = "6030";
    private static final String CODE_IS_NOT_NEDERLAND = "7766";
    private static final String V1_3A_JA = "Ja";
    private static final String V1_3A_NEE = "Nee";
    @Mock
    private GezagsBepaling gezagsBepaling;
    @Mock
    private Persoon persoon;
    @Mock
    private ARAntwoordenModel arAntwoordenModel;
    private IsGeborenInBuitenlandFunction classUnderTest;

    @BeforeEach
    public void setup() {

        Persoonslijst persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        classUnderTest = new IsGeborenInBuitenlandFunction();
    }

    @Test
    void isGeborenInBuitenlandWithoutValues() {

        classUnderTest.perform(gezagsBepaling);
        verify(gezagsBepaling).addMissendeGegegevens(INDICATION_MISSING_GEBOORTELAND);
    }

    @Test
    void isGeborenInBuitenlandWithEmptyGeboorteland() {

        when(persoon.getGeboorteland()).thenReturn("");
        classUnderTest.perform(gezagsBepaling);
        verify(gezagsBepaling).addMissendeGegegevens(INDICATION_MISSING_GEBOORTELAND);
    }

    @Test
    void isGeborenInBuitenlandWithGeboortelandNederland() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoon.getGeboorteland()).thenReturn(CODE_IS_NEDERLAND);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV0103A(V1_3A_NEE);
    }

    @Test
    void isGeborenInBuitenlandWithGeboortelandNotNederland() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoon.getGeboorteland()).thenReturn(CODE_IS_NOT_NEDERLAND);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV0103A(V1_3A_JA);
    }
}