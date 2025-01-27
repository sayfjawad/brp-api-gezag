package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * TODO: verdere testen schrijven
 */
@ExtendWith(MockitoExtension.class)
class IsStaandeHuwelijkOfPartnerschapGeborenTest {

    private static final String V2B_1_NEE = "Nee";
    @Mock
    private GezagsBepaling gezagsBepaling;
    @Mock
    private Persoon persoon;
    @Mock
    private ARAntwoordenModel arAntwoordenModel;
    private IsStaandeHuwelijkOfPartnerschapGeboren classUnderTest;
    private Persoonslijst persoonslijst;

    @BeforeEach
    public void setup() {

        persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        classUnderTest = new IsStaandeHuwelijkOfPartnerschapGeboren();
    }

    @Test
    void isStaandeHuwelijkOfPartnerschapGeborenWithoutValues() {

        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02B01(V2B_1_NEE);
    }
}