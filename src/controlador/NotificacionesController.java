/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import basededatos.DAL;
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author David
 */
public class NotificacionesController implements Initializable {
    
    public static Stage notificacionesStage;

    public static void initNotificaciones(Stage stage){
        notificacionesStage = stage;
    
    }

    private DAL dal = new DAL();
    @FXML
    private Label labelMensaje0;
    @FXML
    private ImageView imageAceptar0;
    @FXML
    private ImageView imageDenegar0;
    @FXML
    private Label labelEscoger0;
    @FXML
    private Label labelMensaje1;
    @FXML
    private ImageView imageAceptar1;
    @FXML
    private ImageView imageDenegar1;
    @FXML
    private Label labelEscoger1;
    @FXML
    private Label labelMensaje2;
    @FXML
    private ImageView imageAceptar2;
    @FXML
    private ImageView imageDenegar2;
    @FXML
    private Label labelEscoger2;
    @FXML
    private Label labelMensaje3;
    @FXML
    private ImageView imageAceptar3;
    @FXML
    private ImageView imageDenegar3;
    @FXML
    private Label labelEscoger3;
    @FXML
    private Label labelMensaje4;
    @FXML
    private ImageView imageAceptar4;
    @FXML
    private ImageView imageDenegar4;
    @FXML
    private Label labelEscoger4;
    
    private List<Notificacion> listaNotificaciones;
    private Notificacion notificacion0;
    private Notificacion notificacion1;
    private Notificacion notificacion2;
    private Notificacion notificacion3;
    private Notificacion notificacion4;
    
    private int totalPaginas, notificacionesUltimaPagina;
    private int paginaActual = 0;
    @FXML
    private AnchorPane paneNotificacion0;
    @FXML
    private AnchorPane paneNotificacion1;
    @FXML
    private AnchorPane paneNotificacion2;
    @FXML
    private AnchorPane paneNotificacion3;
    @FXML
    private AnchorPane paneNotificacion4;
    
