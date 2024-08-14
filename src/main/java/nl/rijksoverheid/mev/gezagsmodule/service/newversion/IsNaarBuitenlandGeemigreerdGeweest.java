package nl.rijksoverheid.mev.gezagsmodule.service.newversion;

import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.Verblijfplaats;

public class IsNaarBuitenlandGeemigreerdGeweest extends GezagVraag {

    private static final String V1_3_JA = "Ja";
    private static final String V1_3_NEE = "Nee";

    protected IsNaarBuitenlandGeemigreerdGeweest(final GezagBepaling gezagBepaling) {
        super(gezagBepaling);
        currentQuestion = "v1.3";
    }

    /**
     * v1_3
     *
     * @return "Ja" als is naar buitenland geëmigreerd geweest, anders "Nee"
     */
    @Override
    public void perform() {
        Persoonslijst plPersoon = gezagBepaling.getPlPersoon();

        String geboorteland = plPersoon.getPersoon().getGeboorteland();
        Verblijfplaats verblijfplaats = plPersoon.getVerblijfplaats();
        if(geboorteland == null) {
            gezagBepaling.addMissendeGegegevens("Geboorteland van bevraagde persoon");
        } else if(verblijfplaats == null) {
            gezagBepaling.addMissendeGegegevens("Verblijfplaats van bevraagde persoon");
        } else if(geboorteland.equals("6030")
            && verblijfplaats.getDatumVestigingInNederland() != null
            && !verblijfplaats.getDatumVestigingInNederland().isEmpty()){
            answer = V1_3_JA;
        } else {
            answer = V1_3_NEE;
        }
        
        gezagBepaling.getArAntwoordenModel().setV0103(answer);
    }
}
