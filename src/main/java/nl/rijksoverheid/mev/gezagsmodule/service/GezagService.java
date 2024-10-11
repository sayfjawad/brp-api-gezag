package nl.rijksoverheid.mev.gezagsmodule.service;

import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.model.GezagAfleidingsResultaat;
import nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie;
import nl.rijksoverheid.mev.transaction.Transaction;

import java.util.List;

public interface GezagService {

    List<Gezagsrelatie> getGezag(final List<String> bsns, final Transaction transaction);

    GezagAfleidingsResultaat getGezagAfleidingsResultaat(final String bsn, final Transaction transaction) throws GezagException;

    Persoonslijst ophalenOuder1(final Persoonslijst plPersoon, final Transaction originalTransaction);

    Persoonslijst ophalenOuder2(final Persoonslijst plPersoon, final Transaction originalTransaction);

    Persoonslijst ophalenNietOuder(final Persoonslijst plPersoon, final Persoonslijst plOuder1, final Persoonslijst plOuder2, final Transaction originalTransaction);
}
