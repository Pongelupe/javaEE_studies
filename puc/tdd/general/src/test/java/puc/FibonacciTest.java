package puc;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class FibonacciTest {

	@Test
	public void testElemento_positivo() {
		int elemento = Fibonacci.elemento(5);
		assertEquals(5, elemento);
	}
	
	@Test
	public void testElemento_zero() {
		int elemento = Fibonacci.elemento(0);
		assertEquals(0, elemento);
	}
	
	@Test
	public void testElemento_negativo() {
		int elemento = Fibonacci.elemento(-12);
		assertEquals(0, elemento);
	}

	@Test
	@Ignore
	public void testElemento_max_integer() {
		int elemento = Fibonacci.elemento(Integer.MAX_VALUE);
		assertEquals(-1, elemento);
	}
	
	@Test
	public void testElemento_min_integer() {
		int elemento = Fibonacci.elemento(Integer.MIN_VALUE);
		assertEquals(0, elemento);
	}
	
	@Test
	public void testElemento_sequencia_inicial() {
		int elemento1 = Fibonacci.elemento(1);
		int elemento2 = Fibonacci.elemento(2);
		
		assertEquals(1, elemento1);
		assertEquals(1, elemento2);
	}
	
	@Test
	public void testElemento_sequencia() {
		int elemento1 = Fibonacci.elemento(5);
		int elemento2 = Fibonacci.elemento(6);
		
		assertEquals(5, elemento1);
		assertEquals(8, elemento2);
	}
	

}
