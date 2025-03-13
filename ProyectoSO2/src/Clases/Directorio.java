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
    private Lista<Directorio> subdirectorios;
    private Lista<Archivo> archivos;


    // Constructor
    public Directorio(String nombre) {
        this.nombre = nombre;
        this.archivos = new Lista<>();
        this.subdirectorios = new Lista<>();
    }

   public void agregarArchivo(Archivo archivo1) {
        if (!archivos.contains(archivo1)) {
            archivos.insertLast(archivo1);
        } else {
            System.out.println("El archivo '" + archivo1.getNombre() + "' ya existe en el directorio.");
        }
    }

    public Archivo buscarArchivo(String nombreArchivo) {
        for (int i = 0; i < archivos.getLength(); i++) {
            if (archivos.get(i).getNombre().equals(nombreArchivo)) {
                return archivos.get(i);
            }
        }
        return null;
    }


    public void eliminarArchivo(String nombreArchivo) {
        for (int i = 0; i < archivos.getLength(); i++) {
            if (archivos.get(i).getNombre().equals(nombreArchivo)) {
                archivos.deleteIndex(i);
                return;
            }
        }
        System.out.println("El archivo '" + nombreArchivo + "' no existe en el directorio.");
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


    public Lista<Directorio> getSubdirectorios() {
        return subdirectorios;
    }

    public Lista<Archivo> getArchivos() {
        return archivos;
    }
    
    
}
