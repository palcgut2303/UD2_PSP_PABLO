/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosForo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class HiloAdivina implements Runnable{

    String archivo;

    public HiloAdivina(String archivo) {
        this.archivo = archivo;
    }
    
    
    @Override
    public void run() {
        try ( BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            int count = 0;
            int c;
            while ((c = br.read()) != -1) {
                
                count++;
            }
            System.out.println("El archivo '" + archivo + "' tiene " + count + " caracteres.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo '" + archivo + "': " + e.getMessage());
        }
    }
    
}



public class Ejercicio3Hilo {
    
    public static void main(String[] args) throws InterruptedException {
    
        Thread h1 = new Thread(new HiloAdivina("fichero1.txt"));
        Thread h2 = new Thread(new HiloAdivina("fichero2.txt"));
        Thread h3 = new Thread(new HiloAdivina("fichero3.txt"));
        
        long t_comienzo,t_fin;
        t_comienzo = System.currentTimeMillis();
        h1.start();
        h2.start();
        h3.start(); 
        
        h1.join();
        h2.join();
        h3.join();
        t_fin = System.currentTimeMillis();
        
        long tiempototal = t_fin - t_comienzo;
        
        System.out.println(" El proceso ha tardado: "+ tiempototal + " milisegundos");
        
    }
    
}
