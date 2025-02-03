package nl.rijksoverheid.mev.gezagsmodule.domain;

import nl.rijksoverheid.mev.exception.AfleidingsregelException;
import nl.rijksoverheid.mev.exception.GezagException;
import nl.rijksoverheid.mev.gezagsmodule.domain.gezagvraag.GezagsBepaling;

/**
 * Controleerd of aan verschillende precondities is voldaan
 */
public class PreconditieChecker {

    private PreconditieChecker() {}

    /**
     * Controleer of de ouders geregistreerd zijn
     *
     * @param gezagsBepaling de gezagsbepaling om de ouders voor te controleren
     * @throws GezagException wanneer de ouder niet is geregistreerd
     */
    public static  void preconditieCheckOudersGeregistreerd(final GezagsBepaling gezagsBepaling) throws GezagException {
        final var plPersoon = gezagsBepaling.getPlPersoon();
        if (!plPersoon.heeftTweeOuders()) {
            throw new AfleidingsregelException(
                "Preconditie: Kind moet twee ouders hebben",
                "Van de bevraagde persoon zijn geen twee ouders bekend"
            );
        }
        preconditieCheckGeregistreerd("ouder1", gezagsBepaling.getPlOuder1());
        preconditieCheckGeregistreerd("ouder2", gezagsBepaling.getPlOuder2());
    }

    /**
     * Controleer of de persoonslijst een ingeschreven persoon is
     *
     * @param beschrijving de type persoon om te controleren
     * @param plOuder de persoonslijst van de ouder
     * @throws GezagException wanneer de ouder niet is geregistreerd
     */
    public static void preconditieCheckGeregistreerd(final String beschrijving, final Persoonslijst plOuder) throws GezagException {
        boolean ouderGeregistreerdInBrp =
            plOuder != null
                && plOuder.isNietIngeschrevenInRNI()
                && plOuder.isNietGeemigreerd();
        if (!ouderGeregistreerdInBrp) {
            throw new AfleidingsregelException(
                "Preconditie: " + beschrijving + " moet in BRP geregistreerd staan",
                beschrijving + " van bevraagde persoon is niet in BRP geregistreerd");
        }
    }
}
