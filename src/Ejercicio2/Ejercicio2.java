/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio2;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Hilo2 implements Runnable {

    private final String cadena;
    private final String identificador;
    Random r = new Random();
    int pausa = 10 + r.nextInt(500 - 10);

    Hilo2(String identificador, String cadena) {
        this.cadena = cadena;
        this.identificador = identificador;
    }

    @Override
    public void run() {
        System.out.printf("Hola mundo,hilo: %s ,cadena introducida: %s\n", this.identificador, this.cadena);
        try {
            Thread.sleep(pausa);
        } catch (Exception e) {
        }

    }

}

public class Ejercicio2 {

    public static void main(String[] args) {

        Thread[] threads = {
            new Thread(new Hilo2("H1", "mi cadena primera")),
            new Thread(new Hilo2("H2", "mi cadena segunda")),
            new Thread(new Hilo2("H3", "mi cadena tercera")),
            new Thread(new Hilo2("H4", "mi cadena cuarta")),
            new Thread(new Hilo2("H5", "mi cadena quinta")),};

        for (Thread thread : threads) {
            thread.start();
        }

    }
}
