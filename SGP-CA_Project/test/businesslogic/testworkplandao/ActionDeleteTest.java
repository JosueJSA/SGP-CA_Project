/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package businesslogic.testworkplandao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import org.junit.Assert;
import sgp.ca.businesslogic.GoalDAO;
import sgp.ca.dataaccess.ConnectionDatabase;
import sgp.ca.domain.Goal;

public class ActionDeleteTest {
    
    private final ConnectionDatabase CONNECTION = new ConnectionDatabase();
    
    @Test
    public void testCleanAndCompleteActionsDeleteByGoal(){
        try{
            Connection connection = CONNECTION.getConnectionDatabaseNotAutoCommit();
            GoalDAO goalsDAO = new GoalDAO();
            List<Goal> goalsRetrieved = goalsDAO.getGoalListByWorkPlan(2022);
            goalsDAO.deleteActions(connection, goalsRetrieved);
            connection.commit();
            connection.setAutoCommit(true);
            goalsRetrieved = goalsDAO.getGoalListByWorkPlan(2022);
            int expectedSum = 0;
            int retrievedSum = goalsRetrieved.get(0).getActions().size() + goalsRetrieved.get(1).getActions().size();
            Assert.assertEquals(expectedSum, retrievedSum);
        }catch(SQLException ex){
            Logger.getLogger(ActionDeleteTest.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            CONNECTION.closeConnection();
        }
    }
    
}
