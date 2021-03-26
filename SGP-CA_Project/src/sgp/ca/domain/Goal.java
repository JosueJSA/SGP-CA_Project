/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Goal{
    
    private int goalIdentifier;
    private Date startDate, endDate;
    private String statusGoal, description;
    private List<Action> actions;

    public Goal(int goalIdentifier, Date startDate, Date endDate, String statusGoal, String description){
        this.goalIdentifier = goalIdentifier;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusGoal = statusGoal;
        this.description = description;
        actions = new ArrayList<>();
    }
    
    public void addAction(Action newAction){
        actions.add(newAction);
    }
    
    public void removeAction(Action action){
        actions.remove(action);
    }

    public int getGoalIdentifier(){
        return goalIdentifier;
    }

    public void setGoalIdentifier(int goalIdentifier){
        this.goalIdentifier = goalIdentifier;
    }

    public Date getStartDate(){
        return startDate;
    }

    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    public Date getEndDate(){
        return endDate;
    }

    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    public String getStatusGoal(){
        return statusGoal;
    }

    public void setStatusGoal(String statusGoal){
        this.statusGoal = statusGoal;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
