#language: nl

Functionaliteit: Tg020 - Carolussen-Charbon


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 43                 | 0518200000583552                           |
    
    # Lg01_114 - geregistreerd partnerschap vóór 01-04-2014, 2 minderjarige kinderen vóór 01-04-2014 en 1 minderjarig kind daarna
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000012  |
    | voornamen (02.10)               | Carel      |
    | geslachtsnaam (02.40)           | Carolussen |
    | geboortedatum (03.10)           | 19700201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Clivia   |
    | geslachtsnaam (02.40)                              | Clements |
    | geboortedatum (03.10)                              | 19450901 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | voornamen (02.10)                                  | Cees       |
    | geslachtsnaam (02.40)                              | Carolussen |
    | geboortedatum (03.10)                              | 19440201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | beschrijving document (82.30)                      | PK         |
    | ingangsdatum geldigheid (85.10)                    | 0          |
    | datum ingang familierechtelijke betrekking (62.10) | 0          |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000024  |
    | voornamen (02.10)                                                   | Charlotte  |
    | geslachtsnaam (02.40)                                               | Charbon    |
    | geboortedatum (03.10)                                               | 19720310   |
    | geboorteland (03.30)                                                | 6030       |
    | geslachtsaanduiding (04.10)                                         | V          |
    | aktenummer (81.20)                                                  | 5AA0101    |
    | ingangsdatum geldigheid (85.10)                                     | 20091201   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20091201   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Christiaan |
    | geslachtsnaam (02.40)           | Charbon    |
    | geboortedatum (03.10)           | 20150901   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0103    |
    | ingangsdatum geldigheid (85.10) | 20150901   |
    
    # Lg01_115 - geregistreerd partnerschap vóór 01-04-2014, 2 minderjarige kinderen vóór 01-04-2014 en 1 minderjarig kind daarna
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Charlotte  |
    | geslachtsnaam (02.40)           | Charbon    |
    | geboortedatum (03.10)           | 19720310   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde      |
    | voornamen (02.10)                                  | Caroline    |
    | geslachtsnaam (02.40)                              | Courvoisier |
    | geboortedatum (03.10)                              | 19480223    |
    | geboorteland (03.30)                               | 6030        |
    | geslachtsaanduiding (04.10)                        | V           |
    | beschrijving document (82.30)                      | PK          |
    | ingangsdatum geldigheid (85.10)                    | 0           |
    | datum ingang familierechtelijke betrekking (62.10) | 0           |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Charles  |
    | geslachtsnaam (02.40)                              | Charbon  |
    | geboortedatum (03.10)                              | 19431201 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000012  |
    | voornamen (02.10)                                                   | Carel      |
    | geslachtsnaam (02.40)                                               | Carolussen |
    | geboortedatum (03.10)                                               | 19700201   |
    | geboorteland (03.30)                                                | 6030       |
    | geslachtsaanduiding (04.10)                                         | M          |
    | aktenummer (81.20)                                                  | 5AA0101    |
    | ingangsdatum geldigheid (85.10)                                     | 20091201   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20091201   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Cecilia    |
    | geslachtsnaam (02.40)           | Charbon    |
    | geboortedatum (03.10)           | 20110601   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0101    |
    | ingangsdatum geldigheid (85.10) | 20110601   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Chantal    |
    | geslachtsnaam (02.40)           | Charbon    |
    | geboortedatum (03.10)           | 20120801   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0102    |
    | ingangsdatum geldigheid (85.10) | 20120801   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Christiaan |
    | geslachtsnaam (02.40)           | Charbon    |
    | geboortedatum (03.10)           | 20150901   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0103    |
    | ingangsdatum geldigheid (85.10) | 20150901   |
    
    # Lg01_116 - ouders geregistreerd partnerschap vóór 01-04-204, alleen Ouder1 gevuld
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Cecilia    |
    | geslachtsnaam (02.40)           | Charbon    |
    | geboortedatum (03.10)           | 20110601   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0101    |
    | ingangsdatum geldigheid (85.10) | 20110601   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Charlotte  |
    | geslachtsnaam (02.40)                              | Charbon    |
    | geboortedatum (03.10)                              | 19720310   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0101    |
    | ingangsdatum geldigheid (85.10)                    | 20110601   |
    | datum ingang familierechtelijke betrekking (62.10) | 20110601   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0101  |
    | ingangsdatum geldigheid (85.10) | 20110601 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    
    # Lg01_117 - ouders geregistreerd partnerschap vóór 01-04-204, alleen Ouder2 gevuld
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Chantal    |
    | geslachtsnaam (02.40)           | Charbon    |
    | geboortedatum (03.10)           | 20120801   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0102    |
    | ingangsdatum geldigheid (85.10) | 20120801   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0102  |
    | ingangsdatum geldigheid (85.10) | 20120801 |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Charlotte  |
    | geslachtsnaam (02.40)                              | Charbon    |
    | geboortedatum (03.10)                              | 19720310   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0102    |
    | ingangsdatum geldigheid (85.10)                    | 20120801   |
    | datum ingang familierechtelijke betrekking (62.10) | 20120801   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20120801 |
    
    # Lg01_118 - ouders geregistreerd partnerschap na 01-04-2014, 2 ouders
    Gegeven de persoon met burgerservicenummer '000000061' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Christiaan |
    | geslachtsnaam (02.40)           | Charbon    |
    | geboortedatum (03.10)           | 20150901   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0103    |
    | ingangsdatum geldigheid (85.10) | 20150901   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Charlotte  |
    | geslachtsnaam (02.40)                              | Charbon    |
    | geboortedatum (03.10)                              | 19720310   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0103    |
    | ingangsdatum geldigheid (85.10)                    | 20150901   |
    | datum ingang familierechtelijke betrekking (62.10) | 20150901   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Carel      |
    | geslachtsnaam (02.40)                              | Carolussen |
    | geboortedatum (03.10)                              | 19700201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AA0103    |
    | ingangsdatum geldigheid (85.10)                    | 20150901   |
    | datum ingang familierechtelijke betrekking (62.10) | 20150901   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20150901 |


  Scenario: Lg01_114 - geregistreerd partnerschap vóór 01-04-2014, 2 minderjarige kinderen vóór 01-04-2014 en 1 minderjarig kind daarna
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
    | naam                             | waarde           |
    | type                             | GezamenlijkGezag |
    | minderjarige.burgerservicenummer | 000000036        |
    | ouder.burgerservicenummer        | 000000024        |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde           |
    | type                             | GezamenlijkGezag |
    | minderjarige.burgerservicenummer | 000000048        |
    | ouder.burgerservicenummer        | 000000024        |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000061                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |

  Scenario: Lg01_115 - geregistreerd partnerschap vóór 01-04-2014, 2 minderjarige kinderen vóór 01-04-2014 en 1 minderjarig kind daarna
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                       |
    | burgerservicenummer | 000000024                                                                    |
    | toelichting         | Ingezeten - meerderjarig - soort gezag is NVT - gezag is niet van toepassing |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde           |
    | type                             | GezamenlijkGezag |
    | minderjarige.burgerservicenummer | 000000036        |
    | ouder.burgerservicenummer        | 000000024        |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde           |
    | type                             | GezamenlijkGezag |
    | minderjarige.burgerservicenummer | 000000048        |
    | ouder.burgerservicenummer        | 000000024        |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000061                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |

  Scenario: Lg01_116 - ouders geregistreerd partnerschap vóór 01-04-204, alleen Ouder1 gevuld
    # Route: 41 - Wie heeft gezag?: Ouder1 + partner (GG)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                                                                                                 |
    | burgerservicenummer | 000000036                                                                                                                                              |
    | toelichting         | Ingezeten - minderjarig - heeft één ouder - gehuwd/partnerschap - ouder en partner zijn bevoegd tot gezag - soort gezag is GG - ouder en partner gezag |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde           |
    | type                             | GezamenlijkGezag |
    | minderjarige.burgerservicenummer | 000000036        |
    | ouder.burgerservicenummer        | 000000024        |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |

  Scenario: Lg01_117 - ouders geregistreerd partnerschap vóór 01-04-204, alleen Ouder2 gevuld
    # Route: 41 - Wie heeft gezag?: Ouder2 + partner (GG)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                                                                                                 |
    | burgerservicenummer | 000000048                                                                                                                                              |
    | toelichting         | Ingezeten - minderjarig - heeft één ouder - gehuwd/partnerschap - ouder en partner zijn bevoegd tot gezag - soort gezag is GG - ouder en partner gezag |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde           |
    | type                             | GezamenlijkGezag |
    | minderjarige.burgerservicenummer | 000000048        |
    | ouder.burgerservicenummer        | 000000024        |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |

  Scenario: Lg01_118 - ouders geregistreerd partnerschap na 01-04-2014, 2 ouders
    # Route: 49 - Wie heeft gezag?: beide ouders (OG2)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                                                                             |
    | burgerservicenummer | 000000061                                                                                                                          |
    | toelichting         | Ingezeten - minderjarig - heeft twee ouders - gehuwd/partnerschap - beide ouders bevoegd - soort gezag is OG2 - beide ouders gezag |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000061                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
