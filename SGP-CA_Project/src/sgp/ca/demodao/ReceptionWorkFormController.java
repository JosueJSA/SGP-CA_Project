/*
* @author Johann
* @versión v1.0
* Last modification date: 17-06-2021
*/
package sgp.ca.demodao;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import sgp.ca.dataaccess.FtpClient;
import javafx.stage.Stage;
import sgp.ca.businesslogic.CollaboratorDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.businesslogic.ProjectDAO;
import sgp.ca.businesslogic.ReceptionWorkDAO;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Member;
import sgp.ca.domain.Project;
import sgp.ca.domain.ReceptionWork;

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
    private HBox hbReceptionOptions;
    
    private final ReceptionWorkDAO RECEPTIONWORK_DAO = new ReceptionWorkDAO();
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final CollaboratorDAO COLLABORATOR_DAO = new CollaboratorDAO();
    private final ProjectDAO PROJECT_DAO = new ProjectDAO();
    private List<Button> optionButtons;
    private DialogBox TESTBOX;
    private String FILE = null;
    private Integrant token;
    private String UrlReception;
    private final ObservableList<String> MODALITYLIST = FXCollections.observableArrayList("Tesis", "Tesina", "Memoria", "Proyecto de Inversion", "Reporte");
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        colIntegrantName.setCellValueFactory(new PropertyValueFactory<IntegrantTable, String>("integrantName"));
        colIntegrantParticipation.setCellValueFactory(new PropertyValueFactory<IntegrantTable, RadioButton>("participation"));
        colCollaboratorName.setCellValueFactory(new PropertyValueFactory<CollaboratorTable, String>("collaboratorName"));
        colCollaboratorParticipation.setCellValueFactory(new PropertyValueFactory<CollaboratorTable, RadioButton>("participation"));
        cboxProject.setItems(makeitemsProject());
        cboBoxModality.setItems(MODALITYLIST);
        optionButtons = Arrays.asList(
            btnUpdate, btnSave, 
            btnExit
        );
        hbReceptionOptions.getChildren().removeAll(optionButtons);        
    }    

    public void showReceptionWorkSaveForm(){
        hbReceptionOptions.getChildren().addAll(btnSave, btnExit);
    }
    
    public void showReceptionWorkUpdateForm(String receptionWorkUrl){
        hbReceptionOptions.getChildren().addAll(btnUpdate, btnExit);
        ReceptionWork receptionWork = RECEPTIONWORK_DAO.getEvidenceByUrl(receptionWorkUrl);
        this.chBoxImpactBA.setSelected(receptionWork.getImpactAB());
        this.txtFieldReceptionWorkName.setText(receptionWork.getEvidenceTitle());
        this.txtFieldCountry.setText(receptionWork.getCountry());
        this.dtpPublicationDate.setValue(LocalDate.parse(receptionWork.getPublicationDate()));
        this.cboxProject.setValue(receptionWork.getProjectName());
        tvIntegrant.setItems(makeitemsIntegrant());
        tvCollaborator.setItems(makeitemsCollaborator());
        this.lvStudent.setItems(makeItemsStudent(receptionWork.getStudents()));
        this.txtFieldEstimatedDurationMonth.setText(Integer.toString(receptionWork.getEstimatedDurationInMonths()));
        this.txtFieldStatus.setText(receptionWork.getStatus());
        this.cboBoxModality.setValue(receptionWork.getModality());
        this.txtAreaDescription.setText(receptionWork.getDescription());
        this.lvRequirements.setItems(makeItemsRequirements(receptionWork.getRequirements()));
    }
    
    public void receiveReceptionWorkUpdateToken(ReceptionWork receptionWork, Integrant integrantToken){
        this.token = integrantToken;
        this.UrlReception = receptionWork.getUrlFile();
        showReceptionWorkUpdateForm(receptionWork.getUrlFile());
    }
    
    public void receiveReceptionWorkSaveToken(Integrant integrantToken){
        this.token = integrantToken;
        showReceptionWorkSaveForm();
    }
    
    @FXML
    private void saveReceptionWork(ActionEvent event){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        List<String> listRequirements = new ArrayList<>();
        if (UrlReception != null){
            try{
                this.isValidForm();
                ReceptionWork receptionWork = new ReceptionWork(
                    UrlReception,
                    cboxProject.getValue(),
                    chBoxImpactBA.isSelected(),
                    "Trabajo Recepcional",
                    txtFieldReceptionWorkName.getText(),
                    token.getFullName(),
                    dateFormat.format(date),
                    token.getSchooling().toString(),
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
                GenericWindowDriver.getGenericWindowDriver().showInfoAlert(event, "Trabajo recepcional registrado correctamente");
            }catch(InvalidFormException ie){
                GenericWindowDriver.getGenericWindowDriver().showErrorAlert(new ActionEvent(), ie.getMessage());
            }
        }else{
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, "No se a subido el archivo de la evidencia");
        } 
    }
    
    @FXML
    private void updateReceptioWork(ActionEvent event){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        List<String> listRequirements = new ArrayList<>();
         try{
            this.isValidForm();
            ReceptionWork receptionWork = new ReceptionWork(
                UrlReception,
                cboxProject.getValue(),
                chBoxImpactBA.isSelected(),
                "Trabajo Recepcional",
                txtFieldReceptionWorkName.getText(),
                token.getFullName(),
                dateFormat.format(date),
                token.getSchooling().toString(),
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
            RECEPTIONWORK_DAO.updateEvidence(receptionWork, UrlReception);
            GenericWindowDriver.getGenericWindowDriver().showInfoAlert(event, "Trabajo recepcional actualizado correctamente");
        }catch(InvalidFormException ie){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(new ActionEvent(), ie.getMessage());
        }
    }

    @FXML
    private void exit(ActionEvent event){
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("EvidenceList.fxml", txtFieldReceptionWorkName);          
        EvidenceListController controller = loader.getController();
        controller.showGeneralResumeEvidences(token);
    }
    
    public void isValidForm() throws InvalidFormException{
        ValidatorForm.chechkAlphabeticalField(txtFieldReceptionWorkName, 5,80);
        ValidatorForm.checkNotEmptyDateField(dtpPublicationDate);
        ValidatorForm.chechkAlphabeticalField(txtFieldCountry, 3,40);
        ValidatorForm.isComboBoxSelected(cboxProject);
        ValidatorForm.isNumberData(txtFieldEstimatedDurationMonth, 2);
        ValidatorForm.chechkAlphabeticalField(txtFieldStatus,3 ,10);
        ValidatorForm.isComboBoxSelected(cboBoxModality);
        ValidatorForm.chechkAlphabeticalArea(txtAreaDescription, 1 ,500);
    }
    
    private ObservableList<IntegrantTable> makeitemsIntegrant(){
        ObservableList<IntegrantTable> itemsIntegrant = FXCollections.observableArrayList();
        List<Member> integrantsRfcName = INTEGRANT_DAO.getMembers(token.getBodyAcademyKey());
        for(int i = 0; i < integrantsRfcName.size(); i++){
            IntegrantTable participationIntegrantTable = new IntegrantTable(integrantsRfcName.get(i).getRfc(), integrantsRfcName.get(i).getFullName());
            itemsIntegrant.add(participationIntegrantTable);
        }
        return itemsIntegrant;
    }
    
    private ObservableList<CollaboratorTable> makeitemsCollaborator(){
        ObservableList<CollaboratorTable> itemsCollaborator = FXCollections.observableArrayList();
        List<Member> collaboratorRfcName = COLLABORATOR_DAO.getMembers(token.getBodyAcademyKey());
        for(int i = 0; i < collaboratorRfcName.size(); i++){
            CollaboratorTable participationCollaboratorTable = new CollaboratorTable(collaboratorRfcName.get(i).getRfc(), collaboratorRfcName.get(i).getFullName());
            itemsCollaborator.add(participationCollaboratorTable);
        }
        return itemsCollaborator;
    }
    
    private ObservableList<String> makeItemsRequirements(List<String> requirementstList){
        ObservableList<String> itemsRequirements= FXCollections.observableArrayList();
        for(String requirements : requirementstList){
            itemsRequirements.add(requirements);
        }
        return itemsRequirements;
    }
    
    private ObservableList<String> makeItemsStudent(List<String> studentList){
        ObservableList<String> itemsStudent= FXCollections.observableArrayList();
        for(String student : studentList){
            itemsStudent.add(student);
        }
        return itemsStudent;
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
        TESTBOX = new DialogBox( ((Stage)((Node)event.getSource()).getScene().getWindow()));
        FtpClient connection = new FtpClient();
        FILE = connection.saveFileIntoFilesSystem(TESTBOX.getFileSelectedPath(), TESTBOX.getFileNameSelected());
        UrlReception = TESTBOX.getFileNameSelected();
    }
   
    private List<Integrant> IntegrantList(){
        List<Integrant> itemsIntegrantSelected = new ArrayList<>();
        for(IntegrantTable integrantTable : tvIntegrant.getItems()){
            if(integrantTable.getParticipation().isSelected()){
               itemsIntegrantSelected.add(new Integrant(integrantTable.getIntegrantRfc(), integrantTable.getIntegrantName()));
            }
        }
        return itemsIntegrantSelected;
    }
    
    private List<Collaborator> CollaboratorList(){
        List<Collaborator> itemsCollaboratorSelected = new ArrayList<>();
        for(CollaboratorTable collaboratorTable : tvCollaborator.getItems()){
            if(collaboratorTable.getParticipation().isSelected()){
               itemsCollaboratorSelected.add(new Collaborator(collaboratorTable.getCollaboratorRfc(), collaboratorTable.getCollaboratorName()));
            }
        }
        return itemsCollaboratorSelected;
    }
}
