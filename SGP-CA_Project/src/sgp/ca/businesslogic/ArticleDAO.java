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
import sgp.ca.domain.Integrant;
import sgp.ca.domain.Magazine;

public class ArticleDAO implements IArticleDAO{
    private final ConnectionDatabase connectionDataBase = new ConnectionDatabase();
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();
    private final MagazineDAO MAGAZINE_DAO = new MagazineDAO();
    
    @Override
    public List<Article> getArticleByIntegrantRFC(String rfc, String limitDate) {
        List<Article> articles = new ArrayList<>();
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT * FROM Article a, IntegrantArticle i WHERE  a.urlFile = i.urlFile AND i.rfc = ? "
                + "AND cast(registrationDate as date) < cast( ? as date) order by registrationDate DESC LIMIT 10;"
            );
            sentenceQuery.setString(1, rfc);
            sentenceQuery.setString(2, limitDate);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Article article = this.getOutArticleDataFromQuery(resultQuery);
                article.setIntegrants(this.getIntegrantArticleParticipation(connection, article.getUrlFile()));
                article.setCollaborators(this.getCollaboratorArticleParticipation(connection, article.getUrlFile()));
                article.setStudents(this.getStudentsArticleParticipation(connection, article.getUrlFile()));
                article.setMagazine(this.getMagazineArticleParticipation(connection, article.getUrlFile()));
                articles.add(article);
            }
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return articles;
        }
    }

    @Override
    public List<Article> getArticleByTitle(String title, String limitDate) {
        List<Article> articles = new ArrayList<>();
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT * FROM Article WHERE evidenceTitle = ? "
                + "AND cast(registrationDate as date) < cast( ? as date) order by registrationDate DESC LIMIT 10;"
            );
            sentenceQuery.setString(1, title);
            sentenceQuery.setString(2, limitDate);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Article article = this.getOutArticleDataFromQuery(resultQuery);
                article.setIntegrants(this.getIntegrantArticleParticipation(connection, article.getUrlFile()));
                article.setCollaborators(this.getCollaboratorArticleParticipation(connection, article.getUrlFile()));
                article.setStudents(this.getStudentsArticleParticipation(connection, article.getUrlFile()));
                article.setMagazine(this.getMagazineArticleParticipation(connection, article.getUrlFile()));
                articles.add(article);
            }
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return articles;
        }
    }

    @Override
    public List<Article> getArticlesByStudent(String studentName, String limitDate){
        List<Article> articles = new ArrayList<>();
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT * FROM Article a, ArticleStudent ast WHERE  a.urlFile = ast.urlFile AND ast.student = ? "
                + "AND cast(registrationDate as date) < cast( ? as date) order by registrationDate DESC LIMIT 10;"
            );
            sentenceQuery.setString(1, studentName);
            sentenceQuery.setString(2, limitDate);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Article article = this.getOutArticleDataFromQuery(resultQuery);
                article.setIntegrants(this.getIntegrantArticleParticipation(connection, article.getUrlFile()));
                article.setCollaborators(this.getCollaboratorArticleParticipation(connection, article.getUrlFile()));
                article.setStudents(this.getStudentsArticleParticipation(connection, article.getUrlFile()));
                article.setMagazine(this.getMagazineArticleParticipation(connection, article.getUrlFile()));
                articles.add(article);
            }
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException ex){
            Logger.getLogger(ArticleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
            return articles;
        }
    }

    @Override
    public void addArticle(Article newArticle){
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentence = connection.prepareStatement(
                "INSERT INTO Article VALUES (?,?,?,?,?,?,?,?,?,?,?);"
            );
            sentence.setString(1, newArticle.getUrlFile());
            sentence.setString(2, newArticle.getProjectName());
            sentence.setBoolean(3, newArticle.getImpactAB());
            sentence.setString(4, newArticle.getBodyAcademyKey());
            sentence.setString(5, newArticle.getEvidenceTitle());
            sentence.setString(6, newArticle.getRegistrationResponsible());
            sentence.setString(7, newArticle.getRegistrationDate());
            sentence.setString(8, newArticle.getStudyDegree());
            sentence.setString(9, newArticle.getPublicationDate());
            sentence.setString(10, newArticle.getCountry());
            sentence.setDouble(11, newArticle.getIsnn());
            sentence.executeUpdate();
            this.insertIntoArticleMagazine(connection, newArticle);
            this.insertIntoStudentArticle(connection, newArticle);
            this.insertIntoIntegrantArticle(connection, newArticle);
            this.insertIntoCollaboratorArticle(connection, newArticle);
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
    public void updateArticle(Article article, String oldUrlFile) {
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            this.deleteCollaboratorsFromURLFileArticle(connection, oldUrlFile);
            this.deleteIntegrantsFromURLFileArticle(connection, oldUrlFile);
            this.deleteMagazineFromURLFileArticle(connection, oldUrlFile);
            this.deleteStudensFromURLFileArticle(connection, oldUrlFile);
            PreparedStatement sentence = connection.prepareStatement(
                "UPDATE Article SET urlFile = ?, projectName = ?, impactBA = ?, bodyAcademyKey = ?,"
                + " evidenceTitle = ?, registrationResponsible = ?, registrationDate = ?, "
                + "studyDegree = ?, publicationDate = ?, country = ?, isnn = ?"
                + " WHERE urlFile = ?;"
            );
            sentence.setString(1, article.getUrlFile());
            sentence.setString(2, article.getProjectName());
            sentence.setBoolean(3, article.getImpactAB());
            sentence.setString(4, article.getBodyAcademyKey());
            sentence.setString(5, article.getEvidenceTitle());
            sentence.setString(6, article.getRegistrationResponsible());
            sentence.setString(7, article.getRegistrationDate());
            sentence.setString(8, article.getStudyDegree());
            sentence.setString(9, article.getPublicationDate());
            sentence.setString(10, article.getCountry());
            sentence.setDouble(11, article.getIsnn());
            sentence.setString(12, oldUrlFile);
            sentence.executeUpdate();
            this.insertIntoArticleMagazine(connection, article);
            this.insertIntoCollaboratorArticle(connection, article);
            this.insertIntoIntegrantArticle(connection, article);
            this.insertIntoStudentArticle(connection, article);
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
    public void deleteArticle(String urlFileArticle){
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            this.deleteStudensFromURLFileArticle(connection, urlFileArticle);
            this.deleteIntegrantsFromURLFileArticle(connection, urlFileArticle);
            this.deleteCollaboratorsFromURLFileArticle(connection, urlFileArticle);
            this.deleteMagazineFromURLFileArticle(connection, urlFileArticle);
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM Article WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFileArticle);
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
                resultArticleQuery.getString("bodyAcademyKey")
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
            connectionDataBase.closeConnection();
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
            connectionDataBase.closeConnection();
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
            connectionDataBase.closeConnection();
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
            connectionDataBase.closeConnection();
            return magazine;
        }
    }
}
