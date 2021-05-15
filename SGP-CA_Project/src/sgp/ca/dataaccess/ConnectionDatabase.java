/**
* @author 
* Last modification date format: 
*/

package sgp.ca.dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionDatabase {
    
    private final String URL = "jdbc:mysql://localhost:59824/josuesa_sgp_ca";
    private final String USER = "root";
    private final String PASSWORD = "";
    private Connection connectionDatabase;
    
    public Connection getConnectionDatabase(){
        establishConnection();
        return connectionDatabase;
    }
    
    public Connection getConnectionDatabaseNotAutoCommit(){
        establishConnection();
        try {
            connectionDatabase.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return connectionDatabase;
        }
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
