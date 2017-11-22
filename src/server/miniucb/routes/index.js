var express = require('express');
var router = express.Router();

router.use('/api/v1', require('./v1'))

/* GET home page. *
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
}); */

/* GET generates list routes. */
router.get('/', function(req, res, next) {
  res.render('routes', { 
    routes: [
      [{path: req.originalUrl + 'api/v1', method: {get: true}}]
    ]
  });
})

module.exports = router;
