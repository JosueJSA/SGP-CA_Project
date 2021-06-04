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
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.GeneralResumeDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.Integrant;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class IntegrantEditableController implements Initializable {

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
    private TextField memberBodyAcademyNameField;
    @FXML
    private TextField memberEducationalProgramField;
    @FXML
    private TextField memberCellNumberField;
    @FXML
    private TextField memberStaffNumberField;
    @FXML
    private DatePicker memberRegistrationDateField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private CheckBox checkBoxIsIntoBodyAcademy;
    @FXML
    private HBox hboxIntegrantOptions;
    @FXML
    private Button btnIntegrantRegistrer;
    @FXML
    private Button btnResponsibleRegistrer;
    @FXML
    private Button btnUpdateIntegrant;
    @FXML
    private Button btnCancelIntegrantChanges;
    @FXML
    private Button btnCancelResponsibleResgistration;
    
    
    private List<TextField> fields;
    private List<Button> optionButtons;
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final GeneralResumeDAO GENERAL_RESUME = new GeneralResumeDAO();
    private Integrant token;
    private Integrant integrant;
    private String oldRFC;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        integrant = new Integrant();
        this.checkBoxIsIntoBodyAcademy.setSelected(true);
        optionButtons = Arrays.asList(
            btnIntegrantRegistrer, btnCancelIntegrantChanges, 
            btnCancelResponsibleResgistration, 
            btnUpdateIntegrant, btnResponsibleRegistrer
        );
        fields = Arrays.asList(
            memberRFCField, memberFullNameField,
            memberEmailUVField, memberCurpField,
            memberNationalityField, memberEducationalProgramField
        );
        hboxIntegrantOptions.getChildren().removeAll(optionButtons);
    }    
    
    public void showResponsibleInscriptionForm(){
        this.lblParticipationType.setText("Responsable");
        hboxIntegrantOptions.getChildren().addAll(btnResponsibleRegistrer, btnCancelResponsibleResgistration);
    }
    
    public void showIntegrantInscriptionForm(Integrant responsibleToken){
        this.token = responsibleToken;
        this.lblParticipationType.setText("Integrante");
        hboxIntegrantOptions.getChildren().addAll(btnIntegrantRegistrer, btnCancelIntegrantChanges);
        memberBodyAcademyNameField.setVisible(false);
        checkBoxIsIntoBodyAcademy.setVisible(false);
    }
    
    public void showIntegrantUpdateForm(Integrant integrantToken, String emailUV){
        this.token = integrantToken;
        this.integrant = (Integrant) INTEGRANT_DAO.getMemberByUVmail(emailUV);
        this.oldRFC = this.integrant.getRfc();
        setIntegrantDataIntoInterface();
        hboxIntegrantOptions.getChildren().addAll(btnUpdateIntegrant,  btnCancelIntegrantChanges);
        memberBodyAcademyNameField.setDisable(true);
        checkBoxIsIntoBodyAcademy.setDisable(true);
    }

    @FXML
    private void confirmBodyAcademyRegistered(ActionEvent event) {
        if(checkBoxIsIntoBodyAcademy.isSelected()){
            this.memberBodyAcademyNameField.setDisable(false);
        }else{
            this.memberBodyAcademyNameField.setDisable(true);
        }
    }

    @FXML
    private void addNewIntegrant(ActionEvent event) {
        try {
            validateForm();
            integrant.setBodyAcademyKey(this.token.getBodyAcademyKey());
            if(INTEGRANT_DAO.addMember(integrant)){
                AlertGenerator.showInfoAlert(event, "Integrante registrado con éxito");
            }else{
                AlertGenerator.showErrorAlert(event, "Error en el sistema, favor de ponerse en contactor con soporte técnico");
            }
            FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
            GeneralResumeRequestController controller = loader.getController();
            controller.showGeneralResume(this.token);
        } catch (InvalidFormException ex) {
            AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
    }

    @FXML
    private void updateIntegrant(ActionEvent event) {
        try {
            validateForm();
            this.getOutIntegrantDataFromInterface();
            this.integrant.setBodyAcademyKey(this.token.getBodyAcademyKey());
            if(INTEGRANT_DAO.updateMember(this.integrant, oldRFC)){
                AlertGenerator.showInfoAlert(event, "Integrante actualizado con éxito");
            }else{
                AlertGenerator.showErrorAlert(event, "Error en el sistema, favor de ponerse en contactor con soporte técnico");
            }
            FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
            GeneralResumeRequestController controller = loader.getController();
            controller.showGeneralResume(this.token);
        } catch (InvalidFormException ex) {
            AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
    }
    
    @FXML
    private void addNewResponsible(ActionEvent event) {
        try {
            validateForm();
            this.getOutIntegrantDataFromInterface();
            if(INTEGRANT_DAO.addMember(this.integrant)){
                AlertGenerator.showInfoAlert(event, "Integrante registrado con éxito");
            }else{
                AlertGenerator.showErrorAlert(event, "Error en el sistema, favor de ponerse en contactor con soporte técnico");
            }
            FXMLLoader loader = changeWindow("Login.fxml", event);
        } catch (InvalidFormException ex) {
            AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
    }

    @FXML
    private void cancelIntegrantChanges(ActionEvent event) {
        FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
        GeneralResumeRequestController controller = loader.getController();
        controller.showGeneralResume(this.token);
    }

    @FXML
    private void cancelResponsibleSignUp(ActionEvent event) {
        FXMLLoader loader = changeWindow("Login.fxml", event);
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
    
    private void validateForm() throws InvalidFormException{
        ValidatorForm.checkAlaphabeticalFields(fields, 5, 100);
        ValidatorForm.isNumberData(this.memberCellNumberField, 8);
        ValidatorForm.isIntegerNumberData(memberStaffNumberField, 8);
        ValidatorForm.checkNotEmptyDateField(this.memberRegistrationDateField); 
        ValidatorForm.chechkAlphabeticalField(passwordField, 5, 50);
        if(checkBoxIsIntoBodyAcademy.isSelected()){
            if(!GENERAL_RESUME.isBodyAcademyRegistered(memberBodyAcademyNameField.getText())){
                memberBodyAcademyNameField.setStyle("-fx-border-color: red;");
                throw new InvalidFormException("El cuerpo Académico no existe");
            }
        }
    }
    
    private void setIntegrantDataIntoInterface(){
        this.passwordField.setText(integrant.getPassword());
        this.memberBodyAcademyNameField.setText(integrant.getBodyAcademyKey());
        this.memberCellNumberField.setText(integrant.getCellphone());
        this.memberCurpField.setText(integrant.getCurp());
        this.memberEducationalProgramField.setText(integrant.getEducationalProgram());
        this.memberEmailUVField.setText(integrant.getEmailUV());
        this.memberFullNameField.setText(integrant.getFullName());
        this.memberNationalityField.setText(integrant.getNationality());
        this.memberRFCField.setText(integrant.getRfc());
        this.memberRegistrationDateField.setValue(LocalDate.parse(integrant.getDateOfAdmission()));
        this.memberStaffNumberField.setText(String.valueOf(integrant.getStaffNumber()));
        this.lblParticipationType.setText(integrant.getParticipationType());
    }
    
    private void getOutIntegrantDataFromInterface(){
        integrant.setRfc(this.memberRFCField.getText());
        integrant.setFullName(this.memberFullNameField.getText());
        integrant.setEmailUV(this.memberEmailUVField.getText());
        integrant.setCurp(this.memberCurpField.getText());
        integrant.setNationality(this.memberNationalityField.getText());
        integrant.setParticipationType(this.lblParticipationType.getText());
        integrant.setParticipationStatus("Activo");
        if(this.checkBoxIsIntoBodyAcademy.isSelected()){
            integrant.setBodyAcademyKey(this.memberBodyAcademyNameField.getText());
        }
        integrant.setEducationalProgram(this.memberEducationalProgramField.getText());
        integrant.setStaffNumber(Integer.parseInt(this.memberStaffNumberField.getText()));
        integrant.setDateOfAdmission(ValidatorForm.convertJavaDateToSQlDate(this.memberRegistrationDateField));
        integrant.setPassword(this.passwordField.getText());
    }
    
}
