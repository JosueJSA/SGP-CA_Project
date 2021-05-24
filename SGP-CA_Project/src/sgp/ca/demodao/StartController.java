/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.domain.Integrant;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class StartController implements Initializable {

    @FXML
    private Button btnGeneralResume;
    @FXML
    private Button btnPersonalResume;
    @FXML
    private Button btnWorkPlan;
    @FXML
    private Button btnExit;
    @FXML
    private Label lblUserName;
    
    private Integrant integrant;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        integrant = new Integrant();
    }  
    
    public void receiveIntegrantToken(Integrant integrant){
        this.integrant = integrant;
        this.lblUserName.setText(integrant.getFullName());
    }

    @FXML
    private void consultGeneralResume(ActionEvent event) {
        FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
        GeneralResumeRequestController controller = loader.getController();
        controller.showGeneralResume(integrant);
    }

    @FXML
    private void consultPersonalResume(ActionEvent event) {
        FXMLLoader loader = changeWindow("PersonalResumeRequest.fxml", event);
        PersonalResumeRequestController controller = loader.getController();
        controller.receiveIntegrantToken(integrant);
    }

    @FXML
    private void consultWorkPlan(ActionEvent event) {
        FXMLLoader loader = changeWindow("WorkPlan.fxml", event);
        WorkPlanController controller = loader.getController();
        controller.receiveIntegrantToken(integrant);
    }

    @FXML
    private void exit(ActionEvent event) {
        changeWindow("Login.fxml", event);
    }

    private FXMLLoader changeWindow(String window, Event event){
        Stage stage = new Stage();
        FXMLLoader loader = null;
        try{
            loader = new FXMLLoader(getClass().getResource(window));
            stage.setScene(new Scene((Pane)loader.load()));
            stage.show(); 
            Stage currentStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch(IOException io){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, io);
        } finally {
            return loader;
        }
    }
    
}
