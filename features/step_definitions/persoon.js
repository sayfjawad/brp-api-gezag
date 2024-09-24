const { createArrayFrom, getPlId } = require('./dataTable2Array');
const { columnNameMap, persoonTypeMap } = require('./brp');

function getNextStapelNr(sqlData, gegevensgroep) {
    let stapelNr = 0;

    Object.keys(sqlData).forEach(function (key) {
        if (key.startsWith(gegevensgroep)) {
            stapelNr = Number(key.replace(`${gegevensgroep}-`, ''));
        }
    });

    return stapelNr + 1;
}

function createInschrijving(context, dataTable, defaultGeheim = false, defaultPlId = undefined) {
    let sqlData = context.sqlData.at(-1);

    let data = [];

    if (defaultPlId !== undefined) {
        data.push(['pl_id', defaultPlId]);
    }
    if (defaultGeheim) {
        data.push(['geheim_ind', '0']);
    }
    if (dataTable !== undefined) {
        data = data.concat(createArrayFrom(dataTable, columnNameMap));
    }

    sqlData['inschrijving'] = [data];
}

function createPersoonTypeData(persoonType, dataTable, burgerservicenummer, stapelNr) {
    let data = [
        [ 'stapel_nr', stapelNr-1 + ''],
        [ 'volg_nr', '0'],
    ];

    if(persoonTypeMap.has(persoonType)){
        data.push([ 'persoon_type', persoonTypeMap.get(persoonType)]);
    }

    if(burgerservicenummer !== undefined) {
        data.push([ 'burger_service_nr', burgerservicenummer]);
    }

    if(dataTable !== undefined) {
        data = data.concat(createArrayFrom(dataTable, columnNameMap))
    };

    return data;
}

function createPersoon(context, burgerservicenummer, dataTable = undefined) {
    if (context.sqlData === undefined) {
        context.sqlData = [];
    }
    context.sqlData.push({});

    createInschrijving(context, undefined, true, getPlId(dataTable));

    let sqlData = context.sqlData.at(-1);

    sqlData['persoon'] = [createPersoonTypeData('persoon', dataTable, burgerservicenummer, 1)];

    return sqlData;
}

function createVoorkomenData(dataTable) {
    return [
        ['volg_nr', '0']
    ].concat(createArrayFrom(dataTable, columnNameMap));
}

function createGegevensgroep(context, gegevensgroep, dataTable) {
    let sqlData = context.sqlData.at(-1);

    if (gegevensgroep === 'inschrijving' || gegevensgroep === 'kiesrecht') {
        createInschrijving(context, dataTable, gegevensgroep !== 'inschrijving');
    }
    else {
        sqlData[gegevensgroep] = [createVoorkomenData(dataTable)];
    }
}

function createPersoonMetGegevensgroep(context, burgerservicenummer, gegevensgroep, dataTable) {
    createPersoon(context, burgerservicenummer, undefined);

    createGegevensgroep(context, gegevensgroep, dataTable);
}

function createGegevensgroepCollectie(context, relatieType, dataTable) {
    let sqlData = context.sqlData.at(-1);

    const stapelNr = getNextStapelNr(sqlData, relatieType);
    sqlData[`${relatieType}-${stapelNr}`] = [createPersoonTypeData(relatieType, dataTable, undefined, stapelNr)];
}

function createGegevensgroepCollectieMetBsn(context, relatieType, dataTable, burger_service_nr) {
    let sqlData = context.sqlData.at(-1);

    const stapelNr = getNextStapelNr(sqlData, relatieType);
    sqlData[`${relatieType}-${stapelNr}`] = [createPersoonTypeData(relatieType, dataTable, burger_service_nr, stapelNr)];
}

function createPersoonMetGegevensgroepCollectie(context, burgerservicenummer, gegevensgroep, dataTable) {
    createPersoon(context, burgerservicenummer, undefined);

    createGegevensgroepCollectie(context, gegevensgroep, dataTable);
}

function createStapelData(dataTable, columnNameMap, stapelNr = 0) {
    return [
        ['stapel_nr', stapelNr - 1 + '']
    ].concat(createArrayFrom(dataTable, columnNameMap));
}

function createStapel(context, gegevensgroep, dataTable) {
    let sqlData = context.sqlData.at(-1);

    const stapelNr = getNextStapelNr(sqlData, gegevensgroep);

    sqlData[`${gegevensgroep}-${stapelNr}`] = [
        createStapelData(dataTable, columnNameMap, stapelNr)
    ];
}

function createPersoonMetStapel(context, burgerservicenummer, gegevensgroep, dataTable) {
    createPersoon(context, burgerservicenummer);

    createStapel(context, gegevensgroep, dataTable);
}

function updatePersoon(context, dataTable) {
    let sqlData = context.sqlData.at(-1);

    sqlData['persoon'][0] = sqlData['persoon'][0].concat(createArrayFrom(dataTable, columnNameMap));
}

function ophogenVolgnr(sqlData, isCorrectie = false) {
    sqlData.forEach(function (data) {
        let volgNr = data.find(el => el[0] === 'volg_nr');
        if (volgNr[1] === '0' && isCorrectie) {
            data.push(['onjuist_ind', 'O']);
        }
        volgNr[1] = Number(volgNr[1]) + 1 + '';
    });
}

function wijzigPersoon(context, dataTable, isCorrectie = false) {
    let sqlData = context.sqlData.at(-1);

    ophogenVolgnr(sqlData['persoon'], isCorrectie);

    sqlData['persoon'].push(createPersoonTypeData('persoon', dataTable, undefined, 1));
}

