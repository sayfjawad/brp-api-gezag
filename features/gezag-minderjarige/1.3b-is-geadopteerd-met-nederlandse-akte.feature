#language: nl

Functionaliteit: 1.3b - is geadopteerd met nederlandse akte
  Precondities voor deze vraag:
  | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                       |
  | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                       |
  | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                      |
  | 1.3a Is geboren in het buitenland                                      | Ja                                       |

  Een minderjarige geboren in het buitenland kan zijn geadopteerd met nederlandse akte.
  
  Volgens de regels van het Haags Kinderbeschermingsverdrag 1996 volgt uit adopties met een NL akte het Nederlands recht. In dit geval zijn de
  adoptiefouder(s) de juridische ouder(s).
  
  Hoe achterhalen:
  Bepaal of de persoon op de persoonlijst of in de historiche aktenummers een aktenummer heeft die aangeeft dat de persoon is geadopteerd.
  
  Gebruikte velden:
    - Aktenummer persoon -> 01.81.20
    - Historische aktenummer -> 51.81.20

    Achtergrond:
      Gegeven de persoon 'Fabia' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Mohammed' met burgerservicenummer '000000024'
      * is meerderjarig
      En de persoon 'Carlo' met burgerservicenummer '000000036'
      * is minderjarig
      * is geboren in het buitenland
      * verblijft in Nederland

  Regel: Kind is geadopteerd met nederlandse akte

    Scenario: Het kind is geadopteerd door één ouder als ouder 1 er is sprake van EenhoofdigOuderlijkGezag
      Gegeven 'Carlo' is geadopteerd door 'Fabia' als ouder 1
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | EenhoofdigOuderlijkGezag  |
      | minderjarige.burgerservicenummer | 000000036                 |
      | ouder.burgerservicenummer        | 000000012                 |

    Scenario: Het kind is geadopteerd door één ouder als ouder 2 er is sprake van EenhoofdigOuderlijkGezag
      Gegeven 'Carlo' is geadopteerd door 'Mohammed' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | EenhoofdigOuderlijkGezag  |
      | minderjarige.burgerservicenummer | 000000036                 |
      | ouder.burgerservicenummer        | 000000024                 |

    Scenario: Het kind is geadopteerd door twee ouders er is sprake van TweehoofdigOuderlijkGezag
      Gegeven 'Carlo' is geadopteerd door 'Fabia' als ouder 1
      En 'Carlo' is geadopteerd door 'Mohammed' als ouder 2
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

    Scenario: Het kind is geadopteerd door één ouder, de adoptie is als historisch gegeven opgenomen er is sprake van EenhoofdigOuderlijkGezag
      Gegeven 'Carlo' is geadopteerd door 'Fabia' als ouder 1
      Gegeven persoon 'Carlo' 
      * zijn de volgende gegevens gewijzigd
      | naam                            | waarde     |
      | aktenummer (81.20)              |            |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | EenhoofdigOuderlijkGezag  |
      | minderjarige.burgerservicenummer | 000000036                 |
      | ouder.burgerservicenummer        | 000000012                 |

    Scenario: Het kind is geadopteerd door twee ouders, de adoptie is als historisch gegeven opgenomen er is sprake van TweehoofdigOuderlijkGezag
      Gegeven 'Carlo' is geadopteerd door 'Fabia' als ouder 1
      En 'Carlo' is geadopteerd door 'Mohammed' als ouder 2
      Gegeven persoon 'Carlo' 
      * zijn de volgende gegevens gewijzigd
      | naam                            | waarde     |
      | aktenummer (81.20)              |            |
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

    Scenario: Het kind is geëmigreerd geweest en geadopteerd door twee ouders er is sprake van TweehoofdigOuderlijkGezag
      Gegeven 'Carlo' is geadopteerd door 'Fabia' als ouder 1
      En 'Carlo' is geadopteerd door 'Mohammed' als ouder 2
      En persoon 'Carlo'
      * is geëmigreerd geweest
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

  Regel: Kind is niet geadopteerd met nederlandse akte

    Scenario: Het kind niet geadopteerd er is sprake van GezagNietTeBepalen
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                                                                                                                             |
      | type        | GezagNietTeBepalen                                                                                                                                                                                                 |
      | toelichting | gezag is niet te bepalen omdat minderjarige in het buitenland is geboren en geen Nederlandse adoptie-akte bekend is. |

