package nl.rijksoverheid.mev.gezagsmodule.domain;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ARAntwoordenModel {
    private String v0101 = null;
    private String v0102 = null;
    private String v0103 = null;
    private String v0103A = null;
    private String v0103B = null;
    private String v0104 = null;
    private String v0201 = null;
    private String v02A01 = null;
    private String v02A02 = null;
    private String v02A03 = null;
    private String v02B01 = null;
    private String v0301 = null;
    private String v0302 = null;
    private String v04A02 = null;
    private String v04A03 = null;
    private String v04B01 = null;
    // Voorlopig ongebruikte input
    @EqualsAndHashCode.Exclude
    private Exception exception = null;
    // Dit zijn outputs
    @EqualsAndHashCode.Exclude
    private String route = null;
    @EqualsAndHashCode.Exclude
    private String soortGezag = null;
    @EqualsAndHashCode.Exclude
    private String gezagOuder1 = null;
    @EqualsAndHashCode.Exclude
    private String gezagOuder2 = null;
    @EqualsAndHashCode.Exclude
    private String gezagNietOuder1 = null;
    @EqualsAndHashCode.Exclude
    private String gezagNietOuder2 = null;
    @EqualsAndHashCode.Exclude
    private String index = null;
    @EqualsAndHashCode.Exclude
    private String uitleg = null;
}