/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author gianf
 */
public class Usuario {

    public enum ModoUsuario {
        ADMINISTRADOR, USUARIO
    }
    private ModoUsuario modo;

    public Usuario(ModoUsuario modo) {
        this.modo = modo;
    }

    public ModoUsuario getModo() {
        return modo;
    }

    public void setModo(ModoUsuario modo) {
        this.modo = modo;
    }
}
