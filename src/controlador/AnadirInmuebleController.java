/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import basededatos.DAL;
import static controlador.InicioController.inicioStage;
import static controlador.ModificarInmuebleController.modificarInmuebleStage;
import static controlador.ResultadosController.resultadosStage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
 * FXML Controller class
 *
 * @author David
 */
public class AnadirInmuebleController implements Initializable {

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
    private ChoiceBox<String> chooserTipoInmueble;
    @FXML
    private ChoiceBox<String> chooserTipoVenta;
    @FXML
    private ChoiceBox<String> chooserEstado;
    @FXML
    private Label textError;
    @FXML
    private Button buttonCrearEstancia;
    @FXML
    private TextField textEstancia;
    @FXML
    private ChoiceBox<String> chooserEstancia;
    @FXML
    private Button buttonAnadirImagen;
    @FXML
    private Button buttonSeleccionarImagen;
    
    public static Stage anadirInmuebleStage;
    @FXML
    private Label labelErrores;
    @FXML
    private CheckBox checkAmueblado;
    @FXML
    private Text labelNumPlantas;
    @FXML
    private Text labelNumHabitaciones;
    @FXML
    private Text labelNumBaños;
    @FXML
    private Text labelTipoVenta;
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
    private ChoiceBox<String> chooserTipoVivienda;
    @FXML
    private Text labelTipoVivienda;
    
    private String tipoInmueble = EscogerInmuebleAnadirController.tipoInmueble;
    private String tipoVivienda;
    @FXML
    private TextField textEnlaceImagen;
    private Image imagenSeleccionada;
    @FXML
    private ImageView imageViewSeleccionada;
    
    List<Estancia> estancias = new ArrayList<Estancia>();
    @FXML
    private CheckBox checkTrastero;
   
    public static double porcentajeBajo = 0.0;
    public static double porcentajeAlto = 0.0;
    
    private String estado;
    private double precio;
    private int superficie;
    private int numero;
    private int planta;
    private int numbanos;
    private int numhabs;
    private int numplantas;
  
    public void initAnadirInmueble(Stage stage){
            anadirInmuebleStage = stage;     
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        String localDir = System.getProperty("user.dir");
        String pathImagen = localDir + LoginController.cuentaLoged.getImagenPerfil();
        Image imgPerfil = new Image("file:" + pathImagen); 
        
        iconoUsuario.setImage(imgPerfil);
        
        System.out.println(EscogerInmuebleAnadirController.tipoInmueble);
        chooserEstado.getItems().addAll("No Disponible", "Disponible");
        chooserEstado.getSelectionModel().select("No Disponible");
        chooserEstado.setValue("No Disponible");
                
        chooserTipoVivienda.getItems().addAll("Adosado", "Ático","Casa","Chalet","Duplex","Piso");
        chooserTipoVivienda.getSelectionModel().select("Piso");
        chooserTipoVivienda.setValue("Piso");
        System.out.println(EscogerInmuebleAnadirController.tipoInmueble);
        chooserTipoVenta.getItems().addAll("Comprar", "Alquilar");
        chooserTipoVenta.getSelectionModel().select("Comprar");
        chooserTipoVenta.setValue("Comprar");
        mostrarSegunTipoInm(tipoInmueble);
    }    
    
    

    @FXML
    private void clickedMiCuenta(ActionEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/GestionCuentaFXML.fxml"));
            Parent root = (Parent) myLoader.load();
            GestionCuentaController GestionCuentaController = myLoader.<GestionCuentaController>getController();

            Stage GestionCuentaControllerStage = new Stage();
            GestionCuentaControllerStage.getIcons().add(new Image("/recursos/logoFinal.png"));
            GestionCuentaController.initGestionCuenta(GestionCuentaControllerStage);
            //We create the scene for resultadosStage
            Scene scene = new Scene(root);
        
            //Añadir css
            //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
            //scene.getStylesheets().add(css);
        
            //we asign new scene to current stage/window
            GestionCuentaControllerStage.setScene(scene);
            GestionCuentaControllerStage.setTitle("Mi Cuenta");
            GestionCuentaControllerStage.initModality(Modality.APPLICATION_MODAL);
            GestionCuentaControllerStage.show();
            anadirInmuebleStage.close();
    }

