#language: nl

@skip-verify
Functionaliteit: Geen javascript fout bij ongeldig response

  Scenario: naam veld in de response heeft de waarde null
    Gegeven de response body is gelijk aan
    """
    {
      "personen": [ ]
    }
    """
    Dan heeft de response geen personen

  Scenario: naam veld in de response heeft de waarde undefined
    Gegeven de response body is gelijk aan
    """
    {
      "personen": [ ]
    }
    """
    Dan heeft de response geen personen