package tdd.valor;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class ValorIluminacaoPublicaTest {

	@Test
	public void testGetValor() {
		ValorIluminacaoPublica valorIluminacaoPublica = new ValorIluminacaoPublica();
		BigDecimal valor = BigDecimal.valueOf(500);
		assertEquals(ValorIluminacaoPublica.CUSTO.add(valor), valorIluminacaoPublica.getValor(valor));
	}

}
