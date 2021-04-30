/**
 * @author estef
 * Last modification date format: 29-04-2021
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.util.List;
import sgp.ca.domain.Meeting;
import sgp.ca.domain.MeetingAgenda;
import sgp.ca.domain.Prerequisite;
import sgp.ca.domain.Topic;

public class MeetingAgendaDAO implements IMeetingAgendaDAO{

    @Override
    public void addMeetingAgenda(Connection connection, Meeting meeting) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MeetingAgenda getMeetingAgendaByMeeting(String meetingDate, String meetingTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteTopic(Connection connection, MeetingAgenda meetingAgenda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePrerequisite(Connection connection, MeetingAgenda meetingAgenda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
