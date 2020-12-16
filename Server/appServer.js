const mysql = require('mysql')
const express = require('express')
const bodyParser = require('body-parser')
const cookieParser = require('cookie-parser')
const cookie = require('cookie');


var app = express()
app.use(cookieParser())
app.use(bodyParser.json())
app.use(bodyParser.urlencoded({extended : true}))


const hostname = 'dbinstance.cwl5gqu1wws1.ap-northeast-2.rds.amazonaws.com',
    user = 'jym',
    database = 'BETTER_SUBWAY_DB',
    password = 'jang!78566',
    port = 3306

let conn = mysql.createConnection({
    host: hostname,
    user: user,
    database: database,
    password: password,
    port: port,
});

conn.connect(function (error) {
    if (error) {
        console.log(error)
        console.log('DB Connection is Failed...');
    } else {
        console.log('DB Connection is Success!');
    }
})

var stationController = require('./router/stationController')(app,conn);
var userController = require('./router/userController')(app,conn,cookie);
var ardu = require('./router/arduinoController')(app,conn);
app.listen(3000,function(){
    console.log("Server is running...")
})
