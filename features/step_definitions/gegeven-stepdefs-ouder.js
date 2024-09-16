const { Given, DataTable } = require('@cucumber/cucumber');
const { createPersoonMetGegevensgroepCollectie,
    createGegevensgroepCollectie,
    wijzigGegevensgroep,
    createPersoon } = require('./persoon');
const { toDateOrString } = require('./brpDatum');

Given(/^de persoon met burgerservicenummer '(\d*)' heeft een ouder '(\d)' met de volgende gegevens$/, function (burgerservicenummer, ouderType, dataTable) {
    createPersoonMetGegevensgroepCollectie(this.context, burgerservicenummer, `ouder-${ouderType}`, dataTable);
});

Given(/^de persoon heeft een ouder '(\d)' met de volgende gegevens$/, function (_, ouderType, dataTable) {
    createGegevensgroepCollectie(this.context, `ouder-${ouderType}`, dataTable);
});

Given(/^de ouder '(\d)' is gecorrigeerd naar de volgende gegevens$/, function (ouderType, dataTable) {
    wijzigGegevensgroep(this.context, `ouder-${ouderType}`, dataTable, true);
});

Given(/^de ouder '(\d)' is gewijzigd naar de volgende gegevens$/, function (ouderType, dataTable) {
    wijzigGegevensgroep(this.context, `ouder-${ouderType}`, dataTable);
});

Given(/^(?:de persoon(?: '(.*)')? )?heeft een ouder '(\d)' met de volgende gegevens$/, function (_, ouderType, dataTable) {
    createGegevensgroepCollectie(this.context, `ouder-${ouderType}`, dataTable);
});

Given(/^heeft een ouder '(.*)' met burgerservicenummer '(\d*)'/, function (aanduiding, burgerservicenummer) {
    if (this.context.map === undefined) {
        this.context.map = new Map();
    }
    this.context.map.set(aanduiding, burgerservicenummer);

    let dataOuder = [
        ['naam', 'waarde'],
        ['geboortedatum (03.10)', toDateOrString('gisteren - 30 jaar', false)],
        ['geslachtsnaam (02.40)', "Voorbeeld"],
    ];

    createGegevensgroepCollectie(this.context, `ouder-1`, new DataTable(dataOuder));

    createPersoon(this.context, burgerservicenummer, new DataTable(dataOuder));

    let dataKind = [
        ['naam', 'waarde'],
        ['geboortedatum (03.10)', toDateOrString('gisteren - 17 jaar', false)]
    ];

    createGegevensgroepCollectie(this.context, `kind`, new DataTable(dataKind));

    let ouder = this.context.sqlData.pop();
    let kind = this.context.sqlData.pop();
    this.context.sqlData.push(ouder);
    this.context.sqlData.push(kind);
});

Given(/^heeft een ouder 2 '(.*)' met burgerservicenummer '(\d*)'/, function (aanduiding, burgerservicenummer) {
    if (this.context.map === undefined) {
        this.context.map = new Map();
    }
    this.context.map.set(aanduiding, burgerservicenummer);

    let dataOuder = [
        ['naam', 'waarde'],
        ['geboortedatum (03.10)', toDateOrString('gisteren - 30 jaar', false)],
        ['geslachtsnaam (02.40)', "Voorbeeld"],
    ];

    createPersoon(this.context, burgerservicenummer, new DataTable(dataOuder));
    createGegevensgroepCollectie(this.context, `ouder`, undefined);
});

Given(/^beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele/, function () {
   // doe niets
});

Given(/^zijn de volgende gegevens van ouder '(.*)' gewijzigd/, function (aanduiding, dataTable) {
    let bsn = this.context.map.get(aanduiding);

    aanvullenPersoonMetBsn(this.context, bsn, dataTable);
});
