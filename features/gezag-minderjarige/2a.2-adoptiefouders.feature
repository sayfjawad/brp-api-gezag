#language: nl

Functionaliteit: 2a.2 - adoptief ouders
  Precondities voor deze vraag:
  | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                                                 |
  | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                                                                 |
  | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                                                                |
  | 1.3a Is minderjarige in buitenland geboren?                            | Nee of geadopteerd met NL akte                                                     |
  | 1.4 Uitspraak gezag aanwezig?                                          | Nee of door een recente gebeurtenis het gezag toch (weer) van rechtswege verkregen |
  | 2.1 Hoeveel juridische ouders heeft de minderjarige?                   | 2                                                                                  |
  | 2a.1 Zijn beide ouders nu met elkaar gehuwd of geregistreerd partners? | Nee, na geboorte kind nooit gehuwd/partners geweest (met elkaar)                   |

  Wanneer een kind is geadopteerd zowel uit buitenland als uit Nederland kan dit in verschillende situaties zijn die invloed hebben op het gezag over het kind.
  
  In het geval van adoptiefouders is het gezag lastig te achterhalen. In de BRP wordt geen verschil in geslacht aangegeven. 
  Dit bemoeilijkt het afleiden van het gezag bij adoptieouders van hetzelfde geslacht, vooral bij vrouwen (moeder en meemoeder).

  Mogelijke uitkomsten:
  1) Twee adoptiefouders (man/vrouw, man/man, vrouw/vrouw)
    a. gehuwd / geregistreerd partnerschap (1:251 BW (huwelijk) / 1:251aa (geregistreerd partnerschap) er is gezamenlijk ouderlijk gezag
    b. niet gehuwd of partnerschap (1:229 lid 4 BW) er is gezamenlijk ouderlijk gezag
  2) Eén adoptieouder:
    a. gehuwd/geregistreerd partnerschap (met een man/of een vrouw die niet de geboortemoeder of de adoptiemoeder is, 1:245 BW 
       alleen de adoptie-ouder (m/v) heeft het gezag er is sprake van eenhoofdig gezag

       Uitleg: 
       Er zijn geen 2 juridische ouders, dus er ontstaat geen gezamenlijk gezag van rechtswege, ook niet op grond van 1:253sa BW, omdat het
       kind niet staande huwelijk/geregistreerd partnerschap is geboren. Voorbeeld: een echtpaar wil een kind adopteren, maar de man óf de vrouw voldoet niet aan de voorwaarden: partner krijgt geen beginseltoestemming of is te oud. 
       Alleen de andere echtgenoot/partner kan dan dopteren en dan is er sprake van één juridische ouder, met eenhoofdig gezag.
    b. ongehuwd/geen geregistreerd partnerschap er is sprake van eenhoofdig gezag
        
       Uitleg: er is maar 1 juridische ouder: 1:245 BW Zie ook het vorige voorbeeld, maar dan bij een ongehuwd/geen geregistreerd partnerschap.
  3) Stiefouderadoptie/partneradoptie:
       Er is dan altijd maar één adoptant: de partner. Het kind heeft al één ouder: de geboortemoeder of de adoptieouder.

       Zowel bij gehuwd/partnerschap als ongehuwd/geen partnerschap ontstaat bij adoptie door de stiefouder van het kind van de geboortemoeder 
       gezamenlijk gezag: de moeder had het al en de adoptant krijgt het door de adoptie (1:229 lid 4 BW) à gezamenlijk ouderlijk gezag
  4) Geen adoptiefouders 
       De moeder uit wie het kind is geboren heeft in deze situatie alleen het gezag (1:253b lid 1 BW)
       D.w.z. als voor beide ouders geldt dat ze geen adoptiefouder zijn. Mogelijke situaties:
       - Moeder-Vader (Tot 1-1-2023: Vader heeft kind erkend, maar geen gezamenlijk gezag aangevraagd)
       - Moeder-Moeder (Tot 1-1-2023: Meemoeder heeft het kind erkend, maar geen gezamenlijk gezag aangevraagd) -> Let op: Dit is nu niet of
         lastig te achterhalen!
       - Vader-Vader (Deze situatie kan hier niet voorkomen, want dat kan alleen via adoptie)

  Tot 1 april 2014 kon een meemoeder het kind van haar zwangere partner alleen adopteren. Vanaf 1 april 2014 kan de meemoeder kiezen voor erkenning of
  adoptie. Erkenning kan zowel voor (erkenning ongeboren vrucht) als na de geboorte. Een verzoek tot adoptie kan ook voor de geboorte worden ingediend. De
  adoptie zal pas na de geboorte door de rechtbank worden uitgesproken, maar werkt wel terug tot de geboorte. Beide moeders hebben dan als datum ingang
  familierechtelijke betrekking de geboortedatum van het kind.

  Hoe achterhalen: 
  Aktenummer bevat op de 3e positie als akteaanduiding de letter Q. Deze kan in de actuele categorie voorkomen maar ook in een historische categorie omdat er later nog een ander rechtsfeit geregistreerd kan zijn.
  De akteaanduiding Q kan overschreven zijn met de waarde T als door de ouders een verzoek is ontvangen om de gegevens van de adoptie te verwijderen/overschrijven. 
  In die gevallen is de adoptie niet meer te herkennen op een persoonslijst.

  Er kan sprake zijn van een adoptie door beide ouders, maar ook een éénouderadoptie is mogelijk. Bijvoorbeeld door de nieuwe partner van de moeder (ook wel genoemd ‘stiefouderadoptie’ of ‘partneradoptie’) of 
  door de vrouwelijke huwelijkspartner of geregistreerd partner van de moeder als deze partner niet van rechtswege automatisch de andere ouder van het kind wordt bij geboorte (geen onbekende donor).

  Om te bepalen of beide ouders het kind hebben geadopteerd kan de akteaanduiding Q in het aktenummer van beide ouders worden gecontrolleerd.
  Ook hierbij geldt dat deze akteaanduiding in de historie kan voorkomen van de persoon.

  Gebruikte velden:
    - Aktenummer van de persoon -> 01.81.20
    - Aktenummer van ouder 1 -> 02.81.20
    - Aktenummer van ouder 2 -> 03.81.20

    Achtergrond:
      Gegeven de persoon 'Ingrid' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Henk' met burgerservicenummer '000000024'
      * is meerderjarig
      En de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is in Nederland geboren
      * is minderjarig

  Regel: Een kind is geadopteerd door twee personen
    
    Scenario: het kind is geadopteerd door twee personen die ongehuwd zijn/geen partnerschap hebben er is sprake van TweehoofdigOuderlijkGezag
      Gegeven persoon 'Jaimy'
      * is geadopteerd door 'Ingrid' als ouder 1
      * is geadopteerd door 'Henk' als ouder 2
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

    Scenario: het kind is geadopteerd door twee personen die met elkaar gehuwd zijn er is sprake van TweehoofdigOuderlijkGezag
      Gegeven 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      Gegeven persoon 'Jaimy'
      * is geadopteerd door 'Ingrid' als ouder 1
      * is geadopteerd door 'Henk' als ouder 2
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

    Scenario: het kind is geadopteerd door twee personen die gehuwd waren bij de adoptie maar nu zijn gescheiden er is sprake van TweehoofdigOuderlijkGezag
      Gegeven 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      Gegeven persoon 'Jaimy'
      * is geadopteerd door 'Ingrid' als ouder 1
      * is geadopteerd door 'Henk' als ouder 2
      Gegeven 'Ingrid' en 'Henk' zijn gescheiden
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

  Regel: Een kind is geadopteerd door één persoon

    Scenario: het kind is geadopteerd door een alleenstaand persoon als ouder 1 er is sprake van EenhoofdigOuderlijkGezag
      Gegeven persoon 'Jaimy'
      * is geadopteerd door 'Ingrid' als ouder 1
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | EenhoofdigOuderlijkGezag  |
      | minderjarige.burgerservicenummer | 000000036                 |
      | ouder.burgerservicenummer        | 000000012                 |

    Scenario: het kind is geadopteerd door een alleenstaand persoon als ouder 2 er is sprake van EenhoofdigOuderlijkGezag
      Gegeven persoon 'Jaimy'
      * is geadopteerd door 'Henk' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | EenhoofdigOuderlijkGezag  |
      | minderjarige.burgerservicenummer | 000000036                 |
      | ouder.burgerservicenummer        | 000000024                 |

    Scenario: het kind is geadopteerd door een persoon die is gehuwd er is sprake van EenhoofdigOuderlijkGezag
      Gegeven 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | morgen - 5 jaar                                                    |
      Gegeven persoon 'Jaimy'
      * is geadopteerd door 'Ingrid' als ouder 1
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                    |
      | type                             | EenhoofdigOuderlijkGezag  |
      | minderjarige.burgerservicenummer | 000000036                 |
      | ouder.burgerservicenummer        | 000000012                 |

  Regel: Een kind heeft al een ouder en wordt geadopteerd door een persoon

    Scenario: het kind wordt geadopteerd door de huwelijks partner van de moeder er is sprake van TweehoofdigOuderlijkGezag
      Gegeven 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | vandaag - 1 jaar                                                   |
      Gegeven persoon 'Jaimy'
      * heeft 'Ingrid' als ouder 1
      Gegeven 'Jaimy' is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | vorige maand                                       |
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

    Scenario: het kind wordt geadopteerd door de partner van de moeder zonder huwelijk/partnerschap er is sprake van TweehoofdigOuderlijkGezag
      Gegeven persoon 'Jaimy'
      * heeft 'Ingrid' als ouder 1
      Gegeven 'Jaimy' is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | vorige maand                                       |
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

    Scenario: het kind wordt geadopteerd door de huwelijks partner van de moeder en de partner en moeder zijn later weer gescheiden er is sprake van TweehoofdigOuderlijkGezag
      Gegeven 'Ingrid' en 'Henk' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | vandaag - 1 jaar                                                   |
      Gegeven persoon 'Jaimy'
      * heeft 'Ingrid' als ouder 1
      Gegeven 'Jaimy' is geadopteerd door 'Henk' als ouder 2 met de volgende gegevens
      | datum ingang familierechtelijke betrekking (62.10) |
      | vorige maand                                       |
      Gegeven 'Ingrid' en 'Henk' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | gisteren                                                     |
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