/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package testdataaccess;

import java.sql.SQLException;
import java.sql.Connection;

import sgp.ca.dataaccess.ConnectionDatabase;
import org.junit.Test;
import org.junit.Assert;


public class DataBaseConnectionTest {
    
    @Test
    public void testDataBaseConnection() throws SQLException{
        Connection currentConection = new ConnectionDatabase().getConnectionDatabase();
        Assert.assertNotNull(currentConection);
    }
}
