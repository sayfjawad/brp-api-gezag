# Glossary

## Adoptie

definitie: een persoon (minderjarige) wordt door één of twee (meerdere) personen erkend als kind. Deze personen zijn niet de biologische ouders

VRAAG: kan de datum ingang adoptie niet gelijk zijn aan geboortedatum van het kind? Uit de scenarios en de implementatie van automation maak ik op dat enige de onderscheid tussen een (biologische) ouder en een adoptie ouder de datum ingang familierechtelijke betrekking is.

bij persoonslijst van kind:
- toevoegen van rij in lo3_pl_persoon met persoon_type = 'P', volg_nr = 0, akte_nr = '1AQ0101' + overige gegevens uit rij met volg_nr = 1
- toevoegen van rij in lo3_pl_persoon met persoon_type = '1' of '2', volg_nr = 0, familie_betrek_start_datum = datum ingang adoptie + overige gegevens van ouder die bekend zijn 

bij persoonslijst van ouder(s):
- toevoegen van rij in lo3_pl_persoon met persoon_type = 'K', volg_nr = 0, stapel_nr = aantal kinderen - 1(, familie_betrek_start_datum = datum ingang adoptie?)

## Adoptie is ten onrechte geregistreerd

Adoptie wordt ongedaan gemaakt door

bij persoonslijst van kind
- toevoegen van rij in lo3_pl_persoon met persoon_type = 'P', volg_nr = 0, akte_nr = '1AR0200' + overige gegevens uit rij met volg_nr = 1
- toevoegen van onjuist_ind = 'O' aan rij in lo3_pl_persoon met persoon_type = 'P', volg_nr=1
- indien er een rij is in lo3_pl_persoon met persoon_type = '1'
  - toevoegen van rij in lo3_pl_persoon met persoon_type = '1', volg_nr = 0, akte_nr = '1AR0200'
  - toevoegen van onjuist_ind = 'O' aan rij in lo3_pl_persoon met persoon_type = '1', volg_nr=1
- indien er een rij is in lo3_pl_persoon met persoon_type = '2'
  - toevoegen van rij in lo3_pl_persoon met persoon_type = '2', volg_nr = 0, akte_nr = '1AR0200'
  - toevoegen van onjuist_ind = 'O' aan rij in lo3_pl_persoon met persoon_type = '2', volg_nr=1

bij persoonslijst van ouder(s)
- toevoegen van rij in lo3_pl_persoon met persoon_type = 'K', volg_nr = 0, akte_nr = '1AR0200', stapel_nr = stapel_nr van kind met burgerservicenummer = burgerservicenummer van kind
- toevoegen van onjuist_ind = 'O' aan rij in lo3_pl_persoon met persoon_type = 'K', volg_nr = 1, stapel_nr = stapel_nr van kind met burgerservicenummer = burgerservicenummer van kind

**VRAAG: Volgens 3.1-recente-gebeurtenis-adoptie.feature:61 kan een kind een ouder en een adoptie ouder hebben. Echter zoals aangegeven in Adoptie kan adhv aktenummer '1AQ0101' worden afgeleid dat de persoon is geadopteerd, maar er kan niet worden afgeleid wie de adoptie ouder(s) zijn. In geval van hierboven beschreven scenario zou bij onterechte adoptie beide ouders worden geschrapt terwijl maar één van de ouders adoptie ouder is

## Minderjarige

Een persoon jonger dan 18 jaar (op het moment van bevraging)

## Naamswijziging
Bij de persoonslijst wordt bij de persoon met type 'P' behalve de gewijzigde naam velden een aktenummer (81.20) met waarde '1AW0200' geregistreerd.

## Ouder

Bij de persoonslijst wordt een persoon met type '1' of '2' met datum ingang familierechtelijke betrekking is datum geboorte geregistreerd

## Gezag is van rechtswege bepaald

Gezag wordt bepaald aan de hand van de geregistreerde persoonsgegevens. Hierbij wordt indien aanwezig het gezag bepaald door gerechtelijke uitspraak niet meegenomen

## Gerechtelijke uitspraak voor gezag

Door de rechter is bepaald wie gezag hebben over een persoon

bij persoonslijst van persoon
- toevoegen van rij in lo3_pl_gezagsverhouding met volg_nr = 0, minderjarig_gezag_ind = code gezag, geldigheid_start_datum = datum ingang gezag