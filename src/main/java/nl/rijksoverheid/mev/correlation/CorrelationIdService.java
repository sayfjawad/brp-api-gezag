package nl.rijksoverheid.mev.correlation;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * Service voor correlatie id functionaliteit
 */
@Service
public class CorrelationIdService {

    private final CorrelationIdGenerator correlationIdGenerator;
    private final Map<String, String> processIds;

    public CorrelationIdService(final CorrelationIdGenerator correlationIdGenerator) {
        this.correlationIdGenerator = correlationIdGenerator;
        processIds = new HashMap<>();
    }

    /**
     * Maak een correlatie id voor huidige aanvraag
     *
     * @param correlationId de correlatie id, wanneer null, wordt een correlatie
     * id gegenereerd
     * @param dienstverlenerId de dienstverlener id of null generating
     * correlation id
     */
    public void prepareForProcess(final String correlationId, final String dienstverlenerId) {
        String pid = ProcessHandle.current().pid() + String.valueOf(Thread.currentThread().getId());
        if (correlationId == null || correlationId.isEmpty()) {
            processIds.put(pid, correlationIdGenerator.generate(dienstverlenerId));
        } else {
            processIds.put(pid, correlationId);
        }
    }

    /**
     * @return de correlatie id voor huidige aanvraag
     */
    public String getCorrelationIdForProcess() {
        String pid = ProcessHandle.current().pid() + String.valueOf(Thread.currentThread().getId());

        return processIds.get(pid);
    }
}
