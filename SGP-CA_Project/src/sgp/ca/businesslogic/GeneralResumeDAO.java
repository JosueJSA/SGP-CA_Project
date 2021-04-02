/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.GeneralResume;
import sgp.ca.domain.Integrant;

public class GeneralResumeDAO implements IGeneralResumeDAO{

    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();
    
    @Override
    public GeneralResume getGeneralResume(String bodyAcademyKey) {
        GeneralResume generalResume = new GeneralResume();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "select * from GeneralResume where bodyAcademyKey = ?;"
            );
            sentenceQuery.setString(1, bodyAcademyKey);
            ResultSet queryResult = sentenceQuery.executeQuery();
            if(queryResult.next()){
                generalResume = new GeneralResume(
                    queryResult.getString("bodyAcademyKey"), 
                    queryResult.getString("nameBA"),
                    queryResult.getString("areaAscription"),
                    queryResult.getString("ascriptionUnit"),
                    queryResult.getString("consolidationDegree"),
                    queryResult.getString("vision"),
                    queryResult.getString("mission"),
                    queryResult.getString("generalTarjet"),
                    queryResult.getDate("registrationDate").toString(),
                    queryResult.getDate("lastEvaluationDate").toString()
                );
            }
        }catch(SQLException sqlException){
            Logger.getLogger(GeneralResume.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
            return generalResume;
        }
    }

    @Override
    public void addGeneralResume(GeneralResume newGeneralResume){
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "INSERT INTO GeneralResume VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
            );
            sentenceQuery.setString(1, newGeneralResume.getBodyAcademyKey());
            sentenceQuery.setString(2, newGeneralResume.getBodyAcademyName());
            sentenceQuery.setString(3, newGeneralResume.getAscriptionArea());
            sentenceQuery.setString(4, newGeneralResume.getAscriptionUnit());
            sentenceQuery.setString(5, newGeneralResume.getConsolidationDegree());
            sentenceQuery.setString(6, newGeneralResume.getRegistrationDate());
            sentenceQuery.setString(7, newGeneralResume.getLastEvaluation());
            sentenceQuery.setString(8, newGeneralResume.getVision());
            sentenceQuery.setString(9, newGeneralResume.getMission());
            sentenceQuery.setString(10, newGeneralResume.getGeneralTarjet());
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Integrant.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
        }
    }

    
}
