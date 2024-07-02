package nl.rijksoverheid.mev.brpadapter.soap.persoonlijst;

import java.time.Clock;
import nl.rijksoverheid.mev.gezagsmodule.domain.Gezagsverhouding;
import nl.rijksoverheid.mev.gezagsmodule.domain.GeschiedenisHuwelijkOfPartnerschap;
import nl.rijksoverheid.mev.gezagsmodule.domain.GeschiedenisPersoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.HuwelijkOfPartnerschap;
import nl.rijksoverheid.mev.gezagsmodule.domain.Inschrijving;
import nl.rijksoverheid.mev.gezagsmodule.domain.Kind;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder1;
import nl.rijksoverheid.mev.gezagsmodule.domain.Ouder2;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoonslijst;
import nl.rijksoverheid.mev.gezagsmodule.domain.Persoon;
import nl.rijksoverheid.mev.gezagsmodule.domain.Verblijfplaats;
import lombok.extern.slf4j.Slf4j;
import nl.bprbzk.gba.lo3.version1.Categoriestapel;
import nl.bprbzk.gba.lo3.version1.Categorievoorkomen;
import nl.bprbzk.gba.lo3.version1.Element;
import nl.bprbzk.gba.lo3.version1.PL;
import nl.bprbzk.gba.lrdplus.version1.VraagResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

/**
 * Functionaliteit voor het vertalen van BRP data naar persoonslijst gegevens
 */
@Slf4j
@Component
public class PersoonslijstFactory {

    private final Clock clock;

    public PersoonslijstFactory(final Clock clock) {
        this.clock = clock;
    }

    /**
     * Bouw een persoonslijst op basis van het vraag response
     *
     * @param vraagResponse de vraag response
     * @return de persoonslijst
     */
    public Persoonslijst build(final VraagResponse vraagResponse) {
        Persoonslijst persoonslijst = new Persoonslijst(clock);

        PL pl = vraagResponse.getVraagReturn().getPersoonslijsten().getItem().get(0);
        for (Categoriestapel stapel : pl.getCategoriestapels().getItem()) {
            for (Categorievoorkomen categorievoorkomen : stapel.getCategorievoorkomens().getItem()) {
                String categorienummer = leadingZero(Byte.toString(categorievoorkomen.getCategorienummer()), 1);

                Map<String, String> values = new HashMap<>();
                for (Element entry : categorievoorkomen.getElementen().getItem()) {
                    values.put(asCompleteCode(
                            String.valueOf(entry.getNummer()), categorienummer),
                            entry.getWaarde());
                }

                switch (categorienummer) {
                    case Categorie.PERSOON ->
                        persoonslijst.addVeld(Categorie.PERSOON, new Persoon(values, clock));
                    case Categorie.OUDER_1 ->
                        persoonslijst.addVeld(Categorie.OUDER_1, new Ouder1(values, clock));
                    case Categorie.OUDER_2 ->
                        persoonslijst.addVeld(Categorie.OUDER_2, new Ouder2(values, clock));
                    case Categorie.HUWELIJK_OF_PARTNERSCHAP ->
                        persoonslijst.addVeldToList(Categorie.HUWELIJK_OF_PARTNERSCHAP, new HuwelijkOfPartnerschap(values, clock));
                    case Categorie.INSCHRIJVING ->
                        persoonslijst.addVeld(Categorie.INSCHRIJVING, new Inschrijving(values));
                    case Categorie.VERBLIJFPLAATS ->
                        persoonslijst.addVeld(Categorie.VERBLIJFPLAATS, new Verblijfplaats(values, clock));
                    case Categorie.KIND ->
                        persoonslijst.addVeldToList(Categorie.KIND, new Kind(values, clock));
                    case Categorie.GEZAGSVERHOUDING ->
                        persoonslijst.addVeld(Categorie.GEZAGSVERHOUDING, new Gezagsverhouding(values, clock));
                    case Categorie.GESCHIEDENIS_PERSOON ->
                        persoonslijst.addVeldToList(Categorie.GESCHIEDENIS_PERSOON, new GeschiedenisPersoon(values, clock));
                    case Categorie.GESCHIEDENIS_HUWELIJK_OF_PARTNERSCHAP ->
                        persoonslijst.addVeldToList(Categorie.GESCHIEDENIS_HUWELIJK_OF_PARTNERSCHAP, new GeschiedenisHuwelijkOfPartnerschap(values, clock));
                    default ->
                        log.debug("Ongebruikte categorie ontvangen met nummer: {}", categorienummer);
                }
            }
        }

        return persoonslijst;
    }

    private String asCompleteCode(String elementNumber, final String categorieNummer) {
        elementNumber = leadingZero(elementNumber, 3);
        return categorieNummer + elementNumber;
    }

    private String leadingZero(final String elementNumber, int lengthWhenToAdd) {
        return elementNumber.length() == lengthWhenToAdd ? "0" + elementNumber : elementNumber;
    }
}
