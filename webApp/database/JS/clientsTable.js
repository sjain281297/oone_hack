/**
 * Created by hackbansu on 28/5/17.
 */
const mysql = require('mysql');
const database = require('./database.js');
const pool = database.pool;
const createQueryHavingWhereClause = database.createQueryHavingWhereClause;

//get required details(via details(array)) of client via identity(object) and call done(result, fields)
function getClientsByIdentity(identity, details, done) {
    let sql = createQueryHavingWhereClause('SELECT ?? FROM clients WHERE', identity);

    pool.getConnection(function (err, connection) {
        if (err) throw err;

        connection.query(sql, [details], function (err, result, fields) {
            connection.release();
            if (err) throw err;

            done(result, fields);
        });
    });
}

module.exports = {
    getClientsByIdentity,
};