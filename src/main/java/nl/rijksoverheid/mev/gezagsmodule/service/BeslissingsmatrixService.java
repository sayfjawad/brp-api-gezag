package nl.rijksoverheid.mev.gezagsmodule.service;

import lombok.extern.slf4j.Slf4j;
import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.exception.VeldInOnderzoekException;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service voor beslissingsmatrix
 */
@Slf4j
@Component
public class BeslissingsmatrixService {

    private static final String ANTWOORDEN_MODEL_FILENAME = "/AntwoordenModel_v2_2_3.csv";

    private Map<String, ARAntwoordenModel> routes;

    public BeslissingsmatrixService() {
        determineRoutes();
    }

    /**
     * Vraag AR antwoorden model op, op basis van de route van het resultaat
     *
     * @param resultaat resultaat
     * @return het antwoorden model
     * @throws AfleidingsregelException wanneer de route niet kan worden gevonden
     */
    public ARAntwoordenModel getARAntwoordenModel(final ARAntwoordenModel resultaat) throws AfleidingsregelException {
        String route = resultaat.getRoute();
        if (routes.containsKey(route)) {
            return routes.get(route);
        } else {
            log.error("Route kon niet worden gevonden, route is: {} en exceptie: {}", route, resultaat.getException());
            throw new AfleidingsregelException("Route kon niet worden gevonden in het ingelezen antwoorden model, de route is: " + route, "onbekend");
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
            && (Objects.equals(arAntwoordenModel.getException().getClass(), VeldInOnderzoekException.class))) {
            return getRouteFromVraagModel(arAntwoordenModel);
        } else {
            return getRouteFromVraagModel(arAntwoordenModel);
        }
    }

    private void determineRoutes() {
        var inputStream = BeslissingsmatrixService.class.getResourceAsStream(ANTWOORDEN_MODEL_FILENAME);
        if (inputStream == null) throw new IllegalStateException("Unable to find AntwoordenModel");
        List<String> antwoordenModelLines = new BufferedReader(new InputStreamReader(inputStream)).lines().toList();

        Map<String, ARAntwoordenModel> tempMap = antwoordenModelLines.stream()
            .skip(1) // Skip the first line with labels
            .map(line -> line.split(","))
            .map(data -> {
                var exception = data[16].isEmpty() ? null : new Exception(data[16]);
                var route = data[17];

                var dataNullable = Arrays.stream(data).map(it -> it.isEmpty() ? null : it).toList();

                var antwoordenModel = new ARAntwoordenModel(
                    dataNullable.get(0), // v0101
                    dataNullable.get(1), // v0102
                    dataNullable.get(2), // v0103
                    dataNullable.get(3), // v0103a
                    dataNullable.get(4), // v0103b
                    dataNullable.get(5), // v0104
                    dataNullable.get(6), // v0201
                    dataNullable.get(7), // v02A01
                    dataNullable.get(8), // v02A02
                    dataNullable.get(9), // v02A03
                    dataNullable.get(10), // v02B01
                    dataNullable.get(11), // v0301
                    dataNullable.get(12), // v0302
                    dataNullable.get(13), // v04A02
                    dataNullable.get(14), // v04A03
                    dataNullable.get(15), // v04B01
                    exception,
                    dataNullable.get(17), // route
                    dataNullable.get(18), // soortGezag
                    dataNullable.get(19), // gezag_ouder1
                    dataNullable.get(20), // gezag_ouder2
                    dataNullable.get(21), // gezag_niet-ouder1
                    dataNullable.get(22), // gezag_niet-ouder2
                    dataNullable.get(23), // index
                    dataNullable.get(24) // uitleg
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
