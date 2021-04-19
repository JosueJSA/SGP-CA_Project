/**
* @author Josué Alarcón  
* Last modification date format: 
*/

package sgp.ca.businesslogic;

import java.sql.Connection;
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
                "SELECT * FROM Integrant WHERE emailUV = ?;"
            );
            sentenceQuery.setString(1, emailUV);
            ResultSet queryResult = sentenceQuery.executeQuery();
            if(queryResult.next()){
                integrant = new Integrant(
                    queryResult.getString("rfc"), 
                    queryResult.getString("fullName"),
                    queryResult.getString("emailUV"),
                    queryResult.getString("password"),
                    queryResult.getString("curp"),
                    queryResult.getString("nacionality"),
                    queryResult.getDate("dateOfAdmission").toString(),
                    queryResult.getString("educationalProgram"),
                    queryResult.getInt("satffNumber"),
                    queryResult.getString("cellPhone"),
                    queryResult.getString("bodyAcademyKey"),
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
    public void addIntegrant(Integrant newIntegrant){
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "INSERT INTO Integrant VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
            );
            sentenceQuery.setString(1, newIntegrant.getRfc());
            sentenceQuery.setString(2, newIntegrant.getBodyAcademyKey());
            sentenceQuery.setString(3, newIntegrant.getFullName());
            sentenceQuery.setString(4, newIntegrant.getDateOfAdmission());
            sentenceQuery.setString(5, newIntegrant.getEmailUV());
            sentenceQuery.setString(6, newIntegrant.getPassword());
            sentenceQuery.setString(7, newIntegrant.getAditionalMail());
            sentenceQuery.setString(8, newIntegrant.getCurp());
            sentenceQuery.setString(9, newIntegrant.getNationality());
            sentenceQuery.setString(10, newIntegrant.getEducationalProgram());
            sentenceQuery.setString(11, newIntegrant.getCellphone());
            sentenceQuery.setInt(12, newIntegrant.getStaffNumber());
            sentenceQuery.setString(13, newIntegrant.getHomePhone());
            sentenceQuery.setString(14, newIntegrant.getWorkPhone());
            sentenceQuery.setString(15, newIntegrant.getAppointmentMember());
            sentenceQuery.setString(16, newIntegrant.getParticipationType());
            sentenceQuery.executeUpdate();
            this.addIntegrantStudies(connection, newIntegrant);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(Integrant.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(IntegrantDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }
    }

    @Override
    public void updateIntegrant(Integrant integrant, String oldRFC){
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            this.deleteIntegrantStudies(connection, oldRFC);
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "UPDATE Integrant SET rfc = ?, bodyAcademyKey = ?, fullName = ?, dateOfAdmission = ?, emailUV = ?, password = ?, "
                + "additionalEmail = ?, curp = ?, nacionality = ?, educationalProgram = ?, cellPhone = ?, satffNumber = ?,"
                + " homePhone = ?, workPhone = ?, appointment = ?, participationType = ? WHERE rfc = ?;"
            );
            sentenceQuery.setString(1, integrant.getRfc());
            sentenceQuery.setString(2, integrant.getBodyAcademyKey());
            sentenceQuery.setString(3, integrant.getFullName());
            sentenceQuery.setString(4, integrant.getDateOfAdmission());
            sentenceQuery.setString(5, integrant.getEmailUV());
            sentenceQuery.setString(6, integrant.getPassword());
            sentenceQuery.setString(7, integrant.getAditionalMail());
            sentenceQuery.setString(8, integrant.getCurp());
            sentenceQuery.setString(9, integrant.getNationality());
            sentenceQuery.setString(10, integrant.getEducationalProgram());
            sentenceQuery.setString(11, integrant.getCellphone());
            sentenceQuery.setInt(12, integrant.getStaffNumber());
            sentenceQuery.setString(13, integrant.getHomePhone());
            sentenceQuery.setString(14, integrant.getWorkPhone());
            sentenceQuery.setString(15, integrant.getAppointmentMember());
            sentenceQuery.setString(16, integrant.getParticipationType());
            sentenceQuery.setString(17, oldRFC);
            sentenceQuery.executeUpdate();
            this.addIntegrantStudies(connection, integrant);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(Integrant.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(IntegrantDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }
    }
    
    public List<Schooling> getIntegrantStudies(String integrantRFC){
        List<Schooling> schooling = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM Schooling WHERE rfc = ?;"
            );
            sentenceQuery.setString(1, integrantRFC);
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
    public void addIntegrantStudies(Connection connection, Integrant integrant){
        integrant.getSchooling().forEach( schooling -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
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
                try{
                    connection.rollback();
                    Logger.getLogger(Integrant.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(IntegrantDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    @Override
    public void deleteIntegrantStudies(Connection connection, String rfc){
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM Schooling WHERE rfc = ?;"
            );
            sentenceQuery.setString(1, rfc);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(Integrant.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex) {
                Logger.getLogger(IntegrantDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
