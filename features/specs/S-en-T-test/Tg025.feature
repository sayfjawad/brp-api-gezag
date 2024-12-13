#language: nl

Functionaliteit: Tg025 - Geus-Goverwelle - Gegevens in onderzoek


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
      | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
      | 518                  | Turfmarkt          | 55                 | 0518200000583558                           |
    
    # Lg01_134 - gehuwd, kinderen, in onderzoek 010300, 020000, 030310, 050610, 089999, 090000, 090300
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Govert   |
      | voorvoegsel (02.30)             | de       |
      | geslachtsnaam (02.40)           | Geus     |
      | geboortedatum (03.10)           | 19841002 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | M        |
      | beschrijving document (82.30)   | PKA      |
      | aanduiding in onderzoek (83.10) | 10300    |
      | datum ingang onderzoek (83.20)  | 20220825 |
      | ingangsdatum geldigheid (85.10) | 0        |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde   |
      | voornamen (02.10)                                  | Geurtje  |
      | voorvoegsel (02.30)                                | van der  |
      | geslachtsnaam (02.40)                              | Plas     |
      | geboortedatum (03.10)                              | 19120901 |
      | geboorteland (03.30)                               | 0        |
      | geslachtsaanduiding (04.10)                        | V        |
      | beschrijving document (82.30)                      | PK       |
      | aanduiding in onderzoek (83.10)                    | 20000    |
      | datum ingang onderzoek (83.20)                     | 20220825 |
      | ingangsdatum geldigheid (85.10)                    | 0        |
      | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde   |
      | voornamen (02.10)                                  | Gerrit   |
      | voorvoegsel (02.30)                                | de       |
      | geslachtsnaam (02.40)                              | Geus     |
      | geboortedatum (03.10)                              | 19100823 |
      | geboorteland (03.30)                               | 6030     |
      | geslachtsaanduiding (04.10)                        | M        |
      | beschrijving document (82.30)                      | PK       |
      | aanduiding in onderzoek (83.10)                    | 30310    |
      | datum ingang onderzoek (83.20)                     | 20220825 |
      | ingangsdatum geldigheid (85.10)                    | 0        |
      | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                                | waarde     |
      | burgerservicenummer (01.20)                                         | 000000024  |
      | voornamen (02.10)                                                   | Grada      |
      | geslachtsnaam (02.40)                                               | Goverwelle |
      | geboortedatum (03.10)                                               | 19890416   |
      | geboorteland (03.30)                                                | 6030       |
      | geslachtsaanduiding (04.10)                                         | V          |
      | aktenummer (81.20)                                                  | 3AA0102    |
      | aanduiding in onderzoek (83.10)                                     | 50610      |
      | datum ingang onderzoek (83.20)                                      | 20220825   |
      | ingangsdatum geldigheid (85.10)                                     | 20051203   |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20051203   |
      | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
      | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | aanduiding in onderzoek (83.10)   | 89999    |
      | datum ingang onderzoek (83.20)    | 20220825 |
      | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000036 |
      | voornamen (02.10)               | Gerdien   |
      | voorvoegsel (02.30)             | de        |
      | geslachtsnaam (02.40)           | Geus      |
      | geboortedatum (03.10)           | 20160607  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0107   |
      | ingangsdatum geldigheid (85.10) | 20160607  |
    En de persoon heeft nog een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000048 |
      | voornamen (02.10)               | Gijs      |
      | geslachtsnaam (02.40)           | Geus      |
      | geboortedatum (03.10)           | 20191205  |
      | geboorteland (03.30)            | 0         |
      | beschrijving document (82.30)   | ga 5010   |
      | aanduiding in onderzoek (83.10) | 90300     |
      | datum ingang onderzoek (83.20)  | 20220825  |
      | ingangsdatum geldigheid (85.10) | 20191205  |
    En de persoon heeft nog een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000061 |
      | voornamen (02.10)               | Gloria    |
      | voorvoegsel (02.30)             | de        |
      | geslachtsnaam (02.40)           | Geus      |
      | geboortedatum (03.10)           | 20211012  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0108   |
      | aanduiding in onderzoek (83.10) | 90000     |
      | datum ingang onderzoek (83.20)  | 20220825  |
      | ingangsdatum geldigheid (85.10) | 20211012  |
    
    # Lg01_135 - gehuwd, kinderen, in onderzoek 026210, 030000, 050200, 080000, 090330
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                            | waarde     |
      | voornamen (02.10)               | Grada      |
      | geslachtsnaam (02.40)           | Goverwelle |
      | geboortedatum (03.10)           | 19890416   |
      | geboorteland (03.30)            | 6030       |
      | geslachtsaanduiding (04.10)     | V          |
      | beschrijving document (82.30)   | PKA        |
      | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde     |
      | voornamen (02.10)                                  | Graciala   |
      | geslachtsnaam (02.40)                              | Goverwelle |
      | geboortedatum (03.10)                              | 19640223   |
      | geboorteland (03.30)                               | 6030       |
      | geslachtsaanduiding (04.10)                        | V          |
      | beschrijving document (82.30)                      | PK         |
      | aanduiding in onderzoek (83.10)                    | 26210      |
      | datum ingang onderzoek (83.20)                     | 20220825   |
      | ingangsdatum geldigheid (85.10)                    | 0          |
      | datum ingang familierechtelijke betrekking (62.10) | 0          |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde   |
      | beschrijving document (82.30)   | PK       |
      | aanduiding in onderzoek (83.10) | 30000    |
      | datum ingang onderzoek (83.20)  | 20220825 |
      | ingangsdatum geldigheid (85.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                                | waarde    |
      | burgerservicenummer (01.20)                                         | 000000012 |
      | voornamen (02.10)                                                   | Govert    |
      | voorvoegsel (02.30)                                                 | de        |
      | geslachtsnaam (02.40)                                               | Geus      |
      | geboortedatum (03.10)                                               | 19840210  |
      | geboorteland (03.30)                                                | 6030      |
      | geslachtsaanduiding (04.10)                                         | M         |
      | aktenummer (81.20)                                                  | 3AA0102   |
      | aanduiding in onderzoek (83.10)                                     | 50200     |
      | datum ingang onderzoek (83.20)                                      | 20220825  |
      | ingangsdatum geldigheid (85.10)                                     | 20151203  |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20151203  |
      | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518      |
      | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030      |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | aanduiding in onderzoek (83.10)   | 80000    |
      | datum ingang onderzoek (83.20)    | 20220825 |
      | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000036 |
      | voornamen (02.10)               | Gerdien   |
      | voorvoegsel (02.30)             | de        |
      | geslachtsnaam (02.40)           | Geus      |
      | geboortedatum (03.10)           | 20160607  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0107   |
      | ingangsdatum geldigheid (85.10) | 20160607  |
    En de persoon heeft nog een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000048 |
      | voornamen (02.10)               | Gijs      |
      | geslachtsnaam (02.40)           | Geus      |
      | geboortedatum (03.10)           | 20191205  |
      | geboorteland (03.30)            | 0         |
      | beschrijving document (82.30)   | ga 5010   |
      | aanduiding in onderzoek (83.10) | 90330     |
      | datum ingang onderzoek (83.20)  | 20220825  |
      | ingangsdatum geldigheid (85.10) | 20191205  |
    En de persoon heeft nog een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000061 |
      | voornamen (02.10)               | Gloria    |
      | voorvoegsel (02.30)             | de        |
      | geslachtsnaam (02.40)           | Geus      |
      | geboortedatum (03.10)           | 20211012  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0108   |
      | ingangsdatum geldigheid (85.10) | 20211012  |
    
    # Lg01_136 - kind geboren tijdens huwelijk, in onderzoek 010310, 026210, 030210, 080920, 613210
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Gerdien  |
      | voorvoegsel (02.30)             | de       |
      | geslachtsnaam (02.40)           | Geus     |
      | geboortedatum (03.10)           | 20160607 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | V        |
      | aktenummer (81.20)              | 1AA0107  |
      | aanduiding in onderzoek (83.10) | 10310    |
      | datum ingang onderzoek (83.20)  | 20220825 |
      | ingangsdatum geldigheid (85.10) | 20160607 |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde    |
      | burgerservicenummer (01.20)                        | 000000012 |
      | voornamen (02.10)                                  | Govert    |
      | voorvoegsel (02.30)                                | de        |
      | geslachtsnaam (02.40)                              | Geus      |
      | geboortedatum (03.10)                              | 19841002  |
      | geboorteland (03.30)                               | 6030      |
      | geslachtsaanduiding (04.10)                        | M         |
      | aktenummer (81.20)                                 | 1AA0107   |
      | aanduiding in onderzoek (83.10)                    | 26210     |
      | datum ingang onderzoek (83.20)                     | 20220825  |
      | ingangsdatum geldigheid (85.10)                    | 20160607  |
      | datum ingang familierechtelijke betrekking (62.10) | 20160607  |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde     |
      | burgerservicenummer (01.20)                        | 000000024  |
      | voornamen (02.10)                                  | Grada      |
      | geslachtsnaam (02.40)                              | Goverwelle |
      | geboortedatum (03.10)                              | 19890416   |
      | geboorteland (03.30)                               | 6030       |
      | geslachtsaanduiding (04.10)                        | V          |
      | aktenummer (81.20)                                 | 1AA0107    |
      | aanduiding in onderzoek (83.10)                    | 30210      |
      | datum ingang onderzoek (83.20)                     | 20220825   |
      | ingangsdatum geldigheid (85.10)                    | 20160607   |
      | datum ingang familierechtelijke betrekking (62.10) | 20160607   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | aanduiding in onderzoek (83.10)   | 80920    |
      | datum ingang onderzoek (83.20)    | 20220825 |
      | ingangsdatum geldigheid (85.10)   | 20160607 |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde                      |
      | indicatie gezag minderjarige (32.10) | D                           |
      | beschrijving document (82.30)        | kennisgeving gezagsregister |
      | aanduiding in onderzoek (83.10)      | 113210                      |
      | datum ingang onderzoek (83.20)       | 20220825                    |
      | ingangsdatum geldigheid (85.10)      | 20220801                    |
    En de 'gezagsverhouding' is gewijzigd naar de volgende gegevens
      | naam                            | waarde   |
      | ingangsdatum geldigheid (85.10) | 20220825 |
    
    # Lg01_137 - kind geboren tijdens huwelijk in onderzoek 010330 (voorvoegsel ontbreekt), 040510, 081420, 113210
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Gijs     |
      | geslachtsnaam (02.40)           | Geus     |
      | geboortedatum (03.10)           | 20191205 |
      | geboorteland (03.30)            | 0        |
      | geslachtsaanduiding (04.10)     | M        |
      | beschrijving document (82.30)   | ga 5010  |
      | aanduiding in onderzoek (83.10) | 10330    |
      | datum ingang onderzoek (83.20)  | 20220825 |
      | ingangsdatum geldigheid (85.10) | 20191205 |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde     |
      | burgerservicenummer (01.20)                        | 000000024  |
      | voornamen (02.10)                                  | Grada      |
      | geslachtsnaam (02.40)                              | Goverwelle |
      | geboortedatum (03.10)                              | 19890416   |
      | geboorteland (03.30)                               | 6030       |
      | geslachtsaanduiding (04.10)                        | V          |
      | beschrijving document (82.30)                      | ga 5010    |
      | ingangsdatum geldigheid (85.10)                    | 20191205   |
      | datum ingang familierechtelijke betrekking (62.10) | 20191205   |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde    |
      | burgerservicenummer (01.20)                        | 000000012 |
      | voornamen (02.10)                                  | Govert    |
      | voorvoegsel (02.30)                                | de        |
      | geslachtsnaam (02.40)                              | Geus      |
      | geboortedatum (03.10)                              | 19841002  |
      | geboorteland (03.30)                               | 6030      |
      | geslachtsaanduiding (04.10)                        | M         |
      | beschrijving document (82.30)                      | ga 5010   |
      | ingangsdatum geldigheid (85.10)                    | 20191205  |
      | datum ingang familierechtelijke betrekking (62.10) | 20191205  |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                                 | waarde   |
      | gemeente van inschrijving (09.10)    | 518      |
      | land vanwaar ingeschreven (14.10)    | 0        |
      | datum vestiging in Nederland (14.20) | 20191210 |
      | aanduiding in onderzoek (83.10)      | 81420    |
      | datum ingang onderzoek (83.20)       | 20220825 |
      | ingangsdatum geldigheid (85.10)      | 20191205 |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde                      |
      | indicatie gezag minderjarige (32.10) | 1                           |
      | beschrijving document (82.30)        | kennisgeving gezagsregister |
      | aanduiding in onderzoek (83.10)      | 113210                      |
      | datum ingang onderzoek (83.20)       | 20220825                    |
      | ingangsdatum geldigheid (85.10)      | 20220801                    |
    
    # Lg01_138 - kind geboren tijdens huwelijk, overleden, gezag is niet van toepassing
    Gegeven de persoon met burgerservicenummer '000000061' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Gloria   |
      | voorvoegsel (02.30)             | de       |
      | geslachtsnaam (02.40)           | Geus     |
      | geboortedatum (03.10)           | 20211012 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | V        |
      | aktenummer (81.20)              | 1AA0108  |
      | aanduiding in onderzoek (83.10) | 18120    |
      | datum ingang onderzoek (83.20)  | 20220825 |
      | ingangsdatum geldigheid (85.10) | 20211012 |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde    |
      | burgerservicenummer (01.20)                        | 000000012 |
      | voornamen (02.10)                                  | Govert    |
      | voorvoegsel (02.30)                                | de        |
      | geslachtsnaam (02.40)                              | Geus      |
      | geboortedatum (03.10)                              | 19841002  |
      | geboorteland (03.30)                               | 6030      |
      | geslachtsaanduiding (04.10)                        | M         |
      | aktenummer (81.20)                                 | 1AA0108   |
      | aanduiding in onderzoek (83.10)                    | 28120     |
      | datum ingang onderzoek (83.20)                     | 20220825  |
      | ingangsdatum geldigheid (85.10)                    | 20211012  |
      | datum ingang familierechtelijke betrekking (62.10) | 20211012  |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde     |
      | burgerservicenummer (01.20)                        | 000000024  |
      | voornamen (02.10)                                  | Grada      |
      | geslachtsnaam (02.40)                              | Goverwelle |
      | geboortedatum (03.10)                              | 19890416   |
      | geboorteland (03.30)                               | 6030       |
      | geslachtsaanduiding (04.10)                        | V          |
      | aktenummer (81.20)                                 | 1AA0108    |
      | aanduiding in onderzoek (83.10)                    | 38120      |
      | datum ingang onderzoek (83.20)                     | 20220825   |
      | ingangsdatum geldigheid (85.10)                    | 20211012   |
      | datum ingang familierechtelijke betrekking (62.10) | 20211012   |
    En de persoon heeft de volgende 'overlijden' gegevens
      | naam                            | waarde                  |
      | datum overlijden (08.10)        | 20220201                |
      | beschrijving document (82.30)   | kennisgeving ziekenhuis |
      | aanduiding in onderzoek (83.10) | 60000                   |
      | datum ingang onderzoek (83.20)  | 20220825                |
      | ingangsdatum geldigheid (85.10) | 20220201                |
    En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                 | waarde   |
      | datum opschorting bijhouding (67.10) | 20220201 |
      | reden opschorting bijhouding (67.20) | O        |
      | indicatie geheim (70.10)             | 0        |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20211012 |


  Scenario: Lg01_134 - gehuwd, kinderen, in onderzoek 010300, 020000, 030310, 050610, 089999, 090000, 090300
    # Meerderjarig
    # Route: 2mi - Wie heeft gezag?:  (N)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                                                                                   |
      | type                             | GezagNietTeBepalen                                                                                                                       |
      | minderjarige.burgerservicenummer | 000000012                                                                                                                                |
      | toelichting                      | Gezag is niet te bepalen, omdat de volgende relevante gegevens in onderzoek staan. Persoonslijst van persoon: geboortedatum van persoon. |

  Scenario: Lg01_135 - gehuwd, kinderen, in onderzoek 026210, 030000, 050200, 080000, 090330
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon geen gezag

  Scenario: Lg01_136 - kind geboren tijdens huwelijk, in onderzoek 010310, 026210, 030210, 080920, 613210
    # Route: 49i - Wie heeft gezag?: niet te bepalen (N)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                                                                                                                                         |
      | type                             | GezagNietTeBepalen                                                                                                                                                                             |
      | minderjarige.burgerservicenummer | 000000036                                                                                                                                                                                      |
      | toelichting                      | Gezag is niet te bepalen, omdat de volgende relevante gegevens in onderzoek staan. Persoonslijst van persoon: geboortedatum van persoon. Persoonslijst van ouder 1: geboortedatum van persoon. |

  Scenario: Lg01_137 - kind geboren tijdens huwelijk in onderzoek 010330 (voorvoegsel ontbreekt), 040510, 081420, 113210
    # Route: 3ai - Wie heeft gezag?: niet te bepalen (N )
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                                                                                  |
      | type                             | GezagNietTeBepalen                                                                                                                      |
      | minderjarige.burgerservicenummer | 000000048                                                                                                                               |
      | toelichting                      | Gezag is niet te bepalen, omdat de volgende relevante gegevens in onderzoek staan. Persoonslijst van persoon: geboorteland van persoon. |

  Scenario: Lg01_138 - kind geboren tijdens huwelijk, overleden, gezag is niet van toepassing
    # Route: 2o - Wie heeft gezag?: overleden, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
    En heeft de persoon geen gezag
