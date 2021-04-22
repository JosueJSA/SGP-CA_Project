/**
 * @author estef
 * Last modification date format: 20-04-2021
 */

package testbussineslogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import sgp.ca.businesslogic.ArticleMagazineDAO;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.ArticleMagazine;

public class ArticleMagazineDAOTest {
    private final ConnectionDatabase conexion = new ConnectionDatabase();
    int rowsNumber;
    
    @Test
    public void addedArticleMagazineTest() throws SQLException{
        System.out.println("Add new articleMagazine to dataBase");
        ArticleMagazine newArticleMagazine;
        newArticleMagazine = new ArticleMagazine("Titulo de la revista", "Indice", "Editorial de la revista");
        ArticleMagazineDAO addArticleMagazine = new ArticleMagazineDAO();
        addArticleMagazine.addArticleMagazine(newArticleMagazine);
        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
        ResultSet queryResult = instructionQuery.executeQuery("Select magazineName FROM ArticleMagazine");
        queryResult.last();
        rowsNumber = queryResult.getRow();
        int expectedNumberRows = 1;
        Assert.assertEquals(expectedNumberRows, rowsNumber);
    }
    
    @Test
    public void returnArticleMagazineListTest() throws SQLException{
        ArticleMagazineDAO articleMagazineDao = new ArticleMagazineDAO();
        List<ArticleMagazine> articleMagazineList = articleMagazineDao.returnArticleMagazines();
        int expectedResult = 1;
        int obtainingResult = articleMagazineList.size();
        assertEquals("Try to get all the articleMagazines", expectedResult, obtainingResult);
    }
    
    @Test
    public void confirmationDeletedArticleMagazineTest() throws SQLException{
        ArticleMagazineDAO articleMagazineDao = new ArticleMagazineDAO();
        articleMagazineDao.deleteArticleMagazinebyMagazineName("Titulo de la revista");
        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
        ResultSet queryResult = instructionQuery.executeQuery("Select urlFile FROM ArticleMagazine");
        queryResult.last();
        rowsNumber = queryResult.getRow();
        int expectedNumberRows = 0;
        Assert.assertEquals(expectedNumberRows, rowsNumber);
    }
}
