#language: nl

Functionaliteit:  3.1 - Is er door een recente gebeurtenis - adoptie - het gezag toch (weer) van rechtswege ondanks dat er eerder een uitspraak is gedaan?


    Achtergrond:
      Gegeven de persoon 'Ingrid' met burgerservicenummer '000000012'
      * is meerderjarig, niet overleden en staat niet onder curatele
      En de persoon 'Henk' met burgerservicenummer '000000024'
      * is meerderjarig, niet overleden en staat niet onder curatele
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      En de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren

  Regel: Als adoptie heeft plaatsgevonden na de gerechtelijke uitspraak over gezag, wordt het gezag van rechtswege bepaald
    # Als de ingangsdatum gezag (11.85.10) kleiner is dan de datum ingang familierechtelijke betrekking (02.62.10 en 03.62.10) 
    # en de akteaanduiding (01.81.20 of 51.81.20) bevat de waarde Q op de 3e positie dan is sprake van een adoptie nadat het 
    # gezag is opgenomen. 
    # Deze kan in de actuele categorie voorkomen maar ook in een historische categorie omdat er later nog een ander rechtsfeit 
    # geregistreerd kan zijn.

    Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd door beide ouders na de gerechtelijke uitspraak
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | <indicatie gezag>                    | gisteren - 5 jaar               |
      En is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aktenummer (81.20) |
      | morgen - 4 jaar                                    | 1AQ0101            |
      En is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aktenummer (81.20) |
      | morgen - 4 jaar                                    | 1AQ0101            |
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

    Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd door <adoptie ouder> na de gerechtelijke uitspraak
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En heeft '<andere ouder>' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | gisteren - 17 jaar                                 |
      En is geadopteerd door '<adoptie ouder>' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aktenummer (81.20) |
      | morgen - 4 jaar                                    | 1AQ0102            |
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
      | adoptie ouder | andere ouder | andere ouder bsn |
      | Ingrid        | Henk         | 000000024        |
      | Henk          | Ingrid       | 000000012        |

    Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige die is geadopteerd vóór de gerechtelijke uitspraak
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 6 jaar                                    |
      En is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 6 jaar                                    |
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
      En is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
      En is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
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


  Regel: Onjuist opgenomen (gecorrigeerde) adoptiegegevens worden genegeerd
    # Als een historisch voorkomen van de persoonsgegevens een adoptie aangeeft - akteaanduiding (51.81.20) bevat de waarde Q - maar dat voorkomen heeft 
    # indicatie onjuist met een waarde, dan wordt het aktenummer in dat historisch voorkomen genegeerd

    Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige waarbij na de gerechtelijke uitspraak ten onrechte adoptie is geregistreerd
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AQ0100            | morgen - 4 jaar                                    |
      En is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | aktenummer (81.20) | datum ingang familierechtelijke betrekking (62.10) |
      | 1AQ0100            | morgen - 4 jaar                                    |
      En zijn de volgende gegevens gecorrigeerd
      | aktenummer (81.20) |
      | 1AR0200            |
      En zijn van ouder 1 de volgende gegevens gecorrigeerd
      | aktenummer (81.20) |
      | 1AR0200            |
      En zijn van ouder 2 de volgende gegevens gecorrigeerd
      | aktenummer (81.20) |
      | 1AR0200            |
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

    Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd en datum ingang de gerechtelijke uitspraak is volledig onbekend
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | 00000000                        |
      En is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
      En is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
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
      En is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | 20210617                                           |
      En is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | 20210617                                           |
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
      En is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | 20210617                                           |
      En is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | 20210617                                           |
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

    Abstract Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige die is geadopteerd en datum adoptie is volledig onbekend
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | 20210617                        |
      En is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | <datum adoptie door Ingrid>                        |
      En is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | <datum adoptie door Henk>                          |
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
      | datum adoptie door Ingrid | datum adoptie door Henk |
      | 00000000                  | morgen - 17 jaar        |
      | morgen - 17 jaar          | 00000000                |
      | 00000000                  | 00000000                |


  Regel: Als de datum adoptie gedeeltelijk onbekend is, wordt de eerste dag van de maand van de onzekerheidsperiode aangenomen

    Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd en <omschrijving>
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | 20210617                        |
      En is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | <datum adoptie>                                    |
      En is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | <datum adoptie>                                    |
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
      En is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | <datum adoptie>                                    |
      En is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | <datum adoptie>                                    |
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

    
  Regel: Als datum familierechtelijke betrekking in onderzoek staat is het gezag niet te bepalen

    Abstract Scenario: gezag kan niet worden bepaald wanneer <omschrijving> in onderzoek staat
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aanduiding in onderzoek (83.10) |
      | gisteren - 4 jaar                                  | <aanduiding onderzoek Ingrid>   |
      En is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aanduiding in onderzoek (83.10) |
      | gisteren - 4 jaar                                  | <aanduiding onderzoek Henk>     |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                                       |
      | type        | GezagNietTeBepalen                                                                                                           |
      | toelichting | Gezag is niet te bepalen, omdat de volgende relevante gegevens in onderzoek staan. Persoonslijst van persoon: <toelichting>. |

      Voorbeelden:
      | aanduiding onderzoek Ingrid | aanduiding onderzoek Henk | omschrijving                                           | toelichting                                |
      | 020000                      |                           | hele categorie ouder 1                                 | ouder 1                                    |
      | 026200                      |                           | hele groep familierechtelijke betrekking van ouder 1   | datum ingang familiebetrekking van ouder 1 |
      | 026210                      |                           | datum ingang familierechtelijke betrekking van ouder 1 | datum ingang familiebetrekking van ouder 1 |
      |                             | 030000                    | hele categorie ouder 2                                 | ouder 2                                    |
      |                             | 036200                    | hele groep familierechtelijke betrekking van ouder 2   | datum ingang familiebetrekking van ouder 2 |
      |                             | 036210                    | datum ingang familierechtelijke betrekking van ouder 2 | datum ingang familiebetrekking van ouder 2 |

    Scenario: gezag kan wel worden bepaald als het onderzoek op de familierechtelijke betrekking is beëindigd
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aanduiding in onderzoek (83.10) | datum einde onderzoek (83.30) |
      | gisteren - 4 jaar                                  | 026210                          | gisteren                      |
      En is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aanduiding in onderzoek (83.10) | datum einde onderzoek (83.30) |
      | gisteren - 4 jaar                                  | 036210                          | gisteren                      |
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

    Abstract Scenario: gezag kan niet worden bepaald als het onderzoek op de familierechtelijke betrekking is beëindigd voor een ouder en niet beëindigd voor de andere ouder
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aanduiding in onderzoek (83.10) | datum einde onderzoek (83.30) |
      | gisteren - 4 jaar                                  | 026210                          | <einde onderzoek Ingrid>      |
      En is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aanduiding in onderzoek (83.10) | datum einde onderzoek (83.30) |
      | gisteren - 4 jaar                                  | 036210                          | <einde onderzoek Henk>        |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                                       |
      | type        | GezagNietTeBepalen                                                                                                           |
      | toelichting | Gezag is niet te bepalen, omdat de volgende relevante gegevens in onderzoek staan. Persoonslijst van persoon: <toelichting>. |

      Voorbeelden:
      | einde onderzoek Ingrid | einde onderzoek Henk | toelichting                                |
      | gisteren               |                      | datum ingang familiebetrekking van ouder 2 |
      |                        | gisteren             | datum ingang familiebetrekking van ouder 1 |


  Regel: Als indicatie gezag of ingangsdatum gezagsverhouding in onderzoek staan, is het gezag niet te bepalen

    Abstract Scenario: gezag kan niet worden bepaald wanneer <omschrijving> in onderzoek staat
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) | aanduiding in onderzoek (83.10) |
      | D                                    | gisteren - 5 jaar               | <aanduiding onderzoek>          |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                                       |
      | type        | GezagNietTeBepalen                                                                                                           |
      | toelichting | Gezag is niet te bepalen, omdat de volgende relevante gegevens in onderzoek staan. Persoonslijst van persoon: <toelichting>. |

      Voorbeelden:
      | aanduiding onderzoek | omschrijving                    | toelichting                   |
      | 110000               | hele categorie gezagsverhouding | gezagsverhouding              |
      | 113200               | hele groep gezag minderjarige   | indicatie gezag minderjarige  |
      | 113210               | indicatie gezag minderjarige    | indicatie gezag minderjarige  |
      | 118500               | hele groep geldigheid           | ingangsdatum geldigheid gezag |
      | 118510               | datum ingang geldigheid         | ingangsdatum geldigheid gezag |

    Scenario: gezag kan wel worden bepaald als het onderzoek op de gezagsverhouding is beëindigd
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) | aanduiding in onderzoek (83.10) | datum einde onderzoek (83.30) |
      | D                                    | gisteren - 5 jaar               | 110000                          | gisteren                      |
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
