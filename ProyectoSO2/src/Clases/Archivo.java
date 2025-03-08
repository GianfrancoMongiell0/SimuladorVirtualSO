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
        this.permisos = "rw-"; // Permisos por defecto: lectura/escritura
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lista<Bloque> getBloquesAsignados() {
        return bloquesAsignados;
    }

    public void setBloquesAsignados(Lista<Bloque> bloques) {
        this.bloquesAsignados = bloques;
        this.tamañoBloques = bloques.getLength();
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
}