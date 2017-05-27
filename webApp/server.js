/**
 * Created by hackbansu on 28/5/17.
 */
const express = require('express');
const bodyParser = require('body-parser');
const multer = require('multer');
const cookieParser = require('cookie-parser');
const passport = require('passport');
const passportLocal = require('passport-local');
const session = require('express-session');
const path = require('path');
const database = require('./database/JS/database.js');

const app = express();
const LocalStrategy = passportLocal.Strategy;


passport.use(new LocalStrategy({
    usernameField: 'email',
    passwordField: 'password',
}, function (email, password, done) {
    console.log("Checking credentials");

    database.clientsTable.getClientsByIdentity({
        email: email,
        password: password
    }, ["id", "email", "name"], function (result) {
        if (!result[0]) {
            console.log("Invalid email or password");
            done(null, false, {message: "Invalid email or password"})
        }
        else {
            console.log("successfully logged in");
            done(null, result[0], {message: "SUCCESS"})
        }
    })
}));

passport.serializeUser(function (user, done) {
    return done(null, user.id);
})
passport.deserializeUser(function (id, done) {
    database.clientsTable.getClientsByIdentity({id: id}, ["id", "email", "name"], function (result, fields) {
        return done(null, result[0]);
    })
})

app.use(cookieParser());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
app.use(session({
    secret: "bitvert secret key",
    resave: false,
    saveUninitialized: false,
}))
app.use(passport.initialize());
app.use(passport.session())

app.post('/api/loginauth', passport.authenticate('local', {
    successRedirect: '/bidingareana',
    failureRedirect: '/'
}));

app.use('/',express.static(path.join(__dirname, '/public_html/home')));
app.use('/', require('./routes/reqLgn/reqLgn'));

app.listen(5000, function () {
    console.log('server started at 5000');
})