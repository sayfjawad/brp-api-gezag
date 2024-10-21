#language: nl

Functionaliteit: 2a.2 - adoptiefouders?
  # Precondities voor deze vraag:
  # | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                                                 |
  # | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                                                                 |
  # | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                                                                |
  # | 1.3a Is minderjarige in buitenland geboren?                            | Nee of geadopteerd met NL akte                                                     |
  # | 1.4 Uitspraak gezag aanwezig?                                          | Nee of door een recente gebeurtenis het gezag toch (weer) van rechtswege verkregen |
  # | 2.1 Hoeveel juridische ouders heeft de minderjarige?                   | 2                                                                                  |
  # | 2a.1 Zijn beide ouders nu met elkaar gehuwd of geregistreerd partners? | Nee, na geboorte kind nooit gehuwd/partners geweest (met elkaar)                   |

  # Hoe achterhalen: Aktenummer (01.81.20) bevat op de 3e positie als akteaanduiding de letter Q. Deze kan in de actuele categorie voorkomen maar ook in een historische categorie omdat er later nog een ander rechtsfeit geregistreerd kan zijn.
  # De akteaanduiding Q kan overschreven zijn met de waarde T als door de ouders een verzoek is ontvangen om de gegevens van de adoptie te verwijderen/overschrijven. In die gevallen is de adoptie niet meer te herkennen op een persoonslijst.
  # Er kan sprake zijn van een adoptie door beide ouders, maar ook een éénouderadoptie is mogelijk. Bijvoorbeeld door de nieuwe partner van de moeder (ook wel genoemd ‘stiefouderadoptie’ of ‘partneradoptie’) of door de vrouwelijke huwelijkspartner of geregistreerd partner van de moeder als deze partner niet van rechtswege automatisch de andere ouder van het kind wordt bij geboorte (geen onbekende donor).
  # Als achterhaald moet worden of beide ouders het kind geadopteerd hebben dan kun je dat achterhalen door de akteaanduiding Q in het aktenummer in zowel categorie 02 als 03 (02.81.20 en 03.81.20). En ook daarbij geldt dat deze akteaanduiding in de historie kan voorkomen,

    Achtergrond:
      Gegeven de persoon 'Ingrid' met burgerservicenummer '000000012'
      * is ingeschreven in de BRP
      * is meerderjarig, niet overleden en staat niet onder curatele
      En de persoon 'Henk' met burgerservicenummer '000000024'
      * is ingeschreven in de BRP
      * is meerderjarig, niet overleden en staat niet onder curatele


  Regel: Als de ouders nooit getrouwd zijn geweest en de minderjarige is geadopteerd hebben beide ouders gezag
    
    Scenario: Ouders zijn nooit met elkaar getrouwd of partners geweest en minderjarige is geadopteerd door beide ouders
      Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * is geadopteerd door 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aktenummer (81.20) |
      | 20221231                                           | 1AQ0102            |
      * is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aktenummer (81.20) |
      | 20221231                                           | 1AQ0102            |
      * beide ouders zijn nooit met elkaar getrouwd geweest en hebben nooit een geregistreerd partnerschap gehad
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

    Scenario: Ouders zijn nooit met elkaar getrouwd of partners geweest en minderjarige is geadopteerd door ouder 2
      Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * heeft 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aktenummer (81.20) |
      | 20221231                                           | 1AA0102            |
      * is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aktenummer (81.20) |
      | 20221231                                           | 1AQ0102            |
      * beide ouders zijn nooit met elkaar getrouwd geweest en hebben nooit een geregistreerd partnerschap gehad
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



  Regel: Als de ouders nooit getrouwd zijn geweest en de minderjarige is niet geadopteerd ligt het gezag bij één ouder of beide afhankelijk van de datum van erkenning

    Scenario: Ouders zijn nooit met elkaar getrouwd of partners geweest, minderjarige is niet geadopteerd maar na geboorte erkend door ouder 2
      Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * heeft 'Ingrid' als ouder 1 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aktenummer (81.20) |
      | gisteren - 17 jaar                                 | 1AA0102            |
      * heeft 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) | aktenummer (81.20) |
      | 20221231                                           | 1AC0102            |
      * beide ouders zijn nooit met elkaar getrouwd geweest en hebben nooit een geregistreerd partnerschap gehad
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                   |
      | type                             | EenhoofdigOuderlijkGezag |
      | minderjarige.burgerservicenummer | 000000036                |
      | ouder.burgerservicenummer        | 000000012                |


  Regel: Als de adoptie is verwijderd wordt bevoegdheid tot 1-hoofdig ouderlijk gezag bepaald

    Scenario: Ouders zijn nooit met elkaar getrouwd of partners geweest en adoptie is verwijderd
      #erkend voor 1-1-2023: OG1 (moeder)

    Scenario: Ouders zijn nooit met elkaar getrouwd of partners geweest en er is geen aktenummer vastgelegd



