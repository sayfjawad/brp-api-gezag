  #language: nl

  @stap-documentatie
  Functionaliteit: Persoon is erkend

  Achtergrond:
  Gegeven de 1e 'SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl' statement heeft als resultaat '9999'
  En de 2e 'SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl' statement heeft als resultaat '10000'
  En de 3e 'SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl' statement heeft als resultaat '10001'

  Abstract Scenario: is erkend door '[aanduiding]' als ouder [1 of 2] met erkenning bij geboorteaangifte
    Gegeven de persoon 'P2' met burgerservicenummer '000000012'
    En de persoon 'P1' met burgerservicenummer '000000036'
    * is geboren op 01-01-2024
    * is erkend door 'P2' als ouder <ouder type> met erkenning bij geboorteaangifte
    Dan zijn de gegenereerde SQL statements    
    | stap       | categorie          | text                                                                                                                                                                        | values                                                |
    | persoon-P2 | inschrijving       | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                       | 0                                                     |
    |            | persoon            | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                          | 9999,0,0,P,000000012,P2                               |
    |            | kind-1             | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                          | 9999,0,0,K,000000036,P1                               |
    | persoon-P1 | inschrijving       | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                       | 0                                                     |
    |            | persoon            | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,geboorte_datum) VALUES($1,$2,$3,$4,$5,$6,$7)                        | 10000,0,1,P,000000036,P1,20240101                     |
    |            | persoon            | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,geboorte_datum,akte_nr) VALUES($1,$2,$3,$4,$5,$6,$7,$8)             | 10000,0,0,P,000000036,P1,20240101,1AB0100             |
    |            | ouder-<ouder type> | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,akte_nr,familie_betrek_start_datum) VALUES($1,$2,$3,$4,$5,$6,$7,$8) | 10000,0,0,<ouder type>,000000012,P2,1AB0100,20240101  |

    Voorbeelden:
    | ouder type |
    | 1          |
    | 2          |

  Abstract Scenario: is erkend door '[aanduiding]' als ouder [1 of 2] met erkenning op (\d*)-(\d*)-(\d*)
    Gegeven de persoon 'P2' met burgerservicenummer '000000012'
    En de persoon 'P1' met burgerservicenummer '000000036'
    * is geboren op 01-01-2024
    * <stap>
    Dan zijn de gegenereerde SQL statements    
    | stap       | categorie          | text                                                                                                                                                                        | values                                                               |
    | persoon-P2 | inschrijving       | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                       | 0                                                                    |
    |            | persoon            | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                          | 9999,0,0,P,000000012,P2                                              |
    |            | kind-1             | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                          | 9999,0,0,K,000000036,P1                                              |
    | persoon-P1 | inschrijving       | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                       | 0                                                                    |
    |            | persoon            | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,geboorte_datum) VALUES($1,$2,$3,$4,$5,$6,$7)                        | 10000,0,1,P,000000036,P1,20240101                                    |
    |            | persoon            | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,geboorte_datum,akte_nr) VALUES($1,$2,$3,$4,$5,$6,$7,$8)             | 10000,0,0,P,000000036,P1,20240101,<aktenummer>                       |
    |            | ouder-<ouder type> | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,akte_nr,familie_betrek_start_datum) VALUES($1,$2,$3,$4,$5,$6,$7,$8) | 10000,0,0,<ouder type>,000000012,P2,<aktenummer>,<erkennings-datum>  |

    Voorbeelden:
    | ouder type | stap                                                                                   | erkennings-datum | aktenummer |
    | 1          | is erkend door 'P2' als ouder 1 met erkenning na geboorteaangifte op 2-1-2024          | 20240102         | 1AC0100    |
    | 1          | is erkend door 'P2' als ouder 1 met erkenning bij notariële akte op 2-1-2024           | 20240102         | 1AJ0100    |
    | 1          | is erkend door 'P2' als ouder 1 met gerechtelijke vaststelling ouderschap op 2-1-2024  | 20240102         | 1AV0100    |



