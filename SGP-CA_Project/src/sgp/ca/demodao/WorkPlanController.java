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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.WorkPlanDAO;
import sgp.ca.domain.Action;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.WorkPlan;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class WorkPlanController implements Initializable {

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
    private TableColumn<Action, String> columnActionStatus;
    @FXML
    private TableColumn<Action, String> columnActionDescription;
    @FXML
    private TableColumn<Action, String> columnActionEstimatedEndDate;
    @FXML
    private TableColumn<Action, String> columnActionEndDate;
    @FXML
    private TableColumn<Action, String> columnActoinResponsible;
    @FXML
    private TableColumn<Action, String> columnActionStartDate;
    @FXML
    private TextArea textAreaActionDescription;
    @FXML
    private TextArea textAreaActionResources;
    
    private Integrant integrant;
    private final WorkPlanDAO WORKPLAN_DAO = new WorkPlanDAO();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exit(ActionEvent event) {
        FXMLLoader loader = changeWindow("Start.fxml", event);
        StartController controller = loader.getController();
        controller.receiveIntegrantToken(integrant);
    }

    @FXML
    private void updateWorkplan(ActionEvent event) {
    }

    @FXML
    private void addNewWorkPlan(ActionEvent event) {
        FXMLLoader loader = changeWindow("WorkPlanEditable.fxml", event);
        WorkPlanEditableController controller = loader.getController();
        //controller.receiveIntegrantToken(integrant);
    }
    
    @FXML
    private void selectPeriot(ActionEvent event) {
    }

    @FXML
    private void deleteWorkplan(ActionEvent event) {
    }
    
    public void receiveIntegrantToken(Integrant integrant){
        this.integrant = integrant;
    }
    
    public void setDataInWorkPlanInterface(){
        List<WorkPlan> workplanPeriots = WORKPLAN_DAO.getWorkPlanPeriots(integrant.getBodyAcademyKey());
        workplanPeriots.forEach(periot -> {
            cmBoxWorkPlanPeriot.getItems().add("Periodo: [" + periot.getStartDatePlan() + " - " + periot.getEndDatePlan() + "]");
        });
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
