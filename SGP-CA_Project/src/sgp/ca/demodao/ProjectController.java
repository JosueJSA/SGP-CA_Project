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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.ProjectDAO;
import sgp.ca.businesslogic.EvidenceDAO;
import sgp.ca.businesslogic.PrototypeDAO;
import sgp.ca.businesslogic.ReceptionWorkDAO;
import sgp.ca.domain.Evidence;
import sgp.ca.domain.Project;
import sgp.ca.domain.Prototype;
import sgp.ca.domain.ReceptionWork;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class ProjectController implements Initializable {

     @FXML
    private Button btnModify;
    @FXML
    private Button btnExit;
    
    @FXML
    private TextField txtFieldProjectName;
    @FXML
    private TextField txtFieldLgacAssociate;
    @FXML
    private TextField txtFieldDuration;
    @FXML
    private TextField txtFieldStartDate;
    @FXML
    private TextField txtFieldEstimatedEndDate;
    @FXML
    private TextField txtFieldEndDate;
    @FXML
    private TextField txtFieldStatus;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private TableView<Evidence> tvEvidence;
    @FXML
    private TableColumn<Evidence, String> colEvidenceName;
    @FXML
    private TableColumn<Evidence, String> colPublicationDate;
    @FXML
    private TableColumn<Evidence, String> colImpactBA;
    
    private final ProjectDAO PROJECT_DAO = new ProjectDAO();
    private final ReceptionWorkDAO RECEPTIONWORK_DAO = new ReceptionWorkDAO();
    private  Project PROJECT = new Project();
    private final PrototypeDAO PROTOTYPE_DAO = new PrototypeDAO();
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.colEvidenceName.setCellValueFactory(new PropertyValueFactory ("evidenceTitle"));
        this.colPublicationDate.setCellValueFactory(new PropertyValueFactory ("registrationDate"));
        this.colImpactBA.setCellValueFactory(new PropertyValueFactory ("impactBA"));
        tvEvidence.setItems(makeItemsEvidence());
    }    
    
    public void receiveProject(Project projectSelected){
        setProjectInformation(projectSelected.getProjectName());
    }
    
    private void setProjectInformation(String projectName){
        PROJECT = PROJECT_DAO.getProjectbyName(projectName);
        this.txtFieldProjectName.setText(PROJECT.getProjectName());
        this.txtFieldLgacAssociate.setText(PROJECT.getBodyAcademyKey());
        this.txtFieldDuration.setText(Integer.toString(PROJECT.getDurationProjectInMonths()));
        this.txtFieldStartDate.setText(PROJECT.getStartDate());
        this.txtFieldEstimatedEndDate.setText(PROJECT.getEstimatedEndDate());
        this.txtFieldEndDate.setText(PROJECT.getEndDate());
        this.txtFieldStatus.setText(PROJECT.getStatus());
        this.txtAreaDescription.setText(PROJECT.getDescription());
    }
    
    private ObservableList<Evidence> makeItemsEvidence(){
        ObservableList<Evidence> itemsEvidence = FXCollections.observableArrayList();
        List<ReceptionWork> receptionWork = RECEPTIONWORK_DAO.getReceptionWorkListForEvidence();
        //List<Prototype> prototype = PROTOTYPE_DAO.getPrototypeForEvidence();                          /*Esto moudulo se encuentra en construcción */                 
        for(int i = 0; i < receptionWork.size(); i++){
            Evidence evidenceTable = receptionWork.get(i);
            itemsEvidence.add(evidenceTable);
        }
        return itemsEvidence;
    }

    @FXML
    private void modiftyProject(ActionEvent event) {
        FXMLLoader loader = changeWindow("ProjectForm.fxml", event);
        ProjectFormController controller = loader.getController();
        controller.receiveProjectUpdate(PROJECT);
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
