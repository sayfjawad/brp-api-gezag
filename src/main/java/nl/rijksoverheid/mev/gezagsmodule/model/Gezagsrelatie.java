package nl.rijksoverheid.mev.gezagsmodule.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Gezagsrelatie tussen minderjarig en meerderjarige met soort gezag
 */
@Getter
@Setter
@AllArgsConstructor
public class Gezagsrelatie {

    private String bsnBevraagdePersoon;
    private String bsnMinderjarige;
    private String soortGezag;
    private String bsnMeerderjarige;
    private String toelichting;

    public Gezagsrelatie(
            final String bsnMinderjarige,
            final String soortGezag,
            final String bsnMeerderjarige,
            final String toelichting) {
        this.bsnMinderjarige = bsnMinderjarige;
        this.soortGezag = soortGezag;
        this.bsnMeerderjarige = bsnMeerderjarige;
        this.toelichting = toelichting;
    }

    public Gezagsrelatie(final String bsnMinderjarige,
            final String soortGezag) {
        this.bsnMinderjarige = bsnMinderjarige;
        this.soortGezag = soortGezag;
    }

    @Override
    public String toString() {
        return "Gezagsrelatie{" 
                + "bsnBevraagdePersoon=" + bsnBevraagdePersoon 
                + ", bsnMinderjarige=" + bsnMinderjarige 
                + ", soortGezag=" + soortGezag 
                + ", bsnMeerderjarige=" + bsnMeerderjarige 
                + ", toelichting=" + toelichting + '}';
    }
}