/**
* @author Johann 
* Last modification date format: 25-03-2021
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
import sgp.ca.domain.Evidence;
import sgp.ca.domain.ReceptionWork;

public class ReceptionWorkDAO extends EvidenceDAO{
    
    private final ConnectionDatabase query = new ConnectionDatabase();
    private final IntegrantDAO INTEGRANT_DAO = new IntegrantDAO();
    
    public void addReceptionWork(ReceptionWork newReceptionWork){
        Connection connection = query.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentenceQuery = query.getConnectionDatabase().prepareStatement(
                "insert into ReceptionWork values(? , ? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
            );
            sentenceQuery.setString(1, newReceptionWork.getUrlFile());
            sentenceQuery.setString(2, newReceptionWork.getProjectName());
            sentenceQuery.setBoolean(3, newReceptionWork.getImpactAB());
            sentenceQuery.setString(4, newReceptionWork.getEvidenceType());
            sentenceQuery.setString(5, newReceptionWork.getEvidenceTitle());
            sentenceQuery.setString(6, newReceptionWork.getRegistrationResponsible());
            sentenceQuery.setString(7, newReceptionWork.getRegistrationDate());
            sentenceQuery.setString(8, newReceptionWork.getStudyDegree());
            sentenceQuery.setString(9, newReceptionWork.getPublicationDate());
            sentenceQuery.setString(10, newReceptionWork.getCountry());
            sentenceQuery.setString(11, newReceptionWork.getDescription());
            sentenceQuery.setString(12, newReceptionWork.getStatus());
            sentenceQuery.setInt(13, newReceptionWork.getActualDurationInMonths());
            sentenceQuery.setInt(14, newReceptionWork.getEstimatedDurationInMonths());
            sentenceQuery.setString(15, newReceptionWork.getModality());
            sentenceQuery.executeUpdate();
            this.insertIntoIntegrantReceptionWork(connection, newReceptionWork);
            this.insertIntoCollaboratorReceptionWork(connection, newReceptionWork);
            this.insertIntoStudentReceptionWork(connection, newReceptionWork);
            this.insertIntoRequirementReceptionWork(connection, newReceptionWork);
            this.insertIntoLgacReceptionWork(connection, newReceptionWork);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlEsception){
            try{
                connection.rollback();
                Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlEsception);
            }catch(SQLException ex){
                Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            query.closeConnection();
        }
    }
    
    
    
    public List<ReceptionWork> getReceptionWorkList(){
        List<ReceptionWork> listReceptionWork = new ArrayList<>();
        try{
            Statement instructionQuery = query.getConnectionDatabase().createStatement();;
            ResultSet queryResult = instructionQuery.executeQuery("Select urlFile, projectName, impactBA, , evidenceType, "
                    + "evidenceTitle, registrationResponsible, registrationDate, studyDegree, publicationDate, country,  "
                    + "description, status, actualDurationInMonths, estimatedDurationInMonths, modality FROM ReceptionWork");
            while(queryResult.next()){
                listReceptionWork.add(new ReceptionWork(
                    queryResult.getString("urlFile"), 
                    queryResult.getString("projectName"),
                    queryResult.getBoolean("impactBA"),
                    queryResult.getString("evidenceType"),
                    queryResult.getString("evidenceTitle"),
                    queryResult.getString("registrationResponsible"),
                    queryResult.getString("registrationDate"),
                    queryResult.getString("studyDegree"),
                    queryResult.getString("publicationDate"),
                    queryResult.getString("country"),
                    queryResult.getString("description"),
                    queryResult.getString("status"),
                    queryResult.getInt("actualDurationInMonths"),
                    queryResult.getInt("estimatedDurationInMonths"),
                    queryResult.getString("modality")));
            }
        }catch(SQLException ex){
            Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            query.closeConnection();
            return listReceptionWork;
        }
    }

    public ReceptionWork getReceptionWork(String urlFile){
        ReceptionWork receptionWork = new ReceptionWork();
        try{
            PreparedStatement sentenceQuery = query.getConnectionDatabase().prepareStatement(
                "SELECT * FROM ReceptionWork WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFile);
            ResultSet queryResult = sentenceQuery.executeQuery();
            if(queryResult.next()){receptionWork = new ReceptionWork(
                queryResult.getString("urlFile"), 
                queryResult.getString("projectName"),
                queryResult.getBoolean("impactBA"),
                queryResult.getString("evidenceType"),
                queryResult.getString("evidenceTitle"),
                queryResult.getString("registrationResponsible"),
                queryResult.getString("registrationDate"),
                queryResult.getString("studyDegree"),
                queryResult.getString("publicationDate"),
                queryResult.getString("country"),
                queryResult.getString("description"),
                queryResult.getString("status"),
                queryResult.getInt("actualDurationInMonths"),
                queryResult.getInt("estimatedDurationInMonths"),
                queryResult.getString("modality")
            );}
        }catch(SQLException sqlException){
            Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            query.closeConnection();
            return receptionWork;
        }
    }
    

    public void updateReceptionWork(ReceptionWork receptionWork, String oldUrlFile){

        Connection connection = query.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentenceQuery = query.getConnectionDatabase().prepareStatement(
                "UPDATE ReceptionWork SET urlFile = ?, projectName = ?, impactBA = ?, , evidenceType = ?, "
                    + "evidenceTitle = ?, registrationResponsible = ?, registrationDate = ?, studyDegree = ?, publicationDate = ?, country = ?,"
                    + "description = ?, status = ?, actualDurationInMonths = ?, estimatedDurationInMonths = ?, modality = ? WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, receptionWork.getUrlFile());
            sentenceQuery.setString(2, receptionWork.getProjectName());
            sentenceQuery.setBoolean(3, receptionWork.getImpactAB());
            sentenceQuery.setString(4, receptionWork.getEvidenceType());
            sentenceQuery.setString(5, receptionWork.getEvidenceTitle());
            sentenceQuery.setString(6, receptionWork.getRegistrationResponsible());
            sentenceQuery.setString(7, receptionWork.getRegistrationDate());
            sentenceQuery.setString(8, receptionWork.getStudyDegree());
            sentenceQuery.setString(9, receptionWork.getPublicationDate());
            sentenceQuery.setString(10, receptionWork.getCountry());
            sentenceQuery.setString(11, receptionWork.getDescription());
            sentenceQuery.setString(12, receptionWork.getStatus());
            sentenceQuery.setInt(13, receptionWork.getActualDurationInMonths());
            sentenceQuery.setInt(14, receptionWork.getEstimatedDurationInMonths());
            sentenceQuery.setString(15, receptionWork.getModality());
            sentenceQuery.setString(16, oldUrlFile);
            sentenceQuery.executeUpdate();
            this.insertIntoIntegrantReceptionWork(connection, receptionWork);
            this.insertIntoCollaboratorReceptionWork(connection, receptionWork);
            this.insertIntoStudentReceptionWork(connection, receptionWork);
            this.insertIntoRequirementReceptionWork(connection, receptionWork);
            this.insertIntoLgacReceptionWork(connection, receptionWork);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            query.closeConnection();
        }
    }
    

    public void deleteReceptionWork(String urlFileReceptionWork){
        Connection connection = query.getConnectionDatabaseNotAutoCommit();
        try{
            this.deleteIntegrantsFromURLFileReceptionWork(connection, urlFileReceptionWork);
            this.deleteCollaboratorsFromURLFileReceptionWork(connection, urlFileReceptionWork);
            this.deleteStudensFromURLFileReceptionWork(connection, urlFileReceptionWork);
            this.deleteRequirementsFromURLFileReceptionWork(connection, urlFileReceptionWork);
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM ReceptionWork WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFileReceptionWork);
            sentenceQuery.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            query.closeConnection();
        }
    }
    
    public void insertIntoIntegrantReceptionWork(Connection connection, ReceptionWork receptionWork){
        receptionWork.getIntegrants().forEach( integrant -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO IntegrantReceptionWork (rfc, urlFile) VALUES (?, ?);"
                );
                sentenceQuery.setString(1, integrant.getRfc());
                sentenceQuery.setString(2, receptionWork.getUrlFile());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void insertIntoCollaboratorReceptionWork(Connection connection, ReceptionWork receptionWork){
        receptionWork.getCollaborators().forEach( collaborator -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO CollaborateReceptionWork (rfc, urlFile) VALUES (?, ?);"
                );
                sentenceQuery.setString(1, collaborator.getRfc());
                sentenceQuery.setString(2, receptionWork.getUrlFile());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void insertIntoStudentReceptionWork(Connection connection, ReceptionWork receptionWork){
        receptionWork.getStudents().forEach( student -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO ReceptionWorkStudent (urlFile, student) VALUES (?, ?);"
                );
                sentenceQuery.setString(1, receptionWork.getUrlFile());
                sentenceQuery.setString(2, student);
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void insertIntoRequirementReceptionWork(Connection connection, ReceptionWork receptionWork){
        receptionWork.getRequirements().forEach( requirement -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO ReceptionWorkRequirement (urlFile, requirement) VALUES (?, ?);"
                );
                sentenceQuery.setString(1, receptionWork.getUrlFile());
                sentenceQuery.setString(2, requirement);
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void insertIntoLgacReceptionWork(Connection connection, ReceptionWork receptionWork){
        receptionWork.getLgacs().forEach( lgac -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO LGAK-ReceptionWork (identifierLGAC, urlFile) VALUES (?, ?);"
                );
                sentenceQuery.setString(1, lgac.getIdentifierLgac() );
                sentenceQuery.setString(2, receptionWork.getUrlFile());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void deleteIntegrantsFromURLFileReceptionWork(Connection connection, String urlFile){
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM IntegrantReceptionWork WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFile);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteCollaboratorsFromURLFileReceptionWork(Connection connection, String urlFile){
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM CollaborateReceptionWork WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFile);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void deleteStudensFromURLFileReceptionWork(Connection connection, String urlFile){
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM ReceptionWorkStudent WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFile);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteRequirementsFromURLFileReceptionWork(Connection connection, String urlFile){
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM ReceptionWorkRequirement WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFile);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteLgacFromURLFileReceptionWork(Connection connection, String urlFile){
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM LGAK-ReceptionWork WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFile);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(ReceptionWorkDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public Evidence getEvidenceByUrl(String urlEvidenceFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addNewEvidence(Evidence evidence) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateEvidence(Evidence evidence, String oldUrlFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteEvidenceByUrl(String urlEvidenceFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
