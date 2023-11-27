/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio8;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

class Contenedor {

    String[] productos = new String[6];
    int contador = 0;

    synchronized void get(String nombre) throws InterruptedException {
        while (contador == 0) {
            wait();
        }

        String caracter = productos[--contador];
        System.out.println("Consumidor -"+nombre+", recoge el producto " + caracter + ".");
        notifyAll();
    }

    synchronized void put(String valor) throws InterruptedException {
        while (contador == productos.length) {
            wait();
        }
        productos[contador++] = valor;
        System.out.println("Fabricando el Producto " + valor + ".");
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
                cont.put(String.valueOf(caracter));
                Thread.sleep(90);
            } catch (InterruptedException e) {
            }
        }

    }

}

class HiloConsumidor implements Runnable {

    Contenedor cont;
    String nombre;
    
    public HiloConsumidor(Contenedor cont,String nombre) {
        this.cont = cont;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                cont.get(nombre);
                Thread.sleep(100);
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
        Thread hcons1 = new Thread(new HiloConsumidor(almacen,"1"));
        Thread hcons2 = new Thread(new HiloConsumidor(almacen,"2"));
        
        hprod.start();
        hcons1.start();
        hcons2.start();
        
        hprod.join();
        hcons1.join();
        hcons2.join();
        
        System.out.println("Termina el programa");
        
    }
    
}
