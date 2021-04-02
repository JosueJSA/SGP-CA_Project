/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.businesslogic;

import sgp.ca.domain.WorkPlan;


public interface IWorkPlanDAO {
    
    public WorkPlan getWorkPlan(String enDatePlan);
    public void addWorkPlan(WorkPlan newWorkPlan);
    
}
