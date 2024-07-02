package nl.rijksoverheid.mev.brpadapter.soap;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.soap.*;
import lombok.extern.slf4j.Slf4j;
import nl.bprbzk.gba.lrdplus.version1.Vraag;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Implementatie voor versturen van SOAP berichten
 */
@Component
@Slf4j
public class SOAPSender {

    private final DocumentBuilder documentBuilder;
    private final SOAPConnectionFactory soapConnectionFactory;
    private final MessageFactory customMessageFactory;

    @Bean
    public static DocumentBuilder documentBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");

        return factory.newDocumentBuilder();
    }

    @Bean
    public static SOAPConnectionFactory soapConnectionFactory() throws SOAPException {
        return SOAPConnectionFactory.newInstance();
    }

    @Bean
    public static MessageFactory customMessageFactory() throws SOAPException {
        return MessageFactory.newInstance();
    }

    public SOAPSender(final DocumentBuilder documentBuilder, 
            final SOAPConnectionFactory soapConnectionFactory,
            final MessageFactory customMessageFactory) {
        this.documentBuilder = documentBuilder;
        this.soapConnectionFactory = soapConnectionFactory;
        this.customMessageFactory = customMessageFactory;
    }

    /**
     * Verstuur SOAP bericht
     * 
     * @param soapMsg het SOAP bericht
     * @param url de url
     * @return het SOAP antwoord
     */
    public SOAPMessage sendMessage(final SOAPMessage soapMsg, final String url) {
        log.info("sending soap message to: {}", url);

        try (SOAPConnection con = soapConnectionFactory.createConnection()) {
            log.info("SOAP connection created");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            
            return con.call(soapMsg, url);
        } catch (SOAPException e) {
            log.warn("Failed to send SOAP message to {}: {}", url, e.getMessage(), e);
            return null;
        }
    }

    /**
     * Marshall SOAP bericht
     * @param marshaller de marshaller
     * @param jaxbMessage het jaxb bericht
     * @return het SOAP bericht
     * @throws SOAPException 
     */
    public SOAPMessage marshalMessage(final Marshaller marshaller, final JAXBElement<Vraag> jaxbMessage)
            throws SOAPException {
        Document document = documentBuilder.newDocument();
        try {
            marshaller.marshal(jaxbMessage, document);
            SOAPMessage soapMessage = customMessageFactory.createMessage();
            soapMessage.getSOAPBody().addDocument(document);
            return soapMessage;
        } catch (SOAPException | JAXBException | NullPointerException | ArrayIndexOutOfBoundsException e) {
            log.error("Problem marshalling message", e);
            throw new SOAPException("Probleem bij het samenstellen van de vraag");
        }
    }
}
