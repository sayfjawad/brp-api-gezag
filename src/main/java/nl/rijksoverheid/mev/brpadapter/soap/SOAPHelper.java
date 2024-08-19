package nl.rijksoverheid.mev.brpadapter.soap;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import lombok.extern.slf4j.Slf4j;
import nl.bprbzk.gba.lrdplus.version1.*;
import nl.rijksoverheid.mev.exception.BrpException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.namespace.QName;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

/**
 * Helper class voor het aanmaken van BRP SOAP berichten
 */
@Slf4j
@Component
public class SOAPHelper {

    private final JAXBContext jaxbContext;
    private final String caAuthorization;
    private final String caXOriginOIN;
    private final String brpUsername;
    private final String brpPassword;
    private static final QName Q_NAME = new QName("http://www.bprbzk.nl/GBA/LRDPlus/version1.1", "vraag");
    private static final List<Integer> ALL_CODES = List.of(
        // Persoon
        10120, 10210, 10230, 10240, 10310, 10330, 18120, 18230,
        // Persoon Geschiedenis
        518120, 518230,
        // Ouder 1
        20120, 20210, 20240, 26210, 28120,
        // Adoptieouder 1 (Ouder 1 Geschiedenis)
        520210, 520240, 526210, 528120,
        // Ouder 2
        30120, 30210, 30240, 36210, 38120,
        // Adoptieouder 2 (Ouder 2 Geschiedenis)
        530210, 530240, 536210, 538120,
        // HuwelijkOfPartnerschap
        50120, 50610, 50620, 50630, 50710, 50720, 50730, 50740,
        // HuwelijkOfPartnerschap Geschiedenis
        550610, 550620, 550630, 550710, 550720, 550730, 550740,
        // Verblijfplaats
        80910, 81410, 81420,
        // Gezagverhouding
        113210, 113310, 118510,
        // Kind
        90120, 90210, 90240, 90310
    );
    private static final int BSN_BRP_RUBRIEKNUMMER = 10120;

    public SOAPHelper(
            final @Value("${ca-credentials.Authorization}") String caAuthorization,
            final @Value("${ca-credentials.X-Origin-OIN}") String caXOriginOIN,
            final @Value("${brp-credentials.username}") String brpUsername,
            final @Value("${brp-credentials.password}") String brpPassword) throws JAXBException {
        this.caAuthorization = caAuthorization;
        this.caXOriginOIN = caXOriginOIN;
        this.brpUsername = brpUsername;
        this.brpPassword = brpPassword;
        this.jaxbContext = JAXBContext.newInstance(Vraag.class);
    }

    /**
     * Maakt een nieuw SOAP bericht voor de SOAPSender en bsn
     *
     * @param soapSender de soap sender voor het aanmaken van het bericht
     * @param bsn de bsn van de person to request persoonlijst from brp for
     * @return het SOAP bericht
     * @throws SOAPException wanneer een foutmelding optreed bij het
     * samenstellen van het bericht
     */
    public SOAPMessage createMessage(final SOAPSender soapSender, final String bsn) throws SOAPException {
        SOAPMessage soapMessage = soapSender.marshalMessage(getMarshaller(), createVraagJaxBObject(bsn));
        soapMessage.getMimeHeaders().addHeader("Authorization", caAuthorization);
        soapMessage.getMimeHeaders().addHeader("X-Origin-OIN", caXOriginOIN);
        soapMessage.getMimeHeaders().addHeader("X-Brp-Credentials", "Basic "
                + Base64.getEncoder().encodeToString((brpUsername + ":" + brpPassword)
                        .getBytes(StandardCharsets.UTF_8)));
        soapMessage.getMimeHeaders().addHeader("Content-Type", "text/xml");

        return soapMessage;
    }

    private Marshaller getMarshaller() throws BrpException {
        try {
            return jaxbContext.createMarshaller();
        } catch (JAXBException e) {
            log.error("Unable to create marshaller from JAXBContext", e);
            throw new BrpException("Unable to create marshaller from JAXBContext");
        }
    }

    private JAXBElement<Vraag> createVraagJaxBObject(String bsn) {
        Vraag vraag = new Vraag();
        Vraag2 vraag2 = new Vraag2();
        ArrayOfXsdInt maskers = new ArrayOfXsdInt();
        maskers.getItem().addAll(ALL_CODES);
        vraag2.setMasker(maskers);
        ArrayOfZoekparameter parameters = new ArrayOfZoekparameter();
        Zoekparameter zoekparameter = new Zoekparameter();
        zoekparameter.setRubrieknummer(BSN_BRP_RUBRIEKNUMMER);
        zoekparameter.setZoekwaarde(bsn);
        parameters.getItem().add(zoekparameter);
        vraag2.setParameters(parameters);
        vraag.setIn0(vraag2);
        return new JAXBElement<>(Q_NAME, Vraag.class, vraag);
    }
}
