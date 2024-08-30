#language: nl

Functionaliteit: gezamenlijk gezag van een meerderjarige over een kind van een (ex)partner


  Regel: een meerderjarige die gezamenlijk gezag heeft over een minderjarig kind van de partner krijgt de gezagsrelatie geleverd
    # het gezag kan achterhaald worden door het gezag van de kinderen van de partner op te vragen
    # voor elke gezagsrelatie van de minderjarige kinderen wordt bepaald of de niet-ouder gezamenlijk gezag heeft over het kind

    Scenario: persoon heeft van rechtswege gezamenlijk gezag over het minderjarige kind van diens partner
      Gegeven de persoon 'P1' met burgerservicenummer '000000012'
      * is meerderjarig
      * heeft geslachtsnaam gevuld
      En de persoon 'P2' met burgerservicenummer '000000024'
      * is meerderjarig
      * heeft geslachtsnaam gevuld
      En persoon 'P1' heeft geregistreerd partnerschap met persoon 'P2'
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 20 jaar                                                 |
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
      | naam                             | waarde           |
      | type                             | GezamenlijkGezag |
      | minderjarige.burgerservicenummer | 000000036        |
      | ouder.burgerservicenummer        | 000000024        |
      En heeft 'gezag' een 'derde' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
