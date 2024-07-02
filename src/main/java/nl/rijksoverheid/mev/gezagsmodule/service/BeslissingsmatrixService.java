package nl.rijksoverheid.mev.gezagsmodule.service;

import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import nl.rijksoverheid.mev.exception.BrpException;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.exception.VeldInOnderzoekException;
import org.springframework.stereotype.Component;

/**
 * Service voor beslissingsmatrix
 */
@Slf4j
@Component
public class BeslissingsmatrixService {

    private Map<String, ARAntwoordenModel> routes;

    private static final String LEEG = "";

    public BeslissingsmatrixService() {
        determineRoutes();
    }

    /**
     * Vraag AR antwoorden model op, op basis van de route van het resultaat
     *
     * @param resultaat resultaat
     * @return het antwoorden model
     * @throws AfleidingsregelException wanneer de route niet kan worden
     * gevonden
     */
    public ARAntwoordenModel getARAntwoordenModel(final ARAntwoordenModel resultaat) throws AfleidingsregelException {
        String route = resultaat.getRoute();
        if (routes.containsKey(route)) {
            return routes.get(route);
        } else {
            log.error("Route kon niet worden gevonden, route is: {} en exceptie: {}", route, resultaat.getException());
            throw new AfleidingsregelException("Route kon niet worden gevonden in het ingelezen antwoorden model, de route is: " + route);
        }
    }

    /**
     * Vraag bij passende route op
     *
     * @param arAntwoordenModel het model om de route voor te bepalen
     * @return de route of "-503i"
     */
    public String findMatchingRoute(final ARAntwoordenModel arAntwoordenModel) {
        if ((arAntwoordenModel.getException() != null)
                && (Objects.equals(arAntwoordenModel.getException().getClass(), BrpException.class))) {
            return "-503i";
        } else if ((arAntwoordenModel.getException() != null)
                && (Objects.equals(arAntwoordenModel.getException().getClass(), VeldInOnderzoekException.class))) {
            String route = getRouteFromVraagModel(arAntwoordenModel);

            return route + "i";
        } else {
            return getRouteFromVraagModel(arAntwoordenModel);
        }
    }

    private void determineRoutes() {
        List<String> antwoordenModelLines = new ArrayList<>();
        try (BufferedReader antwoordenModelCsvReader = new BufferedReader(
                new InputStreamReader(BeslissingsmatrixService.class.getResourceAsStream("/AntwoordenModel_v2_2_0.csv")))) {
            String line;
            while ((line = antwoordenModelCsvReader.readLine()) != null) {
                antwoordenModelLines.add(line);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        Map<String, ARAntwoordenModel> tempMap = antwoordenModelLines.stream()
                .skip(1) // Skip the first line with labels
                .map(line -> line.split(","))
                .map(data -> {
                    var route = data[17];
                    var antwoordenModel = new ARAntwoordenModel(
                            data[0].equals(LEEG) ? null : data[0], // v0101
                            data[1].equals(LEEG) ? null : data[1], // v0102
                            data[2].equals(LEEG) ? null : data[2], // v0103
                            data[3].equals(LEEG) ? null : data[3], // v0103a
                            data[4].equals(LEEG) ? null : data[4], // v0103b
                            data[5].equals(LEEG) ? null : data[5], // v0104
                            data[6].equals(LEEG) ? null : data[6], // v0201
                            data[7].equals(LEEG) ? null : data[7], // v02A01
                            data[8].equals(LEEG) ? null : data[8], // v02A02
                            data[9].equals(LEEG) ? null : data[9], // v02A03
                            data[10].equals(LEEG) ? null : data[10], // v02B01
                            data[11].equals(LEEG) ? null : data[11], // v0301
                            data[12].equals(LEEG) ? null : data[12], // v0302
                            data[13].equals(LEEG) ? null : data[13], // v04A02
                            data[14].equals(LEEG) ? null : data[14], // v04A03
                            data[15].equals(LEEG) ? null : data[15], // v04B01
                            data[16].equals(LEEG) ? null : new Exception(data[16]), // exception
                            data[17].equals(LEEG) ? null : data[17], // route
                            data[18].equals(LEEG) ? null : data[18], // soortGezag
                            data[19].equals(LEEG) ? null : data[19], // gezag_ouder1
                            data[20].equals(LEEG) ? null : data[20], // gezag_ouder2
                            data[21].equals(LEEG) ? null : data[21], // gezag_niet-ouder1
                            data[22].equals(LEEG) ? null : data[22], // gezag_niet-ouder2
                            data[23].equals(LEEG) ? null : data[23], // index
                            data[24].equals(LEEG) ? null : data[24] // uitleg
                    );
                    return Map.entry(route, antwoordenModel);
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a, // Merge function to resolve potential key collisions
                        LinkedHashMap::new // Use LinkedHashMap to preserve insertion order
                ));
        routes = Collections.unmodifiableMap(tempMap);
    }

    private String getRouteFromVraagModel(ARAntwoordenModel arAntwoordenModel) {
        String route = "-1";
        for (Map.Entry<String, ARAntwoordenModel> entry : routes.entrySet()) {
            if (entry.getValue().equals(arAntwoordenModel)) {
                route = entry.getKey();
                break;
            }
        }

        return route;
    }
}
