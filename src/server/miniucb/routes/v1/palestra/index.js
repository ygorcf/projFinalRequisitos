var express = require('express');
const responseHelper = require('../../../helpers/responseHelper')
const palestras = require('../../../models/palestras')

var router = express.Router();

/* GET list palestras. */
router.get('/', function(req, res, next) {
  res.status(200).json(responseHelper(true, 200, palestras))
})

/* GET search palestra. */
router.get('/:id', function(req, res, next) {
  res.status(200).json(responseHelper(true, 200, palestras[req.params.id]))
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
    res.status(401).json(responseHelper(true, 401, {
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
    res.status(401).json(responseHelper(true, 401, {
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

/* GET search palestra feedbacks. */
router.get('/:idPalestra/feedback', function(req, res, next) {
  res.status(200).json(responseHelper(true, 200, palestras[req.params.idPalestra].feedbacks))
})

/* POST add palestra. */
router.post('/:idPalestra/feedback', function(req, res, next) {
  var novoFeedback = req.body
  if (typeof novoFeedback.matricula === 'string' && novoFeedback.matricula.length > 0 &&
      typeof novoFeedback.comentario === 'string' && novoFeedback.comentario.length > 0) {
    palestras[req.params.idPalestra].feedbacks.push(novoFeedback)
    res.status(200).json(responseHelper(true, 200, palestras[req.params.idPalestra].feedbacks))
  } else {
    res.status(401).json(responseHelper(true, 401, {
      message: "Novo feedback invalido."
    }))
  }
})

module.exports = router;
