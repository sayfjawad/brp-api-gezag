#language: nl

Functionaliteit: 2b.1 - is staande huwelijk of partnerschap geboren
  Precondities voor deze vraag:
  | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                                                 |
  | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                                                                 |
  | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                                                                |
  | 1.3a Is minderjarige in buitenland geboren?                            | Nee of geadopteerd met NL akte                                                     |
  | 1.4 Uitspraak gezag aanwezig?                                          | Nee of door een recente gebeurtenis het gezag toch (weer) van rechtswege verkregen |
  | 2.1 Hoeveel juridische ouders heeft de minderjarige?                   | 1                                                                                |

  Voor het achterhalen van het gezag is het van belang om te weten wat de burgerlijke staat was van de juridisch ouder op moment van
  geboorte van het kind.

  Hoe achterhalen: Geboortedatum moet groter/gelijk zijn aan de datum huwelijk/geregistreerd partnerschap op de
  persoonslijst(en) van de ouders. De datum huwelijk/geregistreerd partnerschap komt actueel voor als de ouders actueel nog gehuwd of geregistreerd
  partner zijn. Datum komt in de historie voor als het huwelijk/geregistreerd partnerschap ná de geboorte van het kind ontbonden is.
  Is het huwelijk/geregistreerd partnerschap van de ouders vóór de geboorte ook al ontbonden dan moet dit gezien worden als geen
  huwelijk/geregistreerd partnerschap.

  Testen of het kind niet door een ander erkend is, is hier niet nodig want de vraag gaat uit van één juridische ouder (en door erkenning zou kind twee
  juridische ouders hebben).

  Wat betekend het wanneer een kind is geboren tijdens staande huwelijk of partnerschap?
  1) Ja (staande huwelijk/partnerschap) -> GG: Ouder en echtgenoot/partner hebben gezamenlijk het gezag (1:253sa BW):
  a. meemoeders: kind tijdens huwelijk/gp geboren, is niet erkend en er is geen sprake van een verklaring onbekende donor of
  b. kind vóór 1 april 2014 geboren tijdens geregistreerd partnerschap van paren van verschillend geslacht en het kind is niet erkend.
  2)
  Nee -> OG1: Ouder heeft alleen het gezag (1:253b lid 1 BW) (Niet alleen de moeder uit wie het kind is geboren, maar ook in het geval van een
  adoptiefouder. Het maakt hier dus niet uit wie die ene juridische ouder is.) →OG1; eenhoofdig gezag

  Opmerkingen
  Bij deze vraag kan een erkenning nietig verklaard zijn en heeft de minderjarige nog maar één ouder. Echter, als gekeken wordt of
  de overgebleven ouder een relatie had tijdens de geboorte van de minderjarige kan het voorkomen dat de partner van de ouder
  uitgerekend de persoon is waar de nietig verklaarde erkenning betrekking op heeft. In zo’n geval heeft de partner geen gezag over de
  minderjarige dus geen gezamenlijk gezag. De huidige afleiding voorziet hier niet in.

  Aanbevelingen
  Voorstel is ook te kijken naar een nietig verklaarde erkenning in de aktenummers van de ouders. Daarmee wordt het antwoord Nee
  aangevuld met “of nietig verklaarde erkenning” en krijgt de partner met nietig verklaarde erkenning geen gezag.

  Gebruikte velden:
  - Geboortedatum persoon -> 01.03.10
  - Burgerservicenummer ouder 1 -> 02.01.20
  - Geslachtsnaam ouder 1 -> 02.02.40
  - Aktenummer ouder 1 -> 02.81.20
  - Aktenummer ouder 1 historisch -> 52.81.20
  - Burgerservicenummer ouder 2 -> 03.01.20
  - Geslachtsnaam ouder 2 -> 03.02.40
  - Aktenummer ouder 2 -> 03.81.20
  - Aktenummer ouder 2 historisch -> 53.81.20
  - Burgerservicenummer huwelijk/geregistreerd partnerschap -> 05.01.20
  - Start datum huwelijk/geregistreerd partnerschap -> 05.06.10
  - Eind datum huwelijk/geregistreerd partnerschap -> 05.07.10
  - Eind reden huwelijk/geregistreerd partnerschap -> 05.07.40
  - Reden opschorting bijhouding -> 07.67.20

  Achtergrond:
    Gegeven de persoon 'Saskia' met burgerservicenummer '000000012'
    * is meerderjarig
    En de persoon 'Maarten' met burgerservicenummer '000000024'
    * is meerderjarig
    En de persoon 'Rachel' met burgerservicenummer '000000036'
    * heeft 'Saskia' als ouder 1
    * is geboren op 1-12-2022
    * is in Nederland geboren
    * is ingeschreven in de BRP

  Regel: Kind is geboren met ouders die op dit moment getrouwd zijn

  Scenario: Huwelijk / partnerschap van ouders is nooit ontbonden geweest er is sprake van GezamenlijkGezag
    Gegeven 'Saskia' en 'Maarten' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20100401                                                           |
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde           |
      | type                             | GezamenlijkGezag |
      | minderjarige.burgerservicenummer | 000000036        |
      | ouder.burgerservicenummer        | 000000012        |
      | derde.burgerservicenummer        | 000000024        |

  Scenario: Huwelijk / partnerschap van ouders heeft plaatsgevonden nadat het kind is geboren er is sprake van EenhoofdigOuderlijkGezag
    Gegeven 'Saskia' en 'Maarten' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20231201                                                           |
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

  Regel: Kind is geboren met ouders die niet langer getrouwd zijn

  Scenario: Huwelijk / partnerschap van ouders was ontbonden voor dat het kind werd geboren er is sprake van EenhoofdigOuderlijkGezag
    Gegeven 'Saskia' en 'Maarten' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20100101                                                           |
    Gegeven 'Saskia' en 'Maarten' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | 20221101                                                     |
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

  Scenario: Huwelijk / partnerschap van ouders is ontbonden nadat het kind werd geboren er is sprake van GezamenlijkGezag
    Gegeven 'Saskia' en 'Maarten' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20100101                                                           |
    Gegeven 'Saskia' en 'Maarten' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | 20221230                                                     |
    Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
    En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde           |
      | type                             | GezamenlijkGezag |
      | minderjarige.burgerservicenummer | 000000036        |
      | ouder.burgerservicenummer        | 000000012        |
      | derde.burgerservicenummer        | 000000024        |

  Regel: Kind is geboren met ouders die nooit getrouwd geweest zijn

  Scenario: Kind werd geboren zonder huwelijk / partnerschap tussen ouders er is sprake van EenhoofdigOuderlijkGezag
    Gegeven 'Saskia' en 'Maarten' zijn met elkaar gehuwd met de volgende gegevens
      | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10) |
      | 20100101                                                           |
    Gegeven 'Saskia' en 'Maarten' zijn gescheiden met de volgende gegevens
      | datum ontbinding huwelijk/geregistreerd partnerschap (07.10) |
      | 20221101                                                     |
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
