/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import basededatos.DAL;
import static controlador.FichaInmuebleController.fichaInmuebleStage;
import static controlador.InicioController.inicioStage;
import static controlador.ResultadosController.resultadosStage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * FXML Controller class
 *
 * @author David
 */
public class RegistroController implements Initializable {

    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellidos;
    @FXML
    private TextField textCorreo;
    @FXML
    private TextField textTelefono;
    @FXML
    private PasswordField textContrasena;
    @FXML
    private ImageView imagePerfil;
    
    private String imagePath;
    private String tipoCuenta = "Usuario";
    private boolean tieneImagen = false;
    
    public static Stage registroStage;

    public static void initRegistro(Stage stage){
        registroStage = stage;
    
    }
    @FXML
    private Label textError;
    @FXML
    private PasswordField textRepetirContrasena;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickRegistrarse(ActionEvent event) throws IOException, SQLException {
        DAL ComprobarCorreo = new DAL();
            boolean existeCorreo = ComprobarCorreo.existsEmail(textCorreo.getText());
        //Comprueba que el campo Nombre no esté vacío
        if(textNombre.getText().length() == 0){
            textError.setText("No puedes dejar campo 'Nombre' vacío");
        //Comprueba que el campo Apellidos no esté vacío
        } else if(textApellidos.getText().length() == 0){
            textError.setText("No puedes dejar campo 'Apellidos' vacío");
        //Comprueba que textTel solo tenga 9 números
        } else if(textTelefono.getText().length() != 9){
            textError.setText("El campo 'Teléfono' debe contener 9 caracteres");
        //Comprueba que el campo correo no esté vacío
        } else if(textCorreo.getText().length() == 0){
            textError.setText("No puedes dejar campo 'Correo electrónico' vacío"); 
        //Comprueba que el correo no esté registrado
        } else if(existeCorreo){
            textError.setText("Este correo ya está registrado, pruebe uno distinto por favor");
        //Comprueba que textPassword tenga almenos 6 números
        } else if(textContrasena.getText().length() < 6){
            textError.setText("El campo 'Contraseña' debe contener almenos 6 caracteres");
        } else if(!tieneImagen) {
            textError.setText("No ha seleccionado una imagen de perfil");
        } else if(!textContrasena.getText().equals(textRepetirContrasena.getText())){
            textError.setText("Las contraseñas no son iguales, revíselo por favor");
        
        
            
        //Limpia el campo textError y salta confirmación
        } else{
            textError.setText("");

            // GUARDAR DATOS
            // GUARDAR DATOS
            //Probamos a guardar
            
            Path from= Paths.get(imagePath);
            String localDir = System.getProperty("user.dir");
            //String extensionImagen = imagePath.substring(imagePath.length()-4);
            Path to = Paths.get(localDir + "/src/imagenesPerfil/" + textCorreo.getText() + ".png");
            String rutaImagen = "/src/imagenesPerfil/" + textCorreo.getText() + ".png";
 
            //Reemplazamos el fichero si ya existe
            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };
            try {
                Files.copy(from, to, options );
            } catch (IOException ex) {
                System.out.println("Error guardando la imagen");
            }

            DAL DAL = new DAL();
            DAL.addCuenta(Integer.parseInt(textTelefono.getText()), textCorreo.getText(), textNombre.getText(), textApellidos.getText(), tipoCuenta, textContrasena.getText(), rutaImagen);

            if(tipoCuenta.equals("Tecnico"))
            {
                Cuenta cuentaAgencia = DAL.login(LoginController.cuentaLoged.getEmail(), DAL.getContrasena(LoginController.cuentaLoged.getId_cuenta()));
                System.out.println(cuentaAgencia.getTipoCuenta() + cuentaAgencia.getEmail());
                Cuenta cuenta = DAL.login(textCorreo.getText(), textContrasena.getText());
                System.out.println("Entra en el if para ADD TECNICO");
                DAL.addTecnico(cuenta,cuentaAgencia);
                Alert a = new Alert(Alert.AlertType.INFORMATION,"Se ha añadido correctamente el técnico a tu agencia",ButtonType.OK);
                    a.setTitle("Técnico añadido");
                    a.showAndWait();
                registroStage.hide();
                //Abris stage de inicio 
                
            }else
            {
                // Selecciona Aceptar
                FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/LoginFXML.fxml"));
                 Parent root = (Parent) myLoader.load();
        
                //Access to login controller and init login
                LoginController LoginController = myLoader.<LoginController>getController();
        
                Stage loginStage = new Stage();
                LoginController.initLogin(loginStage);
        
                //Show win2
                Scene scene = new Scene(root);
                //Añadir css
                //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
                //scene.getStylesheets().add(css);
        
                loginStage.setScene(scene);
                loginStage.setTitle("Login");
                loginStage.initModality(Modality.APPLICATION_MODAL);
                loginStage.show();
                registroStage.hide();
            }
            
        }
    }

    @FXML
    private void clickEscogerImagen (ActionEvent event) throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(

                new FileChooser.ExtensionFilter("ALL", "*.jpg", "*.png", "*.jpeg")

        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(registroStage);

        // Mostar la imagen
        if (imgFile != null) {
            Image image = new Image("file:" + imgFile.getAbsolutePath());

            imagePerfil.setImage(image);
            imagePath = imgFile.getAbsolutePath();
            tieneImagen = true;
    
        }
    }

    @FXML
    private void clickIniciarSesion(MouseEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/LoginFXML.fxml"));
            Parent root = (Parent) myLoader.load();
            LoginController LoginController = myLoader.<LoginController>getController();

            Stage loginStage = new Stage();
            LoginController.initLogin(loginStage);
            loginStage.getIcons().add(new Image("/recursos/logoFinal.png"));
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
            registroStage.hide();
    }

    @FXML
    private void clickProfesional(MouseEvent event) throws IOException {
        
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/RegistroAgenciaFXML.fxml"));
            Parent root = (Parent) myLoader.load();
            RegistroAgenciaController RegistroAgenciaController = myLoader.<RegistroAgenciaController>getController();

            Stage registroAgenciaStage = new Stage();
            RegistroAgenciaController.initRegistro(registroAgenciaStage);
            Scene scene = new Scene(root);
            registroAgenciaStage.getIcons().add(new Image("/recursos/logoFinal.png"));

            registroAgenciaStage.setScene(scene);
            registroAgenciaStage.setTitle("Registrarse");
            registroAgenciaStage.initModality(Modality.APPLICATION_MODAL);
            registroAgenciaStage.show();
            registroStage.hide();
        
    }

    @FXML
    private void clickVolver(ActionEvent event) throws IOException {
        inicioStage.show();
        registroStage.close();
    }

    @FXML
    private void typedNombre(KeyEvent event) {
        //Solo letras
        String input = event.getCharacter();
        String valido = "qwertyuiopasdfghjklñzxcvbnmçQWERTYUIOPASDFGHJKLÑZXCVBNMÇ ";
            if(!valido.contains(input)){
                event.consume();
            }
    }

    @FXML
    private void typedApellidos(KeyEvent event) {
        //Solo letras
        String input = event.getCharacter();
        String valido = "qwertyuiopasdfghjklñzxcvbnmçQWERTYUIOPASDFGHJKLÑZXCVBNMÇ ";
            if(!valido.contains(input)){
                event.consume();
            }
    }

    @FXML
    private void typedTelefono(KeyEvent event) {
        String input = event.getCharacter();
        String valido = "1234567890" ;
        if(!valido.contains(input)){
            event.consume();
        }
        
        
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
    

    
}
