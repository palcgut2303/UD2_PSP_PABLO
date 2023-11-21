/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio6;

import java.util.logging.Level;
import java.util.logging.Logger;

class Contenedor<String> {

    private String cadena;
    private boolean disponible;

    synchronized String get() throws InterruptedException {
        while (!disponible) {
            wait();
        }

        disponible = false;
        notifyAll();;

        return cadena;
    }

    synchronized void put(String valor) throws InterruptedException {
        while (disponible) {
            wait();
        }
        disponible = true;
        cadena = valor;
        notifyAll();
    }

}

class HiloProducto implements Runnable {

    Contenedor<String> contenedor;

    public HiloProducto(Contenedor<String> contenedor) {
        this.contenedor = contenedor;

    }

    @Override
    public void run() {
        for (int i = 2;; i++) {
            if ((i % 2) == 0) {
                try {
                    contenedor.put("PING");
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    contenedor.put("PONG");
                } catch (InterruptedException ex) {
                    Logger.getLogger(HiloProducto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

}

class HiloConsumidor implements Runnable {

    Contenedor<String> cont;

    HiloConsumidor(Contenedor<String> cont) {
        this.cont = cont;
    }

    @Override
    public void run() {
        String valor = "";
        for (int i = 1;; i++) {
            try {
                valor = cont.get(); //recoge el número
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloConsumidor.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(valor);
        }
    }

}

public class Main {

    public static void main(String[] args) {
        Contenedor<String> almacen = new Contenedor<String>();
        Thread hprod = new Thread(new HiloProducto(almacen));
        Thread hcons1 = new Thread(new HiloConsumidor(almacen));

        hprod.start();
        hcons1.start();
    }
}
