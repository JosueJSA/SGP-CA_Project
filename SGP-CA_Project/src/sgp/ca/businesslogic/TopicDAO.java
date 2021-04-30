/**
 *
 * @author estef
 */

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import sgp.ca.domain.MeetingAgenda;
import sgp.ca.domain.Topic;

public class TopicDAO implements ITopicDAO{

    @Override
    public void addTopic(Connection connection, MeetingAgenda meetingAgenda) {
        meetingAgenda.getTopics().forEach(topic ->{
            try{
                PreparedStatement sentenceQuery = connection.prepareStatement(
                    "INSERT INTO Topic (meetingAgendaKey, startTime, endTime, plannedTime, realTime, descriptionTopic, discissionLeader) VALUES(?,?,?,?,?,?,?);",
                    PreparedStatement.RETURN_GENERATED_KEYS    
                );
                sentenceQuery.setInt(1, meetingAgenda.getMeetingAgendaKey());
                sentenceQuery.setString(2, topic.getStartTime());
                sentenceQuery.setString(3, topic.getEndTime());
                sentenceQuery.setString(4, topic.getPlannedTime());
                sentenceQuery.setString(5, topic.getRealTime());
                sentenceQuery.setString(6, topic.getDescriptionTopic());
                sentenceQuery.setString(7, topic.getDiscissionLeader());
                sentenceQuery.executeUpdate();
                this.updateTopicWithNumberTopicGenerated(sentenceQuery, topic);
            }catch(SQLException sqlException){
                try{
                    connection.rollback();
                    java.util.logging.Logger.getLogger(Topic.class.getName()).log(Level.SEVERE, null, sqlException);
                }catch(SQLException ex){
                    java.util.logging.Logger.getLogger(TopicDAO.class.getName()).log(Level.SEVERE, null, sqlException);
                }
            }
        });
    }

    @Override
    public List<Topic> getTopicByAgendaMeeting(int meetingAgendaKey) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void updateTopicWithNumberTopicGenerated(PreparedStatement statement, Topic topic){
        try{
            ResultSet result = statement.getGeneratedKeys();
            if(result.next()){
                topic.setNumberTopic(result.getInt(1));
            }
        }catch(SQLException ex){
            java.util.logging.Logger.getLogger(TopicDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
