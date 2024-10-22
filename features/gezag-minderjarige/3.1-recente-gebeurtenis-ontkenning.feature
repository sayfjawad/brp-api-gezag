#language: nl

Functionaliteit:  3.1 - Is er door een recente gebeurtenis - ontkenning vaderschap - het gezag toch (weer) van rechtswege ondanks dat er eerder een uitspraak is gedaan?


    Achtergrond:
      Gegeven de persoon 'Nelly' met burgerservicenummer '000000012'
      * is meerderjarig, niet overleden en staat niet onder curatele
      En de persoon 'Johnnie' met burgerservicenummer '000000024'
      * is meerderjarig, niet overleden en staat niet onder curatele
      En 'Nelly' en 'Johnnie' zijn met elkaar gehuwd
      En de persoon 'Kees' met burgerservicenummer '000000036'
      * is ingeschreven in de BRP
      * is minderjarig
      * is niet geëmigreerd geweest
      * is in Nederland geboren
      * heeft 'Nelly' als ouder 1
      * heeft 'Johnnie' als ouder 2


  Regel: Als vaderschap is ontkend of de erkenning is vernietigd en er was een uitspraak gezag voor die ouder, wordt het gezag van rechtswege bepaald
    # Dit is het geval wanneer:
    # * indicatie gezag is '12'
    # * (ten minste) een van de ouders is verwijderd of is een 'puntouder'

    Abstract Scenario: er is uitspraak gezag voor ouder 1 en ouder 2 en erkenning door ouder <ouder> is ontkend of vernietigd
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | 12                                   | morgen - 10 jaar                |
      En zijn van ouder <ouder> de volgende gegevens <soort wijziging>
      | naam                                               | waarde       |
      | burgerservicenummer (01.20)                        |              |
      | geslachtsnaam (02.40)                              | <naam ouder> |
      | geboortedatum (03.10)                              |              |
      | datum ingang familierechtelijke betrekking (62.10) |              |
      | aktenummer (81.20)                                 | 1AF0100      |
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
      | ouder.burgerservicenummer        | <ouder bsn>              |

      Voorbeelden:
      | ouder | naam ouder | soort wijziging | ouder bsn |
      | 1     |            | gewijzigd       | 000000024 |
      | 1     | .          | gewijzigd       | 000000024 |
      | 1     |            | gecorrigeerd    | 000000024 |
      | 1     | .          | gecorrigeerd    | 000000024 |
      | 2     |            | gewijzigd       | 000000012 |
      | 2     | .          | gewijzigd       | 000000012 |
      | 2     |            | gecorrigeerd    | 000000012 |
      | 2     | .          | gecorrigeerd    | 000000012 |
 
    Abstract Scenario: er is uitspraak gezag voogdij en erkenning door ouder 2 is ontkend of vernietigd
      Gegeven heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | morgen - 10 jaar                |
      En zijn van ouder <ouder> de volgende gegevens <soort wijziging>
      | naam                                               | waarde       |
      | burgerservicenummer (01.20)                        |              |
      | geslachtsnaam (02.40)                              | <naam ouder> |
      | geboortedatum (03.10)                              |              |
      | datum ingang familierechtelijke betrekking (62.10) |              |
      | aktenummer (81.20)                                 | 1AF0100      |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde    |
      | type                             | Voogdij   |
      | minderjarige.burgerservicenummer | 000000036 |
      En heeft 'gezag' geen derden

      Voorbeelden:
      | ouder | naam ouder | soort wijziging |
      | 1     |            | gewijzigd       |
      | 1     | .          | gewijzigd       |
      | 1     |            | gecorrigeerd    |
      | 1     | .          | gecorrigeerd    |
      | 2     |            | gewijzigd       |
      | 2     | .          | gewijzigd       |
      | 2     |            | gecorrigeerd    |
      | 2     | .          | gecorrigeerd    |
