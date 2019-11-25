package tdd.valor;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ValorImpostoTest {

	@Test
	public void testValorImposto() {
		ValorImposto v = new ValorImposto();

		assertEquals(BigDecimal.valueOf(130.0), v.getValor(BigDecimal.valueOf(100)));
	}

}
