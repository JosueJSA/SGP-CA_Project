/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import sgp.ca.dataaccess.FtpClient;
import javafx.stage.Stage;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.businesslogic.ProjectDAO;
import sgp.ca.businesslogic.ReceptionWorkDAO;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Project;
import sgp.ca.domain.ReceptionWork;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class ReceptionWorkFormController implements Initializable {

    @FXML
    private CheckBox chBoxImpactBA;
    @FXML
    private TextField txtFieldReceptionWorkName;
    @FXML
    private DatePicker dtpPublicationDate;
    @FXML
    private TextField txtFieldCountry;
    @FXML
    private ComboBox<String> cboxProject;
    @FXML
    private TableView<IntegrantTable> tvIntegrant;
    @FXML
    private TableColumn<IntegrantTable, String> colIntegrantName;
    @FXML
    private TableColumn<IntegrantTable, RadioButton> colIntegrantParticipation;
    @FXML
    private TableView<CollaboratorTable> tvCollaborator;
    @FXML
    private TableColumn<CollaboratorTable, String> colCollaboratorName;
    @FXML
    private TableColumn<CollaboratorTable, RadioButton> colCollaboratorParticipation;
    @FXML
    private TextField txtFieldStudent;
    @FXML
    private Button btnAddStudent;
    @FXML
    private ListView<String> lvStudent;
    @FXML
    private TextField txtFieldEstimatedDurationMonth;
    @FXML
    private TextField txtFieldStatus;
    @FXML
    private ComboBox<String> cboBoxModality;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private TextField txtFieldRequirements;
    @FXML
    private Button btnRequirements;
    @FXML
    private ListView<String> lvRequirements;
    @FXML
    private Button btnUploadFile;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnExit;
    @FXML
    private HBox hboxReceptionOptions;
    
    private final ReceptionWorkDAO RECEPTIONWORK_DAO = new ReceptionWorkDAO();
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    private final ProjectDAO PROJECT_DAO = new ProjectDAO();
    private List<Button> optionButtons;
    private DialogBox TESTBOX;
    String FILE = null;
    private ObservableList<String> MODALITYLIST = FXCollections.observableArrayList("Tesis", "Tesina");
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        colIntegrantName.setCellValueFactory(new PropertyValueFactory<IntegrantTable, String>("integrantName"));
        colIntegrantParticipation.setCellValueFactory(new PropertyValueFactory<IntegrantTable, RadioButton>("participation"));
        tvIntegrant.setItems(makeitemsIntegrant());
        colCollaboratorName.setCellValueFactory(new PropertyValueFactory<CollaboratorTable, String>("collaboratorName"));
        colCollaboratorParticipation.setCellValueFactory(new PropertyValueFactory<CollaboratorTable, RadioButton>("participation"));
        tvCollaborator.setItems(makeitemsCollaborator());
        cboxProject.setItems(makeitemsProject());
        cboBoxModality.setItems(MODALITYLIST);
        optionButtons = Arrays.asList(
            btnUpdate, btnSave, 
            btnExit
        );
        hboxReceptionOptions.getChildren().removeAll(optionButtons);
        hboxReceptionOptions.getChildren().addAll(btnSave, btnExit);
    }    

    @FXML
    private void saveReceptionWork(ActionEvent event){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        List<String> listRequirements = new ArrayList<>();
//         try{
//            this.isValidForm();
            ReceptionWork receptionWork = new ReceptionWork(
                "PRUEBA3",
                cboxProject.getValue(),
                chBoxImpactBA.isSelected(),
                "Trabajo Recepcional",
                txtFieldReceptionWorkName.getText(),
                "Responsable",
                dateFormat.format(date),
                "Estudios",
                ValidatorForm.convertJavaDateToSQlDate(dtpPublicationDate),
                txtFieldCountry.getText(),
                txtAreaDescription.getText(),
                txtFieldStatus.getText(),
                0,
                Integer.parseInt(txtFieldEstimatedDurationMonth.getText()),
                cboBoxModality.getValue()
            );
            receptionWork.setRequirements(lvRequirements.getItems());
            receptionWork.setStudents(lvStudent.getItems());
            receptionWork.setIntegrants(IntegrantList());
            receptionWork.setCollaborators(CollaboratorList());
            RECEPTIONWORK_DAO.addNewEvidence(receptionWork);
            AlertGenerator.showInfoAlert(event, "Proyecto registrado correctamente");
//        }catch(InvalidFormException ie){
//            AlertGenerator.showErrorAlert(event, ie.getMessage());
//        }
    }

    @FXML
    private void exit(ActionEvent event){
        FXMLLoader loader = changeWindow("ReceptionWork.fxml", event);          /*Solo para testear*/
        ReceptionWorkController controller = loader.getController();
        String url = "PRUEBA3";
        controller. receiveReceptionWork(url);
       
    }
    
