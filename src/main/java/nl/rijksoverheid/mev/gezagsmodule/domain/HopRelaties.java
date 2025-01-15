package nl.rijksoverheid.mev.gezagsmodule.domain;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * HopRelaties is nodig om op basis van de gebeurtenissen relaties te
 * reconstrueren
 */
@NoArgsConstructor
@Getter
@Setter
public class HopRelaties {

    public static final String START_RELATIE = "Start relatie";
    public static final String ONTBINDING_RELATIE = "Ontbinding relatie";
    private List<Gebeurtenis> gebeurtenissen = new ArrayList<>();
    private List<HopRelatie> relaties = new ArrayList<>();

    public void voegRelatieToe(final HopRelatie relatie) {
        relaties.add(relatie);
    }

    /**
     * Voeg HOP relatie gebeurtenis toe
     *
     * @param type    type gebeurtenis
     * @param partner de partner
     * @param datum   de datum
     * @param reden   de reden voor de gebeurtenis
     */
    public void voegGebeurtenisToe(final String type, final String partner, final String datum, final String reden) {
        Gebeurtenis gebeurtenis = new Gebeurtenis();
        gebeurtenis.setType(type);
        gebeurtenis.setDatum(Integer.parseInt(datum));
        gebeurtenis.setReden(reden);
        gebeurtenis.setPartner(partner);
        gebeurtenissen.add(gebeurtenis);
    }

    /**
     * Sorteer de gebeurtenissen van meest actuele naar minst actuele datum
     */
    public void sorteerGebeurtenissenOpDatum() {
        gebeurtenissen.sort(Comparator.comparingInt(Gebeurtenis::getDatum).reversed());
    }

    /**
     * Reconstrueer relaties op basis van gebeurtenissen in huwelijkofpartnerschap en geschiedenishuwelijkofpartnerschap
     * Sorteert eerst de gebeurtenissen op datum van meest recent naar minst recent.
     * Bouwt vervolgens de relaties op basis van de volgende aannames:
     * - een beëindiging (ontbinding) van een huwelijk of partnerschap is altijd recenter dan het starten van.
     * - relaties worden altijd serieel aangegaan, er is geen sprake van parallelle relaties (bigamie)
     * - beëindiging zonder bsn partner hoeft niet verwerkt te worden, dit is meestal een record dat ontstaan is door een
     * andere administratieve gebeurtenis (zie Lg01_043 voor een voorbeeld op Lg01_041).
     * - de start van een relatie komt in de gesorteerde gebeurtenissenlijst na de beëindiging,
     * als de datum kleiner is dan de eerst gevonden relatie zonder startdatum dan is de datum de start van de relatie.
     */
    public void checkRelaties() {
        sorteerGebeurtenissenOpDatum();
        HopRelatie hopRelatie = new HopRelatie();
        for (Gebeurtenis gebeurtenis : gebeurtenissen) {
            if (gebeurtenis.getType().equals(ONTBINDING_RELATIE)) {
                if (hopRelatie.getStartDatum() != null) {
                    voegRelatieToe(hopRelatie);
                    hopRelatie = new HopRelatie();
                }
                if (gebeurtenis.getPartner() != null) {
                    hopRelatie.setPartner(gebeurtenis.getPartner());
                    hopRelatie.setEindDatum(gebeurtenis.getDatum());
                    hopRelatie.setReden(gebeurtenis.getReden());
                }
                continue;
            }
            if (gebeurtenis.getType().equals(START_RELATIE)) {
                if (gebeurtenis.getPartner() != null) {
                    hopRelatie.setPartner(gebeurtenis.getPartner());
                }
                hopRelatie.setStartDatum(gebeurtenis.getDatum());
            }
        }
        if (hopRelatie.getStartDatum() != null) {
            voegRelatieToe(hopRelatie);
        }
    }

    /*
    Zoek de meest actuele relatie die voldoet aan de volgende voorwaarden:
    De geboortedatum van het kind moet:
     - gelijk of groter zijn dan de startdatum van de relatie
    De relatie moet:
     - een einddatum > dan de geboortedatum van het kind hebben of
     - geen einddatum hebben
     */
    public HopRelatie geborenInRelatie(Integer geboorteDatum) {
        for (HopRelatie relatie : relaties) {
            if ((relatie.getStartDatum() != null) && (relatie.getStartDatum() <= geboorteDatum)) {
                Integer eindDatum = relatie.getEindDatum();
                if (eindDatum == null) {
                    return relatie;
                }
                if (eindDatum > geboorteDatum) {
                    return relatie;
                }
            }
        }
        return null;
    }
}

