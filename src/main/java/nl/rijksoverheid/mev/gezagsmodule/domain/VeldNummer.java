package nl.rijksoverheid.mev.gezagsmodule.domain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Een veldnummer wordt gebruikt om een gegeven in de Basis Registratie Personen aan te duiden
 * <p>
 * Voorbeeld 010120 is burgerservicenummer van persoon
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface VeldNummer {
    String number();
    String name() default "";
}
