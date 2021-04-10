/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.businesslogic;

import java.net.URL;
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

/**
 *
 * @author estef
 */
public class BookDAO implements IBookDAO {
    private final ConnectionDatabase conexion;
    private Connection connection;
    private Statement consult;
    private ResultSet results;

    public BookDAO() {
        conexion = new ConnectionDatabase();
    }

    

    @Override
    public List<Book> returnBooks() {
        List<Book>bookList = new ArrayList<>();
        try{
            connection = conexion.getConnectionDatabase();
            consult = connection.createStatement();
            results = consult.executeQuery("Select * from Book");
            while(results.next()){
                Book newBook = new Book();
                newBook.setUrlFile(results.getString("urlFile"));
                newBook.setEvidenceTitle(results.getNString("evidenceTitle"));
                newBook.setPublicationDate(results.getDate("publicationDate"));
                newBook.setCountry(results.getNString("country"));
                newBook.setPublisher(results.getNString("publisher"));
                newBook.setEditionsNumber(results.getInt("editionsNumber"));
                newBook.setIsbn(results.getDouble("isbn"));
                bookList.add(newBook);
            }
        
        }catch(SQLException ex){
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.closeConnection();
        }
        return bookList;
    }

    @Override
    public Book looforBookbyURL(String url) {
        Book book = null;
        try(Connection connection = conexion.getConnectionDatabase()){
            String query = "Select * from Book where urlFile=? ";
            PreparedStatement sentence = connection.prepareStatement(query);
            sentence.setString(1, url);
            results = sentence.executeQuery();
            
            while(results.next()){
                book = new Book();
                book.setUrlFile(results.getString("urlFile"));
                book.setEvidenceTitle(results.getNString("evidenceTitle"));
                book.setPublicationDate(results.getDate("publicationDate"));
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
    public boolean addBook(Book book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteBookbyURL(String url) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
