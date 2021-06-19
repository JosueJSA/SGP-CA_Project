/*
* @author Johann
* @versión v1.0
* Last modification date: 17-06-2021
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
    private TextField txtFieldTitleProject;
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
    private TextField txtFieldStatus;
    @FXML
    private TextArea txtAreaDescription;
    @FXML
    private HBox hbProjectOptions;
    
    private final ProjectDAO PROJECT_DAO = new ProjectDAO();
    private final GeneralResumeDAO GENERALRESUME_DAO = new GeneralResumeDAO();
    private List<Button> optionButtons;
    private Project oldProject  = new Project();
    private Integrant token;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
         optionButtons = Arrays.asList(
            btnUpdate, btnSave, 
            btnExit
        );
        hbProjectOptions.getChildren().removeAll(optionButtons);
    }    

    public void showProjectSaveForm(){
        hbProjectOptions.getChildren().addAll(btnSave, btnExit);
        makeitemsLgac();
        
        
    }
    
     public void showProjectUpdateForm(Project project){
        oldProject = project;
        makeitemsLgac();
        this.txtFieldTitleProject.setText(project.getProjectName());
        this.cboBoxLgacAssociate.setValue(project.getLgacs().toString());
        this.txtFieldDuration.setText(Integer.toString(project.getDurationProjectInMonths()));
        this.dtpStartDate.setValue(LocalDate.parse(project.getStartDate()));
        this.dtpEstimatedEndDate.setValue(LocalDate.parse(project.getEstimatedEndDate()));
        this.txtFieldStatus.setText(project.getStatus());
        this.txtAreaDescription.setText(project.getDescription());
        this.dtpEndDate.setValue(LocalDate.parse(project.getEndDate()));
        hbProjectOptions.getChildren().addAll(btnUpdate,  btnExit);
    }
     
    @FXML
    private void saveProject(ActionEvent event){
        try{
            this.isValidForm();
            Project project = new Project(
                txtFieldTitleProject.getText(), 
                cboBoxLgacAssociate.getValue(),
                Integer.parseInt(txtFieldDuration.getText()),
                txtFieldStatus.getText(),
                ValidatorForm.convertJavaDateToSQlDate(dtpStartDate),
                optionEndDate(),
                ValidatorForm.convertJavaDateToSQlDate(dtpEstimatedEndDate),
                txtAreaDescription.getText()
            );
            PROJECT_DAO.addProject(project);
            GenericWindowDriver.getGenericWindowDriver().showInfoAlert(event, "Proyecto registrado correctamente");
        }catch(InvalidFormException ex){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(new ActionEvent(), ex.getMessage());
        }
    }
    
    public void isValidForm() throws InvalidFormException{
        ValidatorForm.chechkAlphabeticalField(txtFieldTitleProject, 5 ,80);
        ValidatorForm.isComboBoxSelected(cboBoxLgacAssociate);
        ValidatorForm.isNumberData(txtFieldDuration, 2);
        ValidatorForm.checkNotEmptyDateField(dtpStartDate);
        ValidatorForm.checkNotEmptyDateField(dtpEstimatedEndDate);
        ValidatorForm.chechkAlphabeticalArea(txtAreaDescription, 1 ,450);
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
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("ProjectList.fxml", txtFieldTitleProject);
        ProjectListController controller = loader.getController();
        controller.receiveToken(token);
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
        try{
            this.isValidForm();
            Project project = new Project(
                txtFieldTitleProject.getText(), 
                cboBoxLgacAssociate.getValue(),
                Integer.parseInt(txtFieldDuration.getText()),
                txtFieldStatus.getText(),
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
}
