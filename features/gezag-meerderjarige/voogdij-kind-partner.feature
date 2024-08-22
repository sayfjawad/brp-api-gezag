#language: nl

Functionaliteit: voogdij van een meerderjarige over een kind van een (ex)partner


    Achtergrond:
      Gegeven adres 'A1' heeft de volgende gegevens
      | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
      | 518                  | Turfmarkt          | 43                 | 0518200000583552                           |

      # kind geboren vóór huwelijk/partnerschap
      Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | naam                            | waarde          |
      | geslachtsnaam (02.40)           | Doornenbal      |
      | geboortedatum (03.10)           | morgen - 9 jaar |
      | geboorteland (03.30)            | 6030            |
      | geslachtsaanduiding (04.10)     | M               |
      | aktenummer (81.20)              | 1AA0101         |
      | ingangsdatum geldigheid (85.10) | morgen - 9 jaar |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde          |
      | burgerservicenummer (01.20)                        | 000000024       |
      | geslachtsnaam (02.40)                              | Doodewaard      |
      | geslachtsaanduiding (04.10)                        | V               |
      | aktenummer (81.20)                                 | 1AA0101         |
      | ingangsdatum geldigheid (85.10)                    | morgen - 9 jaar |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 9 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde          |
      | aktenummer (81.20)              | 1AA0101         |
      | ingangsdatum geldigheid (85.10) | morgen - 9 jaar |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde |
      | gemeente van inschrijving (09.10) | 0518   |

      # kind geboren tijdens aangaan huwelijk/partnerschap
      Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
      | naam                            | waarde          |
      | geslachtsnaam (02.40)           | Doornenbal      |
      | geboortedatum (03.10)           | morgen - 5 jaar |
      | geboorteland (03.30)            | 6030            |
      | geslachtsaanduiding (04.10)     | M               |
      | aktenummer (81.20)              | 1AA0101         |
      | ingangsdatum geldigheid (85.10) | 20060610        |
      En de persoon heeft een ouder '1' met de volgende gegevens
      | naam                                               | waarde          |
      | burgerservicenummer (01.20)                        | 000000024       |
      | geslachtsnaam (02.40)                              | Doodewaard      |
      | geslachtsaanduiding (04.10)                        | V               |
      | aktenummer (81.20)                                 | 1AA0101         |
      | ingangsdatum geldigheid (85.10)                    | morgen - 5 jaar |
      | datum ingang familierechtelijke betrekking (62.10) | morgen - 5 jaar |
      En de persoon heeft een ouder '2' met de volgende gegevens
      | naam                            | waarde          |
      | aktenummer (81.20)              | 1AA0101         |
      | ingangsdatum geldigheid (85.10) | morgen - 5 jaar |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |

