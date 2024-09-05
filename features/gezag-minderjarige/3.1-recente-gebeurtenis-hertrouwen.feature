#language: nl

Functionaliteit:  3.1 - Is er door een recente gebeurtenis - hertrouwen - het gezag toch (weer) van rechtswege ondanks dat er eerder een uitspraak is gedaan?


    Achtergrond:
      Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * heeft een ouder 'Ingrid' met met burgerservicenummer '000000012'
      * heeft een ouder 'Henk' met met burgerservicenummer '000000024'
      * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele
      En de persoon 'Joris' met burgerservicenummer '000000048'
      * is meerderjarig
      En de persoon 'Anne-Fleur' met burgerservicenummer '000000061'
      * is meerderjarig



  Regel: Als de ouders met elkaar zijn getrouwd, hertrouwd of partnerschap zijn aangegaan na gerechtelijke uitspraak tot gezag voor één van beide ouders, wordt het gezag van rechtswege bepaald
    # Dit is het geval wanneer:
    # * indicatie gezag is '1' of '2'
    # * ouder1 en ouder2 met elkaar zijn gehuwd of geregistreerd partnerschap aangegaan
    # * datum aangaan huwelijk of geregistreerd partnerschap ligt na de ingangsdatum geldigheid van de gezagsverhouding 

    Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige waarbij na gerechtelijke uitspraak tot gezag ouder<indicatie gezag> de ouders zijn getrouwd
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | <indicatie gezag>                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 4 jaar                                                  |
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

    Abstract Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige waarbij na gerechtelijke uitspraak tot gezag ouder1 de ouders getrouwd zijn met een andere derde persoon (niet de ander ouder)
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | <indicatie gezag>                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Joris' zijn met elkaar gehuwd
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 4 jaar                                                  |
      En 'Henk' en 'Anne-Fleur' zijn met elkaar gehuwd
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
      | ouder.burgerservicenummer        | <gezaghebbende ouder>    |


      Voorbeelden:
      | indicatie gezag | gezaghebbende ouder |
      | 1               | 000000012           |
      | 2               | 000000024           |

    Abstract Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige waarbij voor gerechtelijke uitspraak tot gezag ouder<indicatie gezag> en tot nu de ouders met elkaar getrouwd zijn
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | <indicatie gezag>                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 6 jaar                                                  |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000036                |
      | ouder.burgerservicenummer        | <gezaghebbende ouder>    |


      Voorbeelden:
      | indicatie gezag | gezaghebbende ouder |
      | 1               | 000000012           |
      | 2               | 000000024           |

    Abstract Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige waarbij na gerechtelijke uitspraak tot gezag <omschrijving uitspraak> de ouders zijn getrouwd
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | <indicatie gezag>                    | gisteren - 5 jaar               |
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 4 jaar                                                  |
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

