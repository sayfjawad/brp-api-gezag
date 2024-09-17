#language: nl

Functionaliteit:  3.1 - Is er door een recente gebeurtenis - ontkenning vaderschap - het gezag toch (weer) van rechtswege ondanks dat er eerder een uitspraak is gedaan?


    # Achtergrond:
    #   Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
    #   * is ingeschreven in de BRP
    #   * is minderjarig
    #   * is niet geëmigreerd geweest
    #   * is in Nederland geboren
    #   * heeft een ouder 'Ingrid' met burgerservicenummer '000000012'
    #   * heeft een ouder 'Henk' met burgerservicenummer '000000024'
    #   * 'Ingrid' en 'Henk' zijn met elkaar gehuwd
    #   * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele


  Regel: Als vaderschap is ontkend of de erkenning is vernietigd en er was een uitspraak gezag voor die ouder, wordt het gezag van rechtswege bepaald
    # Dit is het geval wanneer:
    # * indicatie gezag is '12'
    # * (ten minste) een van de ouders is verwijderd of is een 'puntouder'

    Abstract Scenario: er is uitspraak gezag voor ouder 1 en ouder 2 en erkenning door ouder <ouder> is ontkend of vernietigd
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Ingrid   |
      | geboortedatum (03.10) | 19820101 |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Henk     |
      | geboortedatum (03.10) | 19800101 |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                  | waarde           |
      | geslachtsnaam (02.40) | Jaimy            |
      | geboortedatum (03.10) | morgen - 16 jaar |
      | geboorteland (03.30)  | 6030             |
      | aktenummer (81.20)    | 1AA0100          |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000012        |
      | geslachtsnaam (02.40)                              | Ingrid           |
      | geboortedatum (03.10)                              | 19820101         |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 16 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Henk             |
      | geboortedatum (03.10)                              | 19820101         |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 16 jaar |
      En de ouder '<ouder>' is <soort wijziging> naar de volgende gegevens
      | naam                  | waarde       |
      | aktenummer (81.20)    | 1AF0100      |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde           |
      | indicatie gezag minderjarige (32.10) | 12               |
      | ingangsdatum geldigheid (85.10)      | morgen - 10 jaar |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000036                |
      | ouder.burgerservicenummer        | <ouder bsn>              |

      Voorbeelden:
      | soort wijziging | ouder | ouder bsn |
      | gewijzigd       | 1     | 000000024 |
      | gecorrigeerd    | 1     | 000000024 |
      | gewijzigd       | 2     | 000000012 |
      | gecorrigeerd    | 2     | 000000012 |

    Abstract Scenario: er is uitspraak gezag voor ouder 1 en ouder 2 en erkenning door ouder <ouder> is na ontkenning onbekend
      # Wanneer de ouder een puntouder is (geslachtsnaam van de ouder is een .), is sprake van ontkenning of vernietiging en dus van een recente gebeurtenis
      # Gezag wordt dan van rechtswege bepaald, maar kan niet worden bepaald omdat gegevens van deze ouder onbekend zijn
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Ingrid   |
      | geboortedatum (03.10) | 19820101 |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Henk     |
      | geboortedatum (03.10) | 19800101 |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                  | waarde           |
      | geslachtsnaam (02.40) | Jaimy            |
      | geboortedatum (03.10) | morgen - 16 jaar |
      | geboorteland (03.30)  | 6030             |
      | aktenummer (81.20)    | 1AA0100          |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000012        |
      | geslachtsnaam (02.40)                              | Ingrid           |
      | geboortedatum (03.10)                              | 19820101         |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 16 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Henk             |
      | geboortedatum (03.10)                              | 19820101         |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 16 jaar |
      En de ouder '<ouder>' is <soort wijziging> naar de volgende gegevens
      | naam                  | waarde  |
      | geslachtsnaam (02.40) | .       |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde           |
      | indicatie gezag minderjarige (32.10) | 12               |
      | ingangsdatum geldigheid (85.10)      | morgen - 10 jaar |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                         |
      | type                             | GezagNietTeBepalen                                             |
      | toelichting                      | gezag is niet te bepalen omdat één of meer ouders onbekend is. |

      Voorbeelden:
      | soort wijziging | ouder |
      | gewijzigd       | 1     |
      | gecorrigeerd    | 1     |
      | gewijzigd       | 2     |
      | gecorrigeerd    | 2     |

    Abstract Scenario: geen 'recente gebeurtenis' omdat gezag is <indicatie gezag>' en erkenning door ouder <ouder> is ontkend of ingetrokken
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Ingrid   |
      | geboortedatum (03.10) | 19820101 |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Henk     |
      | geboortedatum (03.10) | 19800101 |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                  | waarde           |
      | geslachtsnaam (02.40) | Jaimy            |
      | geboortedatum (03.10) | morgen - 16 jaar |
      | geboorteland (03.30)  | 6030             |
      | aktenummer (81.20)    | 1AA0100          |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000012        |
      | geslachtsnaam (02.40)                              | Ingrid           |
      | geboortedatum (03.10)                              | 19820101         |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 16 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Henk             |
      | geboortedatum (03.10)                              | 19820101         |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 16 jaar |
      En de ouder '<ouder>' is <soort wijziging> naar de volgende gegevens
      | naam                  | waarde  |
      | geslachtsnaam (02.40) | .       |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde            |
      | indicatie gezag minderjarige (32.10) | <indicatie gezag> |
      | ingangsdatum geldigheid (85.10)      | morgen - 10 jaar  |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000036                |
      | ouder.burgerservicenummer        | <ouder bsn>              |

      Voorbeelden:
      | soort wijziging | ouder | indicatie gezag | ouder bsn |
      | gewijzigd       | 1     | 2               | 000000024 |
      | gecorrigeerd    | 1     | 2               | 000000024 |
      | gewijzigd       | 2     | 1               | 000000012 |
      | gecorrigeerd    | 2     | 1               | 000000012 |

    Abstract Scenario: er is uitspraak gezag voogdij '<indicatie gezag>' en erkenning door ouder 2 is ontkend of vernietigd
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Ingrid   |
      | geboortedatum (03.10) | 19820101 |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Henk     |
      | geboortedatum (03.10) | 19800101 |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                  | waarde           |
      | geslachtsnaam (02.40) | Jaimy            |
      | geboortedatum (03.10) | morgen - 16 jaar |
      | geboorteland (03.30)  | 6030             |
      | aktenummer (81.20)    | 1AA0100          |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000012        |
      | geslachtsnaam (02.40)                              | Ingrid           |
      | geboortedatum (03.10)                              | 19820101         |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 16 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Henk             |
      | geboortedatum (03.10)                              | 19820101         |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 16 jaar |
      En de ouder '2' is gewijzigd naar de volgende gegevens
      | naam                  | waarde  |
      | geslachtsnaam (02.40) | .       |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde            |
      | indicatie gezag minderjarige (32.10) | <indicatie gezag> |
      | ingangsdatum geldigheid (85.10)      | morgen - 10 jaar  |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000036 |
      En heeft 'gezag' geen derden

      Voorbeelden:
      | indicatie gezag | omschrijving uitspraak |
      | D               | een derde              |
      | 1D              | ouder 1 en een derde   |
      | 2D              | ouder 2 en een derde   |

  Regel: Als de geslachtsnaam van een ouder staat in onderzoek, is het gezag niet te bepalen

    Abstract Scenario: Gezag is niet te bepalen omdat <omschrijving>
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Ingrid   |
      | geboortedatum (03.10) | 19820101 |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Henk     |
      | geboortedatum (03.10) | 19800101 |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                  | waarde           |
      | geslachtsnaam (02.40) | Jaimy            |
      | geboortedatum (03.10) | morgen - 16 jaar |
      | geboorteland (03.30)  | 6030             |
      | aktenummer (81.20)    | 1AA0100          |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde                            |
      | burgerservicenummer (01.20)                        | 000000012                         |
      | geslachtsnaam (02.40)                              | Ingrid                            |
      | geboortedatum (03.10)                              | 19820101                          |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 16 jaar                  |
      | aanduiding in onderzoek (83.10)                    | <aanduiding in onderzoek ouder 1> |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde                            |
      | burgerservicenummer (01.20)                        | 000000024                         |
      | geslachtsnaam (02.40)                              | Henk                              |
      | geboortedatum (03.10)                              | 19820101                          |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 16 jaar                  |
      | aanduiding in onderzoek (83.10)                    | <aanduiding in onderzoek ouder 2> |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde           |
      | indicatie gezag minderjarige (32.10) | 12               |
      | ingangsdatum geldigheid (85.10)      | morgen - 10 jaar |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                          |
      | type        | GezagNietTeBepalen                                                                              |
      | toelichting | gezag is niet te bepalen omdat de volgende relevante gegevens in onderzoek staan: <toelichting> |

      Voorbeelden:
      | aanduiding in onderzoek ouder 1 | aanduiding in onderzoek ouder 2 | omschrijving                | toelichting               |
      | 020000                          |                                 | hele categorie ouder 1      | ouder 1                   |
      | 020200                          |                                 | hele groep naam van ouder 1 | geslachtsnaam van ouder 1 |
      | 020240                          |                                 | geslachtsnaam van ouder 1   | geslachtsnaam van ouder 1 |
      |                                 | 030000                          | hele categorie ouder 2      | ouder 2                   |
      |                                 | 030200                          | hele groep naam van ouder 2 | geslachtsnaam van ouder 2 |
      |                                 | 030240                          | geslachtsnaam van ouder 2   | geslachtsnaam van ouder 2 |
