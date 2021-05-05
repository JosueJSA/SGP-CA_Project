/**
 * @author Josue
 * Last modification date format: 23-04-2021
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
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Prototype;

public class PrototypeDAO implements IPrototypeDAO {
    
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();
    
    @Override
    public List<Prototype> getPrototypesByIntegrantRFC(String rfc, String limitDate) {
        List<Prototype> prototypes = new ArrayList<>();
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT * FROM Prototype a, IntegrantPrototype i WHERE  a.urlFile = i.urlFile AND i.rfc = ? "
                + "AND cast(registrationDate as date) < cast( ? as date) order by registrationDate DESC LIMIT 10;"
            );
            sentenceQuery.setString(1, rfc);
            sentenceQuery.setString(2, limitDate);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Prototype prototype = this.getOutPrototypeDataFromQuery(resultQuery);
                prototype.setIntegrants(this.getIntegrantsPrototypeParticipation(connection, prototype.getUrlFile()));
                prototype.setCollaborators(this.getCollaboratorsPrototypeParticipation(connection, prototype.getUrlFile()));
                prototype.setStudents(this.getStudentNamesPrototypeParticipation(connection, prototype.getUrlFile()));
                prototypes.add(prototype);
            }
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return prototypes;
        }
    }

    @Override
    public List<Prototype> getPrototypesByTitle(String title, String limitDate) {
        List<Prototype> prototypes = new ArrayList<>();
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT * FROM Prototype WHERE evidenceTitle = ? "
                + "AND cast(registrationDate as date) < cast( ? as date) order by registrationDate DESC LIMIT 10;"
            );
            sentenceQuery.setString(1, title);
            sentenceQuery.setString(2, limitDate);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Prototype prototype = this.getOutPrototypeDataFromQuery(resultQuery);
                prototype.setIntegrants(this.getIntegrantsPrototypeParticipation(connection, prototype.getUrlFile()));
                prototype.setCollaborators(this.getCollaboratorsPrototypeParticipation(connection, prototype.getUrlFile()));
                prototype.setStudents(this.getStudentNamesPrototypeParticipation(connection, prototype.getUrlFile()));
                prototypes.add(prototype);
            }
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return prototypes;
        }
    }

    @Override
    public List<Prototype> getPrototypesByStudent(String studentName, String limitDate) {
        List<Prototype> prototypes = new ArrayList<>();
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT * FROM Prototype a, PrototypeStudent ast WHERE  a.urlFile = ast.urlFile AND ast.student = ? "
                + "AND cast(registrationDate as date) < cast( ? as date) order by registrationDate DESC LIMIT 10;"
            );
            sentenceQuery.setString(1, studentName);
            sentenceQuery.setString(2, limitDate);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Prototype prototype = this.getOutPrototypeDataFromQuery(resultQuery);
                prototype.setIntegrants(this.getIntegrantsPrototypeParticipation(connection, prototype.getUrlFile()));
                prototype.setCollaborators(this.getCollaboratorsPrototypeParticipation(connection, prototype.getUrlFile()));
                prototype.setStudents(this.getStudentNamesPrototypeParticipation(connection, prototype.getUrlFile()));
                prototypes.add(prototype);
            }
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return prototypes;
        }
    }

    @Override
    public void addPrototype(Prototype newPrototype){
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentence = connection.prepareStatement(
                "INSERT INTO Prototype VALUES (?,?,?,?,?,?,?,?,?,?,?);"
            );
            sentence.setString(1, newPrototype.getUrlFile());
            sentence.setString(2, newPrototype.getProjectName());
            sentence.setBoolean(3, newPrototype.getImpactAB());
            sentence.setString(4, newPrototype.getBodyAcademyKey());
            sentence.setString(5, newPrototype.getEvidenceTitle());
            sentence.setString(6, newPrototype.getRegistrationResponsible());
            sentence.setString(7, newPrototype.getRegistrationDate());
            sentence.setString(8, newPrototype.getStudyDegree());
            sentence.setString(9, newPrototype.getPublicationDate());
            sentence.setString(10, newPrototype.getCountry());
            sentence.setString(11, newPrototype.getFeatures());
            sentence.executeUpdate();
            this.insertIntoStudentPrototype(connection, newPrototype);
            this.insertIntoIntegrantPrototype(connection, newPrototype);
            this.insertIntoCollaboratorPrototype(connection, newPrototype);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }
    }

    @Override
    public void updatePrototype(Prototype prototype, String oldUrlFile){
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            this.deleteStudensFromURLFilePrototype(connection, oldUrlFile);
            this.deleteIntegrantsFromURLFilePrototype(connection, oldUrlFile);
            this.deleteCollaboratorsFromURLFilePrototype(connection, oldUrlFile);
            PreparedStatement sentence = connection.prepareStatement(
                "UPDATE Prototype SET urlFile = ?, projectName = ?, impactBA = ?, bodyAcademyKey = ?,"
                + " evidenceTitle = ?, registrationResponsible = ?, registrationDate = ?, "
                + "studyDegree = ?, publicationDate = ?, country = ?, feautures = ?"
                + " WHERE urlFile = ?;"
            );
            sentence.setString(1, prototype.getUrlFile());
            sentence.setString(2, prototype.getProjectName());
            sentence.setBoolean(3, prototype.getImpactAB());
            sentence.setString(4, prototype.getBodyAcademyKey());
            sentence.setString(5, prototype.getEvidenceTitle());
            sentence.setString(6, prototype.getRegistrationResponsible());
            sentence.setString(7, prototype.getRegistrationDate());
            sentence.setString(8, prototype.getStudyDegree());
            sentence.setString(9, prototype.getPublicationDate());
            sentence.setString(10, prototype.getCountry());
            sentence.setString(11, prototype.getFeatures());
            sentence.setString(12, oldUrlFile);
            sentence.executeUpdate();
            this.insertIntoStudentPrototype(connection, prototype);
            this.insertIntoIntegrantPrototype(connection, prototype);
            this.insertIntoCollaboratorPrototype(connection, prototype);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }
    }

    @Override
    public void deletePrototypebyURL(String urlFile){
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            this.deleteStudensFromURLFilePrototype(connection, urlFile);
            this.deleteIntegrantsFromURLFilePrototype(connection, urlFile);
            this.deleteCollaboratorsFromURLFilePrototype(connection, urlFile);
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM Prototype WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFile);
            sentenceQuery.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }
    }
    
    public void deleteStudensFromURLFilePrototype(Connection connection, String urlFilePrototype){
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM PrototypeStudent WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFilePrototype);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteIntegrantsFromURLFilePrototype(Connection connection, String urlFilePrototype){
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM IntegrantPrototype WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFilePrototype);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteCollaboratorsFromURLFilePrototype(Connection connection, String urlFilePrototype){
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM CollaboratePrototype WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFilePrototype);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void insertIntoStudentPrototype(Connection connection, Prototype prototype){
        prototype.getStudents().forEach( student -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO PrototypeStudent VALUES(?,?);"
                );
                sentenceQuery.setString(1, prototype.getUrlFile());
                sentenceQuery.setString(2, student);
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void insertIntoIntegrantPrototype(Connection connection, Prototype prototype){
        prototype.getIntegrants().forEach( integrant -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO IntegrantPrototype VALUES(?,?);"
                );
                sentenceQuery.setString(1, integrant.getRfc());
                sentenceQuery.setString(2, prototype.getUrlFile());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void insertIntoCollaboratorPrototype(Connection connection, Prototype prototype){
        prototype.getCollaborators().forEach( collaborator -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO CollaboratePrototype VALUES(?,?);"
                );
                sentenceQuery.setString(1, collaborator.getRfc());
                sentenceQuery.setString(2, prototype.getUrlFile());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public Prototype getOutPrototypeDataFromQuery(ResultSet resultPrototypeQuery){
        Prototype prototype = new Prototype();
        try{
            prototype = new Prototype(
                resultPrototypeQuery.getString("urlFile"),
                resultPrototypeQuery.getString("projectName"),
                resultPrototypeQuery.getString("evidenceTitle"),
                resultPrototypeQuery.getString("country"),
                resultPrototypeQuery.getString("publicationDate"),
                resultPrototypeQuery.getBoolean("impactBA"),
                resultPrototypeQuery.getString("registrationDate"),
                resultPrototypeQuery.getString("registrationResponsible"),
                resultPrototypeQuery.getString("studyDegree"),
                resultPrototypeQuery.getString("bodyAcademyKey"),
                resultPrototypeQuery.getString("feautures")
            );
        }catch(SQLException ex){
            Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return prototype;
        }
    }
    
    public List<Integrant> getIntegrantsPrototypeParticipation(Connection connection, String urlFilePrototype){
        List<Integrant> integrants = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT i.fullName FROM Integrant i, IntegrantPrototype ia WHERE ia.rfc = i.rfc AND urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFilePrototype);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Integrant integrant = new Integrant();
                integrant.setFullName(resultQuery.getString("fullName"));
                integrants.add(integrant);
            }
        }catch(SQLException ex){
            Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return integrants;
        }
    }
    
    public List<Collaborator> getCollaboratorsPrototypeParticipation(Connection connection, String urlFilePrototype){
        List<Collaborator> collaborators = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT c.fullName FROM CollaboratePrototype ca, Collaborator c WHERE ca.rfc = c.rfc AND urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFilePrototype);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Collaborator collaborator = new Collaborator();
                collaborator.setFullName(resultQuery.getString("fullName"));
                collaborators.add(collaborator);
            }
        }catch(SQLException ex){
            Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return collaborators;
        }
    }
    
    public List<String> getStudentNamesPrototypeParticipation(Connection connection, String urlFilePrototype){
        List<String> students = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT * FROM PrototypeStudent WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFilePrototype);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                students.add(resultQuery.getString("student"));
            }
        }catch(SQLException ex){
            Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return students;
        }
    }
    
}
