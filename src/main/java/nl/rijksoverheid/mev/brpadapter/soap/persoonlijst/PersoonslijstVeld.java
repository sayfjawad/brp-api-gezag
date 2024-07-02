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
}
