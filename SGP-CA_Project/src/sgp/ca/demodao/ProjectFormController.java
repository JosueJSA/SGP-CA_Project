/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.ProjectDAO;
import sgp.ca.domain.Project;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class ProjectFormController implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private Button btnExit;
    @FXML
    private TextField titleProjectField;
    @FXML
    private TextField lgacAssociateField;
    @FXML
    private DatePicker startDateField;
    @FXML
    private DatePicker estimatedEndDateField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField statusField;
    @FXML
    private TextArea descriptionField;
    
    private final ProjectDAO PROJECT_DAO = new ProjectDAO();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void saveProject(ActionEvent event) {
        try{
            this.isValidForm();
            Project project = new Project(
                titleProjectField.getText(), 
                "UV-CA-127",
                Integer.parseInt(durationField.getText()),
                statusField.getText(),
                ValidatorForm.convertJavaDateToSQlDate(startDateField),
                null, 
                ValidatorForm.convertJavaDateToSQlDate(estimatedEndDateField),
                descriptionField.getText()
            );
            PROJECT_DAO.addProject(project);
            AlertGenerator.showInfoAlert(event, "Proyecto registrado correctamente");
        }catch(InvalidFormException ie){
            AlertGenerator.showErrorAlert(event, ie.getMessage());
        }
    }
    
    public void isValidForm() throws InvalidFormException{
//        ValidatorForm.chechkAlphabeticalField(titleProjectField, 80);
//        ValidatorForm.isNumberData(durationField);
//        ValidatorForm.checkNotEmptyDateField(startDateField);
//        ValidatorForm.checkNotEmptyDateField(estimatedEndDateField);
//        ValidatorForm.chechkAlphabeticalArea(descriptionField, 500);
    }

    @FXML
    private void exit(ActionEvent event) {
        FXMLLoader loader = changeWindow("ProjectList.fxml", event);
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
