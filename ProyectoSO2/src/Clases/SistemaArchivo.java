/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import EstructurasDeDatos.Lista;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class SistemaArchivo {

    private Directorio raiz;
    private MemoryManager memoryManager;

    public SistemaArchivo() {
        this.raiz = new Directorio("Raíz");
        this.memoryManager = new MemoryManager(100);
    }

    // Crear directorio
    public void crearDirectorio(String nombrePadre, String nombreDirectorio) {
        Directorio padre = buscarDirectorio(raiz, nombrePadre);
        if (padre != null) {
            if (!padre.buscarSubdirectorio(nombreDirectorio)) {
                Directorio nuevo = new Directorio(nombreDirectorio);
                padre.agregarSubdirectorio(nuevo);
            } else {
                JOptionPane.showMessageDialog(null, "El directorio ya existe.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Directorio padre no encontrado.");
        }
    }

    // Eliminar directorio
    public void eliminarDirectorio(String nombrePadre, String nombreDirectorio) throws Exception {
        Directorio padre = buscarDirectorio(raiz, nombrePadre);
        if (padre == null) {
            throw new Exception("Directorio padre no encontrado.");
        }
        if (!padre.eliminarSubdirectorio(nombreDirectorio, memoryManager)) {
            throw new Exception("No se pudo eliminar el directorio.");
        }
    }

    // Crear archivo
    public void crearArchivo(String nombreDirectorio, String nombreArchivo, int tamano) {
        Directorio dir = buscarDirectorio(raiz, nombreDirectorio);
        if (dir == null) {
            JOptionPane.showMessageDialog(null, "Directorio no encontrado.");
            return;
        }

        if (dir.buscarArchivo(nombreArchivo) != null) {
            JOptionPane.showMessageDialog(null, "El archivo ya existe.");
            return;
        }

        if (memoryManager.bloquesDisponibles() < tamano) {
            JOptionPane.showMessageDialog(null, "Espacio insuficiente en el SD.");
            return;
        }

        Lista<Bloque> bloques = memoryManager.asignarBloquesEncadenados(tamano);
        if (bloques != null) {
            Archivo archivo = new Archivo(nombreArchivo);
            archivo.setBloquesAsignados(bloques);
            archivo.setTamañoBloques(tamano);
            dir.agregarArchivo(archivo);
        }
    }

    // Eliminar archivo
    public void eliminarArchivo(String nombreDirectorio, String nombreArchivo) {
        Directorio dir = buscarDirectorio(raiz, nombreDirectorio);
        if (dir != null) {
            Archivo archivo = dir.buscarArchivo(nombreArchivo);
            if (archivo != null) {
                memoryManager.liberarBloques(archivo.getBloquesAsignados());
                dir.eliminarArchivo(nombreArchivo);
            }
        }
    }

    // Buscar directorio recursivamente
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

    public void crearDirectoriosYArchivosAleatorios() {
        Random rand = new Random();
        int numDirectorios = 5;
        int numArchivosPorDirectorio = 2; // 2 archivos por directorio para el ejemplo

        // Crear directorios aleatorios
        for (int i = 1; i <= numDirectorios; i++) {
            String nombreDirectorio = "Directorio_" + i;
            this.crearDirectorio("Raíz", nombreDirectorio);

            // Crear archivos aleatorios dentro de cada directorio
            for (int j = 1; j <= numArchivosPorDirectorio; j++) {
                String nombreArchivo = "Archivo_" + rand.nextInt(1000);
                int tamaño = rand.nextInt(5) + 1; // Tamaño entre 1 y 5 bloques
                this.crearArchivo(nombreDirectorio, nombreArchivo, tamaño);
            }
        }
    }

    // Getters
    public Directorio getRaiz() {
        return raiz;
    }

    public MemoryManager getMemoryManager() {
        return memoryManager;
    }
}
