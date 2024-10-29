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
  Ingezetenen). In dat gezal is het gezag niet te bepalen.


    Achtergrond:
      Gegeven de persoon 'Gerda' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Aart' met burgerservicenummer '000000024'
      * is meerderjarig
      En 'Gerda' en 'Aart' zijn met elkaar gehuwd
      En de persoon 'Zoe' met burgerservicenummer '000000036'
      * is minderjarig
      * is in Nederland geboren
      * heeft 'Gerda' als ouder 1
      * heeft 'Aart' als ouder 2


  Regel: Als de gemeente van inschrijving is RNI kan het gezag van de persoon niet worden bepaald
    Een persoon staat in de RNI ingeschreven wanneer gemeente van inschrijving (09.10) gelijk is aan 1999.

    Dit wordt bepaald in tabel lo3_pl_verblijfplaats, kolom inschrijving_gemeente_code, waarbij volg_nr = 0.
    Wanneer inschrijving_gemeente_code = 1999 is gezag niet te bepalen.

    Scenario: minderjarige staat ingeschreven in RNI
      Gegeven persoon 'Zoe' heeft de volgende rijen in tabel lo3_pl_verblijfplaats
      | volg_nr | inschrijving_gemeente_code |
      | 0       | 1999                       |
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

    Scenario: minderjarige staat ingeschreven in RNI en stond daarvoor ingeschreven in een Nederlandse gemeente
      Gegeven persoon 'Zoe' heeft de volgende rijen in tabel lo3_pl_verblijfplaats
      | volg_nr | inschrijving_gemeente_code |
      | 0       | 1999                       |
      | 1       | 0518                       |
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


  Regel: Als gemeente van inschrijving is ongelijk aan RNI kan het gezag worden bepaald
    Een persoon staat in de BRP ingeschreven wanneer gemeente van inschrijving (09.10) ongelijk is aan 1999.

    Dit wordt bepaald in tabel lo3_pl_verblijfplaats, kolom inschrijving_gemeente_code, waarbij volg_nr = 0.
    Wanneer inschrijving_gemeente_code ≠ 1999 is gezag niet te bepalen.

    Scenario: minderjarige staat ingeschreven in Nederlandse gemeente
      Gegeven persoon 'Zoe' heeft de volgende rijen in tabel lo3_pl_verblijfplaats
      | volg_nr | inschrijving_gemeente_code |
      | 0       | 0518                       |
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

    Scenario: minderjarige staat ingeschreven in Nederlandse gemeente en stond daarvoor ingeschreven in het RNI
      Gegeven persoon 'Zoe' heeft de volgende rijen in tabel lo3_pl_verblijfplaats
      | volg_nr | inschrijving_gemeente_code |
      | 0       | 0518                       |
      | 1       | 1999                       |
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

    Abstract Scenario: minderjarige staat ingeschreven in Nederlandse gemeente en <omschrijving> staat in onderzoek
      Gegeven persoon 'Zoe' heeft de volgende rijen in tabel lo3_pl_verblijfplaats
      | volg_nr | inschrijving_gemeente_code | onderzoek_gegevens_aand   | onderzoek_start_datum |
      | 0       | 0518                       | <aanduiding in onderzoek> | gisteren              |
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

      Voorbeelden:
      | aanduiding in onderzoek | omschrijving                        |
      | 080000                  | hele categorie verblijfplaats       |
      | 080900                  | groep gemeente                      |
      | 080910                  | gemeente van inschrijving           |
      | 089999                  | vastgesteld verblijft niet op adres |
