/**
 * @author estef
 * Last modification date format: 19-04-2021
 */

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.Book;

public interface IBookDAO {
    public List<Book> returnBooks();
    public Book getBookbyURL(String urlFile);
    public void addBook(Book newBook);
    public void updateBook(Book book, String oldUrlFile);
    public boolean deleteBookbyURL(String urlFile);
}
