/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.net.URL;
import java.sql.SQLException;
import controlador.Inmueble;
import controlador.Cuenta;
import controlador.Favorito;
import controlador.Mensaje;
import controlador.Notificacion;
import controlador.Tecnico;
import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class DAL {
   
    Inmueble inm = new Inmueble();
    Statement statement;
    Statement statement2;
    
    private Connection connection() throws SQLException
    {
        ConnectionClass connectionClass = ConnectionClass.getInstance();
        Connection connection = connectionClass.getConnection();
        statement = connection.createStatement();
        return connection;
    }
    
    
    public Inmueble getInmuebleByID(int id_inm) throws SQLException
    {   
        Inmueble res = new Inmueble();

        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `Inmuebles` WHERE id_inmueble = " + id_inm);
        if(resultSet.next())
        {
           res = inm.convertToInmueble(resultSet);
        }
        connected.close();
        return res;      
    }
    
    public void addInmueble(boolean amueblado, boolean archivado, String barrio, String calle, String ciudad, String descripcion, String estado, boolean exterior ,int id_cuenta, int nBanos, int nHabit, String mapa,
                    String nombre, int numero,int piso, int plantas, double precio, String provincia, int superficie, String tipo_inmueble, String tipo_de_venta, int veces_visto, double latitud, double longitud ) throws SQLException
    {//Pendiente de Testeo
        if(existsInmueble(calle,ciudad,provincia,barrio,numero))
        {
            
            Alert a = new Alert(AlertType.ERROR,"El inmueble que intentas añadir ya existe en la Base de Datos",ButtonType.OK);
            a.setTitle("Inmueble existente");
            a.show();
             
        }
        else{
        String  insertion ="INSERT INTO `Inmuebles`(`id_inmueble`, `id_cuenta`, `nombre`, `tipo_inmueble`, `tipo_de_venta`, `precio`, ";
                insertion +="`descripcion`, `superficie`, `estado`, `veces_visto_semana`, `provincia`, `ciudad`, `barrio`, `calle`, `numero`, ";
                insertion +="`plantas`, `exterior`, `piso`, `n_baños`, `n_habitaciones`, `amueblado`, `archivado`, `mapa`, `latitud`, `longitud`) ";
                
                insertion +="VALUES (NULL," + id_cuenta + ",'" + nombre + "','" + tipo_inmueble +"','" + tipo_de_venta + "'," + precio;
                insertion += ",'" + descripcion + "'," + superficie + ",'" + estado + "'," + veces_visto + ",'" + provincia + "','" + ciudad + "','" + barrio + "','" + calle + "'," + numero;
                insertion += "," + plantas + "," + exterior + "," + piso + "," + nBanos + "," + nHabit + "," + amueblado + "," + archivado + ",'" + mapa + "'," + latitud + "," + longitud + ")" ;
                //System.out.println(insertion);
        Connection connected = connection();
        statement.executeUpdate(insertion);
        connected.close();
        }
    }
    
    public int getIdInmuebleWithIdcuenta(String nombre, int id_cuenta) throws SQLException
    {
        int idInmueble = 0;
        Connection connected = connection();
        String instruction = "SELECT `id_inmueble` FROM `Inmuebles` WHERE id_cuenta =" + id_cuenta + " AND nombre = '" + nombre + "'";
        //System.out.println(instruction);
        ResultSet resultSet = statement.executeQuery(instruction);
        
        if(resultSet.next())
        {
           idInmueble = resultSet.getInt("id_inmueble");
        }else
        {
            idInmueble = 0;
        }
        connected.close();
        return idInmueble;
        
    }
    
    public List<Integer> getIdInmuebleByIdcuenta(int id_cuenta) throws SQLException
    {
        List<Integer> res = new ArrayList<Integer>();
        
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT id_inmueble FROM Inmuebles WHERE id_cuenta=" + id_cuenta);
        while(resultSet.next())
        {
            res.add(resultSet.getInt("id_inmueble"));
            
        }
        connected.close();
        return res;
        
    }
    
    public boolean existsInmueble(String calle, String ciudad, String provincia, String barrio, int numero) throws SQLException 
    {
        boolean res = true;
        Connection connected = connection();
        String instruction = "SELECT id_inmueble FROM `Inmuebles` WHERE calle = '" + calle + "' AND ciudad = '" + ciudad + "' AND provincia = '" + provincia + "' AND barrio = '" + barrio + "' AND numero = " + numero;
        //System.out.println(instruction);
        ResultSet resultSet = statement.executeQuery(instruction);
        if(!resultSet.next())
        {
           res = false;
        }
        connected.close();
        return res;
    }
    
    public void updateInmueble(int id_inmueble, String nombre, String tipo_inmueble, String tipo_de_venta, double precio, String descripcion, int superficie, String estado, int veces_visto_semana,
            String provincia, String ciudad, String barrio, String calle, int numero, int plantas, boolean exterior, int piso, int n_banos, int n_habitaciones,
            boolean amueblado, boolean archivado, String mapa , double latitud, double longitud) throws SQLException 
 //Falta testeo para ver si funciona
    {
        String update = "UPDATE `Inmuebles` SET `nombre`='"+ nombre + "',`tipo_inmueble`='"+ tipo_inmueble;
            update += "',`tipo_de_venta`='"+tipo_de_venta+"',`precio`=" + precio + ",`descripcion`='" + descripcion + "',`superficie`=" + superficie + ",`estado`='" + estado;
            update += "',`veces_visto_semana`=" + veces_visto_semana + ",`provincia`='" + provincia + "',`ciudad`='" + ciudad + "',`barrio`='" + barrio + "',`calle`='" + calle;
            update += "',`numero`=" + numero + ",`plantas`=" + plantas + ",`exterior`=" + exterior + ",`piso`=" + piso + ",`n_baños`=" + n_banos;
            update += ",`n_habitaciones`=" + n_habitaciones + ",`amueblado`=" + amueblado + ",`archivado`=" + archivado + ",`mapa`='" + mapa + "', `latitud`=" + latitud + ", `longitud`=" + longitud + " WHERE id_inmueble = " + id_inmueble;
        Connection connected = connection();
        //System.out.println(update);
        statement.executeUpdate(update);
        connected.close();
    }
    
    public void deleteInmueble(int id_inm) throws SQLException 
    {
        Connection connected = connection();
        statement.executeUpdate("DELETE FROM `Inmuebles` WHERE `id_inmueble` = " + id_inm);
        
        connected.close();
    }
    
    public Cuenta login(String email, String password) throws SQLException
    {
        Cuenta res = new Cuenta();
        
        Connection connected = connection();
        ResultSet rs = statement.executeQuery("SELECT * FROM `Cuentas` WHERE email = '" + email +"' AND contraseña = '" + password + "'");
        if(rs.next())
        {
          res = new Cuenta(rs.getInt("id_cuenta"), rs.getInt("telefono"), rs.getString("email"), rs.getString("nombre"), rs.getString("tipo_cuenta"), rs.getDate("fecha_creacion"), rs.getString("apellidos"), rs.getString("fotoPerfil"));
        }else
        {
            //aqui es cuando la contrasena o email estan mal ya que no encuentra resultados
            res = null;
        }
        connected.close();
        return res;        
    }
    
    public String getContrasena(int id_cuenta) throws SQLException
    {
     String password ="";
     Connection connected = connection();
     ResultSet resultSet = statement.executeQuery("SELECT contraseña FROM `Cuentas` WHERE id_cuenta = " + id_cuenta);
     if(resultSet.next()){
       password  = resultSet.getString("contraseña");
     }
     connected.close();
     return password;
    }
    
    public Cuenta getCuentaById(int id_cuenta) throws SQLException
    {
         Cuenta res = new Cuenta();
        
        Connection connected = connection();
        ResultSet rs = statement.executeQuery("SELECT * FROM `Cuentas` WHERE id_cuenta = " + id_cuenta);
        if(rs.next())
        {
           res = new Cuenta(rs.getInt("id_cuenta"), rs.getInt("telefono"), rs.getString("email"), rs.getString("nombre"), rs.getString("tipo_cuenta"), rs.getDate("fecha_creacion"), rs.getString("apellidos"), rs.getString("fotoPerfil"));
        }else
        {
            res = null;
        }
        connected.close();
        return res;
    }
    
    public boolean addCuenta(int telefono, String email, String nombre, String apellidos, String tipoCuenta, String contrasena, String imagenPerfil) throws SQLException
    {
        if(!existsEmail(email))
        {
            String insertion = "INSERT INTO Cuentas(id_cuenta, nombre, contraseña, email, fecha_creacion, tipo_cuenta, telefono, apellidos, fotoPerfil)";
            insertion += " VALUES (NULL, '" + nombre + "', '" + contrasena + "', '" + email + "', CURRENT_TIMESTAMP, '" + tipoCuenta + "', " + telefono + ", '" + apellidos + "', '" + imagenPerfil +"')";
            Connection connected = connection();
            statement.executeUpdate(insertion);
            connected.close();
            return true;
        }else
        {
            Alert a = new Alert(AlertType.ERROR,"El correo electronico esta asociado a una cuenta existente.",ButtonType.OK);
            a.setTitle("Error");
            a.show();
            return false;
        }
    }
    
    public void updateProfile(int id_cuenta,int telefono,String email, String nombre, String contrasena) throws SQLException
    {
            String insertion = "UPDATE  Cuentas  SET  nombre = '" + nombre + "', contraseña ='" + contrasena + "', email ='" + email + "', telefono = " + telefono + " WHERE id_cuenta = " + id_cuenta;
            //System.out.println(insertion);
            Connection connected = connection();
            statement.executeUpdate(insertion);
            connected.close();
    }
    
    public void updateProfileImage(int id_cuenta, String url) throws SQLException
    {
            String insertion = "UPDATE  Cuentas  SET fotoPerfil = '" + url + "' WHERE id_cuenta = " + id_cuenta;
            Connection connected = connection();
            statement.executeUpdate(insertion);
            connected.close();
    }
    
    public boolean existsEmail(String email) throws SQLException
    {
        boolean res = true;
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT id_cuenta FROM `Cuentas` WHERE `email` = '" + email + "'");
        if(!resultSet.next())
        {
           res = false;
        }
        connected.close();
        return res;
    }
    
    public void addSolicitudVisita(int id_cuenta, int id_inmueble, LocalDate fecha) throws SQLException
    {
    String insertion = "INSERT INTO Visitas (id_visita, id_cuenta, id_inmueble, fecha, completado, incidencia, cancelado, usuario_cancelado)";
        insertion += " VALUES (NULL, " + id_cuenta; 
        insertion += " ," + id_inmueble + ",'" + fecha + "', '0', NULL, '0', '0')";
        
    Connection connected = connection();
    statement.executeUpdate(insertion);
    connected.close();
    }
    
    public String getPreviewPhoto(int id_inm) throws SQLException //Para la pagina de resultados cargamos 1 foto
    {
        String url = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/300px-No_image_available.svg.png"; // enlace con imagen que dice: No image 
        
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT F.enlace FROM Fotos F, Estancias E WHERE E.id_estancia = F.id_estancia AND E.id_inmueble =  " + id_inm);
        
        if(resultSet.next()){
            url = resultSet.getString("enlace");
        }
        connected.close();
        return url;
    }
    
    public List<String> getInmEstanciaPhotos(int id_inm) throws SQLException
    {
       List<String> res = new ArrayList<String>();
       
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT F.enlace, E.nombre FROM Fotos F, Estancias E WHERE E.id_estancia = F.id_estancia AND E.id_inmueble =  " + id_inm);
        
        while(resultSet.next() && res.size() < 20){
            res.add(resultSet.getString("enlace"));
            res.add(resultSet.getString("nombre"));
        }
        connected.close();
        return res;
    }
    
    public List<Integer> getIdInmCiudad(String ciudad, String tipoInmueble, String tipoDeVenta) throws SQLException
    {
        List<Integer> res = new ArrayList<Integer>();
        
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT id_inmueble FROM Inmuebles WHERE tipo_inmueble='" + tipoInmueble + "' AND tipo_de_venta='"+ tipoDeVenta + "' AND (ciudad LIKE  '%" + ciudad + "%' OR barrio LIKE '%" + ciudad + "%' OR calle LIKE '%" + ciudad + "%')");
        while(resultSet.next())
        {
            res.add(resultSet.getInt("id_inmueble"));
            
        }
        connected.close();
        return res;
    }
    
    public List<Integer> getCarBoolInm(String tipoInm) throws SQLException
    {
        List<Integer> res = new ArrayList<Integer>();
        
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT garaje g, admite_mascotas am, aire_acondicionado ac,"
                + "ascensor a, jardin j, piscina p, terraza te, trastero tr, agua_caliente ag, calefaccion c, seguridad s"
                + " FROM Caracteristicas_Boolean WHERE tipo_inmueble='" + tipoInm + "'");
        while(resultSet.next())
        {
            res.add(resultSet.getInt("g"));
            res.add(resultSet.getInt("am"));
            res.add(resultSet.getInt("ac"));
            res.add(resultSet.getInt("a"));
            res.add(resultSet.getInt("j"));
            res.add(resultSet.getInt("p"));
            res.add(resultSet.getInt("te"));
            res.add(resultSet.getInt("tr"));
            res.add(resultSet.getInt("ag"));
            res.add(resultSet.getInt("c"));
            res.add(resultSet.getInt("s"));
        }
        connected.close();
        return res;
    }
    
    public List<String> getViviendas() throws SQLException{
         List<String> res = new ArrayList<String>();
        
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT nombre FROM Tipo_Vivienda");
        while(resultSet.next())
        {
            res.add(resultSet.getString("nombre"));
        }
        connected.close();
        return res;
    }
    
    public void deleteEstanciaWithPhotos(int id_estancia) throws SQLException
    {
        Connection connected = connection();
        String delete ="DELETE FROM Estancias WHERE id_estancia = " + id_estancia;
        statement.executeUpdate(delete);
        connected.close();
    }
    
    public void deletePhoto(String url) throws SQLException
    {
        Connection connected = connection();
        String  delete = "DELETE FROM Fotos WHERE enlace = '" + url + "'";
        statement.executeUpdate(delete);
        connected.close();
    }

    public String getTipoViviendaByIdInm(int id_inmueble) throws SQLException
    {
        String tipoVivienda ="";
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT tipo_vivienda FROM `Caracteristicas` WHERE id_inmueble = " + id_inmueble);
        if(resultSet.next())
        {
           tipoVivienda = resultSet.getString("tipo_vivienda");
        }
        connected.close();
        return tipoVivienda;
    }
    
    public List<Integer> buscarInmuebles(String ciudad, String tipoInmueble, String tipoDeVenta, String precioMin, String precioMax, 
            String habitaciones, String baños, boolean amueblado, String tipoVivienda, boolean garaje, boolean admite_mascotas, boolean aire_acondicionado, 
            boolean ascensor, boolean jardin, boolean piscina, boolean terraza, boolean trastero, boolean agua_caliente, boolean calefaccion , boolean seguridad) throws SQLException
    {
        List<Integer> res = new ArrayList<Integer>();
        
        Connection connected = connection();
        String consulta = "";
        consulta += "SELECT I.id_inmueble FROM Inmuebles I INNER JOIN Caracteristicas C WHERE I.estado = 'Disponible' AND C.id_inmueble=I.id_inmueble AND I.tipo_inmueble='" + tipoInmueble + "' AND I.tipo_de_venta='"+ tipoDeVenta + "' ";
        if(ciudad.length()>=0){
            consulta += " AND (I.ciudad LIKE  '%" + ciudad + "%' OR I.barrio LIKE '%" + ciudad + "%' OR I.calle LIKE '%" + ciudad + "%')";
        }
        if(precioMin.length()>0){
            consulta += " AND I.precio>=" + precioMin;
        }
        if(precioMax.length()>0){
            consulta += " AND I.precio<=" + precioMax;
        }
        if(habitaciones.length()>0){
            consulta += " AND I.n_habitaciones>=" + habitaciones;
        }
        if(baños.length()>0){
            consulta += " AND I.n_baños>=" + baños;
        }
        if(amueblado){
            consulta += " AND I.amueblado=1";
        }
        if(tipoVivienda!=""){
            consulta += " AND C.tipo_vivienda='" + tipoVivienda + "'";
        }
        if(garaje){
            consulta += " AND C.garaje=1";
        }
        if(admite_mascotas){
            consulta += " AND C.admite_mascotas=1";
        }
        if(aire_acondicionado){
            consulta += " AND C.aire_acondicionado=1";
        }
        if(ascensor){
            consulta += " AND C.ascensor=1";
        }
        if(jardin){
            consulta += " AND C.jardin=1";
        }
        if(piscina){
            consulta += " AND C.piscina=1";
        }
        if(terraza){
            consulta += " AND C.terraza=1";
        }
        if(trastero){
            consulta += " AND C.trastero=1";
        }
        if(agua_caliente){
            consulta += " AND C.agua_caliente=1";
        }
        if(calefaccion){
            consulta += " AND C.calefaccion=1";
        }
        if(seguridad){
            consulta += " AND C.seguridad=1";
        }
        
        //System.out.println("consulta: " + consulta);
        
        ResultSet resultSet = statement.executeQuery(consulta);
        while(resultSet.next())
        {
            res.add(resultSet.getInt("id_inmueble"));
            
        }
        connected.close();
        return res;
    }
    
    public void addInmuebleToFavoritos(int id_inmueble, int id_cuenta, String grupo) throws SQLException
    {
        if(isFavorito(id_inmueble,id_cuenta))
        {
            Alert a = new Alert(AlertType.INFORMATION,"El inmueble ya está en favoritos.",ButtonType.OK);
            a.setTitle("Inmueble en favoritos");
            a.show();
            Stage stage = (Stage)a.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/recursos/logoFinal.png"));
        }else
        {
            Connection connected = connection();
            String insertion = "INSERT INTO `Favoritos`(`id_favorito`, `id_cuenta`, `id_grupo`, `id_inmueble`, `sigue_favorito`, `nota_detalle`, `notificacion`, `grupo`) ";
                   insertion += "VALUES (NULL, "+ id_cuenta +", "+ 1 +", "+ id_inmueble +", " + false + ", '', " + true + ", '" + grupo + "')";
            //System.out.println(insertion);
            statement.executeUpdate(insertion);
            connected.close();
        }
    }
    
    public boolean isFavorito(int id_inmueble, int id_cuenta) throws SQLException
    {
        boolean res = false;
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT `id_favorito` FROM `Favoritos` WHERE `id_inmueble` = " + id_inmueble + " AND id_cuenta = " + id_cuenta);
        
        if(resultSet.next())
        {
           res = true;
        }
        connected.close();
        return res;
    }
    
    public List<String> getListGruposFavs(int id_cuenta) throws SQLException
    {
        List<String> res = new ArrayList<String>();
        int i = 1;
        
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT grupo FROM Favoritos WHERE id_cuenta = " + id_cuenta + " GROUP BY grupo");
        
        while(resultSet.next())
        {
            res.add(resultSet.getString(1));
        }
        connected.close();
        return res;
    }
    
    public List<String> getPhotos(int id_inmueble, String nombre) throws SQLException
    {
        List<String> res = new ArrayList<String>();
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT Fotos.enlace FROM Estancias, Fotos WHERE Estancias.id_inmueble = " + id_inmueble + " AND Estancias.nombre = '" + nombre +"' AND Fotos.id_estancia = Estancias.id_estancia");
        
        while(resultSet.next())
        {
            res.add(resultSet.getString("enlace"));
        }
        connected.close();
        return res;
    }
    
    public List<String> getListEstancias(int id_inmueble) throws SQLException
    {
        List<String> res = new ArrayList<String>();
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT `nombre` FROM `Estancias` WHERE `id_inmueble` = " + id_inmueble);
        
        while(resultSet.next())
        {
            res.add(resultSet.getString("nombre"));
        }
        connected.close();
        return res;
    }
    
    public List<Favorito> getListFavs(int id_cuenta) throws SQLException
    {
        List<Favorito> res = new ArrayList<Favorito>();
        Favorito aux = new Favorito();
        
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `Favoritos` WHERE id_cuenta = " + id_cuenta);
        
        while(resultSet.next())
        {
            res.add(aux.convertToFavorito(resultSet));
        }
        connected.close();
        return res;
    }
    
    public void deleteFav(int id_favorito) throws SQLException
    {
        Connection connected = connection();
        String delete = "DELETE FROM `Favoritos` WHERE id_favorito = " + id_favorito;
        statement.executeUpdate(delete);
        connected.close();
    }
    
    
    
    public void addEstancia(String nombre, int id_inmueble) throws SQLException
    {
        Connection connected = connection();
        String insertion = "INSERT INTO `Estancias`(`id_estancia`, `id_inmueble`, `nombre`) ";
               insertion += "VALUES (NULL, " + id_inmueble + ", '" + nombre + "')";
        statement.executeUpdate(insertion);
        connected.close();
    }
    
    public void addImagen(int id_estancia, String url) throws SQLException
    {
        Connection connected = connection();
        String insertion = "INSERT INTO `Fotos`(`id_foto`, `id_estancia`, `enlace`) ";
               insertion += "VALUES (NULL, " + id_estancia + ", '" + url + "' )";
        statement.executeUpdate(insertion);
        connected.close();
    }
    
    public int getIdEstancia(String nombre, int id_inmueble) throws SQLException
    {
        int idEstancia;
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT `id_estancia` FROM `Estancias` WHERE nombre = '" + nombre + "' AND id_inmueble = " + id_inmueble);
        if(resultSet.next())
        {
           idEstancia = resultSet.getInt("id_estancia");
        }else{
            idEstancia = 0;
        }
        connected.close();
        return idEstancia;
    }
    
    public void saveNota(String nota, int fav_id)throws SQLException {
        Connection connected = connection();
        String sql="UPDATE Favoritos SET nota_detalle = '" + nota + "' WHERE id_favorito = " + fav_id;
        statement.executeUpdate(sql);
        connected.close();
    }
    
    public void addCaracteristicas(int id_inmueble, String tipo_vivienda, boolean garaje,boolean admite_mascotas,boolean aire_acondicionado,boolean ascensor,boolean jardin,boolean piscina,boolean terraza,boolean trastero,boolean agua_caliente,boolean calefaccion,boolean seguridad) throws SQLException
    {
        Connection connected = connection();
        String  insertion ="INSERT INTO `Caracteristicas`(`id_caracteristica`, `id_inmueble`, `tipo_vivienda`, `garaje`, `admite_mascotas`, `aire_acondicionado`, `ascensor`, `jardin`, `piscina`, `terraza`, `trastero`, `agua_caliente`, `calefaccion`, `seguridad`) VALUES (";
                insertion +="NULL, " + id_inmueble + ",'" + tipo_vivienda + "', " + garaje + "," + admite_mascotas + "," + aire_acondicionado + "," + ascensor + "," + jardin + "," + piscina + "," + terraza + "," + trastero + "," + agua_caliente + "," + calefaccion + "," + seguridad + ")";
        //System.out.println(insertion);
        statement.executeUpdate(insertion);
        connected.close();
    }
    
    public List<Integer> comparadorPrecio(int superficiemax, int superficiemin, String ciudad, String barrio, String tipoInmueble, String tipoDeVenta, String numBanos, String numHabi, String tipoVivienda) throws SQLException{
        //requisitos minnimos : superficie, ciudad, barrio, tipoInmueble, tipoVenta, tipoVivienda
        //requisito ampliable : num baños, num habi
            
        List<Integer> res = new ArrayList<Integer>();
        Connection connected = connection();
        String consulta = "";
        consulta += "SELECT I.id_inmueble FROM Inmuebles I INNER JOIN Caracteristicas C WHERE I.id_inmueble = C.id_inmueble AND I.tipo_inmueble='" + tipoInmueble + "' AND I.tipo_de_venta='"+ tipoDeVenta + "' AND I.superficie >= " + superficiemin ;
        consulta += " AND I.superficie <= " + superficiemax + " AND I.barrio='" + barrio + "'";
        if(numBanos.length() > 0){
            consulta += " AND I.n_baños = " + numBanos;
        }
        if(numHabi.length() > 0){
            consulta += " AND I.n_habitaciones = " + numHabi;
        }
        if(tipoInmueble.equals("Vivienda")){
            consulta += " AND tipo_vivienda = '" + tipoVivienda +"'";
        }
        
        //System.out.println(consulta);
        ResultSet resultSet = statement.executeQuery(consulta);
        while(resultSet.next()){
            res.add(resultSet.getInt("id_inmueble"));
        }
        
        connected.close();
        return res;
        
    }
    
    public void updateCaract(int id_inmueble, String tipo_vivienda, boolean garaje, boolean admite_mascotas, boolean aire_acondicionado, boolean ascensor, boolean jardin, boolean piscina, boolean terraza, boolean trastero, boolean agua_caliente, boolean calefaccion,boolean seguridad) throws SQLException
    {
        Connection connected = connection();
        String  update = "UPDATE `Caracteristicas` SET `tipo_vivienda`= '" + tipo_vivienda + "',`garaje`=" + garaje + ",`admite_mascotas`= " + admite_mascotas;
                update += ",`aire_acondicionado`=" + aire_acondicionado + ",`ascensor`= " + ascensor + ",`jardin`= " + jardin + ",`piscina`= " + piscina;
                update += ",`terraza`= " + terraza + ",`trastero`= " + trastero + ",`agua_caliente`= " + agua_caliente + ",`calefaccion`= " + calefaccion + ",`seguridad`= " + seguridad + " WHERE id_inmueble = " + id_inmueble;
        statement.executeUpdate(update);
        
        connected.close();
    }
    
    public List<Boolean> getListCaracteristicas(int id_inmueble) throws SQLException
    {
        List<Boolean> res = new ArrayList<Boolean>();
        
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `Caracteristicas` WHERE `id_inmueble` = " + id_inmueble);
        
        resultSet.next();
        res.add(resultSet.getBoolean("garaje"));
        res.add(resultSet.getBoolean("admite_mascotas"));
        res.add(resultSet.getBoolean("aire_acondicionado"));
        res.add(resultSet.getBoolean("ascensor"));
        res.add(resultSet.getBoolean("jardin"));
        res.add(resultSet.getBoolean("piscina"));
        res.add(resultSet.getBoolean("terraza"));
        res.add(resultSet.getBoolean("trastero"));
        res.add(resultSet.getBoolean("agua_caliente"));
        res.add(resultSet.getBoolean("calefaccion"));
        res.add(resultSet.getBoolean("seguridad"));
        
        connected.close();
        return res;
    }

    public List<Notificacion> getAllNotificacionesByIdCuentaDestino(int id_cuentaD) throws SQLException
    {
        List<Notificacion> res = new ArrayList<Notificacion>();
        Notificacion aux = new Notificacion();
        
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM `Notificaciones` WHERE `id_cuenta_destino` = " + id_cuentaD );
        
         while(resultSet.next())
        {
            res.add(aux.convertToNotificacion(resultSet));
        }
         
        connected.close();
        return res;
    }
    
    public void addNotificacion(int id_cuenta_origen, int id_cuenta_destino, int id_inmueble, LocalDate fechaNotificacion, String tipoNotificacion, String mensaje) throws SQLException
    {
        String  insertion = "INSERT INTO `Notificaciones`(`id_notificacion`, `id_cuenta_origen`, `id_cuenta_destino`, `id_inmueble`, `fecha`, `tipoNotificacion`, `mensaje`) ";
                insertion += "VALUES (NULL," + id_cuenta_origen + "," + id_cuenta_destino + "," + id_inmueble + ",'" + fechaNotificacion + "','" + tipoNotificacion + "','" + mensaje + "')";
        //System.out.println(insertion);
        Connection connected = connection();
        statement.executeUpdate(insertion);
        connected.close();
    }
    
    public void deleteNotificacion(int id_notificacion) throws SQLException
    {
        String delete = "DELETE FROM `Notificaciones` WHERE `id_notificacion` = " + id_notificacion;
        
        Connection connected = connection();
        statement.executeUpdate(delete);
        connected.close();
    }
    
    public List<Integer> getIdCuentasInmuebleFav(int id_inm) throws SQLException{
        List<Integer> res = new ArrayList<Integer>();
        
        String insertion = "SELECT id_cuenta FROM Favoritos WHERE id_inmueble=" + id_inm;
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery(insertion);
        
        while(resultSet.next()){
            res.add(resultSet.getInt("id_cuenta"));
        }
        
        connected.close();
        return res;
    }
    
    public void addCuentaAgencia(int telefono, String email, String nombre, String tipoCuenta, String contrasena, String imagenPerfil, String pagWeb, String cuentaBancaria) throws SQLException
    {
        if(!existsEmail(email))
        {
            String insertion = "INSERT INTO Cuentas(id_cuenta, nombre, contraseña, email, fecha_creacion, tipo_cuenta, telefono, apellidos, fotoPerfil)";
            insertion += " VALUES (NULL, '" + nombre + "', '" + contrasena + "', '" + email + "', CURRENT_TIMESTAMP, '" + tipoCuenta + "', " + telefono + ", '', '" + imagenPerfil +"')";
            Connection connected = connection();
            statement.executeUpdate(insertion);
            connected.close();
            int id_cuenta = login(email, contrasena).getId_cuenta();
            //System.out.println(id_cuenta);
            insertAgenciaIntoBD(id_cuenta, cuentaBancaria, pagWeb);

            
            
            //connected.close();
        }else
        {
            Alert a = new Alert(AlertType.ERROR,"El correo electronico esta asociado a una cuenta existente.",ButtonType.OK);
            a.setTitle("Error");
            a.showAndWait();
        }
    }
    
    public void insertAgenciaIntoBD(int id_cuenta, String cuentaBancaria, String pagWeb) throws SQLException
    {
        
           String insertion = "INSERT INTO `Agencias`(`id_agencia`, `id_cuenta`, `cuenta_bancaria`, `ubicacion`, `web`) ";
            insertion += "VALUES (NULL," + id_cuenta + ",'" + cuentaBancaria + "','','" + pagWeb + "')";
            Connection connected = connection();
            statement.executeUpdate(insertion);
            connected.close();
    }
    
    public int getIdAgencia(Cuenta conte) throws SQLException
    {
        int res = -1;
        String execute = "SELECT id_agencia FROM Agencias WHERE id_cuenta = " + conte.getId_cuenta();
        //System.out.println(execute);
        Connection connected = connection();
        ResultSet rs = statement.executeQuery(execute);
        
        if(rs.next())
        {
            res = rs.getInt("id_agencia");
        }  
        connected.close();
        return res;
    }
    public int getIdAgenciaByIdTecnico(int id_cuentaTec) throws SQLException
    {
        int res = -1;
        String execute = "SELECT id_agencia FROM Tecnicos WHERE id_tecnico = " + id_cuentaTec;
        
        Connection connected = connection();
        ResultSet rs = statement.executeQuery(execute);
        
        if(rs.next())
        {
            res = rs.getInt("id_agencia");
        }  
        connected.close();
        return res;
    }
    
    public void addTecnico(Cuenta persona, Cuenta CuentaAgencia) throws SQLException
    {
        
        int id_agencia = getIdAgencia(CuentaAgencia);
        
        if(id_agencia != -1)
        {
            String insertion = "INSERT INTO `Tecnicos`(`id_tecnico`, `id_cuenta`, `id_agencia`) ";
            insertion += "VALUES (NULL," + persona.getId_cuenta() + "," + id_agencia + ")";
            Connection connected = connection();
            statement.executeUpdate(insertion);
            connected.close();
        }
    }
    
    public List<Tecnico> getAllTecnicosFromAgencia(int id_cuentaAgencia) throws SQLException
    {
        //int id_agencia = getIdAgenciaByIdTecnico(id_tecnico);
        int id_agencia = getIdAgenciaByCuenta(id_cuentaAgencia);
        List<Tecnico> res = new ArrayList<Tecnico>();
        String execute ="SELECT T.id_tecnico, T.id_cuenta, T.id_agencia,C.nombre FROM Tecnicos T, Cuentas C WHERE T.id_agencia = " + id_agencia + " AND C.id_cuenta = T.id_cuenta";
        //System.out.println(execute);
        Connection connected = connection();
        ResultSet rs = statement.executeQuery(execute);
        
        while(rs.next())
        {
            res.add(new Tecnico(rs.getInt("T.id_tecnico"),rs.getInt("T.id_agencia"),rs.getInt("T.id_cuenta"),rs.getString("C.nombre")));
            
        }
        connected.close();
        return res;
    }
    
    public void deleteTecnico(int id_tecnico) throws SQLException
    {
        String update = "DELETE FROM `Tecnicos` WHERE `id_tecnico` = " + id_tecnico;
        Connection connected = connection();
        statement.executeUpdate(update);
        connection().close();
    }
    
    public int getIdAgenciaByCuenta(int id_cuenta) throws SQLException
    {
        int res = -1;
        String execute = "SELECT `id_agencia` FROM `Agencias` WHERE id_cuenta = " + id_cuenta;
        
        Connection connected = connection();
        ResultSet rs = statement.executeQuery(execute);
        
        if(rs.next())
        {
            res = rs.getInt("id_agencia");
        }  
        connected.close();
        return res;
    }
    
    public List<Integer> getMensajeriaById(int id_cuenta) throws SQLException
    {
        List<Integer> res = new ArrayList<Integer>();
        
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT id_cuenta_origen as origen, id_cuenta_destino as destino FROM `Mensajes` WHERE id_cuenta_origen = " + id_cuenta + " || id_cuenta_destino=" + id_cuenta);
        int id = 0;
        while(resultSet.next())
        {
            if(resultSet.getInt("origen")==id_cuenta){
                id = resultSet.getInt("destino");
            }else{
                id = resultSet.getInt("origen");
            }
            
            if(!res.contains(id)) res.add(id);
            
        }
        connected.close();
        return res;
        
    }
    
    public Inmueble getInmuebleByMensajes(int id_cuenta1, int id_cuenta2) throws SQLException
    {
        Inmueble inmb = new Inmueble();;
        
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT I.* FROM Inmuebles I INNER JOIN Mensajes M WHERE M.id_inmueble = I.id_inmueble && ((id_cuenta_origen="+ id_cuenta1 + " && id_cuenta_destino="+ id_cuenta2 + ") || (id_cuenta_origen="+ id_cuenta2 + " && id_cuenta_destino="+ id_cuenta1 + "))");
        
        while(resultSet.next())
        {
            inmb = new Inmueble(resultSet.getBoolean("amueblado"), resultSet.getBoolean("archivado"), resultSet.getString("barrio"), 
            resultSet.getString("calle"), resultSet.getString("ciudad"), resultSet.getString("descripcion"), 
            resultSet.getString("estado"), resultSet.getBoolean("exterior"), resultSet.getInt("id_cuenta"), 
            resultSet.getInt("id_inmueble"), resultSet.getInt("n_baños"), resultSet.getInt("n_habitaciones"), 
            resultSet.getString("mapa"), resultSet.getString("nombre"), resultSet.getInt("numero"), 
            resultSet.getInt("piso"), resultSet.getInt("plantas"), resultSet.getDouble("precio"), 
            resultSet.getString("provincia"), resultSet.getInt("superficie"), resultSet.getString("tipo_inmueble"), 
            resultSet.getString("tipo_de_venta"), resultSet.getInt("veces_visto_semana"));
            
        }
        connected.close();
        return inmb;
        
    }
    
    public List<Mensaje> getMensajesByIds(int id_cuenta1, int id_cuenta2) throws SQLException
    {
        List<Mensaje> res = new ArrayList<Mensaje>();
        Connection connected = connection();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Mensajes WHERE (id_cuenta_origen=" + id_cuenta1 + " and id_cuenta_destino=" + id_cuenta2 + ") || (id_cuenta_origen=" + id_cuenta2 + " and id_cuenta_destino=" + id_cuenta1 + ") order by id_mensaje");
        
        while(resultSet.next())
        {
            res.add(new Mensaje(resultSet.getInt("id_cuenta_origen"),resultSet.getInt("id_cuenta_destino"),resultSet.getInt("id_inmueble"), resultSet.getString("contenido")));
            
        }
        connected.close();
        return res;
    }
    
    public void enviarMensaje(int id_cuenta1, int id_cuenta2, int id_inmueble, String contenido) throws SQLException
    {
        Connection connected = connection();
        statement.executeUpdate("INSERT INTO `Mensajes` (`id_mensaje`, `id_cuenta_origen`, `id_cuenta_destino`, `id_inmueble`, `contenido`, `fecha`, `visto`) VALUES (NULL, "+ id_cuenta1 + ", " + id_cuenta2 +", " + id_inmueble + ", '" + contenido +"', CURRENT_TIMESTAMP, '0')");

        connected.close();
    }
}

