package nl.rijksoverheid.mev.brpadapter.soap;

import nl.rijksoverheid.mev.exception.BrpException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.transaction.Transaction;

/**
 * Client voor communicatie met de BRP
 */
public interface BrpClient {

    /**
     * Opvragen persoonslijst bij de BRP
     *
     * @param bsn de bsn van de persoon waarvoor de persoonslijst wordt
     * opgevraagd
     * @param transaction de transactie zoals bij binnenkomst van het request
     * @return de persoonslijst
     * @throws BrpException wanneer een exceptie optreed bij de communicatie met
     * de BRP
     */
    Persoonslijst opvragenPersoonslijst(final String bsn, final Transaction transaction) throws BrpException;
}