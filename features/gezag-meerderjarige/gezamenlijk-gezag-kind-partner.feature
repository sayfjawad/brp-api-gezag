#language: nl

Functionaliteit: gezamenlijk gezag van een meerderjarige over een kind van een (ex)partner


  Regel: een meerderjarige die gezamenlijk gezag heeft over een minderjarig kind van de partner krijgt de gezagsrelatie geleverd
    # het gezag kan achterhaald worden door het gezag van de kinderen van de partner op te vragen
    # voor elke gezagsrelatie van de minderjarige kinderen wordt bepaald of de niet-ouder gezamenlijk gezag heeft over het kind

    Scenario: persoon heeft van rechtswege gezamenlijk gezag over het minderjarige kind van diens partner
      Gegeven de persoon 'Henk' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Ingrid' met burgerservicenummer '000000024'
      * is meerderjarig
      En 'Henk' en 'Ingrid' zijn met elkaar gehuwd
      En de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is in Nederland geboren
      * is niet geëmigreerd geweest
      * heeft 'Ingrid' als ouder 1
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde           |
      | type                             | GezamenlijkGezag |
      | minderjarige.burgerservicenummer | 000000036        |
      | ouder.burgerservicenummer        | 000000024        |
      | derde.burgerservicenummer        | 000000012        |
