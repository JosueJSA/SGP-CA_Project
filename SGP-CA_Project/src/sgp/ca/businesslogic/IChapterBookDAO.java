/**
 * @author estef
 * Last modification date format: 16-05-2021
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.util.List;
import sgp.ca.domain.Book;
import sgp.ca.domain.ChapterBook;

public interface IChapterBookDAO {
    public void addChapterBooks(Connection connection, Book book);
    public List<ChapterBook> getChapterBooksListByBook(Connection connection, String urlFileBook);
    public void deleteStudentsFromChapterBook(Connection connection, List<ChapterBook> chapterBooks);
    public void deleteIntegrantsFromChapterBook(Connection connection, List<ChapterBook> chapterBooks);
    public void deleteCollaboratorsFromChapterBook(Connection connection, List<ChapterBook> chapterBooks);
}
