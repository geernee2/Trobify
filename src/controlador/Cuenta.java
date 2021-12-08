package controlador;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;


public class Cuenta {
    
    private  int id_cuenta;
    private  int telefono;
    
    private  String email;
    private  String nombre;
    private  String tipoCuenta;
    private  String apellidos;
    private  String imagenPerfil;
    
    private  Date fechaCreacion;

    public Cuenta(int id_cuenta, int telefono, String email, String nombre, String tipoCuenta, Date fechaCreacion, String apellidos, String imagenPerfil) 
    {
        this.id_cuenta = id_cuenta;
        this.telefono = telefono;
        this.email = email;
        this.nombre = nombre;
        this.tipoCuenta = tipoCuenta;
        this.fechaCreacion = fechaCreacion;
        this.apellidos = apellidos;
        this.imagenPerfil = imagenPerfil;

    }

    public Cuenta(){}//Constructor Cuenta Vacia
    
    public Cuenta convertToCuenta(ResultSet rs) throws SQLException
    {
        id_cuenta = rs.getInt("id_cuenta");
        telefono = rs.getInt("telefono");
        email = rs.getString("email");
        nombre = rs.getString("nombre");
        tipoCuenta = rs.getString("tipo_cuenta");
        fechaCreacion = rs.getDate("fecha_creacion");
        apellidos = rs.getString("apellidos");
        imagenPerfil = rs.getString("fotoPerfil");
        
        Cuenta res = new Cuenta(id_cuenta, telefono, email, nombre, tipoCuenta, fechaCreacion, apellidos, imagenPerfil);
        
        return res;
    }

    
    
    //Getters And Setter and toString and equals
    public int getId_cuenta() {
        return id_cuenta;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }


    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    
    
}
