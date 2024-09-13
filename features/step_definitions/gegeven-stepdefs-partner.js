const { Given, DataTable } = require('@cucumber/cucumber');
const { createPersoonMetGegevensgroepCollectie,
    createGegevensgroepCollectie,
    wijzigGegevensgroep,
    createGegevensgroepMetBsn } = require('./persoon');
const { toDateOrString } = require('./brpDatum');

Given(/^de persoon met burgerservicenummer '(\d*)' heeft een 'partner' met de volgende gegevens$/, function (burgerservicenummer, dataTable) {
    createPersoonMetGegevensgroepCollectie(this.context, burgerservicenummer, 'partner', dataTable);
});

Given(/^de persoon heeft ?(?:nog)? een '?(?:ex-)?partner' met ?(?:alleen)? de volgende gegevens$/, async function (dataTable) {
    createGegevensgroepCollectie(this.context, 'partner', dataTable);
});

Given(/^de persoon heeft geen (?:actuele partner|\(ex\)partner)$/, function () {
    // doe niks
});

Given(/^de persoon heeft nooit een actueel of ontbonden huwelijk of partnerschap gehad$/, function () {
    // doe niets
});

Given(/^de 'partner' is gewijzigd naar de volgende gegevens$/, async function (dataTable) {
    wijzigGegevensgroep(this.context, 'partner', dataTable);
});

Given(/^de 'partner' is gecorrigeerd naar de volgende gegevens$/, async function (dataTable) {
    wijzigGegevensgroep(this.context, 'partner', dataTable, true);
});

Given(/^'(.*)' en '(.*)' zijn met elkaar gehuwd/, function (aanduiding1, aanduiding2) {
    bsn1 = this.context.map.get(aanduiding1);
    bsn2 = this.context.map.get(aanduiding2);

    const data1 = [
        ['naam', 'waarde'],
        ['burgerservicenummer (01.20)', bsn2],
        ['geslachtsnaam (02.40)', aanduiding2],
        ['geboortedatum (03.10)', toDateOrString('morgen - 35 jaar', false)],
        ['datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)', toDateOrString('gisteren - 20 jaar', false)],
        ['plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20)', '0518'],
        ['land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)', '6030'],
    ];

    const data2 = [
        ['naam', 'waarde'],
        ['burgerservicenummer (01.20)', bsn1],
        ['geslachtsnaam (02.40)', aanduiding1],
        ['geboortedatum (03.10)', toDateOrString('morgen - 35 jaar', false)],
        ['datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)', toDateOrString('gisteren - 20 jaar', false)],
        ['plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20)', '0518'],
        ['land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)', '6030'],
    ]

    createGegevensgroepMetBsn(this.context, bsn1, 'partner', new DataTable(data1));
    createGegevensgroepMetBsn(this.context, bsn2, 'partner', new DataTable(data2));
});