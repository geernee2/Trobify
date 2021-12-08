/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author David
 */
public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/InicioFXML.fxml"));
        
        Parent root = myLoader.load();
        
        InicioController mainWinController = myLoader.<InicioController>getController();
        mainWinController.initInicio(stage);
        
        Scene scene = new Scene(root);
        
        stage.getIcons().add(new Image("/recursos/logoFinal.png"));
        
        String css= this.getClass().getResource("/css/inicio.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        stage.setTitle("Bienvenido a Trobify");
        stage.setScene(scene);
        stage.show();
        
        /*FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/AnadirInmuebleFXML.fxml"));
        
        Parent root = myLoader.load();
        
        AnadirInmuebleController mainWinController = myLoader.<AnadirInmuebleController>getController();
        mainWinController.initAnadirInmueble(stage);
        
        Scene scene = new Scene(root);
        
        String css= this.getClass().getResource("/css/anadirinmueble.css").toExternalForm();
        scene.getStylesheets().add(css);
        
        stage.setTitle("Bienvenido a Trobify");
        stage.setScene(scene);
        stage.show();*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }
    
}
