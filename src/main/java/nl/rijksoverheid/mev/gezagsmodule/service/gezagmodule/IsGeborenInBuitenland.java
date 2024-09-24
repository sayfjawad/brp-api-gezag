package nl.rijksoverheid.mev.gezagsmodule.service.gezagmodule;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;

public class IsGeborenInBuitenland extends GezagVraag {

    private static final String GEBOORTELAND_CODE_NEDERLAND = "6030";
    private static final String V1_3A_JA = "Ja";
    private static final String V1_3A_NEE = "Nee";

    protected IsGeborenInBuitenland(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v1.3a";
    }

    /**
     * Sets the answer to {@code "Ja"} if <i>persoon is in buitenland geboren</i>, otherwise {@code "Nee"}.
     *
     * <p>
     * This is question 1_3A.
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();
        if (plPersoon == null) {
            gezagBepaling.addMissendeGegegevens("persoonlijst van bevraagde persoon");
            return;
        }

        String geboorteland = plPersoon.getPersoon().getGeboorteland();
        if (geboorteland == null) {
            gezagBepaling.addMissendeGegegevens("Geboorteland van bevraagde persoon");
            return;
        }

        var verblijfplaats = plPersoon.getVerblijfplaats();
        if (verblijfplaats == null) {
            gezagBepaling.addMissendeGegegevens("Verblijfplaats van bevraagde persoon");
            return;
        }
        String datumVestigingInNederland = verblijfplaats.getDatumVestigingInNederland();

        if (datumVestigingInNederland == null
            || datumVestigingInNederland.isEmpty()
            || geboorteland.equals(GEBOORTELAND_CODE_NEDERLAND)
        ) {
            answer = V1_3A_NEE;
        } else {
            answer = V1_3A_JA;
        }

        gezagBepaling.getArAntwoordenModel().setV0103A(answer);
    }
}
