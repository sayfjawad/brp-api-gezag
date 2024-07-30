package nl.rijksoverheid.mev.common.util;

import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.exception.InvalidBSNException;
import java.util.ArrayList;
import java.util.List;

/**
 * Validator voor BSNs
 */
public final class BSNValidator {

    private static final int MAX_BSN_LEN = 9;
    private static final int MIN_BSN_LEN = 8;
    private static final int ELF_PROEF_DENOMINATOR = 11;

    /**
     * Valideer één of meerdere bsns
     *
     * @param bsns de lijst met bsns
     * @return of de bsns valide zijn
     * @throws GezagException wanneer één van de bsns niet voldoet aan de elf
     * proef
     */
    public boolean isValid(final List<String> bsns) {
        boolean valid = true;

        if (bsns != null) {
            for (String bsn : bsns) {
                valid = isValid(bsn);

                if (!valid) {
                    break;
                }
            }
        }

        return valid;
    }
    
    /**
     * Valideer een bsn
     *
     * @param bsn de bsn
     * @return of de bsn valide is
     * @throws GezagException wanneer de bsn niet voldoet aan de elf proef
     */
    public boolean isValid(final String bsn) throws GezagException {
        if (bsn == null) {
            return false;
        }
        List<Integer> bsnArray = new ArrayList<>();
        if (bsn.matches("\\d+")) {
            for (int i = 0; i < bsn.length(); i++) {
                String charString = String.valueOf(bsn.charAt(i));
                bsnArray.add(Integer.valueOf(charString));
            }
            int bsnArrayLength = bsnArray.size();
            if (bsnArrayLength == MIN_BSN_LEN || bsnArrayLength == MAX_BSN_LEN) {
                return elfProef(bsnArray, bsnArrayLength);
            }
        }

        throw new InvalidBSNException();
    }

    private boolean elfProef(List<Integer> bsnArray, Integer bsnArrayLength) {
        if (bsnArrayLength == MIN_BSN_LEN) {
            bsnArray.add(0, 0);
        }
        int times = MAX_BSN_LEN;
        int total = 0;
        for (int number : bsnArray) {
            if (times == 1) {
                times = -1;
            }
            total += number * times;
            times -= 1;
        }
        return total % ELF_PROEF_DENOMINATOR == 0;
    }
}