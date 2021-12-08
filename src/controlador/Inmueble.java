/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.time.LocalDate;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Logger;

/**
 *
 * @author vicen
 */
public class Inmueble {
    
    private boolean amueblado;
    private boolean archivado;
    private boolean exterior;
    
    private double precio;
    

    private int id_cuenta;
    private int id_inm;
    private int nBanos;
    private int nHabit;
    private int numero;
    private int superficie;
    private int veces_visto;
    private int piso;
    private int plantas;
    
    private String barrio;
    private String calle;
    private String ciudad;
    private String descripcion;
    private String estado;
    private String nombre;
    private String provincia;
    private String tipoInm;
    private String tipoVenta;
    private String mapa;
    
            
            
    public Inmueble(boolean amueblado, boolean archivado, String barrio, String calle, String ciudad, String descripcion, String estado, boolean exterior ,int id_cuenta, int id_inm, int nBanos, int nHabit, String mapa,
                    String nombre, int numero,int piso, int plantas, double precio, String provincia, int superficie, String tipoInm, String tipoVenta, int veces_visto)
    {
        this.amueblado = amueblado;
        this.archivado = archivado;
        this.barrio = barrio;
        this.calle = calle;
        this.ciudad = ciudad;
        this.estado = estado;
        this.descripcion = descripcion;
        this.id_cuenta = id_cuenta;
        this.id_inm = id_inm;
        this.nBanos = nBanos;
        this.nHabit = nHabit;
        this.mapa = mapa;
        this.nombre = nombre;
        this.numero = numero;
        this.piso = piso;
        this.plantas = plantas;
        this.precio = precio;
        this.provincia = provincia;
        this.superficie = superficie;
        this.tipoInm = tipoInm;
        this.tipoVenta = tipoVenta;
        this.veces_visto = veces_visto;
    }

    public Inmueble(){} // Constructor de Inmueble Vacio
    
    public Inmueble convertToInmueble(ResultSet rs) throws SQLException
    {
       amueblado = rs.getBoolean("amueblado");
       archivado = rs.getBoolean("archivado");
       barrio = rs.getString("barrio");
       calle = rs.getString("calle");
       ciudad = rs.getString("ciudad");
       descripcion = rs.getString("descripcion");
       estado = rs.getString("estado");
       exterior = rs.getBoolean("exterior");
       id_cuenta = rs.getInt("id_cuenta");
       id_inm = rs.getInt("id_inmueble");
       nBanos = rs.getInt("n_baños");
       nHabit = rs.getInt("n_habitaciones");
       mapa = rs.getString("mapa");
       nombre = rs.getString("nombre");
       numero = rs.getInt("numero");
       piso = rs.getInt("piso");
       plantas = rs.getInt("plantas");
       precio = rs.getDouble("precio");
       provincia = rs.getString("provincia");
       superficie = rs.getInt("superficie");
       tipoInm = rs.getString("tipo_inmueble");
       tipoVenta = rs.getString("tipo_de_venta");
       veces_visto = rs.getInt("veces_visto_semana");
       
       Inmueble inm = new Inmueble(amueblado,archivado,barrio,calle,ciudad,descripcion,estado,exterior,id_cuenta,id_inm,nBanos,nHabit,mapa,nombre,numero,piso,plantas,precio,provincia,superficie,tipoInm,tipoVenta,veces_visto);
       
       return inm;
    }

    public String getMapa() {
        return mapa;
    }

    public void setMapa(String mapa) {
        this.mapa = mapa;
    }

    
    
    public boolean getAmueblado() {
        return amueblado;
    }

    public void setAmueblado(boolean amueblado) {
        this.amueblado = amueblado;
    }

    public boolean getArchivado() {
        return archivado;
    }

    public void setArchivado(boolean archivado) {
        this.archivado = archivado;
    }

    public boolean getExterior() {
        return exterior;
    }

    public void setExterior(boolean exterior) {
        this.exterior = exterior;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(int id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public int getId_inm() {
        return id_inm;
    }

    public void setId_inm(int id_inm) {
        this.id_inm = id_inm;
    }

    public int getnBanos() {
        return nBanos;
    }

    public void setnBanos(int nBanos) {
        this.nBanos = nBanos;
    }

    public int getnHabit() {
        return nHabit;
    }

    public void setnHabit(int nHabit) {
        this.nHabit = nHabit;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public int getVeces_visto() {
        return veces_visto;
    }

    public void setVeces_visto(int veces_visto) {
        this.veces_visto = veces_visto;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public int getPlantas() {
        return plantas;
    }

    public void setPlantas(int plantas) {
        this.plantas = plantas;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }


    public String getTipoInm() {
        return tipoInm;
    }

    public void setTipoInm(String tipoInm) {
        this.tipoInm = tipoInm;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
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
        final Inmueble other = (Inmueble) obj;
        if (this.amueblado != other.amueblado) {
            return false;
        }
        if (this.archivado != other.archivado) {
            return false;
        }
        if (this.exterior != other.exterior) {
            return false;
        }
        if (Double.doubleToLongBits(this.precio) != Double.doubleToLongBits(other.precio)) {
            return false;
        }
        if (this.id_cuenta != other.id_cuenta) {
            return false;
        }
        if (this.id_inm != other.id_inm) {
            return false;
        }
        if (this.nBanos != other.nBanos) {
            return false;
        }
        if (this.nHabit != other.nHabit) {
            return false;
        }
        if (this.numero != other.numero) {
            return false;
        }
        if (this.superficie != other.superficie) {
            return false;
        }
        if (this.veces_visto != other.veces_visto) {
            return false;
        }
        if (this.piso != other.piso) {
            return false;
        }
        if (this.plantas != other.plantas) {
            return false;
        }
        if (!Objects.equals(this.barrio, other.barrio)) {
            return false;
        }
        if (!Objects.equals(this.calle, other.calle)) {
            return false;
        }
        if (!Objects.equals(this.ciudad, other.ciudad)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.provincia, other.provincia)) {
            return false;
        }
        if (!Objects.equals(this.tipoInm, other.tipoInm)) {
            return false;
        }
        if (!Objects.equals(this.tipoVenta, other.tipoVenta)) {
            return false;
        }
        return true;
    }


    public String inmToString() {
        return "Inmueble{" + "amueblado=" + amueblado + ", archivado=" + archivado + ", exterior=" + exterior + ", precio=" + precio + ", id_cuenta=" + id_cuenta + ", id_inm=" + id_inm + ", nBanos=" + nBanos + ", nHabit=" + nHabit + ", numero=" + numero + ", superficie=" + superficie + ", veces_visto=" + veces_visto + ", piso=" + piso + ", plantas=" + plantas + ", barrio=" + barrio + ", calle=" + calle + ", ciudad=" + ciudad + ", descripcion=" + descripcion + ", estado=" + estado + ", nombre=" + nombre + ", provincia=" + provincia + ", tipoInm=" + tipoInm + ", tipoVenta=" + tipoVenta + '}';
    }

    
    
}

