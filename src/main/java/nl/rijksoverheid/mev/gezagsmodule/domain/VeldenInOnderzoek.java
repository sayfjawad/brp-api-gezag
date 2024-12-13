package nl.rijksoverheid.mev.gezagsmodule.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Object met verzameling velden in onderzoek met koppeling aan welke persoon
 */
@JsonInclude(Include.NON_NULL)
public class VeldenInOnderzoek {

    private Set<String> persoon;
    private Set<String> ouder1;
    private Set<String> ouder2;
    private Set<String> nietOuder;

    /**
     * @return velden in onderzoek voor persoon
     */
    public Set<String> getPersoon() {
        return Objects.requireNonNullElse(persoon, Collections.emptySet());
    }

    /**
     * @param persoon velden in onderzoek voor persoon
     */
    public void setPersoon(final Set<String> persoon) {
        this.persoon = persoon;
    }

    /**
     * @return velden in onderzoek voor ouder1
     */
    public Set<String> getOuder1() {
        return Objects.requireNonNullElse(ouder1, Collections.emptySet());
    }

    /**
     * @param ouder1 velden in onderzoek voor ouder1
     */
    public void setOuder1(final Set<String> ouder1) {
        this.ouder1 = ouder1;
    }

    /**
     * @return velden in onderzoek voor ouder2
     */
    public Set<String> getOuder2() {
        return Objects.requireNonNullElse(ouder2, Collections.emptySet());
    }

    /**
     * @param ouder2 velden in onderzoek voor ouder2
     */
    public void setOuder2(final Set<String> ouder2) {
        this.ouder2 = ouder2;
    }

    /**
     * @return velden in onderzoek voor niet ouder
     */
    public Set<String> getNietOuder() {
        return Objects.requireNonNullElse(nietOuder, Collections.emptySet());
    }

    /**
     * @param nietOuder velden in onderzoek voor niet ouder
     */
    public void setNietOuder(final Set<String> nietOuder) {
        this.nietOuder = nietOuder;
    }

    /**
     * @return of er velden in onderzoek zijn in dit object
     */
    public boolean hasValues() {
        return persoon != null && !persoon.isEmpty()
                || ouder1 != null && !ouder1.isEmpty()
                || ouder2 != null && !ouder2.isEmpty()
                || nietOuder != null && !nietOuder.isEmpty();
    }
}