    private String respuesta;
    private LocalDate fecha;
    private String nombreInmueble;
    @FXML
    private Button buttonAnterior;
    @FXML
    private Button buttonSiguiente;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        cargarNotificaciones();
        mostrarNotificaciones();
        mostrarBotones();
    }    

    @FXML
    private void clickAceptar0(MouseEvent event) {
        if(notificacion0.getTipoNotificacion().equals("contraoferta")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion0.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha aceptado tu contraoferta respecto al inmueble " + nombreInmueble + " .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion0.getCuentaOrigen().getId_cuenta(),notificacion0.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion0.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 0");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else if(notificacion0.getTipoNotificacion().equals("visita")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion0.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha aceptado tu solicitud de visita al inmueble .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion0.getCuentaOrigen().getId_cuenta(),notificacion0.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion0.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 0");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        }
    }

    @FXML
    private void clickDenegar0(MouseEvent event) {
        if(notificacion0.getTipoNotificacion().equals("contraoferta")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion0.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha denegado tu contraoferta respecto al inmueble " + nombreInmueble + " .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion0.getCuentaOrigen().getId_cuenta(),notificacion0.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion0.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 0");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else if(notificacion0.getTipoNotificacion().equals("visita")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion0.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha denegado tu solicitud de visita al inmueble .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion0.getCuentaOrigen().getId_cuenta(),notificacion0.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion0.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 0");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else {
            try {
                dal.deleteNotificacion(notificacion0.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error eliminando la notificacion 0");
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        }
    }

    @FXML
    private void clickAceptar1(MouseEvent event) {
        if(notificacion1.getTipoNotificacion().equals("contraoferta")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion1.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha aceptado tu contraoferta respecto al inmueble " + nombreInmueble + " .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion1.getCuentaOrigen().getId_cuenta(),notificacion1.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion1.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 1");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else if(notificacion1.getTipoNotificacion().equals("visita")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion1.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha aceptado tu solicitud de visita al inmueble .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion1.getCuentaOrigen().getId_cuenta(),notificacion1.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion1.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 1");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        }
    }

    @FXML
    private void clickDenegar1(MouseEvent event) {
        if(notificacion1.getTipoNotificacion().equals("contraoferta")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion1.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha denegado tu contraoferta respecto al inmueble " + nombreInmueble + " .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion1.getCuentaOrigen().getId_cuenta(),notificacion1.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion1.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 1");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else if(notificacion1.getTipoNotificacion().equals("visita")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion1.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha denegado tu solicitud de visita al inmueble .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion1.getCuentaOrigen().getId_cuenta(),notificacion1.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion1.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 1");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else {
            try {
                dal.deleteNotificacion(notificacion1.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error eliminando la notificacion 1");
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        }
    }

    @FXML
    private void clickAceptar2(MouseEvent event) {
        if(notificacion2.getTipoNotificacion().equals("contraoferta")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion2.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha aceptado tu contraoferta respecto al inmueble " + nombreInmueble + " .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion2.getCuentaOrigen().getId_cuenta(),notificacion2.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion2.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 2");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else if(notificacion2.getTipoNotificacion().equals("visita")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion2.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha aceptado tu solicitud de visita al inmueble .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion2.getCuentaOrigen().getId_cuenta(),notificacion2.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion2.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 2");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        }
    }

    @FXML
    private void clickDenegar2(MouseEvent event) {
        if(notificacion2.getTipoNotificacion().equals("contraoferta")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion2.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha denegado tu contraoferta respecto al inmueble " + nombreInmueble + " .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion2.getCuentaOrigen().getId_cuenta(),notificacion2.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion2.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 2");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else if(notificacion2.getTipoNotificacion().equals("visita")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion2.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha denegado tu solicitud de visita al inmueble .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion2.getCuentaOrigen().getId_cuenta(),notificacion2.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion2.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 2");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else {
            try {
                dal.deleteNotificacion(notificacion2.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error eliminando la notificacion 2");
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        }
    }

    @FXML
    private void clickAceptar3(MouseEvent event) {
        if(notificacion3.getTipoNotificacion().equals("contraoferta")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion3.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha aceptado tu contraoferta respecto al inmueble " + nombreInmueble + " .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion3.getCuentaOrigen().getId_cuenta(),notificacion3.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion3.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 3");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else if(notificacion3.getTipoNotificacion().equals("visita")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion3.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha aceptado tu solicitud de visita al inmueble .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion3.getCuentaOrigen().getId_cuenta(),notificacion3.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion3.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 3");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        }
    }

    @FXML
    private void clickDenegar3(MouseEvent event) {
        if(notificacion3.getTipoNotificacion().equals("contraoferta")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion3.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha denegado tu contraoferta respecto al inmueble " + nombreInmueble + " .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion3.getCuentaOrigen().getId_cuenta(),notificacion3.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion3.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 3");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else if(notificacion3.getTipoNotificacion().equals("visita")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion3.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha denegado tu solicitud de visita al inmueble .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion3.getCuentaOrigen().getId_cuenta(),notificacion3.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion3.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 3");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else {
            try {
                dal.deleteNotificacion(notificacion3.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error eliminando la notificacion 3");
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        }
    }

    @FXML
    private void clickAceptar4(MouseEvent event) {
        if(notificacion4.getTipoNotificacion().equals("contraoferta")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion4.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha aceptado tu contraoferta respecto al inmueble " + nombreInmueble + " .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion4.getCuentaOrigen().getId_cuenta(),notificacion4.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion4.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 4");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else if(notificacion4.getTipoNotificacion().equals("visita")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion4.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha aceptado tu solicitud de visita al inmueble .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion4.getCuentaOrigen().getId_cuenta(),notificacion4.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion4.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 4");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        }
    }

    @FXML
    private void clickDenegar4(MouseEvent event) {
        if(notificacion4.getTipoNotificacion().equals("contraoferta")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion4.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha denegado tu contraoferta respecto al inmueble " + nombreInmueble + " .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion4.getCuentaOrigen().getId_cuenta(),notificacion4.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion4.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 4");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else if(notificacion4.getTipoNotificacion().equals("visita")){
            try {
                nombreInmueble = (dal.getInmuebleByID(notificacion4.getId_inmueble())).getNombre();
            } catch (SQLException ex) {
                Logger.getLogger(NotificacionesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            respuesta = "El usuario " + LoginController.cuentaLoged.getEmail() + " ha denegado tu solicitud de visita al inmueble .";
            fecha = LocalDate.now();
            try {
                dal.addNotificacion(LoginController.cuentaLoged.getId_cuenta(), notificacion4.getCuentaOrigen().getId_cuenta(),notificacion4.getInmuebleNotificacion().getId_inm(), fecha, "mensaje", respuesta );
                dal.deleteNotificacion(notificacion4.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error guardando la notificacion 4");;
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        } else {
            try {
                dal.deleteNotificacion(notificacion4.getId_notificacion());
            } catch (SQLException ex) {
                System.out.println("Error eliminando la notificacion 4");
            }
            paginaActual = 0;
            cargarNotificaciones();
            mostrarNotificaciones();
        }
    }
    
    private void cargarNotificaciones(){
        DAL dal = new DAL();
        
        try {
            //Carga en la lista de notificaciones todas las que tienen como destino la cuenta logueada
            listaNotificaciones = dal.getAllNotificacionesByIdCuentaDestino(LoginController.cuentaLoged.getId_cuenta());
            
        } catch (SQLException ex) {
            System.out.println("");
        }
        totalPaginas = listaNotificaciones.size()/5;
        notificacionesUltimaPagina = listaNotificaciones.size()%5;
    }
    
    private void mostrarNotificaciones(){
        
        mostrarBotones();
        if(paginaActual<totalPaginas){
            //Cargamos las 5 notificaciones
            paneNotificacion0.setVisible(true);
            paneNotificacion1.setVisible(true);
            paneNotificacion2.setVisible(true);
            paneNotificacion3.setVisible(true);
            paneNotificacion4.setVisible(true);
            //Notificacion 0
            notificacion0 = listaNotificaciones.get(0 + paginaActual*5);
            if(notificacion0.getTipoNotificacion().equals("mensaje")){
                labelMensaje0.setText("");
                labelEscoger0.setText(notificacion0.getMensaje());
                imageAceptar0.setVisible(false);
                imageDenegar0.setVisible(true);
                
            } else if(notificacion0.getTipoNotificacion().equals("contraoferta")){
                labelEscoger0.setText(notificacion0.getMensaje());
                labelMensaje0.setText("");
                imageAceptar0.setVisible(true);
                imageDenegar0.setVisible(true);
                
            } else if((notificacion0.getTipoNotificacion().equals("visita"))){
                labelEscoger0.setText(notificacion0.getMensaje());
                labelMensaje0.setText("");
                imageAceptar0.setVisible(true);
                imageDenegar0.setVisible(true);
            }
            //Notificacion 1
            notificacion1 = listaNotificaciones.get(1 + paginaActual*5);
            if(notificacion0.getTipoNotificacion().equals("mensaje")){
                labelMensaje1.setText("");
                labelEscoger1.setText(notificacion1.getMensaje());
                imageAceptar1.setVisible(false);
                imageDenegar1.setVisible(true);
                
            } else if(notificacion1.getTipoNotificacion().equals("contraoferta")){
                labelEscoger1.setText(notificacion1.getMensaje());
                labelMensaje1.setText("");
                imageAceptar1.setVisible(true);
                imageDenegar1.setVisible(true);
                
            } else if((notificacion1.getTipoNotificacion().equals("visita"))){
                labelEscoger1.setText(notificacion1.getMensaje());
                labelMensaje1.setText("");
                imageAceptar1.setVisible(true);
                imageDenegar1.setVisible(true);
            }
            //Notificacion 2
            notificacion2 = listaNotificaciones.get(2 + paginaActual*5);
            if(notificacion2.getTipoNotificacion().equals("mensaje")){
                labelMensaje2.setText("");
                labelEscoger2.setText(notificacion2.getMensaje());
                imageAceptar2.setVisible(false);
                imageDenegar2.setVisible(true);
                
            } else if(notificacion2.getTipoNotificacion().equals("contraoferta")){
                labelEscoger2.setText(notificacion2.getMensaje());
                labelMensaje2.setText("");
                imageAceptar2.setVisible(true);
                imageDenegar2.setVisible(true);
                
            } else if((notificacion2.getTipoNotificacion().equals("visita"))){
                labelEscoger2.setText(notificacion2.getMensaje());
                labelMensaje2.setText("");
                imageAceptar2.setVisible(true);
                imageDenegar2.setVisible(true);
            }
            //Notificacion 3
            notificacion3 = listaNotificaciones.get(3 + paginaActual*5);
            if(notificacion3.getTipoNotificacion().equals("mensaje")){
                labelMensaje3.setText("");
                labelEscoger3.setText(notificacion3.getMensaje());
                imageAceptar3.setVisible(false);
                imageDenegar3.setVisible(true);
                
            } else if(notificacion3.getTipoNotificacion().equals("contraoferta")){
                labelEscoger3.setText(notificacion3.getMensaje());
                labelMensaje3.setText("");
                imageAceptar3.setVisible(true);
                imageDenegar3.setVisible(true);
                
            } else if((notificacion3.getTipoNotificacion().equals("visita"))){
                labelEscoger3.setText(notificacion3.getMensaje());
                labelMensaje3.setText("");
                imageAceptar3.setVisible(true);
                imageDenegar3.setVisible(true);
            }
            //Notificacion 4
            notificacion4 = listaNotificaciones.get(4 + paginaActual*5);
            if(notificacion4.getTipoNotificacion().equals("mensaje")){
                labelMensaje4.setText("");
                labelEscoger4.setText(notificacion4.getMensaje());
                imageAceptar4.setVisible(false);
                imageDenegar4.setVisible(true);
                
            } else if(notificacion4.getTipoNotificacion().equals("contraoferta")){
                labelEscoger4.setText(notificacion4.getMensaje());
                labelMensaje4.setText("");
                imageAceptar4.setVisible(true);
                imageDenegar4.setVisible(true);
                
            } else if((notificacion4.getTipoNotificacion().equals("visita"))){
                labelEscoger4.setText(notificacion4.getMensaje());
                labelMensaje4.setText("");
                imageAceptar4.setVisible(true);
                imageDenegar4.setVisible(true);
            }
            
        } else if(notificacionesUltimaPagina == 4){
            //Notificacion 0
            notificacion0 = listaNotificaciones.get(0 + paginaActual*5);
            if(notificacion0.getTipoNotificacion().equals("mensaje")){
                labelMensaje0.setText("");
                labelEscoger0.setText(notificacion0.getMensaje());
                imageAceptar0.setVisible(false);
                imageDenegar0.setVisible(true);
                
            } else if(notificacion0.getTipoNotificacion().equals("contraoferta")){
                labelEscoger0.setText(notificacion0.getMensaje());
                labelMensaje0.setText("");
                imageAceptar0.setVisible(true);
                imageDenegar0.setVisible(true);
                
            } else if((notificacion0.getTipoNotificacion().equals("visita"))){
                labelEscoger0.setText(notificacion0.getMensaje());
                labelMensaje0.setText("");
                imageAceptar0.setVisible(true);
                imageDenegar0.setVisible(true);
            }
            //Notificacion 1
            notificacion1 = listaNotificaciones.get(1 + paginaActual*5);
            if(notificacion0.getTipoNotificacion().equals("mensaje")){
                labelMensaje1.setText("");
                labelEscoger1.setText(notificacion1.getMensaje());
                imageAceptar1.setVisible(false);
                imageDenegar1.setVisible(true);
                
            } else if(notificacion1.getTipoNotificacion().equals("contraoferta")){
                labelEscoger1.setText(notificacion1.getMensaje());
                labelMensaje1.setText("");
                imageAceptar1.setVisible(true);
                imageDenegar1.setVisible(true);
                
            } else if((notificacion1.getTipoNotificacion().equals("visita"))){
                labelEscoger1.setText(notificacion1.getMensaje());
                labelMensaje1.setText("");
                imageAceptar1.setVisible(true);
                imageDenegar1.setVisible(true);
            }
            //Notificacion 2
            notificacion2 = listaNotificaciones.get(2 + paginaActual*5);
            if(notificacion2.getTipoNotificacion().equals("mensaje")){
                labelMensaje2.setText("");
                labelEscoger2.setText(notificacion2.getMensaje());
                imageAceptar2.setVisible(false);
                imageDenegar2.setVisible(true);
                
            } else if(notificacion2.getTipoNotificacion().equals("contraoferta")){
                labelEscoger2.setText(notificacion2.getMensaje());
                labelMensaje2.setText("");
                imageAceptar2.setVisible(true);
                imageDenegar2.setVisible(true);
                
            } else if((notificacion2.getTipoNotificacion().equals("visita"))){
                labelEscoger2.setText(notificacion2.getMensaje());
                labelMensaje2.setText("");
                imageAceptar2.setVisible(true);
                imageDenegar2.setVisible(true);
            }
            //Notificacion 3
            notificacion3 = listaNotificaciones.get(3 + paginaActual*5);
            if(notificacion3.getTipoNotificacion().equals("mensaje")){
                labelMensaje3.setText("");
                labelEscoger3.setText(notificacion3.getMensaje());
                imageAceptar3.setVisible(false);
                imageDenegar3.setVisible(true);
                
            } else if(notificacion3.getTipoNotificacion().equals("contraoferta")){
                labelEscoger3.setText(notificacion3.getMensaje());
                labelMensaje3.setText("");
                imageAceptar3.setVisible(true);
                imageDenegar3.setVisible(true);
                
            } else if((notificacion3.getTipoNotificacion().equals("visita"))){
                labelEscoger3.setText(notificacion3.getMensaje());
                labelMensaje3.setText("");
                imageAceptar3.setVisible(true);
                imageDenegar3.setVisible(true);
            }

            paneNotificacion4.setVisible(false);
            
        } else if(notificacionesUltimaPagina == 3){
            //Notificacion 0
            notificacion0 = listaNotificaciones.get(0 + paginaActual*5);
            if(notificacion0.getTipoNotificacion().equals("mensaje")){
                labelMensaje0.setText("");
                labelEscoger0.setText(notificacion0.getMensaje());
                imageAceptar0.setVisible(false);
                imageDenegar0.setVisible(true);
                
            } else if(notificacion0.getTipoNotificacion().equals("contraoferta")){
                labelEscoger0.setText(notificacion0.getMensaje());
                labelMensaje0.setText("");
                imageAceptar0.setVisible(true);
                imageDenegar0.setVisible(true);
                
            } else if((notificacion0.getTipoNotificacion().equals("visita"))){
                labelEscoger0.setText(notificacion0.getMensaje());
                labelMensaje0.setText("");
                imageAceptar0.setVisible(true);
                imageDenegar0.setVisible(true);
            }
            //Notificacion 1
            notificacion1 = listaNotificaciones.get(1 + paginaActual*5);
            if(notificacion1.getTipoNotificacion().equals("mensaje")){
                labelMensaje1.setText("");
                labelEscoger1.setText(notificacion1.getMensaje());
                imageAceptar1.setVisible(false);
                imageDenegar1.setVisible(true);
                
            } else if(notificacion1.getTipoNotificacion().equals("contraoferta")){
                labelEscoger1.setText(notificacion1.getMensaje());
                labelMensaje1.setText("");
                imageAceptar1.setVisible(true);
                imageDenegar1.setVisible(true);
                
            } else if((notificacion1.getTipoNotificacion().equals("visita"))){
                labelEscoger1.setText(notificacion1.getMensaje());
                labelMensaje1.setText("");
                imageAceptar1.setVisible(true);
                imageDenegar1.setVisible(true);
            }
            
            //Notificacion 2
            notificacion2 = listaNotificaciones.get(2 + paginaActual*5);
            if(notificacion2.getTipoNotificacion().equals("mensaje")){
                labelMensaje2.setText("");
                labelEscoger2.setText(notificacion2.getMensaje());
                imageAceptar2.setVisible(false);
                imageDenegar2.setVisible(true);
                
            } else if(notificacion2.getTipoNotificacion().equals("contraoferta")){
                labelEscoger2.setText(notificacion2.getMensaje());
                labelMensaje2.setText("");
                imageAceptar2.setVisible(true);
                imageDenegar2.setVisible(true);
                
            } else if((notificacion2.getTipoNotificacion().equals("visita"))){
                labelEscoger2.setText(notificacion2.getMensaje());
                labelMensaje2.setText("");
                imageAceptar2.setVisible(true);
                imageDenegar2.setVisible(true);
            }
           
            paneNotificacion3.setVisible(false);
            paneNotificacion4.setVisible(false);
            
        } else if(notificacionesUltimaPagina == 2){
            //Notificacion 0
            notificacion0 = listaNotificaciones.get(0 + paginaActual*5);
            if(notificacion0.getTipoNotificacion().equals("mensaje")){
                labelMensaje0.setText("");
                labelEscoger0.setText(notificacion0.getMensaje());
                imageAceptar0.setVisible(false);
                imageDenegar0.setVisible(true);
                
            } else if(notificacion0.getTipoNotificacion().equals("contraoferta")){
                labelEscoger0.setText(notificacion0.getMensaje());
                labelMensaje0.setText("");
                imageAceptar0.setVisible(true);
                imageDenegar0.setVisible(true);
                
            } else if((notificacion0.getTipoNotificacion().equals("visita"))){
                labelEscoger0.setText(notificacion0.getMensaje());
                labelMensaje0.setText("");
                imageAceptar0.setVisible(true);
                imageDenegar0.setVisible(true);
            }
            //Notificacion 1
            notificacion1 = listaNotificaciones.get(1 + paginaActual*5);
            if(notificacion1.getTipoNotificacion().equals("mensaje")){
                labelMensaje1.setText("");
                labelEscoger1.setText(notificacion1.getMensaje());
                imageAceptar1.setVisible(false);
                imageDenegar1.setVisible(true);
                
            } else if(notificacion1.getTipoNotificacion().equals("contraoferta")){
                labelEscoger1.setText(notificacion1.getMensaje());
                labelMensaje1.setText("");
                imageAceptar1.setVisible(true);
                imageDenegar1.setVisible(true);
                
            } else if((notificacion1.getTipoNotificacion().equals("visita"))){
                labelEscoger1.setText(notificacion1.getMensaje());
                labelMensaje1.setText("");
                imageAceptar1.setVisible(true);
                imageDenegar1.setVisible(true);
            }

            paneNotificacion2.setVisible(false);
            paneNotificacion3.setVisible(false);
            paneNotificacion4.setVisible(false);
            
        } else if(notificacionesUltimaPagina == 1){
            //Notificacion 0
            notificacion0 = listaNotificaciones.get(0 + paginaActual*5);
            if(notificacion0.getTipoNotificacion().equals("mensaje")){
                labelMensaje0.setText("");
                labelEscoger0.setText(notificacion0.getMensaje());
                imageAceptar0.setVisible(false);
                imageDenegar0.setVisible(true);
                
            } else if(notificacion0.getTipoNotificacion().equals("contraoferta")){
                labelEscoger0.setText(notificacion0.getMensaje());
                labelMensaje0.setText("");
                imageAceptar0.setVisible(true);
                imageDenegar0.setVisible(true);
                
            } else if((notificacion0.getTipoNotificacion().equals("visita"))){
                labelEscoger0.setText(notificacion0.getMensaje());
                labelMensaje0.setText("");
                imageAceptar0.setVisible(true);
                imageDenegar0.setVisible(true);
            }
            paneNotificacion1.setVisible(false);
            paneNotificacion2.setVisible(false);
            paneNotificacion3.setVisible(false);
            paneNotificacion4.setVisible(false);
        } else {
                    System.out.println("No hay resultados");
                    paneNotificacion0.setVisible(true);
                    labelMensaje0.setText("No hay notificaciones");
                    imageAceptar0.setVisible(false);
                    imageDenegar0.setVisible(false);
                    labelEscoger0.setText("");
                    paneNotificacion1.setVisible(false);
                    paneNotificacion2.setVisible(false);
                    paneNotificacion3.setVisible(false);
                    paneNotificacion4.setVisible(false);
                }
        
    }
    
    public static void notificacionInmuebleFavEliminado(int id_inm){
        DAL dal = new DAL();
        List<Integer> res = new ArrayList<Integer>();
        int cuenta_destino;
        int cuenta_origen;
        String cuenta_origen_nombre;
        LocalDate hora = LocalDate.now();
        String tipoNotificacion = "mensaje";
        
        try{
            res = dal.getIdCuentasInmuebleFav(id_inm);
            Inmueble inm = dal.getInmuebleByID(id_inm);
            cuenta_origen = inm.getId_cuenta();
            cuenta_origen_nombre = dal.getCuentaById(cuenta_origen).getNombre();
            String mensaje = "El usuario "+ cuenta_origen_nombre + " ha borrado el inmueble "+ inm.getNombre()+ " que usted guardaba en favoritos.";
            for(int i = 0; i < res.size(); i++){
              cuenta_destino = res.get(i);
              dal.addNotificacion(cuenta_origen, cuenta_destino, id_inm, hora, tipoNotificacion, mensaje);
              //System.out.println("############### CUENTA DESTINO= " + cuenta_destino + "----"+ mensaje);
            }
            
        }catch(SQLException e){System.out.println(e);}
    }

    @FXML
    private void clickAnterior(ActionEvent event) {
        paginaActual--;
        buttonSiguiente.setDisable(false);
        if(paginaActual==0) buttonAnterior.setDisable(true);
        cargarNotificaciones();
        mostrarNotificaciones();
        
    }

    @FXML
    private void clickSiguiente(ActionEvent event) {
        paginaActual++;
        buttonAnterior.setDisable(false);
        if(paginaActual==totalPaginas) buttonSiguiente.setDisable(true);
        cargarNotificaciones();
        mostrarNotificaciones();
    }
    
    private void mostrarBotones(){
        if(paginaActual == totalPaginas) buttonSiguiente.setDisable(true); else buttonSiguiente.setDisable(false);
        if(paginaActual == 0) buttonAnterior.setDisable(true); else buttonAnterior.setDisable(false);
    }
}
