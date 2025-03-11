/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;


/**
 *
 * @author LENOVO
 */

public class SistemaArchivo {
    private Directorio raiz;
    
    public SistemaArchivo() {
        this.raiz = new Directorio("/" );
    }
    
    // Crear un archivo en un directorio dado
    public void crearArchivo(String nombreDirectorio, String nombreArchivo, int tamano) {
        Directorio dir = buscarDirectorio(raiz, nombreDirectorio);
        if (dir != null) {
            Archivo archivo = new Archivo(nombreArchivo, tamano);
            dir.agregarArchivo(nombreArchivo);
            System.out.println("Archivo '" + nombreArchivo + "' creado en '" + nombreDirectorio + "'.");
        } else {
            System.out.println("Directorio no encontrado: " + nombreDirectorio);
        }
    }
    
    // Eliminar un archivo de un directorio dado
    public void eliminarArchivo(String nombreDirectorio, String nombreArchivo) {
        Directorio dir = buscarDirectorio(raiz, nombreDirectorio);
        if (dir != null) {
            dir.eliminarArchivo(nombreArchivo);
            System.out.println("Archivo '" + nombreArchivo + "' eliminado de '" + nombreDirectorio + "'.");
        } else {
            System.out.println("Directorio no encontrado: " + nombreDirectorio);
        }
    }
    
    // Crear un subdirectorio dentro de otro
    public void crearDirectorio(String nombrePadre, String nombreDirectorio) {
        Directorio padre = buscarDirectorio(raiz, nombrePadre);
        if (padre != null) {
            Directorio nuevo = new Directorio(nombreDirectorio);
            padre.agregarSubdirectorio(nuevo);
            System.out.println("Directorio '" + nombreDirectorio + "' creado dentro de '" + nombrePadre + "'.");
        } else {
            System.out.println("Directorio padre no encontrado: " + nombrePadre);
        }
    }
    
    // Eliminar un subdirectorio
    public void eliminarDirectorio(String nombrePadre, String nombreDirectorio) {
        Directorio padre = buscarDirectorio(raiz, nombrePadre);
        if (padre != null) {
            padre.eliminarSubdirectorio(nombreDirectorio);
            System.out.println("Directorio '" + nombreDirectorio + "' eliminado de '" + nombrePadre + "'.");
        } else {
            System.out.println("Directorio padre no encontrado: " + nombrePadre);
        }
    }
    
    // Buscar un directorio por su nombre en la estructura jerárquica
    private Directorio buscarDirectorio(Directorio actual, String nombre) {
        if (actual.getNombre().equals(nombre)) {
            return actual;
        }
        for (int i = 0; i < actual.getSubdirectorios().getLength(); i++) {
            Directorio encontrado = buscarDirectorio(actual.getSubdirectorios().get(i), nombre);
            if (encontrado != null) {
                return encontrado;
            }
        }
        return null;
    }
    
    // Listar el contenido de un directorio específico
    public void listarContenido(String nombreDirectorio) {
        Directorio dir = buscarDirectorio(raiz, nombreDirectorio);
        if (dir != null) {
            dir.listarContenido();
        } else {
            System.out.println("Directorio no encontrado: " + nombreDirectorio);
        }
    }

    void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
