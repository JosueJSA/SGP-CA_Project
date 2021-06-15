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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sgp.ca.businesslogic.ReceptionWorkDAO;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.ReceptionWork;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class ReceptionWorkController implements Initializable {

    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnExit;
    @FXML
    private CheckBox chBoxImpactBA;
    @FXML
    private TextField txtFieldReceptionWorkName;
    @FXML
    private TextField txtFieldCountry;
    @FXML
    private TextField txtFieldProject;
    @FXML
    private TextField txtFieldPublicationDate;
    @FXML
    private ListView<String> lvIntegrant;
    @FXML
    private ListView<String> lvCollaborator;
    @FXML
    private TextField txtFieldEstimatedDurationMonth;
    @FXML
    private TextField txtFieldStatus;
    @FXML
    private TextField txtFieldModality;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private Button btnFile;
    @FXML
    private ListView<String> lvStudent;
    @FXML
    private ListView<String> lvRequirements;
    
    private final ReceptionWorkDAO RECEPTIONWORK_DAO = new ReceptionWorkDAO();
    private  ReceptionWork RECEPTIONWORK = new ReceptionWork();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void receiveReceptionWork(String url){                      
        //setReceptionWorkInformation(receptionWorkSelected.getUrlFile());                  /*Usar esto despues de testear*/ 
        setReceptionWorkInformation(url);
    }
    
    private void setReceptionWorkInformation(String receptionWorkUrl){
        RECEPTIONWORK = RECEPTIONWORK_DAO.getEvidenceByUrl(receptionWorkUrl);
        this.chBoxImpactBA.setSelected(RECEPTIONWORK.getImpactAB());
        this.txtFieldReceptionWorkName.setText(RECEPTIONWORK.getEvidenceTitle());
        this.txtFieldCountry.setText(RECEPTIONWORK.getCountry());
        this.txtFieldPublicationDate.setText(RECEPTIONWORK.getPublicationDate());
        this.txtFieldProject.setText(RECEPTIONWORK.getProjectName());
        this.lvIntegrant.setItems(makeItemsIntegrantName(RECEPTIONWORK.getIntegrants()));
        this.lvCollaborator.setItems(makeItemsCollaboratorName(RECEPTIONWORK.getCollaborators()));
        this.lvStudent.setItems(makeItemsStudent(RECEPTIONWORK.getStudents()));
        this.txtFieldEstimatedDurationMonth.setText(Integer.toString(RECEPTIONWORK.getEstimatedDurationInMonths()));
        this.txtFieldStatus.setText(RECEPTIONWORK.getStatus());
        this.txtFieldModality.setText(RECEPTIONWORK.getModality());
        this.txtAreaDescription.setText(RECEPTIONWORK.getDescription());
        this.lvRequirements.setItems(makeItemsRequirements(RECEPTIONWORK.getRequirements()));
    }
    

    @FXML
    private void exit(ActionEvent event) {
    }
    
    private ObservableList<String> makeItemsIntegrantName(List<Integrant> integrantList){
        ObservableList<String> itemsIntegrant= FXCollections.observableArrayList();
        //List<Integrant> integrant = RECEPTIONWORK_DAO.ge;
        //integrantList.forEach(itemsIntegrant.add(new integrant;
        for(Integrant integrant : integrantList){
            itemsIntegrant.add(integrant.getFullName());
        }
        return itemsIntegrant;
    }
    
    private ObservableList<String> makeItemsCollaboratorName(List<Collaborator> collaboratorList){
        ObservableList<String> itemsCollaborator= FXCollections.observableArrayList();
        //List<Integrant> integrant = RECEPTIONWORK_DAO.ge;
        //integrantList.forEach(itemsIntegrant.add(new integrant;
        for(Collaborator collaborator : collaboratorList){
            itemsCollaborator.add(collaborator.getFullName());
        }
        return itemsCollaborator;
    }
    
    private ObservableList<String> makeItemsStudent(List<String> studentList){
        ObservableList<String> itemsStudent= FXCollections.observableArrayList();
        //List<Integrant> integrant = RECEPTIONWORK_DAO.ge;
        //integrantList.forEach(itemsIntegrant.add(new integrant;
        for(String student : studentList){
            itemsStudent.add(student);
        }
        return itemsStudent;
    }
    
    private ObservableList<String> makeItemsRequirements(List<String> requirementstList){
        ObservableList<String> itemsRequirements= FXCollections.observableArrayList();
        //List<Integrant> requirements = RECEPTIONWORK_DAO.ge;
        //integrantList.forEach(itemsIntegrant.add(new integrant;
        for(String requirements : requirementstList){
            itemsRequirements.add(requirements);
        }
        return itemsRequirements;
    }

    @FXML
    private void updateReceptionWork(ActionEvent event) {
    }
}
