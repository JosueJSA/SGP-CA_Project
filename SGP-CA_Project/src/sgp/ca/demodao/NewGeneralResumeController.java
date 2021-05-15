/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sgp.ca.businesslogic.GeneralResumeDAO;
import sgp.ca.domain.GeneralResume;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class NewGeneralResumeController implements Initializable {

    @FXML
    private TextField bodyAcademyNameField;
    @FXML
    private TextField bdyAcademyKeyField;
    @FXML
    private TextField areaAscriptionFiel;
    @FXML
    private TextField ascriptionUnitField;
    @FXML
    private Button btnSignUpBodyAcademy;
    @FXML
    private Button btnCancelRegistration;
    @FXML
    private DatePicker dateFieldRegistration;
    @FXML
    private DatePicker dateFieldLastEvaluation;
    @FXML
    private TableView<String> tableViewLgac;
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
    private ComboBox<String> cmboxConsolidationDegree;

    private final GeneralResumeDAO GENERAL_RESUME = new GeneralResumeDAO();
    private List<TextField> fields;
    private List<TextArea> textAreas;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmboxConsolidationDegree.getItems().add("En formación");
        cmboxConsolidationDegree.getItems().add("En consolidación");
        cmboxConsolidationDegree.getItems().add("Consolidado");
        fields  = new ArrayList<>();
        textAreas = new ArrayList<>();
        fields.add(bodyAcademyNameField);
        fields.add(bdyAcademyKeyField);
        fields.add(areaAscriptionFiel);
        fields.add(ascriptionUnitField);
        textAreas.add(txtAreaGeneralTarget);
        textAreas.add(txtAreaMission);
        textAreas.add(txtAreaVision);
    }    

    @FXML
    private void signUpBodyAcademy(ActionEvent event) {
        try{
            this.isValidForm();
            GeneralResume generalResume = new GeneralResume(
                bdyAcademyKeyField.getText(), 
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
            GENERAL_RESUME.addGeneralResume(generalResume);
            AlertGenerator.showInfoAlert(event, "Cuerpo Académico dado de alta correctamente");
            this.changeWindow("Login.fxml", event);
        }catch(InvalidFormException ie){
            AlertGenerator.showErrorAlert(event, ie.getMessage());
        }
    }

    @FXML
    private void signUpCancelRegistration(ActionEvent event) {
        Optional<ButtonType> action = AlertGenerator.showConfirmacionAlert(event, "¿Seguro que deseas anular el registro del cuerpo académico?");
        if(action.get() == ButtonType.OK){
            this.changeWindow("Login.fxml", event);
        }
    }

    @FXML
    private void addLgac(ActionEvent event) {
        
    }

    @FXML
    private void removeLgac(ActionEvent event) {
    }
    
    public void isValidForm() throws InvalidFormException{
        ValidatorForm.checkAlaphabeticalFields(fields, 100);
        ValidatorForm.checkAlaphabeticalTextAreas(textAreas, 5000);
        ValidatorForm.checkNotEmptyDateField(dateFieldRegistration);
        ValidatorForm.checkNotEmptyDateField(dateFieldLastEvaluation);
        ValidatorForm.isComboBoxSelected(cmboxConsolidationDegree);
        this.isBodyAcademyKeyRegistered();
    }
    
    public void isBodyAcademyKeyRegistered() throws InvalidFormException{
        if(GENERAL_RESUME.getGeneralResumeKeys().contains(bdyAcademyKeyField.getText())){
            bdyAcademyKeyField.setStyle("-fx-border-color: red;");
            throw new InvalidFormException("La clave del cuerpo académico ya existe");
        }else{
            bdyAcademyKeyField.setStyle("-fx-border-color: green;");
        }
    }
    
    public void changeWindow(String window, ActionEvent event){
        Stage stage = new Stage();
        Parent root;
        try{
            root = FXMLLoader.load(getClass().getResource(window));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        }catch(IOException ex){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
