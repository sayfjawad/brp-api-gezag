package nl.rijksoverheid.mev.persoonsgegevens;

import nl.rijksoverheid.mev.gezagsmodule.domain.Inschrijving;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.openapitools.model.Persoonsgegevens;

/**
 * Breid {@link Persoonsgegevens} uit met extra methodes.
 */
final class PersoongegevensExtensions {

    private PersoongegevensExtensions() {
    }

    /**
     * Geeft <i>persoonsgegevens</i> terug gegeven een vergelijkbaar model uit
     * de context van Basisregistratie Personen.
     *
     * @param persoonsgegevens persoonsgegevens model uit de context van
     * Basisregistratie Personen
     * @return <i>persoonsgegevens</i> model in de context van deze module
     */
    static Persoonsgegevens of(final Persoonslijst persoonLijst) {
        Persoon persoon = persoonLijst.getPersoon();
        Inschrijving inschrijving = persoonLijst.getInschrijving();
        
        return new Persoonsgegevens()
                .bsn(persoon.getBsn())
                .voornamen(persoon.getVoornamen())
                .voorvoegsel(persoon.getVoorvoegsel())
                .geslachtsnaam(persoon.getGeslachtsnaam())
                .geboortedatum(persoon.getGeboortedatum())
                .opschortReden(inschrijving != null ? inschrijving.getRedenOpschortingBijhouding() : null)
                .opschortDatum(inschrijving != null ? inschrijving.getDatumOpschortingBijhouding() : null);
    }
}
