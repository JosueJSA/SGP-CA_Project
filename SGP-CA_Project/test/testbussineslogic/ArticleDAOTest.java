///**
// * @author estef
// * Last modification date format: 19-04-2021
// */
//
//package testbussineslogic;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import org.junit.Assert;
//import org.junit.Test;
//import sgp.ca.businesslogic.ArticleDAO;
//import sgp.ca.dataaccess.ConnectionDatabase;
//import sgp.ca.domain.Article;
//
//public class ArticleDAOTest {
//    private final ConnectionDatabase conexion = new ConnectionDatabase();
//    int rowsNumber;
//    
//    @Test
//    public void addedArticleTest() throws SQLException{
//        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
//        ResultSet queryResultBeforeAdded = instructionQuery.executeQuery("Select urlFile FROM Article");
//        queryResultBeforeAdded.last();
//        int expectedNumberRows = queryResultBeforeAdded.getRow() + 1;
//        System.out.println("Add new article to dataBase");
//        Article newArticle;
//        newArticle = new Article(218974587, "Titulo de la revista", "https://eminus.uv.mx/eminus4/", "Crecimiento de lenguajes de programación", "Titulo", "Mexico", "2021-02-01", "Si");
//        ArticleDAO addArticle = new ArticleDAO();
//        addArticle.addArticle(newArticle);
//        ResultSet queryResultAfterAdded = instructionQuery.executeQuery("Select urlFile FROM Article");
//        queryResultAfterAdded.last();
//        rowsNumber = queryResultAfterAdded.getRow();
//        Assert.assertEquals(expectedNumberRows, rowsNumber);
//    }
//    
//    @Test
//    public void confirmationDeletedArticleTest() throws SQLException{
//        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
//        ResultSet queryResultBeforeDeleted = instructionQuery.executeQuery("Select urlFile FROM Article");
//        queryResultBeforeDeleted.last();
//        int expectedNumberRows = queryResultBeforeDeleted.getRow() - 1;
//        ArticleDAO articleDao = new ArticleDAO();
//        articleDao.deleteArticlebyURL("https://eminus.uv.mx/eminus4/");
//        ResultSet queryResultAfterDeleted = instructionQuery.executeQuery("Select urlFile FROM Article");
//        queryResultAfterDeleted.last();
//        rowsNumber = queryResultAfterDeleted.getRow();
//        Assert.assertEquals(expectedNumberRows, rowsNumber);
//    }
//}
