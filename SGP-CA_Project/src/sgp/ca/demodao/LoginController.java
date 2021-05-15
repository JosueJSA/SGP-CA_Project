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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sgp.ca.businesslogic.GeneralResumeDAO;

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
    
    private final GeneralResumeDAO GENERAL_RESUME = new GeneralResumeDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Todo
    }    

    @FXML
    private void signIn(ActionEvent event) {
    }
    
    @FXML
    private void signUpNewBdyAcademy(MouseEvent event) {
        this.changeWindow("NewGeneralResume.fxml", event);
    }

    @FXML
    private void signUpNewUser(MouseEvent event) {
        this.changeWindow("Member.fxml", event);
    }
    
    public void changeWindow(String window, MouseEvent event){
        Stage stage = new Stage();
        Parent root;
        try{
            root = FXMLLoader.load(getClass().getResource(window));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            Node source = (Node) event.getSource();
            Stage currentStage = (Stage) source.getScene().getWindow();
            currentStage.close();
        }catch(IOException ex){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
