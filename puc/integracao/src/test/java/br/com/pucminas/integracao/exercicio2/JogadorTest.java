package br.com.pucminas.integracao.exercicio2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JogadorTest {

	private Jogador jogador;

	private Dado dado1 = mock(Dado.class);
	private Dado dado2 = mock(Dado.class);

	@Before
	public void setUp() {
		jogador = new Jogador(dado1, dado2);
	}

	@Test
	public void testRolarDadosSimples() {
		when(dado1.sortear()).thenReturn(2);
		when(dado2.sortear()).thenReturn(6);

		int somaDados = jogador.rolarDados();

		assertEquals(8, somaDados);
	}

	@Test
	public void venceuRodadaComUmaRolagem() {
		when(dado1.sortear()).thenReturn(1, 5, 6, 2, 5, 4, 3, 6, 1);
		when(dado2.sortear()).thenReturn(6, 6, 5, 5, 2, 3, 4, 1, 6);

		for (int i = 0; i < 9; i++) {
			boolean venceuRodada = jogador.venceuRodada();

			assertTrue(venceuRodada);
			verify(dado1, times(i + 1)).sortear();
			verify(dado2, times(i + 1)).sortear();
		}
	}

	@Test
	public void venceuRodadaComMaisDeUmaRolagem() {
		when(dado1.sortear()).thenReturn(2, 1, 2);
		when(dado2.sortear()).thenReturn(2, 2, 2);

		boolean venceuRodada = jogador.venceuRodada();

		assertTrue(venceuRodada);
		verify(dado1, times(3)).sortear();
		verify(dado2, times(3)).sortear();
	}

	@Test
	public void perdeuRodadaComUmaRolagem() {
		when(dado1.sortear()).thenReturn(6, 1, 1, 2);
		when(dado2.sortear()).thenReturn(6, 1, 2, 1);

		for (int i = 0; i < 4; i++) {
			boolean perdeuRodada = jogador.perdeuRodada();

			assertTrue(perdeuRodada);
			verify(dado1, times(i + 1)).sortear();
			verify(dado2, times(i + 1)).sortear();
		}

	}

	@Test
	public void perdeuRodadaComMaisDeUmaRolagem() {
		when(dado1.sortear()).thenReturn(2, 1, 2);
		when(dado2.sortear()).thenReturn(2, 2, 5);

		boolean perdeuRodada = jogador.perdeuRodada();

		assertTrue(perdeuRodada);
		verify(dado1, times(3)).sortear();
		verify(dado2, times(3)).sortear();
	}
	
	@Test
	public void fimRodadaSemRodada() {
		assertFalse(jogador.fimDeRodada());
	}

}
