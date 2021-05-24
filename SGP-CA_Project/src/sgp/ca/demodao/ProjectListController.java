/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.ProjectDAO;
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
    private TextField searchField;
    @FXML
    private TableView<Project> projectsTableView;
    @FXML
    private TableColumn<Project, String> columnNameProject;
    @FXML
    private TableColumn<Project, String> columnDuration;
    @FXML
    private TableColumn<Project, String> columnStatus;
    @FXML
    private TableColumn<Project, String> columnStartDate;
    @FXML
    private TableColumn<Project, String> columnEndDate;

    private final ProjectDAO PROJECT = new ProjectDAO();
    @FXML
    private DatePicker searchDateField;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Project initializeProject = new Project();
        
        this.columnNameProject.setCellValueFactory(new PropertyValueFactory ("projectName"));
        this.columnDuration.setCellValueFactory(new PropertyValueFactory ("durationProjectInMonths"));
        this.columnStatus.setCellValueFactory(new PropertyValueFactory ("status"));
        this.columnStartDate.setCellValueFactory(new PropertyValueFactory ("startDate"));
        this.columnEndDate.setCellValueFactory(new PropertyValueFactory ("endDate"));
        projectsTableView.setItems(FXCollections.observableArrayList(PROJECT.getProjectList()));
    }    

    @FXML
    private void addProject(ActionEvent event) {
        FXMLLoader loader = changeWindow("ProjectForm.fxml", event);
    }

    @FXML
    private void exit(ActionEvent event) {
        
    }
    
    @FXML
    private void searchProject(ActionEvent event) {
        List<Project> result = PROJECT.getProjectListbyName(searchField.getText());
        if (searchField.getText().isEmpty()){
            result = PROJECT.getProjectList();
        }
        ObservableList<Project> itemsProject = FXCollections.observableArrayList(result);
        projectsTableView.setItems(itemsProject);
    }

    @FXML
    private void selectProject(MouseEvent event) {
        Project projectSelected = this.projectsTableView.getSelectionModel().getSelectedItem();
        if (projectSelected != null){
            FXMLLoader loader = changeWindow("Project.fxml", event);
            ProjectController controller = loader.getController();
            controller.receiveProject(projectSelected);
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
