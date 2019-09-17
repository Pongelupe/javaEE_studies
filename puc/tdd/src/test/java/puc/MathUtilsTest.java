package puc;

import static org.junit.Assert.*;

import org.junit.Test;

public class MathUtilsTest {

	@Test
	public void test_squareRoot_exata() {
		var squareRoot = MathUtils.squareRoot(100);
		assertEquals(10, squareRoot);
	}
	
	@Test
	public void test_squareRoot_nao_exata_positiva() {
		var squareRoot = MathUtils.squareRoot(2);
		assertEquals(-1, squareRoot);
	}
	
	@Test
	public void test_squareRoot_nao_exata_negativa() {
		var squareRoot = MathUtils.squareRoot(-2);
		assertEquals(-1, squareRoot);
	}
	
	@Test
	public void test_squareRoot_zero() {
		var squareRoot = MathUtils.squareRoot(0);
		assertEquals(0, squareRoot);
	}

}
