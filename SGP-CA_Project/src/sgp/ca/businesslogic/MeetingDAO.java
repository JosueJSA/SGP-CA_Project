/**
 * @author estef
 * Last modification date format: 29-04-2021
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
            meeting.setMeetingAgenda(meetingAgendaDAO.getMeetingAgendaByMeeting(meeting.getMeetingDate(), meeting.getMeetingTime()));
            meeting.setAgreements(agreementDAO.getAgreementListByMeeting(meeting.getMeetingDate(), meeting.getMeetingTime()));
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
        Connection connection = CONNECTION.getConnectionDatabase();
        try{
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "INSERT INTO Meeting (meetingDate, meetingTime, meetingAgendaKey, meetingProject, meetingRegistrationDate, statusMeeting, meetingNote, meetingPending) VALUES(?,?,?,?,?,?,?,?);"
            );
            sentenceQuery.setString(1, newMeeting.getMeetingDate());
            sentenceQuery.setString(2, newMeeting.getMeetingTime());
            sentenceQuery.setInt(3, newMeeting.getMeetingAgenda().getMeetingAgendaKey());
            sentenceQuery.setString(4, newMeeting.getMeetingProject());
            sentenceQuery.setString(5, newMeeting.getMeetingRegistrationDate());
            sentenceQuery.setString(6, newMeeting.getStatusMeeting());
            sentenceQuery.setString(7, newMeeting.getMeetingNote());
            sentenceQuery.setString(8, newMeeting.getNeetingPending());
            sentenceQuery.executeUpdate();
            meetingAgendaDAO.addMeetingAgenda(connection, newMeeting);
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
            this.deleteAgreement(connection, oldMeeting);
            PreparedStatement sentenceQuery = connection.prepareStatement(
                "UPDATE Meeting SET meetingDate = ?, meetingTime = ?, meetingAgendaKey = ?, "
                + "meetingProject = ?, meetingRegistrationDate = ?, statusMeeting = ?, "
                + "meetingNote = ?, meetingPending = ? WHERE meetingDate = ? AND meetingTime = ?;"
            );
            sentenceQuery.setString(1, meeting.getMeetingDate());
            sentenceQuery.setString(2, meeting.getMeetingTime());
            sentenceQuery.setInt(3, meeting.getMeetingAgenda().getMeetingAgendaKey());
            sentenceQuery.setString(4, meeting.getMeetingProject());
            sentenceQuery.setString(5, meeting.getMeetingRegistrationDate());
            sentenceQuery.setString(6, meeting.getStatusMeeting());
            sentenceQuery.setString(7, meeting.getMeetingNote());
            sentenceQuery.setString(8, meeting.getNeetingPending());
            sentenceQuery.setString(9, oldMeeting.getMeetingDate());
            sentenceQuery.setString(10, oldMeeting.getMeetingTime());
            sentenceQuery.executeUpdate();
            agreementDAO.addAgreements(connection, meeting);
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
            this.deleteAgreement(connection, meeting);
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
        }finally{
            CONNECTION.closeConnection();
        }
    }
}
