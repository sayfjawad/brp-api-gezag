package nl.rijksoverheid.mev.gezagsmodule.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    @EqualsAndHashCode.Exclude
    private Exception exception = null;
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
    private String index = null;
    @EqualsAndHashCode.Exclude
    private String uitleg = null;

    @Override
    public String toString() {
        return "ARAntwoordenModel{" +
            "soortGezag='" + soortGezag + '\'' +
            ", gezagOuder1='" + gezagOuder1 + '\'' +
            ", gezagOuder2='" + gezagOuder2 + '\'' +
            ", gezagNietOuder1='" + gezagNietOuder1 + '\'' +
            ", route='" + route + '\'' +
            ", uitleg='" + uitleg + '\'' +
            ", exception=" + exception +
            ", v0101='" + v0101 + '\'' +
            ", v0102='" + v0102 + '\'' +
            ", v0103='" + v0103 + '\'' +
            ", v0103A='" + v0103A + '\'' +
            ", v0103B='" + v0103B + '\'' +
            ", v0104='" + v0104 + '\'' +
            ", v0201='" + v0201 + '\'' +
            ", v02A01='" + v02A01 + '\'' +
            ", v02A02='" + v02A02 + '\'' +
            ", v02A03='" + v02A03 + '\'' +
            ", v02B01='" + v02B01 + '\'' +
            ", v0301='" + v0301 + '\'' +
            ", v0302='" + v0302 + '\'' +
            ", v04A02='" + v04A02 + '\'' +
            ", v04A03='" + v04A03 + '\'' +
            ", v04B01='" + v04B01 + '\'' +
            '}';
    }
}