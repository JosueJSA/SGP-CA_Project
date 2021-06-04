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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.domain.Integrant;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class MemberSelectionController implements Initializable {

    @FXML
    private Button btnCancel;
    @FXML
    private Button btnIntegrantRegistrer;
    @FXML
    private Button btnCollaboratorRegistrer;

    private Integrant token;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void receiveResponsibeleToken(Integrant integrantToken){
        this.token = integrantToken;
    }

    @FXML
    private void cancelRegistrerSelection(ActionEvent event) {
        FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
        GeneralResumeRequestController controller = loader.getController();
        controller.showGeneralResume(token);
    }

    @FXML
    private void addNewIntegrant(ActionEvent event) {
        FXMLLoader loader = changeWindow("IntegrantEditable.fxml", event);
        IntegrantEditableController controller = loader.getController();
        controller.showIntegrantInscriptionForm(token);
    }

    @FXML
    private void addNewCollaborator(ActionEvent event) {
        FXMLLoader loader = changeWindow("CollaboratorEditable.fxml", event);
        CollaboratorEditableController controller = loader.getController();
        controller.showCollaboratorRegistrationForm(token);
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
