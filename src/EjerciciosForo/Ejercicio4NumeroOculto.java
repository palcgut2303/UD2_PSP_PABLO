/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EjerciciosForo;

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
        int numeroDevuelto = 0;
        adivinado = false;
        while (!adivinado) {
            if (num == numero) {
                numeroDevuelto = 1;
                adivinado = true;
            } else if (num != numero) {
                numeroDevuelto = -1;
                adivinado = true;
            } else {
                numeroDevuelto = 0;
            }
        }

        return numeroDevuelto;
    }

}
