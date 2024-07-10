package nl.rijksoverheid.mev.brpadapter.soap.persoonlijst;

import lombok.Getter;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Een veld in de persoonslijst die potentieel in onderzoek kan zijn
 */
@Getter
public abstract class PotentieelInOnderzoek implements PersoonslijstVeld {

    private final String categorie;
    private final List<String> veldenInOnderzoek;
    
    protected final Clock clock;
    protected final Map<String, String> values;

    protected final String aanduidingGegevensInOnderzoek;
    protected final String datumIngangOnderzoek;
    protected final String datumEindeOnderzoek;

    private static final int GROEP_POSITIE_GETAL = 4;
    private static final int CATEGORIE_POSITIE_GETAL = 2;

    protected static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");

    protected PotentieelInOnderzoek(final String categorie, final Map<String, String> values, final Clock clock) {
        this.categorie = categorie;
        this.values = values;
        this.clock = clock;

        this.aanduidingGegevensInOnderzoek = categorie + "8310";
        this.datumIngangOnderzoek = categorie + "8320";
        this.datumEindeOnderzoek = categorie + "8330";
        veldenInOnderzoek = new ArrayList<>();
    }

    /**
     * Haal de waarde van een veld op nadat gecontroleerd is of het veld in
     * onderzoek is
     *
     * @param key het veld om op te halen
     */
    protected void inOnderzoek(final String key) {
        String inOnderzoek = values.get(aanduidingGegevensInOnderzoek);
        if (inOnderzoek != null && !inOnderzoek.isEmpty()) {
            String formattedVeldName = getFormattedVeldName(inOnderzoek, key);
            
            if (inOnderzoek.equals(key) || inOnderzoek.equals(formattedVeldName)) {
                String datumEindeOnderzoekValue = values.get(datumEindeOnderzoek);
                if (datumEindeOnderzoekValue != null && !datumEindeOnderzoekValue.isEmpty()) {
                    int datumEindeOnderzoekInt = Integer.parseInt(datumEindeOnderzoekValue);
                    int datumVandaag = Integer.parseInt(LocalDate.now(clock).format(FORMATTER));

                    if (datumVandaag <= datumEindeOnderzoekInt) {
                        voegVeldInOnderzoekToeAlsDezeNogNietBestaat(key);
                    }
                } else {
                    voegVeldInOnderzoekToeAlsDezeNogNietBestaat(key);
                }
            }
        }
    }
    protected void voegVeldInOnderzoekToeAlsDezeNogNietBestaat(String key){
        if (veldenInOnderzoek.contains(key)) return;

        veldenInOnderzoek.add(key);
    }
    @Override
    public String get(final String key) {
        inOnderzoek(key);

        return values.get(key);
    }

    protected String getAanduidingGegevensInOnderzoek() {
        return get(aanduidingGegevensInOnderzoek);
    }

    protected String getDatumIngangOnderzoek() {
        return get(datumIngangOnderzoek);
    }

    protected String getDatumEindeOnderzoek() {
        return get(datumEindeOnderzoek);
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
}
