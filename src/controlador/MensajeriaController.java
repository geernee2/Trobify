/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import basededatos.DAL;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.application.Application; 
import javafx.collections.ObservableList; 
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets; 
import javafx.scene.Scene; 
import javafx.scene.control.Button; 
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage; 
import javafx.scene.layout.VBox; 
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author Edgar
 */
public class MensajeriaController implements Initializable {

    @FXML
    private VBox contactBox;
    public static Stage mensajeriaStage;
    
    public static void initMensajeriaController(Stage stage){
        mensajeriaStage = stage;     
    }
    @FXML
    private ImageView userImage;
    @FXML
    private ImageView imagenConver;
    @FXML
    private Text inmuebleConver;
    @FXML
    private Text nombreConver;
    @FXML
    private AnchorPane contChat;
    @FXML
    private TextArea campoMensaje;
    
    int yBuff = 30;
    int id_cuenta_contacto = 0;
    Inmueble imb;
    
    public void initialize(URL url, ResourceBundle rb) {
        
      String localDir = System.getProperty("user.dir");
      String pathImagen = localDir + LoginController.cuentaLoged.getImagenPerfil();
      userImage.setImage(new Image("file:" + pathImagen));
      userImage.setFitWidth(75);
      userImage.setFitHeight(75);
      userImage.setPreserveRatio(false);
      userImage.setSmooth(true);
      userImage.setCache(true);
        // TODO
        VBox vBox = new VBox();   
      
      //Setting the space between the nodes of a VBox pane 
      vBox.setSpacing(10);   
      AnchorPane contenedor;
      ImageView imagenView;
      Image imagen;
      Text nombre;
      final int id = 0;
      
      DAL dal = new DAL();
        List<Integer> res = new ArrayList<Integer>();
        Cuenta contacto;
        
      try{
            res = dal.getMensajeriaById(LoginController.cuentaLoged.getId_cuenta());
            for(int i = 0; i < res.size(); i++){
                contacto = dal.getCuentaById(res.get(i));
                contenedor = new AnchorPane();
                contenedor.setId("" + contacto.getId_cuenta());
                imagenView = new ImageView();
                nombre = new Text();
                System.out.println("file:" + localDir + contacto.getImagenPerfil());
                imagen = new Image("file:" + localDir + contacto.getImagenPerfil()); 
                imagenView.setImage(imagen);
                imagenView.setFitWidth(60);
                imagenView.setFitHeight(60);
                imagenView.setPreserveRatio(false);
                imagenView.setSmooth(true);
                imagenView.setCache(true);
                imagenView.setLayoutX(14);
                imagenView.setLayoutY(14);
                nombre.setText(contacto.getNombre() + " " + contacto.getApellidos());
                nombre.setStyle("-fx-font-size: 15");
                nombre.setLayoutX(100);
                nombre.setLayoutY(50);
                contenedor.setOnMouseClicked(display(contacto));
                contenedor.getChildren().addAll(imagenView, nombre);
                contactBox.getChildren().addAll(contenedor);
            }
            
        }catch(SQLException e){System.out.println(e);}
      
      
    }   

    final EventHandler<MouseEvent>  display(Cuenta contacto) {
        
        return new EventHandler<MouseEvent>(){

          @Override
          public void handle(MouseEvent arg0) {
              imagenConver.setImage(new Image("file:" + System.getProperty("user.dir") + contacto.getImagenPerfil()));
              nombreConver.setText(contacto.getNombre() + " " + contacto.getApellidos());
              id_cuenta_contacto = contacto.getId_cuenta();
              DAL dal = new DAL();
              try {
                  
                  imb = dal.getInmuebleByMensajes(contacto.getId_cuenta(), LoginController.cuentaLoged.getId_cuenta());
                  inmuebleConver.setText(imb.getNombre());
              } catch (SQLException ex) {
                  Logger.getLogger(MensajeriaController.class.getName()).log(Level.SEVERE, null, ex);
              }
              getMensajes(contacto.getId_cuenta(), LoginController.cuentaLoged.getId_cuenta());
          }

      };
    }
    
    public void getMensajes(int cuenta_contacto, int cuenta_propietario){
        contChat.getChildren().removeAll(contChat.getChildren());
        yBuff= 0;
        Text tIzq;
        Text tDer;
        int layXIzq = 15;
        int layYIzq = 15;
        int layXDer = 280;
        int layYDer = 15;
        List<Mensaje> listaM = new ArrayList<Mensaje>();
        Mensaje M;
        DAL dal = new DAL();
              try {
                  listaM = dal.getMensajesByIds(cuenta_contacto, cuenta_propietario);
              } catch (SQLException ex) {
                  Logger.getLogger(MensajeriaController.class.getName()).log(Level.SEVERE, null, ex);
              } 
        for(int i = 0; i<listaM.size(); i++){
            M = listaM.get(i);
            if(M.getId_cuenta_origen()==cuenta_propietario){
                tDer = new Text();
                tDer.setText(M.getContenido());
                tDer.setLayoutX(layXDer);
                tDer.setLayoutY(layYDer + yBuff);
                tDer.setStyle("-fx-background-color: grey");
                addMesage(tDer);
                yBuff += 50;
            }else{
                tIzq = new Text();
                tIzq.setText(M.getContenido());
                tIzq.setLayoutX(layXIzq);
                tIzq.setLayoutY(layYIzq + yBuff);
                tIzq.setStyle("-fx-background-color: green");
                addMesage(tIzq);
                yBuff += 50;
            }
        }
    }
        
    public void addMesage(Text t){
        contChat.getChildren().add(t);
        
    }

    @FXML
    private void añadirMensaje(ActionEvent event) {
        if(0!=campoMensaje.getText().compareTo("")){
            try{
            Text t = new Text();
            t.setText(campoMensaje.getText());
            campoMensaje.setText("");
            t.setLayoutX(280);
            t.setLayoutY(15 + yBuff);
            addMesage(t);
            yBuff += 50;
            DAL dal = new DAL();
            String notificacion = LoginController.cuentaLoged.getNombre() + " te ha enviado un mensaje.";
            
            dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), id_cuenta_contacto, imb.getId_inm(), LocalDate.now(), "mensaje", notificacion);
            dal.enviarMensaje(LoginController.cuentaLoged.getId_cuenta(), id_cuenta_contacto, imb.getId_inm(), t.getText());
            
            addMesage(t);
            
            }catch(Exception e){System.out.println("e: " + e);}
        }
    }
    
}
