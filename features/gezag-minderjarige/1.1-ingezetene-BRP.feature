#language: nl

Functionaliteit: 1.1 - Staat persoon (minderjarige) als ingezetene in de BRP?


    Achtergrond:
      Gegeven de persoon 'P1' met burgerservicenummer '000000012'
      * is meerderjarig
      * heeft geslachtsnaam gevuld
      En de persoon 'P2' met burgerservicenummer '000000024'
      * is meerderjarig
      * heeft geslachtsnaam gevuld
      En persoon 'P1' heeft geregistreerd partnerschap met persoon 'P2'
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 20 jaar                                                 |


  Regel: Als de gemeente van inschrijving is RNI kan het gezag van de persoon niet worden bepaald

    Scenario: minderjarige staat ingeschreven in RNI (route 1)
      Gegeven de persoon 'P3' met burgerservicenummer '000000036'
      * is in Nederland geboren
      * is minderjarige
      * is geregistreerd met de geboorteakte
      | aktenummer (81.20) |
      | 1GA0001            |
      * heeft persoon 'P1' als ouder1 vanaf de geboorteaangifte
      * heeft persoon 'P2' als ouder2 vanaf de geboorteaangifte
      * is ingeschreven in de de RNI
      | gemeente van inschrijving (09.10) |
      | 1999                              |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                               |
      | type        | GezagNietTeBepalen                                                   |
      | toelichting | gezag is niet te bepalen omdat minderjarige niet in Nederland woont. |

    Scenario: minderjarige staat ingeschreven in Nederlandse gemeente
      Gegeven de persoon 'P3' met burgerservicenummer '000000036'
      * is in Nederland geboren
      * is minderjarige
      * is geregistreerd met de geboorteakte
      | aktenummer (81.20) |
      | 1GA0001            |
      * heeft persoon 'P1' als ouder1 vanaf de geboorteaangifte
      * heeft persoon 'P2' als ouder2 vanaf de geboorteaangifte
      * is ingeschreven in de de BRP
      | gemeente van inschrijving (09.10) |
      | 0518                              |
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


  Regel: onderzoek op gemeente van inschrijving leidt niet tot uitval

    Abstract Scenario: minderjarige staat ingeschreven in Nederlandse gemeente en <omschrijving> staat in onderzoek
      Gegeven de persoon 'P3' met burgerservicenummer '000000036'
      * is in Nederland geboren
      * is minderjarige
      * is geregistreerd met de geboorteakte
      | aktenummer (81.20) |
      | 1GA0001            |
      * heeft persoon 'P1' als ouder1 vanaf de geboorteaangifte
      * heeft persoon 'P2' als ouder2 vanaf de geboorteaangifte
      * is ingeschreven in de de BRP
      | gemeente van inschrijving (09.10) | aanduiding in onderzoek (83.10) |
      | 0518                              | <aanduiding in onderzoek>       |
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
      | aanduiding in onderzoek | omschrijving                        |
      | 080000                  | hele categorie verblijfplaats       |
      | 080900                  | groep gemeente                      |
      | 080910                  | gemeente van inschrijving           |
      | 089999                  | vastgesteld verblijft niet op adres |


  Regel: wanneer gemeente van inschrijving ontbreekt is gezag niet te bepalen

    Scenario: er is geen verblijfplaats vastgelegd van de minderjarige  
      Gegeven de persoon 'P3' met burgerservicenummer '000000036'
      * is in Nederland geboren
      * is minderjarige
      * is geregistreerd met de geboorteakte
      | aktenummer (81.20) |
      | 1GA0001            |
      * heeft persoon 'P1' als ouder1 vanaf de geboorteaangifte
      * heeft persoon 'P2' als ouder2 vanaf de geboorteaangifte
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                        |
      | type        | GezagNietTeBepalen                                                                                            |
      | toelichting | gezag is niet te bepalen omdat de volgende relevante gegevens ontbreken: verblijfplaats van bevraagde persoon |
