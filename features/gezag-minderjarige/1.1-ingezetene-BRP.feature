#language: nl

Functionaliteit: 1.1 - Staat persoon (minderjarige) als ingezetene in de BRP?


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


  Regel: Als de gemeente van inschrijving is RNI kan het gezag van de persoon niet worden bepaald

    Scenario: minderjarige staat ingeschreven in RNI (route 1)
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde           |
      | voornamen (02.10)      | Junior           |
      | geslachtsnaam (02.40)  | Test             |
      | geboortedatum (03.10)  | morgen - 15 jaar |
      | geboorteplaats (03.20) | 0518             |
      | geboorteland (03.30)   | 6030             |
      En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                  | waarde          |
      | datum eerste inschrijving GBA (68.10) | <geboortedatum> |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                               | waarde          |
      | gemeente van inschrijving (09.10)  | 1999            |
      | datum aanvang adreshouding (10.30) | <geboortedatum> |
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
      | naam | waarde    |
      | bsn  | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                               |
      | type        | GezagNietTeBepalen                                                   |
      | toelichting | gezag is niet te bepalen omdat minderjarige niet in Nederland woont. |


  Regel: Als gemeente van inschrijving in onderzoek staat kan het gezag niet worden bepaald

    Abstract Scenario: gezag kan niet worden bepaald wanneer <omschrijving> in onderzoek staat (route 49i)
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde             |
      | voornamen (02.10)      | Junior             |
      | geslachtsnaam (02.40)  | Test               |
      | geboortedatum (03.10)  | gisteren - 15 jaar |
      | geboorteplaats (03.20) | 0518               |
      | geboorteland (03.30)   | 6030               |
      En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                  | waarde           |
      | datum eerste inschrijving GBA (68.10) | morgen - 15 jaar |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                               | waarde                    |
      | gemeente van inschrijving (09.10)  | 0518                      |
      | datum aanvang adreshouding (10.30) | morgen - 15 jaar          |
      | aanduiding in onderzoek (83.10)    | <aanduiding in onderzoek> |
      | datum ingang onderzoek (83.20)     | 20240701                  |
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
      | naam | waarde    |
      | bsn  | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                      |
      | type        | GezagNietTeBepalen                                                                                          |
      | toelichting | gezag is niet te bepalen omdat de volgende relevante gegevens in onderzoek staan: gemeente van inschrijving |

      Voorbeelden:
      | aanduiding in onderzoek | omschrijving                        |
      | 080000                  | hele categorie verblijfplaats       |
      | 080900                  | groep gemeente                      |
      | 080910                  | gemeente van inschrijving           |
      | 089999                  | vastgesteld verblijft niet op adres |

    Abstract Scenario: gezag kan wel worden bepaald wanneer <omschrijving> in onderzoek staat
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde             |
      | voornamen (02.10)      | Junior             |
      | geslachtsnaam (02.40)  | Test               |
      | geboortedatum (03.10)  | gisteren - 15 jaar |
      | geboorteplaats (03.20) | 0518               |
      | geboorteland (03.30)   | 6030               |
      En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                  | waarde           |
      | datum eerste inschrijving GBA (68.10) | morgen - 15 jaar |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                               | waarde                    |
      | gemeente van inschrijving (09.10)  | 0518                      |
      | datum aanvang adreshouding (10.30) | morgen - 15 jaar          |
      | aanduiding in onderzoek (83.10)    | <aanduiding in onderzoek> |
      | datum ingang onderzoek (83.20)     | 20240701                  |
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
      | naam | waarde    |
      | bsn  | 000000012 |
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
      | aanduiding in onderzoek | omschrijving                      |
      | 080920                  | datum inschrijving in de gemeente |
      | 081000                  | hele groep adreshouding           |
      | 081010                  | functie adres                     |
      | 081030                  | datum aanvang adreshouding        |
      | 081100                  | groep adres                       |
      | 081120                  | huisnummer                        |
      | 081300                  | groep verblijf buitenland         |
      | 081310                  | land                              |
      | 081400                  | groep immigratie                  |
      | 081410                  | land vanwaar ingeschreven         |
      | 081420                  | datum vestiging in Nederland      |

    Scenario: gezag kan wel worden bepaald wanneer onderzoek naar de verblijfplaats is beëindigd
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde             |
      | voornamen (02.10)      | Junior             |
      | geslachtsnaam (02.40)  | Test               |
      | geboortedatum (03.10)  | gisteren - 15 jaar |
      | geboorteplaats (03.20) | 0518               |
      | geboorteland (03.30)   | 6030               |
      En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                  | waarde           |
      | datum eerste inschrijving GBA (68.10) | morgen - 15 jaar |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                               | waarde           |
      | gemeente van inschrijving (09.10)  | 0518             |
      | datum aanvang adreshouding (10.30) | morgen - 15 jaar |
      | aanduiding in onderzoek (83.10)    | 080000           |
      | datum ingang onderzoek (83.20)     | 20240701         |
      | datum einde onderzoek (83.30)      | 20240709         |
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
      | naam | waarde    |
      | bsn  | 000000012 |
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

    Scenario: gezag kan wel worden bepaald wanneer de vorige verblijfplaats in onderzoek staat
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                   | waarde             |
      | voornamen (02.10)      | Junior             |
      | geslachtsnaam (02.40)  | Test               |
      | geboortedatum (03.10)  | gisteren - 15 jaar |
      | geboorteplaats (03.20) | 0518               |
      | geboorteland (03.30)   | 6030               |
      En de persoon heeft de volgende 'inschrijving' gegevens
      | naam                                  | waarde           |
      | datum eerste inschrijving GBA (68.10) | morgen - 15 jaar |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                               | waarde           |
      | gemeente van inschrijving (09.10)  | 0518             |
      | datum aanvang adreshouding (10.30) | morgen - 15 jaar |
      | aanduiding in onderzoek (83.10)    | 080000           |
      | datum ingang onderzoek (83.20)     | 20240701         |
      En de persoon is vervolgens ingeschreven op adres 'A2' met de volgende gegevens
      | naam                               | waarde   |
      | gemeente van inschrijving (09.10)  | 0518     |
      | datum aanvang adreshouding (10.30) | 20240516 |
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
      | naam | waarde    |
      | bsn  | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde                                                                                                                             |
      | burgerservicenummer | 000000012                                                                                                                          |
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
