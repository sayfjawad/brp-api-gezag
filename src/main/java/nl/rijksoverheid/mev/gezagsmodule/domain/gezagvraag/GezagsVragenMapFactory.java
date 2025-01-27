package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GezagsVragenMapFactory {

    private final List<GezagVraag> gezagVraags;

    public HashMap<String, GezagVraag> getGezagVraagFunctionsMap() {
        return gezagVraags.stream()
                .collect(Collectors.toMap(
                        GezagVraag::getQuestionId,
                        Function.identity(),
                        (oldVal, newVal) -> oldVal, HashMap::new));
    }
}
