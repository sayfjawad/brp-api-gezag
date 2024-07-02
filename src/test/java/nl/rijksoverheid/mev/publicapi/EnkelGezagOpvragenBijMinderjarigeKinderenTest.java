package nl.rijksoverheid.mev.publicapi;

import java.time.Clock;
import nl.rijksoverheid.mev.gezagsmodule.domain.Kind;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.brpadapter.soap.BrpClient;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagService;
import nl.rijksoverheid.mev.gmapi.BevoegdheidTotGezagService;
import nl.rijksoverheid.mev.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.openapitools.model.GezagRequest;
import org.openapitools.model.Gezagsrelatie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie.KIND;
import static nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie.PERSOON;
import static nl.rijksoverheid.mev.gezagsmodule.domain.Persoon.BSN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class EnkelGezagOpvragenBijMinderjarigeKinderenTest {

    static final String BSN_MEERDERJARIGE_OUDER = "777777770";
    static final String BSN_MINDERJARIG_KIND = "888888880";
    static final String BSN_MEERDERJARIG_KIND = "999999990";

    @Autowired
    BevoegdheidTotGezagService bevoegdheidTotGezagService;

    @Autowired
    Clock clock;

    @MockBean
    GezagService gezagService;

    @MockBean
    BrpClient brpClient;

    @Mock
    private Transaction transaction;

    private String getGeboortedatumVoorLeeftijd(int leeftijdInJaren) {
        return String.valueOf(Integer.parseInt(LocalDate.now(clock).format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                - (leeftijdInJaren * 10000));
    }

    @BeforeEach
    void setup() {
        geenGezaghebbendenVanMeerderjarigeOuderEnKind();
        meerderjarigeOuderAlsGezaghebbendeVanMinderjarigKind();
    }

    private void geenGezaghebbendenVanMeerderjarigeOuderEnKind() {
        when(gezagService.getGezag(BSN_MEERDERJARIGE_OUDER, transaction)).thenReturn(Collections.emptyList());
        when(gezagService.getGezag(BSN_MEERDERJARIG_KIND, transaction)).thenReturn(Collections.emptyList());
    }

    private void meerderjarigeOuderAlsGezaghebbendeVanMinderjarigKind() {
        var gezagsrelatieMinderjarigKindMetOuder = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(
                BSN_MINDERJARIG_KIND, "OG1", BSN_MEERDERJARIGE_OUDER
        );
        var gezagsrelatiesMinderjarigKind = List.of(gezagsrelatieMinderjarigKindMetOuder);
        when(gezagService.getGezag(BSN_MINDERJARIG_KIND, transaction)).thenReturn(gezagsrelatiesMinderjarigKind);
    }

    @Test
    void onlyRequestsGezagForMeerderjarigeKinderen_meerderjarigenEndpoint() throws GezagException {
        setupMeerderjarigeAlsOuderVanMinderjarigEnMeerderjarigKind();

        GezagRequest gezagRequest = new GezagRequest()
                .bsn(BSN_MEERDERJARIGE_OUDER);
        List<Gezagsrelatie> returnedGezagsrelaties = bevoegdheidTotGezagService.bepaalBevoegdheidTotGezagMeerderjarige(
                gezagRequest,
                transaction
        );

        var gezagsrelatieMinderjarigKindMetOuder = new Gezagsrelatie(BSN_MINDERJARIG_KIND, Gezagsrelatie.SoortGezagEnum.OG1)
                .bsnMeerderjarige(BSN_MEERDERJARIGE_OUDER);
        assertThat(returnedGezagsrelaties).containsExactlyInAnyOrder(gezagsrelatieMinderjarigKindMetOuder);
        // Gezag should be queried once, for minderjarig kind only
        verify(gezagService).getGezag(anyString(), eq(transaction));
    }

    @Test
    void onlyRequestsGezagForMeerderjarigeKinderen_meerEnMinderjarigenEndpoint() throws GezagException {
        setupMeerderjarigeAlsOuderVanMinderjarigEnMeerderjarigKind();

        GezagRequest gezagRequest = new GezagRequest()
                .bsn(BSN_MEERDERJARIGE_OUDER);
        List<Gezagsrelatie> returnedGezagsrelaties = bevoegdheidTotGezagService.bepaalBevoegdheidTotGezag(
                gezagRequest,
                transaction
        );

        var gezagsrelatieMinderjarigKindMetOuder = new Gezagsrelatie(BSN_MINDERJARIG_KIND, Gezagsrelatie.SoortGezagEnum.OG1)
                .bsnMeerderjarige(BSN_MEERDERJARIGE_OUDER);
        assertThat(returnedGezagsrelaties).containsExactlyInAnyOrder(gezagsrelatieMinderjarigKindMetOuder);
        // Gezag should be queried twice: once for subject (which might be minderjarig), once for minderjarig kind
        verify(gezagService, times(2)).getGezag(anyString(), eq(transaction));
    }

    private void setupMeerderjarigeAlsOuderVanMinderjarigEnMeerderjarigKind() throws GezagException {
        Map<String, String> persoonValues = new HashMap<>();
        persoonValues.put(Persoon.BSN, BSN_MEERDERJARIGE_OUDER);
        Persoon ouder = new Persoon(persoonValues, clock);

        Map<String, String> minderjarigValues = new HashMap<>();
        minderjarigValues.put(Kind.BSN, BSN_MINDERJARIG_KIND);
        minderjarigValues.put(Kind.GEBOORTEDATUM, getGeboortedatumVoorLeeftijd(17));
        Kind minderjarigKind = new Kind(minderjarigValues, clock);

        Map<String, String> meerjarigValues = new HashMap<>();
        meerjarigValues.put(Kind.BSN, BSN_MEERDERJARIG_KIND);
        meerjarigValues.put(Kind.GEBOORTEDATUM, getGeboortedatumVoorLeeftijd(18));
        Kind meerderjarigKind = new Kind(meerjarigValues, clock);

        var persoonslijstOuder = new Persoonslijst(clock);
        persoonslijstOuder.addVeld(PERSOON, ouder);
        persoonslijstOuder.addVeldToList(KIND, minderjarigKind);
        persoonslijstOuder.addVeldToList(KIND, meerderjarigKind);

        when(brpClient.opvragenPersoonslijst(BSN_MEERDERJARIGE_OUDER, transaction)).thenReturn(persoonslijstOuder);
    }

    /**
     * A child can have no BSN for example in case of a dead born baby.
     */
    @Test
    void noGezagsrelatiesSinceBrpAdapterReturnsNoKinderenWithoutBsn_meerderjarigenEndpoint() throws GezagException {
        setupMeerderjarigeAlsOuderVanMinderjarigKindZonderBsn();

        GezagRequest gezagRequest = new GezagRequest()
                .bsn(BSN_MEERDERJARIGE_OUDER);
        List<Gezagsrelatie> returnedGezagsrelaties = bevoegdheidTotGezagService.bepaalBevoegdheidTotGezagMeerderjarige(
                gezagRequest,
                transaction
        );

        assertThat(returnedGezagsrelaties).isEmpty();
        verify(gezagService, times(0)).getGezag(anyString(), eq(transaction));
    }

    @Test
    void noGezagsrelatiesSinceBrpAdapterReturnsNoKinderenWithoutBsn_meerEnMinderjarigenEndpoint() throws GezagException {
        setupMeerderjarigeAlsOuderVanMinderjarigKindZonderBsn();

        GezagRequest gezagRequest = new GezagRequest()
                .bsn(BSN_MEERDERJARIGE_OUDER);
        List<Gezagsrelatie> returnedGezagsrelaties = bevoegdheidTotGezagService.bepaalBevoegdheidTotGezag(
                gezagRequest,
                transaction
        );

        assertThat(returnedGezagsrelaties).isEmpty();
        verify(gezagService, times(1)).getGezag(anyString(), eq(transaction));
    }

    private void setupMeerderjarigeAlsOuderVanMinderjarigKindZonderBsn() throws GezagException {
        Map<String, String> values = new HashMap<>();
        values.put(Persoon.BSN, BSN_MEERDERJARIGE_OUDER);
        var ouder = new Persoon(values, clock);

        Map<String, String> valuesKind = new HashMap<>();
        values.put(Kind.GEBOORTEDATUM, getGeboortedatumVoorLeeftijd(12));
        var kindZonderBsn = new Kind(valuesKind, clock);

        var persoonslijstOuder = new Persoonslijst(clock);
        persoonslijstOuder.addVeld(PERSOON, ouder);
        persoonslijstOuder.addVeldToList(KIND, kindZonderBsn);
        when(brpClient.opvragenPersoonslijst(BSN_MEERDERJARIGE_OUDER, transaction)).thenReturn(persoonslijstOuder);
    }

    /**
     * This behaviour was unspecified by the requirements in #324, but copied
     * from a previous implementation in the Gm-API
     * GezagController.isGeboortedatumVanMinderjarige().
     *
     * Even though everything is set up for this child as minderjarig kind,
     * without geboortedatum it should be treated as a meerderjarig kind.
     */
    @Test
    void noGezagsrelatiesSinceKindWithoutGeboortedatumIsTreatedAsMeerderjarige_meerderjarigenEndpoint() throws GezagException {
        setupMeerderjarigeAlsOuderVanKindZonderGeboortedatum();

        GezagRequest gezagRequest = new GezagRequest()
                .bsn(BSN_MEERDERJARIGE_OUDER);
        List<Gezagsrelatie> returnedGezagsrelaties = bevoegdheidTotGezagService.bepaalBevoegdheidTotGezagMeerderjarige(
                gezagRequest,
                transaction
        );

        assertThat(returnedGezagsrelaties).isEmpty();
        verify(gezagService, times(0)).getGezag(anyString(), eq(transaction));
    }

    @Test
    void noGezagsrelatiesSinceKindWithoutGeboortedatumIsTreatedAsMeerderjarige_meerEnMinderjarigenEndpoint() throws GezagException {
        setupMeerderjarigeAlsOuderVanKindZonderGeboortedatum();

        GezagRequest gezagRequest = new GezagRequest()
                .bsn(BSN_MEERDERJARIGE_OUDER);
        List<Gezagsrelatie> returnedGezagsrelaties = bevoegdheidTotGezagService.bepaalBevoegdheidTotGezag(
                gezagRequest,
                transaction
        );

        assertThat(returnedGezagsrelaties).isEmpty();
        verify(gezagService, times(1)).getGezag(anyString(), eq(transaction));
    }

    private void setupMeerderjarigeAlsOuderVanKindZonderGeboortedatum() throws GezagException {
        Map<String, String> values = new HashMap<>();
        values.put(BSN, BSN_MEERDERJARIGE_OUDER);
        var ouder = new Persoon(values, clock);

        Map<String, String> valuesKind = new HashMap<>();
        valuesKind.put(BSN, BSN_MINDERJARIG_KIND);
        var kindZonderGeboortedatum = new Kind(valuesKind, clock);

        var persoonslijstOuder = new Persoonslijst(clock);
        persoonslijstOuder.addVeld(PERSOON, ouder);
        persoonslijstOuder.addVeldToList(KIND, kindZonderGeboortedatum);
        when(brpClient.opvragenPersoonslijst(BSN_MEERDERJARIGE_OUDER, transaction)).thenReturn(persoonslijstOuder);
    }

    @Test
    void noGezagsrelatiesSinceNoKinderen_meerderjarigenEndpoint() throws GezagException {
        setupMeerderjarigeZonderKinderen();

        GezagRequest gezagRequest = new GezagRequest()
                .bsn(BSN_MEERDERJARIGE_OUDER);
        List<Gezagsrelatie> returnedGezagsrelaties = bevoegdheidTotGezagService.bepaalBevoegdheidTotGezagMeerderjarige(
                gezagRequest,
                transaction
        );

        assertThat(returnedGezagsrelaties).isEmpty();
        verify(gezagService, times(0)).getGezag(anyString(), eq(transaction));
    }

    @Test
    void noGezagsrelatiesSinceNoKinderen_meerEnMinderjarigenEndpoint() throws GezagException {
        setupMeerderjarigeZonderKinderen();

        GezagRequest gezagRequest = new GezagRequest()
                .bsn(BSN_MEERDERJARIGE_OUDER);
        List<Gezagsrelatie> returnedGezagsrelaties = bevoegdheidTotGezagService.bepaalBevoegdheidTotGezag(
                gezagRequest,
                transaction
        );

        assertThat(returnedGezagsrelaties).isEmpty();
        verify(gezagService, times(1)).getGezag(anyString(), eq(transaction));
    }

    private void setupMeerderjarigeZonderKinderen() throws GezagException {
        Map<String, String> ouderValues = new HashMap<>();
        ouderValues.put(BSN, BSN_MEERDERJARIGE_OUDER);
        var ouder = new Persoon(ouderValues, clock);

        var persoonslijstOuder = new Persoonslijst(clock);
        persoonslijstOuder.addVeld(PERSOON, ouder);
        when(brpClient.opvragenPersoonslijst(BSN_MEERDERJARIGE_OUDER, transaction)).thenReturn(persoonslijstOuder);
    }
}
