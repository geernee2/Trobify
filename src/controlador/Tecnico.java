package controlador;

import java.util.Objects;

public class Tecnico {
    
    private int id_tecnico, id_agencia, id_cuenta;
    
    private String nombre;
    
    public Tecnico(){}//Constructor Vacio
    
    public Tecnico(int id_tecnico, int id_agencia, int id_cuenta, String nombre)
    {
        this.id_agencia = id_agencia;
        this.id_cuenta = id_cuenta;
        this.id_tecnico = id_tecnico;
        this.nombre = nombre;
    }

    public int getId_tecnico() {
        return id_tecnico;
    }

    public void setId_tecnico(int id_tecnico) {
        this.id_tecnico = id_tecnico;
    }

    public int getId_agencia() {
        return id_agencia;
    }

    public void setId_agencia(int id_agencia) {
        this.id_agencia = id_agencia;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Tecnico{" + "id_tecnico=" + id_tecnico + ", id_agencia=" + id_agencia + ", id_cuenta=" + id_cuenta + ", nombre=" + nombre + '}';
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tecnico other = (Tecnico) obj;
        if (this.id_tecnico != other.id_tecnico) {
            return false;
        }
        if (this.id_agencia != other.id_agencia) {
            return false;
        }
        if (this.id_cuenta != other.id_cuenta) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
    
    
}
