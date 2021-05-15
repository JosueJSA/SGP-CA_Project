/**
 * @author estef
 * Last modification date format: 13-05-2021
 */

package sgp.ca.demodao;

import java.net.URL;
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.businesslogic.MeetingDAO;

public class MeetingFormController implements Initializable {
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final MeetingDAO MEETING_DAO = new MeetingDAO();
//    private Hashtable gridForm = new Hashtable();
    
    @FXML
    private Button btnRegisterMeeting;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnAddRowPrerequisiteTable;
    @FXML
    private Button btnRemoveRowPrerequisiteTable;
    @FXML
    private Button btnAddRowMeetingAgendaTable;
    @FXML
    private Button btnRemoveRowMeetingAgendaTable;
    @FXML
    private ComboBox<String> cboBoxMeetingHour;
    @FXML
    private ComboBox<String> cboBoxMeetingMinute;
    @FXML
    private DatePicker meetingDateField;
    @FXML
    private TextField meetingProjectField;
    @FXML
    private TextField placeMeetingField;
    @FXML
    private TextField issueMeetingField;
    @FXML
    private TableView<AssistantTable> assistansTableView;
    @FXML
    private TableColumn<AssistantTable, String> columnIntegrantName;
    @FXML
    private TableColumn<AssistantTable, RadioButton> columnSelectAssistant;
    @FXML
    private TableColumn<AssistantTable, RadioButton> columnDiscussionLeader;
    @FXML
    private TableColumn<AssistantTable, RadioButton> columnSecretary;
    @FXML
    private TableColumn<AssistantTable, RadioButton> columnTimeTaker;
    @FXML
    private TableView prerequisiteTableView;
    @FXML
    private TableView meetingAgendaTableView;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cboBoxMeetingHour.setItems(makeitemsHoursList());
        cboBoxMeetingMinute.setItems(makeitemsMinutesList());
        columnIntegrantName.setCellValueFactory(new PropertyValueFactory<AssistantTable, String>("integrantName"));
        
        columnSelectAssistant.setStyle("-fx-alignment: CENTER");
        columnSelectAssistant.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("assistant"));
        
        columnDiscussionLeader.setStyle("-fx-alignment: CENTER");
        columnDiscussionLeader.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("discussionLeader"));
        
        columnSecretary.setStyle("-fx-alignment: CENTER");
        columnSecretary.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("secretary"));
        
        columnTimeTaker.setStyle("-fx-alignment: CENTER");
        columnTimeTaker.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("takerTime"));
        assistansTableView.setItems(makeitemsIntegrant());
                
    } 
    
    @FXML
    void addNewMeeting(ActionEvent event) {

    }

    @FXML
    void addRowMeetingAgendaTable(ActionEvent event) {

    }

    @FXML
    void addRowPrerequisiteTable(ActionEvent event) {

    }

    @FXML
    void closeMeetingFormWindow(ActionEvent event) {

    }

    @FXML
    void removeRowMeetingAgendaTable(ActionEvent event) {

    }

    @FXML
    void removeRowPrerequisiteTable(ActionEvent event) {

    }
    
    
    private ObservableList<AssistantTable> makeitemsIntegrant(){
        ObservableList<AssistantTable> itemsIntegrant = FXCollections.observableArrayList();
        List<String> integrantsName = INTEGRANT_DAO.getAllIntegrantsName();
        for(int i = 0; i < integrantsName.size(); i++){
            AssistantTable assistantTable = new AssistantTable(integrantsName.get(i));
            itemsIntegrant.add(assistantTable);
            
        }
        return itemsIntegrant;
    }
    
    private ObservableList<String> makeitemsHoursList(){
        ObservableList<String> itemsHours = FXCollections.observableArrayList();
        int hours = 24;
        for(int i = 1; i <= hours; i++){
            itemsHours.add("" + i);
        }
        return itemsHours;
    }
    
    private ObservableList<String> makeitemsMinutesList(){
        ObservableList<String> itemsMinutes = FXCollections.observableArrayList();
        int minutes = 60;
        for(int i = 0; i <= minutes; i++){
            if(i<10){
                itemsMinutes.add("0" + i);
            }else{
                itemsMinutes.add("" + i);
            }
        }
        return itemsMinutes;
    }
    
}
