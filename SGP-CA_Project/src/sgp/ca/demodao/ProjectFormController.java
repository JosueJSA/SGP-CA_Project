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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import sgp.ca.businesslogic.GeneralResumeDAO;
import sgp.ca.businesslogic.ProjectDAO;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Lgac;
import sgp.ca.domain.Project;

public class ProjectFormController implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField txtFieldProjectName;
    @FXML
    private ComboBox<String> cboBoxLgacAssociate;
    @FXML
    private DatePicker dtpStartDate;
    @FXML
    private DatePicker dtpEstimatedEndDate;
    @FXML
    private DatePicker dtpEndDate;
    @FXML
    private TextField txtFieldDuration;
    @FXML
    private ComboBox<String> cboBoxStatus;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private HBox hbProjectOptions;
    
    private final ProjectDAO PROJECT_DAO = new ProjectDAO();
    private final GeneralResumeDAO GENERALRESUME_DAO = new GeneralResumeDAO();
    private List<Button> optionButtons;
    private Project oldProject  = new Project();
    private Integrant token;
    private final ObservableList<String> STATUSLIST = FXCollections.observableArrayList("Propuesto", "Asignado", "Cancelado", "Terminado");
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
         optionButtons = Arrays.asList(
            btnUpdate, btnSave, 
            btnExit
        );
        hbProjectOptions.getChildren().removeAll(optionButtons);
        cboBoxStatus.setItems(STATUSLIST);
    }    

    public void showProjectSaveForm(){
        hbProjectOptions.getChildren().addAll(btnSave, btnExit);
        makeitemsLgac();
    }
    
    public void showProjectUpdateForm(Project project){
        oldProject = project;
        makeitemsLgac();
        this.txtFieldProjectName.setText(project.getProjectName());
        this.cboBoxLgacAssociate.setValue(project.getLgacs().get(0).getTitle());
        this.txtFieldDuration.setText(Integer.toString(project.getDurationProjectInMonths()));
        this.dtpStartDate.setValue(LocalDate.parse(project.getStartDate()));
        this.dtpEstimatedEndDate.setValue(LocalDate.parse(project.getEstimatedEndDate()));
        this.cboBoxStatus.setValue(project.getStatus());
        this.txtAreaDescription.setText(project.getDescription());
        this.dtpEndDate.setValue(LocalDate.parse(project.getEndDate()));
        hbProjectOptions.getChildren().addAll(btnUpdate,  btnExit);
    }
     
    @FXML
    private void saveProject(ActionEvent event){
        try{
            this.isValidForm();
            checkExistProjectName();
            checkStatusWithDates();
            Project project = new Project(
                txtFieldProjectName.getText(), 
                token.getBodyAcademyKey(),
                Integer.parseInt(txtFieldDuration.getText()),
                cboBoxStatus.getValue(),
                ValidatorForm.convertJavaDateToSQlDate(dtpStartDate),
                optionEndDate(),
                ValidatorForm.convertJavaDateToSQlDate(dtpEstimatedEndDate),
                txtAreaDescription.getText()
            );
            project.getLgacs().add(new Lgac(cboBoxLgacAssociate.getValue()));
            PROJECT_DAO.addProject(project);
            GenericWindowDriver.getGenericWindowDriver().showInfoAlert(event, "Proyecto registrado correctamente");
        }catch(InvalidFormException ex){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(new ActionEvent(), ex.getMessage());
        }
    }
    
    public void isValidForm() throws InvalidFormException{
        ValidatorForm.chechkAlphabeticalField(txtFieldProjectName, 5 ,80);
        ValidatorForm.isComboBoxSelected(cboBoxLgacAssociate);
        ValidatorForm.isNumberData(txtFieldDuration, 2);
        ValidatorForm.checkNotEmptyDateField(dtpStartDate);
        ValidatorForm.checkNotEmptyDateField(dtpEstimatedEndDate);
        if(cboBoxStatus.getValue() == "Terminado"){
            ValidatorForm.checkNotEmptyDateField(dtpEndDate);
        }
        ValidatorForm.isComboBoxSelected(cboBoxStatus);
        ValidatorForm.chechkAlphabeticalArea(txtAreaDescription, 1 ,450);
        if(dtpEstimatedEndDate.getValue().isBefore(dtpStartDate.getValue())){
            dtpStartDate.setStyle("-fx-border-color: red;");
            throw new InvalidFormException("La fecha de inicio no puede ser despues de la fecha estimada de fin");
        }
    }
    
    public void receiveProjectUpdateToken(Project project, Integrant integrantToken){
        this.token = integrantToken;
        showProjectUpdateForm(project);
    }
    
     public void receiveProjectSaveToken(Integrant integrantToken){
        this.token = integrantToken;
        showProjectSaveForm();
    }

    @FXML
    private void exit(ActionEvent event){
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("ProjectList.fxml", txtFieldProjectName);
        ProjectListController controller = loader.getController();
        controller.receiveToken(token);
    }
    
    @FXML
    private void updateProyect(ActionEvent event){
        try{
            this.isValidForm();
            Project project = new Project(
                txtFieldProjectName.getText(), 
                cboBoxLgacAssociate.getValue(),
                Integer.parseInt(txtFieldDuration.getText()),
                cboBoxStatus.getValue(),
                ValidatorForm.convertJavaDateToSQlDate(dtpStartDate),
                optionEndDate(),
                ValidatorForm.convertJavaDateToSQlDate(dtpEstimatedEndDate),
                txtAreaDescription.getText()
            );
            PROJECT_DAO.updateProject(project, oldProject.getProjectName());
            GenericWindowDriver.getGenericWindowDriver().showInfoAlert(event, "Proyecto actualizado correctamente");
        }catch(InvalidFormException ie){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(new ActionEvent(), ie.getMessage());
        }
    }
    
    public String optionEndDate(){
        String endDate = null;
        if((dtpEndDate.getValue())!=null){
            endDate = ValidatorForm.convertJavaDateToSQlDate(dtpEndDate);
        }
        return endDate;
    }
    
     private void makeitemsLgac(){
        for (Lgac lgac : GENERALRESUME_DAO.getGeneralResumeByKey(token.getBodyAcademyKey()).getLgacList()){
            cboBoxLgacAssociate.getItems().add(lgac.getTitle());
        }
    }

    private void checkExistProjectName() throws InvalidFormException{
        if(PROJECT_DAO.projectRegistered(this.txtFieldProjectName.getText())){
            this.txtFieldProjectName.setStyle("-fx-border-color: red;");
            throw new InvalidFormException("Ya existe un Proyecto con el mismo nombre");
        }
    }
     
    private void checkStatusWithDates() throws InvalidFormException{
        java.util.Date date = new Date();
        if("Terminado".equals(this.cboBoxStatus.getValue())){
            if(dtpEndDate.getValue().isBefore(LocalDate.parse(date.toString()))){
                dtpStartDate.setStyle("-fx-border-color: red;");
                throw new InvalidFormException("La fecha de inicio no puede ser despues de la fecha estimada de fin");
            }
        }
    }
}
