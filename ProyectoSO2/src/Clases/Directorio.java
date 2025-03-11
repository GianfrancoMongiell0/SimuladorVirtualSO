/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import EstructurasDeDatos.Lista;
/**
 *
 * @author LENOVO
 */

public class Directorio {
    private String nombre;
    private Lista<String> archivos;
    private Lista<Directorio> subdirectorios;

    // Constructor
    public Directorio(String nombre) {
        this.nombre = nombre;
        this.archivos = new Lista<>();
        this.subdirectorios = new Lista<>();
    }

    // Métodos para manejar archivos
    public void agregarArchivo(String nombreArchivo) {
        if (!archivos.contains(nombreArchivo)) {
            archivos.insertLast(nombreArchivo);
        } else {
            System.out.println("El archivo '" + nombreArchivo + "' ya existe en el directorio.");
        }
    }

    public void eliminarArchivo(String nombreArchivo) {
        if (archivos.contains(nombreArchivo)) {
            archivos.deleteContent(nombreArchivo);
        } else {
            System.out.println("El archivo '" + nombreArchivo + "' no existe en el directorio.");
        }
    }

    public boolean buscarArchivo(String nombreArchivo) {
        return archivos.contains(nombreArchivo);
    }

    // Métodos para manejar subdirectorios
    public void agregarSubdirectorio(Directorio subdirectorio) {
        if (!subdirectorios.contains(subdirectorio)) {
            subdirectorios.insertLast(subdirectorio);
        } else {
            System.out.println("El subdirectorio '" + subdirectorio.getNombre() + "' ya existe.");
        }
    }

    public void eliminarSubdirectorio(String nombreSubdirectorio) {
        for (int i = 0; i < subdirectorios.getLength(); i++) {
            if (subdirectorios.get(i).getNombre().equals(nombreSubdirectorio)) {
                subdirectorios.deleteIndex(i);
                return;
            }
        }
        System.out.println("El subdirectorio '" + nombreSubdirectorio + "' no existe.");
    }

    public boolean buscarSubdirectorio(String nombreSubdirectorio) {
        for (int i = 0; i < subdirectorios.getLength(); i++) {
            if (subdirectorios.get(i).getNombre().equals(nombreSubdirectorio)) {
                return true;
            }
        }
        return false;
    }

    // Método para listar el contenido del directorio
    public void listarContenido() {
        System.out.println("Directorio: " + nombre);
        System.out.println("Archivos:");
        archivos.imprimir();
        System.out.println("Subdirectorios:");
        for (int i = 0; i < subdirectorios.getLength(); i++) {
            System.out.println("- " + subdirectorios.get(i).getNombre());
        }
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lista<String> getArchivos() {
        return archivos;
    }

    public Lista<Directorio> getSubdirectorios() {
        return subdirectorios;
    }
}
