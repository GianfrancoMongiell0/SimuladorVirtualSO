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
    private Bloque siguienteBloque; // Para asignación encadenada

    public Bloque(int id) {
        this.id = id;
        this.ocupado = false;
        this.siguienteBloque = null;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public Bloque getSiguienteBloque() {
        return siguienteBloque;
    }

    public void setSiguienteBloque(Bloque siguiente) {
        this.siguienteBloque = siguiente;
    }
}