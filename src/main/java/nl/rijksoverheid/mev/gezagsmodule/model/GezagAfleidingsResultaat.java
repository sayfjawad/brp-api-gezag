package nl.rijksoverheid.mev.gezagsmodule.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.rijksoverheid.mev.gezagsmodule.domain.ARAntwoordenModel;
import java.util.List;

/**
 * Resultaat voor afleiden van gezag
 */
@Getter
@Setter
@NoArgsConstructor
public class GezagAfleidingsResultaat {

    private List<Gezagsrelatie> gezagsrelaties;
    private ARAntwoordenModel arAntwoordenModel;
    private String route;

    public GezagAfleidingsResultaat(
            final List<Gezagsrelatie> gezagsrelaties,
            final ARAntwoordenModel arAntwoordenModel,
            final String route) {
        this.gezagsrelaties = gezagsrelaties;
        this.arAntwoordenModel = arAntwoordenModel;
        this.route = route;
    }
}
