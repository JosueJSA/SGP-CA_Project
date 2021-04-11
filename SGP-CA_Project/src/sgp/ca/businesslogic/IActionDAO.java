/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.businesslogic;

import java.sql.Connection;
import java.util.List;
import sgp.ca.domain.Action;
import sgp.ca.domain.Goal;

/**
 *
 * @author Josue Alarcon
 */
public interface IActionDAO {
    
    public void addActions(Connection connection, Goal goal);
    public List<Action> getActionsByGoal(int goalIdentifier);
    
}
