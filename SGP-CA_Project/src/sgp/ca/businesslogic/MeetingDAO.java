/**
 *
 * @author estef
 */

package sgp.ca.businesslogic;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Meeting;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MeetingDAO implements IMeetingDAO{
    
    private final ConnectionDatabase query = new ConnectionDatabase();
    
    @Override
    public void addMeetingNote(String newMeetingNote, Meeting meeting) {
        try{
            PreparedStatement sentenceQuery = query.getConnectionDatabase().prepareStatement(
                "UPDATE Meeting SET meetingNote= ? WHERE meetingDate = ?, meetingTime = ? ;"
            );
            sentenceQuery.setString(1, newMeetingNote);
            sentenceQuery.setString(2, meeting.getMeetingDate());
            sentenceQuery.setString(3, meeting.getMeetingTime());
            sentenceQuery.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            query.closeConnection();
        }
    }
    
    @Override
    public void addMeetingPending(String newMeetingPending, Meeting meeting) {
        try{
            PreparedStatement sentenceQuery = query.getConnectionDatabase().prepareStatement(
                "UPDATE Meeting SET meetingNote= ? WHERE meetingDate = ?, meetingTime = ? ;"
            );
            sentenceQuery.setString(1, newMeetingPending);
            sentenceQuery.setString(2, meeting.getMeetingDate());
            sentenceQuery.setString(3, meeting.getMeetingTime());
            sentenceQuery.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(MeetingDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            query.closeConnection();
        }
    }
}