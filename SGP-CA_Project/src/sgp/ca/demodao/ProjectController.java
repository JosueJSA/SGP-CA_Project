/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Button btn;
    @FXML
    private TextField projectNameField;
    @FXML
    private TextField lgacAssociateFIeld;
    @FXML
    private TextField durationField;
    @FXML
    private TextField startDateField;
    @FXML
    private TextField estimatedEndDateField;
    @FXML
    private TextField endDateField;
    @FXML
    private TextField statusField;
    @FXML
    private TextArea descriptionField;
    @FXML
    private Button btnSearch;
    @FXML
    private TextField searchEvidenceField;
    @FXML
    private DatePicker searchDateFIeld;
    @FXML
    private TableView<Evidence> evidenceTableView;
    @FXML
    private TableColumn<Evidence, String> columnEvidenceName;
    @FXML
    private TableColumn<Evidence, String> columnPublicationDate;
    @FXML
    private TableColumn<Evidence, String> columnImpactBA;
    
    private final ProjectDAO PROJECT_DAO = new ProjectDAO();
    private final ReceptionWorkDAO RECEPTIONWORK_DAO = new ReceptionWorkDAO();
    private final PrototypeDAO PROTOTYPE_DAO = new PrototypeDAO();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.columnEvidenceName.setCellValueFactory(new PropertyValueFactory ("evidenceTitle"));
        this.columnPublicationDate.setCellValueFactory(new PropertyValueFactory ("registrationDate"));
        this.columnImpactBA.setCellValueFactory(new PropertyValueFactory ("impactBA"));
        evidenceTableView.setItems(makeItemsEvidence());
    }    
    
    public void receiveProject(Project projectSelected){
        setProjectInformation(projectSelected.getProjectName());
    }
    
    private void setProjectInformation(String projectName){
        Project source = PROJECT_DAO.getProjectbyName(projectName);
        this.projectNameField.setText(source.getProjectName());
        this.lgacAssociateFIeld.setText(source.getBodyAcademyKey());
        this.durationField.setText(Integer.toString(source.getDurationProjectInMonths()));
        this.startDateField.setText(source.getStartDate());
        this.estimatedEndDateField.setText(source.getEstimatedEndDate());
        this.endDateField.setText(source.getEndDate());
        this.statusField.setText(source.getStatus());
        this.descriptionField.setText(source.getDescription());
    
        
        
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
    
}
