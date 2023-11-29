/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio8;

import Ejercicio9.Producto;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

class RecursoCompartido {

    ArrayList<Producto> productos = new ArrayList<>();
    boolean esDisponible = false;
    final int tamañoArray = 10;

    synchronized void get(String nombre) throws InterruptedException {
        while (productos.size() == 0) {
            wait();
        }

        System.out.println("Consumidor -"+ nombre+", ha consumido el producto:" + productos.get(0).getId());
        productos.remove(0);
        notifyAll();
    }

    synchronized void put() throws InterruptedException {
        while (productos.size() >= tamañoArray) {
            wait();
        }
        Producto nuevoProducto = new Producto();
        productos.add(nuevoProducto);
        System.out.println("Producto con id: "+ nuevoProducto.getId()+", producido.");
        notifyAll();
    }

}

class HiloProductor implements Runnable {

    
    RecursoCompartido cont;

    public HiloProductor(RecursoCompartido cont) {
        this.cont = cont;
    }

    @Override
    public void run() {
        while(true) {
            try {
                cont.put();
                Thread.sleep(90);
            } catch (InterruptedException e) {
            }
        }

    }

}

class HiloConsumidor implements Runnable {

    RecursoCompartido cont;
    String nombre;
    
    public HiloConsumidor(RecursoCompartido cont,String nombre) {
        this.cont = cont;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        while(true) {
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
        RecursoCompartido almacen = new RecursoCompartido();
        Thread hprod = new Thread(new HiloProductor(almacen));
        Thread hcons1 = new Thread(new HiloConsumidor(almacen,"1"));
        
        hprod.start();
        hcons1.start();
        
        
    }
    
}
