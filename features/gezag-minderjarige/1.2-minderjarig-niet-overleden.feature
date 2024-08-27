#language: nl

Functionaliteit: 1.2 - Is persoon a. minderjarig en b. niet overleden?


    Achtergrond:
      Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Check    |
      | geboortedatum (03.10) | 19800101 |
      En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                               | waarde    |
      | burgerservicenummer (01.20)                                        | 000000036 |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) | 20010928  |
      En de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Test     |
      | geboortedatum (03.10) | 19800201 |
      En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                               | waarde    |
      | burgerservicenummer (01.20)                                        | 000000024 |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) | 20010928  |


  Regel: Als de persoon ouder is dan 18 jaar is gezag niet van toepassing

    Scenario: de persoon is minderjarig
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde          |
      | geboortedatum (03.10)  | <geboortedatum> |
      | geboorteland (03.30)   | 6030            |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 0518   |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde          |
      | burgerservicenummer (01.20)                        | 000000024       |
      | geslachtsnaam (02.40)                              | Check           |
      | datum ingang familierechtelijke betrekking (62.10) | <geboortedatum> |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde          |
      | burgerservicenummer (01.20)                        | 000000036       |
      | geslachtsnaam (02.40)                              | Test            |
      | datum ingang familierechtelijke betrekking (62.10) | <geboortedatum> |
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

      Voorbeelden:
      | geboortedatum      |
      | morgen - 18 jaar   |
      | gisteren - 17 jaar |
      | vandaag - 16 jaar  |
      | gisteren           |
      | vandaag            |

    Scenario: de persoon is meerderjarig want is <omschrijving>
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde          |
      | geboortedatum (03.10)  | <geboortedatum> |
      | geboorteland (03.30)   | 6030            |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 0518   |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde          |
      | burgerservicenummer (01.20)                        | 000000024       |
      | geslachtsnaam (02.40)                              | Check           |
      | datum ingang familierechtelijke betrekking (62.10) | <geboortedatum> |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde          |
      | burgerservicenummer (01.20)                        | 000000036       |
      | geslachtsnaam (02.40)                              | Test            |
      | datum ingang familierechtelijke betrekking (62.10) | <geboortedatum> |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon geen gezag

      Voorbeelden:
      | geboortedatum      | omschrijving              |
      | vandaag - 18 jaar  | vandaag 18 jaar geworden  |
      | gisteren - 18 jaar | gisteren 18 jaar geworden |
      | morgen - 19 jaar   | 18 jaar                   |
      | vandaag - 19 jaar  | 19 jaar                   |
      | gisteren - 20 jaar | 20 jaar                   |


  Regel: Als de geboortedatum van de persoon volledig onbekend is wordt de persoon als meerderjarig beschouwd en is gezag niet van toepassing

    Scenario: geboortedatum is volledig onbekend
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                  | waarde   |
      | geboortedatum (03.10) | 00000000 |
      | geboorteland (03.30)  | 6030     |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 0518   |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Check            |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 15 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000036        |
      | geslachtsnaam (02.40)                              | Test             |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 15 jaar |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon geen gezag


  Regel: Als de geboortedatum van de persoon gedeeltelijk bekend is wordt de leeftijd berekend met de 1e dag van de onzekerheidsperiode

    Scenario: geboortedatum <omschrijving>
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde          |
      | geboortedatum (03.10)  | <geboortedatum> |
      | geboorteland (03.30)   | 6030            |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 0518   |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde          |
      | burgerservicenummer (01.20)                        | 000000024       |
      | geslachtsnaam (02.40)                              | Check           |
      | datum ingang familierechtelijke betrekking (62.10) | <geboortedatum> |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde          |
      | burgerservicenummer (01.20)                        | 000000036       |
      | geslachtsnaam (02.40)                              | Test            |
      | datum ingang familierechtelijke betrekking (62.10) | <geboortedatum> |
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

      Voorbeelden:
      | geboortedatum            | omschrijving                                                         |
      | 20230000                 | alleen jaar is bekend maar persoon is zeker minder dan 18 jaar oud   |
      | 20230500                 | jaar en maand is bekend maar persoon is zeker minder dan 18 jaar oud |
      | dit jaar - 17 jaar       | alleen jaar is bekend en persoon wordt volgend jaar 18 jaar oud      |
      | volgende maand - 18 jaar | jaar en maand is bekend en persoon wordt volgende maand 18 jaar oud  |

    Scenario: geboortedatum <omschrijving>
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde          |
      | geboortedatum (03.10)  | <geboortedatum> |
      | geboorteland (03.30)   | 6030            |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 0518   |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde          |
      | burgerservicenummer (01.20)                        | 000000024       |
      | geslachtsnaam (02.40)                              | Check           |
      | datum ingang familierechtelijke betrekking (62.10) | <geboortedatum> |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde          |
      | burgerservicenummer (01.20)                        | 000000036       |
      | geslachtsnaam (02.40)                              | Test            |
      | datum ingang familierechtelijke betrekking (62.10) | <geboortedatum> |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon geen gezag

      Voorbeelden:
      | geboortedatum        | omschrijving                                                          |
      | dit jaar - 18 jaar   | alleen jaar is bekend en persoon wordt of is dit jaar 18 jaar oud     |
      | deze maand - 18 jaar | jaar en maand is bekend en persoon wordt of is deze maand 18 jaar oud |


  Regel: Wanneer er onderzoek loopt naar de geboortedatum is gezag niet te bepalen

    Abstract Scenario: persoon is volgens geboortedatum <meer of minderjarig> en <onderzoek omschrijving> staat in onderzoek
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde                    |
      | geboortedatum (03.10)           | <geboortedatum>           |
      | geboorteland (03.30)            | 6030                      |
      | aanduiding in onderzoek (83.10) | <aanduiding in onderzoek> |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 0518   |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde          |
      | burgerservicenummer (01.20)                        | 000000024       |
      | geslachtsnaam (02.40)                              | Check           |
      | datum ingang familierechtelijke betrekking (62.10) | <geboortedatum> |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde          |
      | burgerservicenummer (01.20)                        | 000000036       |
      | geslachtsnaam (02.40)                              | Test            |
      | datum ingang familierechtelijke betrekking (62.10) | <geboortedatum> |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                         |
      | type        | GezagNietTeBepalen                                                                                             |
      | toelichting | gezag is niet te bepalen omdat de volgende relevante gegevens in onderzoek staan: geboortedatum van de persoon |

      Voorbeelden:
      | geboortedatum    | meer of minderjarig | aanduiding in onderzoek | onderzoek omschrijving | toelichting                                            |
      | morgen - 15 jaar | minderjarig         | 010000                  | hele categorie persoon | geboortedatum van de persoon, geboorteland van persoon |
      | morgen - 15 jaar | minderjarig         | 010300                  | hele groep geboorte    | geboortedatum van de persoon, geboorteland van persoon |
      | morgen - 15 jaar | minderjarig         | 010310                  | geboortedatum          | geboortedatum van de persoon                           |
      | morgen - 21 jaar | meerderjarig        | 010310                  | geboortedatum          | geboortedatum van de persoon                           |

    Abstract Scenario: bij minderjarige staat <onderzoek omschrijving> in onderzoek
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde                    |
      | geboortedatum (03.10)           | morgen - 15 jaar          |
      | geboorteland (03.30)            | 6030                      |
      | aanduiding in onderzoek (83.10) | <aanduiding in onderzoek> |
      | datum einde onderzoek (83.30)   | 20230526                  |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 0518   |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Check            |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 15 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000036        |
      | geslachtsnaam (02.40)                              | Test             |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 15 jaar |
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

      Voorbeelden:
      | aanduiding in onderzoek | onderzoek omschrijving |
      | 010120                  | burgerservicenummer    |
      | 010200                  | hele groep naam        |
      | 010240                  | geslachtsnaam          |
      | 010320                  | geboorteplaats         |

    Scenario: minderjarige heeft beëindigd onderzoek naar de geboortedatum
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde           |
      | geboortedatum (03.10)           | morgen - 15 jaar |
      | geboorteland (03.30)            | 6030             |
      | aanduiding in onderzoek (83.10) | 010310           |
      | datum einde onderzoek (83.30)   | 20230526         |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 0518   |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Check            |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 15 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000036        |
      | geslachtsnaam (02.40)                              | Test             |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 15 jaar |
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

 
  Regel: Als de geboortedatum van de persoon ontbreekt is gezag niet te bepalen

    Scenario: de geboortedatum van de persoon is niet ingevuld1
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde          |
      | geboorteland (03.30)   | 6030            |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 0518   |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Check            |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 15 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000036        |
      | geslachtsnaam (02.40)                              | Test             |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 15 jaar |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                           |
      | type        | GezagNietTeBepalen                                                               |
      | toelichting | gezag is niet te bepalen omdat de geboortedatum van minderjarige niet bekend is. |


  Regel: Als de persoon is overleden is gezag niet van toepassing
    # overlijden wordt bepaald door aanwezigheid van datum opschorting bijhouding
    # aangezien opschorting bijhouding na emigratie (R en E) al zijn afgevangen wordt aangenomen 
    # dat aanwezigheid van datum opschorting bijhouding betekent dat de persoon is overleden
    

    Scenario: minderjarige is overleden
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                  | waarde           |
      | geboortedatum (03.10) | morgen - 15 jaar |
      | geboorteland (03.30)  | 6030             |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 0518   |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Check            |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 15 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000036        |
      | geslachtsnaam (02.40)                              | Test             |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 15 jaar |
      En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                 | waarde   |
      | datum opschorting bijhouding (67.10) | gisteren |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon geen gezag
