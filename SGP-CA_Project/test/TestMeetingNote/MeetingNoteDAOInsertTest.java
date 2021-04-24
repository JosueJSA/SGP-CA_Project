
package TestMeetingNote;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Assert;
import org.junit.Test;
import sgp.ca.businesslogic.MeetingNoteDAO;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.MeetingNote;

public class MeetingNoteDAOInsertTest {
    
    private final ConnectionDatabase query = new ConnectionDatabase();
    int cantidadRows;
    
    @Test
    public void addMeetingNote() throws SQLException{
        System.out.println("addMeetingPending");
        MeetingNote meetingPending = new MeetingNote("2021-10-09", "17:30:00", "La reunión fue mas corta de lo habitual");
        MeetingNoteDAO instanceProject = new MeetingNoteDAO();
        instanceProject.addMeetingNote(meetingPending); 
        Statement instructionQuery = query.getConnectionDatabase().createStatement();;
        ResultSet queryResult = instructionQuery.executeQuery("Select meetingDate, meetingTime FROM MeetingNote");
        queryResult.last();
        cantidadRows = queryResult.getRow();  
        Assert.assertEquals(3, cantidadRows);
    }
    
}
