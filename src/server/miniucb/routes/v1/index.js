var express = require('express');
var router = express.Router();

router.use('/palestra', require('./palestra'))
router.use('/usuario', require('./usuario'))

/* GET generates list routes. */
router.get('/', function(req, res, next) {
  res.render('routes', { 
    routes: [
      require('./palestra').stack.map(v => v.route ? {path: req.originalUrl + '/palestra' + v.route.path, method: v.route.methods} : {}),
      require('./usuario').stack.map(v => v.route ? {path: req.originalUrl + '/usuario' + v.route.path, method: v.route.methods} : {}),
    ]
  });
})

module.exports = router;
