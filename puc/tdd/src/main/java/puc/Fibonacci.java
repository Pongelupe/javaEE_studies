package puc;

public class Fibonacci {

	public static int elemento(int n) {
		int ant1 = 1;
		int ant2 = 1;
		int resultado = 0;
		for (int i = 3; i <= n; i++) {
			resultado = ant1 + ant2;
			ant1 = ant2;
			ant2 = resultado;
		}
		return resultado;

	}

}