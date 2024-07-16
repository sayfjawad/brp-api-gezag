#language: nl

Functionaliteit: Tg036 - Taher – Gegevens in onderzoek


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 79                 | 0518200000583570                           |
    
    # Lg01_188 - moeder van minderjarig kind, 010100 in onderzoek
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000012  |
    | voornamen (02.10)               | Tasnim     |
    | geslachtsnaam (02.40)           | Taher      |
    | geboortedatum (03.10)           | 19851211   |
    | geboorteland (03.30)            | 7009       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | voe        |
    | aanduiding in onderzoek (83.10) | 10100      |
    | datum ingang onderzoek (83.20)  | 20200202   |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | geslachtsnaam (02.40)                              | .        |
    | beschrijving document (82.30)                      | .        |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | geslachtsnaam (02.40)                              | .        |
    | beschrijving document (82.30)                      | .        |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                                 | waarde   |
    | gemeente van inschrijving (09.10)    | 518      |
    | land vanwaar ingeschreven (14.10)    | 7009     |
    | datum vestiging in Nederland (14.20) | 20090101 |
    | ingangsdatum geldigheid (85.10)      | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Thaliya    |
    | geslachtsnaam (02.40)           | Taher      |
    | geboortedatum (03.10)           | 20100316   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0153    |
    | ingangsdatum geldigheid (85.10) | 20100316   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 999970331  |
    | voornamen (02.10)               | Tamineh    |
    | geslachtsnaam (02.40)           | Taher      |
    | geboortedatum (03.10)           | 20100316   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0152    |
    | ingangsdatum geldigheid (85.10) | 20100316   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000012  |
    | voornamen (02.10)               | Tayeh      |
    | geslachtsnaam (02.40)           | Taher      |
    | geboortedatum (03.10)           | 20100316   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0151    |
    | ingangsdatum geldigheid (85.10) | 20100316   |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Thurayya   |
    | geslachtsnaam (02.40)           | Taher      |
    | geboortedatum (03.10)           | 20100316   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0150    |
    | ingangsdatum geldigheid (85.10) | 20100316   |
    
    # Lg01_189 - minderjarig kind, 010000 in onderzoek
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Thurayya   |
    | geslachtsnaam (02.40)           | Taher      |
    | geboortedatum (03.10)           | 20100316   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0150    |
    | aanduiding in onderzoek (83.10) | 10000      |
    | datum ingang onderzoek (83.20)  | 20200202   |
    | ingangsdatum geldigheid (85.10) | 20100316   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Tasnim     |
    | geslachtsnaam (02.40)                              | Taher      |
    | geboortedatum (03.10)                              | 19851211   |
    | geboorteland (03.30)                               | 7009       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0150    |
    | ingangsdatum geldigheid (85.10)                    | 20100316   |
    | datum ingang familierechtelijke betrekking (62.10) | 20100316   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0150  |
    | ingangsdatum geldigheid (85.10) | 20100316 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    
    # Lg01_190 - minderjarig kind, 080000 in onderzoek
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Tayeh      |
    | geslachtsnaam (02.40)           | Taher      |
    | geboortedatum (03.10)           | 20100316   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0151    |
    | ingangsdatum geldigheid (85.10) | 20100316   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Tasnim     |
    | geslachtsnaam (02.40)                              | Taher      |
    | geboortedatum (03.10)                              | 19851211   |
    | geboorteland (03.30)                               | 7009       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0151    |
    | ingangsdatum geldigheid (85.10)                    | 20100316   |
    | datum ingang familierechtelijke betrekking (62.10) | 20100316   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0151  |
    | ingangsdatum geldigheid (85.10) | 20100316 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | aanduiding in onderzoek (83.10)   | 80000    |
    | datum ingang onderzoek (83.20)    | 20220202 |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    
    # Lg01_191 - minderjarig kind, 020000 in onderzoek
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Tamineh    |
    | geslachtsnaam (02.40)           | Taher      |
    | geboortedatum (03.10)           | 20100316   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0152    |
    | ingangsdatum geldigheid (85.10) | 20100316   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Tasnim     |
    | geslachtsnaam (02.40)                              | Taher      |
    | geboortedatum (03.10)                              | 19851211   |
    | geboorteland (03.30)                               | 7009       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0152    |
    | aanduiding in onderzoek (83.10)                    | 20000      |
    | datum ingang onderzoek (83.20)                     | 20220202   |
    | ingangsdatum geldigheid (85.10)                    | 20100316   |
    | datum ingang familierechtelijke betrekking (62.10) | 20100316   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0152  |
    | ingangsdatum geldigheid (85.10) | 20100316 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    
    # Lg01_192 - minderjarig kind, 010320 in onderzoek
    Gegeven de persoon met burgerservicenummer '000000061' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Thaliya    |
    | geslachtsnaam (02.40)           | Taher      |
    | geboortedatum (03.10)           | 20100316   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0153    |
    | aanduiding in onderzoek (83.10) | 10320      |
    | datum ingang onderzoek (83.20)  | 20220202   |
    | ingangsdatum geldigheid (85.10) | 20100316   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Tasnim     |
    | geslachtsnaam (02.40)                              | Taher      |
    | geboortedatum (03.10)                              | 19851211   |
    | geboorteland (03.30)                               | 7009       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0153    |
    | ingangsdatum geldigheid (85.10)                    | 20100316   |
    | datum ingang familierechtelijke betrekking (62.10) | 20100316   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0153  |
    | ingangsdatum geldigheid (85.10) | 20100316 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |


  Scenario: Lg01_188 - moeder van minderjarig kind, 010100 in onderzoek
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT )
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                       |
    | burgerservicenummer | 000000012                                                                    |
    | toelichting         | Ingezeten - meerderjarig - soort gezag is NVT - gezag is niet van toepassing |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000036                |
    | ouder.burgerservicenummer        | 000000012                |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000061                |
    | ouder.burgerservicenummer        | 000000012                |

  Scenario: Lg01_189 - minderjarig kind, 010000 in onderzoek
    # Route: 40o1i - Wie heeft gezag?: niet te bepalen (N)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                                                                                                                                                                                                                                                                                                                          |
    | burgerservicenummer | 000000024                                                                                                                                                                                                                                                                                                                                                                       |
    | toelichting         | Ingezeten - minderjarig - heeft één ouder - ouder ongehuwd/geen partnerschap - soort gezag is OG1 - ouder1 heeft gezag. Uitspraak is gezag niet te bepalen, omdat er bij de gezagbepaling waardes in onderzoek waren gedetecteerd. Bij het bepalen van gezag werd het volgende veld gebruikt dat in onderzoek staat: geboortedatum, geboorteland, burgerservicenummer van ouder |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam | waarde             |
    | type | GezagNietTeBepalen |

  Scenario: Lg01_190 - minderjarig kind, 080000 in onderzoek
    # Route: 40o1 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                                                                 |
    | burgerservicenummer | 000000036                                                                                                              |
    | toelichting         | Ingezeten - minderjarig - heeft één ouder - ouder ongehuwd/geen partnerschap - soort gezag is OG1 - ouder1 heeft gezag |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000036                |
    | ouder.burgerservicenummer        | 000000012                |

  Scenario: Lg01_191 - minderjarig kind, 020000 in onderzoek
    # Route: 40o1i - Wie heeft gezag?: niet te bepalen (N)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                                                                                                                                                                                                                                                                                                      |
    | burgerservicenummer | 000000048                                                                                                                                                                                                                                                                                                                                                   |
    | toelichting         | Ingezeten - minderjarig - heeft één ouder - ouder ongehuwd/geen partnerschap - soort gezag is OG1 - ouder1 heeft gezag. Uitspraak is gezag niet te bepalen, omdat er bij de gezagbepaling waardes in onderzoek waren gedetecteerd. Bij het bepalen van gezag werd het volgende veld gebruikt dat in onderzoek staat: ouder 1, burgerservicenummer van ouder |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam | waarde             |
    | type | GezagNietTeBepalen |

  Scenario: Lg01_192 - minderjarig kind, 010320 in onderzoek
    # Route: 40o1 - Wie heeft gezag?: moeder (OG1)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde                                                                                                                 |
    | burgerservicenummer | 000000061                                                                                                              |
    | toelichting         | Ingezeten - minderjarig - heeft één ouder - ouder ongehuwd/geen partnerschap - soort gezag is OG1 - ouder1 heeft gezag |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde                   |
    | type                             | EenhoofdigOuderlijkGezag |
    | minderjarige.burgerservicenummer | 000000061                |
    | ouder.burgerservicenummer        | 000000012                |
