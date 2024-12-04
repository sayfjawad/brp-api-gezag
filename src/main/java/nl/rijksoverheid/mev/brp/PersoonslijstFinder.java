package nl.rijksoverheid.mev.brp;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

import java.util.Optional;

public interface PersoonslijstFinder {

    Optional<Persoonslijst> opvragenPersoonslijst(String bsn);

}
