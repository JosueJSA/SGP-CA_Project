/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.domain;

import java.util.ArrayList;
import java.util.List;


public class WorkPlan {
    
    private int workplanKey;
    private int durationInYears;
    private String generalTarget;
    private String startDatePlan;
    private String endDatePlan;
    private List<Goal> goals;
    private String bodyAcademyKey;

    public WorkPlan(int workplanKey, int durationInYears, String generalTarget, 
    String startDatePlan, String endDatePlan,String bodyAcademyKey){
        this.workplanKey = workplanKey;
        this.durationInYears = durationInYears;
        this.generalTarget = generalTarget;
        this.startDatePlan = startDatePlan;
        this.endDatePlan = endDatePlan;
        this.goals = new ArrayList<>();
        this.bodyAcademyKey = bodyAcademyKey;
    }

    public WorkPlan(){
        this.goals = new ArrayList<>();
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public String getBodyAcademyKey() {
        return bodyAcademyKey;
    }

    public void setBodyAcademyKey(String bodyAcademyKey) {
        this.bodyAcademyKey = bodyAcademyKey;
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

    public String getStartDatePlan(){
        return startDatePlan;
    }

    public void setStartDatePlan(String startDatePlan){
        this.startDatePlan = startDatePlan;
    }

    public String getEndDatePlan(){
        return endDatePlan;
    }

    public void setEndDatePlan(String endDatePlan){
        this.endDatePlan = endDatePlan;
    }
    
}
