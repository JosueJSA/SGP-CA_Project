/**
 *
 * @author estef
 */

package sgp.ca.businesslogic;

import sgp.ca.domain.Meeting;

public interface IMeetingDAO {
    
    public void addMeetingNote(String newMeetingNote, Meeting meeting);
    public void addMeetingPending(String newMeetingPending, Meeting meeting);
}
