/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosForo;

import java.util.Random;

class Hilo implements Runnable{

    private Ejercicio4NumeroOculto numeroOculto;
    private int identificador;
    private int numeroPropuestoHilo;
    
    public Hilo(int identificador,Ejercicio4NumeroOculto numeroOculto){
        this.identificador = identificador;
        this.numeroOculto = numeroOculto;
    }
    
    @Override
    public void run() {
        Random numero = new Random();
        int numeroRandom = 15;
       if( numeroOculto.propuestaNumero(numeroRandom) == -1){
           System.out.println("Hilo " + identificador + " numero adivinado por otro hilo.");
           
       }else if(numeroOculto.propuestaNumero(numeroRandom) == 1){
           System.out.println("Hilo " + identificador + " ha adivinado el numero.");
       }else{
           System.out.println("Hilo " + identificador + " no se ha adivinado el numero por ningun hilo");
       }
        
        
    }
    
}




public class Ejercicio4Aleatorio {
    
    public static void main(String[] args) throws InterruptedException {
        Ejercicio4NumeroOculto numeroHilo1 = new Ejercicio4NumeroOculto(20);
        Ejercicio4NumeroOculto numeroHilo2 = new Ejercicio4NumeroOculto(0);
        Ejercicio4NumeroOculto numeroHilo3 = new Ejercicio4NumeroOculto(2);
        
        Thread hilo1 = new Thread(new Hilo(1, numeroHilo1));
        Thread hilo2 = new Thread(new Hilo(2, numeroHilo2));
        Thread hilo3 = new Thread(new Hilo(3, numeroHilo3));
        
        
        hilo1.start();
        hilo2.start();
        hilo3.start();
        
        hilo1.join();
        hilo2.join();
        hilo3.join();
        
    }
    
}
