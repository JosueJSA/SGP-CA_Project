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
public class CollaboratorEditableController implements Initializable {

    @FXML
    private Button btnRegistrerColaborator;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnCollaboratorUpdate;
    @FXML
    private HBox hboxCollaboratorOptions;
    @FXML
    private Label lbParticipationType;
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
    private TextField txtFieldStaffNumber;
    @FXML
    private DatePicker datePickerAdmisionDate;
    @FXML
    private TextField txtFieldStudyArea;
    @FXML
    private TextField txtFieldBodyAcademyName;
    @FXML
    private TextField txtFieldLevelStudy;
    
    private Integrant token;
    private Collaborator collaborator;
    private String oldRFC;
    private List<Button> optionButtons;
    private List<TextField> fields;
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        collaborator = new Collaborator();
        this.fields = Arrays.asList(
            txtFieldBodyAcademyName, txtFieldCurp, txtFieldEducationalProgram,
            txtFieldEmailUv, txtFieldFullName, txtFieldLevelStudy, txtFieldNationality, 
            txtFieldRfcMember, txtFieldStudyArea
        );
        optionButtons = Arrays.asList(btnCancel, btnCollaboratorUpdate, btnRegistrerColaborator);
        hboxCollaboratorOptions.getChildren().removeAll(optionButtons);
    }  
    
    public void showCollaboratorRegistrationForm(Integrant integrantToken){
        this.token = integrantToken;
        hboxCollaboratorOptions.getChildren().addAll(btnRegistrerColaborator, btnCancel);
    }
    
    public void showCollaboratorUpdateForm(Integrant integrantToken, String emailUV){
        this.token = integrantToken;
        hboxCollaboratorOptions.getChildren().addAll(btnCollaboratorUpdate, btnCancel);
        this.collaborator = (Collaborator) COLLABORATOR_DAO.getMemberByUVmail(emailUV);
        this.oldRFC = collaborator.getRfc();
        this.setIntegrantDataIntoInterface();
    }

    @FXML
    private void addNewCollaborator(ActionEvent event) {
        try {
            this.checkCollabortorForm();
            this.getOutIntegrantDataFromInterface();
            collaborator.setParticipationStatus("Activo");
            if(COLLABORATOR_DAO.addMember(this.collaborator)){
                AlertGenerator.showInfoAlert(event, "El colaborador ha sido registrado en el sistema");
            }else{
                AlertGenerator.showErrorAlert(event, "Ocurrió un error en el sistema, favor de ponerse en contacto con soporte técnico");
            }
            FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
            GeneralResumeRequestController controller = loader.getController();
            controller.showGeneralResume(token);
        } catch (InvalidFormException ex) {
            AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
    }

    @FXML
    private void updateCollaborator(ActionEvent event) {
        try {
            this.checkCollabortorForm();
            this.getOutIntegrantDataFromInterface();
            if(COLLABORATOR_DAO.updateMember(this.collaborator, this.oldRFC)){
                AlertGenerator.showInfoAlert(event, "El colaborador ha sido registrado en el sistema");
            }else{
                AlertGenerator.showErrorAlert(event, "Ocurrió un error en el sistema, favor de ponerse en contacto con soporte técnico");
            }
            FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
            GeneralResumeRequestController controller = loader.getController();
            controller.showGeneralResume(token);
        } catch (InvalidFormException ex) {
            AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
    }

    @FXML
    private void cancelCollaboratorChanges(ActionEvent event) {
        FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
        GeneralResumeRequestController controller = loader.getController();
        controller.showGeneralResume(token);
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
    }
    
    private void getOutIntegrantDataFromInterface(){
        collaborator.setBodyAcademyKey(this.token.getBodyAcademyKey());
        collaborator.setCellphone(txtFieldCellNumber.getText());
        collaborator.setRfc(this.txtFieldRfcMember.getText());
        collaborator.setFullName(this.txtFieldFullName.getText());
        collaborator.setEmailUV(this.txtFieldEmailUv.getText());
        collaborator.setCurp(this.txtFieldCurp.getText());
        collaborator.setNationality(this.txtFieldNationality.getText());
        collaborator.setParticipationType(this.lbParticipationType.getText());
        collaborator.setParticipationStatus("Activo");
        collaborator.setEducationalProgram(this.txtFieldEducationalProgram.getText());
        collaborator.setStaffNumber(Integer.parseInt(this.txtFieldStaffNumber.getText()));
        collaborator.setDateOfAdmission(ValidatorForm.convertJavaDateToSQlDate(this.datePickerAdmisionDate));
        collaborator.setStudyArea(this.txtFieldStudyArea.getText());
        collaborator.setNameBACollaborator(this.txtFieldBodyAcademyName.getText());
        collaborator.setHighestDegreeStudies(this.txtFieldLevelStudy.getText());
    }
    
    public void checkCollabortorForm() throws InvalidFormException{
        ValidatorForm.checkAlaphabeticalFields(fields, 5, 50);
        ValidatorForm.checkNotEmptyDateField(datePickerAdmisionDate);
        ValidatorForm.isNumberData(txtFieldCellNumber, 11);
        ValidatorForm.isIntegerNumberData(txtFieldStaffNumber, 8);
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
