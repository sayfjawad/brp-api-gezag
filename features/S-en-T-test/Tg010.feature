#language: nl

Functionaliteit: Tg010 - Janssen-Jozefs en Hanssen - adoptie, gehuwde juridische ouders - ex-juridische moeder


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 17                 | 0518200000583537                           |
    Gegeven adres 'A2' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 19                 | 0518200000583538                           |

    # Lg01_050 - niet gehuwd, 2 minderjarige kinderen (tweeling) geadopteerd door echtpaar, heeft hierdoor geen gezag meer over ex-kinderen
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Helena     |
    | geslachtsnaam (02.40)           | Hanssen    |
    | geboortedatum (03.10)           | 19971201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 19971201   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde                         |
    | burgerservicenummer (01.20)     | 000000012                      |
    | voornamen (02.10)               | Helena                         |
    | geslachtsnaam (02.40)           | Hanssen                        |
    | geboortedatum (03.10)           | 19971201                       |
    | geboorteland (03.30)            | 6030                           |
    | geslachtsaanduiding (04.10)     | V                              |
    | beschrijving document (82.30)   | Melding Minister van Financiën |
    | ingangsdatum geldigheid (85.10) | 19971210                       |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 999998729  |
    | voornamen (02.10)                                  | Hedwig     |
    | geslachtsnaam (02.40)                              | Harmsen    |
    | geboortedatum (03.10)                              | 19700401   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0100    |
    | ingangsdatum geldigheid (85.10)                    | 19971201   |
    | datum ingang familierechtelijke betrekking (62.10) | 19971201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 999998766  |
    | voornamen (02.10)                                  | Harald     |
    | geslachtsnaam (02.40)                              | Hanssen    |
    | geboortedatum (03.10)                              | 19640802   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AA0100    |
    | ingangsdatum geldigheid (85.10)                    | 19971201   |
    | datum ingang familierechtelijke betrekking (62.10) | 19971201   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | William    |
    | geslachtsnaam (02.40)           | Hanssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0101    |
    | ingangsdatum geldigheid (85.10) | 20161201   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AQ0101  |
    | ingangsdatum geldigheid (85.10) | 20191201 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Harry      |
    | geslachtsnaam (02.40)           | Hanssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20161201   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AQ0100  |
    | ingangsdatum geldigheid (85.10) | 20191201 |
    
    # Lg01_051 - minderjarig kind, geadopteerd + voornaamswijziging, onder gezag Derde (vóór adoptie).
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Harry      |
    | geslachtsnaam (02.40)           | Hanssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20161201   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Harry      |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AQ0100    |
    | ingangsdatum geldigheid (85.10) | 20191201   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Jorik      |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AM0100    |
    | ingangsdatum geldigheid (85.10) | 20200105   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Helena     |
    | geslachtsnaam (02.40)                              | Hanssen    |
    | geboortedatum (03.10)                              | 19971201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0100    |
    | ingangsdatum geldigheid (85.10)                    | 20161201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20161201   |
    En de ouder '1' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000036  |
    | voornamen (02.10)                                  | Jaqueline  |
    | geslachtsnaam (02.40)                              | Jozefs     |
    | geboortedatum (03.10)                              | 19831001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AQ0100    |
    | ingangsdatum geldigheid (85.10)                    | 20191201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20191201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0100  |
    | ingangsdatum geldigheid (85.10) | 20161201 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000048  |
    | voornamen (02.10)                                  | Jeroen     |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19821001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AQ0100    |
    | ingangsdatum geldigheid (85.10)                    | 20191201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20191201   |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20181201 |
    En de persoon heeft de volgende 'gezagsverhouding' gegevens
    | naam                                 | waarde                      |
    | indicatie gezag minderjarige (32.10) | D                           |
    | beschrijving document (82.30)        | kennisgeving gezagsregister |
    | ingangsdatum geldigheid (85.10)      | 20190101                    |
    
    # Lg01_052 - gehuwd + 7 adoptiekinderen
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Jaqueline  |
    | geslachtsnaam (02.40)           | Jozefs     |
    | geboortedatum (03.10)           | 19831001   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Janny    |
    | voorvoegsel (02.30)                                | de       |
    | geslachtsnaam (02.40)                              | Jong     |
    | geboortedatum (03.10)                              | 19581001 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Jacob    |
    | geslachtsnaam (02.40)                              | Jozefs   |
    | geboortedatum (03.10)                              | 19561001 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000048  |
    | voornamen (02.10)                                                   | Jeroen     |
    | geslachtsnaam (02.40)                                               | Janssen    |
    | geboortedatum (03.10)                                               | 19821001   |
    | geboorteland (03.30)                                                | 6030       |
    | geslachtsaanduiding (04.10)                                         | M          |
    | aktenummer (81.20)                                                  | 3AA0100    |
    | ingangsdatum geldigheid (85.10)                                     | 20131001   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20131001   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde       |
    | burgerservicenummer (01.20)     | 000000115    |
    | voornamen (02.10)               | Jinthe Jiang |
    | geslachtsnaam (02.40)           | Janssen      |
    | geboortedatum (03.10)           | 20150101     |
    | geboorteland (03.30)            | 6022         |
    | aktenummer (81.20)              | 1VQ0700      |
    | ingangsdatum geldigheid (85.10) | 20180101     |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000103  |
    | voornamen (02.10)               | Jia        |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | aktenummer (81.20)              | 1VQ0701    |
    | ingangsdatum geldigheid (85.10) | 20190101   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000097  |
    | voornamen (02.10)               | Jiali      |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | aktenummer (81.20)              | 1VQ0700    |
    | ingangsdatum geldigheid (85.10) | 20180101   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000097  |
    | voornamen (02.10)               | Jiali Jade |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | aktenummer (81.20)              | 1VM0700    |
    | ingangsdatum geldigheid (85.10) | 20190510   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Jin        |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | aktenummer (81.20)              | 1VQ0700    |
    | ingangsdatum geldigheid (85.10) | 20190101   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Jasmin-Jin |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | aktenummer (81.20)              | 1VM0700    |
    | ingangsdatum geldigheid (85.10) | 20190510   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Li         |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | aktenummer (81.20)              | 1VQ0700    |
    | ingangsdatum geldigheid (85.10) | 20180101   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | William    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AQ0101    |
    | ingangsdatum geldigheid (85.10) | 20191201   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Jasper     |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AM0101    |
    | ingangsdatum geldigheid (85.10) | 20200105   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Harry      |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AQ0100    |
    | ingangsdatum geldigheid (85.10) | 20191201   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Jorik      |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AM0100    |
    | ingangsdatum geldigheid (85.10) | 20200105   |
    
    # Lg01_053 - gehuwd + 7 adoptiekinderen
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Jeroen     |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 19821001   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Judith   |
    | geslachtsnaam (02.40)                              | Jaarsma  |
    | geboortedatum (03.10)                              | 19571001 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Jurgen   |
    | geslachtsnaam (02.40)                              | Janssen  |
    | geboortedatum (03.10)                              | 19551001 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000036  |
    | voornamen (02.10)                                                   | Jaqueline  |
    | geslachtsnaam (02.40)                                               | Jozefs     |
    | geboortedatum (03.10)                                               | 19831001   |
    | geboorteland (03.30)                                                | 6030       |
    | geslachtsaanduiding (04.10)                                         | V          |
    | aktenummer (81.20)                                                  | 3AA0100    |
    | ingangsdatum geldigheid (85.10)                                     | 20131001   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20131001   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde       |
    | burgerservicenummer (01.20)     | 000000115    |
    | voornamen (02.10)               | Jinthe Jiang |
    | geslachtsnaam (02.40)           | Janssen      |
    | geboortedatum (03.10)           | 20150101     |
    | geboorteland (03.30)            | 6022         |
    | aktenummer (81.20)              | 1VQ0700      |
    | ingangsdatum geldigheid (85.10) | 20180101     |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000103  |
    | voornamen (02.10)               | Jia        |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | aktenummer (81.20)              | 1VQ0701    |
    | ingangsdatum geldigheid (85.10) | 20190101   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000097  |
    | voornamen (02.10)               | Jiali      |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | aktenummer (81.20)              | 1VQ0700    |
    | ingangsdatum geldigheid (85.10) | 20180101   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000097  |
    | voornamen (02.10)               | Jiali Jade |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | aktenummer (81.20)              | 1VM0700    |
    | ingangsdatum geldigheid (85.10) | 20190510   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Jin        |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | aktenummer (81.20)              | 1VQ0700    |
    | ingangsdatum geldigheid (85.10) | 20190101   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Li         |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | aktenummer (81.20)              | 1VQ0700    |
    | ingangsdatum geldigheid (85.10) | 20180101   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | William    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AQ0101    |
    | ingangsdatum geldigheid (85.10) | 20191201   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Jasper     |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AM0101    |
    | ingangsdatum geldigheid (85.10) | 20200105   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Harry      |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AQ0100    |
    | ingangsdatum geldigheid (85.10) | 20191201   |
    En het 'kind' is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Jorik      |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AM0100    |
    | ingangsdatum geldigheid (85.10) | 20200105   |
    
    # Lg01_054 - minderjarig kind, geadopteerd uit buitenland, Nederlandse adoptieakte
    Gegeven de persoon met burgerservicenummer '000000061' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Li         |
    | geslachtsnaam (02.40)           | Yu         |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1VA0700    |
    | ingangsdatum geldigheid (85.10) | 20150101   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Li         |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1VQ0700    |
    | ingangsdatum geldigheid (85.10) | 20180101   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Wei      |
    | geslachtsnaam (02.40)                              | Yu       |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | ga 6022  |
    | ingangsdatum geldigheid (85.10)                    | 20150101 |
    | datum ingang familierechtelijke betrekking (62.10) | 20150101 |
    En de ouder '1' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000036  |
    | voornamen (02.10)                                  | Jaqueline  |
    | geslachtsnaam (02.40)                              | Jozefs     |
    | geboortedatum (03.10)                              | 19831001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1VQ0700    |
    | ingangsdatum geldigheid (85.10)                    | 20180101   |
    | datum ingang familierechtelijke betrekking (62.10) | 20180101   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | beschrijving document (82.30)   | ga 6022  |
    | ingangsdatum geldigheid (85.10) | 20150101 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000048  |
    | voornamen (02.10)                                  | Jeroen     |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19821001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1VQ0700    |
    | ingangsdatum geldigheid (85.10)                    | 20180101   |
    | datum ingang familierechtelijke betrekking (62.10) | 20180101   |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                                 | waarde   |
    | gemeente van inschrijving (09.10)    | 518      |
    | land vanwaar ingeschreven (14.10)    | 6022     |
    | datum vestiging in Nederland (14.20) | 20180501 |
    | ingangsdatum geldigheid (85.10)      | 20180501 |
    
    # Lg01_084 - minderjarig kind, geadopteerd + voornaamswijziging, onder gezag adoptieouders, Geen gezag ex-moeder
    Gegeven de persoon met burgerservicenummer '000000073' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | William    |
    | geslachtsnaam (02.40)           | Hanssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0101    |
    | ingangsdatum geldigheid (85.10) | 20161201   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | William    |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AQ0101    |
    | ingangsdatum geldigheid (85.10) | 20191201   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Jasper     |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20161201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AM0101    |
    | ingangsdatum geldigheid (85.10) | 20200105   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Helena     |
    | geslachtsnaam (02.40)                              | Hanssen    |
    | geboortedatum (03.10)                              | 19971201   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0101    |
    | ingangsdatum geldigheid (85.10)                    | 20161201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20161201   |
    En de ouder '1' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000036  |
    | voornamen (02.10)                                  | Jaqueline  |
    | geslachtsnaam (02.40)                              | Jozefs     |
    | geboortedatum (03.10)                              | 19831001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AQ0101    |
    | ingangsdatum geldigheid (85.10)                    | 20191201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20191201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0101  |
    | ingangsdatum geldigheid (85.10) | 20161201 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000048  |
    | voornamen (02.10)                                  | Jeroen     |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19821001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AQ0101    |
    | ingangsdatum geldigheid (85.10)                    | 20191201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20191201   |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20181201 |
    
    # Lg01_193 - buitenland geboren, geadopteerd met Nederlandse Q-akte in historie
    Gegeven de persoon met burgerservicenummer '000000085' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Jin        |
    | geslachtsnaam (02.40)           | Jang       |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1VA0700    |
    | ingangsdatum geldigheid (85.10) | 20150101   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Jin        |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1VQ0700    |
    | ingangsdatum geldigheid (85.10) | 20190101   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Jasmin-Jin |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1VM0700    |
    | ingangsdatum geldigheid (85.10) | 20190510   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1VA0700  |
    | ingangsdatum geldigheid (85.10) | 20150101 |
    En de ouder '1' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000036  |
    | voornamen (02.10)                                  | Jaqueline  |
    | geslachtsnaam (02.40)                              | Jozefs     |
    | geboortedatum (03.10)                              | 19831001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1VQ0700    |
    | ingangsdatum geldigheid (85.10)                    | 20190101   |
    | datum ingang familierechtelijke betrekking (62.10) | 20190101   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1VA0700  |
    | ingangsdatum geldigheid (85.10) | 20150101 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000048  |
    | voornamen (02.10)                                  | Jeroen     |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19821001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1VQ0700    |
    | ingangsdatum geldigheid (85.10)                    | 20190101   |
    | datum ingang familierechtelijke betrekking (62.10) | 20190101   |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                                 | waarde   |
    | gemeente van inschrijving (09.10)    | 518      |
    | land vanwaar ingeschreven (14.10)    | 6022     |
    | datum vestiging in Nederland (14.20) | 20180501 |
    | ingangsdatum geldigheid (85.10)      | 20180501 |
    
    # Lg01_194 - buitenland geboren, geadopteerd met Nederlandse Q-akte in historie, onderzoek in 018120
    Gegeven de persoon met burgerservicenummer '000000097' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000097  |
    | voornamen (02.10)               | Jiali      |
    | geslachtsnaam (02.40)           | Jang       |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1VA0700    |
    | aanduiding in onderzoek (83.10) | 518120     |
    | datum ingang onderzoek (83.20)  | 20190501   |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000097  |
    | voornamen (02.10)               | Jiali      |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1VQ0700    |
    | aanduiding in onderzoek (83.10) | 518120     |
    | datum ingang onderzoek (83.20)  | 20190501   |
    | ingangsdatum geldigheid (85.10) | 20180101   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000097  |
    | voornamen (02.10)               | Jiali Jade |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1VM0700    |
    | aanduiding in onderzoek (83.10) | 18120      |
    | datum ingang onderzoek (83.20)  | 20190501   |
    | datum einde onderzoek (83.30)   | 20220202   |
    | ingangsdatum geldigheid (85.10) | 20190510   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1VA0700  |
    | ingangsdatum geldigheid (85.10) | 20150101 |
    En de ouder '1' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000036  |
    | voornamen (02.10)                                  | Jaqueline  |
    | geslachtsnaam (02.40)                              | Jozefs     |
    | geboortedatum (03.10)                              | 19831001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1VQ0700    |
    | ingangsdatum geldigheid (85.10)                    | 20190101   |
    | datum ingang familierechtelijke betrekking (62.10) | 20190101   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1VA0700  |
    | ingangsdatum geldigheid (85.10) | 20150101 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000048  |
    | voornamen (02.10)                                  | Jeroen     |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19821001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1VQ0700    |
    | ingangsdatum geldigheid (85.10)                    | 20180101   |
    | datum ingang familierechtelijke betrekking (62.10) | 20190101   |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                                 | waarde   |
    | gemeente van inschrijving (09.10)    | 518      |
    | land vanwaar ingeschreven (14.10)    | 6022     |
    | datum vestiging in Nederland (14.20) | 20180501 |
    | ingangsdatum geldigheid (85.10)      | 20180501 |
    
    # Lg01_195 - buitenland geboren, geadopteerd met Nederlandse Q-akte in historie, onderzoek in 536200
    Gegeven de persoon met burgerservicenummer '000000103' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000103  |
    | voornamen (02.10)               | Jia        |
    | geslachtsnaam (02.40)           | Jang       |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1VA0701    |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000103  |
    | voornamen (02.10)               | Jia        |
    | geslachtsnaam (02.40)           | Janssen    |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1VQ0701    |
    | ingangsdatum geldigheid (85.10) | 20190101   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1VA0701  |
    | ingangsdatum geldigheid (85.10) | 20150101 |
    En de ouder '1' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000036  |
    | voornamen (02.10)                                  | Jaqueline  |
    | geslachtsnaam (02.40)                              | Jozefs     |
    | geboortedatum (03.10)                              | 19831001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1VQ0701    |
    | ingangsdatum geldigheid (85.10)                    | 20190101   |
    | datum ingang familierechtelijke betrekking (62.10) | 20190101   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1VA0701  |
    | ingangsdatum geldigheid (85.10) | 20150101 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000048  |
    | voornamen (02.10)                                  | Jeroen     |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19821001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1VQ0701    |
    | aanduiding in onderzoek (83.10)                    | 36200      |
    | datum ingang onderzoek (83.20)                     | 20220202   |
    | ingangsdatum geldigheid (85.10)                    | 20190101   |
    | datum ingang familierechtelijke betrekking (62.10) | 20190101   |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                                 | waarde   |
    | gemeente van inschrijving (09.10)    | 518      |
    | land vanwaar ingeschreven (14.10)    | 6022     |
    | datum vestiging in Nederland (14.20) | 20180501 |
    | ingangsdatum geldigheid (85.10)      | 20180501 |
    
    # Lg01_196 - buitenland geboren, geadopteerd met Nederlandse Q-akte in historie, afgerond onderzoek in 018120, 026200, 036200
    Gegeven de persoon met burgerservicenummer '000000115' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000115  |
    | voornamen (02.10)               | Jiang      |
    | geslachtsnaam (02.40)           | Jang       |
    | geboortedatum (03.10)           | 20150101   |
    | geboorteland (03.30)            | 6022       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1VA0700    |
    | aanduiding in onderzoek (83.10) | 18120      |
    | datum ingang onderzoek (83.20)  | 20190501   |
    | datum einde onderzoek (83.30)   | 20220202   |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde       |
    | burgerservicenummer (01.20)     | 000000115    |
    | voornamen (02.10)               | Jinthe Jiang |
    | geslachtsnaam (02.40)           | Janssen      |
    | geboortedatum (03.10)           | 20150101     |
    | geboorteland (03.30)            | 6022         |
    | geslachtsaanduiding (04.10)     | V            |
    | aktenummer (81.20)              | 1VQ0700      |
    | aanduiding in onderzoek (83.10) | 18120        |
    | datum ingang onderzoek (83.20)  | 20190501     |
    | datum einde onderzoek (83.30)   | 20220202     |
    | ingangsdatum geldigheid (85.10) | 20180101     |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1VA0700  |
    | aanduiding in onderzoek (83.10) | 26200    |
    | datum ingang onderzoek (83.20)  | 20190501 |
    | datum einde onderzoek (83.30)   | 20220202 |
    | ingangsdatum geldigheid (85.10) | 20150101 |
    En de ouder '1' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000036  |
    | voornamen (02.10)                                  | Jaqueline  |
    | geslachtsnaam (02.40)                              | Jozefs     |
    | geboortedatum (03.10)                              | 19831001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1VQ0700    |
    | aanduiding in onderzoek (83.10)                    | 26200      |
    | datum ingang onderzoek (83.20)                     | 20190501   |
    | datum einde onderzoek (83.30)                      | 20220202   |
    | ingangsdatum geldigheid (85.10)                    | 20190101   |
    | datum ingang familierechtelijke betrekking (62.10) | 20190101   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1VA0700  |
    | aanduiding in onderzoek (83.10) | 36200    |
    | datum ingang onderzoek (83.20)  | 20190501 |
    | datum einde onderzoek (83.30)   | 20220202 |
    | ingangsdatum geldigheid (85.10) | 20150101 |
    En de ouder '2' is gewijzigd naar de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000048  |
    | voornamen (02.10)                                  | Jeroen     |
    | geslachtsnaam (02.40)                              | Janssen    |
    | geboortedatum (03.10)                              | 19821001   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1VQ0700    |
    | aanduiding in onderzoek (83.10)                    | 36200      |
    | datum ingang onderzoek (83.20)                     | 20190501   |
    | datum einde onderzoek (83.30)                      | 20220202   |
    | ingangsdatum geldigheid (85.10)                    | 20180101   |
    | datum ingang familierechtelijke betrekking (62.10) | 20190101   |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                                 | waarde   |
    | gemeente van inschrijving (09.10)    | 518      |
    | land vanwaar ingeschreven (14.10)    | 6022     |
    | datum vestiging in Nederland (14.20) | 20180501 |
    | ingangsdatum geldigheid (85.10)      | 20180501 |


  Scenario: Lg01_050 - niet gehuwd, 2 minderjarige kinderen (tweeling) geadopteerd door echtpaar, heeft hierdoor geen gezag meer over ex-kinderen
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon geen gezag

  Scenario: Lg01_051 - minderjarig kind, geadopteerd + voornaamswijziging, onder gezag Derde (vóór adoptie).
    # Route: 27 - Wie heeft gezag?: beide ouders (OG2)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000024                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |

  Scenario: Lg01_052 - gehuwd + 7 adoptiekinderen
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000024                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000061                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000073                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000085                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000097                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000103                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000115                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |

  Scenario: Lg01_053 - gehuwd + 7 adoptiekinderen
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000024                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000061                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000073                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000085                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000097                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000103                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000115                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |

  Scenario: Lg01_054 - minderjarig kind, geadopteerd uit buitenland, Nederlandse adoptieakte
    # Route: 49a - Wie heeft gezag?: beide ouders (OG2)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000061 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000061                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |

  Scenario: Lg01_084 - minderjarig kind, geadopteerd + voornaamswijziging, onder gezag adoptieouders, Geen gezag ex-moeder
    # Route: 49 - Wie heeft gezag?: beide ouders (OG2)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000073 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000073 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000073                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |

  Scenario: Lg01_193 - buitenland geboren, geadopteerd met Nederlandse Q-akte in historie
    # Route: 49a - Wie heeft gezag?: beide adoptieouders (OG2)
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
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |

  Scenario: Lg01_194 - buitenland geboren, geadopteerd met Nederlandse Q-akte in historie, onderzoek in 018120
    # Route: 49a - Wie heeft gezag?: beide adoptieouders (OG2)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000097 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000097 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000097                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |

  Scenario: Lg01_195 - buitenland geboren, geadopteerd met Nederlandse Q-akte in historie, onderzoek in 536200
    # Route: 49a - Wie heeft gezag?: beide adoptieouders (OG2)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000103 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000103 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000103                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |

  Scenario: Lg01_196 - buitenland geboren, geadopteerd met Nederlandse Q-akte in historie, afgerond onderzoek in 018120, 026200, 036200
    # Route: 49a - Wie heeft gezag?: beide adoptieouders (OG2)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000115 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000115 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                    |
    | type                             | TweehoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000115                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
