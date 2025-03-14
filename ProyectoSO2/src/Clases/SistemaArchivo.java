/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.Random;


/**
 *
 * @author LENOVO
 */

public class SistemaArchivo {
    private Directorio raiz;
    
    public SistemaArchivo() {
        this.raiz = new Directorio("Raíz" );
    }

    public Directorio getRaiz() {
        return raiz;
    }
    

    
   // Crear un archivo en un directorio dado
    public void crearArchivo(String nombreDirectorio, String nombreArchivo, int tamano) {
        Directorio dir = buscarDirectorio(raiz, nombreDirectorio);
        if (dir != null) {
            Archivo archivo = new Archivo(nombreArchivo, tamano);
            dir.agregarArchivo(archivo); // Pasar el objeto Archivo en lugar de un String
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
    
   public Archivo buscarArchivo(String nombreDirectorio, String nombreArchivo) {
        Directorio dir = buscarDirectorio(raiz, nombreDirectorio);
        return (dir != null) ? dir.buscarArchivo(nombreArchivo) : null;
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
            boolean eliminado = padre.eliminarSubdirectorio(nombreDirectorio);
            if (eliminado) {
                System.out.println("Directorio '" + nombreDirectorio + "' eliminado de '" + nombrePadre + "'.");
            } else {
                System.out.println("No se pudo eliminar el directorio '" + nombreDirectorio + "'.");
            }
        } else {
            System.out.println("Directorio padre no encontrado: " + nombrePadre);
        }
    }
    
    // Buscar un directorio por su nombre en la estructura jerárquica
    public Directorio buscarDirectorio(Directorio actual, String nombre) {
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
    
    // Método para crear 5 directorios y 10 archivos aleatorios
    public void crearDirectoriosYArchivosAleatorios() {
        Random rand = new Random();

        // Crear 5 directorios aleatorios
        for (int i = 1; i <= 5; i++) {
            String nombreDirectorio = "Directorio_" + i;
            // Crear directorio en la raíz
            crearDirectorio("Raíz", nombreDirectorio);

            // Crear entre 1 y 3 archivos aleatorios por directorio
            int numArchivos = rand.nextInt(3) + 1;
            for (int j = 1; j <= numArchivos; j++) {
                String nombreArchivo = "Archivo_" + rand.nextInt(1000);
                int tamano = rand.nextInt(500) + 1;  // Tamaño aleatorio entre 1 y 500 KB
                crearArchivo(nombreDirectorio, nombreArchivo, tamano);
            }
        }
    }

}
