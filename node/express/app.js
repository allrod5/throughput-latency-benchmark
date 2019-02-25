var express = require('express');

var indexRouter = require('./routes/index');

var app = express();

console.log('heyy');

app.use('/', indexRouter);
app.listen(1995);

module.exports = app;
