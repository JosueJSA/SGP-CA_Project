/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.GeneralResume;
import sgp.ca.domain.Member;

public class GeneralReumeDAO implements IGeneralResumeDAO{

    private final ConnectionDatabase connectionQuery = new ConnectionDatabase();
    
    @Override
    public GeneralResume getGeneralRsume(String bodyAcademyKey) {
        GeneralResume generalResume = new GeneralResume();
        try{
            PreparedStatement sentenceQuery = connectionQuery.getConnectionDatabase().prepareStatement(
                "select * from GeneralResume where bodyAcademyKey = ?;"
            );
            sentenceQuery.setString(1, bodyAcademyKey);
            ResultSet queryResult = sentenceQuery.executeQuery();
            queryResult.next();
            generalResume = new GeneralResume(
                queryResult.getString("bodyAcademyKey"), 
                queryResult.getString("nameBA"),
                queryResult.getString("areaAscription"),
                queryResult.getString("ascriptionUnit"),
                queryResult.getString("consolidationDegree"),
                queryResult.getString("vision"),
                queryResult.getString("mission"),
                queryResult.getString("educationalProgram"),
                queryResult.getString("generalTarjet"),
                queryResult.getDate("registrationDate")
            );
        }catch(SQLException sqlException){
            Logger.getLogger(GeneralResume.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionQuery.closeConnection();
            return generalResume;
        }
    }

    @Override
    public List<Member> searchMembersByName(String memberName) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
}
