/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import basededatos.DAL;
import com.sun.scenario.effect.Effect;
import static controlador.ResultadosController.resultadosStage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderStroke;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import static javax.imageio.ImageIO.write;

/**
 * FXML Controller class
 *
 * @author David
 */
public class InicioController implements Initializable {

    @FXML
    private VBox fondoSize;
    @FXML
    private ChoiceBox<String> buttonTipoInmueble;
    @FXML
    private TextField textBuscar;
    @FXML
    private Button buttonBuscar;
    @FXML
    private Button buttonComprar;
    @FXML
    private Button buttonAlquilar;
    
    public static Stage inicioStage;

    public static String ciudad;
    
    public static String tipoInmueble;
    
    public static String tipoDeVenta="Comprar";
    @FXML
    private Button buttonRegistrarse;
    @FXML
    private Button buttonIniciarSesion;
    @FXML
    private ImageView imagePerfil;
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        buttonAlquilar.getStyleClass().add("botonAlquilar");
        buttonComprar.getStyleClass().add("botonComprar");
        
        buttonTipoInmueble.getItems().addAll("Vivienda", "Oficina","Habitacion","Garaje");
        buttonTipoInmueble.getSelectionModel().selectFirst();
        
        buttonComprar.setStyle("-fx-border-color: black");
        if(LoginController.isLoged==true){
            userLoged();
        }

           
    }
    public void initInicio(Stage stage){
            inicioStage = stage;  
            stage.getIcons().add(new Image("/recursos/logoFinal.png"));
    }
    
    public void userLoged(){
        buttonIniciarSesion.setText("Mi cuenta");
        buttonRegistrarse.visibleProperty().set(false);
        
        String localDir = System.getProperty("user.dir");
        String pathImagen = localDir + LoginController.cuentaLoged.getImagenPerfil();
        Image imgPerfil = new Image("file:" + pathImagen); 
        
        imagePerfil.setImage(imgPerfil);
    }
  

    @FXML
    private void clickedComprar(ActionEvent event) {
        tipoDeVenta="Comprar";
        buttonComprar.setStyle("-fx-border-color: black");
        buttonAlquilar.setStyle("");

    }

    @FXML
    private void clickedAlquilar(ActionEvent event) {
        tipoDeVenta="Alquilar";
        buttonAlquilar.setStyle("-fx-border-color: black");
        buttonComprar.setStyle("");
    }


    @FXML
    private void clickTipoInmueble(MouseEvent event) {
    }
    
    @FXML
    private void clickedBuscar(ActionEvent event) throws IOException {
        
    ciudad = textBuscar.getText();
    tipoInmueble = buttonTipoInmueble.getValue();
        
    mostrarResultados();
    }
    
    private void mostrarResultados() throws IOException {
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/ResultadosFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        ResultadosController ResultadosController = myLoader.<ResultadosController>getController();

        Stage resultadosStage = new Stage();
        resultadosStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        ResultadosController.initResultados(resultadosStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //Añadir css
        //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        //we asign new scene to current stage/window
        resultadosStage.setScene(scene);
        resultadosStage.setTitle("Resultados");
        resultadosStage.initModality(Modality.APPLICATION_MODAL);
        resultadosStage.show();
        inicioStage.hide();
        
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
            loginStage.setTitle("Iniciar sesión");
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.show();
            inicioStage.hide();
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
            inicioStage.hide();
            
        }
        
    }
           
    private static Image convertToFxImage(BufferedImage image) {
    WritableImage wr = null;
    if (image != null) {
        wr = new WritableImage(image.getWidth(), image.getHeight());
        PixelWriter pw = wr.getPixelWriter();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                pw.setArgb(x, y, image.getRGB(x, y));
            }
        }
    }

    return new ImageView(wr).getImage();
}
}
