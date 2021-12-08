/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import basededatos.DAL;
import static controlador.InicioController.inicioStage;
import java.util.ArrayList;
import java.util.List;
import controlador.Inmueble;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * FXML Controller class
 *
 * @author David
 */
public class ResultadosController implements Initializable {
    
    public static Stage resultadosStage;

    public static void initResultados(Stage stage){
        resultadosStage = stage;
    
    }
    int paginaActual = 0;
    String ciudad = InicioController.ciudad; 
    String tipoInmueble = InicioController.tipoInmueble;
    String tipoDeVenta = InicioController.tipoDeVenta;
    public static Inmueble inmuebleSeleccionado;
    
    private Inmueble inmueble0;
    private Inmueble inmueble1;
    private Inmueble inmueble2;
    private Inmueble inmueble3;
    private Inmueble inmueble4;
    @FXML
    private ImageView iconoUsuario;
    @FXML
    private ImageView imageView1;
    @FXML
    private ImageView imageView2;
    @FXML
    private ImageView imageView3;
    @FXML
    private ImageView imageView4;
    @FXML
    private AnchorPane panelInmueble2;
    @FXML
    private AnchorPane panelInmueble3;
    @FXML
    private AnchorPane panelInmueble4;
    @FXML
    private AnchorPane panelInmueble1;
    @FXML
    private Button botonAnterior;
    @FXML
    private Button botonSiguiente;
    @FXML
    private Text textoNumPagina;
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
    private Button botonContactar0;
    @FXML
    private Label textNumTelefono0;
    @FXML
    private AnchorPane panelInmueble0;
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
    private Button botonContactar1;
    @FXML
    private Label textNumTelefono1;
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
    private Button botonContactar2;
    @FXML
    private Label textNumTelefono2;
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
    private Button botonContactar3;
    @FXML
    private Label textNumTelefono3;
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
    private Button botonContactar4;
    @FXML
    private Label textNumTelefono4;
    @FXML
    private ScrollPane scrollPaneResultados;
    @FXML
    private Label textBarrio0;
    @FXML
    private Label textBarrio1;
    @FXML
    private Label textBarrio2;
    @FXML
    private Label textBarrio3;
    @FXML
    private Label textBarrio4;
    @FXML
    private TextField textBuscar;
    @FXML
    private Button botonBuscar;
    @FXML
    private Label textTitulo0;
    @FXML
    private Label textTitulo1;
    @FXML
    private Label textTitulo2;
    @FXML
    private Label textTitulo3;
    @FXML
    private Label textTitulo4;
    @FXML
    private ChoiceBox<String> escogerTipoInmueble;
    @FXML
    private ChoiceBox<String> escogerTipoVenta;
    @FXML
    private TextField escogerPrecioMin;
    @FXML
    private TextField escogerPrecioMax;
    @FXML
    private TextField escogerHabitaciones;
    @FXML
    private TextField escogerBaños;
    @FXML
    private CheckBox amueblado;
    @FXML
    private ComboBox<String> escogerTipoVivienda;
    @FXML
    private CheckBox garaje;
    @FXML
    private CheckBox admite_mascotas;
    @FXML
    private CheckBox ascensor;
    @FXML
    private CheckBox jardin;
    @FXML
    private CheckBox piscina;
    @FXML
    private CheckBox terraza;
    @FXML
    private CheckBox trastero;
    @FXML
    private CheckBox agua_caliente;
    @FXML
    private CheckBox calefaccion;
    @FXML
    private CheckBox seguridad;
    @FXML
    private CheckBox aire_acondicionado;
    @FXML
    private Button botonIniciarSesion;
    @FXML
    private Button botonRegistrarse;
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
    private Text labelBanos;
    @FXML
    private Text labelHabitaciones;
    
    List<Integer> listaInmuebles;
    @FXML
    private WebView webView;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        buttonFav0.getStyleClass().add("buttonFav");
        buttonFav1.getStyleClass().add("buttonFav");
        buttonFav2.getStyleClass().add("buttonFav");
        buttonFav3.getStyleClass().add("buttonFav");
        buttonFav4.getStyleClass().add("buttonFav");
        CargarResultados(paginaActual);
        
        if(LoginController.isLoged==true){
            botonContactar0.disableProperty().set(false);
            botonContactar1.disableProperty().set(false);
            botonContactar2.disableProperty().set(false);
            botonContactar3.disableProperty().set(false);
            botonContactar4.disableProperty().set(false);
            userLoged();
        }else{
            botonContactar0.disableProperty().set(true);
            botonContactar1.disableProperty().set(true);
            botonContactar2.disableProperty().set(true);
            botonContactar3.disableProperty().set(true);
            botonContactar4.disableProperty().set(true);
        }

