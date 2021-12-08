/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import basededatos.DAL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author David
 */
public class Notificacion {
    private DAL dal = new DAL();
    
    private int id_notificacion;
    private Cuenta cuentaOrigen;
    private int id_cuenta_origen, id_cuenta_destino, id_inmueble;
    private Cuenta cuentaDestino;
    private Inmueble inmuebleNotificacion;
    private Date fechaNotificacion;
    private String tipoNotificacion, mensaje;

    //Constructor vacío
    public Notificacion(){}
    
    //Constructor opcional sin objetos
    public Notificacion(int id_notificacion, int id_cuenta_origen, int id_cuenta_destino, int id_inmueble, Date fechaNotificacion, String tipoNotificacion, String mensaje){
        this.id_notificacion = id_notificacion;
        this.id_cuenta_origen = id_cuenta_origen;
        this.id_cuenta_destino = id_cuenta_destino;
        this.id_inmueble = id_inmueble;
        this.fechaNotificacion = fechaNotificacion;
        this.tipoNotificacion = tipoNotificacion;
        this.mensaje = mensaje;
    }
    
    //Constructor default con objetos
    public Notificacion(int id_notificacion, Cuenta cuentaOrigen, Cuenta cuentaDestino, Inmueble inmuebleNotificacion, Date fechaNotificacion, String tipoNotificacion, String mensaje){
        this.id_notificacion = id_notificacion;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.inmuebleNotificacion = inmuebleNotificacion;
        this.fechaNotificacion = fechaNotificacion;
        this.tipoNotificacion = tipoNotificacion;
        this.mensaje = mensaje;
    }
    
    public Notificacion convertToNotificacion(ResultSet rs) throws SQLException
    {
        id_notificacion = rs.getInt("id_notificacion");
        Cuenta cuentaOrigen = dal.getCuentaById(rs.getInt("id_cuenta_origen"));
        Cuenta cuentaDestino = dal.getCuentaById(rs.getInt("id_cuenta_destino"));
        Inmueble inmuebleNotificacion = dal.getInmuebleByID(rs.getInt("id_inmueble"));
        fechaNotificacion = rs.getDate("fecha");
        tipoNotificacion = rs.getString("tipoNotificacion");
        mensaje = rs.getString("mensaje");

        
        Notificacion res = new Notificacion(id_notificacion, cuentaOrigen, cuentaDestino, inmuebleNotificacion, fechaNotificacion, tipoNotificacion, mensaje);
        return res;
    }

    public void setDal(DAL dal) {
        this.dal = dal;
    }

    public int getId_notificacion() {
        return id_notificacion;
    }

    public void setId_notificacion(int id_notificacion) {
        this.id_notificacion = id_notificacion;
    }

    public Cuenta getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(Cuenta cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
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

    public Cuenta getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(Cuenta cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public Inmueble getInmuebleNotificacion() {
        return inmuebleNotificacion;
    }

    public void setInmuebleNotificacion(Inmueble inmuebleNotificacion) {
        this.inmuebleNotificacion = inmuebleNotificacion;
    }

    public Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    public void setFechaNotificacion(Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
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
        final Notificacion other = (Notificacion) obj;
        if (this.id_notificacion != other.id_notificacion) {
            return false;
        }
        if (this.id_cuenta_origen != other.id_cuenta_origen) {
            return false;
        }
        if (this.id_cuenta_destino != other.id_cuenta_destino) {
            return false;
        }
        if (this.id_inmueble != other.id_inmueble) {
            return false;
        }
        if (!Objects.equals(this.tipoNotificacion, other.tipoNotificacion)) {
            return false;
        }
        if (!Objects.equals(this.mensaje, other.mensaje)) {
            return false;
        }
        if (!Objects.equals(this.cuentaOrigen, other.cuentaOrigen)) {
            return false;
        }
        if (!Objects.equals(this.cuentaDestino, other.cuentaDestino)) {
            return false;
        }
        if (!Objects.equals(this.inmuebleNotificacion, other.inmuebleNotificacion)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Notificacion{" + "id_notificacion=" + id_notificacion + ", cuentaOrigen=" + cuentaOrigen + ", id_cuenta_origen=" + id_cuenta_origen + ", id_cuenta_destino=" + id_cuenta_destino + ", id_inmueble=" + id_inmueble + ", cuentaDestino=" + cuentaDestino + ", inmuebleNotificacion=" + inmuebleNotificacion + ", fechaNotificacion=" + fechaNotificacion + ", tipoNotificacion=" + tipoNotificacion + ", mensaje=" + mensaje + '}';
    }

    
    
    
    
}
