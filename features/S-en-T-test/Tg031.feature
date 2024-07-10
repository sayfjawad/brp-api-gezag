#language: nl

Functionaliteit: Tg031 - Nooitgedacht-Nimwegen - Cat.11 en adoptie of ontkenning erkenning


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 69                 | 0518200000583565                           |

    # Lg01_169 - vrouw niet gehuwd/GP, 1 minderjarig kind geadopteerd, 1 minderjarig kind erkend en later erkenning vernietigd
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde       |
    | burgerservicenummer (01.20)     | 000000012    |
    | voornamen (02.10)               | Nicolette    |
    | geslachtsnaam (02.40)           | Nooitgedacht |
    | geboortedatum (03.10)           | 19901201     |
    | geboorteland (03.30)            | 6030         |
    | geslachtsaanduiding (04.10)     | V            |
    | beschrijving document (82.30)   | PKA          |
    | ingangsdatum geldigheid (85.10) | 0            |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde       |
    | voornamen (02.10)                                  | Naatje       |
    | geslachtsnaam (02.40)                              | Nooitgedacht |
    | geboortedatum (03.10)                              | 19600701     |
    | geboorteland (03.30)                               | 6030         |
    | geslachtsaanduiding (04.10)                        | V            |
    | beschrijving document (82.30)                      | PK           |
    | ingangsdatum geldigheid (85.10)                    | 0            |
    | datum ingang familierechtelijke betrekking (62.10) | 0            |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | beschrijving document (82.30)   | PK       |
    | ingangsdatum geldigheid (85.10) | 0        |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde       |
    | burgerservicenummer (01.20)     | 000000048    |
    | voornamen (02.10)               | Niels        |
    | geslachtsnaam (02.40)           | Nooitgedacht |
    | geboortedatum (03.10)           | 20190101     |
    | geboorteland (03.30)            | 6030         |
    | aktenummer (81.20)              | 1AA0001      |
    | ingangsdatum geldigheid (85.10) | 20190101     |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde       |
    | burgerservicenummer (01.20)     | 000000048    |
    | voornamen (02.10)               | Niels        |
    | geslachtsnaam (02.40)           | Nooitgedacht |
    | geboortedatum (03.10)           | 20190101     |
    | geboorteland (03.30)            | 6030         |
    | aktenummer (81.20)              | 1AN0001      |
    | ingangsdatum geldigheid (85.10) | 20210101     |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde       |
    | burgerservicenummer (01.20)     | 000000036    |
    | voornamen (02.10)               | Nicolas      |
    | geslachtsnaam (02.40)           | Nooitgedacht |
    | geboortedatum (03.10)           | 20200101     |
    | geboorteland (03.30)            | 6030         |
    | aktenummer (81.20)              | 1AA0001      |
    | ingangsdatum geldigheid (85.10) | 20200101     |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Nicolas    |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Nimwegen   |
    | geboortedatum (03.10)           | 20200101   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AQ0001    |
    | ingangsdatum geldigheid (85.10) | 20221101   |
    
    # Lg01_170 - man niet gehuwd/GP, 1 minderjarig kind geadopteerd, 1 minderjarig kind erkend en later erkenning vernietigd
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Nicodemus  |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Nimwegen   |
    | geboortedatum (03.10)           | 19900901   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Nola     |
    | voorvoegsel (02.30)                                | van      |
    | geslachtsnaam (02.40)                              | Nimwegen |
    | geboortedatum (03.10)                              | 19620101 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | beschrijving document (82.30)   | PK       |
    | ingangsdatum geldigheid (85.10) | 0        |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AN0001  |
    | ingangsdatum geldigheid (85.10) | 20210101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Nicolas    |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Nimwegen   |
    | geboortedatum (03.10)           | 20200101   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AQ0001    |
    | ingangsdatum geldigheid (85.10) | 20221101   |
    
    # Lg01_171 - kind, geboren uit alleen moeder, vervolgens gezag 1D, later geactualiseerd in 1, en vervolgens 1-ouder adoptie
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                            | waarde       |
    | burgerservicenummer (01.20)     | 000000036    |
    | voornamen (02.10)               | Nicolas      |
    | geslachtsnaam (02.40)           | Nooitgedacht |
    | geboortedatum (03.10)           | 20200101     |
    | geboorteland (03.30)            | 6030         |
    | geslachtsaanduiding (04.10)     | M            |
    | aktenummer (81.20)              | 1AA0001      |
    | ingangsdatum geldigheid (85.10) | 20200101     |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Nicolas    |
    | voorvoegsel (02.30)             | van        |
    | geslachtsnaam (02.40)           | Nimwegen   |
    | geboortedatum (03.10)           | 20200101   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AQ0001    |
    | ingangsdatum geldigheid (85.10) | 20221101   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde       |
    | burgerservicenummer (01.20)                        | 000000012    |
    | voornamen (02.10)                                  | Nicolette    |
    | geslachtsnaam (02.40)                              | Nooitgedacht |
    | geboortedatum (03.10)                              | 19901201     |
    | geboorteland (03.30)                               | 6030         |
    | geslachtsaanduiding (04.10)                        | V            |
    | aktenummer (81.20)                                 | 1AA0001      |
    | ingangsdatum geldigheid (85.10)                    | 20200101     |
    | datum ingang familierechtelijke betrekking (62.10) | 20200101     |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0001  |
    | ingangsdatum geldigheid (85.10) | 20200101 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Nicodemus  |
    | voorvoegsel (02.30)                                | van        |
    | geslachtsnaam (02.40)                              | Nimwegen   |
    | geboortedatum (03.10)                              | 19900901   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AQ0001    |
    | ingangsdatum geldigheid (85.10)                    | 20221101   |
    | datum ingang familierechtelijke betrekking (62.10) | 20221101   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20200101 |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
    | naam                                 | waarde                     |
    | indicatie gezag minderjarige (32.10) | 1D                         |
    | beschrijving document (82.30)        | kennsigeving gezagregister |
    | ingangsdatum geldigheid (85.10)      | 20200201                   |
    En de 'gezagsverhouding' is gewijzigd naar de volgende gegevens
    | naam                                 | waarde                     |
    | indicatie gezag minderjarige (32.10) | 1                          |
    | beschrijving document (82.30)        | kennsigeving gezagregister |
    | ingangsdatum geldigheid (85.10)      | 20221101                   |
    
    # Lg01_172 - kind geboren uit alleen moeder, erkend door vader, vervolgens gezag 12 en daarna vernietiging erkenning
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
    | naam                            | waarde       |
    | burgerservicenummer (01.20)     | 000000048    |
    | voornamen (02.10)               | Niels        |
    | geslachtsnaam (02.40)           | Nooitgedacht |
    | geboortedatum (03.10)           | 20190101     |
    | geboorteland (03.30)            | 6030         |
    | geslachtsaanduiding (04.10)     | M            |
    | aktenummer (81.20)              | 1AA0001      |
    | ingangsdatum geldigheid (85.10) | 20190101     |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde       |
    | burgerservicenummer (01.20)     | 000000048    |
    | voornamen (02.10)               | Niels        |
    | geslachtsnaam (02.40)           | Nooitgedacht |
    | geboortedatum (03.10)           | 20190101     |
    | geboorteland (03.30)            | 6030         |
    | geslachtsaanduiding (04.10)     | M            |
    | aktenummer (81.20)              | 1AN0001      |
    | ingangsdatum geldigheid (85.10) | 20210101     |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde       |
    | burgerservicenummer (01.20)                        | 000000012    |
    | voornamen (02.10)                                  | Nicolette    |
    | geslachtsnaam (02.40)                              | Nooitgedacht |
    | geboortedatum (03.10)                              | 19901201     |
    | geboorteland (03.30)                               | 6030         |
    | geslachtsaanduiding (04.10)                        | V            |
    | aktenummer (81.20)                                 | 1AA0001      |
    | ingangsdatum geldigheid (85.10)                    | 20190101     |
    | datum ingang familierechtelijke betrekking (62.10) | 20190101     |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0001  |
    | ingangsdatum geldigheid (85.10) | 20190101 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AN0001  |
    | ingangsdatum geldigheid (85.10) | 20210101 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20190101 |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
    | naam                                 | waarde                     |
    | indicatie gezag minderjarige (32.10) | 12                         |
    | beschrijving document (82.30)        | kennsigeving gezagregister |
    | ingangsdatum geldigheid (85.10)      | 20210501                   |


  Scenario: Lg01_169 - vrouw niet gehuwd/GP, 1 minderjarig kind geadopteerd, 1 minderjarig kind erkend en later erkenning vernietigd
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000012 |
    Dan heeft de response de volgende gezagsrelaties
    | bsnMinderjarige | soortGezag | bsnMeerderjarige |
    | 000000036       | OG2        | 000000012        |
    | 000000048       | OG1        | 000000012        |

  Scenario: Lg01_170 - man niet gehuwd/GP, 1 minderjarig kind geadopteerd, 1 minderjarig kind erkend en later erkenning vernietigd
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000024 |
    Dan heeft de response de volgende gezagsrelaties
    | bsnMinderjarige | soortGezag | bsnMeerderjarige |
    | 000000036       | OG2        | 000000024        |

  Scenario: Lg01_171 - kind, geboren uit alleen moeder, vervolgens gezag 1D, later geactualiseerd in 1, en vervolgens 1-ouder adoptie
    # Route: 33 - Wie heeft gezag?: beide ouders (OG2)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000036 |
    Dan heeft de response de volgende gezagsrelaties
    | bsnMinderjarige | soortGezag | bsnMeerderjarige |
    | 000000036       | OG2        | 000000012        |
    | 000000036       | OG2        | 000000024        |

  Scenario: Lg01_172 - kind geboren uit alleen moeder, erkend door vader, vervolgens gezag 12 en daarna vernietiging erkenning
    # Route: 18o1 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000048 |
    Dan heeft de response de volgende gezagsrelaties
    | bsnMinderjarige | soortGezag | bsnMeerderjarige |
    | 000000048       | OG1        | 000000012        |
