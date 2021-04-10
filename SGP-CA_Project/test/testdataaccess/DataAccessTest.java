/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testdataaccess;

import java.sql.SQLException;
import java.sql.Connection;

import sgp.ca.dataaccess.ConnectionDatabase;
import org.junit.Test;
import org.junit.Assert;
/**
 *
 * @author Josue Alarcon
 */
public class DataAccessTest {
    @Test
    public void DataBaseConnectionTest() throws SQLException{
        Connection currentConection = new ConnectionDatabase().getConnectionDatabase();
        Assert.assertNotNull(currentConection);
    }
}
