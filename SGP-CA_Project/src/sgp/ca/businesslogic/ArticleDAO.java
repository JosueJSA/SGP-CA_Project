/**
 * @author estef
 * Last modification date format: 23-04-2021
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Article;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Evidence;
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Magazine;

public class ArticleDAO extends EvidenceDAO {
    
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();
    private final MagazineDAO MAGAZINE_DAO = new MagazineDAO();
    
    @Override
    public Evidence getEvidenceByUrl(String urlEvidenceFile) {
        Article article = new Article();
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement senenceQuery = connection.prepareStatement(
                "SELECT * from article WHERE urlFile = ?;"
            );
            senenceQuery.setString(1, urlEvidenceFile);
            ResultSet resultQuery = senenceQuery.executeQuery();
            if(resultQuery.next()){
                article = this.getOutArticleDataFromQuery(resultQuery);
                article.setMagazine(this.getMagazineArticleParticipation(connection, article.getUrlFile()));
                article.setIntegrants(this.getIntegrantArticleParticipation(connection, urlEvidenceFile));
                article.setCollaborators(this.getCollaboratorArticleParticipation(connection, urlEvidenceFile));
                article.setStudents(this.getStudentsArticleParticipation(connection, urlEvidenceFile));
            }
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException ex){
            Logger.getLogger(PrototypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return article;
        }
    }

    @Override
    public void addNewEvidence(Evidence evidence){
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentence = connection.prepareStatement(
                "INSERT INTO Article VALUES (?,?,?,?,?,?,?,?,?,?,?);"
            );
            sentence.setString(1, evidence.getUrlFile());
            sentence.setString(2, evidence.getProjectName());
            sentence.setBoolean(3, evidence.getImpactAB());
            sentence.setString(4, "Artículo");
            sentence.setString(5, evidence.getEvidenceTitle());
            sentence.setString(6, evidence.getRegistrationResponsible());
            sentence.setString(7, evidence.getRegistrationDate());
            sentence.setString(8, evidence.getStudyDegree());
            sentence.setString(9, evidence.getPublicationDate());
            sentence.setString(10, evidence.getCountry());
            sentence.setDouble(11, ((Article)evidence).getIsnn());
            sentence.executeUpdate();
            this.insertIntoArticleMagazine(connection, (Article)evidence);
            this.insertIntoStudentArticle(connection, (Article)evidence);
            this.insertIntoIntegrantArticle(connection, (Article)evidence);
            this.insertIntoCollaboratorArticle(connection, (Article)evidence);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }
    }

    @Override
    public void updateEvidence(Evidence evidence, String oldUrlFile) {
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            this.deleteCollaboratorsFromURLFileArticle(connection, oldUrlFile);
            this.deleteIntegrantsFromURLFileArticle(connection, oldUrlFile);
            this.deleteMagazineFromURLFileArticle(connection, oldUrlFile);
            this.deleteStudensFromURLFileArticle(connection, oldUrlFile);
            PreparedStatement sentence = connection.prepareStatement(
                "UPDATE Article SET urlFile = ?, projectName = ?, impactBA = ?, evidenceType = ?,"
                + " evidenceTitle = ?, registrationResponsible = ?, registrationDate = ?, "
                + "studyDegree = ?, publicationDate = ?, country = ?, isnn = ?"
                + " WHERE urlFile = ?;"
            );
            sentence.setString(1, evidence.getUrlFile());
            sentence.setString(2, evidence.getProjectName());
            sentence.setBoolean(3, evidence.getImpactAB());
            sentence.setString(4, "Artículo");
            sentence.setString(5, evidence.getEvidenceTitle());
            sentence.setString(6, evidence.getRegistrationResponsible());
            sentence.setString(7, evidence.getRegistrationDate());
            sentence.setString(8, evidence.getStudyDegree());
            sentence.setString(9, evidence.getPublicationDate());
            sentence.setString(10, evidence.getCountry());
            sentence.setDouble(11, ((Article)evidence).getIsnn());
            sentence.setString(12, oldUrlFile);
            sentence.executeUpdate();
            this.insertIntoArticleMagazine(connection, (Article)evidence);
            this.insertIntoCollaboratorArticle(connection, (Article)evidence);
            this.insertIntoIntegrantArticle(connection, (Article)evidence);
            this.insertIntoStudentArticle(connection, (Article)evidence);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }
    }

    @Override
    public void deleteEvidenceByUrl(String urlEvidenceFile){
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            this.deleteStudensFromURLFileArticle(connection, urlEvidenceFile);
            this.deleteIntegrantsFromURLFileArticle(connection, urlEvidenceFile);
            this.deleteCollaboratorsFromURLFileArticle(connection, urlEvidenceFile);
            this.deleteMagazineFromURLFileArticle(connection, urlEvidenceFile);
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM Article WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlEvidenceFile);
            sentenceQuery.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }
    }
    
    public void deleteStudensFromURLFileArticle(Connection connection, String urlFileArticle){
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM ArticleStudent WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFileArticle);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteIntegrantsFromURLFileArticle(Connection connection, String urlFileArticle){
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM IntegrantArticle WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFileArticle);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteCollaboratorsFromURLFileArticle(Connection connection, String urlFileArticle){
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM CollaborateArticle WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFileArticle);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteMagazineFromURLFileArticle(Connection connection, String urlFile){
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM ArticleMagazine WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFile);
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void insertIntoArticleMagazine(Connection connection, Article article){
        try{
            PreparedStatement sentence = connection.prepareStatement(
                "INSERT INTO ArticleMagazine VALUES(?,?);"
            );
            sentence.setString(1, article.getUrlFile());
            sentence.setString(2, article.getMagazine().getMagazineName());
            sentence.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                connection.close();
                Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void insertIntoStudentArticle(Connection connection, Article article){
        article.getStudents().forEach( student -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO ArticleStudent VALUES(?,?);"
                );
                sentenceQuery.setString(1, article.getUrlFile());
                sentenceQuery.setString(2, student);
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void insertIntoIntegrantArticle(Connection connection, Article article){
        article.getIntegrants().forEach( integrant -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO IntegrantArticle VALUES(?,?);"
                );
                sentenceQuery.setString(1, integrant.getRfc());
                sentenceQuery.setString(2, article.getUrlFile());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public void insertIntoCollaboratorArticle(Connection connection, Article article){
        article.getCollaborators().forEach( collaborator -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO CollaborateArticle VALUES(?,?);"
                );
                sentenceQuery.setString(1, collaborator.getRfc());
                sentenceQuery.setString(2, article.getUrlFile());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(Article.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(GoalDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    public Article getOutArticleDataFromQuery(ResultSet resultArticleQuery){
        Article article = new Article();
        try{
            article = new Article(
                resultArticleQuery.getDouble("isnn"),
                resultArticleQuery.getString("urlFile"),
                resultArticleQuery.getString("projectName"),
                resultArticleQuery.getString("evidenceTitle"),
                resultArticleQuery.getString("country"),
                resultArticleQuery.getString("publicationDate"),
                resultArticleQuery.getBoolean("impactBA"),
                resultArticleQuery.getString("registrationDate"),
                resultArticleQuery.getString("registrationResponsible"),
                resultArticleQuery.getString("studyDegree"),
                resultArticleQuery.getString("evidenceType")
            );
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return article;
        }
    }
    
    public List<Integrant> getIntegrantArticleParticipation(Connection connection, String urlFileArticle){
        List<Integrant> integrants = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT i.fullName FROM Integrant i, IntegrantArticle ia WHERE ia.rfc = i.rfc AND urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFileArticle);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Integrant integrant = new Integrant();
                integrant.setFullName(resultQuery.getString("fullName"));
                integrants.add(integrant);
            }
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return integrants;
        }
    }
    
    public List<Collaborator> getCollaboratorArticleParticipation(Connection connection, String urlFileArticle){
        List<Collaborator> collaborators = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT c.fullName FROM CollaborateArticle ca, Collaborator c WHERE ca.rfc = c.rfc AND urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFileArticle);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Collaborator collaborator = new Collaborator();
                collaborator.setFullName(resultQuery.getString("fullName"));
                collaborators.add(collaborator);
            }
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return collaborators;
        }
    }
    
    public List<String> getStudentsArticleParticipation(Connection connection, String urlFileArticle){
        List<String> students = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT * FROM ArticleStudent WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFileArticle);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                students.add(resultQuery.getString("student"));
            }
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return students;
        }
    }
    
    public Magazine getMagazineArticleParticipation(Connection connection, String urlFileArticle){
        Magazine magazine = new Magazine();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT * FROM ArticleMagazine WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFileArticle);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                magazine = MAGAZINE_DAO.getMagazineByName(resultQuery.getString("magazineName"));
            }
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return magazine;
        }
    }
    
}
