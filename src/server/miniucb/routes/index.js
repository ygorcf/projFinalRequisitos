var express = require('express');
var router = express.Router();

router.use('/api/v1', require('./v1'))

/* GET home page. *
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
}); */

module.exports = router;
