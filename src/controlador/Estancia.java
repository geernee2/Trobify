package controlador;

import basededatos.DAL;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


public class Estancia {
   
   private List<String> imagenes; 
   private String nombre;
   private DAL dal = new DAL();
    
    public Estancia(){}//Constructor vacio
    
    public Estancia(String nombre, List<String> imagenes)
    {
        this.nombre = nombre;
        this.imagenes = imagenes;
    }

    public void uploadEstanciaAndImagenes(int id_inmueble, String nombre, List<String> imagenes) throws SQLException//debe ir despues de crear el inmueble
    {
        dal.addEstancia(nombre, id_inmueble);
        dal.getIdEstancia(nombre,id_inmueble);
        for(String x: imagenes) dal.addImagen(id_inmueble, x);
    }
    
    
    public List<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(List<String> imagenes) {
        this.imagenes = imagenes;
    }
    
    public void addImagen(String imagen) {
        this.imagenes.add(imagen);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
        final Estancia other = (Estancia) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.imagenes, other.imagenes)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