    @FXML
    private void clickAnadirInmueble(ActionEvent event) throws SQLException, IOException {
        DAL DAL = new DAL();
        String tipoVivienda ="";
        if(tipoInmueble == "Vivienda") {tipoVivienda = chooserTipoVivienda.getValue();}

        //COMPROBAR SI ESTÁ O NO DISPONIBLE
        if(chooserEstado.getValue().equals("Disponible")){
            //ESTA DISPONIBLE
            if(estancias == null){
                labelErrores.setText("No puedes crear un inmueble sin estancias");
            } else if (checkAllCorrect()){
                //Creamos un inmueble válido
                int id_cuenta = LoginController.cuentaLoged.getId_cuenta();
                DAL.addInmueble(checkAmueblado.isSelected(), false, textBarrio.getText(), textCalle.getText(), textCiudad.getText(), textDescripcion.getText(), "Disponible", true, id_cuenta, Integer.parseInt(textNumBanos.getText()), Integer.parseInt(textNumHabitaciones.getText()), "Mapa", textNombre.getText(), Integer.parseInt(textNumero.getText()), Integer.parseInt(textPlanta.getText()), Integer.parseInt(textNumPlantas.getText()), Double.parseDouble(textPrecio.getText()), textProvincia.getText(), Integer.parseInt(textSuperficie.getText()), tipoInmueble, chooserTipoVenta.getValue(), 0, 0, 0);
                int idInmueble = DAL.getIdInmuebleWithIdcuenta(textNombre.getText(), id_cuenta);
                DAL.addCaracteristicas(idInmueble, tipoVivienda, checkGaraje.isSelected(), checkAdmiteMascotas.isSelected(), checkAireAcond.isSelected(), checkAscensor.isSelected(), checkJardin.isSelected(), checkPiscina.isSelected(), checkTerraza.isSelected(), checkTrastero.isSelected(), checkAguaCaliente.isSelected(), checkCalefaccion.isSelected(), checkSeguridad.isSelected());
                //Añadimos las estancias del inmueble
                for(Estancia e:estancias){
                
                    DAL.addEstancia(e.getNombre(),idInmueble);
                    int idEstancia = DAL.getIdEstancia(e.getNombre(), idInmueble);
                    for(String imagen:e.getImagenes()){
                    
                        DAL.addImagen(idEstancia, imagen);
                
                    }
                }
                Alert a = new Alert(Alert.AlertType.INFORMATION,"El inmueble ha sido añadido correctamente.",ButtonType.OK);
                    a.setTitle("Inmueble añadido");
                    a.showAndWait();
                    mostrarMiCuenta();
            }
        } else {
            //NO ESTA DISPONIBLE
            prepararNoDisponible();
            DAL.addInmueble(checkAmueblado.isSelected(), false, textBarrio.getText(), textCalle.getText(), textCiudad.getText(), textDescripcion.getText(), "No Disponible", true, LoginController.cuentaLoged.getId_cuenta(), numbanos, numhabs, "Mapa", textNombre.getText(), numero, planta, numplantas, precio, textProvincia.getText(), superficie, tipoInmueble, chooserTipoVenta.getValue(), 0, 0, 0);
                int idInmueble = DAL.getIdInmuebleWithIdcuenta(textNombre.getText(), LoginController.cuentaLoged.getId_cuenta());
                DAL.addCaracteristicas(idInmueble, tipoVivienda, checkGaraje.isSelected(), checkAdmiteMascotas.isSelected(), checkAireAcond.isSelected(), checkAscensor.isSelected(), checkJardin.isSelected(), checkPiscina.isSelected(), checkTerraza.isSelected(), checkTrastero.isSelected(), checkAguaCaliente.isSelected(), checkCalefaccion.isSelected(), checkSeguridad.isSelected());
                //Añadimos las estancias del inmueble
                for(Estancia e:estancias){
                
                    DAL.addEstancia(e.getNombre(),idInmueble);
                    int idEstancia = DAL.getIdEstancia(e.getNombre(), idInmueble);
                    for(String imagen:e.getImagenes()){
                    
                        DAL.addImagen(idEstancia, imagen);
                
                    }
                }
                Alert a = new Alert(Alert.AlertType.INFORMATION,"El inmueble ha sido añadido correctamente.",ButtonType.OK);
                    a.setTitle("Inmueble añadido");
                    a.showAndWait();
                    mostrarMiCuenta();
       
        }
    }

    @FXML
    private void clickCrearEstancia(ActionEvent event) {
        if(textEstancia.getText().equals("")){
            labelErrores.setText("No puedes crear una estancia sin nombre");
        } else {
        boolean res = true;
        for(Estancia e:estancias){
            System.out.println(e.getNombre());
            if(e.getNombre().equals(textEstancia.getText())){
                labelErrores.setText("Esta estancia ya existe");
                res = false;
            }
        }
        System.out.println(res);
        if(res) CrearEstancia(textEstancia.getText());
        }
        
    }

