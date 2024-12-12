package nl.rijksoverheid.mev.gezagsmodule.domain;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * Een veld in de persoonslijst die potentieel in onderzoek kan zijn
 */
@Getter
public abstract class PotentieelInOnderzoek {

    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    private static final Logger logger = LoggerFactory.getLogger(PotentieelInOnderzoek.class);

    private static final int GROEP_POSITIE_GETAL = 4;
    private static final int CATEGORIE_POSITIE_GETAL = 2;

    private final Set<String> veldenInOnderzoek;

    protected String aanduidingGegevensInOnderzoek;
    protected String datumEindeOnderzoek;

    protected PotentieelInOnderzoek() {
        veldenInOnderzoek = new HashSet<>();
    }

    /**
     * Haal de waarde van een veld op nadat gecontroleerd is of het veld in
     * onderzoek is
     *
     * @param fieldName   het veld om op te halen
     * @param calledClass het aangeroepen object
     */
    protected <T> void registerIfInOnderzoek(final String fieldName, final Class<T> calledClass) {
        if (aanduidingGegevensInOnderzoek == null) return;
        if (aanduidingGegevensInOnderzoek.isEmpty()) return;

        try {
            VeldNummer annotation = calledClass.getDeclaredField(fieldName).getAnnotation(VeldNummer.class);

            String key = annotation.number();
            String formattedVeldName = getFormattedVeldName(aanduidingGegevensInOnderzoek, key);

            if (aanduidingGegevensInOnderzoek.equals(key) || aanduidingGegevensInOnderzoek.equals(formattedVeldName)) {
                String datumEindeOnderzoekValue = datumEindeOnderzoek;
                String veldInOnderzoek = formattedVeldName.endsWith("0000") ? getCategorieName(calledClass) : annotation.name();

                if (datumEindeOnderzoekValue != null && !datumEindeOnderzoekValue.isEmpty()) {
                    int datumEindeOnderzoekInt = Integer.parseInt(datumEindeOnderzoekValue);
                    int datumVandaag = Integer.parseInt(LocalDate.now().format(FORMATTER));

                    if (datumVandaag <= datumEindeOnderzoekInt) {
                        veldenInOnderzoek.add(veldInOnderzoek);
                    }
                } else {
                    veldenInOnderzoek.add(veldInOnderzoek);
                }
            }
        } catch (NoSuchFieldException e) {
            logger.error("Should never happen. Probably a programming error.", e);
        }
    }

    private String getFormattedVeldName(final String inOnderzoek, final String key) {
        String formattedVeldName;
        if (inOnderzoek.startsWith("0000", CATEGORIE_POSITIE_GETAL)) {
            formattedVeldName = key.substring(0, CATEGORIE_POSITIE_GETAL) + "0000";
        } else if (inOnderzoek.startsWith("00", GROEP_POSITIE_GETAL)) {
            formattedVeldName = key.substring(0, GROEP_POSITIE_GETAL) + "00";
        } else {
            formattedVeldName = key;
        }

        return formattedVeldName;
    }

    private <T>  String getCategorieName(final Class<T> calledClass) {
        Categorie annotation = calledClass.getAnnotation(Categorie.class);
        if (annotation != null) {
            return annotation.name();
        } else {
            return null;
        }
    }
}
