/**
 * @author estef
 * Last modification date format: 20-04-2021
 */

package sgp.ca.businesslogic;

import java.util.List;
import sgp.ca.domain.ArticleMagazine;

public interface IArticleMagazineDAO {
    public List<ArticleMagazine> returnArticleMagazines();
    public ArticleMagazine getArticleMagazinebyMagazineName(String magazineName);
    public void addArticleMagazine(ArticleMagazine newMagazineName);
    public void updateArticleMagazine(ArticleMagazine magazineName, String oldMagazineName);
    public boolean deleteArticleMagazinebyMagazineName(String magazineName);
}
