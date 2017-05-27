/**
 * Created by hackbansu on 28/5/17.
 */
const mysql = require('mysql');
const database = require('./database.js');
const pool = database.pool;
const createQueryHavingWhereClause = database.createQueryHavingWhereClause;


//insert a new client and call done(err, result, fields)
function insertNewClient(name, email, video_url, password, done) {
    let clientObj = {
        name: name,
        email: email,
        password: password
    };

    if(video_url){
        clientObj['video_url']  = video_url;
    }

    pool.getConnection(function (err, connection) {
        if (err) throw err;

        connection.query('INSERT INTO clients SET ?', [clientObj], function (err, result, fields) {
            connection.release();

            done(err, result, fields);
        })
    })
}

module.exports = {
    insertNewClient,
};