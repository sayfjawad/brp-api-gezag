#language: nl

Functionaliteit: Tg027 - Janssen-Joosten- Erkenning voor, bij en na geboorteaangifte en ongehuwde ouders en vaststelling vaderschap. Erkenning voor en na 2023-01-01. Cat.11 na erkenning en bij aangifte geboorte.


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 61                 | 0518200000583561                           |
    
    # Lg01_145 - ongehuwde ouders met 6 kinderen geboren voor en na 01-01-2023, 5 kinderen erkend, 2 kinderen vaststelling vaderschap
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Joop       |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 19890727   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Joke     |
    | geslachtsnaam (02.40)                              | Janssen  |
    | geboortedatum (03.10)                              | 19620319 |
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
    En de persoon heeft een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000097  |
    | voornamen (02.10)               | Jettie     |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0502    |
    | ingangsdatum geldigheid (85.10) | 20211231   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Janneke    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0501    |
    | ingangsdatum geldigheid (85.10) | 20230105   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Jantina    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0500    |
    | ingangsdatum geldigheid (85.10) | 20230105   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000103  |
    | voornamen (02.10)               | Joy        |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20200601   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AV0501    |
    | ingangsdatum geldigheid (85.10) | 20200601   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Johanna    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20200601   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AV0500    |
    | ingangsdatum geldigheid (85.10) | 20200601   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Jolanda    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20230106   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0011    |
    | ingangsdatum geldigheid (85.10) | 20230106   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Jacoba     |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20230106   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0010    |
    | ingangsdatum geldigheid (85.10) | 20230106   |
    
    # Lg01_146 - ongehuwde ouders met 6 kinderen geboren voor en na 01-01-2023, 5 kinderen erkend, 2 kinderen vaststelling vaderschap
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Josephien  |
    | geslachtsnaam (02.40)           | Joosten    |
    | geboortedatum (03.10)           | 19900830   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Josje    |
    | geslachtsnaam (02.40)                              | Joosten  |
    | geboortedatum (03.10)                              | 19690821 |
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
    En de persoon heeft een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000097  |
    | voornamen (02.10)               | Jettie     |
    | geslachtsnaam (02.40)           | Joosten    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0502    |
    | ingangsdatum geldigheid (85.10) | 20211201   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000097  |
    | voornamen (02.10)               | Jettie     |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0502    |
    | ingangsdatum geldigheid (85.10) | 20211231   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Janneke    |
    | geslachtsnaam (02.40)           | Joosten    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0501    |
    | ingangsdatum geldigheid (85.10) | 20211201   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Janneke    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0501    |
    | ingangsdatum geldigheid (85.10) | 20230105   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Jantina    |
    | geslachtsnaam (02.40)           | Joosten    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0500    |
    | ingangsdatum geldigheid (85.10) | 20211201   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Jantina    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0500    |
    | ingangsdatum geldigheid (85.10) | 20230105   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000103  |
    | voornamen (02.10)               | Joy        |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20200601   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AV0501    |
    | ingangsdatum geldigheid (85.10) | 20200601   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Johanna    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20200601   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AV0500    |
    | ingangsdatum geldigheid (85.10) | 20200601   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Jolanda    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20230106   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0011    |
    | ingangsdatum geldigheid (85.10) | 20230106   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Jacoba     |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20230106   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0010    |
    | ingangsdatum geldigheid (85.10) | 20230106   |
    
    # Lg01_147 - kind geboren uit ongehuwde ouders na 01-01- 2023, erkenning ongeboren vrucht,  indicatie gezag
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Jacoba     |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20230106   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0010    |
    | ingangsdatum geldigheid (85.10) | 20230106   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Josephien  |
    | geslachtsnaam (02.40)                              | Joosten    |
    | geboortedatum (03.10)                              | 19900830   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0010    |
    | ingangsdatum geldigheid (85.10)                    | 20230106   |
    | datum ingang familierechtelijke betrekking (62.10) | 20230106   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Joop       |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19890727   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AA0010    |
    | ingangsdatum geldigheid (85.10)                    | 20230106   |
    | datum ingang familierechtelijke betrekking (62.10) | 20230106   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20230106 |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
    | naam                                 | waarde            |
    | indicatie gezag minderjarige (32.10) | 1                 |
    | beschrijving document (82.30)        | overgangsregeling |
    | ingangsdatum geldigheid (85.10)      | 20230106          |
    
    # Lg01_148 - kind geboren uit ongehuwde ouders na 01-01- 2023, erkenning ongeboren vrucht,  geen indicatie gezag
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Jolanda    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20230106   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0011    |
    | ingangsdatum geldigheid (85.10) | 20230106   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Josephien  |
    | geslachtsnaam (02.40)                              | Joosten    |
    | geboortedatum (03.10)                              | 19900830   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0011    |
    | ingangsdatum geldigheid (85.10)                    | 20230106   |
    | datum ingang familierechtelijke betrekking (62.10) | 20230106   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Joop       |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19890727   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AA0011    |
    | ingangsdatum geldigheid (85.10)                    | 20230106   |
    | datum ingang familierechtelijke betrekking (62.10) | 20230106   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20230106 |
    
    # Lg01_149 - kind geboren uit ongehuwde ouders voor 01-01-2023, vaststelling ouderschap
    Gegeven de persoon met burgerservicenummer '000000061' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Johanna    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20200601   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AV0500    |
    | ingangsdatum geldigheid (85.10) | 20200601   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Josephien  |
    | geslachtsnaam (02.40)                              | Joosten    |
    | geboortedatum (03.10)                              | 19900830   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0500    |
    | ingangsdatum geldigheid (85.10)                    | 20200601   |
    | datum ingang familierechtelijke betrekking (62.10) | 20200601   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Joop       |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19890727   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AV0500    |
    | ingangsdatum geldigheid (85.10)                    | 20200601   |
    | datum ingang familierechtelijke betrekking (62.10) | 20200601   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20200601 |
    
    # Lg01_150 - kind geboren uit ongehuwde ouders voor 01-01-2023, erkenning na 01-01-2023, aantekening gezag
    Gegeven de persoon met burgerservicenummer '000000073' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Jantina    |
    | geslachtsnaam (02.40)           | Joosten    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0500    |
    | ingangsdatum geldigheid (85.10) | 20211201   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Jantina    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AC0500    |
    | ingangsdatum geldigheid (85.10) | 20230105   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Josephien  |
    | geslachtsnaam (02.40)                              | Joosten    |
    | geboortedatum (03.10)                              | 19900830   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0500    |
    | ingangsdatum geldigheid (85.10)                    | 20211201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20211201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0500  |
    | ingangsdatum geldigheid (85.10) | 20211201 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Joop       |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19890727   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AC0500    |
    | ingangsdatum geldigheid (85.10)                    | 20230105   |
    | datum ingang familierechtelijke betrekking (62.10) | 20230105   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20211201 |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
    | naam                                 | waarde             |
    | indicatie gezag minderjarige (32.10) | 1                  |
    | beschrijving document (82.30)        | kennisgeving gezag |
    | ingangsdatum geldigheid (85.10)      | 20230210           |
    
    # Lg01_151 - kind geboren uit ongehuwde ouders voor 01-01-2023, erkenning na 01-01-2023, geen aantekening gezag
    Gegeven de persoon met burgerservicenummer '000000085' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Janneke    |
    | geslachtsnaam (02.40)           | Joosten    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0501    |
    | ingangsdatum geldigheid (85.10) | 20211201   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Janneke    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AC0501    |
    | ingangsdatum geldigheid (85.10) | 20230105   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Josephien  |
    | geslachtsnaam (02.40)                              | Joosten    |
    | geboortedatum (03.10)                              | 19900830   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0501    |
    | ingangsdatum geldigheid (85.10)                    | 20211201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20211201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0501  |
    | ingangsdatum geldigheid (85.10) | 20211201 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Joop       |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19890727   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AC0501    |
    | ingangsdatum geldigheid (85.10)                    | 20230105   |
    | datum ingang familierechtelijke betrekking (62.10) | 20230105   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20211201 |
    
    # Lg01_152 - kind geboren uit ongehuwde ouders voor 01-01-2023, erkenning voor 01-01-2023, geen aantekening gezag
    Gegeven de persoon met burgerservicenummer '000000097' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Jettie     |
    | geslachtsnaam (02.40)           | Joosten    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0502    |
    | ingangsdatum geldigheid (85.10) | 20211201   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000097  |
    | voornamen (02.10)               | Jettie     |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20211201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AC0502    |
    | ingangsdatum geldigheid (85.10) | 20211231   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Josephien  |
    | geslachtsnaam (02.40)                              | Joosten    |
    | geboortedatum (03.10)                              | 19900830   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0502    |
    | ingangsdatum geldigheid (85.10)                    | 20211201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20211201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0502  |
    | ingangsdatum geldigheid (85.10) | 20211201 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Joop       |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19890727   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AC0502    |
    | ingangsdatum geldigheid (85.10)                    | 20211231   |
    | datum ingang familierechtelijke betrekking (62.10) | 20211231   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20211201 |
    
    # Lg01_215 - kind geboren uit ongehuwde ouders voor 01-01-2023, vaststelling ouderschap, moeder categorie 02
    Gegeven de persoon met burgerservicenummer '000000103' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Joy        |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20200601   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AV0501    |
    | ingangsdatum geldigheid (85.10) | 20200601   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Joop       |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19890727   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AV0501    |
    | ingangsdatum geldigheid (85.10)                    | 20200601   |
    | datum ingang familierechtelijke betrekking (62.10) | 20200601   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Josephien  |
    | geslachtsnaam (02.40)                              | Joosten    |
    | geboortedatum (03.10)                              | 19900830   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0501    |
    | ingangsdatum geldigheid (85.10)                    | 20200601   |
    | datum ingang familierechtelijke betrekking (62.10) | 20200601   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20200601 |


  Scenario: Lg01_145 - ongehuwde ouders met 6 kinderen geboren voor en na 01-01-2023, 5 kinderen erkend, 2 kinderen vaststelling vaderschap
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon geen gezag

  Scenario: Lg01_146 - ongehuwde ouders met 6 kinderen geboren voor en na 01-01-2023, 5 kinderen erkend, 2 kinderen vaststelling vaderschap
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000036                |
    | ouder.burgerservicenummer        | 000000024                |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000048                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 999770501 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000061                |
    | ouder.burgerservicenummer        | 000000024                |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000073                |
    | ouder.burgerservicenummer        | 000000024                |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000085                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 999770501 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000097                |
    | ouder.burgerservicenummer        | 000000024                |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000103                |
    | ouder.burgerservicenummer        | 000000024                |

  Scenario: Lg01_147 - kind geboren uit ongehuwde ouders na 01-01- 2023, erkenning ongeboren vrucht,  indicatie gezag
    # Route: 11 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000036                |
    | ouder.burgerservicenummer        | 000000024                |

  Scenario: Lg01_148 - kind geboren uit ongehuwde ouders na 01-01- 2023, erkenning ongeboren vrucht,  geen indicatie gezag
    # Route: 54n - Wie heeft gezag?: beide ouders (OG2)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000048                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 999770501 |

  Scenario: Lg01_149 - kind geboren uit ongehuwde ouders voor 01-01-2023, vaststelling ouderschap
    # Route: 54v1 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000061 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000061                |
    | ouder.burgerservicenummer        | 000000024                |

  Scenario: Lg01_150 - kind geboren uit ongehuwde ouders voor 01-01-2023, erkenning na 01-01-2023, aantekening gezag
    # Route: 11 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000073 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000073 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000073                |
    | ouder.burgerservicenummer        | 000000024                |

  Scenario: Lg01_151 - kind geboren uit ongehuwde ouders voor 01-01-2023, erkenning na 01-01-2023, geen aantekening gezag
    # Route: 54n - Wie heeft gezag?: beide ouders (OG2)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000085 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000085 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000085                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 999770501 |

  Scenario: Lg01_152 - kind geboren uit ongehuwde ouders voor 01-01-2023, erkenning voor 01-01-2023, geen aantekening gezag
    # Route: 54v1 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000097 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000097 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000097                |
    | ouder.burgerservicenummer        | 000000024                |

  Scenario: Lg01_215 - kind geboren uit ongehuwde ouders voor 01-01-2023, vaststelling ouderschap, moeder categorie 02
    # Route: 54v2 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000103 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000103 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000103                |
    | ouder.burgerservicenummer        | 000000024                |
