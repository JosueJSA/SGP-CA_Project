/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.Book;

/**
 *
 * @author estef
 */
public interface IBookDAO {
    public List<Book> returnBooks();
    public Book getBookbyURL(String urlFile);
    public void addBook(Book book);
    public void updateBook(Book book, String urlFile);
    public boolean deleteBookbyURL(String url);
}
