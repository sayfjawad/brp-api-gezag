package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import static org.assertj.core.api.Assertions.assertThat;
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
class AdoptiefOudersFunctionTest {

    private static final String V2A_2_JA = "Ja";
    private static final String V2A_2_NEE = "Nee";
    private static final String AKTE_ERKENNING = "1AC0109";
    private static final String AKTE_ADOPTIE = "1AQ0109";
    @Mock
    private GezagsBepaling gezagsBepaling;
    @Mock
    private Persoon persoon;
    @Mock
    private ARAntwoordenModel arAntwoordenModel;
    private AdoptiefOudersFunction classUnderTest;

    @BeforeEach
    public void setup() {

        Persoonslijst persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        classUnderTest = new AdoptiefOudersFunction();
    }

    @Test
    void adoptiefOudersWithoutAktenummer() {

        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02A02(V2A_2_NEE);
    }

    @Test
    void adoptiefOudersWithAktenummerNotBeingAdoptie() {

        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02A02(V2A_2_NEE);
    }

    @Test
    void adoptiefOudersWithAktenummerBeingAdoptie() {

        when(persoon.getAktenummer()).thenReturn(AKTE_ADOPTIE);
        var antwoord = classUnderTest.perform(gezagsBepaling);
        assertThat(antwoord.answer()).isEqualTo(V2A_2_JA);
    }
}