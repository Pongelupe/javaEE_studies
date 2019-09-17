package br.com.pucminas.integracao;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class BaralhoTest {

   GeradorNumeroAleatorio mock;
   Baralho baralho = new Baralho();

   /** Fixture initialization (common initialization
    *  for all tests). **/
	@Before
	public void setUp() {

   //Gera Mock que devolve 1� carta
       mock =  new GeradorMock(0);
        //Cria Baralho e seta o objeto falso
       baralho = new Baralho();
       baralho.setGerador(mock);
   }

   @Test
   public void testDevolveCartaAleatoriaERetiraDoBaralho() throws Exception { 
   //Sabendo que no nosso baralho  a 1� carta  � o A de Copas 
        assertEquals(new Carta(1, Carta.naipes.COPAS), baralho.devolveCarta());
   //Como a carta foi retirada, e sabendo que nosso baralho tem o  2 de Copas  na seq��ncia
        assertEquals(new Carta(2, Carta.naipes.COPAS), baralho.devolveCarta());
}  
}