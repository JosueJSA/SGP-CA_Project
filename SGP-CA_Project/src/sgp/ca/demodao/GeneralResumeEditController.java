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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.GeneralResumeDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.GeneralResume;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Lgac;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class GeneralResumeEditController implements Initializable {

    @FXML
    private TextField bodyAcademyNameField;
    @FXML
    private TextField bodyAcademyKeyField;
    @FXML
    private TextField areaAscriptionFiel;
    @FXML
    private TextField ascriptionUnitField;
    @FXML
    private DatePicker dateFieldRegistration;
    @FXML
    private DatePicker dateFieldLastEvaluation;
    @FXML
    private TableView<Lgac> tableViewLgac;
    @FXML
    private Button btnAddLgac;
    @FXML
    private Button btnDeleteLgac;
    @FXML
    private TextArea txtAreaGeneralTarget;
    @FXML
    private TextArea txtAreaMission;
    @FXML
    private TextArea txtAreaVision;
    @FXML
    private TableColumn<Lgac, String> coloumnLgacKey;
    @FXML
    private TableColumn<Lgac, String> coloumnLgacDescription;
    @FXML
    private ComboBox<String> cmboxConsolidationDegree;
    @FXML
    private HBox hboxGeneralResumeOptions;
    @FXML
    private HBox hboxLgacTable;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnSignUpBodyAcademy;
    @FXML
    private Button btnCancelChanges;
    @FXML
    private Button btnCancelRegistration;
    @FXML
    private Label lblUserName;

    private final GeneralResumeDAO GENERAL_RESUME_DAO = new GeneralResumeDAO();
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private Integrant responsibleToken;
    private ObservableList<Lgac> lgacList;
    private List<TextField> fields;
    private List<TextArea> textAreas;
    private List<Button> optionButtons;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.btnDeleteLgac.setDisable(true);
        optionButtons = Arrays.asList(btnCancelChanges, btnSignUpBodyAcademy, btnUpdate, btnCancelRegistration);
        this.hboxGeneralResumeOptions.getChildren().removeAll(optionButtons);
        cmboxConsolidationDegree.getItems().addAll("En formación", "En consolidación", "Consolidado");
        textAreas = Arrays.asList(txtAreaGeneralTarget, txtAreaMission, txtAreaVision);
        fields = Arrays.asList(bodyAcademyNameField, bodyAcademyKeyField, areaAscriptionFiel, ascriptionUnitField);
        textAreas.forEach(txtArea -> txtArea.setWrapText(true));
    }
    
    public void showGeneralResumeInsertForm(Integrant responsible){
        this.hboxGeneralResumeOptions.getChildren().addAll(btnSignUpBodyAcademy, btnCancelRegistration);
        this.responsibleToken = responsible;
        this.lblUserName.setText(this.responsibleToken.getFullName());
    }
    
    public void showGeneralResumeUpdateForm(Integrant responsible) {
        this.hboxGeneralResumeOptions.getChildren().addAll(btnUpdate, btnCancelChanges);
        this.responsibleToken = responsible;
        this.lblUserName.setText(this.responsibleToken.getFullName());
        GeneralResume generalResume = GENERAL_RESUME_DAO.getGeneralResumeByKey(this.responsibleToken.getBodyAcademyKey());
        this.setGeneralResumeDataIntoInterface(generalResume);
    }

    @FXML
    private void signUpBodyAcademy(ActionEvent event) {
        try {
            isValidForm();
            GeneralResume generalResume = this.getOutGeneralResumeFormData();
            if(GENERAL_RESUME_DAO.addGeneralResume(generalResume)){
                AlertGenerator.showInfoAlert(event, "El currículum general ha sido registrado con éxito");
                this.addResponsibleInGeneralResumeRegistered(generalResume, event);
            }else{
                AlertGenerator.showErrorAlert(event, "Error del sistema, favor de contactar a soporte técnico");
                FXMLLoader loader = changeWindow("Login.fxml", event);
            }
        } catch (InvalidFormException ex) {
            AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
    }
    
    private void addResponsibleInGeneralResumeRegistered(GeneralResume generalResume, Event event){
        this.responsibleToken = (Integrant) INTEGRANT_DAO.getMemberByUVmail(responsibleToken.getEmailUV());
        this.responsibleToken.setBodyAcademyKey(generalResume.getBodyAcademyKey());
        if(INTEGRANT_DAO.updateMember(responsibleToken, responsibleToken.getRfc())){
            FXMLLoader loader = changeWindow("Start.fxml", event);
            StartController controller = loader.getController();
            controller.receiveIntegrantToken(responsibleToken);
        }else{
            FXMLLoader loader = changeWindow("Login.fxml", event);
        }
        
    }
    
    @FXML
    private void updateGeneralResume(ActionEvent event){
        try {
            isValidForm();
            GeneralResume generalResume = this.getOutGeneralResumeFormData();
            if(GENERAL_RESUME_DAO.updateGeneralResume(generalResume, this.responsibleToken.getBodyAcademyKey())){
                this.responsibleToken.setBodyAcademyKey(generalResume.getBodyAcademyKey());
                AlertGenerator.showInfoAlert(event, "El currículum ha sido actualizado exitosamente");
            }else{
                AlertGenerator.showErrorAlert(event, "Error del sistema, favor de contactar a soporte técnico");
            }
            FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
            GeneralResumeRequestController controller = loader.getController();
            controller.showGeneralResume(this.responsibleToken);
        } catch (InvalidFormException ex) {
            AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
    }
    
    @FXML
    private void cancelChanges(ActionEvent event) {
        Optional<ButtonType> action = AlertGenerator.showConfirmacionAlert(event, "¿Seguro que deseas cancelar la actualización?");
        if(action.get() == ButtonType.OK){
            FXMLLoader loader = changeWindow("Start.fxml", event);
            StartController controller = loader.getController();
            controller.receiveIntegrantToken(this.responsibleToken);
        }
    }
    
    @FXML
    private void cancelSignUpBodyAcademy(ActionEvent event) {
        Optional<ButtonType> action = AlertGenerator.showConfirmacionAlert(event, "¿Seguro que deseas cancelar el registro?");
        if(action.get() == ButtonType.OK){
            FXMLLoader loader = changeWindow("Login.fxml", event);
        }
    }
    
    @FXML
    private void addLgac(ActionEvent event) {
    }

    @FXML
    private void removeLgac(ActionEvent event) {
    }
    
    @FXML
    private void selectLgasRow(MouseEvent event) {
    }
    
    private GeneralResume getOutGeneralResumeFormData() {
        GeneralResume generalResume = new GeneralResume(
            bodyAcademyKeyField.getText(), 
            bodyAcademyNameField.getText(), 
            areaAscriptionFiel.getText(), 
            ascriptionUnitField.getText(),
            cmboxConsolidationDegree.getValue(),
            txtAreaVision.getText(), 
            txtAreaMission.getText(), 
            txtAreaGeneralTarget.getText(), 
            ValidatorForm.convertJavaDateToSQlDate(dateFieldRegistration), 
            ValidatorForm.convertJavaDateToSQlDate(dateFieldLastEvaluation)
        );
        return generalResume;
    }
    
    private void isValidForm() throws InvalidFormException{
        ValidatorForm.checkAlaphabeticalFields(fields, 100);
        ValidatorForm.checkAlaphabeticalTextAreas(textAreas, 5000);
        ValidatorForm.checkNotEmptyDateField(dateFieldRegistration);
        ValidatorForm.checkNotEmptyDateField(dateFieldLastEvaluation);
        ValidatorForm.isComboBoxSelected(cmboxConsolidationDegree);
    }
    
    private void setGeneralResumeDataIntoInterface(GeneralResume generalResume){
        if(generalResume != null){
            this.bodyAcademyKeyField.setText(generalResume.getBodyAcademyKey());
            this.bodyAcademyNameField.setText(generalResume.getBodyAcademyName());
            this.ascriptionUnitField.setText(generalResume.getAscriptionUnit());
            this.cmboxConsolidationDegree.setValue(generalResume.getConsolidationDegree());
            this.dateFieldRegistration.setValue(LocalDate.parse(generalResume.getRegistrationDate()));
            this.areaAscriptionFiel.setText(generalResume.getAscriptionArea());
            this.dateFieldLastEvaluation.setValue(LocalDate.parse(generalResume.getLastEvaluation()));
            this.txtAreaGeneralTarget.setText(generalResume.getGeneralTarjet());
            this.txtAreaMission.setText(generalResume.getMission());
            this.txtAreaVision.setText(generalResume.getVision());
        }
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
