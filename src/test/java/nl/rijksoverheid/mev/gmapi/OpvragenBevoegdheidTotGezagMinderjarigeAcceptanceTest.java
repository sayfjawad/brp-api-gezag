package nl.rijksoverheid.mev.gmapi;

import nl.rijksoverheid.mev.GezagApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openapitools.OpenApiGeneratorApplication;
import org.openapitools.model.GezagRequest;
import org.openapitools.model.Gezagsrelatie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        classes = {GezagApplication.class, OpenApiGeneratorApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "app.clock=2023-02-01T00:00:00Z"
)
class OpvragenBevoegdheidTotGezagMinderjarigeAcceptanceTest {

    private static final String OIN = "00000004003214345001";

    @Autowired
    WebTestClient webTestClient;

    @ParameterizedTest
    @ValueSource(strings = {
        "999998778", // Lg01_001
        "999998791", // Lg01_002
        "999998353", // Lg01_004
        "999998390", // Lg01_010
        "999998444", // Lg01_015
        "999998468", // Lg01_017
        "999998493", // Lg01_019
        "999998547", // Lg01_024
        "999998559", // Lg01_025
        "999998584", // Lg01_028
        "999998596", // Lg01_029
        "999998626", // Lg01_032
        "999998638", // Lg01_033
        "999998675", // Lg01_036
        "999998687", // Lg01_037
        "999998729", // Lg01_041
        "999998742", // Lg01_042
        "999998766", // Lg01_046
        "999998821", // Lg01_047
        "999998857", // Lg01_050
        "999998870", // Lg01_052
        "999998882", // Lg01_053
        "999998900", // Lg01_055
        "999998912", // Lg01_056
        "999998973", // Lg01_061
        "999999011", // Lg01_063
        "999999035", // Lg01_065
        "999999047", // Lg01_066
        "999999084", // Lg01_070
        "999999096", // Lg01_071
        "999999114", // Lg01_073
        "999999126", // Lg01_074
        "999999138", // Lg01_075
        "999999163", // Lg01_077
        "999999187", // Lg01_078
        "999999199", // Lg01_079
        "999999217", // Lg01_081
        "999999229", // Lg01_082
        "999999461", // Lg01_085
        "999999485", // Lg01_087
        "999999497", // Lg01_088
        "999999527", // Lg01_091
        "999999539", // Lg01_092
        "999999552", // Lg01_094
        "999999564", // Lg01_095
        "999999588", // Lg01_097
        "999999606", // Lg01_098
        "999999643", // Lg01_101
        "999999655", // Lg01_102
        "999999692", // Lg01_106
        "999999709", // Lg01_107
        "999999783", // Lg01_114
        "999999795", // Lg01_115
        "999999837", // Lg01_119
        "999999849", // Lg01_120
        "999999862", // Lg01_122
        "999999874", // Lg01_123
        "999999898", // Lg01_125
        "999999904", // Lg01_126
        "999999953", // Lg01_130
        "999999965", // Lg01_131
        "999970148", // Lg01_135
        "999970422", // Lg01_138
        "999970446", // Lg01_140
        "999970458", // Lg01_141
        "999970501", // Lg01_145
        "999970513", // Lg01_146
        "999970586", // Lg01_153
        "999970598", // Lg01_154
        "999970616", // Lg01_156
        "999970628", // Lg01_157
        "999970653", // Lg01_159
        "999970667", // Lg01_161
        "999970689", // Lg01_162
        "999970690", // Lg01_163
        "999970057", // Lg01_169
        "999970069", // Lg01_170
        "999970124", // Lg01_176
        "999970136", // Lg01_177
        "999970197", // Lg01_181
        "999970203", // Lg01_182
        "999970239", // Lg01_185
        "999970240", // Lg01_186
        "999970318", // Lg01_188
        "999970410", // Lg01_199
        "999970720", // Lg01_200
        "999970732", // Lg01_201
        "999970756", // Lg01_203
        "999970768", // Lg01_204
    })
    @DisplayName("""
        given a burgerservicenummer as input,
        when sending a POST request to `/opvragenBevoegdheidTotGezagMinderjarige`,
        the response should contain an empty list of gezagsrelaties, which semantically corresponds to `soortGezag: NVT`
        """)
    void opvragenBevoegdheidTotGezagMinderjarige1(String input) {
        var request = new GezagRequest().bsn(input);

        webTestClient.post().uri("/api/v1/opvragenBevoegdheidTotGezagMinderjarige")
                .contentType(MediaType.APPLICATION_JSON)
                .header("OIN", OIN)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Gezagsrelatie.class).consumeWith(response -> {
            var gezagsrelaties = response.getResponseBody();

            System.out.println(input);
            System.out.println(gezagsrelaties);

            assertThat(gezagsrelaties).isNotNull();
            assertThat(gezagsrelaties).isEmpty();
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {

        "999998365", // Lg01_007
        "999998377", // Lg01_008
        "999998389", // Lg01_009
        "999998420", // Lg01_013
        "999998481", // Lg01_018
        "999998511", // Lg01_020
        "999998523", // Lg01_021
        "999998535", // Lg01_022
        "999999175", // Lg01_023
        "999998754", // Lg01_044
        "999999242", // Lg01_045
        //        "999999245", // Lg01_049 disable until this burgerservicenummer is added to the BRP on pre-production
        "999998948", // Lg01_058
        "999999072", // Lg01_069
        "999999928", // Lg01_128
        "999999230", // Lg01_083
        "999970008", // Lg01_134
        "999970288", // Lg01_136
        "999970331", // Lg01_137
        "999970434", // Lg01_139
        "999970094", // Lg01_173
        "999970264", // Lg01_189
        "999970306", // Lg01_191
        "999970392", // Lg01_197
    })
    @DisplayName("""
        given a burgerservicenummer as input,
        when sending a POST request to `/opvragenBevoegdheidTotGezagMinderjarige`,
        the response should contain a single gezagsrelatie with `soortGezag: N` and an empty `bsnMeerderjarige`
        """)
    void opvragenBevoegdheidTotGezagMinderjarige2(String input) {
        var request = new GezagRequest().bsn(input);

        webTestClient.post().uri("/api/v1/opvragenBevoegdheidTotGezagMinderjarige")
                .contentType(MediaType.APPLICATION_JSON)
                .header("OIN", OIN)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Gezagsrelatie.class).consumeWith(response -> {
            var gezagsrelaties = response.getResponseBody();

            System.out.println(input);
            System.out.println(gezagsrelaties);

            assertThat(gezagsrelaties).isNotNull();
            assertThat(gezagsrelaties).hasSize(1);

            var gezagsrelatie = gezagsrelaties.get(0);
            assertThat(gezagsrelatie.getBsnMeerderjarige()).isEmpty();
            assertThat(gezagsrelatie.getSoortGezag()).isEqualTo(Gezagsrelatie.SoortGezagEnum.N);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "999998432", // Lg01_014
        "999999473", // Lg01_086
        "999999503", // Lg01_089
        "999999576", // Lg01_096
        "999999631", // Lg01_100
        "999999667", // Lg01_103
        "999999679", // Lg01_104
        "999999710", // Lg01_108
        "999970100", // Lg01_174
        "999970227", // Lg01_184
    })
    @DisplayName("""
        given a burgerservicenummer as input,
        when sending a POST request to `/opvragenBevoegdheidTotGezagMinderjarige`,
        the response should contain a single gezagsrelatie with `soortGezag: G` and an empty `bsnMeerderjarige`
        """)
    void opvragenBevoegdheidTotGezagMinderjarige3(String input) {
        var request = new GezagRequest().bsn(input);

        webTestClient.post().uri("/api/v1/opvragenBevoegdheidTotGezagMinderjarige")
                .contentType(MediaType.APPLICATION_JSON)
                .header("OIN", OIN)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Gezagsrelatie.class).consumeWith(response -> {
            var results = response.getResponseBody();

            System.out.println(input);
            System.out.println(results);

            assertThat(results).isNotNull();
            assertThat(results).hasSize(1);

            var gezagsrelatie = results.get(0);
            assertThat(gezagsrelatie.getBsnMeerderjarige()).isEmpty();
            assertThat(gezagsrelatie.getSoortGezag()).isEqualTo(Gezagsrelatie.SoortGezagEnum.G);
        });
    }

