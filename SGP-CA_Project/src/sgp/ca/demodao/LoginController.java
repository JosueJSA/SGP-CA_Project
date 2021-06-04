/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sgp.ca.businesslogic.GeneralResumeDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.Integrant;

/**
 * FXML Controller class
 *
 * @author josue
 */
public class LoginController implements Initializable {

    @FXML
    private Label btnSignUpNewBdyAcademy;
    @FXML
    private Label btnSignUpNewUser;
    @FXML
    private TextField fieldEmailUv;
    @FXML
    private PasswordField fieldPasswordMailUv;
    @FXML
    private TextField fieldBodyAcademyKey;
    @FXML
    private Button btnSignIn;
    
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    private Integrant integrantLogger;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        integrantLogger = new Integrant();
    }    

    @FXML
    private void signIn(ActionEvent event) {
        if(checkUserLoginWithBodyKey()){
            FXMLLoader loader = changeWindow("Start.fxml", event);
            StartController controller = loader.getController();
            controller.receiveIntegrantToken(integrantLogger);
        }else{
            AlertGenerator.showErrorAlert(event, "Hay campos inválidos en el formulario");
        }
    }
    
    @FXML
    private void signUpNewBdyAcademy(MouseEvent event) {
        if(checkUserLogin() && this.integrantLogger.getBodyAcademyKey()== null){
            FXMLLoader loader = changeWindow("GeneralResumeEditable.fxml", event);
            GeneralResumeEditableController controller = loader.getController();
            controller.showGeneralResumeInsertForm(integrantLogger);
        }else{
            AlertGenerator.showErrorAlert(event, "El email y contraseña deben estar correctos para");
        }
    }

    @FXML
    private void signUpNewUser(MouseEvent event) {
        FXMLLoader loader = changeWindow("IntegrantEditable.fxml", event);
        IntegrantEditableController controller = loader.getController();
        controller.showResponsibleInscriptionForm();
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
    
    private boolean checkUserLogin(){
        integrantLogger.setEmailUV(this.fieldEmailUv.getText());
        integrantLogger.setPassword(this.fieldPasswordMailUv.getText());
        integrantLogger = INTEGRANT_DAO.getIntegrantToken(this.integrantLogger.getEmailUV(), this.integrantLogger.getPassword());
        boolean isVerify = false;
        if(integrantLogger.getFullName() != null){
            isVerify = true;
        }
        return isVerify;
    }
    
    private boolean checkUserLoginWithBodyKey(){
        integrantLogger.setEmailUV(this.fieldEmailUv.getText());
        integrantLogger.setPassword(this.fieldPasswordMailUv.getText());
        integrantLogger.setBodyAcademyKey(this.fieldBodyAcademyKey.getText());
        boolean isVerify = false;
        integrantLogger = INTEGRANT_DAO.getIntegrantTocken(integrantLogger);
        if(integrantLogger.getFullName() != null){
            isVerify = true;
        }
        return isVerify;
    }
    
}
