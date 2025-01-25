package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

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

    private final List<GezagVraag> gezagVraags;


    public HashMap<String, GezagVraag> getGezagVraagFunctionsMap() {

        return gezagVraags.stream()
                .collect(Collectors.toMap(
                        GezagVraag::getQuestionId,
                        Function.identity(),
                        (oldVal, newVal) -> oldVal, HashMap::new));
    }
}
