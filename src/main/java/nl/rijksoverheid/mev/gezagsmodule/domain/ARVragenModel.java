package nl.rijksoverheid.mev.gezagsmodule.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagService;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagServiceOld;
import nl.rijksoverheid.mev.transaction.Transaction;

import java.util.*;

import static nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst.isValideGeslachtsnaam;

@Slf4j
@NoArgsConstructor(force = true)
@Getter
@Setter
public class ARVragenModel {

    private static final int DATE_JAN_1_2023 = 20230101;
    private static final String INDICATIE_GEZAG_OUDER1 = "1";
    private static final String INDICATIE_GEZAG_OUDER2 = "2";
    private static final String STANDAARD_WAARDE_INGANGSDATUM_GELDIGHEID_GEZAG = "00000000";
    private static final String INDICATIE_GEZAG_BEIDE_OUDERS = "12";
    private static final String TABEL_41_OVERLIJDEN_ECHTGENOOT_GEREGISTREERD_PARTNER = "O";
    private static final String TABEL_41_RECHTSVERMOEDEN_VAN_OVERLIJDEN_ECHTGENOOT_GEREGISTREERD_PARTNER = "R";
    private static final String INDICATIE_HUWELIJK_NIETIG_VERKLAARD = "N";
    private static final String V1_1_NEE = "Nee";
    private static final String V1_1_JA = "Ja";
    private static final String V1_2_JA = "Ja";
    private static final String V1_2_NEE_MEERDERJARIG = "Nee_meerderjarig";
    private static final String V1_2_NEE_OVERLEDEN = "Nee_overleden";
    private static final String V1_3_JA = "Ja";
    private static final String V1_3_NEE = "Nee";
    private static final String V1_3A_JA = "Ja";
    private static final String V1_3A_NEE = "Nee";
    private static final String V1_3B_JA = "Ja";
    private static final String V1_3B_NEE = "Nee";
    private static final String V1_4_JA = "Ja";
    private static final String V1_4_NEE = "Nee";
    private static final String V2A_1_JA_GEHUWD_OF_PARTNERS = "Ja";
    private static final String V2A_1_NEE = "Nee";
    private static final String V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR = "Nee_nooit";
    private static final String V2A_2_JA = "Ja";
    private static final String V2A_2_NEE = "Nee";
    private static final String V2A_3_VOOR = "Voor";
    private static final String V2A_3_VOOR_OUDER1 = "Voor_ouder1";
    private static final String V2A_3_VOOR_OUDER2 = "Voor_ouder2";
    private static final String V2A_3_NA = "Na";
    private static final String V2B_1_JA = "Ja";
    private static final String V2B_1_NEE = "Nee";
    private static final String V3_1_JA = "Ja";
    private static final String V3_1_NEE = "Nee";
    private static final String V4A_2_JA_BEIDEN = "Ja_beiden";
    private static final String V4A_2_JA_OUDER1 = "Ja_ouder1";
    private static final String V4A_2_JA_OUDER2 = "Ja_ouder2";
    private static final String V4A_2_NEE = "Nee";
    private static final String V4A_3_NEE_OUDER1 = "Nee_ouder1";
    private static final String V4A_3_NEE_OUDER2 = "Nee_ouder2";
    private static final String V4A_3_NEE = "Nee";
    private static final String V4A_3_JA = "Ja";
    private static final String V4B_1_NEE = "Nee";
    private static final String V4B_1_JA_BEIDEN = "Ja_beiden";
    private static final String V4B_1_JA_OUDER1 = "Ja_ouder1";
    private static final String V4B_1_JA_OUDER2 = "Ja_ouder2";
    private static final String V4B_1_JA_NIET_OUDER1 = "Ja_nietouder1";
    private static final String V4B_1_JA_NIET_OUDER2 = "Ja_nietouder2";
    private static final String[] HUWELIJK_OF_PARTNERSCHAP_REDEN_ONTBINDING
            = new String[]{TABEL_41_OVERLIJDEN_ECHTGENOOT_GEREGISTREERD_PARTNER,
                TABEL_41_RECHTSVERMOEDEN_VAN_OVERLIJDEN_ECHTGENOOT_GEREGISTREERD_PARTNER};

    private Persoonslijst plPersoon;
    private Persoonslijst plOuder1;
    private Persoonslijst plOuder2;
    private Persoonslijst plNietOuder;
    private final Transaction transaction;

