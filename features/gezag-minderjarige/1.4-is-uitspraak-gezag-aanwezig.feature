#language: nl

Functionaliteit: 1.4 - is uitspraak gezag aanwezig
  Precondities voor deze vraag:
  | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                                                 |
  | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                                                                 |
  | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                                                                |
  | 1.3a Is minderjarige in buitenland geboren?                            | Nee of geadopteerd met NL akte                                                     |

  Indien er een uitspraak is die invloed heeft op het gezag, is het gezag in principe niet meer van rechtswege bepaald.

  Uitspraak moet hier breed worden gezien. Het betreft alle rechtsfeiten die invloed hebben op het gezag en van de rechtbank
  (Gezagsregister) afkomstig zijn, zoals o.m. de aantekening in het gezagsregister zonder rechterlijke tussenkomst op grond van art. 1:252 BW. Het kan
  voorkomen dat er een uitspraak m.b.t. het gezag is gedaan, maar dat door een bepaalde gebeurtenis op een later moment toch het gezag weer van
  rechtswege is bepaald.

  Op dit moment is het niet mogelijk om te achterhalen of een minderjarige meerderjarig is verklaard. Dit zou bij indicatie gezag kunnen
  worden opgelost door opname van een extra indicatie. Hiervoor ligt een voorstel de letter M van meerderjarig tot te voegen aan de
  mogelijke indicaties bij element indicatie gezag.

  Aanbevelingen
  Meerderjarigheidsverklaring opnemen in indicatie gezag. Uiteraard moet dan ook bij vraag 1.4 en 4a.2, 4a.3 en 4b.1
  rekening gehouden worden met deze indicatie; als de leeftijd van een persoon kleiner is dan 18 jaar en er geen
  meerderjarigheidsverklaring voorligt is de persoon minderjarig.

  Gebruikte velden:
    - Indiatie gezag -> 11.32.10

    Achtergrond:
      Gegeven de persoon 'Rianne' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Adriaan' met burgerservicenummer '000000024'
      * is meerderjarig
      En de persoon 'Babette' met burgerservicenummer '000000036'
      * heeft 'Rianne' als ouder 1
      * heeft 'Adriaan' als ouder 2
      * is in Nederland geboren
      * is ingeschreven in de BRP

  Regel: Geen uitspraak gezag van toepassing

    Scenario: Voor het kind is geen uitspraak gezag aanwezig

    Scenario: Voor het kind een uitspraak gedaan maar is geen indicatie betreft gezag

#    Scenario: Voor het kind een uitspraak gedaan maar de indicatie betreft gezag is nog niet ingegaan
#    Niet ondersteund, issue voor deze functionaliteit: https://github.com/BRP-API/brp-api-gezag/issues/171

#    Scenario: Voor het kind een uitspraak gedaan met een indicatie voor gezag maar deze uitspraak is beëindigd
#    Niet ondersteund, issue voor deze functionaliteit: https://github.com/BRP-API/brp-api-gezag/issues/171

#    Scenario: Voor het kind een uitspraak gedaan met een indicatie voor gezag maar deze uitspraak is gecorrigeerd
#    Niet ondersteund, issue voor deze functionaliteit: https://github.com/BRP-API/brp-api-gezag/issues/171

  Regel: Uitspraak gezag is in onderzoek

    Scenario: Voor het kind een uitspraak gedaan maar deze uitspraak staat op dit moment in onderzoek

    Scenario: Voor het kind een uitspraak gedaan, deze uitspraak stond in onderzoek

  Regel: Uitspraak gezag is aanwezig

    Scenario: Voor het kind een actuele uitspraak gedaan
