#language: nl

Functionaliteit: 1.1 - Staat persoon (minderjarige) als ingezetene in de BRP?

  De persoon (minderjarige) moet voorkomen in de BRP aangezien dit de bron is voor
  het achterhalen van het gezag. Een niet ingezeten kind staat wel de RNI (ook
  onderdeel van de BRP), maar hiervan zijn de gegevens (mogelijk) niet actueel. Een
  gezagsmutatie wordt bijvoorbeeld niet bijgewerkt in de RNI.

  Een persoon is "ingezetene" en "ingeschreven in de BRP" wanneer deze staat ingeschreven
  in een Nederlandse gemeente. In dat geval gaat de gezagbepaling 
  verder met vraag 1.2.

  Een persoon is "niet ingezetene" wanneer deze staat ingeschreven in het RNI (Register Niet 
  Ingezetenen). In dat geval is het gezag niet te bepalen.


    Achtergrond:
      Gegeven de persoon 'Gerda' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Bert' met burgerservicenummer '000000024'
      * is meerderjarig
      En 'Gerda' en 'Bert' zijn met elkaar gehuwd
      En de persoon 'Zoe' met burgerservicenummer '000000036'
      * is minderjarig
      * is in Nederland geboren
      * heeft 'Gerda' als ouder 1
      * heeft 'Bert' als ouder 2


  Regel: Als de persoon staat ingeschreven in het RNI kan het gezag van de persoon niet worden bepaald

    Scenario: minderjarige staat ingeschreven in RNI
      Gegeven persoon 'Zoe' is ingeschreven in de RNI
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

    Scenario: minderjarige staat ingeschreven in een Nederlandse gemeente
      Gegeven persoon 'Zoe' is ingeschreven in de BRP
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


  Regel: onderzoek op gemeente van inschrijving leidt niet tot uitval

   Scenario: minderjarige staat ingeschreven in Nederlandse gemeente en de verblijfplaats staat in onderzoek
      Gegeven persoon 'Zoe' is ingeschreven in de BRP
      * de verblijfplaats staat in onderzoek
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
