/**
 * @author estef
 * Last modification date format: 20-05-2021
 */

package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.domain.Agreement;
import sgp.ca.domain.AssistantRol;
import sgp.ca.domain.Comment;
import sgp.ca.domain.Meeting;
import sgp.ca.domain.Prerequisite;
import sgp.ca.domain.Topic;
import sgp.ca.businesslogic.MeetingDAO;

public class MeetingController implements Initializable {
    private final MeetingDAO MEETING_DAO = new MeetingDAO();
    private Meeting meeting = new Meeting();
    Calendar date = new GregorianCalendar();

    @FXML
    private Button btnCloseMeetingWindow;
    @FXML
    private Button btnCancelMeeting;
    @FXML
    private Button btnDownloadMinute;
    @FXML
    private TextField meetingRecordDateField;
    @FXML
    private TextField meetingProjectLabel;
    @FXML
    private TextField meetingIssueLabel;
    @FXML
    private TextField meetingPlaceLabel;
    @FXML
    private TextField estimatedTotalTimeLabel;
    @FXML
    private TextField meetingDateLabel;
    @FXML
    private TextField meetingTimeLabel;
    @FXML
    private TextField totalTimeLabel;
    @FXML
    private Button btnUpdateMeeting;
    @FXML
    private Button btnStartMeeting;
    @FXML
    private TableView<Comment> commentsTableView;
    @FXML
    private TableColumn<Comment, String> commentatorColumn;
    @FXML
    private TableColumn<Comment, String> commentDescriptionColumn;
    @FXML
    private TextArea txtAreaComment;
    @FXML
    private Button btnDeleteComment;
    @FXML
    private Button btnAddNewComment;
    @FXML
    private TableView<AssistantRol> assistantRolTableView;
    @FXML
    private TableColumn<AssistantRol, String> assistantNameColumn;
    @FXML
    private TableColumn<AssistantRol, String> rolAssistantColumn;
    @FXML
    private TableColumn<AssistantRol, String> initialsAssistantsColumn;
    @FXML
    private TableView<Prerequisite> prerequisitetableView;
    @FXML
    private TableColumn<Prerequisite, String> descriptionPrerequisiteColumn;
    @FXML
    private TableColumn<Prerequisite, String> responsiblePrerequisiteColumn;
    @FXML
    private TableView<Topic> meetingAgendaTableView;
    @FXML
    private TableColumn<Topic, String> startTimeColumn;
    @FXML
    private TableColumn<Topic, String> endTimeColumn;
    @FXML
    private TableColumn<Topic, String> plannedTimeColumn;
    @FXML
    private TableColumn<Topic, String> realTimeColumn;
    @FXML
    private TableColumn<Topic, String> descriptionTopicColumn;
    @FXML
    private TableColumn<Topic, String> discussionLeaderColumn;
    @FXML
    private TableView<Agreement> agreementTableView;
    @FXML
    private TableColumn<Agreement, String> descriptionAgreementColumn;
    @FXML
    private TableColumn<Agreement, String> deliveryDateColumn;
    @FXML
    private TableColumn<Agreement, String> responsibleAgreementColumn;
    @FXML
    private TextArea txtAreaNoteMeeting;
    @FXML
    private TextArea txtAreaPendingMeeting;

    public void reciveMeeting (int meetingKey){
        this.meeting = MEETING_DAO.getMeeting(meetingKey);
        this.setMeetingInformation();
        this.preparedCommentTable();
        this.preparedAssistantTable();
        this.preparedPrerequisiteTable();
        this.preparedAgendaMeetingTable();
        this.preparedAgreementTable();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    private void setMeetingInformation(){
        this.meetingRecordDateField.setText(meeting.getMeetingRegistrationDate());
        this.meetingProjectLabel.setText(meeting.getMeetingProject());
        this.meetingIssueLabel.setText(meeting.getIssueMeeting());
        this.meetingPlaceLabel.setText(meeting.getPlaceMeeting());
        this.estimatedTotalTimeLabel.setText(meeting.getMeetingAgenda().getEstimatedTotalTime());
        this.meetingDateLabel.setText(meeting.getMeetingDate());
        this.meetingTimeLabel.setText(meeting.getMeetingTime());
        this.totalTimeLabel.setText(meeting.getMeetingAgenda().getTotalTime());
        this.txtAreaNoteMeeting.setText(meeting.getMeetingNote());
        this.txtAreaPendingMeeting.setText(meeting.getMeetingPending());
    }

    @FXML
    void CancelMeeting(ActionEvent event) {
        if(meeting.getStatusMeeting().compareTo("Pendiente") == 0){
            meeting.setStatusMeeting("Cancelada");
            this.btnStartMeeting.setDisable(true);
        }else{
            AlertGenerator.showInfoAlert(event, "Esta reunión no se puede cancelar");
        }
    }

    @FXML
    void addNewComment(ActionEvent event) {
        String commentDescription = this.txtAreaComment.getText();
        String actualTime = date.get(Calendar.HOUR_OF_DAY) + ":" + date.get(Calendar.MINUTE) + ":" + date.get(Calendar.SECOND);
        String actualDate = date.get(Calendar.YEAR) + "-" + (date.get(Calendar.MONTH) + 1) + "-" + date.get(Calendar.DAY_OF_MONTH);
        Comment newComment = new Comment(0, commentDescription, "IntegrantName", actualTime, actualDate);
        meeting.getComments().add(newComment);
        this.preparedCommentTable();
        this.txtAreaComment.clear();
        
    }

    @FXML
    void closeMeetingWindow(ActionEvent event) {
        MEETING_DAO.updateMeeting(meeting, meeting);
        FXMLLoader loader = this.changeWindow("MeetingHistory.fxml", event);
        MeetingHistoryController controller = loader.getController();
    }

    @FXML
    void deleteComment(ActionEvent event) {
        Comment commentDeleted =  commentsTableView.getSelectionModel().getSelectedItem();
        meeting.removeComment(commentDeleted);
        this.preparedCommentTable();
    }

    @FXML
    void downloadMinute(ActionEvent event) {
        AlertGenerator.showInfoAlert(event, "Lo sentimos. La minuta no esta disponible por el momento");
    }

    @FXML
    void startMeeting(ActionEvent event) {
        if(meeting.getStatusMeeting().compareTo("Pendiente") == 0){
            //meeting.setStatusMeeting("En proceso");
        }else{
            AlertGenerator.showInfoAlert(event, "Esta reunión ya no se puede inicar");
        }
    }

    @FXML
    void updateMeeting(ActionEvent event) {
        if((meeting.getStatusMeeting().compareTo("Pendiente")) == 0){
            MEETING_DAO.updateMeeting(meeting, meeting);
            FXMLLoader loader = this.changeWindow("MeetingForm.fxml", event);
            MeetingFormController controller = loader.getController();
            controller.reciveMeeting(meeting);
        }
        if((meeting.getStatusMeeting().compareTo("Realizada")) == 0){
            MEETING_DAO.updateMeeting(meeting, meeting);
            FXMLLoader loader = this.changeWindow("UpdateMeeting.fxml", event);
            UpdateMeetingController controller = loader.getController();
            controller.reciveMeeting(meeting);
        }

    }
    
    private void preparedCommentTable(){
        commentatorColumn.setCellValueFactory(new PropertyValueFactory<Comment, String>("commentator"));
        commentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<Comment, String>("commentDescription"));
        commentsTableView.setItems(makeItemsForCommentTableView());
    
    }
    
