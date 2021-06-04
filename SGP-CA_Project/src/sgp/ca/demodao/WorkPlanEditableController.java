/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.WorkPlanDAO;
import sgp.ca.domain.Action;
import sgp.ca.domain.Goal;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.WorkPlan;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class WorkPlanEditableController implements Initializable {

    @FXML
    private Label lblUserName;
    @FXML
    private HBox hboxWorkPlanOptions;
    @FXML
    private Button btnSaveWorkplan;
    @FXML
    private Button btnUpdateWorkplan;
    @FXML
    private Button btnCancelChanges;
    @FXML
    private DatePicker datePickerWorkplanStartDate;
    @FXML
    private DatePicker datePickerWorkplanEndDate;
    @FXML
    private DatePicker datePickerGoalStartDate;
    @FXML
    private DatePicker datePickerGoalEndDate;
    @FXML
    private HBox hboxActionOptions;
    @FXML
    private Button btnAddAction;
    @FXML
    private Button btnDeleteAction;
    @FXML
    private TableView<Action> tableViewWorkplanActions;
    @FXML
    private TableColumn<Action, String> columnActionDescription;
    @FXML
    private TableColumn<Action, String> columnActionEndDateEstimated;
    @FXML
    private TableColumn<Action, String> columnActionResponsible;
    @FXML
    private TableColumn<Action, String> columnActionStartDate;
    @FXML
    private TableColumn<Action, String> columnResourceAction;
    @FXML
    private DatePicker datePickerStartDate;
    @FXML
    private DatePicker datePickerStimatedEndDate;
    @FXML
    private Button btnAddGoal;
    @FXML
    private Button btnSaveGoalChanges;
    @FXML
    private Button btnDeleteGoal;
    @FXML
    private Button btnUpdateAction;
    @FXML
    private ListView<String> listViewGoals;
    @FXML
    private TextArea txtAreaWorkPlanGeneralTarget;
    @FXML
    private TextArea txtAreaGoalDescripctionDetail;
    @FXML
    private TextField txtFieldActionResponsible;
    @FXML
    private TextArea txtAreaActionDescriptionDetail;
    @FXML
    private TextArea txtAreaActionResources;
    
    private Integrant token;
    private WorkPlan workplan; 
    private WorkPlan oldWorkPlanCone;
    private final WorkPlanDAO WORKPLAN_DAO = new WorkPlanDAO();    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.hboxWorkPlanOptions.getChildren().removeAll(btnSaveWorkplan, btnUpdateWorkplan, btnCancelChanges);
        this.preprareActionsTable();
        this.workplan = new WorkPlan();
    }  
    
    public void showWorkPlanInsertionForm(Integrant token){
        this.token = token;
        this.hboxWorkPlanOptions.getChildren().addAll(btnSaveWorkplan, btnCancelChanges);
        this.lblUserName.setText(this.token.getFullName());
    }
    
    public void showWorkPlanUpdateForm(Integrant token, String workPlanEndDate){
        this.token = token;
        this.lblUserName.setText(this.token.getFullName());
        this.hboxWorkPlanOptions.getChildren().addAll(btnUpdateWorkplan, btnCancelChanges);
        this.workplan = WORKPLAN_DAO.getWorkPlan(workPlanEndDate, this.token.getBodyAcademyKey());
        this.oldWorkPlanCone = WORKPLAN_DAO.getWorkPlan(workPlanEndDate, this.token.getBodyAcademyKey());
        this.setWorkPlanDataIntoInterface();
    }
    
    @FXML
    private void saveWorkPlan(ActionEvent event) {
        this.getOutWorkPlanDataFromInterface();
        this.workplan.getGoals().forEach(goal -> goal.setEndDate(this.workplan.getEndDatePlan()));
        this.workplan.getGoals().forEach(goal -> goal.getActions().forEach(action -> action.setEndDate(this.workplan.getEndDatePlan())));
        if(WORKPLAN_DAO.addWorkPlan(this.workplan)){
            AlertGenerator.showInfoAlert(event, "El plan de trabajo ha sido registrado exitosamente");
        }else{
            AlertGenerator.showErrorAlert(event, "El sistema ha presentado un error, favor de ponerse en contacto con soporte técnico");
        }
        FXMLLoader loader = changeWindow("WorkPlanRequest.fxml", event);
        WorkPlanRequestController controller = loader.getController();
        controller.receiveIntegrantToken(token);
    }

    @FXML
    private void updateWorkPlan(ActionEvent event) {
        this.getOutWorkPlanDataFromInterface();
        this.workplan.getGoals().forEach(goal -> goal.setEndDate(this.workplan.getEndDatePlan()));
        this.workplan.getGoals().forEach(goal -> goal.getActions().forEach(action -> action.setEndDate(this.workplan.getEndDatePlan())));
        if(WORKPLAN_DAO.updateWorkPlan(this.workplan, this.oldWorkPlanCone)){
            AlertGenerator.showInfoAlert(event, "El plan de trabajo ha sido actualizado exitosamente");
        }else{
            AlertGenerator.showErrorAlert(event, "El sistema ha presentado un error, favor de ponerse en contacto con soporte técnico");
        }
        FXMLLoader loader = changeWindow("WorkPlanRequest.fxml", event);
        WorkPlanRequestController controller = loader.getController();
        controller.receiveIntegrantToken(token);
    }

    @FXML
    private void cancelChanges(ActionEvent event) {
        Optional<ButtonType> action = AlertGenerator.showConfirmacionAlert(event, "¿Seguro que deseas cancelar los cambios?");
        if(action.get() == ButtonType.OK){
            FXMLLoader loader = changeWindow("WorkPlanRequest.fxml", event);
            WorkPlanRequestController controller = loader.getController();
            controller.receiveIntegrantToken(token);
        }
    }

    @FXML
    private void addAction(ActionEvent event) {
        tableViewWorkplanActions.getItems().add(new Action(
            this.txtAreaActionDescriptionDetail.getText(), 
            ValidatorForm.convertJavaDateToSQlDate(this.datePickerStimatedEndDate),
            this.txtFieldActionResponsible.getText(), 
            ValidatorForm.convertJavaDateToSQlDate(this.datePickerStartDate),
            this.txtAreaActionResources.getText()
        ));
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        tableViewWorkplanActions.getItems().remove(
            tableViewWorkplanActions.getSelectionModel().getSelectedItem()
        );
    }

    @FXML
    private void selectAction(MouseEvent event) {
        if(tableViewWorkplanActions.getSelectionModel().getSelectedItem() != null){
            Action action = tableViewWorkplanActions.getSelectionModel().getSelectedItem();
            this.txtFieldActionResponsible.setText(action.getResponsibleAction());
            this.txtAreaActionResources.setText(action.getResource());
            this.txtAreaActionDescriptionDetail.setText(action.getDescriptionAction());
            this.datePickerStartDate.setValue(LocalDate.parse(action.getStartDate()));
            this.datePickerStimatedEndDate.setValue(LocalDate.parse(action.getEstimatedEndDate()));
        }
    }

    @FXML
    private void addGoal(ActionEvent event) {
        this.listViewGoals.getItems().add("[Sin guardar]");
    }

    @FXML
    private void saveGoalChanges(ActionEvent event) {
        Goal goal = new Goal(
            0, 
            datePickerGoalStartDate.getValue().toString(), 
            "0000-00-00", 
            true, 
            txtAreaGoalDescripctionDetail.getText()
        );
        for(Action action : tableViewWorkplanActions.getItems()){
            action.setEndDate("0000-00-00");
            goal.addAction(action);
        }
        if(workplan.searchGoalByDescription(this.listViewGoals.getSelectionModel().getSelectedItem()) == null){
            this.workplan.addGoal(goal);
        }else{
            this.workplan.updateGoal(goal, this.listViewGoals.getSelectionModel().getSelectedItem());
        }
        this.cleanGoalForm();
        this.refreshGoalsList();
    }

    @FXML
    private void deleteGoal(ActionEvent event) {
        this.workplan.deleteGoal(this.listViewGoals.getSelectionModel().getSelectedItem());
        this.refreshGoalsList();
    }
    
    @FXML
    private void selectGoal(MouseEvent event) {
        this.cleanGoalForm();
        if(this.listViewGoals.getSelectionModel().getSelectedItem() != null){
            this.setGoalDataInInterface(this.workplan.searchGoalByDescription(this.listViewGoals.getSelectionModel().getSelectedItem()));
        }
    }
    
    @FXML
    private void updateAction(ActionEvent event) {
        if(tableViewWorkplanActions.getSelectionModel().getSelectedItem() != null){
            tableViewWorkplanActions.getItems().remove(tableViewWorkplanActions.getSelectionModel().getSelectedItem());
            tableViewWorkplanActions.getItems().add(new Action(
                this.txtAreaActionDescriptionDetail.getText(), 
                this.datePickerStimatedEndDate.getValue().toString(),
                this.txtFieldActionResponsible.getText(), 
                this.datePickerStartDate.getValue().toString(),
                this.txtAreaActionResources.getText()
            ));
        }
        
    }
    
    private void setWorkPlanDataIntoInterface(){
        this.datePickerWorkplanStartDate.setValue(LocalDate.parse(this.workplan.getStartDatePlan()));
        this.datePickerWorkplanEndDate.setValue(LocalDate.parse(this.workplan.getEndDatePlan()));
        this.txtAreaWorkPlanGeneralTarget.setText(this.workplan.getGeneralTarget());
        this.refreshGoalsList();
    }
    
    private void refreshGoalsList(){
        this.listViewGoals.getItems().clear();
        this.workplan.getGoals().forEach(goalElement -> {
            this.listViewGoals.getItems().add(goalElement.getDescription());
        });
    }
    
    private void getOutWorkPlanDataFromInterface(){
        this.workplan.setDurationInYears(this.datePickerWorkplanEndDate.getValue().getYear() - this.datePickerWorkplanStartDate.getValue().getYear());
        this.workplan.setEndDatePlan(ValidatorForm.convertJavaDateToSQlDate(datePickerWorkplanEndDate));
        this.workplan.setStartDatePlan(ValidatorForm.convertJavaDateToSQlDate(datePickerWorkplanStartDate));
        this.workplan.setGeneralTarget(this.txtAreaWorkPlanGeneralTarget.getText());
        this.workplan.setBodyAcademyKey(this.token.getBodyAcademyKey());
    }
    
    private void setGoalDataInInterface(Goal goal){
        if(goal != null){
            this.txtAreaGoalDescripctionDetail.setText(goal.getDescription());
            this.datePickerGoalStartDate.setValue(LocalDate.parse(goal.getStartDate()));
            this.tableViewWorkplanActions.getItems().addAll(goal.getActions());
        }
    }
                
    private void preprareActionsTable(){
        columnActionDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionAction"));
        columnActionEndDateEstimated.setCellValueFactory(new PropertyValueFactory<>("estimatedEndDate"));
        columnActionResponsible.setCellValueFactory(new PropertyValueFactory<>("responsibleAction"));
        columnActionStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        columnResourceAction.setCellValueFactory(new PropertyValueFactory<>("resource"));
    }
    
    private void cleanGoalForm(){
        this.tableViewWorkplanActions.getItems().removeAll(tableViewWorkplanActions.getItems());
        this.txtAreaGoalDescripctionDetail.setText("");
        this.datePickerGoalEndDate.setValue(null);
        this.datePickerGoalStartDate.setValue(null);
        this.txtFieldActionResponsible.setText("");
        this.datePickerStimatedEndDate.setValue(null);
        this.datePickerStartDate.setValue(null);
        this.txtAreaActionDescriptionDetail.setText("");
        this.txtAreaActionResources.setText("");
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
    
    private void checkActionForm() throws InvalidFormException{
        ValidatorForm.checkNotEmptyDateField(datePickerStartDate);
        ValidatorForm.checkNotEmptyDateField(datePickerStimatedEndDate);
        ValidatorForm.chechkAlphabeticalField(txtFieldActionResponsible, 3, 50);
        ValidatorForm.chechkAlphabeticalArea(txtAreaActionResources, 0, 450);
        ValidatorForm.chechkAlphabeticalArea(txtAreaActionDescriptionDetail, 20, 450);
    }
    
    private void checkWorkPlanForm() throws InvalidFormException{
    }
    
}
