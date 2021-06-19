/**
 * @author Estefanía 
 * @versión v1.0
 * Last modification date: 17-06-2021
 */

package sgp.ca.demodao;



import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.businesslogic.MeetingDAO;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Meeting;
import sgp.ca.domain.Prerequisite;
import sgp.ca.domain.Topic;

public class MeetingEditController implements Initializable{
    
    @FXML
    private Label lbWindowName;
    @FXML
    private Label lblUserName;
    @FXML
    private Button btnUpdateMeeting;
    @FXML
    private Button btnRegisterMeeting;
    @FXML
    private Button btnClose;
    @FXML
    private TextField txtFieldMeetingProject;
    @FXML
    private TextField txtFieldPlaceMeeting;
    @FXML
    private DatePicker dtpMeetingDate;
    @FXML
    private ComboBox<String> cboBoxMeetingHour;
    @FXML
    private ComboBox<String> cboBoxMeetingMinute;
    @FXML
    private TextField txtFieldIssueMeeting;
    @FXML
    private TableView<AssistantTable> tvAssistans;
    @FXML
    private TableColumn<AssistantTable, String> colIntegrantName;
    @FXML
    private TableColumn<AssistantTable, RadioButton> colSelectAssistant;
    @FXML
    private TableColumn<AssistantTable, RadioButton> colDiscussionLeader;
    @FXML
    private TableColumn<AssistantTable, RadioButton> colSecretary;
    @FXML
    private TableColumn<AssistantTable, RadioButton> colTimeTaker;
    @FXML
    private Button btnAddRowPrerequisiteTable;
    @FXML
    private Button btnRemoveRowPrerequisiteTable;
    @FXML
    private TextField txtFieldDescriptionPrerequisite;
    @FXML
    private ComboBox<String> cboBoxResponsiblePrerequisite;
    @FXML
    private TableView<Prerequisite> tvPrerequisite;
    @FXML
    private TableColumn<Prerequisite, String> colDescriptionPrerequisite;
    @FXML
    private TableColumn<Prerequisite, String> colResponsiblePrerequisite;
    @FXML
    private Button btnAddRowMeetingAgendaTable;
    @FXML
    private Button btnRemoveRowMeetingAgendaTable;
    @FXML
    private ComboBox<String> cboBoxHourTopic;
    @FXML
    private ComboBox<String> cboBoxMinuteTopic;
    @FXML
    private TextField txtFieldDescriptionTopic;
    @FXML
    private ComboBox<String> cboBoxDiscissionLeaderTopic;
    @FXML
    private TableView<Topic> tvMeetingAgenda;
    @FXML
    private TableColumn<Topic, String> colEstimatedTime;
    @FXML
    private TableColumn<Topic, String> colDescriptionTopic;
    @FXML
    private TableColumn<Topic, String> colDiscussionLeaderTopic;
    @FXML
    private Label lbUserName;
    
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
        this.lbUserName.setText(token.getFullName());
    }
    
    public void reciveMeeting (Meeting meeting){ 
        this.meeting = meeting;
        setMeetingInformation();
        prepareAssistantTable();
        preparePrerequisiteTable();
        prepareMeetingAgendaTable();
    }
    
    private void setMeetingInformation(){
        LocalDate meetingDate = LocalDate.parse(meeting.getMeetingDate());
        this.dtpMeetingDate.setValue(meetingDate);
        this.cboBoxMeetingHour.setValue(meeting.getMeetingTime().substring(0, 2));
        this.cboBoxMeetingMinute.setValue(meeting.getMeetingTime().substring(3, 5));
        this.txtFieldMeetingProject.setText(meeting.getMeetingProject());
        this.txtFieldPlaceMeeting.setText(meeting.getPlaceMeeting());
        this.txtFieldIssueMeeting.setText(meeting.getIssueMeeting());
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
        ValidatorForm.checkNotEmptyDateField(this.dtpMeetingDate);
        ValidatorForm.chechkAlphabeticalField(this.txtFieldMeetingProject, 1, 80);
        ValidatorForm.isComboBoxSelected(this.cboBoxMeetingHour);
        ValidatorForm.isComboBoxSelected(this.cboBoxMeetingMinute);
        ValidatorForm.chechkAlphabeticalField(this.txtFieldPlaceMeeting, 1 ,  80);
        ValidatorForm.chechkAlphabeticalField(this.txtFieldIssueMeeting,1, 200);
        
    }
    
    private void getOutMeetingInformation(){
        String hourMeeting = this.cboBoxMeetingHour.getValue();
        String minuteMeting = this.cboBoxMeetingMinute.getValue();
        this.meeting.setMeetingDate(this.dtpMeetingDate.getValue().toString());
        this.meeting.setMeetingTime(hourMeeting + ":" + minuteMeting + ":00");
        this.meeting.setMeetingProject(this.txtFieldMeetingProject.getText());
        this.meeting.setMeetingRegistrationDate(
            date.get(Calendar.YEAR) + "-" + (date.get(Calendar.MONTH) + 1) + "-" + date.get(Calendar.DAY_OF_MONTH)
        );
        this.meeting.setStatusMeeting("Pendiente");
        this.meeting.setPlaceMeeting(this.txtFieldPlaceMeeting.getText());
        this.meeting.setIssueMeeting(this.txtFieldIssueMeeting.getText());
        this.meeting.setIntegrantResponsible(token.getRfc());
        this.meeting.setMeetingNote("");
        this.meeting.setMeetingPending("");
       
    }
    
    
    private void setIntoMeetingAgendaInformation(){
        this.meeting.getMeetingAgenda().setTotalTime("00:00:00");
        this.meeting.getMeetingAgenda().setEstimatedTotalTime("00:00:00");
        this.meeting.getMeetingAgenda().setTotaltopics(meeting.getMeetingAgenda().getTopics().size());
    }
    
    private void validateTopicInformation() throws InvalidFormException{
        ValidatorForm.isComboBoxSelected(this.cboBoxHourTopic);
        ValidatorForm.isComboBoxSelected(this.cboBoxMinuteTopic);
        ValidatorForm.chechkAlphabeticalField(this.txtFieldDescriptionTopic, 3 , 200);
        ValidatorForm.isComboBoxSelected(this.cboBoxDiscissionLeaderTopic);
    }

    @FXML
    private void addRowMeetingAgendaTable(ActionEvent event){
        try{
            validateTopicInformation();
            this.meeting.getMeetingAgenda().getTopics().add(getOutTopicInformtation());
            tvMeetingAgenda.setItems(makeTopicItems());
            clearCampsOfTopic();
        }catch(InvalidFormException ex){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, ex.getMessage());
        }
    }
    
    private Topic getOutTopicInformtation(){
        String hourTopic = this.cboBoxHourTopic.getValue();
        String minuteTopic = this.cboBoxMinuteTopic.getValue();
        String planedTimeTopic = hourTopic + ":" + minuteTopic + ":00";
        String descriptionTopic = this.txtFieldDescriptionTopic.getText();
        String discissionLeader = this.cboBoxDiscissionLeaderTopic.getValue();
        
        Topic newTopic = new Topic(
            0, "00:00:00", "00:00:00", planedTimeTopic, "00:00:00", descriptionTopic, discissionLeader, "Pendiente"
        );
        return newTopic;
    }
    
    private void clearCampsOfTopic(){
        this.cboBoxHourTopic.setValue(null);
        this.cboBoxMinuteTopic.setValue(null);
        this.txtFieldDescriptionTopic.clear();
        this.cboBoxDiscissionLeaderTopic.setValue(null);
    }
    
    private void validatePrerequisiteInformation() throws InvalidFormException{
        ValidatorForm.chechkAlphabeticalField(this.txtFieldDescriptionPrerequisite, 1 ,  200);
        ValidatorForm.isComboBoxSelected(this.cboBoxResponsiblePrerequisite);
    }

    @FXML
    private void addRowPrerequisiteTable(ActionEvent event){
        try{
            validatePrerequisiteInformation();
            meeting.getMeetingAgenda().getPrerequisites().add(getOutPrerequisiteInformation());
            tvPrerequisite.setItems(makePrerequisiteItems());
            clearCampsOfPrerequisite();
        }catch(InvalidFormException ex){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, ex.getMessage());
        }
    }
    
    private Prerequisite getOutPrerequisiteInformation(){
        String descriptionPrerequisite = this.txtFieldDescriptionPrerequisite.getText();
        String responsiblePrerequisite = this.cboBoxResponsiblePrerequisite.getValue();
        Prerequisite newPrerequisite = new Prerequisite(
            0, responsiblePrerequisite, descriptionPrerequisite
        );
        return newPrerequisite;
    }
    
    private void clearCampsOfPrerequisite(){
        this.txtFieldDescriptionPrerequisite.clear();
        this.cboBoxResponsiblePrerequisite.setValue(null);
    }

    @FXML
    private void closeMeetingFormWindow(ActionEvent event){
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
    private void removeRowMeetingAgendaTable(ActionEvent event){
        Topic topicToRemove = tvMeetingAgenda.getSelectionModel().getSelectedItem();
        meeting.getMeetingAgenda().getTopics().remove(topicToRemove);
        tvMeetingAgenda.setItems(makeTopicItems());
    }

    @FXML
    private void removeRowPrerequisiteTable(ActionEvent event){
        Prerequisite prerequisiteToRemove = tvPrerequisite.getSelectionModel().getSelectedItem();
        meeting.getMeetingAgenda().getPrerequisites().remove(prerequisiteToRemove);
        tvPrerequisite.setItems(makePrerequisiteItems());
    }
    
    @FXML
    private void updateMeeting(ActionEvent event){
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
        colIntegrantName.setCellValueFactory(new PropertyValueFactory<AssistantTable, String>("integrantName"));
        colSelectAssistant.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("assistant"));
        colDiscussionLeader.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("discussionLeader"));
        colSecretary.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("secretary"));
        colTimeTaker.setCellValueFactory(new PropertyValueFactory<AssistantTable, RadioButton>("takerTime"));
        tvAssistans.setItems(makeitemsIntegrant());
    }
    
    private void preparePrerequisiteTable(){
        colDescriptionPrerequisite.setCellValueFactory(new PropertyValueFactory<Prerequisite, String>("descrptionPrerequisite"));
        colResponsiblePrerequisite.setCellValueFactory(new PropertyValueFactory<Prerequisite, String>("responsiblePrerequisite"));
        tvPrerequisite.setItems(makePrerequisiteItems());
    }
    
    private void prepareMeetingAgendaTable(){
        colEstimatedTime.setCellValueFactory(new PropertyValueFactory<Topic, String>("plannedTime"));
        colDescriptionTopic.setCellValueFactory(new PropertyValueFactory<Topic, String>("descriptionTopic"));
        colDiscussionLeaderTopic.setCellValueFactory(new PropertyValueFactory<Topic, String>("discissionLeader"));
        tvMeetingAgenda.setItems(makeTopicItems());
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