Regel: een meerderjarige die van rechtswege voogdij heeft over een minderjarig kind van de partner krijgt de gezagsrelatie geleverd
    # het gezag kan achterhaald worden door het gezag van de kinderen van de (ex)partner(s) op te vragen
    # voor elke gezagsrelatie van de minderjarige kinderen wordt bepaald of de niet-ouder voogd is van dit kind

    Scenario: de (ex)partner van overleden ouder heeft gezag over een binnen het huwelijk geboren niet-erkend kind
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde     |
      | voornamen (02.10)               | Daniel     |
      | geslachtsnaam (02.40)           | Doornenbal |
      | geboortedatum (03.10)           | 19730301   |
      | geboorteland (03.30)            | 6030       |
      | geslachtsaanduiding (04.10)     | M          |
      | beschrijving document (82.30)   | PKA        |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                                | waarde          |
      | burgerservicenummer (01.20)                                         | 000000024       |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | morgen - 7 jaar |
      | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518            |
      | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030            |
      En de 'partner' is gewijzigd naar de volgende gegevens
      | naam                                                          | waarde    |
      | burgerservicenummer (01.20)                                   | 000000024 |
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10)  | 20200317  |
      | plaats ontbinding huwelijk/geregistreerd partnerschap (07.20) | 518       |
      | land ontbinding huwelijk/geregistreerd partnerschap (07.30)   | 6030      |
      | reden ontbinding huwelijk/geregistreerd partnerschap (07.40)  | O         |
      En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                                | waarde   |
      | geslachtsnaam (02.40)                                               | Dest     |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20220624 |
      | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | Brugge   |
      | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 5010     |
      En de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                            | waarde     |
      | voornamen (02.10)               | Delilah    |
      | voorvoegsel (02.30)             | van        |
      | geslachtsnaam (02.40)           | Doodewaard |
      | geboortedatum (03.10)           | 19741201   |
      | geboorteland (03.30)            | 6030       |
      | geslachtsaanduiding (04.10)     | V          |
      | beschrijving document (82.30)   | PKA        |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                                | waarde          |
      | burgerservicenummer (01.20)                                         | 000000012       |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | morgen - 7 jaar |
      | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518            |
      | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030            |
      En de persoon heeft een 'kind' met de volgende gegevens
      | naam                            | waarde          |
      | burgerservicenummer (01.20)     | 000000036       |
      | voornamen (02.10)               | Diana           |
      | geslachtsnaam (02.40)           | Doornenbal      |
      | geboortedatum (03.10)           | morgen - 9 jaar |
      | geboorteland (03.30)            | 6030            |
      | aktenummer (81.20)              | 1AA0101         |
      | ingangsdatum geldigheid (85.10) | morgen - 9 jaar |
      En de persoon heeft nog een 'kind' met de volgende gegevens
      | naam                            | waarde          |
      | burgerservicenummer (01.20)     | 000000048       |
      | voornamen (02.10)               | Daan            |
      | geslachtsnaam (02.40)           | Doornenbal      |
      | geboortedatum (03.10)           | morgen - 5 jaar |
      | geboorteland (03.30)            | 6030            |
      | aktenummer (81.20)              | 1AA0102         |
      | ingangsdatum geldigheid (85.10) | morgen - 5 jaar |
      En de persoon heeft de volgende 'overlijden' gegevens
      | naam                     | waarde   |
      | datum overlijden (08.10) | 20200317 |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000048 |
      En heeft 'gezag' een 'derde' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      
    Scenario: de partner van ouder onder curatele heeft gezag over een binnen het huwelijk geboren niet-erkend kind
      Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | naam                            | waarde     |
      | voornamen (02.10)               | Daniel     |
      | geslachtsnaam (02.40)           | Doornenbal |
      | geboortedatum (03.10)           | 19730301   |
      | geboorteland (03.30)            | 6030       |
      | geslachtsaanduiding (04.10)     | M          |
      | beschrijving document (82.30)   | PKA        |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                                | waarde          |
      | burgerservicenummer (01.20)                                         | 000000024       |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | morgen - 7 jaar |
      | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518            |
      | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030            |
      En de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
      | naam                            | waarde     |
      | voornamen (02.10)               | Delilah    |
      | voorvoegsel (02.30)             | van        |
      | geslachtsnaam (02.40)           | Doodewaard |
      | geboortedatum (03.10)           | 19741201   |
      | geboorteland (03.30)            | 6030       |
      | geslachtsaanduiding (04.10)     | V          |
      | beschrijving document (82.30)   | PKA        |
      En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
      | naam                              | waarde   |
      | gemeente van inschrijving (09.10) | 518      |
      En de persoon heeft een 'partner' met de volgende gegevens
      | naam                                                                | waarde          |
      | burgerservicenummer (01.20)                                         | 000000012       |
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | morgen - 7 jaar |
      | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518            |
      | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030            |
      En de persoon heeft een 'kind' met de volgende gegevens
      | naam                            | waarde          |
      | burgerservicenummer (01.20)     | 000000036       |
      | voornamen (02.10)               | Diana           |
      | geslachtsnaam (02.40)           | Doornenbal      |
      | geboortedatum (03.10)           | morgen - 9 jaar |
      | geboorteland (03.30)            | 6030            |
      | aktenummer (81.20)              | 1AA0101         |
      | ingangsdatum geldigheid (85.10) | morgen - 9 jaar |
      En de persoon heeft nog een 'kind' met de volgende gegevens
      | naam                            | waarde          |
      | burgerservicenummer (01.20)     | 000000048       |
      | voornamen (02.10)               | Daan            |
      | geslachtsnaam (02.40)           | Doornenbal      |
      | geboortedatum (03.10)           | morgen - 5 jaar |
      | geboorteland (03.30)            | 6030            |
      | aktenummer (81.20)              | 1AA0102         |
      | ingangsdatum geldigheid (85.10) | morgen - 5 jaar |
      En de persoon heeft de volgende 'gezagsverhouding' gegevens
      | naam                               | waarde                        |
      | indicatie curateleregister (33.10) | 1                             |
      | beschrijving document (82.30)      | kennisgeving curateleregister |
      | ingangsdatum geldigheid (85.10)    | 20231107                      |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000048 |
      En heeft 'gezag' een 'derde' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
