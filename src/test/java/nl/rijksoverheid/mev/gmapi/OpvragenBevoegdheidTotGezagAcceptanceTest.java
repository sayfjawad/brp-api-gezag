package nl.rijksoverheid.mev.gmapi;

import java.util.ArrayList;
import nl.rijksoverheid.mev.GezagApplication;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.openapitools.model.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.openapitools.OpenApiGeneratorApplication;
import java.util.stream.Stream;
import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
    classes = {GezagApplication.class, OpenApiGeneratorApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = "app.clock=2023-02-01T00:00:00Z"
)
class OpvragenBevoegdheidTotGezagAcceptanceTest {

    private static final String OIN = "00000004003214345001";
    private static final String UITLEG = "Uitleg";
    private static final String TYPE_NIET_TE_BEPALEN = "GezagNietTeBepalen";
    private static final String TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG = "TweehoofdigOuderlijkGezag";

    @Autowired
    WebTestClient webTestClient;

    static Stream<Arguments> opvragenBevoegdheidTotGezag() {
        return Stream.of(
            // (testcase, input, expected)

            // lg01_001 gehuwd, 4 minderjarige kinderen geboren vóór huwelijk, 2 minderjarige kinderen geboren ná huwelijk,
            // 1 overleden kind, 1 meerderjarig kind, 4 kinderen onder gezag
            meerderjarigeArguments("Lg01_001", "999998778", Set.of(
                new Gezagsrelatie("999998316", "OG2"),
                new Gezagsrelatie("999999746", "OG1"),
                new Gezagsrelatie("999998341", "OG2"),
                new Gezagsrelatie("999998328", "OG2"))),
            // lg01_002 gehuwd, 4 minderjarige kinderen geboren vóór huwelijk, 2 minderjarige kinderen geboren ná huwelijk,
            // 1 overleden kind, 1 meerderjarig kind, 4 kinderen onder gezag
            meerderjarigeArguments("Lg01_002", "999998791", Set.of(
                new Gezagsrelatie("999999734", "OG1"),
                new Gezagsrelatie("999998316", "OG2"),
                new Gezagsrelatie("999998341", "OG2"),
                new Gezagsrelatie("999998328", "OG2"))),
            // lg01_003 minderjarig, ouders gehuwd op moment geboorte, geen categorie 11
            minderjarigeArguments("Lg01_003", "999998316", "OG2", List.of("999998778", "999998791")),
            // lg01_004 minderjarig, overleden, ouders gehuwd op moment geboorte, geen categorie 11
            Arguments.of("Lg01_004", "999998353", Set.of()),
            // lg01_005 minderjarig, erkend als ongeboren vrucht, geen categorie 11, ouders gehuwd ná de geboorte
            minderjarigeArguments("Lg01_005", "999998341", "OG2", List.of("999998791", "999998778")),
            // lg01_006 minderjarig, ouder dan 12, erkend als ongeboren vrucht, geen categorie 11,
            // ouders gehuwd ná de geboorte
            minderjarigeArguments("Lg01_006", "999998328", "OG2", List.of("999998791", "999998778")),
            // lg01_007 minderjarige asielzoeker, puntouders (onbekende ouders), geen categorie 11
            minderjarigeArguments("Lg01_007", "999998365", "N", List.of("")),
            // lg01_008 vondeling in Nederland, puntmoeder (onbekende ouder), lege categorie 03 (juridisch geen ouder),
            // geen categorie 11
            minderjarigeArguments("Lg01_008", "999998377", "N", List.of("")),
            // lg01_009 vondeling in China, juridisch geen ouders, geen categorie 11
            minderjarigeArguments("Lg01_009", "999998389", "N", List.of("")),
            // lg01_010 ongehuwde meerderjarige moeder met 2 minderjarige niet erkende kinderen
            meerderjarigeArguments("Lg01_010", "999998390", Set.of(
                new Gezagsrelatie("999999278", "OG1"),
                new Gezagsrelatie("999999266", "OG1"))),
            // lg01_011 minderjarige, moeder opgenomen in categorie 02, categorie 03 leeg, geen categorie 11
            minderjarigeArguments("Lg01_011", "999999266", "OG1", List.of("999998390")),
            // lg01_012 minderjarige, moeder opgenomen in categorie 03, categorie 02 leeg, geen categorie 11
            minderjarigeArguments("Lg01_012", "999999278", "OG1", List.of("999998390")),
            // lg01_013 ongehuwde minderjarige moeder met 1 minderjarig niet erkend kind
            minderjarigeArguments("Lg01_013", "999998420", "N", List.of("")),
            // lg01_014 minderjarige, minderjarig niet erkend kind, moeder ook minderjarig, geen categorie 11
            minderjarigeArguments("Lg01_014", "999998432", "G", List.of("")),
            // lg01_015 ongehuwde meerderjarige moeder met 1 minderjarig niet-erkend kind,
            // bij geboorte minderjarig inmiddels meerderjarig
            meerderjarigeArguments("Lg01_015", "999998444", Set.of(
                new Gezagsrelatie("999998456", "OG1")
            )),
            // lg01_016 minderjarig niet erkend kind, moeder minderjarig, bij geboorte,
            // inmiddels meerderjarig, geen categorie 11
            minderjarigeArguments("Lg01_016", "999998456", "OG1", List.of("999998444")),
            // lg01_017 gehuwd, echtgenoot niet ingeschreven in Nederland, minderjarig kind geboren tijdens huwelijk
            meerderjarigeArguments("Lg01_017", "999998468", Set.of()),
            // lg01_018 minderjarig kind, geboren tijdens huwelijk ouders, geen categorie 11,
            // vader niet ingeschreven in BRP, kind Nederlandse nationaliteit + Beëindigde vreemde nationaliteit
            minderjarigeArguments("Lg01_018", "999998481", "N", List.of("")),
            // lg01_019 gehuwd, 3 minderjarige kinderen geboren tijdens huwelijk ouders, echtgenoot
            // en 2 kinderen geëmigreerd (RNI), 1 kind weer teruggekeerd (immigratie)
            meerderjarigeArguments("Lg01_019", "999998493", Set.of()),
            // lg01_020 gehuwd,2 minderjarige kinderen geboren tijdens huwelijk ouders, zelf +
            // 2 kinderen geëmigreerd (RNI), 1 kind weer teruggekeerd (immigratie).
            meerderjarigeArguments("Lg01_020", "999998511", Set.of(
                new Gezagsrelatie("999998511", "N"))),
            // lg01_021 minderjarig kind, geboren tijdens huwelijk ouders, geen categorie 11, vader ingeschreven in RNI,
            // kind Nederlandse nationaliteit
            minderjarigeArguments("Lg01_021", "999998523", "N", List.of("")),
            // lg01_022 minderjarig kind, geboren tijdens huwelijk ouders, geen categorie 11, geëmigreerd,
            // ingeschreven in RNI evenals vader
            minderjarigeArguments("Lg01_022", "999998535", "N", List.of("")),
            // lg01_023 minderjarig kind, geboren tijdens huwelijk ouders, geen categorie 11, vader ingeschreven in de RNI,
            // kind Nederlandse nationaliteit, geëmigreerd geweest en geïmmigreerd
            minderjarigeArguments("Lg01_023", "999999175", "N", List.of("")),
            // lg01_024 gehuwd, echtgenoot overleden, 2 minderjarige kinderen
            meerderjarigeArguments("lg01_024", "999998547", Set.of(
                new Gezagsrelatie("999998560", "OG1"))),
            // lg01_025 meerderjarige gehuwd, 2 minderjarige kinderen, overleden
            meerderjarigeArguments("Lg01_025", "999998559", Set.of()),
            // lg01_026 minderjarig kind, geboren tijdens huwelijk ouders, vader (Ouder2) overleden, onder voogdij D
            minderjarigeArguments("Lg01_026", "999998572", "V", List.of("")),
            // lg01_027 minderjarig kind, geboren tijdens huwelijk ouders, vader (Ouder1) overleden, geen categorie 11
            minderjarigeArguments("Lg01_027", "999998560", "OG1", List.of("999998547")),
            // lg01_028 meerderjarige gehuwd, 1 minderjarig kind erkend vóór huwelijk,
            // 1 minderjarig kind geboren ná huwelijk, echtgenoot onder curatele
            meerderjarigeArguments("Lg01_028", "999998584", Set.of(
                new Gezagsrelatie("999998602", "OG1"),
                new Gezagsrelatie("999998614", "OG1"))),
            // lg01_029 meerderjarige gehuwd, 1 minderjarig kind erkend vóór huwelijk 1 minderjarig kind geboren ná huwelijk,
            // onder curatele
            meerderjarigeArguments("Lg01_029", "999998559", Set.of()),
            // lg01_030 minderjarige kind, geboren tijdens huwelijk ouders, vader (Ouder2) onder curatele
            minderjarigeArguments("Lg01_030", "999998602", "OG1", List.of("999998584")),
            // lg01_031 minderjarig erkend kind, ouders inmiddels gehuwd, vader (Ouder2) onder curatele
            minderjarigeArguments("Lg01_031", "999998614", "OG1", List.of("999998584")),
            // lg01_032 meerderjarige gescheiden, 1 minderjarig kind erkend vóór huwelijk, 1 minderjarig kind tijdens huwelijk
            meerderjarigeArguments("Lg01_032", "999998626", Set.of(
                new Gezagsrelatie("999998663", "OG2"),
                new Gezagsrelatie("999998651", "OG2"))),
            // lg01_033 meerderjarige gescheiden, 1 minderjarig kind erkend vóór huwelijk, 1 minderjarig kind tijdens huwelijk
            meerderjarigeArguments("lg01_033", "999998638", Set.of(
                new Gezagsrelatie("999998663", "OG2"),
                new Gezagsrelatie("999998651", "OG2"))),
            // lg01_034 minderjarig kind, erkend ná de geboorte, ouders vervolgens gehuwd en weer gescheiden,
            // categorie 11 met 12 vóór huwelijk ouders
            minderjarigeArguments("Lg01_034", "999998651", "OG2", List.of("999998626", "999998638")),
            // lg01_035 minderjarig kind, geboren tijdens huwelijk ouders, ouders inmiddels gescheiden, geen categorie 11
            minderjarigeArguments("Lg01_035", "999998663", "OG2", List.of("999998626", "999998638")),
            // lg01_036 meerderjarige gescheiden, 1 minderjarig erkend kind geboren ná de echtscheiding
            meerderjarigeArguments("Lg01_036", "999998675", Set.of(
                new Gezagsrelatie("999998705", "OG2"),
                new Gezagsrelatie("999998699", "OG1"))),
            // lg01_037 meerderjarige gescheiden, 1 minderjarig erkend kind geboren ná de echtscheiding
            meerderjarigeArguments("Lg01_037", "999998687", Set.of(
                new Gezagsrelatie("999998705", "OG2"))),
            // lg01_038 erkend kind, ouders gescheiden vóór de geboortedatum, geen categorie 11
            minderjarigeArguments("Lg01_038", "999998699", "OG1", List.of("999998675")),
            // lg01_039 erkend kind, ouders gescheiden vóór de geboortedatum, in categorie 11 aanduiding '12'
            minderjarigeArguments("Lg01_039", "999998705", "OG2", List.of("999998687", "999998675")),
            // lg01_040 erkend kind, ouders gescheiden vóór de geboortedatum, in categorie 11 aanduiding '12'
            minderjarigeArguments("Lg01_040", "999998717", "V", List.of("")),
            // lg01_041 meerderjarige 2 ontbonden huwelijken, 1 ontbonden partnerschap, 1 actueel huwelijk.
            // In eerste ontbonden huwelijk is in 2020 relatie gelegd waardoor deze 'bovenaan' ligt.
            // Totaal 4 kinderen, waarvan 2 ouder dan 18 en 2 jonger dan 18
            meerderjarigeArguments("Lg01_041", "999998729", Set.of(
                new Gezagsrelatie("999998808", "OG2"))),
            // lg01_042 meerderjarige gehuwd, 1 minderjarig kind
            meerderjarigeArguments("Lg01_042", "999998742", Set.of(
                new Gezagsrelatie("999998808", "OG2"))),
            // lg01_043 minderjarig, kind uit gehuwde ouders (moeder meerdere ontbonden huwelijken)
            minderjarigeArguments("Lg01_043", "999998808", "OG2", List.of("999998729", "999998742")),
            // lg01_044 meerderjarig, ontbonden partnerschap, 1 minderjarig kind, emigratie
            //            meerderjarigeArguments("Lg01_044a", "999998754", Set.of()),
            minderjarigeArguments("Lg01_044b", "999998754", "N", List.of("")),
            // lg01_045 minderjarige, emigratie
            minderjarigeArguments("Lg01_045", "999999242", "N", List.of("")),
            // lg01_046 meerderjarige actueel gehuwd en gescheiden, 1 meerderjarig kind, 1 minderjarig erkend kind
            meerderjarigeArguments("Lg01_046", "999998766", Set.of(
                new Gezagsrelatie("999998833", "OG2"))),
            // lg01_047 meerderjarige actueel gehuwd, 1 minderjarige kind met 2 vreemde nationaliteiten,
            // 1 minderjarig erkend kind met Nederlandse nationaliteit door erkenning
            meerderjarigeArguments("Lg01_047", "999998821", Set.of(
                new Gezagsrelatie("999998833", "OG2"))),
            // lg01_048 minderjarige, erkend, vervolgens ouders gehuwd, 2 beëindigde vreemde nationaliteiten,
            // Nederlandse nationaliteit vanaf geboorte
            minderjarigeArguments("Lg01_048", "999998833", "OG2", List.of("999998821", "999998766")),
            // lg01_049 minderjarige, juridisch geen vader, 2 vreemde nationaliteiten
            //            minderjarigeArguments("Lg01_049", "999999245", N, List.of("")),

            // lg01_050 niet gehuwd, minderjarig kind geadopteerd door echtpaar
            meerderjarigeArguments("Lg01_050", "999998857", Set.of()),
            // lg01_051 minderjarig kind, geadopteerd + voornaamswijziging, onder gezag Derde (vóór adoptie).
            minderjarigeArguments("Lg01_051", "999998869", "OG2", List.of("999998870", "999998882")),
            // lg01_052 meerderjarige gehuwd + 2 adoptiekinderen
            meerderjarigeArguments("Lg01_052", "999998870", Set.of(
                new Gezagsrelatie("999998869", "OG2"),
                new Gezagsrelatie("999999448", "OG2"),
                new Gezagsrelatie("999998894", "OG2"),
                new Gezagsrelatie("999970380", "OG2"),
                new Gezagsrelatie("999970367", "OG2"),
                new Gezagsrelatie("999970379", "OG2"),
                new Gezagsrelatie("999970355", "OG2"))),
            // lg01_053 meerderjarige gehuwd + 2 adoptiekinderen
            meerderjarigeArguments("Lg01_053", "999998882", Set.of(
                new Gezagsrelatie("999998869", "OG2"),
                new Gezagsrelatie("999999448", "OG2"),
                new Gezagsrelatie("999998894", "OG2"),
                new Gezagsrelatie("999970367", "OG2"),
                new Gezagsrelatie("999970379", "OG2"),
                new Gezagsrelatie("999970355", "OG2"),
                new Gezagsrelatie("999970380", "OG2"))),
            // lg01_054 minderjarig kind, geadopteerd uit buitenland, Nederlandse adoptieakte
            minderjarigeArguments("Lg01_054", "999998894", "OG2", List.of("999998870", "999998882")),
            // lg01_055 3x gehuwd (1x reparatiehuwelijk), 2x gescheiden, 5 minderjarige kinderen
            meerderjarigeArguments("Lg01_055", "999998900", Set.of(
                new Gezagsrelatie("999998924", "OG2"))),
            // lg01_056 meerderjarige 2x gehuwd (1x reparatiehuwelijk), 1x partnerschap. 1x gescheiden, 1x weduwe,
            // 7 minderjarige kinderen waarvan 2 geboren buiten de huwelijken om
            meerderjarigeArguments("Lg01_056", "999998912", Set.of(
                new Gezagsrelatie("999998985", "OG1"),
                new Gezagsrelatie("999998924", "OG2"),
                new Gezagsrelatie("999970963", "OG1"),
                new Gezagsrelatie("999970975", "OG1"),
                new Gezagsrelatie("999998936", "OG1"))),
            // lg01_057 onder gezag derde, geldigheid vóór scheiding ouders
            minderjarigeArguments("Lg01_057", "999998961", "V", List.of("")),
            // lg01_058 minderjarige, gezag ouder1, datum geldigheid standaardwaarde
            minderjarigeArguments("Lg01_058", "999998948", "N", List.of("")),
            // lg01_059 minderjarige, gezag ouder2, geldigheid ná datum reparatiehuwelijk
            minderjarigeArguments("Lg01_059", "999998936", "OG1", List.of("999998912")),
            // lg01_060 minderjarige, gezag ouder1, datum geldigheid vóór reparatiehuwelijk
            minderjarigeArguments("Lg01_060", "999998924", "OG2", List.of("999998912", "999998900")),
            // lg01_061 meerderjarige partnerschap, overleden, 1 minderjarig kind
            meerderjarigeArguments("Lg01_061", "999998973", Set.of()),
            // lg01_062 minderjarige, vader overleden, moeder gezag van rechtswege
            minderjarigeArguments("Lg01_062", "999998985", "OG1", List.of("999998912")),
            // lg01_063 meerderjarige gescheiden, 1 minderjarig kind
            meerderjarigeArguments("Lg01_063", "999999011", Set.of(
                new Gezagsrelatie("999999023", "OG1"))),
            // lg01_064 minderjarige, ouders gescheiden, moeder gezag vóór scheiding
            minderjarigeArguments("Lg01_064", "999999023", "OG1", List.of("999999011")),
            // lg01_065 meerderjarige ongehuwde vrouw, 3 minderjarige kinderen samen met ongehuwde vrouw
            meerderjarigeArguments("Lg01_065", "999999035", Set.of(
                new Gezagsrelatie("999999059", "OG2"),
                new Gezagsrelatie("999999060", "OG2"))),
            // lg01_066 meerderjarige ongehuwde vrouw, 3 minderjarige kinderen samen met ongehuwde vrouw
            meerderjarigeArguments("Lg01_066", "999999047", Set.of(
                new Gezagsrelatie("999999059", "OG2"),
                new Gezagsrelatie("999999060", "OG2"))),
            // lg01_067 minderjarige, geboorte uit ongehuwde vrouw,
            // adoptie met terugwerkende kracht tot geboortedatum door duo-moeder
            minderjarigeArguments("Lg01_067", "999999059", "OG2", List.of("999999047", "999999035")),
            // lg01_068 minderjarige, geboorte uit ongehuwde vrouw,
            // erkenning bij geboorteaangifte (ná dag geboorte) door meemoeder, categorie 11 met 12
            minderjarigeArguments("Lg01_068", "999999060", "OG2", List.of("999999035", "999999047")),
            // lg01_069 minderjarige, geboorte uit ongehuwde vrouw, erkenning ongeboren vrucht door meemoeder,
            // geen categorie 11
            minderjarigeArguments("Lg01_069", "999999072", "N", List.of("")),
            // lg01_070 meerderjarige ongehuwde vrouw met 1 minderjarig kind (erkend, erkenning vernietigd en opnieuw erkend
            meerderjarigeArguments("Lg01_070", "999999084", Set.of(
                new Gezagsrelatie("999999102", "OG1"))),
            // lg01_071 meerderjarige ongehuwde man met vernietiging erkenning
            meerderjarigeArguments("Lg01_071", "999999096", Set.of()),
            // lg01_072 minderjarig kind, erkenning, vernietiging erkenning, nieuwe erkenning,
            // cat 11 in historie 12, actueel leeg (vernietiging erkenning)
            minderjarigeArguments("Lg01_072", "999999102", "OG1", List.of("999999084")),
            // lg01_073 ongehuwde man met erkend kind
            meerderjarigeArguments("Lg01_073", "999999114", Set.of()),
            // lg01_074 gescheiden vrouw, relatielegging ná ontbinding huwelijk, 1 minderjarig kind, correctie geboorte
            // en erkenning (gehuwd op datum geboorte kind), ontkenning ouderschap, opnieuw erkenning door eerste erkenner,
            // vervolgens een kind wat ontkend is (en niet opnieuw erkend) en 2 kinderen waarbij vaderschap vastgesteld is.
            meerderjarigeArguments("Lg01_074", "999999126", Set.of(
                new Gezagsrelatie("999999151", "OG1"),
                new Gezagsrelatie("999970021", "OG1"),
                new Gezagsrelatie("999970033", "OG1"),
                new Gezagsrelatie("999970045", "OG2"))),
            // lg01_075 meerderjarige ongehuwde man, kind erkend, geboorteakte kind gecorrigeerd (erkenning daardoor onjuist),
            // na ontkenning ouderschap opnieuw erkend
            meerderjarigeArguments("Lg01_075", "999999138", Set.of()),
            // lg01_076 minderjarig kind bij geboorte alleen moeder, vervolgens erkend, geboorte en erkenning onjuist
            // moeder bleek gehuwd op datum geboorte, ontkenning ouderschap door (ex)huwelijkspartner moeder,
            // vervolgens opnieuw erkend door eerste erkenner, geen categorie 11
            minderjarigeArguments("Lg01_076", "999999151", "OG1", List.of("999999126")),
            // lg01_077 meerderjarige latere vestiging in Nederland, gescheiden, ouderschap ontkend van 2 minderjarige kinderen,
            // bij 2 kinderen vaderschap vastgesteld
            meerderjarigeArguments("Lg01_077", "999999163", Set.of(
                new Gezagsrelatie("999970045", "OG2"))),
            // lg01_078 meerderjarige geregistreerd partnerschap en minderjarig kind geboren vóór 01-04-2014, overleden
            meerderjarigeArguments("Lg01_078", "999999187", Set.of()),
            // lg01_079 meerderjarige geregistreerd partnerschap, partner heeft minderjarig, niet erkend kind
            // geboren vóór 01-04-2014, partner overleden
            meerderjarigeArguments("Lg01_079", "999999199", Set.of()),
            // lg01_080 miderjarige, moeder geregistreerd partnerschap, niet erkend, geen categorie 11,
            // moeder overleden geregistreerd partner van moeder automatisch gezag
            minderjarigeArguments("Lg01_080", "999999205", "V", List.of("999999199")),
            // lg01_081 meerderjarige huwelijk personen gelijk geslacht (V), 1 minderjarig kind erkend als ongeboren
            // vrucht door vader
            meerderjarigeArguments("Lg01_081", "999999217", Set.of()),
            // lg01_082 meerderjarige huwelijk personen gelijk geslacht (V), echtgenote 1 minderjarig kind erkend als
            // ongeboren vrucht door vader
            meerderjarigeArguments("Lg01_082", "999999229", Set.of()),
            // lg01_083 geboren uit huwelijk 2 vrouwen, als ongeboren vrucht erkend door vader
            // (huwelijkspartner moeder is daardoor geen ouder), geen categorie 11
            minderjarigeArguments("Lg01_083", "999999230", "N", List.of("")), // route 54, maar kan hier nog iets veranderen?

            // lg01_084 minderjarig kind, geadopteerd + voornaamswijziging, onder gezag Geen gezag
            minderjarigeArguments("Lg01_084", "999999448", "OG2", List.of("999998870", "999998882")),
            // lg01_085 meerderjarige ongehuwde vrouw, overleden, één minderjarig niet erkend kind
            meerderjarigeArguments("Lg01_085", "999999461", Set.of()),
            // lg01_086 minderjarig kind, niet erkend, moeder overleden
            minderjarigeArguments("Lg01_086", "999999473", "G", List.of("")),
            // lg01_087 meerderjarige geregistreerd partnerschap, overleden
            meerderjarigeArguments("Lg01_087", "999999485", Set.of()),
            // lg01_088 meerderjarige geregistreerd partnerschap, overleden
            meerderjarigeArguments("Lg01_088", "999999497", Set.of()),
            // lg01_089 niet erkend minderjarig kind, moeder geregistreerd partnerschap voor 01-04-2014,
            // zowel moeder als geregistreerd partner moeder overleden
            minderjarigeArguments("Lg01_089", "999999503", "G", List.of("")),
            // lg01_090 niet erkend minderjarig kind, moeder geregistreerd partnerschap voor 01-04-2014,
            // zowel moeder als geregistreerd partner moeder overleden, gezag 1D opgenomen vóór overlijden moeder
            minderjarigeArguments("Lg01_090", "999999515", "V", List.of("")),
            // lg01_091 meerderjarige huwelijk tussen 2 vrouwen vóór 01-04-2014,
            // huwelijkspartner niet automatisch ouder van kind, partner overleden
            meerderjarigeArguments("Lg01_091", "999999527", Set.of(
                new Gezagsrelatie("999999540", "OG1"))),
            // lg01_092 meerderjarige huwelijk tussen 2 vrouwen vóór 01-04-2014,
            // huwelijkspartner niet automatisch ouder van kind, overleden
            meerderjarigeArguments("Lg01_092", "999999539", Set.of()),
            // lg01_093 minderjarige, moeder gehuwd met vrouw vóór 01-04-2014, partner moeder geen ouder maar wel gezamenlijk gezag,
            // partner moeder overleden
            minderjarigeArguments("Lg01_093", "999999540", "OG1", List.of("999999527")),
            // lg01_094 meerderjarige gehuwd, overleden
            meerderjarigeArguments("Lg01_094", "999999552", Set.of()),
            // lg01_095 meerderjarige gehuwd, overleden
            meerderjarigeArguments("Lg01_095", "999999564", Set.of()),
            // lg01_096 minderjarig kind van gehuwde ouders, beide ouders overleden, geen gezagsverhouding in cat 11
            minderjarigeArguments("Lg01_096", "999999576", "G", List.of("")),
            // lg01_097 meerderjarige gehuwd, 1 minderjarig kind
            meerderjarigeArguments("Lg01_097", "999999588", Set.of()),
            // lg01_098 meerderjarige gehuwd, 1 minderjarig kind
            meerderjarigeArguments("Lg01_098", "999999606", Set.of()),
            // lg01_099 ouders gehuwd, indicatie gezag 1D
            minderjarigeArguments("Lg01_099", "999999618", "V", List.of("")),
            // lg01_100 vondeling, juridisch geen ouders (is fout in BRP opgenomen)
            minderjarigeArguments("Lg01_100", "999999631", "G", List.of("")),
            // lg01_101 meerderjarige gehuwd, 3 kinderen, overleden op zelfde dag als partner (als eerste overleden,
            // dus geen huwelijksontbinding)
            meerderjarigeArguments("Lg01_101", "999999643", Set.of()),
            // lg01_102 meerderjarige gehuwd, 3 kinderen, overleden op zelfde dag als partner (partner als eerste
            // overleden dus huwelijksontbinding
            meerderjarigeArguments("Lg01_102", "999999655", Set.of()),
            // lg01_103 minderjarige, gehuwde ouders beiden overleden, geen categorie 11
            minderjarigeArguments("Lg01_103", "999999667", "G", List.of("")),
            // lg01_104 minderjarige, gehuwde ouders beiden overleden, indicatie gezag 1 geplaatst vóór overlijden Ouder1
            minderjarigeArguments("Lg01_104", "999999679", "G", List.of("")),
            // lg01_105 minderjarige, gehuwde ouders beiden overleden, indicatie D geplaatst ná overlijden Ouders
            minderjarigeArguments("Lg01_105", "999999680", "V", List.of("")),
            // lg01_106 meerderjarige overleden
            meerderjarigeArguments("Lg01_106", "999999692", Set.of()),
            // lg01_107 meerderjarige onder curatele
            meerderjarigeArguments("Lg01_107", "999999709", Set.of()),
            // lg01_108 minderjarige, vader overleden, moeder onder curatele, geen categorie 11
            minderjarigeArguments("Lg01_108", "999999710", "G", List.of("")),
            // lg01_109 minderjarige, vader overleden, moeder onder curatele, kind categorie 11 gezag bij derde
            minderjarigeArguments("Lg01_109", "999999722", "V", List.of("")),
            // lg01_110 minderjarige, ouders gehuwd, indicatie gezag "2"
            minderjarigeArguments("Lg01_110", "999999734", "OG1", List.of("999998791")),
            // lg01_111 erkend, ouders later gehuwd, indicatie gezag "1" geldig voor huwelijksdatum
            minderjarigeArguments("Lg01_111", "999999746", "OG1", List.of("999998778")),
            // lg01_112 erkend, ouders later gehuwd, indicatie gezag "1D" geldig na huwelijksdatum
            minderjarigeArguments("Lg01_112", "999999758", "V", List.of("")),
            // lg01_113 erkend, ouders later gehuwd, indicatie gezag "1D" geldig voor huwelijksdatum
            minderjarigeArguments("Lg01_113", "999999771", "V", List.of("")),
            // lg01_114 meerderjarige geregistreerd partnerschap vóór 01-04-2014, 2 minderjarige kinderen vóór 01-04-2014
            // en 1 minderjarig kind daarna
            meerderjarigeArguments("Lg01_114", "999999783", Set.of(
                new Gezagsrelatie("999999825", "OG2"))),
            // lg01_115 meerderjarige geregistreerd partnerschap vóór 01-04-2014, 2 minderjarige kinderen vóór 01-04-2014
            // en 1 minderjarig kind daarna
            meerderjarigeArguments("Lg01_115", "999999795", Set.of(
                new Gezagsrelatie("999999825", "OG2"),
                new Gezagsrelatie("999999813", "GG"),
                new Gezagsrelatie("999999801", "GG"))),
            // lg01_116 minderjarige, ouders geregistreerd partnerschap vóór 01-04-204, alleen Ouder1 gevuld
            minderjarigeArguments("Lg01_116", "999999801", "GG", List.of("999999795", "999999783")),
            // lg01_117 minderjarige, ouders geregistreerd partnerschap vóór 01-04-204, alleen Ouder2 gevuld
            minderjarigeArguments("Lg01_117", "999999813", "GG", List.of("999999795", "999999783")),
            // lg01_118 minderjarige, ouders geregistreerd partnerschap na 01-04-2014, 2 ouders
            minderjarigeArguments("Lg01_118", "999999825", "OG2", List.of("999999795", "999999783")),
            // lg01_119 meerderjarige geregistreerd partnerschap omgezet in huwelijk
            meerderjarigeArguments("Lg01_119", "999999837", Set.of()),
            // lg01_120 meerderjarige geregistreerd partnerschap omgezet in huwelijk
            meerderjarigeArguments("Lg01_120", "999999849", Set.of(
                new Gezagsrelatie("999999850", "GG"))),
            // lg01_121 minderjarige, ouders geregistreerd partnerschap vóór 01-04-2014, alleen Ouder1 gevuld.
            // Na geboorte kind hebben ouders geregistreerd partnerschap omgezet in een huwelijk.
            minderjarigeArguments("Lg01_121", "999999850", "GG", List.of("999999849", "999999837")),
            // lg01_122 meerderjarige alleen huwelijksjaar bekend
            meerderjarigeArguments("Lg01_122", "999999862", Set.of(
                new Gezagsrelatie("999999886", "OG2"))),
            // lg01_123 meerderjarige alleen huwelijksjaar bekend
            meerderjarigeArguments("Lg01_123", "999999874", Set.of(
                new Gezagsrelatie("999999886", "OG2"))),
            // lg01_124 minderjarige, ouders geboren en gehuwd in buitenland, alleen jaar bekend, kind in Nederland geboren
            minderjarigeArguments("Lg01_124", "999999886", "OG2", List.of("999999862", "999999874")),
            // lg01_125 meerderjarige, 3 erkende kinderen
            meerderjarigeArguments("Lg01_125", "999999898", Set.of(
                new Gezagsrelatie("999999916", "OG2"))),
            // lg01_126 meerderjarige, 3 erkende kinderen
            meerderjarigeArguments("Lg01_126", "999999904", Set.of(
                new Gezagsrelatie("999999941", "OG2"),
                new Gezagsrelatie("999999916", "OG2"))),
            // lg01_127 minderjarige, erkend, categorie 09 op PL vader/erkenner zonder SoFi-nr/BSN, indicatie gezag 12
            minderjarigeArguments("Lg01_127", "999999916", "OG2", List.of("999999904", "999999898")),
            // lg01_128 minderjarige, erkend, categorie 03 op PL kind zonder SoFi-nr/BSN, wel A-nr/BSN in categorie 09 PL vader/erkenner, indicatie gezag 12
            minderjarigeArguments("Lg01_128", "999999928", "N", List.of("")),
            // lg01_129 minderjarige, erkend categorie 03 op PL kind met Anr/BSN, categorie 09 ontbreekt bij vader, indicatie gezag 12
            minderjarigeArguments("Lg01_129", "999999941", "OG2", List.of("999999904", "999999898")),
            // lg01_130 meerderjarige kind geboren tijdens huwelijk dat later nietig is verklaard, ander kind erkend en
            // erkenning is vernietigd
            meerderjarigeArguments("Lg01_130", "999999953", Set.of(
                new Gezagsrelatie("999999977", "OG2")
            )),
            // lg01_131 meerderjarige kind geboren tijdens huwelijk dat later nietig is verklaard, ander kind erkend en
            // erkenning is vernietigd
            meerderjarigeArguments("Lg01_131", "999999965", Set.of(
                new Gezagsrelatie("999999989", "OG1"),
                new Gezagsrelatie("999999977", "OG2")
            )),
            // lg01_132 minderjarige, geboren uit huwelijk ouders wat later nietig is verklaard
            // gaat niet goed maar levert na 01-01-2023 wel een juist resultaat op. Voor 1-1-2023 geen juist resultaat,
            // alleen moeder gezag ipv beide ouders. Probleem is dat huwelijksdatum en huwelijksontbindingsdatum gelijk
            // zijn en voor geboortedatum kind liggen, hierdoor wordt dit 'gelezen' als een erkenning en ongehuwde
            // ouders en niet als een nietigverklaring huwelijk waarbij de rechtsgevolgen voor het kind niet wijzigen.
            // Heidi legt dit voor aan Ronald met het verzoek om in dit geval van rechtswege gezag op te nemen.
            minderjarigeArguments("Lg01_132", "999999977", "OG2", List.of("999999965", "999999953")),
            // lg01_133 minderjarige, erkend, maar erkenning is vernietigd
            minderjarigeArguments("Lg01_133", "999999989", "OG1", List.of("999999965")),
            // lg01_134 meerderjarige gehuwd, kinderen, in onderzoek 010300, 020000, 030310, 050610, 089999, 090000, 090300
            meerderjarigeArguments("Lg01_134", "999970008", Set.of(
                new Gezagsrelatie("999970008", "N"))),
            // lg01_135 meerderjarige gehuwd, kinderen, in onderzoek 026210, 030000, 050200, 080000, 090330
            meerderjarigeArguments("Lg01_135", "999970148", Set.of()),
            // lg01_136 minderjarige, kind geboren tijdens huwelijk, in onderzoek 010310, 026210, 030210, 080920, 613210
            minderjarigeArguments("Lg01_136", "999970288", "N", List.of("")),
            // lg01_137 minderjarige, kind geboren tijdens huwelijk in onderzoek 010330, 040510, 081420, 113210
            minderjarigeArguments("Lg01_137", "999970331", "N", List.of("")),
            // lg01_138 minderjarige, kind geboren tijdens huwelijk, overleden, in onderzoek 018120, 028120, 038120, 060000
            Arguments.of("Lg01_138", "999970422", Set.of()),
            // lg01_139 minderjarige, test op inlezen in de toekomst met geboortejaar 2023 (is inlezen in toekomst mogelijk)
            minderjarigeArguments("Lg01_139", "999970434", "N", List.of("")),
            // lg01_140 meerderjarige gehuwd, kinderen, in onderzoek 010300, 020000, 030310, 050610, 089999, 090000, 090300
            meerderjarigeArguments("Lg01_140", "999970008", Set.of(
                new Gezagsrelatie("999970008", "N"))),
            // lg01_141 meerderjarige 3 kinderen in 2023, 1 erkend als ongeboren vrucht, 1 erkend bij geboorteaangifte,
            // 1 erkend na geboorteaangifte
            meerderjarigeArguments("Lg01_141", "999970446", Set.of(
                new Gezagsrelatie("999970471", "OG2"),
                new Gezagsrelatie("999970483", "OG2"),
                new Gezagsrelatie("999970495", "OG2"))),
            // lg01_142 minderjarige, kind geboren uit ongehuwde ouders in 2023, erkenning na de geboorteaangifte (akteaanduiding C)
            minderjarigeArguments("Lg01_142", "999970471", "OG2", List.of("999970446", "999970458")),
            // lg01_143 minderjarige, kind geboren uit ongehuwde ouders in 2023, erkenning bij geboorteaangifte (akteaanduiding B)
            minderjarigeArguments("Lg01_143", "999970483", "OG2", List.of("999970446", "999970458")),
            // lg01_144 kind geboren uit ongehuwde ouders in januari 2023, erkenning ongeboren vrucht, geen indicatie gezag
            minderjarigeArguments("Lg01_144", "999970495", "OG2", List.of("999970446", "999970458")),
            // lg01_145 meerderjarige ongehuwde ouders met 6 kinderen geboren voor en na 01-01-2023, 5 kinderen erkend,
            // 2 kinderen vaststelling vaderschap
            meerderjarigeArguments("Lg01_145", "999970501", Set.of(
                new Gezagsrelatie("999970537", "OG2"),
                new Gezagsrelatie("999970562", "OG2"))),
            // lg01_146 meerderjarige ongehuwde ouders met 7 kinderen geboren voor en na 01-01-2023, 5 kinderen erkend,
            // 2 kinderen vaststelling vaderschap
            meerderjarigeArguments("Lg01_146", "999970513", Set.of(
                new Gezagsrelatie("999970525", "OG1"),
                new Gezagsrelatie("999970562", "OG2"),
                new Gezagsrelatie("999970537", "OG2"),
                new Gezagsrelatie("999970550", "OG1"),
                new Gezagsrelatie("999970574", "OG1"),
                new Gezagsrelatie("999970549", "OG1"),
                new Gezagsrelatie("999971049", "OG1"))),
            // lg01_147 minderjarige, kind geboren uit ongehuwde ouders na 01-01- 2023, erkenning ongeboren vrucht,
            // indicatie gezag
            minderjarigeArguments("Lg01_147", "999970525", "OG1", List.of("999970513")),
            // lg01_148 minderjarige, kind geboren uit ongehuwde ouders na 01-01- 2023, erkenning ongeboren vrucht,
            // geen indicatie gezag
            minderjarigeArguments("Lg01_148", "999970537", "OG2", List.of("999970513", "999970501")),
            // lg01_149 minderjarige, kind geboren uit ongehuwde ouders voor 01-01-2023, vaststelling vaderschap
            minderjarigeArguments("Lg01_149", "999970549", "OG1", List.of("999970513")),
            // lg01_150 minderjarige, kind geboren uit ongehuwde ouders voor 01-01-2023, erkenning na 01-01-2023,
            // aantekening gezag
            minderjarigeArguments("Lg01_150", "999970550", "OG1", List.of("999970513")),
            // lg01_151 minderjarige, kind geboren uit ongehuwde ouders voor 01-01-2023, erkenning na 01-01-2023,
            // geen aantekening gezag
            minderjarigeArguments("Lg01_151", "999970562", "OG2", List.of("999970513", "999970501")),
            // lg01_152 minderjarige, kind geboren uit ongehuwde ouders voor 01-01-2023, erkenning voor 01-01-2023,
            // geen aantekening gezag
            minderjarigeArguments("Lg01_152", "999970574", "OG1", List.of("999970513")),
            // lg01_153 meerderjarige onvolledige geboortedatum, namenreeks, gehuwd, 1 minderjarig kind met namenreeks
            meerderjarigeArguments("Lg01_153", "999970586", Set.of(
                new Gezagsrelatie("999970604", "OG2"))),
            // lg01_154 meerderjarige onvolledige geboortedatum, namenreeks, gehuwd, 1 minderjarig kind met namenreeks
            meerderjarigeArguments("Lg01_154", "999970598", Set.of(
                new Gezagsrelatie("999970604", "OG2"))),
            // lg01_155 minderjarige, in Nederland geboren kind met namenreeks, ouders onvolledige geboortedatum
            minderjarigeArguments("Lg01_155", "999970604", "OG2", List.of("999970598", "999970586")),
            // lg01_156 meerderjarige vrouw, huwelijk met vrouw, 2 kinderen, maar echtgenote geen duomoeder,
            // echtgenote wel gezag, echtgenote overleden, kinderen worden in 2023 erkend
            meerderjarigeArguments("Lg01_156", "999970616", Set.of(
                new Gezagsrelatie("999970665", "OG2"),
                new Gezagsrelatie("999970641", "OG2"))),
            // lg01_157 meerderjarige vrouw, huwelijk met vrouw, echtgenote krijgt 2 kinderen (geen anonieme donor),
            // overleden
            meerderjarigeArguments("Lg01_157", "999970628", Set.of()),
            // lg01_158 minderjarige, alleen moeder, moeder wel gehuwd, maar geen anonieme donor,
            // na overlijden huwelijkspartner moeder erkend in 2023,
            // akteaanduiding C in actuele categorie bij persoon en ouder2
            minderjarigeArguments("Lg01_158", "999970641", "OG2", List.of("999970616", "999970653")),
            // lg01_159 meerderjarige erkenner van 2 kinderen waarvan de moeder gehuwd was met een vrouw die niet de meemoeder
            // is van de kinderen (geen anonieme donor). Huwelijkspartner moeder overleden.
            meerderjarigeArguments("Lg01_159", "999970653", Set.of(
                new Gezagsrelatie("999970665", "OG2"),
                new Gezagsrelatie("999970641", "OG2"))),
            // lg01_160 minderjarige, alleen moeder, moeder wel gehuwd, maar geen anonieme donor,
            // na overlijden huwelijkspartner moeder erkend in 2023,
            // akteaanduiding C in historische categorie bij persoon en ouder2
            minderjarigeArguments("Lg01_160", "999970665", "OG2", List.of("999970616", "999970653")),
            // lg01_161 meerderjarige moeder van 2 erkende kinderen
            meerderjarigeArguments("Lg01_161", "999970677", Set.of()),
            // lg01_162 meerderjarige voogd van 2 minderjarige kinderen (1D), 1 kind door hem later erkend
            meerderjarigeArguments("Lg01_162", "999970689", Set.of()),
            // lg01_163 meerderjarige erkenner van minderjarig kind wat eerder onder gezag (1D) is geplaatst
            meerderjarigeArguments("Lg01_163", "999970690", Set.of()),
            // lg01_164 minderjarige, minderjarig kind onder gezag 1D, erkend
            minderjarigeArguments("Lg01_164", "999970707", "V", List.of("")),
            // lg01_165 minderjarige, minderjarig kind onder gezag 1D, erkend door voogd
            minderjarigeArguments("Lg01_165", "999970719", "V", List.of("")),
            // lg01_166 minderjarige, ontkenning vaderschap, juridisch geen ouder1, wel ouder2
            minderjarigeArguments("Lg01_166", "999970021", "OG1", List.of("999999126")),
            // lg01_167 minderjarige, vaststelling vaderschap, geen categorie 11
            minderjarigeArguments("Lg01_167", "999970033", "OG1", List.of("999999126")),
            // vaststelling ouderschap leidt niet tot automatisc gezag,
            // Heidi bespreekt met Ronald of dit op te lossen is door van rechtswege gezag moeder op te nemen.

            // lg01_168 minderjarige, vaststelling vaderschap, wel categorie 11
            minderjarigeArguments("Lg01_168", "999970045", "OG2", List.of("999999126", "999999163")),
            // lg01_169 meerderjarige vrouw niet gehuwd/GP, 1 minderjarig kind geadopteerd, 1 minderjarig kind erkend
            // en later erkenning vernietigd
            meerderjarigeArguments("Lg01_163", "999970057", Set.of(
                new Gezagsrelatie("999970070", "OG2"),
                new Gezagsrelatie("999970082", "OG1"))),
            // lg01_170 meerderjarige man niet gehuwd/GP, 1 minderjarig kind geadopteerd, 1 minderjarig kind erkend en
            // later erkenning vernietigd
            meerderjarigeArguments("Lg01_170", "999970069", Set.of(
                new Gezagsrelatie("999970070", "OG2"))),
            // lg01_171 minderjarige, kind, geboren uit alleen moeder, vervolgens gezag 1D, later geactualiseerd in 1,
            // en vervolgens 1-ouder adoptie
            minderjarigeArguments("Lg01_171", "999970070", "OG2", List.of("999970057", "999970069")),
            // lg01_172 minderjarige, kind geboren uit alleen moeder, erkend door vader, vervolgens gezag 12
            // en daarna vernietiging erkenning
            minderjarigeArguments("Lg01_172", "999970082", "OG1", List.of("999970057")),
            // lg01_173 minderjarige, minderjarige moeder, gezag niet te bepalen BSN moeder ontbreekt
            minderjarigeArguments("Lg01_173", "999970094", "N", List.of("")),
            // lg01_174 minderjarige, minderjarige moeder, geen cateorie 11
            minderjarigeArguments("Lg01_174", "999970100", "G", List.of("")),
            // lg01_175 minderjarige, minderjarige moeder, wel categorie 11
            minderjarigeArguments("Lg01_175", "999970112", "V", List.of("")),
            // lg01_176 meerderjarige vrouw gehuwd met vrouw, 4 kinderen (2 van bekende donor tijdens huwelijken
            // 1 voor het huwelijk en 1 na 01-01-2023 erkend voor geboorte door ander dan partner
            meerderjarigeArguments("Lg01_176", "999970124", Set.of(
                new Gezagsrelatie("999970409", "OG2"),
                new Gezagsrelatie("999970161", "GG"),
                new Gezagsrelatie("999970185", "OG1"))),
            // lg01_177 meerderjarige huwelijkspartner van vrouw, geen meemoeder van de kinderen,
            // bij 1 kind gezamenlijk gezag
            meerderjarigeArguments("Lg01_177", "999970136", Set.of()),
            // lg01_178 minderjarige, 1 ouder, moeder gehuwd, gezamenlijk gezag moeder en meemoeder
            minderjarigeArguments("Lg01_178", "999970161", "GG", List.of("999970124", "999970136")),
            // lg01_179 minderjarige, 1 ouder, moeder gehuwd, indicatie gezag 1D geen gezamenlijk gezag moeder en meemoeder
            minderjarigeArguments("Lg01_179", "999970173", "V", List.of("")),
            // lg01_180 minderjarige, 1 ouder, moeder gehuwd na geboorte kind, geen gezamenlijk gezag
            minderjarigeArguments("Lg01_180", "999970185", "OG1", List.of("999970124")),
            // lg01_181 meerderjarige vrouw gehuwd met vrouw, 1 kind (bekende donor) huwelijkspartner geen meemoeder kind,
            // onder curatele
            meerderjarigeArguments("Lg01_181", "999970197", Set.of()),
            // lg01_182 meerderjarige huwelijkspartner van vrouw, geen meemoeder van kind, eenhoofdig gezag
            // (moeder onder curatele)
            meerderjarigeArguments("Lg01_182", "999970203", Set.of()),
            // lg01_183 minderjarige, 1 ouder, moeder gehuwd, huwelijkspartner moeder alleen gezag (moeder onder curatele)
            minderjarigeArguments("Lg01_183", "999970215", "V", List.of("999970203")),
            // lg01_184 minderjarige, 1 ouder, moeder gehuwd, huwelijkspartner moeder geen gezag (moeder onder curatele, indicatie gezag 1
            minderjarigeArguments("Lg01_184", "999970227", "G", List.of("")),
            // lg01_185 meerderjarige 1 ouder, moeder gehuwd, moeder alleen gezag, moeder overleden
            meerderjarigeArguments("Lg01_185", "999970239", Set.of()),
            // lg01_186 meerderjarige huwelijkspartner van vrouw, van rechtswege gezag
            meerderjarigeArguments("Lg01_186", "999970240", Set.of()),
            // lg01_187 minderjarige, 1 ouder, moeder gehuwd, meemoeder geen ouder, moeder overleden
            minderjarigeArguments("Lg01_187", "999970252", "V", List.of("999970240")),
            // lg01_188 meerderjarige moeder van minderjarig kind, 010100 in onderzoek
            meerderjarigeArguments("Lg01_188", "999970318", Set.of(
                new Gezagsrelatie("999970343", "OG1"))),
            // lg01_189 minderjarige, minderjarig kind, 010000 in onderzoek
            minderjarigeArguments("Lg01_189", "999970264", "N", List.of("")),
            // lg01_190 minderjarige, minderjarig kind, 080000 in onderzoek
            minderjarigeArguments("Lg01_190", "999970276", "OG1", List.of("999970318")),
            // Van verblijfplaats is afgesproken dat 'in Onderzoek' niet wordt gebruikt, deze wordt gefilterd

            // lg01_191 minderjarige, minderjarig kind, 020000 in onderzoek
            minderjarigeArguments("Lg01_191", "999970306", "N", List.of("")),
            // lg01_192 minderjarige, minderjarig kind, 010320 in onderzoek
            minderjarigeArguments("Lg01_192", "999970343", "OG1", List.of("999970318")),
            // lg01_193 minderjarige, buitenland geboren, geadopteerd met Nederlandse Q-akte in historie
            minderjarigeArguments("Lg01_193", "999970355", "OG2", List.of("999998870", "999998882")),
            // lg01_194 minderjarige, buitenland geboren, geadopteerd met Nederlandse Q-akte in historie, onderzoek in 018120
            minderjarigeArguments("Lg01_194", "999970367", "OG2", List.of("999998870", "999998882")),
            // Het onderzoek in 018120 is afgerond dus telt niet mee

            // lg01_195 minderjarige, buitenland geboren, geadopteerd met Nederlandse Q-akte in historie, onderzoek in 536200
            minderjarigeArguments("Lg01_195", "999970379", "OG2", List.of("999998870", "999998882")),
            // lg01_196 minderjarige, buitenland geboren, geadopteerd met Nederlandse Q-akte in historie,
            // afgerond onderzoek in 018120, 026200, 036200
            minderjarigeArguments("Lg01_196", "999970380", "OG2", List.of("999998870", "999998882")),
            // lg01_197 minderjarige, minderjarig kind, geboren in Nederland, moeder ingezetene, vader RNI
            minderjarigeArguments("Lg01_197", "999970392", "N", List.of("")),
            // lg01_198 minderjarige, minderjarige geboren na 01-01-2023 uit gehuwde moeder en bekende donor,
            // erkend voor geboorte door ander dan partner
            minderjarigeArguments("Lg01_198", "999970409", "OG2", List.of("999970124", "999970410")),
            // lg01_199 meerderjarige erkenner (voor geboorte) van minderjarig kind van gehuwde moeder en bekende donor
            meerderjarigeArguments("Lg01_199", "999970410", Set.of(
                new Gezagsrelatie("999970409", "OG2"))),
            // lg01_200 meerderjarige gehuwd, kind geboren tijdens huwelijk, huwelijk nadien nietig (gecorrigeerd),
            // kind eveneens gecorrigeerd
            meerderjarigeArguments("Lg01_200", "999970720", Set.of()),
            // lg01_201 meerderjarige gehuwd, kind geboren tijdens huwelijk, huwelijk nadien nietig (gecorrigeerd)
            meerderjarigeArguments("Lg01_201", "999970732", Set.of(
                new Gezagsrelatie("999970744", "OG1"))),
            // lg01_202 minderjarige, geboren tijdens huwelijk ouders, huwelijk ouders nietig,
            // categorie 03 aangepast in juridisch geen ouder
            minderjarigeArguments("Lg01_202", "999970744", "OG1", List.of("999970732")),
            // lg01_203 meerderjarige ongehuwd, 2 kinderen (1 geboren en erkend voor 01-01-2023 en
            // 1 geboren en erkend na 01-01-2023
            meerderjarigeArguments("Lg01_203", "999970756", Set.of()),
            // lg01_204 meerderjarige ongehuwd, 2 kinderen (1 geboren en erkend voor 01-01-2023
            // en 1 erkend na 01-01-2023
            meerderjarigeArguments("Lg01_204", "999970768", Set.of(
                new Gezagsrelatie("999970793", "OG1"),
                new Gezagsrelatie("999970781", "OG1"))),
            // =====================================================================================================================
            // De volgende persoonslijsten staan nog niet in de testBRP, dat maakt de uitslag wel wat vreemd, want nu lijkt het alsof
            // we het niet weten
            // =====================================================================================================================
            // lg01_205 minderjarige, geboren tijdens huwelijk ouders, huwelijk ouders nietig,
            // categorie 03 aangepast in juridisch geen ouder
            minderjarigeArguments("Lg01_205", "999970781", "OG1", List.of("999970768")),
            // lg01_206 minderjarige, geboren en erkend na 01-01-2023, moeder overleden, geen indicatie gezag
            minderjarigeArguments("Lg01_206", "999970793", "OG1", List.of("999970768")),
            // lg01_207 minderjarige, geboren tussen verschillende (ontbonden) huwelijken moeder, juridisch 1 ouder.
            // Moeder in categorie 02
            minderjarigeArguments("Lg01_207", "999970963", "OG1", List.of("999998912")),
            // lg01_208 minderjarige, geboren tussen verschillende (ontbonden) huwelijken moeder, juridisch 1 ouder.
            // Moeder in categorie 03
            minderjarigeArguments("Lg01_208", "999970975", "OG1", List.of("999998912")),
            // lg01_209 meerderjarige geregistreerd partnerschap met vrouw, partner overleden,
            // minderjarig kind van bekende donor
            meerderjarigeArguments("Lg01_209", "999970987", Set.of(
                new Gezagsrelatie("999971001", "OG1"),
                new Gezagsrelatie("999971013", "OG1"))),
            // lg01_210 meerderjarige geregistreerd partnerschap met vrouw, overleden
            meerderjarigeArguments("Lg01_210", "999970999", Set.of()),
            // lg01_211 minderjarige, juridisch alleen moeder, geregistreerd partner van moeder overleden,
            // moeder categorie 02
            minderjarigeArguments("Lg01_211", "999971001", "OG1", List.of("999970987")),
            // lg01_212 minderjarige, juridisch alleen moeder, geregistreerd partner van moeder overleden,
            // moeder categorie 03
            minderjarigeArguments("Lg01_212", "999971013", "OG1", List.of("999970987")),
            // lg01_213 minderjarige, juridisch alleen moeder, moeder overleden,
            // geregistreerd partner moeder geen ouder wel gezag, moeder categorie 02
            minderjarigeArguments("Lg01_213", "999971025", "V", List.of("999970987")),
            // lg01_214 minderjarige, juridisch alleen moeder, moeder overleden,
            // geregistreerd partner moeder geen ouder wel gezag, moeder categorie 03
            minderjarigeArguments("Lg01_214", "999971037", "V", List.of("999970987")),
            // lg01_215 minderjarige, kind geboren uit ongehuwde ouders voor 01-01-2023, vaststelling vaderschap, moeder categorie 0
            minderjarigeArguments("Lg01_215", "999971049", "OG1", List.of("999970513"))
        );
    }

