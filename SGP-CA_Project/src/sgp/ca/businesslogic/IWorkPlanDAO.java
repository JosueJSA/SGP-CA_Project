/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.businesslogic;

import java.sql.Connection;
import sgp.ca.domain.WorkPlan;


public interface IWorkPlanDAO {
    
    public WorkPlan getWorkPlan(String endDatePlan, String bodyAcademyKey);
    public void addWorkPlan(WorkPlan newWorkPlan);
    public void updateWorkPlan(WorkPlan worPlan, WorkPlan oldWorkPlan);
    public void deleteWorkPlan(WorkPlan workPlan, String bodyAcademyKey);
    public void deleteGoals(Connection connection, WorkPlan workPlan);
    
}
