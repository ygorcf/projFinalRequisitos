var express = require('express');
var router = express.Router();

router.use('/palestra', require('./palestra'))
router.use('/usuario', require('./usuario'))

module.exports = router;
