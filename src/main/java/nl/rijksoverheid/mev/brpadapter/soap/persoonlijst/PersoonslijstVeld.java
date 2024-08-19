package nl.rijksoverheid.mev.brpadapter.soap.persoonlijst;

/**
 * Een veld in de persoonslijst (key + value)
 */
public interface PersoonslijstVeld {

    /**
     * Haal de waarde op voor een key
     *
     * @param key de key
     * @return de waarde of null
     */
    String get(final String key);

    /**
     * Haal de waarde op voor een key
     *
     * @param key de key
     * @param fieldName de naam van het opgevraagde veld, dit wordt gebruikt om
     * een gebruikers leesbare text te generen wanneer het veld in onderzoek is.
     * Deze waarde is optioneel (e.g. Inschrijving is niet een potentieel in
     * onderzoek object) en kan gevuld worden wanneer de naam van het veld
     * relevant is voor de gezag bepaling.
     * @return de waarde of null
     */
    String get(final String key, final String fieldName);
}
