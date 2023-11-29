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
    int contador = 0;

    
    
    synchronized void get() throws InterruptedException {
        while (contador == 0) {
            wait();
        }

        char caracter = cadena[--contador];
        System.out.println("Recogido el caracter " + caracter + " del buffer.");
        notifyAll();
    }

    synchronized void put(char valor) throws InterruptedException {
        while (contador == cadena.length) {
            wait();
        }
        cadena[contador++] = valor;
        System.out.println("Depositado el caracter " + valor + " del buffer.");
        notifyAll();
    }

}

class HiloProductor implements Runnable {

    Random random = new Random();
    
    Contenedor cont;

    public HiloProductor(Contenedor cont) {
        this.cont = cont;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                char caracter = (char) (random.nextInt(26) + 'a');
                cont.put(caracter);
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
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
        for (int i = 0; i < 10; i++) {
            try {
                cont.get();
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloConsumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Contenedor almacen = new Contenedor();
        Thread hprod = new Thread(new HiloProductor(almacen));
        Thread hcons1 = new Thread(new HiloConsumidor(almacen));
        hprod.start();
        hcons1.start();
        
        hprod.join();
        hcons1.join();
        System.out.println("Termina el programa");
        
    }
}
