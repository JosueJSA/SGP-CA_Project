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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Schooling;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class PersonalResumeEditableController implements Initializable {

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnCancel;
    @FXML
    private DatePicker datePickerRegistrationDate;
    @FXML
    private Button btnAddSchooling;
    @FXML
    private Button btnRemoveSchooling;
    @FXML
    private TableView<Schooling> tableViewSchooling;
    @FXML
    private TableColumn<Schooling, String> columnSchoolingDegree;
    @FXML
    private TableColumn<Schooling, String> columnSchoolingName;
    @FXML
    private TableColumn<Schooling, String> columnRegistrationStudyDate;
    @FXML
    private TableColumn<Schooling, String> columnInstitution;
    @FXML
    private TableColumn<Schooling, String> columnState;
    @FXML
    private TableColumn<Schooling, String> columnCeduleNumber;
    @FXML
    private TableColumn<Schooling, String> columnSchoolingArea;
    @FXML
    private TableColumn<Schooling, String> columnDiscipline;
    @FXML
    private HBox hboxNewSchooling;
    @FXML
    private DatePicker datePickerDateStudy;
    @FXML
    private Label lblUserName;
    @FXML
    private TextField txtFieldRfc;
    @FXML
    private TextField txtFieldFullName;
    @FXML
    private TextField txtFieldEmailUv;
    @FXML
    private TextField txtFieldStatus;
    @FXML
    private TextField txtFieldCurp;
    @FXML
    private TextField txtFieldNationality;
    @FXML
    private TextField txtFieldEducationalProgram;
    @FXML
    private TextField txtFieldStaffNumber;
    @FXML
    private TextField txtFieldCellPhone;
    @FXML
    private TextField txtFieldWorkPhone;
    @FXML
    private TextField txtFieldHomePhone;
    @FXML
    private TextField txtFieldBodyAcademyKey;
    @FXML
    private TextField txtFieldAppoinment;
    @FXML
    private TextField txtFieldParticipationType;
    @FXML
    private TextField txtFieldAditionalMail;
    @FXML
    private PasswordField passFieldIntegrantPassword;
    @FXML
    private TextField txtFieldStudyDegree;
    @FXML
    private TextField txtFieldStudyName;
    @FXML
    private TextField txtFieldInstitutionSchooling;
    @FXML
    private TextField txtFieldState;
    @FXML
    private TextField txtFieldCeduleNumber;
    @FXML
    private TextField txtFieldStudyArea;
    @FXML
    private TextField txtFieldDiscipline;
    
    private Integrant integrantToken;
    private Integrant integrantUpdated;
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private List<TextField> schoolingFields;
    private List<TextField> personalResumeFields;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.preprareSchoolingTable();
        this.btnRemoveSchooling.setDisable(true);
        schoolingFields = Arrays.asList(
            txtFieldStudyDegree, txtFieldStudyName, txtFieldInstitutionSchooling,
            txtFieldState, txtFieldCeduleNumber, txtFieldStudyArea, txtFieldDiscipline
        );
        personalResumeFields = Arrays.asList(
            txtFieldRfc, txtFieldFullName, txtFieldEmailUv,
            txtFieldStatus, txtFieldCurp, txtFieldNationality,
            txtFieldEducationalProgram, txtFieldStaffNumber,
            txtFieldCellPhone, txtFieldWorkPhone, txtFieldHomePhone,
            txtFieldBodyAcademyKey, txtFieldAppoinment, txtFieldParticipationType,
            txtFieldAditionalMail, passFieldIntegrantPassword
        );
    }

    public void receiveIntegrantToken(Integrant integrant){
        this.integrantToken = integrant;
        this.lblUserName.setText(this.integrantToken.getFullName());
        this.setIntegrantDataIntoInterface();
    }

    @FXML
    private void updatePersonalResume(ActionEvent event) {
        try {
            this.checkPersonalResumeForm();
            this.getOutIntegrantData();
            if(INTEGRANT_DAO.updateMember(this.integrantUpdated, integrantToken.getRfc())){
                AlertGenerator.showInfoAlert(event, "Currículum personal actualizado exitosamente");
                FXMLLoader loader = changeWindow("Start.fxml", event);
                StartController controller = loader.getController();
                controller.receiveIntegrantToken(this.integrantUpdated);
            }else{
                AlertGenerator.showErrorAlert(event, "Error en el sistema, favor de ponerse en contacto con soporte técnico");
                FXMLLoader loader = changeWindow("Start.fxml", event);
                StartController controller = loader.getController();
                controller.receiveIntegrantToken(integrantToken);
            }
        } catch (InvalidFormException ex) {
            AlertGenerator.showErrorAlert(event, ex.getMessage());
        }
    }
    
    @FXML
    private void selectSchooling(MouseEvent event) {
        if(tableViewSchooling.getSelectionModel().getSelectedItem() != null){
            this.btnRemoveSchooling.setDisable(false);
        }else{
            this.btnRemoveSchooling.setDisable(true);
        }
    }
    
    @FXML
    private void cancelUpdate(ActionEvent event) {
        Optional<ButtonType> action = AlertGenerator.showConfirmacionAlert(event, "¿Seguro que deseas cancelar la operción?");
        if(action.get() == ButtonType.OK){
            FXMLLoader loader = changeWindow("Start.fxml", event);
            StartController controller = loader.getController();
            controller.receiveIntegrantToken(this.integrantToken);
        }
    }

    @FXML
    private void addSchooling(ActionEvent event) {
        try {
            this.checkShooling();
            tableViewSchooling.getItems().add(this.getOutSchooling());
        } catch (InvalidFormException ex) {
            AlertGenerator.showErrorAlert(event, "No hay elementos suficientes para llenar un estudio");
        }
    }

    @FXML
    private void removeSchooling(ActionEvent event) {
        tableViewSchooling.getItems().remove(
            tableViewSchooling.getSelectionModel().getSelectedItem()
        );
    }
    
    private void checkPersonalResumeForm() throws InvalidFormException{
        ValidatorForm.checkAlaphabeticalFields(personalResumeFields, 5, 100);
        ValidatorForm.isIntegerNumberData(txtFieldRfc, 0);
        ValidatorForm.checkNotEmptyDateField(datePickerRegistrationDate);
    }
    
    private void checkShooling() throws InvalidFormException{
        ValidatorForm.checkAlaphabeticalFields(schoolingFields, 5, 50);
        ValidatorForm.checkNotEmptyDateField(datePickerDateStudy);
    }
    
    private void preprareSchoolingTable(){
        columnSchoolingDegree.setCellValueFactory(new PropertyValueFactory<>("levelOfStudy"));
        columnSchoolingName.setCellValueFactory(new PropertyValueFactory<>("studyName"));
        columnRegistrationStudyDate.setCellValueFactory(new PropertyValueFactory<>("dateOfObteiningStudies"));
        columnInstitution.setCellValueFactory(new PropertyValueFactory<>("studiesInstitution"));
        columnState.setCellValueFactory(new PropertyValueFactory<>("studiesSatate"));
        columnCeduleNumber.setCellValueFactory(new PropertyValueFactory<>("idProfessionalStudies"));
        columnSchoolingArea.setCellValueFactory(new PropertyValueFactory<>("studyArea"));
        columnDiscipline.setCellValueFactory(new PropertyValueFactory<>("studiesDiscipline"));
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
    
    private void setIntegrantDataIntoInterface(){
        Integrant integrant = (Integrant) INTEGRANT_DAO.getMemberByUVmail(integrantToken.getEmailUV());
        if(integrant != null){
            this.txtFieldAditionalMail.setText(integrant.getAditionalMail());
            this.txtFieldAppoinment.setText(integrant.getAppointmentMember());
            this.txtFieldBodyAcademyKey.setText(integrant.getBodyAcademyKey());
            this.txtFieldCellPhone.setText(integrant.getCellphone());
            this.txtFieldCurp.setText(integrant.getCurp());
            this.txtFieldEducationalProgram.setText(integrant.getEducationalProgram());
            this.txtFieldEmailUv.setText(integrant.getEmailUV());
            this.txtFieldFullName.setText(integrant.getFullName());
            this.txtFieldHomePhone.setText(integrant.getHomePhone());
            this.txtFieldNationality.setText(integrant.getNationality());
            this.txtFieldParticipationType.setText(integrant.getParticipationType());
            this.passFieldIntegrantPassword.setText(integrant.getPassword());
            this.txtFieldRfc.setText(integrant.getRfc());
            this.txtFieldStaffNumber.setText( String.valueOf(integrant.getStaffNumber()));
            this.txtFieldStatus.setText(integrant.getParticipationStatus());
            this.txtFieldWorkPhone.setText(integrant.getWorkPhone());
            this.datePickerRegistrationDate.setValue(LocalDate.parse(integrant.getDateOfAdmission()));
            ObservableList<Schooling> list = FXCollections.observableArrayList();
            list.addAll(integrant.getSchooling());
            tableViewSchooling.setItems(list);
        }else{
            AlertGenerator.showErrorAlert(new ActionEvent(), "Error en el sistema, favor de contactar con soportr técnico");
            FXMLLoader loader = changeWindow("Start.fxml", new ActionEvent());
            StartController controller = loader.getController();
            controller.receiveIntegrantToken(this.integrantToken);
        }
    }
    
    private void getOutIntegrantData(){
        this.integrantUpdated = new Integrant(
            this.txtFieldRfc.getText(), 
            this.txtFieldFullName.getText(), 
            this.txtFieldEmailUv.getText(), 
            "Activo", 
            this.passFieldIntegrantPassword.getText(), 
            this.txtFieldCurp.getText(), 
            this.txtFieldParticipationType.getText(), 
            this.txtFieldNationality.getText(), 
            this.datePickerRegistrationDate.getValue().toString(), 
            this.txtFieldEducationalProgram.getText(), 
            Integer.parseInt(this.txtFieldStaffNumber.getText()), 
            this.txtFieldCellPhone.getText(), 
            this.txtFieldBodyAcademyKey.getText(), 
            this.txtFieldAppoinment.getText(), 
            this.txtFieldAditionalMail.getText(), 
            this.txtFieldHomePhone.getText(), 
            this.txtFieldWorkPhone.getText()
        );
        this.integrantUpdated.setSchooling(tableViewSchooling.getItems());
    }
    
    private Schooling getOutSchooling(){
        Schooling schooling = new Schooling(
            this.txtFieldStudyDegree.getText(), 
            this.txtFieldStudyName.getText(), 
            this.datePickerDateStudy.getValue().toString(), 
            ValidatorForm.convertJavaDateToSQlDate(datePickerDateStudy), 
            this.txtFieldState.getText(), 
            this.txtFieldCeduleNumber.getText(), 
            this.txtFieldStudyArea.getText(), 
            this.txtFieldDiscipline.getText()
        );
        return schooling;
    }
    
}