        escogerTipoInmueble.getItems().addAll("Vivienda", "Oficina","Habitacion","Garaje");
        escogerTipoInmueble.getSelectionModel().select(tipoInmueble);
        escogerTipoVenta.getItems().addAll("Comprar", "Alquilar");
        escogerTipoVenta.getSelectionModel().select(tipoDeVenta);
        escogerTipoVivienda.getItems().addAll("Adosado", "Ático","Casa","Chalet","Duplex","Piso");
        escogerTipoVivienda.getSelectionModel().select("Piso");
        escogerTipoVivienda.setValue("Piso");
        
        escogerPrecioMin.setOnKeyTyped(event ->{
            String c = event.getCharacter();
            if(escogerPrecioMin.getText().length() > 8 || !c.matches("\\d*")) event.consume();
        });
        escogerPrecioMax.setOnKeyTyped(event ->{
            String c = event.getCharacter();
            if(escogerPrecioMax.getText().length() > 8 || !c.matches("\\d*")) event.consume();
        });
        escogerHabitaciones.setOnKeyTyped(event ->{
            String c = event.getCharacter();
            if(escogerHabitaciones.getText().length() > 1 || !c.matches("\\d*")) event.consume();
        });
        escogerBaños.setOnKeyTyped(event ->{
            String c = event.getCharacter();
            if(escogerBaños.getText().length() > 1 || !c.matches("\\d*")) event.consume();
        });
        escogerTipoInmueble.setOnAction(event -> {
            try{
                habilitarCaracteristicas(escogerTipoInmueble.getValue());
            }catch(SQLException ex){
                ex.printStackTrace();
            }
            
        });
        
