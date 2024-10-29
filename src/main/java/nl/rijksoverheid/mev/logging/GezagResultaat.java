package nl.rijksoverheid.mev.logging;

import lombok.Data;

@Data
public final class GezagResultaat {
    private long plId;
    private String type;
    private String toelichting;
    private String route;
}
