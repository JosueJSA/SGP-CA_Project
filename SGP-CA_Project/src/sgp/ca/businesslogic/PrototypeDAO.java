/**
 * @author estef
 * Last modification date format: 19-04-2021
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Prototype;

public class PrototypeDAO implements IPrototypeDAO {
    private final ConnectionDatabase connectionDataBase = new ConnectionDatabase();
    private Connection connection;
    private Statement consult;
    private ResultSet results;

    @Override
    public List<Prototype> returnPrototype() {
        List<Prototype>prototypeList = new ArrayList<>();
        try{
            connection = connectionDataBase.getConnectionDatabase();
            consult = connection.createStatement();
            results = consult.executeQuery("Select * from Prototype");
            while(results.next()){
                Prototype prototype = new Prototype();
                prototype.setUrlFile(results.getString("urlFile"));
                prototype.setProjectName(results.getNString("projectName"));
                prototype.setImpactAB(results.getNString("impactBA"));
                prototype.setEvidenceTitle(results.getNString("evidenceTitle"));
                prototype.setPublicationDate(results.getString("publicationDate"));
                prototype.setCountry(results.getNString("country"));
                prototype.setFeatures(results.getNString("feautures"));
                prototypeList.add(prototype);
            }
        
        }catch(SQLException ex){
            Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            connectionDataBase.closeConnection();
        }
        return prototypeList;
    }

    @Override
    public Prototype getPrototypebyURL(String urlFile) {
        Prototype prototype = null;
        try(Connection connection = connectionDataBase.getConnectionDatabase()){
            String query = "Select * from Prototype where urlFile = ? ";
            PreparedStatement sentence = connection.prepareStatement(query);
            sentence.setString(1, urlFile);
            results = sentence.executeQuery();
            
            while(results.next()){
                prototype = new Prototype();
                prototype.setUrlFile(results.getString("urlFile"));
                prototype.setProjectName(results.getNString("projectName"));
                prototype.setImpactAB(results.getNString("impactBA"));
                prototype.setEvidenceTitle(results.getNString("evidenceTitle"));
                prototype.setPublicationDate(results.getString("publicationDate"));
                prototype.setCountry(results.getNString("county"));
                prototype.setFeatures(results.getNString("feautures"));
            }
                
        }catch(SQLException ex){
            Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prototype;
    }

    @Override
    public void addPrototype(Prototype newPrototype) {
        try{
            PreparedStatement sentence = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "INSERT INTO Prototype VALUES (?,?,?,?,?,?,?);"
            );
            sentence.setString(1, newPrototype.getUrlFile());
            sentence.setString(2, newPrototype.getProjectName());
            sentence.setString(3, newPrototype.getImpactAB());
            sentence.setString(4, newPrototype.getEvidenceTitle());
            sentence.setString(5, newPrototype.getPublicationDate());
            sentence.setString(6, newPrototype.getCountry());
            sentence.setString(7, newPrototype.getFeatures());
            sentence.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Prototype.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
    }

    @Override
    public void updatePrototype(Prototype prototype, String oldUrlFile) {
        try{
            PreparedStatement sentence = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "UPDATE Prototype SET urlFile = ?, projectName = ?, impactBA = ?, evidenceTitle = ?," 
                    + "publicationDate = ?, country = ?, feautures = ?"
                    + "WHERE urlFile = ?;"
            );
            sentence.setString(1, prototype.getUrlFile());
            sentence.setString(2, prototype.getProjectName());
            sentence.setString(3, prototype.getImpactAB());
            sentence.setString(4, prototype.getEvidenceTitle());
            sentence.setString(5, prototype.getPublicationDate());
            sentence.setString(6, prototype.getCountry());
            sentence.setString(7, prototype.getFeatures());
            sentence.setString(8, oldUrlFile);
            sentence.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Prototype.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
    }

    @Override
    public boolean deletePrototypebyURL(String urlFile) {
        boolean deletedPrototype = false;
        try{
            PreparedStatement sentenceQuery = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "DELETE FROM Prototype "
                    + "WHERE urlFile = ? "
                    + "LIMIT 1;"
            );
            sentenceQuery.setString(1, urlFile);
            sentenceQuery.executeUpdate();
            deletedPrototype = true;
        }catch(SQLException sqlException){
            Logger.getLogger(Prototype.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
        return deletedPrototype;
    }
    
    
}
