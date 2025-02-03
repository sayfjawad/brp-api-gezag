package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Factory voor het samenstellen van een Map<vraag id, gezag vraag> op basis van autowiring
 */
@Component
@RequiredArgsConstructor
public class GezagsVragenMapFactory {

    private final List<GezagVraag> gezagVragen;

    /**
     * @return de verzameling gezagsvragen (alle classen die `implements GezagVraag`) op basis van vraag ID
     */
    public Map<String, GezagVraag> getGezagVragen() {
        return gezagVragen.stream()
            .collect(Collectors.toMap(
                GezagVraag::getQuestionId,
                Function.identity(),
                (oldVal, newVal) -> oldVal, HashMap::new));
    }
}
