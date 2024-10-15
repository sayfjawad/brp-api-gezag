#language: nl

Functionaliteit: 1.1 - Staat persoon (minderjarige) als ingezetene in de BRP?


    Achtergrond:
      Gegeven de persoon 'Ingrid' met burgerservicenummer '000000012'
      * is meerderjarig, niet overleden en staat niet onder curatele
      En de persoon 'Henk' met burgerservicenummer '000000024'
      * is meerderjarig, niet overleden en staat niet onder curatele
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      En de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * heeft 'Ingrid' als ouder 1
      * heeft 'Henk' als ouder 2


  Regel: Als de gemeente van inschrijving is RNI kan het gezag van de persoon niet worden bepaald
    # een persoon staat in de RNI ingeschreven wanneer gemeente van inschrijving (09.10) gelijk is aan 1999.
    # een persoon staat in de BRP ingeschreven wanneer gemeente van inschrijving (09.10) ongelijk is aan 1999.

    Scenario: minderjarige staat ingeschreven in RNI (route 1)
      Gegeven is ingeschreven in de RNI met de volgende gegevens
      | gemeente van inschrijving (09.10) |
      | 1999                              |
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

    Scenario: minderjarige staat ingeschreven in Nederlandse gemeente
      Gegeven is ingeschreven in de BRP met de volgende gegevens
      | gemeente van inschrijving (09.10) |
      | 0518                              |
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


  Regel: onderzoek op gemeente van inschrijving leidt niet tot uitval

    Abstract Scenario: minderjarige staat ingeschreven in Nederlandse gemeente en <omschrijving> staat in onderzoek
      Gegeven de persoon 'P3' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP met de volgende gegevens
      | gemeente van inschrijving (09.10) | aanduiding in onderzoek (83.10) |
      | 0518                              | <aanduiding in onderzoek>       |
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
      | aanduiding in onderzoek | omschrijving                        |
      | 080000                  | hele categorie verblijfplaats       |
      | 080900                  | groep gemeente                      |
      | 080910                  | gemeente van inschrijving           |
      | 089999                  | vastgesteld verblijft niet op adres |
