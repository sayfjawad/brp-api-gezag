package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HoeveelJuridischeOudersHeeftMinderjarigeTest {

    @Mock
    private GezagsBepaling gezagsBepaling;

    @Mock
    private Persoon persoon;

    @Mock
    private ARAntwoordenModel arAntwoordenModel;

    @Mock
    private Ouder1 ouder1;
    @Mock
    private Ouder2 ouder2;

    private Persoonslijst persoonslijst;
    private HoeveelJuridischeOudersHeeftMinderjarige classUnderTest;

    private static final String INDICATION_PUNTOUDER = ".";
    private static final String GEEN_OUDERS = "Geen_ouders";
    private static final String EEN_OUDER = "Een_ouder";
    private static final String TWEE_OUDERS = "Twee_ouders";
    private static final String PUNTOUDERS = "1_of_2_puntouders";
    private static final String GESLACHTSNAAM = "mock";

    @BeforeEach
    public void setup() {
        persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        classUnderTest = new HoeveelJuridischeOudersHeeftMinderjarige(gezagsBepaling);
    }

    @Test
    void hoeveelJuridischeOudersHeeftMinderjarigeWithoutValues() {
        String expected = GEEN_OUDERS;

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0201(expected);
    }

    @Test
    void hoeveelJuridischeOudersWithOneParentOuder1NotHavingGeslachtsnaam() {
        String expected = GEEN_OUDERS;
        persoonslijst.setOuder1(ouder1);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0201(expected);
    }

    @Test
    void hoeveelJuridischeOudersWithOneParentOuder2NotHavingGeslachtsnaam() {
        String expected = GEEN_OUDERS;
        persoonslijst.setOuder2(ouder2);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0201(expected);
    }

    @Test
    void hoeveelJuridischeOudersWithOneParentPuntOuder() {
        String expected = PUNTOUDERS;
        when(ouder1.getGeslachtsnaam()).thenReturn(INDICATION_PUNTOUDER);
        persoonslijst.setOuder1(ouder1);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0201(expected);
    }

    @Test
    void hoeveelJuridischeOudersWithOneParentOuder1() {
        String expected = EEN_OUDER;
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0201(expected);
    }

    @Test
    void hoeveelJuridischeOudersWithOneParentOuder2() {
        String expected = EEN_OUDER;
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder2(ouder2);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0201(expected);
    }

    @Test
    void hoeveelJuridischeOudersWithTwoParents() {
        String expected = TWEE_OUDERS;
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0201(expected);
    }

    @Test
    void hoeveelJuridischeOudersWithTwoParentsOneBeingPuntOuder() {
        String expected = PUNTOUDERS;
        when(ouder1.getGeslachtsnaam()).thenReturn(INDICATION_PUNTOUDER);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV0201(expected);
    }
}