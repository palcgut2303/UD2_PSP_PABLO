/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio5;

class Contenedor<Integer> {

    private int numero;
    private boolean disponible;

    synchronized int get(){
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        //visualizar número
        disponible = false;
        notifyAll();
        return numero;
    }

    synchronized void put(int valor) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        numero = valor;
        disponible = true;
        //visualizar número
        notifyAll();
    }

}

class HiloProductor implements Runnable {

    final Contenedor<Integer> cont;
    String miNombre;

    HiloProductor(Contenedor<Integer> cont, String miNombre) {
        this.cont = cont;
        this.miNombre = miNombre;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            cont.put(i); 
            System.out.println(i + "=>Productor : " + miNombre + ", produce : " + i);
           
        }
        System.out.println("Fin productor...");
    }

}

class HiloConsumidor implements Runnable {

    final Contenedor<Integer> cont;
    String miNombre;

    HiloConsumidor(Contenedor<Integer> cont, String miNombre) {
        this.cont = cont;
        this.miNombre = miNombre;
    }

   public void run() {
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            valor = cont.get(); //recoge el número
            System.out.println(i + "=>Consumidor: " + miNombre + ", consume: " + valor);
        }
       
    }

}

public class Ejercicio5 {

    public static void main(String[] args) throws InterruptedException {
        Contenedor<Integer> almacen = new Contenedor<Integer>();
        Thread hprod = new Thread(new HiloProductor(almacen, "1"));
        Thread hcons1 = new Thread(new HiloConsumidor(almacen, "1"));
        Thread hcons2 = new Thread(new HiloConsumidor(almacen, "2"));
        
        hprod.start();
        hcons1.start();
        hcons2.start();
        
       
        
        
        
    }
}
