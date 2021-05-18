/**
 * @author estef
 * Last modification date format: 16-05-2021
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
import sgp.ca.domain.Book;
import sgp.ca.domain.ChapterBook;
import sgp.ca.domain.Collaborator;
import sgp.ca.domain.Integrant;

public class ChapterBookDAO implements IChapterBookDAO{
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();

    @Override
    public void addChapterBooks(Connection connection, Book book) {
        book.getChapterBooks().forEach(chapterBook -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO ChapterBook (urlFile, chapterBookTitle, "
                    + "registrationDate, registrationResponsible, pages-number, "
                    + "urlFileBook) VALUES(?,?,?,?,?,?);"
                );
                sentenceQuery.setString(1, chapterBook.getUrlFile());
                sentenceQuery.setString(2, chapterBook.getChapterBookTitle());
                sentenceQuery.setString(3, chapterBook.getRegistrationDate());
                sentenceQuery.setString(4, chapterBook.getRegistrationResponsible());
                sentenceQuery.setString(5, chapterBook.getPageNumberRange());
                sentenceQuery.setString(6, book.getUrlFile());
                sentenceQuery.executeUpdate();
                this.insertIntoChapterbookStudent(connection, chapterBook);
                this.insertIntoIntegrantChapterbook(connection, chapterBook);
                this.insertIntoCollaborateChapterbook(connection, chapterBook);
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public List<ChapterBook> getChapterBooksListByBook(Connection connection, String urlFileBook) {
        List<ChapterBook> chapterBooksList = new ArrayList();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM ChapterBook WHERE urlFileBook = ?;"
            );
            sentenceQuery.setString(1, urlFileBook);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                ChapterBook chapterBook = new ChapterBook(
                    resultQuery.getString("urlFile"),
                    resultQuery.getString("chapterBookTitle"),
                    resultQuery.getDate("registrationDate").toString(),
                    resultQuery.getString("registrationResponsible"),
                    resultQuery.getString("pages-number")
                );
                chapterBook.setStudents(this.getStudentNamesChapterBookParticipation(connection, chapterBook.getUrlFile()));
                chapterBook.setIntegrants(this.getIntegrantsChapterBookParticipation(connection, chapterBook.getUrlFile()));
                chapterBook.setCollaborators(this.getCollaboratorsChapterBookParticipation(connection, chapterBook.getUrlFile()));
                chapterBooksList.add(chapterBook);
            }
        }catch(SQLException sqlException){
            Logger.getLogger(ChapterBookDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            return chapterBooksList;
        }
    }

    @Override
    public void deleteStudentsFromChapterBook(Connection connection, List<ChapterBook> chapterBooks) {
        chapterBooks.forEach(chapterBook -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "DELETE FROM ChapterbookStudent WHERE urlFile = ?;"
                );
                sentenceQuery.setString(1, chapterBook.getUrlFile());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(ChapterBookDAO.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(ChapterBookDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public void deleteIntegrantsFromChapterBook(Connection connection, List<ChapterBook> chapterBooks) {
        chapterBooks.forEach(chapterBook -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "DELETE FROM IntegrantChapterbook WHERE urlFile = ?;"
                );
                sentenceQuery.setString(1, chapterBook.getUrlFile());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(ChapterBookDAO.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(ChapterBookDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public void deleteCollaboratorsFromChapterBook(Connection connection, List<ChapterBook> chapterBooks) {
        chapterBooks.forEach(chapterBook -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "DELETE FROM CollaborateChapterbook WHERE urlFile = ?;"
                );
                sentenceQuery.setString(1, chapterBook.getUrlFile());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(ChapterBookDAO.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(ChapterBookDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void insertIntoChapterbookStudent(Connection connection, ChapterBook chapterBook){
        chapterBook.getStudents().forEach( student -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO ChapterbookStudent VALUES(?,?);"
                );
                sentenceQuery.setString(1, chapterBook.getUrlFile());
                sentenceQuery.setString(2, student);
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void insertIntoIntegrantChapterbook(Connection connection, ChapterBook chapterBook){
        chapterBook.getIntegrants().forEach( integrant -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO IntegrantChapterbook VALUES(?,?);"
                );
                sentenceQuery.setString(1, integrant.getRfc());
                sentenceQuery.setString(2, chapterBook.getUrlFile());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void insertIntoCollaborateChapterbook(Connection connection, ChapterBook chapterBook){
        chapterBook.getCollaborators().forEach( collaborator -> {
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO CollaborateChapterbook VALUES(?,?);"
                );
                sentenceQuery.setString(1, collaborator.getRfc());
                sentenceQuery.setString(2, chapterBook.getUrlFile());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    connection.close();
                    Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private List<String> getStudentNamesChapterBookParticipation(Connection connection, String urlFileChapterBook){
        List<String> students = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT * FROM ChapterbookStudent WHERE urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFileChapterBook);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                students.add(resultQuery.getString("student"));
            }
        }catch(SQLException ex){
            Logger.getLogger(ChapterBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return students;
        }
    }
    
    private List<Integrant> getIntegrantsChapterBookParticipation(Connection connection, String urlFileChapterBook){
        List<Integrant> integrants = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT i.fullName FROM Integrant i, IntegrantChapterbook ia WHERE ia.rfc = i.rfc AND urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFileChapterBook);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Integrant integrant = new Integrant();
                integrant.setFullName(resultQuery.getString("fullName"));
                integrants.add(integrant);
            }
        }catch(SQLException ex){
            Logger.getLogger(ChapterBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return integrants;
        }
    }
    
    private List<Collaborator> getCollaboratorsChapterBookParticipation(Connection connection, String urlFileChapterBook){
        List<Collaborator> collaborators = new ArrayList<>();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "SELECT c.fullName FROM CollaborateChapterbook ca, Collaborator c WHERE ca.rfc = c.rfc AND urlFile = ?;"
            );
            sentenceQuery.setString(1, urlFileChapterBook);
            ResultSet resultQuery = sentenceQuery.executeQuery();
            while(resultQuery.next()){
                Collaborator collaborator = new Collaborator();
                collaborator.setFullName(resultQuery.getString("fullName"));
                collaborators.add(collaborator);
            }
        }catch(SQLException ex){
            Logger.getLogger(ChapterBookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            return collaborators;
        }
    }
}
