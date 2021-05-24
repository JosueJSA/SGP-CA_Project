/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Integrant;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class CollaboratorRequestController implements Initializable {

    @FXML
    private HBox hboxIntegrantOptions;
    @FXML
    private Button btnUnsubscribe;
    @FXML
    private Button btnSubscribe;
    @FXML
    private Button btnCollaboratorEdit;
    @FXML
    private Button btnExit;
    @FXML
    private Label lblParticipationType;
    @FXML
    private TextField memberRFCField;
    @FXML
    private TextField memberFullNameField;
    @FXML
    private TextField memberEmailUVField;
    @FXML
    private TextField memberCurpField;
    @FXML
    private TextField memberNationalityField;
    @FXML
    private TextField memberEducationalProgramField;
    @FXML
    private TextField memberCellNumberField;
    @FXML
    private TextField memberStaffNumberField;
    @FXML
    private DatePicker memberRegistrationDateField;
    @FXML
    private TextField fieldStudyArea;
    @FXML
    private TextField fieldHighestDegreeStudies;
    @FXML
    private TextField fieldParticipationStatus;

    private Collaborator collaborator;
    private Integrant integrantToken;
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void showCollaboratorByEmail(Integrant integrantToken, String collaboratorEmailUV){
        this.integrantToken = integrantToken;
        this.collaborator = (Collaborator) COLLABORATOR_DAO.getMemberByUVmail(collaboratorEmailUV);
        if(this.collaborator != null){
            this.setIntegrantDataIntoInterface();
        }
    }

    @FXML
    private void unsubscribeCollaborator(ActionEvent event) {
        if(COLLABORATOR_DAO.unsubscribeMemberByEmailUV(this.collaborator.getEmailUV())){
            AlertGenerator.showInfoAlert(event, "Colaborador dado de baja");
        }else{
            AlertGenerator.showErrorAlert(event, "Error en el sistema, favor de comunicarse con soporte técnico");
        }
        FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
        GeneralResumeRequestController controller = loader.getController();
        controller.showGeneralResume(integrantToken);
    }

    @FXML
    private void subscribeCollaborator(ActionEvent event) {
        if(COLLABORATOR_DAO.subscribeMemberByEmailUV(this.collaborator.getEmailUV())){
            AlertGenerator.showInfoAlert(event, "Colaborador dado de alta");
        }else{
            AlertGenerator.showErrorAlert(event, "Error en el sistema, favor de comunicarse con soporte técnico");
        }
        FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
        GeneralResumeRequestController controller = loader.getController();
        controller.showGeneralResume(integrantToken);
    }

    @FXML
    private void editCollaborator(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
        FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
        GeneralResumeRequestController controller = loader.getController();
        controller.showGeneralResume(integrantToken);
    }
    
    private void setIntegrantDataIntoInterface(){
        this.memberCellNumberField.setText(this.collaborator.getCellphone());
        this.memberCurpField.setText(this.collaborator.getCurp());
        this.memberEducationalProgramField.setText(this.collaborator.getEducationalProgram());
        this.memberEmailUVField.setText(this.collaborator.getEmailUV());
        this.memberFullNameField.setText(this.collaborator.getFullName());
        this.memberNationalityField.setText(this.collaborator.getNationality());
        this.memberRFCField.setText(this.collaborator.getRfc());
        this.memberRegistrationDateField.setValue(LocalDate.parse(this.collaborator.getDateOfAdmission()));
        this.memberStaffNumberField.setText(String.valueOf(this.collaborator.getStaffNumber()));
        this.lblParticipationType.setText(this.collaborator.getParticipationType());
        this.fieldHighestDegreeStudies.setText(this.collaborator.getHighestDegreeStudies());
        this.fieldParticipationStatus.setText(this.collaborator.getParticipationStatus());
        this.fieldStudyArea.setText(this.collaborator.getStudyArea());
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