    private final GezagService gezagService;

    public ARVragenModel(final Persoonslijst plPersoon,
            final GezagService gezagService,
            final Transaction transaction) {
        this.plPersoon = plPersoon;
        this.gezagService = gezagService;
        this.transaction = transaction;
    }

    /**
     * v1_1
     *
     * @return "Ja" als isIngezeteneInBRP anders "Nee"
     */
    public String v11isPersoonIngezeteneInBRP() {
        if (plPersoon != null && plPersoon.isIngezeteneInBRP()) {
            return V1_1_JA;
        } else if (plPersoon != null && !plPersoon.isIngezeteneInBRP()) {
            return V1_1_NEE;
        } else {
            return null;
        }
    }

    /**
     * v1_2
     * <p>
     * Vraag {@link ARVragenModel#v11isPersoonIngezeteneInBRP()} in combinatie met {@code isAlsMinderjarigOpgeschort}
     * leidt ertoe dat het enige mogelijke antwoord {@code V1_2_NEE_OVERLEDEN} is.
     *
     * @return of minderjarig en niet overleden is
     */
    public String v12IsPersoonMinderjarigEnNietOverleden() throws GezagException {
        boolean isMinderjarig = plPersoon.minderjarig();
        boolean isOpgeschort = plPersoon.isOpgeschort();
        boolean isAlsMinderjarigOpgeschort = plPersoon.alsMinderjarigeOpgeschort();

        if (isAlsMinderjarigOpgeschort) return V1_2_NEE_OVERLEDEN;
        if (isOpgeschort) return V1_2_NEE_MEERDERJARIG;
        if (isMinderjarig) return V1_2_JA; // Minderjarig en niet overleden
        return V1_2_NEE_MEERDERJARIG;
    }

    /**
     * v1_3
     *
     * @return "Ja" als is naar buitenland geëmigreerd geweest, anders "Nee"
     */
    public String v13isNaarHetBuitenlandGeemigreerdGeweest() {
        if (plPersoon.naarBuitenlandGeemigreerdGeweest()) {
            return V1_3_JA;
        }
        return V1_3_NEE;
    }

    /**
     * v1_3a
     *
     * @return "Ja" als is geboren in het buitenland anders "Nee"
     */
    public String v13aisGeborenInBuitenland() {
        if (plPersoon.isGeborenInBuitenland()) {
            return V1_3A_JA;
        }
        return V1_3A_NEE;
    }

    /**
     * v1_3b
     *
     * @return "Ja" als is geadopteerd met nederlandse akte anders "Nee"
     */
    public String v13bIsGeadopteerdMetNlAkte() {
        if (plPersoon.geadopteerdMetNlAkte()) {
            return V1_3B_JA;
        }
        return V1_3B_NEE;

    }

    /**
     * sv1_4
     *
     * @return JA/NEE of IN_ONDERZOEK
     */
    public String v14IsUitspraakGezagAanwezig() {
        if (plPersoon.heefIndicatieGezag()) {
            return V1_4_JA;
        }
        return V1_4_NEE;
    }

    /**
     * sv2_1
     *
     * @return EEN OUDER/TWEE OUDERS/GEEN OUDERS/2 PUNTOUDERS/ONBEKEND of
     * IN_ONDERZOEK
     */
    public String v21HoeveelJuridischeOudersHeeftMinderjarige() {
        return plPersoon.hoeveelJuridischeOuders();
    }

    public void preconditieCheckGeregistreerd(String beschrijving, Persoonslijst plOuder) throws GezagException {
        boolean ouderGeregistreerdInBrp
            = plOuder != null
            && plOuder.isNietIngeschrevenInRNI()
            && plOuder.isNietGeemigreerd();
        if (!ouderGeregistreerdInBrp) {
            throw new AfleidingsregelException("Preconditie: " + beschrijving + " moet in BRP geregistreerd staan");
        }
    }

    public void preconditieCheckOudersGeregistreerd() throws GezagException {
        if (!plPersoon.heeftTweeOuders()) {
            throw new AfleidingsregelException("Preconditie: Kind moet twee ouders hebben");
        }
        preconditieCheckGeregistreerd("ouder1", getPlOuder1());
        preconditieCheckGeregistreerd("ouder2", getPlOuder2());
    }

