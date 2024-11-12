#language: nl

Functionaliteit: 1.3 - is naar buitenland geëmigreerd geweest
  Precondities voor deze vraag:
  | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                       |
  | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                       |

  Een kind kan het gezag uit het buitenland hebben meegenomen wanneer hij naar het buitenland geëmigreerd is geweest of tijdelijk in het buitenland heeft gewoond. Dit gezag kan niet betrouwbaar worden bepaald, omdat regelgeving uit het buitenland mogelijk niet bekend is bij de Nederlandse overheid.

  Vanaf 1 mei 2011 zijn de regels van het Haags Kinderbeschermingsverdrag 1996 van toepassing.
  Vanaf deze datum wordt het toepasselijk recht bepaald door het recht van het land van de gewone verblijfplaats van het kind.
  Daarnaast blijft het verkregen gezag behouden na wijziging van de gewone verblijfplaats. Gezag verhuist dus mee met het kind.
  
  Hoe achterhalen:
  Bepaal of de persoon als geboorteland Nederland heeft en datum vestiging in Nederland voor komt.
  
  Gebruikte velden:
    - Geboorteland -> 01.03.30
    - Datum vestiging in Nederland -> 08.14.20

    Achtergrond:
      Gegeven de persoon 'Ruth' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Maurice' met burgerservicenummer '000000024'
      * is meerderjarig
      En 'Ruth' en 'Maurice' zijn met elkaar gehuwd
      En de persoon 'Lisa' met burgerservicenummer '000000036'
      * is minderjarig
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * heeft 'Ruth' als ouder 1
      * heeft 'Maurice' als ouder 2

  Regel: Gezag wordt bepaald voor kinderen die onafgebroken in Nederland hebben gewoond

    Scenario: Een kind met twee ouders dat altijd in Nederland heeft gewoond heeft tweehoofdig ouderlijk gezag
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

    Scenario: Van een kind dat geëmigreerd is geweest kan geen gezag worden bepaald
      Gegeven persoon 'Lisa'
      * is geëmigreerd geweest
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                         |
      | type        | GezagNietTeBepalen                                                             |
      | toelichting | gezag is niet te bepalen omdat minderjarige buiten Nederland heeft verbleven.  |
