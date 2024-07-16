#language: nl

Functionaliteit: Tg018 - Vondeling - Kind zonder ouders


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 1                  | 0518200000583526                           |

    # Lg01_100 - vondeling, juridisch geen ouders (is fout in BRP opgenomen)
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000012  |
    | voornamen (02.10)               | Zacharias  |
    | geslachtsnaam (02.40)           | Vondeling  |
    | geboortedatum (03.10)           | 20210801   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA1000    |
    | ingangsdatum geldigheid (85.10) | 20210801   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA1000  |
    | ingangsdatum geldigheid (85.10) | 20210801 |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                            | waarde   |
    | aktenummer (81.20)              | 1AA1000  |
    | ingangsdatum geldigheid (85.10) | 20210801 |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20210801 |


  Scenario: Lg01_100 - vondeling, juridisch geen ouders (is fout in BRP opgenomen)
    # Route: 37 - Wie heeft gezag?: niet te bepalen (G)
    Als gezag wordt gezocht met de volgende parameters
    | naam | waarde    |
    | bsn  | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam | waarde             |
    | type | TijdelijkGeenGezag |
