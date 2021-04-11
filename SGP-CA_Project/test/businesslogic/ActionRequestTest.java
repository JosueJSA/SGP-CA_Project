/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package businesslogic;

import java.util.List;
import org.junit.Test;
import sgp.ca.businesslogic.ActionDAO;
import org.junit.Assert;
import sgp.ca.domain.Action;


public class ActionRequestTest {
    
    @Test
    public void testCorrectGetActionByGoalID(){
        ActionDAO actionDAO = new ActionDAO();
        List<Action> actions = actionDAO.getActionsByGoal(2);
        
        Assert.assertEquals("Actions by goal", 2, actions.get(0).getActionKey());
        Assert.assertEquals("Actions by goal", 3, actions.get(1).getActionKey());
        Assert.assertEquals(2, actions.size());

    }
    
    @Test
    public void testEmptyGetActionByGoalIDNotExist(){
        ActionDAO actionDAO = new ActionDAO();
        List<Action> actions = actionDAO.getActionsByGoal(100);
        
        Assert.assertEquals(0, actions.size());

    }
    
}
