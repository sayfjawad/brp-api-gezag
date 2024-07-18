#language: nl

Functionaliteit: meerderjarige zonder gezag


    Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 1                  | 0518200000583526                           |
    
    
Regel: wanneer de persoon meerderjarig is en geen gezag heeft over minderjarige(n) wordt het burgerservicenummer geleverd en een lege lijst gezag  
    
  Scenario: persoon is meerderjarig en heeft geen kinderen  
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000012  |
    | voornamen (02.10)               | Annet      |
    | geslachtsnaam (02.40)           | Adema      |
    | geboortedatum (03.10)           | 19820101   |
    | geboorteland (03.30)            | 6030       |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon geen gezag

  Scenario: persoon is meerderjarig, staat onder curatele en heeft daarom geen gezag over het minderjarige kind
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Baukje     |
    | geslachtsnaam (02.40)           | Bolder     |
    | geboortedatum (03.10)           | 19750901   |
    | geboorteland (03.30)            | 6030       |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde    |
    | burgerservicenummer (01.20)     | 000000036 |
    | voornamen (02.10)               | Boudewijn |
    | geslachtsnaam (02.40)           | Bolder    |
    | geboortedatum (03.10)           | 20191205  |
    | geboorteland (03.30)            | 6030      |
    | aktenummer (81.20)              | 1AA0104   |
    | ingangsdatum geldigheid (85.10) | 20191205  |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
    | naam                               | waarde                      |
    | indicatie curateleregister (33.10) | 1                           |
    | beschrijving document (82.30)      | mededeling curateleregister |
    | ingangsdatum geldigheid (85.10)    | 20210701                    |
    En de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                        | waarde    |
    | burgerservicenummer (01.20) | 000000036 |
    | voornamen (02.10)           | Boudewijn |
    | geslachtsnaam (02.40)       | Bolder    |
    | geboortedatum (03.10)       | 20191205  |
    | geboorteland (03.30)        | 6030      |
    | aktenummer (81.20)          | 1AA0105   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Baukje     |
    | geslachtsnaam (02.40)                              | Bolder     |
    | geboortedatum (03.10)                              | 19750901   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0105    |
    | ingangsdatum geldigheid (85.10)                    | 20191205   |
    | datum ingang familierechtelijke betrekking (62.10) | 20191205   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | aktenummer (81.20)                                 | 1AA0105    |
    | ingangsdatum geldigheid (85.10)                    | 20191205   |
    | datum ingang familierechtelijke betrekking (62.10) | 20191205   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
    | naam                                 | waarde                    |
    | indicatie gezag minderjarige (32.10) | D                         |
    | beschrijving document (82.30)        | mededeling gezagsregister |
    | ingangsdatum geldigheid (85.10)      | 20210824                  |
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft de persoon geen gezag

  Scenario: persoon is meerderjarig, heeft een minderjarig kind die geëmigreerd is geweest waardoor gezag niet bepaald kan worden
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000012  |
    | voornamen (02.10)               | Desiree    |
    | geslachtsnaam (02.40)           | Donkers    |
    | geboortedatum (03.10)           | 19900301   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Daryl      |
    | geslachtsnaam (02.40)           | Donkers    |
    | geboortedatum (03.10)           | 20180301   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0100    |
    En de persoon met burgerservicenummer '000000061' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Daryl      |
    | geslachtsnaam (02.40)           | Donkers    |
    | geboortedatum (03.10)           | 20180301   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20180301   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Desiree    |
    | geslachtsnaam (02.40)                              | Donkers    |
    | geboortedatum (03.10)                              | 19900301   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0100    |
    | ingangsdatum geldigheid (85.10)                    | 20180301   |
    | datum ingang familierechtelijke betrekking (62.10) | 20180301   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | aktenummer (81.20)                                 | 1AA0100  |
    | ingangsdatum geldigheid (85.10)                    | 20180301 |
    | datum ingang familierechtelijke betrekking (62.10) | 20180301 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                                 | waarde   |
    | gemeente van inschrijving (09.10)    | 518      |
    | land vanwaar ingeschreven (14.10)    | 6027     |
    | datum vestiging in Nederland (14.20) | 20221014 |
    | ingangsdatum geldigheid (85.10)      | 20221014 |
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon geen gezag
