/**
 * @author estef
 * Last modification date format: 20-05-2021
 */

package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.businesslogic.MeetingDAO;
import sgp.ca.domain.Agreement;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Meeting;

public class MeetingRealizedEditController implements Initializable {
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private final MeetingDAO MEETING_DAO = new MeetingDAO();
    private Meeting meeting = new Meeting();
    
    @FXML
    private Button btnUpdateMeeting;

    @FXML
    private Button btnCloseUpdateMeetingWindow;

    @FXML
    private Button btnAddAgreementFile;

    @FXML
    private Button btnUpdateAgreementFile;

    @FXML
    private Button btnDeleteAgreementFile;

    @FXML
    private TextField descriptionAgreementField;

    @FXML
    private DatePicker deliveryDateField;

    @FXML
    private ComboBox<String> cboBoxResponsibleAgreement;

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
    @FXML
    private Label lblUserName;
    
    private Integrant token;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cboBoxResponsibleAgreement.setItems(makeitemsIntegrantForComboBox());
    }
    
    public void reciveMeeting (Meeting meeting){
        this.meeting = meeting;
        setMeetingInformation();
        prepareAgreementTable();
    }
    
    public void receiveToken(Integrant integrantToken){
        this.token = integrantToken;
        this.lblUserName.setText(token.getFullName());
    }
    
    private void setMeetingInformation(){
        this.txtAreaNoteMeeting.setText(meeting.getMeetingNote());
        this.txtAreaPendingMeeting.setText(meeting.getMeetingPending());
    }
    
    private void validateAgreementCamps() throws InvalidFormException{
        ValidatorForm.chechkAlphabeticalField(this.descriptionAgreementField, 5 , 200);
        ValidatorForm.checkNotEmptyDateField(this.deliveryDateField);
        ValidatorForm.isComboBoxSelected(this.cboBoxResponsibleAgreement);
    }

    @FXML
    private void addAgreementFile(ActionEvent event) {
        try{
            validateAgreementCamps();
            meeting.getAgreements().add(getOutCommentInformation());
            this.prepareAgreementTable();
            clearAgreementCamps();
        }catch(InvalidFormException ex){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, ex.getMessage());
        }

    }
    
    private Agreement getOutCommentInformation(){
        String agreementDescription = this.descriptionAgreementField.getText();
        String agreementDeliveyDate = this.deliveryDateField.getValue().toString();
        String agreementIntegrantResponsible = this.cboBoxResponsibleAgreement.getValue();
        Agreement newAgreement = new Agreement(0, agreementDescription, agreementIntegrantResponsible, agreementDeliveyDate);
        return newAgreement;
    }
    
    private void clearAgreementCamps(){
        this.descriptionAgreementField.clear();
        this.deliveryDateField.setValue(null);
        this.cboBoxResponsibleAgreement.setValue(null);
    }

    @FXML
    private void closeUpdateMeetingWindow(ActionEvent event) {
        Optional<ButtonType> action = GenericWindowDriver.getGenericWindowDriver().showConfirmacionAlert(event,
        "¿Seguro que deseas salir del registro de reunión? Los cambios no se guardarán");
        if(action.get() == ButtonType.OK){
            FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("MeetingRequest.fxml", btnCloseUpdateMeetingWindow);
            MeetingRequestController controller = loader.getController();
            controller.reciveMeeting(meeting.getMeetingKey());
            controller.receiveToken(token);
        }
    }

    @FXML
    private void deleteAgreementFile(ActionEvent event) {
        Agreement agreementForDelete = this.agreementTableView.getSelectionModel().getSelectedItem();
        meeting.getAgreements().remove(agreementForDelete);
        this.agreementTableView.setItems(makeItemsAgreementTable());
    }

    @FXML
    private void updateAgreementFile(ActionEvent event) {
        Agreement agreementForUpdate = this.agreementTableView.getSelectionModel().getSelectedItem();
        setAgreementInformationsCamps(agreementForUpdate);
        meeting.getAgreements().remove(agreementForUpdate);
    }
    
    private void setAgreementInformationsCamps(Agreement agreement){
        this.descriptionAgreementField.setText(agreement.getDescriptionAgreement());
        LocalDate deliveryDate = LocalDate.parse(agreement.getDeliveryDate());
        this.deliveryDateField.setValue(deliveryDate);
        this.cboBoxResponsibleAgreement.setValue(agreement.getResponsibleAgreement());
    }

    @FXML
    private void updateMeeting(ActionEvent event) {
        try{
            validateMeetingInformation();
            meeting.setMeetingNote(this.txtAreaNoteMeeting.getText());
            meeting.setMeetingPending(this.txtAreaPendingMeeting.getText());
            if(MEETING_DAO.updateMeeting(meeting, meeting)){
                GenericWindowDriver.getGenericWindowDriver().showInfoAlert(event, "Reunión modificada con éxito");
            }else{
                GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, "Error en el sistema, favor de ponerse en contacto con sopoerte técnico");
            }
            FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("MeetingRequest.fxml", btnCloseUpdateMeetingWindow);
            MeetingRequestController controller = loader.getController();
            controller.receiveToken(token);
            controller.reciveMeeting(meeting.getMeetingKey());
        }catch(InvalidFormException ex){
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, ex.getMessage());
        }
    }
    
    private void validateMeetingInformation() throws InvalidFormException{
        ValidatorForm.chechkAlphabeticalArea(this.txtAreaNoteMeeting, 0, 3000);
        ValidatorForm.chechkAlphabeticalArea(this.txtAreaPendingMeeting, 0, 3000);
    }
    
    private void prepareAgreementTable(){
        this.descriptionAgreementColumn.setCellValueFactory(new PropertyValueFactory<Agreement, String>("descriptionAgreement"));
        this.deliveryDateColumn.setCellValueFactory(new PropertyValueFactory<Agreement, String>("deliveryDate"));
        this.responsibleAgreementColumn.setCellValueFactory(new PropertyValueFactory<Agreement, String>("responsibleAgreement"));
        this.agreementTableView.setItems(makeItemsAgreementTable());
    }
    
    private ObservableList<Agreement> makeItemsAgreementTable(){
        ObservableList<Agreement> itemsAgreement = FXCollections.observableArrayList();
        List agreementsList = meeting.getAgreements();
        itemsAgreement.addAll(agreementsList);
        return itemsAgreement;
    }
    
    private ObservableList<String> makeitemsIntegrantForComboBox(){
        ObservableList<String> itemsIntegrant = FXCollections.observableArrayList();
        List<String> integrantsName = INTEGRANT_DAO.getAllIntegrantsName();
        itemsIntegrant.addAll(integrantsName);
        return itemsIntegrant;
    }
    
}
