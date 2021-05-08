/**
 * @author estef
 * Last modification date format: 06-05-2021
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
import sgp.ca.domain.Comment;
import sgp.ca.domain.Meeting;

public class CommentDAO implements ICommentDAO {
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();

    @Override
    public void addComment(Connection connection, Meeting meeting) {
        meeting.getComments().forEach(comment ->{
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                        "INSERT INTO Comment (meetingDate, meetingTime, "
                        + "commentDescription, commentator, commentTime, commentDate) "
                        + "VALUES (?, ?, ?, ?, ?, ?);"
                );
                sentenceQuery.setString(1, meeting.getMeetingDate());
                sentenceQuery.setString(2, meeting.getMeetingTime());
                sentenceQuery.setString(3, comment.getCommentDescription());
                sentenceQuery.setString(4, comment.getCommentator());
                sentenceQuery.setString(5, comment.getCommentTime());
                sentenceQuery.setString(6, comment.getCommentDate());
                sentenceQuery.executeUpdate();
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public List<Comment> getCommentsByMeeting(String meetingDate, String meetingTime) {
        List<Comment> commentList = new ArrayList();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM Comment WHERE meetingDate = ? AND meetingTime = ?;"
            );
            sentenceQuery.setString(1, meetingDate);
            sentenceQuery.setString(2, meetingTime);
            ResultSet queryResult = sentenceQuery.executeQuery();
            while(queryResult.next()){commentList.add(new Comment(
                queryResult.getInt("commentKey"),
                queryResult.getString("commentDescription"),
                queryResult.getString("commentator"),
                queryResult.getTime("commentTime").toString(),
                queryResult.getDate("commentDate").toString()
            ));}
        }catch(SQLException sqlException){
            Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            return commentList;
        }
    } 
}
