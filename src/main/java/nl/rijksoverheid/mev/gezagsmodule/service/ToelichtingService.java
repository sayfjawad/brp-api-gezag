package nl.rijksoverheid.mev.gezagsmodule.service;

import nl.rijksoverheid.mev.gezagsmodule.domain.VeldenInOnderzoek;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service voor het samenstellen van de toelichting bij de gezag uitspraak
 */
@Service
public class ToelichtingService {

    private static final String IN_ONDERZOEK = "Uitspraak is gezag niet te bepalen, omdat er bij de gezagbepaling waardes in onderzoek waren gedetecteerd. Bij het bepalen van gezag werd het volgende veld gebruikt dat in onderzoek staat: \n ";
    private static final String IN_ONDERZOEK_PERSOONSVELDEN = "Persoonsvelden: ";
    private static final String IN_ONDERZOEK_VELDEN_OUDER_1 = " Velden van ouder 1: ";
    private static final String IN_ONDERZOEK_VELDEN_OUDER_2 = " Velden van ouder 2: ";
    private static final String IN_ONDERZOEK_VELDEN_NIET_OUDER = " Velden van niet ouder: ";
    private static final String PLACEHOLDER = "%s";
    private static final String SPACE = " ";
    private static final String DOT_ENDLINE = ".\n";

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
                sb.append(baseToelichting);
                sb.append(SPACE);
                setInOnderzoek(sb, veldenInOnderzoek);
            } else if (missendeGegevens != null && !missendeGegevens.isEmpty()) {
                setMissendeGegevens(sb, baseToelichting, missendeGegevens);
            }
        }
        
        return sb.toString();
    }

    private void setInOnderzoek(final StringBuilder sb, final VeldenInOnderzoek veldenInOnderzoek) {
        sb.append(IN_ONDERZOEK);
        List<String> persoonInOnderzoekVelden = veldenInOnderzoek.getPersoon();
        if (!persoonInOnderzoekVelden.isEmpty()) {
            sb.append(IN_ONDERZOEK_PERSOONSVELDEN);
            sb.append(String.join(", ", persoonInOnderzoekVelden));
            sb.append(DOT_ENDLINE);
        }
        List<String> ouder1InOnderzoekVelden = veldenInOnderzoek.getOuder1();
        if (!ouder1InOnderzoekVelden.isEmpty()) {
            sb.append(IN_ONDERZOEK_VELDEN_OUDER_1);
            sb.append(String.join(", ", ouder1InOnderzoekVelden));
            sb.append(DOT_ENDLINE);
        }
        List<String> ouder2InOnderzoekVelden = veldenInOnderzoek.getOuder2();
        if (!ouder2InOnderzoekVelden.isEmpty()) {
            sb.append(IN_ONDERZOEK_VELDEN_OUDER_2);
            sb.append(String.join(", ", ouder2InOnderzoekVelden));
            sb.append(DOT_ENDLINE);
        }
        List<String> nietOuderInOnderzoekVelden = veldenInOnderzoek.getNietOuder();
        if (!nietOuderInOnderzoekVelden.isEmpty()) {
            sb.append(IN_ONDERZOEK_VELDEN_NIET_OUDER);
            sb.append(String.join(", ", nietOuderInOnderzoekVelden));
            sb.append(DOT_ENDLINE);
        }
    }

    private void setMissendeGegevens(final StringBuilder sb, final String baseUitleg, final List<String> missendeGegevens) {
        if (baseUitleg.contains(PLACEHOLDER)) {
            sb.append(baseUitleg.format(String.join(", ", missendeGegevens)));
        } else {
            // toelichting bevat al volledige informatie en heeft geen formattering nodig
        }
    }
}
