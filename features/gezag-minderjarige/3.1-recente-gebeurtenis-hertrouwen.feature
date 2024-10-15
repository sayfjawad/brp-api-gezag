#language: nl

Functionaliteit:  3.1 - Is er door een recente gebeurtenis - hertrouwen - het gezag toch (weer) van rechtswege ondanks dat er eerder een uitspraak is gedaan?


    Achtergrond:
      Gegeven de persoon 'Ingrid' met burgerservicenummer '000000012'
      * is meerderjarig, niet overleden en staat niet onder curatele
      En de persoon 'Henk' met burgerservicenummer '000000024'
      * is meerderjarig, niet overleden en staat niet onder curatele
      En de persoon 'Geert' met burgerservicenummer '000000048'
      * is meerderjarig
      En de persoon 'Femke' met burgerservicenummer '000000061'
      * is meerderjarig
      En de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * heeft 'Ingrid' als ouder 1
      * heeft 'Henk' als ouder 2



  Regel: Als de ouders met elkaar zijn hertrouwd of opnieuw partnerschap zijn aangegaan na gerechtelijke uitspraak tot gezag voor één van beide ouders, wordt het gezag van rechtswege bepaald
    # Dit is het geval wanneer:
    # * indicatie gezag is '1' of '2'
    # * ouder1 en ouder2 met elkaar zijn eerder gescheiden en daarna opnieuw gehuwd of geregistreerd partnerschap aangegaan
    # * datum aangaan huwelijk of geregistreerd partnerschap ligt na de ingangsdatum geldigheid van de gezagsverhouding 

    Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige waarbij na gerechtelijke uitspraak tot gezag ouder<indicatie gezag> de ouders zijn hertrouwd
      Gegeven heeft gezag uitspraak 
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | <indicatie gezag>                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 20 jaar                                                 |
      En 'Ingrid' en 'Henk' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | gisteren - 6 jaar                                            |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 2 jaar                                                  |
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

    Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige waarbij <omschrijving>
      Gegeven heeft gezag uitspraak 
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 1                                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | <datum aangaan 1e huwelijk>                                        |
      En 'Ingrid' en 'Henk' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | <datum ontbinding 1e huwelijk>                               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 2 jaar                                                  |
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
      | datum aangaan 1e huwelijk | datum ontbinding 1e huwelijk | omschrijving                                                                             |
      | gisteren - 20 jaar        | gisteren - 6 jaar            | ouders zijn getrouwd en gescheiden voor de uitspraak en na de uitspraak hertrouwd        |
      | gisteren - 20 jaar        | gisteren - 4 jaar            | ouders zijn getrouwd voor de uitspraak en gescheiden na de uitspraak en daarna hertrouwd |
      | gisteren - 4 jaar         | gisteren - 3 jaar            | ouders zijn getrouwd en gescheiden na de uitspraak en daarna hertrouwd                   |

    Scenario: geen 'recente gebeurtenis' omdat de ouders na de uitspraak voor het eerst met elkaar zijn getrouwd
      Gegeven heeft gezag uitspraak 
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 1                                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 3 jaar                                                  |
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

    Scenario: geen 'recente gebeurtenis' omdat de ouders na het 'herstelhuwelijk' weer zijn gescheiden
      Gegeven heeft gezag uitspraak 
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 1                                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 20 jaar                                                 |
      En 'Ingrid' en 'Henk' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | gisteren - 6 jaar                                            |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 2 jaar                                                  |
      En 'Ingrid' en 'Henk' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | gisteren - 1 jaar                                            |
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

    Scenario: geen 'recente gebeurtenis' omdat de ouders getrouwd zijn met een andere derde persoon (niet de ander ouder)
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 1                                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 20 jaar                                                 |
      En 'Ingrid' en 'Henk' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | gisteren - 6 jaar                                            |
      En 'Ingrid' en 'Geert' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 4 jaar                                                  |
      En 'Henk' en 'Femke' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 4 jaar                                                  |
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

    Scenario: geen 'recente gebeurtenis' omdat de ouders al voor de uitspraak met elkaar hertrouwd zijn
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 1                                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 20 jaar                                                 |
      En 'Ingrid' en 'Henk' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | gisteren - 12 jaar                                           |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 6 jaar                                                  |
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

    Scenario: geen 'recente gebeurtenis' omdat de gerechtelijke uitspraak tot gezag <omschrijving uitspraak> is
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | <indicatie gezag>                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 20 jaar                                                 |
      En 'Ingrid' en 'Henk' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | gisteren - 6 jaar                                            |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 2 jaar                                                  |
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


  Regel: Onjuist opgenomen (gecorrigeerde) huwelijks- of partnerschapsgegevens worden genegeerd
    # Als een historisch voorkomen van de partnergegevens een indicatie onjuist heeft met een waarde, dan wordt dat historisch 
    # voorkomen genegeerd
    
    Scenario: geen 'recente gebeurtenis' omdat het herstelhuwelijk met een correctie volledig is verwijderd
      Gegeven heeft gezag uitspraak 
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 1                                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 20 jaar                                                 |
      En 'Ingrid' en 'Henk' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | gisteren - 6 jaar                                            |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 2 jaar                                                  |
      En is het huwelijk van 'Ingrid' en 'Henk' gecorrigeerd met de volgende gegevens
      | naam                                                                | waarde |
      | burgerservicenummer (01.20)                                         |        |
      | voornamen (02.10)                                                   |        |
      | geslachtsnaam (02.40)                                               |        |
      | geboortedatum (03.10)                                               |        |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  |        |
      | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) |        |
      | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   |        |
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

    Scenario: geen 'recente gebeurtenis' wanneer het eerste huwelijk met een correctie volledig is verwijderd
      Gegeven heeft gezag uitspraak 
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 1                                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 20 jaar                                                 |
      En is het huwelijk van 'Ingrid' en 'Henk' gecorrigeerd met de volgende gegevens
      | naam                                                                | waarde |
      | burgerservicenummer (01.20)                                         |        |
      | voornamen (02.10)                                                   |        |
      | geslachtsnaam (02.40)                                               |        |
      | geboortedatum (03.10)                                               |        |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  |        |
      | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) |        |
      | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   |        |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 2 jaar                                                  |
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

    Scenario: geen 'recente gebeurtenis' wanneer de datum huwelijkssluiting van het eerste en enige huwelijk is gecorrigeerd
      Gegeven heeft gezag uitspraak 
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 1                                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 2 jaar                                                  |
      En is het huwelijk van 'Ingrid' en 'Henk' gecorrigeerd
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | vandaag - 2 jaar                                                   |
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


  Regel: Als het huwelijk of partnerschap in staat onderzoek, is het gezag wel te bepalen
    # Onderzoek naar het "reparatiehuwelijk" is geen reden voor GezagNietTeBepalen. 
    # Het gezag kan dan normaal bepaald worden

    Abstract Scenario: Gezag wordt van rechtswege bepaald na reparatiehuwelijk en <omschrijving> staat in onderzoek
      Gegeven heeft gezag uitspraak 
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 1                                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 20 jaar                                                 |
      En 'Ingrid' en 'Henk' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | gisteren - 6 jaar                                            |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) | aanduiding in onderzoek (83.10) |
      | gisteren - 2 jaar                                                  | <aanduiding onderzoek>          |
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
      | aanduiding onderzoek | omschrijving                                |
      | 050000               | hele categorie partnerschap                 |
      | 050600               | hele groep aangaan huwelijk of partnerschap |
      | 050610               | datum aangaan huwelijk of partnerschap      |
      | 050120               | burgerservicenummer partner                 |
      | 050200               | hele groep naam van partner                 |
      | 050240               | geslachtsnaam van partner                   |
      | 050300               | hele groep geboorte van partner             |
      | 050620               | plaats aangaan huwelijk of partnerschap     |
      | 050670               | land aangaan huwelijk of partnerschap       |
