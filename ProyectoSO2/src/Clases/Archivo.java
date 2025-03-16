/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import EstructurasDeDatos.Lista;
import java.time.LocalDateTime;

/**
 *
 * @author gianf
 */
public class Archivo {

    private String nombre;
    private int tamañoBloques;
    private String permisos;
    private LocalDateTime fechaCreacion;
    private Lista<Bloque> bloquesAsignados;

    public Archivo(String nombre) {
        this.nombre = nombre;
        this.fechaCreacion = LocalDateTime.now();
        this.bloquesAsignados = new Lista<>();
    }

    // Setters para integración con MemoryManager
    public void setBloquesAsignados(Lista<Bloque> bloques) {
        this.bloquesAsignados = bloques;
    }

    public void setTamañoBloques(int tamaño) {
        this.tamañoBloques = tamaño;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getTamañoBloques() {
        return tamañoBloques;
    }

    public Lista<Bloque> getBloquesAsignados() {
        return bloquesAsignados;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
