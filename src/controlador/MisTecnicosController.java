package controlador;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import basededatos.DAL;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;




public class MisTecnicosController implements Initializable {

    @FXML
    private AnchorPane paneNotificacion0;
    @FXML
    private Label labelMensaje0;
    @FXML
    private ImageView imageDenegar0;
    @FXML
    private Label labelEscoger0;
    @FXML
    private AnchorPane paneNotificacion1;
    @FXML
    private Label labelMensaje1;
    @FXML
    private ImageView imageDenegar1;
    @FXML
    private Label labelEscoger1;
    @FXML
    private AnchorPane paneNotificacion2;
    @FXML
    private Label labelMensaje2;
    @FXML
    private ImageView imageDenegar2;
    @FXML
    private Label labelEscoger2;
    @FXML
    private AnchorPane paneNotificacion3;
    @FXML
    private Label labelMensaje3;
    @FXML
    private ImageView imageDenegar3;
    @FXML
    private Label labelEscoger3;
    @FXML
    private AnchorPane paneNotificacion4;
    @FXML
    private Label labelMensaje4;
    @FXML
    private ImageView imageDenegar4;
    @FXML
    private Label labelEscoger4;
    @FXML
    private Button buttonAnterior;
    @FXML
    private Button buttonSiguiente;
    
    public static Stage misTecnicosStage;

    public static void initMisTecnicos(Stage stage){
        misTecnicosStage = stage;
    
    }
    
    private List<Tecnico> listaTecnicos;
    private DAL DAL =new DAL();
    
