//uvoz modula
const express = require('express');
const app = express();
const path = require('path');
const bodyParser = require('body-parser');

//uvoz modula s definiranom funkcionalnosti ruta
const homeRouter = require('./routes/home.routes');
const datatableRouter = require('./routes/datatable.routes');

//middleware - predlošci (ejs)
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

//middleware - statički resursi
app.use(express.static(path.join(__dirname, 'public')));

//middleware - dekodiranje parametara
app.use(express.urlencoded({ extended: true }));

app.use(bodyParser.json());

//definicija ruta
app.use('/', homeRouter);
app.use('/datatable', datatableRouter);

app.listen(3000);