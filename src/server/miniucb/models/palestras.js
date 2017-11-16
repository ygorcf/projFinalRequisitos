var palestras = [
  {
    nome: 'Abertura',
    horario: '08:30',
    dia: new Date(2017, 08, 27),
    alunosCheckin: []
  },
  {
    nome: 'Segurança da Informação',
    horario: '19:30',
    dia: new Date(2017, 08, 27),
    alunosCheckin: [],
	perguntas: [
	  {
	    pergunta: 'O \'Firewall\' é uma parede de fogo?',
		respostas: [
		  {
		    resposta: 'Sim',
			correta: false
		  },
		  {
		    resposta: 'Não',
			correta: true
		  }
		]
	]
  },
  {
    nome: 'Papo de banco de dados',
    horario: '19:30',
    dia: new Date(2017, 08, 29),
    alunosCheckin: [],
	perguntas: [
	  {
		pergunta: 'O comando \'INSERT\' é de que tipo:',
		respostas: [
		  {
		    resposta: 'DDL',
			correta: false
		  },
		  {
		    resposta: 'DML',
			correta: true
		  },
		  {
		    resposta: 'DLC',
			correta: false
		  },
		  {
		    resposta: 'DLC',
			correta: false
		  },
		  {
		    resposta: 'UFC',
			correta: false
		  }
		]
	  },
	  {
		pergunta: 'O que o comando \'COMMIT\' faz:',
		respostas: [
		  {
		    resposta: 'Come',
			correta: false
		  },
		  {
		    resposta: 'Esse comando não existe',
			correta: false
		  },
		  {
		    resposta: 'Deleta tudo',
			correta: false
		  },
		  {
		    resposta: 'Salva o trabalho feito',
			correta: true
		  }
		]
	  },
	  {
		pergunta: 'O comando \'CREATE\' é de que tipo:',
		respostas: [
		  {
		    resposta: 'DDL',
			correta: true
		  },
		  {
		    resposta: 'Criador',
			correta: false
		  },
		  {
		    resposta: 'DML',
			correta: false
		  }
		]
	  }
	]
  }
]

module.exports = palestras
