#language: nl

Functionaliteit: Tg024 - Felsen-Freitag - Nietig verklaring huwelijk en Nietig verklaring erkenning


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
      | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
      | 518                  | Turfmarkt          | 53                 | 0518200000583557                           |
    
    # Lg01_130 - kind geboren tijdens huwelijk dat later nietig is verklaard, ander kind erkend en erkenning is vernietigd
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Frederik |
      | voorvoegsel (02.30)             | van      |
      | geslachtsnaam (02.40)           | Felsen   |
      | geboortedatum (03.10)           | 19711101 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | M        |
      | beschrijving document (82.30)   | PKA      |
      | ingangsdatum geldigheid (85.10) | 0        |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde   |
      | voornamen (02.10)                                  | Fientje  |
      | geslachtsnaam (02.40)                              | Flakkee  |
      | geboortedatum (03.10)                              | 19430701 |
      | geboorteland (03.30)                               | 6030     |
      | geslachtsaanduiding (04.10)                        | V        |
      | beschrijving document (82.30)                      | PK       |
      | ingangsdatum geldigheid (85.10)                    | 0        |
      | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde   |
      | voornamen (02.10)                                  | Filip    |
      | voorvoegsel (02.30)                                | van      |
      | geslachtsnaam (02.40)                              | Felsen   |
      | geboortedatum (03.10)                              | 19410901 |
      | geboorteland (03.30)                               | 6030     |
      | geslachtsaanduiding (04.10)                        | M        |
      | beschrijving document (82.30)                      | PK       |
      | ingangsdatum geldigheid (85.10)                    | 0        |
      | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                                | waarde    |
      | burgerservicenummer (01.20)                                         | 000000024 |
      | voornamen (02.10)                                                   | Francien  |
      | geslachtsnaam (02.40)                                               | Freitag   |
      | geboortedatum (03.10)                                               | 19880601  |
      | geboorteland (03.30)                                                | 6030      |
      | geslachtsaanduiding (04.10)                                         | V         |
      | aktenummer (81.20)                                                  | 3AA0102   |
      | ingangsdatum geldigheid (85.10)                                     | 20131001  |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20131001  |
      | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518      |
      | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030      |
    En de 'partner' is gewijzigd naar de volgende gegevens
      | naam                                                          | waarde    |
      | burgerservicenummer (01.20)                                   | 000000024 |
      | voornamen (02.10)                                             | Francien  |
      | geslachtsnaam (02.40)                                         | Freitag   |
      | geboortedatum (03.10)                                         | 19880601  |
      | geboorteland (03.30)                                          | 6030      |
      | geslachtsaanduiding (04.10)                                   | V         |
      | aktenummer (81.20)                                            | 3AN0102   |
      | ingangsdatum geldigheid (85.10)                               | 20160901  |
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10)  | 20131001  |
      | plaats ontbinding huwelijk/geregistreerd partnerschap (07.20) | 0518      |
      | land ontbinding huwelijk/geregistreerd partnerschap (07.30)   | 6030      |
      | reden ontbinding huwelijk/geregistreerd partnerschap (07.40)  | N         |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000036 |
      | voornamen (02.10)               | Felicia   |
      | voorvoegsel (02.30)             | van       |
      | geslachtsnaam (02.40)           | Felsen    |
      | geboortedatum (03.10)           | 20140801  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0105   |
      | ingangsdatum geldigheid (85.10) | 20140801  |
    En de persoon heeft nog een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000048 |
      | voornamen (02.10)               | Fabienne  |
      | voorvoegsel (02.30)             | van       |
      | geslachtsnaam (02.40)           | Felsen    |
      | geboortedatum (03.10)           | 20180601  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AC0106   |
      | ingangsdatum geldigheid (85.10) | 20190201  |
    En het 'kind' is gewijzigd naar de volgende gegevens
      | naam                            | waarde   |
      | aktenummer (81.20)              | 1AN0106  |
      | ingangsdatum geldigheid (85.10) | 20190201 |
    
    # Lg01_131 - kind geboren tijdens huwelijk dat later nietig is verklaard, ander kind erkend en erkenning is vernietigd
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Francien |
      | geslachtsnaam (02.40)           | Freitag  |
      | geboortedatum (03.10)           | 19880601 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | V        |
      | beschrijving document (82.30)   | PKA      |
      | ingangsdatum geldigheid (85.10) | 0        |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde     |
      | voornamen (02.10)                                  | Florentina |
      | geslachtsnaam (02.40)                              | Freeke     |
      | geboortedatum (03.10)                              | 19550801   |
      | geboorteland (03.30)                               | 6030       |
      | geslachtsaanduiding (04.10)                        | V          |
      | beschrijving document (82.30)                      | PK         |
      | ingangsdatum geldigheid (85.10)                    | 0          |
      | datum ingang familierechtelijke betrekking (62.10) | 0          |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde   |
      | voornamen (02.10)                                  | Frits    |
      | geslachtsnaam (02.40)                              | Freitag  |
      | geboortedatum (03.10)                              | 19540401 |
      | geboorteland (03.30)                               | 6030     |
      | geslachtsaanduiding (04.10)                        | M        |
      | beschrijving document (82.30)                      | PK       |
      | ingangsdatum geldigheid (85.10)                    | 0        |
      | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                                | waarde    |
      | burgerservicenummer (01.20)                                         | 000000012 |
      | voornamen (02.10)                                                   | Frederik  |
      | voorvoegsel (02.30)                                                 | van       |
      | geslachtsnaam (02.40)                                               | Felsen    |
      | geboortedatum (03.10)                                               | 19711101  |
      | geboorteland (03.30)                                                | 6030      |
      | geslachtsaanduiding (04.10)                                         | M         |
      | aktenummer (81.20)                                                  | 3AA0102   |
      | ingangsdatum geldigheid (85.10)                                     | 20131001  |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20131001  |
      | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518      |
      | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030      |
    En de 'partner' is gewijzigd naar de volgende gegevens
      | naam                                                          | waarde    |
      | burgerservicenummer (01.20)                                   | 000000012 |
      | voornamen (02.10)                                             | Frederik  |
      | voorvoegsel (02.30)                                           | van       |
      | geslachtsnaam (02.40)                                         | Felsen    |
      | geboortedatum (03.10)                                         | 19711101  |
      | geboorteland (03.30)                                          | 6030      |
      | geslachtsaanduiding (04.10)                                   | M         |
      | aktenummer (81.20)                                            | 3AN0102   |
      | ingangsdatum geldigheid (85.10)                               | 20160901  |
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10)  | 20131001  |
      | plaats ontbinding huwelijk/geregistreerd partnerschap (07.20) | 0518      |
      | land ontbinding huwelijk/geregistreerd partnerschap (07.30)   | 6030      |
      | reden ontbinding huwelijk/geregistreerd partnerschap (07.40)  | N         |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000036 |
      | voornamen (02.10)               | Felicia   |
      | voorvoegsel (02.30)             | van       |
      | geslachtsnaam (02.40)           | Felsen    |
      | geboortedatum (03.10)           | 20140801  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0105   |
      | ingangsdatum geldigheid (85.10) | 20140801  |
    En de persoon heeft nog een 'kind' met de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000048 |
      | voornamen (02.10)               | Fabienne  |
      | geslachtsnaam (02.40)           | Freitag   |
      | geboortedatum (03.10)           | 20180601  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AA0106   |
      | ingangsdatum geldigheid (85.10) | 20180601  |
    En het 'kind' is gecorrigeerd naar de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000048 |
      | voornamen (02.10)               | Fabienne  |
      | voorvoegsel (02.30)             | van       |
      | geslachtsnaam (02.40)           | Felsen    |
      | geboortedatum (03.10)           | 20180601  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AC0106   |
      | ingangsdatum geldigheid (85.10) | 20190201  |
    En het 'kind' is gewijzigd naar de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000048 |
      | voornamen (02.10)               | Fabienne  |
      | geslachtsnaam (02.40)           | Freitag   |
      | geboortedatum (03.10)           | 20180601  |
      | geboorteland (03.30)            | 6030      |
      | aktenummer (81.20)              | 1AN0106   |
      | ingangsdatum geldigheid (85.10) | 20190201  |
    
    # Lg01_132 - geboren uit huwelijk ouders wat later nietig is verklaard
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Felicia  |
      | voorvoegsel (02.30)             | van      |
      | geslachtsnaam (02.40)           | Felsen   |
      | geboortedatum (03.10)           | 20140801 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | V        |
      | aktenummer (81.20)              | 1AA0105  |
      | ingangsdatum geldigheid (85.10) | 20140801 |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde    |
      | burgerservicenummer (01.20)                        | 000000024 |
      | voornamen (02.10)                                  | Francien  |
      | geslachtsnaam (02.40)                              | Freitag   |
      | geboortedatum (03.10)                              | 19880601  |
      | geboorteland (03.30)                               | 6030      |
      | geslachtsaanduiding (04.10)                        | V         |
      | aktenummer (81.20)                                 | 1AA0105   |
      | ingangsdatum geldigheid (85.10)                    | 20140801  |
      | datum ingang familierechtelijke betrekking (62.10) | 20140801  |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde    |
      | burgerservicenummer (01.20)                        | 000000012 |
      | voornamen (02.10)                                  | Frederik  |
      | voorvoegsel (02.30)                                | van       |
      | geslachtsnaam (02.40)                              | Felsen    |
      | geboortedatum (03.10)                              | 19711101  |
      | geboorteland (03.30)                               | 6030      |
      | geslachtsaanduiding (04.10)                        | M         |
      | aktenummer (81.20)                                 | 1AA0105   |
      | ingangsdatum geldigheid (85.10)                    | 20140801  |
      | datum ingang familierechtelijke betrekking (62.10) | 20140801  |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20140801 |
    
    # Lg01_133 - erkend, maar erkenning is vernietigd
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
      | naam                            | waarde   |
      | voornamen (02.10)               | Fabienne |
      | geslachtsnaam (02.40)           | Freitag  |
      | geboortedatum (03.10)           | 20180601 |
      | geboorteland (03.30)            | 6030     |
      | geslachtsaanduiding (04.10)     | V        |
      | aktenummer (81.20)              | 1AA0106  |
      | ingangsdatum geldigheid (85.10) | 20180601 |
    En de persoon is gecorrigeerd naar de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000048 |
      | voornamen (02.10)               | Fabienne  |
      | voorvoegsel (02.30)             | van       |
      | geslachtsnaam (02.40)           | Felsen    |
      | geboortedatum (03.10)           | 20180601  |
      | geboorteland (03.30)            | 6030      |
      | geslachtsaanduiding (04.10)     | V         |
      | aktenummer (81.20)              | 1AC0106   |
      | ingangsdatum geldigheid (85.10) | 20190201  |
    En de persoon is gewijzigd naar de volgende gegevens
      | naam                            | waarde    |
      | burgerservicenummer (01.20)     | 000000048 |
      | voornamen (02.10)               | Fabienne  |
      | geslachtsnaam (02.40)           | Freitag   |
      | geboortedatum (03.10)           | 20180601  |
      | geboorteland (03.30)            | 6030      |
      | geslachtsaanduiding (04.10)     | V         |
      | aktenummer (81.20)              | 1AN0106   |
      | ingangsdatum geldigheid (85.10) | 20190201  |
    En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde    |
      | burgerservicenummer (01.20)                        | 000000024 |
      | voornamen (02.10)                                  | Francien  |
      | geslachtsnaam (02.40)                              | Freitag   |
      | geboortedatum (03.10)                              | 19880601  |
      | geboorteland (03.30)                               | 6030      |
      | geslachtsaanduiding (04.10)                        | V         |
      | aktenummer (81.20)                                 | 1AA0106   |
      | ingangsdatum geldigheid (85.10)                    | 20180601  |
      | datum ingang familierechtelijke betrekking (62.10) | 20180601  |
    En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde   |
      | aktenummer (81.20)              | 1AA0106  |
      | ingangsdatum geldigheid (85.10) | 20180601 |
    En de ouder '2' is gecorrigeerd naar de volgende gegevens
      | naam                                               | waarde    |
      | burgerservicenummer (01.20)                        | 000000012 |
      | voornamen (02.10)                                  | Frederik  |
      | voorvoegsel (02.30)                                | van       |
      | geslachtsnaam (02.40)                              | Felsen    |
      | geboortedatum (03.10)                              | 19711101  |
      | geboorteland (03.30)                               | 6030      |
      | geslachtsaanduiding (04.10)                        | M         |
      | aktenummer (81.20)                                 | 1AC0106   |
      | ingangsdatum geldigheid (85.10)                    | 20190201  |
      | datum ingang familierechtelijke betrekking (62.10) | 20190201  |
    En de ouder '2' is gewijzigd naar de volgende gegevens
      | naam                            | waarde   |
      | aktenummer (81.20)              | 1AN0106  |
      | ingangsdatum geldigheid (85.10) | 20190201 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      | ingangsdatum geldigheid (85.10)   | 20180601 |


  Scenario: Lg01_130 - kind geboren tijdens huwelijk dat later nietig is verklaard, ander kind erkend en erkenning is vernietigd
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000036                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |

  Scenario: Lg01_131 - kind geboren tijdens huwelijk dat later nietig is verklaard, ander kind erkend en erkenning is vernietigd
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000036                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000048                |
      | ouder.burgerservicenummer        | 000000024                |

  Scenario: Lg01_132 - geboren uit huwelijk ouders wat later nietig is verklaard
    # Route: 45 - Wie heeft gezag?: beide ouders (OG2)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000036                 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
    En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |

  Scenario: Lg01_133 - erkend, maar erkenning is vernietigd
    # Route: 40o1 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000048                |
      | ouder.burgerservicenummer        | 000000024                |
