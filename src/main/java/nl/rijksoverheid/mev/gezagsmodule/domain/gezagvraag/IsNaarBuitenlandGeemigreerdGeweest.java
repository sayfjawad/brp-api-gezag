package nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.Verblijfplaats;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * v1_3
 * "Ja" als is naar buitenland geëmigreerd geweest, anders "Nee"
 */
public class IsNaarBuitenlandGeemigreerdGeweest extends GezagVraag {

    private static final Logger logger = LoggerFactory.getLogger(IsNaarBuitenlandGeemigreerdGeweest.class);

    private static final String V1_3_JA = "Ja";
    private static final String V1_3_NEE = "Nee";

    protected IsNaarBuitenlandGeemigreerdGeweest(final GezagsBepaling gezagsBepaling) {
        super(gezagsBepaling);
        currentQuestion = "v1.3";
    }

    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagsBepaling.getPlPersoon();

        String geboorteland = plPersoon.getPersoon().getGeboorteland();
        Verblijfplaats verblijfplaats = plPersoon.getVerblijfplaats();
        if (geboorteland == null || geboorteland.isEmpty()) {
            gezagsBepaling.addMissendeGegegevens("Geboorteland van bevraagde persoon");
        } else if (verblijfplaats == null) {
            gezagsBepaling.addMissendeGegegevens("Verblijfplaats van bevraagde persoon");
        } else if (geboorteland.equals("6030")
            && verblijfplaats.getDatumVestigingInNederland() != null
            && !verblijfplaats.getDatumVestigingInNederland().isEmpty()) {
            answer = V1_3_JA;
        } else {
            answer = V1_3_NEE;
        }

        logger.debug("""
            1.3 Is minderjarige naar het buitenland geëmigreerd geweest?
            {}""", answer);
        gezagsBepaling.getArAntwoordenModel().setV0103(answer);
    }
}
