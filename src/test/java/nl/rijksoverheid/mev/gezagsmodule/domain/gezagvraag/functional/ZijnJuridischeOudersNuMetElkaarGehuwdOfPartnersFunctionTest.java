package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder1;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder2;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.ZijnJuridischeOudersNuMetElkaarGehuwdOfPartners;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * TODO: verdere testen schrijven
 */
@ExtendWith(MockitoExtension.class)
class ZijnJuridischeOudersNuMetElkaarGehuwdOfPartnersFunctionTest {

    @Mock
    private GezagsBepaling gezagsBepaling;

    @Mock
    private Persoon persoon;
    @Mock
    private Ouder1 ouder1;
    @Mock
    private Ouder2 ouder2;
    @Mock
    private ARAntwoordenModel arAntwoordenModel;

    private ZijnJuridischeOudersNuMetElkaarGehuwdOfPartnersFunction classUnderTest;

    private Persoonslijst persoonslijst;
    @Mock
    private Persoonslijst persoonslijstOuder1;
    @Mock
    private Persoonslijst persoonslijstOuder2;

    private static final String INDICATION_EXCEPTION_TWO_PARENTS = "Preconditie: Kind moet twee ouders hebben";
    private static final String INDICATION_EXCEPTION_PARENT_ONE_NOT_REGISTERED = "Preconditie: ouder1 moet in BRP geregistreerd staan";
    private static final String INDICATION_EXCEPTION_PARENT_TWO_NOT_REGISTERED = "Preconditie: ouder2 moet in BRP geregistreerd staan";
    private static final String GESLACHTSNAAM = "mock";
    private static final String V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR = "Nee_nooit";

    @BeforeEach
    public void setup() {
        persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        classUnderTest = new ZijnJuridischeOudersNuMetElkaarGehuwdOfPartnersFunction();
    }

    @Test
    void zijnJuridischeOudersNuMetElkaarGehuwdOfPartnersWithoutValues() {
        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform(gezagsBepaling));

        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_TWO_PARENTS));
    }

    @Test
    void zijnJuridischeOudersNuMetElkaarGehuwdOfPartnersWithOneEmptyParent() {
        persoonslijst.setOuder1(ouder1);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform(gezagsBepaling));

        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_TWO_PARENTS));
    }

    @Test
    void zijnJuridischeOudersNuMetElkaarGehuwdOfPartnersWithTwoEmptyParent() {
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform(gezagsBepaling));

        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_TWO_PARENTS));
    }

    @Test
    void zijnJuridischeOudersNuMetElkaarGehuwdOfPartnersWithParentsHavingGeslachtsnaam() {
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform(gezagsBepaling));
        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_PARENT_ONE_NOT_REGISTERED));
    }

    @Test
    void zijnJuridischeOudersNuMetElkaarGehuwdOfPartnersWithOneParent() {
        when(ouder1.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        when(ouder2.getGeslachtsnaam()).thenReturn(GESLACHTSNAAM);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoonslijstOuder1.isNietIngeschrevenInRNI()).thenReturn(true);
        when(persoonslijstOuder1.isNietGeemigreerd()).thenReturn(true);
        when(gezagsBepaling.getPlOuder1()).thenReturn(persoonslijstOuder1);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform(gezagsBepaling));
        assertTrue(exception.getMessage().contains(INDICATION_EXCEPTION_PARENT_TWO_NOT_REGISTERED));
    }

    @Test
    void zijnJuridischeOudersNuMetElkaarGehuwdOfPartnersBothParentsRegistred() {
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

        classUnderTest.perform(gezagsBepaling);

        verify(arAntwoordenModel).setV02A01(V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR);
    }
}


