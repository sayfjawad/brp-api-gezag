  #language: nl

  @stap-documentatie
  Functionaliteit: Persoon, Inschrijving, Verblijfplaats gegeven stap definities

  Achtergrond:
  Gegeven de 1e 'SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl' statement heeft als resultaat '9999'
  En de 2e 'SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl' statement heeft als resultaat '10000'

  Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' heeft inschrijving
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * is ingeschreven in de BRP
      Dan zijn de gegenereerde SQL statements
      | stap | categorie      | text                                                                                                                                                  | values                  |
      | 1    | inschrijving   | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                       |
      |      | persoon        | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,voor_naam) VALUES($1,$2,$3,$4,$5,$6)                         | 9999,0,0,P,000000036,P1 |
      |      | verblijfplaats | INSERT INTO public.lo3_pl_verblijfplaats(pl_id,volg_nr,inschrijving_gemeente_code) VALUES($1,$2,$3)                                                   | 9999,0,0518             |

  Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' heeft inschrijving
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * is in Nederland geboren
      Dan zijn de gegenereerde SQL statements
      | stap | categorie    | text                                                                                                                                                           | values                               |
      | 1    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING *          | 0                                    |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,voor_naam,geboorte_land_code,akte_nr) VALUES($1,$2,$3,$4,$5,$6,$7,$8) | 9999,0,0,P,000000036,P1,6030,1AA0100 |

# ouder 1 is meerderjarig met geboorte_datum [gisteren - 30 jaar]
  Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' heeft inschrijving
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * heeft een ouder 1 'P2' met burgerservicenummer '000000012'
      Dan zijn de gegenereerde SQL statements
      | stap | categorie    | text                                                                                                                                                  | values                                  |
      | 1    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                                       |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,voor_naam) VALUES($1,$2,$3,$4,$5,$6)                         | 9999,0,0,P,000000036,P1                 |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geboorte_datum,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6,$7)  | 9999,0,0,P,000000012,19940922,Voorbeeld |

# ouder 2 is meerderjarig met geboorte_datum [gisteren - 30 jaar]
  Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' heeft inschrijving
    Gegeven de persoon 'P1' met burgerservicenummer '000000036'
    * heeft een ouder 2 'P2' met burgerservicenummer '000000012'
      Dan zijn de gegenereerde SQL statements
      | stap | categorie    | text                                                                                                                                                  | values                                  |
      | 1    | inschrijving | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                                       |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,voor_naam) VALUES($1,$2,$3,$4,$5,$6)                         | 9999,0,0,P,000000036,P1                 |
      |      | persoon      | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,geboorte_datum,geslachts_naam) VALUES($1,$2,$3,$4,$5,$6,$7)  | 9999,0,0,P,000000012,19940922,Voorbeeld |