var express = require('express');
var router = express.Router();

/* GET checkin qr code. */
router.get('/qrcode/:matricula', function(req, res, next) {
  res.status(200).send("Aluno '" + req.params.matricula + "' autenticado com sucesso.")
});

module.exports = router;
