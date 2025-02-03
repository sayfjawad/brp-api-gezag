package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
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
class IsGeborenInBuitenlandTest {

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
    private IsGeborenInBuitenland classUnderTest;

    @BeforeEach
    public void setup() {
        Persoonslijst persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        classUnderTest = new IsGeborenInBuitenland();
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

        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(V1_3A_NEE);
    }

    @Test
    void isGeborenInBuitenlandWithGeboortelandNotNederland() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoon.getGeboorteland()).thenReturn(CODE_IS_NOT_NEDERLAND);

        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(V1_3A_JA);
    }
}