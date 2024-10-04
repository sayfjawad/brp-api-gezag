#language: nl

Functionaliteit: Tg005 - Donkers-Dangor-Dass - Gezag kan niet bepaald worden


  Achtergrond:
    Gegeven adres 'A1' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 7                  | 0518200000583530                           |
    Gegeven adres 'A2' heeft de volgende gegevens
    | gemeentecode (92.10) | straatnaam (11.10) | huisnummer (11.20) | huisletter (11.30) | identificatiecode nummeraanduiding (11.90) |
    | 518                  | Turfmarkt          | 7                  | a                  | 0518200000583531                           |
    
    # Lg01_017 - gehuwd, echtgenoot niet ingeschreven in Nederland, minderjarig kind geboren tijdens huwelijk
    Gegeven de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Desiree    |
    | geslachtsnaam (02.40)           | Donkers    |
    | geboortedatum (03.10)           | 19900301   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Dirk     |
    | geslachtsnaam (02.40)                              | Donkers  |
    | geboortedatum (03.10)                              | 19600301 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 19900301 |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Deborah  |
    | geslachtsnaam (02.40)                              | Dekkers  |
    | geboortedatum (03.10)                              | 19650301 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 19900301 |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde        |
    | voornamen (02.10)                                                   | Damon         |
    | geslachtsnaam (02.40)                                               | Dangor        |
    | geboortedatum (03.10)                                               | 19881201      |
    | geboorteland (03.30)                                                | 5014          |
    | geslachtsaanduiding (04.10)                                         | M             |
    | beschrijving document (82.30)                                       | huw.akte 5014 |
    | ingangsdatum geldigheid (85.10)                                     | 20131201      |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20131201      |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | Bisho         |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 5014          |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000024  |
    | voornamen (02.10)               | Daryl      |
    | geslachtsnaam (02.40)           | Dangor     |
    | geboortedatum (03.10)           | 20140301   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20140301   |
    
    # Lg01_018 - minderjarig kind, geboren tijdens huwelijk ouders, geen categorie 11, vader niet ingeschreven in BRP, kind Nederlandse nationaliteit + Beëindigde vreemde nationaliteit
    Gegeven de persoon met burgerservicenummer '000000024' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Daryl      |
    | geslachtsnaam (02.40)           | Dangor     |
    | geboortedatum (03.10)           | 20140301   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0100    |
    | ingangsdatum geldigheid (85.10) | 20140301   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Damon    |
    | geslachtsnaam (02.40)                              | Dangor   |
    | geboortedatum (03.10)                              | 19881201 |
    | geboorteland (03.30)                               | 5014     |
    | geslachtsaanduiding (04.10)                        | M        |
    | aktenummer (81.20)                                 | 1AA0100  |
    | ingangsdatum geldigheid (85.10)                    | 20140301 |
    | datum ingang familierechtelijke betrekking (62.10) | 20140301 |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000012  |
    | voornamen (02.10)                                  | Desiree    |
    | geslachtsnaam (02.40)                              | Donkers    |
    | geboortedatum (03.10)                              | 19900301   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0100    |
    | ingangsdatum geldigheid (85.10)                    | 20140301   |
    | datum ingang familierechtelijke betrekking (62.10) | 20140301   |
    En de persoon is ingeschreven op adres 'A1' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20140301 |
    
    # Lg01_019 - gehuwd, 3 minderjarige kinderen geboren tijdens huwelijk ouders, echtgenoot en 2 kinderen geëmigreerd (RNI), 1 kind weer teruggekeerd (immigratie)
    Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Donna      |
    | geslachtsnaam (02.40)           | Donkers    |
    | geboortedatum (03.10)           | 19900301   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | V          |
    | beschrijving document (82.30)   | PKA        |
    | ingangsdatum geldigheid (85.10) | 0          |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Dirk     |
    | geslachtsnaam (02.40)                              | Donkers  |
    | geboortedatum (03.10)                              | 19600301 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 19900301 |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Deborah  |
    | geslachtsnaam (02.40)                              | Dekkers  |
    | geboortedatum (03.10)                              | 19650301 |
    | geboorteland (03.30)                               | 6030     |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | PK       |
    | ingangsdatum geldigheid (85.10)                    | 0        |
    | datum ingang familierechtelijke betrekking (62.10) | 19900301 |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000048  |
    | voornamen (02.10)                                                   | Dag        |
    | geslachtsnaam (02.40)                                               | Dass       |
    | geboortedatum (03.10)                                               | 19821201   |
    | geboorteland (03.30)                                                | 6027       |
    | geslachtsaanduiding (04.10)                                         | M          |
    | aktenummer (81.20)                                                  | 3AA0100    |
    | ingangsdatum geldigheid (85.10)                                     | 20151201   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20151201   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20111101 |
    En de persoon heeft een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Ditmar     |
    | geslachtsnaam (02.40)           | Dass       |
    | geboortedatum (03.10)           | 20160801   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0150    |
    | ingangsdatum geldigheid (85.10) | 20160801   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Darian     |
    | geslachtsnaam (02.40)           | Dass       |
    | geboortedatum (03.10)           | 20171201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0200    |
    | ingangsdatum geldigheid (85.10) | 20171201   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Duko       |
    | geslachtsnaam (02.40)           | Dass       |
    | geboortedatum (03.10)           | 20181201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0200    |
    | ingangsdatum geldigheid (85.10) | 20181201   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000097  |
    | voornamen (02.10)               | Dorian     |
    | geslachtsnaam (02.40)           | Dass       |
    | geboortedatum (03.10)           | 20181201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0201    |
    | ingangsdatum geldigheid (85.10) | 20181201   |
    
    # Lg01_020 - gehuwd, 2 minderjarige kinderen geboren tijdens huwelijk ouders, zelf + 2 kinderen geëmigreerd (RNI), 1 kind weer teruggekeerd (immigratie).
    Gegeven de persoon met burgerservicenummer '000000048' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Dag        |
    | geslachtsnaam (02.40)           | Dass       |
    | geboortedatum (03.10)           | 19821201   |
    | geboorteland (03.30)            | 6027       |
    | geslachtsaanduiding (04.10)     | M          |
    | beschrijving document (82.30)   | ga 6027    |
    | ingangsdatum geldigheid (85.10) | 19821201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Dellingr |
    | geslachtsnaam (02.40)                              | Dass     |
    | geslachtsaanduiding (04.10)                        | M        |
    | beschrijving document (82.30)                      | ga 6027  |
    | ingangsdatum geldigheid (85.10)                    | 19821201 |
    | datum ingang familierechtelijke betrekking (62.10) | 19821201 |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde   |
    | voornamen (02.10)                                  | Dagmar   |
    | geslachtsnaam (02.40)                              | Digerud  |
    | geslachtsaanduiding (04.10)                        | V        |
    | beschrijving document (82.30)                      | ga 6027  |
    | ingangsdatum geldigheid (85.10)                    | 19821201 |
    | datum ingang familierechtelijke betrekking (62.10) | 19821201 |
    En de persoon heeft een 'partner' met de volgende gegevens
    | naam                                                                | waarde     |
    | burgerservicenummer (01.20)                                         | 000000036  |
    | voornamen (02.10)                                                   | Donna      |
    | geslachtsnaam (02.40)                                               | Donkers    |
    | geboortedatum (03.10)                                               | 19900301   |
    | geboorteland (03.30)                                                | 6030       |
    | geslachtsaanduiding (04.10)                                         | V          |
    | aktenummer (81.20)                                                  | 3AA0100    |
    | ingangsdatum geldigheid (85.10)                                     | 20151201   |
    | datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)  | 20151201   |
    | plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20) | 0518       |
    | land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)   | 6030       |
    En de persoon heeft de volgende 'inschrijving' gegevens
    | naam                                 | waarde                     |
    | datum opschorting bijhouding (67.10) | 20200801                   |
    | reden opschorting bijhouding (67.20) | E                          |
    | indicatie geheim (70.10)             | 0                          |
    En de persoon is ingeschreven op een buitenlands adres met de volgende gegevens
    | naam                                   | waarde            |
    | gemeente van inschrijving (09.10)      | 1999              |
    | land (13.10)                           | 6027              |
    | datum aanvang adres buitenland (13.20) | 20200801          |
    | ingangsdatum geldigheid (85.10)        | 20200801          |
    En de persoon heeft een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000073  |
    | voornamen (02.10)               | Ditmar     |
    | geslachtsnaam (02.40)           | Dass       |
    | geboortedatum (03.10)           | 20160801   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0150    |
    | ingangsdatum geldigheid (85.10) | 20160801   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000061  |
    | voornamen (02.10)               | Darian     |
    | geslachtsnaam (02.40)           | Dass       |
    | geboortedatum (03.10)           | 20171201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0200    |
    | ingangsdatum geldigheid (85.10) | 20171201   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000085  |
    | voornamen (02.10)               | Duko       |
    | geslachtsnaam (02.40)           | Dass       |
    | geboortedatum (03.10)           | 20181201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0200    |
    | ingangsdatum geldigheid (85.10) | 20181201   |
    En de persoon heeft nog een 'kind' met de volgende gegevens
    | naam                            | waarde     |
    | burgerservicenummer (01.20)     | 000000097  |
    | voornamen (02.10)               | Dorian     |
    | geslachtsnaam (02.40)           | Dass       |
    | geboortedatum (03.10)           | 20181201   |
    | geboorteland (03.30)            | 6030       |
    | aktenummer (81.20)              | 1AA0201    |
    | ingangsdatum geldigheid (85.10) | 20181201   |
    
    # Lg01_021 - minderjarig kind, geboren tijdens huwelijk ouders, geen categorie 11, vader ingeschreven in RNI, kind Nederlandse nationaliteit
    Gegeven de persoon met burgerservicenummer '000000061' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Darian     |
    | geslachtsnaam (02.40)           | Dass       |
    | geboortedatum (03.10)           | 20171201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0200    |
    | ingangsdatum geldigheid (85.10) | 20171201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000036  |
    | voornamen (02.10)                                  | Donna      |
    | geslachtsnaam (02.40)                              | Donkers    |
    | geboortedatum (03.10)                              | 19900301   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0200    |
    | ingangsdatum geldigheid (85.10)                    | 20171201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20171201   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000048  |
    | voornamen (02.10)                                  | Dag        |
    | geslachtsnaam (02.40)                              | Dass       |
    | geboortedatum (03.10)                              | 19821201   |
    | geboorteland (03.30)                               | 6027       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AA0200    |
    | ingangsdatum geldigheid (85.10)                    | 20171201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20171201   |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20171201 |
    
    # Lg01_022 - minderjarig kind, geboren tijdens huwelijk ouders, geen categorie 11, geëmigreerd, ingeschreven in RNI evenals vader
    Gegeven de persoon met burgerservicenummer '000000073' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Ditmar     |
    | geslachtsnaam (02.40)           | Dass       |
    | geboortedatum (03.10)           | 20160801   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0150    |
    | ingangsdatum geldigheid (85.10) | 20160801   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000048  |
    | voornamen (02.10)                                  | Dag        |
    | geslachtsnaam (02.40)                              | Dass       |
    | geboortedatum (03.10)                              | 19821201   |
    | geboorteland (03.30)                               | 6027       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AA0150    |
    | ingangsdatum geldigheid (85.10)                    | 20160801   |
    | datum ingang familierechtelijke betrekking (62.10) | 20160801   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000036  |
    | voornamen (02.10)                                  | Donna      |
    | geslachtsnaam (02.40)                              | Donkers    |
    | geboortedatum (03.10)                              | 19900301   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0150    |
    | ingangsdatum geldigheid (85.10)                    | 20160801   |
    | datum ingang familierechtelijke betrekking (62.10) | 20160801   |
    En de persoon heeft de volgende 'inschrijving' gegevens
    | naam                                 | waarde                     |
    | datum opschorting bijhouding (67.10) | 20200801                   |
    | reden opschorting bijhouding (67.20) | E                          |
    | indicatie geheim (70.10)             | 0                          |
    En de persoon is ingeschreven op een buitenlands adres met de volgende gegevens
    | naam                                   | waarde            |
    | gemeente van inschrijving (09.10)      | 1999              |
    | land (13.10)                           | 6027              |
    | datum aanvang adres buitenland (13.20) | 20200801          |
    | ingangsdatum geldigheid (85.10)        | 20200801          |
    
    # Lg01_023 - minderjarig kind, geboren tijdens huwelijk ouders, geen categorie 11, vader ingeschreven in de RNI, kind Nederlandse nationaliteit, geëmigreerd geweest en geïmmigreerd
    Gegeven de persoon met burgerservicenummer '000000085' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Duko       |
    | geslachtsnaam (02.40)           | Dass       |
    | geboortedatum (03.10)           | 20181201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0200    |
    | ingangsdatum geldigheid (85.10) | 20181201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000048  |
    | voornamen (02.10)                                  | Dag        |
    | geslachtsnaam (02.40)                              | Dass       |
    | geboortedatum (03.10)                              | 19821201   |
    | geboorteland (03.30)                               | 6027       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AA0200    |
    | ingangsdatum geldigheid (85.10)                    | 20181201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20181201   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000036  |
    | voornamen (02.10)                                  | Donna      |
    | geslachtsnaam (02.40)                              | Donkers    |
    | geboortedatum (03.10)                              | 19900301   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0220    |
    | ingangsdatum geldigheid (85.10)                    | 20181201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20181201   |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                                 | waarde   |
    | gemeente van inschrijving (09.10)    | 518      |
    | land vanwaar ingeschreven (14.10)    | 6027     |
    | datum vestiging in Nederland (14.20) | 20210501 |
    | ingangsdatum geldigheid (85.10)      | 20210501 |
    
    # Lg01_197 - minderjarig kind, geboren in Nederland, moeder ingezetene, vader RNI
    Gegeven de persoon met burgerservicenummer '000000097' heeft de volgende gegevens
    | naam                            | waarde     |
    | voornamen (02.10)               | Dorian     |
    | geslachtsnaam (02.40)           | Dass       |
    | geboortedatum (03.10)           | 20181201   |
    | geboorteland (03.30)            | 6030       |
    | geslachtsaanduiding (04.10)     | M          |
    | aktenummer (81.20)              | 1AA0201    |
    | ingangsdatum geldigheid (85.10) | 20181201   |
    En de persoon heeft een ouder '2' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000048  |
    | voornamen (02.10)                                  | Dag        |
    | geslachtsnaam (02.40)                              | Dass       |
    | geboortedatum (03.10)                              | 19821201   |
    | geboorteland (03.30)                               | 6027       |
    | geslachtsaanduiding (04.10)                        | M          |
    | aktenummer (81.20)                                 | 1AA0201    |
    | ingangsdatum geldigheid (85.10)                    | 20181201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20181201   |
    En de persoon heeft een ouder '1' met de volgende gegevens
    | naam                                               | waarde     |
    | burgerservicenummer (01.20)                        | 000000036  |
    | voornamen (02.10)                                  | Donna      |
    | geslachtsnaam (02.40)                              | Donkers    |
    | geboortedatum (03.10)                              | 19900301   |
    | geboorteland (03.30)                               | 6030       |
    | geslachtsaanduiding (04.10)                        | V          |
    | aktenummer (81.20)                                 | 1AA0221    |
    | ingangsdatum geldigheid (85.10)                    | 20181201   |
    | datum ingang familierechtelijke betrekking (62.10) | 20181201   |
    En de persoon is ingeschreven op adres 'A2' met de volgende gegevens
    | naam                              | waarde   |
    | gemeente van inschrijving (09.10) | 518      |
    | ingangsdatum geldigheid (85.10)   | 20181201 |


  Scenario: Lg01_017 - gehuwd, echtgenoot niet ingeschreven in Nederland, minderjarig kind geboren tijdens huwelijk
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000012 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000012 |
    En heeft de persoon geen gezag

  Scenario: Lg01_018 - minderjarig kind, geboren tijdens huwelijk ouders, geen categorie 11, vader niet ingeschreven in BRP, kind Nederlandse nationaliteit + Beëindigde vreemde nationaliteit
    # Route: 45e - Wie heeft gezag?: exceptie, BSN vader ontbreekt (N)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000024 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000024 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam        | waarde                                                                                                                                                                                      |
    | type        | GezagNietTeBepalen                                                                                                                                                                          |
    | toelichting | gezag is niet te bepalen omdat bij bepaling huwelijk/partnerschap relevante gegevens ontbreken. Het gaat om de volgende gegevens: ouder2 van bevraagde persoon is niet in BRP geregistreerd |

  Scenario: Lg01_019 - gehuwd, 3 minderjarige kinderen geboren tijdens huwelijk ouders, echtgenoot en 2 kinderen geëmigreerd (RNI), 1 kind weer teruggekeerd (immigratie)
    # Meerderjarig
    # Route: 2m - Wie heeft gezag?: meerderjarig, gezag is niet van toepassing (NVT)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000036 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000036 |
    En heeft de persoon geen gezag

  Scenario: Lg01_020 - gehuwd, 2 minderjarige kinderen geboren tijdens huwelijk ouders, zelf + 2 kinderen geëmigreerd (RNI), 1 kind weer teruggekeerd (immigratie).
    # Meerderjarig
    # Route: 1 - Wie heeft gezag?:  (N)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000048 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000048 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam        | waarde                                                               |
    | type        | GezagNietTeBepalen                                                   |
    | toelichting | gezag is niet te bepalen omdat minderjarige niet in Nederland woont. |

  Scenario: Lg01_021 - minderjarig kind, geboren tijdens huwelijk ouders, geen categorie 11, vader ingeschreven in RNI, kind Nederlandse nationaliteit
    # Route: 45e - Wie heeft gezag?:  (N)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000061 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000061 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam        | waarde                                                                                                                                                                                      |
    | type        | GezagNietTeBepalen                                                                                                                                                                          |
    | toelichting | gezag is niet te bepalen omdat bij bepaling huwelijk/partnerschap relevante gegevens ontbreken. Het gaat om de volgende gegevens: ouder1 van bevraagde persoon is niet in BRP geregistreerd |

  Scenario: Lg01_022 - minderjarig kind, geboren tijdens huwelijk ouders, geen categorie 11, geëmigreerd, ingeschreven in RNI evenals vader
    # Route: 1 - Wie heeft gezag?:  (N)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000073 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000073 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam        | waarde                                                               |
    | type        | GezagNietTeBepalen                                                   |
    | toelichting | gezag is niet te bepalen omdat minderjarige niet in Nederland woont. |

  Scenario: Lg01_023 - minderjarig kind, geboren tijdens huwelijk ouders, geen categorie 11, vader ingeschreven in de RNI, kind Nederlandse nationaliteit, geëmigreerd geweest en geïmmigreerd
    # Route: 3 - Wie heeft gezag?:  (N)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000085 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000085 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam        | waarde                                                                        |
    | type        | GezagNietTeBepalen                                                            |
    | toelichting | gezag is niet te bepalen omdat minderjarige buiten Nederland heeft verbleven. |

  Scenario: Lg01_197 - minderjarig kind, geboren in Nederland, moeder ingezetene, vader RNI
    # Route: 45e - Wie heeft gezag?: niet te bepalen (N)
    Als gezag wordt gezocht met de volgende parameters
    | naam                 | waarde    |
    | burgerservicenummer  | 000000097 |
    Dan heeft de response een persoon met de volgende gegevens
    | naam                | waarde    |
    | burgerservicenummer | 000000097 |
    En heeft de persoon een 'gezag' met de volgende gegevens
    | naam        | waarde                                                                                                                                                                                      |
    | type        | GezagNietTeBepalen                                                                                                                                                                          |
    | toelichting | gezag is niet te bepalen omdat bij bepaling huwelijk/partnerschap relevante gegevens ontbreken. Het gaat om de volgende gegevens: ouder2 van bevraagde persoon is niet in BRP geregistreerd |
