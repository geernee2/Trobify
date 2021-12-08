/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David
 */
public class EscogerInmuebleAnadirController implements Initializable {

    @FXML
    private ChoiceBox<String> chooserTipoInmueble;
    public static Stage escogerTipoInmuebleStage;
    public static String tipoInmueble;
    
    public void initEscogerInmuebleAnadir(Stage stage){
        escogerTipoInmuebleStage = stage;     
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    chooserTipoInmueble.getItems().addAll("Vivienda", "Habitacion","Garaje","Oficina");
    chooserTipoInmueble.getSelectionModel().select("Vivienda");
    chooserTipoInmueble.setValue("Vivienda");
        
    }    

    @FXML
    private void clickElegirTipoInmueble(MouseEvent event) {
        
    }

    @FXML
    private void clickConfirmar(ActionEvent event) throws IOException {
        if(chooserTipoInmueble.getValue()==null){
            tipoInmueble = "Vivienda";
        } else {
            tipoInmueble = chooserTipoInmueble.getValue();
        }
        mostrarAnadirInmueble();
    }
    
    private void mostrarAnadirInmueble() throws IOException{
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/AnadirInmuebleFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        AnadirInmuebleController AnadirInmuebleController = myLoader.<AnadirInmuebleController>getController();

        Stage anadirInmuebleStage = new Stage();
        anadirInmuebleStage.getIcons().add(new Image("/recursos/logoFinal.png"));
        AnadirInmuebleController.initAnadirInmueble(anadirInmuebleStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //Añadir css
        //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        //we asign new scene to current stage/window
        anadirInmuebleStage.setScene(scene);
        anadirInmuebleStage.setTitle("Añadir Inmueble");
        anadirInmuebleStage.initModality(Modality.APPLICATION_MODAL);
        anadirInmuebleStage.show();
        
        escogerTipoInmuebleStage.close();
        MisInmueblesController.misInmueblesStage.close();
    }
    
}
