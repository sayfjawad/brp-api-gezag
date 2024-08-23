#language: nl

Functionaliteit: 1.2 - Is persoon a. minderjarig en b. niet overleden?


    Achtergrond:
      Gegeven adres 'A1' heeft de volgende gegevens
      | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) |
      | 518                  | Turfmarkt          | 1                  |
      En de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                  | waarde |
      | geslachtsnaam (02.40) | Check  |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                               | waarde           |
      | gemeente van inschrijving (09.10)  | 0518             |
      En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                               | waarde    |
      | burgerservicenummer (01.20)                                        | 000000036 |
      | geslachtsnaam (02.40)                                              | Test      |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) | 20010928  |
      | soort verbintenis (15.10)                                          | H         |
      En de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                  | waarde |
      | geslachtsnaam (02.40) | Test   |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                               | waarde           |
      | gemeente van inschrijving (09.10)  | 0518             |
      En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                               | waarde    |
      | burgerservicenummer (01.20)                                        | 000000024 |
      | geslachtsnaam (02.40)                                              | Check     |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) | 20010928  |
      | soort verbintenis (15.10)                                          | H         |


  Regel: Als de persoon ouder is dan 18 jaar is gezag niet van toepassing

    Scenario: De persoon is minderjarig
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde          |
      | voornamen (02.10)      | Junior          |
      | geslachtsnaam (02.40)  | Test            |
      | geboortedatum (03.10)  | <geboortedatum> |
      | geboorteplaats (03.20) | 0518            |
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
      | geboortedatum      |
      | morgen - 18 jaar   |
      | gisteren - 17 jaar |
      | vandaag - 16 jaar  |
      | gisteren           |
      | vandaag            |

    Scenario: De persoon is meerderjarig want is <omschrijving>

      Voorbeelden:
      | geboortedatum      | omschrijving              |
      | vandaag - 18 jaar  | vandaag 18 jaar geworden  |
      | gisteren - 18 jaar | gisteren 18 jaar geworden |
      | morgen - 19 jaar   | 18 jaar                   |
      | vandaag - 19 jaar  | 19 jaar                   |
      | gisteren - 20 jaar | 20 jaar                   |


  Regel: Ald de geboortedatum van de persoon volledig onbekend is ...


  Regel: Ald de geboortedatum van de persoon gedeeltelijk bekend is ...


  Regel: Als de persoon is overleden is gezag niet van toepassing