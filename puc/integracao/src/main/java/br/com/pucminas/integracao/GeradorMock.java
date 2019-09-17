package br.com.pucminas.integracao;
public class GeradorMock implements GeradorNumeroAleatorio{
   private int sorteado;

   public GeradorMock(int sorteado){
      this.sorteado = sorteado;
   }

   public int sortear(int inicioIntervalo, int fimIntervalo){
      return sorteado;
   }
}



