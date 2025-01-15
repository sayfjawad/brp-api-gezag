#language: nl

Functionaliteit: 4b.1 - Ouder of partner overleden of onbevoegd tot gezag
  Precondities voor deze vraag:
  | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                                       |
  | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                                                       |
  | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                                                      |
  | 1.3a Is minderjarige in buitenland geboren?                            | Nee of geadopteerd met NL akte                                           |
  | 1.4 Is uitspraak gezag aanwezig                                        | Nee                                                                      |
  | 2.1 Hoeveel juridische ouders heeft minderjarigen                      | Een_ouder                                                                |
  | 2b.1 is staande huwelijk of partnerschap geboren                       | Ja                                                                       |

  Onbevoegd tot het gezag zijn minderjarigen, zij die onder curatele zijn gesteld en zij wier geestvermogens zodanig zijn gestoord, om het
  gezag uit te kunnen oefenen (1:246 BW). Daarnaast zijn overleden ouders ook onbevoegd tot het gezag.

  Mogelijke uitkomsten:
  1) Nee, de ouder en partner zijn beiden niet overleden en bevoegd tot gezag
  -> Gezamenlijk gezag: Ouder en echtgenoot/partner hebben gezamenlijk het gezag (1:253sa BW)
  a. meemoeders: kind tijdens huwelijk of geregistreerd partnerschap geboren, niet erkend en er is geen sprake van een verklaring onbekende donor
  b. kind is vóór 1 april 2014 geboren tijdens geregistreerd partnerschap van paren verschillend geslacht, kind is niet erkend (Zie 2b.1 onder 1)
  2) Ja, de partner is overleden of onbevoegd tot gezag
  -> De ouder heeft alleen het gezag (1:253f BW)
  3) Ja, de ouder van het kind is overleden of onbevoegd tot gezag
  -> De partner heeft als voogd het gezag (1:253x BW).
  4) Ja, beide ouders
  -> Er is tijdelijk geen gezag (1:253g lid 1 BW).

  Let op: De kinderrechter kan een minderjarige moeder o.b.v. artikel 1:253ha lid 1 BW meerderjarig verklaren. Dit is nu niet in de BRP te achterhalen,
  dit is wel zichtbaar als aantekening in het Gezagsregister.

  Hoe achterhalen:
  Niet overleden: op de persoonslijst van de ouder(s) mag de datum opschorting bijhouding niet voorkomen.
  Meerderjarig: de geboortedatum van de ouder(s) moet 18 jaar of hoger zijn.
  Niet onder curatele: in de gezagsverhouding op de persoonslijst van de ouder(s) mag de indicatie curatele niet voorkomen.

  Het wordt in de BRP niet geregistreerd dat de geestelijke vermogens van een ouder gestoord zijn.

  Aanbeveling
  • Meerderjarig verklaring: ophalen meerderjarig verklaring uit de aantekening in het Gezagsregister

  Gebruikte velden:
  - Burgerservicenummer ouder 1 -> 02.01.20
  - Burgerservicenummer ouder 2 -> 03.01.20
  - Geboortedatum persoon (PL ouder 1) -> 01.03.10
  - Geboortedatum persoon (PL ouder 2) -> 01.03.10
  - Burgerservicenummer huwelijk/geregistreerd partnerschap (PL ouder 1) -> 05.01.20
  - Burgerservicenummer huwelijk/geregistreerd partnerschap (PL ouder 2) -> 05.01.20
  - Start datum huwelijk/geregistreerd partnerschap (PL ouder 1) -> 05.06.10
  - Start datum huwelijk/geregistreerd partnerschap (PL ouder 2) -> 05.06.10
  - Eind datum huwelijk/geregistreerd partnerschap (PL ouder 1) -> 05.07.10
  - Eind datum huwelijk/geregistreerd partnerschap (PL ouder 2) -> 05.07.10
  - Eind reden huwelijk/geregistreerd partnerschap (PL ouder 1) -> 05.07.40
  - Eind reden huwelijk/geregistreerd partnerschap (PL ouder 2) -> 05.07.40
  - Datum opschorting bijhouding (PL ouder 1) -> 07.67.10
  - Datum opschorting bijhouding (PL ouder 2) -> 07.67.10
  - Indicatie curatele (PL ouder 1) -> 11.33.10
  - Indicatie curatele (PL ouder 2) -> 11.33.10

  Achtergrond:
    Gegeven de persoon 'Quincy' met burgerservicenummer '000000012'
    Gegeven de persoon 'Mees' met burgerservicenummer '000000036'
    Gegeven 'Quincy' en 'Mees' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20230101                                                           |
    En de persoon 'Maarten' met burgerservicenummer '000000024'
    * is geboren op 4-4-2023
    * is in Nederland geboren
    * is ingeschreven in de BRP
    * heeft 'Quincy' als ouder 1

  Regel: De ouder en partner zijn niet overleden, niet minderjarig en staan niet onder curatele

  Scenario: De moeder en de partner hebben gezag er is sprake van GezamenlijkGezag
    Gegeven persoon 'Quincy'
    * is meerderjarig
    En persoon 'Mees'
    * is meerderjarig
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde           |
      | type                             | GezamenlijkGezag |
      | minderjarige.burgerservicenummer | 000000024        |
      | ouder.burgerservicenummer        | 000000012        |
      | derde.burgerservicenummer        | 000000036        |

  Regel: De ouder of partner is overleden

  Scenario: De moeder is overleden er is sprake van Voogdij
    Gegeven persoon 'Quincy'
    * is meerderjarig
    * is overleden
    En persoon 'Mees'
    * is meerderjarig
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
      | burgerservicenummer |
      | 000000036           |

  Scenario: De partner is overleden er is sprake van EenhoofdigOuderlijkGezag
    En persoon 'Quincy'
    * is meerderjarig
    Gegeven persoon 'Mees'
    * is meerderjarig
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000024                |
      | ouder.burgerservicenummer        | 000000012                |

  Regel: Zowel de ouder als partner zijn overleden

  Scenario: De moeder en partner zijn overleden er is sprake van TijdelijkGeenGezag
    Gegeven persoon 'Quincy'
    * is meerderjarig
    * is overleden
    Gegeven persoon 'Mees'
    * is meerderjarig
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                  |
      | type                             | TijdelijkGeenGezag                                      |
      | minderjarige.burgerservicenummer | 000000024                                               |
      | toelichting                      | Tijdelijk geen gezag omdat beide ouders overleden zijn. |

  Regel: De ouder of partner is minderjarig

  Scenario: De moeder is minderjarig er is sprake van Voogdij
    Gegeven persoon 'Quincy'
    * is minderjarig
    En persoon 'Mees'
    * is meerderjarig
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
      | burgerservicenummer |
      | 000000036           |

  Scenario: De partner is minderjarig er is sprake van EenhoofdigOuderlijkGezag
    Gegeven persoon 'Quincy'
    * is meerderjarig
    En persoon 'Mees'
    * is minderjarig
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000024                |
      | ouder.burgerservicenummer        | 000000012                |

  Regel: Zowel de ouder als partner zijn minderjarig

  Scenario: De moeder en partner zijn minderjarig er is sprake van TijdelijkGeenGezag
    Gegeven persoon 'Quincy'
    * is minderjarig
    Gegeven persoon 'Mees'
    * is minderjarig
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                    |
      | type                             | TijdelijkGeenGezag                                        |
      | minderjarige.burgerservicenummer | 000000024                                                 |
      | toelichting                      | Tijdelijk geen gezag omdat beide ouders minderjarig zijn. |

  Regel: De ouder of partner staat onder curatele

  Scenario: De moeder is onder curatele er is sprake van Voogdij
    Gegeven persoon 'Quincy'
    * is meerderjarig
    * is onder curatele gesteld
    Gegeven persoon 'Mees'
    * is meerderjarig
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
      | burgerservicenummer |
      | 000000036           |

  Scenario: De partner is onder curatele er is sprake van EenhoofdigOuderlijkGezag
    Gegeven persoon 'Quincy'
    * is meerderjarig
    Gegeven persoon 'Mees'
    * is meerderjarig
    * is onder curatele gesteld
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000024                |
      | ouder.burgerservicenummer        | 000000012                |

  Regel: Zowel de ouder als partner zijn onder curatele

  Scenario: De moeder en partner zijn onder curatele er is sprake van TijdelijkGeenGezag
    Gegeven persoon 'Quincy'
    * is meerderjarig
    * is onder curatele gesteld
    Gegeven persoon 'Mees'
    * is meerderjarig
    * is onder curatele gesteld
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                        |
      | type                             | TijdelijkGeenGezag                                            |
      | minderjarige.burgerservicenummer | 000000024                                                     |
      | toelichting                      | Tijdelijk geen gezag omdat beide ouders onder curatele staan. |

  Regel: De ouder of partner is minderjarig en overleden

  Scenario: De moeder is minderjarig en overleden er is sprake van Voogdij
    Gegeven persoon 'Quincy'
    * is minderjarig
    * is overleden
    En persoon 'Mees'
    * is meerderjarig
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
      | burgerservicenummer |
      | 000000036           |

  Scenario: De partner is minderjarig en overleden er is sprake van EenhoofdigOuderlijkGezag
    Gegeven persoon 'Quincy'
    * is meerderjarig
    En persoon 'Mees'
    * is minderjarig
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000024                |
      | ouder.burgerservicenummer        | 000000012                |

  Regel: De ouder en partner zijn minderjarig en overleden

  Scenario: De moeder is minderjarig en overleden er is sprake van TijdelijkGeenGezag
    Gegeven persoon 'Quincy'
    * is minderjarig
    * is overleden
    Gegeven persoon 'Mees'
    * is minderjarig
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                  |
      | type                             | TijdelijkGeenGezag                                      |
      | minderjarige.burgerservicenummer | 000000024                                               |
      | toelichting                      | Tijdelijk geen gezag omdat beide ouders overleden zijn. |

  Regel: De ouder of partner is minderjarig en onder curatele

  Scenario: De moeder is minderjarig en overleden er is sprake van Voogdij
    Gegeven persoon 'Quincy'
    * is minderjarig
    * is onder curatele gesteld
    En persoon 'Mees'
    * is meerderjarig
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
      | burgerservicenummer |
      | 000000036           |

  Scenario: De partner is minderjarig en onder curatele er is sprake van EenhoofdigOuderlijkGezag
    Gegeven persoon 'Quincy'
    * is meerderjarig
    En persoon 'Mees'
    * is minderjarig
    * is onder curatele gesteld
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000024                |
      | ouder.burgerservicenummer        | 000000012                |

  Regel: De ouder en partner zijn minderjarig en onder curatele gesteld

  Scenario: De moeder is minderjarig en overleden er is sprake van TijdelijkGeenGezag
    Gegeven persoon 'Quincy'
    * is minderjarig
    * is onder curatele gesteld
    Gegeven persoon 'Mees'
    * is minderjarig
    * is onder curatele gesteld
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                        |
      | type                             | TijdelijkGeenGezag                                            |
      | minderjarige.burgerservicenummer | 000000024                                                     |
      | toelichting                      | Tijdelijk geen gezag omdat beide ouders onder curatele staan. |

  Regel: De ouder of partner is onder curatele en overleden

  Scenario: De moeder is minderjarig en overleden er is sprake van Voogdij
    Gegeven persoon 'Quincy'
    * is meerderjarig
    * is onder curatele gesteld
    * is overleden
    En persoon 'Mees'
    * is meerderjarig
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
      | burgerservicenummer |
      | 000000036           |

  Scenario: De partner is onder curatele en overleden er is sprake van EenhoofdigOuderlijkGezag
    Gegeven persoon 'Quincy'
    * is meerderjarig
    En persoon 'Mees'
    * is meerderjarig
    * is onder curatele gesteld
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000024                |
      | ouder.burgerservicenummer        | 000000012                |

  Regel: De ouder en partner zijn onder curatele gesteld en overleden

  Scenario: De moeder en partner zijn onder curatele en overleden er is sprake van TijdelijkGeenGezag
    Gegeven persoon 'Quincy'
    * is meerderjarig
    * is onder curatele gesteld
    * is overleden
    Gegeven persoon 'Mees'
    * is meerderjarig
    * is onder curatele gesteld
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                  |
      | type                             | TijdelijkGeenGezag                                      |
      | minderjarige.burgerservicenummer | 000000024                                               |
      | toelichting                      | Tijdelijk geen gezag omdat beide ouders overleden zijn. |

  Regel: De ouder of partner is minderjarig, onder curatele en overleden

  Scenario: De moeder is minderjarig, onder curatele en overleden er is sprake van Voogdij
    Gegeven persoon 'Quincy'
    * is minderjarig
    * is onder curatele gesteld
    * is overleden
    Gegeven persoon 'Mees'
    * is meerderjarig
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
      | burgerservicenummer |
      | 000000036           |

  Scenario: De partner is minderjarig, onder curatele en overleden er is sprake van EenhoofdigOuderlijkGezag
    Gegeven persoon 'Quincy'
    * is meerderjarig
    Gegeven persoon 'Mees'
    * is minderjarig
    * is onder curatele gesteld
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000024                |
      | ouder.burgerservicenummer        | 000000012                |

  Regel: De ouder en partner zijn minderjarig, onder curatele gesteld en overleden

  Scenario: De moeder is minderjarig, onder curatele en overleden er is sprake van TijdelijkGeenGezag
    Gegeven persoon 'Quincy'
    * is minderjarig
    * is onder curatele gesteld
    * is overleden
    Gegeven persoon 'Mees'
    * is minderjarig
    * is onder curatele gesteld
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                  |
      | type                             | TijdelijkGeenGezag                                      |
      | minderjarige.burgerservicenummer | 000000024                                               |
      | toelichting                      | Tijdelijk geen gezag omdat beide ouders overleden zijn. |
