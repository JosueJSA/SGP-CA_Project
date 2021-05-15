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

    private List<TextField> fields = new ArrayList<>();
    private List<Object> registerParticipator = new ArrayList<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        comboBoxParticipationType.getItems().addAll("Integrante", "Responsable", "Colaborador");
    }    

    @FXML
    private void addNewMember(ActionEvent event){
            Integrant integrant = new Integrant(
                memberRFCField.getText(),
                memberFullNameField.getText(),
                memberEmailUVField.getText(), 
                "Activo", 
                "admin",
                memberCurpField.getText(), 
                memberNationalityField.getText(),
                "2012-12-12", memberEducationalProgramField.getText(),
                Integer.parseInt(memberStaffNumberField.getText()), memberCellNumberField.getText(), 
                memberBodyAcademyNameField.getText(), "...", 
                comboBoxParticipationType.getValue(), "...", "...", "..."
            );
            INTEGRANT_DAO.addIntegrant(integrant);
            if(!INTEGRANT_DAO.getIntegrantByUVmail(memberEmailUVField.getText()).getFullName().isEmpty()){
                AlertGenerator.showInfoAlert(event, "El Integrante ha sido registrado correctamente");
            }
    }

    @FXML
    private void unsubscribeMember(ActionEvent event){
    }

    @FXML
    private void closeMemberWindow(ActionEvent event){
    }
    
    public void isValidForm(){
        
    }

    
}
