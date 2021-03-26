/**
* @author Josué Alarcón  
* Last modification date format: 26-03-2021
*/

package sgp.ca.domain;

import java.util.Date;

public class Action{
    
    private int actionKey;
    private Date startDate, endDate, estimatedEndDate;
    private String statusAction, descriptionAction, responsibleAction, resource;

    public Action(int actionKey, Date startDate, Date endDate, Date estimatedEndDate, 
    String statusAction, String descriptionAction, String responsibleAction, String resource){
        this.actionKey = actionKey;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedEndDate = estimatedEndDate;
        this.statusAction = statusAction;
        this.descriptionAction = descriptionAction;
        this.responsibleAction = responsibleAction;
        this.resource = resource;
    }

    public int getActionKey(){
        return actionKey;
    }

    public void setActionKey(int actionKey){
        this.actionKey = actionKey;
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

    public Date getEstimatedEndDate(){
        return estimatedEndDate;
    }

    public void setEstimatedEndDate(Date estimatedEndDate){
        this.estimatedEndDate = estimatedEndDate;
    }

    public String getStatusAction(){
        return statusAction;
    }

    public void setStatusAction(String statusAction){
        this.statusAction = statusAction;
    }

    public String getDescriptionAction(){
        return descriptionAction;
    }

    public void setDescriptionAction(String descriptionAction){
        this.descriptionAction = descriptionAction;
    }

    public String getResponsibleAction(){
        return responsibleAction;
    }

    public void setResponsibleAction(String responsibleAction){
        this.responsibleAction = responsibleAction;
    }

    public String getResource(){
        return resource;
    }

    public void setResource(String resource){
        this.resource = resource;
    }
    
    
}
