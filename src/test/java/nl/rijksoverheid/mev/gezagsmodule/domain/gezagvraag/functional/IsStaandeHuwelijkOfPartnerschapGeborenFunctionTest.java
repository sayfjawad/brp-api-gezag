package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.IsStaandeHuwelijkOfPartnerschapGeboren;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * TODO: verdere testen schrijven
 */
@ExtendWith(MockitoExtension.class)
class IsStaandeHuwelijkOfPartnerschapGeborenFunctionTest {

    @Mock
    private GezagsBepaling gezagsBepaling;

    @Mock
    private Persoon persoon;

    @Mock
    private ARAntwoordenModel arAntwoordenModel;

    private IsStaandeHuwelijkOfPartnerschapGeborenFunction classUnderTest;

    private Persoonslijst persoonslijst;

    private static final String V2B_1_NEE = "Nee";

    @BeforeEach
    public void setup() {
        persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        classUnderTest = new IsStaandeHuwelijkOfPartnerschapGeborenFunction();
    }

    @Test
    void isStaandeHuwelijkOfPartnerschapGeborenWithoutValues() {
        classUnderTest.perform(gezagsBepaling);

        verify(arAntwoordenModel).setV02B01(V2B_1_NEE);
    }

}