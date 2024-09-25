    #language: nl

    Functionaliteit:  3.1 - Is er door een recente gebeurtenis - adoptie - het gezag toch (weer) van rechtswege ondanks dat er eerder een uitspraak is gedaan?


    Achtergrond:
    Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
    * is ingeschreven in de BRP
    * is minderjarig
    * is niet geëmigreerd geweest
    * is in Nederland geboren
    * heeft een ouder 1 'Ingrid' met burgerservicenummer '000000012'
    * heeft een ouder 2 'Henk' met burgerservicenummer '000000024'
    * 'Ingrid' en 'Henk' zijn met elkaar gehuwd
    * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele
    * 'Ingrid' en 'Henk' zijn gescheiden
    # * is het huwelijk van 'Ingrid' en 'Henk' gecorrigeerd
    #   | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
    #   | morgen - 4 jaar                                                    |

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
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
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
      En is geadopteerd
      En zijn de volgende gegevens van ouder '<adoptie ouder>' gewijzigd
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

      Voorbeelden:
      | adoptie ouder |
      | Ingrid        |
      | Henk          |

  Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige die is geadopteerd vóór de gerechtelijke uitspraak
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 6 jaar                                    |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
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
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
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


  Regel: Als eerder opgegeven adoptie na de gerechtelijke uitspraak is gecorrigeerd wordt gezag bepaald volgens de gerechtelijke uitspraak over gezag
  # Als een historisch voorkomen van de persoonsgegevens een adoptie aangeeft - akteaanduiding (51.81.20) bevat de waarde Q - maar dat voorkomen heeft
  # indicatie onjuist met een waarde, dan wordt het aktenummer in dat historisch voorkomen genegeerd

  Scenario: gezag wordt bepaald uit gerechtelijke uitspraak voor minderjarige waarbij na de gerechtelijke uitspraak ten onrechte adoptie is geregistreerd
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
      En zijn de volgende gegevens gecorrigeerd
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

  Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd na de gerechtelijke uitspraak
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | 00000000                        |
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
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
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | 20210617                                           |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
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
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | 20210617                                           |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
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
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | <datum adoptie door Ingrid>                        |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
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

      Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd na de gerechtelijke uitspraak en datum ingang familierechtelijke betrekking van de andere ouder is volledig onbekend
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | 20210617                        |
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | <datum voor Ingrid>                                |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | <datum voor Henk>                                  |
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
      | datum voor Ingrid | datum voor Henk  |
      | 00000000          | morgen - 17 jaar |
      | 20230526          | 20230526         |


      Regel: Als de datum adoptie gedeeltelijk onbekend is, wordt de eerste dag van de maand van de onzekerheidsperiode aangenomen

      Abstract Scenario: gezag wordt van rechtswege bepaald voor minderjarige die is geadopteerd en <omschrijving>
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | 20210617                        |
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | <datum adoptie>                                    |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
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
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | <datum adoptie>                                    |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
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
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) | aanduiding in onderzoek (83.10) |
      | gisteren - 4 jaar                                  | <aanduiding onderzoek Ingrid>   |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) | aanduiding in onderzoek (83.10) |
      | gisteren - 4 jaar                                  | <aanduiding onderzoek Henk>     |
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
      | aanduiding onderzoek Ingrid | aanduiding onderzoek Henk | omschrijving                                           | toelichting                               |
      | 020000                      |                           | hele categorie ouder 1                                 | familierechtelijke betrekking van ouder 1 |
      | 026200                      |                           | hele groep familierechtelijke betrekking van ouder 1   | familierechtelijke betrekking van ouder 1 |
      | 026210                      |                           | datum ingang familierechtelijke betrekking van ouder 1 | familierechtelijke betrekking van ouder 1 |
      |                             | 030000                    | hele categorie ouder 2                                 | familierechtelijke betrekking van ouder 2 |
      |                             | 036200                    | hele groep familierechtelijke betrekking van ouder 2   | familierechtelijke betrekking van ouder 2 |
      |                             | 036210                    | datum ingang familierechtelijke betrekking van ouder 2 | familierechtelijke betrekking van ouder 2 |

  Scenario: gezag kan wel worden bepaald als het onderzoek op de familierechtelijke betrekking is beëindigd
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) | aanduiding in onderzoek (83.10) | datum einde onderzoek (83.30) |
      | gisteren - 4 jaar                                  | 026210                          | gisteren                      |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
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
      En is geadopteerd
      En zijn de volgende gegevens van ouder 'Ingrid' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) | aanduiding in onderzoek (83.10) | datum einde onderzoek (83.30) |
      | gisteren - 4 jaar                                  | 026210                          | <einde onderzoek Ingrid>      |
      En zijn de volgende gegevens van ouder 'Henk' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) | aanduiding in onderzoek (83.10) | datum einde onderzoek (83.30) |
      | gisteren - 4 jaar                                  | 036210                          | <einde onderzoek Henk>        |
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
      | einde onderzoek Ingrid | einde onderzoek Henk | toelichting                               |
      | gisteren               |                      | familierechtelijke betrekking van ouder 2 |
      |                        | gisteren             | familierechtelijke betrekking van ouder 1 |


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