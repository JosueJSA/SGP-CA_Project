/**
 * @author estef
 * Last modification date format: 20-04-2021
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.ArticleMagazine;

public class ArticleMagazineDAO implements IArticleMagazineDAO{
    private final ConnectionDatabase connectionDataBase = new ConnectionDatabase();
    private Connection connection;
    private Statement consult;
    private ResultSet results;

    @Override
    public List<ArticleMagazine> returnArticleMagazines() {
        List<ArticleMagazine>articleMagazineList = new ArrayList<>();
        try{
            connection = connectionDataBase.getConnectionDatabase();
            consult = connection.createStatement();
            results = consult.executeQuery("Select * from ArticleMagazine");
            while(results.next()){
                ArticleMagazine articleMagazine = new ArticleMagazine();
                articleMagazine.setMagazineName(results.getNString("magazineName"));
                articleMagazine.setIndex(results.getNString("index"));
                articleMagazine.setMagazinePublisher(results.getNString("magazinePublisher"));
                articleMagazineList.add(articleMagazine);
            }
        
        }catch(SQLException ex){
            Logger.getLogger(ArticleMagazineDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            connectionDataBase.closeConnection();
        }
        return articleMagazineList;
    }

    @Override
    public ArticleMagazine getArticleMagazinebyMagazineName(String magazineName) {
        ArticleMagazine articleMagazine = null;
        try(Connection connection = connectionDataBase.getConnectionDatabase()){
            String query = "Select * from ArticleMagazine where magazineName = ? ";
            PreparedStatement sentence = connection.prepareStatement(query);
            sentence.setString(1, magazineName);
            results = sentence.executeQuery();
            
            while(results.next()){
                articleMagazine = new ArticleMagazine();
                articleMagazine.setMagazineName(results.getNString("magazineName"));
                articleMagazine.setIndex(results.getNString("index"));
                articleMagazine.setMagazinePublisher(results.getNString("magazinePublisher"));
            }
                
        }catch(SQLException ex){
            Logger.getLogger(ArticleMagazineDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return articleMagazine;
    }

    @Override
    public void addArticleMagazine(ArticleMagazine newMagazineName) {
         try{
            PreparedStatement sentence = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "INSERT INTO ArticleMagazine VALUES (?,?,?);"
            );
            sentence.setString(1, newMagazineName.getMagazineName());
            sentence.setString(2, newMagazineName.getIndex());
            sentence.setString(3, newMagazineName.getMagazinePublisher());
            sentence.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(ArticleMagazine.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
    }

    @Override
    public void updateArticleMagazine(ArticleMagazine magazineName, String oldMagazineName) {
        try{
            PreparedStatement sentence = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "UPDATE ArticleMagazine SET magazineName = ?, index = ?, magazinePublisher = ?"
                    + "WHERE magazineName = ?;"
            );
            sentence.setString(1, magazineName.getMagazineName());
            sentence.setString(2, magazineName.getIndex());
            sentence.setString(3, magazineName.getMagazinePublisher());
            sentence.setString(4, oldMagazineName);
            sentence.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(ArticleMagazine.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
    }

    @Override
    public boolean deleteArticleMagazinebyMagazineName(String magazineName) {
        boolean deletedMagazinebyMagazine = false;
        try{
            PreparedStatement sentenceQuery = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "DELETE FROM ArticleMagazine "
                    + "WHERE magazineName = ? "
                    + "LIMIT 1;"
            );
            sentenceQuery.setString(1, magazineName);
            sentenceQuery.executeUpdate();
            deletedMagazinebyMagazine = true;
        }catch(SQLException sqlException){
            Logger.getLogger(ArticleMagazine.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
        return deletedMagazinebyMagazine;
    }
    
    
}
