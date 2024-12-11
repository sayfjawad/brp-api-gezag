package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OuderOfPartnerOverledenOfOnbevoegdTotGezagTest {

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
    private OuderOfPartnerOverledenOfOnbevoegdTotGezag classUnderTest;

    private static final String V4B_1_NEE = "Nee";
    private static final String V4B_1_JA_BEIDEN = "Ja_beiden";
    private static final String V4B_1_JA_OUDER1 = "Ja_ouder1";
    private static final String V4B_1_JA_OUDER2 = "Ja_ouder2";
    private static final String V4B_1_JA_NIET_OUDER1 = "Ja_nietouder1";
    private static final String INDICATION_EXCEPTION_MINIMAL_ONE_PARENT = "Preconditie: Minimaal 1 ouder moet geregistreerd staan";
    private static final String INDICATION_EXCEPTION_NIET_OUDER = "Preconditie: niet_ouder moet geregistreerd";

    @BeforeEach
    public void setup() {
        classUnderTest = new OuderOfPartnerOverledenOfOnbevoegdTotGezag(gezagsBepaling);
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithoutValues() {
        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_MINIMAL_ONE_PARENT));
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithOuder1() {
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_NIET_OUDER));
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithOuder2() {
        when(gezagsBepaling.getPlOuder2()).thenReturn(persoonslijstOuder2);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_NIET_OUDER));
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithOuderAndNietOuder() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(gezagsBepaling.getPlNietOuder()).thenReturn(persoonslijstNietOuder);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04B01(V4B_1_NEE);
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithOuderOverledenOfOnbevoegd() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoonslijstOuder1.isOverledenOfOnbevoegd()).thenReturn(true);
        when(persoonslijstNietOuder.isOverledenOfOnbevoegd()).thenReturn(false);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(gezagsBepaling.getPlNietOuder()).thenReturn(persoonslijstNietOuder);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04B01(V4B_1_JA_OUDER1);
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithNietOuderOverledenOfOnbevoegd() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoonslijstOuder1.isOverledenOfOnbevoegd()).thenReturn(false);
        when(persoonslijstNietOuder.isOverledenOfOnbevoegd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(gezagsBepaling.getPlNietOuder()).thenReturn(persoonslijstNietOuder);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04B01(V4B_1_JA_NIET_OUDER1);
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithOuder2OverledenOfOnbevoegd() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoonslijstOuder2.isOverledenOfOnbevoegd()).thenReturn(true);
        when(persoonslijstNietOuder.isOverledenOfOnbevoegd()).thenReturn(false);
        when(gezagsBepaling.getPlOuder2()).thenReturn(persoonslijstOuder2);
        when(gezagsBepaling.getPlNietOuder()).thenReturn(persoonslijstNietOuder);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04B01(V4B_1_JA_OUDER2);
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithAllParentsOuder2OverledenOfOnbevoegd() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoonslijstOuder1.isOverledenOfOnbevoegd()).thenReturn(false);
        when(persoonslijstOuder2.isOverledenOfOnbevoegd()).thenReturn(true);
        when(persoonslijstNietOuder.isOverledenOfOnbevoegd()).thenReturn(false);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(gezagsBepaling.getPlOuder2()).thenReturn(persoonslijstOuder2);
        when(gezagsBepaling.getPlNietOuder()).thenReturn(persoonslijstNietOuder);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04B01(V4B_1_NEE);
    }

    @Test
    void ouderOfPartnerOverledenOfOnbevoegdTotGezagWithBothParentsOverledenOfOnbevoegd() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(persoonslijstOuder1.isOverledenOfOnbevoegd()).thenReturn(true);
        when(persoonslijstNietOuder.isOverledenOfOnbevoegd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(gezagsBepaling.getPlNietOuder()).thenReturn(persoonslijstNietOuder);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04B01(V4B_1_JA_BEIDEN);
    }
}