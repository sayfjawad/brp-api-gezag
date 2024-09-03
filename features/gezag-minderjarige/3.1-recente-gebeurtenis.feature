#language: nl

Functionaliteit: 3.1 - Is er door een recente gebeurtenis het gezag toch (weer) van rechtswege ondanks dat er eerder een uitspraak is gedaan?


    Achtergrond:
      Gegeven de persoon 'P1' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * heeft een juridische ouder 'moeder' met burgerservicenummer '000000012'
      * heeft een juridische ouder 'vader' met burgerservicenummer '000000024'
      * beide ouders zijn nu met elkaar gehuwd
      * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele

  Regel: Als adoptie heeft plaatsgevonden na de gerechtelijke uitspraak over gezag, wordt het gezag van rechtswege bepaald
    # Als de ingangsdatum gezag (11.85.10) kleiner is dan de datum ingang familierechtelijke betrekking (02.62.10 en 03.62.10) 
    # en de akteaanduiding (02.81.20 of 52.81.20) bevat de waarde Q op de 3e positie dan is sprake van een adoptie nadat het 
    # gezag is opgenomen. 
    # Deze kan in de actuele categorie voorkomen maar ook in een historische categorie omdat er later nog een ander rechtsfeit 
    # geregistreerd kan zijn.

    Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd na de gerechtelijke uitspraak
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | <indicatie gezag>                    | gisteren - 5 jaar               |
      En is geadopteerd door beide ouders
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AQ0100            | morgen - 4 jaar                                    |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000036                 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |

      Voorbeelden:
      | indicatie gezag |
      | 1               |
      | 2               |
      | 12              |
      | 1D              |
      | 2D              |
      | D               |

    Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige die is geadopteerd vóór de gerechtelijke uitspraak
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is geadopteerd door beide ouders
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AQ0100            | morgen - 6 jaar                                    |
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

    Abstract Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige die is geadopteerd vóór de gerechtelijke uitspraak en gegevens van de minderjarige zijn na de uitspraak gewijzigd <omschrijving>
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is geadopteerd door beide ouders
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AQ0100            | morgen - 6 jaar                                    |
      En zijn de volgende gegevens gewijzigd
      | aktenummer (81.20) | ingangsdatum geldigheid (85.10) |
      | <aktenummer>       | morgen - 4 jaar                 |
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
      | aktenummer | omschrijving                           |
      | 1AQ0100    | onder zelfde adoptie aktenummer        |
      | 1AW0200    | ander soort wijziging (naamswijziging) |

    Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige waarbij na de gerechtelijke uitspraak ten onrechte adoptie is geregistreerd
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is geadopteerd door beide ouders
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AQ0100            | morgen - 4 jaar                                    |
      En de adoptie is gecorrigeerd
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AR0200            | morgen - 4 jaar                                    |
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


  Regel: Als de datum van de gerechtelijke uitspraak over gezag volledig onbekend is, wordt het gezag van rechtswege bepaald

    Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd na de gerechtelijke uitspraak
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | 00000000                        |
      En is geadopteerd door beide ouders
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AQ0100            | morgen - 4 jaar                                    |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000036                 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |


  Regel: Als de datum van de gerechtelijke uitspraak over gezag gedeeltelijk onbekend is, wordt de eerste dag van de maand van de onzekerheidsperiode aangenomen

    Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd en <omschrijving>
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | <datum uitspraak gezag>         |
      En is geadopteerd
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AQ0100            | 20210617                                           |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000036                 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |

      Voorbeelden:
      | datum uitspraak gezag | omschrijving                                                                     |
      | 20190000              | alleen jaar is bekend van datum uitspraak en ligt zeker voor datum adoptie       |
      | 20210000              | alleen jaar is bekend van datum uitspraak en is zelfde jaar als datum adoptie    |
      | 20210500              | jaar en maand is bekend van datum uitspraak en ligt zeker voor datum adoptie     |
      | 20210600              | jaar en maand is bekend van datum uitspraak en is zelfde maand als datum adoptie |

    Abstract Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige die is geadopteerd en <omschrijving>
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | <datum uitspraak gezag>         |
      En is geadopteerd
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AQ0100            | 20210617                                           |
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
      | datum uitspraak gezag | omschrijving                                                               |
      | 20230000              | alleen jaar is bekend van datum uitspraak en ligt zeker na datum adoptie   |
      | 20210700              | jaar en maand is bekend van datum uitspraak en ligt zeker na datum adoptie |


  Regel: Als de datum adoptie volledig onbekend is, wordt gezag bepaald volgens de gerechtelijke uitspraak over gezag

    Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige die is geadopteerd en datum adoptie is volledig onbekend
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | 20210617                        |
      En is geadopteerd
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AQ0100            | 00000000                                           |
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


  Regel: Als de datum adoptie gedeeltelijk onbekend is, wordt de eerste dag van de maand van de onzekerheidsperiode aangenomen

    Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd en <omschrijving>
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | 20210617                        |
      En is geadopteerd
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AQ0100            | <datum adoptie>                                    |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000036                 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |

      Voorbeelden:
      | datum adoptie | omschrijving                                                                     |
      | 20230000      | alleen jaar is bekend van datum adoptie en ligt zeker na datum uitspraak gezag   |
      | 20210700      | jaar en maand is bekend van datum adoptie en ligt zeker na datum uitspraak gezag |
      | 20230000      | alleen jaar is bekend van datum adoptie en ligt zeker na datum uitspraak gezag   |
      | 20210700      | jaar en maand is bekend van datum adoptie en ligt zeker na datum uitspraak gezag |

    Abstract Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige die is geadopteerd en <omschrijving>
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | <datum uitspraak gezag>         |
      En is geadopteerd
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AQ0100            | <datum adoptie>                                    |
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
      | datum adoptie | datum uitspraak gezag | omschrijving                                                                             |
      | 20190000      | 20210617              | alleen jaar is bekend van datum adoptie en ligt zeker voor datum uitspraak gezag         |
      | 20210000      | 20210617              | alleen jaar is bekend van datum adoptie en is zelfde jaar als uitspraak gezag            |
      | 20210000      | 20210101              | alleen jaar is bekend van datum adoptie en uitspraak gezag is eerste dag van dat jaar    |
      | 20210500      | 20210617              | jaar en maand is bekend van datum adoptie en ligt zeker voor datum uitspraak gezag       |
      | 20210600      | 20210617              | jaar en maand is bekend van datum adoptie en is zelfde maand als uitspraak gezag         |
      | 20210600      | 20210601              | jaar en maand is bekend van datum adoptie en uitspraak gezag is eerste dag van die maand |

    

  Regel: Als datum familierechtelijke betrekking of indicatie gezag of ingangsdatum gezagsverhouding in onderzoek staan, is het gezag niet te bepalen

    Abstract Scenario: gezag kan niet worden bepaald wanneer <omschrijving> in onderzoek staat
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is geadopteerd
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AQ0100            | gisteren - 4 jaar                                    |
      * heeft de volgende gegevens van ouder 'moeder'
      | aanduiding in onderzoek (83.10) |
      | <aanduiding onderzoek moeder>   |
      * heeft de volgende gegevens van ouder 'vader'
      | aanduiding in onderzoek (83.10) |
      | <aanduiding onderzoek vader>    |
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
      | aanduiding onderzoek moeder | aanduiding onderzoek vader | omschrijving                                           | toelichting                               |
      | 020000                      |                            | hele categorie ouder 1                                 | familierechtelijke betrekking van ouder 1 |
      | 026200                      |                            | hele groep familierechtelijke betrekking van ouder 1   | familierechtelijke betrekking van ouder 1 |
      | 026210                      |                            | datum ingang familierechtelijke betrekking van ouder 1 | familierechtelijke betrekking van ouder 1 |
      |                             | 030000                     | hele categorie ouder 2                                 | familierechtelijke betrekking van ouder 2 |
      |                             | 036200                     | hele groep familierechtelijke betrekking van ouder 2   | familierechtelijke betrekking van ouder 2 |
      |                             | 036210                     | datum ingang familierechtelijke betrekking van ouder 2 | familierechtelijke betrekking van ouder 2 |

    Abstract Scenario: gezag kan niet worden bepaald wanneer <omschrijving> in onderzoek staat
      Gegeven de persoon 'P1' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      * heeft 2 juridische ouders met burgerservicenummer '000000012' en '000000024'
      * beide ouders zijn nu met elkaar gehuwd
      * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele
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
      | aanduiding onderzoek | omschrijving                    | toelichting                                                                  |
      | 110000               | hele categorie gezagsverhouding | indicatie gezag minderjarige,datum ingang geldigheid van de gezagsverhouding |
      | 113200               | hele groep gezag minderjarige   | indicatie gezag minderjarige                                                 |
      | 113210               | indicatie gezag minderjarige    | indicatie gezag minderjarige                                                 |
      | 118500               | hele groep geldigheid           | datum ingang geldigheid van de gezagsverhouding                              |
      | 118510               | datum ingang geldigheid         | datum ingang geldigheid van de gezagsverhouding                              |

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
