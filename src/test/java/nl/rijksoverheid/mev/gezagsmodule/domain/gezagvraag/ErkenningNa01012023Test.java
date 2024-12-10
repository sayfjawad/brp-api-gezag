package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.gezagsmodule.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ErkenningNa01012023Test {

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

    private static final String V2A_3_VOOR = "Voor";
    private static final String V2A_3_VOOR_OUDER1 = "Voor_ouder1";
    private static final String V2A_3_VOOR_OUDER2 = "Voor_ouder2";
    private static final String V2A_3_NA = "Na";
    private static final String PRECONDITION_INDICATION_MISSING_PARENTS = "Preconditie: vraag 2a.3 - Geen twee ouders bij erkenning";
    private static final String PRECONDITION_INDICATION_UNKNOWN_MOTHER = "Preconditie: vraag 2a.3 - Geboortemoeder niet te bepalen";
    private static final String AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE = "1AB0109";
    private static final String AKTE_AANDUIDING_GEBOORTE = "1AA0109";
    private static final String MISSING_DATUM_INGANG_OUDER_1 = "datum ingang familie betrekking van ouder 1";
    private static final String MISSING_DATUM_INGANG_OUDER_2 = "datum ingang familie betrekking van ouder 2";
    private static final String DATE_BEFORE_01012023 = "20221201";
    private static final String DATE_ON_01012023 = "20230101";
    private static final String DATE_AFTER_01012023 = "20230102";

    @BeforeEach
    public void setup() {
        persoonslijst = new Persoonslijst();
        persoonslijst.setPersoon(persoon);
        when(gezagsBepaling.getPlPersoon()).thenReturn(persoonslijst);
        classUnderTest = new ErkenningNa01012023(gezagsBepaling);
    }

    @Test
    void erkenningNa01012023WithoutValuesFailingPrecondition() {
        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains(PRECONDITION_INDICATION_MISSING_PARENTS));
    }

    @Test
    void erkenningNa01012023OnlyHavingOneParentAsOuder1FailingPrecondition() {
        persoonslijst.setOuder1(ouder1);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains(PRECONDITION_INDICATION_MISSING_PARENTS));
    }

    @Test
    void erkenningNa01012023OnlyHavingOneParentAsOuder2FailingPrecondition() {
        persoonslijst.setOuder2(ouder2);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains(PRECONDITION_INDICATION_MISSING_PARENTS));
    }

    @Test
    void erkenningNa01012023HavingBothParentsAndNoAktenummer() {
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);

        AfleidingsregelException exception = assertThrows(AfleidingsregelException.class, () -> classUnderTest.perform());

        assertNotNull(exception);
        assertTrue(exception.getMessage().contains(PRECONDITION_INDICATION_UNKNOWN_MOTHER));
    }

    @Test
    void erkenningNa01012023HavingBothParentsNotHavingDatumIngangAndAktenummerIndicatingErkenning() {
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);

        classUnderTest.perform();

        verify(gezagsBepaling).addMissendeGegegevens(MISSING_DATUM_INGANG_OUDER_1);
    }

    @Test
    void erkenningNa01012023HavingOuder2NotHavingDatumIngangAndAktenummerIndicatingErkenning() {
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);

        classUnderTest.perform();

        verify(gezagsBepaling).addMissendeGegegevens(MISSING_DATUM_INGANG_OUDER_2);
    }

    @Test
    void erkenningNa01012023HavingOuderErkendAfterDate() {
        String expected = V2A_3_NA;
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_AFTER_01012023);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_AFTER_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV02A03(expected);
    }

    @Test
    void erkenningNa01012023HavingOuderErkendOnDate() {
        String expected = V2A_3_NA;
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_ON_01012023);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_ON_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV02A03(expected);
    }

    @Test
    void erkenningNa01012023HavingOuder1ErkendBeforeDate() {
        String expected = V2A_3_VOOR_OUDER2;
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        when(ouder1.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV02A03(expected);
    }

    @Test
    void erkenningNa01012023HavingOuder2ErkendBeforeDate() {
        String expected = V2A_3_VOOR_OUDER1;
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        when(ouder2.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV02A03(expected);
    }

    @Test
    void erkenningNa01012023HavingBeenBornBefore() {
        String expected = V2A_3_VOOR;
        when(gezagsBepaling.getArAntwoordenModel()).thenReturn(arAntwoordenModel);
        when(ouder1.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        when(ouder2.getDatumIngangFamiliebetrekking()).thenReturn(DATE_BEFORE_01012023);
        persoonslijst.setOuder1(ouder1);
        persoonslijst.setOuder2(ouder2);
        when(persoon.getAktenummer()).thenReturn(AKTE_AANDUIDING_GEBOORTE);
        when(persoon.getGeboortedatum()).thenReturn(DATE_BEFORE_01012023);

        classUnderTest.perform();

        verify(arAntwoordenModel).setV02A03(expected);
    }
}