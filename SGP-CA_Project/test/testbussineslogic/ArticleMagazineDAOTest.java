/**
 * @author estef
 * Last modification date format: 20-04-2021
 */

package testbussineslogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.ArticleMagazineDAO;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.ArticleMagazine;

public class ArticleMagazineDAOTest {
    private final ConnectionDatabase conexion = new ConnectionDatabase();
    int rowsNumber;
    
    @Test
    public void addedArticleMagazineTest() throws SQLException{
        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
        ResultSet queryResultBeforeAdded = instructionQuery.executeQuery("Select magazineName FROM ArticleMagazine");
        queryResultBeforeAdded.last();
        int expectedNumberRows = queryResultBeforeAdded.getRow() + 1;
        System.out.println("Add new articleMagazine to dataBase");
        ArticleMagazine newArticleMagazine;
        newArticleMagazine = new ArticleMagazine("Titulo de la revista", "Indice", "Editorial de la revista");
        ArticleMagazineDAO addArticleMagazine = new ArticleMagazineDAO();
        addArticleMagazine.addArticleMagazine(newArticleMagazine);
        ResultSet queryResultAfterAdded = instructionQuery.executeQuery("Select magazineName FROM ArticleMagazine");
        queryResultAfterAdded.last();
        rowsNumber = queryResultAfterAdded.getRow();
        Assert.assertEquals(expectedNumberRows, rowsNumber);
    }
    
    @Test
    public void confirmationDeletedArticleMagazineTest() throws SQLException{
        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
        ResultSet queryResultBeforeDeleted = instructionQuery.executeQuery("Select magazineName FROM ArticleMagazine");
        queryResultBeforeDeleted.last();
        int expectedNumberRows = queryResultBeforeDeleted.getRow() - 1;
        ArticleMagazineDAO articleMagazineDao = new ArticleMagazineDAO();
        articleMagazineDao.deleteArticleMagazinebyMagazineName("Titulo de la revista");
        ResultSet queryResultAfterDeleted = instructionQuery.executeQuery("Select magazineName FROM ArticleMagazine");
        queryResultAfterDeleted.last();
        rowsNumber = queryResultAfterDeleted.getRow();
        Assert.assertEquals(expectedNumberRows, rowsNumber);
    }
}