    /**
     * v2a_1
     *
     * @return JA GEHUWD OF PARTNERS/NEE NA GEBOORTE NOOIT GEHUWD PARTNERS
     * GEWEEST MET ELKAAR / NEE GESCHEIDEN NA GEBOORTE
     */
    public String v2a1ZijnJuridischeOudersNuMetElkaarGehuwdOfPartners() throws GezagException {
        String geboortedatumKind = plPersoon.getPersoon().getGeboortedatum();
        preconditieCheckOudersGeregistreerd();
        HuwelijkOfPartnerschap hopOuder1 = ouderGetHetHuwelijkOfPartnerschap(plOuder1, plOuder2);
        HuwelijkOfPartnerschap hopOuder2 = ouderGetHetHuwelijkOfPartnerschap(plOuder2, plOuder1);
        // Als er geen huwelijk tussen de ouders gevonden wordt is het antwoord "Nee_nooit"
        if (hopOuder1 == null || hopOuder2 == null) {
            return V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR;
        } else if (isHuwelijkOfPartnerschapTussenOudersActueel(hopOuder1, hopOuder2)) {
            if (!plOuder1.isOverleden() && !plOuder2.isOverleden()){
            return V2A_1_JA_GEHUWD_OF_PARTNERS;
            }
            else return V2A_1_NEE;
        } else if (ouderGescheiden(hopOuder1, geboortedatumKind) || ouderGescheiden(hopOuder2, geboortedatumKind)) {
            return V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR;
        } else {
            return V2A_1_NEE;
        }
    }

    /**
     * v2a_2
     *
     * @return "Ja" als is adoptief ouders, anders "Nee"
     */
    public String v2a2AdoptiefOuders() {
        return (plPersoon.geadopteerdMetNlAkte()) ? V2A_2_JA : V2A_2_NEE;
    }

    /**
     * v2a_3
     *
     * @return VOOR of NA
     */
    public String v2a3ErkenningNa01012023() {
        Ouder1 persoonOuder1 = plPersoon.getOuder1();
        Ouder2 persoonOuder2 = plPersoon.getOuder2();
        if (persoonOuder1 == null || persoonOuder2 == null)
            throw new AfleidingsregelException("Preconditie: vraag 2a.3 - Geen twee ouders bij erkenning");
        // voor snelheid en gegevens in onderzoek
        boolean persoonErkend = plPersoon.geenOngeborenVruchtErkendOfGerechtelijkeVaststelling();
        boolean ouder1Erkend = plPersoon.geenOngeborenVruchtDoorOuder1ErkendOfGerechtelijkeVaststelling();
        boolean ouder2Erkend = plPersoon.geenOngeborenVruchtDoorOuder2ErkendOfGerechtelijkeVaststelling();
        boolean persoonOngeborenVruchtErkend = plPersoon.ongeborenVruchtErkend();
        boolean ouder1ErkendOpOfNa01012023 = Integer.parseInt(persoonOuder1.getDatumIngangFamiliebetrekking()) >= DATE_JAN_1_2023;
        boolean ouder2ErkendOpOfNa01012023 = Integer.parseInt(persoonOuder2.getDatumIngangFamiliebetrekking()) >= DATE_JAN_1_2023;
        boolean persoonGeborenVoor01012023 = Integer.parseInt(plPersoon.getPersoon().getGeboortedatum()) < DATE_JAN_1_2023;
        // evaluatie op volgorde
        if ((persoonErkend || persoonOngeborenVruchtErkend) &&
            (ouder1ErkendOpOfNa01012023 || ouder2ErkendOpOfNa01012023)) return V2A_3_NA;
        if (ouder1Erkend) return V2A_3_VOOR_OUDER2;
        if (ouder2Erkend) return V2A_3_VOOR_OUDER1;
        if (!persoonErkend && persoonOngeborenVruchtErkend && persoonGeborenVoor01012023)
            return V2A_3_VOOR;

        throw new AfleidingsregelException("Preconditie: vraag 2a.3 - Geboortemoeder niet te bepalen");
    }

    public boolean heeftOuderRelatieBijGeboorteKind(Persoonslijst plOuder, String geboortedatum)
    {
        List<HuwelijkOfPartnerschap> hopPlOuder = plOuder.getHuwelijkOfPartnerschappen();
        if (!hopPlOuder.isEmpty() && (hopPlOuder.get(0).getBsnPartner() != null))
        {
            HopRelaties hopRelaties = plOuder.getHopRelaties();
            HopRelatie geborenInRelatie = hopRelaties.geborenInRelatie(Integer.parseInt(geboortedatum));
            return geborenInRelatie != null;
        }
        return false;
    }

