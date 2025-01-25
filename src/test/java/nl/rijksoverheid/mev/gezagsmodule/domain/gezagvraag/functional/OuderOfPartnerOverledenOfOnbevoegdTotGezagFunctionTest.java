package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OuderOfPartnerOverledenOfOnbevoegdTotGezagFunctionTest {

    private static final char OVERLEDEN_TOKEN = 'o';
    private static final String V4B_1_NEE = "Nee";
    private static final String V4B_1_JA_BEIDEN_OVERLEDEN = "Ja_beiden_overleden";
    private static final String V4B_1_JA_OUDER1 = "Ja_ouder1";
    private static final String V4B_1_JA_OUDER2 = "Ja_ouder2";
    private static final String V4B_1_JA_NIET_OUDER1 = "Ja_nietouder1";
    private static final String INDICATION_EXCEPTION_MINIMAL_ONE_PARENT = "Preconditie: Minimaal 1 ouder moet geregistreerd staan";
    private static final String INDICATION_EXCEPTION_NIET_OUDER = "Preconditie: niet_ouder moet geregistreerd";
    @Mock
    private GezagsBepaling gezagsBepaling;
    @Mock
    private ARAntwoordenModel arAntwoordenModel;
    @Mock
    private Persoonslijst persoonslijstOuder1;
    @Mock
    private Persoonslijst persoonslijstOuder2;
    @Mock
    private Persoonslijst persoonslijstNietOuder;
    private OuderOfPartnerOverledenOfOnbevoegdTotGezagFunction classUnderTest;

    @BeforeEach
    public void setup() {

        classUnderTest = new OuderOfPartnerOverledenOfOnbevoegdTotGezagFunction();
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithoutValues() {

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class,
                () -> classUnderTest.perform(gezagsBepaling));
        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_MINIMAL_ONE_PARENT));
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithOuder1() {

        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class,
                () -> classUnderTest.perform(gezagsBepaling));
        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_NIET_OUDER));
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithOuder2() {

        when(gezagsBepaling.getPlOuder2()).thenReturn(persoonslijstOuder2);
        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class,
                () -> classUnderTest.perform(gezagsBepaling));
        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_NIET_OUDER));
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithOuderAndNietOuder() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(gezagsBepaling.getPlNietOuder()).thenReturn(persoonslijstNietOuder);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV04B01(V4B_1_NEE);
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithOuderOverledenOfOnbevoegd() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoonslijstOuder1.isOverledenOfOnbevoegd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(gezagsBepaling.getPlNietOuder()).thenReturn(persoonslijstNietOuder);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV04B01(V4B_1_JA_OUDER1);
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithNietOuderOverledenOfOnbevoegd() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoonslijstOuder1.isOverledenOfOnbevoegd()).thenReturn(false);
        when(persoonslijstNietOuder.isOverledenOfOnbevoegdEncoded()).thenReturn(
                Optional.of(OVERLEDEN_TOKEN));
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(gezagsBepaling.getPlNietOuder()).thenReturn(persoonslijstNietOuder);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV04B01(V4B_1_JA_NIET_OUDER1);
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithOuder2OverledenOfOnbevoegd() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoonslijstOuder2.isOverledenOfOnbevoegd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder2()).thenReturn(persoonslijstOuder2);
        when(gezagsBepaling.getPlNietOuder()).thenReturn(persoonslijstNietOuder);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV04B01(V4B_1_JA_OUDER2);
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithAllParentsOuder2OverledenOfOnbevoegd() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoonslijstOuder1.isOverledenOfOnbevoegd()).thenReturn(false);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(gezagsBepaling.getPlOuder2()).thenReturn(persoonslijstOuder2);
        when(gezagsBepaling.getPlNietOuder()).thenReturn(persoonslijstNietOuder);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV04B01(V4B_1_NEE);
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithBothParentsOverledenOfOnbevoegd() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoonslijstOuder1.isOverledenOfOnbevoegd()).thenReturn(true);
        when(persoonslijstOuder1.isOverledenOfOnbevoegdEncoded()).thenReturn(
                Optional.of(OVERLEDEN_TOKEN));
        when(persoonslijstNietOuder.isOverledenOfOnbevoegdEncoded()).thenReturn(
                Optional.of(OVERLEDEN_TOKEN));
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(gezagsBepaling.getPlNietOuder()).thenReturn(persoonslijstNietOuder);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV04B01(V4B_1_JA_BEIDEN_OVERLEDEN);
    }
}
