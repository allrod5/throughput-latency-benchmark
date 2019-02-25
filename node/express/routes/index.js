var express = require('express');
var rp = require('request-promise');

var router = express.Router();

async function make_resquests() {
  var responses = [];
  for (let i = 0; i < 100; i++) {
     responses.push(await rp('http://localhost:2000').then((r) => { return r; }));
  }
  return responses;
}

router.get('/', function(req, res, next) {
  make_resquests().then((responses) => {
    res.send(responses.join(','));
  });
});

module.exports = router;
