/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package businesslogic;

import java.util.List;
import org.junit.Test;
import org.junit.Assert;
import sgp.ca.businesslogic.GoalDAO;
import sgp.ca.domain.Goal;

public class GoalRequestTest {
    
    @Test
    public void testCorrectGetGoalByWorkPlanKey(){
        GoalDAO goalDAO = new GoalDAO();
        List<Goal> goals = goalDAO.getGoalListByWorkPlan(2022);
        
        Assert.assertEquals(2, goals.size());
        Assert.assertEquals("Correct retirved goal", 1, goals.get(0).getGoalIdentifier());
        Assert.assertEquals("Correct retirved goal", 2, goals.get(1).getGoalIdentifier());
    }
    
    @Test
    public void testIncorrectGetGoalByWorkPlanKeyNotExist(){
        GoalDAO goalDAO = new GoalDAO();
        List<Goal> goals = goalDAO.getGoalListByWorkPlan(777);
        
        Assert.assertEquals(0, goals.size());
    }
    
}
