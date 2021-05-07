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
import sgp.ca.domain.Meeting;

public class MeetingDAO implements IMeetingDAO{
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();
    private MeetingAgendaDAO meetingAgendaDAO = new MeetingAgendaDAO();
    private AgreementDAO agreementDAO = new AgreementDAO();
    private CommentDAO commentDAO = new CommentDAO();
    private AssistantRolDAO assisntantRolDAO = new AssistantRolDAO();

    @Override
    public List<Meeting> getAllMeetings() {
        List<Meeting> meetingsList = new ArrayList();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM Meeting;"
            );
            
            ResultSet queryResult = sentenceQuery.executeQuery();
            while(queryResult.next()){
                Meeting meeting = new Meeting (
                queryResult.getDate("meetingDate").toString(),
                queryResult.getTime("meetingTime").toString(),
                queryResult.getString("meetingProject"),
                queryResult.getDate("meetingRegistrationDate").toString(),
                queryResult.getString("statusMeeting"),
                queryResult.getString("meetingNote"),
                queryResult.getString("meetingPending")
                );
                meetingsList.add(meeting);
            }
        }catch(SQLException sqlException){
            Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
            return meetingsList;
        }
    }

    @Override
    public Meeting getMeeting(String meetingDate, String meetingTime) {
        Meeting meeting = new Meeting();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM Meeting WHERE meetingDate = ? AND meetingTime = ?;"
            );
            
            sentenceQuery.setString(1, meetingDate);
            sentenceQuery.setString(2, meetingTime);
            ResultSet queryResult = sentenceQuery.executeQuery();
            if(queryResult.next()){meeting = new Meeting (
                queryResult.getDate("meetingDate").toString(),
                queryResult.getTime("meetingTime").toString(),
                queryResult.getString("meetingProject"),
                queryResult.getDate("meetingRegistrationDate").toString(),
                queryResult.getString("statusMeeting"),
                queryResult.getString("meetingNote"),
                queryResult.getString("meetingPending")
            );}
            meeting.setMeetingAgenda(meetingAgendaDAO.getMeetingAgendaByMeeting(meetingDate, meetingTime));
            meeting.setAgreements(agreementDAO.getAgreementListByMeeting(meetingDate, meetingTime));
            meeting.setComments(commentDAO.getCommentsByMeeting(meetingDate, meetingTime));
            meeting.setAssistantsRole(assisntantRolDAO.getAssistantsRolByMeeting(meetingDate, meetingTime));
        }catch(SQLException sqlException){
            Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
            return meeting;
        }
    }

    @Override
    public List<Meeting> getMeetingsbyDate(String meetingDate) {
        List<Meeting> meetingsList = new ArrayList();
        try{
            PreparedStatement sentenceQuery = CONNECTION.getConnectionDatabase().prepareStatement(
                "SELECT * FROM Meeting WHERE meetingDate = ?;"
            );
            sentenceQuery.setString(1, meetingDate);
            ResultSet queryResult = sentenceQuery.executeQuery();
            while(queryResult.next()){
                Meeting meeting = new Meeting (
                queryResult.getDate("meetingDate").toString(),
                queryResult.getTime("meetingTime").toString(),
                queryResult.getString("meetingProject"),
                queryResult.getDate("meetingRegistrationDate").toString(),
                queryResult.getString("statusMeeting"),
                queryResult.getString("meetingNote"),
                queryResult.getString("meetingPending")
                );
                meetingsList.add(meeting);
            }
        }catch(SQLException sqlException){
            Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, sqlException);
        }finally{
            CONNECTION.closeConnection();
            return meetingsList;
        }
    }

    @Override
    public void addMeeting(Meeting newMeeting) {
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "INSERT INTO Meeting (meetingDate, meetingTime, meetingProject, "
                + "meetingRegistrationDate, statusMeeting, meetingNote, "
                + "meetingPending) VALUES(?,?,?,?,?,?,?);"
            );
            sentenceQuery.setString(1, newMeeting.getMeetingDate());
            sentenceQuery.setString(2, newMeeting.getMeetingTime());
            sentenceQuery.setString(3, newMeeting.getMeetingProject());
            sentenceQuery.setString(4, newMeeting.getMeetingRegistrationDate());
            sentenceQuery.setString(5, newMeeting.getStatusMeeting());
            sentenceQuery.setString(6, newMeeting.getMeetingNote());
            sentenceQuery.setString(7, newMeeting.getMeetingPending());
            sentenceQuery.executeUpdate();
            meetingAgendaDAO.addMeetingAgenda(connection, newMeeting);
            agreementDAO.addAgreements(connection, newMeeting);
            commentDAO.addComment(connection, newMeeting);
            assisntantRolDAO.addAssistantRol(connection, newMeeting);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }
    }

    @Override
    public void updateMeeting(Meeting meeting, Meeting oldMeeting) {
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            this.deleteMeetingAgenda(connection, oldMeeting);
            this.deleteAgreement(connection, oldMeeting);
            this.deleteComment(connection, oldMeeting);
            this.deleteAssistantRol(connection, oldMeeting);
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "UPDATE Meeting SET meetingDate = ?, meetingTime = ?, meetingProject = ?, "
                + "meetingRegistrationDate = ?, statusMeeting = ?, meetingNote = ?, "
                + "meetingPending = ? WHERE meetingDate = ? AND meetingTime = ?;"
            );
            sentenceQuery.setString(1, meeting.getMeetingDate());
            sentenceQuery.setString(2, meeting.getMeetingTime());
            sentenceQuery.setString(3, meeting.getMeetingProject());
            sentenceQuery.setString(4, meeting.getMeetingRegistrationDate());
            sentenceQuery.setString(5, meeting.getStatusMeeting());
            sentenceQuery.setString(6, meeting.getMeetingNote());
            sentenceQuery.setString(7, meeting.getMeetingPending());
            sentenceQuery.setString(8, oldMeeting.getMeetingDate());
            sentenceQuery.setString(9, oldMeeting.getMeetingTime());
            sentenceQuery.executeUpdate();
            meetingAgendaDAO.addMeetingAgenda(connection, meeting);
            agreementDAO.addAgreements(connection, meeting);
            commentDAO.addComment(connection, meeting);
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
        try{
            this.deleteMeetingAgenda(connection, meeting);
            this.deleteAgreement(connection, meeting);
            this.deleteComment(connection, meeting);
            this.deleteAssistantRol(connection, meeting);
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM Meeting WHERE meetingDate = ? AND meetingTime = ?;"
            );
            sentenceQuery.setString(1, meeting.getMeetingDate());
            sentenceQuery.setString(2, meeting.getMeetingTime());
            sentenceQuery.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }finally{
            CONNECTION.closeConnection();
        }
    }
    
    @Override
    public void deleteMeetingAgenda(Connection connection, Meeting meeting) {
        try{
            meetingAgendaDAO.deleteTopic(connection, meeting.getMeetingAgenda());
            meetingAgendaDAO.deletePrerequisite(connection, meeting.getMeetingAgenda());
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM MeetingAgenda WHERE meetingDate = ? AND meetingTime = ?;"
            );
            sentenceQuery.setString(1, meeting.getMeetingDate());
            sentenceQuery.setString(2, meeting.getMeetingTime());
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteAgreement(Connection connection, Meeting meeting) {
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM Agreement WHERE meetingDate = ? AND meetingTime = ?;"
            );
            sentenceQuery.setString(1, meeting.getMeetingDate());
            sentenceQuery.setString(2, meeting.getMeetingTime());
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteComment(Connection connection, Meeting meeting) {
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM Comment WHERE meetingDate = ? AND meetingTime = ?;"
            );
            sentenceQuery.setString(1, meeting.getMeetingDate());
            sentenceQuery.setString(2, meeting.getMeetingTime());
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteAssistantRol(Connection connection, Meeting meeting) {
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "DELETE FROM IntegrantMeeting WHERE meetingDate = ? AND meetingTime = ?;"
            );
            sentenceQuery.setString(1, meeting.getMeetingDate());
            sentenceQuery.setString(2, meeting.getMeetingTime());
            sentenceQuery.executeUpdate();
        }catch(SQLException sqlException){
            try{
                connection.rollback();
                Logger.getLogger(Meeting.class.getName()).log(Level.SEVERE, null, sqlException);
            }catch(SQLException ex){
                Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
