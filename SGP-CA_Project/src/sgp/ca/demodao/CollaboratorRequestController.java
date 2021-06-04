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
    private TextField txtFieldRfcMember;
    @FXML
    private TextField txtFieldFullName;
    @FXML
    private TextField txtFieldEmailUv;
    @FXML
    private TextField txtFieldCurp;
    @FXML
    private TextField txtFieldNationality;
    @FXML
    private TextField txtFieldEducationalProgram;
    @FXML
    private TextField txtFieldCellNumber;
    @FXML
    private TextField txtFieldStatus;
    @FXML
    private TextField txtFieldStaffNumber;
    @FXML
    private DatePicker datePickerAdmisionDate;
    @FXML
    private TextField txtFieldStudyArea;
    @FXML
    private TextField txtFieldBodyAcademyName;
    @FXML
    private TextField txtFieldLevelStudy;
    @FXML
    private Label lbParticipationType;

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
        FXMLLoader loader = changeWindow("CollaboratorEditable.fxml", event);
        CollaboratorEditableController controller = loader.getController();
        controller.showCollaboratorUpdateForm(integrantToken, this.collaborator.getEmailUV());
    }

    @FXML
    private void exit(ActionEvent event) {
        FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
        GeneralResumeRequestController controller = loader.getController();
        controller.showGeneralResume(integrantToken);
    }
    
    private void setIntegrantDataIntoInterface(){
        this.txtFieldCellNumber.setText(collaborator.getCellphone());
        this.txtFieldCurp.setText(collaborator.getCurp());
        this.txtFieldEducationalProgram.setText(collaborator.getEducationalProgram());
        this.txtFieldEmailUv.setText(collaborator.getEmailUV());
        this.txtFieldFullName.setText(collaborator.getFullName());
        this.txtFieldNationality.setText(collaborator.getNationality());
        this.txtFieldRfcMember.setText(collaborator.getRfc());
        this.datePickerAdmisionDate.setValue(LocalDate.parse(collaborator.getDateOfAdmission()));
        this.txtFieldStaffNumber.setText(String.valueOf(collaborator.getStaffNumber()));
        this.lbParticipationType.setText(collaborator.getParticipationType());
        this.txtFieldBodyAcademyName.setText(collaborator.getNameBACollaborator());
        this.txtFieldLevelStudy.setText(collaborator.getHighestDegreeStudies());
        this.txtFieldStudyArea.setText(collaborator.getStudyArea());
        this.txtFieldStatus.setText(collaborator.getParticipationStatus());
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
