/**
 * @author estef
 * Last modification date format: 19-04-2021
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Book;

public class BookDAO implements IBookDAO {
    private final ConnectionDatabase connectionDataBase = new ConnectionDatabase();
    private Connection connection;
    private Statement consult;
    private ResultSet results;
    
    @Override
    public List<Book> returnBooks() {
        List<Book>bookList = new ArrayList<>();
        try{
            connection = connectionDataBase.getConnectionDatabase();
            consult = connection.createStatement();
            results = consult.executeQuery("Select * from Book");
            while(results.next()){
                Book book = new Book();
                book.setUrlFile(results.getString("urlFile"));
                book.setProjectName(results.getNString("projectName"));
                book.setImpactAB(results.getNString("impactBA"));
                book.setEvidenceTitle(results.getNString("evidenceTitle"));
                book.setPublicationDate(results.getString("publicationDate"));
                book.setCountry(results.getNString("country"));
                book.setPublisher(results.getNString("publisher"));
                book.setEditionsNumber(results.getInt("editionsNumber"));
                book.setIsbn(results.getDouble("isbn"));
                bookList.add(book);
            }
        
        }catch(SQLException ex){
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            connectionDataBase.closeConnection();
        }
        return bookList;
    }

    @Override
    public Book getBookbyURL(String url) {
        Book book = null;
        try(Connection connection = connectionDataBase.getConnectionDatabase()){
            String query = "Select * from Book where urlFile = ? ";
            PreparedStatement sentence = connection.prepareStatement(query);
            sentence.setString(1, url);
            results = sentence.executeQuery();
            
            while(results.next()){
                book = new Book();
                book.setUrlFile(results.getString("urlFile"));
                book.setProjectName(results.getNString("projectName"));
                book.setImpactAB(results.getNString("impactBA"));
                book.setEvidenceTitle(results.getNString("evidenceTitle"));
                book.setPublicationDate(results.getString("publicationDate"));
                book.setCountry(results.getNString("county"));
                book.setPublisher(results.getNString("publisher"));
                book.setEditionsNumber(results.getInt("editiosNumber"));
                book.setIsbn(results.getDouble("isbn"));
            }
                
        }catch(SQLException ex){
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return book;
    }

    @Override
    public void addBook(Book book) {
        try{
            PreparedStatement sentence = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "INSERT INTO Book VALUES (?,?,?,?,?,?,?,?,?);"
            );
            sentence.setString(1, book.getUrlFile());
            sentence.setString(2, book.getProjectName());
            sentence.setString(3, book.getImpactAB());
            sentence.setString(4, book.getEvidenceTitle());
            sentence.setString(5, book.getPublicationDate());
            sentence.setString(6, book.getCountry());
            sentence.setString(7, book.getPublisher());
            sentence.setInt(8, book.getEditionsNumber());
            sentence.setDouble(9, book.getIsbn());
            sentence.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
    }

    @Override
    public void updateBook(Book book, String urlFile) {
        try{
            PreparedStatement sentence = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "UPDATE Book SET urlFile = ?, projectName = ?, impactBA = ?, evidenceTitle = ?," 
                    + "publicationDate = ?, country = ?, publisher = ?, editionsNumber = ?, isbn = ?"
                    + "WHERE urlFile = ?;"
            );
            sentence.setString(1, book.getUrlFile());
            sentence.setString(2, book.getProjectName());
            sentence.setString(3, book.getImpactAB());
            sentence.setString(4, book.getEvidenceTitle());
            sentence.setString(5, book.getPublicationDate());
            sentence.setString(6, book.getCountry());
            sentence.setString(7, book.getPublisher());
            sentence.setInt(8, book.getEditionsNumber());
            sentence.setDouble(9, book.getIsbn());
            sentence.setString(10, urlFile);
            sentence.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
    }
    
    @Override
    public boolean deleteBookbyURL(String url) {
        boolean deletedBook = false;
        try{
            PreparedStatement sentenceQuery = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "DELETE FROM Book "
                    + "WHERE urlFile = ? "
                    + "LIMIT 1;"
            );
            sentenceQuery.setString(1, url);
            sentenceQuery.executeUpdate();
            deletedBook = true;
        }catch(SQLException sqlException){
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
        return deletedBook;
    }
}
