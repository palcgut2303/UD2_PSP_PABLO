/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio7;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

class Contenedor {

    char[] cadena = new char[6];
    private boolean disponible;

    synchronized char get() throws InterruptedException {

        while (!disponible) {
            wait();
        }
        disponible = false;
        notifyAll();;

        return cadena[cadena.length - 1];
    }

    synchronized void put(char valor) throws InterruptedException {
        while (disponible) {
            wait();
        }
        disponible = true;
        System.out.println("Productor ha producido el caracter " + valor);
        notifyAll();
    }

}

class HiloProductor implements Runnable {

    Random random = new Random();
    char caracter = (char) (random.nextInt(26) + 'a');
    Contenedor cont;

    public HiloProductor(Contenedor cont) {
        this.cont = cont;
    }

    @Override
    public void run() {
        try {
            cont.put(caracter);
        } catch (Exception e) {
        }

    }

}

class HiloConsumidor implements Runnable {

    Contenedor cont;

    public HiloConsumidor(Contenedor cont) {
        this.cont = cont;
    }

    @Override
    public void run() {
        try {
            char valor = cont.get();
            System.out.println("Consumidor ha cogido la letra: " + valor);
        } catch (InterruptedException ex) {
            Logger.getLogger(HiloConsumidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

public class Main {

    public static void main(String[] args) {
        Contenedor almacen = new Contenedor();
        Thread hprod = new Thread(new HiloProductor(almacen));
        Thread hcons1 = new Thread(new HiloConsumidor(almacen));

        hprod.start();
        hcons1.start();
    }
}
