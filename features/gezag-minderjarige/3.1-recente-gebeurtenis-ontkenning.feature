#language: nl

Functionaliteit:  3.1 - Is er door een recente gebeurtenis - ontkenning vaderschap - het gezag toch (weer) van rechtswege ondanks dat er eerder een uitspraak is gedaan?


    Achtergrond:
      Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * heeft een ouder 'Ingrid' met burgerservicenummer '000000012'
      * heeft een ouder 'Henk' met burgerservicenummer '000000024'
      * 'Ingrid' en 'Henk' zijn met elkaar gehuwd
      * beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele


  Regel: Als vaderschap is ontkend of de erkenning is vernietigd en er was een uitspraak gezag voor die ouder, wordt het gezag van rechtswege bepaald

    Abstract Scenario: er is uitspraak gezag <indicatie gezag> en erkenning door ouder 1 is ontkend of vernietigd
      # Eenouderlijk gezag voor ouder 2

      Voorbeelden:
      | indicatie gezag |
      | 1               |
      | 12              |

    Abstract Scenario: er is uitspraak gezag <indicatie gezag> en erkenning door ouder 2 is ontkend of vernietigd
      # Eenouderlijk gezag voor ouder 1

      Voorbeelden:
      | indicatie gezag |
      | 2               |
      | 12              |

    Abstract Scenario: er is uitspraak gezag 1 en erkenning door ouder 2 is ontkend of vernietigd
      # Eenouderlijk gezag voor ouder 1

    Scenario: er is uitspraak gezag voogdij en erkenning door ouder 2 is ontkend of vernietigd
      # gezag is voogdij
