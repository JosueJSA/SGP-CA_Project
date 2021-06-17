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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void receiveResponsibeleToken(Integrant integrantToken){
        this.token = integrantToken;
    }

    @FXML
    private void cancelRegistrerSelection(ActionEvent event) {
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("GeneralResumeRequest.fxml", btnCancel);
        GeneralResumeRequestController controller = loader.getController();
        controller.showGeneralResume(token);
    }

    @FXML
    private void addNewIntegrant(ActionEvent event) {
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("IntegrantEditable.fxml", btnCancel);
        IntegrantEditableController controller = loader.getController();
        controller.showIntegrantInscriptionForm(token);
    }

    @FXML
    private void addNewCollaborator(ActionEvent event) {
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("CollaboratorEditable.fxml", btnCancel);
        CollaboratorEditableController controller = loader.getController();
        controller.showCollaboratorRegistrationForm(token);
    }
    
}
