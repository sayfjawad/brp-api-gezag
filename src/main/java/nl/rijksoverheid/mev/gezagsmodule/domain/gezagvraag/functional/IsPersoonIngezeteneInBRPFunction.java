package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IsPersoonIngezeteneInBRPFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(
            IsPersoonIngezeteneInBRPFunction.class);
    private static final String V1_1_ID = "v1.1";
    private static final String V1_1_NEE = "Nee";
    private static final String V1_1_JA = "Ja";
    private static final String REGISTRATIE_NIET_INGEZETENE = "1999";

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {

        var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            // je kunt hier óf exceptions gooien, óf een speciaal antwoord meegeven,
            // óf de 'missendeGegevens' direct in de GezagsBepaling bijhouden.
            // Voor het voorbeeld tonen we even: throw nieuwe exception
            throw new IllegalStateException("Persoonlijst van bevraagde persoon ontbreekt.");
        }
        var verblijfplaats = plPersoon.getVerblijfplaats();
        if (verblijfplaats == null) {
            throw new IllegalStateException("Verblijfplaats van bevraagde persoon ontbreekt.");
        }
        var gemeenteVanInschrijving = verblijfplaats.getGemeenteVanInschrijving();
        if (gemeenteVanInschrijving == null || gemeenteVanInschrijving.isEmpty()) {
            throw new IllegalStateException(
                    "Gemeente van inschrijving van bevraagde persoon ontbreekt.");
        }
        String answer = V1_1_JA;
        if (gemeenteVanInschrijving.equals(REGISTRATIE_NIET_INGEZETENE)) {
            answer = V1_1_NEE;
        }
        logger.debug("""
                1.1 Staat persoon (minderjarige) als ingezetene in de BRP? => {}
                """, answer);
        // Zet het antwoord in je eigen model
        gezagsBepaling.getArAntwoordenModel().setV0101(answer);
        // En geef het *ook* als functioneel resultaat terug!
        return new GezagVraagResult(V1_1_ID, answer);
    }
}