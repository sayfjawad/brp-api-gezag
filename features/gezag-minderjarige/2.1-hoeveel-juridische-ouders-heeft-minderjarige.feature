#language: nl

Functionaliteit: 2.1 - hoeveel juridische ouders heeft minderjarige
  Precondities voor deze vraag:
  | 1.1 Staat persoon (minderjarige) als ingezetene in de BRP?             | Ja                                                                                 |
  | 1.2 Is persoon a. minderjarig en b. niet overleden?                    | Ja                                                                                 |
  | 1.3 Is minderjarige naar het buitenland geëmigreerd geweest?           | Nee                                                                                |
  | 1.3a Is minderjarige in buitenland geboren?                            | Nee of geadopteerd met NL akte                                                     |
  | 1.4 Uitspraak gezag aanwezig?                                          | Nee of door een recente gebeurtenis het gezag toch (weer) van rechtswege verkregen |                                                                               |

  Juridisch ouderschap is een belangrijk gegeven voor het bepalen van het gezag van rechtswege. De juridische ouders oefenen veelal het
  gezag uit over de minderjarige van rechtswege.

  Mogelijke uitkomsten:
  1. Twee ouders
  -> Ga naar: 2a1 - Achterhalen gezag bij 2 ouders
  3. Eén ouder
  -> Ga naar: 2b - Achterhalen gezag bij 1 ouder
  4. Geen ouders
  -> Er is (tijdelijk) geen gezag
  5. Onbekende ouders (puntouders)
  -> Gezag is onbekend (in BRP wordt vastgelegd dat er wel een ouder is, maar het is niet bekend wie, zgn Puntouders)

  Hoe achterhalen: Geslachtsnaam Ouder1 komt voor en is ongelijk aan ‘.’. Idem voor geslachtsnaam Ouder2.
  Geslachtsnaam ‘.’ bij een ouder geeft aan dat er nog geen documenten getoond zijn waaruit de juridische ouder afgeleid kan (en mag) worden. Dat zal
  voor 99,99% het geval zijn bij minderjarigen die in het buitenland geboren zijn en die vallen al af in regel 1.3. Komt de geslachtsnaam van de ouder
  niet voor dan is er juridisch geen ouder.

  Regel: Kind heeft twee juridische ouders

    Scenario: Beide ouders staan geregistreerd voor het kind
   
  Regel: Kind heeft een juridisch ouder 

    Scenario: Voor het kind is 1 ouder bekend

  Regel: Kind heeft geen juridische ouders

    Scenario: Voor het kind is geen van de ouders bekend

  Regel: Kind heeft punt ouders 

    Scenario: Kind heeft een punt ouder als ouder 1

    Scenario: Kind heeft een punt ouder als ouder 2

    Scenario: Kind heeft twee puntouders