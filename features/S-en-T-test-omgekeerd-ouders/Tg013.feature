#language: nl

Functionaliteit: Tg013 - Maasland-Meindersma-Meer-Meulengraaf-Mann - Bijzondere Aktenummers


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 25                 | 0518200000583542                           |
    Gegeven adres 'A2' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | huisletter (11.30) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 25                 | a                  | 0518200000583543                           |
    Gegeven adres 'A3' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 25                 | 0518200000583543                           |
    
    # Lg01_070 - ongehuwde vrouw met 1 minderjarig kind (erkend, erkenning vernietigd en opnieuw erkend)
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Monique    |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 19900201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Mees     |
    | geslachtsnaam (02.40)                              | Maasland |
    | geboortedatum (03.10)                              | 19600201 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Mona     |
    | geslachtsnaam (02.40)                              | Manziek  |
    | geboortedatum (03.10)                              | 19650201 |
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
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Mark       |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20140201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20140201   |
    En het 'kind' is gecorrigeerd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Mark       |
    | geslachtsnaam (02.40)           | Meindersma |
    | geboortedatum (03.10)           | 20140201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0100    |
    | ingangsdatum geldigheid (85.10) | 20140301   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Mark       |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20140201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AN0100    |
    | ingangsdatum geldigheid (85.10) | 20140301   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Mark       |
    | voorvoegsel (02.30)             | van der    |
    | geslachtsnaam (02.40)           | Meer       |
    | geboortedatum (03.10)           | 20140201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0100    |
    | ingangsdatum geldigheid (85.10) | 20160201   |
    
    # Lg01_071 - ongehuwde man met vernietiging erkenning
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Maurits    |
    | geslachtsnaam (02.40)           | Meindersma |
    | geboortedatum (03.10)           | 19800201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | voornamen (02.10)                                  | Mathias    |
    | geslachtsnaam (02.40)                              | Meindersma |
    | geboortedatum (03.10)                              | 19590201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | beschrijving document (82.30)                      | PK         |
    | ingangsdatum geldigheid (85.10)                    | 0          |
    | datum ingang familierechtelijke betrekking (62.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | voornamen (02.10)                                  | Mina       |
    | voorvoegsel (02.30)                                | van        |
    | geslachtsnaam (02.40)                              | Molkenboer |
    | geboortedatum (03.10)                              | 19630201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | beschrijving document (82.30)                      | PK         |
    | ingangsdatum geldigheid (85.10)                    | 0          |
    | datum ingang familierechtelijke betrekking (62.10) | 0          |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Mark       |
    | geslachtsnaam (02.40)           | Meindersma |
    | geboortedatum (03.10)           | 20140201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0100    |
    | ingangsdatum geldigheid (85.10) | 20140301   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AN0100  |
    | ingangsdatum geldigheid (85.10) | 20140301 |
    
    # Lg01_072 - minderjarig kind, erkenning, vernietiging erkenning, nieuwe erkenning, cat 11 in historie 12, actueel leeg (vernietiging erkenning)
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Mark       |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20140201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20140201   |
    En de persoon is gecorrigeerd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Mark       |
    | geslachtsnaam (02.40)           | Meindersma |
    | geboortedatum (03.10)           | 20140201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AC0100    |
    | ingangsdatum geldigheid (85.10) | 20140301   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Mark       |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20140201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AN0100    |
    | ingangsdatum geldigheid (85.10) | 20140301   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Mark       |
    | voorvoegsel (02.30)             | van der    |
    | geslachtsnaam (02.40)           | Meer       |
    | geboortedatum (03.10)           | 20140201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AC0100    |
    | ingangsdatum geldigheid (85.10) | 20160201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0100  |
    | ingangsdatum geldigheid (85.10) | 20140201 |
    En de ouder '2' is gecorrigeerd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000024  |
    | voornamen (02.10)                                  | Maurits    |
    | geslachtsnaam (02.40)                              | Meindersma |
    | geboortedatum (03.10)                              | 19800201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AC0100    |
    | ingangsdatum geldigheid (85.10)                    | 20140301   |
    | datum ingang familierechtelijke betrekking (62.10) | 20140301   |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AN0100  |
    | ingangsdatum geldigheid (85.10) | 20140301 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000048  |
    | voornamen (02.10)                                  | Mike       |
    | voorvoegsel (02.30)                                | van der    |
    | geslachtsnaam (02.40)                              | Meer       |
    | geboortedatum (03.10)                              | 19890201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AC0100    |
    | ingangsdatum geldigheid (85.10)                    | 20160201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20160201   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Monique    |
    | geslachtsnaam (02.40)                              | Maasland   |
    | geboortedatum (03.10)                              | 19900201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0100    |
    | ingangsdatum geldigheid (85.10)                    | 20140201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20140201   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20140201 |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
    | naam                            | waarde                 |
    | beschrijving document (82.30)   | vernietiging erkenning |
    | ingangsdatum geldigheid (85.10) | 20140401               |
    
    # Lg01_073 - ongehuwde man met erkend kind
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Mike       |
    | voorvoegsel (02.30)             | van der    |
    | geslachtsnaam (02.40)           | Meer       |
    | geboortedatum (03.10)           | 19890201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Machiel  |
    | voorvoegsel (02.30)                                | van der  |
    | geslachtsnaam (02.40)                              | Meer     |
    | geboortedatum (03.10)                              | 19580201 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Meike    |
    | voorvoegsel (02.30)                                | van      |
    | geslachtsnaam (02.40)                              | Mierlo   |
    | geboortedatum (03.10)                              | 19600201 |
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
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Mark       |
    | voorvoegsel (02.30)             | van der    |
    | geslachtsnaam (02.40)           | Meer       |
    | geboortedatum (03.10)           | 20140201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AC0100    |
    | ingangsdatum geldigheid (85.10) | 20160201   |
    
    # Lg01_074 - gescheiden vrouw, relatielegging ná ontbinding huwelijk, 1 minderjarig kind, correctie geboorte en erkenning (gehuwd op datum geboorte kind), ontkenning vaderschap, opnieuw erkenning door eerste erkenner, vervolgens een kind wat ontkend is (en niet opnieuw erkend) en 2 kinderen waarbij ouderschap vastgesteld is.
    Gegeven de persoon met burgerservicenummer '000000061' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Manon      |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 19880201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Mees     |
    | geslachtsnaam (02.40)                              | Maasland |
    | geboortedatum (03.10)                              | 19600201 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Mona     |
    | geslachtsnaam (02.40)                              | Manziek  |
    | geboortedatum (03.10)                              | 19650201 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde        |
    | voornamen (02.10)                                                   | Manfred       |
    | geslachtsnaam (02.40)                                               | Mann          |
    | geboortedatum (03.10)                                               | 19750201      |
    | geboorteland (03.30)                                                | 9089          |
    | geslachtsaanduiding (04.10)                                         | M             |
    | beschrijving document (82.30)                                       | huw akte 9089 |
    | ingangsdatum geldigheid (85.10)                                     | 20140201      |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20140201      |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | Altleiningen  |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 9089          |
    En de 'partner' is gewijzigd naar de volgende gegevens
    | naam                                                          | waarde       |
    | voornamen (02.10)                                             | Manfred      |
    | geslachtsnaam (02.40)                                         | Mann         |
    | geboortedatum (03.10)                                         | 19750201     |
    | geboorteland (03.30)                                          | 9089         |
    | geslachtsaanduiding (04.10)                                   | M            |
    | aktenummer (81.20)                                            | 3XB0100      |
    | ingangsdatum geldigheid (85.10)                               | 20160201     |
    | datum ontbinding huwelijk/geregistreerd partnerschap (07.10)  | 20160201     |
    | plaats ontbinding huwelijk/geregistreerd partnerschap (07.20) | 0518         |
    | land ontbinding huwelijk/geregistreerd partnerschap (07.30)   | 6030         |
    | reden ontbinding huwelijk/geregistreerd partnerschap (07.40)  | S            |
    En de 'partner' is gewijzigd naar de volgende gegevens
    | naam                                                          | waarde          |
    | burgerservicenummer (01.20)                                   | 000000097       |
    | voornamen (02.10)                                             | Manfred         |
    | geslachtsnaam (02.40)                                         | Mann            |
    | geboortedatum (03.10)                                         | 19750201        |
    | geboorteland (03.30)                                          | 9089            |
    | geslachtsaanduiding (04.10)                                   | M               |
    | beschrijving document (82.30)                                 | PL gerelateerde |
    | ingangsdatum geldigheid (85.10)                               | 20160401        |
    | datum ontbinding huwelijk/geregistreerd partnerschap (07.10)  | 20160201        |
    | plaats ontbinding huwelijk/geregistreerd partnerschap (07.20) | 0518            |
    | land ontbinding huwelijk/geregistreerd partnerschap (07.30)   | 6030            |
    | reden ontbinding huwelijk/geregistreerd partnerschap (07.40)  | S               |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000127  |
    | voornamen (02.10)               | Meike      |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20170101   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0103    |
    | ingangsdatum geldigheid (85.10) | 20170101   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000115  |
    | voornamen (02.10)               | Mira       |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20170101   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0102    |
    | ingangsdatum geldigheid (85.10) | 20170101   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000103  |
    | voornamen (02.10)               | Maya       |
    | geslachtsnaam (02.40)           | Mann       |
    | geboortedatum (03.10)           | 20150201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0101    |
    | ingangsdatum geldigheid (85.10) | 20150201   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Mirthe     |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20150201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20150201   |
    En het 'kind' is gecorrigeerd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Mirthe     |
    | geslachtsnaam (02.40)           | Mann       |
    | geboortedatum (03.10)           | 20150201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20150201   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Mirthe     |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20150201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AE0100    |
    | ingangsdatum geldigheid (85.10) | 20150201   |
    En het 'kind' is gecorrigeerd naar de volgende gegevens
    | naam                            | waarde      |
    | burgerservicenummer (01.20)     | 000000085   |
    | voornamen (02.10)               | Mirthe      |
    | voorvoegsel (02.30)             | van de      |
    | geslachtsnaam (02.40)           | Meulengraaf |
    | geboortedatum (03.10)           | 20150201    |
    | geboorteland (03.30)            | 6030        |
    | aktenummer (81.20)              | 1AC0100     |
    | ingangsdatum geldigheid (85.10) | 20150401    |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde      |
    | burgerservicenummer (01.20)     | 000000085   |
    | voornamen (02.10)               | Mirthe      |
    | voorvoegsel (02.30)             | van de      |
    | geslachtsnaam (02.40)           | Meulengraaf |
    | geboortedatum (03.10)           | 20150201    |
    | geboorteland (03.30)            | 6030        |
    | aktenummer (81.20)              | 1AC0100     |
    | ingangsdatum geldigheid (85.10) | 20160801    |
    
    # Lg01_075 - ongehuwde man, kind erkend, geboorteakte kind gecorrigeerd (erkenning daardoor onjuist), na ontkenning vaderschap opnieuw erkend
    Gegeven de persoon met burgerservicenummer '000000073' heeft de volgende gegevens
    | naam                            | waarde      |
    | voornamen (02.10)               | Menno       |
    | voorvoegsel (02.30)             | van de      |
    | geslachtsnaam (02.40)           | Meulengraaf |
    | geboortedatum (03.10)           | 19870201    |
    | geboorteland (03.30)            | 6030        |
    | geslachtsaanduiding (04.10)     | M           |
    | beschrijving document (82.30)   | PKA         |
    | ingangsdatum geldigheid (85.10) | 0           |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde      |
    | voornamen (02.10)                                  | Maarten     |
    | voorvoegsel (02.30)                                | van de      |
    | geslachtsnaam (02.40)                              | Meulengraaf |
    | geboortedatum (03.10)                              | 19500601    |
    | geboorteland (03.30)                               | 6030        |
    | geslachtsaanduiding (04.10)                        | M           |
    | beschrijving document (82.30)                      | PK          |
    | ingangsdatum geldigheid (85.10)                    | 0           |
    | datum ingang familierechtelijke betrekking (62.10) | 0           |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Maaike   |
    | geslachtsnaam (02.40)                              | Mol      |
    | geboortedatum (03.10)                              | 19550601 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft een 'kind' met de volgende gegevens
    | naam                            | waarde      |
    | burgerservicenummer (01.20)     | 000000085   |
    | voornamen (02.10)               | Mirthe      |
    | voorvoegsel (02.30)             | van de      |
    | geslachtsnaam (02.40)           | Meulengraaf |
    | geboortedatum (03.10)           | 20150201    |
    | geboorteland (03.30)            | 6030        |
    | aktenummer (81.20)              | 1AC0100     |
    | ingangsdatum geldigheid (85.10) | 20150401    |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde                 |
    | beschrijving document (82.30)   | ten onrechte opgenomen |
    | ingangsdatum geldigheid (85.10) | 20150401               |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde      |
    | burgerservicenummer (01.20)     | 000000085   |
    | voornamen (02.10)               | Mirthe      |
    | voorvoegsel (02.30)             | van de      |
    | geslachtsnaam (02.40)           | Meulengraaf |
    | geboortedatum (03.10)           | 20150201    |
    | geboorteland (03.30)            | 6030        |
    | aktenummer (81.20)              | 1AC0100     |
    | ingangsdatum geldigheid (85.10) | 20160801    |
    
    # Lg01_076 - minderjarig kind bij geboorte alleen moeder, vervolgens erkend, geboorte en erkenning onjuist moeder bleek gehuwd op datum geboorte, ontkenning vaderschap door (ex)huwelijkspartner moeder, vervolgens opnieuw erkend door eerste erkenner, geen categorie 11
    Gegeven de persoon met burgerservicenummer '000000085' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Mirthe     |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20150201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20150201   |
    En de persoon is gecorrigeerd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Mirthe     |
    | geslachtsnaam (02.40)           | Mann       |
    | geboortedatum (03.10)           | 20150201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20150201   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Mirthe     |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20150201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AE0100    |
    | ingangsdatum geldigheid (85.10) | 20150201   |
    En de persoon is gecorrigeerd naar de volgende gegevens
    | naam                            | waarde      |
    | burgerservicenummer (01.20)     | 000000085   |
    | voornamen (02.10)               | Mirthe      |
    | voorvoegsel (02.30)             | van de      |
    | geslachtsnaam (02.40)           | Meulengraaf |
    | geboortedatum (03.10)           | 20150201    |
    | geboorteland (03.30)            | 6030        |
    | geslachtsaanduiding (04.10)     | V           |
    | aktenummer (81.20)              | 1AC0100     |
    | ingangsdatum geldigheid (85.10) | 20150401    |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde      |
    | burgerservicenummer (01.20)     | 000000085   |
    | voornamen (02.10)               | Mirthe      |
    | voorvoegsel (02.30)             | van de      |
    | geslachtsnaam (02.40)           | Meulengraaf |
    | geboortedatum (03.10)           | 20150201    |
    | geboorteland (03.30)            | 6030        |
    | geslachtsaanduiding (04.10)     | V           |
    | aktenummer (81.20)              | 1AC0100     |
    | ingangsdatum geldigheid (85.10) | 20160801    |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000061  |
    | voornamen (02.10)                                  | Manon      |
    | geslachtsnaam (02.40)                              | Maasland   |
    | geboortedatum (03.10)                              | 19880201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0100    |
    | ingangsdatum geldigheid (85.10)                    | 20150201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20150201   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0100  |
    | ingangsdatum geldigheid (85.10) | 20150201 |
    En de ouder '1' is gecorrigeerd naar de volgende gegevens
    | naam                                               | waarde       |
    | voornamen (02.10)                                  | Manfred      |
    | geslachtsnaam (02.40)                              | Mann         |
    | geboortedatum (03.10)                              | 19750201     |
    | geboorteland (03.30)                               | 9089         |
    | geslachtsaanduiding (04.10)                        | M            |
    | aktenummer (81.20)                                 | 1AA0100      |
    | ingangsdatum geldigheid (85.10)                    | 20150201     |
    | datum ingang familierechtelijke betrekking (62.10) | 20150201     |
    En de ouder '1' is gewijzigd naar de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AE0100  |
    | ingangsdatum geldigheid (85.10) | 20150201 |
    En de ouder '1' is gecorrigeerd naar de volgende gegevens
    | naam                                               | waarde      |
    | burgerservicenummer (01.20)                        | 000000073   |
    | voornamen (02.10)                                  | Menno       |
    | voorvoegsel (02.30)                                | van de      |
    | geslachtsnaam (02.40)                              | Meulengraaf |
    | geboortedatum (03.10)                              | 19870201    |
    | geboorteland (03.30)                               | 6030        |
    | geslachtsaanduiding (04.10)                        | M           |
    | aktenummer (81.20)                                 | 1AC0100     |
    | ingangsdatum geldigheid (85.10)                    | 20150401    |
    | datum ingang familierechtelijke betrekking (62.10) | 20150401    |
    En de ouder '1' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde      |
    | burgerservicenummer (01.20)                        | 000000073   |
    | voornamen (02.10)                                  | Menno       |
    | voorvoegsel (02.30)                                | van de      |
    | geslachtsnaam (02.40)                              | Meulengraaf |
    | geboortedatum (03.10)                              | 19870201    |
    | geboorteland (03.30)                               | 6030        |
    | geslachtsaanduiding (04.10)                        | M           |
    | aktenummer (81.20)                                 | 1AC0100     |
    | ingangsdatum geldigheid (85.10)                    | 20160801    |
    | datum ingang familierechtelijke betrekking (62.10) | 20160801    |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20150201 |
    
    # Lg01_077 - latere vestiging in Nederland, gescheiden, vaderschap ontkend van 2 minderjarige kinderen, bij 2 kinderen ouderschap vastgesteld
    Gegeven de persoon met burgerservicenummer '000000097' heeft de volgende gegevens
    | naam                            | waarde       |
    | voornamen (02.10)               | Manfred      |
    | geslachtsnaam (02.40)           | Mann         |
    | geboortedatum (03.10)           | 19750201     |
    | geboorteland (03.30)            | 9089         |
    | geslachtsaanduiding (04.10)     | M            |
    | beschrijving document (82.30)   | ga 9089      |
    | ingangsdatum geldigheid (85.10) | 0            |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde       |
    | voornamen (02.10)                                  | Matz         |
    | geslachtsnaam (02.40)                              | Mann         |
    | geboortedatum (03.10)                              | 19380201     |
    | geboorteland (03.30)                               | 6029         |
    | geslachtsaanduiding (04.10)                        | M            |
    | beschrijving document (82.30)                      | ga 9089      |
    | ingangsdatum geldigheid (85.10)                    | 0            |
    | datum ingang familierechtelijke betrekking (62.10) | 0            |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde       |
    | voornamen (02.10)                                  | Mauzie       |
    | geslachtsnaam (02.40)                              | Mann         |
    | geboortedatum (03.10)                              | 19400201     |
    | geboorteland (03.30)                               | 6029         |
    | geslachtsaanduiding (04.10)                        | V            |
    | beschrijving document (82.30)                      | ga 9089      |
    | ingangsdatum geldigheid (85.10)                    | 0            |
    | datum ingang familierechtelijke betrekking (62.10) | 0            |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde        |
    | burgerservicenummer (01.20)                                         | 000000061     |
    | voornamen (02.10)                                                   | Manon         |
    | geslachtsnaam (02.40)                                               | Maasland      |
    | geboortedatum (03.10)                                               | 19880201      |
    | geboorteland (03.30)                                                | 6030          |
    | geslachtsaanduiding (04.10)                                         | V             |
    | beschrijving document (82.30)                                       | huw akte 9089 |
    | ingangsdatum geldigheid (85.10)                                     | 20140201      |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20140201      |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | Altleiningen  |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 9089          |
    En de 'partner' is gewijzigd naar de volgende gegevens
    | naam                                                          | waarde     |
    | burgerservicenummer (01.20)                                   | 000000061  |
    | voornamen (02.10)                                             | Manon      |
    | geslachtsnaam (02.40)                                         | Maasland   |
    | geboortedatum (03.10)                                         | 19880201   |
    | geboorteland (03.30)                                          | 6030       |
    | geslachtsaanduiding (04.10)                                   | V          |
    | aktenummer (81.20)                                            | 3XB0100    |
    | ingangsdatum geldigheid (85.10)                               | 20160201   |
    | datum ontbinding huwelijk/geregistreerd partnerschap (07.10)  | 20160201   |
    | plaats ontbinding huwelijk/geregistreerd partnerschap (07.20) | 0518       |
    | land ontbinding huwelijk/geregistreerd partnerschap (07.30)   | 6030       |
    | reden ontbinding huwelijk/geregistreerd partnerschap (07.40)  | S          |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                                 | waarde   |
    | gemeente van inschrijving (09.10)    | 518      |
    | land vanwaar ingeschreven (14.10)    | 9089     |
    | datum vestiging in Nederland (14.20) | 20160401 |
    | ingangsdatum geldigheid (85.10)      | 20160401 |
    En de persoon heeft een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000115  |
    | voornamen (02.10)               | Mira       |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20170101   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AV0102    |
    | ingangsdatum geldigheid (85.10) | 20170101   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000127  |
    | voornamen (02.10)               | Meike      |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20170101   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AV0103    |
    | ingangsdatum geldigheid (85.10) | 20170101   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000103  |
    | voornamen (02.10)               | Maya       |
    | geslachtsnaam (02.40)           | Mann       |
    | geboortedatum (03.10)           | 20150201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0101    |
    | ingangsdatum geldigheid (85.10) | 20150201   |
    En het 'kind' is gecorrigeerd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Mirthe     |
    | geslachtsnaam (02.40)           | Mann       |
    | geboortedatum (03.10)           | 20150201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20150201   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AE0100  |
    | ingangsdatum geldigheid (85.10) | 20150201 |
    
    # Lg01_166 - ontkenning vaderschap, juridisch geen ouder1, wel ouder2
    Gegeven de persoon met burgerservicenummer '000000103' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Maya       |
    | geslachtsnaam (02.40)           | Mann       |
    | geboortedatum (03.10)           | 20150201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0101    |
    | ingangsdatum geldigheid (85.10) | 20150201   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000103  |
    | voornamen (02.10)               | Maya       |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20150201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AE0101    |
    | ingangsdatum geldigheid (85.10) | 20150201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000061  |
    | voornamen (02.10)                                  | Manon      |
    | geslachtsnaam (02.40)                              | Maasland   |
    | geboortedatum (03.10)                              | 19880201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0101    |
    | ingangsdatum geldigheid (85.10)                    | 20150201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20150201   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde       |
    | burgerservicenummer (01.20)                        | 000000097    |
    | voornamen (02.10)                                  | Manfred      |
    | geslachtsnaam (02.40)                              | Mann         |
    | geboortedatum (03.10)                              | 19750201     |
    | geboorteland (03.30)                               | 9089         |
    | geslachtsaanduiding (04.10)                        | M            |
    | aktenummer (81.20)                                 | 1AA0101      |
    | ingangsdatum geldigheid (85.10)                    | 20150201     |
    | datum ingang familierechtelijke betrekking (62.10) | 20150201     |
    En de ouder '1' is gewijzigd naar de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AE0101  |
    | ingangsdatum geldigheid (85.10) | 20150201 |
    En de persoon is ingeschreven op adres 'A3' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20150201 |
    
    # Lg01_167 - vaststelling ouderschap, geen categorie 11
    Gegeven de persoon met burgerservicenummer '000000115' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Mira       |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20170101   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0102    |
    | ingangsdatum geldigheid (85.10) | 20170101   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000115  |
    | voornamen (02.10)               | Mira       |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20170101   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AV0102    |
    | ingangsdatum geldigheid (85.10) | 20170101   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0102  |
    | ingangsdatum geldigheid (85.10) | 20170101 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde       |
    | burgerservicenummer (01.20)                        | 000000097    |
    | voornamen (02.10)                                  | Manfred      |
    | geslachtsnaam (02.40)                              | Mann         |
    | geboortedatum (03.10)                              | 19750201     |
    | geboorteland (03.30)                               | 9089         |
    | geslachtsaanduiding (04.10)                        | M            |
    | aktenummer (81.20)                                 | 1AV0102      |
    | ingangsdatum geldigheid (85.10)                    | 20170101     |
    | datum ingang familierechtelijke betrekking (62.10) | 20170101     |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000061  |
    | voornamen (02.10)                                  | Manon      |
    | geslachtsnaam (02.40)                              | Maasland   |
    | geboortedatum (03.10)                              | 19880201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0102    |
    | ingangsdatum geldigheid (85.10)                    | 20170101   |
    | datum ingang familierechtelijke betrekking (62.10) | 20170101   |
    En de persoon is ingeschreven op adres 'A3' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20170101 |
    
    # Lg01_168 - vaststelling ouderschap, wel categorie 11
    Gegeven de persoon met burgerservicenummer '000000127' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Meike      |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20170101   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0103    |
    | ingangsdatum geldigheid (85.10) | 20170101   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000127  |
    | voornamen (02.10)               | Meike      |
    | geslachtsnaam (02.40)           | Maasland   |
    | geboortedatum (03.10)           | 20170101   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AV0103    |
    | ingangsdatum geldigheid (85.10) | 20170101   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0103  |
    | ingangsdatum geldigheid (85.10) | 20170101 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde       |
    | burgerservicenummer (01.20)                        | 000000097    |
    | voornamen (02.10)                                  | Manfred      |
    | geslachtsnaam (02.40)                              | Mann         |
    | geboortedatum (03.10)                              | 19750201     |
    | geboorteland (03.30)                               | 9089         |
    | geslachtsaanduiding (04.10)                        | M            |
    | aktenummer (81.20)                                 | 1AV0103      |
    | ingangsdatum geldigheid (85.10)                    | 20170101     |
    | datum ingang familierechtelijke betrekking (62.10) | 20170101     |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000061  |
    | voornamen (02.10)                                  | Manon      |
    | geslachtsnaam (02.40)                              | Maasland   |
    | geboortedatum (03.10)                              | 19880201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0103    |
    | ingangsdatum geldigheid (85.10)                    | 20170101   |
    | datum ingang familierechtelijke betrekking (62.10) | 20170101   |
    En de persoon is ingeschreven op adres 'A3' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20170101 |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
    | naam                                 | waarde                     |
    | indicatie gezag minderjarige (32.10) | 12                         |
    | beschrijving document (82.30)        | kennsigeving gezagregister |
    | ingangsdatum geldigheid (85.10)      | 20221101                   |


  Scenario: Lg01_070 - ongehuwde vrouw met 1 minderjarig kind (erkend, erkenning vernietigd en opnieuw erkend)
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000036                |
    | ouder.burgerservicenummer        | 000000012                |

  Scenario: Lg01_071 - ongehuwde man met vernietiging erkenning
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft de persoon geen gezag

  Scenario: Lg01_072 - minderjarig kind, erkenning, vernietiging erkenning, nieuwe erkenning, cat 11 in historie 12, actueel leeg (vernietiging erkenning)
    # Route: 54v1 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000036                |
    | ouder.burgerservicenummer        | 000000012                |

  Scenario: Lg01_073 - ongehuwde man met erkend kind
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon geen gezag

  Scenario: Lg01_074 - gescheiden vrouw, relatielegging ná ontbinding huwelijk, 1 minderjarig kind, correctie geboorte en erkenning (gehuwd op datum geboorte kind), ontkenning vaderschap, opnieuw erkenning door eerste erkenner, vervolgens een kind wat ontkend is (en niet opnieuw erkend) en 2 kinderen waarbij ouderschap vastgesteld is.
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000061 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000085                |
    | ouder.burgerservicenummer        | 000000061                |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000103                |
    | ouder.burgerservicenummer        | 000000061                |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000115                |
    | ouder.burgerservicenummer        | 000000061                |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000127                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000061 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000097 |

  Scenario: Lg01_075 - ongehuwde man, kind erkend, geboorteakte kind gecorrigeerd (erkenning daardoor onjuist), na ontkenning vaderschap opnieuw erkend
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000073 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000073 |
    En heeft de persoon geen gezag

  Scenario: Lg01_076 - minderjarig kind bij geboorte alleen moeder, vervolgens erkend, geboorte en erkenning onjuist moeder bleek gehuwd op datum geboorte, ontkenning vaderschap door (ex)huwelijkspartner moeder, vervolgens opnieuw erkend door eerste erkenner, geen categorie 11
    # Route: 54v2 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000085 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000085 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000085                |
    | ouder.burgerservicenummer        | 000000061                |

  Scenario: Lg01_077 - latere vestiging in Nederland, gescheiden, vaderschap ontkend van 2 minderjarige kinderen, bij 2 kinderen ouderschap vastgesteld
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000097 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000097 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000127                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000061 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000097 |

  Scenario: Lg01_166 - ontkenning vaderschap, juridisch geen ouder1, wel ouder2
    # Route: 40o2 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000103 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000103 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000103                |
    | ouder.burgerservicenummer        | 000000061                |

  Scenario: Lg01_167 - vaststelling ouderschap, geen categorie 11
    # Route: 54v1 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000115 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000115 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000115                |
    | ouder.burgerservicenummer        | 000000061                |

  Scenario: Lg01_168 - vaststelling ouderschap, wel categorie 11
    # Route: 7 - Wie heeft gezag?: beide ouders (OG2)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000127 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000127 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000127                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000061 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000097 |
