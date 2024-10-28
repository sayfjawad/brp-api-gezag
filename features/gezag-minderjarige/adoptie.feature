#language: nl

Functionaliteit: Adoptie en gezagsbepaling met behulp van Gherkin voor verschillende stakeholders specificeren

  Als BDD practioner
  wil ik andere BDD practictioners (zowel bestaande als toekomstige) meenemen in het proces
  om met behulp van Gherkin voor verschillende stakeholders (consumer/provider developer, BRP domein expert)
  complexe onderwerpen in het BRP domein zoals adoptie en gezagsbepaling als scenarios/voorbeelden te beschrijven

  Regel: Adoptie specificeren met behulp van de huidige personen stap definities 

    De huidige persoon stap definities bestaan uit stap definities waarmee wordt aangegeven:
    - om welke persoon het gaat (bijv. 'de persoon met burgerservicenummer heeft de volgende gegeven' stap definitie)
    - wat er bij de persoon aan gegevens verandert (bijv. 'de persoon is gewijzigd met de volgende gegevens' of 'de persoon heeft een ouder met de volgende gegevens' stap definities)

    Scenarios beschrijven voor gezagsbepaling bij een geadopteerd persoon met behulp van de huidige persoon stap definities
    zijn moeilijk te begrijpen door een lezer die geen/weinig kennis heeft van adoptie en gezagsbepaling.
    Ze zijn ook moeilijk te schrijven omdat de schrijver moet weten hoe de automation achter een stap definitie werkt
    en de schrijver moet weten welke gegevens bij welke personen worden toegevoegd

    Het registreren van adoptie leidt tot de volgende gegevens toevoegingen in de BRP
    - toevoegen akt_nr = 1AQ0100 aan persoon die wordt geadopteerd
    - toevoegen adoptie ouder als ouder 1 of 2 met familie_betrek_start_datum = ingangsdatum adoptie aan de geadopteerde persoon
    - toevoegen van kind aan persoon die adoptie ouder wordt

    Het scenario waarmee gezagsbepaling bij adoptie door één persoon wordt beschreven ziet er dan als volgt uit:

    Scenario: minderjarige is geadopteerd door één meerderjarige
      Gegeven de persoon met burgerservicenummer '000000036' heeft de volgende gegevens
      | voor_naam | geboorte_datum     |
      | Bert      | gisteren - 17 jaar |
      # adoptie stap
      En de persoon is gewijzigd naar de volgende gegevens
      | voor_naam | geboorte_datum     | akte_nr |
      | Bert      | gisteren - 17 jaar | 1AQ0100 |
      # adoptie stap
      En de persoon heeft een ouder '1' met de volgende gegevens
      | burger_service_nr | voor_naam | geboorte_datum     | familie_betrek_start_datum |
      | 000000012         | Gerda     | gisteren - 20 jaar | vandaag                    |
      En de persoon met burgerservicenummer '000000012' heeft de volgende gegevens
      | voor_naam | geboorte_datum     |
      | Gerda     | gisteren - 20 jaar |
      # adoptie stap
      En de persoon heeft een 'kind' met de volgende gegevens
      | burger_service_nr | voor_naam | geboorte_datum     |
      | 000000036         | Bert      | gisteren - 17 jaar |
      Als personen wordt gezocht met de volgende parameters
      | naam                | waarde                           |
      | type                | RaadpleegMetBurgerservicenummer  |
      | burgerservicenummer | 000000036,000000012              |
      | fields              | burgerservicenummer,ouders,gezag |

  Regel: Adoptie specificeren met behulp van aanduiding stap definities

    Door de huidige stap definities uit te breiden met persoon aanduiding wordt het mogelijk om stappen die bij elkaar horen bij elkaar te specificeren.
    De scenarios zijn hierdoor makkelijker te begrijpen door een lezer en zijn ook beheerbaarder.
    De scenario hierboven kan dan als volgt worden herschreven

    Scenario: minderjarige is geadopteerd door één meerderjarige
      Gegeven de persoon 'Bert' met burgerservicenummer '000000036' heeft de volgende gegevens
      | voor_naam | geboorte_datum     |
      | Bert      | gisteren - 17 jaar |
      En de persoon 'Gerda' met burgerservicenummer '000000012' heeft de volgende gegevens
      | voor_naam | geboorte_datum     |
      | Gerda     | gisteren - 20 jaar |
      # adoptie (stappen)
      En de persoon 'Bert' is gewijzigd naar de volgende gegevens
      | voor_naam | geboorte_datum     | akte_nr |
      | Bert      | gisteren - 17 jaar | 1AQ0100 |
      En de persoon 'Bert' heeft een ouder '1' met de volgende gegevens
      | burger_service_nr | voor_naam | geboorte_datum     | familie_betrek_start_datum |
      | 000000012         | Gerda     | gisteren - 20 jaar | vandaag                    |
      En de persoon 'Gerda' heeft een 'kind' met de volgende gegevens
      | burger_service_nr | voor_naam | geboorte_datum     |
      | 000000036         | Bert      | gisteren - 17 jaar |
      Als personen wordt gezocht met de volgende parameters
      | naam                | waarde                           |
      | type                | RaadpleegMetBurgerservicenummer  |
      | burgerservicenummer | 000000036,000000012              |
      | fields              | burgerservicenummer,ouders,gezag |

  Regel: impliciete voorwaarden voor adoptie expliciet maken met expressieve stap definities

    Met de huidige stap definities zijn de voorwaarden voor adoptie impliciet benoemd.
    Een persoon kan worden geadopteerd als hij/zij jonger is dan 18 jaar en een persoon kan adoptie ouder worden als hij/zij ouder is dan 18 jaar

    Expressieve stap definities beschrijven in natuurlijke taal de voorwaarden bij de betreffende persoon
    - is minder-/meerder- jarig
    - is niet overleden (opmerking. Als bij een persoon deze stap niet is opgenomen, betekent dit dan dat de betreffende persoon is overleden?)
    - staat niet onder curatele (zie opmerking hierboven)

    De scenario hierboven kan dan als volgt worden herschreven

    Scenario: minderjarige is geadopteerd door één meerderjarige
      Gegeven de persoon 'Bert' met burgerservicenummer '000000036'
      * is minderjarig
      En de persoon 'Gerda' met burgerservicenummer '000000012'
      * is meerderjarig
      * is niet overleden
      * staat niet onder curatele
      # adoptie (stappen)
      En de persoon 'Bert' is gewijzigd naar de volgende gegevens
      | voor_naam | geboorte_datum     | akte_nr |
      | Bert      | gisteren - 17 jaar | 1AQ0100 |
      En de persoon 'Bert' heeft een ouder '1' met de volgende gegevens
      | burger_service_nr | voor_naam | geboorte_datum     | familie_betrek_start_datum |
      | 000000012         | Gerda     | gisteren - 20 jaar | vandaag                    |
      En de persoon 'Gerda' heeft een 'kind' met de volgende gegevens
      | burger_service_nr | voor_naam | geboorte_datum     |
      | 000000036         | Bert      | gisteren - 17 jaar |
      Als personen wordt gezocht met de volgende parameters
      | naam                | waarde                           |
      | type                | RaadpleegMetBurgerservicenummer  |
      | burgerservicenummer | 000000036,000000012              |
      | fields              | burgerservicenummer,ouders,gezag |

  Regel: Adoptie specificeren mbv een expressieve adoptie stap definitie 

    Het uitschrijven van alle stappen die bij adoptie horen maakt de scenarios minder beheersbaar.
    Als in de toekomst een extra gegeven moet worden opgenomen bij adoptie, dan moeten alle adoptie scenarios worden doorlopen om het extra gegeven toe te voegen.
    Ook zijn niet alle lezers geïnteresseerd in welke gegevens worden toegevoegd aan de BRP bij het registreren van een adoptie.
    Consumer developers zullen meer geïnteresseerd zijn hoe gezag eruit ziet voor een geadopteerd persoon.

    Door adoptie te encapsuleren in een expressieve adoptie stap definitie kan het scenario hierboven als volgt worden herschreven

    Scenario: minderjarige is geadopteerd door één meerderjarige
      Gegeven de persoon 'Gerda' met burgerservicenummer '000000012'
      * is meerderjarig
      * is niet overleden
      * staat niet onder curatele
      En de persoon 'Bert' met burgerservicenummer '000000036'
      * is minderjarig
      En 'Bert' is geadopteerd door 'Gerda' als ouder 1 op datum 'vandaag'
      Als personen wordt gezocht met de volgende parameters
      | naam                | waarde                           |
      | type                | RaadpleegMetBurgerservicenummer  |
      | burgerservicenummer | 000000036,000000012              |
      | fields              | burgerservicenummer,ouders,gezag |

  Regel: Ontkennende gegeven stappen weglaten en de Als stap definitie expressief maken

    Ontkennende gegeven stappen dragen over het algemeen niet bij aan het begrip van de lezer.
    Het kan zelf tot verwarring leiden. Voorbeeld: als bij een persoon niet is aangegeven dat hij niet is overleden, is hij dan overleden?

    Verder bevat de als stap veel detail die voor veel lezers niet interresant zijn:
    - welke type moet worden opgegeven
    - welke verplichte parameters moet opgegeven
    - wat zijn de namen van de verplichte parameters

    Met een expressieve Als stap wordt bovengenoemde detail verborgen voor de lezer zonder dat er concessie wordt gedaan op begrijpbaarheid/leesbaarheid van het scenario.

    Het scenario hierboven kan dan als volgt worden herschreven. Daaronder zijn andere adoptie scenarios te vinden.

    Scenario: minderjarige is geadopteerd door één meerderjarige
      Gegeven de persoon 'Gerda' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Bert' met burgerservicenummer '000000036'
      * is minderjarig
      En 'Bert' is geadopteerd door 'Gerda' als ouder 1 op datum 'vandaag'
      Als van 'Bert,Gerda' hun 'burgerservicenummer,ouders,gezag' wordt gevraagd

    Scenario: minderjarige is geadopteerd door een echtpaar
      Gegeven de persoon 'Gerda' met burgerservicenummer '000000012'
      * is meerderjarig
      En de persoon 'Aart' met burgerservicenummer '000000024'
      * is meerderjarig
      En 'Aart' en 'Gerda' zijn gehuwd op datum 'vandaag - 2 jaar'
      En de persoon 'Bert' met burgerservicenummer '000000036'
      * is minderjarig
      En 'Bert' is geadopteerd door 'Gerda' als ouder 1 en 'Aart' als ouder 2 op datum 'vandaag'
      Als van 'Bert,Gerda,Aart' hun 'burgerservicenummer,ouders,gezag' wordt gevraagd

    Scenario: minderjarige met één ouder en is geadopteerd door één meerderjarige
      Gegeven de persoon 'Gerda' met burgerservicenummer '000000012'
      * is meerderjarig
      * is niet overleden
      * staat niet onder curatele
      En de persoon 'Aart' met burgerservicenummer '000000024'
      * is meerderjarig
      * is niet overleden
      * staat niet onder curatele
      En de persoon 'Bert' met burgerservicenummer '000000036'
      * is minderjarig
      En 'Gerda' is ouder 1 van 'Bert'
      En 'Bert' is geadopteerd door 'Aart' als ouder 2 op datum 'vandaag'
      Als van 'Bert,Gerda,Aart' hun 'burgerservicenummer,ouders,gezag' wordt gevraagd

  Regel: Adoptie specificeren voor de automation developer

    Door de stap definities expressief te maken en veel (implementatie) detail te verbergen, heeft de automation developer behoefte aan specificaties die de werking van expressieve stap definities beschrijven.

    In onderstaand scenario is de 'adoptie door 1 ouder' stap definitie gespecificeerd 

    Scenario: '[aanduiding kind]' is geadopteerd door '[aanduiding ouder]' als ouder [1 of 2] op datum '[adoptie datum]'
      Gegeven de persoon 'P1' met burgerservicenummer '000000012'
      En de persoon 'P2' met burgerservicenummer '000000024'
      En 'P2' is geadopteerd door 'P1' als ouder <ouder type> op datum 'vandaag'
      Dan zijn de gegenereerde SQL statements    
      | stap       | categorie          | text                                                                                                                                                             | values                                      |
      | persoon-P1 | inschrijving       | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *            | 0                                           |
      |            | persoon            | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                               | 9999,0,0,P,000000012,P1                     |
      |            | kind-1             | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                               | 9999,0,0,K,000000024,P2                     |
      | persoon-P2 | inschrijving       | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *            | 0                                           |
      |            | persoon            | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                               | 10000,0,1,P,000000024,P2                    |
      |            | persoon            | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,akte_nr) VALUES($1,$2,$3,$4,$5,$6,$7)                    | 10000,0,0,P,000000024,P2,1AQ0100            |
      |            | ouder-<ouder type> | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,familie_betrek_start_datum) VALUES($1,$2,$3,$4,$5,$6,$7) | 10000,0,0,<ouder type>,000000012,P1,vandaag |

  Regel: Adoptie specificeren voor de provider (=API) developer van de BRP API

    Ook bij een provider developer zal behoefte ontstaan voor specificaties die de implementatie detail van bijv. adoptie beschrijven. 
    Een provider developer zoals de Gezag API developer zal bijv. willen weten hoe een persoon er in de BRP eruit ziet vóór en nadat hij is geadopteerd, om in code deze situatie te kunnen herkennen

    Een scenario om dit te specificeren kan er dan als volgt uit zien:

    Scenario: minderjarige wordt geadopteerd door één persoon
      Gegeven de persoon 'Gerda' heeft de volgende rijen in tabel lo3_pl_persoon
      | pl_id | persoon_type | stapel_nr | volg_nr | burger_service_nr | voor_naam | geboorte_datum     |
      | 1     | P            | 0         | 0       | 000000012         | Gerda     | gisteren - 20 jaar |
      En de persoon 'Bert' heeft de volgende rijen in tabel lo3_pl_persoon
      | pl_id | persoon_type | stapel_nr | volg_nr | burger_service_nr | voor_naam | geboorte_datum     |
      | 2     | P            | 0         | 0       | 000000036         | Bert      | gisteren - 17 jaar |
      Als 'Bert' is geadopteerd door 'Gerda' als ouder '1' op datum 'vandaag'
      Dan heeft de persoon 'Bert' de volgende rijen in tabel lo3_pl_persoon
      | pl_id | persoon_type | stapel_nr | volg_nr | burger_service_nr | voor_naam | geboorte_datum     | akte_nr | familie_betrek_start_datum |
      | 2     | P            | 0         | 1       | 000000036         | Bert      | gisteren - 17 jaar |         |                            |
      | 2     | P            | 0         | 0       | 000000036         | Bert      | gisteren - 17 jaar | 1AQ0100 |                            |
      | 2     | 1            | 0         | 0       | 000000012         | Gerda     | gisteren - 20 jaar |         | vandaag                    |
      En heeft de persoon 'Gerda' de volgende rijen in tabel lo3_pl_persoon
      | pl_id | persoon_type | stapel_nr | volg_nr | burger_service_nr | voor_naam | geboorte_datum     |
      | 1     | P            | 0         | 0       | 000000012         | Gerda     | gisteren - 20 jaar |
      | 1     | K            | 0         | 0       | 000000036         | Bert      | gisteren - 17 jaar |

  Regel: 'Gezag vragen van een persoon' specificeren voor de consumer developer

    De scenarios opgenomen bij de vorige regels beschrijven het gedrag van de BRP API (wat is de response van de API bij een gegeven context)
    De scenarios beschrijven niet wat de consumer developer moet doen om bijv. het gezag van een persoon te vragen. Met andere woorden, hoe moet de request er uit zien om het gezag van een persoon te vragen.
    Onderstaand scenario is een voorbeeld voor het specificeren van de request voor het vragen van de burgerservicenummer, ouders en gezag velden van de persoon met burgerservicenummer '000000012'  

    Scenario: Vragen van de velden 'burgerservicenummer, ouders en gezag' van een persoon met zijn burgerservicenummer
      Gegeven de url van de personen bevragen endpoint is 'http://localhost/api/haalcentraal/personen'
      Als de velden 'burgerservicenummer,ouders,gezag' van de persoon met burgerservice '000000012' moet worden gevraagd
      Dan ziet de request er als volgt uit
      """
      POST http://localhost/api/haalcentraal/personen
      Accept: application/json
      Content-Type: application/json

      {
        "type": "RaadpleegMetBurgerservicenummer",
        "burgerservicenummer": [ "000000012" ],
        "fields" : [ "burgerservicenummer", "ouders", "gezag" ]
      }
      """
