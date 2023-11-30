/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio10;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class RecursoCompartido{
    
    ArrayList<Integer> cola = new ArrayList<>();
    private final int tamañoArray = 20;
    
    synchronized void get() throws InterruptedException{
        while(cola.isEmpty()){
            wait();
        }
        
        int numero = cola.get(0);
        cola.remove(0);
        System.out.println("Consumidor 1, ha consumido el número: " + numero);
        notify();
    }
    
    synchronized void put(int numero) throws InterruptedException{
        while(cola.size() >= tamañoArray){
            wait();
        }
        cola.add(numero);
        System.out.println("Producido el numero: " + numero);
        notify();
    }
    
    
}

class HiloProductor implements Runnable{

    RecursoCompartido rc;

    public HiloProductor(RecursoCompartido rc) {
        this.rc = rc;
    }

    
    
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            try {
                rc.put(i);
                Thread.sleep(90);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloProductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
        
    }
    
}

class HiloConsumidor implements Runnable{

    RecursoCompartido rc;

    public HiloConsumidor(RecursoCompartido rc) {
        this.rc = rc;
    }

    
    
    @Override
    public void run() {
        for (int i = 1; i <= 20; i++) {
            try {
                rc.get();
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(HiloProductor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}

public class Ejercicio10 {
    public static void main(String[] args) {
        
       RecursoCompartido almacen = new RecursoCompartido();
        Thread hprod = new Thread(new HiloProductor(almacen));
        Thread hcons1 = new Thread(new HiloConsumidor(almacen));
        
        hprod.start();
        hcons1.start();
        
    }
}
