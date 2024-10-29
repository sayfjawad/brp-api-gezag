package nl.rijksoverheid.mev.gezagsmodule.model;

public record Burgerservicenummer(long value) {
    public String asString() {
        return String.valueOf(value);
    }
}
