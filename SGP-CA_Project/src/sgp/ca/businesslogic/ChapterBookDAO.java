/**
 * @author estef
 * Last modification date format: 19-04-2021
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
import sgp.ca.domain.ChapterBook;

public class ChapterBookDAO implements IChapterBookDAO{
    private final ConnectionDatabase connectionDataBase = new ConnectionDatabase();
    private Connection connection;
    private Statement consult;
    private ResultSet results;

    @Override
    public List<ChapterBook> returnChapterBooks() {
        List<ChapterBook>chapterBookList = new ArrayList<>();
        try{
            connection = connectionDataBase.getConnectionDatabase();
            consult = connection.createStatement();
            results = consult.executeQuery("Select * from ChapterBook");
            while(results.next()){
                ChapterBook chapterBook = new ChapterBook();
                chapterBook.setUrlFile(results.getString("urlFile"));
                chapterBook.setProjectName(results.getNString("projectName"));
                chapterBook.setImpactAB(results.getNString("impactBA"));
                chapterBook.setEvidenceTitle(results.getNString("evidenceTitle"));
                chapterBook.setPublicationDate(results.getString("publicationDate"));
                chapterBook.setCountry(results.getNString("country"));
                chapterBook.setBookName(results.getNString("bookName"));
                chapterBook.setPagesNumber(results.getNString("pages-number"));
                chapterBookList.add(chapterBook);
            }
        
        }catch(SQLException ex){
            Logger.getLogger(ChapterBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            connectionDataBase.closeConnection();
        }
        return chapterBookList;
    }

    @Override
    public ChapterBook getChapterBookbyURL(String urlFile) {
        ChapterBook chapterBook = null;
        try(Connection connection = connectionDataBase.getConnectionDatabase()){
            String query = "Select * from ChapterBook where urlFile = ? ";
            PreparedStatement sentence = connection.prepareStatement(query);
            sentence.setString(1, urlFile);
            results = sentence.executeQuery();
            
            while(results.next()){
                chapterBook = new ChapterBook();
                chapterBook.setUrlFile(results.getString("urlFile"));
                chapterBook.setProjectName(results.getNString("projectName"));
                chapterBook.setImpactAB(results.getNString("impactBA"));
                chapterBook.setEvidenceTitle(results.getNString("evidenceTitle"));
                chapterBook.setPublicationDate(results.getString("publicationDate"));
                chapterBook.setCountry(results.getNString("county"));
                chapterBook.setBookName(results.getNString("bookName"));
                chapterBook.setPagesNumber(results.getNString("pages-number"));
            }
                
        }catch(SQLException ex){
            Logger.getLogger(ChapterBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return chapterBook;
    }

    @Override
    public void addChapterBook(ChapterBook newChapterBook) {
        try{
            PreparedStatement sentence = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "INSERT INTO ChapterBook VALUES (?,?,?,?,?,?,?,?);"
            );
            sentence.setString(1, newChapterBook.getUrlFile());
            sentence.setString(2, newChapterBook.getProjectName());
            sentence.setString(3, newChapterBook.getImpactAB());
            sentence.setString(4, newChapterBook.getEvidenceTitle());
            sentence.setString(5, newChapterBook.getPublicationDate());
            sentence.setString(6, newChapterBook.getCountry());
            sentence.setString(7, newChapterBook.getBookName());
            sentence.setString(8, newChapterBook.getPagesNumber());
            sentence.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(ChapterBook.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
    }

    @Override
    public void updateChapterBook(ChapterBook chapterBook, String oldUrlFile) {
        try{
            PreparedStatement sentence = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "UPDATE ChapterBook SET urlFile = ?, projectName = ?, impactBA = ?, evidenceTitle = ?," 
                    + "publicationDate = ?, country = ?, bookName = ?, pages-number = ?"
                    + "WHERE urlFile = ?;"
            );
            sentence.setString(1, chapterBook.getUrlFile());
            sentence.setString(2, chapterBook.getProjectName());
            sentence.setString(3, chapterBook.getImpactAB());
            sentence.setString(4, chapterBook.getEvidenceTitle());
            sentence.setString(5, chapterBook.getPublicationDate());
            sentence.setString(6, chapterBook.getCountry());
            sentence.setString(7, chapterBook.getBookName());
            sentence.setString(8, chapterBook.getPagesNumber());
            sentence.setString(9, oldUrlFile);
            sentence.executeUpdate();
        }catch(SQLException sqlException){
            Logger.getLogger(ChapterBook.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
    }

    @Override
    public boolean deleteChapterBookbyURL(String urlFile) {
        boolean deletedChapterBook = false;
        try{
            PreparedStatement sentenceQuery = connectionDataBase.getConnectionDatabase().prepareStatement(
                    "DELETE FROM ChapterBook "
                    + "WHERE urlFile = ? "
                    + "LIMIT 1;"
            );
            sentenceQuery.setString(1, urlFile);
            sentenceQuery.executeUpdate();
            deletedChapterBook = true;
        }catch(SQLException sqlException){
            Logger.getLogger(ChapterBook.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            connectionDataBase.closeConnection();
        }
        return deletedChapterBook;
    }
    
}