    private static Arguments meerderjarigeArguments(final String testcase, final String commonValue, final Set<Gezagsrelatie> gezagsrelaties) {
        for (Gezagsrelatie gezagsrelatie : gezagsrelaties) {
            switch (gezagsrelatie.getSoortGezag()) {
                case "OG1", "OG2", "GG", "V" ->
                    gezagsrelatie.setBsnMeerderjarige(commonValue);
                default ->
                    gezagsrelatie.setBsnMeerderjarige("");
            }
            gezagsrelatie.setToelichting(UITLEG);
        }
        return Arguments.of(testcase, commonValue, gezagsrelaties);
    }

    private static Arguments minderjarigeArguments(final String testcase, final String bsnMinderjarige, final String soortGezag, final List<String> bsnsMeerderjarigen) {
        Set<Gezagsrelatie> gezagsrelaties = new HashSet<>();
        switch (soortGezag) {
            case "OG2", "OG1", "V", "GG" -> {
                for (String bsnMeerderjarige : bsnsMeerderjarigen) {
                    Gezagsrelatie gezagsrelatie = new Gezagsrelatie(bsnMinderjarige, soortGezag);
                    gezagsrelatie.setBsnMeerderjarige(bsnMeerderjarige);
                    gezagsrelatie.setToelichting(UITLEG);
                    if("GG".equals(soortGezag) && !gezagsrelaties.isEmpty()) {
                        gezagsrelatie.setDerde(true);
                    }
                    gezagsrelaties.add(gezagsrelatie);
                }
            }
            default -> {
                Gezagsrelatie gezagsrelatie = new Gezagsrelatie(bsnMinderjarige, soortGezag);
                gezagsrelatie.setBsnMeerderjarige("");
                gezagsrelatie.setToelichting(UITLEG);
                gezagsrelaties.add(gezagsrelatie);
            }
        }
        return Arguments.of(testcase, bsnMinderjarige, gezagsrelaties);
    }

