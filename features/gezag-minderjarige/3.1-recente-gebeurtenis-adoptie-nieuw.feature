#language: nl

Functionaliteit: Bepaling van gezag bij een geadopteerd minderjarige met een gerechtelijke uitspraak over zijn gezag

  Als consumer van de gezag api
  Wil ik weten of hoe api het gezag bepaald voor een minderjarige
  - over wie een gerechtelijke uitspraak over zijn gezag is gedaan
  - en vóór of ná de gerechtelijke uitspraak is geadopteerd

  Zie:
  - [personas](./personas.md) voor betekenis gebruikte personas
  - [glossary](./glossary.md) voor terminologie

  Regel: het gezag wordt van rechtswege bepaald als de adoptie na de gerechtelijke uitspraak over gezag heeft plaatsgevonden

    Er is sprake van adoptie na opname van het gezag als:
    - ingangsdatum gezag (11.85.10) vóór datum ingang familierechtelijke betrekking (02.62.10 en 03.62.10) van tenminste één adoptie ouder ligt én
    - de actuele/historische akteaanduiding (01.81.20/05.81.20) bij de minderjarige de waarde Q op de 3e positie heeft

    Scenario: persoon met gerechtelijke uitspraak en geen ouders wordt door een echtpaar na de gerechtelijke uitspraak geadopteerd (regel 25)
      Gegeven de persoon 'Henk'
      En de persoon 'Ingrid'
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      En de persoon 'Jaimy'
  	  En voor 'Jaimy' is een gerechtelijke uitspraak over het gezag gedaan met de volgende gegevens
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En 'Jaimy' is geadopteerd met de volgende gegevens
      | ouder 1 | ouder 2 | datum ingang familierechtelijke betrekking (62.10) |
      | Ingrid  | Henk    | morgen - 4 jaar                                    |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan is het gezag van rechtswege bepaald
      En heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |

    Scenario: persoon met gerechtelijke uitspraak en één ouder wordt door één persoon na de gerechtelijke uitspraak geadopteerd (regel 61)
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan is het gezag van rechtswege bepaald
      En heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |

    Scenario: persoon met gerechtelijke uitspraak en geen ouders is door een echtpaar vóór de gerechtelijke uitspraak geadopteerd (regel 93)
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan is het gezag aan de hand van het gerechtelijke uitspraak over het gezag bepaald
      En heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |

    Scenario: adoptie door één persoon na de gerechtelijke uitspraak én gegevens van de minderjarige zijn op/na de adoptie gewijzigd (regel 115)
    # typo in 'oude' scenario titel. Die zegt vóór de gerechtelijke uitspraak maar beschrijft adoptie na de gerechtelijke uitspraak. Maar de response is wel weer wat je krijgt uit als het gezag uit de gerechtelijke uitspraak wordt geleverd
  	  Gegeven voor 'Jaimy' is een gerechtelijke uitspraak over het gezag gedaan met de volgende gegevens
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is 'Jaimy' geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
      En heeft 'Jaimy' zijn naam gewijzigd op 'morgen - 4 jaar'
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan is het gezag van rechtswege bepaald
      En heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |

  Regel: gecorrigeerde adoptiegegevens worden genegeerd bij het bepalen van het gezag

    Scenario: er is ten onrechte adoptie geregistreerd bij de minderjarige (regel 150)
  	  Gegeven voor 'Jaimy' is een gerechtelijke uitspraak over het gezag gedaan met de volgende gegevens
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | gisteren - 5 jaar               |
      En is 'Jaimy' geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
      En de adoptie wordt als ten onrechte geregistreerd
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan is het gezag aan de hand van het gerechtelijke uitspraak over het gezag bepaald
      En heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |

  Regel: gerechtelijke uitspraken over gezag met een volledig onbekend ingangsdatum worden genegeerd bij het bepalen van het gezag

    Scenario: datum ingang gerechtelijke uitspraak is volledig onbekend (regel 184)
  	  Gegeven voor 'Jaimy' is een gerechtelijke uitspraak over het gezag gedaan met de volgende gegevens
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | 00000000                        |
      En is 'Jaimy' geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | morgen - 4 jaar                                    |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan is het gezag van rechtswege bepaald
      En heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |

  Regel: bij gerechtelijke uitspraken over gezag met een deels onbekend ingangsdatum wordt de eerste dag van de onzekerheidsperiode als ingangsdatum gehanteerd

    Scenario: ingangsdatum adoptie ligt na de eerste dag van de onzekerheidsperiode van datum ingang gerechtelijke uitspraak
  	  Gegeven voor 'Jaimy' is een gerechtelijke uitspraak over het gezag gedaan met de volgende gegevens
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | 20210600                        |
      En is 'Jaimy' geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | 20210617                                           |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan wordt 20160601 als datum ingang gerechtelijke uitspraak gehanteerd
      En is het gezag van rechtswege bepaald
      En heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |

  Regel: adopties met volledig onbekend ingangsdatum worden genegeerd bij het bepalen van het gezag

    Scenario: ingangsdatum adoptie is volledig onbekend en de persoon heeft een gerechtelijke uitspraak over gezag
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan is het gezag aan de hand van het gerechtelijke uitspraak over het gezag bepaald
      En heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |

  Regel: bij adopties met een deels onbekend ingangsdatum wordt de eerste dag van de onzekerheidsperiode als ingangsdatum gehanteerd

    Scenario: de eerste dag van de onzekerheidsperiode van datum ingang adoptie ligt na datum ingang gerechtelijke uitspraak

  Regel: het gezag kan niet worden bepaald als de adoptie na de gerechtelijke uitspraak over gezag heeft plaatsgevonden en datum ingang adoptie van één of beide adoptie ouders staat in onderzoek

    Scenario: alle gegevens van adoptie ouder 1 staan in onderzoek (regel 400 en 403)

    Scenario: alle familierechtelijke betrekking gegevens van adoptie ouder 1 staan in onderzoek (regel 401 en 404)

    Scenario: datum ingang adoptie van adoptie ouder 1 staan in onderzoek (regel 402 en 405)

    Scenario: wat als de persoon een niet-adoptie ouder heeft die in onderzoek staat

    Scenario: datum ingang adoptie van adoptie ouder(s) staan in onderzoek en het onderzoek is beëindigd (regel 407)

    Scenario: datum ingang adoptie van adoptie ouders staan in onderzoek en het onderzoek is alleen bij één adoptie ouder beëindigd (regel 434)

  Regel: het gezag kan niet worden bepaald als de adoptie vóór de gerechtelijke uitspraak over gezag heeft plaatsgevonden én de ingangsdatum of de indicatie van de gerechtelijke uitspraak staat in onderzoek

    Scenario: alle gegevens van de gerechtelijke uitspraak staan in onderzoek (regel 480)

    Scenario: de indicatie van het gezag van de gerechtelijke uitspraak staat in onderzoek (regel 482)

    Scenario: de ingangsdatum van het gerechtelijke gezag staat in onderzoek (regel 484)

    Scenario: de gehele gerechtelijke uitspraak staat in onderzoek en het onderzoek is beëindigd (regel 486)
