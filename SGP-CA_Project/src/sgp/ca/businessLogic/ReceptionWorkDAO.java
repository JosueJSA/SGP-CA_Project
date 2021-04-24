/**
* @author Johann 
* Last modification date format: 25-03-2021
*/

package sgp.ca.businessLogic;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.ReceptionWork;

public class ReceptionWorkDAO implements IReceptionWorkDAO{
    
    private final ConnectionDatabase query = new ConnectionDatabase();
    
    @Override
    public void addReceptionWork(ReceptionWork newReceptionWork){
        try{
            PreparedStatement sentenceQuery = query.getConnectionDatabase().prepareStatement(
                "insert into ReceptionWork values(? , ? , ?, ?, ?, ?, ?, ?, ?, ?, ?);"
            );
            sentenceQuery.setString(1, newReceptionWork.getUrlFile());
            sentenceQuery.setString(2, newReceptionWork.getProjectName());
            sentenceQuery.setString(3, newReceptionWork.isImpactBA());
            sentenceQuery.setString(4, newReceptionWork.getEvidenceTitle());
            sentenceQuery.setString(5, newReceptionWork.getPublicationDate());
            sentenceQuery.setString(6, newReceptionWork.getCountry());
            sentenceQuery.setString(7, newReceptionWork.getDescription());
            sentenceQuery.setString(8, newReceptionWork.getStatus());
            sentenceQuery.setInt(9, newReceptionWork.getActualDurationInMonths());
            sentenceQuery.setInt(10, newReceptionWork.getEstimatedDurationInMonths());
            sentenceQuery.setString(11, newReceptionWork.getModality());
            sentenceQuery.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            query.closeConnection();
        }
    }
    
    @Override
    public List<ReceptionWork> getReceptionWorkList(){
        List<ReceptionWork> listReceptionWork = new ArrayList<>();
        try{
            Statement instructionQuery = query.getConnectionDatabase().createStatement();;
            ResultSet queryResult = instructionQuery.executeQuery("Select urlFile, projectName, impactBA, evidenceTitle, publicationDate, "
                    + "country,  description, status, actualDurationInMonths, estimatedDurationInMonths, modality FROM ReceptionWork");
            while(queryResult.next()){
                listReceptionWork.add(new ReceptionWork(
                    queryResult.getString("urlFile"), 
                    queryResult.getString("projectName"),
                    queryResult.getString("impactBA"),
                    queryResult.getString("evidenceTitle"),
                    queryResult.getString("publicationDate"),
                    queryResult.getString("country"),
                    queryResult.getString("description"),
                    queryResult.getString("status"),
                    queryResult.getInt("actualDurationInMonths"),
                    queryResult.getInt("estimatedDurationInMonths"),
                    queryResult.getString("modality")));
            }
        }catch(SQLException ex){
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            query.closeConnection();
            return listReceptionWork;
        }
    }
    
    @Override
    public boolean updateReceptionWork(ReceptionWork receptionWork, String oldUrlFile){
        try{
            PreparedStatement sentenceQuery = query.getConnectionDatabase().prepareStatement(
                "UPDATE ReceptionWork SET urlFile = ?, projectName = ?, impactBA = ?, evidenceTitle = ?,"
                + " publicationDate = ?, country = ?, description = ?, status= ?, actualDurationInMonths = ?,"
                + "estimatedDurationInMonths = ?, modality = ? WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, receptionWork.getUrlFile());
            sentenceQuery.setString(2, receptionWork.getProjectName());
            sentenceQuery.setString(3, receptionWork.isImpactBA());
            sentenceQuery.setString(4, receptionWork.getEvidenceTitle());
            sentenceQuery.setString(5, receptionWork.getPublicationDate());
            sentenceQuery.setString(6, receptionWork.getCountry());
            sentenceQuery.setString(7, receptionWork.getDescription());
            sentenceQuery.setString(8, receptionWork.getStatus());
            sentenceQuery.setInt(9, receptionWork.getActualDurationInMonths());
            sentenceQuery.setInt(10, receptionWork.getEstimatedDurationInMonths());
            sentenceQuery.setString(11, receptionWork.getModality());
            sentenceQuery.setString(12, oldUrlFile);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(ReceptionWork.class.getName()).log(Level.SEVERE, null, sqlException);
            return false;
        }finally{
            query.closeConnection();
            return true;
        }
    }
   
}
