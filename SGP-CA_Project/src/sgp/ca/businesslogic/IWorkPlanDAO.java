/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.businesslogic;

import java.sql.Connection;
import java.util.List;
import sgp.ca.domain.WorkPlan;


public interface IWorkPlanDAO {
    
    public List<WorkPlan> getWorkPlanPeriots(String bodyAcademyKey);
    public WorkPlan getWorkPlan(String endDatePlan, String bodyAcademyKey);
    public boolean addWorkPlan(WorkPlan newWorkPlan);
    public boolean updateWorkPlan(WorkPlan worPlan, WorkPlan oldWorkPlan);
    public boolean deleteWorkPlan(WorkPlan workPlan, String bodyAcademyKey);
    public boolean deleteGoals(Connection connection, WorkPlan workPlan);
    
}
