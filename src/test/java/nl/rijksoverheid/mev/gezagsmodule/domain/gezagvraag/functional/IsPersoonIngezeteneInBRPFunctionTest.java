package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.Verblijfplaats;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.IsPersoonIngezeteneInBRP;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IsPersoonIngezeteneInBRPFunctionTest {

    @Mock
    private GezagsBepaling gezagsBepaling;

    @Mock
    private Persoon persoon;

    @Mock
    private Verblijfplaats verblijfplaats;

    @Mock
    private ARAntwoordenModel arAntwoordenModel;

    private IsPersoonIngezeteneInBRPFunction classUnderTest;

    private Persoonslijst persoonslijst;

    private static final String V1_1_NEE = "Nee";
    private static final String V1_1_JA = "Ja";
    private static final String RNI = "1999";
    private static final String NOT_RNI = "1888";
    private static final String INDICATION_MISSING_VERBLIJFPLAATS = "verblijfplaats van bevraagde persoon";
    private static final String INDICATION_MISSING_GEMEENTE_VAN_INSCHRIJVING = "gemeente van inschrijving van bevraagde persoon";

    @BeforeEach
    public void setup() {
        persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        classUnderTest = new IsPersoonIngezeteneInBRPFunction();
    }

    @Test
    void isPersoonIngezeteneInBRPWithoutValues() {
        classUnderTest.perform(gezagsBepaling);

        verify(gezagsBepaling).addMissendeGegegevens(INDICATION_MISSING_VERBLIJFPLAATS);
    }

    @Test
    void isPersoonIngezeteneInBRPWithEmptyVerblijfsplaats() {
        persoonslijst.setVerblijfplaats(verblijfplaats);

        classUnderTest.perform(gezagsBepaling);

        verify(gezagsBepaling).addMissendeGegegevens(INDICATION_MISSING_GEMEENTE_VAN_INSCHRIJVING);
    }

    @Test
    void isPersoonIngezeteneInBRPWithEmptyGemeenteVanInschrijving() {
        when(verblijfplaats.getGemeenteVanInschrijving()).thenReturn("");
        persoonslijst.setVerblijfplaats(verblijfplaats);

        classUnderTest.perform(gezagsBepaling);

        verify(gezagsBepaling).addMissendeGegegevens(INDICATION_MISSING_GEMEENTE_VAN_INSCHRIJVING);
    }

    @Test
    void isPersoonIngezeteneInBRPGemeenteVanInschrijvingNotRNI() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(verblijfplaats.getGemeenteVanInschrijving()).thenReturn(NOT_RNI);
        persoonslijst.setVerblijfplaats(verblijfplaats);

        classUnderTest.perform(gezagsBepaling);

        verify(arAntwoordenModel).setV0101(V1_1_JA);
    }

    @Test
    void isPersoonIngezeteneInBRPGemeenteVanInschrijvingRNI() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(verblijfplaats.getGemeenteVanInschrijving()).thenReturn(RNI);
        persoonslijst.setVerblijfplaats(verblijfplaats);

        classUnderTest.perform(gezagsBepaling);

        verify(arAntwoordenModel).setV0101(V1_1_NEE);
    }
}