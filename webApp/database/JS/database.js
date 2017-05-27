/**
 * Created by hackbansu on 28/5/17.
 */
const mysql = require('mysql');

const dbPoolConf = {
    connectionLimit: 100,
    host: 'localhost',
    user: 'hackbansu',
    password: 'developer6272426',
    database: 'oone_hack',
};
const pool = mysql.createPool(dbPoolConf);

pool.on('acquire', function (connection) {
    console.log('Connection %d acquired', connection.threadId);
});

pool.on('connection', function (connection) {
    connection.query('SET SESSION auto_increment_increment=1')
});

pool.on('enqueue', function () {
    console.log('Waiting for available connections slot');
})

pool.on('release', function (connection) {
    console.log('Connection %d released', connection.threadId);
});


function createQueryHavingWhereClause(sql, identity) {
    let k = 0;
    for (i in identity) {
        if (k != 0) {
            sql += " and";
        } else {
            k++;
        }

        //need 'IS' for null values otherwise '='
        let operator = identity[i] || identity[i] === 0 ? " = " : " IS ";
        sql += " " + mysql.escapeId(i) + operator + mysql.escape(identity[i]);
    }
    // console.log(sql);

    return sql;
}

//exporting pool
module.exports.pool = pool;
module.exports.createQueryHavingWhereClause = createQueryHavingWhereClause;

//exporting things
module.exports = {
    bidsTable: require('./bidsTable.js'),
    clientsTable: require('./clientsTable.js'),
    locationsTable: require('./locationsTable.js'),
    scheduleTable: require('./scheduleTable.js'),
    screensTable: require('./screensTable.js'),
};
