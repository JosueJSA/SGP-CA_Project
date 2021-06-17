/*
 * @author Josué 
 * @versión v1.0
 * Last modification date: 17-06-2021
 */

package sgp.ca.demodao;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.Integrant;


public class LoginController implements Initializable{

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
    public void initialize(URL url, ResourceBundle rb){
        integrantLogger = new Integrant();
    }    

    @FXML
    private void signIn(ActionEvent event){
        if(checkUserLoginWithBodyKey()){
            FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("Start.fxml", btnSignIn);
            StartController controller = loader.getController();
            controller.receiveIntegrantToken(integrantLogger);
        }else{
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, "Hay campos inválidos en el formulario");
        }
    }
    
    @FXML
    private void signUpNewBdyAcademy(MouseEvent event){
        if(checkUserLogin() && this.integrantLogger.getBodyAcademyKey()== null){
            System.out.println(btnSignUpNewUser.getScene().getWindow());
            FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("GeneralResumeEditable.fxml", btnSignIn);
            GeneralResumeEditableController controller = loader.getController();
            controller.showGeneralResumeInsertForm(integrantLogger);
        }else{
            GenericWindowDriver.getGenericWindowDriver().showErrorAlert(event, "El email y contraseña deben estar correctos para");
        }
    }

    @FXML
    private void signUpNewUser(MouseEvent event){
        FXMLLoader loader = GenericWindowDriver.getGenericWindowDriver().changeWindow("IntegrantEditable.fxml", btnSignIn);
        IntegrantEditableController controller = loader.getController();
        controller.showResponsibleInscriptionForm();
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
        if(integrantLogger.getFullName() != null && integrantLogger.getParticipationStatus().equalsIgnoreCase("Activo")){
            isVerify = true;
        }
        return isVerify;
    }
    
}