    /**
     * v2b_1
     *
     * @return "Ja" als is staande huwelijk of partnerschap geboren, anders
     * "Nee"
     */
    public String v2b1IsStaandeHuwelijkOfPartnerschapGeboren() throws GezagException {
        String geboorteDatumKind = plPersoon.getPersoon().getGeboortedatum();
        Ouder1 lOuder1 = plPersoon.getOuder1();
        Ouder2 lOuder2 = plPersoon.getOuder2();
        /*
        Nu vastgesteld is dat het kind 1 ouder heeft kan gecontroleerd worden of deze ouder
        een relatie had tijdens de geboorte van het kind, indien dat het geval is, dan wordt gekeken
        of dit nog steeds de actuele relatie is. Het maakt daarbij niet uit of de ouder overleden is,
        de registratie in het BRP is in zo'n geval bij de ouder onveranderd, bij de partner daarentegen
        is de relatie ontbonden met als reden overlijden. Controleer of de BSN van de partner bij geboorte
        gelijk is aan de BSN van de actuele partner. Als dat het geval is, heeft deze partner ook gezag.
         */
        if (lOuder1 != null && isValideGeslachtsnaam(lOuder1.getGeslachtsnaam())){
            preconditieCheckGeregistreerd("ouder1", getPlOuder1());
            if (heeftOuderRelatieBijGeboorteKind(plOuder1, geboorteDatumKind) && !plPersoon.ontkenningOuderschapDoorOuder2())
                return V2B_1_JA;
        }

        if (lOuder2!=null && isValideGeslachtsnaam(lOuder2.getGeslachtsnaam())){
            preconditieCheckGeregistreerd("ouder2", getPlOuder2());
            if (heeftOuderRelatieBijGeboorteKind(plOuder2, geboorteDatumKind) && !plPersoon.ontkenningOuderschapDoorOuder1())
                return V2B_1_JA;
        }
        return V2B_1_NEE;
    }

    /**
     * v3_1
     *
     * @return Ja, als er sprake is van een recente gebeurtenis anders Nee
     */
    public String v31IsErSprakeVanEenRecenteGebeurtenis() throws GezagException {
        Gezagsverhouding gezagsverhouding = plPersoon.getGezagsverhouding();
        String indicatieGezagMinderjarige = gezagsverhouding.getIndicatieGezagMinderjarige();
        String ingangsdatumGeldigheidGezag = gezagsverhouding.getIngangsdatumGeldigheidGezag();
        // Check voor ontkenning erkenning, als er niet erkend is, dan is er sprake van ontkenning erkenning
        if ((indicatieGezagMinderjarige.equals(INDICATIE_GEZAG_BEIDE_OUDERS)) && (!plPersoon.heeftTweeOuders())) {
            return V3_1_JA;
        }
        // Preconditie ingangsdatum geldigheid gezag moet een geldige datum zijn en niet de standaard waarde
        if (Objects.equals(ingangsdatumGeldigheidGezag, STANDAARD_WAARDE_INGANGSDATUM_GELDIGHEID_GEZAG)) {
            throw new AfleidingsregelException("Preconditie: Ingangsdatum geldigheid gezag moet een valide datum bevatten");
        }
        // Controleer op adoptie na uitspraak gezag, als adoptie heeft plaatsgevonden na de uitspraak
        // dan is er sprake van een recente gebeurtenis
        if (plPersoon.adoptieNaIngangsGeldigheidsdatum()) {
            return V3_1_JA;
        }
        // Controleer op reparatiehuwelijk alleen als gezag aan een van de ouders is toegewezen
        // In geval van een reparatiehuwelijk na ingangsdatum is er sprake van een recente gebeurtenis
        if ((indicatieGezagMinderjarige.equals(INDICATIE_GEZAG_OUDER1) || indicatieGezagMinderjarige.equals(INDICATIE_GEZAG_OUDER2))
                && plPersoon.heeftTweeOuders() && plPersoon.beideOudersHebbenEenBSN()) {
            Persoonslijst lplOuder1 = getPlOuder1();
            Persoonslijst lplOuder2 = getPlOuder2();
            if (nuGehuwdOudersNaGeldigheidsdatum(lplOuder1, lplOuder2,
                    ingangsdatumGeldigheidGezag)) {
                return V3_1_JA;
            }
        }
        return V3_1_NEE;

    }

