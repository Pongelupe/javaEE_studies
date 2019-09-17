package br.com.pucminas.integracao.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BaralhoTest {

	Baralho baralho;
	@Mock
	private GeradorNumeroAleatorio gerador;

	GeradorNumeroAleatorio mockGerador = mock(GeradorNumeroAleatorio.class);

	/**
	 * Fixture initialization (common initialization for all tests).
	 **/
	@Before
	public void setUp() {
//		MockitoAnnotations.initMocks(this);
		baralho = new Baralho();
		baralho.setGerador(gerador);
	}

	@Test
	public void testDevolveCartaAleatoriaERetiraDoBaralho() throws Exception {
		when(gerador.sortear(anyInt(), anyInt())).thenReturn(0);
		// Sabendo que no nosso baralho a 1� carta � o A de Copas
		assertEquals(new Carta(1, Carta.naipes.COPAS), baralho.devolveCarta());
		// Como a carta foi retirada, e sabendo que nosso baralho tem o 2 de Copas na
		// seq��ncia
		assertEquals(new Carta(2, Carta.naipes.COPAS), baralho.devolveCarta());
		verify(gerador, times(2)).sortear(anyInt(), anyInt());
	}

	@Test(expected = IllegalStateException.class)
	public void testDevolveCartaBaralhoVazio() throws Exception {
		for (int i = 0; i < 50; i++)
			baralho.devolveCarta();
		verify(gerador, times(52)).sortear(anyInt(), anyInt());
		baralho.devolveCarta();
	}

}
