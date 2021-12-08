/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import basededatos.DAL;
import static controlador.GestionCuentaController.gestionCuentaStage;
import static controlador.ResultadosController.inmuebleSeleccionado;
import static controlador.ResultadosController.resultadosStage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David
 */
public class MisInmueblesController implements Initializable {

    @FXML
    private ImageView iconoUsuario;
    @FXML
    private ScrollPane scrollPaneResultados;
    @FXML
    private AnchorPane panelInmueble0;
    @FXML
    private ImageView imageView0;
    @FXML
    private Label textDescripcion0;
    @FXML
    private Label textPrecio0;
    @FXML
    private Label textNumHabs0;
    @FXML
    private Label textNumBanyos0;
    @FXML
    private Label textSuperficie0;
    @FXML
    private Label textTipoInmueble0;
    @FXML
    private Label textBarrio0;

    @FXML
    private Label textTitulo0;
    @FXML
    private AnchorPane panelInmueble1;
    @FXML
    private ImageView imageView1;
    @FXML
    private Label textDescripcion1;
    @FXML
    private Label textPrecio1;
    @FXML
    private Label textNumHabs1;
    @FXML
    private Label textNumBanyos1;
    @FXML
    private Label textSuperficie1;
    @FXML
    private Label textTipoInmueble1;
    @FXML
    private Label textBarrio1;
    @FXML
    private Label textTitulo1;
    @FXML
    private AnchorPane panelInmueble2;
    @FXML
    private ImageView imageView2;
    @FXML
    private Label textDescripcion2;
    @FXML
    private Label textPrecio2;
    @FXML
    private Label textNumHabs2;
    @FXML
    private Label textNumBanyos2;
    @FXML
    private Label textSuperficie2;
    @FXML
    private Label textTipoInmueble2;
    @FXML
    private Label textBarrio2;
    @FXML

    private Label textTitulo2;
    @FXML
    private AnchorPane panelInmueble3;
    @FXML
    private ImageView imageView3;
    @FXML
    private Label textDescripcion3;
    @FXML
    private Label textPrecio3;
    @FXML
    private Label textNumHabs3;
    @FXML
    private Label textNumBanyos3;
    @FXML
    private Label textSuperficie3;
    @FXML
    private Label textTipoInmueble3;
    @FXML
    private Label textBarrio3;
    @FXML

    private Label textTitulo3;
    @FXML
    private AnchorPane panelInmueble4;
    @FXML
    private ImageView imageView4;
    @FXML
    private Label textDescripcion4;
    @FXML
    private Label textPrecio4;
    @FXML
    private Label textNumHabs4;
    @FXML
    private Label textNumBanyos4;
    @FXML
    private Label textSuperficie4;
    @FXML
    private Label textTipoInmueble4;
    @FXML
    private Label textBarrio4;
    @FXML

    private Label textTitulo4;
    @FXML
    private Button botonAnterior;
    @FXML
    private Button botonSiguiente;
    @FXML
    private Text textoNumPagina;
    @FXML
    private Button botonMiCuenta;
    @FXML
    private Button buttonFav0;
    @FXML
    private Button buttonFav1;
    @FXML
    private Button buttonFav2;
    @FXML
    private Button buttonFav3;
    @FXML
    private Button buttonFav4;
    @FXML
    private Button buttonAnadirInmueble;
    
    public static Inmueble inmuebleModificar;
    private Inmueble inmueble0;
    private Inmueble inmueble1;
    private Inmueble inmueble2;
    private Inmueble inmueble3;
    private Inmueble inmueble4;
    int paginaActual = 0;
    
    List<Integer> listaInmuebles;
    
    public static Stage misInmueblesStage;

    public static void initMisInmuebles(Stage stage)
    {
        misInmueblesStage = stage;
    
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*buttonFav0.getStyleClass().add("buttonFav");
        buttonFav1.getStyleClass().add("buttonFav");
        buttonFav2.getStyleClass().add("buttonFav");
        buttonFav3.getStyleClass().add("buttonFav");
        buttonFav4.getStyleClass().add("buttonFav");*/
        CargarResultados(paginaActual);
        

    }    



