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
import sgp.ca.businesslogic.WorkPlanDAO;

public class SGPCA_Project extends Application {
    static WorkPlanDAO WORKPLAN_DAO = new WorkPlanDAO();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
        System.out.println();
        
    
    }
}
