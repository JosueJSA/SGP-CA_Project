/**
 * @author estef
 * Last modification date format: 29-04-2021
 */

package businesslogic.testmeetingdao;

import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.MeetingDAO;
import sgp.ca.domain.Meeting;

public class MeetingRequestTest {
    public final MeetingInitializer INITIALIZER = new MeetingInitializer();
    public final MeetingDAO MEETING_DAO = new MeetingDAO();
    
    @Test
    public void testGetExistMeetingByMeetingDateandMeetingTime(){
        INITIALIZER.preparedRequestMeetingTestCase();
        Meeting meetingRetrieved = MEETING_DAO.getMeeting("2021-05-02", "17:00:00");
        String meetingProjectExpected = "Plan de Estudios de ISOF";
        INITIALIZER.cleanMeetingTestCaseData();
        Assert.assertEquals(meetingProjectExpected, meetingRetrieved.getMeetingProject());
    }
    
    @Test
    public void testGetNotExistMeetingByMeetingDateandMeetingTime(){
        Meeting meetingRetrieved = MEETING_DAO.getMeeting("2021-05-02", "17:00:00");
        Assert.assertNull(meetingRetrieved.getMeetingProject());
    }
}
