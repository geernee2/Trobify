package controlador;

import static controlador.InicioController.inicioStage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import basededatos.DAL;
import static controlador.GestionCuentaController.gestionCuentaStage;
import static controlador.ResultadosController.inmuebleSeleccionado;
import static controlador.ResultadosController.resultadosStage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;


public class FavoritosController implements Initializable {

    @FXML
    private ImageView iconoUsuario;
    @FXML
    private Button botonMiCuenta;
    @FXML
    private ScrollPane scrollPaneResultados;
    @FXML
    private AnchorPane panelInmueble0;
    @FXML
    private ImageView imageView0;
    @FXML
    private Label textPrecio0;
    @FXML
    private Label textNumHabs0;
    @FXML
    private Label textNumBanyos0;
    @FXML
    private Label textTipoInmueble0;
    @FXML
    private Label textBarrio0;
    @FXML
    private Label textNumTelefono0;
    @FXML
    private Label textTitulo0;
    @FXML
    private Button buttonFav0;
    @FXML
    private AnchorPane panelInmueble1;
    @FXML
    private ImageView imageView1;
    @FXML
    private Label textPrecio1;
    @FXML
    private Label textNumHabs1;
    @FXML
    private Label textNumBanyos1;
    @FXML
    private Label textTipoInmueble1;
    @FXML
    private Label textBarrio1;
    @FXML
    private Label textNumTelefono1;
    @FXML
    private Label textTitulo1;
    @FXML
    private Button buttonFav1;
    @FXML
    private AnchorPane panelInmueble2;
    @FXML
    private ImageView imageView2;
    @FXML
    private Label textPrecio2;
    @FXML
    private Label textNumHabs2;
    @FXML
    private Label textNumBanyos2;
    @FXML
    private Label textTipoInmueble2;
    @FXML
    private Label textBarrio2;
    @FXML
    private Label textNumTelefono2;
    @FXML
    private Label textTitulo2;
    @FXML
    private Button buttonFav2;
    @FXML
    private AnchorPane panelInmueble3;
    @FXML
    private ImageView imageView3;
    @FXML
    private Label textPrecio3;
    @FXML
    private Label textNumHabs3;
    @FXML
    private Label textNumBanyos3;
    @FXML
    private Label textTipoInmueble3;
    @FXML
    private Label textBarrio3;
    @FXML
    private Label textNumTelefono3;
    @FXML
    private Label textTitulo3;
    @FXML
    private Button buttonFav3;
    @FXML
    private AnchorPane panelInmueble4;
    @FXML
    private ImageView imageView4;
    @FXML
    private Label textPrecio4;
    @FXML
    private Label textNumHabs4;
    @FXML
    private Label textNumBanyos4;
    @FXML
    private Label textTipoInmueble4;
    @FXML
    private Label textBarrio4;
    @FXML
    private Label textNumTelefono4;
    @FXML
    private Label textTitulo4;
    @FXML
    private Button buttonFav4;
    @FXML
    private Button botonAnterior;
    @FXML
    private Button botonSiguiente;
    @FXML
    private Text textoNumPagina;
    
    public static Stage favoritosStage;
    
    public DAL dal = new DAL();
    int size;
    int numPagMax;
    int pagActual; // de 0 a numPagMax
    
    private List<Favorito> listaMostrar = new ArrayList<Favorito>();
    private List<Favorito> listaTodosFavoritos = new ArrayList<Favorito>();
    private List<String> listaGruposFavs = new ArrayList<String>();

    public static void initFavoritos(Stage stage)
    {
        favoritosStage = stage;
    
    }
    @FXML
    private ChoiceBox<String> choiceGrupoFav;
    @FXML
    private TextArea notaDetalle0;
    @FXML
    private Button saveNota0;
    @FXML
    private TextArea notaDetalle1;
    @FXML
    private Button saveNota1;
    @FXML
    private TextArea notaDetalle2;
    @FXML
    private Button saveNota2;
    @FXML
    private TextArea notaDetalle3;
    @FXML
    private Button saveNota3;
    @FXML
    private TextArea notaDetalle4;
    @FXML
    private Button saveNota4;
    
