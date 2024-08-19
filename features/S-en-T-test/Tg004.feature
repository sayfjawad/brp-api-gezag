#language: nl

Functionaliteit: Tg004 - Custers - Bij geboorte minderjarige moeders


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 5                  | 0518200000583529                           |
    
    # Lg01_013 - ongehuwde minderjarige moeder met 1 minderjarig niet erkend kind
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde           |
    | voornamen (02.10)               | Cindy            |
    | geslachtsnaam (02.40)           | Custers          |
    | geboortedatum (03.10)           | morgen - 18 jaar |
    | geboorteland (03.30)            | 6030             |
    | geslachtsaanduiding (04.10)     | V                |
    | aktenummer (81.20)              | 1AA0100          |
    | ingangsdatum geldigheid (85.10) | morgen - 18 jaar |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde                         |
    | burgerservicenummer (01.20)     | 000000012                      |
    | voornamen (02.10)               | Cindy                          |
    | geslachtsnaam (02.40)           | Custers                        |
    | geboortedatum (03.10)           | morgen - 18 jaar               |
    | geboorteland (03.30)            | 6030                           |
    | geslachtsaanduiding (04.10)     | V                              |
    | beschrijving document (82.30)   | Melding Minister van Financiën |
    | ingangsdatum geldigheid (85.10) | 20060410                       |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde           |
    | voornamen (02.10)                                  | Carola           |
    | geslachtsnaam (02.40)                              | Custers          |
    | geboortedatum (03.10)                              | 19840401         |
    | geboorteland (03.30)                               | 6030             |
    | geslachtsaanduiding (04.10)                        | V                |
    | aktenummer (81.20)                                 | 1AA0100          |
    | ingangsdatum geldigheid (85.10)                    | morgen - 18 jaar |
    | datum ingang familierechtelijke betrekking (62.10) | morgen - 18 jaar |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde           |
    | aktenummer (81.20)              | 1AA0100          |
    | ingangsdatum geldigheid (85.10) | morgen - 18 jaar |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde           |
    | gemeente van inschrijving (09.10) | 518              |
    | ingangsdatum geldigheid (85.10)   | 20111101         |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Chrissy    |
    | geslachtsnaam (02.40)           | Custers    |
    | geboortedatum (03.10)           | 20210401   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20210401   |
    
    # Lg01_014 - minderjarig niet erkend kind, moeder ook minderjarig, geen categorie 11
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Chrissy    |
    | geslachtsnaam (02.40)           | Custers    |
    | geboortedatum (03.10)           | 20210401   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20210401   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde           |
    | burgerservicenummer (01.20)                        | 000000012        |
    | voornamen (02.10)                                  | Cindy            |
    | geslachtsnaam (02.40)                              | Custers          |
    | geboortedatum (03.10)                              | morgen - 18 jaar |
    | geboorteland (03.30)                               | 6030             |
    | geslachtsaanduiding (04.10)                        | V                |
    | aktenummer (81.20)                                 | 1AA0100          |
    | ingangsdatum geldigheid (85.10)                    | 20210401         |
    | datum ingang familierechtelijke betrekking (62.10) | 20210401         |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0100  |
    | ingangsdatum geldigheid (85.10) | 20210401 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20210401 |
    
    # Lg01_015 - ongehuwde meerderjarige moeder met 1 minderjarig niet-erkend kind,bij geboorte minderjarig inmiddels meerderjarig
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Carice     |
    | geslachtsnaam (02.40)           | Custers    |
    | geboortedatum (03.10)           | 20020501   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20020501   |
    En de persoon is gewijzigd naar de volgende gegevens
    | naam                            | waarde                         |
    | burgerservicenummer (01.20)     | 000000036                      |
    | voornamen (02.10)               | Carice                         |
    | geslachtsnaam (02.40)           | Custers                        |
    | geboortedatum (03.10)           | 20020501                       |
    | geboorteland (03.30)            | 6030                           |
    | geslachtsaanduiding (04.10)     | V                              |
    | beschrijving document (82.30)   | Melding Minister van Financiën |
    | ingangsdatum geldigheid (85.10) | 20020510                       |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Carola   |
    | geslachtsnaam (02.40)                              | Custers  |
    | geboortedatum (03.10)                              | 19840401 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | aktenummer (81.20)                                 | 1AA0100  |
    | ingangsdatum geldigheid (85.10)                    | 20020501 |
    | datum ingang familierechtelijke betrekking (62.10) | 20020501 |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0100  |
    | ingangsdatum geldigheid (85.10) | 20020501 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Carlijn    |
    | geslachtsnaam (02.40)           | Custers    |
    | geboortedatum (03.10)           | 20190501   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20190501   |
    
    # Lg01_016 - minderjarig niet erkend kind, moeder minderjarig, bij geboorte, inmiddels meerderjarig, geen categorie 11
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Carlijn    |
    | geslachtsnaam (02.40)           | Custers    |
    | geboortedatum (03.10)           | 20190501   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20190501   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000036  |
    | voornamen (02.10)                                  | Carice     |
    | geslachtsnaam (02.40)                              | Custers    |
    | geboortedatum (03.10)                              | 20020501   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0100    |
    | ingangsdatum geldigheid (85.10)                    | 20190501   |
    | datum ingang familierechtelijke betrekking (62.10) | 20190501   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0100  |
    | ingangsdatum geldigheid (85.10) | 20190501 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20190501 |


  Scenario: Lg01_013 - ongehuwde minderjarige moeder met 1 minderjarig niet erkend kind
    # Meerderjarig
    # Route: 39e - Wie heeft gezag?: gezag niet te bepalen (N)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon geen gezag

  Scenario: Lg01_014 - minderjarig niet erkend kind, moeder ook minderjarig, geen categorie 11
    # Route: 39 - Wie heeft gezag?: geen gezag (tijdelijk) (G)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam | waarde             |
    | type | TijdelijkGeenGezag |

  Scenario: Lg01_015 - ongehuwde meerderjarige moeder met 1 minderjarig niet-erkend kind,bij geboorte minderjarig inmiddels meerderjarig
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000048                |
    | ouder.burgerservicenummer        | 000000036                |

  Scenario: Lg01_016 - minderjarig niet erkend kind, moeder minderjarig, bij geboorte, inmiddels meerderjarig, geen categorie 11
    # Route: 40o1 - Wie heeft gezag?: Ouder1 eenhoofdig ouderlijk gezag (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000048                |
    | ouder.burgerservicenummer        | 000000036                |
