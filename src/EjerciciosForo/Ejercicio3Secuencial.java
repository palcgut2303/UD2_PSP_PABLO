/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosForo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Pablo Alcudia
 */
public class Ejercicio3Secuencial {
    
    public static void main(String[] args) {
        
        
        for (String archivo : args) {
            contarCaracteres(archivo);
        }
    }

    private static void contarCaracteres(String archivo) {
        
        try ( BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            int count = 0;
            int linea;
            while ((linea = br.read()) != -1) {
                
                count++;
            }
            
            System.out.println("El archivo '" + archivo + "' tiene " + count + " caracteres.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo '" + archivo + "': " + e.getMessage());
        }
    }

}
