package puc;

import static org.junit.Assert.*;

import org.junit.Test;

public class BuscadorStringTest {

	@Test(expected = IllegalArgumentException.class)
	public void testBuscarCharInString_string_error_empty() {
		BuscadorString.buscarCharInString("      ", (char) 42);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuscarCharInString_string_error_length() {
		BuscadorString.buscarCharInString("0123456789012345678910", (char) 42);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuscarCharInString_string_error_null() {
		BuscadorString.buscarCharInString(null, (char) 42);
	}

	@Test
	public void testBuscarCharInString_valid() {
		assertEquals(0, BuscadorString.buscarCharInString("Pedro", 'P'));
	}

	@Test
	public void testBuscarCharInString_valid_ignore_case() {
		assertEquals(0, BuscadorString.buscarCharInString("Pedro", 'p'));
	}

	@Test
	public void testBuscarCharInString_valid_ignore_whitespace() {
		assertEquals(0, BuscadorString.buscarCharInString("           Pedro             ", 'p'));
	}
	
	@Test
	public void testBuscarCharInString_valid_not_first_char() {
		assertEquals(2, BuscadorString.buscarCharInString("banana", 'n'));
	}

}