function wijzigGegevensgroep(context, gegevensgroep, dataTable, isCorrectie = false) {
    let sqlData = context.sqlData.at(-1);

    const foundRelatie = Object.keys(sqlData).findLast(key => key.startsWith(gegevensgroep));

    ophogenVolgnr(sqlData[foundRelatie], isCorrectie);

    const stapelNr = sqlData[foundRelatie][0].find(el => el[0] === 'stapel_nr');

    if (stapelNr !== undefined) {
        sqlData[foundRelatie].push(createPersoonTypeData(gegevensgroep, dataTable, undefined, Number(stapelNr[1]) + 1));
    }
    else {
        sqlData[foundRelatie].push(createVoorkomenData(dataTable));
    }
}

function wijzigGegevensgroepMetBsn(context, gegevensgroep, dataTable, isCorrectie = false, burgerservicenummer) {
    const sqlData = context.sqlData;

    sqlData.forEach(el => {
        let persoonData = el['persoon'][0];

        persoonData.forEach(([key, value]) => {
            if (key === 'burger_service_nr' && value === burgerservicenummer) {
                foundRelatie = el[gegevensgroep];
                console.dir(foundRelatie);
                ophogenVolgnr(sqlData[foundRelatie], isCorrectie);

                const stapelNr = sqlData[foundRelatie][0].find(el => el[0] === 'stapel_nr');

                if (stapelNr !== undefined) {
                    sqlData[foundRelatie].push(createPersoonTypeData(gegevensgroep, dataTable, burgerservicenummer, Number(stapelNr[1]) + 1));
                }
                else {
                    sqlData[foundRelatie].push(createVoorkomenData(dataTable));
                }
            }
        });
    });
}

function aanvullenPersoon(context, dataTable) {
    const bsn = context.latestBsn;
    const latestSqlData = context.sqlData?.at(-1);
    const recordToUpdate = valideerEnOphalenGegevens(latestSqlData, bsn);
    const dataArray = createArrayFrom(dataTable, columnNameMap);

    updateGegevens(recordToUpdate, dataArray);
}

function aanvullenPersoonMetBsn(context, bsn, dataTable) {
    const sqlData = context.sqlData;

    sqlData.forEach(el => {
        let persoonData = el['persoon'][0];

        persoonData.forEach(([key, value]) => {
            if (key === 'burger_service_nr' && value === bsn) {
                const dataArray = createArrayFrom(dataTable, columnNameMap);
                updateGegevens(persoonData, dataArray);

            }
        });
    });
}

function aanvullenRelatieMetBsn(context, bsn, dataTable) {
    const sqlData = context.sqlData;
    sqlData.forEach(el => {

        if (el['partner']) {

            let persoonData = el['partner'][0];

            persoonData.forEach(([key, value]) => {
                if (key === 'burger_service_nr' && value === bsn) {
                    const dataArray = createArrayFrom(dataTable, columnNameMap);
                    updateGegevens(persoonData, dataArray);
                }
            });
        }
    });
}

function valideerEnOphalenGegevens(sqlData, bsn) {
    if (!sqlData) {
        throw new Error("No valid sqlData found.");
    }

    const relatieKey = Object.keys(sqlData).findLast(key => key.startsWith('persoon'));
    if (!relatieKey || !Array.isArray(sqlData[relatieKey])) {
        throw new Error("RelatieKey or corresponding data is invalid.");
    }

    const recordToUpdate = sqlData[relatieKey].find(record =>
        record.some(([columnName, value]) => columnName === 'burger_service_nr' && value === bsn)
    );
    if (!recordToUpdate || !Array.isArray(recordToUpdate)) {
        throw new Error("Record to update not found or is not in the correct format.");
    }

    return recordToUpdate;
}

function updateGegevens(record, dataArray) {
    dataArray.forEach(([newKey, newValue]) => {
        const existingEntry = record.find(([key]) => key === newKey);

        if (existingEntry) {
            existingEntry[1] = newValue;
        } else {
            record.push([newKey, newValue]);
        }
    });
}

function berekenJaar(dateString, offset) {
    let year = parseInt(dateString.slice(0, 4));
    let month = parseInt(dateString.slice(4, 6)) - 1;
    let day = parseInt(dateString.slice(6, 8));
    let date = new Date(year, month, day);
    date.setFullYear(date.getFullYear() + offset);
    let newYear = date.getFullYear();
    let newMonth = ('0' + (date.getMonth() + 1)).slice(-2);
    let newDay = ('0' + date.getDate()).slice(-2);
    return `${newYear}${newMonth}${newDay}`;
}

function createGegevensgroepMetBsn(context, bsn, gegevensgroep, dataTable) {
    const sqlData = context.sqlData;

    sqlData.forEach(el => {
        let persoonData = el['persoon'][0];
        persoonData.forEach(([key, value]) => {
            if (key === 'burger_service_nr' && value === bsn) {
                el[gegevensgroep] = [createVoorkomenData(dataTable)];

                if (persoonTypeMap.has(gegevensgroep)) {
                    el[gegevensgroep][0].push(['persoon_type', persoonTypeMap.get(gegevensgroep)]);
                    el[gegevensgroep][0].push(['stapel_nr', 0]);
                }
            }
        });
    });
}

module.exports = {
    createPersoon,
    createPersoonMetGegevensgroep,
    createPersoonMetStapel,
    createStapel,
    createPersoonMetGegevensgroepCollectie,
    createInschrijving,
    createGegevensgroep,
    createGegevensgroepCollectie,
    updatePersoon,
    wijzigPersoon,
    wijzigGegevensgroep,
    aanvullenPersoon,
    aanvullenPersoonMetBsn,
    berekenJaar,
    updateGegevens,
    createGegevensgroepMetBsn,
    createGegevensgroepCollectieMetBsn,
    aanvullenRelatieMetBsn,
    wijzigGegevensgroepMetBsn
}
