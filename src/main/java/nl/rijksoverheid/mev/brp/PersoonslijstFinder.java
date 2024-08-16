package nl.rijksoverheid.mev.brp;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.model.Burgerservicenummer;

import java.util.Optional;

public interface PersoonslijstFinder {

    Optional<Persoonslijst> findPersoonslijst(Burgerservicenummer burgerservicenummer);
}
