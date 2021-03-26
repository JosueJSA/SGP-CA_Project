/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.demodao;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sgp.ca.businesslogic.GeneralReumeDAO;
import sgp.ca.businesslogic.IntegrantDAO;
import sgp.ca.domain.GeneralResume;
import sgp.ca.domain.Integrant;


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
                
        GeneralReumeDAO general = new GeneralReumeDAO();
        GeneralResume anotherGeneral = general.getGeneralRsume("UV-CA-127");
        System.out.println(anotherGeneral);
        
        IntegrantDAO integrant = new IntegrantDAO();
        Integrant anotherInterant = integrant.getIntegrant("cortes@uv.mx");
        System.out.println(anotherInterant.getFullName());
        
//        Integrant newIntegrant = new Integrant("DEJO", "María Karen Cortés Verdín", "cortes@uv.mx", "cortes@gmail.com", "FEOIOWJOIJ", "Mexicana", 23, 22829394, "2021-12-12","PTC", "Responsable", 22321, 2234);
//        integrant.addIntegrant(newIntegrant, "UV-CA-127");
    }
    
}
