/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.Objects;

/**
 *
 * @author Edgar
 */
public class Mensaje {
    
    private int id_cuenta_origen, id_cuenta_destino, id_inmueble;
    String contenido;
    
    public Mensaje(){}//Constructor Vacio
    
    public Mensaje(int id_cuenta_origen, int id_cuenta_destino, int id_inmueble, String contenido)
    {
        this.id_cuenta_origen = id_cuenta_origen;
        this.id_cuenta_destino = id_cuenta_destino;
        this.id_inmueble = id_inmueble;
        this.contenido = contenido;
    }

    public int getId_cuenta_origen() {
        return id_cuenta_origen;
    }

    public void setId_cuenta_origen(int id_cuenta_origen) {
        this.id_cuenta_origen = id_cuenta_origen;
    }

    public int getId_cuenta_destino() {
        return id_cuenta_destino;
    }

    public void setId_cuenta_destino(int id_cuenta_destino) {
        this.id_cuenta_destino = id_cuenta_destino;
    }

    public int getId_inmueble() {
        return id_inmueble;
    }

    public void setId_inmueble(int id_inmueble) {
        this.id_inmueble = id_inmueble;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    
}
