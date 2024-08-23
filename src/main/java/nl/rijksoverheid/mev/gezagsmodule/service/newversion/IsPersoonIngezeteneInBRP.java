package nl.rijksoverheid.mev.gezagsmodule.service.newversion;


import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.Verblijfplaats;

public class IsPersoonIngezeteneInBRP extends GezagVraag {

    private static final String V1_1_NEE = "Nee";
    private static final String V1_1_JA = "Ja";
    private static final String REGISTRATIE_NIET_INGEZETENEN = "1999";

    protected IsPersoonIngezeteneInBRP(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v1.1";
    }

    /**
     * v1_1
     *
     * @return "Ja" als isIngezeteneInBRP anders "Nee"
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        if (plPersoon != null) {
            Verblijfplaats verblijfplaats = plPersoon.getVerblijfplaats();
            if (verblijfplaats == null) {
                gezagBepaling.addMissendeGegegevens("verblijfplaats van bevraagde persoon");
            } else if (verblijfplaats.getGemeenteVanInschrijving() == null) {
                gezagBepaling.addMissendeGegegevens("gemeente van inschrijving van bevraagde persoon");
            }

            if (verblijfplaats != null && !verblijfplaats.getGemeenteVanInschrijving().equals(REGISTRATIE_NIET_INGEZETENEN)) {
                answer = V1_1_JA;
            } else {
                answer = V1_1_NEE;
            }

            if (plPersoon != null && plPersoon.isIngezeteneInBRP()) {
                answer = V1_1_JA;
            } else if (plPersoon != null && !plPersoon.isIngezeteneInBRP()) {
                answer = V1_1_NEE;
            }
        } else {
            gezagBepaling.addMissendeGegegevens("persoonlijst van bevraagde persoon");
        }


        gezagBepaling.getArAntwoordenModel().setV0101(answer);
    }
}
