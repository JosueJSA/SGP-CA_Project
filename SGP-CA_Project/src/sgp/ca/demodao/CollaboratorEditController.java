/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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
public class CollaboratorEditController implements Initializable {

    @FXML
    private Button btnRegistrerColaborator;
    @FXML
    private Button btnCancel;
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
    private TextField memberStateField;
    @FXML
    private TextField memberStaffNumberField;
    @FXML
    private TextField fieldStudyArea;
    @FXML
    private TextField fieldBodyAcademyName;
    @FXML
    private TextField fieldHighestDegreeStudies;
    @FXML
    private DatePicker memberRegistrationDateField;
    @FXML
    private Button btnCollaboratorUpdate;
    @FXML
    private HBox hboxCollaboratorOptions;

    private Integrant token;
    private Collaborator collaborator;
    private String oldRFC;
    private List<Button> optionButtons;
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        collaborator = new Collaborator();
        optionButtons = Arrays.asList(btnCancel, btnCollaboratorUpdate, btnRegistrerColaborator);
        hboxCollaboratorOptions.getChildren().removeAll(optionButtons);
    }  
    
    public void showCollaboratorRegistrationForm(Integrant integrantToken){
        this.token = integrantToken;
        hboxCollaboratorOptions.getChildren().addAll(btnRegistrerColaborator, btnCancel);
    }
    
    public void showCollaboratorUpdateForm(Integrant integrantToken, String emailUV){
        this.token = integrantToken;
        this.collaborator = (Collaborator) COLLABORATOR_DAO.getMemberByUVmail(emailUV);
        this.oldRFC = collaborator.getRfc();
        this.setIntegrantDataIntoInterface();
    }

    @FXML
    private void addNewCollaborator(ActionEvent event) {
        this.getOutIntegrantDataFromInterface();
        if(COLLABORATOR_DAO.addMember(this.collaborator)){
            AlertGenerator.showInfoAlert(event, "El colaborador ha sido registrado en el sistema");
        }else{
            AlertGenerator.showErrorAlert(event, "Ocurrió un error en el sistema, favor de ponerse en contacto con soporte técnico");
        }
        FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
        GeneralResumeRequestController controller = loader.getController();
        controller.showGeneralResume(token);
    }

    @FXML
    private void updateCollaborator(ActionEvent event) {
        this.getOutIntegrantDataFromInterface();
        if(COLLABORATOR_DAO.updateMember(this.collaborator, this.oldRFC)){
            AlertGenerator.showInfoAlert(event, "El colaborador ha sido registrado en el sistema");
        }else{
            AlertGenerator.showErrorAlert(event, "Ocurrió un error en el sistema, favor de ponerse en contacto con soporte técnico");
        }
        FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
        GeneralResumeRequestController controller = loader.getController();
        controller.showGeneralResume(token);
    }

    @FXML
    private void cancelCollaboratorChanges(ActionEvent event) {
        FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
        GeneralResumeRequestController controller = loader.getController();
        controller.showGeneralResume(token);
    }
    
    private void setIntegrantDataIntoInterface(){
        this.memberCellNumberField.setText(collaborator.getCellphone());
        this.memberCurpField.setText(collaborator.getCurp());
        this.memberEducationalProgramField.setText(collaborator.getEducationalProgram());
        this.memberEmailUVField.setText(collaborator.getEmailUV());
        this.memberFullNameField.setText(collaborator.getFullName());
        this.memberNationalityField.setText(collaborator.getNationality());
        this.memberRFCField.setText(collaborator.getRfc());
        this.memberRegistrationDateField.setValue(LocalDate.parse(collaborator.getDateOfAdmission()));
        this.memberStaffNumberField.setText(String.valueOf(collaborator.getStaffNumber()));
        this.lblParticipationType.setText(collaborator.getParticipationType());
    }
    
    private void getOutIntegrantDataFromInterface(){
        collaborator.setBodyAcademyKey(this.token.getBodyAcademyKey());
        collaborator.setRfc(this.memberRFCField.getText());
        collaborator.setFullName(this.memberFullNameField.getText());
        collaborator.setEmailUV(this.memberEmailUVField.getText());
        collaborator.setCurp(this.memberCurpField.getText());
        collaborator.setNationality(this.memberNationalityField.getText());
        collaborator.setParticipationType(this.lblParticipationType.getText());
        collaborator.setParticipationStatus("Activo");
        collaborator.setEducationalProgram(this.memberEducationalProgramField.getText());
        collaborator.setStaffNumber(Integer.parseInt(this.memberStaffNumberField.getText()));
        collaborator.setDateOfAdmission(ValidatorForm.convertJavaDateToSQlDate(this.memberRegistrationDateField));
        collaborator.setStudyArea(this.fieldStudyArea.getText());
        collaborator.setNameBACollaborator(this.fieldBodyAcademyName.getText());
        collaborator.setHighestDegreeStudies(this.fieldHighestDegreeStudies.getText());
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
