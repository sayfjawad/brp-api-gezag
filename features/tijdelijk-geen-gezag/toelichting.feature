#language: nl

Functionaliteit: wanneer er tijdelijk geen gezag is wordt een beschrijvende toelichting gegevens

  Wanneer er tijdelijk geen gezag is dan is het belangrijk om de echte oorzaak van deze situatie toe te lichten.
  Dit stelt de persoon in staat om hier op actie te kunnen ondernemen en versterkt de informatie positie van de persoon.

  De volgende uitkomsten zijn mogelijk bij tijdelijk geen gezag:

  Tijdelijk geen gezag omdat:    
    - een ouder onder curatele staat
    - een ouder overleden is
    - een ouder minderjarig is
    - een ouder onder curatele staat en is overleden
    - een ouder minderjarig is en is overleden
    - een ouder minderjarig is en onder curatele staat
    - een ouder minderjarig, onder curatele staat en overleden is
    - beide ouders geen gegevens bekend zijn 
    - beide ouders zijn minderjarig
    - beide ouders zijn overleden
    - beide ouders onder curatele staan
    - een ouder is minderjarig en een ouder staat onder curatele
    - een ouder staat onder curatele en een ouder is minderjarig
    - een ouder is minderjarig en een ouder is overleden
    - een ouder is overleden en een ouder is minderjarig
    - een ouder is overleden en een ouder staat onder curatele
    - een ouder staat onder curatele en een ouder is overleden    

  Achtergrond:
      Gegeven de persoon 'Trudy' met burgerservicenummer '000000012'
      En de persoon 'Kees' met burgerservicenummer '000000024'
      Gegeven 'Trudy' en 'Kees' zijn met elkaar gehuwd
      En de persoon 'Lucas' met burgerservicenummer '000000036'
      * is geboren op 1-1-2023
      * is in Nederland geboren
      * is ingeschreven in de BRP
      En de persoon 'Karlijn' met burgerservicenummer '000000048'
  
  Regel: Voor een kind zijn de ouders niet bekend

    Scenario: Voor het kind zijn geen ouders bekend
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                |
      | type                             | TijdelijkGeenGezag                                                    |
      | toelichting                      | tijdelijk geen gezag omdat van beide ouders geen gegevens bekend zijn |
    #  | minderjarige.burgerservicenummer | 000000036                 |
  
  Regel: Voor een kind met twee ouders is tijdelijk geen gezag 

    Scenario: Van het kind zijn beide ouders minderjarig
      Gegeven persoon 'Trudy'
      * is minderjarig
      Gegeven persoon 'Kees'
      * is minderjarig
      Gegeven persoon 'Lucas'
      * heeft 'Trudy' als ouder 1
      * heeft 'Kees' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                              |
      | type                             | TijdelijkGeenGezag                                  |
      | toelichting                      | tijdelijk geen gezag omdat ouders minderjarig zijn  |
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind staan beide ouders onder curatele
      Gegeven persoon 'Trudy'
      * is meerderjarig
      * is onder curatele gesteld
      Gegeven persoon 'Kees'
      * is meerderjarig
      * is onder curatele gesteld
      Gegeven persoon 'Lucas'
      * heeft 'Trudy' als ouder 1
      * heeft 'Kees' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                 |
      | type                             | TijdelijkGeenGezag                                     |
      | toelichting                      | tijdelijk geen gezag omdat ouders onder curatele staan |
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind zijn beide ouders overleden
      Gegeven persoon 'Trudy'
      * is meerderjarig
      * is overleden
      Gegeven persoon 'Kees'
      * is meerderjarig
      * is overleden
      Gegeven persoon 'Lucas'
      * heeft 'Trudy' als ouder 1
      * heeft 'Kees' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                 |
      | type                             | TijdelijkGeenGezag                                     |
      | toelichting                      | tijdelijk geen gezag omdat ouders overleden zijn       |
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind is een ouder minderjarig en een ouder staat onder curatele
      Gegeven persoon 'Trudy'
      * is minderjarig
      Gegeven persoon 'Kees'
      * is meerderjarig
      * is onder curatele gesteld
      Gegeven persoon 'Lucas'
      * heeft 'Trudy' als ouder 1
      * heeft 'Kees' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                                 |
      | type                             | TijdelijkGeenGezag                                                                     |
      | toelichting                      | tijdelijk geen gezag omdat een ouder minderjarig is en een ouder onder curatele staat  |
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind staat een ouder onder curatele en een ouder is minderjarig
      Gegeven persoon 'Trudy'
      * is meerderjarig
      * is onder curatele gesteld
      Gegeven persoon 'Kees'
      * is minderjarig
      Gegeven persoon 'Lucas'
      * heeft 'Trudy' als ouder 1
      * heeft 'Kees' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                                 |
      | type                             | TijdelijkGeenGezag                                                                     |
      | toelichting                      | tijdelijk geen gezag omdat een ouder onder curatele staat en een ouder minderjarig is  |
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind is een ouder staat onder curatele en een ouder is minderjarig
      Gegeven persoon 'Trudy'
      * is minderjarig
      Gegeven persoon 'Kees'
      * is meerderjarig
      * is overleden
      Gegeven persoon 'Lucas'
      * heeft 'Trudy' als ouder 1
      * heeft 'Kees' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                         |
      | type                             | TijdelijkGeenGezag                                                             |
      | toelichting                      | tijdelijk geen gezag omdat een ouder minderjarig is en een ouder overleden is  |
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind is een ouder overleden is en een ouder minderjarig is
      Gegeven persoon 'Trudy'
      * is meerderjarig
      * is overleden
      Gegeven persoon 'Kees'
      * is minderjarig
      Gegeven persoon 'Lucas'
      * heeft 'Trudy' als ouder 1
      * heeft 'Kees' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                         |
      | type                             | TijdelijkGeenGezag                                                             |
      | toelichting                      | tijdelijk geen gezag omdat een ouder overleden is en een ouder minderjarig is  |
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind is een ouder minderjarig en een ouder overleden
      Gegeven persoon 'Trudy'
      * is minderjarig
      Gegeven persoon 'Kees'
      * is meerderjarig
      * is overleden
      Gegeven persoon 'Lucas'
      * heeft 'Trudy' als ouder 1
      * heeft 'Kees' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                         |
      | type                             | TijdelijkGeenGezag                                                             |
      | toelichting                      | tijdelijk geen gezag omdat een ouder minderjarig is en een ouder overleden is  |
      #  | minderjarige.burgerservicenummer | 000000036                 |

   Scenario: Van het kind is een ouder overleden is en een ouder onder curatele gesteld
      Gegeven persoon 'Trudy'
      * is meerderjarig
      * is overleden
      Gegeven persoon 'Kees'
      * is meerderjarig
      * is onder curatele gesteld
      Gegeven persoon 'Lucas'
      * heeft 'Trudy' als ouder 1
      * heeft 'Kees' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                               |
      | type                             | TijdelijkGeenGezag                                                                   |
      | toelichting                      | tijdelijk geen gezag omdat een ouder overleden is en een ouder onder curatele staat  |
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind is een ouder minderjarig en een ouder overleden
      Gegeven persoon 'Trudy'
      * is meerderjarig
      * is onder curatele gesteld
      Gegeven persoon 'Kees'
      * is meerderjarig
      * is overleden
      Gegeven persoon 'Lucas'
      * heeft 'Trudy' als ouder 1
      * heeft 'Kees' als ouder 2
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                               |
      | type                             | TijdelijkGeenGezag                                                                   |
      | toelichting                      | tijdelijk geen gezag omdat een ouder onder curatele staat en een ouder overleden is  |  
      #  | minderjarige.burgerservicenummer | 000000036                 |

   Regel: Voor een kind met een ouder is tijdelijk geen gezag

    Scenario: Van het kind is een ouder minderjarig
      Gegeven persoon 'Karlijn' met burgerservicenummer '00000027'
      * is minderjarig
      Gegeven persoon 'Lucas'
      * heeft 'Karlijn' als ouder 1
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                               |
      | type                             | TijdelijkGeenGezag                                   |
      | toelichting                      | tijdelijk geen gezag omdat een ouder minderjarig is  |  
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind is een ouder onder curatele gesteld
      Gegeven persoon 'Karlijn'
      * is meerderjarig
      * is onder curatele gesteld
      Gegeven persoon 'Lucas'
      * heeft 'Karlijn' als ouder 1
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                     |
      | type                             | TijdelijkGeenGezag                                         |
      | toelichting                      | tijdelijk geen gezag omdat een ouder onder curatele staat  |  
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind is een ouder onder curatele gesteld
      Gegeven persoon 'Karlijn'
      * is meerderjarig
      * is overleden
      Gegeven persoon 'Lucas'
      * heeft 'Karlijn' als ouder 1
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                             |
      | type                             | TijdelijkGeenGezag                                 |
      | toelichting                      | tijdelijk geen gezag omdat een ouder overleden is  |  
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind is een ouder minderjarig en onder curatele gesteld
      Gegeven persoon 'Karlijn'
      * is minderjarig
      * is onder curatele gesteld
      Gegeven persoon 'Lucas'
      * heeft 'Karlijn' als ouder 1
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                                |
      | type                             | TijdelijkGeenGezag                                                                    |
      | toelichting                      | tijdelijk geen gezag omdat een ouder minderjarig is en onder curatele staat           |  
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind is een ouder onder curatele en overleden
      Gegeven persoon 'Karlijn'
      * is meerderjarig
      * is onder curatele gesteld
      * is overleden
      Gegeven persoon 'Lucas'
      * heeft 'Karlijn' als ouder 1
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                                |
      | type                             | TijdelijkGeenGezag                                                                    |
      | toelichting                      | tijdelijk geen gezag omdat een ouder onder curatele staat en overleden is             |  
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind is een ouder minderjarig en overleden
      Gegeven persoon 'Karlijn'
      * is minderjarig
      * is overleden
      Gegeven persoon 'Lucas'
      * heeft 'Karlijn' als ouder 1
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                                |
      | type                             | TijdelijkGeenGezag                                                                    |
      | toelichting                      | tijdelijk geen gezag omdat een ouder minderjarig is en overleden is                   |  
      #  | minderjarige.burgerservicenummer | 000000036                 |

    Scenario: Van het kind is een ouder minderjarig, onder curatele en overleden
      Gegeven persoon 'Karlijn'
      * is minderjarig
      * is onder curatele gesteld
      * is overleden
      Gegeven persoon 'Lucas'
      * heeft 'Karlijn' als ouder 1
      Als gezag wordt gezocht met de volgende parameters
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      Dan heeft de response een persoon met de volgende gegevens
      | naam                | waarde    |
      | burgerservicenummer | 000000036 |
      En heeft de persoon een 'gezag' met de volgende gegevens
      | naam                             | waarde                                                                                    |
      | type                             | TijdelijkGeenGezag                                                                        |
      | toelichting                      | tijdelijk geen gezag omdat een ouder minderjarig is, onder curatele staat en overleden is |  
      #  | minderjarige.burgerservicenummer | 000000036                 |