    static Stream<Arguments> opvragenBevoegdheidTotGezagMinderjarige4() {
        return Stream.of(
                // (input, expected)
                Arguments.of("999999266", "999998390"), // Lg01_011
                Arguments.of("999999278", "999998390"), // Lg01_012
                Arguments.of("999998456", "999998444"), // Lg01_016
                Arguments.of("999998560", "999998547"), // Lg01_027
                Arguments.of("999998602", "999998584"), // Lg01_030
                Arguments.of("999998614", "999998584"), // Lg01_031
                Arguments.of("999998699", "999998675"), // Lg01_038
                Arguments.of("999998936", "999998912"), // Lg01_059
                Arguments.of("999998985", "999998912"), // Lg01_062
                Arguments.of("999999023", "999999011"), // Lg01_064

                Arguments.of("999999102", "999999084"), // Lg01_072
                Arguments.of("999999151", "999999126"), // Lg01_076

                Arguments.of("999999540", "999999527"), // Lg01_093
                Arguments.of("999999734", "999998791"), // Lg01_110
                Arguments.of("999999746", "999998778"), // Lg01_111
                Arguments.of("999999989", "999999965"), // Lg01_133
                Arguments.of("999970525", "999970513"), // Lg01_147
                Arguments.of("999970549", "999970513"), // Lg01_149
                Arguments.of("999970550", "999970513"), // Lg01_150
                Arguments.of("999970574", "999970513"), // Lg01_152
                Arguments.of("999970021", "999999126"), // Lg01_166
                Arguments.of("999970033", "999999126"), // Lg01_167
                Arguments.of("999970082", "999970057"), // Lg01_172
                Arguments.of("999970185", "999970124"), // Lg01_180
                Arguments.of("999970276", "999970318"), // Lg01_190
                Arguments.of("999970343", "999970318"), // Lg01_192
                Arguments.of("999970744", "999970732"), // Lg01_202
                Arguments.of("999970781", "999970768"), // Lg01_205
                Arguments.of("999970793", "999970768") // Lg01_206
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("""
        given a burgerservicenummer as input,
        when sending a POST request to `/opvragenBevoegdheidTotGezagMinderjarige`,
        the response should contain a single gezagsrelatie with `soortGezag: OG1` and the expected `bsnMeerderjarige`
        """)
    void opvragenBevoegdheidTotGezagMinderjarige4(String input, String expected) {
        var request = new GezagRequest().bsn(input);

        webTestClient.post().uri("/api/v1/opvragenBevoegdheidTotGezagMinderjarige")
                .contentType(MediaType.APPLICATION_JSON)
                .header("OIN", OIN)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Gezagsrelatie.class).consumeWith(response -> {
            var results = response.getResponseBody();

            System.out.println(input);
            System.out.println(results);

            assertThat(results).isNotNull();
            assertThat(results).hasSize(1);

            var gezagsrelatie = results.get(0);
            assertThat(gezagsrelatie.getBsnMeerderjarige()).isEqualTo(expected);
            assertThat(gezagsrelatie.getSoortGezag()).isEqualTo(Gezagsrelatie.SoortGezagEnum.OG1);
        });
    }

    static Stream<Arguments> opvragenBevoegdheidTotGezagMinderjarige5() {
        return Stream.of(
                // (input, expected)
                Arguments.of("999998316", Set.of("999998778", "999998791")), // Lg01_003
                Arguments.of("999998341", Set.of("999998778", "999998791")), // Lg01_005
                Arguments.of("999998328", Set.of("999998778", "999998791")), // Lg01_006
                Arguments.of("999998651", Set.of("999998626", "999998638")), // Lg01_034
                Arguments.of("999998663", Set.of("999998626", "999998638")), // Lg01_035
                Arguments.of("999998705", Set.of("999998675", "999998687")), // Lg01_039
                Arguments.of("999998808", Set.of("999998742", "999998729")), // Lg01_043
                Arguments.of("999998833", Set.of("999998821", "999998766")), // Lg01_048
                Arguments.of("999998869", Set.of("999998870", "999998882")), // Lg01_051
                Arguments.of("999998894", Set.of("999998870", "999998882")), // Lg01_054
                Arguments.of("999998924", Set.of("999998912", "999998900")), // Lg01_060
                Arguments.of("999999059", Set.of("999999047", "999999035")), // Lg01_067
                Arguments.of("999999060", Set.of("999999035", "999999047")), // Lg01_068
                Arguments.of("999999448", Set.of("999998870", "999998882")), // Lg01_084
                Arguments.of("999999825", Set.of("999999795", "999999783")), // Lg01_118
                Arguments.of("999999886", Set.of("999999862", "999999874")), // Lg01_124
                Arguments.of("999999916", Set.of("999999904", "999999898")), // Lg01_127
                Arguments.of("999999941", Set.of("999999904", "999999898")), // Lg01_129
                Arguments.of("999999977", Set.of("999999953", "999999965")), // Lg01_132
                Arguments.of("999970471", Set.of("999970458", "999970446")), // Lg01_142
                Arguments.of("999970483", Set.of("999970458", "999970446")), // Lg01_143
                Arguments.of("999970495", Set.of("999970458", "999970446")), // Lg01_144
                Arguments.of("999970537", Set.of("999970513", "999970501")), // Lg01_148
                Arguments.of("999970562", Set.of("999970513", "999970501")), // Lg01_151
                Arguments.of("999970604", Set.of("999970598", "999970586")), // Lg01_155
                Arguments.of("999970641", Set.of("999970616", "999970653")), // Lg01_158
                Arguments.of("999970665", Set.of("999970616", "999970653")), // Lg01_160
                Arguments.of("999970045", Set.of("999999126", "999999163")), // Lg01_168
                Arguments.of("999970070", Set.of("999970057", "999970069")), // Lg01_171
                Arguments.of("999970355", Set.of("999998870", "999998882")), // Lg01_193
                Arguments.of("999970367", Set.of("999998870", "999998882")), // Lg01_194
                Arguments.of("999970379", Set.of("999998870", "999998882")), // Lg01_195
                Arguments.of("999970380", Set.of("999998870", "999998882")), // Lg01_196
                Arguments.of("999970409", Set.of("999970124", "999970410")) // Lg01_198
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("""
        given a burgerservicenummer as input,
        when sending a POST request to `/opvragenBevoegdheidTotGezagMinderjarige`,
        the response should contain a single gezagsrelatie with `soortGezag: OG2` and the expected `bsnMeerderjarige`
        """)
    void opvragenBevoegdheidTotGezagMinderjarige5(String input, Set<String> expected) {
        var request = new GezagRequest().bsn(input);

        webTestClient.post().uri("/api/v1/opvragenBevoegdheidTotGezagMinderjarige")
                .contentType(MediaType.APPLICATION_JSON)
                .header("OIN", OIN)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Gezagsrelatie.class).consumeWith(response -> {
            var results = response.getResponseBody();

            System.out.println(input);
            System.out.println(results);

            assertThat(results).isNotNull();
            assertThat(results).hasSize(2);

            var gezagshouders = results.stream()
                    .map(Gezagsrelatie::getBsnMeerderjarige)
                    .toList();
            assertThat(gezagshouders).containsExactlyInAnyOrderElementsOf(expected);

            var soortenGezag = results.stream()
                    .map(Gezagsrelatie::getSoortGezag)
                    .toList();
            assertThat(soortenGezag).containsOnly(Gezagsrelatie.SoortGezagEnum.OG2);
        });
    }

    static Stream<Arguments> opvragenBevoegdheidTotGezagMinderjarig6() {
        return Stream.of(
                // (input, expected)
                Arguments.of("999998572", Set.of("")), // Lg01_026
                Arguments.of("999998717", Set.of("")), // Lg01_040
                Arguments.of("999998961", Set.of("")), // Lg01_057
                Arguments.of("999999205", Set.of("999999199")), // Lg01_080
                Arguments.of("999999515", Set.of("")), // Lg01_090
                Arguments.of("999999618", Set.of("")), // Lg01_099
                Arguments.of("999999680", Set.of("")), // Lg01_105
                Arguments.of("999999722", Set.of("")), // Lg01_109
                Arguments.of("999999758", Set.of("")), // Lg01_112
                Arguments.of("999999771", Set.of("")), // Lg01_113
                Arguments.of("999970707", Set.of("")), // Lg01_164
                Arguments.of("999970719", Set.of("")), // Lg01_165
                Arguments.of("999970112", Set.of("")), // Lg01_175
                Arguments.of("999970173", Set.of("")), // Lg01_179
                Arguments.of("999970215", Set.of("999970203")), // Lg01_183
                Arguments.of("999970252", Set.of("999970240")) // Lg01_187
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("""
        given a burgerservicenummer as input,
        when sending a POST request to `/opvragenBevoegdheidTotGezagMinderjarige`,
        the response should contain a single gezagsrelatie with `soortGezag: V` and the expected `bsnMeerderjarige`
        """)
    void opvragenBevoegdheidTotGezagMinderjarig6(String input, Set<String> expected) {
        var request = new GezagRequest().bsn(input);

        webTestClient.post().uri("/api/v1/opvragenBevoegdheidTotGezagMinderjarige")
                .contentType(MediaType.APPLICATION_JSON)
                .header("OIN", OIN)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Gezagsrelatie.class).consumeWith(response -> {
            var results = response.getResponseBody();

            System.out.println(input);
            System.out.println(results);

            assertThat(results).isNotNull();
            assertThat(results).hasSize(1);

            var gezagshouders = results.stream()
                    .map(Gezagsrelatie::getBsnMeerderjarige)
                    .toList();
            assertThat(gezagshouders).containsExactlyInAnyOrderElementsOf(expected);

            var soortenGezag = results.stream()
                    .map(Gezagsrelatie::getSoortGezag)
                    .toList();
            assertThat(soortenGezag).containsOnly(Gezagsrelatie.SoortGezagEnum.V);
        });
    }

    static Stream<Arguments> opvragenBevoegdheidTotGezagMinderjarig7() {
        return Stream.of(
                // (input, expected)

                Arguments.of("999999801", Set.of("999999795", "999999783")), // Lg01_116
                Arguments.of("999999813", Set.of("999999795", "999999783")), // Lg01_117
                Arguments.of("999999850", Set.of("999999849", "999999837")), // Lg01_121
                Arguments.of("999970161", Set.of("999970124", "999970136")) // Lg01_178
        );
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("""
        given a burgerservicenummer as input,
        when sending a POST request to `/opvragenBevoegdheidTotGezagMinderjarige`,
        the response should contain a single gezagsrelatie with `soortGezag: GG` and the expected `bsnMeerderjarige`
        """)
    void opvragenBevoegdheidTotGezagMinderjarig7(String input, Set<String> expected) {
        var request = new GezagRequest().bsn(input);

        webTestClient.post().uri("/api/v1/opvragenBevoegdheidTotGezagMinderjarige")
                .contentType(MediaType.APPLICATION_JSON)
                .header("OIN", OIN)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Gezagsrelatie.class).consumeWith(response -> {
            var results = response.getResponseBody();

            System.out.println(input);
            System.out.println(results);

            assertThat(results).isNotNull();
            assertThat(results).hasSize(2);

            var gezagshouders = results.stream()
                    .map(Gezagsrelatie::getBsnMeerderjarige)
                    .toList();
            assertThat(gezagshouders).containsExactlyInAnyOrderElementsOf(expected);

            var soortenGezag = results.stream()
                    .map(Gezagsrelatie::getSoortGezag)
                    .toList();
            assertThat(soortenGezag).containsOnly(Gezagsrelatie.SoortGezagEnum.GG);
        });
    }
}
