/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import basededatos.DAL;
import static controlador.InicioController.inicioStage;
import static controlador.RegistroController.registroStage;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David
 */
public class LoginController implements Initializable {

    @FXML
    private TextField textCorreo;
    @FXML
    private PasswordField textContrasena;
    
    public static Stage loginStage;
    
    public static boolean isLoged = false;
    public static Cuenta cuentaLoged = new Cuenta();

    public static void initLogin(Stage stage){
        loginStage = stage;
    
    }
    @FXML
    private Label textError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickRegistro(MouseEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/RegistroFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        RegistroController RegistroController = myLoader.<RegistroController>getController();

        Stage registroStage = new Stage();
        RegistroController.initRegistro(registroStage);
        registroStage.getIcons().add(new Image("/recursos/logoFinal.png"));

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
        loginStage.hide();
    }

    @FXML
    private void clickIniciarSesion(ActionEvent event) throws IOException {
        String correoEscrito = textCorreo.getText();
        String constrasenaEscrita = textContrasena.getText();
        try{
            DAL DAL = new DAL();
            boolean res = DAL.existsEmail(correoEscrito);
            //Si el correo no existe
            if(res == false){
                textError.setText("El correo introducido no está registrado, regístrelo o compruebe que lo ha escrito correctamente.");
            //Si el correo si existe
            } else {
                Cuenta cuenta = new Cuenta();
                cuenta = DAL.login(correoEscrito, constrasenaEscrita);
                if (cuenta == null){
                    textError.setText("El correo y la contraseña no coinciden, inténtelo de nuevo por favor.");
                    
                } else {
                    //Loguearse
                    
                   
                    isLoged = true;
                    cuentaLoged = cuenta;
                    inicioStage.close();
                    mostrarInicio();
                    loginStage.close();
                }
            }
            
        } catch(SQLException e){}
    }

    @FXML
    private void clickVolver(ActionEvent event) {
        inicioStage.show();
        
        loginStage.close();
    }
    
    private void mostrarInicio() throws IOException, SQLException{
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/InicioFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        InicioController InicioController = myLoader.<InicioController>getController();

        Stage inicioStage = new Stage();
        InicioController.initInicio(inicioStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //Añadir css
        //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        //we asign new scene to current stage/window
        inicioStage.setScene(scene);
        inicioStage.setTitle("Inicio");
        inicioStage.initModality(Modality.APPLICATION_MODAL);
        inicioStage.show();
        actualizarCuenta();
        
    }
    
    public static void actualizarCuenta() throws SQLException{
        DAL DAL = new DAL();
        cuentaLoged = DAL.getCuentaById(cuentaLoged.getId_cuenta());
    }
    
}
