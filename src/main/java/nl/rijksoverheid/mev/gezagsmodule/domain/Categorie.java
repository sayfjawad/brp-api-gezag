package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Een categorie wordt gebruikt om een set aan gegevens in de Basis Registratie Personen aan te duiden
 * <p>
 * Voorbeeld categorie 02 is ouder1
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Categorie {
    String number();

    String name();
}
