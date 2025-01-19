package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.functional;

import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IsPersoonIngezeteneInBRPFunction implements GezagVraagFunction {

    private static final Logger logger = LoggerFactory.getLogger(IsPersoonIngezeteneInBRPFunction.class);
    private static final String V1_1_ID = "v1.1";
    private static final String V1_1_NEE = "Nee";
    private static final String V1_1_JA = "Ja";
    private static final String REGISTRATIE_NIET_INGEZETENE = "1999";

    @Override
    public String getQuestionId() {
        return V1_1_ID;
    }

    @Override
    public GezagVraagResult perform(GezagsBepaling gezagsBepaling) {

        var plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            gezagsBepaling.addMissendeGegegevens("persoonlijst van bevraagde persoon");
            return new GezagVraagResult(getQuestionId(), null);
        }

        var verblijfplaats = plPersoon.getVerblijfplaats();
        if (verblijfplaats == null) {
            gezagsBepaling.addMissendeGegegevens("verblijfplaats van bevraagde persoon");
            return new GezagVraagResult(getQuestionId(), null);
        }

        var gemeenteVanInschrijving = verblijfplaats.getGemeenteVanInschrijving();
        if (gemeenteVanInschrijving == null || gemeenteVanInschrijving.isEmpty()) {
            gezagsBepaling.addMissendeGegegevens("gemeente van inschrijving van bevraagde persoon");
            return new GezagVraagResult(getQuestionId(), null);
        }

        // Alles aanwezig, bepaal antwoord:
        String answer = REGISTRATIE_NIET_INGEZETENE.equals(gemeenteVanInschrijving)
                ? V1_1_NEE
                : V1_1_JA;

        logger.debug("1.1 Staat persoon (minderjarige) als ingezetene in de BRP? => {}", answer);

        // Zet in ARAntwoordenModel:
        gezagsBepaling.getArAntwoordenModel().setV0101(answer);

        // Geef ook terug als functioneel resultaat
        return new GezagVraagResult(getQuestionId(), answer);
    }
}