    @FXML
    private void clickAnadirImagen(ActionEvent event) {
        for(Estancia e:estancias){
            if(e.getNombre().equals(chooserEstancia.getValue())){
                e.addImagen(textEnlaceImagen.getText());
            }
        }
    }

    @FXML
    private void clickSeleccionarImagen(ActionEvent event) {
        imagenSeleccionada = new Image(textEnlaceImagen.getText());
        imageViewSeleccionada.setImage(imagenSeleccionada);
    }
    
    private boolean checkAllCorrect()//Devuelve true si esta todo correcto
    {
        boolean res = true;
        
        if(Integer.parseInt(textPrecio.getText()) <= 0) {res = false; labelErrores.setText("El precio debe tener un valor válido");}
        else if(Integer.parseInt(textSuperficie.getText()) <= 0) {res = false; labelErrores.setText("La superficie debe tener un valor válido");}
        else if(tipoInmueble.equals("Vivienda") && Integer.parseInt(textNumPlantas.getText()) < 0) {res = false; labelErrores.setText("El número de plantas no debe ser negativo");}
        else if(tipoInmueble.equals("Vivienda") && Integer.parseInt(textNumHabitaciones.getText()) < 0) {res = false; labelErrores.setText("El número de habitaciones no debe ser negativo");}
        else if(tipoInmueble.equals("Vivienda") && Integer.parseInt(textNumBanos.getText()) < 0) {res = false; labelErrores.setText("El número de baños no debe ser negativo");}
        else if(textDescripcion.getText()==""){res = false; labelErrores.setText("La descripción debe tener algún valor");}
        else if(textCiudad.getText()==""){res = false; labelErrores.setText("La ciudad debe tener algún valor");}
        else if(textProvincia.getText()==""){res = false; labelErrores.setText("La provincia debe tener algún valor");}
        else if(textBarrio.getText()==""){res = false; labelErrores.setText("El barrio debe tener algún valor");}
        else if(textCalle.getText()==""){res = false; labelErrores.setText("La calle debe tener algún valor");}
        else if(textNumero.getText()==""){res = false; labelErrores.setText("El número debe tener algún valor");}
        else if(textPlanta.getText()==""){res = false; labelErrores.setText("La planta debe tener algún valor");}
        else if(tipoInmueble == "Vivienda" && textNumBanos.getText()==""){res = false; labelErrores.setText("La vivienda debe tener algun baño");}
        else if(tipoInmueble == "Vivienda" && textNumHabitaciones.getText()==""){res = false; labelErrores.setText("La vivienda debe tener alguna habitación");}
        else if(tipoInmueble == "Vivienda" && textNumPlantas.getText()==""){res = false; labelErrores.setText("Debe indicar el número de plantas de la vivienda");}
        
        else if(estancias.isEmpty()) {res = false; labelErrores.setText("No puedes crear un inmueble sin estancias");}
        else if(hayAlgunaImagen()) {res = false; labelErrores.setText("El inmueble no tiene ninguna imagen");}
        
        return res;
    }
    
