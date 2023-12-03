/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class RecursoCompartido{
    
    boolean esDisponible = false;
    String datosFichero = "";
    
    synchronized void get() throws InterruptedException{
        /*while(!esDisponible){
            wait();
        }*/
        
        System.out.println("Lo recogido por el consumidor es: " + datosFichero);
        esDisponible = false;
       /* notifyAll();*/
    }
    
    synchronized void put(String fichero) throws InterruptedException{
       /* while(esDisponible){
            wait();
        }*/
        
        try ( BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            
            int linea;
            while ((linea = br.read()) != -1) {
                
                datosFichero = datosFichero + " " + (char)linea;
            }
            
            esDisponible = true;
           /*notifyAll();*/
        } catch (IOException e) {
            System.err.println("Error al leer el archivo '" + fichero + "': " + e.getMessage());
        }
        
        
    }
    
    
}

class Productor implements Runnable{

    RecursoCompartido rc;
    String fichero;
    
    public Productor(RecursoCompartido rc,String fichero) {
        this.rc = rc;
        this.fichero = fichero;
    }
    
    @Override
    public void run() {
        
        try {
            rc.put(fichero);
        } catch (InterruptedException ex) {
            Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

class Consumidor implements Runnable{

    RecursoCompartido rc;

    public Consumidor(RecursoCompartido rc) {
        this.rc = rc;
    }
    
    @Override
    public void run() {
        try {
            rc.get();
        } catch (InterruptedException ex) {
            Logger.getLogger(Consumidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
public class Main {
    public static void main(String[] args) throws InterruptedException {
        RecursoCompartido rc = new RecursoCompartido();
        Thread prod1 = new Thread(new Productor(rc, "ejemplo.txt"));
        Thread cons1 = new Thread(new Consumidor(rc));
        Thread cons2 = new Thread(new Consumidor(rc));
        
        prod1.start();
        cons1.start();
        cons2.start();
        
        System.out.println("Estado de cons1 después de que el productor ha terminado: " + cons1.getState());
        System.out.println("Estado de cons2 después de que el productor ha terminado: " + cons2.getState());

        
        prod1.join();
        cons1.join();
        cons2.join();
        
        System.out.println("Estado de cons1 después de que el productor ha terminado: " + cons1.getState());
        System.out.println("Estado de cons2 después de que el productor ha terminado: " + cons2.getState());

        
        System.out.println("LECTURA TERMINADA");
        
        
    }
}
