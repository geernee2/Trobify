/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Edgar
 */
public class SimuladorHipotecaController implements Initializable {
    
    public static Stage simuladorHipotecaControllerStage;
    @FXML
    private TextField interes;
    @FXML
    private TextField plazo;
    @FXML
    private Label ahorroAportado;
    @FXML
    private TextField precioInmueble;
    @FXML
    private Label cuotaMensual;
    @FXML
    private Label importeHipoteca1;
    @FXML
    private Label costeTotal;
    @FXML
    private Label precioTotalInmueble;
    @FXML
    private Label impuestosGastos;
    @FXML
    private Label importeHipoteca2;
    @FXML
    private Label interesHipoteca;
    @FXML
    private TextField ahorro;
    @FXML
    private Label totalConHipoteca;
    
    public void initSimuladorHipotecaController(Stage stage){
        simuladorHipotecaControllerStage = stage;     
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        calculoHipoteca();
    }    
    
    @FXML
    public void calculoHipoteca(){
        double numInteres = 0.0, numInteresHipoteca = 0.0, numCuotaMensual = 0.0;
        int numPrecioInmueble = 0, numAhorro = 0, numPlazo = 0, numImporteHipoteca1 = 0,  numImporteHipoteca2 = 0, 
                numImpuestosGastos = 0, numCostetotal = 0, numTotalConHipoteca = 0;
        int gestoria, tasas, impuestos; 
        double notaria, registro;
        int totalDisminuir = 0;
        //Recojo valores
        numPrecioInmueble = Integer.parseInt(precioInmueble.getText());
        numAhorro = Integer.parseInt(ahorro.getText());
        numPlazo = Integer.parseInt(plazo.getText());
        numInteres = Double.parseDouble(interes.getText());
        
        //Calculos
        //impuestos y gastos = 400tasacion + 6% precioinmueble + 0,45% precioinmueblegestoria + 0,25% precioinmueble registro (+ 950notaria si precioinmueble>150000)
        gestoria = 300;
        tasas = 400;
        impuestos = (numPrecioInmueble*6/100);
        notaria = (numPrecioInmueble*0.45/100);
        registro = (numPrecioInmueble*0.25/100);
        numImpuestosGastos = (int)registro + gestoria + impuestos + tasas;
        if(numPrecioInmueble>=150000) numImpuestosGastos+=(int)notaria;
        numImporteHipoteca1 = numImpuestosGastos + (numPrecioInmueble-numAhorro);
        numImporteHipoteca2 = numImporteHipoteca1;
        totalDisminuir = numImporteHipoteca1;
        numCuotaMensual = (numImporteHipoteca1 * Math.pow(1.0+0.0125, Double.valueOf(numPlazo)) *0.0125)/(Math.pow(1.0+0.0125, Double.valueOf(numPlazo)) - 1.0 );
        for(int i = 0; i<numPlazo; i++){
            numInteresHipoteca += (totalDisminuir*1.25/100);
            totalDisminuir -= numCuotaMensual;
        }
        numCuotaMensual = (numImporteHipoteca1+numInteresHipoteca) / (numPlazo*12);
        numCostetotal = numImpuestosGastos + numPrecioInmueble;
        numTotalConHipoteca = numAhorro + numImporteHipoteca2 + (int)numInteresHipoteca;
        
        //Asigno resultados
        cuotaMensual.setText(Integer.toString((int)numCuotaMensual));
        importeHipoteca1.setText(Integer.toString(numImporteHipoteca1)); 
        costeTotal.setText(Integer.toString(numCostetotal));
        precioTotalInmueble.setText(Integer.toString(numPrecioInmueble));
        impuestosGastos.setText(Integer.toString(numImpuestosGastos));
        totalConHipoteca.setText(Integer.toString(numTotalConHipoteca));
        ahorroAportado.setText(Integer.toString(numAhorro));
        importeHipoteca2.setText(Integer.toString(numImporteHipoteca2));
        impuestosGastos.setText(Integer.toString(numImpuestosGastos));
        interesHipoteca.setText(Integer.toString((int)numInteresHipoteca));
    }
    
}
