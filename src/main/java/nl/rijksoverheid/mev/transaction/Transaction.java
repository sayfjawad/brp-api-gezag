package nl.rijksoverheid.mev.transaction;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

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
@Entity
@Getter
public class Transaction implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "generated_id")
    private String generatedId;

    @Setter
    @Column(name = "received_id")
    private String receivedId;

    @Column(name = "datetime_created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp datetimeCreated;

    @Setter
    @Column(name = "transaction_datetime", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp transactionDatetime;

    @Setter
    @Column(name = "oin")
    private String oin;

    @Setter
    @Column(name = "soort_gezag")
    private String soortGezag;

    @Setter
    @Column(name = "gezagmodule_gezag_soort")
    private String gezagmoduleGezagSoort;

    @Setter
    @Column(name = "gezagmodule_path")
    private String gezagmodulePath;

    @Setter
    @Column(name = "gezagmodule_route")
    private String gezagmoduleRoute;

    @Setter
    @Column(name = "applicatie_naam")
    private String applicationName;

    @Setter
    @Column(name = "gezagmodule_versie")
    private String gezagmoduleVersie;

    @Setter
    @Column(name = "dienst_id")
    private String dienst;

    @Setter
    @Column(name = "dienstverlener_id")
    private String dienstverlenerId;

    @Setter
    @Column(name = "response_code")
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
