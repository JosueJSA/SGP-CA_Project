/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.domain;

import java.util.ArrayList;
import java.util.List;


public class Goal{
    
    private int goalIdentifier;
    private String startDate;
    private String endDate;
    private boolean statusGoal;
    private String description;
    private List<Action> actions;

    public Goal(int goalIdentifier, String startDate, String endDate, 
    boolean statusGoal, String description){
        this.goalIdentifier = goalIdentifier;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusGoal = statusGoal;
        this.description = description;
        this.actions = new ArrayList<>();
    }
    
    public Goal(){
        this.actions = new ArrayList<>();
    }

    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
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

    public String getStartDate(){
        return startDate;
    }

    public void setStartDate(String startDate){
        this.startDate = startDate;
    }

    public String getEndDate(){
        return endDate;
    }

    public void setEndDate(String endDate){
        this.endDate = endDate;
    }

    public boolean isStatusGoal(){
        return statusGoal;
    }

    public void setStatusGoal(boolean statusGoal){
        this.statusGoal = statusGoal;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