    /**
     * v3_2
     *
     * @return 1/2/12/1D/2D/D/ONBEKEND
     */
    public String v32IndicatieGezagMinderjarige() {
        return plPersoon.getGezagsverhouding().getIndicatieGezagMinderjarige();
    }

    /**
     * v4a_2
     *
     * @return JA_OUDER1/JA_OUDER2/NEE/BEIDEN/ONBEKEND
     */
    public String v4a2OudersOverledenOfOnbevoegdTotGezag() throws GezagException {
        preconditieCheckOudersGeregistreerd();
        boolean ouder1OverledenOfOnbevoegd = plOuder1.isOverledenOfOnbevoegd();
        boolean ouder2OverledenOfOnbevoegd = plOuder2.isOverledenOfOnbevoegd();
        String result;
        if (ouder1OverledenOfOnbevoegd && ouder2OverledenOfOnbevoegd) {
            result = V4A_2_JA_BEIDEN;
        } else if (ouder1OverledenOfOnbevoegd) {
            result = V4A_2_JA_OUDER1;
        } else if (ouder2OverledenOfOnbevoegd) {
            result = V4A_2_JA_OUDER2;
        } else {
            result = V4A_2_NEE;
        }
        return result;
    }

    /**
     * sv4a_3
     *
     * @return JA/NEE
     */
    public String v4a3OuderOverledenOfOnbevoegdTotGezag() throws GezagException {
        String result = "";
        // Preconditie: minimaal 1 ouder moet een BSN hebben
        Ouder1 persoonOuder1 = plPersoon.getOuder1();
        Ouder2 persoonOuder2 = plPersoon.getOuder2();
        if ((persoonOuder1 == null || persoonOuder1.getBsn() == null)
                && (persoonOuder2 == null || persoonOuder2.getBsn() == null)) {
            throw new AfleidingsregelException("Preconditie: Ouder moet een BSN hebben");
        }
        Persoonslijst lplOuder1 = getPlOuder1();
        Persoonslijst lplOuder2 = getPlOuder2();
        if ((lplOuder1 == null) && (lplOuder2 == null)) {
            throw new AfleidingsregelException("Preconditie: Ouder moet geregistreerd staan in het BRP");
        }
        if (lplOuder1 != null
                && (!lplOuder1.isOverledenOfOnbevoegd())) {
            result = V4A_3_NEE_OUDER1;
        }
        if (lplOuder2 != null
                && (!lplOuder2.isOverledenOfOnbevoegd())) {
            if (result.equals(V4A_3_NEE_OUDER1)) {
                result = V4A_3_NEE;
            } else {
                result = V4A_3_NEE_OUDER2;
            }
        }
        if (result.isEmpty()) {
            result = V4A_3_JA;
        }
        return result;
    }

    /**
     * Beslissingstabel met drie inputs ouder, ouder overleden of onbevoegd, partner overleden of onbevoegd en
     * een output die teruggeven moet worden. Wordt gebruikt in v4b1OuderOfPartnerOverledenOfOnbevoegdTotGezag.
     */
    private static Map<String, String> ouderOfPartnerOverledenOfOnbevoegdTotGezagMap = Map.of(
        "ouder1,true,true", V4B_1_JA_BEIDEN,
        "ouder1,true,false", V4B_1_JA_OUDER1,
        "ouder1,false,true", V4B_1_JA_NIET_OUDER1,
        "ouder1,false,false", V4B_1_NEE,
        "ouder2,true,true", V4B_1_JA_BEIDEN,
        "ouder2,true,false", V4B_1_JA_OUDER2,
        "ouder2,false,true", V4B_1_JA_NIET_OUDER2,
        "ouder2,false,false", V4B_1_NEE
    );

