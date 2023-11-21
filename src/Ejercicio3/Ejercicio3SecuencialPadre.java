/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Pablo Alcudia
 */
public class Ejercicio3SecuencialPadre {

    public static void main(String[] args) throws IOException {

        String cadena = "fichero1.txt fichero2.txt fichero3.txt";
        String comando = "java EjerciciosForo.Ejercicio3Secuencial " + cadena;
        ProcessBuilder pb = new ProcessBuilder(comando.split("\\s"));
        pb.directory(new File("build/classes"));
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);

        Process p = pb.start();

        mostrarInformacion(p);
        mostrarErrores(p);

        /*try {
            InputStream reader = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(reader));

            String linea;

            //int i = 1;
            while ((linea = br.readLine()) != null) {

                System.out.println(linea);
            }

            br.close();
        } catch (Exception ee) {
            ee.printStackTrace();
        }*/
    }

    private static void mostrarErrores(Process p) {
        try {
            InputStream er = p.getErrorStream();
            BufferedReader brer = new BufferedReader(new InputStreamReader(er));
            String liner = null;
            while ((liner = brer.readLine()) != null) {
                System.out.println("ERROR >" + liner);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void mostrarInformacion(Process p) {
        int exitVal;
        try {
            exitVal = p.waitFor();
            System.out.println("Valor de Salida: " + exitVal);
            switch (exitVal) {
                case (0):
                    System.out.println("FINAL CORRECTO...");
                    break;
                case (1):
                    System.out.println("FINAL INCORRECTO...");
                    break;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
