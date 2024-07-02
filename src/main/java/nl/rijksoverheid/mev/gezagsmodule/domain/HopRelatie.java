package nl.rijksoverheid.mev.gezagsmodule.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
HopRelaties is nodig om op basis van de gebeurtenissen relaties te reconstrueren
 */
@NoArgsConstructor
@Getter
@Setter
public class HopRelatie {

    private String partner;
    private Integer startDatum;
    private Integer eindDatum;
    private String reden;
}