    /* v4b1OuderOfPartnerOverledenOfOnbevoegdTotGezag
     * @return if ouder of partner overleden of onbevoegd tot gezag is
     */
    public String v4b1OuderOfPartnerOverledenOfOnbevoegdTotGezag() throws GezagException {


        String key;
        // Preconditie: minimaal 1 ouder moet een BSN hebben
        Persoonslijst lplOuder1 = getPlOuder1();
        Persoonslijst lplOuder2 = getPlOuder2();
        Persoonslijst lplNietOuder = getPlNietOuder();
        // Preconditie: minimaal 1 ouder moet geregistreerd staan in BRP
        if (lplOuder1 == null && lplOuder2 == null) {
            throw new AfleidingsregelException("Preconditie: Minimaal 1 ouder moet geregistreerd staan in BRP");
        }
        if (lplNietOuder == null) {
            throw new AfleidingsregelException("Preconditie: niet_ouder  moet geregistreerd staan in BRP");
        }
        if (lplOuder1!=null) {
            key = "ouder1,"+(lplOuder1.isOverledenOfOnbevoegd())+","+(lplNietOuder.isOverledenOfOnbevoegd());
            return ouderOfPartnerOverledenOfOnbevoegdTotGezagMap.get(key);
        }
        key = "ouder2,"+(lplOuder2.isOverledenOfOnbevoegd())+","+(lplNietOuder.isOverledenOfOnbevoegd());
        return ouderOfPartnerOverledenOfOnbevoegdTotGezagMap.get(key);
    }

    /*
    Probeer hier niet de ouders of nietouders op te halen, in dit stadium zijn
    deze al lang opgehaald of was dit onnodig.
     */
    public Set<String> bepalenGezagdragers(final ARAntwoordenModel arAntwoordenModel) {
        Set<String> gezagsdragers = new HashSet<>();
        if (arAntwoordenModel != null) {
            if (arAntwoordenModel.getGezagOuder1() != null && arAntwoordenModel.getGezagOuder1().equals("Ja")
                    && (plOuder1 != null)) {
                gezagsdragers.add(plOuder1.getPersoon().getBsn());
            }
            if (arAntwoordenModel.getGezagOuder2() != null && arAntwoordenModel.getGezagOuder2().equals("Ja")
                    && (plOuder2 != null)) {
                gezagsdragers.add(plOuder2.getPersoon().getBsn());
            }
            if ((((arAntwoordenModel.getGezagNietOuder1() != null && arAntwoordenModel.getGezagNietOuder1().equals("Ja"))
                    || (arAntwoordenModel.getGezagNietOuder2() != null && arAntwoordenModel.getGezagNietOuder2().equals("Ja")))
                    && (plNietOuder != null))) {
                gezagsdragers.add(plNietOuder.getPersoon().getBsn());
            }
        }

        return gezagsdragers;
    }

    /**
     * @return of er velden in onderzoek waren (010120 en 080910 worden
     * gefiltered)
     */
    public boolean warenVeldenInOnderzoek() {
        List<String> veldenInOnderzoek = plPersoon.getUsedVeldenInOnderzoek();
        if (plOuder1 != null) {
            veldenInOnderzoek.addAll(plOuder1.getUsedVeldenInOnderzoek());
        }

        if (plOuder2 != null) {
            veldenInOnderzoek.addAll(plOuder2.getUsedVeldenInOnderzoek());
        }

        if (plNietOuder != null) {
            veldenInOnderzoek.addAll(plNietOuder.getUsedVeldenInOnderzoek());
        }

        if (!veldenInOnderzoek.isEmpty()) {
            log.info("Velden waren in onderzoek: {}", veldenInOnderzoek);
        }

        veldenInOnderzoek = filterVelden(veldenInOnderzoek);

        return !veldenInOnderzoek.isEmpty();
    }

    /**
     * @return velden in onderzoek per persoon
     */
    public VeldenInOnderzoek getVeldenInOnderzoek() {
        VeldenInOnderzoek velden = new VeldenInOnderzoek();
        velden.setPersoon(plPersoon.getUsedVeldenInOnderzoek());
        if (plOuder1 != null) {
            velden.setOuder1(plOuder1.getUsedVeldenInOnderzoek());
        }
        if (plOuder2 != null) {
            velden.setOuder2(plOuder2.getUsedVeldenInOnderzoek());
        }
        if (plNietOuder != null) {
            velden.setNietOuder(plNietOuder.getUsedVeldenInOnderzoek());
        }

        return velden;
    }

