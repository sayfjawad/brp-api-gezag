package nl.rijksoverheid.mev.correlation;

import org.springframework.stereotype.Component;
import java.security.SecureRandom;
import org.springframework.beans.factory.annotation.Value;

/**
 * Generator for generating correlation id
 */
@Component
public class CorrelationIdGenerator {

    @Value("${correlation.component-id}")
    private String componentId;

    private static final String VERSION = "01";
    private static final char[] HEX_OPTIONS = "0123456789abcdef".toCharArray();
    private static final String NO_DIENSTVERLENER_ID = "00000000000000000000";
    private static final String CORRELATION_BETWEEN = "-";

    /**
     * Genereerd een correlatie id<br>
     * (versie)-(correlatie id)-(dienstverlener id)-(id van dit component) Example:
     * 00-b1d2a5ec5ae1edfe18cd0669c26c5ff6-00000003301977740000-00
     *
     * @param dienstverlenerId de dienstverlener id of null
     * @return de correlatie id
     */
    public String generate(final String dienstverlenerId) {
        StringBuilder sb = new StringBuilder();
        sb.append(VERSION);
        sb.append(CORRELATION_BETWEEN);
        sb.append(bytesToHex(new byte[16]));
        sb.append(CORRELATION_BETWEEN);
        sb.append((dienstverlenerId != null && !dienstverlenerId.isEmpty() ? dienstverlenerId : NO_DIENSTVERLENER_ID));
        sb.append(CORRELATION_BETWEEN);
        sb.append(componentId);

        return sb.toString();
    }

    private static String bytesToHex(byte[] bytes) {
        new SecureRandom().nextBytes(bytes);
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_OPTIONS[v >>> 4];
            hexChars[i * 2 + 1] = HEX_OPTIONS[v & 0x0F];
        }

        return new String(hexChars);
    }
}
