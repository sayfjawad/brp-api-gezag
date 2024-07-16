#language: nl

Functionaliteit: Tg030 - Leeuwen-Luiten-Leijten - Cat.11 en latere erkenning


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 67                 | 0518200000583564                           |

    # Lg01_161 - moeder van 2 erkende kinderen
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Laurentien |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Leeuwen    |
    | geboortedatum (03.10)           | 20031224   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0750    |
    | ingangsdatum geldigheid (85.10) | 20031224   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde                         |
    | burgerservicenummer (01.20)     | 000000012                      |
    | voornamen (02.10)               | Laurentien                     |
    | voorvoegsel (02.30)             | van                            |
    | geslachtsnaam (02.40)           | Leeuwen                        |
    | geboortedatum (03.10)           | 20031224                       |
    | geboorteland (03.30)            | 6030                           |
    | geslachtsaanduiding (04.10)     | V                              |
    | beschrijving document (82.30)   | Melding Minister van Financien |
    | ingangsdatum geldigheid (85.10) | 20040102                       |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Lenie    |
    | voorvoegsel (02.30)                                | van      |
    | geslachtsnaam (02.40)                              | Lennep   |
    | geboortedatum (03.10)                              | 19801109 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | aktenummer (81.20)                                 | 1AA0750  |
    | ingangsdatum geldigheid (85.10)                    | 20031224 |
    | datum ingang familierechtelijke betrekking (62.10) | 20031224 |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Lars     |
    | voorvoegsel (02.30)                                | van      |
    | geslachtsnaam (02.40)                              | Leeuwen  |
    | geboortedatum (03.10)                              | 19760626 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | aktenummer (81.20)                                 | 1AA0750  |
    | ingangsdatum geldigheid (85.10)                    | 20031224 |
    | datum ingang familierechtelijke betrekking (62.10) | 20031224 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Laeticia   |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Leeuwen    |
    | geboortedatum (03.10)           | 20220901   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0601    |
    | ingangsdatum geldigheid (85.10) | 20220901   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Laeticia   |
    | geslachtsnaam (02.40)           | Luiten     |
    | geboortedatum (03.10)           | 20220901   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0601    |
    | ingangsdatum geldigheid (85.10) | 20230125   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Larissa    |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Leeuwen    |
    | geboortedatum (03.10)           | 20220901   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0600    |
    | ingangsdatum geldigheid (85.10) | 20220901   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Larissa    |
    | geslachtsnaam (02.40)           | Leijten    |
    | geboortedatum (03.10)           | 20220901   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0600    |
    | ingangsdatum geldigheid (85.10) | 20230131   |
    
    # Lg01_162 - voogd van 2 minderjarige kinderen (1D), 1 kind door hem later erkend
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Louis      |
    | geslachtsnaam (02.40)           | Luiten     |
    | geboortedatum (03.10)           | 19901201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Lucia    |
    | geslachtsnaam (02.40)                              | Liebeek  |
    | geboortedatum (03.10)                              | 19650524 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Leen     |
    | geslachtsnaam (02.40)                              | Luiten   |
    | geboortedatum (03.10)                              | 19630729 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Laeticia   |
    | geslachtsnaam (02.40)           | Luiten     |
    | geboortedatum (03.10)           | 20220901   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0601    |
    | ingangsdatum geldigheid (85.10) | 20230125   |
    
    # Lg01_163 - erkenner van minderjarig kind wat eerder onder gezag (1D) is geplaatst
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Leroy      |
    | geslachtsnaam (02.40)           | Leijten    |
    | geboortedatum (03.10)           | 20010225   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0300    |
    | ingangsdatum geldigheid (85.10) | 20010225   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde                         |
    | burgerservicenummer (01.20)     | 000000036                      |
    | voornamen (02.10)               | Leroy                          |
    | geslachtsnaam (02.40)           | Leijten                        |
    | geboortedatum (03.10)           | 20010225                       |
    | geboorteland (03.30)            | 6030                           |
    | geslachtsaanduiding (04.10)     | M                              |
    | beschrijving document (82.30)   | Melding Minister van Financien |
    | ingangsdatum geldigheid (85.10) | 20010301                       |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Liesbeth |
    | geslachtsnaam (02.40)                              | Lanen    |
    | geboortedatum (03.10)                              | 19780209 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | aktenummer (81.20)                                 | 1AA0300  |
    | ingangsdatum geldigheid (85.10)                    | 20010225 |
    | datum ingang familierechtelijke betrekking (62.10) | 20010225 |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Lucas    |
    | geslachtsnaam (02.40)                              | Leijten  |
    | geboortedatum (03.10)                              | 19790204 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | aktenummer (81.20)                                 | 1AA0300  |
    | ingangsdatum geldigheid (85.10)                    | 20010225 |
    | datum ingang familierechtelijke betrekking (62.10) | 20010225 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Larissa    |
    | geslachtsnaam (02.40)           | Leijten    |
    | geboortedatum (03.10)           | 20220901   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0600    |
    | ingangsdatum geldigheid (85.10) | 20230131   |
    
    # Lg01_164 - minderjarig kind onder gezag 1D, erkend
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Larissa    |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Leeuwen    |
    | geboortedatum (03.10)           | 20220901   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0600    |
    | ingangsdatum geldigheid (85.10) | 20220901   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Larissa    |
    | geslachtsnaam (02.40)           | Leijten    |
    | geboortedatum (03.10)           | 20220901   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AC0600    |
    | ingangsdatum geldigheid (85.10) | 20230131   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Laurentien |
    | voorvoegsel (02.30)                                | van        |
    | geslachtsnaam (02.40)                              | Leeuwen    |
    | geboortedatum (03.10)                              | 20031224   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0600    |
    | ingangsdatum geldigheid (85.10)                    | 20220901   |
    | datum ingang familierechtelijke betrekking (62.10) | 20220901   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0600  |
    | ingangsdatum geldigheid (85.10) | 20220901 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000036  |
    | voornamen (02.10)                                  | Leroy      |
    | geslachtsnaam (02.40)                              | Leijten    |
    | geboortedatum (03.10)                              | 20010225   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AC0600    |
    | ingangsdatum geldigheid (85.10)                    | 20230131   |
    | datum ingang familierechtelijke betrekking (62.10) | 20230131   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20220901 |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
    | naam                                 | waarde                      |
    | indicatie gezag minderjarige (32.10) | 1D                          |
    | beschrijving document (82.30)        | kennisgeving gezagsregister |
    | ingangsdatum geldigheid (85.10)      | 20221201                    |
    
    # Lg01_165 - minderjarig kind onder gezag 1D, erkend  door voogd
    Gegeven de persoon met burgerservicenummer '000000061' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Laeticia   |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Leeuwen    |
    | geboortedatum (03.10)           | 20220901   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0601    |
    | ingangsdatum geldigheid (85.10) | 20220901   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Laeticia   |
    | geslachtsnaam (02.40)           | Luiten     |
    | geboortedatum (03.10)           | 20220901   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AC0601    |
    | ingangsdatum geldigheid (85.10) | 20230125   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Laurentien |
    | voorvoegsel (02.30)                                | van        |
    | geslachtsnaam (02.40)                              | Leeuwen    |
    | geboortedatum (03.10)                              | 20031224   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0601    |
    | ingangsdatum geldigheid (85.10)                    | 20220901   |
    | datum ingang familierechtelijke betrekking (62.10) | 20220901   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0601  |
    | ingangsdatum geldigheid (85.10) | 20220901 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Louis      |
    | geslachtsnaam (02.40)                              | Luiten     |
    | geboortedatum (03.10)                              | 19901201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AC0601    |
    | ingangsdatum geldigheid (85.10)                    | 20230125   |
    | datum ingang familierechtelijke betrekking (62.10) | 20230125   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20220901 |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
    | naam                                 | waarde                      |
    | indicatie gezag minderjarige (32.10) | 1D                          |
    | beschrijving document (82.30)        | kennisgeving gezagsregister |
    | ingangsdatum geldigheid (85.10)      | 20221201                    |


  Scenario: Lg01_161 - moeder van 2 erkende kinderen
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon geen gezag

  Scenario: Lg01_162 - voogd van 2 minderjarige kinderen (1D), 1 kind door hem later erkend
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft de persoon geen gezag

  Scenario: Lg01_163 - erkenner van minderjarig kind wat eerder onder gezag (1D) is geplaatst
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft de persoon geen gezag

  Scenario: Lg01_164 - minderjarig kind onder gezag 1D, erkend
    # Route: 4 - Wie heeft gezag?: erkenner/voogd (V)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde    |
    | type                             | Voogdij   |
    | minderjarige.burgerservicenummer | 000000048 |
    En heeft 'gezag' geen derden

  Scenario: Lg01_165 - minderjarig kind onder gezag 1D, erkend  door voogd
    # Route: 4 - Wie heeft gezag?: voogd (V)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000061 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde    |
    | type                             | Voogdij   |
    | minderjarige.burgerservicenummer | 000000061 |
    En heeft 'gezag' geen derden
