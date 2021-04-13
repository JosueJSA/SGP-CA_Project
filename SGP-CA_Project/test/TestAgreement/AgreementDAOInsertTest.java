
package TestAgreement;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businessLogic.AgreementDAO;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Agreement;

public class AgreementDAOInsertTest {
    
    private final ConnectionDatabase query = new ConnectionDatabase();
    int cantidadRows;
    
     @Test
    public void addAgreement() throws SQLException{
        System.out.println("addAgreement");
        Agreement agreement = new Agreement(1, "2021-04-02", "17:30:00", "se realizo por motivos...", "2021-05-04");
        AgreementDAO instanceProject = new AgreementDAO();
        instanceProject.addAgreement(agreement); 
        Statement instructionQuery = query.getConnectionDatabase().createStatement();;
        ResultSet queryResult = instructionQuery.executeQuery("Select agreementNumber FROM Agreement");
        queryResult.last();
        cantidadRows = queryResult.getRow();   
        Assert.assertEquals(1, cantidadRows);
    }
   
}
