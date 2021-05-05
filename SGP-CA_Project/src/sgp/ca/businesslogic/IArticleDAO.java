/**
 * @author estef
 * Last modification date format: 19-04-2021
 */

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.Article;

public interface IArticleDAO {
    
    public List<Article> getArticleByIntegrantRFC(String rfc, String limitDate);
    public List<Article> getArticleByTitle(String title, String limitDate);
    public List<Article> getArticlesByStudent(String studentName, String limitDate);
    public void addArticle(Article newArticle);
    public void updateArticle(Article article, String oldUrlFile);
    public void deleteArticlebyURL(String urlFileArticle);
    
}
