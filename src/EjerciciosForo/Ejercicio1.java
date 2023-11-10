/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosForo;

class Hilo extends Thread {

    private final String identificador;

    Hilo(String cadena) {
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
            new Hilo("H1"),
            new Hilo("H2"),
            new Hilo("H3"),
            new Hilo("H4"),
            new Hilo("H5")
        };

        for (Thread thread : threads) {
            thread.start();
        }

        System.out.println("Hilo principal terminado.");
    }

}
