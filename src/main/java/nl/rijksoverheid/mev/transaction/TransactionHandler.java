package nl.rijksoverheid.mev.transaction;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nl.rijksoverheid.mev.gezagsmodule.domain.VeldenInOnderzoek;
import nl.rijksoverheid.mev.gezagsmodule.service.PersoonlijstType;
import org.springframework.http.HttpStatusCode;

/**
 * Alle momenten waarop transacties worden gemaakt
 */
public interface TransactionHandler {

    /**
     * Maak een GM api transactie aan
     *
     * @param request de aanvraag
     * @param response het antwoord
     * @return de transactie
     */
    Transaction createGmApiTransaction(
            final HttpServletRequest request,
            final HttpServletResponse response);

    /**
     * Sla een BRP transactie op
     *
     * @param path de actie
     * @param receivedId de ontvangen correlatie id vanuit de BRP
     * @param originalTransaction de orginele transactie, aangemaakt in de BDV
     * of GM-API controlle bij binnenkomst
     */
    void saveBrpServiceTransaction(
            final String path,
            final String receivedId,
            final Transaction originalTransaction);

    /**
     * Sla een BRP adapter transactie op
     *
     * @param receivedId de ontvangen correlatie id vanuit de BRP
     * @param originalTransaction de orginele transactie, aangemaakt in de BDV
     * of GM-API controlle bij binnenkomst
     */
    void saveBrpAdapterTransaction(
            final String receivedId,
            final Transaction originalTransaction);

    /**
     * Sla een gezagsmodule transactie op
     *
     * @param type type persoonslijst waar gezagmodule voor aangeroepen is
     * @param receivedId de ontvangen correlatie id vanuit de BRP
     * @param route gezagsmodule route resultaat van bepalen gezag
     * @param soortGezag gezagsmodule soort gezag resultaat van bepalen gezag
     * @param veldenInOnderzoek velden die in onderzoek waren bij de gezag bepaling
     * @param originalTransaction de orginele transactie, aangemaakt in de BDV
     * of GM-API controlle bij binnenkomst
     */
    void saveGezagmoduleTransaction(
            final PersoonlijstType type,
            final String receivedId,
            final String route,
            final String soortGezag,
            final VeldenInOnderzoek veldenInOnderzoek,
            final Transaction originalTransaction);

    /**
     * Update een transactie
     * 
     * @param statusCode de http status code die terug wordt gegeven voor de aanvraag
     * @param transaction de transactie om te updaten
     * @param request de aanvraag
     */
    void updateTransaction(
        final HttpStatusCode statusCode,
            final Transaction transaction,
            final HttpServletRequest request);
}
