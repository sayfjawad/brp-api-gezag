package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.brp.brpv.generated.tables.records.Lo3PlPersoonRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersoonTest {

    /**
     * Temporary test as domain objects should refactor the type of {@code bsn} to long. After the refactor, a similar
     * test should be present to test burgerservicenummers during the transformation of domain objects to OpenAPI DTOs.
     * <p>
     * As it's temporary, the test is only written for one domain object.
     *
     * @param input burgerservicenummers as provided by the database
     */
    @DisplayName("the length of burgerservicenummers should always be 9")
    @ParameterizedTest
    @ValueSource(longs = {0, 1, 22, 333, 4444, 55555, 666666, 7777777, 88888888, 999999999})
    void getBsn(long input) {
        var plPersoonRecord = new Lo3PlPersoonRecord();
        plPersoonRecord.setBurgerServiceNr(input);

        var subject = new Persoon(plPersoonRecord);
        var result = subject.getBurgerservicenummer();

        assertEquals(9, result.length());
    }
}
