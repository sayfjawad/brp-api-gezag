package nl.rijksoverheid.mev.brpadapter.soap;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.Categorie;
import nl.rijksoverheid.mev.common.util.BSNValidator;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.domain.*;
import nl.rijksoverheid.mev.transaction.Transaction;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Clock;
import java.util.*;

@Slf4j
@Component
@Profile("fake-brp")
public class BrpFakeClient implements BrpClient {

    private final Path datasetPath;
    private final Clock clock;
    private final HashMap<String, PersoonslijstFixture> fixtures;

    /**
     * @param datasetPath path to a directory containing a BRP dataset
     */
    public BrpFakeClient(@Value("${app.features.brp.dataset-path}") Path datasetPath, final Clock clock) {
        this.datasetPath = datasetPath;
        this.clock = clock;
        this.fixtures = new HashMap<>();
        List<Path> files = new ArrayList<>();
        try ( var stream = Files.newDirectoryStream(this.datasetPath)) {
            for(Path entry : stream) {
                 files.add(entry);
            }
            Collections.sort(files);
            for (Path entry : files) {
                if (Files.isRegularFile(entry)) {
                    var inputStream = Files.newInputStream(entry);
                    PersoonslijstFixture fixture = readAsPersoonslijstFixture(inputStream);
                    String bsn = fixture.arguments.bsn;
                    if (!fixtures.containsKey(bsn)){
                        fixtures.put(fixture.arguments.bsn,fixture);
                    }
                    else {
                        log.error(
                            "Duplicaat van BSN " + bsn + " gevonden in de testsets: \n" +
                                "Bestaand: " + fixtures.get(bsn).arguments.testcase + "\n"+
                                "Nieuw: " + fixture.arguments.testcase
                        );
                    }
                }
            }
        } catch (IOException e) {
            throw new StubException(e);
        }
    }

    @Override
    public Optional<Persoonslijst> opvragenPersoonslijst(String bsn, Transaction transaction) throws GezagException {
        if (!new BSNValidator().isValid(bsn)) {
            return Optional.empty();
        }
        if (fixtures.containsKey(bsn)){
            return Optional.of(fixtures.get(bsn).data);
        }
        log.info("Persoonslijst niet gevonden: " + bsn);
        return Optional.of(createEmptyPersoonslijstFixture("", bsn).data);
    }

    private PersoonslijstFixture readAsPersoonslijstFixture(final InputStream inputStream) {
        Persoonslijst persoonslijst = new Persoonslijst(clock);
        String bsn = null;
        String testcase = null;
        JSONObject json = new JSONObject(new JSONTokener(inputStream));
        if (!json.isEmpty()) {
            JSONObject arguments = (JSONObject) json.get("arguments");
            testcase = (String) arguments.get("testcase");
            bsn = (String) arguments.get("bsn");

            JSONObject returned = (JSONObject) json.get("returned");
            JSONArray names = returned.names();
            for (int i = 0; i < names.length(); i++) {
                String name = (String) names.get(i);

                Object obj = returned.get(name);
                if (obj instanceof JSONArray jsonArray) {
                    for (int j = 0; j < jsonArray.length(); j++) {
                        appendPersoonslijstVeld(name, jsonArray.getJSONObject(j), persoonslijst);
                    }
                } else if (obj instanceof JSONObject jsonObject) {
                    appendPersoonslijstVeld(name, jsonObject, persoonslijst);
                }
            }
        }

        return new PersoonslijstFixture(
                new PersoonslijstFixture.Arguments(testcase, bsn),
                persoonslijst,
                null
        );
    }

    private void appendPersoonslijstVeld(final String categorienummer, final JSONObject obj, final Persoonslijst persoonslijst) {
        JSONArray names = obj.names();
        if (names == null) return;

        Map<String, String> values = new HashMap<>();
        for (int i = 0; i < names.length(); i++) {
            String name = names.optString(i);
            if (!name.isEmpty()) {
                Object objEntry = obj.opt(name);
                if (objEntry instanceof String stringValue) {
                    values.put(name, stringValue);
                }
            }
        }

        switch (categorienummer) {
            case Categorie.PERSOON ->
                persoonslijst.addVeld(Categorie.PERSOON, new Persoon(values, clock));
            case Categorie.OUDER_1 ->
                persoonslijst.addVeld(Categorie.OUDER_1, new Ouder1(values, clock));
            case Categorie.OUDER_2 ->
                persoonslijst.addVeld(Categorie.OUDER_2, new Ouder2(values, clock));
            case Categorie.HUWELIJK_OF_PARTNERSCHAP ->
                persoonslijst.addVeldToList(Categorie.HUWELIJK_OF_PARTNERSCHAP, new HuwelijkOfPartnerschap(values, clock));
            case Categorie.INSCHRIJVING ->
                persoonslijst.addVeld(Categorie.INSCHRIJVING, new Inschrijving(values));
            case Categorie.VERBLIJFPLAATS ->
                persoonslijst.addVeld(Categorie.VERBLIJFPLAATS, new Verblijfplaats(values, clock));
            case Categorie.KIND ->
                persoonslijst.addVeldToList(Categorie.KIND, new Kind(values, clock));
            case Categorie.GEZAGSVERHOUDING ->
                persoonslijst.addVeld(Categorie.GEZAGSVERHOUDING, new Gezagsverhouding(values, clock));
            case Categorie.GESCHIEDENIS_PERSOON ->
                persoonslijst.addVeldToList(Categorie.GESCHIEDENIS_PERSOON, new GeschiedenisPersoon(values, clock));
            case Categorie.GESCHIEDENIS_OUDER_1 ->
                persoonslijst.addVeldToList(Categorie.GESCHIEDENIS_OUDER_1, new GeschiedenisOuder1(values, clock));
            case Categorie.GESCHIEDENIS_OUDER_2 ->
                persoonslijst.addVeldToList(Categorie.GESCHIEDENIS_OUDER_2, new GeschiedenisOuder2(values, clock));
            case Categorie.GESCHIEDENIS_HUWELIJK_OF_PARTNERSCHAP ->
                persoonslijst.addVeldToList(Categorie.GESCHIEDENIS_HUWELIJK_OF_PARTNERSCHAP, new GeschiedenisHuwelijkOfPartnerschap(values, clock));
            default -> {
                // niet gebruikte, maar wel aanwezige categorieën, niet importeren
            }
        }
    }

    private PersoonslijstFixture createEmptyPersoonslijstFixture(String testcase, String bsn) {
        return new PersoonslijstFixture(
                new PersoonslijstFixture.Arguments(testcase, bsn),
                new Persoonslijst(clock),
                null
        );
    }

    public record PersoonslijstFixture(
            Arguments arguments,
            @JsonProperty("returned") Persoonslijst data,
            Exception exception) {

        public record Arguments(String testcase, String bsn) {

        }

        public record Exception(String description) {

        }

        public boolean hasException() {
            return exception != null;
        }
    }

    public static class StubException extends RuntimeException {

        public StubException(Throwable cause) {
            super(cause);
        }
    }
}
