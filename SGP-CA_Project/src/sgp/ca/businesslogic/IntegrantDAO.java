/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.businesslogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Integrant;

public class IntegrantDAO implements IIntegrantDAO{

    private final ConnectionDatabase connectionQuery = new ConnectionDatabase();
    
    @Override
    public Integrant getIntegrant(String emailUV) {
        Integrant integrant = new Integrant();
        try{
            PreparedStatement sentenceQuery = connectionQuery.getConnectionDatabase().prepareStatement(
                "select * from Integrant where emailUV = ?;"
            );
            sentenceQuery.setString(1, emailUV);
            ResultSet queryResult = sentenceQuery.executeQuery();
            queryResult.next();
            integrant = new Integrant(
                queryResult.getString("rfc"), 
                queryResult.getString("fullName"),
                queryResult.getString("emailUV"),
                queryResult.getString("additionalEmail"),
                queryResult.getString("curp"),
                queryResult.getString("nacionality"),
                queryResult.getInt("satffNumber"),
                queryResult.getInt("cellPhone"),
                queryResult.getDate("dateOfAdmission").toString(),
                queryResult.getString("appointment"),
                queryResult.getString("participationType"),
                queryResult.getInt("homePhone"),
                queryResult.getInt("workPhone")
            );
        }catch(SQLException sqlException){
            Logger.getLogger(Integrant.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionQuery.closeConnection();
            return integrant;
        }
    }

    @Override
    public void addIntegrant(Integrant newIntegrant, String bodyAcademyKey) {
        try{
            PreparedStatement sentenceQuery = connectionQuery.getConnectionDatabase().prepareStatement(
                "INSERT INTO Integrant VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
            );
            sentenceQuery.setString(1, newIntegrant.getRfc());
            sentenceQuery.setString(2, bodyAcademyKey);
            sentenceQuery.setString(3, newIntegrant.getFullName());
            sentenceQuery.setString(4, newIntegrant.getDateOfAdmission());
            sentenceQuery.setString(5, newIntegrant.getAditionalEmail());
            sentenceQuery.setString(6, newIntegrant.getEmailUV());
            sentenceQuery.setString(7, newIntegrant.getCurp());
            sentenceQuery.setString(8, newIntegrant.getNationality());
            sentenceQuery.setInt(9, newIntegrant.getCellphone());
            sentenceQuery.setInt(10, newIntegrant.getStaffNumber());
            sentenceQuery.setInt(11, newIntegrant.getHomePhone());
            sentenceQuery.setInt(12, newIntegrant.getWorkPhone());
            sentenceQuery.setString(13, newIntegrant.getAppointmentMember());
            sentenceQuery.setString(14, newIntegrant.getParticipationType());
            sentenceQuery.executeUpdate();
            JOptionPane.showConfirmDialog(null, "listo");
        }catch(SQLException sqlException){
            Logger.getLogger(Integrant.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionQuery.closeConnection();
        }
    }
    
}
