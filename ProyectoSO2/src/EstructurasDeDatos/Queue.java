/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDeDatos;

/**
 *
 * @author gianf
 */
public class Queue<T> {

    private Nodo<T> first;
    private Nodo<T> last;
    private int length;

    public Queue() {
        this.first = null;
        this.last = null;
        this.length = 0;
    }

    // Getters
    public int getLength() {
        return length;
    }

    public Nodo<T> getFirst() {
        return first;
    }

    // Metodo para saber si es vacio
    public boolean isEmpty() {
        return length == 0;
    }

    // Metodo para inserta un elemento al final de la cola
    public void enqueue(T data) {
        Nodo<T> nuevoNodo = new Nodo<>(data);
        if (isEmpty()) {
            first = nuevoNodo;
            last = nuevoNodo;
        } else {
            last.setNext(nuevoNodo);
            last = nuevoNodo;
        }
        length++;
    }

    // Metodo para elliminar y retonar el elemnto de alfrente de la cola
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
            return null;
        }
        T data = first.getData();
        first = first.getNext();
        length--;
        if (length == 0) { // Si la cola quedó vacía, reiniciar last a null
            last = null;
        }
        return data;
    }

    // MEtodo que retorna el elemento de alfrente sin eliminarlo 
    public T peek() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
            return null;
        }
        return first.getData();
    }

    // Metodo para imprimir la cola
    public String imprimir() {
        if (isEmpty()) {
            return "La cola está vacía.";
        }
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = first;
        while (actual != null) {
            sb.append(actual.getData()).append(" -> ");
            actual = actual.getNext();
        }
        sb.append("NULL");
        return sb.toString();
    }

    // Metodo para dejar la cola vacia
    public void clear() {
        first = null;
        last = null;
        length = 0;
    }
}
