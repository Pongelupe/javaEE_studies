package puc;

import java.util.Optional;

public class BuscadorString {

	public static int buscarCharInString(String target, char search) {
		var validString = Optional.ofNullable(target).filter(s -> !s.trim().isEmpty() && s.trim().length() <= 20)
				.map(String::trim)
				.map(String::toLowerCase)
				.orElseThrow(() -> new IllegalArgumentException("A String target deve ter tamanho entre 1 e 20"));

		var index = validString.indexOf(Character.toLowerCase(search));
		if (index == -1) {
			System.out.println("Char não existe na String");
		}
		return index;
	}

}
