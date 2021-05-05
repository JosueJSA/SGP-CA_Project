/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.businesslogic.ICollaboratorDAO;
import sgp.ca.businesslogic.IIntegrantDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.Integrant;

/**
 * FXML Controller class
 *
 * @author Josue Alarcon
 */
public class MemberController implements Initializable{
    
    private final IIntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final ICollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    private Hashtable gridForm = new Hashtable();

    @FXML
    private Button btnRegistrerMember;
    @FXML
    private Button btnUnsubscribe;
    @FXML
    private Button btnClose;
    @FXML
    private ComboBox<String> comboBoxParticipationType;
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
    private TextField memberStateField;
    @FXML
    private TextField memberStaffNumberField;
    @FXML
    private DatePicker memberRegistrationDateField;
    @FXML
    private Label lblMemberRfcHelp;
    @FXML
    private Label lblMemberDateHelp;
    @FXML
    private Label lblMemberFullNameHelp;
    @FXML
    private Label lblMemberEducationalProgramHelp;
    @FXML
    private Label lblMemberEmailUvHelp;
    @FXML
    private Label lblMemberCellNumberHelp;
    @FXML
    private Label lblMemberCurpHelp;
    @FXML
    private Label lblMemberStateHelp;
    @FXML
    private Label lblMemberNationalityHelp;
    @FXML
    private Label lblMemberStaffNumberHelp;
    @FXML
    private Label lblMemberBosyAcademyNameHelp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        gridForm.put(memberRFCField, lblMemberRfcHelp);
        gridForm.put(memberFullNameField, lblMemberFullNameHelp);
        gridForm.put(memberEmailUVField, lblMemberEmailUvHelp);
        gridForm.put(memberCurpField, lblMemberCurpHelp);
        gridForm.put(memberNationalityField, lblMemberNationalityHelp);
        gridForm.put(memberBodyAcademyNameField, lblMemberBosyAcademyNameHelp);
        gridForm.put(memberEducationalProgramField, lblMemberEducationalProgramHelp);
        gridForm.put(memberStateField, lblMemberStateHelp);
        gridForm.put(memberStaffNumberField, lblMemberStaffNumberHelp);

        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll("Integrante", "Responsable", "Colaborador");
        comboBoxParticipationType.setItems(items);
    }    

    @FXML
    private void addNewMember(ActionEvent event){
        if(isValidForm()){
            Integrant integrant = new Integrant(
                memberRFCField.getText(), memberFullNameField.getText(),
                memberEmailUVField.getText(), "Activo", "admin",
                memberCurpField.getText(), memberNationalityField.getText(),
                "2012-12-12", memberEducationalProgramField.getText(),
                Integer.parseInt(memberStaffNumberField.getText()), memberCellNumberField.getText(), 
                memberBodyAcademyNameField.getText(), "...", 
                comboBoxParticipationType.getValue(), "...", "...", "..."
            );
            INTEGRANT_DAO.addIntegrant(integrant);
            if(!INTEGRANT_DAO.getIntegrantByUVmail(memberEmailUVField.getText()).getFullName().isEmpty()){
                AlertGenerator.showInfoAlert(event, "El Integrante ha sido registrado correctamente");
            }
        }else{
            AlertGenerator.showErrorAlert(event, "Lo sentimos, tienes campos inválidos en el formulario");
        }
    }

    @FXML
    private void unsubscribeMember(ActionEvent event){
    }

    @FXML
    private void closeMemberWindow(ActionEvent event){
    }
    
    public boolean isValidForm(){
        boolean correctForm = true;
        Set<TextField> keys = gridForm.keySet();
        for(TextField field : keys){
            if(!ValidatorForm.chechkAlphabeticalField(field, 50)){
                correctForm = false;
                markIncorrectField(field);
            }else{
                markCorrectField(field);
            }
        }
        if(!ValidatorForm.isIntegerNumberData(memberStaffNumberField.getText())){
            correctForm = false;
            markIncorrectField(memberStaffNumberField);
        }else{
            markCorrectField(memberStaffNumberField);
        }
        return correctForm;
    }
    
    public void markCorrectField(TextField field){
        field.setStyle("-fx-border-color: green;");
        Label label = (Label) gridForm.get(field);
        label.setText("");
    }
    
    public void markIncorrectField(TextField field){
        field.setStyle("-fx-border-color: red;");
        Label label = (Label) gridForm.get(field);
        label.setText(field.getAccessibleHelp());
    }
    
}
