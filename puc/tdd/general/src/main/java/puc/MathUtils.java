package puc;

public class MathUtils {

	public static int squareRoot(int x) {
		var squareRoot = -1;
		if (x >= 0) {
			squareRoot++;
			var sub = 1;
			while (x > 0) {
				x -= sub;
				sub += 2;
				squareRoot++;
			}

			return x == 0 ? squareRoot : -1;
		}

		return squareRoot;
	}
}
