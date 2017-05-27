/**
 * Created by hackbansu on 28/5/17.
 */
const db = require('../../database/JS/database');
const express = require('express')
const router = express.Router;
const route = router();
const path = require('path')

const routes = {
}

function checkUser(req, res, next) {
    if(req['user']){
        console.log("User authenticated at " + route.baseUrl);
        next();
    }
    else{
        console.log("User NOT authenticated at " + route.baseUrl);
        res.redirect('/')
    }
}
route.use(checkUser);

route.use('/',express.static(path.join(__dirname, '../../public_html')));

module.exports = route;