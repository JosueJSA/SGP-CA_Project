/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sgp.ca.businesslogic.MeetingDAO;
import sgp.ca.domain.Agreement;
import sgp.ca.domain.Meeting;
import sgp.ca.domain.Topic;

/**
 * FXML Controller class
 *
 * @author johan
 */
public class StartMeetingController implements Initializable {

    @FXML
    private TableView<Topic> tvAgenda;
    @FXML
    private TableColumn<Topic, String> colDescriptionTopic;
    @FXML
    private TableColumn<Topic, String> colStartTime;
    @FXML
    private TableColumn<Topic, String> colEndTime;
    @FXML
    private TextField txtFieldDescriptionAgreement;
    @FXML
    private TextField txtFieldResponsibleAgreement;
    @FXML
    private Button btnAddAgreement;
    @FXML
    private Button btnDeleteAgreement;
    @FXML
    private TableView<Agreement> tvAgreement;
    @FXML
    private TableColumn<Agreement, String> coldescriptionAgreement;
    @FXML
    private TableColumn<Agreement, String> colResponsibleAgreement;
    @FXML
    private TableColumn<Agreement, Date> coldeliveryDate;
    @FXML
    private Button btnNextTopic;
    @FXML
    private TextField txtFieldCurrentTopic;
    @FXML
    private TextArea txtAreaNoteMeeting;
    @FXML
    private TextArea txtAreaPendingMeeting;
    @FXML
    private Button btnEndMeeting;
    @FXML
    private Button btnExitMeeting;

    private Meeting MEETING;
    private final MeetingDAO MEETING_DAO = new MeetingDAO();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        this.receiveMeetingKey(6);
        coldescriptionAgreement.setCellValueFactory(new PropertyValueFactory<>("descriptionAgreement"));
        colResponsibleAgreement.setCellValueFactory(new PropertyValueFactory<>("responsibleAgreement"));
        coldeliveryDate.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));
        
    }    

    public void receiveMeetingKey(int meetingKey){
        this.MEETING = MEETING_DAO.getMeeting(meetingKey);
        preparedAgendaMeetingTable();
        //MEETING_DAO.updateMeetingStarted(meeting, meeting)
    }

    @FXML
    private void addAgreement(ActionEvent event){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        List<String> listRequirements = new ArrayList<>();
//         try{
//            this.isValidForm();
            Agreement agreement = new Agreement(
                001,
                txtFieldDescriptionAgreement.getText(),
                txtFieldResponsibleAgreement.getText(),
                dateFormat.format(date)
            );
            ObservableList<Agreement> itemsAgreement = FXCollections.observableArrayList();
            itemsAgreement.add(agreement);
            System.out.println(agreement.getDescriptionAgreement());
            System.out.println(agreement.getResponsibleAgreement());
            System.out.println(agreement.getDeliveryDate());
            tvAgreement.getItems().addAll(itemsAgreement);
            txtFieldDescriptionAgreement.setText("");
            txtFieldResponsibleAgreement.setText("");
//        }catch(InvalidFormException ie){
//            AlertGenerator.showErrorAlert(event, ie.getMessage());
//        }
    }

    @FXML
    private void deleteAgreement(ActionEvent event){
        int indexSelection = tvAgreement.getSelectionModel().getSelectedIndex();
        if(indexSelection >= 0){
            tvAgreement.getItems().remove(indexSelection);
        }else{
            //Alert
            System.out.println("No se seleccionó un acuerdo");
        }
    }

    @FXML
    private void endMeeting(ActionEvent event){
        //Validar
        MEETING.setAgreements(tvAgreement.getItems());
        MEETING.setMeetingNote(txtAreaNoteMeeting.getText());
        MEETING.setMeetingPending(txtAreaPendingMeeting.getText());
        MEETING.setStatusMeeting("Finalizada");
        //Actualizar
    }

    @FXML
    private void exitMeeting(ActionEvent event){
    }
    
     private void preparedAgendaMeetingTable(){
        colDescriptionTopic.setCellValueFactory(new PropertyValueFactory<Topic, String>("descriptionTopic"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<Topic, String>("startTime"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<Topic, String>("endTime"));
        tvAgenda.setItems(makeItemsForMeetingAgendaTableView());
        txtFieldCurrentTopic.setText(tvAgenda.getItems().get(0).getDescriptionTopic());
    }
    
    private ObservableList<Topic> makeItemsForMeetingAgendaTableView(){
        DateTimeFormatter FormatTime = DateTimeFormatter.ofPattern("HH:mm");
        ObservableList<Topic>itemsTopic = FXCollections.observableArrayList();
        List topicsList = MEETING.getMeetingAgenda().getTopics();
        itemsTopic.addAll(topicsList);
        itemsTopic.get(0).setStartTime(FormatTime.format(LocalDateTime.now()));
        return itemsTopic;
    }

    @FXML
    private void nextTopic(ActionEvent event){
        DateTimeFormatter FormatTime = DateTimeFormatter.ofPattern("HH:mm");
        for(Topic topic : tvAgenda.getItems()){
            if (topic.getEndTime().equals("00:00:00")){
                topic.setEndTime(FormatTime.format(LocalDateTime.now()));
                break;
            }
        }
    }
}
