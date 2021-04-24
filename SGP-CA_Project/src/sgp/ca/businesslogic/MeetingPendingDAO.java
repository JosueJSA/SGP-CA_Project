/**
* @author Johann 
* Last modification date format: 08-04-2021
*/

package sgp.ca.businesslogic;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.MeetingPending;

public class MeetingPendingDAO implements IMeetingPendingDAO{

    private final ConnectionDatabase query = new ConnectionDatabase();
    
    @Override
    public void addMeetingPending(MeetingPending newMeetingPending) {
        try{
            PreparedStatement sentenceQuery = query.getConnectionDatabase().prepareStatement(
                "INSERT INTO MeetingPending VALUES(?, ?, ?)"
            );
            sentenceQuery.setString(1, newMeetingPending.getMeetingDate());
            sentenceQuery.setString(2, newMeetingPending.getMeetingTime());
            sentenceQuery.setString(3, newMeetingPending.getPending());

            sentenceQuery.executeUpdate();
        }catch(SQLException ex){
            //Logger.getLogger(ProjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            query.closeConnection();
        }
    }
    
}
