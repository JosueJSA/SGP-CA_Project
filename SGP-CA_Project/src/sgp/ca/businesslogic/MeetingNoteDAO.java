/**
* @author Johann 
* Last modification date format: 07-04-2021
*/

package sgp.ca.businesslogic;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.MeetingNote;

public class MeetingNoteDAO implements IMeetingNoteDAO{
    private final ConnectionDatabase query = new ConnectionDatabase();
    
    @Override
    public void addMeetingNote(MeetingNote newMeetingNote) {
        try{
            PreparedStatement sentenceQuery = query.getConnectionDatabase().prepareStatement(
                "INSERT INTO MeetingNote VALUES(?, ?, ?)"
            );
            sentenceQuery.setString(1, newMeetingNote.getMeetingDate());
            sentenceQuery.setString(2, newMeetingNote.getMeetingTime());
            sentenceQuery.setString(3, newMeetingNote.getNote());

            sentenceQuery.executeUpdate();
        }catch(SQLException ex){
            //Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            query.closeConnection();
        }
    }
}
