/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ejercicio9;

/**
 *
 * @author manana
 */
public class Producto {
    
    private static int contador = 0;
    private int id;

    public Producto() {
        this.id = contador++;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