    @FXML
    private void ClickInmueble0(MouseEvent event) throws IOException {
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleModificar = DAL.getInmuebleByID(listaInmuebles.get(0 + paginaActual*5));
            mostrarModificarInmueble();
        } catch(SQLException e){}
    }


    @FXML
    private void ClickInmueble1(MouseEvent event) throws IOException {
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleModificar = DAL.getInmuebleByID(listaInmuebles.get(1 + paginaActual*5));
            mostrarModificarInmueble();
        } catch(SQLException e){}
    }


    @FXML
    private void ClickInmueble2(MouseEvent event) throws IOException {
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleModificar = DAL.getInmuebleByID(listaInmuebles.get(2 + paginaActual*5));
            mostrarModificarInmueble();
        } catch(SQLException e){}
    }


    @FXML
    private void ClickInmueble3(MouseEvent event) throws IOException {
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleModificar = DAL.getInmuebleByID(listaInmuebles.get(3 + paginaActual*5));
            mostrarModificarInmueble();
        } catch(SQLException e){}
    }


    @FXML
    private void ClickInmueble4(MouseEvent event) throws IOException {
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleModificar = DAL.getInmuebleByID(listaInmuebles.get(4 + paginaActual*5));
            mostrarModificarInmueble();
        } catch(SQLException e){}
    }

    @FXML
    private void clickAnterior(ActionEvent event) {
        paginaActual--;
        CargarResultados(paginaActual);
    }

    @FXML
    private void clickSiguiente(ActionEvent event) {
        paginaActual++;
        CargarResultados(paginaActual);
    }


    @FXML
    private void clickAnadirInmueble(ActionEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/EscogerInmuebleAnadirFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        EscogerInmuebleAnadirController EscogerInmuebleAnadirController = myLoader.<EscogerInmuebleAnadirController>getController();

        Stage escogerInmuebleAnadirStage = new Stage();
        escogerInmuebleAnadirStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        EscogerInmuebleAnadirController.initEscogerInmuebleAnadir(escogerInmuebleAnadirStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //Añadir css
        //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        //we asign new scene to current stage/window
        escogerInmuebleAnadirStage.setScene(scene);
        escogerInmuebleAnadirStage.setTitle("Escoger Tipo de Inmueble");
        escogerInmuebleAnadirStage.initModality(Modality.APPLICATION_MODAL);
        escogerInmuebleAnadirStage.show();
        //gestionCuentaStage.hide();
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
            misInmueblesStage.close();
    }
    
    private void CargarResultados(int paginaActual){
        
       
        try{
            DAL DAL = new DAL();
            LoginController.actualizarCuenta();
            listaInmuebles = DAL.getIdInmuebleByIdcuenta(LoginController.cuentaLoged.getId_cuenta());
            int totalPaginas = (listaInmuebles.size() / 5);
            int pisosUltimaPagina = listaInmuebles.size()%5;
            
            //La página tiene 5 resultados
            if(paginaActual < totalPaginas ){
                panelInmueble0.visibleProperty().set(true);
                panelInmueble1.visibleProperty().set(true);
                panelInmueble2.visibleProperty().set(true);
                panelInmueble3.visibleProperty().set(true);
                panelInmueble4.visibleProperty().set(true);

                //Cargamos inmueble 0 en la interfaz
                inmueble0 = DAL.getInmuebleByID(listaInmuebles.get(0 + paginaActual*5));
                textPrecio0.setText("Precio: " + String.valueOf((int) inmueble0.getPrecio()) + "€");
                textDescripcion0.setText(String.valueOf(inmueble0.getDescripcion()));
                textNumHabs0.setText("Núm. Habitaciones: " + String.valueOf(inmueble0.getnHabit()));
                textNumBanyos0.setText("Núm. Baños: " + String.valueOf(inmueble0.getnBanos()));
                textSuperficie0.setText("Superficie: " + String.valueOf(inmueble0.getSuperficie()) + "m");
                textBarrio0.setText("Barrio: " + String.valueOf(inmueble0.getBarrio()));
                textTipoInmueble0.setText(String.valueOf(inmueble0.getTipoInm()));
                textTitulo0.setText(String.valueOf(inmueble0.getNombre()));
                
                Image imagen0 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(0 + paginaActual*5)));
                imageView0.setImage(imagen0);
                    
                //Cargamos inmueble 1 en la interfaz
                inmueble1 = DAL.getInmuebleByID(listaInmuebles.get(1 + paginaActual*5));
                textPrecio1.setText("Precio: " + String.valueOf((int) inmueble1.getPrecio()) + "€");
                textDescripcion1.setText(String.valueOf(inmueble1.getDescripcion()));
                textNumHabs1.setText("Núm. Habitaciones: " + String.valueOf(inmueble1.getnHabit()));
                textNumBanyos1.setText("Núm. Baños: " + String.valueOf(inmueble1.getnBanos()));
                textSuperficie1.setText("Superficie: " + String.valueOf(inmueble1.getSuperficie()) + "m");
                textBarrio1.setText("Barrio: " + String.valueOf(inmueble1.getBarrio()));
                textTipoInmueble1.setText(String.valueOf(inmueble1.getTipoInm()));
                Image imagen1 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(1 + paginaActual*5)));
                imageView1.setImage(imagen1);
                textTitulo1.setText(String.valueOf(inmueble1.getNombre()));
                    
                //Cargamos inmueble 2 en la interfaz
                inmueble2 = DAL.getInmuebleByID(listaInmuebles.get(2 + paginaActual*5));
                textPrecio2.setText("Precio: " + String.valueOf((int) inmueble2.getPrecio()) + "€");
                textDescripcion2.setText(String.valueOf(inmueble2.getDescripcion()));
                textNumHabs2.setText("Núm. Habitaciones: " + String.valueOf(inmueble2.getnHabit()));
                textNumBanyos2.setText("Núm. Baños: " + String.valueOf(inmueble2.getnBanos()));
                textSuperficie2.setText("Superficie: " + String.valueOf(inmueble2.getSuperficie()) + "m");
                textBarrio2.setText("Barrio: " + String.valueOf(inmueble2.getBarrio()));
                textTipoInmueble2.setText(String.valueOf(inmueble2.getTipoInm()));
                Image imagen2 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(2 + paginaActual*5)));
                imageView2.setImage(imagen2);
                textTitulo2.setText(String.valueOf(inmueble2.getNombre()));
                    
                //Cargamos inmueble 3 en la interfaz
                inmueble3= DAL.getInmuebleByID(listaInmuebles.get(3 + paginaActual*5));
                textPrecio3.setText("Precio: " + String.valueOf((int) inmueble3.getPrecio()) + "€");
                textDescripcion3.setText(String.valueOf(inmueble3.getDescripcion()));
                textNumHabs3.setText("Núm. Habitaciones: " + String.valueOf(inmueble3.getnHabit()));
                textNumBanyos3.setText("Núm. Baños: " + String.valueOf(inmueble3.getnBanos()));
                textSuperficie3.setText("Superficie: " + String.valueOf(inmueble3.getSuperficie()) + "m");
                textBarrio3.setText("Barrio: " + String.valueOf(inmueble3.getBarrio()));
                textTipoInmueble3.setText(String.valueOf(inmueble3.getTipoInm()));
                Image imagen3 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(3 + paginaActual*5)));
                imageView3.setImage(imagen3);
                textTitulo3.setText(String.valueOf(inmueble3.getNombre()));
                    
                //Cargamos inmueble 4 en la interfaz
                inmueble4 = DAL.getInmuebleByID(listaInmuebles.get(4 + paginaActual*5));
                textPrecio4.setText("Precio: " + String.valueOf((int) inmueble4.getPrecio()) + "€");
                textDescripcion4.setText(String.valueOf(inmueble4.getDescripcion()));
                textNumHabs4.setText("Núm. Habitaciones: " + String.valueOf(inmueble4.getnHabit()));
                textNumBanyos4.setText("Núm. Baños: " + String.valueOf(inmueble4.getnBanos()));
                textSuperficie4.setText("Superficie: " + String.valueOf(inmueble4.getSuperficie()) + "m");
                textBarrio4.setText("Barrio: " + String.valueOf(inmueble4.getBarrio()));
                textTipoInmueble4.setText(String.valueOf(inmueble4.getTipoInm()));
                Image imagen4 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(4 + paginaActual*5)));
                imageView4.setImage(imagen4);
                textTitulo4.setText(String.valueOf(inmueble4.getNombre()));
            //La página tiene menos de 5 resultados
            } else if(paginaActual == totalPaginas) {
                //Hay 1 resultado
                if(pisosUltimaPagina == 1){
                    //Cargamos inmueble 0 en la interfaz
                    
                    inmueble0 = DAL.getInmuebleByID(listaInmuebles.get(0 + paginaActual*5));
                    textPrecio0.setText("Precio: " + String.valueOf((int) inmueble0.getPrecio()) + "€");
                    textDescripcion0.setText(String.valueOf(inmueble0.getDescripcion()));
                    textNumHabs0.setText("Núm. Habitaciones: " + String.valueOf(inmueble0.getnHabit()));
                    textNumBanyos0.setText("Núm. Baños: " + String.valueOf(inmueble0.getnBanos()));
                    textSuperficie0.setText("Superficie: " + String.valueOf(inmueble0.getSuperficie()) + "m");
                    textBarrio0.setText("Barrio: " + String.valueOf(inmueble0.getBarrio()));
                    textTipoInmueble0.setText(String.valueOf(inmueble0.getTipoInm()));
                    textTitulo0.setText(String.valueOf(inmueble0.getNombre()));
                
                    Image imagen0 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(0 + paginaActual*5)));
                    imageView0.setImage(imagen0);
                    
                    panelInmueble0.visibleProperty().set(true);
                    panelInmueble1.visibleProperty().set(false);
                    panelInmueble2.visibleProperty().set(false);
                    panelInmueble3.visibleProperty().set(false);
                    panelInmueble4.visibleProperty().set(false);
                //Hay 2 resultados
                } else if(pisosUltimaPagina == 2){
                    //Cargamos inmueble 0 en la interfaz
                    inmueble0 = DAL.getInmuebleByID(listaInmuebles.get(0 + paginaActual*5));
                    textPrecio0.setText("Precio: " + String.valueOf((int) inmueble0.getPrecio()) + "€");
                    textDescripcion0.setText(String.valueOf(inmueble0.getDescripcion()));
                    textNumHabs0.setText("Núm. Habitaciones: " + String.valueOf(inmueble0.getnHabit()));
                    textNumBanyos0.setText("Núm. Baños: " + String.valueOf(inmueble0.getnBanos()));
                    textSuperficie0.setText("Superficie: " + String.valueOf(inmueble0.getSuperficie()) + "m");
                    textBarrio0.setText("Barrio: " + String.valueOf(inmueble0.getBarrio()));
                    textTipoInmueble0.setText(String.valueOf(inmueble0.getTipoInm()));
                    textTitulo0.setText(String.valueOf(inmueble0.getNombre()));
                
                    Image imagen0 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(0 + paginaActual*5)));
                    imageView0.setImage(imagen0);
                    
                    //Cargamos inmueble 1 en la interfaz
                    inmueble1 = DAL.getInmuebleByID(listaInmuebles.get(1 + paginaActual*5));
                    textPrecio1.setText("Precio: " + String.valueOf((int) inmueble1.getPrecio()) + "€");
                    textDescripcion1.setText(String.valueOf(inmueble1.getDescripcion()));
                    textNumHabs1.setText("Núm. Habitaciones: " + String.valueOf(inmueble1.getnHabit()));
                    textNumBanyos1.setText("Núm. Baños: " + String.valueOf(inmueble1.getnBanos()));
                    textSuperficie1.setText("Superficie: " + String.valueOf(inmueble1.getSuperficie()) + "m");
                    textBarrio1.setText("Barrio: " + String.valueOf(inmueble1.getBarrio()));
                    textTipoInmueble1.setText(String.valueOf(inmueble1.getTipoInm()));
                    Image imagen1 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(1 + paginaActual*5)));
                    imageView1.setImage(imagen1);
                    textTitulo1.setText(String.valueOf(inmueble1.getNombre()));
                    
                    panelInmueble0.visibleProperty().set(true);
                    panelInmueble1.visibleProperty().set(true);
                    panelInmueble2.visibleProperty().set(false);
                    panelInmueble3.visibleProperty().set(false);
                    panelInmueble4.visibleProperty().set(false);
                //Hay 3 resultados
                } else if(pisosUltimaPagina == 3) {
                    //Cargamos inmueble 0 en la interfaz
                    inmueble0 = DAL.getInmuebleByID(listaInmuebles.get(0 + paginaActual*5));
                    textPrecio0.setText("Precio: " + String.valueOf((int) inmueble0.getPrecio()) + "€");
                    textDescripcion0.setText(String.valueOf(inmueble0.getDescripcion()));
                    textNumHabs0.setText("Núm. Habitaciones: " + String.valueOf(inmueble0.getnHabit()));
                    textNumBanyos0.setText("Núm. Baños: " + String.valueOf(inmueble0.getnBanos()));
                    textSuperficie0.setText("Superficie: " + String.valueOf(inmueble0.getSuperficie()) + "m");
                    textBarrio0.setText("Barrio: " + String.valueOf(inmueble0.getBarrio()));
                    textTipoInmueble0.setText(String.valueOf(inmueble0.getTipoInm()));
                    textTitulo0.setText(String.valueOf(inmueble0.getNombre()));
                
                    Image imagen0 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(0 + paginaActual*5)));
                    imageView0.setImage(imagen0);
                    
                    //Cargamos inmueble 1 en la interfaz
                    inmueble1 = DAL.getInmuebleByID(listaInmuebles.get(1 + paginaActual*5));
                    textPrecio1.setText("Precio: " + String.valueOf((int) inmueble1.getPrecio()) + "€");
                    textDescripcion1.setText(String.valueOf(inmueble1.getDescripcion()));
                    textNumHabs1.setText("Núm. Habitaciones: " + String.valueOf(inmueble1.getnHabit()));
                    textNumBanyos1.setText("Núm. Baños: " + String.valueOf(inmueble1.getnBanos()));
                    textSuperficie1.setText("Superficie: " + String.valueOf(inmueble1.getSuperficie()) + "m");
                    textBarrio1.setText("Barrio: " + String.valueOf(inmueble1.getBarrio()));
                    textTipoInmueble1.setText(String.valueOf(inmueble1.getTipoInm()));
                    Image imagen1 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(1 + paginaActual*5)));
                    imageView1.setImage(imagen1);
                    textTitulo1.setText(String.valueOf(inmueble1.getNombre()));
                    
                    //Cargamos inmueble 2 en la interfaz
                    inmueble2 = DAL.getInmuebleByID(listaInmuebles.get(2 + paginaActual*5));
                    textPrecio2.setText("Precio: " + String.valueOf((int) inmueble2.getPrecio()) + "€");
                    textDescripcion2.setText(String.valueOf(inmueble2.getDescripcion()));
                    textNumHabs2.setText("Núm. Habitaciones: " + String.valueOf(inmueble2.getnHabit()));
                    textNumBanyos2.setText("Núm. Baños: " + String.valueOf(inmueble2.getnBanos()));
                    textSuperficie2.setText("Superficie: " + String.valueOf(inmueble2.getSuperficie()) + "m");
                    textBarrio2.setText("Barrio: " + String.valueOf(inmueble2.getBarrio()));
                    textTipoInmueble2.setText(String.valueOf(inmueble2.getTipoInm()));
                    Image imagen2 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(2 + paginaActual*5)));
                    imageView2.setImage(imagen2);
                    textTitulo2.setText(String.valueOf(inmueble2.getNombre()));
                    
                    panelInmueble0.visibleProperty().set(true);
                    panelInmueble1.visibleProperty().set(true);
                    panelInmueble2.visibleProperty().set(true);
                    panelInmueble3.visibleProperty().set(false);
                    panelInmueble4.visibleProperty().set(false);
                //Hay 4 resultados
                } else if(pisosUltimaPagina == 4){
                    //Cargamos inmueble 0 en la interfaz
                    inmueble0 = DAL.getInmuebleByID(listaInmuebles.get(0 + paginaActual*5));
                    textPrecio0.setText("Precio: " + String.valueOf((int) inmueble0.getPrecio()) + "€");
                    textDescripcion0.setText(String.valueOf(inmueble0.getDescripcion()));
                    textNumHabs0.setText("Núm. Habitaciones: " + String.valueOf(inmueble0.getnHabit()));
                    textNumBanyos0.setText("Núm. Baños: " + String.valueOf(inmueble0.getnBanos()));
                    textSuperficie0.setText("Superficie: " + String.valueOf(inmueble0.getSuperficie()) + "m");
                    textBarrio0.setText("Barrio: " + String.valueOf(inmueble0.getBarrio()));
                    textTipoInmueble0.setText(String.valueOf(inmueble0.getTipoInm()));
                    textTitulo0.setText(String.valueOf(inmueble0.getNombre()));
                
                    Image imagen0 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(0 + paginaActual*5)));
                    imageView0.setImage(imagen0);
                    
                    //Cargamos inmueble 1 en la interfaz
                    inmueble1 = DAL.getInmuebleByID(listaInmuebles.get(1 + paginaActual*5));
                    textPrecio1.setText("Precio: " + String.valueOf((int) inmueble1.getPrecio()) + "€");
                    textDescripcion1.setText(String.valueOf(inmueble1.getDescripcion()));
                    textNumHabs1.setText("Núm. Habitaciones: " + String.valueOf(inmueble1.getnHabit()));
                    textNumBanyos1.setText("Núm. Baños: " + String.valueOf(inmueble1.getnBanos()));
                    textSuperficie1.setText("Superficie: " + String.valueOf(inmueble1.getSuperficie()) + "m");
                    textBarrio1.setText("Barrio: " + String.valueOf(inmueble1.getBarrio()));
                    textTipoInmueble1.setText(String.valueOf(inmueble1.getTipoInm()));
                    Image imagen1 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(1 + paginaActual*5)));
                    imageView1.setImage(imagen1);
                    textTitulo1.setText(String.valueOf(inmueble1.getNombre()));
                    
                    //Cargamos inmueble 2 en la interfaz
                    inmueble2 = DAL.getInmuebleByID(listaInmuebles.get(2 + paginaActual*5));
                    textPrecio2.setText("Precio: " + String.valueOf((int) inmueble2.getPrecio()) + "€");
                    textDescripcion2.setText(String.valueOf(inmueble2.getDescripcion()));
                    textNumHabs2.setText("Núm. Habitaciones: " + String.valueOf(inmueble2.getnHabit()));
                    textNumBanyos2.setText("Núm. Baños: " + String.valueOf(inmueble2.getnBanos()));
                    textSuperficie2.setText("Superficie: " + String.valueOf(inmueble2.getSuperficie()) + "m");
                    textBarrio2.setText("Barrio: " + String.valueOf(inmueble2.getBarrio()));
                    textTipoInmueble2.setText(String.valueOf(inmueble2.getTipoInm()));
                    Image imagen2 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(2 + paginaActual*5)));
                    imageView2.setImage(imagen2);
                    textTitulo2.setText(String.valueOf(inmueble2.getNombre()));
                    
                    //Cargamos inmueble 3 en la interfaz
                    inmueble3= DAL.getInmuebleByID(listaInmuebles.get(3 + paginaActual*5));
                    textPrecio3.setText("Precio: " + String.valueOf((int) inmueble3.getPrecio()) + "€");
                    textDescripcion3.setText(String.valueOf(inmueble3.getDescripcion()));
                    textNumHabs3.setText("Núm. Habitaciones: " + String.valueOf(inmueble3.getnHabit()));
                    textNumBanyos3.setText("Núm. Baños: " + String.valueOf(inmueble3.getnBanos()));
                    textSuperficie3.setText("Superficie: " + String.valueOf(inmueble3.getSuperficie()) + "m");
                    textBarrio3.setText("Barrio: " + String.valueOf(inmueble3.getBarrio()));
                    textTipoInmueble3.setText(String.valueOf(inmueble3.getTipoInm()));
                    Image imagen3 = new Image(DAL.getPreviewPhoto(listaInmuebles.get(3 + paginaActual*5)));
                    imageView3.setImage(imagen3);
                    textTitulo3.setText(String.valueOf(inmueble3.getNombre()));
                    
                    panelInmueble0.visibleProperty().set(true);
                    panelInmueble1.visibleProperty().set(true);
                    panelInmueble2.visibleProperty().set(true);
                    panelInmueble3.visibleProperty().set(true);
                    panelInmueble4.visibleProperty().set(false);
                    
                } else {
                    System.out.println("No hay resultados");
                    panelInmueble0.visibleProperty().set(false);
                    panelInmueble1.visibleProperty().set(false);
                    panelInmueble2.visibleProperty().set(false);
                    panelInmueble3.visibleProperty().set(false);
                    panelInmueble4.visibleProperty().set(false);
                    botonAnterior.disableProperty().set(true);
                    botonSiguiente.disableProperty().set(true);
                }
            }
            
            if(paginaActual == 0){botonAnterior.disableProperty().set(true);} else {botonAnterior.disableProperty().set(false);}
            if(paginaActual == totalPaginas){botonSiguiente.disableProperty().set(true);} else {botonSiguiente.disableProperty().set(false);}
            textoNumPagina.setText("Página " + String.valueOf(paginaActual + 1) + " de " + String.valueOf(totalPaginas + 1));
            
        }catch(SQLException e){ 
            //No hay resultados
            System.out.println("Error SQL");
            panelInmueble0.visibleProperty().set(false);
            panelInmueble1.visibleProperty().set(false);
            panelInmueble2.visibleProperty().set(false);
            panelInmueble3.visibleProperty().set(false);
            panelInmueble4.visibleProperty().set(false);
            botonAnterior.disableProperty().set(true);
            botonSiguiente.disableProperty().set(true);
        }
    }
    
    private void mostrarModificarInmueble() throws IOException {
        
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/ModificarInmuebleFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        ModificarInmuebleController ModificarInmuebleController = myLoader.<ModificarInmuebleController>getController();

        Stage modificarInmuebleStage = new Stage();
        modificarInmuebleStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        ModificarInmuebleController.initModificarInmueble(modificarInmuebleStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //Añadir css
        //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        //we asign new scene to current stage/window
        modificarInmuebleStage.setScene(scene);
        modificarInmuebleStage.setTitle("Modificar Inmueble");
        modificarInmuebleStage.initModality(Modality.APPLICATION_MODAL);
        modificarInmuebleStage.show();
        misInmueblesStage.close();
    }
    
}
