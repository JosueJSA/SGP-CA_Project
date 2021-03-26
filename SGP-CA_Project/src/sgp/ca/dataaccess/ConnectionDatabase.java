/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionDatabase {
    
    private final String URL = "jdbc:mysql://185.31.40.56:3306/josuesa_sgp-ca";
    private final String USER = "josuesa_admin";
    private final String PASSWORD = "sgp-caEJJ21teamA_full4k1080p";
    private Connection connectionDatabase;
    
    public Connection getConnectionDatabase(){
        establishConnection();
        return connectionDatabase;
    }
    
    public void establishConnection(){
        try{
            connectionDatabase = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch(SQLException sqlException){
            Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, sqlException);
        }
    }
    
    public void closeConnection(){
        if(connectionDatabase != null){
            try{
                if(!connectionDatabase.isClosed()){
                    connectionDatabase.close();
                }
            }catch(SQLException sqlException){
                Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, sqlException);
            }
        }
    }    

}
