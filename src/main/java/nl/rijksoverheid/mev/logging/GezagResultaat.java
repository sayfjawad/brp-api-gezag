package nl.rijksoverheid.mev.logging;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class GezagResultaat {
    private long plId;
    private String type;
    private String toelichting;
    private String route;
}
