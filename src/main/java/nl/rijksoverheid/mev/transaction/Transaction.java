package nl.rijksoverheid.mev.transaction;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;

/**
 * Transaction
 *
 * Elke aanvraag op gezag, dat geen correlatiie-id heeft krijgt een correlatie
 * id. Vervolgens wordt elke aanvraag opgeslagen in de transactie database
 * bestaande uit:
 * <ul>
 * <li>Aanvraag op BDV / GM-API, wordt opgeslagen op moment van geven van
 * antwoord waarbij ook de statuscode wordt opgeslagen</li>
 * <li>Gezagmodule actie, gerelateerd aan type persoon</li>
 * <li>BRP aanvragen</li>
 * </ul>
 */
@Getter
public class Transaction implements Serializable {

    private long id;

    private String generatedId;

    @Setter
    private String receivedId;

    private Timestamp datetimeCreated;

    @Setter
    private Timestamp transactionDatetime;

    @Setter
    private String oin;

    @Setter
    private String soortGezag;

    @Setter
    private String gezagmoduleGezagSoort;

    @Setter
    private String gezagmodulePath;

    @Setter
    private String gezagmoduleRoute;

    @Setter
    private String applicationName;

    @Setter
    private String gezagmoduleVersie;

    @Setter
    private String dienst;

    @Setter
    private String dienstverlenerId;

    @Setter
    private String responseCode;

    /**
     * @param generatedId de correlatie id van de binnekomende aanvraag gelezen
     * uit de header of gegenereerd
     * @param receivedId null, of de correlatie id van het BRP antwoord
     * @param dienstverlenerId the dienstverlener id van de aanvraag
     * @param transactionDatetime de transactiie datum en tijd
     */
    public Transaction(final String generatedId, final String receivedId, final String dienstverlenerId, final Timestamp transactionDatetime) {
        this.generatedId = generatedId;
        this.receivedId = receivedId;
        this.oin = dienstverlenerId;
        this.dienstverlenerId = dienstverlenerId;
        this.transactionDatetime = transactionDatetime;
        this.datetimeCreated = Timestamp.from(Instant.now());
    }

    /**
     * Create transaction
     * 
     * @param generatedId de correlatie id van de binnekomende aanvraag gelezen
     * uit de header of gegenereerd
     * @param dienstverlenerId the dienstverlener id van de aanvraag
     * @param dienst de dienst id van de aanvraag
     * @param path de uitgevoerde actie
     * @param name de naam van het applicatiedeel dat de actie uitvoerd
     * @param transactionDatetime de transactiie datum en tijd
     */
    public Transaction(final String generatedId,
            final String dienstverlenerId,
            final String dienst,
            final String path,
            final String name,
            final Timestamp transactionDatetime) {
        this.generatedId = generatedId;
        this.oin = dienstverlenerId;
        this.dienstverlenerId = dienstverlenerId;
        this.dienst = dienst;
        this.gezagmodulePath = path;
        this.applicationName = name;
        this.transactionDatetime = transactionDatetime;
        this.datetimeCreated = Timestamp.from(Instant.now());
    }

    public Transaction() {
    }
}
