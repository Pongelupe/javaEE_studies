# language: pt
Funcionalidade: Alugar filme
	Como um usuário
	Eu quero cadastrar aluguéis de filme
	Para controlar preços e datas de entrega
	
Cenário: Deve alugar um filme com sucesso
	Dado um filme com estoque de 2 unidades
	E que o preço do alugel seja R$ 3
	Quando alugar
	Então o preço do aluguel será de R$ 3
	E a data de entrega será no dia seguinte
	E o estoque do filme será de 1 unidade
	