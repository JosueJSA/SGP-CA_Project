/**
 *
 * @author estef
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.util.List;
import sgp.ca.domain.MeetingAgenda;
import sgp.ca.domain.Prerequisite;

public interface IPrerequisiteDAO {
    public void addPrerequisite(Connection connection, MeetingAgenda meetingAgenda);
    public List<Prerequisite> getPrerequisiteByAgendaMeeting(int meetingAgendaKey);
}
