const { Given, DataTable } = require('@cucumber/cucumber');
const {
    aanvullenPersoon,
    aanvullenPersoonMetBsn,
    aanvullenRelatieMetBsn,
    createPersoon,
    createGegevensgroepCollectie,
    createGegevensgroepMetBsn,
    createGegevensgroepCollectieMetBsn,
    wijzigPersoon,
} = require('./persoon');
const { createVerblijfplaats } = require('./verblijfplaats')
const { toDateOrString } = require('./brpDatum');

/**
 * Expressieve Gegeven-stappen voor Persoon
 */

Given(/^(?:de persoon(?: '(.*)')? )?met burgerservicenummer '(\d*)'$/, function (aanduiding, burgerservicenummer) {
    if (this.context.map === undefined) {
        this.context.map = new Map();
    }

    this.context.latestBsn = burgerservicenummer;
    this.context.map.set(aanduiding, burgerservicenummer);

    createPersoon(this.context, burgerservicenummer);

    let data = [
        ['naam', 'waarde'],
        ['geslachtsnaam (02.40)', aanduiding],
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
    let data = [
        ['naam', 'waarde'],
        ['geboorteland (03.30)', '6030'],
        ['aktenummer (81.20)', '1AA0100']
    ];

    aanvullenPersoon(this.context, new DataTable(data));
});

Given(/^is geadopteerd/, function () {
    let data = [
        ['naam', 'waarde'],
        ['aktenummer (81.20)', '1AQ0100']
    ];

    aanvullenPersoon(this.context, new DataTable(data));
});

Given(/^zijn de volgende gegevens gewijzigd$/, function (dataTable) {
    wijzigPersoon(this.context, dataTable);
});

Given(/^zijn de volgende gegevens gecorrigeerd$/, function (dataTable) {
    wijzigPersoon(this.context, dataTable, true);
});

/**
 * Expressieve Gegeven-stappen voor Partner
 */

Given(/^'(.*)' en '(.*)' zijn met elkaar gehuwd/, function (aanduiding1, aanduiding2) {
    bsn1 = this.context.map.get(aanduiding1);
    bsn2 = this.context.map.get(aanduiding2);

    const data1 = [
        ['naam', 'waarde'],
        ['burgerservicenummer (01.20)', bsn2],
        ['geslachtsnaam (02.40)', aanduiding2],
        ['datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)', toDateOrString('gisteren - 2 jaar', false)],
        ['plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20)', '0518'],
        ['land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)', '6030'],
    ];

    const data2 = [
        ['naam', 'waarde'],
        ['burgerservicenummer (01.20)', bsn1],
        ['geslachtsnaam (02.40)', aanduiding1],
        ['datum huwelijkssluiting/aangaan geregistreerd partnerschap (06.10)', toDateOrString('gisteren - 2 jaar', false)],
        ['plaats huwelijkssluiting/aangaan geregistreerd partnerschap (06.20)', '0518'],
        ['land huwelijkssluiting/aangaan geregistreerd partnerschap (06.30)', '6030'],
    ]

    createGegevensgroepMetBsn(this.context, bsn1, 'partner', new DataTable(data1));
    createGegevensgroepMetBsn(this.context, bsn2, 'partner', new DataTable(data2));
});

Given('beide ouders zijn nooit met elkaar getrouwd geweest en hebben nooit een geregistreerd partnerschap gehad', function () {
    // doe niets
});

Given(/^'(.*)' en '(.*)' zijn gescheiden/, function (aanduiding1, aanduiding2) {
    bsn1 = this.context.map.get(aanduiding1);
    bsn2 = this.context.map.get(aanduiding2);

    const data1 = [
        ['naam', 'waarde'],
        ['datum ontbinding huwelijk/geregistreerd partnerschap (07.10)', toDateOrString('gisteren - 1 jaar', false)],
        ['plaats ontbinding huwelijk/geregistreerd partnerschap (07.20)', '0518'],
        ['land ontbinding huwelijk/geregistreerd partnerschap (07.30)', '6030']
    ];

    const data2 = [
        ['naam', 'waarde'],
        ['datum ontbinding huwelijk/geregistreerd partnerschap (07.10)', toDateOrString('gisteren - 1 jaar', false)],
        ['plaats ontbinding huwelijk/geregistreerd partnerschap (07.20)', '0518'],
        ['land ontbinding huwelijk/geregistreerd partnerschap (07.30)', '6030']
    ]

    aanvullenRelatieMetBsn(this.context, bsn1, new DataTable(data1));
    aanvullenRelatieMetBsn(this.context, bsn2, new DataTable(data2));
});

Given(/^is het huwelijk van '(.*)' en '(.*)' gecorrigeerd/, function (aanduiding1, aanduiding2, dataTable) {
    return 'pending';
});

/**
 * Expressieve Gegeven-stappen voor Ouder
 */

Given(/^heeft een ouder '(\d)' met de volgende gegevens$/, function (_, ouderType, dataTable) {
    createGegevensgroepCollectie(this.context, `ouder-${ouderType}`, dataTable);
});

Given(/^heeft een ouder 1 '(.*)' met burgerservicenummer '(\d*)'/, function (aanduiding, burgerservicenummer) {
    if (this.context.map === undefined) {
        this.context.map = new Map();
    }
    this.context.map.set(aanduiding, burgerservicenummer);

    let dataOuder = [
        ['naam', 'waarde'],
        ['geslachtsnaam (02.40)', aanduiding],
    ];

    createGegevensgroepCollectieMetBsn(this.context, `ouder-1`, new DataTable(dataOuder), burgerservicenummer); // met burgerservicenr

    createPersoon(this.context, burgerservicenummer, new DataTable(dataOuder)); // ouder PL

    let dataKind = [
        ['naam', 'waarde'],
        ['geboortedatum (03.10)', '']
    ];

    createGegevensgroepCollectieMetBsn(this.context, `kind`, new DataTable(dataKind), this.context.latestBsn);

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
        ['geslachtsnaam (02.40)', aanduiding]
    ];

    createGegevensgroepCollectieMetBsn(this.context, `ouder-2`, new DataTable(dataOuder), burgerservicenummer); // met burgerservicenr

    createPersoon(this.context, burgerservicenummer, new DataTable(dataOuder)); // ouder PL

    let dataKind = [
        ['naam', 'waarde'],
        ['geboortedatum (03.10)', '']
    ];

    createGegevensgroepCollectieMetBsn(this.context, `kind`, new DataTable(dataKind), this.context.latestBsn);

    let ouder = this.context.sqlData.pop();
    let kind = this.context.sqlData.pop();
    this.context.sqlData.push(ouder);
    this.context.sqlData.push(kind);
});

