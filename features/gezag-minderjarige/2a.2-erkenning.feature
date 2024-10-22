#language: nl

Functionaliteit: 2a.3 - erkenning
  # Precondities voor deze vraag:
  # | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                                                 |
  # | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                                                                 |
  # | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                                                                |
  # | 1.3a Is minderjarige in buitenland geboren?                            | Nee of geadopteerd met NL akte                                                     |
  # | 1.4 Uitspraak gezag aanwezig?                                          | Nee of door een recente gebeurtenis het gezag toch (weer) van rechtswege verkregen |
  # | 2.1 Hoeveel juridische ouders heeft de minderjarige?                   | 2                                                                                  |
  # | 2a.1 Zijn beide ouders nu met elkaar gehuwd of geregistreerd partners? | Nee nooit                                                                          |
  # | 2a.2 Is persoon geadopteerd met Nederlandse akte                       | Nee

  # Aktenummers zoals vastgelecht in de BRP en opgenomen in de lijst erkenningscodes uit de publieke tabel 39. 
  # Zie: https://publicaties.rvig.nl/Landelijke_tabellen/Landelijke_tabellen_32_t_m_61_excl_tabel_35/Landelijke_Tabellen_32_t_m_61_in_csv_formaat/Tabel_39_Akteaanduiding
  # Weergeven een feit over de persoon danwel relatie met andere personen.
  # ??

  # Hoe achterhalen of er erkenning is: Aktenummer Ouder1 of Ouder2 (02.81.20 of 03.81.20) bevat op de 3e positie de akteaanduiding B (erkenning bij
  # geboorteaangifte), C (erkenning), J (erkenning bij notariële akte) of V (gerechtelijke vaststelling ouderschap). Deze kan in de actuele categorie voorkomen maar ook 
  # in een historische categorie (52.81.20 of 53.81.20) omdat er later nog een ander rechtsfeit geregistreerd kan zijn. De datum van erkenning kan ontleend worden aan 
  # datum familierechtelijke betrekking (02.62.10 of 03.62.10) van dezelfde categorie als die waarin akteaanduiding B of C geconstateerd is. Kan dus ook in de
  # historie voorkomen (52.62.10 of 53.62.10).

  # Het bepalen of sprake is geweest van een erkenning ongeboren vrucht kan aan de hand van akteaanduiding A (01.81.20) terwijl de ouders op
  # moment geboorte niet met elkaar gehuwd zijn of een geregistreerd partnerschap hebben. Ook hierbij kan de akteaanduiding A in de historie
  # voorkomen (51.81.20). Vervolgens moet dan wel uitgesloten worden dat ook niet een categorie voorkomt waarin akteaanduiding B, C of J voorkomt,
  # want dat zou betekenen dat het kind later erkend is. Ook akteaanduiding Q moet uitgesloten worden want dat zou betekenen dat het kind later
  # geadopteerd is. Ook hier geldt actueel en historisch (01.81.20 en 51.81.20) Voor wat betreft personen geboren voor 1-1-23 is duidelijk dat de
  # erkenning ongeboren vrucht ook voor 1-1-23 geweest moet zijn, voor personen geboren na 1-1-23 is dat tot medio 10-2023 niet aan de BRP te
  # herleiden.

  # Wat betekend het wanneer een kind (of ongeboren vrucht) is erkend?
  # Indien het kind (of de ongeboren vrucht) is erkend voor 1-1-2023 is er sprake van 1 hoofdig gezag voor de moeder en volgt stap 4a Achterhalen bevoegdheid tot gezag (1 hoofdig gezag)
  # Indien het kind of de ongeboren vrucht is erkend na 1-1-2023 is er sprake van gezamenlijk ouderlijk gezag en volgt stap 4a Achterhalen bevoegdheid tot gezag (gezamenlijk ouderlijk gezag)

    Achtergrond:
      Gegeven de persoon 'Lotte' met burgerservicenummer '000000012'
      * is meerderjarig, niet overleden en staat niet onder curatele
      En de persoon 'Dirk' met burgerservicenummer '000000024'
      * is meerderjarig, niet overleden en staat niet onder curatele

  # voorbeeld als ouder 1 met gegevens:
  # * heeft 'Lotte' als ouder 1 met de volgende gegevens
  #    | naam                  | waarde            |
  #    | geboortedatum (03.10) | 20221201          |

  Regel: Kind is geboren voor 01-01-2023

    Scenario: Het kind is niet erkend er is sprake van EenhoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036'
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20221201          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
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

    Scenario: Eén van de ouders heeft het kind erkend met de geboorteaangifte er is sprake van EenhoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036'
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20221201          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning bij geboorteaangifte
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

    Scenario: Eén van de ouders heeft het kind erkend na de geboorteaangifte er is sprake van EenhoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20221201          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning na geboorteaangifte op 30-12-2022
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

    Scenario: Eén van de ouders heeft het kind erkend na de geboorteaangifte op 01-01-2023 er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20221201          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning na geboorteaangifte op 01-01-2023
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

    Scenario: Eén van de ouders heeft het kind erkend na de geboorteaangifte na 01-01-2023 er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20221201          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning na geboorteaangifte op 01-02-2023
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

    Scenario: Eén van de ouders heeft het kind met een notariële akte erkend er is sprake van EenhoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20221201          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning bij notariële akte op 30-12-2022
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

    Scenario: Eén van de ouders heeft het kind met een notariële akte erkend op 01-01-2023 er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20221201          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning bij notariële akte op 01-01-2023
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

    Scenario: Eén van de ouders heeft het kind met een notariële akte erkend na 01-01-2023 er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20221201          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning bij notariële akte op 26-01-2023
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

    Scenario: Er is een gerechtelijke vaststelling ouderschap door één van de ouders is sprake van EenhoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20221201          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met gerechtelijke vaststelling ouderschap op 30-12-2022
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

    Scenario: Er is een gerechtelijke vaststelling ouderschap door één van de ouders op 01-01-2023 er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20221201          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning bij notariële akte op 01-01-2023
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

    Scenario: Er is een gerechtelijke vaststelling ouderschap door één van de ouders na 01-01-2023 er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20221201          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning bij notariële akte op 03-03-2023
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

  Regel: Kind is geboren op 01-01-2023
  
    Scenario: Het kind is niet erkend er is sprake van EenhoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036'
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20230101          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
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

    Scenario: Eén van de ouders heeft het kind erkend met de geboorteaangifte er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20230101          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning bij geboorteaangifte
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

    Scenario: Eén van de ouders heeft het kind erkend na de geboorteaangifte  er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20230101          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning na geboorteaangifte op 29-01-2023
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

    Scenario: Eén van de ouders heeft het kind met een notariële akte erkend er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20230101          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning bij notariële akte op 28-01-2023
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

    Scenario: Er is een gerechtelijke vaststelling ouderschap door één van de ouders er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20230101          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met gerechtelijke vaststelling ouderschap op 01-02-2023
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

  Regel: Kind is geboren na 01-01-2023

    Scenario: Het kind is niet erkend er is sprake van EenhoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036'
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20230406          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
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

    Scenario: Eén van de ouders heeft het kind erkend met de geboorteaangifte er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20230406          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning bij geboorteaangifte
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

    Scenario: Eén van de ouders heeft het kind erkend na de geboorteaangifte  er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20230406          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning na geboorteaangifte op 29-01-2023
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

    Scenario: Eén van de ouders heeft het kind met een notariële akte erkend er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20230406          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met erkenning bij notariële akte op 28-01-2023
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

    Scenario: Er is een gerechtelijke vaststelling ouderschap door één van de ouders er is sprake van TweehoofdigOuderlijkGezag
      Gegeven de persoon 'Filip' met burgerservicenummer '000000036' 
      * heeft 'Lotte' als ouder 1 
      * heeft de volgende gegevens
      | naam                  | waarde            |
      | geboortedatum (03.10) | 20230406          |
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * is erkend door 'Dirk' als ouder 2 met gerechtelijke vaststelling ouderschap op 01-02-2023
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
