package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HoeveelJuridischeOudersHeeftMinderjarigeTest {

    private static final String INDICATION_PUNTOUDER = ".";
    private static final String GEEN_OUDERS = "Geen_ouders";
    private static final String EEN_OUDER = "Een_ouder";
    private static final String TWEE_OUDERS = "Twee_ouders";
    private static final String PUNTOUDERS = "1_of_2_puntouders";
    private static final String GESLACHTSNAAM = "mock";
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

    @BeforeEach
    public void setup() {
        persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        classUnderTest = new HoeveelJuridischeOudersHeeftMinderjarige();
    }

    @Test
    void hoeveelJuridischeOudersHeeftMinderjarigeWithoutValues() {
        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(GEEN_OUDERS);
    }

    @Test
    void hoeveelJuridischeOudersWithOneParentOuder1NotHavingGeslachtsnaam() {
        persoonslijst.setOuder1(ouder1);

        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(GEEN_OUDERS);
    }

    @Test
    void hoeveelJuridischeOudersWithOneParentOuder2NotHavingGeslachtsnaam() {
        persoonslijst.setOuder2(ouder2);

        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(GEEN_OUDERS);
    }

    @Test
    void hoeveelJuridischeOudersWithOneParentPuntOuder() {
        when(ouder1.getGeslachtsnaam()).thenReturn(INDICATION_PUNTOUDER);
        persoonslijst.setOuder1(ouder1);

        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(PUNTOUDERS);
    }

    @Test
    void hoeveelJuridischeOudersWithOneParentOuder1() {
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);

        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(EEN_OUDER);
    }

    @Test
    void hoeveelJuridischeOudersWithOneParentOuder2() {
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder2(ouder2);

        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(EEN_OUDER);
    }

    @Test
    void hoeveelJuridischeOudersWithTwoParents() {
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);

        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(TWEE_OUDERS);
    }

    @Test
    void hoeveelJuridischeOudersWithTwoParentsOneBeingPuntOuder() {
        when(ouder1.getGeslachtsnaam()).thenReturn(INDICATION_PUNTOUDER);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);

        var antwoord = classUnderTest.perform(gezagsBepaling);

        assertThat(antwoord.answer()).isEqualTo(PUNTOUDERS);
    }
}