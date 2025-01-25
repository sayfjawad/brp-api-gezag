package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.Verblijfplaats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IsNaarBuitenlandGeemigreerdGeweestTest {

    private static final String V1_3_JA = "Ja";
    private static final String V1_3_NEE = "Nee";
    private static final String CODE_IS_NEDERLAND = "6030";
    private static final String INDICATION_MISSING_GEBOORTELAND = "Geboorteland van bevraagde persoon";
    private static final String INDICATION_MISSING_VERBLIJFPLAATS = "Verblijfplaats van bevraagde persoon";
    private static final String DATE_01012023 = "20230101";
    @Mock
    private GezagsBepaling gezagsBepaling;
    @Mock
    private Persoon persoon;
    @Mock
    private Verblijfplaats verblijfplaats;
    @Mock
    private ARAntwoordenModel arAntwoordenModel;
    private IsNaarBuitenlandGeemigreerdGeweest classUnderTest;
    private Persoonslijst persoonslijst;

    @BeforeEach
    public void setup() {

        persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        classUnderTest = new IsNaarBuitenlandGeemigreerdGeweest();
    }

    @Test
    void isNaarBuitenlandGeemigreerdGeweestWithoutValues() {

        classUnderTest.perform(gezagsBepaling);
        verify(gezagsBepaling).addMissendeGegegevens(INDICATION_MISSING_GEBOORTELAND);
        verify(arAntwoordenModel).setV0103(null);
    }

    @Test
    void isNaarBuitenlandGeemigreerdGeweestWithEmptyGeboorteland() {

        when(persoon.getGeboorteland()).thenReturn("");
        classUnderTest.perform(gezagsBepaling);
        verify(gezagsBepaling).addMissendeGegegevens(INDICATION_MISSING_GEBOORTELAND);
        verify(arAntwoordenModel).setV0103(null);
    }

    @Test
    void isNaarBuitenlandGeemigreerdGeweestWithGeboortelandAndNotHavingVerblijfplaats() {

        when(persoon.getGeboorteland()).thenReturn(CODE_IS_NEDERLAND);
        classUnderTest.perform(gezagsBepaling);
        verify(gezagsBepaling).addMissendeGegegevens(INDICATION_MISSING_VERBLIJFPLAATS);
        verify(arAntwoordenModel).setV0103(null);
    }

    @Test
    void isNaarBuitenlandGeemigreerdGeweestWithGeboortelandAndVerblijfplaats() {

        persoonslijst.setVerblijfplaats(verblijfplaats);
        when(persoon.getGeboorteland()).thenReturn(CODE_IS_NEDERLAND);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV0103(V1_3_NEE);
    }

    @Test
    void isNaarBuitenlandGeemigreerdGeweestWithGeboortelandNederlandAndVerblijfplaatsDatumVestigingNederlandLeeg() {

        persoonslijst.setVerblijfplaats(verblijfplaats);
        when(verblijfplaats.getDatumVestigingInNederland()).thenReturn("");
        when(persoon.getGeboorteland()).thenReturn(CODE_IS_NEDERLAND);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV0103(V1_3_NEE);
    }

    @Test
    void isNaarBuitenlandGeemigreerdGeweestWithGeboortelandNederlandAndVerblijfplaatsDatumVestigingNederlandGevuld() {

        persoonslijst.setVerblijfplaats(verblijfplaats);
        when(verblijfplaats.getDatumVestigingInNederland()).thenReturn(DATE_01012023);
        when(persoon.getGeboorteland()).thenReturn(CODE_IS_NEDERLAND);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV0103(V1_3_JA);
    }
}