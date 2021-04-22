/**
 * @author estef
 * Last modification date format: 19-04-2021
 */

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.Article;

public interface IArticleDAO {
    public List<Article> returnArticle();
    public Article getArticlebyURL(String urlFile);
    public void addArticle(Article newArticle);
    public void updateArticle(Article article, String oldUrlFile);
    public boolean deleteArticlebyURL(String urlFile);
}
