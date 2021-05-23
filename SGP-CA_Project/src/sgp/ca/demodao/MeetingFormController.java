/**
 * @author estef
 * Last modification date format: 13-05-2021
 */

package sgp.ca.demodao;


import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ChoiceBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.businesslogic.MeetingDAO;
import sgp.ca.domain.Meeting;
import sgp.ca.domain.Prerequisite;
import sgp.ca.domain.Topic;

public class MeetingFormController implements Initializable {
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final MeetingDAO MEETING_DAO = new MeetingDAO();
    private Meeting meeting = new Meeting();
    
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
    private TableView <Prerequisite> prerequisiteTableView;
    @FXML
    private TableColumn<Prerequisite, String> descriptionPrerequisiteColumn;
    @FXML
    private TableColumn<Prerequisite, String> responsiblePrerequisiteColumn;
    @FXML
    private TableView <Topic> meetingAgendaTableView;
    @FXML
    private TableColumn<Topic, String> estimatedTimeColumn;
    @FXML
    private TableColumn<Topic, String> descriptionTopicColumn;
    @FXML
    private TableColumn<Topic, String> discussionLeaderCoumn;
    ObservableList<Prerequisite> itemsPrerequisite = FXCollections.observableArrayList();
    ObservableList<Topic> itemsTopic = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cboBoxMeetingHour.setItems(makeitemsHoursList());
        cboBoxMeetingMinute.setItems(makeitemsMinutesList());
        
        columnIntegrantName.setCellValueFactory(new PropertyValueFactory<AssistantTable, String>("integrantName"));
        columnSelectAssistant.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("assistant"));
        columnDiscussionLeader.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("discussionLeader"));
        columnSecretary.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("secretary"));
        columnTimeTaker.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("takerTime"));
        assistansTableView.setItems(makeitemsIntegrant());
        
        descriptionPrerequisiteColumn.setCellValueFactory(new PropertyValueFactory<Prerequisite, String>("descrptionPrerequisite"));
        descriptionPrerequisiteColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        responsiblePrerequisiteColumn.setCellValueFactory(new PropertyValueFactory<Prerequisite, String>("responsiblePrerequisite"));
        responsiblePrerequisiteColumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(makeitemsIntegrantForChoiceBoxTableCell()));
        responsiblePrerequisiteColumn.setOnEditCommit(data -> {
            Prerequisite prerequisite = data.getRowValue();
            prerequisite.setDescrptionPrerequisite(data.getNewValue());

            System.out.println(prerequisite);
        });
        prerequisiteTableView.setItems(itemsPrerequisite);
        
        estimatedTimeColumn.setCellValueFactory(new PropertyValueFactory<Topic, String>("plannedTime"));
        estimatedTimeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        descriptionTopicColumn.setCellValueFactory(new PropertyValueFactory<Topic, String>("descriptionTopic"));
        descriptionTopicColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        discussionLeaderCoumn.setCellValueFactory(new PropertyValueFactory<Topic, String>("discissionLeader"));
        discussionLeaderCoumn.setCellFactory(ChoiceBoxTableCell.forTableColumn(makeitemsIntegrantForChoiceBoxTableCell()));
        meetingAgendaTableView.setItems(itemsTopic);
                
    } 
    
    public void reciveMeeting (Meeting meeting){
        this.meeting = meeting;
    }
    
    @FXML
    void addNewMeeting(ActionEvent event) {
        

    }

    @FXML
    void addRowMeetingAgendaTable(ActionEvent event) {
        Topic newTopic = new Topic();
        itemsTopic.add(newTopic);
    }

    @FXML
    void addRowPrerequisiteTable(ActionEvent event) {
        Prerequisite newPrerequisite = new Prerequisite();
        itemsPrerequisite.add(newPrerequisite);
        System.out.print(itemsPrerequisite.size() + " - ");
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
    
    private ObservableList<String> makeitemsIntegrantForChoiceBoxTableCell(){
        ObservableList<String> itemsIntegrant = FXCollections.observableArrayList();
        List<String> integrantsName = INTEGRANT_DAO.getAllIntegrantsName();
        itemsIntegrant.addAll(integrantsName);
        return itemsIntegrant;
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
