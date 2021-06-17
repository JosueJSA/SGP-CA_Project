/**
 * @author estef
 * Last modification date format: 13-05-2021
 */

package sgp.ca.demodao;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.businesslogic.MeetingDAO;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Meeting;
import sgp.ca.domain.Prerequisite;
import sgp.ca.domain.Topic;

public class MeetingEditController implements Initializable{
    
    @FXML
    private Button btnRegisterMeeting;
    @FXML
    private Button btnClose;
    @FXML
    private TextField meetingProjectField;
    @FXML
    private TextField placeMeetingField;
    @FXML
    private DatePicker meetingDateField;
    @FXML
    private ComboBox<String> cboBoxMeetingHour;
    @FXML
    private ComboBox<String> cboBoxMeetingMinute;
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
    private Button btnAddRowPrerequisiteTable;
    @FXML
    private Button btnRemoveRowPrerequisiteTable;
    @FXML
    private TextField descriptionPrerequisiteField;
    @FXML
    private ComboBox<String> cboBoxResponsiblePrerequisite;
    @FXML
    private TableView <Prerequisite> prerequisiteTableView;
    @FXML
    private TableColumn<Prerequisite, String> descriptionPrerequisiteColumn;
    @FXML
    private TableColumn<Prerequisite, String> responsiblePrerequisiteColumn;
    @FXML
    private Button btnAddRowMeetingAgendaTable;
    @FXML
    private Button btnRemoveRowMeetingAgendaTable;
    @FXML
    private ComboBox<String> cboBoxHourTopic;
    @FXML
    private ComboBox<String> cboBoxMinuteTopic;
    @FXML
    private TextField descriptionTopicField;
    @FXML
    private ComboBox<String> cboBoxDiscissionLeaderTopic;
    @FXML
    private TableView <Topic> meetingAgendaTableView;
    @FXML
    private TableColumn<Topic, String> estimatedTimeColumn;
    @FXML
    private TableColumn<Topic, String> descriptionTopicColumn;
    @FXML
    private TableColumn<Topic, String> discussionLeaderCoumn;
    @FXML
    private Label lbWindowName;
    @FXML
    private Label lblUserName;
    @FXML
    private Button btnUpdateMeeting;
    
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final MeetingDAO MEETING_DAO = new MeetingDAO();
    private Meeting meeting = new Meeting();
    Calendar date = new GregorianCalendar();
    private Integrant token;
    private boolean addNewMeeting;
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        setMeetingFormCampsInformation();
        prepareAssistantTable();
        preparePrerequisiteTable();
        prepareMeetingAgendaTable();
    } 
    
    public void receiveToken(Integrant integrantToken){
        this.token = integrantToken;
        this.lblUserName.setText(token.getFullName());
    }
    
    public void reciveMeeting (Meeting meeting){ //Caso de modificacion
        this.meeting = meeting;
        setMeetingInformation();
        prepareAssistantTable();
        preparePrerequisiteTable();
        prepareMeetingAgendaTable();
    }
    
    private void setMeetingInformation(){
        LocalDate meetingDate = LocalDate.parse(meeting.getMeetingDate());
        this.meetingDateField.setValue(meetingDate);
        this.cboBoxMeetingHour.setValue(meeting.getMeetingTime().substring(0, 2));
        this.cboBoxMeetingMinute.setValue(meeting.getMeetingTime().substring(3, 5));
        this.meetingProjectField.setText(meeting.getMeetingProject());
        this.placeMeetingField.setText(meeting.getPlaceMeeting());
        this.issueMeetingField.setText(meeting.getIssueMeeting());
    }
    
    public void addNewMeeting(Boolean addedNewMeeting){
        this.addNewMeeting = addedNewMeeting;
        if(!addNewMeeting){
            this.btnRegisterMeeting.setVisible(false);
            this.lbWindowName.setText("MODIFICAR REUNIÓN");
        }else{
            this.btnUpdateMeeting.setVisible(false);
        }
    }
    
    private void setMeetingFormCampsInformation(){//cambiar nombre
        this.cboBoxMeetingHour.setItems(makeitemsHoursList());
        this.cboBoxMeetingMinute.setItems(makeitemsMinutesList());
        this.cboBoxResponsiblePrerequisite.setItems(makeitemsIntegrantForComboBox());
        this.cboBoxHourTopic.setItems(makeitemsHoursListForTopic());
        this.cboBoxMinuteTopic.setItems(makeitemsMinutesListForTopic());
        this.cboBoxDiscissionLeaderTopic.setItems(makeitemsIntegrantForComboBox());
    }
    
    
    @FXML
    private void addNewMeeting(ActionEvent event){
        try{
            validateMeetingInformation();
            this.getOutMeetingInformation();
            this.meeting.setMeetingKey(0);
            this.setIntoMeetingAgendaInformation();
            this.meeting.getMeetingAgenda().setMeetingAgendaKey(0);
            if(MEETING_DAO.addMeeting(this.meeting)){
                GenericWindowDriver.getGenericWindowDriver().showInfoAlert(event, "Reunión agendada con éxito");
            }else{
                GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, "Error en el sistema, favor de ponerse en contacto con sopoerte técnico");
            }
            FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("MeetingHistory.fxml", btnClose);
            MeetingHistoryController controller = loader.getController();
            controller.receiveToken(token);
        }catch(InvalidFormException ex){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, ex.getMessage());
        }
    }
    
    private void validateMeetingInformation() throws InvalidFormException{
        ValidatorForm.checkNotEmptyDateField(meetingDateField);
        ValidatorForm.chechkAlphabeticalField(meetingProjectField, 5, 80);
        ValidatorForm.isComboBoxSelected(cboBoxMeetingHour);
        ValidatorForm.isComboBoxSelected(cboBoxMeetingMinute);
        ValidatorForm.chechkAlphabeticalField(placeMeetingField, 4 ,  80);
        ValidatorForm.chechkAlphabeticalField(issueMeetingField,1, 200);
        
    }
    
    private void getOutMeetingInformation(){
        String hourMeeting = cboBoxMeetingHour.getValue();
        String minuteMeting = cboBoxMeetingMinute.getValue();
        this.meeting.setMeetingDate(meetingDateField.getValue().toString());
        this.meeting.setMeetingTime(hourMeeting + ":" + minuteMeting + ":00");
        this.meeting.setMeetingProject(meetingProjectField.getText());
        this.meeting.setMeetingRegistrationDate(
            date.get(Calendar.YEAR) + "-" + (date.get(Calendar.MONTH) + 1) + "-" + date.get(Calendar.DAY_OF_MONTH)
        );
        this.meeting.setStatusMeeting("Pendiente");
        this.meeting.setPlaceMeeting(placeMeetingField.getText());
        this.meeting.setIssueMeeting(issueMeetingField.getText());
        this.meeting.setIntegrantResponsible(token.getRfc());
        this.meeting.setMeetingNote("");
        this.meeting.setMeetingPending("");
       
    }
    
    
    private void setIntoMeetingAgendaInformation(){
        this.meeting.getMeetingAgenda().setTotalTime("00:00:00");
        this.meeting.getMeetingAgenda().setEstimatedTotalTime("00:00:00"); //Calcular
        this.meeting.getMeetingAgenda().setTotaltopics(meeting.getMeetingAgenda().getTopics().size());
    }
    
    private void validateTopicInformation() throws InvalidFormException{
        ValidatorForm.isComboBoxSelected(cboBoxHourTopic);
        ValidatorForm.isComboBoxSelected(cboBoxMinuteTopic);
        ValidatorForm.chechkAlphabeticalField(descriptionTopicField, 5 , 200);
        ValidatorForm.isComboBoxSelected(cboBoxDiscissionLeaderTopic);
    }

    @FXML
    private void addRowMeetingAgendaTable(ActionEvent event) {
        try{
            validateTopicInformation();
            this.meeting.getMeetingAgenda().getTopics().add(getOutTopicInformtation());
            meetingAgendaTableView.setItems(makeTopicItems());
            clearCampsOfTopic();
        }catch(InvalidFormException ex){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, ex.getMessage());
        }
    }
    
    private Topic getOutTopicInformtation(){
        String hourTopic = cboBoxHourTopic.getValue();
        String minuteTopic = cboBoxMinuteTopic.getValue();
        String planedTimeTopic = hourTopic + ":" + minuteTopic + ":00";
        String descriptionTopic = descriptionTopicField.getText();
        String discissionLeader = cboBoxDiscissionLeaderTopic.getValue();
        
        Topic newTopic = new Topic(
            0, "00:00:00", "00:00:00", planedTimeTopic, "00:00:00", descriptionTopic, discissionLeader, "Pendiente"
        );
        return newTopic;
    }
    
    private void clearCampsOfTopic(){
        cboBoxHourTopic.setValue(null);
        cboBoxMinuteTopic.setValue(null);
        descriptionTopicField.clear();
        cboBoxDiscissionLeaderTopic.setValue(null);
    }
    
    private void validatePrerequisiteInformation() throws InvalidFormException{
        ValidatorForm.chechkAlphabeticalField(descriptionPrerequisiteField, 5 ,  200);
        ValidatorForm.isComboBoxSelected(cboBoxResponsiblePrerequisite);
    }

    @FXML
    private void addRowPrerequisiteTable(ActionEvent event) {
        try{
            validatePrerequisiteInformation();
            meeting.getMeetingAgenda().getPrerequisites().add(getOutPrerequisiteInformation());
            prerequisiteTableView.setItems(makePrerequisiteItems());
            clearCampsOfPrerequisite();
        }catch(InvalidFormException ex){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, ex.getMessage());
        }
    }
    
    private Prerequisite getOutPrerequisiteInformation(){
        String descriptionPrerequisite = descriptionPrerequisiteField.getText();
        String responsiblePrerequisite = cboBoxResponsiblePrerequisite.getValue();
        Prerequisite newPrerequisite = new Prerequisite(
            0, responsiblePrerequisite, descriptionPrerequisite
        );
        return newPrerequisite;
    }
    
    private void clearCampsOfPrerequisite(){
        descriptionPrerequisiteField.clear();
        cboBoxResponsiblePrerequisite.setValue(null);
    }

    @FXML
    private void closeMeetingFormWindow(ActionEvent event) {
        Optional<ButtonType> action = GenericWindowDriver.getGenericWindowDriver().showConfirmacionAlert(event,
        "¿Seguro que deseas salir del registro de reunión? Los cambios no se guardarán");
        if(action.get() == ButtonType.OK){
            if(this.addNewMeeting){
                FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("MeetingHistory.fxml", btnClose);
                MeetingHistoryController controller = loader.getController();
                controller.receiveToken(token);
            }else{
                FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("MeetingRequest.fxml", btnClose);
                MeetingRequestController controller = loader.getController();
                controller.reciveMeeting(meeting.getMeetingKey());
                controller.receiveToken(token);
            }
        }
    }

    @FXML
    private void removeRowMeetingAgendaTable(ActionEvent event) {
        Topic topicToRemove = meetingAgendaTableView.getSelectionModel().getSelectedItem();
        meeting.getMeetingAgenda().getTopics().remove(topicToRemove);
        meetingAgendaTableView.setItems(makeTopicItems());
    }

    @FXML
    private void removeRowPrerequisiteTable(ActionEvent event) {
        Prerequisite prerequisiteToRemove = prerequisiteTableView.getSelectionModel().getSelectedItem();
        meeting.getMeetingAgenda().getPrerequisites().remove(prerequisiteToRemove);
        prerequisiteTableView.setItems(makePrerequisiteItems());
    }
    
    @FXML
    private void updateMeeting(ActionEvent event) {
        try{
            validateMeetingInformation();
            this.getOutMeetingInformation();
            this.setIntoMeetingAgendaInformation();
            if(MEETING_DAO.updateMeeting(meeting, meeting)){
                GenericWindowDriver.getGenericWindowDriver().showInfoAlert(event, "Reunión modificada con éxito");
            }else{
                GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, "Error en el sistema, favor de ponerse en contacto con soporte técnico");
            }
            FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("MeetingRequest.fxml", btnClose);
            MeetingRequestController controller = loader.getController();
            controller.receiveToken(token);
            controller.reciveMeeting(meeting.getMeetingKey());
        }catch(InvalidFormException ex){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, ex.getMessage());
        }

    } 
    
    private void prepareAssistantTable(){
        columnIntegrantName.setCellValueFactory(new PropertyValueFactory<AssistantTable, String>("integrantName"));
        columnSelectAssistant.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("assistant"));
        columnDiscussionLeader.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("discussionLeader"));
        columnSecretary.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("secretary"));
        columnTimeTaker.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("takerTime"));
        assistansTableView.setItems(makeitemsIntegrant());
    }
    
    private void preparePrerequisiteTable(){
        descriptionPrerequisiteColumn.setCellValueFactory(new PropertyValueFactory<Prerequisite, String>("descrptionPrerequisite"));
        responsiblePrerequisiteColumn.setCellValueFactory(new PropertyValueFactory<Prerequisite, String>("responsiblePrerequisite"));
        prerequisiteTableView.setItems(makePrerequisiteItems());
    }
    
    private void prepareMeetingAgendaTable(){
        estimatedTimeColumn.setCellValueFactory(new PropertyValueFactory<Topic, String>("plannedTime"));
        descriptionTopicColumn.setCellValueFactory(new PropertyValueFactory<Topic, String>("descriptionTopic"));
        discussionLeaderCoumn.setCellValueFactory(new PropertyValueFactory<Topic, String>("discissionLeader"));
        meetingAgendaTableView.setItems(makeTopicItems());
    }
    
    private ObservableList<Topic> makeTopicItems(){
        ObservableList<Topic> itemsTopic = FXCollections.observableArrayList();
        List<Topic> topicsList = meeting.getMeetingAgenda().getTopics();
        itemsTopic.addAll(topicsList);
        return itemsTopic;
    }
    
    private ObservableList<Prerequisite> makePrerequisiteItems(){
        ObservableList<Prerequisite> itemsPrerequisite = FXCollections.observableArrayList();
        List<Prerequisite> prerequisiteList = meeting.getMeetingAgenda().getPrerequisites();
        itemsPrerequisite.addAll(prerequisiteList);
        return itemsPrerequisite;
    }
    
    private ObservableList<String> makeitemsIntegrantForComboBox(){
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
            if(i<10){
                itemsHours.add("0" + i);
            }else{
                itemsHours.add("" + i);
            }
        }
        return itemsHours;
    }
    
    private ObservableList<String> makeitemsHoursListForTopic(){
        ObservableList<String> itemsHours = FXCollections.observableArrayList();
        int hours = 24;
        for(int i = 0; i <= hours; i++){
            if(i<10){
                itemsHours.add("0" + i);
            }else{
                itemsHours.add("" + i);
            }
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
    
    private ObservableList<String> makeitemsMinutesListForTopic(){
        ObservableList<String> itemsMinutes = FXCollections.observableArrayList();
        int minutes = 60;
        for(int i = 1; i <= minutes; i++){
            if(i<10){
                itemsMinutes.add("0" + i);
            }else{
                itemsMinutes.add("" + i);
            }
        }
        return itemsMinutes;
    }
    
    private void comproveMeetingDifferentNull(Meeting meeting){
        if (meeting == null){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(null, "Lo sentimos,no se pudo encontrar la información de la reunión");
            FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("MeetingHistory.fxml", null);
            MeetingHistoryController controller = loader.getController();
            controller.receiveToken(token);
        }
    }
    
}
