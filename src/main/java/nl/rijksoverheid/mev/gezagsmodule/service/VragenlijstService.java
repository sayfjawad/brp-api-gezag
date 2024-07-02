package nl.rijksoverheid.mev.gezagsmodule.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import nl.rijksoverheid.mev.exception.HoofdstroomSchemaNietGevondenExceptie;

/**
 * Leest de vragen en antwoorden van een CSV-bestand en slaat ze op in een map
 * van vragen en mogelijke antwoorden met de bijbehorende acties.
 */
@Service
@Slf4j
public class VragenlijstService {

    private static final String HOOFDSTROOMSCHEMA = "/hoofdstroomschema.csv";
    private final Map<String, Map<String, String>> vragenMap;

    public VragenlijstService() {
        vragenMap = new HashMap<>();

        readHoofdstroomSchema();
    }

    /**
     * @return Een map van vragen en mogelijke antwoorden met de bijbehorende
     * acties
     */
    public Map<String, Map<String, String>> getVragenMap() {
        return vragenMap;
    }

    private void readHoofdstroomSchema() {
        try (InputStream schemaFile = getClass().getResourceAsStream(HOOFDSTROOMSCHEMA); InputStreamReader is = new InputStreamReader(schemaFile); BufferedReader bis = new BufferedReader(is)) {
            String line;
            while ((line = bis.readLine()) != null) {
                String[] row = line.split(",");
                String question = row[0];
                String answer = row[1];
                String action = row[2];
                if (!vragenMap.containsKey(question)) {
                    vragenMap.put(question, new HashMap<>());
                }
                vragenMap.get(question).put(answer, action);
            }
        } catch (IOException | NullPointerException ex) {
            log.error("Not able to read {} file in the classpath", HOOFDSTROOMSCHEMA, ex);
            throw new HoofdstroomSchemaNietGevondenExceptie("Kan hoofdstroomschema CSV niet lezen");
        }
    }
}
