/**
 * @author estef
 * Last modification date format: 20-05-2021
 */

package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.MeetingDAO;
import sgp.ca.domain.Meeting;

public class MeetingHistoryController implements Initializable {
    
    private final MeetingDAO MEETING_DAO = new MeetingDAO();
    private List<Meeting> meetingsList = new ArrayList<>();
    
    @FXML
    private Button btnClose;
    @FXML
    private Button btnSearchMeeting;
    @FXML
    private Button btnAddMeting;
    @FXML
    private TextField meetingIssueField;
    @FXML
    private DatePicker meetingDateField;
    @FXML
    private TableView<Meeting> meetingHistoryTableView;
    @FXML
    private TableColumn<Meeting, String> columnIssueMeeting;
    @FXML
    private TableColumn<Meeting, String> columnMeetingDate;
    @FXML
    private TableColumn<Meeting, String> columnMeetingTime;
    @FXML
    private TableColumn<Meeting, String> columnIntegrantResponsibleMeeting;
    @FXML
    private Button btnRefreshTable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        preparedMeetingTable();
    }   
    
    
    
    @FXML
    void addMeeting(ActionEvent event) {
        FXMLLoader loader = this.changeWindow("MeetingForm.fxml", event);
        MeetingFormController controller = loader.getController();
        
    }

    @FXML
    void closeMeetingHistoryWindow(ActionEvent event) {
        

    }

    @FXML
    void refreshMeetingHistoryMeeting(ActionEvent event) {
        meetingHistoryTableView.getItems().clear();
        setAllMeetingInformationIntoTable();
        
    }
    
    @FXML
    void observeMeetingInformation(MouseEvent event) {
        FXMLLoader loader = changeWindow("Meeting.fxml", event);
        MeetingController controller = loader.getController();
        controller.reciveMeeting(
            meetingHistoryTableView.getSelectionModel().getSelectedItem().getMeetingKey()
        );

    }

    @FXML
    void searchMeeting(ActionEvent event) {
        System.out.print(meetingIssueField.getText());
        if((meetingIssueField.getText())== null){
            if((meetingDateField.getValue())!=null){
                meetingHistoryTableView.getItems().clear();
                setMeetingInformationByDate();
            }    
        }else {
            if((meetingDateField.getValue())!=null){
                meetingHistoryTableView.getItems().clear();
                setMeetingInformationByDateAndIssueIntoTable();
            }else{
                meetingHistoryTableView.getItems().clear();
                setMeetingInformationByIssue();
            }  
        }
        this.meetingIssueField.clear();
        this.meetingDateField.setValue(null);
    }
    
    private ObservableList<Meeting> makeitemsAllMeetings(){
        ObservableList<Meeting> itemsMeeting = FXCollections.observableArrayList();
        meetingsList.addAll(MEETING_DAO.getAllMeetings());
        itemsMeeting.addAll(meetingsList);
        return itemsMeeting;
    }
    
    private ObservableList<Meeting> makeitemsMeetings(List meetingList){
        ObservableList<Meeting> itemsMeeting = FXCollections.observableArrayList();
        itemsMeeting.addAll(meetingList);
        return itemsMeeting;
    }
    
    private void preparedMeetingTable(){
        columnIssueMeeting.setCellValueFactory(new PropertyValueFactory<Meeting, String>("issueMeeting"));
        columnMeetingDate.setCellValueFactory(new PropertyValueFactory<Meeting, String>("meetingDate"));
        columnMeetingTime.setCellValueFactory(new PropertyValueFactory<Meeting, String>("meetingTime"));
        columnIntegrantResponsibleMeeting.setCellValueFactory(new PropertyValueFactory<Meeting,String>("integrantResponsible"));
        meetingHistoryTableView.setItems(makeitemsAllMeetings());
    }
    
    private void setAllMeetingInformationIntoTable(){
        List meetingslist = this.meetingsList;
        meetingHistoryTableView.setItems(makeitemsMeetings(meetingslist));
    }
    
    private void setMeetingInformationByDateAndIssueIntoTable(){
        String dateMeeting = converterDate(meetingDateField.getValue().toString());
        String meetingIssue = (meetingIssueField.getText());
        List meetingslist = new ArrayList<>();
        
        for(int i=0; i< this.meetingsList.size(); i++){
            if((this.meetingsList.get(i).getMeetingDate() == dateMeeting) && 
            (this.meetingsList.get(i).getIssueMeeting().contains(meetingIssue))){
                meetingslist.add(this.meetingsList.get(i));
            }
        }
       meetingHistoryTableView.setItems(makeitemsMeetings(meetingslist));
    }
    
    private void setMeetingInformationByIssue(){
        String meetingIssue = (meetingIssueField.getText());
        List meetingslist = new ArrayList<>();
        for(int i=0; i< this.meetingsList.size(); i++){
            if(this.meetingsList.get(i).getIssueMeeting().contains(meetingIssue)){
                meetingslist.add(this.meetingsList.get(i));
            }
        }
        meetingHistoryTableView.setItems(makeitemsMeetings(meetingslist));
    }
    
    private void setMeetingInformationByDate(){
        String meetingDate = converterDate(meetingDateField.getValue().toString());
        System.out.print(meetingDate);
        List meetingslist = new ArrayList<>();
        for(int i=0; i<this.meetingsList.size(); i++){
            if(this.meetingsList.get(i).getMeetingDate() == meetingDate){
                meetingslist.add(this.meetingsList.get(i));
            }
        }
        meetingHistoryTableView.setItems(makeitemsMeetings(meetingslist));
        
    }
    
    private String converterDate(String date){
        String newDate= "";
        try{
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateConverted = formatter.parse(date);
            newDate = targetFormat.format(dateConverted);
            System.out.print(newDate);
        }catch(ParseException parseException){
            Logger.getLogger(MeetingHistoryController.class.getName()).log(Level.SEVERE, null, parseException);
        }finally{
            return newDate;
        }
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
