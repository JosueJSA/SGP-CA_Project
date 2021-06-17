/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sgp.ca.businesslogic.ProjectDAO;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Project;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class ProjectListController implements Initializable {

    @FXML
    private Button btnAddIntegrant;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField txtFieldSearch;
    @FXML
    private DatePicker dtpSearchDate;
    @FXML
    private TableView<Project> tvProjects;
    @FXML
    private TableColumn<Project, String> colNameProject;
    @FXML
    private TableColumn<Project, String> colDuration;
    @FXML
    private TableColumn<Project, String> colStatus;
    @FXML
    private TableColumn<Project, String> colStartDate;
    @FXML
    private TableColumn<Project, String> colEndDate;
    @FXML
    private Label lbUserName;
    
    private Integrant token;
    private final ProjectDAO PROJECT = new ProjectDAO();    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Project initializeProject = new Project();
    }    
    
    public void receiveToken(Integrant integrantToken){
        this.token = integrantToken;
        this.lbUserName.setText(integrantToken.getFullName());
        List<Project> projects = PROJECT.getProjectList();
        if(projects != null){
            this.prepareTableFormat();
            tvProjects.setItems(FXCollections.observableArrayList(projects));
        }else{
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(new ActionEvent(), "Lo sentimos, el sistema no está disponible, favor de contactar a soporte técnico");
            FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("Start.fxml", btnExit);
            StartController controller = loader.getController();
            controller.receiveIntegrantToken(token);
        }
    }

    @FXML
    private void addProject(ActionEvent event) {
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("ProjectForm.fxml", btnExit);
        ProjectFormController controller = loader.getController();
        controller.receiveProjectSave();
    }

    @FXML
    private void exit(ActionEvent event) {
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("Start.fxml", btnExit);
        StartController controller = loader.getController();
        controller.receiveIntegrantToken(token);
    }
    
    @FXML
    private void searchProject(ActionEvent event) {
        List<Project> result = PROJECT.getProjectListbyName(txtFieldSearch.getText());
        if (txtFieldSearch.getText().isEmpty()){
            result = PROJECT.getProjectList();
        }
        ObservableList<Project> itemsProject = FXCollections.observableArrayList(result);
        tvProjects.setItems(itemsProject);
    }

    @FXML
    private void selectProject(MouseEvent event) {
        Project projectSelected = this.tvProjects.getSelectionModel().getSelectedItem();
        if (projectSelected != null){
            FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("Project.fxml", btnExit);
            ProjectController controller = loader.getController();
            controller.showProject(projectSelected, this.token);
        }
    }
    
    private void prepareTableFormat(){
        this.colNameProject.setCellValueFactory(new PropertyValueFactory ("projectName"));
        this.colDuration.setCellValueFactory(new PropertyValueFactory ("durationProjectInMonths"));
        this.colStatus.setCellValueFactory(new PropertyValueFactory ("status"));
        this.colStartDate.setCellValueFactory(new PropertyValueFactory ("startDate"));
        this.colEndDate.setCellValueFactory(new PropertyValueFactory ("endDate"));
    }
    
}
