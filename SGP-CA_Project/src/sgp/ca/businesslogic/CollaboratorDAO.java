/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Collaborator;

/**
 *
 * @author Josue Alarcon
 */
public class CollaboratorDAO implements ICollaboratorDAO{
    
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();

    @Override
    public Collaborator getCollaboratorByUVmail(String uvMail) {
        Collaborator collaborator = new Collaborator();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "select * from Collaborator where emailUV = ?;"
            );
            sentenceQuery.setString(1, uvMail);
            ResultSet queryResult = sentenceQuery.executeQuery();
            if(queryResult.next()){collaborator = new Collaborator(
                queryResult.getString("rfc"), 
                queryResult.getString("fullName"),
                queryResult.getString("emailUV"),
                queryResult.getString("curp"),
                queryResult.getString("nacionality"),
                queryResult.getDate("dateOfAdmission").toString(),
                queryResult.getString("educationalProgram"),
                queryResult.getInt("satffNumber"),
                queryResult.getString("cellPhone"),
                queryResult.getString("bodyAcademyKey"),
                queryResult.getString("studyArea"),
                queryResult.getString("nameBACollaborator"),
                queryResult.getString("highestDegreeStudies")
            );}
        }catch(SQLException sqlException){
            Logger.getLogger(Collaborator.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
            return collaborator;
        }
    }

    @Override
    public void addCollaborator(Collaborator newCollaborator){
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "INSERT INTO Collaborator VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
            );
            sentenceQuery.setString(1, newCollaborator.getRfc());
            sentenceQuery.setString(2, newCollaborator.getBodyAcademyKey());
            sentenceQuery.setString(3, newCollaborator.getFullName());
            sentenceQuery.setString(4, newCollaborator.getDateOfAdmission());
            sentenceQuery.setString(5, newCollaborator.getEmailUV());
            sentenceQuery.setString(6, newCollaborator.getCurp());
            sentenceQuery.setString(7, newCollaborator.getNationality());
            sentenceQuery.setString(8, newCollaborator.getEducationalProgram());
            sentenceQuery.setString(9, newCollaborator.getCellphone());
            sentenceQuery.setInt(10, newCollaborator.getStaffNumber());
            sentenceQuery.setString(11, newCollaborator.getStudyArea());
            sentenceQuery.setString(12, newCollaborator.getNameBACollaborator());
            sentenceQuery.setString(13, newCollaborator.getHighestDegreeStudies());
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Collaborator.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
        }
    }

    @Override
    public void updateCollaborator(Collaborator collaborator, String oldRFC) {
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "UPDATE Collaborator SET rfc = ?, bodyAcademyKey = ?, fullName = ?, dateOfAdmission = ?, emailUV = ?, "
                + "curp = ?, nacionality = ?, educationalProgram = ?, cellPhone = ?, satffNumber = ?,"
                + " studyArea = ?, nameBACollaborator = ?, highestDegreeStudies = ? WHERE rfc = ?;"
            );
            sentenceQuery.setString(1, collaborator.getRfc());
            sentenceQuery.setString(2, collaborator.getBodyAcademyKey());
            sentenceQuery.setString(3, collaborator.getFullName());
            sentenceQuery.setString(4, collaborator.getDateOfAdmission());
            sentenceQuery.setString(5, collaborator.getEmailUV());
            sentenceQuery.setString(6, collaborator.getCurp());
            sentenceQuery.setString(7, collaborator.getNationality());
            sentenceQuery.setString(8, collaborator.getEducationalProgram());
            sentenceQuery.setString(9, collaborator.getCellphone());
            sentenceQuery.setInt(10, collaborator.getStaffNumber());
            sentenceQuery.setString(11, collaborator.getStudyArea());
            sentenceQuery.setString(12, collaborator.getNameBACollaborator());
            sentenceQuery.setString(13, collaborator.getHighestDegreeStudies());
            sentenceQuery.setString(14, oldRFC);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Collaborator.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
        }
    }
    
    @Override
    public void deleteCollaboratorByEmailUV(String emailUV){
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "DELETE FROM Collaborator where emailUV = ?;"
            );
            sentenceQuery.setString(1,emailUV);
            sentenceQuery.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(CollaboratorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
        }
    }
    
}
