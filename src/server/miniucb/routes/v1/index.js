var express = require('express');
var router = express.Router();

router.use('/checkin', require('./checkin'))

module.exports = router;
