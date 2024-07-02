package nl.rijksoverheid.mev.gezagsmodule.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Gebeurtenis is nodig om huwelijkofpartnerschap actueel en geschiedenis te kunnen combineren
waarbij de datum van de gebeurtenis uit verschillende velden komt
 */
@NoArgsConstructor
@Getter
@Setter
public class Gebeurtenis {

    private String type;
    private String partner;
    private int datum;
    private String reden;
}
