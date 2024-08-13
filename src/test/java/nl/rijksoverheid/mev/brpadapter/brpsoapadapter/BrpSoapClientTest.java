package nl.rijksoverheid.mev.brpadapter.brpsoapadapter;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import nl.rijksoverheid.mev.brpadapter.soap.BRPCache;
import nl.rijksoverheid.mev.brpadapter.soap.BrpSoapClient;
import nl.rijksoverheid.mev.brpadapter.soap.SOAPHelper;
import nl.rijksoverheid.mev.brpadapter.soap.SOAPSender;
import nl.rijksoverheid.mev.exception.BrpException;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.transaction.Transaction;
import nl.rijksoverheid.mev.transaction.TransactionHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Tests for brp soap client
 */
@ExtendWith(MockitoExtension.class)
class BrpSoapClientTest {

    @Mock
    private BRPCache brpCache;

    @Mock
    private SOAPHelper soapHelper;

    @Mock
    private SOAPSender sender;

    @Mock
    private SOAPMessage message;

    @Mock
    private SOAPMessage response;

    @Mock
    private SOAPBody soapBody;

    @Mock
    private TransactionHandler transactionHandler;

    @Mock
    private Transaction transaction;

    @InjectMocks
    private BrpSoapClient classUnderTest;

    private static final String BSN = "999999564";

    @Test
    void testRetrySuccessAfterOneFailure() throws GezagException, SOAPException {
        when(brpCache.get(BSN)).thenReturn(null);
        when(soapHelper.createMessage(sender, BSN)).thenReturn(message);
        when(sender.sendMessage(message, null))
                .thenReturn(null).thenReturn(response);
        when(response.getSOAPBody()).thenReturn(soapBody);
        when(soapBody.extractContentAsDocument()).thenReturn(okResponseBody(BSN));

        classUnderTest.opvragenPersoonslijst(BSN, transaction);

        verify(sender, times(2)).sendMessage(message, null);
    }

    @Test
    void testRetrySuccessAfterTwoFailures() throws GezagException, SOAPException {
        when(brpCache.get(BSN)).thenReturn(null);
        when(soapHelper.createMessage(sender, BSN)).thenReturn(message);
        when(sender.sendMessage(message, null))
                .thenReturn(null).thenReturn(null).thenReturn(response);
        when(response.getSOAPBody()).thenReturn(soapBody);
        when(soapBody.extractContentAsDocument()).thenReturn(okResponseBody(BSN));

        classUnderTest.opvragenPersoonslijst(BSN, transaction);

        verify(sender, times(3)).sendMessage(message, null);
    }

    @Test
    void testRetryExhausted() throws BrpException, SOAPException {
        when(brpCache.get(BSN)).thenReturn(null);
        when(soapHelper.createMessage(sender, BSN)).thenReturn(message);
        when(sender.sendMessage(message, null)).thenReturn(null);
        BrpException exception = assertThrows(BrpException.class, () -> {
            classUnderTest.opvragenPersoonslijst(BSN, transaction);
        });

        verify(sender, times(3)).sendMessage(message, null);
        assertNotNull(exception);
    }

