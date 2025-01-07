#language: nl

Functionaliteit: 4a.3 - Ouder overleden of onbevoegd tot gezag
  Precondities voor deze vraag:
  | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                                       |
  | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                                                       |
  | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                                                      |
  | 1.3a Is minderjarige in buitenland geboren?                            | Nee of geadopteerd met NL akte                                           |
  | 1.4 Is uitspraak gezag aanwezig                                        | Nee                                                                      |
  | 2.1 Hoeveel juridische ouders heeft minderjarigen                      | Een_ouder                                                                |
  | 2b.1 is staande huwelijk of partnerschap geboren                       | Nee                                                                      |

  Onbevoegd tot het gezag zijn minderjarigen, zij die onder curatele zijn gesteld en zij wier geestvermogens zodanig zijn gestoord, om het
  gezag uit te kunnen oefenen (1:246 BW). Daarnaast zijn overleden ouders ook onbevoegd tot het gezag.

  Mogelijke uitkomsten:
  1) Nee
  -> OG1: De ouder heeft alleen het gezag
  2) Ja:
  -> G: Er is (tijdelijk) geen gezag (1:253g lid 1 BW bij overlijden of 1:253q lid 2 BW bij onbevoegdheid)

  Op het moment dat de moeder weer bevoegd wordt tot het gezag, verkrijgt zij van rechtswege het gezag, tenzij op dat tijdstip een ander met het
  gezag is belast (art. 1:253b lid 2 BW). Dit geldt ook o.g.v. art. 1:253q lid 1 BW.
  Let op: De kinderrechter kan een minderjarige moeder o.b.v. artikel 1:253ha lid 1 BW meerderjarig verklaren. Dit is nu niet in de BRP te achterhalen,
  dit is wel zichtbaar als aantekening in het Gezagsregister.

  Hoe achterhalen:
  Niet overleden: op de persoonslijst van de ouder(s) mag de datum opschorting bijhouding niet voorkomen.
  Meerderjarig: de geboortedatum van de ouder(s) moet 18 jaar of hoger zijn.
  Niet onder curatele: op de persoonslijst van de ouder(s) mag de indicatie curatele niet voorkomen.

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
    Gegeven de persoon 'Lieke' met burgerservicenummer '000000012'
    * is meerderjarig
    En de persoon 'Ronald' met burgerservicenummer '000000024'
    * is minderjarig
    * is in Nederland geboren
    * is ingeschreven in de BRP
    * heeft 'Lieke' als ouder 1

    Gegeven de persoon 'Rik' met burgerservicenummer '000000036'
    * is meerderjarig
    En de persoon 'Thea' met burgerservicenummer '000000061'
    * is minderjarig
    * is in Nederland geboren
    * is ingeschreven in de BRP
    * heeft 'Rik' als ouder 2

  Regel: De ouder is niet overleden, minderjarig of onder curatele

  Scenario: De moeder (ouder 1 van 'Ronald') is niet overleden, minderjarig of onder curatele er is sprake van EenhoofdigOuderlijkGezag
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

  Scenario: De vader (ouder 2 van 'Thea') is niet overleden, minderjarig of onder curatele er is sprake van EenhoofdigOuderlijkGezag
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000061                |
      | ouder.burgerservicenummer        | 000000036                |

  Regel: De ouder is overleden

  Scenario: De moeder (ouder 1 van 'Ronald') is overleden er is TijdelijkGeenGezag
    Gegeven persoon 'Lieke'
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                            |
      | type                             | TijdelijkGeenGezag                                |
      | minderjarige.burgerservicenummer | 000000024                                         |
      | toelichting                      | Tijdelijk geen gezag omdat de ouder overleden is. |

  Scenario: De vader (ouder 2 van 'Thea') is overleden er is TijdelijkGeenGezag
    Gegeven persoon 'Rik'
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                            |
      | type                             | TijdelijkGeenGezag                                |
      | minderjarige.burgerservicenummer | 000000061                                         |
      | toelichting                      | Tijdelijk geen gezag omdat de ouder overleden is. |

  Regel: De ouder is minderjarig

  Scenario: De moeder (ouder 1 van 'Ronald') is minderjarig er is TijdelijkGeenGezag
    Gegeven persoon 'Lieke'
    * is minderjarig
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                              |
      | type                             | TijdelijkGeenGezag                                  |
      | minderjarige.burgerservicenummer | 000000024                                           |
      | toelichting                      | Tijdelijk geen gezag omdat de ouder minderjarig is. |

  Scenario: De vader (ouder 2 van 'Thea') is minderjarig er is TijdelijkGeenGezag
    Gegeven persoon 'Rik'
    * is minderjarig
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                              |
      | type                             | TijdelijkGeenGezag                                  |
      | minderjarige.burgerservicenummer | 000000061                                           |
      | toelichting                      | Tijdelijk geen gezag omdat de ouder minderjarig is. |

  Regel: De ouder is onder curatele gesteld

  Scenario: De moeder (ouder 1 van 'Ronald') is onder curatele er is TijdelijkGeenGezag
    Gegeven persoon 'Lieke'
    * is onder curatele gesteld
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
      | toelichting                      | Tijdelijk geen gezag omdat de ouder onder curatele staat. |

  Scenario: De vader (ouder 2 van 'Thea') is onder curatele er is TijdelijkGeenGezag
    Gegeven persoon 'Rik'
    * is onder curatele gesteld
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                    |
      | type                             | TijdelijkGeenGezag                                        |
      | minderjarige.burgerservicenummer | 000000061                                                 |
      | toelichting                      | Tijdelijk geen gezag omdat de ouder onder curatele staat. |

  Regel: De ouder is als minderjarige overleden

  Scenario: De moeder (ouder 1 van 'Ronald') is als minderjarige overleden er is TijdelijkGeenGezag
    Gegeven persoon 'Lieke'
    * is minderjarig
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                            |
      | type                             | TijdelijkGeenGezag                                |
      | minderjarige.burgerservicenummer | 000000024                                         |
      | toelichting                      | Tijdelijk geen gezag omdat de ouder overleden is. |

  Scenario: De vader (ouder 2 van 'Thea') is als minderjarige overleden er is TijdelijkGeenGezag
    Gegeven persoon 'Rik'
    * is minderjarig
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                            |
      | type                             | TijdelijkGeenGezag                                |
      | minderjarige.burgerservicenummer | 000000061                                         |
      | toelichting                      | Tijdelijk geen gezag omdat de ouder overleden is. |

  Regel: De ouder is onder curatele gesteld en overleden

  Scenario: De moeder (ouder 1 van 'Ronald') is onder curatele en overleden er is TijdelijkGeenGezag
    Gegeven persoon 'Lieke'
    * is minderjarig
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                            |
      | type                             | TijdelijkGeenGezag                                |
      | minderjarige.burgerservicenummer | 000000024                                         |
      | toelichting                      | Tijdelijk geen gezag omdat de ouder overleden is. |

  Scenario: De vader (ouder 2 van 'Thea') is onder curatele en overleden er is TijdelijkGeenGezag
    Gegeven persoon 'Rik'
    * is minderjarig
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                            |
      | type                             | TijdelijkGeenGezag                                |
      | minderjarige.burgerservicenummer | 000000061                                         |
      | toelichting                      | Tijdelijk geen gezag omdat de ouder overleden is. |

  Regel: De ouder is onder curatele gesteld, overleden en minderjarig

  Scenario: De moeder (ouder 1 van 'Ronald') is onder curatele, overleden en minderjarig er is TijdelijkGeenGezag
    Gegeven persoon 'Lieke'
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
      | naam                             | waarde                                            |
      | type                             | TijdelijkGeenGezag                                |
      | minderjarige.burgerservicenummer | 000000024                                         |
      | toelichting                      | Tijdelijk geen gezag omdat de ouder overleden is. |

  Scenario: De vader (ouder 2 van 'Thea') is onder curatele, overleden en minderjarig er is TijdelijkGeenGezag
    Gegeven persoon 'Rik'
    * is minderjarig
    * is onder curatele gesteld
    * is overleden
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                            |
      | type                             | TijdelijkGeenGezag                                |
      | minderjarige.burgerservicenummer | 000000061                                         |
      | toelichting                      | Tijdelijk geen gezag omdat de ouder overleden is. |
