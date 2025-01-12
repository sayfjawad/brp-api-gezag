package nl.rijksoverheid.mev.logging;

public record GezagResultaat(
    long plId,
    String type,
    String toelichting,
    String route
) {
}
