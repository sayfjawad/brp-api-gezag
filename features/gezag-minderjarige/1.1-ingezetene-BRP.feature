#language: nl

Functionaliteit: 1.1 - Staat persoon (minderjarige) als ingezetene in de BRP?


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


  Regel: Als de gemeente van inschrijving is RNI kan het gezag van de persoon niet worden bepaald

    Scenario: minderjarige staat ingeschreven in RNI (route 1)
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde             |
      | geboortedatum (03.10)  | gisteren - 15 jaar |
      | geboorteland (03.30)   | 6030               |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                               | waarde           |
      | gemeente van inschrijving (09.10)  | 1999             |
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
      | naam        | waarde                                                               |
      | type        | GezagNietTeBepalen                                                   |
      | toelichting | gezag is niet te bepalen omdat minderjarige niet in Nederland woont. |

    Scenario: minderjarige staat ingeschreven in Nederlandse gemeente
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde             |
      | geboortedatum (03.10)  | gisteren - 15 jaar |
      | geboorteland (03.30)   | 6030               |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                               | waarde           |
      | gemeente van inschrijving (09.10)  | 0518             |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde             |
      | burgerservicenummer (01.20)                        | 000000024          |
      | geslachtsnaam (02.40)                              | Check              |
      | datum ingang familierechtelijke betrekking (62.10) | gisteren - 15 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde             |
      | burgerservicenummer (01.20)                        | 000000036          |
      | geslachtsnaam (02.40)                              | Test               |
      | datum ingang familierechtelijke betrekking (62.10) | gisteren - 15 jaar |
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


  Regel: onderzoek op gemeente van inschrijving leidt niet tot uitval

    Abstract Scenario: minderjarige staat ingeschreven in Nederlandse gemeente en <omschrijving> staat in onderzoek
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde             |
      | geboortedatum (03.10)  | gisteren - 15 jaar |
      | geboorteland (03.30)   | 6030               |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                               | waarde                    |
      | gemeente van inschrijving (09.10)  | 0518                      |
      | aanduiding in onderzoek (83.10)    | <aanduiding in onderzoek> |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde             |
      | burgerservicenummer (01.20)                        | 000000024          |
      | geslachtsnaam (02.40)                              | Check              |
      | datum ingang familierechtelijke betrekking (62.10) | gisteren - 15 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde             |
      | burgerservicenummer (01.20)                        | 000000036          |
      | geslachtsnaam (02.40)                              | Test               |
      | datum ingang familierechtelijke betrekking (62.10) | gisteren - 15 jaar |
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
      | aanduiding in onderzoek | omschrijving                        |
      | 080000                  | hele categorie verblijfplaats       |
      | 080900                  | groep gemeente                      |
      | 080910                  | gemeente van inschrijving           |
      | 089999                  | vastgesteld verblijft niet op adres |


  Regel: wanneer gemeente van inschrijving ontbreekt is gezag niet te bepalen

    Scenario: er is geen verblijfplaats vastgelegd van de minderjarige
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde             |
      | geboortedatum (03.10)  | gisteren - 15 jaar |
      | geboorteland (03.30)   | 6030               |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde             |
      | burgerservicenummer (01.20)                        | 000000024          |
      | geslachtsnaam (02.40)                              | Check              |
      | datum ingang familierechtelijke betrekking (62.10) | gisteren - 15 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde             |
      | burgerservicenummer (01.20)                        | 000000036          |
      | geslachtsnaam (02.40)                              | Test               |
      | datum ingang familierechtelijke betrekking (62.10) | gisteren - 15 jaar |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                        |
      | type        | GezagNietTeBepalen                                                                                            |
      | toelichting | gezag is niet te bepalen omdat de volgende relevante gegevens ontbreken: verblijfplaats van bevraagde persoon |