    @ParameterizedTest
    @MethodSource
    void opvragenBevoegdheidTotGezag(final String testcase, final String input, final Set<Gezagsrelatie> expected) {
        GezagRequest request = new GezagRequest().burgerservicenummer(List.of(input));

        webTestClient.post().uri("/api/v1/opvragenBevoegdheidTotGezag").contentType(MediaType.APPLICATION_JSON).header("OIN", OIN).bodyValue(request).exchange().expectStatus().isOk().expectHeader().contentType(MediaType.APPLICATION_JSON).expectBody(GezagResponse.class).consumeWith(response -> {
            GezagResponse result = response.getResponseBody();
            System.out.printf("\tTestcase: %s, BSN: %s%n", testcase, input);

            if (expected != null && !expected.isEmpty()) {
                GezagTransformer transformer = new GezagTransformer();
                List<AbstractGezagsrelatie> expectedGezagsrelaties = transformer.from(expected.stream().toList());

                Persoon persoonResult = result.getPersonen().get(0);
                assertThat(persoonResult).isNotNull();

                List<AbstractGezagsrelatie> actualGezagsrelaties = persoonResult.getGezag();
                for (AbstractGezagsrelatie gezagsrelatie : actualGezagsrelaties) {
                    String type = gezagsrelatie.getType();
                    if (TYPE_NIET_TE_BEPALEN.equals(type)) {
                        Optional<String> toelichting = ((GezagNietTeBepalen) gezagsrelatie).getToelichting();
                        System.out.printf("\tTestcase: %s, niet te bepalen toelichting: %s%n", testcase, toelichting);
                        assertTrue(toelichting.isPresent() && !toelichting.isEmpty());
                    } else if (TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG.equals(type)) {
                        boolean correct = expectedGezagsrelaties.contains(gezagsrelatie);
                        if (!correct) {
                            /**
                             * Wanneer een object meer dan 1 gezag ouder bevat
                             * kan het object niet op object niveau worden
                             * vergeleken. Dit komt omdat de volgorde van de
                             * gezag ouders invloed heeft op de vergelijking als
                             * object.
                             *
                             * De vergelijing is erg omslagtig vanwege de
                             * abstractgezagrelatie in combinatie met de
                             * gegenereerde objecten
                             */
                            TweehoofdigOuderlijkGezag actualTweehoofdigOuderlijkGezag = (TweehoofdigOuderlijkGezag) gezagsrelatie;
                            for (AbstractGezagsrelatie expectedEntry : expectedGezagsrelaties) {
                                if (TYPE_TWEEHOOFDIG_OUDERLIJK_GEZAG.equals(expectedEntry.getType())) {
                                    TweehoofdigOuderlijkGezag expectedTweehoofdigOuderlijkGezag = (TweehoofdigOuderlijkGezag) expectedEntry;
                                    assertThat(expectedTweehoofdigOuderlijkGezag.getOuders())
                                        .containsAnyElementsOf(actualTweehoofdigOuderlijkGezag.getOuders());
                                }
                            }
                        }
                    } else {
                        assertTrue(expectedGezagsrelaties.contains(gezagsrelatie));
                    }
                }

                assertTrue(actualGezagsrelaties.size() == expectedGezagsrelaties.size());
            } else {
                assertTrue(result.getPersonen().get(0).getGezag().isEmpty());
            }
        });
    }
}