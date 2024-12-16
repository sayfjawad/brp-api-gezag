#language: nl

Functionaliteit: Tg033 - Paassen-Pauw - Twee gehuwde vrouwen met 4 kinderen


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
      | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
      | 518                  | Turfmarkt          | 73                 | 0518200000583567                           |
    
    # Lg01_176 - vrouw gehuwd met vrouw, 4 kinderen (2 van bekende donor tijdens huwelijken 1 voor het huwelijk en 1 na 01-01-2023 erkend voor geboorte door ander dan partner))
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde     |
      | voornamen (02.10)               | Petronella |
      | voorvoegsel (02.30)             | van        |
      | geslachtsnaam (02.40)           | Paassen    |
      | geboortedatum (03.10)           | 19981201   |
      | geboorteland (03.30)            | 6030       |
      | geslachtsaanduiding (04.10)     | V          |
      | aktenummer (81.20)              | 1AA0200    |
      | ingangsdatum geldigheid (85.10) | 19981201   |
    En de persoon is gewijzigd naar de volgende gegevens
      | naam                            | waarde                         |
      | burgerservicenummer (01.20)     | 000000012                      |
      | voornamen (02.10)               | Petronella                     |
      | voorvoegsel (02.30)             | van                            |
      | geslachtsnaam (02.40)           | Paassen                        |
      | geboortedatum (03.10)           | 19981201                       |
      | geboorteland (03.30)            | 6030                           |
      | geslachtsaanduiding (04.10)     | V                              |
      | beschrijving document (82.30)   | Melding Minister van Financien |
      | ingangsdatum geldigheid (85.10) | 19981212                       |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde   |
      | aktenummer (81.20)              | 1AA0200  |
      | ingangsdatum geldigheid (85.10) | 19981201 |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde   |
      | voornamen (02.10)                                  | Pleuni   |
      | voorvoegsel (02.30)                                | van      |
      | geslachtsnaam (02.40)                              | Paassen  |
      | geboortedatum (03.10)                              | 19700201 |
      | geboorteland (03.30)                               | 6030     |
      | geslachtsaanduiding (04.10)                        | V        |
      | aktenummer (81.20)                                 | 1AA0200  |
      | ingangsdatum geldigheid (85.10)                    | 19981201 |
      | datum ingang familierechtelijke betrekking (62.10) | 19981201 |
    En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                                | waarde    |
      | burgerservicenummer (01.20)                                         | 000000024 |
      | voornamen (02.10)                                                   | Pia       |
      | geslachtsnaam (02.40)                                               | Pauw      |
      | geboortedatum (03.10)                                               | 19890401  |
      | geboorteland (03.30)                                                | 6030      |
      | geslachtsaanduiding (04.10)                                         | V         |
      | aktenummer (81.20)                                                  | 3AA0100   |
      | ingangsdatum geldigheid (85.10)                                     | 20170101  |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20170101  |
      | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518      |
      | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030      |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000061 |
      | voornamen (02.10)               | Pep       |
      | voorvoegsel (02.30)             | van       |
      | geslachtsnaam (02.40)           | Paassen   |
      | geboortedatum (03.10)           | 20160501  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0102   |
      | ingangsdatum geldigheid (85.10) | 20160501  |
    En de persoon heeft nog een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000048 |
      | voornamen (02.10)               | Pelle     |
      | voorvoegsel (02.30)             | van       |
      | geslachtsnaam (02.40)           | Paassen   |
      | geboortedatum (03.10)           | 20170901  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0101   |
      | ingangsdatum geldigheid (85.10) | 20170901  |
    En de persoon heeft nog een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000036 |
      | voornamen (02.10)               | Peet      |
      | voorvoegsel (02.30)             | van       |
      | geslachtsnaam (02.40)           | Paassen   |
      | geboortedatum (03.10)           | 20181201  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0100   |
      | ingangsdatum geldigheid (85.10) | 20181201  |
    En de persoon heeft nog een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000073 |
      | voornamen (02.10)               | Pero      |
      | voorvoegsel (02.30)             | van       |
      | geslachtsnaam (02.40)           | Paassen   |
      | geboortedatum (03.10)           | 20230201  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0100   |
      | ingangsdatum geldigheid (85.10) | 20230201  |
    
    # Lg01_177 - huwelijkspartner van vrouw, geen meemoeder van de kinderen, bij 1 kind gezamenlijk gezag
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Pia      |
      | geslachtsnaam (02.40)           | Pauw     |
      | geboortedatum (03.10)           | 19890401 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | V        |
      | beschrijving document (82.30)   | PKA      |
      | ingangsdatum geldigheid (85.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde |
      | beschrijving document (82.30)   | PK     |
      | ingangsdatum geldigheid (85.10) | 0      |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde   |
      | voornamen (02.10)                                  | Phea     |
      | geslachtsnaam (02.40)                              | Pauw     |
      | geboortedatum (03.10)                              | 19650901 |
      | geboorteland (03.30)                               | 6030     |
      | geslachtsaanduiding (04.10)                        | V        |
      | beschrijving document (82.30)                      | PK       |
      | ingangsdatum geldigheid (85.10)                    | 0        |
      | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                                | waarde     |
      | burgerservicenummer (01.20)                                         | 000000012  |
      | voornamen (02.10)                                                   | Petronella |
      | voorvoegsel (02.30)                                                 | van        |
      | geslachtsnaam (02.40)                                               | Paassen    |
      | geboortedatum (03.10)                                               | 19981201   |
      | geboorteland (03.30)                                                | 6030       |
      | geslachtsaanduiding (04.10)                                         | V          |
      | aktenummer (81.20)                                                  | 3AA0100    |
      | ingangsdatum geldigheid (85.10)                                     | 20170101   |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20170101   |
      | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
      | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20111101 |
    
    # Lg01_178 - 1 ouder, moeder gehuwd, gezamenlijk gezag moeder en meemoeder
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Peet     |
      | voorvoegsel (02.30)             | van      |
      | geslachtsnaam (02.40)           | Paassen  |
      | geboortedatum (03.10)           | 20181201 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | M        |
      | aktenummer (81.20)              | 1AA0100  |
      | ingangsdatum geldigheid (85.10) | 20181201 |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde   |
      | aktenummer (81.20)              | 1AA0100  |
      | ingangsdatum geldigheid (85.10) | 20181201 |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde     |
      | burgerservicenummer (01.20)                        | 000000012  |
      | voornamen (02.10)                                  | Petronella |
      | voorvoegsel (02.30)                                | van        |
      | geslachtsnaam (02.40)                              | Paassen    |
      | geboortedatum (03.10)                              | 19981201   |
      | geboorteland (03.30)                               | 6030       |
      | geslachtsaanduiding (04.10)                        | V          |
      | aktenummer (81.20)                                 | 1AA0100    |
      | ingangsdatum geldigheid (85.10)                    | 20181201   |
      | datum ingang familierechtelijke betrekking (62.10) | 20181201   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20181201 |
    
    # Lg01_179 - 1 ouder, moeder gehuwd, indicatie gezag 1D geen gezamenlijk gezag moeder en meemoeder
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Pelle    |
      | voorvoegsel (02.30)             | van      |
      | geslachtsnaam (02.40)           | Paassen  |
      | geboortedatum (03.10)           | 20170901 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | M        |
      | aktenummer (81.20)              | 1AA0101  |
      | ingangsdatum geldigheid (85.10) | 20170901 |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde   |
      | aktenummer (81.20)              | 1AA0101  |
      | ingangsdatum geldigheid (85.10) | 20170901 |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde     |
      | burgerservicenummer (01.20)                        | 000000012  |
      | voornamen (02.10)                                  | Petronella |
      | voorvoegsel (02.30)                                | van        |
      | geslachtsnaam (02.40)                              | Paassen    |
      | geboortedatum (03.10)                              | 19981201   |
      | geboorteland (03.30)                               | 6030       |
      | geslachtsaanduiding (04.10)                        | V          |
      | aktenummer (81.20)                                 | 1AA0101    |
      | ingangsdatum geldigheid (85.10)                    | 20170901   |
      | datum ingang familierechtelijke betrekking (62.10) | 20170901   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20170901 |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde                      |
      | indicatie gezag minderjarige (32.10) | 1D                          |
      | beschrijving document (82.30)        | kennisgeving gezagsregister |
      | ingangsdatum geldigheid (85.10)      | 20171201                    |
    
    # Lg01_180 - 1 ouder, moeder gehuwd na geboorte kind, geen gezamenlijk gezag
    Gegeven de persoon met burgerservicenummer '000000061' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Pep      |
      | voorvoegsel (02.30)             | van      |
      | geslachtsnaam (02.40)           | Paassen  |
      | geboortedatum (03.10)           | 20160501 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | M        |
      | aktenummer (81.20)              | 1AA0102  |
      | ingangsdatum geldigheid (85.10) | 20160501 |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde   |
      | aktenummer (81.20)              | 1AA0102  |
      | ingangsdatum geldigheid (85.10) | 20160501 |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde     |
      | burgerservicenummer (01.20)                        | 000000012  |
      | voornamen (02.10)                                  | Petronella |
      | voorvoegsel (02.30)                                | van        |
      | geslachtsnaam (02.40)                              | Paassen    |
      | geboortedatum (03.10)                              | 19981201   |
      | geboorteland (03.30)                               | 6030       |
      | geslachtsaanduiding (04.10)                        | V          |
      | aktenummer (81.20)                                 | 1AA0102    |
      | ingangsdatum geldigheid (85.10)                    | 20160501   |
      | datum ingang familierechtelijke betrekking (62.10) | 20160501   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20160501 |
    
    # Lg01_198 - minderjarige geboren na 01-01-2023 uit gehuwde moeder en bekende donor, erkend voor geboorte door ander dan partner
    Gegeven de persoon met burgerservicenummer '000000073' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Pero     |
      | voorvoegsel (02.30)             | van      |
      | geslachtsnaam (02.40)           | Paassen  |
      | geboortedatum (03.10)           | 20230201 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | M        |
      | aktenummer (81.20)              | 1AA0100  |
      | ingangsdatum geldigheid (85.10) | 20230201 |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde    |
      | burgerservicenummer (01.20)                        | 000000085 |
      | voornamen (02.10)                                  | Peterjan  |
      | geslachtsnaam (02.40)                              | Pluym     |
      | geboortedatum (03.10)                              | 19760501  |
      | geboorteland (03.30)                               | 6030      |
      | geslachtsaanduiding (04.10)                        | M         |
      | aktenummer (81.20)                                 | 1AA0100   |
      | ingangsdatum geldigheid (85.10)                    | 20181201  |
      | datum ingang familierechtelijke betrekking (62.10) | 20230201  |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde     |
      | burgerservicenummer (01.20)                        | 000000012  |
      | voornamen (02.10)                                  | Petronella |
      | voorvoegsel (02.30)                                | van        |
      | geslachtsnaam (02.40)                              | Paassen    |
      | geboortedatum (03.10)                              | 19981201   |
      | geboorteland (03.30)                               | 6030       |
      | geslachtsaanduiding (04.10)                        | V          |
      | aktenummer (81.20)                                 | 1AA0100    |
      | ingangsdatum geldigheid (85.10)                    | 20230201   |
      | datum ingang familierechtelijke betrekking (62.10) | 20230201   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20230201 |
    
    # Lg01_199 - erkenner (voor geboorte) van minderjarig kind van gehuwde moeder en bekende donor
    Gegeven de persoon met burgerservicenummer '000000085' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Peterjan |
      | geslachtsnaam (02.40)           | Pluym    |
      | geboortedatum (03.10)           | 19760501 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | M        |
      | beschrijving document (82.30)   | PKA      |
      | ingangsdatum geldigheid (85.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde   |
      | voornamen (02.10)                                  | Peter    |
      | geslachtsnaam (02.40)                              | Pluym    |
      | geboortedatum (03.10)                              | 19450301 |
      | geboorteland (03.30)                               | 6030     |
      | geslachtsaanduiding (04.10)                        | M        |
      | beschrijving document (82.30)                      | PK       |
      | ingangsdatum geldigheid (85.10)                    | 0        |
      | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde   |
      | voornamen (02.10)                                  | Piadora  |
      | geslachtsnaam (02.40)                              | Pattel   |
      | geboortedatum (03.10)                              | 19500201 |
      | geboorteland (03.30)                               | 6030     |
      | geslachtsaanduiding (04.10)                        | V        |
      | beschrijving document (82.30)                      | PK       |
      | ingangsdatum geldigheid (85.10)                    | 0        |
      | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000073 |
      | voornamen (02.10)               | Pero      |
      | voorvoegsel (02.30)             | van       |
      | geslachtsnaam (02.40)           | Paassen   |
      | geboortedatum (03.10)           | 20230201  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0100   |
      | ingangsdatum geldigheid (85.10) | 20230201  |


  Scenario: Lg01_176 - vrouw gehuwd met vrouw, 4 kinderen (2 van bekende donor tijdens huwelijken 1 voor het huwelijk en 1 na 01-01-2023 erkend voor geboorte door ander dan partner))
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde           |
      | type                             | GezamenlijkGezag |
      | minderjarige.burgerservicenummer | 000000036        |
      | ouder.burgerservicenummer        | 000000012        |
      | derde.burgerservicenummer        | 000000024        |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000061                |
      | ouder.burgerservicenummer        | 000000012                |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000073                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000085 |

  Scenario: Lg01_177 - huwelijkspartner van vrouw, geen meemoeder van de kinderen, bij 1 kind gezamenlijk gezag
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde           |
      | type                             | GezamenlijkGezag |
      | minderjarige.burgerservicenummer | 000000036        |
      | ouder.burgerservicenummer        | 000000012        |
      | derde.burgerservicenummer        | 000000024        |

  Scenario: Lg01_178 - 1 ouder, moeder gehuwd, gezamenlijk gezag moeder en meemoeder
    # Route: 41 - Wie heeft gezag?: moeder en meemoeder (GG)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde           |
      | type                             | GezamenlijkGezag |
      | minderjarige.burgerservicenummer | 000000036        |
      | ouder.burgerservicenummer        | 000000012        |
      | derde.burgerservicenummer        | 000000024        |

  Scenario: Lg01_179 - 1 ouder, moeder gehuwd, indicatie gezag 1D geen gezamenlijk gezag moeder en meemoeder
    # Route: 4 - Wie heeft gezag?: moeder en derde (V)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000048 |
    En heeft 'gezag' geen derden

  Scenario: Lg01_180 - 1 ouder, moeder gehuwd na geboorte kind, geen gezamenlijk gezag
    # Route: 40o1 - Wie heeft gezag?: moeder (OG1)
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
      | ouder.burgerservicenummer        | 000000012                |

  Scenario: Lg01_198 - minderjarige geboren na 01-01-2023 uit gehuwde moeder en bekende donor, erkend voor geboorte door ander dan partner
    # Route: 54n - Wie heeft gezag?: moeder (OG2)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000073 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000073 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000073                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000085 |

  Scenario: Lg01_199 - erkenner (voor geboorte) van minderjarig kind van gehuwde moeder en bekende donor
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000085 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000085 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000073                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000085 |
