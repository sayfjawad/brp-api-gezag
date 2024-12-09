package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v1_2
 * "Ja" als persoon is ingezetene anders "Nee"
 */
public class IsPersoonIngezeteneInBRP extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(IsPersoonIngezeteneInBRP.class);

    private static final String V1_1_NEE = "Nee";
    private static final String V1_1_JA = "Ja";
    private static final String REGISTRATIE_NIET_INGEZETENE = "1999";

    protected IsPersoonIngezeteneInBRP(final GezagsBepaling gezagsBepaling) {
        super(gezagsBepaling);
        currentQuestion = "v1.1";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();
        if (plPersoon == null) {
            gezagsBepaling.addMissendeGegegevens("persoonlijst van bevraagde persoon");
            return;
        }

        var verblijfplaats = plPersoon.getVerblijfplaats();
        if (verblijfplaats == null) {
            gezagsBepaling.addMissendeGegegevens("verblijfplaats van bevraagde persoon");
            return;
        }

        var gemeenteVanInschrijving = verblijfplaats.getGemeenteVanInschrijving();
        if (gemeenteVanInschrijving == null || gemeenteVanInschrijving.isEmpty()) {
            gezagsBepaling.addMissendeGegegevens("gemeente van inschrijving van bevraagde persoon");
            return;
        }

        if (verblijfplaats.getGemeenteVanInschrijving().equals(REGISTRATIE_NIET_INGEZETENE)) {
            answer = V1_1_NEE;
        } else {
            answer = V1_1_JA;
        }

        logger.debug("""
            1.1 Staat persoon (minderjarige) als ingezetene in de BRP?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV0101(answer);
    }
}
