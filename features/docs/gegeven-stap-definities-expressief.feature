    #language: nl

    @stap-documentatie
    Functionaliteit: Persoon, Inschrijving, Verblijfplaats gegeven stap definities

    Achtergrond:
    Gegeven de 1e 'SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl' statement heeft als resultaat '9999'
    En de 2e 'SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl' statement heeft als resultaat '10000'

    Scenario: de persoon '[aanduiding]' met burgerservicenummer '[bsn]' heeft inschrijving
        Gegeven de persoon 'Jaimy' met burgerservicenummer '000000036'
        * is ingeschreven in de BRP
            Dan zijn de gegenereerde SQL statements
            | stap | categorie      | text                                                                                                                                                  | values                     |
            | 1    | inschrijving   | INSERT INTO public.lo3_pl(pl_id,mutatie_dt,geheim_ind) VALUES((SELECT COALESCE(MAX(pl_id), 0)+1 FROM public.lo3_pl),current_timestamp,$1) RETURNING * | 0                          |
            |      | persoon        | INSERT INTO public.lo3_pl_persoon(pl_id,stapel_nr,volg_nr,persoon_type,burger_service_nr,voor_naam) VALUES($1,$2,$3,$4,$5,$6)                         | 9999,0,0,P,000000036,Jaimy |
            |      | verblijfplaats | INSERT INTO public.lo3_pl_verblijfplaats(pl_id,volg_nr,inschrijving_gemeente_code) VALUES($1,$2,$3)                                                   | 9999,0,0518                |