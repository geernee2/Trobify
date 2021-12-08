/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import basededatos.DAL;
import static controlador.AnadirInmuebleController.anadirInmuebleStage;
import static controlador.InicioController.inicioStage;
import static controlador.RegistroController.registroStage;
import static controlador.ResultadosController.resultadosStage;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author David
 */
public class ModificarInmuebleController {

    @FXML
    private ImageView iconoUsuario;
    @FXML
    private Button botonMiCuenta;
    @FXML
    private Button buttonAnadirInmueble;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textPrecio;
    @FXML
    private TextField textSuperficie;
    @FXML
    private TextField textCiudad;
    @FXML
    private TextField textProvincia;
    @FXML
    private TextField textBarrio;
    @FXML
    private TextField textCalle;
    @FXML
    private TextField textNumero;
    @FXML
    private TextField textPlanta;
    @FXML
    private TextField textDescripcion;
    @FXML
    private TextField textNumPlantas;
    @FXML
    private TextField textNumBanos;
    @FXML
    private TextField textNumHabitaciones;
    @FXML
    private CheckBox checkAmueblado;
    @FXML
    private ChoiceBox<String> chooserTipoVenta;
    @FXML
    private Label textError;
    @FXML
    private Button buttonCrearEstancia;
    @FXML
    private TextField textEstancia;
    @FXML
    private ChoiceBox<String> chooserEstancia;
    @FXML
    private ImageView imageViewSeleccionada;
    @FXML
    private Button buttonAnadirImagen;
    @FXML
    private Button buttonSeleccionarImagen;
    @FXML
    private Text labelNumPlantas;
    @FXML
    private Text labelNumHabitaciones;
    @FXML
    private Text labelNumBaños;
    @FXML
    private Text labelTipoVenta;
    @FXML
    private Label labelErrores;
    @FXML
    private CheckBox checkAguaCaliente;
    @FXML
    private CheckBox checkGaraje;
    @FXML
    private CheckBox checkSeguridad;
    @FXML
    private CheckBox checkAdmiteMascotas;
    @FXML
    private CheckBox checkCalefaccion;
    @FXML
    private CheckBox checkAireAcond;
    @FXML
    private CheckBox checkJardin;
    @FXML
    private CheckBox checkTerraza;
    @FXML
    private CheckBox checkAscensor;
    @FXML
    private CheckBox checkPiscina;
    @FXML
    private Text labelTipoVivienda;
    @FXML
    private TextField textEnlaceImagen;
    @FXML
    private CheckBox checkTrastero;
    
    public static Stage modificarInmuebleStage;
    @FXML
    private Button buttonAnteriorImagen;
    @FXML
    private Button buttonSiguienteImagen;
    
    List<String> imagenesEstancia = new ArrayList<String>(); 
    @FXML
    private ChoiceBox<String> chooserTipoVivienda;
    private String tipoVivienda;
    private String tipoInmueble;
    private Image imagenSeleccionada;
    List<String> estancias = new ArrayList<String>();
    //Parametros del inmueble
    private boolean amueblado;
    private boolean archivado;
    private String barrio;
    private String calle;
    private String ciudad;
    private String descripcion;
    private String estado;
    private boolean exterior;
    private int id_cuenta;
    private int nBanos;
    private int nHabit;
    private String mapa;
    private String nombre;
    private int numero;
    private int piso;
    private int plantas;
    private double precio;
    private String provincia;
    private int superficie;
    private String tipo_de_venta;
    private int veces_visto;
    private double latitud;
    private double longitud;
    private int posImagen;
    
