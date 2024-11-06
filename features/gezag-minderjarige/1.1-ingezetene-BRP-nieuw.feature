#language: nl

Functionaliteit: 1.1 - Staat persoon (minderjarige) als ingezetene in de BRP?

  Regel: Een persoon is "niet ingezetene" wanneer deze staat ingeschreven in het RNI

    Scenario: minderjarige staat ingeschreven in RNI
      Gegeven de persoon 'Zoe' met burgerservicenummer '000000036'
      En persoon 'Zoe' is ingeschreven in de RNI
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                               |
      | type        | GezagNietTeBepalen                                                   |
      | toelichting | gezag is niet te bepalen omdat minderjarige niet in Nederland woont. |
      En heeft de gezag API de volgende logregels voor persoon 'Zoe'
      """
      1.1. persoon staat niet als ingezetene in de BRP => uitval
      """

    @skip-verify
    Abstract Scenario: minderjarige staat ingeschreven in een Nederlandse gemeente
      Gegeven adres 'A1' heeft de volgende gegevens
      | gemeentecode (92.10) | identificatiecode verblijfplaats (11.80) |
      | <gemeentecode>       | <gemeentecode>000000000001               |
      En de persoon 'Zoe' met burgerservicenummer '000000036' staat ingeschreven op adres 'A1'
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan gaat de gezag API verder naar vraag 1.2
      Dan heeft de gezag API de volgende logregels voor persoon 'Zoe'
      """
      1.1. persoon staat als ingezetene in de BRP => vervolg vraag 1.2
      """

      Voorbeelden:
      | gemeentecode |
      | 0518         |
      | 0344         |
      | 0599         |

  Regel: Open vragen

    - Wat als de persoon dakloos is?
    een ingechreven dakloze persoon heeft een briefadres (bijv. op het stadhuis)
    - Wat als de persoon een tijdelijk verblijfadres heeft?
    een persoon met een tijdelijk verblijfadres is ingeschreven in de RNI
    - Wat als de geregistreerde verblijfplaats van de persoon een briefadres is?
    @Patrick, @Frank weten jullie hoe de gezagsmodule hier mee omgaat? Kan het niet direct vinden in de documentatie.
    - Wat als bij de geregistreerde verblijfplaats van de persoon bij gemeente van inschrijving een Nederlandse gemeente is maar de buitenlandse adres velden zijn gevuld?
