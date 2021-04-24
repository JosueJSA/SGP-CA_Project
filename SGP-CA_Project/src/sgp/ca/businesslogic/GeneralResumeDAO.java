/**
* @author Josué Alarcón  
* Last modification date format: 
*/

package sgp.ca.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.GeneralResume;
import sgp.ca.domain.Integrant;

public class GeneralResumeDAO implements IGeneralResumeDAO{

    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();
    
    @Override
    public GeneralResume getGeneralResumeByKey(String bodyAcademyKey) {
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

    @Override
    public void updateGeneralResume(GeneralResume generalResume, String oldBodyAcademyKey){
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "UPDATE GeneralResume SET bodyAcademyKey = ?, nameBA = ?, areaAscription = ?, ascriptionUnit = ?,"
                + " consolidationDegree = ?, registrationDate = ?, lastEvaluationDate = ?, vision = ?, mission = ?, "
                + "generalTarjet = ? WHERE bodyAcademyKey = ?;"
            );
            sentenceQuery.setString(1, generalResume.getBodyAcademyKey());
            sentenceQuery.setString(2, generalResume.getBodyAcademyName());
            sentenceQuery.setString(3, generalResume.getAscriptionArea());
            sentenceQuery.setString(4, generalResume.getAscriptionUnit());
            sentenceQuery.setString(5, generalResume.getConsolidationDegree());
            sentenceQuery.setString(6, generalResume.getRegistrationDate());
            sentenceQuery.setString(7, generalResume.getLastEvaluation());
            sentenceQuery.setString(8, generalResume.getVision());
            sentenceQuery.setString(9, generalResume.getMission());
            sentenceQuery.setString(10, generalResume.getGeneralTarjet());
            sentenceQuery.setString(11, oldBodyAcademyKey);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Integrant.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
        }
    }

    
}
