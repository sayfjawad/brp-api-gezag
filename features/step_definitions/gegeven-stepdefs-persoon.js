const { Given, DataTable } = require('@cucumber/cucumber');
const { createPersoon,
    createPersoonMetGegevensgroep,
    createInschrijving,
    updatePersoon,
    wijzigPersoon } = require('./persoon');
const { createVerblijfplaats } = require('./verblijfplaats');
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

Given(/^(?:de persoon(?: '(.*)')? )?is ingeschreven in de BRP(?: met de volgende gegevens)?$/, function (_, dataTable) {
    if (dataTable && dataTable instanceof DataTable) {
        createInschrijving(this.context, dataTable);
    } else {
        const data = [
            ['naam', 'waarde'],
            ['gemeente van inschrijving (09.10)', '518']
        ];
        createInschrijving(this.context, new DataTable(data));
    }
});

Given(/^(?:de persoon(?: '(.*)')? )?is ingeschreven in de RNI/, function (_) {
    const data = [
        ['naam', 'waarde'],
        ['gemeente van inschrijving (09.10)', '1999']
    ];

    createInschrijving(this.context, new DataTable(data));
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

Given(/^(?:de persoon(?: '(.*)')? )?is niet geëmigreerd geweest/, function (_) {
    // doe niets
});

Given(/^(?:de persoon(?: '(.*)')? )?is geëmigreerd geweest(?: met de volgende gegevens)?$/, function (_, dataTable) {
    
    if (dataTable && dataTable instanceof DataTable) {
        createVerblijfplaats(this.context, dataTable);
    }else {
        const data = [
            ['naam', 'waarde'],
            ['datum vestiging in Nederland (14.20)', toDateOrString('vandaag - 10 jaar', true)]
        ];
        createVerblijfplaats(this.context, new DataTable(data));
    }
});