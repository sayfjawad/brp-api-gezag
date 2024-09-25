    #language: nl

    Functionaliteit:  3.1 - Is er door een recente gebeurtenis - adoptie - het gezag toch (weer) van rechtswege ondanks dat er eerder een uitspraak is gedaan?

    Achtergrond:
    Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
    * is ingeschreven in de BRP
    * is minderjarig
    * heeft een ouder 'Ingrid' met burgerservicenummer '000000012'
    * heeft een ouder 'Henk' met burgerservicenummer '000000024'
    * 'Ingrid' en 'Henk' zijn met elkaar gehuwd
    * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele

      Regel: Als adoptie heeft plaatsgevonden na de gerechtelijke uitspraak over gezag, wordt het gezag van rechtswege bepaald
      # Als de ingangsdatum gezag (11.85.10) kleiner is dan de datum ingang familierechtelijke betrekking (02.62.10 en 03.62.10)
      # en de akteaanduiding (01.81.20 of 51.81.20) bevat de waarde Q op de 3e positie dan is sprake van een adoptie nadat het
      # gezag is opgenomen.
      # Deze kan in de actuele categorie voorkomen maar ook in een historische categorie omdat er later nog een ander rechtsfeit
      # geregistreerd kan zijn.

      Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd door beide ouders na de gerechtelijke uitspraak
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | <indicatie gezag>                    | gisteren - 5 jaar               |
      En is geadopteerd met de volgende gegevens
      | aktenummer (81.20) |
      | 1AQ0100            |
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000036                 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |

      Voorbeelden:
      | indicatie gezag |
      | 1               |
      | 2               |
      | 12              |
      | 1D              |
      | 2D              |
      | D               |
