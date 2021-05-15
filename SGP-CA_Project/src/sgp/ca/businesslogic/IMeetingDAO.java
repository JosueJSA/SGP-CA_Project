/**
 * @author estef
 * Last modification date format: 06-05-2021
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.util.List;
import sgp.ca.domain.Meeting;

public interface IMeetingDAO {
    
    public List<Meeting> getAllMeetings();
    public Meeting getMeeting(int meetingKey);
    public List<Meeting> getMeetingsbyDate(String meetingDate);
    public void addMeeting(Meeting newMeeting);
    public void updateMeeting(Meeting meeting, Meeting oldMeeting);
    public void deleteMeeting(Meeting meeting);
    public void deleteMeetingAgenda(Connection connection, Meeting meeting);
    public void deleteAgreement(Connection connection, Meeting meeting);
    public void deleteComment(Connection connection, Meeting meeting);
    public void deleteAssistantRol(Connection connection, Meeting meeting);
    public void addMeetingNote(String newMeetingNote, Meeting meeting);
    public void addMeetingPending(String newMeetingPending, Meeting meeting);
}
