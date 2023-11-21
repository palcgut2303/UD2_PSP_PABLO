/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio4;

import java.util.Random;

class HiloAdivina implements Runnable {

    private Ejercicio4NumeroOculto numeroDelHilo;
    private int identificador;
    

    public HiloAdivina(int identificador, Ejercicio4NumeroOculto numeroDelHilo) {
        this.identificador = identificador;
        this.numeroDelHilo = numeroDelHilo;
    }

    @Override
    public void run() {

        Random numero = new Random();
        int numeroRandom = numero.nextInt(10);

        int resultado = numeroDelHilo.propuestaNumero(5);
        if (resultado == -1) {

            System.out.println("Hilo " + identificador + " numero adivinado por otro hilo.");

        } else if (resultado == 1) {

            System.out.println("Hilo " + identificador + " ha adivinado el numero.");

        } else {
            System.out.println("Hilo " + identificador + " no ha adivinado el numero.");

        }

        
    }

}

public class Ejercicio4Aleatorio {

    public static void main(String[] args) throws InterruptedException {
        /*Ejercicio4NumeroOculto numerosHilos[] = new Ejercicio4NumeroOculto[10];

        for (int i = 0; i < 10; i++) {
            Random rnd = new Random();
            numerosHilos[i] = new Ejercicio4NumeroOculto(rnd.nextInt(10));
        }

        Thread hilos[] = new Thread[numerosHilos.length];
        for (int i = 0; i < numerosHilos.length; i++) {
            Random rnd = new Random();
            hilos[i] = new Thread(new HiloAdivina(i, numerosHilos[i]));
        }

        for (int i = 0; i < hilos.length; i++) {
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        Ejercicio4NumeroOculto numeroHilo1 = new Ejercicio4NumeroOculto(6);
        Ejercicio4NumeroOculto numeroHilo2 = new Ejercicio4NumeroOculto(5);
        Ejercicio4NumeroOculto numeroHilo3 = new Ejercicio4NumeroOculto(2);

        Thread hilo1 = new Thread(new HiloAdivina(1, numeroHilo1));
        Thread hilo2 = new Thread(new HiloAdivina(2, numeroHilo2));
        Thread hilo3 = new Thread(new HiloAdivina(3, numeroHilo3));
        
        hilo1.start();
        hilo2.start();
        hilo3.start();
        
        hilo1.join();
        hilo2.join();
        hilo3.join();
        System.out.println("Juego terminado.");
    }

}
