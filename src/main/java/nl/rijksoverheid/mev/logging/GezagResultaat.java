package nl.rijksoverheid.mev.logging;

public record GezagResultaat(
    Long plId,
    String type,
    String toelichting,
    String route
) {
}
