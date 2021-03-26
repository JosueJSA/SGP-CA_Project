/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class WorkPlan {
    
    private int workplanKey, durationInYears;
    private String generalTarget;
    private Date startDatePlan, endDatePlan;
    private List<Goal> goals;

    public WorkPlan(int workplanKey, int durationInYears, String generalTarget, Date startDatePlan, Date endDatePlan){
        this.workplanKey = workplanKey;
        this.durationInYears = durationInYears;
        this.generalTarget = generalTarget;
        this.startDatePlan = startDatePlan;
        this.endDatePlan = endDatePlan;
        goals = new ArrayList<>();
    }
    
    public void addGoal(Goal newGoal){
        goals.add(newGoal);
    }
    
    public void removeGoal(Goal goal){
        goals.remove(goal);
    }

    public int getWorkplanKey(){
        return workplanKey;
    }

    public void setWorkplanKey(int workplanKey){
        this.workplanKey = workplanKey;
    }

    public int getDurationInYears(){
        return durationInYears;
    }

    public void setDurationInYears(int durationInYears){
        this.durationInYears = durationInYears;
    }

    public String getGeneralTarget(){
        return generalTarget;
    }

    public void setGeneralTarget(String generalTarget){
        this.generalTarget = generalTarget;
    }

    public Date getStartDatePlan(){
        return startDatePlan;
    }

    public void setStartDatePlan(Date startDatePlan){
        this.startDatePlan = startDatePlan;
    }

    public Date getEndDatePlan(){
        return endDatePlan;
    }

    public void setEndDatePlan(Date endDatePlan){
        this.endDatePlan = endDatePlan;
    }    
    
}
