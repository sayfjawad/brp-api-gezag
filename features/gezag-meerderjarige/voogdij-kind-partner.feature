#language: nl

Functionaliteit: voogdij van een meerderjarige over een kind van een (ex)partner


Regel: een meerderjarige die van rechtswege voogdij heeft over een minderjarig kind van de partner krijgt de gezagsrelatie geleverd
    # het gezag kan achterhaald worden door het gezag van de kinderen van de (ex)partner(s) op te vragen
    # voor elke gezagsrelatie van de minderjarige kinderen wordt bepaald of de niet-ouder voogd is van dit kind

      
    Scenario: de partner van ouder onder curatele heeft gezag over een binnen het huwelijk geboren niet-erkend kind
      Gegeven de persoon 'Henk' met burgerservicenummer '000000012'
      * is ingeschreven in de BRP
      * is meerderjarig
      En de persoon 'Ingrid' met burgerservicenummer '000000024'
      * is ingeschreven in de BRP
      * is meerderjarig
      * staat onder curatele
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      En de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is in Nederland geboren
      * is niet geëmigreerd geweest
      * heeft 'Ingrid' als ouder 1
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
      
    Scenario: de ex-partner van ouder onder curatele heeft gezag over een binnen het huwelijk geboren niet-erkend kind
      Gegeven de persoon 'Henk' met burgerservicenummer '000000012'
      * is ingeschreven in de BRP
      * is meerderjarig
      En de persoon 'Ingrid' met burgerservicenummer '000000024'
      * is ingeschreven in de BRP
      * is meerderjarig
      * staat onder curatele
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 19 jaar                                                 |
      En 'Ingrid' en 'Henk' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | gisteren - 2 jaar                                            |
      En de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is in Nederland geboren
      * is niet geëmigreerd geweest
      * heeft 'Ingrid' als ouder 1
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

    Scenario: de (ex)partner van overleden ouder heeft gezag over een binnen het huwelijk geboren niet-erkend kind
      Gegeven de persoon 'Henk' met burgerservicenummer '000000012'
      * is ingeschreven in de BRP
      * is meerderjarig
      En de persoon 'Ingrid' met burgerservicenummer '000000024'
      * is ingeschreven in de BRP
      * is meerderjarig
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      En de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is in Nederland geboren
      * is niet geëmigreerd geweest
      * heeft 'Ingrid' als ouder 1
      En 'Ingrid'
      * is overleden
      En de persoon 'Femke' met burgerservicenummer '000000048'
      * is meerderjarig
      En 'Henk' en 'Femke' zijn met elkaar gehuwd
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

    Scenario: de inmiddels met een ander getrouwde (ex)partner van overleden ouder heeft gezag over een binnen het huwelijk geboren niet-erkend kind
      Gegeven de persoon 'Henk' met burgerservicenummer '000000012'
      * is ingeschreven in de BRP
      * is meerderjarig
      En de persoon 'Ingrid' met burgerservicenummer '000000024'
      * is ingeschreven in de BRP
      * is meerderjarig
      En 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      En de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is in Nederland geboren
      * heeft 'Ingrid' als ouder 1
      En 'Ingrid'
      * is overleden
      En de persoon 'Femke' met burgerservicenummer '000000048'
      En 'Henk' en 'Femke' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | gisteren - 1 jaar                                                  |
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
