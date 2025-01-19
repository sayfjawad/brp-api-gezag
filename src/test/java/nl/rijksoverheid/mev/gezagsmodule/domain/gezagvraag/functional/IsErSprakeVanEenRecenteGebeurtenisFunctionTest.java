package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Gezagsverhouding;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder1;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder2;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.IsErSprakeVanEenRecenteGebeurtenis;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class IsErSprakeVanEenRecenteGebeurtenisFunctionTest {

    @Mock
    private Gezagsverhouding gezagsverhouding;

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
    private IsErSprakeVanEenRecenteGebeurtenisFunction classUnderTest;

    private static final String MISSING_GEZAGSVERHOUDING = "gezagsverhouding van bevraagde persoon";
    private static final String INDICATION_MISSING_INGANGSDATUM = "Preconditie: Ingangsdatum geldigheid gezag moet een valide datum bevatten";
    private static final String INDICATIE_ONE_AND_TWO = "12";
    private static final String DATE_FIRST_DAY_2021 = "20210101";
    private static final String DATE_FIRST_DAY_2022 = "20220101";
    private static final String GESLACHTSNAAM = "mock";
    private static final String V3_1_JA = "Ja";
    private static final String V3_1_NEE = "Nee";
    private static final String AKTE_ADOPTIE = "1AQ0109";

    @BeforeEach
    public void setup() {
        persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        classUnderTest = new IsErSprakeVanEenRecenteGebeurtenisFunction();
    }

    @Test
    void isErSprakeVanEenRecenteGebeurtenisWithoutValues() {
        classUnderTest.perform(gezagsBepaling);

        verify(gezagsBepaling).addMissendeGegegevens(MISSING_GEZAGSVERHOUDING);
    }

    @Test
    void isErSprakeVanEenRecenteGebeurtenisWithEmptyGezagsverhouding() {
        persoonslijst.setGezagsverhouding(gezagsverhouding);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform(gezagsBepaling));

        assertTrue(exception.getMessage().contains(INDICATION_MISSING_INGANGSDATUM));
    }

    @Test
    void isErSprakeVanEenRecenteGebeurtenisIndicatingTwoParentsWithoutParentsHavingRecenteGebeurtenis() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(gezagsverhouding.getIndicatieGezagMinderjarige()).thenReturn(INDICATIE_ONE_AND_TWO);
        when(gezagsverhouding.getIngangsdatumGeldigheidGezag()).thenReturn(DATE_FIRST_DAY_2021);
        when(gezagsverhouding.hasIngangsdatumGeldigheidGezag()).thenReturn(true);
        persoonslijst.setGezagsverhouding(gezagsverhouding);

        classUnderTest.perform(gezagsBepaling);

        verify(arAntwoordenModel).setV0301(V3_1_JA);
    }

    @Test
    void isErSprakeVanEenRecenteGebeurtenisIndicatingTwoParentsWithOneParentHavingRecenteGebeurtenis() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(gezagsverhouding.getIndicatieGezagMinderjarige()).thenReturn(INDICATIE_ONE_AND_TWO);
        when(gezagsverhouding.getIngangsdatumGeldigheidGezag()).thenReturn(DATE_FIRST_DAY_2021);
        when(gezagsverhouding.hasIngangsdatumGeldigheidGezag()).thenReturn(true);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setGezagsverhouding(gezagsverhouding);

        classUnderTest.perform(gezagsBepaling);

        verify(arAntwoordenModel).setV0301(V3_1_JA);
    }

    @Test
    void isErSprakeVanEenRecenteGebeurtenisIndicatingTwoParentsWithHavingTwoParents() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(gezagsverhouding.getIndicatieGezagMinderjarige()).thenReturn(INDICATIE_ONE_AND_TWO);
        when(gezagsverhouding.getIngangsdatumGeldigheidGezag()).thenReturn(DATE_FIRST_DAY_2021);
        when(gezagsverhouding.hasIngangsdatumGeldigheidGezag()).thenReturn(true);
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder2(ouder2);
        persoonslijst.setGezagsverhouding(gezagsverhouding);

        classUnderTest.perform(gezagsBepaling);

        verify(arAntwoordenModel).setV0301(V3_1_NEE);
    }

    @Test
    void isErSprakeVanEenRecenteGebeurtenisAdoptieNaIngangsDatum() {
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(gezagsverhouding.getIndicatieGezagMinderjarige()).thenReturn(INDICATIE_ONE_AND_TWO);
        when(gezagsverhouding.getIngangsdatumGeldigheidGezag()).thenReturn(DATE_FIRST_DAY_2021);
        when(gezagsverhouding.hasIngangsdatumGeldigheidGezag()).thenReturn(true);
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_FIRST_DAY_2022);
        persoonslijst.setOuder1(ouder1);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_FIRST_DAY_2022);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_ADOPTIE);
        persoonslijst.setPersoon(persoon);
        persoonslijst.setGezagsverhouding(gezagsverhouding);

        classUnderTest.perform(gezagsBepaling);

        verify(arAntwoordenModel).setV0301(V3_1_JA);
    }
}