        try{
                habilitarCaracteristicas(escogerTipoInmueble.getValue());
            }catch(SQLException ex){
                ex.printStackTrace();
            }
    }    

    
    private void CargarResultados(int paginaActual){
        
       
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles;
            
                //listaInmuebles = DAL.getIdInmCiudad(ciudad, escogerTipoInmueble.getValue(), escogerTipoVenta.getValue());
    
            String tipoV = "";
            if(tipoInmueble == "Vivienda") {
                if(escogerTipoVivienda.getValue()==null) {
                    tipoV = "Piso";
                } else {
                tipoV = escogerTipoVivienda.getValue();
                }
                System.out.println(tipoInmueble + tipoV);
            }
            listaInmuebles = DAL.buscarInmuebles(ciudad, tipoInmueble, tipoDeVenta, escogerPrecioMin.getText(), escogerPrecioMax.getText(), 
            escogerHabitaciones.getText(), escogerBaños.getText(), amueblado.selectedProperty().getValue(), tipoV, 
            garaje.selectedProperty().getValue(), admite_mascotas.selectedProperty().getValue(), aire_acondicionado.selectedProperty().getValue(), 
            ascensor.selectedProperty().getValue(), jardin.selectedProperty().getValue(), piscina.selectedProperty().getValue(), 
            terraza.selectedProperty().getValue(), trastero.selectedProperty().getValue(), agua_caliente.selectedProperty().getValue(), 
            calefaccion.selectedProperty().getValue() ,seguridad.selectedProperty().getValue());
            
            iniciarMapa(listaInmuebles);
            
            int totalPaginas = listaInmuebles.size() / 5;
            int pisosUltimaPagina = listaInmuebles.size()%5;
            
            //La página tiene 5 resultados
            if(paginaActual < totalPaginas){
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
                textNumTelefono0.setText(String.valueOf(inmueble0.getNumero()));
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
                textNumTelefono1.setText(String.valueOf(inmueble1.getNumero()));    
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
                textNumTelefono2.setText(String.valueOf(inmueble2.getNumero()));    
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
                textNumTelefono3.setText(String.valueOf(inmueble3.getNumero()));    
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
                textNumTelefono4.setText(String.valueOf(inmueble4.getNumero()));    
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
                    textNumTelefono0.setText(String.valueOf(inmueble0.getNumero()));
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
                    textNumTelefono0.setText(String.valueOf(inmueble0.getNumero()));
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
                    textNumTelefono1.setText(String.valueOf(inmueble1.getNumero()));    
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
                    textNumTelefono0.setText(String.valueOf(inmueble0.getNumero()));
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
                    textNumTelefono1.setText(String.valueOf(inmueble1.getNumero()));    
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
                    textNumTelefono2.setText(String.valueOf(inmueble2.getNumero()));    
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
                    textNumTelefono0.setText(String.valueOf(inmueble0.getNumero()));
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
                    textNumTelefono1.setText(String.valueOf(inmueble1.getNumero()));    
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
                    textNumTelefono2.setText(String.valueOf(inmueble2.getNumero()));    
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
                    textNumTelefono3.setText(String.valueOf(inmueble3.getNumero()));    
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
    
    private void habilitarCaracteristicas(String tipoInm) throws SQLException{
        if(tipoInm=="Vivienda"){
            escogerTipoVivienda.setVisible(true);
            garaje.setVisible(true);
            admite_mascotas.setVisible(true);
            ascensor.setVisible(true);
            jardin.setVisible(true);
            piscina.setVisible(true);
            terraza.setVisible(true);
            trastero.setVisible(true);
            agua_caliente.setVisible(true);
            calefaccion.setVisible(true);
            seguridad.setVisible(true);
            aire_acondicionado.setVisible(true);
            //
            labelBanos.setVisible(true);
            labelHabitaciones.setVisible(true);
            escogerBaños.setVisible(true);
            escogerHabitaciones.setVisible(true);
            amueblado.setVisible(true);
        } else if(tipoInm=="Habitacion"){
            escogerTipoVivienda.setVisible(false);
            garaje.setVisible(false);
            admite_mascotas.setVisible(true);
            ascensor.setVisible(true);
            jardin.setVisible(false);
            piscina.setVisible(false);
            terraza.setVisible(true);
            trastero.setVisible(false);
            agua_caliente.setVisible(true);
            calefaccion.setVisible(true);
            seguridad.setVisible(true);
            aire_acondicionado.setVisible(true);
            garaje.setSelected(false);
            jardin.setSelected(false);
            piscina.setSelected(false);
            trastero.setSelected(false);
            //
            labelBanos.setVisible(false);
            labelHabitaciones.setVisible(false);
            escogerBaños.setVisible(false);
            escogerHabitaciones.setVisible(false);
            amueblado.setVisible(true);
            escogerBaños.deselect();
            escogerHabitaciones.deselect();

            
        } else if(tipoInm=="Oficina"){
            escogerTipoVivienda.setVisible(false);
            garaje.setVisible(false);
            admite_mascotas.setVisible(false);
            ascensor.setVisible(true);
            jardin.setVisible(false);
            piscina.setVisible(false);
            terraza.setVisible(false);
            trastero.setVisible(false);
            agua_caliente.setVisible(false);
            calefaccion.setVisible(true);
            seguridad.setVisible(true);
            aire_acondicionado.setVisible(true);
            
            garaje.setSelected(false);
            jardin.setSelected(false);
            piscina.setSelected(false);
            trastero.setSelected(false);
            admite_mascotas.setSelected(false);
            terraza.setSelected(false);
            agua_caliente.setSelected(false);
            //
            labelBanos.setVisible(false);
            labelHabitaciones.setVisible(false);
            escogerBaños.setVisible(false);
            escogerHabitaciones.setVisible(false);
            amueblado.setVisible(false);
            escogerBaños.deselect();
            escogerHabitaciones.deselect();
            amueblado.setSelected(false);
            
        } else if(tipoInm=="Garaje"){
            escogerTipoVivienda.setVisible(false);
            garaje.setVisible(false);
            admite_mascotas.setVisible(false);
            ascensor.setVisible(false);
            jardin.setVisible(false);
            piscina.setVisible(false);
            terraza.setVisible(false);
            trastero.setVisible(false);
            agua_caliente.setVisible(false);
            calefaccion.setVisible(false);
            seguridad.setVisible(true);
            aire_acondicionado.setVisible(false);
            
            garaje.setSelected(false);
            jardin.setSelected(false);
            piscina.setSelected(false);
            trastero.setSelected(false);
            admite_mascotas.setSelected(false);
            terraza.setSelected(false);
            agua_caliente.setSelected(false);
            ascensor.setSelected(false);
            calefaccion.setSelected(false);
            aire_acondicionado.setSelected(false);
            //
            labelBanos.setVisible(false);
            labelHabitaciones.setVisible(false);
            escogerBaños.setVisible(false);
            escogerHabitaciones.setVisible(false);
            amueblado.setVisible(false);
            escogerBaños.deselect();
            escogerHabitaciones.deselect();
            amueblado.setSelected(false);
            
        }
        
    }
    
    @FXML
    private void ClickInmueble0 (MouseEvent event) throws IOException {
       
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = DAL.getInmuebleByID(listaInmuebles.get(0 + paginaActual*5));
            System.out.println(inmuebleSeleccionado.inmToString());
            mostrarFichaInmueble();
        } catch(SQLException e){}
    }

    @FXML
    private void ClickInmueble1 (MouseEvent event) throws IOException {
       
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = DAL.getInmuebleByID(listaInmuebles.get(1 + paginaActual*5));
            mostrarFichaInmueble();
            
        } catch(SQLException e){}
    }
    
    @FXML
    private void ClickInmueble2 (MouseEvent event) throws IOException {
       
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = DAL.getInmuebleByID(listaInmuebles.get(2 + paginaActual*5));
            mostrarFichaInmueble();
        } catch(SQLException e){}
    }

    @FXML
    private void ClickInmueble3 (MouseEvent event) throws IOException {
       
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = DAL.getInmuebleByID(listaInmuebles.get(3 + paginaActual*5));
            mostrarFichaInmueble();
        } catch(SQLException e){}
    }

    @FXML
    private void ClickInmueble4 (MouseEvent event) throws IOException {
       
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = DAL.getInmuebleByID(listaInmuebles.get(4 + paginaActual*5));
            mostrarFichaInmueble();
        } catch(SQLException e){}
    }

    
    private void mostrarFichaInmueble() throws IOException {
        
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/FichaInmuebleFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        FichaInmuebleController FichaInmuebleController = myLoader.<FichaInmuebleController>getController();

        Stage fichaInmuebleStage = new Stage();
        fichaInmuebleStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        FichaInmuebleController.initFichaInmueble(fichaInmuebleStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //Añadir css
        //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        //we asign new scene to current stage/window
        fichaInmuebleStage.setScene(scene);
        fichaInmuebleStage.setTitle("Ficha Inmueble");
        fichaInmuebleStage.initModality(Modality.APPLICATION_MODAL);
        fichaInmuebleStage.show();
        FichaInmuebleController.vuelve = true;
        resultadosStage.hide();
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
    private void clickEscogerTipoVivienda(MouseEvent event) {
    }

    @FXML
    private void clickEscogerPrecio(MouseEvent event) {
    }


    @FXML
    private void clickContactar1(ActionEvent event) throws SQLException, IOException {
        DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            DAL.enviarMensaje(LoginController.cuentaLoged.getId_cuenta(), inmueble1.getId_cuenta(), inmueble1.getId_inm() , "Hola buenas! Estoy interesado en su inmueble!");
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/MensajeriaFXML.fxml"));
            Parent root = (Parent) myLoader.load();
            MensajeriaController MensajeriaController = myLoader.<MensajeriaController>getController();

            Stage mensajeriaStage = new Stage();
            mensajeriaStage.getIcons().add(new Image("/recursos/logoFinal.png"));
            MensajeriaController.initMensajeriaController(mensajeriaStage);
            //We create the scene for resultadosStage
            Scene scene = new Scene(root);

            //we asign new scene to current stage/window
            mensajeriaStage.setScene(scene);
            mensajeriaStage.setTitle("Mensajería");
            mensajeriaStage.initModality(Modality.APPLICATION_MODAL);
            mensajeriaStage.show();
    
    }



    @FXML
    private void clickContactar0(ActionEvent event) throws SQLException, IOException {
        DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            DAL.enviarMensaje(LoginController.cuentaLoged.getId_cuenta(), inmueble0.getId_cuenta(), inmueble0.getId_inm() , "Hola buenas! Estoy interesado en su inmueble!");
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/MensajeriaFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        MensajeriaController MensajeriaController = myLoader.<MensajeriaController>getController();

        Stage mensajeriaStage = new Stage();
        mensajeriaStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        MensajeriaController.initMensajeriaController(mensajeriaStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //we asign new scene to current stage/window
        mensajeriaStage.setScene(scene);
        mensajeriaStage.setTitle("Mensajería");
        mensajeriaStage.initModality(Modality.APPLICATION_MODAL);
        mensajeriaStage.show();
    }


    @FXML
    private void clickContactar2(ActionEvent event) throws SQLException, IOException {
        DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            DAL.enviarMensaje(LoginController.cuentaLoged.getId_cuenta(), inmueble2.getId_cuenta(), inmueble2.getId_inm() , "Hola buenas! Estoy interesado en su inmueble!");
    FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/MensajeriaFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        MensajeriaController MensajeriaController = myLoader.<MensajeriaController>getController();

        Stage mensajeriaStage = new Stage();
        mensajeriaStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        MensajeriaController.initMensajeriaController(mensajeriaStage);
        
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //we asign new scene to current stage/window
        mensajeriaStage.setScene(scene);
        mensajeriaStage.setTitle("Mensajería");
        mensajeriaStage.initModality(Modality.APPLICATION_MODAL);
        mensajeriaStage.show();
    }


    @FXML
    private void clickContactar3(ActionEvent event) throws SQLException, IOException {
        DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            DAL.enviarMensaje(LoginController.cuentaLoged.getId_cuenta(), inmueble3.getId_cuenta(), inmueble3.getId_inm() , "Hola buenas! Estoy interesado en su inmueble!");
    FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/MensajeriaFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        MensajeriaController MensajeriaController = myLoader.<MensajeriaController>getController();

        Stage mensajeriaStage = new Stage();
        mensajeriaStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        MensajeriaController.initMensajeriaController(mensajeriaStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //we asign new scene to current stage/window
        mensajeriaStage.setScene(scene);
        mensajeriaStage.setTitle("Mensajería");
        mensajeriaStage.initModality(Modality.APPLICATION_MODAL);
        mensajeriaStage.show();
    }


    @FXML
    private void clickContactar4(ActionEvent event) throws SQLException, IOException {
        DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            DAL.enviarMensaje(LoginController.cuentaLoged.getId_cuenta(), inmueble4.getId_cuenta(), inmueble4.getId_inm() , "Hola buenas! Estoy interesado en su inmueble!");
    FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/MensajeriaFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        MensajeriaController MensajeriaController = myLoader.<MensajeriaController>getController();

        Stage mensajeriaStage = new Stage();
        mensajeriaStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        MensajeriaController.initMensajeriaController(mensajeriaStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //we asign new scene to current stage/window
        mensajeriaStage.setScene(scene);
        mensajeriaStage.setTitle("Mensajería");
        mensajeriaStage.initModality(Modality.APPLICATION_MODAL);
        mensajeriaStage.show();
    }


    @FXML
    private void clickBuscar(ActionEvent event) {
        ciudad = textBuscar.getText();
        tipoInmueble = escogerTipoInmueble.getValue(); 
        tipoDeVenta = escogerTipoVenta.getValue();
        CargarResultados(paginaActual);
    }

    @FXML
    private void clickedIniciarSesion(ActionEvent event) throws IOException {
        
        if(LoginController.isLoged==false){
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/LoginFXML.fxml"));
            Parent root = (Parent) myLoader.load();
            LoginController LoginController = myLoader.<LoginController>getController();

            Stage loginStage = new Stage();
            loginStage.getIcons().add(new Image("/recursos/logoFinal.png"));
            LoginController.initLogin(loginStage);
            //We create the scene for resultadosStage
            Scene scene = new Scene(root);
        
            //Añadir css
            //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
            //scene.getStylesheets().add(css);
        
            //we asign new scene to current stage/window
            loginStage.setScene(scene);
            loginStage.setTitle("Iniciar Sesion");
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.show();
            resultadosStage.hide();
        } else {
            //Boton Mi Cuenta Activado
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
            resultadosStage.close();
            
        }
    }

    @FXML
    private void clickedRegistrarse(ActionEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/RegistroFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        RegistroController RegistroController = myLoader.<RegistroController>getController();

        Stage registroStage = new Stage();
        registroStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        RegistroController.initRegistro(registroStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //Añadir css
        //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        //we asign new scene to current stage/window
        registroStage.setScene(scene);
        registroStage.setTitle("Registrarse");
        registroStage.initModality(Modality.APPLICATION_MODAL);
        registroStage.show();
        inicioStage.hide();
    }

    @FXML
    private void clickFav0(ActionEvent event) throws IOException {
        if(LoginController.isLoged){
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = DAL.getInmuebleByID(listaInmuebles.get(0 + paginaActual*5));
            System.out.println(inmuebleSeleccionado.inmToString());
            escogerFavoritos();
        } catch(SQLException e){}
        } else {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Sin registrar");
            alert.setContentText("Debes estar logueado para poder añadir un inmueble a favoritos");
            Stage stage = (Stage)alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/recursos/logoFinal.png"));
            Optional<ButtonType> result = alert.showAndWait();
        }
        
    }

    @FXML
    private void clickFav1(ActionEvent event) throws IOException {
        if(LoginController.isLoged){
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = DAL.getInmuebleByID(listaInmuebles.get(1 + paginaActual*5));
            System.out.println(inmuebleSeleccionado.inmToString());
            escogerFavoritos();
        } catch(SQLException e){}
        
        } else {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Sin registrar");
            alert.setContentText("Debes estar logueado para poder añadir un inmueble a favoritos");
            Stage stage = (Stage)alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/recursos/logoFinal.png"));
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    private void clickFav2(ActionEvent event) throws IOException {
        if(LoginController.isLoged){
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = DAL.getInmuebleByID(listaInmuebles.get(2 + paginaActual*5));
            System.out.println(inmuebleSeleccionado.inmToString());
            escogerFavoritos();
        } catch(SQLException e){}
        
        } else {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Sin registrar");
            alert.setContentText("Debes estar logueado para poder añadir un inmueble a favoritos");
            Stage stage = (Stage)alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/recursos/logoFinal.png"));
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    private void clickFav3(ActionEvent event) throws IOException {
        if(LoginController.isLoged){
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = DAL.getInmuebleByID(listaInmuebles.get(3 + paginaActual*5));
            System.out.println(inmuebleSeleccionado.inmToString());
            escogerFavoritos();
        } catch(SQLException e){}
        
        } else {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Sin registrar");
            alert.setContentText("Debes estar logueado para poder añadir un inmueble a favoritos");
            Stage stage = (Stage)alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/recursos/logoFinal.png"));
            Optional<ButtonType> result = alert.showAndWait();
        }
    }

    @FXML
    private void clickFav4(ActionEvent event) throws IOException {
        if(LoginController.isLoged){
        try{
            DAL DAL = new DAL();
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = DAL.getInmuebleByID(listaInmuebles.get(4 + paginaActual*5));
            System.out.println(inmuebleSeleccionado.inmToString());
            escogerFavoritos();
        } catch(SQLException e){}
        
        } else {
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Sin registrar");
            alert.setContentText("Debes estar logueado para poder añadir un inmueble a favoritos");
            Stage stage = (Stage)alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("/recursos/logoFinal.png"));
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
    
    public void userLoged(){
        botonIniciarSesion.setText("Mi cuenta");
        botonRegistrarse.visibleProperty().set(false);
        
        String localDir = System.getProperty("user.dir");
        String pathImagen = localDir + LoginController.cuentaLoged.getImagenPerfil();
        Image imgPerfil = new Image("file:" + pathImagen); 
        
        iconoUsuario.setImage(imgPerfil);
    }
    
    private void escogerFavoritos() throws IOException {
        
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/MarcarFavoritoFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        MarcarFavoritoController MarcarFavoritoController = myLoader.<MarcarFavoritoController>getController();

        Stage marcarFavoritoStage = new Stage();
        marcarFavoritoStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        MarcarFavoritoController.initMarcarFavorito(marcarFavoritoStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //Añadir css
        //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        //we asign new scene to current stage/window
        marcarFavoritoStage.setScene(scene);
        marcarFavoritoStage.setTitle("Escoge grupo de favoritos");
        marcarFavoritoStage.initModality(Modality.APPLICATION_MODAL);
        marcarFavoritoStage.show();
        //resultadosStage.hide();
    }
    
    private void iniciarMapa(List<Integer> resultados){
        try{
        //pillo los inmuebles resultados pero me quedo solo con sus calles y numeros    
        String calles = "";
        Inmueble inmueble = new Inmueble();
        DAL dal = new DAL();
        for(int i = 0; i<resultados.size(); i++){
            inmueble = dal.getInmuebleByID(resultados.get(i));
            calles += inmueble.getCalle()+ " "+ inmueble.getNumero();
            if(i != resultados.size()-1) calles += "#";
        }
        //System.out.println("---------" + calles);
        
        WebEngine webEngine = webView.getEngine();
        File input = new File("./src/recursos/ejemploMaps.html");
        Document doc = Jsoup.parse(input, "UTF-8","");
        Element content = doc.getElementById("inmuebles");
        content.text(calles);
        //modificar el id = inmuebles del html y pasarle las calles
        
        webEngine.loadContent(doc.toString());
        
        }catch(IOException e){System.out.println(e);
        }catch(SQLException e1){System.out.println(e1);}
    }
}
