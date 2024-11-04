#language: nl

Functionaliteit: 1.4 - is uitspraak gezag aanwezig
  Precondities voor deze vraag:
  | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                                                 |
  | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                                                                 |
  | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                                                                |
  | 1.3a Is minderjarige in buitenland geboren?                            | Nee of geadopteerd met NL akte                                                     |

  Indien er een uitspraak is die invloed heeft op het gezag, is het gezag in principe niet meer van rechtswege bepaald.

  Uitspraak moet hier breed worden gezien. Het betreft alle rechtsfeiten die invloed hebben op het gezag en van de rechtbank
  (Gezagsregister) afkomstig zijn, zoals o.m. de aantekening in het gezagsregister zonder rechterlijke tussenkomst op grond van art. 1:252 BW. Het kan
  voorkomen dat er een uitspraak m.b.t. het gezag is gedaan, maar dat door een bepaalde gebeurtenis op een later moment toch het gezag weer van
  rechtswege is bepaald.

  Op dit moment is het niet mogelijk om te achterhalen of een minderjarige meerderjarig is verklaard. Dit zou bij indicatie gezag kunnen
  worden opgelost door opname van een extra indicatie. Hiervoor ligt een voorstel de letter M van meerderjarig tot te voegen aan de
  mogelijke indicaties bij element indicatie gezag.

  Aanbevelingen
  Meerderjarigheidsverklaring opnemen in indicatie gezag. Uiteraard moet dan ook bij vraag 1.4 en 4a.2, 4a.3 en 4b.1
  rekening gehouden worden met deze indicatie; als de leeftijd van een persoon kleiner is dan 18 jaar en er geen
  meerderjarigheidsverklaring voorligt is de persoon minderjarig.

  Gebruikte velden:
    - Indiatie gezag -> 11.32.10

    Achtergrond:
      Gegeven de persoon 'Rianne' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Adriaan' met burgerservicenummer '000000024'
      * is meerderjarig
      En 'Rianne' en 'Adriaan' zijn met elkaar gehuwd
      En de persoon 'Babette' met burgerservicenummer '000000036'
      * is minderjarig
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * heeft 'Rianne' als ouder 1
      * heeft 'Adriaan' als ouder 2

  Regel: Geen uitspraak gezag van toepassing

    Scenario: Voor het kind is geen uitspraak gezag aanwezig er is sprake van TweehoofdigOuderlijkGezag
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

    Scenario: Voor het kind een uitspraak gedaan maar is geen indicatie betreft gezag er is sprake van TweehoofdigOuderlijkGezag
      Gegeven voor 'Babette' is een gerechtelijke uitspraak over het gezag gedaan met de volgende gegevens
      | naam                               | waarde                      |
      | indicatie curateleregister (33.10) | 1                           |
      | ingangsdatum geldigheid (85.10)    | 20210701                    |
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

#    Scenario: Voor het kind een uitspraak gedaan maar de indicatie betreft gezag is nog niet ingegaan
#    Niet ondersteund, issue voor deze functionaliteit: https://github.com/BRP-API/brp-api-gezag/issues/171

#    Scenario: Voor het kind een uitspraak gedaan met een indicatie voor gezag maar deze uitspraak is beëindigd
#    Niet ondersteund, issue voor deze functionaliteit: https://github.com/BRP-API/brp-api-gezag/issues/171

#    Scenario: Voor het kind een uitspraak gedaan met een indicatie voor gezag maar deze uitspraak is gecorrigeerd
#    Niet ondersteund, issue voor deze functionaliteit: https://github.com/BRP-API/brp-api-gezag/issues/171

  Regel: Uitspraak gezag is in onderzoek

    Scenario: Voor het kind een uitspraak gedaan dat alleen ouder 1 gezag heeft, maar deze uitspraak staat op dit moment in onderzoek, resulteerd in GezagNietTeBepalen
      Gegeven voor 'Babette' is een gerechtelijke uitspraak over het gezag gedaan met de volgende gegevens
      | naam                                 | waarde                          |
      | indicatie gezag minderjarige (32.10) | 1                               |
      | ingangsdatum geldigheid (85.10)      | 20230101                        |
      | aanduiding in onderzoek (83.10)      | 113210                          |
      | datum ingang onderzoek (83.20)       | 20230101                        |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                                                                                      |
      | type                             | GezagNietTeBepalen                                                                                                                          |
      | toelichting                      | Gezag is niet te bepalen, omdat de volgende relevante gegevens in onderzoek staan. Persoonslijst van persoon: indicatie gezag minderjarige. |

    Scenario: Voor het kind een uitspraak gedaan, deze uitspraak stond in onderzoek er is sprake van EenhoofdigOuderlijkGezag
      Gegeven voor 'Babette' is een gerechtelijke uitspraak over het gezag gedaan met de volgende gegevens
      | naam                                 | waarde                          |
      | indicatie gezag minderjarige (32.10) | 1                               |
      | ingangsdatum geldigheid (85.10)      | 20230101                        |
      | aanduiding in onderzoek (83.10)      | 113210                          |
      | datum ingang onderzoek (83.20)       | 20230101                        |
      | datum einde onderzoek (83.30)        | 20231001                        |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                       |
      | type                             | EenhoofdigOuderlijkGezag     |
      | minderjarige.burgerservicenummer | 000000036                    |
      | ouder.burgerservicenummer        | 000000012                    |

  Regel: Uitspraak gezag is aanwezig

    Scenario: Voor het kind een actuele uitspraak gedaan dat alleen ouder 2 gezag heeft er is sprake van EenhoofdigOuderlijkGezag
      Gegeven voor 'Babette' is een gerechtelijke uitspraak over het gezag gedaan met de volgende gegevens
      | naam                                 | waarde                          |
      | indicatie gezag minderjarige (32.10) | 2                               |
      | ingangsdatum geldigheid (85.10)      | 20230101                        |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                       |
      | type                             | EenhoofdigOuderlijkGezag     |
      | minderjarige.burgerservicenummer | 000000036                    |
      | ouder.burgerservicenummer        | 000000024                    |
