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
class IsGeadopteerdMetNlAkteFunctionTest {

    private static final String V1_3B_JA = "Ja";
    private static final String V1_3B_NEE = "Nee";
    private static final String AKTE_ERKENNING = "1AC0109";
    private static final String AKTE_ADOPTIE = "1AQ0109";
    @Mock
    private GezagsBepaling gezagsBepaling;
    @Mock
    private Persoon persoon;
    @Mock
    private ARAntwoordenModel arAntwoordenModel;
    private IsGeadopteerdMetNlAkteFunction classUnderTest;

    @BeforeEach
    public void setup() {

        Persoonslijst persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        classUnderTest = new IsGeadopteerdMetNlAkteFunction();
    }

    @Test
    void isGeadopteerdMetNlAkteWithoutAktenummer() {

        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV0103B(V1_3B_NEE);
    }

    @Test
    void isGeadopteerdMetNlAkteWithAktenummerNotBeingAdoptie() {

        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV0103B(V1_3B_NEE);
    }

    @Test
    void isGeadopteerdMetNlAkteWithAktenummerBeingAdoptie() {

        when(persoon.getAktenummer()).thenReturn(AKTE_ADOPTIE);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV0103B(V1_3B_JA);
    }
}