    private int totalPaginas, tecnicosUltimaPagina;
    private int paginaActual = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cargarTecnicos();
        mostrarTecnicos();
        mostrarBotones();
    }    


    @FXML
    private void clickDenegar0(MouseEvent event) throws SQLException {
        
        DAL.deleteTecnico(listaTecnicos.get(0 + paginaActual * 5).getId_tecnico());
        Alert a = new Alert(Alert.AlertType.INFORMATION,"Tecnico eliminado de la agencia",ButtonType.YES);
                    a.setTitle("Técnico eliminado");
                    a.showAndWait();
        cargarTecnicos();
        mostrarTecnicos();
        
    }


    @FXML
    private void clickDenegar1(MouseEvent event) throws SQLException {
        DAL.deleteTecnico(listaTecnicos.get(1 + paginaActual * 5).getId_tecnico());
        Alert a = new Alert(Alert.AlertType.INFORMATION,"Tecnico eliminado de la agencia",ButtonType.YES);
                    a.setTitle("Técnico eliminado");
                    a.showAndWait();
        cargarTecnicos();
        mostrarTecnicos();
    }


    @FXML
    private void clickDenegar2(MouseEvent event) throws SQLException {
        DAL.deleteTecnico(listaTecnicos.get(2 + paginaActual * 5).getId_tecnico());
        Alert a = new Alert(Alert.AlertType.INFORMATION,"Tecnico eliminado de la agencia",ButtonType.YES);
                    a.setTitle("Técnico eliminado");
                    a.showAndWait();
        cargarTecnicos();
        mostrarTecnicos();
    }


    @FXML
    private void clickDenegar3(MouseEvent event) throws SQLException {
        DAL.deleteTecnico(listaTecnicos.get(3 + paginaActual * 5).getId_tecnico());
        Alert a = new Alert(Alert.AlertType.INFORMATION,"Tecnico eliminado de la agencia",ButtonType.YES);
                    a.setTitle("Técnico eliminado");
                    a.showAndWait();
        cargarTecnicos();
        mostrarTecnicos();
    }


    @FXML
    private void clickDenegar4(MouseEvent event) throws SQLException {
        DAL.deleteTecnico(listaTecnicos.get(4 + paginaActual * 5).getId_tecnico());
        Alert a = new Alert(Alert.AlertType.INFORMATION,"Tecnico eliminado de la agencia",ButtonType.YES);
                    a.setTitle("Técnico eliminado");
                    a.showAndWait();
        cargarTecnicos();
        mostrarTecnicos();
        
    }

    @FXML
    private void clickAnterior(ActionEvent event) {
        paginaActual--;
        buttonSiguiente.setDisable(false);
        if(paginaActual==0) buttonAnterior.setDisable(true);
        cargarTecnicos();
        mostrarTecnicos();
    }

    @FXML
    private void clickSiguiente(ActionEvent event) {
        paginaActual++;
        buttonAnterior.setDisable(false);
        if(paginaActual==totalPaginas) buttonSiguiente.setDisable(true);
        cargarTecnicos();
        mostrarTecnicos();
    }
    
    private void cargarTecnicos()
    {
        DAL dal = new DAL();
        
       try {
           listaTecnicos = dal.getAllTecnicosFromAgencia(LoginController.cuentaLoged.getId_cuenta());
            for(Tecnico e: listaTecnicos) System.out.println(e.getNombre());
       } catch (SQLException ex) {
        }
        totalPaginas = listaTecnicos.size()/5;
        tecnicosUltimaPagina = listaTecnicos.size()%5;
    }
    
    private void mostrarTecnicos()
    {
         mostrarBotones();
         if(paginaActual < totalPaginas)
         {
             labelMensaje0.setText(listaTecnicos.get(0 + paginaActual * 5).getNombre());
             imageDenegar0.setVisible(true);
             labelMensaje1.setText(listaTecnicos.get(1 + paginaActual * 5).getNombre());
             imageDenegar1.setVisible(true);
             labelMensaje2.setText(listaTecnicos.get(2 + paginaActual * 5).getNombre());
             imageDenegar2.setVisible(true);
             labelMensaje3.setText(listaTecnicos.get(3 + paginaActual * 5).getNombre());
             imageDenegar3.setVisible(true);
             labelMensaje4.setText(listaTecnicos.get(4 + paginaActual * 5).getNombre());
             imageDenegar4.setVisible(true);
         }else
         {
             if(tecnicosUltimaPagina == 1)
             {
                 labelMensaje0.setText(listaTecnicos.get(0 + paginaActual * 5).getNombre());
                 imageDenegar0.setVisible(true);
                 
                imageDenegar1.setVisible(false);
                imageDenegar2.setVisible(false);
                imageDenegar3.setVisible(false);
                imageDenegar4.setVisible(false);
                
                labelMensaje1.setText("");
                labelMensaje2.setText("");
                labelMensaje3.setText("");
                labelMensaje4.setText("");
             }
             if(tecnicosUltimaPagina == 2)
             {
                labelMensaje0.setText(listaTecnicos.get(0 + paginaActual * 5).getNombre());
                imageDenegar0.setVisible(true);
                labelMensaje1.setText(listaTecnicos.get(1 + paginaActual * 5).getNombre());
                imageDenegar1.setVisible(true);
                
                imageDenegar2.setVisible(false);
                imageDenegar3.setVisible(false);
                imageDenegar4.setVisible(false);
                
                labelMensaje2.setText("");
                labelMensaje3.setText("");
                labelMensaje4.setText("");
             }
             if(tecnicosUltimaPagina == 3)
             {
                labelMensaje0.setText(listaTecnicos.get(0 + paginaActual * 5).getNombre());
                imageDenegar0.setVisible(true);
                labelMensaje1.setText(listaTecnicos.get(1 + paginaActual * 5).getNombre());
                imageDenegar1.setVisible(true);
                labelMensaje2.setText(listaTecnicos.get(2 + paginaActual * 5).getNombre());
                imageDenegar2.setVisible(true);
                
                imageDenegar3.setVisible(false);
                imageDenegar4.setVisible(false);
                
                labelMensaje3.setText("");
                labelMensaje4.setText("");
             }
             if(tecnicosUltimaPagina == 4)
             {
                labelMensaje0.setText(listaTecnicos.get(0 + paginaActual * 5).getNombre());
                imageDenegar0.setVisible(true);
                labelMensaje1.setText(listaTecnicos.get(1 + paginaActual * 5).getNombre());
                imageDenegar1.setVisible(true);
                labelMensaje2.setText(listaTecnicos.get(2 + paginaActual * 5).getNombre());
                imageDenegar2.setVisible(true);
                labelMensaje3.setText(listaTecnicos.get(3 + paginaActual * 5).getNombre());
                imageDenegar3.setVisible(true);
                
                imageDenegar4.setVisible(false);
                
                labelMensaje4.setText("");
             }
             if(tecnicosUltimaPagina == 5)
             {
                labelMensaje0.setText(listaTecnicos.get(0 + paginaActual * 5).getNombre());
                imageDenegar0.setVisible(true);
                labelMensaje1.setText(listaTecnicos.get(1 + paginaActual * 5).getNombre());
                imageDenegar1.setVisible(true);
                labelMensaje2.setText(listaTecnicos.get(2 + paginaActual * 5).getNombre());
                imageDenegar2.setVisible(true);
                labelMensaje3.setText(listaTecnicos.get(3 + paginaActual * 5).getNombre());
                imageDenegar3.setVisible(true);
                labelMensaje4.setText(listaTecnicos.get(4 + paginaActual * 5).getNombre());
                imageDenegar4.setVisible(true);
             }
         }
    }
    
    private void mostrarBotones()
    {
        if(paginaActual == totalPaginas) buttonSiguiente.setDisable(true); else buttonSiguiente.setDisable(false);
        if(paginaActual == 0) buttonAnterior.setDisable(true); else buttonAnterior.setDisable(false);
    }
}
