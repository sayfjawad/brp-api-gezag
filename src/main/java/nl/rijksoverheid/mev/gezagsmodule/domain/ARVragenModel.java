package nl.rijksoverheid.mev.gezagsmodule.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.service.GezagService;
import nl.rijksoverheid.mev.transaction.Transaction;

import java.util.*;

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
    private static final String V2A_1_NEE_NIETIG_VERKLAARD = "Nee_nietig_verklaard";
    private static final String V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR = "Nee_nooit";
    private static final String V2A_1_NEE_GESCHEIDEN_NA_GEBOORTE = "Nee_gescheiden";
    private static final String V2A_2_JA = "Ja";
    private static final String V2A_2_NEE = "Nee";
    private static final String V2A_3_VOOR = "Voor";
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

    public ARVragenModel(final Persoonslijst _plPersoon,
            final GezagService gezagService,
            final Transaction transaction) {
        this.plPersoon = _plPersoon;
        this.gezagService = gezagService;
        this.transaction = transaction;
    }

    /**
     * v1_1
     *
     * @return "Ja" als isIngezeteneInBRP anders "Nee"
     */
    public String v1_1_isPersoonIngezeteneInBRP() {
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
     * Vraag {@link ARVragenModel#v1_1_isPersoonIngezeteneInBRP()} in combinatie met {@code isAlsMinderjarigOpgeschort}
     * leidt ertoe dat het enige mogelijke antwoord {@code V1_2_NEE_OVERLEDEN} is.
     *
     * @return of minderjarig en niet overleden is
     */
    public String v1_2_IsPersoonMinderjarigEnNietOverleden() throws GezagException {
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
    public String v1_3_isNaarHetBuitenlandGeemigreerdGeweest() {
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
    public String v1_3a_isGeborenInBuitenland() {
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
    public String v1_3b_IsGeadopteerdMetNlAkte() {
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
    public String v1_4_IsUitspraakGezagAanwezig() {
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
    public String v2_1_HoeveelJuridischeOudersHeeftMinderjarige() {
        return plPersoon.hoeveelJuridischeOuders();
    }

    /**
     * v2a_1
     *
     * @return JA GEHUWD OF PARTNERS/NEE NA GEBOORTE NOOIT GEHUWD PARTNERS
     * GEWEEST MET ELKAAR / NEE GESCHEIDEN NA GEBOORTE
     */
    public String v2a_1_ZijnJuridischeOudersNuMetElkaarGehuwdOfPartners() throws GezagException {
        String geboortedatumKind = plPersoon.getPersoon().getGeboortedatum();
        if (!plPersoon.heeftTweeOuders()) {
            throw new AfleidingsregelException("Preconditie: Kind moet twee ouders hebben");
        }
        Persoonslijst plOuder1 = getPlOuder1();
        Persoonslijst plOuder2 = getPlOuder2();
        boolean oudersGeregistreerdinBrp
                = ((plOuder1 != null)
                && (plOuder2 != null)
                && (plOuder1.isNietIngeschrevenInRNI())
                && (plOuder2.isNietIngeschrevenInRNI())
                && (plOuder1.isNietGeemigreerd())
                && (plOuder2.isNietGeemigreerd()));
        if (!oudersGeregistreerdinBrp) {
            throw new AfleidingsregelException("Preconditie: Ouders moeten in BRP geregistreerd staan");
        }
        HuwelijkOfPartnerschap hopOuder1 = ouderGetHetHuwelijkOfPartnerschap(plOuder1, plOuder2);
        HuwelijkOfPartnerschap hopOuder2 = ouderGetHetHuwelijkOfPartnerschap(plOuder2, plOuder1);
        // Als er geen huwelijk tussen de ouders gevonden wordt is het antwoord "Nee_nooit"
        if (hopOuder1 == null || hopOuder2 == null) {
            return V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR;
        } else if (isHOPActueelOfOntbondenDoorOverlijden(hopOuder1, hopOuder2)) {
            return V2A_1_JA_GEHUWD_OF_PARTNERS;
        } else if (huwelijkNietigVerklaard(hopOuder1) || huwelijkNietigVerklaard(hopOuder2)) {
            return V2A_1_NEE_NIETIG_VERKLAARD;
        } else if (ouderGescheiden(hopOuder1, geboortedatumKind) || ouderGescheiden(hopOuder2, geboortedatumKind)) {
            return V2A_1_NEE_NA_GEBOORTE_NOOIT_GEHUWD_PARTNERS_GEWEEST_MET_ELKAAR;
        } else {
            return V2A_1_NEE_GESCHEIDEN_NA_GEBOORTE;
        }
    }

    /**
     * v2a_2
     *
     * @return "Ja" als is adoptief ouders, anders "Nee"
     */
    public String v2a_2_AdoptiefOuders() {
        return (plPersoon.geadopteerdMetNlAkte()) ? V2A_2_JA : V2A_2_NEE;
    }

    /**
     * v2a_3
     *
     * @return VOOR of NA
     */
    public String v2a_3_ErkenningNa01012023() {
        String result = V2A_3_VOOR;
        // Als het kind erkend is, zoek dan uit of dit voor of na 01-01-2023 is gebeurd
        // Na 01-01-2023 hebben beide ouders gezag
        // Voor 01-01-2023 heeft de erkenner geen gezag
        if (plPersoon.erkend()) {
            Ouder1 persoonOuder1 = plPersoon.getOuder1();
            Ouder2 persoonOuder2 = plPersoon.getOuder2();
            if ((persoonOuder1 != null && Integer.parseInt(persoonOuder1.getDatumIngangFamiliebetrekking()) >= DATE_JAN_1_2023)
                    || (persoonOuder2 != null && Integer.parseInt(persoonOuder2.getDatumIngangFamiliebetrekking()) >= DATE_JAN_1_2023)) {
                result = V2A_3_NA;
            }
        }
        return result;
    }

    /**
     * v2b_1
     *
     * @return "Ja" als is staande huwelijk of partnerschap geboren, anders
     * "Nee"
     */
    public String v2_b_1_IsStaandeHuwelijkOfPartnerschapGeboren() throws GezagException {
        String geboorteDatumKind = plPersoon.getPersoon().getGeboortedatum();
        Integer geboorteDatumKindInt = Integer.parseInt(geboorteDatumKind);
        HopRelaties hopRelaties;
        HopRelatie geborenInRelatie;
        Ouder1 persoonOuder1 = plPersoon.getOuder1();
        Ouder2 persoonOuder2 = plPersoon.getOuder2();
        /*
        Preconditie kind heeft 1 ouder op de persoonslijst
         */
        String ouder1Geslachtsnaam = (persoonOuder1 != null) ? persoonOuder1.getGeslachtsnaam() : null;
        String ouder2Geslachtsnaam = (persoonOuder2 != null) ? persoonOuder2.getGeslachtsnaam() : null;
        if ((ouder1Geslachtsnaam == null || ouder1Geslachtsnaam.isBlank())
                == (ouder2Geslachtsnaam == null || ouder2Geslachtsnaam.isBlank())) {
            throw new AfleidingsregelException("Kind heeft 0 of 2 ouders.");
        }
        /*
        Nu vastgesteld is dat het kind 1 ouder heeft kan gecontroleerd worden of deze ouder
        een relatie had tijdens de geboorte van het kind, indien dat het geval is, dan wordt gekeken
        of dit nog steeds de actuele relatie is. Het maakt daarbij niet uit of de ouder overleden is,
        de registratie in het BRP is in zo'n geval bij de ouder onveranderd, bij de partner daarentegen
        is de relatie ontbonden met als reden overlijden. Controleer of de BSN van de partner bij geboorte
        gelijk is aan de BSN van de actuele partner. Als dat het geval is, heeft deze partner ook gezag.
         */
        if (ouder1Geslachtsnaam != null){
            if (persoonOuder1.getBsn() == null) {
                throw new AfleidingsregelException("Preconditie: Ouder1 moet een BSN hebben");
            }
            Persoonslijst plOuder1 = getPlOuder1();
            if (plOuder1 == null){
                throw new AfleidingsregelException("Preconditie: Ouder1 moet geregistreerd staan in het BRP");
            }
            List<HuwelijkOfPartnerschap> hopPlOuder1 = plOuder1.getHuwelijkOfPartnerschappen();
            if (!hopPlOuder1.isEmpty() && (hopPlOuder1.get(0).getBsnPartner() != null)) {
                hopRelaties = plOuder1.getHopRelaties();
                geborenInRelatie = hopRelaties.geborenInRelatie(geboorteDatumKindInt);
                if (geborenInRelatie != null) return V2B_1_JA;
            }
        }

        if (ouder2Geslachtsnaam != null){
            if (persoonOuder2.getBsn() == null) {
                throw new AfleidingsregelException("Preconditie: Ouder2 moet een BSN hebben");
            }
            Persoonslijst plOuder2 = getPlOuder2();
            if (plOuder2 == null){
                throw new AfleidingsregelException("Preconditie: Ouder2 moet geregistreerd staan in het BRP");
            }
            List<HuwelijkOfPartnerschap> hopPlOuder2 = plOuder2.getHuwelijkOfPartnerschappen();
            // Heeft de ouder nu een relatie?
            if (!hopPlOuder2.isEmpty() && (hopPlOuder2.get(0).getBsnPartner() != null)) {
                hopRelaties = plOuder2.getHopRelaties();
                geborenInRelatie = hopRelaties.geborenInRelatie(geboorteDatumKindInt);
                if (geborenInRelatie != null) return V2B_1_JA;
            }
        }
        return V2B_1_NEE;
    }

    /**
     * v3_1
     *
     * @return Ja, als er sprake is van een recente gebeurtenis anders Nee
     */
    public String v3_1_IsErSprakeVanEenRecenteGebeurtenis() throws GezagException {
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
            Persoonslijst plOuder1 = getPlOuder1();
            Persoonslijst plOuder2 = getPlOuder2();
            if (nuGehuwdOudersNaGeldigheidsdatum(plOuder1, plOuder2,
                    plPersoon.getGezagsverhouding().getIngangsdatumGeldigheidGezag())) {
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
    public String v3_2_IndicatieGezagMinderjarige() {
        return plPersoon.getGezagsverhouding().getIndicatieGezagMinderjarige();
    }

    /**
     * v4a_2
     *
     * @return JA_OUDER1/JA_OUDER2/NEE/BEIDEN/ONBEKEND
     */
    public String v4a_2_OudersOverledenOfOnbevoegdTotGezag() throws GezagException {
        if (!plPersoon.beideOudersHebbenEenBSN()) {
            throw new AfleidingsregelException("Preconditie: Ouders moeten een BSN hebben");
        }
        Persoonslijst plOuder1 = getPlOuder1();
        Persoonslijst plOuder2 = getPlOuder2();
        if ((plOuder1 == null) || (plOuder2 == null)) {
            throw new AfleidingsregelException("Preconditie: Ouders moeten geregistreerd staan in het BRP");
        }
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
    public String v4a_3_OuderOverledenOfOnbevoegdTotGezag() throws GezagException {
        String result = "";
        // Preconditie: minimaal 1 ouder moet een BSN hebben
        Ouder1 persoonOuder1 = plPersoon.getOuder1();
        Ouder2 persoonOuder2 = plPersoon.getOuder2();
        if ((persoonOuder1 == null || persoonOuder1.getBsn() == null)
                && (persoonOuder2 == null || persoonOuder2.getBsn() == null)) {
            throw new AfleidingsregelException("Preconditie: Ouder moet een BSN hebben");
        }
        Persoonslijst plOuder1 = getPlOuder1();
        Persoonslijst plOuder2 = getPlOuder2();
        if ((plOuder1 == null) && (plOuder2 == null)) {
            throw new AfleidingsregelException("Preconditie: Ouder moet geregistreerd staan in het BRP");
        }
        if (plOuder1 != null
                && plPersoon.getOuder1().getDatumIngangFamiliebetrekking().equals(plPersoon.getPersoon().getGeboortedatum())
                && (!plOuder1.isOverledenOfOnbevoegd())) {
            result = V4A_3_NEE_OUDER1;
        }
        if (plOuder2 != null
                && plPersoon.getOuder2().getDatumIngangFamiliebetrekking().equals(plPersoon.getPersoon().getGeboortedatum())
                && (!plOuder2.isOverledenOfOnbevoegd())) {
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
     * v4b_1
     *
     * @return if ouder of partner overleden of onbevoegd tot gezag is
     */
    public String v4b_1_OuderOfPartnerOverledenOfOnbevoegdTotGezag() throws GezagException {
        // Preconditie: minimaal 1 ouder moet een BSN hebben
        Ouder1 persoonOuder1 = plPersoon.getOuder1();
        Ouder2 persoonOuder2 = plPersoon.getOuder2();
        if ((persoonOuder1 == null || persoonOuder1.getBsn() == null)
            && (persoonOuder2 == null || persoonOuder2.getBsn() == null)) {
            throw new AfleidingsregelException("Preconditie: Minimaal 1 ouder moet een BSN hebben");
        }
        Persoonslijst plOuder1 = getPlOuder1();
        Persoonslijst plOuder2 = getPlOuder2();
        // Preconditie: minimaal 1 ouder moet geregistreerd staan in BRP
        if ((plOuder1 == null) && (plOuder2 == null)) {
            throw new AfleidingsregelException("Preconditie: Minimaal 1 ouder moet geregistreerd staan in BRP");
        }
        Persoonslijst plNietOuder = getPlNietOuder();
        if ( plNietOuder == null) {
            throw new AfleidingsregelException("Preconditie: niet_ouder  moet geregistreerd staan in BRP");
        }
        if ((plOuder1 != null) && (plNietOuder != null)) {
                if (plOuder1.isOverledenOfOnbevoegd()) {
                    return plNietOuder.isOverledenOfOnbevoegd() ? V4B_1_JA_BEIDEN : V4B_1_JA_OUDER1;
                } else {
                    return plNietOuder.isOverledenOfOnbevoegd() ? V4B_1_JA_NIET_OUDER1 : V4B_1_NEE;
                }
        }

        if ((plOuder2 != null) && (plNietOuder != null)) {
                if (plOuder2.isOverledenOfOnbevoegd()) {
                    return plNietOuder.isOverledenOfOnbevoegd() ? V4B_1_JA_BEIDEN : V4B_1_JA_OUDER2;
                } else {
                    return plNietOuder.isOverledenOfOnbevoegd() ? V4B_1_JA_NIET_OUDER2 : V4B_1_NEE;
                }
        }
        return "";
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
                .filter(v -> !v.equals("010120") && !v.equals("080910"))
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

    /**
     * Als deze wordt aangeroepen weet je al dat er een relatie is tussen de
     * twee ouders alleen is nog niet vastgesteld wat die relatie is
     */
    private boolean isHOPActueelOfOntbondenDoorOverlijden(HuwelijkOfPartnerschap hopOuder1,
            HuwelijkOfPartnerschap hopOuder2) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder1.getDatumVoltrokken())
                || org.apache.commons.lang3.StringUtils.isNotBlank(hopOuder2.getDatumVoltrokken())) {
            return true;
        }
        return checkHopReden(hopOuder1) || checkHopReden(hopOuder2);
    }

    private boolean checkHopReden(HuwelijkOfPartnerschap huwelijkOfPartnerschap) {
        for (String reden : HUWELIJK_OF_PARTNERSCHAP_REDEN_ONTBINDING) {
            if (reden.equals(huwelijkOfPartnerschap.getRedenOntbinding())) {
                return true;
            }
        }
        return false;
    }

    private boolean ouderGescheiden(HuwelijkOfPartnerschap hop, String geboortedatumKind) {
        return (hop.getRedenOntbinding() != null && hop.getRedenOntbinding().equals("S")
                && Integer.parseInt(hop.getDatumOntbinding()) < Integer.parseInt(geboortedatumKind));
    }

    private boolean huwelijkNietigVerklaard(HuwelijkOfPartnerschap hop) {
        return (hop.getRedenOntbinding() != null
                && hop.getRedenOntbinding().equals(INDICATIE_HUWELIJK_NIETIG_VERKLAARD));
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
