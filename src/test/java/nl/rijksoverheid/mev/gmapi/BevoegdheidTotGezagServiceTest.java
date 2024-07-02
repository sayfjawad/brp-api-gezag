package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.brpadapter.service.BrpService;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagService;
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

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class BevoegdheidTotGezagServiceTest {

    static final String BSN_KIND_1 = "111111110"; // Kind #1
    static final String BSN_KIND_2 = "222222220"; // Kind #2

    // Minderjarige ouder #1 van Kind #1 en #2, zonder gezag over kinderen
    static final String BSN_MINDERJARIGE_OUDER = "333333330";

    // Meerderjarige ouder #2 van Kind #1 en #2, met gezag over kinderen
    static final String BSN_MEERDERJARIGE_OUDER = "444444440";

    // Meerderjarige ouder met gezag over Minderjarige ouder en diens Kind #1
    static final String BSN_OUDER_VAN_MINDERJARIGE_OUDER = "555555550";

    static final String BSN_VOOGD_KIND_2 = "666666660"; // Voogd van kind #2

    @Autowired
    BevoegdheidTotGezagService subject;

    @MockBean
    GezagService gezagService;

    @MockBean
    BrpService brpService;

    @Mock
    private Transaction transaction;

    @BeforeEach
    void setup() throws GezagException {
        setupFamilieRelaties();
        setupGezagRelaties();
    }

    void setupFamilieRelaties() throws GezagException {
        when(brpService.getBsnsMinderjarigeKinderen(BSN_OUDER_VAN_MINDERJARIGE_OUDER, transaction)).thenReturn(List.of(BSN_MINDERJARIGE_OUDER));
        when(brpService.getBsnsMinderjarigeKinderen(BSN_MINDERJARIGE_OUDER, transaction)).thenReturn(List.of(BSN_KIND_1, BSN_KIND_2));
        when(brpService.getBsnsMinderjarigeKinderen(BSN_MEERDERJARIGE_OUDER, transaction)).thenReturn(List.of(BSN_KIND_1, BSN_KIND_2));
    }

    void setupGezagRelaties() {
        geenGezaghebbendenVanMeerderjarigen();
        grootOuderAlsGezaghebbendeVanMinderjarigeOuder();
        grootOuderEnMeerderjarigeOuderAlsGezaghebbendenVanKind1();
        voogdEnMeerderjarigeOuderAlsGezaghebbendenVanKind2();
    }

    void geenGezaghebbendenVanMeerderjarigen() {
        doReturn(Collections.emptyList()).when(gezagService).getGezag(BSN_OUDER_VAN_MINDERJARIGE_OUDER, transaction);
        doReturn(Collections.emptyList()).when(gezagService).getGezag(BSN_MEERDERJARIGE_OUDER, transaction);
        doReturn(Collections.emptyList()).when(gezagService).getGezag(BSN_VOOGD_KIND_2, transaction);
    }

    void grootOuderAlsGezaghebbendeVanMinderjarigeOuder() {
        var gezagsrelatieMinderjarigeOuderMetDiensOuder = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_MINDERJARIGE_OUDER, "OG1", BSN_OUDER_VAN_MINDERJARIGE_OUDER);
        var gezagsrelatiesMinderjarigeOuderMetDiensOuder = List.of(gezagsrelatieMinderjarigeOuderMetDiensOuder);

        when(gezagService.getGezag(BSN_MINDERJARIGE_OUDER, transaction)).thenReturn(gezagsrelatiesMinderjarigeOuderMetDiensOuder);
    }

    void grootOuderEnMeerderjarigeOuderAlsGezaghebbendenVanKind1() {
        var gezagsrelatieKind1MetGrootouder = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_KIND_1, "OG1", BSN_OUDER_VAN_MINDERJARIGE_OUDER);
        var gezagsrelatieKind1MetMeerderjarigeOuder = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_KIND_1, "OG1", BSN_MEERDERJARIGE_OUDER);
        var gezagsrelatiesKind1 = List.of(gezagsrelatieKind1MetGrootouder, gezagsrelatieKind1MetMeerderjarigeOuder);

        when(gezagService.getGezag(BSN_KIND_1, transaction)).thenReturn(gezagsrelatiesKind1);
    }

    void voogdEnMeerderjarigeOuderAlsGezaghebbendenVanKind2() {
        var gezagsrelatieKind2MetVoogd = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_KIND_2, "V", BSN_VOOGD_KIND_2);
        var gezagsrelatieKind2MetMeerderjarigeOuder = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_KIND_2, "OG1", BSN_MEERDERJARIGE_OUDER);
        var gezagsrelatiesKind2 = List.of(gezagsrelatieKind2MetVoogd, gezagsrelatieKind2MetMeerderjarigeOuder);

        when(gezagService.getGezag(BSN_KIND_2, transaction)).thenReturn(gezagsrelatiesKind2);
    }

    @Test
    void kanBevoegdheidTotGezagOpvragenVoorMinderjarigKind1() throws GezagException {
        // Hier worden alle gezagsrelaties voor het kind opgehaald
        var gezagRequest = new GezagRequest().bsn(BSN_KIND_1);
        var results = subject.bepaalBevoegdheidTotGezag(gezagRequest, transaction);

        System.out.println(BSN_KIND_1);
        System.out.println(results);

        var gezagsrelatieKind1MetGrootouder = new Gezagsrelatie(BSN_KIND_1, Gezagsrelatie.SoortGezagEnum.OG1).bsnMeerderjarige(BSN_OUDER_VAN_MINDERJARIGE_OUDER);
        var gezagsrelatieKind1MetMeerderjarigeOuder = new Gezagsrelatie(BSN_KIND_1, Gezagsrelatie.SoortGezagEnum.OG1).bsnMeerderjarige(BSN_MEERDERJARIGE_OUDER);
        assertThat(results).containsExactlyInAnyOrder(gezagsrelatieKind1MetGrootouder,
                gezagsrelatieKind1MetMeerderjarigeOuder);
    }

    @Test
    void kanBevoegdheidTotGezagOpvragenVoorMinderjarigKind2() throws GezagException {
        // Hier worden alle gezagsrelaties voor het kind opgehaald
        var gezagRequest = new GezagRequest().bsn(BSN_KIND_2);
        var results = subject.bepaalBevoegdheidTotGezag(gezagRequest, transaction);

        System.out.println(BSN_KIND_2);
        System.out.println(results);

        var gezagsrelatieKind2MetVoogd = new Gezagsrelatie(BSN_KIND_2, Gezagsrelatie.SoortGezagEnum.V).bsnMeerderjarige(BSN_VOOGD_KIND_2);
        var gezagsrelatieKind2MetMeerderjarigeOuder = new Gezagsrelatie(BSN_KIND_2, Gezagsrelatie.SoortGezagEnum.OG1).bsnMeerderjarige(BSN_MEERDERJARIGE_OUDER);
        assertThat(results).containsExactlyInAnyOrder(gezagsrelatieKind2MetVoogd,
                gezagsrelatieKind2MetMeerderjarigeOuder);
    }

    @Test
    void kanBevoegdheidTotGezagOpvragenVoorMeerderjarigeGrootouder() throws GezagException {
        // Hier worden enkel de gezagsrelaties van de kinderen van de meerderjarige opgehaald
        var gezagRequest = new GezagRequest().bsn(BSN_OUDER_VAN_MINDERJARIGE_OUDER);
        var results = subject.bepaalBevoegdheidTotGezag(gezagRequest, transaction);

        System.out.println(BSN_OUDER_VAN_MINDERJARIGE_OUDER);
        System.out.println(results);

        var gezagsrelatieMinderjarigeOuderMetDiensOuder
                = new Gezagsrelatie(BSN_MINDERJARIGE_OUDER, Gezagsrelatie.SoortGezagEnum.OG1).bsnMeerderjarige(BSN_OUDER_VAN_MINDERJARIGE_OUDER);
        assertThat(results).containsExactlyInAnyOrder(gezagsrelatieMinderjarigeOuderMetDiensOuder);
    }

    @Test
    void kanBevoegdheidTotGezagOpvragenVoorMeerderjarigeOuder() throws GezagException {
        var expected = Set.of(
            new Gezagsrelatie(BSN_KIND_1, Gezagsrelatie.SoortGezagEnum.OG1).bsnMeerderjarige(BSN_MEERDERJARIGE_OUDER),
            new Gezagsrelatie(BSN_KIND_2, Gezagsrelatie.SoortGezagEnum.OG1).bsnMeerderjarige(BSN_MEERDERJARIGE_OUDER)
        );

        // Hier worden enkel de gezagsrelaties van de kinderen van de meerderjarige opgehaald
        var gezagRequest = new GezagRequest().bsn(BSN_MEERDERJARIGE_OUDER);
        var results = subject.bepaalBevoegdheidTotGezag(gezagRequest, transaction);

        System.out.println(BSN_MEERDERJARIGE_OUDER);
        System.out.println(results);

        assertThat(results).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    void bevoegdhedenTotGezagVanMinderjarigeOuderBevattenOokNietDirectGerelateerdeGezagsrelatiesVanKinderen() throws GezagException {
        var expected = Set.of(
            new Gezagsrelatie(BSN_MINDERJARIGE_OUDER, Gezagsrelatie.SoortGezagEnum.OG1).bsnMeerderjarige(BSN_OUDER_VAN_MINDERJARIGE_OUDER)
        );

        // Hier worden alleen de gezagsrelaties voor de minderjarige ouder
        var gezagRequest = new GezagRequest().bsn(BSN_MINDERJARIGE_OUDER);
        var results = subject.bepaalBevoegdheidTotGezag(gezagRequest, transaction);

        System.out.println(BSN_MINDERJARIGE_OUDER);
        System.out.println(results);

        assertThat(results).containsExactlyInAnyOrderElementsOf(expected);
    }
}
