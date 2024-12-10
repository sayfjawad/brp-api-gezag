package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IsErSprakeVanEenRecenteGebeurtenisTest {

    @Mock
    private GezagsBepaling gezagsBepaling;

    @Mock
    private Persoon persoon;

    @Mock
    private ARAntwoordenModel arAntwoordenModel;

    private Persoonslijst persoonslijst;
    private IsErSprakeVanEenRecenteGebeurtenis classUnderTest;

    private static final String MISSING_GEZAGSVERHOUDING = "gezagsverhouding van bevraagde persoon";

    @BeforeEach
    public void setup() {
        persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        classUnderTest = new IsErSprakeVanEenRecenteGebeurtenis(gezagsBepaling);
    }

    @Test
    void isErSprakeVanEenRecenteGebeurtenisWithoutValues() {
        String missendeGegeven = MISSING_GEZAGSVERHOUDING;

        classUnderTest.perform();

        verify(gezagsBepaling).addMissendeGegegevens(missendeGegeven);
    }

    // when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
}