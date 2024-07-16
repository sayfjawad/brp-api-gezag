#language: nl

Functionaliteit: Tg039 - Tubbergen-Tintelen – 2 vrouwen met geregistreerd partnerschap en daarbinnen 4 kinderen. Beide vrouwen zijn geboortemoeder van 2 kinderen. 1 moeder is overleden


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 79                 | 0518200000583570                           |

    # Lg01_209 - geregistreerd partnerschap met vrouw, partner overleden, minderjarig kind van bekende donor
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000012  |
    | voornamen (02.10)               | Tina       |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Tubbergen  |
    | geboortedatum (03.10)           | 19800201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Toos     |
    | geslachtsnaam (02.40)                              | Triest   |
    | geboortedatum (03.10)                              | 19500901 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde    |
    | voornamen (02.10)                                  | Teunis    |
    | voorvoegsel (02.30)                                | van       |
    | geslachtsnaam (02.40)                              | Tubbergen |
    | geboortedatum (03.10)                              | 19520401  |
    | geboorteland (03.30)                               | 6030      |
    | geslachtsaanduiding (04.10)                        | M         |
    | beschrijving document (82.30)                      | PK        |
    | ingangsdatum geldigheid (85.10)                    | 0         |
    | datum ingang familierechtelijke betrekking (62.10) | 0         |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000024  |
    | voornamen (02.10)                                                   | Trudy      |
    | voorvoegsel (02.30)                                                 | van        |
    | geslachtsnaam (02.40)                                               | Tintelen   |
    | geboortedatum (03.10)                                               | 19810301   |
    | geboorteland (03.30)                                                | 6030       |
    | geslachtsaanduiding (04.10)                                         | V          |
    | aktenummer (81.20)                                                  | 5AA0200    |
    | ingangsdatum geldigheid (85.10)                                     | 20200201   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20200201   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de 'partner' is gewijzigd naar de volgende gegevens
    | naam                                                          | waarde     |
    | burgerservicenummer (01.20)                                   | 000000024  |
    | voornamen (02.10)                                             | Trudy      |
    | voorvoegsel (02.30)                                           | van        |
    | geslachtsnaam (02.40)                                         | Tintelen   |
    | geboortedatum (03.10)                                         | 19810301   |
    | geboorteland (03.30)                                          | 6030       |
    | geslachtsaanduiding (04.10)                                   | V          |
    | aktenummer (81.20)                                            | 2AA0100    |
    | ingangsdatum geldigheid (85.10)                               | 20230401   |
    | datum ontbinding huwelijk/geregistreerd partnerschap (07.10)  | 20230401   |
    | plaats ontbinding huwelijk/geregistreerd partnerschap (07.20) | 0518       |
    | land ontbinding huwelijk/geregistreerd partnerschap (07.30)   | 6030       |
    | Reden ontbinding huwelijk/geregistreerd partnerschap (07.40)  | O          |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Trudetty   |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Tubbergen  |
    | geboortedatum (03.10)           | 20230201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0301    |
    | ingangsdatum geldigheid (85.10) | 20230201   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Trinette   |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Tubbergen  |
    | geboortedatum (03.10)           | 20230201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0300    |
    | ingangsdatum geldigheid (85.10) | 20230201   |
    
    # Lg01_210 - geregistreerd partnerschap met vrouw, overleden
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Trudy      |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Tintelen   |
    | geboortedatum (03.10)           | 19810301   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde    |
    | voornamen (02.10)                                  | Teuntje   |
    | geslachtsnaam (02.40)                              | Tollenaar |
    | geboortedatum (03.10)                              | 19520301  |
    | geboorteland (03.30)                               | 6030      |
    | geslachtsaanduiding (04.10)                        | V         |
    | beschrijving document (82.30)                      | PK        |
    | ingangsdatum geldigheid (85.10)                    | 0         |
    | datum ingang familierechtelijke betrekking (62.10) | 0         |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Tobias   |
    | voorvoegsel (02.30)                                | van      |
    | geslachtsnaam (02.40)                              | Tintelen |
    | geboortedatum (03.10)                              | 19510304 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000012  |
    | voornamen (02.10)                                                   | Tina       |
    | voorvoegsel (02.30)                                                 | van        |
    | geslachtsnaam (02.40)                                               | Tubbergen  |
    | geboortedatum (03.10)                                               | 19800201   |
    | geboorteland (03.30)                                                | 6030       |
    | geslachtsaanduiding (04.10)                                         | V          |
    | aktenummer (81.20)                                                  | 5AA0200    |
    | ingangsdatum geldigheid (85.10)                                     | 20200201   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20200201   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de persoon heeft de volgende 'overlijden' gegevens
    | naam                            | waarde   |
    | datum overlijden (08.10)        | 20230401 |
    | aktenummer (81.20)              | 2AA0100  |
    | ingangsdatum geldigheid (85.10) | 20230401 |
    En de persoon heeft de volgende 'inschrijving' gegevens
    | naam                                 | waarde                     |
    | datum opschorting bijhouding (67.10) | 20230401                   |
    | reden opschorting bijhouding (67.20) | O                          |
    | indicatie geheim (70.10)             | 0                          |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Tom        |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Tintelen   |
    | geboortedatum (03.10)           | 20221001   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0401    |
    | ingangsdatum geldigheid (85.10) | 20221001   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Tim        |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Tintelen   |
    | geboortedatum (03.10)           | 20221001   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0400    |
    | ingangsdatum geldigheid (85.10) | 20221001   |
    
    # Lg01_211 - juridisch alleen moeder, geregistreerd partner van moeder overleden, moeder categorie 02
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Trinette   |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Tubbergen  |
    | geboortedatum (03.10)           | 20230201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0300    |
    | ingangsdatum geldigheid (85.10) | 20230201   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Tina       |
    | voorvoegsel (02.30)                                | van        |
    | geslachtsnaam (02.40)                              | Tubbergen  |
    | geboortedatum (03.10)                              | 19800201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0300    |
    | ingangsdatum geldigheid (85.10)                    | 20230201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20230201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0300  |
    | ingangsdatum geldigheid (85.10) | 20230201 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20230201 |
    
    # Lg01_212 - juridisch alleen moeder, geregistreerd partner van moeder overleden, moeder categorie 03
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Trudetty   |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Tubbergen  |
    | geboortedatum (03.10)           | 20230201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0301    |
    | ingangsdatum geldigheid (85.10) | 20230201   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0301  |
    | ingangsdatum geldigheid (85.10) | 20230201 |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Tina       |
    | voorvoegsel (02.30)                                | van        |
    | geslachtsnaam (02.40)                              | Tubbergen  |
    | geboortedatum (03.10)                              | 19800201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0301    |
    | ingangsdatum geldigheid (85.10)                    | 20230201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20230201   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20230201 |
    
    # Lg01_213 - juridisch alleen moeder, moeder overleden, geregistreerd partner moeder geen ouder wel gezag, moeder categorie 02
    Gegeven de persoon met burgerservicenummer '000000061' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Tim        |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Tintelen   |
    | geboortedatum (03.10)           | 20221001   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0400    |
    | ingangsdatum geldigheid (85.10) | 20221001   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Trudy      |
    | voorvoegsel (02.30)                                | van        |
    | geslachtsnaam (02.40)                              | Tintelen   |
    | geboortedatum (03.10)                              | 19810301   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0400    |
    | ingangsdatum geldigheid (85.10)                    | 20221001   |
    | datum ingang familierechtelijke betrekking (62.10) | 20221001   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0400  |
    | ingangsdatum geldigheid (85.10) | 20221001 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20221001 |
    
    # Lg01_214 - juridisch alleen moeder, moeder overleden, geregistreerd partner moeder geen ouder wel gezag, moeder categorie 03
    Gegeven de persoon met burgerservicenummer '000000073' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Tom        |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Tintelen   |
    | geboortedatum (03.10)           | 20221001   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0401    |
    | ingangsdatum geldigheid (85.10) | 20221001   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0401  |
    | ingangsdatum geldigheid (85.10) | 20221001 |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Trudy      |
    | voorvoegsel (02.30)                                | van        |
    | geslachtsnaam (02.40)                              | Tintelen   |
    | geboortedatum (03.10)                              | 19810301   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0401    |
    | ingangsdatum geldigheid (85.10)                    | 20221001   |
    | datum ingang familierechtelijke betrekking (62.10) | 20221001   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20221001 |


  Scenario: Lg01_209 - geregistreerd partnerschap met vrouw, partner overleden, minderjarig kind van bekende donor
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                       |
    | burgerservicenummer | 000000012                                                                    |
    | toelichting         | Ingezeten - meerderjarig - soort gezag is NVT - gezag is niet van toepassing |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000036                |
    | ouder.burgerservicenummer        | 000000012                |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000048                |
    | ouder.burgerservicenummer        | 000000012                |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde    |
    | type                             | Voogdij   |
    | minderjarige.burgerservicenummer | 000000061 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde    |
    | type                             | Voogdij   |
    | minderjarige.burgerservicenummer | 000000073 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |

  Scenario: Lg01_210 - geregistreerd partnerschap met vrouw, overleden
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                       |
    | burgerservicenummer | 000000024                                                                    |
    | toelichting         | Ingezeten - meerderjarig - soort gezag is NVT - gezag is niet van toepassing |
    En heeft de persoon geen gezag

  Scenario: Lg01_211 - juridisch alleen moeder, geregistreerd partner van moeder overleden, moeder categorie 02
    # Route: 42no1 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                                                                                                  |
    | burgerservicenummer | 000000036                                                                                                                                               |
    | toelichting         | Ingezeten - minderjarig - heeft één ouder - ouder ongehuwd/geen partnerschap - partner is onbevoegd tot gezag - soort gezag is OG1 - ouder1 heeft gezag |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000036                |
    | ouder.burgerservicenummer        | 000000012                |

  Scenario: Lg01_212 - juridisch alleen moeder, geregistreerd partner van moeder overleden, moeder categorie 03
    # Route: 42no2 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                                                                                                  |
    | burgerservicenummer | 000000048                                                                                                                                               |
    | toelichting         | Ingezeten - minderjarig - heeft één ouder - ouder ongehuwd/geen partnerschap - partner is onbevoegd tot gezag - soort gezag is OG1 - ouder2 heeft gezag |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000048                |
    | ouder.burgerservicenummer        | 000000012                |

  Scenario: Lg01_213 - juridisch alleen moeder, moeder overleden, geregistreerd partner moeder geen ouder wel gezag, moeder categorie 02
    # Route: 43o1 - Wie heeft gezag?: partner moeder (V)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                                                                                             |
    | burgerservicenummer | 000000061                                                                                                                                          |
    | toelichting         | Ingezeten - minderjarig - heeft één ouder - ouder ongehuwd/geen partnerschap - ouder onbevoegd - soort gezag is V - de partner van ouder1 is voogd |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde    |
    | type                             | Voogdij   |
    | minderjarige.burgerservicenummer | 000000061 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |

  Scenario: Lg01_214 - juridisch alleen moeder, moeder overleden, geregistreerd partner moeder geen ouder wel gezag, moeder categorie 03
    # Route: 43o2 - Wie heeft gezag?: partner moeder (V)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000073 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                                                                                             |
    | burgerservicenummer | 000000073                                                                                                                                          |
    | toelichting         | Ingezeten - minderjarig - heeft één ouder - ouder ongehuwd/geen partnerschap - ouder onbevoegd - soort gezag is V - de partner van ouder2 is voogd |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde    |
    | type                             | Voogdij   |
    | minderjarige.burgerservicenummer | 000000073 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
