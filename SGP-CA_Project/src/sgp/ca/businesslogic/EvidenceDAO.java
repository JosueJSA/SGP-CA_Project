/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Evidence;

/**
 *
 * @author josue
 */
public abstract class EvidenceDAO {
    
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();
    
    public List<Map> getAllBodyAcademyEvidences(){
        List<Map> evidences = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM Evidences WHERE impactBA=TRUE GROUP BY urlFile ORDER BY registrationDate DESC;"
            );
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Map<String,Object> evidence = new HashMap<>();
                evidence.put("urlFile", resultQuery.getString("urlFile"));
                evidence.put("evidenceType", resultQuery.getString("evidenceType"));
                evidence.put("evidenceTitle", resultQuery.getString("evidenceTitle"));
                evidence.put("impactBA", resultQuery.getBoolean("impactBA"));
                evidence.put("registrationResponsible", resultQuery.getString("registrationResponsible"));
                evidence.put("registrationDate", resultQuery.getString("registrationDate"));
                evidences.add(evidence);
            }
        }catch(SQLException ex){
            Logger.getLogger(EvidenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return evidences;
        }
    }
    
    public List<Map> getAllEvidencesByIntegrantMailUv(String mailUv){
        List<Map> evidences = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM Evidences WHERE emailUV = ? ORDER BY registrationDate DESC;"
            );
            sentenceQuery.setString(1, mailUv);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Map<String,Object> evidence = new HashMap<>();
                evidence.put("urlFile", resultQuery.getString("urlFile"));
                evidence.put("evidenceType", resultQuery.getString("evidenceType"));
                evidence.put("evidenceTitle", resultQuery.getString("evidenceTitle"));
                evidence.put("impactBA", resultQuery.getBoolean("impactBA"));
                evidence.put("registrationResponsible", resultQuery.getString("registrationResponsible"));
                evidence.put("registrationDate", resultQuery.getString("registrationDate"));
                evidences.add(evidence);
            }
        }catch(SQLException ex){
            Logger.getLogger(EvidenceDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return evidences;
        }
    }
    
    public List<Map> getEvidencesByRegistrationDate(String date){
        List<Map> evidences = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM Evidences WHERE cast(registrationDate as date) < CAST( ? as DATE) GROUP BY urlFile ORDER BY registrationDate DESC;"
            );
            sentenceQuery.setString(1, date);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Map<String,Object> evidence = new HashMap<>();
                evidence.put("urlFile", resultQuery.getString("urlFile"));
                evidence.put("evidenceType", resultQuery.getString("evidenceType"));
                evidence.put("evidenceTitle", resultQuery.getString("evidenceTitle"));
                evidence.put("impactBA", resultQuery.getBoolean("impactBA"));
                evidence.put("registrationResponsible", resultQuery.getString("registrationResponsible"));
                evidence.put("registrationDate", resultQuery.getString("registrationDate"));
                evidences.add(evidence);
            }
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return evidences;
        }
    }
    
    public List<Map> getEvidencesByStudent(String student){
        List<Map> evidences = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT DISTINCT * FROM (SELECT * FROM Evidences GROUP BY urlFile) ev, Students  WHERE ev.urlFile = Students.urlFile AND Students.student = ?;"
            );
            sentenceQuery.setString(1, student);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Map<String,Object> evidence = new HashMap<>();
                evidence.put("urlFile", resultQuery.getString("urlFile"));
                evidence.put("evidenceType", resultQuery.getString("evidenceType"));
                evidence.put("evidenceTitle", resultQuery.getString("evidenceTitle"));
                evidence.put("impactBA", resultQuery.getBoolean("impactBA"));
                evidence.put("registrationResponsible", resultQuery.getString("registrationResponsible"));
                evidence.put("registrationDate", resultQuery.getString("registrationDate"));
                evidences.add(evidence);
            }
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return evidences;
        }
    }
    
    public abstract Evidence getEvidenceByUrl(String urlEvidenceFile);
    public abstract boolean addNewEvidence(Evidence evidence);
    public abstract boolean updateEvidence(Evidence evidence, String oldUrlFile);
    public abstract boolean deleteEvidenceByUrl(String urlEvidenceFile);
    
    
}
