package nl.rijksoverheid.mev.brpadapter;

import jakarta.annotation.PostConstruct;
import nl.rijksoverheid.mev.exception.SSLContextCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

/**
 * Configuratie voor het inregelen van de certificaten om, via het CA, met de
 * BRP te communiceren. <br>
 * - truststore -> met certificaat dat vertrouwd wordt voor TLS verbinding<br>
 * - keystore -> met certificaat dat gebruikt moet worden voor communicatie naar
 * CA
 */
@Configuration
@Profile("real-brp")
public class SSLContext {

    @Value("${services.certificates.trust-store-password}")
    private String trustStorePassword;
    @Value("${services.certificates.key-store-password}")
    private String keystorePassword;
    @Value("${services.certificates.key-password}")
    private String keyPassword;
    @Value("${services.certificates.trust-store}")
    private String truststoreLocation;
    @Value("${services.certificates.key-store}")
    private String keystoreLocation;

    private static final String SSL_CONTEXT_TYPE = "TLS";

    @PostConstruct
    private void setDefaultSslContext() {
        try {
            KeyManagerFactory keyManagerFactory = configureKeyManagerFactory(keyPassword);
            TrustManagerFactory trustManagerFactory = configureTrustManagerFactory();
            javax.net.ssl.SSLContext ctx = javax.net.ssl.SSLContext.getInstance(SSL_CONTEXT_TYPE);
            ctx.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);
            javax.net.ssl.SSLContext.setDefault(ctx);
        } catch (KeyStoreException | NoSuchAlgorithmException | KeyManagementException | UnrecoverableKeyException |
                 IOException e) {
            throw new SSLContextCreationException(e);
        }
    }

    private TrustManagerFactory configureTrustManagerFactory() throws NoSuchAlgorithmException, KeyStoreException, IOException {
        TrustManagerFactory trustManagerFactory;
        try (InputStream is = new FileInputStream(truststoreLocation)) {
            KeyStore trustKeyStore = createKeyStore(is, trustStorePassword);
            trustManagerFactory
                = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustKeyStore);
        }
        return trustManagerFactory;
    }

    private KeyManagerFactory configureKeyManagerFactory(String password) throws NoSuchAlgorithmException,
        KeyStoreException, UnrecoverableKeyException, IOException {
        KeyManagerFactory keyManagerFactory;
        try (InputStream is = new FileInputStream(keystoreLocation)) {
            KeyStore keyStore = createKeyStore(is, keystorePassword);
            keyManagerFactory
                = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, password.toCharArray());
        }
        return keyManagerFactory;
    }

    private static KeyStore createKeyStore(InputStream keyStoreInputStream, String password) throws IOException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(keyStoreInputStream, password.toCharArray());
            return keyStore;
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException e) {
            throw new SSLContextCreationException(e);
        }
    }
}
