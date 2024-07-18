#language: nl

Functionaliteit: gevraagde persoon is niet gevonden


Regel: wanneer de persoon niet wordt gevonden bevat het antwoord geen personen

  Scenario: een burgerservicenummer is opgegeven die niet gevonden wordt in de BRP-V
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000255 |
    Dan heeft de response 0 personen
