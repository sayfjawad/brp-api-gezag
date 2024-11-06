#language: nl

Functionaliteit: 1.1 - Staat het kind (minderjarige) als ingezetene in de BRP?

  De persoon (minderjarige) moet voorkomen in de BRP aangezien dit de bron is voor
  het achterhalen van het gezag. Een niet ingezeten kind staat wel de RNI (ook
  onderdeel van de BRP), maar hiervan zijn de gegevens (mogelijk) niet actueel. Een
  gezagsmutatie wordt bijvoorbeeld niet bijgewerkt in de RNI.

  Een persoon is "ingezetene" en "ingeschreven in de BRP" wanneer deze staat ingeschreven
  in een Nederlandse gemeente. Een persoon is "niet ingezetene" wanneer deze staat ingeschreven 
  in het RNI (Register Niet Ingezetenen). In dat geval is het gezag niet te bepalen.

  Gebruikte velden:
    - Gemeente van inschrijving -> 08.09.10

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

  Regel: Het kind is niet ingezetene in de BRP

    Scenario: het kind staat niet als ingezetene in de BRP er is sprake van GezagNietTeBepalen
      Gegeven persoon 'Zoe' 
      * is niet ingeschreven in de BRP
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

  Regel: Het kind is wel ingezetene in de BRP

    Scenario: het kind staat niet als ingezetene in de BRP er is sprake van TweehoofdigOuderlijkGezag
      Gegeven persoon 'Zoe' 
      * is ingeschreven in de BRP
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

  Regel: Het kan niet worden bepaald of het kind ingezetene in de BRP is

    Scenario: Van het kind is niet bekend want zijn/haar verblijfplaats is er is sprake van GezagNietTeBepalen
      Gegeven persoon 'Zoe' 
      * heeft een onbekende verblijfplaats
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                        |
      | type        | GezagNietTeBepalen                                                                                            |
      | toelichting | gezag is niet te bepalen omdat de volgende relevante gegevens ontbreken: verblijfplaats van bevraagde persoon |

    Scenario: Van het kind is niet bekend want zijn/haar gemeente van inschrijving is er is sprake van GezagNietTeBepalen
      Gegeven persoon 'Zoe' 
      * heeft een onbekende gemeente van inschrijving
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                                                                   |
      | type        | GezagNietTeBepalen                                                                                                       |
      | toelichting | gezag is niet te bepalen omdat de volgende relevante gegevens ontbreken: gemeente van inschrijving van bevraagde persoon |