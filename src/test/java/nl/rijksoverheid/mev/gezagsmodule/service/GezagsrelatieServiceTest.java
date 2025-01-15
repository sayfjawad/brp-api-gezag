package nl.rijksoverheid.mev.gezagsmodule.service;

import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openapitools.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GezagsrelatieServiceTest {

    @Mock
    private GezagsBepaling gezagsBepalingMock;

    private GezagsrelatieService classUnderTest;

    private static final String OG1 = "OG1";
    private static final String OG2 = "OG2";
    private static final String V = "V";
    private static final String GG = "GG";
    private static final String INDICATION_GEZAG = "Ja";
    private static final String BURGERSERVICENUMMER_ONE = "000000012";
    private static final String BURGERSERVICENUMMER_TWO = "000000024";
    private static final String BURGERSERVICENUMMER_THREE = "000000036";

    @BeforeEach
    public void setup() {
        classUnderTest = new GezagsrelatieService();
    }

    @Test
    void bepaalGezagsrelatiesWithoutAntwoordenModelAndGezagsbepaling() {
        ARAntwoordenModel arAntwoordenModel = null;
        GezagsBepaling gezagsBepaling = null;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertTrue(gezagsRelaties.isEmpty());
    }

    @Test
    void bepaalGezagsrelatiesWithGezagsuitkomstInAntwoordenModelAndHavingNoParents() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertTrue(gezagsRelaties.isEmpty());
    }

    @Test
    void bepaalGezagsrelatiesWithAntwoordenModelOG1ForOuder1NotHavingPersonAndNotHavingAnyParents() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        arAntwoordenModel.setSoortGezag(OG1);
        arAntwoordenModel.setGezagOuder1(INDICATION_GEZAG);
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertTrue(gezagsRelaties.isEmpty());
    }

    @Test
    void bepaalGezagsrelatiesWithAntwoordenModelOG1HavingPersonAndNotHavingAnyParents() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        arAntwoordenModel.setSoortGezag(OG1);
        arAntwoordenModel.setGezagOuder1(INDICATION_GEZAG);

        String minderjarige = BURGERSERVICENUMMER_ONE;
        String bevraagdePersoon = minderjarige;
        when(gezagsBepalingMock.getBurgerservicenummer()).thenReturn(minderjarige);
        when(gezagsBepalingMock.getBurgerservicenummerPersoon()).thenReturn(bevraagdePersoon);
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertTrue(gezagsRelaties.isEmpty());
    }

    @Test
    void bepaalGezagsrelatiesWithAntwoordenModelOG1AndHavingOneParentAsOuder1() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        arAntwoordenModel.setSoortGezag(OG1);
        arAntwoordenModel.setGezagOuder1(INDICATION_GEZAG);

        String minderjarige = BURGERSERVICENUMMER_ONE;
        String bevraagdePersoon = minderjarige;
        String ouder1 = BURGERSERVICENUMMER_TWO;
        when(gezagsBepalingMock.getBurgerservicenummer()).thenReturn(minderjarige);
        when(gezagsBepalingMock.getBurgerservicenummerPersoon()).thenReturn(bevraagdePersoon);
        when(gezagsBepalingMock.getBurgerservicenummerOuder1()).thenReturn(ouder1);
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertFalse(gezagsRelaties.isEmpty());
        EenhoofdigOuderlijkGezag gezag = (EenhoofdigOuderlijkGezag) gezagsRelaties.getFirst();
        assertEquals(minderjarige, gezag.getMinderjarige().get().getBurgerservicenummer());
        assertEquals(ouder1, gezag.getOuder().get().getBurgerservicenummer());
    }

    @Test
    void bepaalGezagsrelatiesWithAntwoordenModelOG1AndHavingOneParentAsOuder2_resultingInNoGezag() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        arAntwoordenModel.setSoortGezag(OG1);
        arAntwoordenModel.setGezagOuder1(INDICATION_GEZAG);

        String minderjarige = BURGERSERVICENUMMER_ONE;
        String bevraagdePersoon = minderjarige;
        String ouder2 = BURGERSERVICENUMMER_TWO;
        when(gezagsBepalingMock.getBurgerservicenummer()).thenReturn(minderjarige);
        when(gezagsBepalingMock.getBurgerservicenummerPersoon()).thenReturn(bevraagdePersoon);
        when(gezagsBepalingMock.getBurgerservicenummerOuder2()).thenReturn(ouder2);
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertTrue(gezagsRelaties.isEmpty());
    }

    @Test
    void bepaalGezagsrelatiesWithAntwoordenModelOG2AndHavingNoParents() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        arAntwoordenModel.setSoortGezag(OG2);

        String minderjarige = BURGERSERVICENUMMER_ONE;
        String bevraagdePersoon = minderjarige;
        when(gezagsBepalingMock.getBurgerservicenummer()).thenReturn(minderjarige);
        when(gezagsBepalingMock.getBurgerservicenummerPersoon()).thenReturn(bevraagdePersoon);
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertFalse(gezagsRelaties.isEmpty());
        TweehoofdigOuderlijkGezag gezag = (TweehoofdigOuderlijkGezag) gezagsRelaties.getFirst();
        assertEquals(minderjarige, gezag.getMinderjarige().get().getBurgerservicenummer());
    }

    @Test
    void bepaalGezagsrelatiesWithAntwoordenModelOG2AndHavingOneParentAsOuder1() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        arAntwoordenModel.setSoortGezag(OG2);

        String minderjarige = BURGERSERVICENUMMER_ONE;
        String bevraagdePersoon = minderjarige;
        String ouder1 = BURGERSERVICENUMMER_TWO;
        when(gezagsBepalingMock.getBurgerservicenummer()).thenReturn(minderjarige);
        when(gezagsBepalingMock.getBurgerservicenummerPersoon()).thenReturn(bevraagdePersoon);
        when(gezagsBepalingMock.getBurgerservicenummerOuder1()).thenReturn(ouder1);
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertFalse(gezagsRelaties.isEmpty());
        TweehoofdigOuderlijkGezag gezag = (TweehoofdigOuderlijkGezag) gezagsRelaties.getFirst();
        assertEquals(minderjarige, gezag.getMinderjarige().get().getBurgerservicenummer());
        assertEquals(ouder1, gezag.getOuders().get(0).getBurgerservicenummer());
    }

    @Test
    void bepaalGezagsrelatiesWithAntwoordenModelOG2AndHavingTwoParents() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        arAntwoordenModel.setSoortGezag(OG2);

        String minderjarige = BURGERSERVICENUMMER_ONE;
        String bevraagdePersoon = minderjarige;
        String ouder1 = BURGERSERVICENUMMER_TWO;
        String ouder2 = BURGERSERVICENUMMER_THREE;
        when(gezagsBepalingMock.getBurgerservicenummer()).thenReturn(minderjarige);
        when(gezagsBepalingMock.getBurgerservicenummerPersoon()).thenReturn(bevraagdePersoon);
        when(gezagsBepalingMock.getBurgerservicenummerOuder1()).thenReturn(ouder1);
        when(gezagsBepalingMock.getBurgerservicenummerOuder2()).thenReturn(ouder2);
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertFalse(gezagsRelaties.isEmpty());
        TweehoofdigOuderlijkGezag gezag = (TweehoofdigOuderlijkGezag) gezagsRelaties.getFirst();
        assertEquals(minderjarige, gezag.getMinderjarige().get().getBurgerservicenummer());
        assertEquals(ouder1, gezag.getOuders().get(0).getBurgerservicenummer());
        assertEquals(ouder2, gezag.getOuders().get(1).getBurgerservicenummer());
    }

    @Test
    void bepaalGezagsrelatiesWithAntwoordenModelVAndHavingOneParentOuder1() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        arAntwoordenModel.setSoortGezag(V);
        arAntwoordenModel.setGezagNietOuder1(INDICATION_GEZAG);

        String minderjarige = BURGERSERVICENUMMER_ONE;
        String bevraagdePersoon = minderjarige;
        String ouder1 = BURGERSERVICENUMMER_TWO;
        when(gezagsBepalingMock.getBurgerservicenummer()).thenReturn(minderjarige);
        when(gezagsBepalingMock.getBurgerservicenummerPersoon()).thenReturn(bevraagdePersoon);
        when(gezagsBepalingMock.getBurgerservicenummerOuder1()).thenReturn(ouder1);
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertFalse(gezagsRelaties.isEmpty());
        Voogdij gezag = (Voogdij) gezagsRelaties.getFirst();
        assertEquals(minderjarige, gezag.getMinderjarige().get().getBurgerservicenummer());
    }

    @Test
    void bepaalGezagsrelatiesWithAntwoordenModelVAndHavingOneParentNietOuder() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        arAntwoordenModel.setSoortGezag(V);
        arAntwoordenModel.setGezagNietOuder1(INDICATION_GEZAG);

        String minderjarige = BURGERSERVICENUMMER_ONE;
        String bevraagdePersoon = minderjarige;
        String nietOuder = BURGERSERVICENUMMER_TWO;
        when(gezagsBepalingMock.getBurgerservicenummer()).thenReturn(minderjarige);
        when(gezagsBepalingMock.getBurgerservicenummerPersoon()).thenReturn(bevraagdePersoon);
        when(gezagsBepalingMock.getBurgerservicenummerNietOuder()).thenReturn(nietOuder);
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertFalse(gezagsRelaties.isEmpty());
        Voogdij gezag = (Voogdij) gezagsRelaties.getFirst();
        assertEquals(minderjarige, gezag.getMinderjarige().get().getBurgerservicenummer());
        assertEquals(nietOuder, gezag.getDerden().get(0).getBurgerservicenummer().get());
    }

    @Test
    void bepaalGezagsrelatiesWithAntwoordenModelVAndHavingOneParentNietOuder2() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        arAntwoordenModel.setSoortGezag(V);
        arAntwoordenModel.setGezagNietOuder2(INDICATION_GEZAG);

        String minderjarige = BURGERSERVICENUMMER_ONE;
        String bevraagdePersoon = minderjarige;
        String nietOuder = BURGERSERVICENUMMER_TWO;
        when(gezagsBepalingMock.getBurgerservicenummer()).thenReturn(minderjarige);
        when(gezagsBepalingMock.getBurgerservicenummerPersoon()).thenReturn(bevraagdePersoon);
        when(gezagsBepalingMock.getBurgerservicenummerNietOuder()).thenReturn(nietOuder);
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertFalse(gezagsRelaties.isEmpty());
        Voogdij gezag = (Voogdij) gezagsRelaties.getFirst();
        assertEquals(minderjarige, gezag.getMinderjarige().get().getBurgerservicenummer());
        assertEquals(nietOuder, gezag.getDerden().get(0).getBurgerservicenummer().get());
    }

    @Test
    void bepaalGezagsrelatiesWithAntwoordenModelGGAndHavingOneParentOuder1AndNoNietOuder() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        arAntwoordenModel.setSoortGezag(GG);
        arAntwoordenModel.setGezagOuder1(INDICATION_GEZAG);
        arAntwoordenModel.setGezagNietOuder1(INDICATION_GEZAG);

        String minderjarige = BURGERSERVICENUMMER_ONE;
        String bevraagdePersoon = minderjarige;
        String ouder1 = BURGERSERVICENUMMER_TWO;
        when(gezagsBepalingMock.getBurgerservicenummer()).thenReturn(minderjarige);
        when(gezagsBepalingMock.getBurgerservicenummerPersoon()).thenReturn(bevraagdePersoon);
        when(gezagsBepalingMock.getBurgerservicenummerOuder1()).thenReturn(ouder1);
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertFalse(gezagsRelaties.isEmpty());
        GezamenlijkGezag gezag = (GezamenlijkGezag) gezagsRelaties.getFirst();
        assertEquals(minderjarige, gezag.getMinderjarige().get().getBurgerservicenummer());
        assertEquals(ouder1, gezag.getOuder().get().getBurgerservicenummer());
    }

    @Test
    void bepaalGezagsrelatiesWithAntwoordenModelGGAndHavingOneParentOuder1AndHavingNietOuder() {
        ARAntwoordenModel arAntwoordenModel = new ARAntwoordenModel();
        arAntwoordenModel.setSoortGezag(GG);
        arAntwoordenModel.setGezagOuder1(INDICATION_GEZAG);
        arAntwoordenModel.setGezagNietOuder1(INDICATION_GEZAG);

        String minderjarige = BURGERSERVICENUMMER_ONE;
        String bevraagdePersoon = minderjarige;
        String ouder1 = BURGERSERVICENUMMER_TWO;
        String nietOuder = BURGERSERVICENUMMER_THREE;
        when(gezagsBepalingMock.getBurgerservicenummer()).thenReturn(minderjarige);
        when(gezagsBepalingMock.getBurgerservicenummerPersoon()).thenReturn(bevraagdePersoon);
        when(gezagsBepalingMock.getBurgerservicenummerOuder1()).thenReturn(ouder1);
        when(gezagsBepalingMock.getBurgerservicenummerNietOuder()).thenReturn(nietOuder);
        GezagsBepaling gezagsBepaling = gezagsBepalingMock;

        List<AbstractGezagsrelatie> gezagsRelaties = classUnderTest.bepaalGezagsrelaties(arAntwoordenModel, gezagsBepaling);

        assertFalse(gezagsRelaties.isEmpty());
        GezamenlijkGezag gezag = (GezamenlijkGezag) gezagsRelaties.getFirst();
        assertEquals(minderjarige, gezag.getMinderjarige().get().getBurgerservicenummer());
        assertEquals(ouder1, gezag.getOuder().get().getBurgerservicenummer());
        assertEquals(nietOuder, gezag.getDerde().get().getBurgerservicenummer().get());
    }
}