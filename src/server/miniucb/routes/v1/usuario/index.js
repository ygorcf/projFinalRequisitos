var express = require('express');
const responseHelper = require('../../../helpers/responseHelper')

var router = express.Router();

/* POST login. */
router.post('/login', function(req, res, next) {
  var matricula = req.body.matricula
  if (matricula.match(/^UC[0-9]{8}/) !== null) {
    res.status(200).json(responseHelper(true, 200, "Login realizado com sucesso.", {
      logged: true,
      matricula: matricula,
      userType: 'a'
    }))
  } else if (matricula.match(/^(UC)?[0-9]{6}/) !== null) {
    res.status(200).json(responseHelper(true, 200, "Login realizado com sucesso.", {
      logged: true,
      matricula: matricula,
      userType: 'd'
    }))
  } else if (matricula.match(/.*@.*\.com/) !== null) {
    res.status(200).json(responseHelper(true, 200, "Login realizado com sucesso.", {
      logged: true,
      matricula: matricula,
      userType: 'p'
    }))
  } else {
    res.status(404).json(responseHelper(false, 404, "Falha ao realizar o login. Matricula invalida.", null))
  }
});

module.exports = router;