    private void mostrarSegunTipoInm(String tipoInmueble){
        if(tipoInmueble == "Vivienda"){
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
            
            
        } else if(tipoInmueble == "Habitacion"){
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
            
        } else if(tipoInmueble == "Oficina") {
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
            
        } else if(tipoInmueble == "Garaje") {
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
    private void CrearEstancia(String nombreEstancia){
        
        List<String> imagenes = new ArrayList<String>();
        Estancia estancia = new Estancia(nombreEstancia, imagenes);
        chooserEstancia.getItems().addAll(estancia.getNombre());
        chooserEstancia.getSelectionModel().select(estancia.getNombre());
        chooserEstancia.setValue(estancia.getNombre());
        estancias.add(estancia);
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
    private void sugerirPrecio(ActionEvent event) throws IOException {
        
        DAL dal = new DAL();
        String tipoInmueble = EscogerInmuebleAnadirController.tipoInmueble;
        String ciudad = textCiudad.getText().trim();
        String tipoVenta = chooserTipoVenta.getValue();
        String tipoVivienda = chooserTipoVivienda.getValue();
        String barrio = textBarrio.getText().trim();
        Boolean amueblado = checkAmueblado.isSelected();
        String superficie = textSuperficie.getText();
        String numBanos = textNumBanos.getText();
        String numHabi = textNumHabitaciones.getText();
        
        double sumaInmuebles = 0.0;
        double media = 0.0;
        
        List<Integer> inmueblesComparar = new ArrayList<Integer>();
        
        //comparar los minimos
        //requisitos minnimos : superficie, ciudad, barrio, tipoInmueble, tipoVenta, tipoVivienda
        //requisito ampliable : num baños, num habi
      
        try{
           System.out.println("----" + superficie.length() + ciudad.length() + barrio.length());
            if(superficie.length()>0 && ciudad.length()>0 && barrio.length()>0){
                labelErrores.setText("");
                int superficieNum = Integer.parseInt(textSuperficie.getText());
                int superficieAlta = superficieNum + 10;
                int superficieBaja = superficieNum - 10;
                
                inmueblesComparar = dal.comparadorPrecio(superficieAlta, superficieBaja, ciudad, barrio, tipoInmueble, tipoVenta, numBanos, numHabi, tipoVivienda);
                
                if(inmueblesComparar.size() > 0){
                    
                    for(int i = 0; i < inmueblesComparar.size(); i++){
                        Inmueble inm = dal.getInmuebleByID(inmueblesComparar.get(i));
                        sumaInmuebles += inm.getPrecio();                      
                    }
                    
                    System.out.println(sumaInmuebles);
                    media = sumaInmuebles / inmueblesComparar.size();
                    System.out.println("----------" + media + "   "+inmueblesComparar.size());
                    porcentajeBajo = media - media * 0.10;
                    porcentajeAlto = media + media * 0.10;
                    
                    FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/SugerirPrecioFXML.fxml"));
                    Parent root = (Parent) myLoader.load();
                    SugerirPrecioController SugerirPrecioController = myLoader.<SugerirPrecioController>getController();
                    Stage sugerirPrecioControllerStage = new Stage();
                    sugerirPrecioControllerStage.getIcons().add(new Image("/recursos/logoFinal.png"));
                    SugerirPrecioController.initSugerirPrecioController(sugerirPrecioControllerStage);
                    Scene scene = new Scene(root);
                    sugerirPrecioControllerStage.setScene(scene);
                    sugerirPrecioControllerStage.setTitle("Sugerencia de precio");
                    sugerirPrecioControllerStage.initModality(Modality.APPLICATION_MODAL);
                    sugerirPrecioControllerStage.show();
                    
                }else{
                    labelErrores.setText("No hay suficientes inmuebles en la base de datos para estimar el precio de su vivienda");
                }
                
            }else{
                labelErrores.setText("Faltan campos por rellenar: Ciudad, Superficie y Barrio son obligatorios");
            }
        } catch(SQLException e){
            System.out.println("Error al añadir los parámetros de la consulta: " + e);
        } catch (IOException e1){
            System.out.println("Excepcion abriendo fxml: " + e1);
        }
    }
    
    private void mostrarMiCuenta() throws IOException{
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/GestionCuentaFXML.fxml"));
            Parent root = (Parent) myLoader.load();
            GestionCuentaController GestionCuentaController = myLoader.<GestionCuentaController>getController();

            Stage GestionCuentaControllerStage = new Stage();
            GestionCuentaControllerStage.getIcons().add(new Image("/recursos/logoFinal.png"));
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
            anadirInmuebleStage.close();
    }
    
    private void prepararNoDisponible(){
        if(textPrecio.getText().equals("")) precio = 0; else precio = Double.parseDouble(textPrecio.getText());
        if(textSuperficie.getText().equals("")) superficie = 0; else superficie = Integer.parseInt(textSuperficie.getText());
        if(textNumero.getText().equals("")) numero = 0; else numero = Integer.parseInt(textNumero.getText());
        if(textNumBanos.getText().equals("")) numbanos = 0; else numbanos = Integer.parseInt(textNumBanos.getText());
        if(textNumPlantas.getText().equals("")) numplantas = 0; else numplantas = Integer.parseInt(textNumPlantas.getText());
        if(textNumHabitaciones.getText().equals("")) numhabs = 0; else numhabs = Integer.parseInt(textNumHabitaciones.getText());
        if(textPlanta.getText().equals("")) planta = 0; else planta = Integer.parseInt(textPlanta.getText());
    }
    
    private boolean hayAlgunaImagen(){
        boolean hayImagen = false;
        for(Estancia e:estancias){
            if(e.getImagenes().isEmpty()) hayImagen = true;
        }
        return hayImagen;
    }
}
