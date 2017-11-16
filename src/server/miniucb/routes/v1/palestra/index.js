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
      typeof novaPalestra.horario === 'string' && novaPalestra.horario.match(/[0-9]{2}:[0-9]{2}/) !== null &&
      (novaPalestra.dia instanceof Date || (typeof novaPalestra.dia === 'string' && novaPalestra.dia.match(/[0-9]{4}-[0-9]{2}-[0-9]{2}/) !== null))) {
    if (!(novaPalestra.alunosCheckin instanceof Array)) {
      novaPalestra.alunosCheckin = []
    }
    if (!(novaPalestra.perguntas instanceof Array)) {
      novaPalestra.perguntas = []
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
    res.status(404).json(responseHelper(true, 404, {
      checkin: false, 
      message: "Id da Palestra '" + idPalestra + "' não existe."
    }))
  }
})

module.exports = router;
