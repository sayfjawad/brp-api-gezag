  #language: nl

  @stap-documentatie
  Functionaliteit: Persoon, Inschrijving, Verblijfplaats gegeven stap definities

  Achtergrond:
  Gegeven de 1e 'SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl' statement heeft als resultaat '9999'
  En de 2e 'SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl' statement heeft als resultaat '10000'
  En de 3e 'SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl' statement heeft als resultaat '10001'

  Scenario: de persoon met burgerservicenummer '[bsn]'
      Gegeven de persoon met burgerservicenummer '000000012'
      Dan zijn de gegenereerde SQL statements
      | stap      | categorie    | text                                                                                                                                                  | values               |
      | persoon-1 | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                    |
      |           | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr) VALUES($1,$2,$3,$4,$5)                                      | 9999,0,0,P,000000012 |

  Scenario: is minderjarig
    Gegeven de persoon met burgerservicenummer '000000012'
    * is minderjarig
    Dan zijn de gegenereerde SQL statements
    | stap      | categorie    | text                                                                                                                                                  | values                                  |
    | persoon-1 | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                                       |
    |           | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geboorte_datum) VALUES($1,$2,$3,$4,$5,$6)                    | 9999,0,0,P,000000012,gisteren - 17 jaar |

  Scenario: is ingeschreven in de BRP
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * is ingeschreven in de BRP
    Dan zijn de gegenereerde SQL statements
    | stap       | categorie      | text                                                                                                                                                  | values                  |
    | persoon-P1 | inschrijving   | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                       |
    |            | persoon        | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                    | 9999,0,0,P,000000036,P1 |
    |            | verblijfplaats | INSERT INTO public.lo3_pl_verblijfplaats(pl_id,volg_nr,inschrijving_gemeente_code) VALUES($1,$2,$3)                                                   | 9999,0,0518             |

  Scenario: is in Nederland geboren
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * is in Nederland geboren
    Dan zijn de gegenereerde SQL statements
    | stap       | categorie    | text                                                                                                                                                                | values                               |
    | persoon-P1 | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *               | 0                                    |
    |            | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,geboorte_land_code,akte_nr) VALUES($1,$2,$3,$4,$5,$6,$7,$8) | 9999,0,0,P,000000036,P1,6030,1AA0100 |

  Abstract Scenario: heeft '[aanduiding]' als ouder [1 of 2]
    Gegeven de persoon 'P2' met burgerservicenummer '000000012' 
    En de persoon 'P1' met burgerservicenummer '000000036'
    * heeft 'P2' als ouder <ouder type>
      Dan zijn de gegenereerde SQL statements
      | stap       | categorie          | text                                                                                                                                                             | values                                                 |
      | persoon-P2 | inschrijving       | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *            | 0                                                      |
      |            | persoon            | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                               | 9999,0,0,P,000000012,P2                                |
      |            | kind-1             | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                               | 9999,0,0,K,000000036,P1                                |
      | persoon-P1 | inschrijving       | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *            | 0                                                      |
      |            | persoon            | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                               | 10000,0,0,P,000000036,P1                               |
      |            | ouder-<ouder type> | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,familie_betrek_start_datum) VALUES($1,$2,$3,$4,$5,$6,$7) | 10000,0,0,<ouder type>,000000012,P2,gisteren - 17 jaar |

    Voorbeelden:
    | ouder type |
    | 1          |
    | 2          |

  Scenario: '[aanduiding 1]' en '[aanduiding 2]' zijn met elkaar gehuwd
    Gegeven de persoon 'P2' met burgerservicenummer '000000012'
    En de persoon 'P3' met burgerservicenummer '000000024'
    * 'P2' en 'P3' zijn met elkaar gehuwd
    Dan zijn de gegenereerde SQL statements
    | stap       | categorie    | text                                                                                                                                                                                                         | values                                                |
    | persoon-P2 | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                                                        | 0                                                     |
    |            | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                           | 9999,0,0,P,000000012,P2                               |
    |            | partner-1    | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,relatie_start_datum,relatie_start_plaats,relatie_start_land_code) VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9) | 9999,0,0,R,000000024,P3,gisteren - 20 jaar,0518,6030  |
    | persoon-P3 | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                                                        | 0                                                     |
    |            | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                           | 10000,0,0,P,000000024,P3                              |
    |            | partner-1    | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,relatie_start_datum,relatie_start_plaats,relatie_start_land_code) VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9) | 10000,0,0,R,000000012,P2,gisteren - 20 jaar,0518,6030 |

  Scenario: '[aanduiding 1]' en '[aanduiding 2]' zijn gescheiden
    Gegeven de persoon 'P2' met burgerservicenummer '000000012'
    En de persoon 'P3' met burgerservicenummer '000000024'
    * 'P2' en 'P3' zijn met elkaar gehuwd
    * 'P2' en 'P3' zijn gescheiden
    Dan zijn de gegenereerde SQL statements
    | stap       | categorie    | text                                                                                                                                                                                                         | values                                                |
    | persoon-P2 | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                                                        | 0                                                     |
    |            | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                           | 9999,0,0,P,000000012,P2                               |
    |            | partner-1    | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,relatie_start_datum,relatie_start_plaats,relatie_start_land_code) VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9) | 9999,0,1,R,000000024,P3,gisteren - 20 jaar,0518,6030  |
    |            | partner-1    | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,relatie_eind_datum,relatie_eind_plaats,relatie_eind_land_code) VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9)    | 9999,0,0,R,000000024,P3,gisteren - 1 jaar,0518,6030   |
    | persoon-P3 | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                                                        | 0                                                     |
    |            | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                           | 10000,0,0,P,000000024,P3                              |
    |            | partner-1    | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,relatie_start_datum,relatie_start_plaats,relatie_start_land_code) VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9) | 10000,0,1,R,000000012,P2,gisteren - 20 jaar,0518,6030 |
    |            | partner-1    | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,relatie_eind_datum,relatie_eind_plaats,relatie_eind_land_code) VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9)    | 10000,0,0,R,000000012,P2,gisteren - 1 jaar,0518,6030  |

  Scenario: heeft ouder 1 met burgerservicenummer en gewijzigde gegevens
    Gegeven de persoon 'P2' met burgerservicenummer '000000012'
    En de persoon 'P1' met burgerservicenummer '000000036'
    * heeft 'P2' als ouder 1
    * zijn van ouder 1 de volgende gegevens gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | 20200101                                           |
    Dan zijn de gegenereerde SQL statements
    | stap       | categorie    | text                                                                                                                                                             | values                                      |
    | persoon-P2 | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *            | 0                                           |
    |            | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                               | 9999,0,0,P,000000012,P2                     |
    |            | kind-1       | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                               | 9999,0,0,K,000000036,P1                     |
    | persoon-P1 | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *            | 0                                           |
    |            | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                               | 10000,0,0,P,000000036,P1                    |
    |            | ouder-1      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,familie_betrek_start_datum) VALUES($1,$2,$3,$4,$5,$6,$7) | 10000,0,1,1,000000012,P2,gisteren - 17 jaar |
    |            | ouder-1      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,familie_betrek_start_datum) VALUES($1,$2,$3,$4,$5,$6,$7) | 10000,0,0,1,000000012,P2,20200101        |


  Scenario: heeft gezag uitspraak
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | 20220701                        |
      Dan zijn de gegenereerde SQL statements
      | stap       | categorie        | text                                                                                                                                                  | values                  |
      | persoon-P1 | inschrijving     | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                       |
      |            | persoon          | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                    | 9999,0,0,P,000000036,P1 |
      |            | gezagsverhouding | INSERT INTO public.lo3_pl_gezagsverhouding(pl_id,volg_nr,minderjarig_gezag_ind,geldigheid_start_datum) VALUES($1,$2,$3,$4)                            | 9999,0,D,20220701       |
