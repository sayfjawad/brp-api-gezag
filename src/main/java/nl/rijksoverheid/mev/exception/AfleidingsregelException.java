package nl.rijksoverheid.mev.exception;

import lombok.Getter;

/**
 * Exceptie wanneer iets misgaat met afleidingsregels
 */
public class AfleidingsregelException extends GezagException {

    @Getter
    private final String missendVeld;

    public AfleidingsregelException(final String message, final String missendVeld) {
        super(message);

        this.missendVeld = missendVeld;
    }
}
