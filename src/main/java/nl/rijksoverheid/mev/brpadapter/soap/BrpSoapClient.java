package nl.rijksoverheid.mev.brpadapter.soap;

import jakarta.xml.bind.JAXB;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import lombok.extern.slf4j.Slf4j;
import nl.bprbzk.gba.lrdplus.version1.Antwoord;
import nl.bprbzk.gba.lrdplus.version1.VraagResponse;
import nl.rijksoverheid.mev.brpadapter.soap.persoonlijst.PersoonslijstFactory;
import nl.rijksoverheid.mev.common.util.BSNValidator;
import nl.rijksoverheid.mev.exception.BrpException;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.transaction.Transaction;
import nl.rijksoverheid.mev.transaction.TransactionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.xml.transform.dom.DOMSource;
import java.lang.reflect.InvocationTargetException;
import java.time.Clock;

/**
 * SOAP client voor communicatie met de BRP
 */
@Component
@Profile("real-brp")
@Slf4j
public class BrpSoapClient implements BrpClient {

    private static final int RETRY_AFTER_MILLIS = 500;
    private static final int MAX_RETRIES = 3;

    private final BRPCache brpCache;
    private final SOAPSender soapSender;
    private final String caBrpEndpoint;
    private final SOAPHelper soapHelper;
    private final BSNValidator bsnValidator;
    private final TransactionHandler transactionHandler;
    private final PersoonslijstFactory persoonslijstFactory;
    private final Clock clock;

    public BrpSoapClient(BRPCache brpCache, SOAPSender soapSender,
            @Value("${ca-brp-endpoint}") String caBrpEndpoint, SOAPHelper soapHelper, TransactionHandler transactionHandler, PersoonslijstFactory persoonslijstFactory, Clock clock) {
        this.brpCache = brpCache;
        this.soapSender = soapSender;
        this.caBrpEndpoint = caBrpEndpoint;
        this.soapHelper = soapHelper;
        this.transactionHandler = transactionHandler;
        this.persoonslijstFactory = persoonslijstFactory;
        this.clock = clock;
        this.bsnValidator = new BSNValidator();
    }

    @Override
    public Persoonslijst opvragenPersoonslijst(final String bsn, final Transaction transaction) throws BrpException {
        VraagResponse vraagResponse = null;
        try {
            if (bsnValidator.isValid(bsn)) {
                Persoonslijst persoonslijst;
                if (brpCache.get(bsn) == null) {
                    persoonslijst = generatePersoonslijst(bsn);
                } else {
                    var cachedBrpResponse = brpCache.get(bsn);
                    vraagResponse = cachedBrpResponse.vraagResponse();
                    persoonslijst = persoonslijstFactory.build(vraagResponse);
                    if (vraagResponse.getVraagReturn() != null) {
                        persoonslijst.setReceivedId(vraagResponse.getVraagReturn().getResultaat().getReferentie());
                    }
                }

                transactionHandler.saveBrpAdapterTransaction(persoonslijst.getReceivedId(), transaction);

                return persoonslijst;
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | SOAPException e) {
            log.error("Er trad een fout op tijdens het verwerken van het antwoord uit de BRP adapter", e);
            throw new BrpException(
                    "Er ging iets fout tijdens het vertalen van de reponse naar een persoonslijst");
        }

        return null;
    }

    private Persoonslijst generatePersoonslijst(final String bsn)
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, SOAPException {
        Persoonslijst persoonslijst = new Persoonslijst(clock);
        SOAPMessage soapMessage = soapHelper.createMessage(soapSender, bsn);

        VraagResponse vraagResponse = sendMessage(soapMessage, 0);
        if (vraagResponse != null) {
            Antwoord antwoord = vraagResponse.getVraagReturn();
            if (antwoord != null) {
                String letter = antwoord.getResultaat().getLetter();
                int code = antwoord.getResultaat().getCode();
                if ("A".equals(letter) && code == 0) {
                    persoonslijst = persoonslijstFactory.build(vraagResponse);

                    brpCache.put(bsn, new BRPCache.BrpResponse(vraagResponse));
                } else {
                    // Handle non A-Codes
                    if ("G".equals(letter)) {
                        log.info("Geen gegevens gevonden.");
                    } else {
                        String omschrijving = antwoord.getResultaat().getOmschrijving();

                        log.error("Onverwacht antwoord ontvangen van de BRP: {}{} {}", letter, code, omschrijving);

                        throw new BrpException();
                    }
                }

                persoonslijst.setReceivedId(vraagResponse.getVraagReturn().getResultaat().getReferentie());
            } else {
                log.error("Geen antwoord in vraag response ontvangen bij BRP aanvraag");
            }
        } else {
            log.error("Geen vraag response ontvangen bij BRP aanvraag");
        }

        return persoonslijst;
    }

    private VraagResponse sendMessage(final SOAPMessage soapMessage, int retried) throws BrpException {
        SOAPMessage soapResponse;
        try {
            soapResponse = soapSender.sendMessage(soapMessage, caBrpEndpoint);

            if (soapResponse != null) {
                log.info("response received after {} retries", retried);
                DOMSource source = new DOMSource(soapResponse.getSOAPBody().extractContentAsDocument());
                return JAXB.unmarshal(source, VraagResponse.class);
            }
        } catch (SOAPException ex) {
            log.error("Exception while communicating with BRP", ex);
        }

        log.info("Failed {} time, max retry is: {} will continue? {}", (retried + 1), MAX_RETRIES, (MAX_RETRIES > retried));

        int next = retried + 1;
        if (MAX_RETRIES > next) {
            try {
                Thread.sleep(RETRY_AFTER_MILLIS);

                return sendMessage(soapMessage, next);
            } catch (InterruptedException e) {
                log.error("Thread interrupted during SOAP request to BRP service after {} amount of retries", retried);
                Thread.currentThread().interrupt();
                throw new BrpException();
            }
        } else {
            log.error("Exhausted amount of retries during communicating with BRP, giving up", retried);
            throw new BrpException();
        }
    }
}
