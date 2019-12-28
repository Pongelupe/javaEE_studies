package tdd.valor;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ValorServicoTest {

	@Test
	public void testValor() {
		ValorServico valorServico = new ValorServico(100);
		assertEquals(BigDecimal.valueOf(5001), valorServico.getValor(BigDecimal.ZERO));
	}

}
