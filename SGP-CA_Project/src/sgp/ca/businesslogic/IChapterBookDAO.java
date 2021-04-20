/**
 * @author estef
 * Last modification date format: 19-04-2021
 */

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.ChapterBook;

public interface IChapterBookDAO {
    public List<ChapterBook> returnChapterBooks();
    public ChapterBook getChapterBookbyURL(String urlFile);
    public void addChapterBook(ChapterBook newChapterBook);
    public void updateChapterBook(ChapterBook chapterBook, String oldUrlFile);
    public boolean deleteChapterBookbyURL(String urlFile);
}
