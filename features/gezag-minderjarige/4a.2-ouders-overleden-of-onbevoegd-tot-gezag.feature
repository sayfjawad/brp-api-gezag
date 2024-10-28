#language: nl

Functionaliteit: 3.2 - Achterhalen gezag na uitspraak
  # Precondities voor deze vraag:
  # | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                                                 |
  # | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                                                                 |
  # | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                                                                |
  # | 1.3a Is minderjarige in buitenland geboren?                            | Nee of geadopteerd met NL akte                                                     |
  # | 1.4 Is uitspraak gezag aanwezig                                        | Nee                                                                                |
  # | 2.1 Hoeveel juridische ouders heeft minderjarigen                      | Twee_ouders                                                                        |
  # | 2a.1 Zijn juridische ouders nu met elkaar gehuwd of partners           | Ja                                                                                 |

  # Onbevoegd tot het gezag zijn minderjarigen, zij die onder curatele zijn gesteld en zij wier geestvermogens zodanig zijn gestoord, om het
  # gezag uit te kunnen oefenen (1:246 BW). Overleden ouder spreekt voor zich.
  # Uit de wet Gezamenlijk gezag door erkenning volgt dat beide ouders het gezag hebben, tenzij sprake is van de uitzonderingen genoemd in art. 1:251b
  # BW.
  
  # Mogelijke uitkomsten:
  # 1) Nee
  # -> OG2: Beide ouders hebben gezamenlijk het ouderlijk gezag
  # 2) Ja, één van beide ouders
  # -> OG1: De andere ouder heeft alleen het gezag (1:253f BW bij overlijden of 1:253q lid 1 BW bij onbevoegdheid, of art 251b BW)
  # 3) Ja, beide ouders
  # -> G: Er is (tijdelijk) geen gezag (1:253g lid 1 BW bij overlijden of 1:253q lid 2 BW bij onbevoegdheid)

  # Op het moment dat de moeder weer bevoegd wordt tot het gezag, verkrijgt zij van rechtswege het gezag, tenzij op dat tijdstip een ander met het
  # gezag is belast (art. 1:253b lid 2 BW). Dit geldt ook o.g.v. art. 1:253q lid 1 BW.
  # Let op: De kinderrechter kan een minderjarige moeder o.b.v. artikel 1:253ha lid 1 BW meerderjarig verklaren. Dit is nu niet in de BRP te achterhalen,
  # dit is wel zichtbaar als aantekening in het Gezagsregister.

  # Hoe achterhalen:
  # Niet overleden: op de persoonslijst van de ouder(s) mag de reden opschorting (07.67.20) niet voorkomen of moet ongelijk zijn aan ‘O’.
  # Meerderjarig: de geboortedatum van de ouder(s) (02.03.10 en 03.03.10) moet kleiner zijn dan de geboortedatum van het kind (01.03.10) – 18 jaar.
  # Niet onder curatele: op de persoonslijst van de ouder(s) mag de indicatie curatele (11.33.10) niet voorkomen.
  # Het wordt in de BRP niet geregistreerd dat de geestelijke vermogens van een ouder gestoord zijn.
  # Uitzondering genoemd in de wet Gezamenlijk gezag door erkenning: De moeder en de persoon die een kind heeft erkend oefenen het gezag over hun
  # kind gezamenlijk uit, tenzij
  # a. een voogd met de voogdij over het kind is belast;
  # b. de voorziening in het gezag over het kind is komen te ontbreken; of
  # c. de ouder die het gezag heeft, dit gezamenlijk met een ander dan een ouder uitoefent.

  # De moeder oefent het gezag over haar kind alleen uit, indien uit de akte, bedoeld in artikel 203, eerste lid, onderdeel a of b, blijkt dat:
  # a. de moeder en de erkenner hebben verklaard dat het gezag alleen door de moeder wordt uitgeoefend; of
  # b. de rechtbank vervangende toestemming voor de erkenning heeft verleend als bedoeld in artikel 204, derde en vierde lid.
  # De ambtenaar van de burgerlijke stand zendt onverwijld een afschrift of uittreksel van de akte aan de griffier ter aantekening van de verklaring,
  # bedoeld onder a, of de erkenning met vervangende toestemming, bedoeld onder b, in het register, bedoeld in artikel 244.

  # Opmerkingen
  # • Vraag 4a.2: Het antwoord op de vraag 4a.2 is mogelijk te beperkt om gezag juist te bepalen. de gevolgen van overlijden en curatele
  # voor gezag zijn verschillend. Of komt de situatie van curatele eigenlijk niet voor omdat er dan al een uitspraak gezag voorligt?

    Achtergrond:
      Gegeven de persoon 'Trudy' met burgerservicenummer '000000012'
      * is meerderjarig, niet overleden en staat niet onder curatele
      En de persoon 'Kess' met burgerservicenummer '000000024'
      * is meerderjarig, niet overleden en staat niet onder curatele

  Regel: 

    Scenario: 
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

