/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ernes
 */
public class SugerirPrecioController implements Initializable {

  
    
    private Double prAlto;
    private Double prBajo;
    
    public static Stage sugerirPrecioControllerStage;
    @FXML
    private Label texto;

    public void initSugerirPrecioController(Stage stage){
        sugerirPrecioControllerStage = stage;     
    }
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prAlto = AnadirInmuebleController.porcentajeAlto;
        prBajo = AnadirInmuebleController.porcentajeBajo;
        texto.setText("que oscila entre " + prBajo.toString()+ " y " + prAlto.toString()+ " €.");
       
    }    
    
}
