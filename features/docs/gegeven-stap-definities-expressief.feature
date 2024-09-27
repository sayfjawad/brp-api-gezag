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
    | stap | categorie    | text                                                                                                                                                  | values               |
    | 1    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                    |
    |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr) VALUES($1,$2,$3,$4,$5)                                      | 9999,0,0,P,000000012 |

  Scenario: is minderjarig
    Gegeven de persoon met burgerservicenummer '000000012'
    * is minderjarig
    Dan zijn de gegenereerde SQL statements
    | stap | categorie    | text                                                                                                                                                  | values                                  |
    | 1    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                                       |
    |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geboorte_datum) VALUES($1,$2,$3,$4,$5,$6)                    | 9999,0,0,P,000000012,gisteren - 17 jaar |

  Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' heeft inschrijving
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * is ingeschreven in de BRP
      Dan zijn de gegenereerde SQL statements
      | stap | categorie      | text                                                                                                                                                  | values                  |
      | 1    | inschrijving   | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                       |
      |      | persoon        | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                    | 9999,0,0,P,000000036,P1 |
      |      | verblijfplaats | INSERT INTO public.lo3_pl_verblijfplaats(pl_id,volg_nr,inschrijving_gemeente_code) VALUES($1,$2,$3)                                                   | 9999,0,0518             |

  Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' is in Nederland geboren
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * is in Nederland geboren
      Dan zijn de gegenereerde SQL statements
      | stap | categorie    | text                                                                                                                                                                | values                               |
      | 1    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *               | 0                                    |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,geboorte_land_code,akte_nr) VALUES($1,$2,$3,$4,$5,$6,$7,$8) | 9999,0,0,P,000000036,P1,6030,1AA0100 |

  Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' heeft ouder 1 met burgerservicenummer '[bsn]'
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * heeft een ouder 1 'P2' met burgerservicenummer '000000012'
      Dan zijn de gegenereerde SQL statements
      | stap | categorie    | text                                                                                                                                                  | values                   |
      | 1    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                        |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                    | 9999,0,0,P,000000012,P2  |
      |      | kind-1       | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr) VALUES($1,$2,$3,$4,$5)                                      | 9999,0,0,K,000000036     |
      | 2    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                        |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                    | 10000,0,0,P,000000036,P1 |
      |      | ouder-1-1    | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                    | 10000,0,0,1,000000012,P2 |

  Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' heeft ouder 2 met burgerservicenummer '[bsn]'
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * heeft een ouder 2 'P2' met burgerservicenummer '000000012'
      Dan zijn de gegenereerde SQL statements
      | stap | categorie    | text                                                                                                                                                  | values                   |
      | 1    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                        |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                    | 9999,0,0,P,000000012,P2  |
      |      | kind-1       | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr) VALUES($1,$2,$3,$4,$5)                                      | 9999,0,0,K,000000036     |
      | 2    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                        |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                    | 10000,0,0,P,000000036,P1 |
      |      | ouder-2-1    | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                    | 10000,0,0,2,000000012,P2 |

  #  relatie_start_datum = gisteren - 2 jaar
  Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' heeft ouder 1 en 2 en ouders zijn met elkaar gehuwd
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * heeft een ouder 1 'P2' met burgerservicenummer '000000012'
    * heeft een ouder 2 'P3' met burgerservicenummer '000000024'
    * 'P2' en 'P3' zijn met elkaar gehuwd
      Dan zijn de gegenereerde SQL statements
      | stap | categorie    | text                                                                                                                                                                                                         | values                                      |
      | 1    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                                                        | 0                                           |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                           | 9999,0,0,P,000000012,P2                     |
      |      | partner      | INSERT INTO public.lo3_pl_persoon(pl_id,volg_nr,burger_service_nr,geslachts_naam,relatie_start_datum,relatie_start_plaats,relatie_start_land_code,persoon_type,stapel_nr) VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9) | 9999,0,000000024,P3,20220925,0518,6030,R,0  |
      |      | kind-1       | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr) VALUES($1,$2,$3,$4,$5)                                                                                             | 9999,0,0,K,000000036                        |
      | 2    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                                                        | 0                                           |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                           | 10000,0,0,P,000000024,P3                    |
      |      | partner      | INSERT INTO public.lo3_pl_persoon(pl_id,volg_nr,burger_service_nr,geslachts_naam,relatie_start_datum,relatie_start_plaats,relatie_start_land_code,persoon_type,stapel_nr) VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9) | 10000,0,000000012,P2,20220925,0518,6030,R,0 |
      |      | kind-1       | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr) VALUES($1,$2,$3,$4,$5)                                                                                             | 10000,0,0,K,000000036                       |
      | 3    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                                                        | 0                                           |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                           | 10001,0,0,P,000000036,P1                    |
      |      | ouder-1-1    | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                           | 10001,0,0,1,000000012,P2                    |
      |      | ouder-2-1    | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                           | 10001,0,0,2,000000024,P3                    |

  #  relatie_eind_datum = gisteren - 1 jaar
  Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' heeft ouder 1 en 2 en ouders zijn met elkaar gehuwd en vervolgens gescheiden
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * heeft een ouder 1 'P2' met burgerservicenummer '000000012'
    * heeft een ouder 2 'P3' met burgerservicenummer '000000024'
    * 'P2' en 'P3' zijn gescheiden
      Dan zijn de gegenereerde SQL statements
      | stap | categorie    | text                                                                                                                                                                                                      | values                                      |
      | 1    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                                                     | 0                                           |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                        | 9999,0,0,P,000000012,P2                     |
      |      | partner      | INSERT INTO public.lo3_pl_persoon(pl_id,volg_nr,burger_service_nr,geslachts_naam,relatie_eind_datum,relatie_eind_plaats,relatie_eind_land_code,persoon_type,stapel_nr) VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9) | 9999,0,000000024,P3,20230925,0518,6030,R,0  |
      |      | kind-1       | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr) VALUES($1,$2,$3,$4,$5)                                                                                          | 9999,0,0,K,000000036                        |
      | 2    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                                                     | 0                                           |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                        | 10000,0,0,P,000000024,P3                    |
      |      | partner      | INSERT INTO public.lo3_pl_persoon(pl_id,volg_nr,burger_service_nr,geslachts_naam,relatie_eind_datum,relatie_eind_plaats,relatie_eind_land_code,persoon_type,stapel_nr) VALUES($1,$2,$3,$4,$5,$6,$7,$8,$9) | 10000,0,000000012,P2,20230925,0518,6030,R,0 |
      |      | kind-1       | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr) VALUES($1,$2,$3,$4,$5)                                                                                          | 10000,0,0,K,000000036                       |
      | 3    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *                                                     | 0                                           |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                        | 10001,0,0,P,000000036,P1                    |
      |      | ouder-1-1    | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                        | 10001,0,0,1,000000012,P2                    |
      |      | ouder-2-1    | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                                                                        | 10001,0,0,2,000000024,P3                    |

  Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' is geadopteerd
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * is geadopteerd
      Dan zijn de gegenereerde SQL statements
      | stap | categorie    | text                                                                                                                                                  | values                          |
      | 1    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                               |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,akte_nr) VALUES($1,$2,$3,$4,$5,$6,$7)         | 9999,0,0,P,000000036,P1,1AQ0100 |

  Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' heeft ouder 1 en gegevens van de ouder zijn gewijzigd
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * heeft een ouder 1 'P2' met burgerservicenummer '000000012'
      En zijn de volgende gegevens van ouder 'P2' gewijzigd
      | datum ingang familierechtelijke betrekking (62.10) |
      | 20200101                                           |
      Dan zijn de gegenereerde SQL statements
      | stap | categorie    | text                                                                                                                                                             | values                           |
      | 1    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *            | 0                                |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam,familie_betrek_start_datum) VALUES($1,$2,$3,$4,$5,$6,$7) | 9999,0,0,P,000000012,P2,20200101 |
      |      | kind-1       | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr) VALUES($1,$2,$3,$4,$5)                                                 | 9999,0,0,K,000000036             |
      | 2    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *            | 0                                |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                               | 10000,0,0,P,000000036,P1         |
      |      | ouder-1-1    | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                               | 10000,0,0,1,000000012,P2         |


  Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' heeft gezag uitspraak
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * heeft gezag uitspraak
      | indicatie gezag minderjarige (32.10) | ingangsdatum geldigheid (85.10) |
      | D                                    | 20220701                        |
      Dan zijn de gegenereerde SQL statements
      | stap | categorie        | text                                                                                                                                                  | values                  |
      | 1    | inschrijving     | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                       |
      |      | persoon          | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6)                    | 9999,0,0,P,000000036,P1 |
      |      | gezagsverhouding | INSERT INTO public.lo3_pl_gezagsverhouding(pl_id,volg_nr,minderjarig_gezag_ind,geldigheid_start_datum) VALUES($1,$2,$3,$4)                            | 9999,0,D,20220701       |
