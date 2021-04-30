/**
 * @author estef
 * Last modification date format: 29-04-2021
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import sgp.ca.domain.Meeting;
import sgp.ca.domain.MeetingAgenda;

public interface IMeetingAgendaDAO {
    
    public void addMeetingAgenda(Connection connection, Meeting meeting);
    public MeetingAgenda getMeetingAgendaByMeeting(String meetingDate, String meetingTime);
    public void deleteTopic(Connection connection, MeetingAgenda meetingAgenda);
    public void deletePrerequisite(Connection connection, MeetingAgenda meetingAgenda);
}
