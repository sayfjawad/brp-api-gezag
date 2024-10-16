package nl.rijksoverheid.mev.gezagsmodule.service;

import nl.rijksoverheid.mev.gezagsmodule.domain.VeldenInOnderzoek;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service voor het samenstellen van de toelichting bij de gezag uitspraak
 */
@Service
public class ToelichtingService {

    private static final String IN_ONDERZOEK = "Gezag is niet te bepalen, omdat de volgende relevante gegevens in onderzoek staan.";
    private static final String IN_ONDERZOEK_PERSOONSVELDEN = " Persoonslijst van persoon: ";
    private static final String IN_ONDERZOEK_VELDEN_OUDER_1 = " Persoonslijst van ouder 1: ";
    private static final String IN_ONDERZOEK_VELDEN_OUDER_2 = " Persoonslijst van ouder 2: ";
    private static final String IN_ONDERZOEK_VELDEN_NIET_OUDER = " Persoonslijst van niet ouder: ";
    private static final String PLACEHOLDER = "%s";
    private static final String ERROR = "gezag is niet te bepalen. Neem contact op met RvIG via info@RvIG onder vermelding van BRP API gezag met de volgende foutcode: %s";

    /**
     * Past de basis toelichting zoals opgenomen in het antwoordenmodel aan op basis van of velden in onderzoek of missende gegevens.
     *
     * @param baseToelichting   de basis toelichting
     * @param veldenInOnderzoek de velden die in onderzoek waren - of null
     * @param missendeGegevens  de missende gegevens - of null
     * @return de toelichting zoals uit het antwoorden model bewerkt met de additionele gegevens
     */
    public String decorateToelichting(final String baseToelichting, final VeldenInOnderzoek veldenInOnderzoek, final List<String> missendeGegevens) {
        StringBuilder sb = new StringBuilder();
        if (baseToelichting != null && !baseToelichting.isEmpty()) {
            if (veldenInOnderzoek != null && veldenInOnderzoek.hasValues()) {
                setInOnderzoek(sb, veldenInOnderzoek);
            } else if (missendeGegevens != null && !missendeGegevens.isEmpty()) {
                setMissendeGegevens(sb, baseToelichting, missendeGegevens);
            }
        }
        
        return sb.toString();
    }

    public String setErrorReferenceToelichting(final String errorTrace) {
        return String.format(ERROR, errorTrace);
    }

    private void setInOnderzoek(final StringBuilder sb, final VeldenInOnderzoek veldenInOnderzoek) {
        sb.append(IN_ONDERZOEK);
        List<String> persoonInOnderzoekVelden = veldenInOnderzoek.getPersoon();
        if (!persoonInOnderzoekVelden.isEmpty()) {
            sb.append(IN_ONDERZOEK_PERSOONSVELDEN);
            sb.append(String.join(", ", persoonInOnderzoekVelden));
            sb.append(".");
        }
        List<String> ouder1InOnderzoekVelden = veldenInOnderzoek.getOuder1();
        if (!ouder1InOnderzoekVelden.isEmpty()) {
            sb.append(IN_ONDERZOEK_VELDEN_OUDER_1);
            sb.append(String.join(", ", ouder1InOnderzoekVelden));
            sb.append(".");
        }
        List<String> ouder2InOnderzoekVelden = veldenInOnderzoek.getOuder2();
        if (!ouder2InOnderzoekVelden.isEmpty()) {
            sb.append(IN_ONDERZOEK_VELDEN_OUDER_2);
            sb.append(String.join(", ", ouder2InOnderzoekVelden));
            sb.append(".");
        }
        List<String> nietOuderInOnderzoekVelden = veldenInOnderzoek.getNietOuder();
        if (!nietOuderInOnderzoekVelden.isEmpty()) {
            sb.append(IN_ONDERZOEK_VELDEN_NIET_OUDER);
            sb.append(String.join(", ", nietOuderInOnderzoekVelden));
            sb.append(".");
        }
    }

    private void setMissendeGegevens(final StringBuilder sb, final String baseUitleg, final List<String> missendeGegevens) {
        if (baseUitleg.contains(PLACEHOLDER)) {
            sb.append(String.format(baseUitleg, String.join(", ", missendeGegevens)));
        } else {
            // toelichting bevat al volledige informatie en heeft geen formattering nodig
        }
    }
}