    int idf0,idf1,idf2,idf3,idf4;
    
    Alert saveNoteAlert = new Alert(AlertType.INFORMATION);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setEmptyAll();
        String localDir = System.getProperty("user.dir");
        String pathImagen = localDir + LoginController.cuentaLoged.getImagenPerfil();
        Image imgPerfil = new Image("file:" + pathImagen); 
        iconoUsuario.setImage(imgPerfil);
        botonAnterior.setDisable(true);
        saveNoteAlert.setTitle("Nota guardada");
        saveNoteAlert.setHeaderText("¡Éxito!");
        saveNoteAlert.setContentText("¡Nota guardada con éxito!");
        Stage stage = (Stage)saveNoteAlert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("/recursos/logoFinal.png"));
        
        try
        {
            listaTodosFavoritos = dal.getListFavs(LoginController.cuentaLoged.getId_cuenta());
            listaGruposFavs = dal.getListGruposFavs(LoginController.cuentaLoged.getId_cuenta());
            for(String x: listaGruposFavs){choiceGrupoFav.getItems().add(x);}
            choiceGrupoFav.getSelectionModel().select(0);
            listaMostrar = cargarFavsPorGrupo(choiceGrupoFav.getValue());
            //actualizarVistaFavoritos(listaMostrar);

            
            choiceGrupoFav.getSelectionModel().selectedIndexProperty().addListener(
            (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
            if(new_val.intValue() != old_val.intValue())
            {
                try{
                    listaMostrar = cargarFavsPorGrupo(choiceGrupoFav.getItems().get(new_val.intValue()));
                    for(Favorito x : listaMostrar) System.out.println(x.toString());
                    setEmptyAll();
                    actualizarVistaFavoritos(listaMostrar);
                }catch(Exception e){e.printStackTrace();}
            }
            });
            
            size = listaMostrar.size();
            pagActual = 0;
            if(listaMostrar.size() % 5 >= 1)
            {
                numPagMax = listaMostrar.size() / 5;
                
            }else{numPagMax = listaMostrar.size() / 5; numPagMax--;}
            if(numPagMax == 0)
            {
                botonSiguiente.setDisable(true);
            }
            System.out.println("Pag max es :" + numPagMax);
            actualizarVistaFavoritos(listaMostrar);
            
            
        }catch(Exception e){System.out.println(e.getMessage());}
    }    
    
    private List<Favorito> cargarFavsPorGrupo(String grupo)
    {
        List<Favorito> res = new ArrayList<Favorito>();
        res.clear();
        for(Favorito x: listaTodosFavoritos)
        {
            if(x.getGrupo().equals(grupo))
            {
                res.add(x);
            } 
        }
        return res;
    }

    @FXML
    private void clickedIniciarSesion(ActionEvent event) throws IOException {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/GestionCuentaFXML.fxml"));
            Parent root = (Parent) myLoader.load();
            GestionCuentaController GestionCuentaController = myLoader.<GestionCuentaController>getController();

            Stage GestionCuentaControllerStage = new Stage();
            GestionCuentaControllerStage.getIcons().add(new Image("/recursos/logoFinal.png"));
            GestionCuentaController.initGestionCuenta(GestionCuentaControllerStage);
            //We create the scene for resultadosStage
            Scene scene = new Scene(root);
        
            //Añadir css
            //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
            //scene.getStylesheets().add(css);
        
            //we asign new scene to current stage/window
            GestionCuentaControllerStage.setScene(scene);
            GestionCuentaControllerStage.setTitle("Mi Cuenta");
            GestionCuentaControllerStage.initModality(Modality.APPLICATION_MODAL);
            GestionCuentaControllerStage.show();
            favoritosStage.close();
    }

    @FXML
    private void clickFav0(ActionEvent event) throws SQLException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Seguro que quieres quitar de favoritos el inmueble?");
            a.setTitle("Quitar inmueble de favoritos");
            
            Optional<ButtonType> result = a.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                dal.deleteFav(listaMostrar.get(0 + (pagActual * 5)).getId_fav());
                listaTodosFavoritos.remove(listaMostrar.get(0 + (pagActual * 5)));
                if(choiceGrupoFav.getItems()==null){
                    favoritosStage.close();
                    gestionCuentaStage.show();
                } else {
                    resetChoiceBox();
                }
                
            } else {
              System.out.println("Cancelado quitar de favoritos");
            }
    }

    @FXML
    private void ClickInmueble0(MouseEvent event) throws IOException {
        try{
            
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = dal.getInmuebleByID(listaMostrar.get(0 + pagActual*5).getInmueble().getId_inm());
            System.out.println(inmuebleSeleccionado.inmToString());
            mostrarFichaInmueble();
        } catch(SQLException e){}
    }

    @FXML
    private void clickFav1(ActionEvent event) throws SQLException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Seguro que quieres quitar de favoritos el inmueble?");
            a.setTitle("Quitar inmueble de favoritos");
            
            Optional<ButtonType> result = a.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                dal.deleteFav(listaMostrar.get(1 + (pagActual * 5)).getId_fav());
                listaTodosFavoritos.remove(listaMostrar.get(1 + (pagActual * 5)));
                if(choiceGrupoFav.getItems()==null){
                    favoritosStage.close();
                    gestionCuentaStage.show();
                } else {
                    resetChoiceBox();
                }
            } else {
              System.out.println("Cancelado quitar de favoritos");
            }
    }

    @FXML
    private void ClickInmueble1(MouseEvent event) throws IOException {
        try{
            
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = dal.getInmuebleByID(listaMostrar.get(1 + pagActual*5).getInmueble().getId_inm());
            System.out.println(inmuebleSeleccionado.inmToString());
            mostrarFichaInmueble();
        } catch(SQLException e){}
    }

    @FXML
    private void clickFav2(ActionEvent event) throws SQLException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Seguro que quieres quitar de favoritos el inmueble?");
            a.setTitle("Quitar inmueble de favoritos");
            
            Optional<ButtonType> result = a.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                dal.deleteFav(listaMostrar.get(2 + (pagActual * 5)).getId_fav());
                listaTodosFavoritos.remove(listaMostrar.get(2 + (pagActual * 5)));
                if(choiceGrupoFav.getItems()==null){
                    favoritosStage.close();
                    gestionCuentaStage.show();
                } else {
                    resetChoiceBox();
                }
            } else {
              System.out.println("Cancelado quitar de favoritos");
            }
    }

    @FXML
    private void ClickInmueble2(MouseEvent event) throws IOException {
        try{
            
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = dal.getInmuebleByID(listaMostrar.get(2 + pagActual*5).getInmueble().getId_inm());
            System.out.println(inmuebleSeleccionado.inmToString());
            mostrarFichaInmueble();
        } catch(SQLException e){}
    }

    @FXML
    private void clickFav3(ActionEvent event) throws SQLException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Seguro que quieres quitar de favoritos el inmueble?");
            a.setTitle("Quitar inmueble de favoritos");
            
            Optional<ButtonType> result = a.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                dal.deleteFav(listaMostrar.get(3 + (pagActual * 5)).getId_fav());
                listaTodosFavoritos.remove(listaMostrar.get(3 + (pagActual * 5)));
                
                
                    resetChoiceBox();
                    if(choiceGrupoFav.getItems()==null){
                    favoritosStage.close();
                    gestionCuentaStage.show();
                }
            } else {
              System.out.println("Cancelado quitar de favoritos");
            }
    }

    @FXML
    private void ClickInmueble3(MouseEvent event) throws IOException {
        try{
            
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = dal.getInmuebleByID(listaMostrar.get(3 + pagActual*5).getInmueble().getId_inm());
            System.out.println(inmuebleSeleccionado.inmToString());
            mostrarFichaInmueble();
        } catch(SQLException e){}
    }

    @FXML
    private void clickFav4(ActionEvent event) throws SQLException {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Seguro que quieres quitar de favoritos el inmueble?");
            a.setTitle("Quitar inmueble de favoritos");
            
            Optional<ButtonType> result = a.showAndWait();
            ButtonType button = result.orElse(ButtonType.CANCEL);

            if (button == ButtonType.OK) {
                dal.deleteFav(listaMostrar.get(4 + (pagActual * 5)).getId_fav());
                listaTodosFavoritos.remove(listaMostrar.get(4 + (pagActual * 5)));
                if(choiceGrupoFav.getItems()==null){
                    favoritosStage.close();
                    gestionCuentaStage.show();
                } else {
                    resetChoiceBox();
                }
            } else {
              System.out.println("Cancelado quitar de favoritos");
            }
    }

    @FXML
    private void ClickInmueble4(MouseEvent event) throws IOException {
        try{
            
            //List<Integer> listaInmuebles = DAL.getIdInmCiudad(ciudad, tipoInmueble, tipoDeVenta);
            inmuebleSeleccionado = dal.getInmuebleByID(listaMostrar.get(4 + pagActual*5).getInmueble().getId_inm());
            System.out.println(inmuebleSeleccionado.inmToString());
            mostrarFichaInmueble();
        } catch(SQLException e){}
    }

    @FXML
    private void clickAnterior(ActionEvent event) {
        pagActual--;
        botonSiguiente.setDisable(false);
        if(pagActual == 0){botonAnterior.setDisable(true);}
        else{botonAnterior.setDisable(false);}
    }

    @FXML
    private void clickSiguiente(ActionEvent event) {
        pagActual++;
        botonAnterior.setDisable(false);
        if(pagActual == numPagMax){botonSiguiente.setDisable(true);}
        else{botonSiguiente.setDisable(false);}
    }

    private void resetChoiceBox() throws SQLException
    {
        choiceGrupoFav.getItems().clear();
        List<String> res = new ArrayList<String>();
        res = dal.getListGruposFavs(LoginController.cuentaLoged.getId_cuenta());;
        for(String x: res){choiceGrupoFav.getItems().add(x);}
    }
    
    private void actualizarVistaFavoritos(List<Favorito> favs) throws SQLException
    {
        if(pagActual != numPagMax)
        {
            cargaFichaFav0(listaMostrar.get(0 +(pagActual * 5) ));
            cargaFichaFav1(listaMostrar.get(1 +(pagActual * 5) ));
            cargaFichaFav2(listaMostrar.get(2 +(pagActual * 5) ));
            cargaFichaFav3(listaMostrar.get(3 +(pagActual * 5) ));
            cargaFichaFav4(listaMostrar.get(4 +(pagActual * 5) ));
        }else
        {
            if(favs.size() % 5  == 1)
            {
               cargaFichaFav0(listaMostrar.get(0 +(pagActual * 5) ));
            }
            if(favs.size() % 5  == 2)
            {
               cargaFichaFav0(listaMostrar.get(0 +(pagActual * 5) ));
               cargaFichaFav1(listaMostrar.get(1 +(pagActual * 5) ));
            }
            if(favs.size() % 5  == 3)
            {
               cargaFichaFav0(listaMostrar.get(0 +(pagActual * 5) ));
               cargaFichaFav1(listaMostrar.get(1 +(pagActual * 5) ));
               cargaFichaFav2(listaMostrar.get(2 +(pagActual * 5) ));
            }
            if(favs.size() % 5  == 4)
            {
               cargaFichaFav0(listaMostrar.get(0 +(pagActual * 5) ));
               cargaFichaFav1(listaMostrar.get(1 +(pagActual * 5) ));
               cargaFichaFav2(listaMostrar.get(2 +(pagActual * 5) ));
               cargaFichaFav3(listaMostrar.get(3 +(pagActual * 5) ));
            }
            if(favs.size() % 5  == 0)
            {
               cargaFichaFav0(listaMostrar.get(0 +(pagActual * 5) ));
               cargaFichaFav1(listaMostrar.get(1 +(pagActual * 5) ));
               cargaFichaFav2(listaMostrar.get(2 +(pagActual * 5) ));
               cargaFichaFav3(listaMostrar.get(3 +(pagActual * 5) ));
               cargaFichaFav4(listaMostrar.get(4 +(pagActual * 5) ));
            }
            
        }
    }
    
    
    
  private void mostrarFichaInmueble() throws IOException {
        
        
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/vista/FichaInmuebleFXML.fxml"));
        Parent root = (Parent) myLoader.load();
        FichaInmuebleController FichaInmuebleController = myLoader.<FichaInmuebleController>getController();

        Stage fichaInmuebleStage = new Stage();
        FichaInmuebleController.initFichaInmueble(fichaInmuebleStage);
        //We create the scene for resultadosStage
        Scene scene = new Scene(root);
        
        //Añadir css
        //String css= this.getClass().getResource("/css/hojaEstiloInicio.css").toExternalForm();
        //scene.getStylesheets().add(css);
        
        //we asign new scene to current stage/window
        fichaInmuebleStage.setScene(scene);
        fichaInmuebleStage.setTitle("Ficha Inmueble");
        fichaInmuebleStage.initModality(Modality.APPLICATION_MODAL);
        
        fichaInmuebleStage.show();
        FichaInmuebleController.vuelve = false;
        favoritosStage.hide();
        //.hide();
    }
  
  
  private void setEmptyAll()
  {
      textBarrio0.setText("");
      textBarrio1.setText("");
      textBarrio2.setText("");
      textBarrio3.setText("");
      textBarrio4.setText("");
      
      //textDescripcion0.setText("");
      //textDescripcion1.setText("");
      //textDescripcion2.setText("");
      //textDescripcion3.setText("");
      //textDescripcion4.setText("");
      
      textNumBanyos0.setText("");
      textNumBanyos1.setText("");
      textNumBanyos2.setText("");
      textNumBanyos3.setText("");
      textNumBanyos4.setText("");
      
      textNumHabs0.setText("");
      textNumHabs1.setText("");
      textNumHabs2.setText("");
      textNumHabs3.setText("");
      textNumHabs4.setText("");
      
      textNumTelefono0.setText("");
      textNumTelefono1.setText("");
      textNumTelefono2.setText("");
      textNumTelefono3.setText("");
      textNumTelefono4.setText("");
      
      textPrecio0.setText("");
      textPrecio1.setText("");
      textPrecio2.setText("");
      textPrecio3.setText("");
      textPrecio4.setText("");
      
      /*textSuperficie0.setText("");
      textSuperficie1.setText("");
      textSuperficie2.setText("");
      textSuperficie3.setText("");
      textSuperficie4.setText("");*/
      
      textTipoInmueble0.setText("");
      textTipoInmueble1.setText("");
      textTipoInmueble2.setText("");
      textTipoInmueble3.setText("");
      textTipoInmueble4.setText("");
      
      textTitulo0.setText("");
      textTitulo1.setText("");
      textTitulo2.setText("");
      textTitulo3.setText("");
      textTitulo4.setText("");
      
      imageView0.setImage(null);
      imageView1.setImage(null);
      imageView2.setImage(null);
      imageView3.setImage(null);
      imageView4.setImage(null);
      
      buttonFav0.setVisible(false);
      buttonFav1.setVisible(false);
      buttonFav2.setVisible(false);
      buttonFav3.setVisible(false);
      buttonFav4.setVisible(false);
      
      notaDetalle0.setText("");
      notaDetalle1.setText("");
      notaDetalle2.setText("");
      notaDetalle3.setText("");
      notaDetalle4.setText("");
      notaDetalle0.setVisible(false);
      notaDetalle1.setVisible(false);
      notaDetalle2.setVisible(false);
      notaDetalle3.setVisible(false);
      notaDetalle4.setVisible(false);
      
      saveNota0.setVisible(false);
      saveNota1.setVisible(false);
      saveNota2.setVisible(false);
      saveNota3.setVisible(false);
      saveNota4.setVisible(false);

  }
  
  private void cargaFichaFav0(Favorito f) throws SQLException
    {
        Inmueble inm = f.getInmueble();
        
        buttonFav0.setVisible(true);
        
        textBarrio0.setVisible(true);
        textNumBanyos0.setVisible(true);
        textNumHabs0.setVisible(true);
        textPrecio0.setVisible(true);
        textTipoInmueble0.setVisible(true);
        textTitulo0.setVisible(true);
        notaDetalle0.setVisible(true);
        saveNota0.setVisible(true);
        
        textBarrio0.setText("En " + inm.getBarrio());
        textNumBanyos0.setText(String.valueOf(inm.getnBanos()) + " baños");
        textNumHabs0.setText(String.valueOf(inm.getnHabit()) + " habs.");
        textPrecio0.setText(String.valueOf(inm.getPrecio()) + " €");
        textTipoInmueble0.setText("Tipo de inmueble: " + inm.getTipoInm());
        textTitulo0.setText(inm.getNombre());
        notaDetalle0.setText(f.getNota());
        idf0 = f.getId_fav();
        
        imageView0.setImage(new Image(dal.getPreviewPhoto(inm.getId_inm())));
    }
  
  private void cargaFichaFav1(Favorito f) throws SQLException
    {
        Inmueble inm = f.getInmueble();
        
        buttonFav1.setVisible(true);
        
        textBarrio1.setVisible(true);
        textNumBanyos1.setVisible(true);
        textNumHabs1.setVisible(true);
        textPrecio1.setVisible(true);
        textTipoInmueble1.setVisible(true);
        textTitulo1.setVisible(true);
        notaDetalle1.setVisible(true);
        saveNota1.setVisible(true);
        
        textBarrio1.setText("En " + inm.getBarrio());
        textNumBanyos1.setText(String.valueOf(inm.getnBanos()) + " baños");
        textNumHabs1.setText(String.valueOf(inm.getnHabit()) + " habs.");
        textPrecio1.setText(String.valueOf(inm.getPrecio()) + " €");
        textTipoInmueble1.setText("Tipo de inmueble: " + inm.getTipoInm());
        textTitulo1.setText(inm.getNombre());
        notaDetalle1.setText(f.getNota());
        idf1 = f.getId_fav();
        
        imageView1.setImage(new Image(dal.getPreviewPhoto(inm.getId_inm())));
    }
    
  private void cargaFichaFav2(Favorito f) throws SQLException
    {
        Inmueble inm = f.getInmueble();
        
        buttonFav2.setVisible(true);
        
        textBarrio2.setVisible(true);
        textNumBanyos2.setVisible(true);
        textNumHabs2.setVisible(true);
        textPrecio2.setVisible(true);
        textTipoInmueble2.setVisible(true);
        textTitulo2.setVisible(true);
        notaDetalle2.setVisible(true);
        saveNota2.setVisible(true);
        
        textBarrio2.setText("En " + inm.getBarrio());
        textNumBanyos2.setText(String.valueOf(inm.getnBanos()) + " baños");
        textNumHabs2.setText(String.valueOf(inm.getnHabit()) + " habs.");
        textPrecio2.setText(String.valueOf(inm.getPrecio()) + " €");
        textTipoInmueble2.setText("Tipo de inmueble: " + inm.getTipoInm());
        textTitulo2.setText(inm.getNombre());
        notaDetalle2.setText(f.getNota());
        idf2 = f.getId_fav();
        
        imageView2.setImage(new Image(dal.getPreviewPhoto(inm.getId_inm())));
    }
  
  private void cargaFichaFav3(Favorito f) throws SQLException
    {
        Inmueble inm = f.getInmueble();
        
        buttonFav3.setVisible(true);
        
        textBarrio3.setVisible(true);
        textNumBanyos3.setVisible(true);
        textNumHabs3.setVisible(true);
        textPrecio3.setVisible(true);
        textTipoInmueble3.setVisible(true);
        textTitulo3.setVisible(true);
        notaDetalle3.setVisible(true);
        saveNota3.setVisible(true);
        
        
        textBarrio3.setText("En " + inm.getBarrio());
        textNumBanyos3.setText(String.valueOf(inm.getnBanos()) + " baños");
        textNumHabs3.setText(String.valueOf(inm.getnHabit()) + " habs.");
        textPrecio3.setText(String.valueOf(inm.getPrecio()) + " €");
        textTipoInmueble3.setText("Tipo de inmueble: " + inm.getTipoInm());
        textTitulo3.setText(inm.getNombre());
        notaDetalle3.setText(f.getNota());
        idf3 = f.getId_fav();
        
        imageView3.setImage(new Image(dal.getPreviewPhoto(inm.getId_inm())));
    }
  
  private void cargaFichaFav4(Favorito f) throws SQLException
    {
        Inmueble inm = f.getInmueble();
        
        buttonFav4.setVisible(true);
        
        textBarrio4.setVisible(true);
        textNumBanyos4.setVisible(true);
        textNumHabs4.setVisible(true);
        textPrecio4.setVisible(true);
        textTipoInmueble4.setVisible(true);
        textTitulo4.setVisible(true);
        notaDetalle4.setVisible(true);
        saveNota4.setVisible(true);
        
        textBarrio4.setText("En " + inm.getBarrio());
        textNumBanyos4.setText(String.valueOf(inm.getnBanos()) + " baños");
        textNumHabs4.setText(String.valueOf(inm.getnHabit()) + " habs.");
        textPrecio4.setText(String.valueOf(inm.getPrecio()) + " €");
        textTipoInmueble4.setText("Tipo de inmueble: " + inm.getTipoInm());
        textTitulo4.setText(inm.getNombre());
        notaDetalle4.setText(f.getNota());
        idf4 = f.getId_fav();
        
        imageView4.setImage(new Image(dal.getPreviewPhoto(inm.getId_inm())));
    }

    @FXML
    private void clickSaveNota0(ActionEvent event) throws SQLException {
        try{
            dal.saveNota(notaDetalle0.getText(), idf0);
            saveNoteAlert.show();
        } catch(SQLException e){
            System.out.println("Error al añadir la nota: " + e);
        }
    }

    @FXML
    private void clickSaveNota1(ActionEvent event) throws SQLException {
        try{
            dal.saveNota(notaDetalle1.getText(), idf1);
            saveNoteAlert.show();
        } catch(SQLException e){
            System.out.println("Error al añadir la nota: " + e);
        }
    }

    @FXML
    private void clickSaveNota2(ActionEvent event) throws SQLException {
        try{
            dal.saveNota(notaDetalle2.getText(), idf2);
            saveNoteAlert.show();
        } catch(SQLException e){
            System.out.println("Error al añadir la nota: " + e);
        }
    }

    @FXML
    private void clickSaveNota3(ActionEvent event) throws SQLException {
        try{
            dal.saveNota(notaDetalle3.getText(), idf3);
            saveNoteAlert.show();
        } catch(SQLException e){
            System.out.println("Error al añadir la nota: " + e);
        }
    }

    @FXML
    private void clickSaveNota4(ActionEvent event) throws SQLException {
        try{
            dal.saveNota(notaDetalle4.getText(), idf4);
            saveNoteAlert.show();
        } catch(SQLException e){
            System.out.println("Error al añadir la nota: " + e);
        }
    }
    
    
}
