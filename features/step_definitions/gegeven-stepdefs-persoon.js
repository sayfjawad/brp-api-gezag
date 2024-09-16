const { Given, DataTable } = require('@cucumber/cucumber');
const { createPersoon,
    createPersoonMetGegevensgroep,
    createInschrijving,
    updatePersoon,
    wijzigPersoon,
    aanvullenPersoon, 
    aanvullenPersoonMetBsn } = require('./persoon');
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

Given(/^(?:de persoon(?: '(.*)')? )?met burgerservicenummer '(\d*)'$/, function (aanduiding, burgerservicenummer) {
    if (this.context.map === undefined) {
        this.context.map = new Map();
    }

    this.context.latestBsn = burgerservicenummer;
    this.context.map.set(aanduiding, burgerservicenummer);

    createPersoon(this.context, burgerservicenummer);

    let data = [
        ['naam', 'waarde'],
        ['voornamen (02.10)', aanduiding]
    ];

    aanvullenPersoon(this.context, new DataTable(data));
});

Given(/^is minderjarig/, function () {
    let data = [
        ['naam', 'waarde'],
        ['geboortedatum (03.10)', toDateOrString('gisteren - 17 jaar', false)]
    ];

    aanvullenPersoon(this.context, new DataTable(data));
});

Given(/^is meerderjarig/, function () {
    let data = [
        ['naam', 'waarde'],
        ['geboortedatum (03.10)', toDateOrString('morgen - 35 jaar', false)]
    ];

    aanvullenPersoon(this.context, new DataTable(data));
});

Given(/^is in Nederland geboren/, function () {
    const data = [
        ['naam', 'waarde'],
        ['geboorteland (03.30)', '6030'],
        ['aktenummer (81.20)', '1AA0100']
    ];

    aanvullenPersoon(this.context, new DataTable(data));
});

Given(/^is geadopteerd/, function (dataTable) {
    aanvullenPersoon(this.context, dataTable);
});

Given(/^zijn de volgende gegevens van ouder '(.*)' gewijzigd/, function (aanduiding, dataTable) {
    let bsn = this.context.map.get(aanduiding);

    aanvullenPersoonMetBsn(this.context, bsn, dataTable);
  });