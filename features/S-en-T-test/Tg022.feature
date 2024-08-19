#language: nl

Functionaliteit: Tg022 - Barakat-Idilbi - Ouders met een onvolledige huwelijksdatum


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 47                 | 0518200000583554                           |
    
    # Lg01_122 - alleen huwelijksjaar bekend
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000012  |
    | voornamen (02.10)               | Saadallah  |
    | geslachtsnaam (02.40)           | Barakat    |
    | geboortedatum (03.10)           | 19800000   |
    | geboorteland (03.30)            | 7009       |
    | geslachtsaanduiding (04.10)     | M          |
    | beschrijving document (82.30)   | voe        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | geslachtsnaam (02.40)                              | .        |
    | beschrijving document (82.30)                      | voe      |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | geslachtsnaam (02.40)                              | .        |
    | beschrijving document (82.30)                      | voe      |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000024  |
    | voornamen (02.10)                                                   | Maryana    |
    | geslachtsnaam (02.40)                                               | Idilbi     |
    | geboortedatum (03.10)                                               | 19890000   |
    | geboorteland (03.30)                                                | 7009       |
    | geslachtsaanduiding (04.10)                                         | V          |
    | beschrijving document (82.30)                                       | voe        |
    | ingangsdatum geldigheid (85.10)                                     | 20100000   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20100000   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | Aleppo     |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 7009       |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                                 | waarde   |
    | gemeente van inschrijving (09.10)    | 518      |
    | land vanwaar ingeschreven (14.10)    | 7009     |
    | datum vestiging in Nederland (14.20) | 20131201 |
    | ingangsdatum geldigheid (85.10)      | 20131201 |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Salim      |
    | geslachtsnaam (02.40)           | Barakat    |
    | geboortedatum (03.10)           | 20140701   |
    | geboorteland (03.30)            | 6030       |
    | beschrijving document (82.30)   | PL Kind    |
    | ingangsdatum geldigheid (85.10) | 20140701   |
    
    # Lg01_123 - alleen huwelijksjaar bekend
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Maryana    |
    | geslachtsnaam (02.40)           | Idilbi     |
    | geboortedatum (03.10)           | 19890000   |
    | geboorteland (03.30)            | 7009       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | voe        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | geslachtsnaam (02.40)                              | .        |
    | beschrijving document (82.30)                      | voe      |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | geslachtsnaam (02.40)                              | .        |
    | beschrijving document (82.30)                      | voe      |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000012  |
    | voornamen (02.10)                                                   | Saadallah  |
    | geslachtsnaam (02.40)                                               | Barakat    |
    | geboortedatum (03.10)                                               | 19800000   |
    | geboorteland (03.30)                                                | 7009       |
    | geslachtsaanduiding (04.10)                                         | M          |
    | beschrijving document (82.30)                                       | voe        |
    | ingangsdatum geldigheid (85.10)                                     | 20100000   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20100000   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | Aleppo     |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 7009       |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                                 | waarde   |
    | gemeente van inschrijving (09.10)    | 518      |
    | land vanwaar ingeschreven (14.10)    | 7009     |
    | datum vestiging in Nederland (14.20) | 20131201 |
    | ingangsdatum geldigheid (85.10)      | 20131201 |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Salim      |
    | geslachtsnaam (02.40)           | Barakat    |
    | geboortedatum (03.10)           | 20140701   |
    | geboorteland (03.30)            | 6030       |
    | beschrijving document (82.30)   | PL Kind    |
    | ingangsdatum geldigheid (85.10) | 20140701   |
    
    # Lg01_124 - ouders geboren en gehuwd in buitenland, alleen jaar bekend, kind in Nederland geboren
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Salim      |
    | geslachtsnaam (02.40)           | Barakat    |
    | geboortedatum (03.10)           | 20140701   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0101    |
    | ingangsdatum geldigheid (85.10) | 20140701   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Saadallah  |
    | geslachtsnaam (02.40)                              | Barakat    |
    | geboortedatum (03.10)                              | 19800000   |
    | geboorteland (03.30)                               | 7009       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AA0102    |
    | ingangsdatum geldigheid (85.10)                    | 20140701   |
    | datum ingang familierechtelijke betrekking (62.10) | 20140701   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Maryana    |
    | geslachtsnaam (02.40)                              | Idilbi     |
    | geboortedatum (03.10)                              | 19890000   |
    | geboorteland (03.30)                               | 7009       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0102    |
    | ingangsdatum geldigheid (85.10)                    | 20140701   |
    | datum ingang familierechtelijke betrekking (62.10) | 20140701   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20140701 |


  Scenario: Lg01_122 - alleen huwelijksjaar bekend
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000036                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |

  Scenario: Lg01_123 - alleen huwelijksjaar bekend
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000036                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |

  Scenario: Lg01_124 - ouders geboren en gehuwd in buitenland, alleen jaar bekend, kind in Nederland geboren
    # Route: 49 - Wie heeft gezag?: beide ouders (OG2)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000036                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
