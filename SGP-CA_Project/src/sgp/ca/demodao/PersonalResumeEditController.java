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
public class PersonalResumeEditController implements Initializable {

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnCancel;
    @FXML
    private TextField fieldRfc;
    @FXML
    private TextField fieldFullName;
    @FXML
    private TextField fieldEmailUV;
    @FXML
    private TextField fieldStatus;
    @FXML
    private TextField fieldCurp;
    @FXML
    private TextField fieldNationality;
    @FXML
    private TextField fieldEducationalProgram;
    @FXML
    private TextField fieldStaffNumber;
    @FXML
    private TextField fieldCellPhone;
    @FXML
    private TextField fieldWorkPhone;
    @FXML
    private TextField fieldHomePhone;
    @FXML
    private TextField fieldBodyAcademyKey;
    @FXML
    private TextField fieldAppointment;
    @FXML
    private TextField fieldParticipationType;
    @FXML
    private TextField fieldAditionalEmail;
    @FXML
    private DatePicker datePickerRegistrationDate;
    @FXML
    private PasswordField fieldPassword;
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
    private TextField fieldStudyDegree;
    @FXML
    private TextField fieldStudyName;
    @FXML
    private TextField fieldInstitutionSchooling;
    @FXML
    private TextField fieldState;
    @FXML
    private TextField fieldIDCedule;
    @FXML
    private TextField fieldArea;
    @FXML
    private TextField fieldDiscipline;
    @FXML
    private DatePicker datePickerDateStudy;
    @FXML
    private Label lblUserName;
    
    private Integrant integrantToken;
    private Integrant integrantUpdated;
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.preprareSchoolingTable();
        this.btnRemoveSchooling.setDisable(true);
    }

    public void receiveIntegrantToken(Integrant integrant){
        this.integrantToken = integrant;
        this.lblUserName.setText(this.integrantToken.getFullName());
        this.setIntegrantDataIntoInterface();
    }

    @FXML
    private void updatePersonalResume(ActionEvent event) {
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
        tableViewSchooling.getItems().add(this.getOutSchooling());
    }

    @FXML
    private void removeSchooling(ActionEvent event) {
        tableViewSchooling.getItems().remove(
            tableViewSchooling.getSelectionModel().getSelectedItem()
        );
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
            this.fieldAditionalEmail.setText(integrant.getAditionalMail());
            this.fieldAppointment.setText(integrant.getAppointmentMember());
            this.fieldBodyAcademyKey.setText(integrant.getBodyAcademyKey());
            this.fieldCellPhone.setText(integrant.getCellphone());
            this.fieldCurp.setText(integrant.getCurp());
            this.fieldEducationalProgram.setText(integrant.getEducationalProgram());
            this.fieldEmailUV.setText(integrant.getEmailUV());
            this.fieldFullName.setText(integrant.getFullName());
            this.fieldHomePhone.setText(integrant.getHomePhone());
            this.fieldNationality.setText(integrant.getNationality());
            this.fieldParticipationType.setText(integrant.getParticipationType());
            this.fieldPassword.setText(integrant.getPassword());
            this.fieldRfc.setText(integrant.getRfc());
            this.fieldStaffNumber.setText( String.valueOf(integrant.getStaffNumber()));
            this.fieldStatus.setText(integrant.getParticipationStatus());
            this.fieldWorkPhone.setText(integrant.getWorkPhone());
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
            this.fieldRfc.getText(), 
            this.fieldFullName.getText(), 
            this.fieldEmailUV.getText(), 
            "Activo", 
            this.fieldPassword.getText(), 
            this.fieldCurp.getText(), 
            this.fieldParticipationType.getText(), 
            this.fieldNationality.getText(), 
            this.datePickerRegistrationDate.getValue().toString(), 
            this.fieldEducationalProgram.getText(), 
            Integer.parseInt(this.fieldStaffNumber.getText()), 
            this.fieldCellPhone.getText(), 
            this.fieldBodyAcademyKey.getText(), 
            this.fieldAppointment.getText(), 
            this.fieldAditionalEmail.getText(), 
            this.fieldHomePhone.getText(), 
            this.fieldWorkPhone.getText()
        );
        this.integrantUpdated.setSchooling(tableViewSchooling.getItems());
    }
    
    private Schooling getOutSchooling(){
        Schooling schooling = new Schooling(
            this.fieldStudyDegree.getText(), 
            this.fieldStudyName.getText(), 
            this.datePickerDateStudy.getValue().toString(), 
            ValidatorForm.convertJavaDateToSQlDate(datePickerDateStudy), 
            this.fieldState.getText(), 
            this.fieldIDCedule.getText(), 
            this.fieldArea.getText(), 
            this.fieldDiscipline.getText()
        );
        return schooling;
    }
    
}
