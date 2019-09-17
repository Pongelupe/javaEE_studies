package br.com.pucminas.integracao.mockito;

public class Carta{

   protected static String faces[] = {"","A","2","3","4","5","6","7","8","9","10","J","Q","K"}; 

   public static enum naipes{
      COPAS, OUROS, PAUS, ESPADAS;
   }
   
   protected String face;
   protected Carta.naipes naipe;
   
   
   public Carta(int val, Carta.naipes naipe){
      if(val>=1 && val <=13)
         this.face = faces[val];
      else
         this.face = "A";
      this.naipe = naipe;      
   }

   @Override
   public String toString(){
      return this.face + " de " + this.naipe;
   }
   

   @Override
    public boolean equals(Object o) { 
      Carta outra = (Carta)o;
      
      if(this.face.equals(outra.face) && (this.naipe==outra.naipe))
         return true;
      else 
         return false;
   }
}