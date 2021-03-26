/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sgp.ca.dataaccess.ConnectionDatabase;


/**
 *
 * @author Josue Alarcon
 */
public class SGPCA_Project extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        ConnectionDatabase con = new ConnectionDatabase();        

        try{
            System.out.println("OIJOIJ");
            Statement instructionQuery = con.getConnectionDatabase().createStatement();
            ResultSet queryResult = instructionQuery.executeQuery("Select * from GeneralResume;");
            System.out.println("OIJOIJ");
            while(queryResult.next()){
                System.out.println(queryResult.getString("bodyAcademyKey"));
                System.out.println(queryResult.getString("nameBA"));
                System.out.println(queryResult.getString("areaAscription"));
                System.out.println(queryResult.getString("ascriptionUnit"));
                System.out.println(queryResult.getString("consolidationDegree"));
                System.out.println(queryResult.getString("registrationDate"));
                System.out.println(queryResult.getString("vision"));
                System.out.println(queryResult.getString("mission"));
                System.out.println(queryResult.getString("educationalProgram"));
                System.out.println(queryResult.getString("generalTarjet"));                
            }
        }catch(SQLException sqlException){

        }finally{

        }
        
    }
    
}
