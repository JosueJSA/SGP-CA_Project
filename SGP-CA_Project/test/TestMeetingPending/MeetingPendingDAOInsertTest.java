
package TestMeetingPending;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.MeetingPendingDAO;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.MeetingPending;

public class MeetingPendingDAOInsertTest {
    
    private final ConnectionDatabase query = new ConnectionDatabase();
    int cantidadRows;
    
    @Test
    public void addMeetingPending() throws SQLException{
        System.out.println("addMeetingPending");
        MeetingPending meetingPending = new MeetingPending("2021-10-09", "17:30:00", "Falto tocar el tema del cuidado de las instalaciones");
        MeetingPendingDAO instanceProject = new MeetingPendingDAO();
        instanceProject.addMeetingPending(meetingPending); 
        Statement instructionQuery = query.getConnectionDatabase().createStatement();;
        ResultSet queryResult = instructionQuery.executeQuery("Select meetingDate, meetingTime FROM MeetingPending");
        queryResult.last();
        cantidadRows = queryResult.getRow();   
        Assert.assertEquals(2, cantidadRows);
    }
    
}
