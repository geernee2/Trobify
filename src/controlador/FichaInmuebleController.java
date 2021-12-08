package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import controlador.Inmueble;
import java.util.ArrayList;
import java.util.List;
import basededatos.DAL;
import static controlador.FavoritosController.favoritosStage;
import static controlador.ResultadosController.inmuebleSeleccionado;
import static controlador.ResultadosController.resultadosStage;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import org.jsoup.nodes.Document;
/**
 * FXML Controller class
 */
public class FichaInmuebleController implements Initializable 
{
    public boolean vuelve = false;
    public static Stage fichaInmuebleStage;

    public static void initFichaInmueble(Stage stage)
    {
        fichaInmuebleStage = stage;
    
    }

    @FXML
    private ImageView imagenInmueble;
    @FXML
    private ImageView iconoBano;
    @FXML
    private ImageView iconoDormitorio;
    @FXML
    private Label labelPrecio;
    @FXML
    private Label labelDescripcion;
    @FXML
    private ImageView iconoTrobify;
    @FXML
    private ImageView iconoCuenta;
    @FXML
    private Button buttonSolicitarVisita;
    @FXML
    private Button buttonNextPhoto;
    @FXML
    private Button buttonBackPhoto;
    @FXML
    private Label labelEstancia;
    @FXML
    private Label labelNumBanos;
    @FXML
    private Label labelNumHabit;
    @FXML
    private Button botonAtras;
    @FXML
    private WebView webview;
    
    private int photoActual;
    
    private Image mainPhoto;
    
    private List<String> photosAndEstancias = new ArrayList<>();
    @FXML
    private Button buttonContraoferta;
    @FXML
    private TextField textContraoferta;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        cargarCuentaLoged();
        try
        {
            DAL DAL = new DAL();
            photoActual = 0;
            
            Inmueble inm = ResultadosController.inmuebleSeleccionado;
            cargaInmueble(inm);
            photosAndEstancias = DAL.getInmEstanciaPhotos(inm.getId_inm());
            
            if(photosAndEstancias.size() > 0)
            {
                mainPhoto = new Image(photosAndEstancias.get(0));
                imagenInmueble.setImage(mainPhoto);
                
                labelEstancia.setText(photosAndEstancias.get(1));
            }
            buttonBackPhoto.setDisable(true);
            
            if(photosAndEstancias.size() <= 2){buttonNextPhoto.setDisable(true);}
            
            WebEngine webEngine = webview.getEngine();
            EditorHTML a = new EditorHTML();
            Document doc = a.abrirHTML(inm.getMapa());
            webEngine.loadContent(doc.toString());
            
            
        }catch(IOException ex){System.out.println("Error al cargar el fichero");
        
        }catch(Exception e){ e.printStackTrace();}
        
        
    }   
        

    @FXML
    private void actionSolicitarVisita(ActionEvent event) throws SQLException, IOException
    {
        /*DAL DAL = new DAL();
        DAL.addSolicitudVisita(1,inmuebleSeleccionado.getId_inm());
        Alert confirmacion = new Alert(AlertType.CONFIRMATION);
	confirmacion.setTitle("Solicitud de Visita");
	confirmacion.setContentText("Solicitud de visita enviada correctamente");
	confirmacion.initStyle(StageStyle.UTILITY);
	confirmacion.showAndWait();*/
        //Abre ventana datePicker
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/SolicitarVisita.fxml"));
        Parent root = (Parent) myLoader.load();
        SolicitarVisitaController SolicitarVisitaController = myLoader.<SolicitarVisitaController>getController();

        Stage solicitarVisitaStage = new Stage();
        solicitarVisitaStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        SolicitarVisitaController.initSolicitarVisita(solicitarVisitaStage);
       
        Scene scene = new Scene(root);
        
        solicitarVisitaStage.setScene(scene);
        solicitarVisitaStage.setTitle("Solicitar Visita");
        //solicitarVisitaStage.initModality(Modality.APPLICATION_MODAL);
        solicitarVisitaStage.show();
    }

    @FXML
    private void actionNextPhoto(ActionEvent event) 
    {
        photoActual = photoActual + 2;
        mainPhoto = new Image(photosAndEstancias.get(photoActual));
        imagenInmueble.setImage(mainPhoto);
        labelEstancia.setText(photosAndEstancias.get(photoActual + 1));
        
        if(photosAndEstancias.size() <= photoActual + 2){buttonNextPhoto.setDisable(true);}
        if(photoActual > 1){buttonBackPhoto.setDisable(false);}
    }

    @FXML
    private void actionBackPhoto(ActionEvent event) 
    {
        photoActual = photoActual - 2;
        mainPhoto = new Image(photosAndEstancias.get(photoActual));
        imagenInmueble.setImage(mainPhoto);
        labelEstancia.setText(photosAndEstancias.get(photoActual + 1));
        
        if(photosAndEstancias.size() > photoActual + 2){buttonNextPhoto.setDisable(false);}
        if(photoActual < 2){buttonBackPhoto.setDisable(true);}
    }
    
    private void cargaInmueble(Inmueble inm)
    {
        //inm = ResultadosController.inmuebleSeleccionado;
        
        labelDescripcion.setText(inm.getDescripcion());
        labelPrecio.setText("Precio: " + (int) inm.getPrecio() + " €");
        labelNumBanos.setText("Núm. Baños: " + inm.getnBanos());
        labelNumHabit.setText("Núm. Habitaciones: " + inm.getnHabit());
       
        
    }
    

    @FXML
    private void clickVolverAtras(ActionEvent event) throws IOException {
        mostrarResultados();
    }
    
    private void mostrarResultados() throws IOException {
 
        if(vuelve){
            resultadosStage.show();
        } else {
            favoritosStage.show();
        }
        fichaInmuebleStage.close();
    }

    @FXML
    private void clickContraoferta(ActionEvent event) {
        String nombreInmueble = inmuebleSeleccionado.getNombre();
        String mensaje = "El usuario " + LoginController.cuentaLoged.getEmail() + " ofrece " + textContraoferta.getText() + " € por su inmueble " + nombreInmueble;
        DAL dal = new DAL();
        try {
            dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), inmuebleSeleccionado.getId_cuenta(), inmuebleSeleccionado.getId_inm(), LocalDate.now(), "contraoferta", mensaje);
        } catch (SQLException ex) {
            System.out.println("Error guardando la contraoferta");
        }
    }
    
    private void cargarCuentaLoged(){
        if(LoginController.isLoged){
            String localDir = System.getProperty("user.dir");
            String pathImagen = localDir + LoginController.cuentaLoged.getImagenPerfil();
            Image imgPerfil = new Image("file:" + pathImagen); 
        
            iconoCuenta.setImage(imgPerfil);
        
            textContraoferta.setVisible(true);
            buttonContraoferta.setVisible(true);
            buttonSolicitarVisita.setVisible(true);
        } else {
            textContraoferta.setVisible(false);
            buttonContraoferta.setVisible(false);
            buttonSolicitarVisita.setVisible(false);
        }
    }
}
