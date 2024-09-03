    #language: nl

    @api
    Functionaliteit: 1.3 - Is minderjarige naar het buitenland geëmigreerd geweest?

    Achtergrond:
    Gegeven de persoon 'P1' met burgerservicenummer '000000012'
    * is meerderjarig
    * is in Nederland geboren
    En de persoon 'P2' met burgerservicenummer '000000024'
    * is minderjarig
    * is in Nederland geboren
    * heeft uitspraak gezag met de volgende gegevens
      | naam                                 | waarde |
      | indicatie gezag minderjarige (32.10) | 1      |


  Regel: Als de minderjarige geëmigreerd is geweest kan het gezag van de persoon niet worden bepaald

  Scenario: de minderjarige is geëmigreerd geweest en woont nu weer in Nederland
    Gegeven de persoon 'P3' met burgerservicenummer '000000036'
    * is minderjarig
    * is geëmigreerd geweest met de volgende gegevens
      | datum vestiging in Nederland (14.20) |
      | 20231014                             |
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde                          |
      | type                | RaadpleegMetBurgerservicenummer |
      | burgerservicenummer | 000000036                       |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam        | waarde                                                                        |
      | type        | GezagNietTeBepalen                                                            |
      | toelichting | gezag is niet te bepalen omdat minderjarige buiten Nederland heeft verbleven. |