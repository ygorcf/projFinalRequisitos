var express = require('express');
const responseHelper = require('../../../helpers/responseHelper')
const palestras = require('../../../models/palestras')

var router = express.Router();

/* GET list palestras. */
router.get('/', function(req, res, next) {
  res.status(200).json(responseHelper(true, 200, palestras))
})

/* POST add palestra. */
router.post('/', function(req, res, next) {
  var novaPalestra = req.body
  if (typeof novaPalestra.nome === 'string' && novaPalestra.nome.length > 0 &&
      typeof novaPalestra.horario === 'string' && novaPalestra.horario.match(/[0-9]{2}:[0-9]{2}/) &&
      novaPalestra.horario instanceof Date) {
    if (!(novaPalestra.alunosCheckin instanceof Array)) {
      novaPalestra.alunosCheckin = []
    }
    palestras.push(novaPalestra)
    res.status(200).json(responseHelper(true, 200, novaPalestra))
  } else {
    res.status(404).json(responseHelper(true, 404, {
      message: "Nova palestra invalida."
    }))
  }
})

/* GET checkin qr code. */
router.get('/qrcode/:idPalestra/:matricula', function(req, res, next) {
  var idPalestra = req.params.idPalestra
  var palestra = palestras[idPalestra]
  if (palestra !== null && palestra !== undefined) {
    palestra.alunosCheckin.push(req.params.matricula)
    res.status(200).json(responseHelper(true, 200, {
      checkin: true, 
      message: "Aluno '" + req.params.matricula + "' autenticado com sucesso na palestra '" + idPalestra + "'.",
      palestra: palestra
    }))
  } else {
    res.status(401).json(responseHelper(true, 404, {
      checkin: false, 
      message: "Id da Palestra '" + idPalestra + "' não existe."
    }))
  }
})

/* GET generates qr code. */
router.get('/qrcode/:idPalestra', function(req, res, next) {
  var idPalestra = req.params.idPalestra
  res.render('qrcode', { 
    urlCompleta: req.protocol + '://' + req.host + ":" + req.app.settings.port + req.baseUrl + req.url,
    url: req.url
  });
})

module.exports = router;
