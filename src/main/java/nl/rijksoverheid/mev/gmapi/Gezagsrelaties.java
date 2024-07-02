package nl.rijksoverheid.mev.gmapi;

import org.openapitools.model.Gezagsrelatie;

/**
 * Gezagrelaties tussen minderjarige en 0 - * meerderjarige
 */
public final class Gezagsrelaties {

    private Gezagsrelaties() {
    }

    public static Gezagsrelatie of(final nl.rijksoverheid.mev.gezagsmodule.model.Gezagsrelatie gezagsrelatie) {
        var result = new Gezagsrelatie(
                gezagsrelatie.bsnMinderjarige(),
                Gezagsrelatie.SoortGezagEnum.fromValue(gezagsrelatie.soortGezag())
        );
        result.setBsnMeerderjarige(gezagsrelatie.bsnMeerderjarige());

        return result;
    }
}
