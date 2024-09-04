const { Given, DataTable } = require('@cucumber/cucumber');
const { createPersoon,
    createPersoonMetGegevensgroep,
    createInschrijving,
    updatePersoon,
    wijzigPersoon } = require('./persoon');
const { toDateOrString } = require('./brpDatum');

Given(/^de persoon met burgerservicenummer '(\d*)' heeft de volgende gegevens$/, function (burgerservicenummer, dataTable) {
    createPersoon(this.context, burgerservicenummer, dataTable);
});

Given(/^de persoon met burgerservicenummer '(\d*)' heeft de volgende 'inschrijving' gegevens$/, function (burgerservicenummer, dataTable) {
    createPersoonMetGegevensgroep(this.context, burgerservicenummer, 'inschrijving', dataTable);
});

Given(/^de persoon met burgerservicenummer '(\d*)' heeft de volgende 'kiesrecht' gegevens$/, function (burgerservicenummer, dataTable) {
    createPersoonMetGegevensgroep(this.context, burgerservicenummer, 'kiesrecht', dataTable);
});

Given(/^de persoon heeft de volgende 'inschrijving' gegevens$/, function (dataTable) {
    createInschrijving(this.context, dataTable);
});

Given(/^de persoon heeft de volgende gegevens$/, function (dataTable) {
    updatePersoon(this.context, dataTable);
});

Given(/^de persoon is gewijzigd naar de volgende gegevens$/, function (dataTable) {
    wijzigPersoon(this.context, dataTable);
});

Given(/^de persoon is gecorrigeerd naar de volgende gegevens$/, function (dataTable) {
    wijzigPersoon(this.context, dataTable, true);
});

Given(/^de persoon heeft (?:GEEN|geen) '(.*)' gegevens$/, function (_) {
    // doe niets
});

Given(/^(?:de persoon(?: '(.*)')? )?met burgerservicenummer '(\d*)'$/, function (_, burgerservicenummer) {
    createPersoon(this.context, burgerservicenummer);
});

Given(/^(?:de persoon(?: '(.*)')? )?is minderjarig/, function (_) {
    const data = [
        ['naam', 'waarde'],
        ['geboortedatum (03.10)', toDateOrString('gisteren - 17 jaar', false)]
    ];

    wijzigPersoon(this.context, new DataTable(data), true);
});

Given(/^(?:de persoon(?: '(.*)')? )?is meerderjarig/, function (_) {
    const data = [
        ['naam', 'waarde'],
        ['geboortedatum (03.10)', toDateOrString('morgen - 35 jaar', false)]
    ];

    wijzigPersoon(this.context, new DataTable(data), true);
});

Given(/^(?:de persoon(?: '(.*)')? )?is in Nederland geboren/, function (_) {
    const data = [
        ['naam', 'waarde'],
        ['geboorteland (03.30)', '6030'],
        ['aktenummer (81.20)', '1AA0100']
    ];

    wijzigPersoon(this.context, new DataTable(data), true);
});

