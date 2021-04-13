
package TestReceptionWork;


import domain.ReceptionWork;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businessLogic.ReceptionWorkDAO;
import sgp.ca.dataaccess.ConnectionDatabase;


public class ReceptionWorkInsertTest {
    private final ConnectionDatabase query = new ConnectionDatabase();
    int cantidadRows;
    
    @Test
    public void addReceptionWorkTest() throws SQLException{
        System.out.println("addReceptionWork");
        ReceptionWork receptionWork = new ReceptionWork("http...prueba.com","Crecimiento de lenguajes de programación", "true", "Analisis de metodologias", "2021-05-07", "México", "Comprobar la forma de reacción de cada una de ellas...", "Activo", 1, 4, "Tesina");
        ReceptionWorkDAO instanceProject = new ReceptionWorkDAO();
        instanceProject.addReceptionWork(receptionWork); 
        Statement instructionQuery = query.getConnectionDatabase().createStatement();;
        ResultSet queryResult = instructionQuery.executeQuery("Select urlFile FROM ReceptionWork");
        queryResult.last();
        cantidadRows = queryResult.getRow();  
        Assert.assertEquals(2, cantidadRows);
    }
   
}