    /**
     * Verwijder velden in onderzoek die we niet willen overwegen
     *
     * @param veldenInOnderzoek lijst met velden in onderzoek om te filteren
     */
    private List<String> filterVelden(final List<String> veldenInOnderzoek) {
        return veldenInOnderzoek.stream()
                .filter(v -> !v.equals("bsn") && !v.equals("gemeente van inschrijving"))
            .toList();
    }

    private Persoonslijst getPlOuder1() {
        if (plOuder1 == null && gezagService != null) {
            plOuder1 = gezagService.ophalenOuder1(plPersoon, transaction);
        }

        return plOuder1;
    }

    private Persoonslijst getPlOuder2() {
        if (plOuder2 == null && gezagService != null) {
            plOuder2 = gezagService.ophalenOuder2(plPersoon, transaction);
        }

        return plOuder2;
    }

    private Persoonslijst getPlNietOuder() {
        if (plNietOuder == null && gezagService != null) {
            plNietOuder = gezagService.ophalenNietOuder(plPersoon, plOuder1, plOuder2, transaction);
        }

        return plNietOuder;
    }

    private HuwelijkOfPartnerschap ouderGetHetHuwelijkOfPartnerschap(
            Persoonslijst persoonslijst1, Persoonslijst persoonslijst2) {
        if ((persoonslijst1 == null) || (persoonslijst2 == null)) {
            return null;
        }
        for (HuwelijkOfPartnerschap huwelijkOfPartnerschap : persoonslijst1.getHuwelijkOfPartnerschappen()) {
            if (persoonslijst2.getPersoon() != null
                    && persoonslijst2.getPersoon().getBsn().equals(huwelijkOfPartnerschap.getBsnPartner())) {
                return huwelijkOfPartnerschap;
            }
        }
        return null;
    }

    private boolean isHuwelijkOfPartnerschapTussenOudersActueel(
        HuwelijkOfPartnerschap hopOuder1,
        HuwelijkOfPartnerschap hopOuder2
    ) {
        // Alleen als het huwelijk actueel is bij beide ouders is er sprake van een actueel huwelijk of partnerschap
        return org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder1.getDatumVoltrokken())
            && org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder2.getDatumVoltrokken());
    }

    private boolean ouderGescheiden(HuwelijkOfPartnerschap hop, String geboortedatumKind) {
        return (hop.getRedenOntbinding() != null && hop.getRedenOntbinding().equals("S")
                && Integer.parseInt(hop.getDatumOntbinding()) < Integer.parseInt(geboortedatumKind));
    }


    // Haal de lijst met huwelijken/partnerschappen tussen een ouder en diens partner op
    private List<HuwelijkOfPartnerschap> getHuwelijkOfPartnerschap(Persoonslijst plOuder, String bsnPartner) {
        List<HuwelijkOfPartnerschap> hopList = new ArrayList<>();
        if (plOuder.getHuwelijkOfPartnerschappen() != null) {
            for (HuwelijkOfPartnerschap hop : plOuder.getHuwelijkOfPartnerschappen()) {
                if (bsnPartner.equals(hop.getBsnPartner())) {
                    hopList.add(hop);
                }
            }
        }
        return hopList;
    }

    private boolean nuGehuwdOudersNaGeldigheidsdatum(
            Persoonslijst plOuder1, Persoonslijst plOuder2, String geldigheidsdatum) {
        if ((plOuder1 == null) || (plOuder2 == null)) {
            return false;
        }
        List<HuwelijkOfPartnerschap> hopListOuder1 = getHuwelijkOfPartnerschap(plOuder1, plOuder2.getPersoon().getBsn());
        List<HuwelijkOfPartnerschap> hopListOuder2 = getHuwelijkOfPartnerschap(plOuder2, plOuder1.getPersoon().getBsn());
        if (hopListOuder1.isEmpty() || hopListOuder2.isEmpty()) {
            return false;
        }
        String hopOuder1Actueel = hopListOuder1.get(0).getDatumVoltrokken();
        String hopOuder2Actueel = hopListOuder2.get(0).getDatumVoltrokken();
        return (org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder1Actueel)
                && org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder2Actueel))
                && (Integer.parseInt(geldigheidsdatum) <= Integer.parseInt(hopOuder1Actueel))
                && (Integer.parseInt(geldigheidsdatum) <= Integer.parseInt(hopOuder2Actueel))
                && (hopListOuder1.size() > 1)
                && (hopListOuder2.size() > 1);
    }
}
