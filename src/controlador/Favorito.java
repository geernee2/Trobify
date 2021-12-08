/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import basededatos.DAL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author vicen
 */
public class Favorito {
    
    private DAL dal = new DAL();
    
    private int id_fav, id_cuenta, id_inm;
    
    private Inmueble inmueble;
    
    private String grupo, nota;
    
    private Boolean sigue, notificacion;
    
    public Favorito(int id_fav, int id_cuenta, int id_inm, boolean sigue, String nota, boolean notificacion, String grupo)//Constructor opcional sin gasto de consulta SQL para Inmueble
    {
        this.id_fav = id_fav;
        this.id_inm = id_inm;
        this.sigue = sigue;
        this.nota = nota;
        this.notificacion = notificacion;
        this.grupo = grupo;
    }
    
    public Favorito(){}//Constructor vacio
    
    public Favorito(int id_fav, int id_cuenta, Inmueble inmueble, boolean sigue, String nota, boolean notificacion, String grupo)
    {
        this.id_fav = id_fav;
        this.id_cuenta = id_cuenta;
        this.inmueble = inmueble;
        this.sigue = sigue;
        this.nota = nota;
        this.notificacion = notificacion;
        this.grupo = grupo;
    }
    
    public Favorito convertToFavorito(ResultSet rs) throws SQLException
    {
        id_fav = rs.getInt("id_favorito");
        id_cuenta = rs.getInt("id_cuenta");
        Inmueble inm = dal.getInmuebleByID(rs.getInt("id_inmueble"));
        sigue = rs.getBoolean("sigue_favorito");
        nota = rs.getString("nota_detalle");
        notificacion = rs.getBoolean("notificacion");
        grupo = rs.getString("grupo");
        
        Favorito res = new Favorito(id_fav,id_cuenta,inm,sigue,nota,notificacion,grupo);
        return res;
    }


    public int getId_fav() {
        return id_fav;
    }

    public void setId_fav(int id_fav) {
        this.id_fav = id_fav;
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

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Boolean getSigue() {
        return sigue;
    }

    public void setSigue(Boolean sigue) {
        this.sigue = sigue;
    }

    public Boolean getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Boolean notificacion) {
        this.notificacion = notificacion;
    }

    @Override
    public String toString() {
        return "Favorito{" + "id_fav=" + id_fav + ", id_cuenta=" + id_cuenta + ", id_inm=" + id_inm + ", o este inmueble=" + inmueble.getId_inm() + ", grupo=" + grupo + ", nota=" + nota + ", sigue=" + sigue + ", notificacion=" + notificacion + '}';
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
        final Favorito other = (Favorito) obj;
        if (this.id_fav != other.id_fav) {
            return false;
        }
        if (this.id_cuenta != other.id_cuenta) {
            return false;
        }
        if (this.id_inm != other.id_inm) {
            return false;
        }
        if (!Objects.equals(this.grupo, other.grupo)) {
            return false;
        }
        if (!Objects.equals(this.nota, other.nota)) {
            return false;
        }
        if (!Objects.equals(this.inmueble, other.inmueble)) {
            return false;
        }
        if (!Objects.equals(this.sigue, other.sigue)) {
            return false;
        }
        if (!Objects.equals(this.notificacion, other.notificacion)) {
            return false;
        }
        return true;
    }
    
    
}
