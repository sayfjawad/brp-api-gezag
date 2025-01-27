package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ErkenningNa01012023Test {

    private static final String V2A_3_VOOR = "Voor";
    private static final String V2A_3_VOOR_OUDER1 = "Voor_ouder1";
    private static final String V2A_3_VOOR_OUDER2 = "Voor_ouder2";
    private static final String V2A_3_NA = "Na";
    private static final String PRECONDITION_INDICATION_MISSING_PARENTS = "Preconditie: vraag 2a.3 - Geen twee ouders bij erkenning";
    private static final String PRECONDITION_INDICATION_UNKNOWN_MOTHER = "Preconditie: vraag 2a.3 - Geboortemoeder niet te bepalen";
    private static final String AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE = "1AB0109";
    private static final String AKTE_AANDUIDING_GEBOORTE = "1AA0109";
    private static final String MISSING_DATUM_INGANG_OUDER_1 = "datum ingang familiebetrekking van ouder 1";
    private static final String MISSING_DATUM_INGANG_OUDER_2 = "datum ingang familiebetrekking van ouder 2";
    private static final String DATE_BEFORE_01012023 = "20221201";
    private static final String DATE_ON_01012023 = "20230101";
    private static final String DATE_AFTER_01012023 = "20230102";
    private static final String GESLACHT_AANDUIDING_UNKNOWN = "O";
    private static final String GESLACHT_AANDUIDING_WOMAN = "V";
    private static final String GESLACHT_AANDUIDING_MAN = "M";
    private static final String GESLACHTSNAAM_ONE = "mock";
    private static final String GESLACHTSNAAM_TWO = "mock2";
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
    private Persoonslijst persoonslijst;
    private ErkenningNa01012023 classUnderTest;

    @BeforeEach
    public void setup() {
        // create a real ARAntwoordenModel or mock one
        persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);

        classUnderTest = new ErkenningNa01012023();
    }


    @Test
    void erkenningNa01012023WithoutValuesFailingPrecondition() {

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class,
                () -> classUnderTest.perform(gezagsBepaling));
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains(PRECONDITION_INDICATION_MISSING_PARENTS));
    }

    @Test
    void erkenningNa01012023OnlyHavingOneParentAsOuder1FailingPrecondition() {

        persoonslijst.setOuder1(ouder1);
        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class,
                () -> classUnderTest.perform(gezagsBepaling));
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains(PRECONDITION_INDICATION_MISSING_PARENTS));
    }

    @Test
    void erkenningNa01012023OnlyHavingOneParentAsOuder2FailingPrecondition() {

        persoonslijst.setOuder2(ouder2);
        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class,
                () -> classUnderTest.perform(gezagsBepaling));
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains(PRECONDITION_INDICATION_MISSING_PARENTS));
    }

    @Test
    void erkenningNa01012023HavingBothParentsAndNoAktenummer() {

        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class,
                () -> classUnderTest.perform(gezagsBepaling));
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains(PRECONDITION_INDICATION_UNKNOWN_MOTHER));
    }

    @Test
    void erkenningNa01012023HavingBothParentsNotHavingDatumIngangAndAktenummerIndicatingErkenning() {

        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);
        classUnderTest.perform(gezagsBepaling);
        verify(gezagsBepaling).addMissendeGegegevens(MISSING_DATUM_INGANG_OUDER_1);
    }

    @Test
    void erkenningNa01012023HavingOuder2NotHavingDatumIngangAndAktenummerIndicatingErkenning() {

        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);
        classUnderTest.perform(gezagsBepaling);
        verify(gezagsBepaling).addMissendeGegegevens(MISSING_DATUM_INGANG_OUDER_2);
    }

    @Test
    void erkenningNa01012023HavingOuderErkendAfterDate() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_AFTER_01012023);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_AFTER_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02A03(V2A_3_NA);
    }

    @Test
    void erkenningNa01012023HavingOuderErkendOnDate() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_ON_01012023);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_ON_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02A03(V2A_3_NA);
    }

    @Test
    void erkenningNa01012023HavingOuder1ErkendBeforeDate() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        when(ouder1.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02A03(V2A_3_VOOR_OUDER2);
    }

    @Test
    void erkenningNa01012023HavingOuder2ErkendBeforeDate() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        when(ouder2.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02A03(V2A_3_VOOR_OUDER1);
    }

    @Test
    void erkenningNa01012023HavingBeenBornBefore() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_AANDUIDING_GEBOORTE);
        when(persoon.getGeboortedatum()).thenReturn(DATE_BEFORE_01012023);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02A03(V2A_3_VOOR);
    }

    @Test
    void erkenningNa01012023HavingBeenBornBeforeAndParentsNotHavingGeslachtsAand() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getGeslachtsAanduiding()).thenReturn(null);
        when(ouder2.getGeslachtsAanduiding()).thenReturn(null);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_AANDUIDING_GEBOORTE);
        when(persoon.getGeboortedatum()).thenReturn(DATE_BEFORE_01012023);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02A03(V2A_3_VOOR);
    }

    @Test
    void erkenningNa01012023HavingBeenBornBeforeAndParentsHavingEmptyGeslachtsAand() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getGeslachtsAanduiding()).thenReturn("");
        when(ouder2.getGeslachtsAanduiding()).thenReturn("");
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_AANDUIDING_GEBOORTE);
        when(persoon.getGeboortedatum()).thenReturn(DATE_BEFORE_01012023);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02A03(V2A_3_VOOR);
    }

    @Test
    void erkenningNa01012023HavingBeenBornBeforeAndParentsHavingUnknownGeslachtsAand() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getGeslachtsAanduiding()).thenReturn(GESLACHT_AANDUIDING_UNKNOWN);
        when(ouder2.getGeslachtsAanduiding()).thenReturn(GESLACHT_AANDUIDING_UNKNOWN);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_AANDUIDING_GEBOORTE);
        when(persoon.getGeboortedatum()).thenReturn(DATE_BEFORE_01012023);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02A03(V2A_3_VOOR);
    }

    @Test
    void erkenningNa01012023HavingBeenBornBeforeAndOneParentHavingWomanGeslachtsAand() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getGeslachtsAanduiding()).thenReturn(GESLACHT_AANDUIDING_WOMAN);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_AANDUIDING_GEBOORTE);
        when(persoon.getGeboortedatum()).thenReturn(DATE_BEFORE_01012023);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02A03(V2A_3_VOOR_OUDER1);
    }

    @Test
    void erkenningNa01012023HavingBeenBornBeforeAndSecondParentHavingWomanGeslachtsAand() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder2.getGeslachtsAanduiding()).thenReturn(GESLACHT_AANDUIDING_WOMAN);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_AANDUIDING_GEBOORTE);
        when(persoon.getGeboortedatum()).thenReturn(DATE_BEFORE_01012023);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02A03(V2A_3_VOOR_OUDER2);
    }

    @Test
    void erkenningNa01012023HavingBeenBornBeforeAndBothParentHavingManGeslachtsAand() {

        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getGeslachtsAanduiding()).thenReturn(GESLACHT_AANDUIDING_MAN);
        when(ouder2.getGeslachtsAanduiding()).thenReturn(GESLACHT_AANDUIDING_MAN);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_AANDUIDING_GEBOORTE);
        when(persoon.getGeboortedatum()).thenReturn(DATE_BEFORE_01012023);
        classUnderTest.perform(gezagsBepaling);
        verify(arAntwoordenModel).setV02A03(V2A_3_VOOR);
    }
}