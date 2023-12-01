/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio11;

import java.util.Random;

class Arbitro {

    Random rnd = new Random();
    int numeroAAdivinar = rnd.nextInt(11);

    public int getNumeroAAdivinar() {
        return numeroAAdivinar;
    }

    boolean esAdivinado = false;
    int contador = 1;

    public  synchronized int getContador() {
        if(contador < 4){
            return contador;
        }else{
            return contador = 1;    
        }
        
    }

    public synchronized void pregunta(int id, int numeroJugador) {

        if(!esAdivinado) {
            System.out.println("Jugador " + id + " dice: " + numeroJugador);
            if (numeroJugador == numeroAAdivinar) {
                System.out.println("El Jugador " + contador + " ha adivinado el numero.");
                esAdivinado = true;
            }
            contador++;
        }

    }

}

class Jugador implements Runnable {

    Arbitro arb;
    int id;
    int numeroJugador;

    public Jugador(Arbitro arb, int id) {
        this.arb = arb;
        this.id = id;
    }

    @Override
    public void run() {
        while (!arb.esAdivinado) {
            if (id == arb.getContador()) {
                Random rnd = new Random();
                numeroJugador = rnd.nextInt(11);
                arb.pregunta(id, numeroJugador);
            }
        }

    }

}

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Arbitro arb = new Arbitro();

        System.out.println("Arbitro dice: " + arb.getNumeroAAdivinar());
        Thread jugador1 = new Thread(new Jugador(arb, 1));
        Thread jugador2 = new Thread(new Jugador(arb, 2));
        Thread jugador3 = new Thread(new Jugador(arb, 3));

        jugador1.start();
        jugador2.start();
        jugador3.start();

    }

}
