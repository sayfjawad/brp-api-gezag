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
}