package nl.rijksoverheid.mev.gezagsmodule.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PersoonslijstTest {
    @Mock
    private Inschrijving inschrijving;

    private Persoonslijst classUnderTest;

    private static final String DATE_FIRST_DAY_2021 = "20210101";
    private static final String OPSCHORT_REDEN_EMIGRATION = "E";
    private static final String OPSCHORT_REDEN_OVERLIJDEN = "O";

    @BeforeEach
    public void setup() {
        classUnderTest = new Persoonslijst();
    }

    @Test
    void isOverledenWithoutValues() {
        boolean expected = false;

        boolean result = classUnderTest.isOverleden();

        assertEquals(expected, result);
    }

    @Test
    void isOverledenWithEmptyInschrijving() {
        boolean expected = false;
        classUnderTest.setInschrijving(inschrijving);

        boolean result = classUnderTest.isOverleden();

        assertEquals(expected, result);
    }

    @Test
    void isOverledenWithInschrijvingHavingDatumOpschortingButNoOpschortReden() {
        boolean expected = false;
        when(inschrijving.getDatumOpschortingBijhouding()).thenReturn(DATE_FIRST_DAY_2021);
        classUnderTest.setInschrijving(inschrijving);

        boolean result = classUnderTest.isOverleden();

        assertEquals(expected, result);
    }

    @Test
    void isOverledenWithInschrijvingHavingDatumOpschortingAndOpschortRedenNotO() {
        boolean expected = false;
        when(inschrijving.getDatumOpschortingBijhouding()).thenReturn(DATE_FIRST_DAY_2021);
        when(inschrijving.getRedenOpschortingBijhouding()).thenReturn(OPSCHORT_REDEN_EMIGRATION);
        classUnderTest.setInschrijving(inschrijving);

        boolean result = classUnderTest.isOverleden();

        assertEquals(expected, result);
    }

    @Test
    void isOverledenWithInschrijvingHavingDatumOpschortingAndOpschortRedenO() {
        boolean expected = true;
        when(inschrijving.getDatumOpschortingBijhouding()).thenReturn(DATE_FIRST_DAY_2021);
        when(inschrijving.getRedenOpschortingBijhouding()).thenReturn(OPSCHORT_REDEN_OVERLIJDEN);
        classUnderTest.setInschrijving(inschrijving);

        boolean result = classUnderTest.isOverleden();

        assertEquals(expected, result);
    }
}