    private boolean garaje, admite_mascotas, aire_acondicionado, ascensor, jardin, piscina, terraza, trastero, agua_caliente, calefaccion, seguridad;
    
    
    Inmueble inmuebleModificar = MisInmueblesController.inmuebleModificar;
    @FXML
    private Button cargarImagenes;
    @FXML
    private Button buttonEliminarInmueble;
    @FXML
    private ChoiceBox<String> chooserEstado;
    
    
 
    
    
    
    public void initModificarInmueble(Stage stage) {
            modificarInmuebleStage = stage;
            String localDir = System.getProperty("user.dir");
            String pathImagen = localDir + LoginController.cuentaLoged.getImagenPerfil();
            Image imgPerfil = new Image("file:" + pathImagen); 
        
            iconoUsuario.setImage(imgPerfil);
            tipoInmueble = inmuebleModificar.getTipoInm();
            estado = inmuebleModificar.getEstado();
            
            chooserEstado.getItems().addAll("No Disponible", "Disponible");
            chooserEstado.getSelectionModel().select(estado);
            chooserEstado.setValue(estado);
            if(tipoInmueble.equals("Vivienda")){
                try {
                    //Cargamos choiceBoxes
                    tipoVivienda=getTipoVivienda();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ModificarInmuebleController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
    
                chooserTipoVivienda.getItems().addAll("Adosado", "Ático","Casa","Chalet","Duplex","Piso");
                chooserTipoVivienda.getSelectionModel().select(tipoVivienda);
                chooserTipoVivienda.setValue(tipoVivienda);
            }
            mostrarSegunTipoInm(tipoInmueble);
        try {
            marcarCaracteristicas();
        } catch (SQLException ex) {
            Logger.getLogger(ModificarInmuebleController.class.getName()).log(Level.SEVERE, null, ex);
        }
            chooserTipoVenta.getItems().addAll("Alquilar", "Comprar");
            chooserTipoVenta.getSelectionModel().select(inmuebleModificar.getTipoVenta());
            chooserTipoVenta.setValue(inmuebleModificar.getTipoVenta());
            try {
                DAL DAL = new DAL();
                estancias = DAL.getListEstancias(inmuebleModificar.getId_inm());
                for(String e:estancias){
                    chooserEstancia.getItems().addAll(e);
                }
                chooserEstancia.getSelectionModel().selectFirst();
            } catch (SQLException ex) {
                Logger.getLogger(ModificarInmuebleController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        //Cargador de datos en textFields
        textBarrio.setText(inmuebleModificar.getBarrio());
        textCalle.setText(inmuebleModificar.getCalle());
        textCiudad.setText(inmuebleModificar.getCiudad());
        textDescripcion.setText(inmuebleModificar.getDescripcion());
        textNombre.setText(inmuebleModificar.getNombre());
        textNumBanos.setText("" + inmuebleModificar.getnBanos());
        textNumHabitaciones.setText("" + inmuebleModificar.getnHabit());
        textNumPlantas.setText("" + inmuebleModificar.getPlantas());
        textPrecio.setText("" + inmuebleModificar.getPrecio());
        textPlanta.setText("" +inmuebleModificar.getPiso());
        textProvincia.setText(inmuebleModificar.getProvincia());
        textSuperficie.setText("" + inmuebleModificar.getSuperficie());
        textNumero.setText("" + inmuebleModificar.getNumero());
        
    }
    

    @FXML
    private void clickedMiCuenta(ActionEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/GestionCuentaFXML.fxml"));
            Parent root = (Parent) myLoader.load();
            GestionCuentaController GestionCuentaController = myLoader.<GestionCuentaController>getController();

            Stage GestionCuentaControllerStage = new Stage();
            GestionCuentaController.initGestionCuenta(GestionCuentaControllerStage);
            //We create the scene for resultadosStage
            Scene scene = new Scene(root);
        
            //Añadir css
            //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
            //scene.getStylesheets().add(css);
        
            //we asign new scene to current stage/window
            GestionCuentaControllerStage.setScene(scene);
            GestionCuentaControllerStage.setTitle("Iniciar sesión");
            GestionCuentaControllerStage.initModality(Modality.APPLICATION_MODAL);
            GestionCuentaControllerStage.show();
            modificarInmuebleStage.close();
    }

    @FXML
    private void clickAnadirInmueble(ActionEvent event) throws SQLException, IOException {
        //Guardar inmueble modificado
        DAL DAL = new DAL();
        if(chooserEstado.getValue().equals("Disponible")){
            if(checkAllCorrect()){
                guardarDatos();
                comprobarDatos();
        
                System.out.println(tipoInmueble);
                DAL.updateInmueble(inmuebleModificar.getId_inm(), nombre, tipoInmueble, tipo_de_venta, precio, descripcion, superficie, estado, veces_visto, provincia, ciudad, barrio, calle, numero, plantas, exterior, piso, nBanos, nHabit, amueblado, archivado, mapa, latitud, longitud);
                DAL.updateCaract(inmuebleModificar.getId_inm(), tipoVivienda, garaje, admite_mascotas, aire_acondicionado, ascensor, jardin, piscina, terraza, trastero, agua_caliente, calefaccion, seguridad);
                mostrarMiCuenta();
            }
        } else {
            //El piso sigue en no disponible
            guardarDatos();
            comprobarDatos();
            DAL.updateInmueble(inmuebleModificar.getId_inm(), nombre, tipoInmueble, tipo_de_venta, precio, descripcion, superficie, estado, veces_visto, provincia, ciudad, barrio, calle, numero, plantas, exterior, piso, nBanos, nHabit, amueblado, archivado, mapa, latitud, longitud);
            DAL.updateCaract(inmuebleModificar.getId_inm(), tipoVivienda, garaje, admite_mascotas, aire_acondicionado, ascensor, jardin, piscina, terraza, trastero, agua_caliente, calefaccion, seguridad);    
            mostrarMiCuenta();
        }
        
    }

    @FXML
    private void onlyNumbers(KeyEvent event) {
        String input = event.getCharacter();
        String valido = "1234567890" ;
        if(!valido.contains(input)){
            event.consume();
        }
    }

    @FXML
    private void clickCrearEstancia(ActionEvent event) throws SQLException {

        String nombreEstancia = textEstancia.getText();
        //Capturar error nombre repetido
        if(!textEstancia.getText().equals("")){
            for(String e:estancias){
                if(nombreEstancia.equals(e)){
                    labelErrores.setText("Ya existe una estancia con este nombre");
                    return;
                }  
            }
        DAL DAL = new DAL();
        DAL.addEstancia(nombreEstancia, inmuebleModificar.getId_inm());
        chooserEstancia.getItems().addAll(nombreEstancia);
        chooserEstancia.getSelectionModel().select(nombreEstancia);
        estancias.add(nombreEstancia);
        } else {
            labelErrores.setText("No puedes dejar el nombre de la estancia vacío");
        }
    }

    @FXML
    private void clickAnadirImagen(ActionEvent event) throws SQLException {
        DAL DAL = new DAL();
        if(!textEnlaceImagen.getText().equals("")){
        int idEst = DAL.getIdEstancia(chooserEstancia.getValue(), inmuebleModificar.getId_inm());
        DAL.addImagen(idEst, textEnlaceImagen.getText());
        } else {
            labelErrores.setText("No has introducida ninguna imagen");
        }
    }

    @FXML
    private void clickSeleccionarImagen(ActionEvent event) {
        imagenSeleccionada = new Image(textEnlaceImagen.getText());
        imageViewSeleccionada.setImage(imagenSeleccionada);
    }

    @FXML
    private void clickAnteriorImagen(ActionEvent event) {
        if(posImagen>0){
            posImagen--;
            Image img = new Image(imagenesEstancia.get(posImagen));
            imageViewSeleccionada.setImage(img);
            
            if(posImagen==0) buttonAnteriorImagen.setDisable(true);
            buttonSiguienteImagen.setDisable(false);
        } 
    }

    @FXML
    private void clickSiguienteImagen(ActionEvent event) {
        if(posImagen<(imagenesEstancia.size()-1)){
            posImagen++;
            Image img = new Image(imagenesEstancia.get(posImagen));
            imageViewSeleccionada.setImage(img);
            
            if(posImagen==(imagenesEstancia.size()-1)) buttonSiguienteImagen.setDisable(true);
            buttonAnteriorImagen.setDisable(false);
        } 
    }

    @FXML
    private void clickEliminarEstancia(ActionEvent event) throws SQLException {
        DAL DAL = new DAL();
        int idEst = DAL.getIdEstancia(chooserEstancia.getValue(), inmuebleModificar.getId_inm());
        estancias.remove(chooserEstancia.getValue());
        chooserEstancia.getItems().remove(chooserEstancia.getValue());
        
        DAL.deleteEstanciaWithPhotos(idEst);
        
        /*for(String e:estancias){
                if(nombreEstancia.equals(e)){
                    labelErrores.setText("Ya existe una estancia con este nombre");
                    return;
                }  
            }*/
    }

    @FXML
    private void clickEliminarImagen(ActionEvent event) throws SQLException {
        DAL DAL = new DAL();
        DAL.deletePhoto(imagenesEstancia.get(posImagen));
    }
    
    private void mostrarSegunTipoInm(String tipoInmueble){
        if(tipoInmueble.equals("Vivienda")){
            labelNumBaños.setVisible(true);
            textNumBanos.setVisible(true);
            labelNumHabitaciones.setVisible(true);
            textNumHabitaciones.setVisible(true);
            labelNumPlantas.setVisible(true);
            textNumPlantas.setVisible(true);
            labelTipoVivienda.setVisible(true);
            chooserTipoVivienda.setVisible(true);
            tipoVivienda = chooserTipoVivienda.getValue();
            //Checkboxes
            checkAscensor.setVisible(true);
            checkCalefaccion.setVisible(true);
            checkGaraje.setVisible(true);
            checkAireAcond.setVisible(true);
            checkJardin.setVisible(true);
            checkTerraza.setVisible(true);
            checkPiscina.setVisible(true);
            checkAguaCaliente.setVisible(true);
            checkAmueblado.setVisible(true);
            checkAdmiteMascotas.setVisible(true);
            checkTrastero.setVisible(true);
            
            
        } else if("Habitacion".equals(tipoInmueble)){
            labelNumBaños.setVisible(false);
            textNumBanos.setVisible(false);
            labelNumHabitaciones.setVisible(false);
            textNumHabitaciones.setVisible(false);
            labelNumPlantas.setVisible(false);
            textNumPlantas.setVisible(false);
            labelTipoVivienda.setVisible(false);
            chooserTipoVivienda.setVisible(false);
            textNumBanos.setText("0");
            textNumHabitaciones.setText("0");
            tipoVivienda = "";
            textNumPlantas.setText("0");
            //Checkboxes
            checkAscensor.setVisible(true);
            checkCalefaccion.setVisible(true);
            checkGaraje.setVisible(false);
            checkGaraje.setSelected(false);
            checkAireAcond.setVisible(true);
            checkJardin.setVisible(false);
            checkJardin.setSelected(false);
            checkTerraza.setVisible(true);
            checkPiscina.setVisible(false);
            checkPiscina.setSelected(false);
            checkAguaCaliente.setVisible(true);
            checkAmueblado.setVisible(true);
            checkAdmiteMascotas.setVisible(true);
            checkTrastero.setVisible(false);
            checkTrastero.setSelected(false);
            
        } else if("Oficina".equals(tipoInmueble)) {
            labelNumBaños.setVisible(false);
            textNumBanos.setVisible(false);
            labelNumHabitaciones.setVisible(false);
            textNumHabitaciones.setVisible(false);
            labelNumPlantas.setVisible(false);
            textNumPlantas.setVisible(false);
            labelTipoVivienda.setVisible(false);
            chooserTipoVivienda.setVisible(false);
            textNumBanos.setText("0");
            textNumHabitaciones.setText("0");
            tipoVivienda = "";
            textNumPlantas.setText("0");
            //Checkboxes
            checkAscensor.setVisible(true);
            checkCalefaccion.setVisible(true);
            checkGaraje.setVisible(false);
            checkGaraje.setSelected(false);
            checkAireAcond.setVisible(true);
            checkJardin.setVisible(false);
            checkJardin.setSelected(false);
            checkTerraza.setVisible(false);
            checkTerraza.setSelected(false);
            checkPiscina.setVisible(false);
            checkPiscina.setSelected(false);
            checkAguaCaliente.setVisible(false);
            checkAguaCaliente.setSelected(false);
            checkAmueblado.setVisible(false);
            checkAmueblado.setSelected(false);
            checkAdmiteMascotas.setVisible(false);
            checkTrastero.setVisible(false);
            checkTrastero.setSelected(false);
            
        } else if("Garaje".equals(tipoInmueble)) {
            labelNumBaños.setVisible(false);
            textNumBanos.setVisible(false);
            labelNumHabitaciones.setVisible(false);
            textNumHabitaciones.setVisible(false);
            labelNumPlantas.setVisible(false);
            textNumPlantas.setVisible(false);
            labelTipoVivienda.setVisible(false);
            chooserTipoVivienda.setVisible(false);
            textNumBanos.setText("0");
            textNumHabitaciones.setText("0");
            tipoVivienda = "";
            textNumPlantas.setText("0");
            //Checkboxes
            checkAscensor.setVisible(false);
            checkAscensor.setSelected(false);
            checkCalefaccion.setVisible(false);
            checkCalefaccion.setSelected(false);
            checkGaraje.setVisible(false);
            checkGaraje.setSelected(false);
            checkAireAcond.setVisible(false);
            checkAireAcond.setSelected(false);
            checkJardin.setVisible(false);
            checkJardin.setSelected(false);
            checkTerraza.setVisible(false);
            checkTerraza.setSelected(false);
            checkPiscina.setVisible(false);
            checkPiscina.setSelected(false);
            checkAguaCaliente.setVisible(false);
            checkAguaCaliente.setSelected(false);
            checkAmueblado.setVisible(false);
            checkAmueblado.setSelected(false);
            checkAdmiteMascotas.setVisible(false);
            checkAdmiteMascotas.setSelected(false);
            checkTrastero.setVisible(false);
            checkTrastero.setSelected(false);
        }
        
        
    }
    
    private String getTipoVivienda() throws SQLException{
        DAL DAL = new DAL();
        String tipoViv = DAL.getTipoViviendaByIdInm(inmuebleModificar.getId_inm());
        return tipoViv;
    }
    
    
    private void comprobarDatos(){
        if(textBarrio.getText().equals("")) barrio = inmuebleModificar.getBarrio(); else {barrio = textBarrio.getText();}
        if(textCalle.getText().equals("")) calle = inmuebleModificar.getCalle(); else {calle = textCalle.getText();}
        if(textCiudad.getText().equals("")) ciudad = inmuebleModificar.getCiudad(); else {ciudad = textCiudad.getText();}
        if(textDescripcion.getText().equals("")) descripcion = inmuebleModificar.getDescripcion(); else {descripcion = textDescripcion.getText();}
        if(textNombre.getText().equals("")) nombre = inmuebleModificar.getNombre(); else {nombre = textNombre.getText();}
        if(textNumBanos.getText().equals("")) nBanos = inmuebleModificar.getnBanos(); else {nBanos = Integer.parseInt(textNumBanos.getText());}
        if(textNumHabitaciones.getText().equals("")) nHabit = inmuebleModificar.getnHabit(); else {nHabit = Integer.parseInt(textNumHabitaciones.getText());}
        if(textNumPlantas.getText().equals("")) plantas = inmuebleModificar.getPlantas(); else {plantas = Integer.parseInt(textNumPlantas.getText());}
        if(textNumero.getText().equals("")) numero = inmuebleModificar.getNumero(); else {numero = Integer.parseInt(textNumero.getText());}
        if(textPrecio.getText().equals("")) precio = inmuebleModificar.getPrecio(); else {precio = Double.parseDouble(textPrecio.getText());}
        if(textSuperficie.getText().equals("")) superficie = inmuebleModificar.getSuperficie(); else {superficie = Integer.parseInt(textSuperficie.getText());}
        if(textProvincia.getText().equals("")) provincia = inmuebleModificar.getProvincia(); else {provincia = textProvincia.getText();}
        if(tipoInmueble.equals("Vivienda")){
            if(!chooserTipoVivienda.getValue().equals(tipoVivienda)) tipoVivienda = chooserTipoVivienda.getValue();
        }
        if(!chooserTipoVenta.getValue().equals(inmuebleModificar.getTipoVenta())) tipo_de_venta = chooserTipoVenta.getValue();
        
        estado = chooserEstado.getValue();
        veces_visto = inmuebleModificar.getVeces_visto();
        exterior = inmuebleModificar.getExterior();
        mapa = inmuebleModificar.getMapa();
        latitud = 0;
        longitud = 0;
        archivado = inmuebleModificar.getArchivado();
        
    }

    @FXML
    private void clickCargarImagenes(ActionEvent event) throws SQLException {
        DAL DAL = new DAL();
        imagenesEstancia = DAL.getPhotos(inmuebleModificar.getId_inm(), chooserEstancia.getValue());
        Image img = new Image(imagenesEstancia.get(0));
        imageViewSeleccionada.setImage(img);
        buttonAnteriorImagen.setDisable(true);
        posImagen=0;
    }
    
    private void marcarCaracteristicas() throws SQLException{
        List<Boolean> caracteristicas = new ArrayList<Boolean>();
        DAL DAL = new DAL();
        System.out.println("Antes");
        caracteristicas=DAL.getListCaracteristicas(inmuebleModificar.getId_inm());
        System.out.println("Después " + caracteristicas.toString());
        if(caracteristicas.get(0)) checkGaraje.setSelected(true);
        if(caracteristicas.get(1)) checkAdmiteMascotas.setSelected(true);
        if(caracteristicas.get(2)) checkAireAcond.setSelected(true);
        if(caracteristicas.get(3)) checkAscensor.setSelected(true);
        if(caracteristicas.get(4)) checkJardin.setSelected(true);
        if(caracteristicas.get(5)) checkPiscina.setSelected(true);
        if(caracteristicas.get(6)) checkTerraza.setSelected(true);
        if(caracteristicas.get(7)) checkTrastero.setSelected(true);
        if(caracteristicas.get(8)) checkAguaCaliente.setSelected(true);
        if(caracteristicas.get(9)) checkCalefaccion.setSelected(true);
        if(caracteristicas.get(10)) checkSeguridad.setSelected(true);
        if(inmuebleModificar.getAmueblado()) checkAmueblado.setSelected(true);
        
        
    }
    
    private void guardarDatos(){
        garaje = checkGaraje.isSelected(); 
        admite_mascotas = checkAdmiteMascotas.isSelected();
        aire_acondicionado = checkAireAcond.isSelected();
        ascensor = checkAscensor.isSelected();
        jardin = checkJardin.isSelected();
        piscina = checkPiscina.isSelected();
        terraza = checkTerraza.isSelected();
        trastero = checkTrastero.isSelected();
        agua_caliente = checkAguaCaliente.isSelected();
        calefaccion = checkCalefaccion.isSelected();
        seguridad = checkSeguridad.isSelected();
        //Campos de texto
        nombre = textNombre.getText();
        tipo_de_venta = chooserTipoVenta.getValue();
        
        if(!textPrecio.getText().equals("")) precio = Double.parseDouble(textPrecio.getText());
        descripcion = textDescripcion.getText();
        if(!textSuperficie.getText().equals("")) superficie = Integer.parseInt(textSuperficie.getText());
        provincia = textProvincia.getText();
        ciudad = textCiudad.getText();
        barrio = textBarrio.getText();
        calle = textCalle.getText();
        if(!textNumero.getText().equals("")) numero = Integer.parseInt(textNumero.getText());
        if(!textNumPlantas.getText().equals("")) plantas = Integer.parseInt(textNumPlantas.getText());
        if(!textPlanta.getText().equals("")) piso = Integer.parseInt(textPlanta.getText());
        if(!textNumBanos.getText().equals("")) nBanos = Integer.parseInt(textNumBanos.getText());
        if(!textNumHabitaciones.getText().equals("")) nHabit = Integer.parseInt(textNumHabitaciones.getText());
        amueblado = checkAmueblado.isSelected();
        tipoInmueble = inmuebleModificar.getTipoInm();
        if(tipoInmueble.equals("Vivienda")){
            tipoVivienda = chooserTipoVivienda.getValue();
        } else {
            tipoVivienda = "";
        }
        
        
    }
    
    private void mostrarMiCuenta() throws IOException{
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/GestionCuentaFXML.fxml"));
            Parent root = (Parent) myLoader.load();
            GestionCuentaController GestionCuentaController = myLoader.<GestionCuentaController>getController();

            Stage GestionCuentaControllerStage = new Stage();
            GestionCuentaController.initGestionCuenta(GestionCuentaControllerStage);
            //We create the scene for resultadosStage
            Scene scene = new Scene(root);
        
            //Añadir css
            //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
            //scene.getStylesheets().add(css);
        
            //we asign new scene to current stage/window
            GestionCuentaControllerStage.setScene(scene);
            GestionCuentaControllerStage.setTitle("Iniciar sesión");
            GestionCuentaControllerStage.initModality(Modality.APPLICATION_MODAL);
            GestionCuentaControllerStage.show();
            modificarInmuebleStage.close();
    }

    @FXML
    private void clickEliminarInmueble(ActionEvent event) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmacion");
            alert.setContentText("Está seguro de querer eliminar este inmueble?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                DAL DAL = new DAL();
                DAL.deleteInmueble(inmuebleModificar.getId_inm());
                NotificacionesController.notificacionInmuebleFavEliminado(inmuebleModificar.getId_inm());
                Alert a = new Alert(Alert.AlertType.INFORMATION,"El inmueble ha sido eliminado correctamente.",ButtonType.OK);
                a.setTitle("Inmueble eliminado");
                a.showAndWait();
                mostrarMiCuenta();
            
            } else {
                // Selecciona Cancelar, por tanto no hace nada
                return;

            
            }
    }
    
    private void prepararNoDisponible(){
        if(textPrecio.getText().equals("")) precio = 0; else precio = Double.parseDouble(textPrecio.getText());
        if(textSuperficie.getText().equals("")) superficie = 0; else superficie = Integer.parseInt(textSuperficie.getText());
        if(textNumero.getText().equals("")) numero = 0; else numero = Integer.parseInt(textNumero.getText());
        if(textNumBanos.getText().equals("")) nBanos = 0; else nBanos = Integer.parseInt(textNumBanos.getText());
        if(textNumPlantas.getText().equals("")) plantas = 0; else plantas = Integer.parseInt(textNumPlantas.getText());
        if(textNumHabitaciones.getText().equals("")) nHabit = 0; else nHabit = Integer.parseInt(textNumHabitaciones.getText());
        if(textPlanta.getText().equals("")) piso = 0; else piso = Integer.parseInt(textPlanta.getText());
    }
    
    private boolean checkAllCorrect()//Devuelve true si esta todo correcto
    {
        boolean res = true;
        
        if(Double.parseDouble(textPrecio.getText()) <= 0) {res = false; labelErrores.setText("El precio debe tener un valor válido");}
        else if(Integer.parseInt(textSuperficie.getText()) <= 0) {res = false; labelErrores.setText("La superficie debe tener un valor válido");}
        else if(tipoInmueble.equals("Vivienda") && Integer.parseInt(textNumPlantas.getText()) < 0) {res = false; labelErrores.setText("El número de plantas no debe ser negativo");}
        else if(tipoInmueble.equals("Vivienda") && Integer.parseInt(textNumHabitaciones.getText()) < 0) {res = false; labelErrores.setText("El número de habitaciones no debe ser negativo");}
        else if(tipoInmueble.equals("Vivienda") && Integer.parseInt(textNumBanos.getText()) < 0) {res = false; labelErrores.setText("El número de baños no debe ser negativo");}
        else if(textDescripcion.getText().equals("")){res = false; labelErrores.setText("La descripción debe tener algún valor");}
        else if(textCiudad.getText().equals("")){res = false; labelErrores.setText("La ciudad debe tener algún valor");}
        else if(textProvincia.getText().equals("")){res = false; labelErrores.setText("La provincia debe tener algún valor");}
        else if(textBarrio.getText().equals("")){res = false; labelErrores.setText("El barrio debe tener algún valor");}
        else if(textCalle.getText().equals("")){res = false; labelErrores.setText("La calle debe tener algún valor");}
        else if(textNumero.getText().equals("")){res = false; labelErrores.setText("El número debe tener algún valor");}
        else if(textPlanta.getText().equals("")){res = false; labelErrores.setText("La planta debe tener algún valor");}
        else if("Vivienda".equals(tipoInmueble) && "".equals(textNumBanos.getText())){res = false; labelErrores.setText("La vivienda debe tener algun baño");}
        else if("Vivienda".equals(tipoInmueble) && "".equals(textNumHabitaciones.getText())){res = false; labelErrores.setText("La vivienda debe tener alguna habitación");}
        else if("Vivienda".equals(tipoInmueble) && "".equals(textNumPlantas.getText())){res = false; labelErrores.setText("Debe indicar el número de plantas de la vivienda");}
        
        else if(estancias.isEmpty()) {res = false; labelErrores.setText("No puedes crear un inmueble sin estancias");}
        else if(hayAlgunaImagen()) {res = false; labelErrores.setText("El inmueble no tiene ninguna imagen");}
        
        return res;
    }
    
    private boolean hayAlgunaImagen(){
        boolean hayFotos = false;
        DAL dal = new DAL();
        try {
            String foto = dal.getPreviewPhoto(inmuebleModificar.getId_inm());
            System.out.println(foto);
            if(foto.equals("https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/300px-No_image_available.svg.png")){
                hayFotos = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModificarInmuebleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hayFotos;
    }
}
