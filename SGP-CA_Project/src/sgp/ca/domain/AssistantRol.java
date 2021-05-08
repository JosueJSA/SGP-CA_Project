/**
 * @author estef
 * Last modification date format: 29-04-2021
 */

package sgp.ca.domain;

public class AssistantRol {
    private int assistantRolKey;
    private String assistantRfc;
    private String roleAssistant;
    private int assistantNumber;
    private String initialsAssistant;

    public AssistantRol(int assistantRolKey, String assistantRfc, 
    String roleAssistant, int assistantNumber, String initialsAssistant) {
        this.assistantRolKey = assistantRolKey;
        this.assistantRfc = assistantRfc;
        this.roleAssistant = roleAssistant;
        this.assistantNumber = assistantNumber;
        this.initialsAssistant = initialsAssistant;
    }

    public AssistantRol() {
        
    }

    public int getAssistantRolKey() {
        return assistantRolKey;
    }

    public String getAssistantRfc() {
        return assistantRfc;
    }

    public String getRoleAssistant() {
        return roleAssistant;
    }

    public int getAssistantNumber() {
        return assistantNumber;
    }

    public String getInitialsAssistant() {
        return initialsAssistant;
    }

    public void setAssistantRolKey(int assistantRolKey) {
        this.assistantRolKey = assistantRolKey;
    }

    public void setAssistantRfc(String assistantRfc) {
        this.assistantRfc = assistantRfc;
    }

    public void setRoleAssistant(String roleAssistant) {
        this.roleAssistant = roleAssistant;
    }

    public void setAssistantNumber(int assistantNumber) {
        this.assistantNumber = assistantNumber;
    }

    public void setInitialsAssistant(String initialsAssistant) {
        this.initialsAssistant = initialsAssistant;
    }
}
