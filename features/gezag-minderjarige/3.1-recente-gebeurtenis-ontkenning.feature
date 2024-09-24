#language: nl

Functionaliteit:  3.1 - Is er door een recente gebeurtenis - ontkenning vaderschap - het gezag toch (weer) van rechtswege ondanks dat er eerder een uitspraak is gedaan?


    # Achtergrond:
    #   Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
    #   * is ingeschreven in de BRP
    #   * is minderjarig
    #   * is niet geëmigreerd geweest
    #   * is in Nederland geboren
    #   * heeft een ouder 1 'Ingrid' met burgerservicenummer '000000012'
    #   * heeft een ouder 2 'Henk' met burgerservicenummer '000000024'
    #   * 'Ingrid' en 'Henk' zijn met elkaar gehuwd
    #   * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele


  Regel: Als vaderschap is ontkend of de erkenning is vernietigd en er was een uitspraak gezag voor die ouder, wordt het gezag van rechtswege bepaald
    # Dit is het geval wanneer:
    # * indicatie gezag is '12'
    # * (ten minste) een van de ouders is verwijderd of is een 'puntouder'

    Abstract Scenario: er is uitspraak gezag voor ouder 1 en ouder 2 en erkenning door ouder 1 is ontkend of vernietigd
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
      | burgerservicenummer (01.20)                        | 000000024        |
      | geslachtsnaam (02.40)                              | Henk             |
      | geboortedatum (03.10)                              | 19820101         |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 16 jaar |
      En de ouder '1' is <soort wijziging> naar de volgende gegevens
      | naam                  | waarde       |
      | geslachtsnaam (02.40) | <naam ouder> |
      | aktenummer (81.20)    | 1AF0100      |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde           |
      | burgerservicenummer (01.20)                        | 000000012        |
      | geslachtsnaam (02.40)                              | Ingrid           |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 16 jaar |
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
      | ouder.burgerservicenummer        | 000000012                |

      Voorbeelden:
      | indicatie gezag | naam ouder | soort wijziging |
      | 12              |            | gewijzigd       |
      | 12              | .          | gewijzigd       |
      | 12              |            | gecorrigeerd    |
      | 12              | .          | gecorrigeerd    |

    Abstract Scenario: er is uitspraak gezag <indicatie gezag> en erkenning door ouder 2 is ontkend of vernietigd
      # Eenouderlijk gezag voor ouder 1
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
      | geboortedatum (03.10) | 19820101 |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Jaimy    |
      | geboortedatum (03.10) | 20190401 |
      | geboorteland (03.30)  | 6030     |
      | aktenummer (81.20)    | 1AA0100  |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde    |
      | burgerservicenummer (01.20)                        | 000000012 |
      | geslachtsnaam (02.40)                              | Ingrid    |
      | datum ingang familierechtelijke betrekking (62.10) | 20190401  |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde    |
      | burgerservicenummer (01.20)                        | 000000024 |
      | geslachtsnaam (02.40)                              | Henk      |
      | geboortedatum (03.10)                              | 19820101  |
      | datum ingang familierechtelijke betrekking (62.10) | 20190401  |
      En de ouder '2' is gewijzigd naar de volgende gegevens
      | naam                                               | waarde       |
      | datum ingang familierechtelijke betrekking (62.10) | 20190401     |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                                 | waarde            |
      | indicatie gezag minderjarige (32.10) | <indicatie gezag> |
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
      | ouder.burgerservicenummer        | 000000012                |

      Voorbeelden:
      | indicatie gezag | naam ouder |
      | 2               |            |
      | 2               | .          |
      | 12              |            |
      | 12              | .          |

    Abstract Scenario: er is uitspraak gezag 1 en erkenning door ouder 2 is ontkend of vernietigd
      # Eenouderlijk gezag voor ouder 1
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
      | geboortedatum (03.10) | 19820101 |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
      En de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                  | waarde   |
      | geslachtsnaam (02.40) | Jaimy    |
      | geboortedatum (03.10) | 20190401 |
      | geboorteland (03.30)  | 6030     |
      | aktenummer (81.20)    | 1AA0100  |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde    |
      | burgerservicenummer (01.20)                        | 000000012 |
      | geslachtsnaam (02.40)                              | Ingrid    |
      | datum ingang familierechtelijke betrekking (62.10) | 20190401  |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                                               | waarde    |
      | burgerservicenummer (01.20)                        | 000000024 |
      | geslachtsnaam (02.40)                              | Henk      |
      | geboortedatum (03.10)                              | 19820101  |
      | datum ingang familierechtelijke betrekking (62.10) | 20190401  |
      En de ouder '2' is gewijzigd naar de volgende gegevens
      | naam                                               | waarde   |
      | datum ingang familierechtelijke betrekking (62.10) | 20190401 |
      En de persoon heeft de volgende 'verblijfplaats' gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 518    |
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
      | ouder.burgerservicenummer        | 000000012                |

    Scenario: er is uitspraak gezag voogdij en erkenning door ouder 2 is ontkend of vernietigd
      # gezag is voogdij
