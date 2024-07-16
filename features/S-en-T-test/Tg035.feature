#language: nl

Functionaliteit: Tg035 - Saasveld-Suijdema - Vanuit gezamenlijk gezag komt moeder te overlijden


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 77                 | 0518200000583569                           |
    
    # Lg01_185 - 1 ouder, moeder gehuwd,moeder alleen gezag, moeder overleden
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Simone     |
    | geslachtsnaam (02.40)           | Saasveld   |
    | geboortedatum (03.10)           | 19981201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0202    |
    | ingangsdatum geldigheid (85.10) | 19981201   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde                         |
    | burgerservicenummer (01.20)     | 000000012                      |
    | voornamen (02.10)               | Simone                         |
    | geslachtsnaam (02.40)           | Saasveld                       |
    | geboortedatum (03.10)           | 19981201                       |
    | geboorteland (03.30)            | 6030                           |
    | geslachtsaanduiding (04.10)     | V                              |
    | beschrijving document (82.30)   | Melding Minister van Financien |
    | ingangsdatum geldigheid (85.10) | 19981212                       |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Sarina   |
    | geslachtsnaam (02.40)                              | Saasveld |
    | geboortedatum (03.10)                              | 19700201 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | aktenummer (81.20)                                 | 1AA0202  |
    | ingangsdatum geldigheid (85.10)                    | 19981201 |
    | datum ingang familierechtelijke betrekking (62.10) | 19981201 |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0202  |
    | ingangsdatum geldigheid (85.10) | 19981201 |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000024  |
    | voornamen (02.10)                                                   | Saskia     |
    | geslachtsnaam (02.40)                                               | Suijdema   |
    | geboortedatum (03.10)                                               | 19890401   |
    | geboorteland (03.30)                                                | 6030       |
    | geslachtsaanduiding (04.10)                                         | V          |
    | aktenummer (81.20)                                                  | 3AA0101    |
    | ingangsdatum geldigheid (85.10)                                     | 20170101   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20170101   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de persoon heeft de volgende 'overlijden' gegevens
    | naam                            | waarde   |
    | datum overlijden (08.10)        | 20200801 |
    | aktenummer (81.20)              | 2AA0100  |
    | ingangsdatum geldigheid (85.10) | 20200801 |
    En de persoon heeft de volgende 'inschrijving' gegevens
    | naam                                 | waarde                     |
    | datum opschorting bijhouding (67.10) | 20200801                   |
    | reden opschorting bijhouding (67.20) | O                          |
    | indicatie geheim (70.10)             | 0                          |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Sanne      |
    | geslachtsnaam (02.40)           | Saasveld   |
    | geboortedatum (03.10)           | 20181201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0110    |
    | ingangsdatum geldigheid (85.10) | 20181201   |
    
    # Lg01_186 - huwelijkspartner van vrouw, van rechtswege gezag
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Saskia     |
    | geslachtsnaam (02.40)           | Suijdema   |
    | geboortedatum (03.10)           | 19890401   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Suus     |
    | geslachtsnaam (02.40)                              | Suijdema |
    | geboortedatum (03.10)                              | 19650901 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | beschrijving document (82.30)   | PK       |
    | ingangsdatum geldigheid (85.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000012  |
    | voornamen (02.10)                                                   | Simone     |
    | geslachtsnaam (02.40)                                               | Saasveld   |
    | geboortedatum (03.10)                                               | 19981201   |
    | geboorteland (03.30)                                                | 6030       |
    | geslachtsaanduiding (04.10)                                         | V          |
    | aktenummer (81.20)                                                  | 3AA0101    |
    | ingangsdatum geldigheid (85.10)                                     | 20170101   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20170101   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    
    # Lg01_187 - 1 ouder, moeder gehuwd, meemoeder geen ouder, moeder overleden
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Sanne      |
    | geslachtsnaam (02.40)           | Saasveld   |
    | geboortedatum (03.10)           | 20181201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0110    |
    | ingangsdatum geldigheid (85.10) | 20181201   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Simone     |
    | geslachtsnaam (02.40)                              | Saasveld   |
    | geboortedatum (03.10)                              | 19981201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0110    |
    | ingangsdatum geldigheid (85.10)                    | 20181201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20181201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0110  |
    | ingangsdatum geldigheid (85.10) | 20181201 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20181201 |


  Scenario: Lg01_185 - 1 ouder, moeder gehuwd,moeder alleen gezag, moeder overleden
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                       |
    | burgerservicenummer | 000000012                                                                    |
    | toelichting         | Ingezeten - meerderjarig - soort gezag is NVT - gezag is niet van toepassing |
    En heeft de persoon geen gezag

  Scenario: Lg01_186 - huwelijkspartner van vrouw, van rechtswege gezag
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
    | naam                             | waarde    |
    | type                             | Voogdij   |
    | minderjarige.burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |

  Scenario: Lg01_187 - 1 ouder, moeder gehuwd, meemoeder geen ouder, moeder overleden
    # Route: 43o1 - Wie heeft gezag?: meemoeder (V)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                                                                                             |
    | burgerservicenummer | 000000036                                                                                                                                          |
    | toelichting         | Ingezeten - minderjarig - heeft één ouder - ouder ongehuwd/geen partnerschap - ouder onbevoegd - soort gezag is V - de partner van ouder1 is voogd |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde    |
    | type                             | Voogdij   |
    | minderjarige.burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
