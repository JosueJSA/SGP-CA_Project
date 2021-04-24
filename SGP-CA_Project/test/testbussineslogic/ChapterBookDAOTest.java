/**
 * @author estef
 * Last modification date format: 19-04-2021
 */

package testbussineslogic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.ChapterBookDAO;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.ChapterBook;

public class ChapterBookDAOTest {
    private final ConnectionDatabase conexion = new ConnectionDatabase();
    int rowsNumber;
    
    @Test
    public void addedChapterBookTest() throws SQLException{
        System.out.println("Add new chapterBook to dataBase");
        ChapterBook newChapterBook;
        newChapterBook = new ChapterBook("Nombre del libro", "97-214", "https://eminus.uv.mx/eminus4/", "Crecimiento de lenguajes de programación", "Titulo", "Mexico", "2021-02-01", "Si");
        ChapterBookDAO addChapterBook = new ChapterBookDAO();
        addChapterBook.addChapterBook(newChapterBook);
        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
        ResultSet queryResult = instructionQuery.executeQuery("Select urlFile FROM ChapterBook");
        queryResult.last();
        rowsNumber = queryResult.getRow();
        int expectedNumberRows = 1;
        Assert.assertEquals(expectedNumberRows, rowsNumber);
    }
    
    @Test
    public void confirmationDeletedChapterBookTest() throws SQLException{
        ChapterBookDAO chapterBookDao = new ChapterBookDAO();
        chapterBookDao.deleteChapterBookbyURL("https://eminus.uv.mx/eminus4/");
        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
        ResultSet queryResult = instructionQuery.executeQuery("Select urlFile FROM ChapterBook");
        queryResult.last();
        rowsNumber = queryResult.getRow();
        int expectedNumberRows = 0;
        Assert.assertEquals(expectedNumberRows, rowsNumber);
    }
}
