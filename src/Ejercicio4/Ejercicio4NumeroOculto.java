/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio4;

/**
 *
 * @author manana
 */
public class Ejercicio4NumeroOculto {

    private int numero;
    private boolean adivinado = false;

    public Ejercicio4NumeroOculto(int numero) {
        this.numero = numero;
    }

    public synchronized int propuestaNumero(int num) {
       
        if(adivinado){
            return -1;
        }else if(num == numero){
            adivinado = true;
            return 1;
        }else{
            return 0;
        }
       
    }

    public int getNumero() {
        return numero;
    }
    
    
}
