/**
 * @author estef
 * Last modification date format: 06-05-2021
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.util.List;
import sgp.ca.domain.AssistantRol;
import sgp.ca.domain.Meeting;

public interface IAssistantRolDAO {
    public void addAssistantRol(Connection connection, Meeting meeting);
    public List<AssistantRol> getAssistantsRolByMeeting(int meetingKey);
}