//    public void isValidForm() throws InvalidFormException{
//        //ValidatorForm.chechkAlphabeticalString(FILE, 100);
//        ValidatorForm.chechkAlphabeticalField(txtFieldReceptionWorkName, 80);
//        ValidatorForm.checkNotEmptyDateField(dtpPublicationDate);
//        ValidatorForm.chechkAlphabeticalField(txtFieldCountry, 40);
//        ValidatorForm.isComboBoxSelected(cboxProject);
//        ValidatorForm.isNumberData(txtFieldEstimatedDurationMonth);
//        ValidatorForm.chechkAlphabeticalField(txtFieldStatus, 10);
//        ValidatorForm.isComboBoxSelected(cboBoxModality);
//        ValidatorForm.chechkAlphabeticalArea(txtAreaDescription, 500);
//    }
    
    private ObservableList<IntegrantTable> makeitemsIntegrant(){
        ObservableList<IntegrantTable> itemsIntegrant = FXCollections.observableArrayList();
        List<Integrant> integrantsRfcName = INTEGRANT_DAO.getAllIntegrantsRfcName();
        for(int i = 0; i < integrantsRfcName.size(); i++){
            IntegrantTable participationIntegrantTable = new IntegrantTable(integrantsRfcName.get(i).getRfc(), integrantsRfcName.get(i).getFullName());
            itemsIntegrant.add(participationIntegrantTable);
        }
        return itemsIntegrant;
    }
    
    private ObservableList<CollaboratorTable> makeitemsCollaborator(){
        ObservableList<CollaboratorTable> itemsCollaborator = FXCollections.observableArrayList();
        List<Collaborator> collaboratorRfcName = COLLABORATOR_DAO.getAllCollaboratorsRfcName();
        for(int i = 0; i < collaboratorRfcName.size(); i++){
            CollaboratorTable participationCollaboratorTable = new CollaboratorTable(collaboratorRfcName.get(i).getRfc(), collaboratorRfcName.get(i).getFullName());
            itemsCollaborator.add(participationCollaboratorTable);
        }
        return itemsCollaborator;
    }

    private ObservableList<String> makeitemsProject(){
        ObservableList<String> itemsProject = FXCollections.observableArrayList();
        List<Project> projectList = PROJECT_DAO.getProjectList();
        for(int i = 0; i < projectList.size(); i++){
            String projectName = projectList.get(i).getProjectName();
            itemsProject.add(projectName);
        }
        return itemsProject;
    }
    
    private ObservableList<String> makeitemsModality(){
        ObservableList<String> itemsModality = FXCollections.observableArrayList();
        itemsModality.add("Tesis");
        itemsModality.add("Tesina");
        return itemsModality;
    }
    
    @FXML
    private void addStudent(ActionEvent event){
        List<String> itemsStudent = new ArrayList<>();
        itemsStudent.add(txtFieldStudent.getText());
        lvStudent.getItems().addAll(itemsStudent);
        txtFieldStudent.setText("");
    }
    
    @FXML
    private void addRequirements(ActionEvent event){
        List<String> itemsRequirements = new ArrayList<>();
        itemsRequirements.add(txtFieldRequirements.getText());
        lvRequirements.getItems().addAll(itemsRequirements);
        txtFieldRequirements.setText("");;
    }

    @FXML
    private void uploadFIle(ActionEvent event){
        TESTBOX = new DialogBox( ((Stage)((Node)event.getSource()).getScene().getWindow()) );
        FtpClient connection = new FtpClient();
        FILE = connection.saveFileIntoFilesSystem(TESTBOX.getFileSelectedPath(), TESTBOX.getFileNameSelected());
        System.out.println("Terminé de guardar");
    }
   
    private List<Integrant> IntegrantList(){
        List<Integrant> itemsIntegrantSelected = new ArrayList<>();
        for(IntegrantTable integrantTable : tvIntegrant.getItems()){
            if(integrantTable.getParticipation().isSelected()){
               itemsIntegrantSelected.add(new Integrant(integrantTable.getIntegrantRfc(), integrantTable.getIntegrantName()));
               //System.out.println(integrantTable.getIntegrantRfc());
            }
        }
        return itemsIntegrantSelected;
    }
    
    private List<Collaborator> CollaboratorList(){
        List<Collaborator> itemsCollaboratorSelected = new ArrayList<>();
        for(CollaboratorTable collaboratorTable : tvCollaborator.getItems()){
            if(collaboratorTable.getParticipation().isSelected()){
               itemsCollaboratorSelected.add(new Collaborator(collaboratorTable.getCollaboratorRfc(), collaboratorTable.getCollaboratorName()));
               //System.out.println(collaboratorTable.getCollaboratorName());
            }
        }
        return itemsCollaboratorSelected;
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
