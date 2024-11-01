#language: nl

Functionaliteit: 2.1 - hoeveel juridische ouders heeft minderjarige
  Precondities voor deze vraag:
  | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                                                 |
  | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                                                                 |
  | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                                                                |
  | 1.3a Is minderjarige in buitenland geboren?                            | Nee of geadopteerd met NL akte                                                     |
  | 1.4 Uitspraak gezag aanwezig?                                          | Nee of door een recente gebeurtenis het gezag toch (weer) van rechtswege verkregen |                                                                               |

  Juridisch ouderschap is een belangrijk gegeven voor het bepalen van het gezag van rechtswege. De juridische ouders oefenen veelal het
  gezag uit over de minderjarige van rechtswege.

  Mogelijke uitkomsten:
  1. Twee ouders
  -> Ga naar: 2a1 - Achterhalen gezag bij 2 ouders
  3. Eén ouder
  -> Ga naar: 2b - Achterhalen gezag bij 1 ouder
  4. Geen ouders
  -> Er is (tijdelijk) geen gezag
  5. Onbekende ouders (puntouders)
  -> Gezag is onbekend (in BRP wordt vastgelegd dat er wel een ouder is, maar het is niet bekend wie, zgn Puntouders)

  Hoe achterhalen: Geslachtsnaam Ouder1 komt voor en is ongelijk aan ‘.’. Idem voor geslachtsnaam Ouder2.
  Geslachtsnaam ‘.’ bij een ouder geeft aan dat er nog geen documenten getoond zijn waaruit de juridische ouder afgeleid kan (en mag) worden. Dat zal
  voor 99,99% het geval zijn bij minderjarigen die in het buitenland geboren zijn en die vallen al af in regel 1.3. Komt de geslachtsnaam van de ouder
  niet voor dan is er juridisch geen ouder.

    Achtergrond:
      Gegeven de persoon 'Gertruda' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Gerrit' met burgerservicenummer '000000024'
      * is meerderjarig
      En de persoon 'Gert' met burgerservicenummer '000000036'
      * is minderjarig
      * is in Nederland geboren
      * is ingeschreven in de BRP

  Regel: Kind heeft twee juridische ouders

    Scenario: Beide ouders staan geregistreerd voor het kind er is sprake van TweehoofdigOuderlijkGezag
      Gegeven 'Gertruda' en 'Gerrit' zijn met elkaar gehuwd
      Gegeven persoon 'Gert'
      * heeft 'Gertruda' als ouder 1
      * heeft 'Gerrit' als ouder 2
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

  Regel: Kind heeft een juridisch ouder 

    Scenario: Voor het kind is 1 ouder bekend als ouder 1 er is sprake van EenhoofdigOuderlijkGezag
      Gegeven  persoon 'Gert'
      * heeft 'Gertruda' als ouder 1
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

    Scenario: Voor het kind is 1 ouder bekend als ouder 2 er is sprake van EenhoofdigOuderlijkGezag
      Gegeven  persoon 'Gert'
      * heeft 'Gerrit' als ouder 2
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

  Regel: Kind heeft geen juridische ouders

    Scenario: Voor het kind is geen van de ouders bekend er is sprake van TijdelijkGeenGezag
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TijdelijkGeenGezag        |

  Regel: Kind heeft punt ouders 

    Scenario: Kind heeft een punt ouder als ouder 1
      Gegeven de persoon '.' met burgerservicenummer '000000048'
      Gegeven  persoon 'Gert'
      * heeft '.' als ouder 1
      * heeft 'Gerrit' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                          |
      | type        | GezagNietTeBepalen                                                              |
      | toelichting | gezag is niet te bepalen omdat één of meer ouders van minderjarige onbekend is. |

    Scenario: Kind heeft een punt ouder als ouder 2
      Gegeven de persoon '.' met burgerservicenummer '000000048'
      Gegeven  persoon 'Gert'
      * heeft 'Gertruda' als ouder 1
      * heeft '.' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                          |
      | type        | GezagNietTeBepalen                                                              |
      | toelichting | gezag is niet te bepalen omdat één of meer ouders van minderjarige onbekend is. |

    Scenario: Kind heeft twee puntouders
      Gegeven de persoon '.' met burgerservicenummer '000000048'
      Gegeven de persoon '.2' met burgerservicenummer '000000061'
      Gegeven  persoon 'Gert'
      * heeft '.' als ouder 1
      * heeft '.2' als ouder 2 met de volgende gegevens
      | naam                                               | waarde   |
      | geslachtsnaam (02.40)                              | .        |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                          |
      | type        | GezagNietTeBepalen                                                              |
      | toelichting | gezag is niet te bepalen omdat één of meer ouders van minderjarige onbekend is. |