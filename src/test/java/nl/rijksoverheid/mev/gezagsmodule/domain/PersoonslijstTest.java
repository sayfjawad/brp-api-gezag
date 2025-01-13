package nl.rijksoverheid.mev.gezagsmodule.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersoonslijstTest {

    @Mock
    private Inschrijving inschrijving;

    @Mock
    private Persoon persoon;

    @Mock
    private Ouder1 ouder1;

    @Mock
    private Ouder2 ouder2;

    private Persoonslijst classUnderTest;

    private static final String DATE_FIRST_DAY_2021 = "20210101";
    private static final String OPSCHORT_REDEN_EMIGRATION = "E";
    private static final String OPSCHORT_REDEN_OVERLIJDEN = "O";
    private static  final String AKTE_ERKENNING = "1AC0109";
    private static  final String AKTE_ONTKENNING = "1AE0109";
    private static  final String AKTE_ONGEBOREN_VRUCHT = "1AA0109";
    private static  final String AKTE_ADOPTIE = "1AQ0109";

    @BeforeEach
    public void setup() {
        classUnderTest = new Persoonslijst();
    }

    @Test
    void isOverledenWithoutValues() {
        boolean result = classUnderTest.isOverleden();

        assertFalse(result);
    }

    @Test
    void isOverledenWithEmptyInschrijving() {
        classUnderTest.setInschrijving(inschrijving);

        boolean result = classUnderTest.isOverleden();

        assertFalse(result);
    }

    @Test
    void isOverledenWithInschrijvingHavingDatumOpschortingButNoOpschortReden() {
        when(inschrijving.getDatumOpschortingBijhouding()).thenReturn(DATE_FIRST_DAY_2021);
        classUnderTest.setInschrijving(inschrijving);

        boolean result = classUnderTest.isOverleden();

        assertFalse(result);
    }

    @Test
    void isOverledenWithInschrijvingHavingDatumOpschortingAndOpschortRedenNotO() {
        when(inschrijving.getDatumOpschortingBijhouding()).thenReturn(DATE_FIRST_DAY_2021);
        when(inschrijving.getRedenOpschortingBijhouding()).thenReturn(OPSCHORT_REDEN_EMIGRATION);
        classUnderTest.setInschrijving(inschrijving);

        boolean result = classUnderTest.isOverleden();

        assertFalse(result);
    }

    @Test
    void isOverledenWithInschrijvingHavingDatumOpschortingAndOpschortRedenO() {
        when(inschrijving.getDatumOpschortingBijhouding()).thenReturn(DATE_FIRST_DAY_2021);
        when(inschrijving.getRedenOpschortingBijhouding()).thenReturn(OPSCHORT_REDEN_OVERLIJDEN);
        classUnderTest.setInschrijving(inschrijving);

        boolean result = classUnderTest.isOverleden();

        assertTrue(result);
    }

    @Test
    void ongeborenVruchtErkendOfGerechtelijkeVaststellingWithoutValues() {
        boolean result = classUnderTest.ongeborenVruchtErkendOfGerechtelijkeVaststelling();

        assertFalse(result);
    }

    @Test
    void ongeborenVruchtErkendOfGerechtelijkeVaststellingWithAktenummer() {
        when(persoon.getAktenummer()).thenReturn(AKTE_ERKENNING);
        classUnderTest.setPersoon(persoon);

        boolean result = classUnderTest.ongeborenVruchtErkendOfGerechtelijkeVaststelling();

        assertTrue(result);
    }

    @Test
    void ongeborenVruchtDoorOuder1ErkendOfGerechtelijkeVaststellingWithoutValues() {
        boolean result = classUnderTest.ongeborenVruchtDoorOuder1ErkendOfGerechtelijkeVaststelling();

        assertFalse(result);
    }

    @Test
    void ongeborenVruchtDoorOuder1ErkendOfGerechtelijkeVaststellingWithAktenummer() {
        when(ouder1.getAktenummer()).thenReturn(AKTE_ERKENNING);
        classUnderTest.setOuder1(ouder1);

        boolean result = classUnderTest.ongeborenVruchtDoorOuder1ErkendOfGerechtelijkeVaststelling();

        assertTrue(result);
    }

    @Test
    void ongeborenVruchtDoorOuder2ErkendOfGerechtelijkeVaststellingWithoutValues() {
        boolean result = classUnderTest.ongeborenVruchtDoorOuder2ErkendOfGerechtelijkeVaststelling();

        assertFalse(result);
    }

    @Test
    void ongeborenVruchtDoorOuder2ErkendOfGerechtelijkeVaststellingWithAktenummer() {
        when(ouder2.getAktenummer()).thenReturn(AKTE_ERKENNING);
        classUnderTest.setOuder2(ouder2);

        boolean result = classUnderTest.ongeborenVruchtDoorOuder2ErkendOfGerechtelijkeVaststelling();

        assertTrue(result);
    }

    @Test
    void ontkenningOuderschapDoorOuder1WithoutValues() {
        boolean result = classUnderTest.ontkenningOuderschapDoorOuder1();

        assertFalse(result);
    }

    @Test
    void ontkenningOuderschapDoorOuder1WithAktenummer() {
        when(ouder1.getAktenummer()).thenReturn(AKTE_ONTKENNING);
        classUnderTest.setOuder1(ouder1);

        boolean result = classUnderTest.ontkenningOuderschapDoorOuder1();

        assertTrue(result);
    }

    @Test
    void ontkenningOuderschapDoorOuder2WithoutValues() {
        boolean result = classUnderTest.ontkenningOuderschapDoorOuder2();

        assertFalse(result);
    }

    @Test
    void ontkenningOuderschapDoorOuder2WithAktenummer() {
        when(ouder2.getAktenummer()).thenReturn(AKTE_ONTKENNING);
        classUnderTest.setOuder2(ouder2);

        boolean result = classUnderTest.ontkenningOuderschapDoorOuder2();

        assertTrue(result);
    }

    @Test
    void ongeborenVruchtErkendWithoutValues() {
        boolean result = classUnderTest.ongeborenVruchtErkend();

        assertFalse(result);
    }

    @Test
    void ongeborenVruchtErkendWithAktenummer() {
        when(persoon.getAktenummer()).thenReturn(AKTE_ONGEBOREN_VRUCHT);
        classUnderTest.setPersoon(persoon);

        boolean result = classUnderTest.ongeborenVruchtErkend();

        assertTrue(result);
    }

    @Test
    void geadopteerdMetNlAkteWithoutValues() {
        boolean result = classUnderTest.geadopteerdMetNlAkte();

        assertFalse(result);
    }

    @Test
    void geadopteerdMetNlAkteWithAktenummer() {
        when(persoon.getAktenummer()).thenReturn(AKTE_ADOPTIE);
        classUnderTest.setPersoon(persoon);

        boolean result = classUnderTest.geadopteerdMetNlAkte();

        assertTrue(result);
    }
}