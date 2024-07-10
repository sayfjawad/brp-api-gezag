package nl.rijksoverheid.mev.gezagsmodule.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;

/**
 * Object met verzameling velden in onderzoek met koppeling aan welke persoon
 */
@JsonInclude(Include.NON_NULL)
public class VeldenInOnderzoek {
    
    private List<String> persoon;
    private List<String> ouder1;
    private List<String> ouder2;
    private List<String> nietOuder;

    /**
     * @return velden in onderzoek voor persoon
     */
    public List<String> getPersoon() {
        return persoon;
    }

    /**
     * @param persoon velden in onderzoek voor persoon
     */
    public void setPersoon(final List<String> persoon) {
        this.persoon = persoon;
    }

    /**
     * @return velden in onderzoek voor ouder1
     */
    public List<String> getOuder1() {
        return ouder1;
    }

    /**
     * @param ouder1 velden in onderzoek voor ouder1
     */
    public void setOuder1(final List<String> ouder1) {
        this.ouder1 = ouder1;
    }

    /**
     * @return velden in onderzoek voor ouder2
     */
    public List<String> getOuder2() {
        return ouder2;
    }
    
    /**
     * @param ouder2 velden in onderzoek voor ouder2
     */
    public void setOuder2(final List<String> ouder2) {
        this.ouder2 = ouder2;
    }

    /**
     * @return velden in onderzoek voor niet ouder
     */
    public List<String> getNietOuder() {
        return nietOuder;
    }

    /**
     * @param nietOuder velden in onderzoek voor niet ouder
     */
    public void setNietOuder(final List<String> nietOuder) {
        this.nietOuder = nietOuder;
    }

    /**
     * @return of er velden in onderzoek zijn in dit object
     */
    public boolean hasValues() {
        return persoon != null && !persoon.isEmpty() ||
               ouder1 != null && !ouder1.isEmpty() ||
               ouder2 != null && !ouder2.isEmpty() ||
               nietOuder != null && !nietOuder.isEmpty();
    }
}
