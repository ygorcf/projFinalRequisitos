var palestras = [
  {
    nome: 'Abertura',
    horario: '08:30',
    dia: new Date(2017, 10, 22).getTime(),
    alunosCheckin: [],
    feedbacks: [{matricula: 'ygor', comentario: 'Ótima Palestra'}]
  },
  {
    nome: 'Segurança da Informação',
    horario: '19:30',
    dia: new Date(2017, 10, 22).getTime(),
    alunosCheckin: [],
    feedbacks: [],
	perguntas: [
	  {
	    pergunta: 'O \'Firewall\' é uma parede de fogo?',
		valor: 100,
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
	],
	ranking: []
  },
  {
    nome: 'Papo de banco de dados',
    horario: '19:30',
    dia: new Date(2017, 10, 24).getTime(),
    alunosCheckin: [],
    feedbacks: [],
	perguntas: [
	  {
		pergunta: 'O comando \'INSERT\' é de que tipo:',
		valor: 30,
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
		valor: 45,
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
		valor: 25,
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
	],
	ranking: []
  }
]

module.exports = palestras
