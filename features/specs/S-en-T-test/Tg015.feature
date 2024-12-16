#language: nl

Functionaliteit: Tg015 - Oei


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
      | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
      | 518                  | Turfmarkt          | 29                 | 0518200000583545                           |
    
    # Lg01_085 - ongehuwde vrouw, overleden, één minderjarig niet erkend kind
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Ottolien |
      | geslachtsnaam (02.40)           | Oei      |
      | geboortedatum (03.10)           | 19820301 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | V        |
      | beschrijving document (82.30)   | PKA      |
      | ingangsdatum geldigheid (85.10) | 0        |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde   |
      | voornamen (02.10)                                  | Olga     |
      | geslachtsnaam (02.40)                              | Oei      |
      | geboortedatum (03.10)                              | 19620105 |
      | geboorteland (03.30)                               | 6030     |
      | geslachtsaanduiding (04.10)                        | V        |
      | beschrijving document (82.30)                      | PK       |
      | ingangsdatum geldigheid (85.10)                    | 0        |
      | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde |
      | beschrijving document (82.30)   | PK     |
      | ingangsdatum geldigheid (85.10) | 0      |
    En de persoon heeft de volgende 'overlijden' gegevens
      | naam                            | waarde   |
      | datum overlijden (08.10)        | 20210801 |
      | aktenummer (81.20)              | 2AA0100  |
      | ingangsdatum geldigheid (85.10) | 20210801 |
    En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                 | waarde   |
      | datum opschorting bijhouding (67.10) | 20210801 |
      | reden opschorting bijhouding (67.20) | O        |
      | indicatie geheim (70.10)             | 0        |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000024 |
      | voornamen (02.10)               | Olinka    |
      | geslachtsnaam (02.40)           | Oei       |
      | geboortedatum (03.10)           | 20200606  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0102   |
      | ingangsdatum geldigheid (85.10) | 20200606  |
    
    # Lg01_086 - minderjarig kind, niet erkend, moeder overleden
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Olinka   |
      | geslachtsnaam (02.40)           | Oei      |
      | geboortedatum (03.10)           | 20200606 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | V        |
      | aktenummer (81.20)              | 1AA0102  |
      | ingangsdatum geldigheid (85.10) | 20200606 |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde    |
      | burgerservicenummer (01.20)                        | 000000012 |
      | voornamen (02.10)                                  | Ottolien  |
      | geslachtsnaam (02.40)                              | Oei       |
      | geboortedatum (03.10)                              | 19820301  |
      | geboorteland (03.30)                               | 6030      |
      | geslachtsaanduiding (04.10)                        | V         |
      | aktenummer (81.20)                                 | 1AA0102   |
      | ingangsdatum geldigheid (85.10)                    | 20200606  |
      | datum ingang familierechtelijke betrekking (62.10) | 20200606  |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde   |
      | aktenummer (81.20)              | 1AA0102  |
      | ingangsdatum geldigheid (85.10) | 20200606 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20200606 |


  Scenario: Lg01_085 - ongehuwde vrouw, overleden, één minderjarig niet erkend kind
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    En heeft de persoon geen gezag

  Scenario: Lg01_086 - minderjarig kind, niet erkend, moeder overleden
    # Route: 39 - Wie heeft gezag?: geen gezag (G)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde             |
      | type                             | TijdelijkGeenGezag |
      | minderjarige.burgerservicenummer | 000000024          |
