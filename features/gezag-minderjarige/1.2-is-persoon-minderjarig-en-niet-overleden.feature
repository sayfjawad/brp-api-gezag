#language: nl

Functionaliteit: 1.2 - Is persoon minderjarig en niet overleden?
  Precondities voor deze vraag:
  | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                     |

  Persoon moet minderjarig zijn, anders is er geen sprake van gezag (artikel 1:245 BW).
  Als persoon is overleden als minderjarige, wordt het achterhalen van het gezag een stuk ingewikkelder, omdat je dan rekening moet houden met
  oudere wetgeving. Voor nu ondersteund de gezagmodule gezag bepaling voor overleden personen niet.

  Volgens de Nederlandse Wet (artikel 1:233 BW) is iemand minderjarig als geldt:
  • Leeftijd van achttien jaar nog niet is bereikt.
  • Niet meerderjarig verklaard (artikel 1:253ha BW).

  Als minderjarige in Nederland woont, in Nederland zijn gewone verblijfplaats heeft, is het Nederlands familierecht van toepassing en dus de
  Nederlandse wettelijke voorschriften t.a.v. minder-/ meerderjarigheid en gezag. Een minderjarige volgens Nederlandse wetgeving, kan wel
  meerderjarigheid uit het buitenland hebben meegenomen. Deze gevallen worden uitgesloten door vraag 1.3.

  Voor inwerkingtreding van de Wet Tegengaan Huwelijksdwang op 5-12-2015 kon iemand meerderjarig worden door afsluiten van een huwelijk of
  geregistreerd partnerschap. Dit is bijna 5 jaar geleden. Iemand die nu 17 jaar is zou dan op 12 jarige leeftijd of eerder getrouwd moeten zijn, wil dit
  nog van toepassing zijn hier wordt daarom geen rekening mee gehouden.

  Hoe te achterhalen:
  De leeftijd van de persoon moet 17 jaar of minder zijn en de persoon moet niet zijn overleden.

  Aanbeveling
  Er is nog geen oplossing voor de meerderjarigheidsverklaring. Het idee is dat persoon de letter M krijgt bij indicatie
  gezag. Zodra dit het geval is moet deze informatie worden meegenomen in de beoordeling en bij aanwezigheid van deze indicatie
  wordt de persoon behandeld als meerderjarige.

  Gebruikte velden:
  - Indicatie gezag -> 11.32.10

  Achtergrond:
    Gegeven de persoon 'Laura' met burgerservicenummer '000000012'
    * is meerderjarig
    En de persoon 'Ludo' met burgerservicenummer '000000024'
    * is meerderjarig
    En 'Laura' en 'Ludo' zijn met elkaar gehuwd
    En de persoon 'Linda' met burgerservicenummer '000000036'
    * is ingeschreven in de BRP
    * is niet geëmigreerd geweest
    * is in Nederland geboren
    * heeft 'Laura' als ouder 1
    * heeft 'Ludo' als ouder 2

  Regel: gezag wordt niet bepaald wanneer het kind overleden is

    Scenario: Wanneer gezag wordt opgevraagd van een overleden kind, wordt geen gezag teruggeven.
      Gegeven persoon 'Linda'
      * is minderjarig
      En bijhouding van de persoonsgegevens van 'Linda' is opgeschort met de volgende gegevens
      | datum opschorting bijhouding (67.10) |
      | gisteren                             |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon geen gezag

  Regel: gezag wordt niet bepaald wanneer het kind meerderjarig is

    Scenario: Wanneer gezag wordt opgevraagd van een kind die meerderjarig is, wordt geen gezag teruggeven.
      Gegeven persoon 'Linda'
      * is meerderjarig
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon geen gezag

  Regel: gezag wordt niet bepaald wanneer leeftijd onbekend is

    Scenario: Wanneer gezag wordt opgevraagd van een kind waarvan leeftijd onbekend is, wordt geen gezag teruggeven.
      Gegeven heeft de volgende gegevens
      | geboortedatum (03.10) |
      | 00000000              |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon geen gezag

  Regel: het bepalen van gezag gaat verder wanneer het kind minderjarig is

    Scenario: Wanneer gezag wordt opgevraagd van een kind die minderjarig is, wordt tweehoofdig ouderlijk gezag teruggeven.
      Gegeven persoon 'Linda'
      * is minderjarig
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
