#language: nl

Functionaliteit: 1.3a - is geboren in het buitenland
  Precondities voor deze vraag:
  | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                       |
  | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                       |
  | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                      |

  Een kind geboren in het buitenland kan het gezag uit het buitenland hebben meegenomen. Dit gezag kan niet betrouwbaar worden bepaald, omdat regelgeving uit het buitenland mogelijk niet bekend is bij de Nederlandse overheid.
  
  Een kind dat in het buitenland is geboren kan het gezag uit het buitenland hebben meegenomen. Het gezag kan niet betrouwbaar worden bepaald, omdat buitenlandse regelgeving van toepassing kan zijn die bij de Nederlandse overheid onbekend is. Uitzondering hierop zijn kinderen die in Nederland zijn geadopteerd.
  
  Hoe achterhalen:
  Bepaal of de persoon als geboorteland een ander land heeft dan Nederland.
  
  Gebruikte velden:
    - Geboorteland -> 01.03.30

    Achtergrond:
      Gegeven de persoon 'Regina' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Paul' met burgerservicenummer '000000024'
      * is meerderjarig
      En 'Regina' en 'Paul' zijn met elkaar gehuwd
      En de persoon 'Nona' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * heeft 'Regina' als ouder 1
      * heeft 'Paul' als ouder 2

  Regel: Gezag wordt bepaald voor kinderen die in Nederland zijn geboren of geadopteerd

    Scenario: Van een kind die in het buitenland is geboren en niet is geadopteerd in Nederland kan gezag niet worden bepaald
      Gegeven persoon 'Nona' 
      * is in het buitenland geboren
      * is niet in Nederland geadopteerd
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                                                                                                                             |
      | type        | GezagNietTeBepalen                                                                                                                                                                                                 |
      | toelichting | gezag is niet te bepalen omdat minderjarige in het buitenland is geboren. |

    Scenario: Een in Nederland geboren kind met twee ouders heeft tweehoofdig ouder gezag
      Gegeven persoon 'Nona' 
      * is in Nederland geboren
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

    Scenario: Van een kind met onbekend geboorteland kan het gezag niet worden bepaald
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                                                                                                                             |
      | type        | GezagNietTeBepalen                                                                                                                                                                                                 |
      | toelichting | gezag is niet te bepalen omdat niet kan worden vastgesteld of de persoon naar het buitenland geëmigreerd geweest is omdat de volgende relevante gegevens ontbreken: Geboorteland van bevraagde persoon |
