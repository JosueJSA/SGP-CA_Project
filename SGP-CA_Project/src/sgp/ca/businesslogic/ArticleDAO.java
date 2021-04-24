/**
 * @author estef
 * Last modification date format: 23-04-2021
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Article;

public class ArticleDAO implements IArticleDAO{
    private final ConnectionDatabase connectionDataBase = new ConnectionDatabase();
    private ResultSet results;

    @Override
    public Article getArticlebyURL(String urlFile) {
        Article article = null;
        try(Connection connection = connectionDataBase.getConnectionDatabase()){
            String query = "Select * from Article where urlFile = ? ";
            PreparedStatement sentence = connection.prepareStatement(query);
            sentence.setString(1, urlFile);
            results = sentence.executeQuery();
            
            while(results.next()){
                article = new Article();
                article.setUrlFile(results.getString("urlFile"));
                article.setProjectName(results.getNString("projectName"));
                article.setImpactAB(results.getNString("impactBA"));
                article.setEvidenceTitle(results.getNString("evidenceTitle"));
                article.setPublicationDate(results.getString("publicationDate"));
                article.setCountry(results.getNString("county"));
                article.setMagazineName(results.getNString("magazineName"));
                article.setIsnn(results.getDouble("isnn"));
            }
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return article;
    }

    @Override
    public void addArticle(Article newArticle) {
        try{
            PreparedStatement sentence = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "INSERT INTO Article VALUES (?,?,?,?,?,?,?,?);"
            );
            sentence.setString(1, newArticle.getUrlFile());
            sentence.setString(2, newArticle.getProjectName());
            sentence.setString(3, newArticle.getImpactAB());
            sentence.setString(4, newArticle.getEvidenceTitle());
            sentence.setString(5, newArticle.getPublicationDate());
            sentence.setString(6, newArticle.getCountry());
            sentence.setString(7, newArticle.getMagazineName());
            sentence.setDouble(8, newArticle.getIsnn());
            sentence.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
    }

    @Override
    public void updateArticle(Article article, String oldUrlFile) {
        try{
            PreparedStatement sentence = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "UPDATE Article SET urlFile = ?, projectName = ?, impactBA = ?, evidenceTitle = ?," 
                    + "publicationDate = ?, country = ?, magazineName = ?, isnn = ?"
                    + "WHERE urlFile = ?;"
            );
            sentence.setString(1, article.getUrlFile());
            sentence.setString(2, article.getProjectName());
            sentence.setString(3, article.getImpactAB());
            sentence.setString(4, article.getEvidenceTitle());
            sentence.setString(5, article.getPublicationDate());
            sentence.setString(6, article.getCountry());
            sentence.setString(7, article.getMagazineName());
            sentence.setDouble(8, article.getIsnn());
            sentence.setString(9, oldUrlFile);
            sentence.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
    }

    @Override
    public boolean deleteArticlebyURL(String urlFile) {
        boolean deletedArticle = false;
        try{
            PreparedStatement sentenceQuery = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "DELETE FROM Article "
                    + "WHERE urlFile = ? "
                    + "LIMIT 1;"
            );
            sentenceQuery.setString(1, urlFile);
            sentenceQuery.executeUpdate();
            deletedArticle = true;
        }catch(SQLException sqlException){
            Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
        return deletedArticle;
    }
}
