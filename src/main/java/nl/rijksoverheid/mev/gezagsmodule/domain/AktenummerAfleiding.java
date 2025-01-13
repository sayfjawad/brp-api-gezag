package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.util.*;

/**
 * Functionaliteit voor het afleiden van aktenummers
 */
public class AktenummerAfleiding {

    private static final char TABEL_39_AKTEAANDUIDING_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE = 'B';
    private static final char TABEL_39_AKTEAANDUIDING_ONTKENNING_OUDERSCHAP = 'E';
    private static final char TABEL_39_AKTEAANDUIDING_ERKENNING_NA_DE_GEBOORTEAANGIFTE = 'C';
    private static final char TABEL_39_AKTEAANDUIDING_NOTARIELE_AKTE_VAN_ERKENNING = 'J';
    private static final char TABEL_39_AKTEAANDUIDING_ADOPTIE = 'Q';
    private static final char TABEL_39_AKTEAANDUIDING_GEBOORTE = 'A';
    private static final char TABEL_39_AKTEAANDUIDING_GERECHTELIJKE_VASTSTELLING_OUDERSCHAP = 'V';
    private static final Set<Character> erkenningsCodes = new HashSet<>(Arrays.asList(
        TABEL_39_AKTEAANDUIDING_ERKENNING_BIJ_DE_GEBOORTE_AANGIFTE,
        TABEL_39_AKTEAANDUIDING_ERKENNING_NA_DE_GEBOORTEAANGIFTE,
        TABEL_39_AKTEAANDUIDING_NOTARIELE_AKTE_VAN_ERKENNING,
        TABEL_39_AKTEAANDUIDING_GERECHTELIJKE_VASTSTELLING_OUDERSCHAP));

    private static final int AKTE_NUMMER_LENGTE = 3;

    private AktenummerAfleiding() {
    }

    /**
     * Valideer of de persoon erkend is / een persoon heeft erkend.
     * Dit is af te lezen uit het aktenummer actueel en historisch.
     * Waarbij zowel op de persoon als bij de ouders als 3e karakter de indicatie staat opgenomen.
     * <p>
     * Indicaties:
     * <ul>
     *     <li>Erkenning bij geboorte aangifte: B</li>
     *     <li>Erkenning na geboorte aangifte: C</li>
     *     <li>Erkenning middels notariele akte: J</li>
     *     <li>Erkenning middels gerechtelijke vaststelleing: V</li>
     * </ul>
     *
     * @param current  de huidige gegevens
     * @param historic de historische gegevens
     * @return of de gegeven persoon is / heeft erkend
     */
    public static boolean ongeborenVruchtErkendOfGerechtelijkeVaststelling(final WithAktenummer current, final List<WithAktenummer> historic) {
        return controleerAkteNummers(current, historic, erkenningsCodes);
    }

    /**
     * Bepaal of de gegeven persoon (een ouder) de erkenning heeft ontkend.
     * Hierbij is als 3e karakter de indicatie opgenomen: E
     *
     * @param current  de huidige gegevens
     * @param historic de historische gegevens
     * @return of de gegeven persoon heeft ontkend
     */
    public static boolean ontkenningOuderschapDoorOuder(final WithAktenummer current, final List<WithAktenummer> historic) {
        return controleerAkteNummers(current, historic, Set.of(TABEL_39_AKTEAANDUIDING_ONTKENNING_OUDERSCHAP));
    }

    /**
     * Bepaal of de gegeven persoon als ongeboren vrucht is erkend.
     * Hierbij is als 3e karakter de indicatie opgenomen: A
     *
     * @param current  de huidige gegevens
     * @param historic de historische gegevens
     * @return of de gegeven persoon als ongeboren vrucht is erkend
     */
    public static boolean ongeborenVruchtErkend(final WithAktenummer current, final List<WithAktenummer> historic) {
        return controleerAkteNummers(current, historic, Set.of(TABEL_39_AKTEAANDUIDING_GEBOORTE));
    }

    /**
     * Bepaal of de gegeven persoon is geadopteerd.
     * Hierbij is als 3e karakter de indicatie opgenomen: Q
     *
     * @param current  de huidige gegevens
     * @param historic de historische gegevens
     * @return of de gegeven persoon is geadopteerd
     */
    public static boolean geadopteerdMetNlAkte(final WithAktenummer current, final List<WithAktenummer> historic) {
        return controleerAkteNummers(current, historic, Set.of(TABEL_39_AKTEAANDUIDING_ADOPTIE));
    }

    private static boolean controleerAkteNummers(final WithAktenummer current, final List<WithAktenummer> historic, final Set<Character> geldigeErkenningCodes) {
        if (geldigeErkenningCodes != null) {
            List<String> akteNummers = new ArrayList<>();
            if (current != null) {
                akteNummers.add(current.getAktenummer());
            }
            if (historic != null) {
                akteNummers.addAll(historic.stream().map(WithAktenummer::getAktenummer).toList());
            }

            return akteNummers.stream()
                .filter(aktenummer -> aktenummer != null && aktenummer.length() >= AKTE_NUMMER_LENGTE)
                .anyMatch(aktenummer -> geldigeErkenningCodes.contains(aktenummer.charAt(2)));
        } else {
            return false;
        }
    }
}

