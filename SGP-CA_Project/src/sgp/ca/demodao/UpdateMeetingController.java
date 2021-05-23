/**
 * @author estef
 * Last modification date format: 20-05-2021
 */

package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.domain.Meeting;

public class UpdateMeetingController implements Initializable {
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
    private ComboBox<?> cboBoxResponsibleAgreement;

    @FXML
    private TableView<?> agreementTableView;

    @FXML
    private TableColumn<?, ?> descriptionAgreementColumn;

    @FXML
    private TableColumn<?, ?> deliveryDateColumn;

    @FXML
    private TableColumn<?, ?> responsibleAgreementColumn;

    @FXML
    private TextArea txtAreaNoteMeeting;

    @FXML
    private TextArea txtAreaPendingMeeting;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void reciveMeeting (Meeting meeting){
        this.meeting = meeting;
    }

    @FXML
    void addAgreementFile(ActionEvent event) {

    }

    @FXML
    void closeUpdateMeetingWindow(ActionEvent event) {

    }

    @FXML
    void deleteAgreementFile(ActionEvent event) {

    }

    @FXML
    void updateAgreementFile(ActionEvent event) {

    }

    @FXML
    void updateMeeting(ActionEvent event) {

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
