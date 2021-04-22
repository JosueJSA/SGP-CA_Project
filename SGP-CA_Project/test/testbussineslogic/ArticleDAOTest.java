/**
 * @author estef
 * Last modification date format: 19-04-2021
 */

package testbussineslogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import sgp.ca.businesslogic.ArticleDAO;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Article;

public class ArticleDAOTest {
    private final ConnectionDatabase conexion = new ConnectionDatabase();
    int rowsNumber;
    
    @Test
    public void addedArticleTest() throws SQLException{
        System.out.println("Add new article to dataBase");
        Article newArticle;
        newArticle = new Article(218974587, "Titulo de la revista", "https://eminus.uv.mx/eminus4/", "Crecimiento de lenguajes de programación", "Titulo", "Mexico", "2021-02-01", "Si");
        ArticleDAO addArticle = new ArticleDAO();
        addArticle.addArticle(newArticle);
        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
        ResultSet queryResult = instructionQuery.executeQuery("Select urlFile FROM Article");
        queryResult.last();
        rowsNumber = queryResult.getRow();
        int expectedNumberRows = 1;
        Assert.assertEquals(expectedNumberRows, rowsNumber);
    }
    
    @Test
    public void returnArticlesListTest() throws SQLException{
        ArticleDAO articleDao = new ArticleDAO();
        List<Article> articleList = articleDao.returnArticle();
        int expectedResult = 1;
        int obtainingResult = articleList.size();
        assertEquals("Try to get all the articles", expectedResult, obtainingResult);
    }
    
    @Test
    public void confirmationDeletedArticleTest() throws SQLException{
        ArticleDAO articleDao = new ArticleDAO();
        articleDao.deleteArticlebyURL("https://eminus.uv.mx/eminus4/");
        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
        ResultSet queryResult = instructionQuery.executeQuery("Select urlFile FROM Article");
        queryResult.last();
        rowsNumber = queryResult.getRow();
        int expectedNumberRows = 0;
        Assert.assertEquals(expectedNumberRows, rowsNumber);
    }
}
