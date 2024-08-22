#language: nl

Functionaliteit: 3.1 - Is er door een recente gebeurtenis het gezag toch (weer) van rechtswege ondanks dat er eerder een uitspraak is gedaan?



    Achtergrond:
      Gegeven adres 'A1' heeft de volgende gegevens
      | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) |
      | 518                  | Turfmarkt          | 1                  |
      En adres 'A2' heeft de volgende gegevens
      | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) |
      | 518                  | Turfmarkt          | 2                  |
      En de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                  | waarde |
      | geslachtsnaam (02.40) | Check  |
      En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                               | waarde    |
      | burgerservicenummer (01.20)                                        | 000000036 |
      | geslachtsnaam (02.40)                                              | Test      |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) | 20010928  |
      | soort verbintenis (15.10)                                          | H         |
      En de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                  | waarde |
      | geslachtsnaam (02.40) | Test   |
      En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                               | waarde    |
      | burgerservicenummer (01.20)                                        | 000000024 |
      | geslachtsnaam (02.40)                                              | Check     |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) | 20010928  |
      | soort verbintenis (15.10)                                          | H         |


  Regel: Als adoptie heeft plaatsgevonden na de gerechtelijke uitspraak over gezag, wordt het gezag van rechtswege bepaald
    # Er heeft adoptie plaatsgevonden als aktenummer van de persoon in de actuele of een historisch voorkomen als 3e karakter een 'Q' heeft

    Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd door beide ouders na de gerechtelijke uitspraak (route 27)
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Test             |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AA0100          |
      | ingangsdatum geldigheid (85.10) | morgen - 15 jaar |
      En de persoon is gewijzigd naar de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Test             |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AQ0100          |
      | ingangsdatum geldigheid (85.10) | morgen - 10 jaar |
      En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                  | waarde           |
      | datum eerste inschrijving GBA (68.10) | morgen - 15 jaar |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                               | waarde           |
      | gemeente van inschrijving (09.10)  | 0518             |
      | datum aanvang adreshouding (10.30) | morgen - 15 jaar |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Check            |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 10 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000036        |
      | geslachtsnaam (02.40)                              | Test             |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 10 jaar |
      En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde                      |
      | indicatie gezag minderjarige (32.10) | <indicatie gezag>           |
      | beschrijving document (82.30)        | kennisgeving gezagsregister |
      | ingangsdatum geldigheid (85.10)      | morgen - 12 jaar            |
      Als gezag wordt gezocht met de volgende parameters
      | naam | waarde    |
      | burgerservicenummer  | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000012                 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |

      Voorbeelden:
      | indicatie gezag |
      | 1               |
      | 2               |
      | 12              |
      | 1D              |
      | 2D              |
      | D               |

    Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd na de gerechtelijke uitspraak en gegevens van de minderjarige zijn daarna gewijzigd met ander aktenummer (route 27)
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Test             |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AA0100          |
      | ingangsdatum geldigheid (85.10) | morgen - 15 jaar |
      En de persoon is gewijzigd naar de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Test             |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AQ0100          |
      | ingangsdatum geldigheid (85.10) | morgen - 10 jaar |
      En de persoon is gewijzigd naar de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Check            |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AM0100          |
      | ingangsdatum geldigheid (85.10) | morgen - 9 jaar  |
      En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                  | waarde           |
      | datum eerste inschrijving GBA (68.10) | morgen - 15 jaar |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                               | waarde           |
      | gemeente van inschrijving (09.10)  | 0518             |
      | datum aanvang adreshouding (10.30) | morgen - 15 jaar |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Check            |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 10 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000036        |
      | geslachtsnaam (02.40)                              | Test             |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 10 jaar |
      En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde                      |
      | indicatie gezag minderjarige (32.10) | D                           |
      | beschrijving document (82.30)        | kennisgeving gezagsregister |
      | ingangsdatum geldigheid (85.10)      | morgen - 12 jaar            |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000012                 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |

    Abstract Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige die is geadopteerd vóór de gerechtelijke uitspraak (route <route>)
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Test             |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AA0100          |
      | ingangsdatum geldigheid (85.10) | morgen - 15 jaar |
      En de persoon is gewijzigd naar de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Test             |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AQ0100          |
      | ingangsdatum geldigheid (85.10) | morgen - 12 jaar |
      En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                  | waarde           |
      | datum eerste inschrijving GBA (68.10) | morgen - 15 jaar |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                               | waarde           |
      | gemeente van inschrijving (09.10)  | 0518             |
      | datum aanvang adreshouding (10.30) | morgen - 15 jaar |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Check            |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 12 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000036        |
      | geslachtsnaam (02.40)                              | Test             |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 12 jaar |
      En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde                      |
      | indicatie gezag minderjarige (32.10) | <indicatie gezag>           |
      | beschrijving document (82.30)        | kennisgeving gezagsregister |
      | ingangsdatum geldigheid (85.10)      | morgen - 10 jaar            |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000012                |
      | ouder.burgerservicenummer        | <gezaghebbende>          |


      Voorbeelden:
      | indicatie gezag | gezaghebbende | route |
      | 1               | 000000024     | 11    |
      | 2               | 000000036     | 13    |

    Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige die is erkend (niet geadopteerd) na de gerechtelijke uitspraak (route 4)
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Check            |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AA0100          |
      | ingangsdatum geldigheid (85.10) | morgen - 15 jaar |
      En de persoon is gewijzigd naar de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Test             |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AA0200          |
      | ingangsdatum geldigheid (85.10) | morgen - 9 jaar  |
      En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                  | waarde           |
      | datum eerste inschrijving GBA (68.10) | morgen - 15 jaar |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                               | waarde           |
      | gemeente van inschrijving (09.10)  | 0518             |
      | datum aanvang adreshouding (10.30) | morgen - 15 jaar |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Check            |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 15 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000036        |
      | geslachtsnaam (02.40)                              | Test             |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 10 jaar |
      En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde                      |
      | indicatie gezag minderjarige (32.10) | 1D                          |
      | beschrijving document (82.30)        | kennisgeving gezagsregister |
      | ingangsdatum geldigheid (85.10)      | morgen - 12 jaar            |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000012 |
      En heeft 'gezag' geen derden

    Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige waarbij na de gerechtelijke uitspraak ten onrechte adoptie is geregistreerd (route 4)
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Test             |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AA0100          |
      | ingangsdatum geldigheid (85.10) | morgen - 15 jaar |
      En de persoon is gewijzigd naar de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Test             |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AQ0100          |
      | ingangsdatum geldigheid (85.10) | morgen - 10 jaar |
      En de persoon is gecorrigeerd naar de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Test             |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AA0200          |
      | ingangsdatum geldigheid (85.10) | morgen - 15 jaar |
      En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                  | waarde           |
      | datum eerste inschrijving GBA (68.10) | morgen - 15 jaar |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                               | waarde           |
      | gemeente van inschrijving (09.10)  | 0518             |
      | datum aanvang adreshouding (10.30) | morgen - 15 jaar |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Check            |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 10 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000036        |
      | geslachtsnaam (02.40)                              | Test             |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 10 jaar |
      En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde                      |
      | indicatie gezag minderjarige (32.10) | D                           |
      | beschrijving document (82.30)        | kennisgeving gezagsregister |
      | ingangsdatum geldigheid (85.10)      | morgen - 12 jaar            |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000012 |
      En heeft 'gezag' geen derden


  Regel: Als de ingangsdatum van de gerechtelijke uitspraak over gezag onbekend is, is het gezag niet te bepalen

    Abstract Scenario: gezag kan niet worden bepaald wanneer minderjarige is geadopteerd en er is een gerechtelijke uitspraak over gezag met ingangsdatum geldigheid <omschrijving>
      # gezag niet te bepalen. ingangsdatum geldigheid van gerechtelijke uitspraak ontbreekt
      # hoe gaat code om met gedeeltelijk onbekende datums die zeker voor of na adoptie liggen?
      # hoe werkt dit wanneer datum gezag onbekend is, maar er is geen adoptie en geen hertrouwen?
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Test             |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AA0100          |
      | ingangsdatum geldigheid (85.10) | morgen - 15 jaar |
      En de persoon is gewijzigd naar de volgende gegevens
      | naam                            | waarde           |
      | voornamen (02.10)               | Junior           |
      | geslachtsnaam (02.40)           | Test             |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteplaats (03.20)          | 0518             |
      | geboorteland (03.30)            | 6030             |
      | aktenummer (81.20)              | 1AQ0100          |
      | ingangsdatum geldigheid (85.10) | 20210526         |
      En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                  | waarde           |
      | datum eerste inschrijving GBA (68.10) | morgen - 15 jaar |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                               | waarde           |
      | gemeente van inschrijving (09.10)  | 0518             |
      | datum aanvang adreshouding (10.30) | morgen - 15 jaar |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Check            |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 10 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000036        |
      | geslachtsnaam (02.40)                              | Test             |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 10 jaar |
      En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde                          |
      | indicatie gezag minderjarige (32.10) | 1                               |
      | beschrijving document (82.30)        | kennisgeving gezagsregister     |
      | ingangsdatum geldigheid (85.10)      | <ingangsdatum geldigheid gezag> |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                               |
      | type        | GezagNietTeBepalen                                                                   |
      | toelichting | gezag niet te bepalen. ingangsdatum geldigheid van gerechtelijke uitspraak ontbreekt |

      Voorbeelden:
      | ingangsdatum geldigheid gezag | omschrijving                                             |
      | 00000000                      | volledig onbekend                                        |
      | 20190000                      | alleen jaar is bekend en ligt zeker voor datum adoptie   |
      | 20230000                      | alleen jaar is bekend en ligt zeker na datum adoptie     |
      | 20210000                      | alleen jaar is bekend en is zelfde jaar als adoptie      |
      | 20210500                      | jaar en maand is bekend en ligt zeker voor datum adoptie |
      | 20210700                      | jaar en maand is bekend en ligt zeker na datum adoptie   |
      | 20210600                      | jaar en maand is bekend en is zelfde maand als adoptie   |

    Scenario: gezag wordt voor minderjarige met een gerechtelijke uitspraak over gezag met onbekende ingangsdatum geldigheid en de minderjarige is niet geadopteerd en ouders zijn niet hertrouwd


  Regel: Als datum familierechtelijke betrekking of indicatie gezag of ingangsdatum gezagsverhouding in onderzoek staan, is het gezag niet te bepalen

    Abstract Scenario: gezag kan niet worden bepaald wanneer <omschrijving> in onderzoek staat

      Voorbeelden:
      | aanduiding onderzoek | omschrijving                                           |
      | 020000               | hele categorie ouder 1                                 |
      | 026200               | hele groep familierechtelijke betrekking van ouder 1   |
      | 026210               | datum ingang familierechtelijke betrekking van ouder 1 |
      | 030000               | hele categorie ouder 2                                 |
      | 036200               | hele groep familierechtelijke betrekking van ouder 2   |
      | 036210               | datum ingang familierechtelijke betrekking van ouder 2 |

    Abstract Scenario: gezag kan niet worden bepaald wanneer <omschrijving> in onderzoek staat

      Voorbeelden:
      | aanduiding onderzoek | omschrijving                    |
      | 110000               | hele categorie gezagsverhouding |
      | 113200               | hele groep gezag minderjarige   |
      | 113210               | indicatie gezag minderjarige    |
      | 118500               | hele groep geldigheid           |
      | 118510               | datum ingang geldigheid         |

    Scenario: gezag kan wel worden bepaald als het onderzoek op de familierechtelijke betrekking is beëindigd

    Scenario: gezag kan wel worden bepaald als het onderzoek op de gezagsverhouding is beëindigd


  Regel: Als de ouders met elkaar zijn hertrouwd na gerechtelijke uitspraak tot gezag voor één van beide ouders, wordt het gezag van rechtswege bepaald
    # Dit is het geval wanneer:
    # * indicatie gezag is '1' of '2'
    # * ouder1 en ouder2 met elkaar zijn gehuwd of geregistreerd partnerschap aangegaan
    # * datum aangaan huwelijk of geregistreerd partnerschap ligt na de ingangsdatum geldigheid van de gezagsverhouding 

    Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige waarbij na gerechtelijke uitspraak tot gezag ouder<indicatie gezag> de ouders hertrouwd
      # Twee ouderlijk gezag

      Voorbeelden:
      | indicatie gezag |
      | 1               |
      | 2               |

    Abstract Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige waarbij na gerechtelijke uitspraak tot gezag ouder<indicatie gezag> de ouders getrouwd zijn met een andere derde persoon
      # Een ouderlijk gezag voor ouder<indicatie gezag>

      Voorbeelden:
      | indicatie gezag |
      | 1               |
      | 2               |

    Abstract Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige waarbij na gerechtelijke uitspraak tot gezag ouder<indicatie gezag> zijn de ouders nog steeds met elkaar getrouwd
      # Een ouderlijk gezag voor ouder<indicatie gezag>

      Voorbeelden:
      | indicatie gezag |
      | 1               |
      | 2               |


  Regel: Als er een gerechtelijke uitspraak over gezag is vastgesteld voor een ouder die niet ingeschreven staat in de BRP, is het gezag niet te bepalen

    Abstract Scenario: gezag kan niet worden bepaald voor minderjarige met gerechtelijke uitspraak gezag <indicatie gezag> en er is geen persoonslijst voor ouder1

      Voorbeelden:
      | indicatie gezag |
      | 1               |
      | 12              |

    Abstract Scenario: gezag kan niet worden bepaald voor minderjarige met gerechtelijke uitspraak gezag <indicatie gezag> en er is geen persoonslijst voor ouder2

      Voorbeelden:
      | indicatie gezag |
      | 2               |
      | 12              |

    Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige met gerechtelijke uitspraak gezag 1 en er is geen persoonslijst voor ouder2


  Regel: Als de ouders met elkaar zijn hertrouwd na gerechtelijke uitspraak tot gezag voor één van beide ouders en de datum aangaan van het huwelijk of partnerschap staat in onderzoek, is het gezag niet te bepalen
    # kijk je ook naar onderzoek op burgerservicenummer of naam van partner op PL van ouder?

    Abstract Scenario: gezag kan niet worden bepaald voor minderjarige waarvan de ouders met elkaar zijn hertrouwd na gerechtelijke uitspraak tot gezag en <omschrijving> van ouder1 staat in onderzoek

      Voorbeelden:
      | aanduiding onderzoek | omschrijving                                |
      | 050000               | hele categorie partnerschap                 |
      | 050600               | hele groep aangaan huwelijk of partnerschap |
      | 050610               | datum aangaan huwelijk of partnerschap      |

    Abstract Scenario: gezag kan niet worden bepaald voor minderjarige waarvan de ouders met elkaar zijn hertrouwd na gerechtelijke uitspraak tot gezag en <omschrijving> van ouder2 staat in onderzoek

      Voorbeelden:
      | aanduiding onderzoek | omschrijving                                |
      | 050000               | hele categorie partnerschap                 |
      | 050600               | hele groep aangaan huwelijk of partnerschap |
      | 050610               | datum aangaan huwelijk of partnerschap      |


    Scenario: gezag wordt van rechtswege bepaald voor minderjarige waarvan de ouders met elkaar zijn hertrouwd na de gerechtelijke uitspraak tot gezag en het onderzoek naar partnerschap van ouder1 is beëindigd

    Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige waarvan de ouders met elkaar zijn hertrouwd na de gerechtelijke uitspraak tot gezag en <omschrijving> van ouder1 staat in onderzoek

      Voorbeelden:
      | aanduiding onderzoek | omschrijving                    |
      | 050120               | burgerservicenummer partner     |
      | 050200               | hele groep naam van partner     |
      | 050240               | geslachtsnaam van partner       |
      | 050300               | hele groep geboorte van partner |


  Regel: Als vaderschap is ontkend of de erkenning is vernietigd en er was een uitspraak gezag voor die ouder, wordt het gezag van rechtswege bepaald

    Abstract Scenario: er is uitspraak gezag <indicatie gezag> en erkenning door ouder 1 is ontkend of vernietigd
      # Eenouderlijk gezag voor ouder 2

      Voorbeelden:
      | indicatie gezag |
      | 1               |
      | 12              |

    Abstract Scenario: er is uitspraak gezag <indicatie gezag> en erkenning door ouder 2 is ontkend of vernietigd
      # Eenouderlijk gezag voor ouder 1

      Voorbeelden:
      | indicatie gezag |
      | 2               |
      | 12              |

    Abstract Scenario: er is uitspraak gezag 1 en erkenning door ouder 2 is ontkend of vernietigd
      # Eenouderlijk gezag voor ouder 1

    Scenario: er is uitspraak gezag voogdij en erkenning door ouder 2 is ontkend of vernietigd
      # gezag is voogdij
