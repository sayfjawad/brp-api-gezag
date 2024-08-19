package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.brpadapter.service.BrpService;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagServiceOld;
import nl.rijksoverheid.mev.transaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import java.util.Collections;
import java.util.List;
import org.openapitools.model.*;
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
    static final String UITLEG = "Uitleg";
    static final String EENHOOFDIG_OUDERLIJK_GEZAG = "EenhoofdigOuderlijkGezag";
    static final String VOOGDIJ = "Voogdij";

    @Autowired
    BevoegdheidTotGezagService subject;

    @MockBean
    GezagServiceOld gezagService;

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
        doReturn(Collections.emptyList()).when(gezagService).getGezag(List.of(BSN_OUDER_VAN_MINDERJARIGE_OUDER), transaction);
        doReturn(Collections.emptyList()).when(gezagService).getGezag(List.of(BSN_MEERDERJARIGE_OUDER), transaction);
        doReturn(Collections.emptyList()).when(gezagService).getGezag(List.of(BSN_VOOGD_KIND_2), transaction);
    }

    void grootOuderAlsGezaghebbendeVanMinderjarigeOuder() {
        var gezagsrelatieMinderjarigeOuderMetDiensOuder = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_MINDERJARIGE_OUDER, BSN_MINDERJARIGE_OUDER, "OG1", BSN_OUDER_VAN_MINDERJARIGE_OUDER, UITLEG);
        var gezagsrelatiesMinderjarigeOuderMetDiensOuder = List.of(gezagsrelatieMinderjarigeOuderMetDiensOuder);

        when(gezagService.getGezag(List.of(BSN_MINDERJARIGE_OUDER), transaction)).thenReturn(gezagsrelatiesMinderjarigeOuderMetDiensOuder);
    }

    void grootOuderEnMeerderjarigeOuderAlsGezaghebbendenVanKind1() {
        var gezagsrelatieKind1MetGrootouder = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_KIND_1, BSN_KIND_1, "OG1", BSN_OUDER_VAN_MINDERJARIGE_OUDER, UITLEG);
        var gezagsrelatieKind1MetMeerderjarigeOuder = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_KIND_1, BSN_KIND_1, "OG1", BSN_MEERDERJARIGE_OUDER, UITLEG);
        var gezagsrelatiesKind1 = List.of(gezagsrelatieKind1MetGrootouder, gezagsrelatieKind1MetMeerderjarigeOuder);

        when(gezagService.getGezag(List.of(BSN_KIND_1), transaction)).thenReturn(gezagsrelatiesKind1);
    }

    void voogdEnMeerderjarigeOuderAlsGezaghebbendenVanKind2() {
        var gezagsrelatieKind2MetVoogd = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_KIND_2, BSN_KIND_2, "V", BSN_VOOGD_KIND_2, UITLEG);
        var gezagsrelatieKind2MetMeerderjarigeOuder = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_KIND_2, BSN_KIND_2, "OG1", BSN_MEERDERJARIGE_OUDER, UITLEG);
        var gezagsrelatiesKind2 = List.of(gezagsrelatieKind2MetVoogd, gezagsrelatieKind2MetMeerderjarigeOuder);

        when(gezagService.getGezag(List.of(BSN_KIND_2), transaction)).thenReturn(gezagsrelatiesKind2);
    }

    void gezagRelatiesKind1EnKind2() {
        var gezagsrelatieKind1MetGrootouder = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_KIND_1, BSN_KIND_1, "OG1", BSN_OUDER_VAN_MINDERJARIGE_OUDER, UITLEG);
        var gezagsrelatieKind1MetMeerderjarigeOuder = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_KIND_1, BSN_KIND_1, "OG1", BSN_MEERDERJARIGE_OUDER, UITLEG);
        var gezagsrelatieKind2MetVoogd = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_KIND_2, BSN_KIND_2, "V", BSN_VOOGD_KIND_2, UITLEG);
        var gezagsrelatieKind2MetMeerderjarigeOuder = new nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie(BSN_KIND_2, BSN_KIND_2, "OG1", BSN_MEERDERJARIGE_OUDER, UITLEG);
        var gezagsrelaties = List.of(gezagsrelatieKind1MetGrootouder, gezagsrelatieKind1MetMeerderjarigeOuder, gezagsrelatieKind2MetVoogd, gezagsrelatieKind2MetMeerderjarigeOuder);

        when(gezagService.getGezag(List.of(BSN_KIND_1, BSN_KIND_2), transaction)).thenReturn(gezagsrelaties);
    }

    @Test
    void kanBevoegdheidTotGezagOpvragenVoorMinderjarigKind1() throws GezagException {
        // Hier worden alle gezagsrelaties voor het kind opgehaald
        GezagRequest gezagRequest = new GezagRequest().burgerservicenummer(List.of(BSN_KIND_1));
        List<Persoon> results = subject.bepaalBevoegdheidTotGezag(gezagRequest, transaction);

        Persoon persoon = new Persoon().burgerservicenummer(BSN_KIND_1);
        AbstractGezagsrelatie gezagKind1MetGrootouder = new EenhoofdigOuderlijkGezag()
                .ouder(new GezagOuder().burgerservicenummer(BSN_OUDER_VAN_MINDERJARIGE_OUDER))
                .minderjarige(new Minderjarige().burgerservicenummer(BSN_KIND_1))
                .type(EENHOOFDIG_OUDERLIJK_GEZAG);
        persoon.addGezagItem(gezagKind1MetGrootouder);

        AbstractGezagsrelatie gezagKind1MetMeerderjarigeOuder = new EenhoofdigOuderlijkGezag()
                .ouder(new GezagOuder().burgerservicenummer(BSN_MEERDERJARIGE_OUDER))
                .minderjarige(new Minderjarige().burgerservicenummer(BSN_KIND_1))
                .type(EENHOOFDIG_OUDERLIJK_GEZAG);
        persoon.addGezagItem(gezagKind1MetMeerderjarigeOuder);

        assertThat(results).contains(persoon);
        Persoon persoonResult = results.get(0);
        assertThat(persoonResult.getGezag()).containsExactlyInAnyOrder(gezagKind1MetGrootouder,
                gezagKind1MetMeerderjarigeOuder);
    }

    @Test
    void kanBevoegdheidTotGezagOpvragenVoorMinderjarigKind2() throws GezagException {
        // Hier worden alle gezagsrelaties voor het kind opgehaald
        GezagRequest gezagRequest = new GezagRequest().burgerservicenummer(List.of(BSN_KIND_2));
        List<Persoon> results = subject.bepaalBevoegdheidTotGezag(gezagRequest, transaction);

        Persoon persoon = new Persoon().burgerservicenummer(BSN_KIND_2);
        AbstractGezagsrelatie gezagKind2MetVoogd = new Voogdij()
                .addDerdenItem(new Meerderjarige().burgerservicenummer(BSN_VOOGD_KIND_2))
                .minderjarige(new Minderjarige().burgerservicenummer(BSN_KIND_2))
                .type(VOOGDIJ);
        persoon.addGezagItem(gezagKind2MetVoogd);

        AbstractGezagsrelatie gezagKind2MetMeerderjarigeOuder = new EenhoofdigOuderlijkGezag()
                .ouder(new GezagOuder().burgerservicenummer(BSN_MEERDERJARIGE_OUDER))
                .minderjarige(new Minderjarige().burgerservicenummer(BSN_KIND_2))
                .type(EENHOOFDIG_OUDERLIJK_GEZAG);
        persoon.addGezagItem(gezagKind2MetMeerderjarigeOuder);

        assertThat(results).contains(persoon);
        Persoon persoonResult = results.get(0);
        assertThat(persoonResult.getGezag()).containsExactlyInAnyOrder(gezagKind2MetVoogd,
                gezagKind2MetMeerderjarigeOuder);
    }

    @Test
    void kanBevoegdheidTotGezagOpvragenVoorMeerderjarigeGrootouder() throws GezagException {
        // Hier worden enkel de gezagsrelaties van de kinderen van de meerderjarige opgehaald
        GezagRequest gezagRequest = new GezagRequest().burgerservicenummer(List.of(BSN_OUDER_VAN_MINDERJARIGE_OUDER));
        List<Persoon> results = subject.bepaalBevoegdheidTotGezag(gezagRequest, transaction);

        Persoon persoon = new Persoon().burgerservicenummer(BSN_OUDER_VAN_MINDERJARIGE_OUDER);
        AbstractGezagsrelatie gezagsMinderjarigeOuderMetDiensOuder = new EenhoofdigOuderlijkGezag()
                .ouder(new GezagOuder().burgerservicenummer(BSN_OUDER_VAN_MINDERJARIGE_OUDER))
                .minderjarige(new Minderjarige().burgerservicenummer(BSN_MINDERJARIGE_OUDER))
                .type(EENHOOFDIG_OUDERLIJK_GEZAG);
        persoon.addGezagItem(gezagsMinderjarigeOuderMetDiensOuder);
        
        assertThat(results).contains(persoon);
        Persoon persoonResult = results.get(0);
        assertThat(persoonResult.getGezag()).containsExactlyInAnyOrder(gezagsMinderjarigeOuderMetDiensOuder);
    }

    @Test
    void kanBevoegdheidTotGezagOpvragenVoorMeerderjarigeOuder() throws GezagException {
        gezagRelatiesKind1EnKind2();

        // Hier worden enkel de gezagsrelaties van de kinderen van de meerderjarige opgehaald
        GezagRequest gezagRequest = new GezagRequest().burgerservicenummer(List.of(BSN_MEERDERJARIGE_OUDER));
        List<Persoon> results = subject.bepaalBevoegdheidTotGezag(gezagRequest, transaction);

        Persoon persoon = new Persoon().burgerservicenummer(BSN_MEERDERJARIGE_OUDER);
        AbstractGezagsrelatie gezagKind1MetMeerderjarigeOuder = new EenhoofdigOuderlijkGezag()
                .ouder(new GezagOuder().burgerservicenummer(BSN_MEERDERJARIGE_OUDER))
                .minderjarige(new Minderjarige().burgerservicenummer(BSN_KIND_1))
                .type(EENHOOFDIG_OUDERLIJK_GEZAG);
        persoon.addGezagItem(gezagKind1MetMeerderjarigeOuder);

        AbstractGezagsrelatie gezagKind2MetMeerderjarigeOuder = new EenhoofdigOuderlijkGezag()
                .ouder(new GezagOuder().burgerservicenummer(BSN_MEERDERJARIGE_OUDER))
                .minderjarige(new Minderjarige().burgerservicenummer(BSN_KIND_2))
                .type(EENHOOFDIG_OUDERLIJK_GEZAG);
        persoon.addGezagItem(gezagKind2MetMeerderjarigeOuder);

        assertThat(results).contains(persoon);
        Persoon persoonResult = results.get(0);
        assertThat(persoonResult.getGezag()).containsExactlyInAnyOrder(gezagKind1MetMeerderjarigeOuder,
                gezagKind2MetMeerderjarigeOuder);
    }

    @Test
    void bevoegdhedenTotGezagVanMinderjarigeOuderBevattenOokNietDirectGerelateerdeGezagsrelatiesVanKinderen() throws GezagException {
        // Hier worden alleen de gezagsrelaties voor de minderjarige ouder
        GezagRequest gezagRequest = new GezagRequest().burgerservicenummer(List.of(BSN_MINDERJARIGE_OUDER));
        List<Persoon> results = subject.bepaalBevoegdheidTotGezag(gezagRequest, transaction);

        Persoon persoon = new Persoon().burgerservicenummer(BSN_MINDERJARIGE_OUDER);
        AbstractGezagsrelatie gezagMinderjarigeOuder = new EenhoofdigOuderlijkGezag()
                .ouder(new GezagOuder().burgerservicenummer(BSN_OUDER_VAN_MINDERJARIGE_OUDER))
                .minderjarige(new Minderjarige().burgerservicenummer(BSN_MINDERJARIGE_OUDER))
                .type(EENHOOFDIG_OUDERLIJK_GEZAG);
        persoon.addGezagItem(gezagMinderjarigeOuder);

        assertThat(results).contains(persoon);
        Persoon persoonResult = results.get(0);
        assertThat(persoonResult.getGezag()).containsExactlyInAnyOrder(gezagMinderjarigeOuder);
    }
}
