/**
* @author Josué Alarcón  
* Last modification date format: 06-04-2021
*/

package sgp.ca.domain;


public class Action{
    
    private int actionKey;
    private String startDate;
    private String endDate;
    private String estimatedEndDate;
    private String descriptionAction;
    private String responsibleAction;
    private String resource;
    private boolean statusAction;

    public Action(int actionKey, String startDate, String endDate, 
    String estimatedEndDate, String descriptionAction, 
    String responsibleAction, String resource, boolean statusAction){
        this.actionKey = actionKey;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedEndDate = estimatedEndDate;
        this.descriptionAction = descriptionAction;
        this.responsibleAction = responsibleAction;
        this.resource = resource;
        this.statusAction = statusAction;
    }
    
    public Action(String descriptionAction, String estimatedEndDate, String responsibleAction, String startDate, String resource){
        this.startDate = startDate;
        this.estimatedEndDate = estimatedEndDate;
        this.descriptionAction = descriptionAction;
        this.responsibleAction = responsibleAction;
        this.resource = resource;
    }
    
    public Action(String descriptionAction, String estimatedEndDate, String endDate, String responsibleAction, String startDate, String resource){
        this.resource = resource;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimatedEndDate = estimatedEndDate;
        this.descriptionAction = descriptionAction;
        this.responsibleAction = responsibleAction;
    }
    
    public Action(){
        
    }

    public int getActionKey(){
        return actionKey;
    }

    public void setActionKey(int actionKey){
        this.actionKey = actionKey;
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

    public String getEstimatedEndDate(){
        return estimatedEndDate;
    }

    public void setEstimatedEndDate(String estimatedEndDate){
        this.estimatedEndDate = estimatedEndDate;
    }

    public boolean isStatusAction(){
        return statusAction;
    }

    public void setStatusAction(boolean statusAction){
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
    
    @Override
    public boolean equals(Object obj){
        boolean isEqual = false;
        if(this.descriptionAction.equalsIgnoreCase(((Action)obj).getDescriptionAction())){
            isEqual = true;
        }
        return isEqual;
    }
    
}
