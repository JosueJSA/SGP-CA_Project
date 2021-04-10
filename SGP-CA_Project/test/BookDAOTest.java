
import java.sql.SQLException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import sgp.ca.businesslogic.BookDAO;
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
    @Test
    public void foundBookbyURLTest()throws SQLException{
        BookDAO bookDao = new BookDAO();
        Book book = bookDao.looforBookbyURL(""); 
    
    }
    
    @Test
    public void returnBooksListTest() throws SQLException{
        BookDAO bookDao = new BookDAO();
        List<Book> bookList = bookDao.returnBooks();
        int expectedResult = 1;
        int obtainingResult = bookList.size();
        assertEquals("Try to get all the books", expectedResult, obtainingResult);
    }
}


