/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Integrant;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class IntegrantRequestController implements Initializable {

    @FXML
    private HBox hboxIntegrantOptions;
    @FXML
    private Button btnUnsubscribe;
    @FXML
    private Button btnSubscribe;
    @FXML
    private Button btnIntegrantUpdate;
    @FXML
    private Button btnExit;
    @FXML
    private Label lblParticipationType;
    @FXML
    private TextField memberRFCField;
    @FXML
    private TextField memberFullNameField;
    @FXML
    private TextField memberEmailUVField;
    @FXML
    private TextField memberCurpField;
    @FXML
    private TextField memberNationalityField;
    @FXML
    private TextField memberEducationalProgramField;
    @FXML
    private TextField memberCellNumberField;
    @FXML
    private TextField memberStaffNumberField;
    @FXML
    private DatePicker memberRegistrationDateField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField fieldParticipationStatus;

    private Integrant token;
    private Integrant integrant;
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void showIntegrantByEmail(Integrant integrantToken, String emailUV){
        this.token = integrantToken;
        this.integrant = (Integrant) INTEGRANT_DAO.getMemberByUVmail(emailUV);
        if(integrant != null){
            setIntegrantDataIntoInterface();
        }
    }

    @FXML
    private void unsubscribeIntegrant(ActionEvent event) {
        if(INTEGRANT_DAO.unsubscribeMemberByEmailUV(integrant.getEmailUV())){
            AlertGenerator.showInfoAlert(event, "El integrante ha sido dado de baja");
        }else{
            AlertGenerator.showErrorAlert(event, "Error del sistema, favor de contactas a soporte técnico");
        }
        FXMLLoader loader = changeWindow("Start.fxml", event);
        StartController controller = loader.getController();
        controller.receiveIntegrantToken(this.token);
    }

    @FXML
    private void subscribeIntegrant(ActionEvent event) {
        if(INTEGRANT_DAO.subscribeMemberByEmailUV(integrant.getEmailUV())){
            AlertGenerator.showInfoAlert(event, "El integrante ha sido dado de alta");
        }else{
            AlertGenerator.showErrorAlert(event, "Error del sistema, favor de contactas a soporte técnico");
        }
        FXMLLoader loader = changeWindow("Start.fxml", event);
        StartController controller = loader.getController();
        controller.receiveIntegrantToken(this.token);
    }

    @FXML
    private void updateIntegrant(ActionEvent event) {
        FXMLLoader loader = changeWindow("IntegrantEditable.fxml", event);
        IntegrantEditableController controller = loader.getController();
        controller.showIntegrantUpdateForm(this.token, integrant.getEmailUV());
    }

    @FXML
    private void exit(ActionEvent event) {
        FXMLLoader loader = changeWindow("GeneralResumeRequest.fxml", event);
        GeneralResumeRequestController controller = loader.getController();
        controller.showGeneralResume(this.token);
    }
    
    private void setIntegrantDataIntoInterface(){
        this.passwordField.setText(this.integrant.getPassword());
        this.memberCellNumberField.setText(this.integrant.getCellphone());
        this.memberCurpField.setText(this.integrant.getCurp());
        this.memberEducationalProgramField.setText(this.integrant.getEducationalProgram());
        this.memberEmailUVField.setText(this.integrant.getEmailUV());
        this.memberFullNameField.setText(this.integrant.getFullName());
        this.memberNationalityField.setText(this.integrant.getNationality());
        this.memberRFCField.setText(this.integrant.getRfc());
        this.memberRegistrationDateField.setValue(LocalDate.parse(this.integrant.getDateOfAdmission()));
        this.memberStaffNumberField.setText(String.valueOf(this.integrant.getStaffNumber()));
        this.lblParticipationType.setText(this.integrant.getParticipationType());
        this.fieldParticipationStatus.setText(this.integrant.getParticipationStatus());
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
