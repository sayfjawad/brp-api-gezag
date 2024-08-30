#language: nl

Functionaliteit: meerderjarige zonder gezag


Regel: wanneer de persoon meerderjarig is en geen gezag heeft over minderjarige(n) wordt het burgerservicenummer geleverd en een lege lijst gezag  
    
    Scenario: persoon is meerderjarig en heeft geen kinderen  
      Gegeven de persoon 'P3' met burgerservicenummer '000000012'
      * is in Nederland geboren
      * is meerderjarig
      * is ingeschreven in de de BRP
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon geen gezag

    Scenario: ouder staat onder curatele en heeft daarom geen gezag over het minderjarige kind
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
      En de persoon 'P3' met burgerservicenummer '000000036'
      * is in Nederland geboren
      * is minderjarig
      * heeft persoon 'P1' als ouder1 vanaf de geboorteaangifte
      * heeft persoon 'P2' als ouder2 vanaf de geboorteaangifte
      * is ingeschreven in de de BRP
      En persoon 'P1' heeft een kind 'P3'
      En persoon 'P2' heeft een kind 'P3'
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000024 |
      En heeft de persoon geen gezag

    Scenario: bij raadplegen van ouder wordt geen gezag geleverd voor kind waarvan gezag niet te bepalen is
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
      * heeft persoon 'P1' als ouder1 vanaf de geboorteaangifte
      * heeft persoon 'P2' als ouder2 vanaf de geboorteaangifte
      * is ingeschreven in de de BRP
      * is geëmigreerd geweest
      | datum vestiging in Nederland (14.20) |
      | 20231014                             |
      En persoon 'P1' heeft een kind 'P3'
      En persoon 'P2' heeft een kind 'P3'
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000012 |
      En heeft de persoon geen gezag
