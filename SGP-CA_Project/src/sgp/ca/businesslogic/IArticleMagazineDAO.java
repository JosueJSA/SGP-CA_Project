/**
 * @author estef
 * Last modification date format: 20-04-2021
 */

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.ArticleMagazine;

public interface IArticleMagazineDAO {
    public List<ArticleMagazine> returnArticleMagazine();
    public ArticleMagazine getArticleMagazinebyMagazineName(String magazineName);
    public void addBook(ArticleMagazine newMagazineName);
    public void updateBook(ArticleMagazine magazineName, String oldMagazineName);
    public boolean deleteBookbyURL(String magazineName);
}
