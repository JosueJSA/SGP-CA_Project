/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Josue Alarcon
 */
public class ConnectionDatabase {
    
    private final String url = "jdbc:mysql://185.31.40.56:3306/josuesa_sgp-ca";
    private Connection connectionDatabase;
    
    public Connection getConnectionDatabase(){
        establishConnection();
        return connectionDatabase;
    }
    
    public void establishConnection(){
        try{
            connectionDatabase = DriverManager.getConnection(url, "josuesa_admin", "sgp-caEJJ21teamA_full4k1080p");
        }catch(SQLException sqlException){
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, sqlException);
        }
    }
    
    public void closeConnection(){
        if(connectionDatabase != null){
            try{
                if(!connectionDatabase.isClosed()){
                    connectionDatabase.close();
                }
            }catch(SQLException sqlException){
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, sqlException);
            }
        }
    }    

}
