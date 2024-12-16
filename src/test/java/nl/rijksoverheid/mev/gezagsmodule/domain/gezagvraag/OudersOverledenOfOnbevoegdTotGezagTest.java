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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OudersOverledenOfOnbevoegdTotGezagTest {

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
    private OudersOverledenOfOnbevoegdTotGezag classUnderTest;

    private static final String V4A_2_JA_BEIDEN = "Ja_beiden";
    private static final String V4A_2_JA_OUDER1 = "Ja_ouder1";
    private static final String V4A_2_JA_OUDER2 = "Ja_ouder2";
    private static final String V4A_2_NEE = "Nee";
    private static final String INDICATION_EXCEPTION_NOT_TWO_PARENTS = "Preconditie: Kind moet twee ouders hebben";
    private static final String INDICATION_EXCEPTION_PARENT_ONE_NOT_REGISTERED = "Preconditie: ouder1 moet in BRP geregistreerd staan";
    private static final String INDICATION_EXCEPTION_PARENT_TWO_NOT_REGISTERED = "Preconditie: ouder2 moet in BRP geregistreerd staan";
    private static final String GESLACHTSNAAM = "mock";

    @BeforeEach
    public void setup() {
        persoonslijst = new Persoonslijst();
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        classUnderTest = new OudersOverledenOfOnbevoegdTotGezag(gezagsBepaling);
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithoutValues() {
        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_NOT_TWO_PARENTS));
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithOneParent() {
        persoonslijst.setOuder1(ouder1);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_NOT_TWO_PARENTS));
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithTwoParentsWithoutGeslachtsnaam() {
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_NOT_TWO_PARENTS));
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithTwoParentsWithGeslachtsnaam() {
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());
        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_PARENT_ONE_NOT_REGISTERED));
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithTwoParentsWithGeslachtsnaamOuder1Registered() {
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoonslijstOuder1.isNietIngeschrevenInRNI()).thenReturn(true);
        when(persoonslijstOuder1.isNietGeemigreerd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());
        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_PARENT_TWO_NOT_REGISTERED));
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithTwoParentsRegistered() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoonslijstOuder1.isNietIngeschrevenInRNI()).thenReturn(true);
        when(persoonslijstOuder1.isNietGeemigreerd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(persoonslijstOuder2.isNietIngeschrevenInRNI()).thenReturn(true);
        when(persoonslijstOuder2.isNietGeemigreerd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder2()).thenReturn(persoonslijstOuder2);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04A02(V4A_2_NEE);
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithTwoParentsOneOverledenOfOnbevoegd() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoonslijstOuder1.isNietIngeschrevenInRNI()).thenReturn(true);
        when(persoonslijstOuder1.isNietGeemigreerd()).thenReturn(true);
        when(persoonslijstOuder1.isOverledenOfOnbevoegd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(persoonslijstOuder2.isNietIngeschrevenInRNI()).thenReturn(true);
        when(persoonslijstOuder2.isNietGeemigreerd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder2()).thenReturn(persoonslijstOuder2);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04A02(V4A_2_JA_OUDER1);
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithTwoParentsParentTwoOverledenOfOnbevoegd() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoonslijstOuder1.isNietIngeschrevenInRNI()).thenReturn(true);
        when(persoonslijstOuder1.isNietGeemigreerd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(persoonslijstOuder2.isNietIngeschrevenInRNI()).thenReturn(true);
        when(persoonslijstOuder2.isNietGeemigreerd()).thenReturn(true);
        when(persoonslijstOuder2.isOverledenOfOnbevoegd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder2()).thenReturn(persoonslijstOuder2);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04A02(V4A_2_JA_OUDER2);
    }

    @Test
    void ouderOverledenOfOnbevoegdTotGezagWithTwoParentsBothOverledenOfOnbevoegd() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoonslijstOuder1.isNietIngeschrevenInRNI()).thenReturn(true);
        when(persoonslijstOuder1.isNietGeemigreerd()).thenReturn(true);
        when(persoonslijstOuder1.isOverledenOfOnbevoegd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);
        when(persoonslijstOuder2.isNietIngeschrevenInRNI()).thenReturn(true);
        when(persoonslijstOuder2.isNietGeemigreerd()).thenReturn(true);
        when(persoonslijstOuder2.isOverledenOfOnbevoegd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder2()).thenReturn(persoonslijstOuder2);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV04A02(V4A_2_JA_BEIDEN);
    }
}