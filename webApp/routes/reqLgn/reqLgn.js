/**
 * Created by hackbansu on 28/5/17.
 */
const db = require('../../database/JS/database');
const router = require('express').Router;
const route = router();

const routes = {
}

function checkUser(req, res) {
    if(req['user']){
        console.log("User authenticated at " + route.baseUrl);
        next();
    }
    else{
        console.log("User NOT authenticated at " + route.baseUrl);
        res.send('Please login first !!');
    }
}
route.use(checkUser);


module.exports = route;