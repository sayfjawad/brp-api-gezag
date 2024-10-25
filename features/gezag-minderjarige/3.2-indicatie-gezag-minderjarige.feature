#language: nl

Functionaliteit: 3.2 - Achterhalen gezag na uitspraak
  # Precondities voor deze vraag:
  # | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                                                 |
  # | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                                                                 |
  # | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                                                                |
  # | 1.3a Is minderjarige in buitenland geboren?                            | Nee of geadopteerd met NL akte                                                     |
  # | 1.4 Uitspraak gezag aanwezig?                                          | Ja                                                                                 |
  # | 3.1 Is er sprake van een recente gebeurtenis?                          | Nee                                                                                 |

  # Uitspraak moet hier breed worden gezien. Het betreft alle rechtsfeiten die invloed hebben op het gezag en van de rechtbank (Gezagsregister)
  # afkomstig zijn.
  
  # Wat is de indicatie gezag minderjarig?
  # Indicatie gezag minderjarige geeft aan of er sprake is van ouderlijk gezag (éénhoofdig of gezamenlijk ouderlijk gezag), gezamenlijk gezag
  # van een ouder en een niet ouder of van voogdij (één of twee niet-ouders).
  # Indicatie gezag minderjarige wordt alleen gevuld op basis van een verkregen uittreksel uit het Gezagsregister van de rechtbank en is dus altijd 
  # tot stand gekomen vanuit een reschtsfeit.

  # Hoe achterhalen:
  # Ouderlijk gezag tweehoofdig: indicatie gezag van de gezagsverhouding (11.32.10) bevat de waarde ‘12’.
  # Ouderlijk gezag eenhoofdig: indicatie gezag van de gezagsverhouding (11.32.10) bevat de waarde ‘1’ of ’2’.
  # Gezamenlijk gezag: indicatie gezag van de gezagsverhouding (11.32.10) bevat de waarde ‘1D’ of ’2D’.
  # Voogdij (één of twee): indicatie gezag van de gezagsverhouding (11.32.10) bevat de waarde ‘D’.

  # Opmerkingen
  # • Er wordt niet (altijd) gecontrolleerd of een opgenomen gezagsverhouding is gecorrigeerd, gewijzigd, is ingegaan of is beindigd.
  # -> hiervoor is issue https://github.com/BRP-API/brp-api-gezag/issues/171 aangemaakt

    Achtergrond:
      Gegeven de persoon 'Klaartje' met burgerservicenummer '000000012'
      * is meerderjarig, niet overleden en staat niet onder curatele
      En de persoon 'Arjan' met burgerservicenummer '000000024'
      * is meerderjarig, niet overleden en staat niet onder curatele
      En de persoon 'Nathan' met burgerservicenummer '000000036'
      * is minderjarig
      * is in Nederland geboren
      * is ingeschreven in de BRP
      * heeft 'Klaartje' als ouder 1
      * heeft 'Arjan' als ouder 2

  Regel: Uitspraak gezag met indicatie dat beide ouders gezag hebben is opgenomen vanuit het Gezagsregister

    Scenario: Vader ontkend het kind te erkennen er is sprake van EenhoofdigOuderlijkGezag 
      Gegeven voor 'Nathan' is een gerechtelijke uitspraak over het gezag gedaan met de volgende gegevens
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 12                                   | 20230101                        |
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

  # FUTURE_WORK: geldigheid van gezagsverhouding controleren

  Regel: Uitspraak gezag met indicatie dat één ouder, in dit geval de moeder gezag heeft, is opgenomen vanuit het Gezagsregister

    Scenario: Vader ontkend het kind te erkennen er is sprake van EenhoofdigOuderlijkGezag 
      Gegeven voor 'Nathan' is een gerechtelijke uitspraak over het gezag gedaan met de volgende gegevens
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 1                                    | 20230101                        |
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
