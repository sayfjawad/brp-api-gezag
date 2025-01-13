package nl.rijksoverheid.mev.gezagsmodule.domain;

/**
 * Wordt gebruikt voor het mogelijk maken van het generiek opvragen van het aktenummer van objecten
 */
public interface WithAktenummer {

    /**
     * @return het aktenummer of null
     */
    String getAktenummer();
}
