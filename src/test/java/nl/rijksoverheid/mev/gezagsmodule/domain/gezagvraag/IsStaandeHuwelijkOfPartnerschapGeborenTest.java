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
import static org.mockito.Mockito.when;

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
        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(V2B_1_NEE);
    }
}