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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
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
    private Button btnUpdate;
    
    @FXML
    private TextField txtFieldTitleProject;
    @FXML
    private TextField txtFieldLgacAssociate;
    @FXML
    private DatePicker dtpStartDate;
    @FXML
    private DatePicker dtpEstimatedEndDate;
    @FXML
    private CheckBox chBoxEndDate;
    @FXML
    private DatePicker dtpEndDate;
    @FXML
    private TextField txtFieldDuration;
    @FXML
    private TextField txtFieldStatus;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private HBox hboxProjectOptions;
    
    private final ProjectDAO PROJECT_DAO = new ProjectDAO();
    private List<Button> optionButtons;
    private Project oldProject  = new Project();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
         optionButtons = Arrays.asList(
            btnUpdate, btnSave, 
            btnExit
        );
        hboxProjectOptions.getChildren().removeAll(optionButtons);
    }    

    public void showProjectSaveForm(){
        hboxProjectOptions.getChildren().addAll(btnSave, btnExit);
    }
    
     public void showProjectUpdateForm(Project project){
        oldProject = project;
        this.txtFieldTitleProject.setText(project.getProjectName());
        this.txtFieldLgacAssociate.setText(project.getBodyAcademyKey());
        this.txtFieldDuration.setText(Integer.toString(project.getDurationProjectInMonths()));
        this.dtpStartDate.setValue(LocalDate.parse(project.getStartDate()));
        this.dtpEstimatedEndDate.setValue(LocalDate.parse(project.getEstimatedEndDate()));
        this.txtFieldStatus.setText(project.getStatus());
        this.txtAreaDescription.setText(project.getDescription());
        //this.dtpEndDate.setValue(LocalDate.parse(project.getEndDate()));
        hboxProjectOptions.getChildren().addAll(btnUpdate,  btnExit);
    }
     
    @FXML
    private void saveProject(ActionEvent event){
//        try{
//            this.isValidForm();
            Project project = new Project(
                txtFieldTitleProject.getText(), 
                "UV-CA-127",
                Integer.parseInt(txtFieldDuration.getText()),
                txtFieldStatus.getText(),
                ValidatorForm.convertJavaDateToSQlDate(dtpStartDate),
                null, 
                ValidatorForm.convertJavaDateToSQlDate(dtpEstimatedEndDate),
                txtAreaDescription.getText()
            );
            PROJECT_DAO.addProject(project);
            GenericWindowDriver.getGenericWindowDriver().showInfoAlert(event, "Proyecto registrado correctamente");
//        }catch(InvalidFormException ie){
//            AlertGenerator.showErrorAlert(event, ie.getMessage());
//        }
    }
    
//    public void isValidForm() throws InvalidFormException{
//        ValidatorForm.chechkAlphabeticalField(txtFieldTitleProject, 80);
//        ValidatorForm.isNumberData(txtFieldDuration);
//        ValidatorForm.checkNotEmptyDateField(dtpStartDate);
//        ValidatorForm.checkNotEmptyDateField(dtpEstimatedEndDate);
//        ValidatorForm.chechkAlphabeticalArea(txtAreaDescription, 500);
//    }
    
    public void receiveProjectUpdate(Project project){
        showProjectUpdateForm(project);
    }
    
     public void receiveProjectSave(){
        showProjectSaveForm();
    }

    @FXML
    private void exit(ActionEvent event){
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

    @FXML
    private void updateProyect(ActionEvent event){
//        try{
//            this.isValidForm();
            Project project = new Project(
                txtFieldTitleProject.getText(), 
                "UV-CA-127",
                Integer.parseInt(txtFieldDuration.getText()),
                txtFieldStatus.getText(),
                ValidatorForm.convertJavaDateToSQlDate(dtpStartDate),
                null, 
                ValidatorForm.convertJavaDateToSQlDate(dtpEstimatedEndDate),
                txtAreaDescription.getText()
            );
            PROJECT_DAO.updateProject(project, oldProject.getProjectName());
            GenericWindowDriver.getGenericWindowDriver().showInfoAlert(event, "Proyecto actualizado correctamente");
//        }catch(InvalidFormException ie){
//            AlertGenerator.showErrorAlert(event, ie.getMessage());
//        }
    }
}
