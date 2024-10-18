#language: nl

Functionaliteit: gezag van een meerderjarige die niet in de BRP staat ingeschreven

    Achtergrond:
      Gegeven de persoon 'Henk' met burgerservicenummer '000000012'
      * is ingeschreven in de BRP
      * is meerderjarig
      En de persoon 'Ingrid' met burgerservicenummer '000000024'
      * is ingeschreven in de BRP
      * is meerderjarig
      En 'Henk' en 'Ingrid' zijn met elkaar gehuwd

  Regel: een persoon die in RNI staat ingeschreven krijgt gezag niet te bepalen geleverd wanneer de leeftijd lager is dan 18 jaar
    # wanneer de persoon meerderjarig is, moet geen 'gezag niet te bepalen' worden geleverd
    # 'gezag niet te bepalen' wordt alleen geleverd wanneer de persoon minderjarig is

    Abstract Scenario: minderjarig persoon in RNI
      Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de RNI
      * is minderjarig met de volgende gegevens
      | geboortedatum (03.10) |
      | <geboortedatum>       |
      * is in Nederland geboren
      * is niet geëmigreerd geweest
      * heeft 'Ingrid' als ouder 1
      * heeft 'Henk' als ouder 2
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

      Voorbeelden:
      | geboortedatum    |
      | 20180301         |
      | vandaag          |
      | gisteren         |
      | morgen - 5 jaar  |
      | morgen - 18 jaar |

    Abstract Scenario: een meerderjarig persoon in RNI en zonder kinderen 
      Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de RNI
      * is meerderjarig met de volgende gegevens
      | geboortedatum (03.10) |
      | <geboortedatum>       |
      * is in Nederland geboren
      * is niet geëmigreerd geweest
      * heeft 'Ingrid' als ouder 1
      * heeft 'Henk' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon geen gezag

      Voorbeelden:
      | geboortedatum      |
      | gisteren - 45 jaar |
      | gisteren - 18 jaar |
      | vandaag - 18 jaar  |

    Scenario: meerderjarige persoon in RNI met gezag over kind
      Gegeven de persoon 'Geert' met burgerservicenummer '000000048'
      * is ingeschreven in de BRP
      * is meerderjarig
      En de persoon 'Femke' met burgerservicenummer '000000061'
      * is ingeschreven in de RNI
      * is meerderjarig
      En 'Geert' en 'Femke' zijn met elkaar gehuwd
      En de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is in Nederland geboren
      * is niet geëmigreerd geweest
      * heeft 'Femke' als ouder 1
      * heeft 'Geert' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | TweehoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000036                 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000048 |
      En heeft 'gezag' een 'ouder' met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000061 |


  Regel: een persoon die in RNI staat ingeschreven met een onvolledige geboortedatum krijgt gezag niet te bepalen geleverd wanneer de persoon op de eerste dag van de onzekerheidsperiode niet ouder dan 18 jaar is
    # leeftijd wordt berekend op basis van een fixtieve geboortedatum gelijk aan de eerste dag van de onzekerheidsperiode
    # wanneer de persoon meerderjarig is, moet geen 'gezag niet te bepalen' worden geleverd
    # 'gezag niet te bepalen' wordt alleen geleverd wanneer de persoon nog minderjarig is
    
    Abstract Scenario: persoon in RNI <omschrijving>
      Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de RNI
      # * is meerderjarig met de volgende gegevens
      # | geboortedatum (03.10) |
      # | <geboortedatum>       |
      * is meerderjarig
      * is in Nederland geboren
      * is niet geëmigreerd geweest
      * heeft 'Ingrid' als ouder 1
      * heeft 'Henk' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met een 'gezag' met de volgende gegevens
      | naam | waarde             |
      | type | GezagNietTeBepalen |

      Voorbeelden:
      | geboortedatum            | leeftijd      | omschrijving                                                                             |
      | deze maand - 5 jaar      | 4 of 5 jaar   | met alleen maand en jaar van geboortedatum bekend en is de hele maand jonger dan 18 jaar |
      | dit jaar - 5 jaar        | 4 of 5 jaar   | met alleen jaar van geboortedatum bekend en is het hele jaar jonger dan 18 jaar          |
      | volgende maand - 18 jaar | 17 jaar       | met alleen maand en jaar van geboortedatum bekend en wordt volgende maand 18 jaar        |
      | dit jaar - 17 jaar       | 16 of 17 jaar | met alleen jaar van geboortedatum bekend en is of wordt dit jaar 17 jaar                 |

    Abstract Scenario: persoon in RNI <omschrijving>
      Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de RNI
      * is meerderjarig met de volgende gegevens
      | geboortedatum (03.10) |
      | <geboortedatum>       |
      * is in Nederland geboren
      * is niet geëmigreerd geweest
      * heeft 'Ingrid' als ouder 1
      * heeft 'Henk' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon zonder gezag

      Voorbeelden:
      | geboortedatum          | leeftijd      | omschrijving                                                                            |
      | deze maand - 35 jaar   | 34 of 35 jaar | met alleen maand en jaar van geboortedatum bekend en is de hele maand ouder dan 18 jaar |
      | dit jaar - 35 jaar     | 34 of 35 jaar | met alleen jaar van geboortedatum bekend en is het hele jaar ouder dan 18 jaar          |
      | deze maand - 18 jaar   | 17 of 18 jaar | met alleen maand en jaar van geboortedatum bekend en is of wordt deze maand 18 jaar     |
      | vorige maand - 18 jaar | 18 jaar       | met alleen maand en jaar van geboortedatum bekend en werd vorige maand 18 jaar          |
      | dit jaar - 18 jaar     | 17 of 18 jaar | met alleen jaar van geboortedatum bekend en is of wordt dit jaar 18 jaar                |
      | dit jaar - 19 jaar     | 18 of 19 jaar | met alleen jaar van geboortedatum bekend en is 18 en is of wordt dit jaar 19 jaar       |
      

  Regel: een persoon die in RNI staat ingeschreven met een volledig onbekende geboortedatum krijgt geen gezag geleverd
    # wanneer de geboortedatum is gelijk aan 00000000, is de persoon meerderjarig 

    Scenario: persoon in RNI en geboortedatum is volledig onbekend
      Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de RNI
      * is meerderjarig met de volgende gegevens
      | geboortedatum (03.10) |
      | 00000000              |
      * is in Nederland geboren
      * is niet geëmigreerd geweest
      * heeft 'Ingrid' als ouder 1
      * heeft 'Henk' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon zonder gezag
