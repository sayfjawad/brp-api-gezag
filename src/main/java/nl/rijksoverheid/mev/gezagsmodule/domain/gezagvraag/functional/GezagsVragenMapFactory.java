package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
@RequiredArgsConstructor
public class GezagsVragenMapFactory {

    private final List<GezagVraagFunction> gezagVraagFunctions;


    public HashMap<String, GezagVraagFunction> getGezagVraagFunctionsMap() {

        return gezagVraagFunctions.stream()
                .collect(Collectors.toMap(
                        GezagVraagFunction::getQuestionId,
                        Function.identity(),
                        (oldVal, newVal) -> oldVal, HashMap::new));
    }
}
