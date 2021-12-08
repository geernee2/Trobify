package controlador;


import basededatos.DAL;
import static controlador.InicioController.inicioStage;
import static controlador.RegistroAgenciaController.registroAgenciaStage;
import static controlador.RegistroController.registroStage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class RegistroAgenciaController implements Initializable {

    @FXML
    private TextField textNombre;
    @FXML
    private TextField textCuentaBancaria;
    @FXML
    private TextField textCorreo;
    @FXML
    private TextField textPagWeb;
    @FXML
    private TextField textTelefono;
    @FXML
    private PasswordField textContrasena;
    @FXML
    private PasswordField textRepetirContrasena;
    @FXML
    private Label textError;
    @FXML
    private ImageView imagePerfil;
    @FXML
    private Label labelLogin;
    @FXML
    private Label labelParticular;
    
    private DAL DAL = new DAL();
    
    private String imagePath;
    
    private boolean tieneImagen = false;
    
    public static Stage registroAgenciaStage;
    
    

    public static void initRegistro(Stage stage){
        registroAgenciaStage = stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void typedNombre(KeyEvent event) {
        String input = event.getCharacter();
        String valido = "qwertyuiopasdfghjklñzxcvbnmçQWERTYUIOPASDFGHJKLÑZXCVBNMÇ ";
            if(!valido.contains(input)){
                event.consume();
            }
    }

    @FXML
    private void typedApellidos(KeyEvent event) {
    }

    @FXML
    private void typedTelefono(KeyEvent event) {
        String input = event.getCharacter();
        String valido = "1234567890" ;
        if(!valido.contains(input)){
            event.consume();
        }
    }

    @FXML
    private void clickEscogerImagen(ActionEvent event) {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(

                new FileChooser.ExtensionFilter("ALL", "*.jpg", "*.png", "*.jpeg")

        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(registroAgenciaStage);

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


            Scene scene = new Scene(root);

            loginStage.setScene(scene);
            loginStage.setTitle("Iniciar sesión");
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.show();
            registroAgenciaStage.hide();
        
    }


    @FXML
    private void clickVolver(ActionEvent event) {
        inicioStage.show();
        registroAgenciaStage.close();
    }

    @FXML
    private void clickParticular(MouseEvent event) throws IOException {
                
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/RegistroFXML.fxml"));
            Parent root = (Parent) myLoader.load();
            RegistroController RegistroController = myLoader.<RegistroController>getController();

            Stage registroStage = new Stage();
            RegistroController.initRegistro(registroStage);
            Scene scene = new Scene(root);
            registroStage.getIcons().add(new Image("/recursos/logoFinal.png"));


            registroStage.setScene(scene);
            registroStage.setTitle("Registrarse");
            registroStage.initModality(Modality.APPLICATION_MODAL);
            registroStage.show();
            registroAgenciaStage.hide();
        
    }
    
        @FXML
    private void clickRegistrarse(ActionEvent event) throws SQLException, IOException {
       
        boolean existeCorreo = DAL.existsEmail(textCorreo.getText());
        
        if(textNombre.getText().length() == 0){
            textError.setText("No puedes dejar campo 'Nombre' vacío");
        } else if(textTelefono.getText().length() != 9){
            textError.setText("El campo 'Teléfono' debe contener 9 caracteres");
        } else if(textCorreo.getText().length() == 0){
            textError.setText("No puede dejar campo 'Correo electrónico' vacío"); 
        } else if(existeCorreo){
            textError.setText("Este correo ya está registrado, pruebe uno distinto por favor");
        } else if(textContrasena.getText().length() < 6){
            textError.setText("El campo 'Contraseña' debe contener almenos 6 caracteres");
        } else if(!tieneImagen) {
            textError.setText("No ha seleccionado una imagen de perfil");
        } else if(!textContrasena.getText().equals(textRepetirContrasena.getText())){
            textError.setText("Las contraseñas no son iguales, revíselo por favor");
        }else if(textPagWeb.getText().length() == 0){
            textError.setText("No puede dejar campo 'Página Web' vacío");
        } else{
            textError.setText("");  
            
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/LoginFXML.fxml"));
            Parent root = (Parent) myLoader.load();
        
            LoginController LoginController = myLoader.<LoginController>getController();
        
            Stage loginStage = new Stage();
            LoginController.initLogin(loginStage);
            loginStage.getIcons().add(new Image("/recursos/logoFinal.png"));

        
            Scene scene = new Scene(root);
        
            loginStage.setScene(scene);
            loginStage.setTitle("Login");
            loginStage.initModality(Modality.APPLICATION_MODAL);
            loginStage.show();
            registroAgenciaStage.hide();
            
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

            DAL.addCuentaAgencia(Integer.parseInt(textTelefono.getText()), textCorreo.getText(), textNombre.getText(), "Agencia", textContrasena.getText(), rutaImagen, textPagWeb.getText(),textCuentaBancaria.getText());

        }
    }

}
