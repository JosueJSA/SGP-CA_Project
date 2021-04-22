/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sgp.ca.dataaccess.FtpClient;
import javafx.stage.Stage;

/**
 *
 * @author Josue Alarcon
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    private DialogBox testBox;
    @FXML
    private Button button;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        testBox = new DialogBox( ((Stage)((Node)event.getSource()).getScene().getWindow()) );
        FtpClient connection = new FtpClient();
        String file = connection.saveFileIntoFilesSystem(testBox.getFileSelectedPath(), testBox.getFileNameSelected());
        System.out.println("Terminé de guardar");
        connection.downloadFileFromFilesSystemByName(testBox.getFileNameSelected(), testBox.getDirectorySelectedPath());
        System.out.println("Terminé recuperar");
        connection.deleteFileFromFilesSystemByName(file);
        System.out.println("Terminé de eliminar");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
