#language: nl

Functionaliteit: 3.1 - Is er door een recente gebeurtenis het gezag toch (weer) van rechtswege ondanks dat er eerder een uitspraak is gedaan?


  Regel: Als adoptie heeft plaatsgevonden na de gerechtelijke uitspraak over gezag, wordt het gezag van rechtswege bepaald
    # Er heeft adoptie plaatsgevonden als aktenummer van de persoon in de actuele of een historisch voorkomen als 3e karakter een 'Q' heeft

    Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd na de gerechtelijke uitspraak (route 27)
      Gegeven de persoon 'P1' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | <indicatie gezag>                    | gisteren - 5 jaar               |
      * is geadopteerd
      | aktenummer (81.20) | ingangsdatum geldigheid (85.10) |
      | 1AQ0100            | morgen - 4 jaar                 |
      * heeft 2 juridische ouders met burgerservicenummer '000000012' en '000000024'
      * beide ouders zijn nooit met elkaar getrouwd geweest en hebben nooit een geregistreerd partnerschap gehad
      * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele
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

    Abstract Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige die is geadopteerd vóór de gerechtelijke uitspraak (route <route>)
      Gegeven de persoon 'P1' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * is geadopteerd
      | aktenummer (81.20) | ingangsdatum geldigheid (85.10) |
      | 1AQ0100            | morgen - 6 jaar                 |
      * heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | <indicatie gezag>                    | gisteren - 5 jaar               |
      * heeft 2 juridische ouders met burgerservicenummer '000000012' en '000000024'
      * beide ouders zijn nooit met elkaar getrouwd geweest en hebben nooit een geregistreerd partnerschap gehad
      * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele
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
      | ouder.burgerservicenummer        | <gezaghebbende>          |

      Voorbeelden:
      | indicatie gezag | gezaghebbende | route |
      | 1               | 000000012     | 11    |
      | 2               | 000000024     | 13    |

    Abstract Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige die is geadopteerd vóór de gerechtelijke uitspraak en gegevens van de minderjarige zijn na de uitspraak gewijzigd
      Gegeven de persoon 'P1' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * is geadopteerd
      | aktenummer (81.20) | ingangsdatum geldigheid (85.10) |
      | 1AQ0100            | morgen - 6 jaar                 |
      * heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 1                                    | gisteren - 5 jaar               |
      * zijn de volgende gegevens gewijzigd
      | aktenummer (81.20) | ingangsdatum geldigheid (85.10) |
      | <aktenummer>       | morgen - 4 jaar                 |
      * heeft 2 juridische ouders met burgerservicenummer '000000012' en '000000024'
      * beide ouders zijn nooit met elkaar getrouwd geweest en hebben nooit een geregistreerd partnerschap gehad
      * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele
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
      | aktenummer | omschrijving                           |
      | 1AQ0100    | onder zelfde adoptie aktenummer        |
      | 1AW0200    | ander soort wijziging (naamswijziging) |

    Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige die is erkend (niet geadopteerd) na de gerechtelijke uitspraak (route 4)
      Gegeven de persoon 'P1' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'P2' met burgerservicenummer '000000024'
      * is meerderjarig
      En de persoon 'P3' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * heeft persoon 'P1' als ouder
      * heeft uitspraak dat gezag is toegewezen aan 'een derde'
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      * is erkend door ouder 'P2'
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
      * beide ouders zijn nooit met elkaar getrouwd geweest en hebben nooit een geregistreerd partnerschap gehad
      * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele
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

    Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige waarbij na de gerechtelijke uitspraak ten onrechte adoptie is geregistreerd (route 4)
      Gegeven de persoon 'P1' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      * is geadopteerd
      | aktenummer (81.20) | ingangsdatum geldigheid (85.10) |
      | 1AQ0100            | morgen - 4 jaar                 |
      * de adoptie is gecorrigeerd
      | aktenummer (81.20) | ingangsdatum geldigheid (85.10) |
      | 1AR0200            | morgen - 4 jaar                 |
      * heeft 2 juridische ouders met burgerservicenummer '000000012' en '000000024'
      * beide ouders zijn nooit met elkaar getrouwd geweest en hebben nooit een geregistreerd partnerschap gehad
      * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele
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


  Regel: Als de ingangsdatum van de gerechtelijke uitspraak over gezag onbekend is, is het gezag niet te bepalen

    Abstract Scenario: gezag kan niet worden bepaald wanneer minderjarige is geadopteerd en er is een gerechtelijke uitspraak over gezag met ingangsdatum geldigheid <omschrijving>
      # gezag niet te bepalen. ingangsdatum geldigheid van gerechtelijke uitspraak ontbreekt
      # hoe gaat code om met gedeeltelijk onbekende datums die zeker voor of na adoptie liggen?
      # hoe werkt dit wanneer datum gezag onbekend is, maar er is geen adoptie en geen hertrouwen?
      Gegeven de persoon 'P1' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * is geadopteerd
      | aktenummer (81.20) | ingangsdatum geldigheid (85.10) |
      | 1AQ0100            | morgen - 6 jaar                 |
      * heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 1                                    | <ingangsdatum geldigheid gezag> |
      * heeft 2 juridische ouders met burgerservicenummer '000000012' en '000000024'
      * beide ouders zijn nooit met elkaar getrouwd geweest en hebben nooit een geregistreerd partnerschap gehad
      * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
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
      Gegeven de persoon 'P1' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      * heeft een ouder met burgerservicenummer '000000012'
      | aanduiding in onderzoek (83.10) |
      | <aanduiding onderzoek ouder 1>  |
      * heeft een ouder met burgerservicenummer '000000024'
      | aanduiding in onderzoek (83.10) |
      | <aanduiding onderzoek ouder 2>  |
      * beide ouders zijn nooit met elkaar getrouwd geweest en hebben nooit een geregistreerd partnerschap gehad
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
      | aanduiding onderzoek ouder 1 | aanduiding onderzoek ouder 2 | omschrijving                                           | toelichting                               |
      | 020000                       |                              | hele categorie ouder 1                                 | familierechtelijke betrekking van ouder 1 |
      | 026200                       |                              | hele groep familierechtelijke betrekking van ouder 1   | familierechtelijke betrekking van ouder 1 |
      | 026210                       |                              | datum ingang familierechtelijke betrekking van ouder 1 | familierechtelijke betrekking van ouder 1 |
      |                              | 030000                       | hele categorie ouder 2                                 | familierechtelijke betrekking van ouder 2 |
      |                              | 036200                       | hele groep familierechtelijke betrekking van ouder 2   | familierechtelijke betrekking van ouder 2 |
      |                              | 036210                       | datum ingang familierechtelijke betrekking van ouder 2 | familierechtelijke betrekking van ouder 2 |

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
      * beide ouders zijn nooit met elkaar getrouwd geweest en hebben nooit een geregistreerd partnerschap gehad
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
