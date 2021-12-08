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
import javafx.stage.Stage;
import basededatos.DAL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.TouchEvent;
import controlador.LoginController;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author David
 */
public class MarcarFavoritoController implements Initializable {

    @FXML
    private ChoiceBox<String> choiceGrupo;
    @FXML
    private TextField textNuevoGrupo;
    @FXML
    private Button buttonCrear;
    @FXML
    private Button buttonAnadir;
    
    private String grupo;
    
    public static Stage marcarFavoritoStage;
    
    public DAL dal = new DAL();

    public static void initMarcarFavorito(Stage stage){
        marcarFavoritoStage = stage;
        marcarFavoritoStage.getIcons().add(new Image("/recursos/logoFinal.png"));
    }
    @FXML
    private Text labelCrearNuevo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            
            //choiceGrupo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>());
            //DAL dal = new DAL();
            List<String> gruposFavs = new ArrayList<String>();
            gruposFavs = dal.getListGruposFavs(LoginController.cuentaLoged.getId_cuenta());
            choiceGrupo.getItems().add("Nuevo");
            for(String x: gruposFavs){choiceGrupo.getItems().add(x);}

            labelCrearNuevo.setVisible(false); 
            textNuevoGrupo.setVisible(false); 
            buttonCrear.setVisible(false);
            
            choiceGrupo.getSelectionModel().selectedIndexProperty().addListener(
            (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            if(new_val.intValue() == 0)
            {
                labelCrearNuevo.setVisible(true); 
                textNuevoGrupo.setVisible(true); 
                buttonCrear.setVisible(true); 
                buttonAnadir.setVisible(false);
            }
            if(new_val.intValue() != 0)
            {
                System.out.println("Observable mirando " + new_val.intValue()); 
                labelCrearNuevo.setVisible(false); 
                textNuevoGrupo.setVisible(false); 
                buttonCrear.setVisible(false);
                buttonAnadir.setVisible(true);
            }
            });
            
            
          
        }catch(SQLException e){}
    }    

    @FXML
    private void clickCrear(ActionEvent event) throws SQLException {
        if(choiceGrupo.getValue().equals("Nuevo")){
            if(!textNuevoGrupo.getText().equals("Nuevo")){
                 dal.addInmuebleToFavoritos(ResultadosController.inmuebleSeleccionado.getId_inm(),LoginController.cuentaLoged.getId_cuenta(),textNuevoGrupo.getText());
                 marcarFavoritoStage.close();
            }else{System.out.println("Nuevo no esta permitido como nombre porque da error");}
        }
    }

    @FXML
    private void clickAnadir(ActionEvent event) throws SQLException {
         if(!choiceGrupo.getValue().equals("Nuevo"))
         {
             dal.addInmuebleToFavoritos(ResultadosController.inmuebleSeleccionado.getId_inm(),LoginController.cuentaLoged.getId_cuenta(),choiceGrupo.getValue());
             marcarFavoritoStage.close();
         }
    }


    
}
