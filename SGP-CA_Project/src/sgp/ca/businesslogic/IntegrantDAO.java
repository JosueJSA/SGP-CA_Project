/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Schooling;

 public class IntegrantDAO implements IIntegrantDAO{

    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();
    
    @Override
    public Integrant getIntegrantByUVmail(String emailUV){
        Integrant integrant = new Integrant();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "select * from Integrant where emailUV = ?;"
            );
            sentenceQuery.setString(1, emailUV);
            ResultSet queryResult = sentenceQuery.executeQuery();
            if(queryResult.next()){
                integrant = new Integrant(
                    queryResult.getString("rfc"), 
                    queryResult.getString("fullName"),
                    queryResult.getString("emailUV"),
                    queryResult.getString("curp"),
                    queryResult.getString("nacionality"),
                    queryResult.getDate("dateOfAdmission").toString(),
                    queryResult.getString("educationalProgram"),
                    queryResult.getInt("satffNumber"),
                    queryResult.getString("cellPhone"),
                    queryResult.getString("appointment"),
                    queryResult.getString("participationType"),
                    queryResult.getString("additionalEmail"),
                    queryResult.getString("homePhone"),
                    queryResult.getString("workPhone")
                );
            }
            integrant.setSchooling(this.getIntegrantStudies(integrant.getRfc()));
        }catch(SQLException sqlException){
            Logger.getLogger(Integrant.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
            return integrant;
        }
    }

    @Override
    public void addIntegrant(Integrant newIntegrant, String bodyAcademyKey){
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "INSERT INTO Integrant VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
            );
            sentenceQuery.setString(1, newIntegrant.getRfc());
            sentenceQuery.setString(2, bodyAcademyKey);
            sentenceQuery.setString(3, newIntegrant.getFullName());
            sentenceQuery.setString(4, newIntegrant.getDateOfAdmission());
            sentenceQuery.setString(5, newIntegrant.getEmailUV());
            sentenceQuery.setString(6, newIntegrant.getAditionalMail());
            sentenceQuery.setString(7, newIntegrant.getCurp());
            sentenceQuery.setString(8, newIntegrant.getNationality());
            sentenceQuery.setString(9, newIntegrant.getEducationalProgram());
            sentenceQuery.setString(10, newIntegrant.getCellphone());
            sentenceQuery.setInt(11, newIntegrant.getStaffNumber());
            sentenceQuery.setString(12, newIntegrant.getHomePhone());
            sentenceQuery.setString(13, newIntegrant.getWorkPhone());
            sentenceQuery.setString(14, newIntegrant.getAppointmentMember());
            sentenceQuery.setString(15, newIntegrant.getParticipationType());
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Integrant.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
        }
    }

    @Override
    public List<Schooling> getIntegrantStudies(String integrantRfc) {
        List<Schooling> schooling = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "select * from Schooling where rfc = ?;"
            );
            sentenceQuery.setString(1, integrantRfc);
            ResultSet queryResult = sentenceQuery.executeQuery();
            while(queryResult.next()){
                schooling.add(new Schooling(
                    queryResult.getString("studyName"), 
                    queryResult.getString("studiesInsitution"),
                    queryResult.getString("levelOfStudy"),
                    queryResult.getString("studiesState"),
                    queryResult.getString("studyDiscipline"),
                    queryResult.getString("studyArea"),
                    queryResult.getDate("dateOfObtainingStudies").toString(),
                    queryResult.getString("professionalID")
                ));
            }
        }catch(SQLException sqlException){
            Logger.getLogger(Integrant.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
            return schooling;
        }
    }

    @Override
    public void addIntegrantStudies(Integrant integrant) {
        integrant.getSchooling().forEach( schooling -> {
            try{
                PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                    "INSERT INTO Schooling VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);"
                );
                sentenceQuery.setString(1, schooling.getIdProfessionalStudies());
                sentenceQuery.setString(2, integrant.getRfc());
                sentenceQuery.setString(3, schooling.getStudiesInstitution());
                sentenceQuery.setString(4, schooling.getLevelOfStudy());
                sentenceQuery.setString(5, schooling.getDateOfObteiningStudies());
                sentenceQuery.setString(6, schooling.getStudiesSatate());
                sentenceQuery.setString(7, schooling.getStudiesDiscipline());
                sentenceQuery.setString(8, schooling.getStudyArea());
                sentenceQuery.setString(9, schooling.getStudyName());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                Logger.getLogger(Integrant.class.getName()).log(Level.SEVERE, null, sqlException);
            }finally{
                CONNECTION.closeConnection();
            }
        });
            
        
    }
    
}
