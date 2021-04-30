/**
 *
 * @author estef
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.util.List;
import sgp.ca.domain.Agreement;
import sgp.ca.domain.Meeting;

public interface IAgreementDAO {
    public void addAgreements(Connection connection, Meeting meeting);
    public List<Agreement> getAgreementListByMeeting(String meetingDate, String meetingTime);
}
