/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosForo;

class HiloAdivina extends Thread {

    private final String identificador;

    HiloAdivina(String cadena) {
        this.identificador = cadena;
    }

    @Override
    public void run() {

        //System.out.printf("Hola soy el hilo: %s.\n",this.cadena);
        //System.out.printf("Hilo %s terminado.\n",this.cadena);
        System.out.printf("Hola mundo,hilo: %s\n", this.identificador);
        System.out.printf("Hilo %s terminado \n", this.identificador);
    }

}

public class Ejercicio1 {

    public static void main(String[] args) {

        Thread[] threads = {
            new HiloAdivina("H1"),
            new HiloAdivina("H2"),
            new HiloAdivina("H3"),
            new HiloAdivina("H4"),
            new HiloAdivina("H5")
        };

        for (Thread thread : threads) {
            thread.start();
        }

        System.out.println("Hilo principal terminado.");
    }

}
