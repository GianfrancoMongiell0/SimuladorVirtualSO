/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author gianf
 */
public class Bloque {

    private int id;
    private boolean ocupado;
    private Bloque siguienteBloque;

    public Bloque(int id) {
        this.id = id;
        this.ocupado = false;
        this.siguienteBloque = null;
    }

    // Setters
    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public void setSiguienteBloque(Bloque siguiente) {
        this.siguienteBloque = siguiente;
    }

    // Getters
    public int getId() {
        return id;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public Bloque getSiguienteBloque() {
        return siguienteBloque;
    }
}
