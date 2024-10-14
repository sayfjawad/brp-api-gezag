#language: nl

Functionaliteit: meerderjarige zonder gezag


Regel: wanneer de persoon meerderjarig is en geen gezag heeft over minderjarige(n) wordt het burgerservicenummer geleverd en een lege lijst gezag  
    
    Scenario: persoon is meerderjarig en heeft geen kinderen  
      Gegeven de persoon 'Dick' met burgerservicenummer '000000012'
      * is ingeschreven in de BRP
      * is meerderjarig
      * is in Nederland geboren
      * is niet geëmigreerd geweest
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon geen gezag

    Scenario: ouder staat onder curatele en heeft daarom geen gezag over het minderjarige kind
      Gegeven de persoon 'Ingrid' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Henk' met burgerservicenummer '000000024'
      * is meerderjarig
      * staat onder curatele
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      En de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is in Nederland geboren
      * heeft 'Ingrid' als ouder 1
      * heeft 'Henk' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
      En heeft de persoon geen gezag

    Scenario: bij raadplegen van ouder wordt geen gezag geleverd voor kind waarvan gezag niet te bepalen is
      Gegeven de persoon 'Ingrid' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Henk' met burgerservicenummer '000000024'
      * is meerderjarig
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      En de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is in Nederland geboren
      * heeft 'Ingrid' als ouder 1
      * heeft 'Henk' als ouder 2
      * is geëmigreerd geweest
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon geen gezag
