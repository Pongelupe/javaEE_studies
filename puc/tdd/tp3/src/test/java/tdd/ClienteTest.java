package tdd;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ClienteTest {

	private Cliente cliente;
	private UnidadeFaturada unidadeFaturada = Mockito.mock(UnidadeFaturada.class);
	private Conta conta = Mockito.mock(Conta.class);

	@Before
	public void setUp() {
		this.cliente = new Cliente("fake cpf");
	}

	@Test
	public void testAdicionarUnidadeFaturadaNula() {
		assertFalse(cliente.adicionarUnidadeFaturada(null));
	}

	@Test
	public void testAdicionarUnidadeFaturada() {
		assertTrue(cliente.adicionarUnidadeFaturada(unidadeFaturada));
	}

	@Test
	public void testMaiorValorAPagar() {
		BigDecimal value = BigDecimal.valueOf(1500);
		cliente.adicionarUnidadeFaturada(unidadeFaturada);
		Mockito.when(unidadeFaturada.getContaMensal(Month.JUNE)).thenReturn(conta);
		Mockito.when(conta.getValor()).thenReturn(value);
		
		assertEquals(value, cliente.getMaiorValorAPagar());
	}

}
