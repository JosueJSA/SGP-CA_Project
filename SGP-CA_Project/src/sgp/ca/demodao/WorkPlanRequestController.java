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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
public class WorkPlanRequestController implements Initializable {

    @FXML
    private Button btnExit;
    @FXML
    private ComboBox<String> cmBoxWorkPlanPeriot;
    @FXML
    private Button btnUpdateWorkplan;
    @FXML
    private Button btnAddNewWorkplan;
    @FXML
    private Button btnDeleteWorkplan;
    @FXML
    private Label lblDuration;
    @FXML
    private TextArea textAreaGeneralTarget;
    @FXML
    private ComboBox<String> cmBoxGoals;
    @FXML
    private TextArea textAreaGoalDescription;
    @FXML
    private Label lblGoalStartDate;
    @FXML
    private Label lblGoalEndDate;
    @FXML
    private Label lblGoalStatus;
    @FXML
    private TableView<Action> tableViewActions;
    @FXML
    private TableColumn<Action, String> columnActionDescription;
    @FXML
    private TableColumn<Action, String> columnActionEstimatedEndDate;
    @FXML
    private TableColumn<Action, String> columnActionEndDate;
    @FXML
    private TableColumn<Action, String> columnActionStartDate;
    @FXML
    private TableColumn<Action, String> columnResources;
    @FXML
    private TableColumn<Action, String> columnActionResponsible;
    @FXML
    private TextArea textAreaActionDescription;
    @FXML
    private TextArea textAreaActionResources;
    
    private Integrant token;
    private final WorkPlanDAO WORKPLAN_DAO = new WorkPlanDAO();
    private WorkPlan workplan;
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.workplan = new WorkPlan();
        this.preprareActionsTable();
    }    

    @FXML
    private void exit(ActionEvent event) {
        FXMLLoader loader = changeWindow("Start.fxml", event);
        StartController controller = loader.getController();
        controller.receiveIntegrantToken(token);
    }

    @FXML
    private void updateWorkplan(ActionEvent event) {
        String date = this.cmBoxWorkPlanPeriot.getSelectionModel().getSelectedItem();
        date = date.substring(date.length()-11, date.length()-1);
        FXMLLoader loader = changeWindow("WorkPlanEditable.fxml", event);
        WorkPlanEditableController controller = loader.getController();
        controller.showWorkPlanUpdateForm(token, date); 
    }

    @FXML
    private void addNewWorkPlan(ActionEvent event) {
        FXMLLoader loader = changeWindow("WorkPlanEditable.fxml", event);
        WorkPlanEditableController controller = loader.getController();
        controller.showWorkPlanInsertionForm(token);
    }
    
    @FXML
    private void selectPeriot(ActionEvent event) {
        String date = this.cmBoxWorkPlanPeriot.getSelectionModel().getSelectedItem();
        date = date.substring(date.length()-11, date.length()-1);
        this.workplan = WORKPLAN_DAO.getWorkPlan(date, this.token.getBodyAcademyKey());
        this.setWorkPlanSelectedDataIntoInterface();
    }

    @FXML
    private void deleteWorkplan(ActionEvent event) {
    }
    
    public void receiveIntegrantToken(Integrant integrant){
        this.token = integrant;
        this.setDataInWorkPlanInterface();
    }
    
    public void setDataInWorkPlanInterface(){
        List<WorkPlan> workplanPeriots = WORKPLAN_DAO.getWorkPlanPeriots(token.getBodyAcademyKey());
        workplanPeriots.forEach(periot -> {
            cmBoxWorkPlanPeriot.getItems().add("Periodo: [" + periot.getStartDatePlan() + " - " + periot.getEndDatePlan() + "]");
        });
    }

    @FXML
    private void selectGoal(ActionEvent event) {
        String goalDescriptionSelected = this.cmBoxGoals.getSelectionModel().getSelectedItem(); 
        if(!goalDescriptionSelected.isEmpty()){
            Goal goal = this.workplan.searchGoalByDescription(goalDescriptionSelected);
            this.lblGoalStatus.setText(String.valueOf(goal.isStatusGoal()));
            this.lblGoalStartDate.setText(goal.getStartDate());
            this.lblGoalEndDate.setText(goal.getEndDate());
            this.textAreaGoalDescription.setText(goal.getDescription());
            this.tableViewActions.getItems().clear();
            goal.getActions().forEach(action -> {
                this.tableViewActions.getItems().add(action);
            });
        }
    }

    @FXML
    private void selectAction(MouseEvent event) {
        String actionDescription = this.tableViewActions.getSelectionModel().getSelectedItem().getDescriptionAction();
        if(!actionDescription.isEmpty()){
            Action action = this.tableViewActions.getSelectionModel().getSelectedItem();
            this.textAreaActionDescription.setText(action.getDescriptionAction());
            this.textAreaActionResources.setText(action.getResource());
        }
    }
    
    private void setWorkPlanSelectedDataIntoInterface(){
        this.lblDuration.setText(String.valueOf(this.workplan.getDurationInYears()));
        this.textAreaGeneralTarget.setText(this.workplan.getGeneralTarget());
        this.cmBoxGoals.getItems().clear();
        this.workplan.getGoals().forEach(goal -> {
            cmBoxGoals.getItems().add(goal.getDescription());
        });
    }
    
    private void preprareActionsTable(){
        columnActionDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionAction"));
        columnActionEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        columnActionEstimatedEndDate.setCellValueFactory(new PropertyValueFactory<>("estimatedEndDate"));
        columnActionStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        columnResources.setCellValueFactory(new PropertyValueFactory<>("resource"));
        columnActionResponsible.setCellValueFactory(new PropertyValueFactory<>("responsibleAction"));
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
