#language: nl

Functionaliteit: Tg014 - Nicolaesen-Niemandsverdiet-Nijman


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 27                 | 0518200000583544                           |
    
    # Lg01_078 - geregistreerd partnerschap en minderjarig kind geboren vóór 01-04-2014, overleden
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000012  |
    | voornamen (02.10)               | Nicolien   |
    | geslachtsnaam (02.40)           | Nicolaesen |
    | geboortedatum (03.10)           | 19850901   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Noor     |
    | geslachtsnaam (02.40)                              | Nokhoff  |
    | geboortedatum (03.10)                              | 19600901 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Norbert  |
    | geslachtsnaam (02.40)                              | Noteboom |
    | geboortedatum (03.10)                              | 19590901 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde           |
    | burgerservicenummer (01.20)                                         | 000000024        |
    | voornamen (02.10)                                                   | Nout             |
    | geslachtsnaam (02.40)                                               | Niemandsverdriet |
    | geboortedatum (03.10)                                               | 19720901         |
    | geboorteland (03.30)                                                | 6030             |
    | geslachtsaanduiding (04.10)                                         | M                |
    | aktenummer (81.20)                                                  | 5AA0100          |
    | ingangsdatum geldigheid (85.10)                                     | 20120901         |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20120901         |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518             |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030             |
    En de persoon heeft de volgende 'overlijden' gegevens
    | naam                            | waarde   |
    | datum overlijden (08.10)        | 20150901 |
    | aktenummer (81.20)              | 2AA0100  |
    | ingangsdatum geldigheid (85.10) | 20150901 |
    En de persoon heeft de volgende 'inschrijving' gegevens
    | naam                                 | waarde                     |
    | datum opschorting bijhouding (67.10) | 20150901                   |
    | reden opschorting bijhouding (67.20) | O                          |
    | indicatie geheim (70.10)             | 0                          |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Naomi      |
    | geslachtsnaam (02.40)           | Nicolaesen |
    | geboortedatum (03.10)           | 20130901   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20130901   |
    
    # Lg01_079 - geregistreerd partnerschap, partner heeft minderjarig, niet erkend kind geboren vóór 01-04-2014, partner overleden
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde           |
    | burgerservicenummer (01.20)     | 000000024        |
    | voornamen (02.10)               | Nout             |
    | geslachtsnaam (02.40)           | Niemandsverdriet |
    | geboortedatum (03.10)           | 19720901         |
    | geboorteland (03.30)            | 6030             |
    | geslachtsaanduiding (04.10)     | M                |
    | beschrijving document (82.30)   | PKA              |
    | ingangsdatum geldigheid (85.10) | 0                |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Nel      |
    | geslachtsnaam (02.40)                              | Noort    |
    | geboortedatum (03.10)                              | 19520901 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde           |
    | voornamen (02.10)                                  | Noll             |
    | geslachtsnaam (02.40)                              | Niemandsverdriet |
    | geboortedatum (03.10)                              | 19490901         |
    | geboorteland (03.30)                               | 6030             |
    | geslachtsaanduiding (04.10)                        | M                |
    | beschrijving document (82.30)                      | PK               |
    | ingangsdatum geldigheid (85.10)                    | 0                |
    | datum ingang familierechtelijke betrekking (62.10) | 0                |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000012  |
    | voornamen (02.10)                                                   | Nicolien   |
    | geslachtsnaam (02.40)                                               | Nicolaesen |
    | geboortedatum (03.10)                                               | 19850901   |
    | geboorteland (03.30)                                                | 6030       |
    | geslachtsaanduiding (04.10)                                         | V          |
    | aktenummer (81.20)                                                  | 5AA0100    |
    | ingangsdatum geldigheid (85.10)                                     | 20120901   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20120901   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de 'partner' is gewijzigd naar de volgende gegevens
    | naam                                                          | waarde     |
    | burgerservicenummer (01.20)                                   | 000000012  |
    | voornamen (02.10)                                             | Nicolien   |
    | geslachtsnaam (02.40)                                         | Nicolaesen |
    | geboortedatum (03.10)                                         | 19850901   |
    | geboorteland (03.30)                                          | 6030       |
    | geslachtsaanduiding (04.10)                                   | V          |
    | aktenummer (81.20)                                            | 2AA0100    |
    | ingangsdatum geldigheid (85.10)                               | 20150901   |
    | datum ontbinding huwelijk/geregistreerd partnerschap (07.10)  | 20150901   |
    | plaats ontbinding huwelijk/geregistreerd partnerschap (07.20) | 0518       |
    | land ontbinding huwelijk/geregistreerd partnerschap (07.30)   | 6030       |
    | Reden ontbinding huwelijk/geregistreerd partnerschap (07.40)  | O          |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Noralie    |
    | geslachtsnaam (02.40)           | Nicolaesen |
    | geboortedatum (03.10)           | 20151201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20151201   |
    
    # Lg01_080 - moeder geregistreerd partnerschap, niet erkend, geen categorie 11, moeder overleden geregistreerd partner van moeder automatisch gezag
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000036  |
    | voornamen (02.10)               | Naomi      |
    | geslachtsnaam (02.40)           | Nicolaesen |
    | geboortedatum (03.10)           | 20130901   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20130901   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Nicolien   |
    | geslachtsnaam (02.40)                              | Nicolaesen |
    | geboortedatum (03.10)                              | 19850901   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0100    |
    | ingangsdatum geldigheid (85.10)                    | 20130901   |
    | datum ingang familierechtelijke betrekking (62.10) | 20130901   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA0100  |
    | ingangsdatum geldigheid (85.10) | 20130901 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20130901 |
    
    # Lg01_081 - huwelijk personen gelijk geslacht (V), 1 minderjarig kind erkend als ongeboren vrucht door vader
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000048  |
    | voornamen (02.10)               | Norah      |
    | geslachtsnaam (02.40)           | Nicolaesen |
    | geboortedatum (03.10)           | 19830901   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Noor     |
    | geslachtsnaam (02.40)                              | Nokhoff  |
    | geboortedatum (03.10)                              | 19600901 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Norbert  |
    | geslachtsnaam (02.40)                              | Noteboom |
    | geboortedatum (03.10)                              | 19590901 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000061  |
    | voornamen (02.10)                                                   | Nienke     |
    | geslachtsnaam (02.40)                                               | Nijman     |
    | geboortedatum (03.10)                                               | 19780901   |
    | geboorteland (03.30)                                                | 6030       |
    | geslachtsaanduiding (04.10)                                         | V          |
    | aktenummer (81.20)                                                  | 3AA0100    |
    | ingangsdatum geldigheid (85.10)                                     | 20140901   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20140901   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft (nog) een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Noralie    |
    | geslachtsnaam (02.40)           | Nicolaesen |
    | geboortedatum (03.10)           | 20151201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20151201   |
    
    # Lg01_082 - huwelijk personen gelijk geslacht (V), echtgenote 1 minderjarig kind erkend als ongeboren vrucht door vader
    Gegeven de persoon met burgerservicenummer '000000061' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Nienke     |
    | geslachtsnaam (02.40)           | Nijman     |
    | geboortedatum (03.10)           | 19780901   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Naatje   |
    | geslachtsnaam (02.40)                              | Nauta    |
    | geboortedatum (03.10)                              | 19500201 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Nicolaas |
    | geslachtsnaam (02.40)                              | Nijman   |
    | geboortedatum (03.10)                              | 19480301 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 0        |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000048  |
    | voornamen (02.10)                                                   | Norah      |
    | geslachtsnaam (02.40)                                               | Nicolaesen |
    | geboortedatum (03.10)                                               | 19830901   |
    | geboorteland (03.30)                                                | 6030       |
    | geslachtsaanduiding (04.10)                                         | V          |
    | aktenummer (81.20)                                                  | 3AA0100    |
    | ingangsdatum geldigheid (85.10)                                     | 20140901   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20140901   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    
    # Lg01_083 - geboren uit huwelijk 2 vrouwen, als ongeboren vrucht erkend door vader (huwelijkspartner moeder is daardoor geen ouder), geen categorie 11
    Gegeven de persoon met burgerservicenummer '000000073' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Noralie    |
    | geslachtsnaam (02.40)           | Nicolaesen |
    | geboortedatum (03.10)           | 20151201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20151201   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000048  |
    | voornamen (02.10)                                  | Norah      |
    | geslachtsnaam (02.40)                              | Nicolaesen |
    | geboortedatum (03.10)                              | 19830901   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0100    |
    | ingangsdatum geldigheid (85.10)                    | 20151201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20151201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde           |
    | burgerservicenummer (01.20)                        | 000000024        |
    | voornamen (02.10)                                  | Nout             |
    | geslachtsnaam (02.40)                              | Niemandsverdriet |
    | geboortedatum (03.10)                              | 19720901         |
    | geboorteland (03.30)                               | 6030             |
    | geslachtsaanduiding (04.10)                        | M                |
    | aktenummer (81.20)                                 | 1AA0100          |
    | ingangsdatum geldigheid (85.10)                    | 20151201         |
    | datum ingang familierechtelijke betrekking (62.10) | 20151201         |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20151201 |


  Scenario: Lg01_078 - geregistreerd partnerschap en minderjarig kind geboren vóór 01-04-2014, overleden
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon geen gezag

  Scenario: Lg01_079 - geregistreerd partnerschap, partner heeft minderjarig, niet erkend kind geboren vóór 01-04-2014, partner overleden
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde    |
    | type                             | Voogdij   |
    | minderjarige.burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |

  Scenario: Lg01_080 - moeder geregistreerd partnerschap, niet erkend, geen categorie 11, moeder overleden geregistreerd partner van moeder automatisch gezag
    # Route: 43o1 - Wie heeft gezag?: geregistreerd partner van overleden moeder gezag (V)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam                             | waarde    |
    | type                             | Voogdij   |
    | minderjarige.burgerservicenummer | 000000036 |
    En heeft 'gezag' een 'derde' met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |

  Scenario: Lg01_081 - huwelijk personen gelijk geslacht (V), 1 minderjarig kind erkend als ongeboren vrucht door vader
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon geen gezag

  Scenario: Lg01_082 - huwelijk personen gelijk geslacht (V), echtgenote 1 minderjarig kind erkend als ongeboren vrucht door vader
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000061 |
    En heeft de persoon geen gezag

  Scenario: Lg01_083 - geboren uit huwelijk 2 vrouwen, als ongeboren vrucht erkend door vader (huwelijkspartner moeder is daardoor geen ouder), geen categorie 11
    # Route: 54 - Wie heeft gezag?: moeder, maar valt niet te achterhalen (N)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000073 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000073 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam        | waarde                                                                               |
    | type        | GezagNietTeBepalen                                                                   |
    | toelichting | gezag kan niet worden bepaald omdat niet bepaald kan worden welke ouder gezag heeft. |
