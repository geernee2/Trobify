/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import basededatos.DAL;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author vicen
 */
public class SolicitarVisitaController implements Initializable {

    public Stage solicitarVisitaStage;

    public void initSolicitarVisita(Stage stage)
    {
        solicitarVisitaStage = stage;

    }
    
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonAceptar;
    @FXML
    private DatePicker datePicker;
    
    private DAL DAL = new DAL();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    

    @FXML
    private void ActionCancelar(ActionEvent event) {
        solicitarVisitaStage.close();
    }

    @FXML
    private void ActionAceptar(ActionEvent event) throws SQLException {
        
        if(!(datePicker.getValue().equals(null) || datePicker.getValue().isBefore(LocalDate.now())))
        {
            String mensaje = LoginController.cuentaLoged.getNombre() + " quiere una visita para el inmueble: " + ResultadosController.inmuebleSeleccionado.getNombre() + " en la fecha " + datePicker.getValue();
            //DAL.addSolicitudVisita(LoginController.cuentaLoged.getId_cuenta(),ResultadosController.inmuebleSeleccionado.getId_inm(),datePicker.getValue());
            DAL.addNotificacion(LoginController.cuentaLoged.getId_cuenta(),ResultadosController.inmuebleSeleccionado.getId_cuenta() , ResultadosController.inmuebleSeleccionado.getId_inm(), datePicker.getValue(), "visita", mensaje);
            solicitarVisitaStage.close();
        }else
        {
            Alert a = new Alert(Alert.AlertType.ERROR,"Fecha incorrecta",ButtonType.OK);
            a.setTitle("Error");
            a.show();
        }
    }
    
}
