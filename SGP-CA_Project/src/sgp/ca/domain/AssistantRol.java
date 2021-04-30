/**
 * @author estef
 * Last modification date format: 29-04-2021
 */

package sgp.ca.domain;

public class AssistantRol {
    private Integrant assistantMeeting;
    private Meeting meeting;
    private String roleAssistant;
    private int assistantNumber;
    private int initialsAssistant;

    public AssistantRol(Integrant assistantMeeting, Meeting meeting, String roleAssistant, int assistantNumber, int initialsAssistant) {
        this.assistantMeeting = assistantMeeting;
        this.meeting = meeting;
        this.roleAssistant = roleAssistant;
        this.assistantNumber = assistantNumber;
        this.initialsAssistant = initialsAssistant;
    }

    public AssistantRol() {
        
    }

    public Integrant getAssistantMeeting() {
        return assistantMeeting;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public String getRoleAssistant() {
        return roleAssistant;
    }

    public int getAssistantNumber() {
        return assistantNumber;
    }

    public int getInitialsAssistant() {
        return initialsAssistant;
    }

    public void setAssistantMeeting(Integrant assistantMeeting) {
        this.assistantMeeting = assistantMeeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public void setRoleAssistant(String roleAssistant) {
        this.roleAssistant = roleAssistant;
    }

    public void setAssistantNumber(int assistantNumber) {
        this.assistantNumber = assistantNumber;
    }

    public void setInitialsAssistant(int initialsAssistant) {
        this.initialsAssistant = initialsAssistant;
    }
}
