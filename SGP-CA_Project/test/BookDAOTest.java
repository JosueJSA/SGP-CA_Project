
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import sgp.ca.businesslogic.BookDAO;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Book;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Estefania Berenice Martinez Ramirez 
 */
public class BookDAOTest {
    private final ConnectionDatabase conexion = new ConnectionDatabase();
    int rowsNumber;
    @Test
    public void foundBookbyURLTest()throws SQLException{
        BookDAO bookDao = new BookDAO();
        //Book book = bookDao.getBookbyURL(); 
    
    }
    
    @Test
    public void addedBook() throws SQLException{
        System.out.println("Add new book to dataBase");
        Book newBook = new Book("Editorial", 4, 218974587, "https://eminus.uv.mx/eminus4/", "fffff", "Titulo", "Mexico", "2021-02-01", "Si");
        BookDAO addBook = new BookDAO();
        addBook.addBook(newBook);
        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();;
        ResultSet queryResult = instructionQuery.executeQuery("Select urlFile FROM MeetingPending");
        queryResult.last();
        rowsNumber = queryResult.getRow();
        Assert.assertEquals(1, rowsNumber);
    }
    
    @Test
    public void returnBooksListTest() throws SQLException{
        BookDAO bookDao = new BookDAO();
        List<Book> bookList = bookDao.returnBooks();
        int expectedResult = 0;
        int obtainingResult = bookList.size();
        assertEquals("Try to get all the books", expectedResult, obtainingResult);
    }
}


