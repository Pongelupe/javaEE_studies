package br.com.pucminas.integracao.exercicio2;

public class Jogador {

	private int somaAtual;
	private int ponto;
	private boolean primeiraJogada = true;
	private Dado dado1;
	private Dado dado2;

	public Jogador(Dado dado1, Dado dado2) {
		this.dado1 = dado1;
		this.dado2 = dado2;
	}

	public int rolarDados() {
		somaAtual = dado1.sortear() + dado2.sortear();
		return somaAtual;
	}

	public boolean venceuRodada() {
		boolean resultadoRodada = jogarRodada();
		fimDeRodada();
		return resultadoRodada;
	}

	public boolean perdeuRodada() {
		return !venceuRodada();
	}

	public boolean fimDeRodada() {
		boolean fimRodada = !primeiraJogada;
		if (fimRodada) {
			ponto = 0;
			primeiraJogada = true;
			somaAtual = 0;
		}

		return fimRodada;
	}

	private boolean jogarRodada() {
		boolean ganhou = true;
		rolarDados();
		if (primeiraJogada) {
			primeiraJogada = false;
			if (somaAtual == 2 || somaAtual == 3 || somaAtual == 12) {
				ganhou = false;
			} else if (somaAtual != 7 && somaAtual != 11) {
				ponto = somaAtual;
				return venceuRodada();
			}
		} else {
			if (somaAtual == 7) {
				ganhou = false;
			} else {
				ganhou = somaAtual == ponto || venceuRodada();
			}
		}

		return ganhou;
	}

}
