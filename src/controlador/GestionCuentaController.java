/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import basededatos.DAL;
import static controlador.LoginController.loginStage;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class GestionCuentaController implements Initializable {

    @FXML
    private Button clickFavoritos;
    @FXML
    private Button clickGestionarInmuebles;
    @FXML
    private ImageView imagePerfil;
    @FXML
    private TextField textContrasenaVieja;
    @FXML
    private TextField textContrasenaNueva;
    @FXML
    private TextField textContrasenaNuevaCorrecta;
    @FXML
    private TextField textCorreoNuevo;
    @FXML
    private TextField textTelefonoNuevo;
    
    public static Stage gestionCuentaStage;
    
    public static void initGestionCuenta(Stage stage){
        gestionCuentaStage = stage;
    
    }
    @FXML
    private Label labelError;
    @FXML
    private Button buttonVolver;
    @FXML
    private Button buttonCambiarImagen;
    @FXML
    private Button buttonSimularHipoteca;
    @FXML
    private Button buttonMensajes;
    @FXML
    private Button buttonNotificaciones;
    @FXML
    private Button buttonRegistroTecnicos;
    @FXML
    private Button buttonMisTecnicos;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        String localDir = System.getProperty("user.dir");
        String pathImagen = localDir + LoginController.cuentaLoged.getImagenPerfil();
        Image imgPerfil = new Image("file:" + pathImagen); 
        
        imagePerfil.setImage(imgPerfil);
        
        if(LoginController.cuentaLoged.getTipoCuenta().equals("Agencia"))
        {
            buttonMisTecnicos.setVisible(true);
            buttonRegistroTecnicos.setVisible(true);
        }else
        {
            buttonMisTecnicos.setVisible(false);
            buttonRegistroTecnicos.setVisible(false);
        }
    }    

    @FXML
    private void clickCambiarContraseña(ActionEvent event) {
        try
        {
            labelError.setText("");
            DAL dal = new DAL();
            String test = dal.getContrasena(LoginController.cuentaLoged.getId_cuenta());
            if(!textContrasenaVieja.getText().equals(test))
            {
                throw new Exception("La antigua contraseña no es correcta");
            }
            if(textContrasenaNueva.getText().length() < 6)
            {
                throw new Exception("El campo 'Contraseña' debe contener almenos 6 caracteres");
            }
            if(!textContrasenaNueva.getText().equals(textContrasenaNuevaCorrecta.getText()))
            {
                throw new Exception("La contraseña nueva no coincide en ambos campos");
            }
            
            dal.updateProfile(LoginController.cuentaLoged.getId_cuenta(), LoginController.cuentaLoged.getTelefono(), LoginController.cuentaLoged.getEmail(), LoginController.cuentaLoged.getNombre(), textContrasenaNueva.getText());
            
        }catch(Exception e){labelError.setText(e.getMessage());}
    }

    @FXML
    private void clickCambiarCorreo(ActionEvent event) throws SQLException, IOException {
        
        labelError.setText("");
        DAL dal = new DAL();
        String contrasena = dal.getContrasena(LoginController.cuentaLoged.getId_cuenta());
        if(textCorreoNuevo.getText().length() == 0)
        {
            labelError.setText("No puedes dejar campo 'Correo electrónico' vacío");
        }
        else
        {
            String correoActualizado = textCorreoNuevo.getText();
            
            //Cambia el nombre de la imagen
            String localDir = System.getProperty("user.dir");
            String viejaImagen = localDir + LoginController.cuentaLoged.getImagenPerfil();
            String nuevaImagen = localDir + "/src/imagenesPerfil/" + textCorreoNuevo.getText()+ ".png";
            
            Path from= Paths.get(viejaImagen);
            System.out.println("");
            Path to = Paths.get(nuevaImagen);
            //Reemplazamos el fichero si ya existe
            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
            };

                Files.copy(from, to, options );


            String pathImagen = localDir + LoginController.cuentaLoged.getImagenPerfil();
            Path eliminar=Paths.get(pathImagen);
            if(Files.exists(eliminar)){
                Files.delete(eliminar);
            }
            
            
                String rename = "/src/imagenesPerfil/" + correoActualizado+ ".png";
                dal.updateProfileImage(LoginController.cuentaLoged.getId_cuenta(), rename);
                dal.updateProfile(LoginController.cuentaLoged.getId_cuenta(), LoginController.cuentaLoged.getTelefono(), correoActualizado, LoginController.cuentaLoged.getNombre(), contrasena);
                LoginController.cuentaLoged = dal.login(correoActualizado, contrasena);
             

        }
    }

    @FXML
    private void clickCambiarTelefono(ActionEvent event) throws SQLException {
        
        labelError.setText("");
        DAL dal = new DAL();
        String contrasena = dal.getContrasena(LoginController.cuentaLoged.getId_cuenta());
        if(textTelefonoNuevo.getText().length() != 9)
        {
            labelError.setText("El telefono debe tener 9 dígitos");
        }
        else
        {
            dal.updateProfile(LoginController.cuentaLoged.getId_cuenta(), Integer.parseInt(textTelefonoNuevo.getText()), LoginController.cuentaLoged.getEmail(), LoginController.cuentaLoged.getNombre(), contrasena);
        }
    }

    @FXML
    private void typedTelefono(KeyEvent event) 
    {
        String input = event.getCharacter();
        String valido = "1234567890" ;
        if(!valido.contains(input)){
            event.consume();
        }
    }

    @FXML
    private void clickFavoritos(ActionEvent event) throws IOException, SQLException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/FavoritosFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        FavoritosController FavoritosController = myLoader.<FavoritosController>getController();

        Stage favoritosStage = new Stage();
        favoritosStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        FavoritosController.initFavoritos(favoritosStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //Añadir css
        //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        //we asign new scene to current stage/window
        favoritosStage.setScene(scene);
        favoritosStage.setTitle("Mis Favoritos");
        favoritosStage.initModality(Modality.APPLICATION_MODAL);
        favoritosStage.show();
        gestionCuentaStage.hide();
        LoginController.actualizarCuenta();
    }

    private void clickAgenda(ActionEvent event) throws SQLException {
        LoginController.actualizarCuenta();
    }

    @FXML
    private void clickMisInmuebles(ActionEvent event) throws IOException, SQLException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/MisInmueblesFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        MisInmueblesController MisInmueblesController = myLoader.<MisInmueblesController>getController();

        Stage misInmueblesStage = new Stage();
        misInmueblesStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        MisInmueblesController.initMisInmuebles(misInmueblesStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //Añadir css
        //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        //we asign new scene to current stage/window
        misInmueblesStage.setScene(scene);
        misInmueblesStage.setTitle("Mis Inmuebles");
        misInmueblesStage.initModality(Modality.APPLICATION_MODAL);
        misInmueblesStage.show();
        gestionCuentaStage.hide();
        LoginController.actualizarCuenta();
    }

    @FXML
    private void clickVolver(ActionEvent event) throws SQLException {
        gestionCuentaStage.close();
        InicioController.inicioStage.show();
        LoginController.actualizarCuenta();
    }

    @FXML
    private void clickCambiarImagen(ActionEvent event) throws IOException, SQLException {
        String localDir = System.getProperty("user.dir");
        
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(

                new FileChooser.ExtensionFilter( "ALL", "*.jpg", "*.png")


        );

        // Obtener la imagen seleccionada
        File imgFile = fileChooser.showOpenDialog(registroStage);

        // Mostar la imagen
        if (imgFile != null) {
            String pathImagen = localDir + LoginController.cuentaLoged.getImagenPerfil();
            Path eliminar=Paths.get(pathImagen);
            if(Files.exists(eliminar)){
                Files.delete(eliminar);
            }
            
            Image image = new Image("file:" + imgFile.getAbsolutePath());

            imagePerfil.setImage(image);
            String imagePath = imgFile.getAbsolutePath();
            
            
            Path from= Paths.get(imagePath);
            
            Path to = Paths.get(localDir + "/src/imagenesPerfil/" + LoginController.cuentaLoged.getEmail() + ".png");
            String rutaImagen = "/src/imagenesPerfil/" + LoginController.cuentaLoged.getEmail() + ".png";
            
            DAL DAL = new DAL();
            DAL.updateProfileImage(LoginController.cuentaLoged.getId_cuenta(), rutaImagen);
 
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
        }

    }
    
    public void cerrarVentana(){
        gestionCuentaStage.close();
    }

    @FXML
    private void clickHipoteca(ActionEvent event) throws IOException, SQLException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/SimuladorHipotecaFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        SimuladorHipotecaController SimuladorHipotecaController = myLoader.<SimuladorHipotecaController>getController();

        Stage simuladorHipotecaStage = new Stage();
        simuladorHipotecaStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        SimuladorHipotecaController.initSimuladorHipotecaController(simuladorHipotecaStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //Añadir css
        //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        //we asign new scene to current stage/window
        simuladorHipotecaStage.setScene(scene);
        simuladorHipotecaStage.setTitle("Simulador de Hipotecas");
        simuladorHipotecaStage.initModality(Modality.APPLICATION_MODAL);
        simuladorHipotecaStage.show();
    }

    @FXML
    private void clickMensajes(ActionEvent event) throws IOException, SQLException {
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
        mensajeriaStage.setTitle("Mis Mensajes");
        mensajeriaStage.initModality(Modality.APPLICATION_MODAL);
        mensajeriaStage.show();
    }

    @FXML
    private void clickNotificaciones(ActionEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/NotificacionesFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        NotificacionesController NotificacionesController = myLoader.<NotificacionesController>getController();

        Stage notificacionesStage = new Stage();
        notificacionesStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        NotificacionesController.initNotificaciones(notificacionesStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //Añadir css
        //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        //we asign new scene to current stage/window
        notificacionesStage.setScene(scene);
        notificacionesStage.setTitle("Mis notificaciones");
        notificacionesStage.initModality(Modality.APPLICATION_MODAL);
        notificacionesStage.show();
        //resultadosStage.hide();
    }

    @FXML
    private void clickRegistroTecnicos(ActionEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/RegistroFXML.fxml"));
            Parent root = (Parent) myLoader.load();
            RegistroController RegistroController = myLoader.<RegistroController>getController();

            Stage registroStage = new Stage();
            registroStage.getIcons().add(new Image("/recursos/logoFinal.png"));
            RegistroController.initRegistro(registroStage);
            Scene scene = new Scene(root);

            registroStage.setScene(scene);
            registroStage.setTitle("Registrar Tecnico");
            registroStage.initModality(Modality.APPLICATION_MODAL);
            registroStage.show();
            RegistroController.setTipoCuenta("Tecnico");
            //registroAgenciaStage.hide();
    }

    @FXML
    private void clickMisTecnicos(ActionEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/MisTecnicosFXML.fxml"));
            Parent root = (Parent) myLoader.load();
            MisTecnicosController MisTecnicosController = myLoader.<MisTecnicosController>getController();

            Stage misTecnicosStage = new Stage();
            misTecnicosStage.getIcons().add(new Image("/recursos/logoFinal.png"));
            MisTecnicosController.initMisTecnicos(misTecnicosStage);
            Scene scene = new Scene(root);

            misTecnicosStage.setScene(scene);
            misTecnicosStage.setTitle("Mis Tecnicos");
            misTecnicosStage.initModality(Modality.APPLICATION_MODAL);
            misTecnicosStage.show();
    }

}
