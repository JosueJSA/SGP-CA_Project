/**
* @author 
* Last modification date format: 
*/

package sgp.ca.demodao;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sgp.ca.businessLogic.ReceptionWorkDAO;
import sgp.ca.dataaccess.FtpClient;
import sgp.ca.domain.ReceptionWork;

public class SGPCA_Project extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);        
        DialogBox testBox = new DialogBox();
        
        FtpClient connection = new FtpClient();
        String file = connection.saveFileIntoFilesSystem(testBox.getFileSelectedPath(), testBox.getFileNameSelected());
        System.out.println("Terminé de guardar");
        connection.downloadFileFromFilesSystemByName(testBox.getFileNameSelected(), testBox.getDirectorySelectedPath());
        System.out.println("Terminé recuperar");
        connection.deleteFileFromFilesSystemByName(file);
        System.out.println("Terminé de eliminar");
    }
    
}
