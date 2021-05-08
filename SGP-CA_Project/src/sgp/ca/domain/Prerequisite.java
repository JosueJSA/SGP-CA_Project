/**
 * Last modification date format: 23-04-2021
 * @author estef
 */

package sgp.ca.domain;

public class Prerequisite {
    private int prerequisiteKey;
    private String responsiblePrerequisite;
    private String descrptionPrerequisite;

    public Prerequisite(int prerequisiteKey, String responsiblePrerequisite, 
    String descrptionPrerequisite) {
        this.prerequisiteKey = prerequisiteKey;
        this.responsiblePrerequisite = responsiblePrerequisite;
        this.descrptionPrerequisite = descrptionPrerequisite;
    }

    public Prerequisite() {
        
    }

    public int getPrerequisiteKey() {
        return prerequisiteKey;
    }

    public String getResponsiblePrerequisite() {
        return responsiblePrerequisite;
    }

    public String getDescrptionPrerequisite() {
        return descrptionPrerequisite;
    }

    public void setPrerequisiteKey(int prerequisiteKey) {
        this.prerequisiteKey = prerequisiteKey;
    }

    public void setResponsiblePrerequisite(String responsiblePrerequisite) {
        this.responsiblePrerequisite = responsiblePrerequisite;
    }

    public void setDescrptionPrerequisite(String descrptionPrerequisite) {
        this.descrptionPrerequisite = descrptionPrerequisite;
    }
    
}
