package tdd;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.Month;

import org.junit.Test;

public class ContaTest {

	@Test
	public void testGetValorConta() {
		Conta conta = new Conta(20, Month.DECEMBER);
		
		assertEquals(BigDecimal.valueOf(3300), conta.getValor());
	}

}
