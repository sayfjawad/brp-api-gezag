const { tableNameMap } = require('./brp');

function mustLog(result) {
    return (result.rowCount === null || result.rowCount === 0) && global.scenario.tags.some(t => ['@protocollering'].includes(t));
}

async function executeAndLogStatement(client, statement) {
    global.logger.info('execute', statement);

    try {
        const result = await client.query(statement.text, statement.values);

        if(mustLog(result)) {
            global.logger.warn(`${global.scenario.name}. 0 rows affected`, sqlStatement);
        }

        return result;
    }
    catch(ex) {
        global.logger.error(`exception in ${global.scenario.name}`, statement, ex);
        throw ex;
    }
}

async function executeInsertInschrijving(client, statement) {
    const result = await executeAndLogStatement(client, statement);

    return result.rows[0]['pl_id'];
}

async function executeStatements(client, statements) {
    let pkId;

    for(const statement of statements) {
        if(statement.categorie === 'inschrijving') {
            pkId = await executeInsertInschrijving(client, statement);
        }
        else {
            statement.values[0] = pkId;
            await executeAndLogStatement(client, statement);
        }
    }

    return pkId;
}

async function execute(sqlStatements) {
    if(!global.pool) {
        global.logger.info('geen pool');
        return;
    }

    const client = await global.pool.connect();
    try {
        await client.query('BEGIN');

        for(let persoon of sqlStatements.personen) {
            persoon.plId = await executeStatements(client, persoon.statements);
        }

        await client.query('COMMIT');
    }
    catch(ex) {
        global.logger.error(ex.message);
        await client.query('ROLLBACK');
    }
    finally {
        client?.release();
    }
}

function deleteStatement(tabelNaam, id) {
    return {
        text: `DELETE FROM public.${tableNameMap.get(tabelNaam)}`,
        values: []
    }
}

async function executeAndLogDeleteStatement(client, tabelNaam, id=undefined) {
    return await executeAndLogStatement(client, deleteStatement(tabelNaam, id));
}

async function deleteAllRowsInAllTables(client) {
    global.logger.debug('delete all rows in all tables');

    for(const [key] of tableNameMap) {
        await executeAndLogDeleteStatement(client, key);
    }
}

async function rollback(sqlContext, sqlStatements) {
    if(!global.pool) {
        global.logger.info('geen pool');
        return;
    }

    if(!sqlContext.cleanup) {
        global.logger.info('geen cleanup');
        return;
    }

    const client = await global.pool.connect();
    try {
        await deleteAllRowsInAllTables(client);
    }
    catch(ex) {
        global.logger.error(ex.stack);
    }
    finally {
        client?.release();
    }
}

module.exports = {
    execute,
    rollback
}