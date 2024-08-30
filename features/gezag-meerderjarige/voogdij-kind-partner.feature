#language: nl

Functionaliteit: voogdij van een meerderjarige over een kind van een (ex)partner


Regel: een meerderjarige die van rechtswege voogdij heeft over een minderjarig kind van de partner krijgt de gezagsrelatie geleverd
    # het gezag kan achterhaald worden door het gezag van de kinderen van de (ex)partner(s) op te vragen
    # voor elke gezagsrelatie van de minderjarige kinderen wordt bepaald of de niet-ouder voogd is van dit kind

    Scenario: de (ex)partner van overleden ouder heeft gezag over een binnen het huwelijk geboren niet-erkend kind
      Gegeven de persoon 'P1' met burgerservicenummer '000000012'
      * is meerderjarig
      * heeft geslachtsnaam gevuld
      En de persoon 'P2' met burgerservicenummer '000000024'
      * is meerderjarig
      * heeft geslachtsnaam gevuld
      * is overleden
      En persoon 'P1' heeft geregistreerd partnerschap met persoon 'P2'
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 20 jaar                                                 |
      En de relatie van persoon 'P1' is beëindigd
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) | plaats ontbinding huwelijk/geregistreerd partnerschap (07.20) | land ontbinding huwelijk/geregistreerd partnerschap (07.30) | reden ontbinding huwelijk/geregistreerd partnerschap (07.40) |
      | 20200317                                                     | 518                                                           | 6030                                                        | O                                                            |
      En de persoon 'P3' met burgerservicenummer '000000036'
      * is in Nederland geboren
      * is minderjarig
      * heeft persoon 'P2' als ouder1 vanaf de geboorteaangifte
      * is ingeschreven in de de BRP
      En persoon 'P2' heeft een kind 'P3'
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000036 |
      En heeft 'gezag' een 'derde' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      
    Scenario: de partner van ouder onder curatele heeft gezag over een binnen het huwelijk geboren niet-erkend kind
      Gegeven de persoon 'P1' met burgerservicenummer '000000012'
      * is meerderjarig
      * heeft geslachtsnaam gevuld
      En de persoon 'P2' met burgerservicenummer '000000024'
      * is meerderjarig
      * heeft geslachtsnaam gevuld
      * staat onder curatele
      En persoon 'P1' heeft geregistreerd partnerschap met persoon 'P2'
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 20 jaar                                                 |
      En de relatie van persoon 'P1' is beëindigd
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) | plaats ontbinding huwelijk/geregistreerd partnerschap (07.20) | land ontbinding huwelijk/geregistreerd partnerschap (07.30) | reden ontbinding huwelijk/geregistreerd partnerschap (07.40) |
      | 20200317                                                     | 518                                                           | 6030                                                        | O                                                            |
      En de persoon 'P3' met burgerservicenummer '000000036'
      * is in Nederland geboren
      * is minderjarig
      * heeft persoon 'P2' als ouder1 vanaf de geboorteaangifte
      * is ingeschreven in de de BRP
      En persoon 'P2' heeft een kind 'P3'
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000036 |
      En heeft 'gezag' een 'derde' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
