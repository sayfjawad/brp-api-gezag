#language: nl

Functionaliteit: Tg032 - Oostingh - Alleen maar minderjarigen incl. moeder


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
      | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
      | 518                  | Turfmarkt          | 71                 | 0518200000583566                           |
    
    # Lg01_173 - minderjarige moeder, gezag niet te bepalen BSN moeder ontbreekt
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Olga             |
      | geslachtsnaam (02.40)           | Oostingh         |
      | geboortedatum (03.10)           | morgen - 18 jaar |
      | geboorteland (03.30)            | 6030             |
      | geslachtsaanduiding (04.10)     | V                |
      | aktenummer (81.20)              | 1AA0001          |
      | ingangsdatum geldigheid (85.10) | morgen - 18 jaar |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde           |
      | aktenummer (81.20)              | 1AA0001          |
      | ingangsdatum geldigheid (85.10) | morgen - 18 jaar |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | voornamen (02.10)                                  | Ophelia          |
      | geslachtsnaam (02.40)                              | Oostingh         |
      | geboortedatum (03.10)                              | 19901201         |
      | geboorteland (03.30)                               | 6030             |
      | geslachtsaanduiding (04.10)                        | V                |
      | aktenummer (81.20)                                 | 1AA0001          |
      | ingangsdatum geldigheid (85.10)                    | morgen - 18 jaar |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 18 jaar |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde           |
      | gemeente van inschrijving (09.10) | 518              |
      | ingangsdatum geldigheid (85.10)   | morgen - 18 jaar |
    En de persoon heeft een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000036 |
      | voornamen (02.10)               | Onne      |
      | geslachtsnaam (02.40)           | Oostingh  |
      | geboortedatum (03.10)           | 20220202  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0011   |
      | ingangsdatum geldigheid (85.10) | 20220202  |
    En de persoon heeft nog een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000024 |
      | voornamen (02.10)               | Olle      |
      | geslachtsnaam (02.40)           | Oostingh  |
      | geboortedatum (03.10)           | 20220202  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0010   |
      | ingangsdatum geldigheid (85.10) | 20220202  |
    
    # Lg01_174 - minderjarige moeder, geen categorie 11
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Olle     |
      | geslachtsnaam (02.40)           | Oostingh |
      | geboortedatum (03.10)           | 20220202 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | M        |
      | aktenummer (81.20)              | 1AA0010  |
      | ingangsdatum geldigheid (85.10) | 20220202 |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde   |
      | aktenummer (81.20)              | 1AA0010  |
      | ingangsdatum geldigheid (85.10) | 20220202 |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000012        |
      | voornamen (02.10)                                  | Olga             |
      | geslachtsnaam (02.40)                              | Oostingh         |
      | geboortedatum (03.10)                              | morgen - 18 jaar |
      | geboorteland (03.30)                               | 6030             |
      | geslachtsaanduiding (04.10)                        | V                |
      | aktenummer (81.20)                                 | 1AA0010          |
      | ingangsdatum geldigheid (85.10)                    | 20220202         |
      | datum ingang familierechtelijke betrekking (62.10) | 20220202         |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20220202 |
    
    # Lg01_175 - minderjarige moeder, wel categorie 11
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Onne     |
      | geslachtsnaam (02.40)           | Oostingh |
      | geboortedatum (03.10)           | 20220202 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | M        |
      | aktenummer (81.20)              | 1AA0011  |
      | ingangsdatum geldigheid (85.10) | 20220202 |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde   |
      | aktenummer (81.20)              | 1AA0011  |
      | ingangsdatum geldigheid (85.10) | 20220202 |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000012        |
      | voornamen (02.10)                                  | Olga             |
      | geslachtsnaam (02.40)                              | Oostingh         |
      | geboortedatum (03.10)                              | morgen - 18 jaar |
      | geboorteland (03.30)                               | 6030             |
      | geslachtsaanduiding (04.10)                        | V                |
      | aktenummer (81.20)                                 | 1AA0011          |
      | ingangsdatum geldigheid (85.10)                    | 20220202         |
      | datum ingang familierechtelijke betrekking (62.10) | 20220202         |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20220202 |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde                      |
      | indicatie gezag minderjarige (32.10) | 1D                          |
      | beschrijving document (82.30)        | kennisgeving gezagsregister |
      | ingangsdatum geldigheid (85.10)      | 20221101                    |


  Scenario: Lg01_173 - minderjarige moeder, gezag niet te bepalen BSN moeder ontbreekt
    # Route: 39e - Wie heeft gezag?: exception (BSN moeder ontbreekt) (N)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                                                                                                                             |
      | type        | GezagNietTeBepalen                                                                                                                                                                                                 |
      | toelichting | gezag is niet te bepalen omdat bij het bepalen van huwelijk/partnerschap van de ouder(s) relevante gegevens ontbreken. Het gaat om de volgende gegevens: ouder1 van bevraagde persoon is niet in BRP geregistreerd |

  Scenario: Lg01_174 - minderjarige moeder, geen categorie 11
    # Route: 39 - Wie heeft gezag?: tijdelijk geen gezag (G)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam | waarde             |
      | type | TijdelijkGeenGezag |

  Scenario: Lg01_175 - minderjarige moeder, wel categorie 11
    # Route: 4 - Wie heeft gezag?: moeder en derde (V)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000036 |
    En heeft 'gezag' geen derden