// Given(/^zijn de volgende gegevens van ouder '(.*)' gewijzigd/, function (aanduiding, dataTable) {
//     // Write code here that turns the phrase above into concrete actions
//     return 'pending';
//   });

Given(/^beide ouders zijn meerderjarig, niet overleden en staan niet onder curatele/, function () {
    // doe niets
});

Given(/^zijn de volgende gegevens van ouder '(.*)' gewijzigd/, function (aanduiding, dataTable) {
    let bsn = this.context.map.get(aanduiding);

    aanvullenPersoonMetBsn(this.context, bsn, dataTable);
});

/**
 * Expressieve Gegeven-stappen voor Verblijfplaats
 */


Given(/^(?:de persoon(?: '(.*)')? )?is ingeschreven in de BRP?$/, function (_) {
    let data = [
        ['naam', 'waarde'],
        ['gemeente van inschrijving (09.10)', '0518']
    ];

    createVerblijfplaats(this.context, new DataTable(data));
});

Given(/^(?:de persoon(?: '(.*)')? )?is ingeschreven in de RNI/, function (_) {
    let data = [
        ['naam', 'waarde'],
        ['gemeente van inschrijving (09.10)', '1999']
    ];

    createVerblijfplaats(this.context, new DataTable(data));
});

Given(/^(?:de persoon(?: '(.*)')? )?is niet geëmigreerd geweest/, function (_) {
    // doe niets
});

Given(/^(?:de persoon(?: '(.*)')? )?is geëmigreerd geweest met de volgende gegevens?$/, function (_, dataTable) {
    createVerblijfplaats(this.context, dataTable);
});

Given(/^(?:de persoon(?: '(.*)')? )?is geëmigreerd geweest?$/, function (_) {
    let data = [
        ['naam', 'waarde'],
        ['datum vestiging in Nederland (14.20)', toDateOrString('vandaag - 1 jaar', true)]
    ];
    createVerblijfplaats(this.context, new DataTable(data));
});