    private Document okResponseBody(final String bsn) {
        return convertStringToDocument("<?xml version='1.0' encoding='UTF-8'?><soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><vraagResponse xmlns:ns2=\"http://www.bprbzk.nl/GBA/LO3/version1.1\" xmlns=\"http://www.bprbzk.nl/GBA/LRDPlus/version1.1\"><vraagReturn><persoonslijsten><ns2:item><ns2:categoriestapels><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>1</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>120</ns2:nummer><ns2:waarde>"
                + bsn + "</ns2:waarde></ns2:item><ns2:item><ns2:nummer>210</ns2:nummer><ns2:waarde>Govert</ns2:waarde></ns2:item><ns2:item><ns2:nummer>240</ns2:nummer><ns2:waarde>Geus</ns2:waarde></ns2:item><ns2:item><ns2:nummer>310</ns2:nummer><ns2:waarde>19841002</ns2:waarde></ns2:item><ns2:item><ns2:nummer>330</ns2:nummer><ns2:waarde>6030</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8230</ns2:nummer><ns2:waarde>PKA</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8310</ns2:nummer><ns2:waarde>010300</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8320</ns2:nummer><ns2:waarde>20220825</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>2</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>210</ns2:nummer><ns2:waarde>Geurtje</ns2:waarde></ns2:item><ns2:item><ns2:nummer>240</ns2:nummer><ns2:waarde>Plas</ns2:waarde></ns2:item><ns2:item><ns2:nummer>6210</ns2:nummer><ns2:waarde>00000000</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8310</ns2:nummer><ns2:waarde>020000</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8320</ns2:nummer><ns2:waarde>20220825</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>3</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>210</ns2:nummer><ns2:waarde>Gerrit</ns2:waarde></ns2:item><ns2:item><ns2:nummer>240</ns2:nummer><ns2:waarde>Geus</ns2:waarde></ns2:item><ns2:item><ns2:nummer>6210</ns2:nummer><ns2:waarde>00000000</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>5</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>120</ns2:nummer><ns2:waarde>999970148</ns2:waarde></ns2:item><ns2:item><ns2:nummer>610</ns2:nummer><ns2:waarde>20051203</ns2:waarde></ns2:item><ns2:item><ns2:nummer>620</ns2:nummer><ns2:waarde>0518</ns2:waarde></ns2:item><ns2:item><ns2:nummer>630</ns2:nummer><ns2:waarde>6030</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8310</ns2:nummer><ns2:waarde>050610</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8320</ns2:nummer><ns2:waarde>20220825</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>8</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>910</ns2:nummer><ns2:waarde>0518</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8310</ns2:nummer><ns2:waarde>089999</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8320</ns2:nummer><ns2:waarde>20220825</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>9</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>120</ns2:nummer><ns2:waarde>999970422</ns2:waarde></ns2:item><ns2:item><ns2:nummer>210</ns2:nummer><ns2:waarde>Gloria</ns2:waarde></ns2:item><ns2:item><ns2:nummer>240</ns2:nummer><ns2:waarde>Geus</ns2:waarde></ns2:item><ns2:item><ns2:nummer>310</ns2:nummer><ns2:waarde>20211012</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8310</ns2:nummer><ns2:waarde>090000</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8320</ns2:nummer><ns2:waarde>20220825</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>9</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>120</ns2:nummer><ns2:waarde>999970331</ns2:waarde></ns2:item><ns2:item><ns2:nummer>210</ns2:nummer><ns2:waarde>Gijs</ns2:waarde></ns2:item><ns2:item><ns2:nummer>240</ns2:nummer><ns2:waarde>Geus</ns2:waarde></ns2:item><ns2:item><ns2:nummer>310</ns2:nummer><ns2:waarde>20191205</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8310</ns2:nummer><ns2:waarde>090300</ns2:waarde></ns2:item><ns2:item><ns2:nummer>8320</ns2:nummer><ns2:waarde>20220825</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item><ns2:item><ns2:categorievoorkomens><ns2:item><ns2:categorienummer>9</ns2:categorienummer><ns2:elementen><ns2:item><ns2:nummer>120</ns2:nummer><ns2:waarde>999970288</ns2:waarde></ns2:item><ns2:item><ns2:nummer>210</ns2:nummer><ns2:waarde>Gerdien</ns2:waarde></ns2:item><ns2:item><ns2:nummer>240</ns2:nummer><ns2:waarde>Geus</ns2:waarde></ns2:item><ns2:item><ns2:nummer>310</ns2:nummer><ns2:waarde>20160607</ns2:waarde></ns2:item></ns2:elementen></ns2:item></ns2:categorievoorkomens></ns2:item></ns2:categoriestapels></ns2:item></persoonslijsten><resultaat><code>0</code><letter>A</letter><omschrijving>Aantal: 1.</omschrijving><referentie>ef97a8cf-c38d-4e06-b6ce-70f26078bd9e</referentie></resultaat></vraagReturn></vraagResponse></soap:Body></soap:Envelope>");
    }

    private Document convertStringToDocument(final String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
            return doc;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
