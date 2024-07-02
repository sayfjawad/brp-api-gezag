package nl.rijksoverheid.mev.exception;

/**
 * Exceptie wanneer hoofdstroomschema niet gevonden kan worden
 */
public class HoofdstroomSchemaNietGevondenExceptie extends GezagException {

    public HoofdstroomSchemaNietGevondenExceptie(final String message) {
        super(message);
    }
}
