package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder1;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder2;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OuderOverledenOfOnbevoegdTotGezagTest {

    @Mock
    private GezagsBepaling gezagsBepaling;

    @Mock
    private ARAntwoordenModel arAntwoordenModel;

    @Mock
    private Ouder1 ouder1;
    @Mock
    private Ouder2 ouder2;
    private Persoonslijst persoonslijst;
    @Mock
    private Persoonslijst persoonslijstOuder1;
    @Mock
    private Persoonslijst persoonslijstOuder2;
    private OuderOverledenOfOnbevoegdTotGezag classUnderTest;

    private static final String JA_OUDER_OVERLEDEN = "Ja_ouder_overleden";
    private static final String V4A_3_NEE_OUDER1 = "Nee_ouder1";
    private static final String V4A_3_NEE_OUDER2 = "Nee_ouder2";
    private static final String V4A_3_NEE = "Nee";
    private static final String INDICATION_PARENT_MISSING = "Preconditie: Ouder moet een BSN hebben";
    private static final String INDICATION_PARENT_NOT_REGISTERED = "Ouder moet geregistreerd staan in het BRP";
    private static final String BURGERSERVICENUMMER = "mock";

    @BeforeEach
    public void setup() {
        persoonslijst = new Persoonslijst();
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        classUnderTest = new OuderOverledenOfOnbevoegdTotGezag(gezagsBepaling);
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithoutValues() {
        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertTrue(exception.getMessage().contains(INDICATION_PARENT_MISSING));
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithOneEmptyParent() {
        persoonslijst.setOuder1(ouder1);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertTrue(exception.getMessage().contains(INDICATION_PARENT_MISSING));
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithTwoEmptyParents() {
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertTrue(exception.getMessage().contains(INDICATION_PARENT_MISSING));
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithParentHavingBurgerservicenummer() {
        when(ouder1.getBurgerservicenummer()).thenReturn(BURGERSERVICENUMMER);
        persoonslijst.setOuder1(ouder1);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertTrue(exception.getMessage().contains(INDICATION_PARENT_NOT_REGISTERED));
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithParentOverledenOfOnbevoegd() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getBurgerservicenummer()).thenReturn(BURGERSERVICENUMMER);
        persoonslijst.setOuder1(ouder1);
        when(persoonslijstOuder1.isOverledenOfOnbevoegd()).thenReturn(true);
        when(persoonslijstOuder1.isOverledenOfOnbevoegdEncoded()).thenReturn(Optional.of('o'));
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04A03(JA_OUDER_OVERLEDEN);
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithParentAsOuder2OverledenOfOnbevoegd() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder2.getBurgerservicenummer()).thenReturn(BURGERSERVICENUMMER);
        persoonslijst.setOuder2(ouder2);
        when(persoonslijstOuder2.isOverledenOfOnbevoegd()).thenReturn(true);
        when(persoonslijstOuder2.isOverledenOfOnbevoegdEncoded()).thenReturn(Optional.of('o'));
        when(gezagsBepaling.getPlOuder2()).thenReturn(persoonslijstOuder2);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04A03(JA_OUDER_OVERLEDEN);
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithParentNotOverledenOfOnbevoegd() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getBurgerservicenummer()).thenReturn(BURGERSERVICENUMMER);
        persoonslijst.setOuder1(ouder1);
        when(persoonslijstOuder1.isOverledenOfOnbevoegd()).thenReturn(false);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04A03(V4A_3_NEE_OUDER1);
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithParentAsOuder2NotOverledenOfOnbevoegd() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder2.getBurgerservicenummer()).thenReturn(BURGERSERVICENUMMER);
        persoonslijst.setOuder2(ouder2);
        when(persoonslijstOuder2.isOverledenOfOnbevoegd()).thenReturn(false);
        when(gezagsBepaling.getPlOuder2()).thenReturn(persoonslijstOuder2);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04A03(V4A_3_NEE_OUDER2);
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithParentsNotOverledenOfOnbevoegd() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getBurgerservicenummer()).thenReturn(BURGERSERVICENUMMER);
        persoonslijst.setOuder1(ouder1);
        when(persoonslijstOuder1.isOverledenOfOnbevoegd()).thenReturn(false);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(persoonslijstOuder2.isOverledenOfOnbevoegd()).thenReturn(false);
        when(gezagsBepaling.getPlOuder2()).thenReturn(persoonslijstOuder2);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04A03(V4A_3_NEE);
    }
}