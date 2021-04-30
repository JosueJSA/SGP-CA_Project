///**
// *
// * @author estef
// * Last modification date format: 19-04-2021
// */
//
//package testbussineslogic;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import junit.framework.Assert;
//import org.junit.Test;
//import sgp.ca.businesslogic.BookDAO;
//import sgp.ca.dataaccess.ConnectionDatabase;
//import sgp.ca.domain.Book;
//
//public class BookDAOTest {
//    private final ConnectionDatabase conexion = new ConnectionDatabase();
//    int rowsNumber;
//    
//    @Test
//    public void addedBookTest() throws SQLException{
//        System.out.println("Add new book to dataBase");
//        Book newBook;
//        newBook = new Book("Editorial", 4, 218974587, "https://eminus.uv.mx/eminus4/", "Crecimiento de lenguajes de programación", "Titulo", "Mexico", "2021-02-01", "Si");
//        BookDAO addBook = new BookDAO();
//        addBook.addBook(newBook);
//        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
//        ResultSet queryResult = instructionQuery.executeQuery("Select urlFile FROM Book");
//        queryResult.last();
//        rowsNumber = queryResult.getRow();
//        int expectedNumberRows = 1;
//        Assert.assertEquals(expectedNumberRows, rowsNumber);
//    }
//    
//    @Test
//    public void confirmationDeletedBookTest() throws SQLException{
//        BookDAO bookDao = new BookDAO();
//        bookDao.deleteBookbyURL("https://eminus.uv.mx/eminus4/");
//        Statement instructionQuery = conexion.getConnectionDatabase().createStatement();
//        ResultSet queryResult = instructionQuery.executeQuery("Select urlFile FROM Book");
//        queryResult.last();
//        rowsNumber = queryResult.getRow();
//        int expectedNumberRows = 0;
//        Assert.assertEquals(expectedNumberRows, rowsNumber);
//    }
//}