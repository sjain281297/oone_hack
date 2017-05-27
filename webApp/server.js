/**
 * Created by hackbansu on 28/5/17.
 */
const express = require('express');
const app = express();

app.use('/', function (req, res) {
    res.send('working');
})

app.listen(5000, function () {
    console.log('server started at 5000');
})