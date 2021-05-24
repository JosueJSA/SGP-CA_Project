/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import sgp.ca.domain.Action;
import sgp.ca.domain.Integrant;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class WorkPlanEditableController implements Initializable {

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
    private TextArea workPlanGeneralTarget;
    @FXML
    private ListView<String> listViewWorkplanGoes;
    @FXML
    private TextArea textAreaGoalDescripctionDetail;
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
    private TableColumn<Action, String> columnActionEndDate;
    @FXML
    private TableColumn<Action, String> columnActionResponsible;
    @FXML
    private TableColumn<Action, String> columnActionStartDate;
    @FXML
    private TextArea textAreaActionDescriptionDetail;
    @FXML
    private TextArea textAreaActionResources;

    private Integrant integrant;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.prepareActionsTable();
    }  
    
    public void receiveIntegrantToken(Integrant integrant){
        this.integrant = integrant;
    }

    @FXML
    private void saveWorkPlan(ActionEvent event) {
    }

    @FXML
    private void updateWorkPlan(ActionEvent event) {
    }

    @FXML
    private void cancelChanges(ActionEvent event) {
    }

    @FXML
    private void addAction(ActionEvent event) {
    }

    @FXML
    private void deleteAction(ActionEvent event) {
    }
    
    public void prepareActionsTable(){
        tableViewWorkplanActions = new TableView<>();
        columnActionDescription.setCellValueFactory(new PropertyValueFactory<>("descriptionAction"));
        columnActionEndDateEstimated.setCellValueFactory(new PropertyValueFactory<>("estimatedEndDate"));
        columnActionEndDate.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        columnActionResponsible.setCellValueFactory(new PropertyValueFactory<>("responsibleAction"));
        columnActionStartDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));        
    }
    
}
