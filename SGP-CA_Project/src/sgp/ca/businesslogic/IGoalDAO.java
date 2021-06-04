/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.util.List;
import sgp.ca.domain.Action;
import sgp.ca.domain.Goal;
import sgp.ca.domain.WorkPlan;

public interface IGoalDAO {
    
    public void addGoals(Connection connection, WorkPlan workPlan);
    public List<Goal> getGoalListByWorkPlan(Connection connection, int getWorkplanKey);
    public void addActions(Connection connection, Goal goal);
    public List<Action> getActionsByGoal(Connection connection, int goalIdentifier);
    
}
