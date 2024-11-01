#language: nl

Functionaliteit: 2a.1 - Zijn juridische ouders nu met elkaar gehuwd of partners
  Precondities voor deze vraag:
  | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                                                 |
  | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                                                                 |
  | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                                                                |
  | 1.3a Is minderjarige in buitenland geboren?                            | Nee of geadopteerd met NL akte                                                     |
  | 1.4 Uitspraak gezag aanwezig?                                          | Nee of door een recente gebeurtenis het gezag toch (weer) van rechtswege verkregen |
  | 2.1 Hoeveel juridische ouders heeft de minderjarige?                   | Twee_ouders                                                                              |

  Burgerlijke staat is van belang voor het achterhalen van het gezag. Bijvoorbeeld: Juridische ouders hebben samen het gezag tijdens
  huwelijk/partnerschap (1:251 lid 1 en 1:253aa lid 1 BW)

  Mogelijke uitkomsten:
  1) Ja, gehuwd/partners
    • Voor of na geboorte kind -> OG2: Beide ouders hebben gezamenlijk het ouderlijk gezag (1:251 lid 1 en 1:253aa lid 1 BW)
    • Hertrouwd -> OG2: Beide ouders hebben het gezag (1:253 lid 1 BW). Dit geldt als het gezag na de scheiding bij één van de ouders lag door beëindiging
    van het gezag op grond van 1:251a of 1:253n. Zie voor de uitzondering de beschrijving bij hertrouwen in ‘3 – Achterhalen gezag na uitspraak’. Let op:
    Dit is nu niet of lastig te achterhalen!
    • Adoptiefouders -> OG2 Zie Uitleg bij 2a2.
  2) Nee, gescheiden (van elkaar) na de geboorte kind
    • OG2: Beide ouders hebben (nog steeds) gezamenlijk het ouderlijk gezag (1:251 lid 2 BW). Dit geldt ook na ontbinding geregistreerd partnerschap.
  3) Nee, na geboorte kind nooit gehuwd/partners geweest (met elkaar)
    • Adoptiefouders -> Zie uitleg bij 2a2
    • Geen adoptiefouders -> OG1: Moeder uit wie het kind is geboren heeft alleen het gezag (1:253b lid 1 BW)

  Hoe achterhalen: IBSN Ouder1 en Ouder2 op persoonslijst kind zijn beiden gevuld. Als bij beide ouders in categorie 05
  Huwelijk/geregistreerd partnerschap het BSN van de andere ouder voorkomt en datum huwelijkssluiting/aangaan geregistreerd
  partnerschap komt actueel voor dan is sprake van een niet ontbonden huwelijk/geregistreerd partnerschap. Komt de datum ontbinding
  huwelijk/geregistreerd partnerschap actueel voor dan is sprake van een ontbonden huwelijk/geregistreerd partnerschap. Aan de hand van
  de reden huwelijksontbinding kan nagegaan worden of sprake is van een scheiding (waarde S).
  
  Groep 06 (Huwelijkssluiting/aangaan geregistreerd partnerschap) en groep 07 (Ontbinding huwelijk/geregistreerd partnerschap) bestaan uit
  meerdere elementen die allen verplicht voorkomen als de groep voorkomt. Het is voldoende om op het voorkomen van één van deze elementen te
  testen. Als het huwelijk van de ouders ontbonden is door overlijden van één van de ouders dan is bij de overleden ouder groep 06 (Sluiting
  huwelijk/geregistreerd partnerschap) opgenomen en bij de weduwe/weduwnaar groep 07 (Ontbinding huwelijk/geregistreerd partnerschap).
  
  Opmerkingen
  Er zijn meerdere oorzaken vormen van ontbinding:
    • Overlijden
    • Nietig verklaring
    • Scheiding

  Bij al deze vormen van ontbinding blijft de relatie met het kind onveranderd, om de vraagstelling en het verloop van de beslissingen in
  het stroomschema cognitief volgbaar te houden is een voorstel ingediend om het antwoord “Nee, gescheiden (van elkaar) na geboorte
  kind” te vervangen door “huwelijk/partnerschap ouders nietig verklaard, dan wel ontbonden door echtscheiding/ontbinding, of
  overlijden van een ouder na geboorte kind”
    • De bepaling of beide juridische ouders nu met elkaar gehuwd/partners zijn kan daarmee scherper gespecificeerd worden. Denk
      hier ook aan de antwoorden die de specificatie verwacht. Hiertoe zullen ook elementen nodig zijn die nu nog niet gespecificeerd
      zijn.

  Gebruikte velden:
    - BSN ouder 1 -> 02.01.20 
    - BSN ouder 2 -> 03.01.20
    - BSN in huwelijk/geregistreerd partnerschap -> 05.01.20
    - Datum huwelijkssluiting/aangaan geregistreerd partnerschap -> 05.06.10
    - Datum ontbinding huwelijk/geregistreerd partnerschap -> 05.07.10
    - Reden huwelijksontbinding -> 05.07.40

    Achtergrond:
      Gegeven de persoon 'Rianne' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Richard' met burgerservicenummer '000000024'
      * is meerderjarig
      En de persoon 'Robin' met burgerservicenummer '000000036'
      * is geboren op 1-12-2022
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * heeft 'Rianne' als ouder 1 
      * heeft 'Richard' als ouder 2

  Regel: Ouders zijn nooit met elkaar gehuwd of geregistreerd partners geweest

    Scenario: Geen sprake van huwelijk of partnerschap, officeel is er sprake van EenhoofdigOuderlijkGezag maar 
              er kan op dit moment niet worden bepaald welke ouder de geboortemoeder (route 54) is er is sprake van GezagNietTeBepalen
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
#      En heeft de persoon een 'gezag' met de volgende gegevens
#      | naam                             | waarde                    |
#      | type                             | EenhoofdigOuderlijkGezag  |
#      | minderjarige.burgerservicenummer | 000000036                 |
#      | ouder.burgerservicenummer        | 000000012                 |
#      En heeft de persoon een 'gezag' met de volgende gegevens
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                               |
      | type        | GezagNietTeBepalen                                                                                                   |
      | toelichting | gezag kan niet worden bepaald omdat niet kan worden vastgesteld welke ouder de geboortemoeder is.                    |

  Regel: Ouders zijn getrouwd geweest voor het kind is geboren

    Scenario: Huwelijk / partnerschap van ouders is beëindigd door scheiding er is sprake van TweehoofdigOuderlijkGezag
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20100401                                                           |
      Gegeven 'Rianne' en 'Richard' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | 20210101                                                     |
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
      | burgerservicenummer |
      | 000000012           |
      En heeft het 'gezag' nog een 'ouder' met de volgende gegevens
      | burgerservicenummer |
      | 000000024           |

    Scenario: Huwelijk / partnerschap van ouders is nietig verklaard er is sprake van TweehoofdigOuderlijkGezag
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20100401                                                           |
      Gegeven 'Rianne' en 'Richard' zijn gescheiden met de volgende gegevens
      | naam                                                         | waarde    |
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) | 20210101  |
      | reden ontbinding huwelijk/geregistreerd partnerschap (07.40) | N         |
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
      | burgerservicenummer |
      | 000000012           |
      En heeft het 'gezag' nog een 'ouder' met de volgende gegevens
      | burgerservicenummer |
      | 000000024           |

    Scenario: Huwelijk / partnerschap van ouders is beëindigd door overlijden van vader er is sprake van EenhoofdigOuderlijkGezag
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20100401                                                           |
      En 'Richard' is overleden met de volgende gegevens
      | datum overlijden (08.10)                                     |
      | 01012021                                                     |
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

  Regel: Ouders zijn getrouwd geweest en gescheiden na de geboorte van het kind

    Scenario: Huwelijk / partnerschap van ouders is beëindigd door scheiding er is sprake van TweehoofdigOuderlijkGezag
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20100401                                                           |
      Gegeven 'Rianne' en 'Richard' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | 20230101                                                     |
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
      | burgerservicenummer |
      | 000000012           |
      En heeft het 'gezag' nog een 'ouder' met de volgende gegevens
      | burgerservicenummer |
      | 000000024           |

    Scenario: Huwelijk / partnerschap van ouders is nietig verklaard er is sprake van TweehoofdigOuderlijkGezag
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20100401                                                           |
      Gegeven 'Rianne' en 'Richard' zijn gescheiden met de volgende gegevens
      | naam                                                         | waarde    |
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) | 20230101  |
      | reden ontbinding huwelijk/geregistreerd partnerschap (07.40) | N         |
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
      | burgerservicenummer |
      | 000000012           |
      En heeft het 'gezag' nog een 'ouder' met de volgende gegevens
      | burgerservicenummer |
      | 000000024           |

    Scenario: Huwelijk / partnerschap van ouders is beëindigd door overlijden van vader er is sprake van EenhoofdigOuderlijkGezag
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens 
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20100401                                                           |
      En 'Richard' is overleden met de volgende gegevens
      | datum overlijden (08.10)                                     |
      | 01012023                                                     |
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

  Regel: Ouders zijn altijd en nu nog getrouwd geweest

    Scenario: Huwelijk / partnerschap van ouders is actueel er is sprake van TweehoofdigOuderlijkGezag
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20100401                                                           |
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
      | burgerservicenummer |
      | 000000012           |
      En heeft het 'gezag' nog een 'ouder' met de volgende gegevens
      | burgerservicenummer |
      | 000000024           |

  Regel: Ouders waren getrouwd, zijn gescheiden en daarna hertrouwd

    Scenario: Kind is geboren tijdens het eerste huwelijk er is sprake van TweehoofdigOuderlijkGezag
      Gegeven persoon 'Robin'
      * is geboren op 06-06-2020
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20200401                                                           |
      Gegeven 'Rianne' en 'Richard' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | 20210101                                                     |
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20220401                                                           |
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
      | burgerservicenummer |
      | 000000012           |
      En heeft het 'gezag' nog een 'ouder' met de volgende gegevens
      | burgerservicenummer |
      | 000000024           |

    Scenario: Kind is geboren terwijl de ouders gescheiden waren er is sprake van TweehoofdigOuderlijkGezag
      Gegeven persoon 'Robin'
      * is geboren op 06-06-2021
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20200401                                                           |
      Gegeven 'Rianne' en 'Richard' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | 20210101                                                     |
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20220401                                                           |
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
      | burgerservicenummer |
      | 000000012           |
      En heeft het 'gezag' nog een 'ouder' met de volgende gegevens
      | burgerservicenummer |
      | 000000024           |

    Scenario: Kind is geboren terwijl het huwelijk van de ouders nietig was verklaard er is sprake van TweehoofdigOuderlijkGezag
      Gegeven persoon 'Robin'
      * is geboren op 06-06-2021
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20200401                                                           |
      Gegeven 'Rianne' en 'Richard' zijn gescheiden met de volgende gegevens
      | naam                                                         | waarde    |
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) | 20210101  |
      | reden ontbinding huwelijk/geregistreerd partnerschap (07.40) | N         |
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20220401                                                           |
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
      | burgerservicenummer |
      | 000000012           |
      En heeft het 'gezag' nog een 'ouder' met de volgende gegevens
      | burgerservicenummer |
      | 000000024           |

    Scenario: Kind is geboren tijdens het tweede (herstel) huwelijk van de ouders nadat ouders waren gescheiden er is sprake van TweehoofdigOuderlijkGezag
      Gegeven persoon 'Robin'
      * is geboren op 06-06-2022
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20200401                                                           |
      Gegeven 'Rianne' en 'Richard' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | 20210101                                                     |
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20220401                                                           |
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
      | burgerservicenummer |
      | 000000012           |
      En heeft het 'gezag' nog een 'ouder' met de volgende gegevens
      | burgerservicenummer |
      | 000000024           |

    Scenario: Kind is geboren tijdens het tweede (herstel) huwelijk van de ouders nadat het huwelijk was nietig verklaard er is sprake van TweehoofdigOuderlijkGezag
      Gegeven persoon 'Robin'
      * is geboren op 06-06-2022
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20200401                                                           |
      Gegeven 'Rianne' en 'Richard' zijn gescheiden met de volgende gegevens
      | naam                                                         | waarde    |
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) | 20210101  |
      | reden ontbinding huwelijk/geregistreerd partnerschap (07.40) | N         |
      Gegeven 'Rianne' en 'Richard' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20220401                                                           |
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
      | burgerservicenummer |
      | 000000012           |
      En heeft het 'gezag' nog een 'ouder' met de volgende gegevens
      | burgerservicenummer |
      | 000000024           |
