package br.com.pucminas.integracao.mockito;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Baralho{
   protected List<Carta> cartas;
   protected GeradorNumeroAleatorio gerador;
   
   public Baralho(){
      Carta.naipes auxNaipes[] = {Carta.naipes.COPAS, Carta.naipes.OUROS, Carta.naipes.ESPADAS, Carta.naipes.PAUS};
      this.cartas = new ArrayList<Carta>();
      Carta aux=null;
      for(int i=0; i<4; i++){
         for(int j=1; j<=13; j++){
             aux = new Carta(j,auxNaipes[i]);
             cartas.add(aux);
         }
      }
   }
   
   public void setGerador(GeradorNumeroAleatorio G){
      this.gerador = G;
   }
   public Carta devolveCarta() throws IllegalStateException{
      System.out.println(cartas);
      int pos = gerador.sortear(0,51);
      if(cartas.size()==0) throw new IllegalStateException("Baralho vazio");
      Carta aux = cartas.remove(pos);

      return aux;
   }
   
   





}