/**
* @author Johann 
* Last modification date format: 26-03-2021
*/

package sgp.ca.businessLogic;


import domain.Agreement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;

public class AgreementDAO implements IAgreementDAO{
    private final ConnectionDatabase query = new ConnectionDatabase();

    @Override
    public void addAgreement(Agreement newAgreement) {
        try{
            PreparedStatement sentenceQuery = query.getConnectionDatabase().prepareStatement(
                "INSERT INTO Agreement VALUES(?, ?, ?, ?, ?)"
            );
            sentenceQuery.setInt(1, newAgreement.getAgreementNumber());
            sentenceQuery.setString(2, newAgreement.getMeetingDate());
            sentenceQuery.setString(3, newAgreement.getMeetingTime());
            sentenceQuery.setString(4, newAgreement.getDescriptionAgreement());
            sentenceQuery.setString(5, newAgreement.getDeliveryDate());
            sentenceQuery.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            query.closeConnection();
        }
    }
}