    private void preparedAssistantTable(){
        assistantNameColumn.setCellValueFactory(new PropertyValueFactory<AssistantRol, String>("assistantRfc"));
        rolAssistantColumn.setCellValueFactory(new PropertyValueFactory<AssistantRol, String>("roleAssistant"));
        initialsAssistantsColumn.setCellValueFactory(new PropertyValueFactory<AssistantRol, String>("initialsAssistant"));
        assistantRolTableView.setItems(makeItemsForAssistantRolTableView());
    }
    
    private void preparedPrerequisiteTable(){
        descriptionPrerequisiteColumn.setCellValueFactory(new PropertyValueFactory<Prerequisite, String>("descrptionPrerequisite"));
        responsiblePrerequisiteColumn.setCellValueFactory(new PropertyValueFactory<Prerequisite, String>("responsiblePrerequisite"));
        prerequisitetableView.setItems(makeItemsForPrerequisiteTableView());
    }
    
    private void preparedAgendaMeetingTable() {
        startTimeColumn.setCellValueFactory(new PropertyValueFactory<Topic, String>("startTime"));
        endTimeColumn.setCellValueFactory(new PropertyValueFactory<Topic, String>("endTime"));
        plannedTimeColumn.setCellValueFactory(new PropertyValueFactory<Topic, String>("plannedTime"));
        realTimeColumn.setCellValueFactory(new PropertyValueFactory<Topic, String>("realTime"));
        descriptionTopicColumn.setCellValueFactory(new PropertyValueFactory<Topic, String>("descriptionTopic"));
        discussionLeaderColumn.setCellValueFactory(new PropertyValueFactory<Topic, String>("discissionLeader"));
        meetingAgendaTableView.setItems(makeItemsForMeetingAgendaTableView());
    }
    
    private void preparedAgreementTable(){
        descriptionAgreementColumn.setCellValueFactory(new PropertyValueFactory<Agreement, String>("descriptionAgreement"));
        deliveryDateColumn.setCellValueFactory(new PropertyValueFactory<Agreement, String>("deliveryDate"));
        responsibleAgreementColumn.setCellValueFactory(new PropertyValueFactory<Agreement, String>("responsibleAgreement"));
        agreementTableView.setItems(makeItemsForAgreementTableView());
    }
    
    private ObservableList<Comment> makeItemsForCommentTableView(){
        ObservableList<Comment> itemsComment = FXCollections.observableArrayList();
        List commentsList = meeting.getComments();
        itemsComment.addAll(commentsList);
        return itemsComment;
    }
    
    private ObservableList<AssistantRol> makeItemsForAssistantRolTableView(){
        ObservableList<AssistantRol> itemsAssisntantRol = FXCollections.observableArrayList();
        List assistantsList = meeting.getAssistantsRol();
        itemsAssisntantRol.addAll(assistantsList);
        return itemsAssisntantRol;
    }
    
    private ObservableList<Prerequisite> makeItemsForPrerequisiteTableView(){
        ObservableList<Prerequisite> itemsPrerequisite = FXCollections.observableArrayList();
        List prerequisitesList = meeting.getMeetingAgenda().getPrerequisites();
        itemsPrerequisite.addAll(prerequisitesList);
        return itemsPrerequisite;
    }
    
    private ObservableList<Topic> makeItemsForMeetingAgendaTableView(){
        ObservableList<Topic>itemsTopic = FXCollections.observableArrayList();
        List topicsList = meeting.getMeetingAgenda().getTopics();
        itemsTopic.addAll(topicsList);
        return itemsTopic;
    }
    
    private ObservableList<Agreement> makeItemsForAgreementTableView(){
        ObservableList<Agreement> itemsAgreement = FXCollections.observableArrayList();
        List agreementsList = meeting.getAgreements();
        itemsAgreement.addAll(agreementsList);
        return itemsAgreement;
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
