/**
 *
 * @author estef
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.util.List;
import sgp.ca.domain.MeetingAgenda;
import sgp.ca.domain.Topic;

public interface ITopicDAO {
    public void addTopic(Connection connection, MeetingAgenda meetingAgenda);
    public List<Topic> getTopicByAgendaMeeting(int meetingAgendaKey);
}
