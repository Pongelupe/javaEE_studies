package br.com.pucminas.integracao.exercicio2;

public class Jogador {

	private int somaAtual;
	private int ponto;
	private boolean primeiraJogada = true;
	private Dado dado1;
	private Dado dado2;

	public int rolarDados() {
		ponto = dado1.sortear() + dado2.sortear();
		return ponto;
	}

	public boolean venceuRodada() {
		boolean ganhou = true;
		if (primeiraJogada && (somaAtual == 2 || somaAtual == 3 || somaAtual == 12)) {
			ganhou = false;
		} else if (!primeiraJogada && (somaAtual == 7 && somaAtual == 11)) {
			primeiraJogada = false;
		}
		return ganhou;
	}

	public boolean perdeuRodada() {
		return !venceuRodada();
	}

}
