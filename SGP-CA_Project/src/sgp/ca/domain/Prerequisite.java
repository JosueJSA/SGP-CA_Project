/**
 * Last modification date format: 23-04-2021
 * @author estef
 */

package sgp.ca.domain;

public class Prerequisite {
    private int prerequisiteKey;
    private MeetingAgenda meetingAgenda;
    private Integrant responsiblePrerequisite;
    private String descrptionPrerequisite;

    public Prerequisite(int prerequisiteKey, MeetingAgenda meetingAgenda, 
    Integrant responsiblePrerequisite, String descrptionPrerequisite) {
        this.prerequisiteKey = prerequisiteKey;
        this.meetingAgenda = meetingAgenda;
        this.responsiblePrerequisite = responsiblePrerequisite;
        this.descrptionPrerequisite = descrptionPrerequisite;
    }

    public Prerequisite() {
        
    }

    public int getPrerequisiteKey() {
        return prerequisiteKey;
    }

    public MeetingAgenda getMeetingAgenda() {
        return meetingAgenda;
    }

    public Integrant getResponsiblePrerequisite() {
        return responsiblePrerequisite;
    }

    public String getDescrptionPrerequisite() {
        return descrptionPrerequisite;
    }

    public void setPrerequisiteKey(int prerequisiteKey) {
        this.prerequisiteKey = prerequisiteKey;
    }

    public void setMeetingAgenda(MeetingAgenda meetingAgenda) {
        this.meetingAgenda = meetingAgenda;
    }

    public void setResponsiblePrerequisite(Integrant responsiblePrerequisite) {
        this.responsiblePrerequisite = responsiblePrerequisite;
    }

    public void setDescrptionPrerequisite(String descrptionPrerequisite) {
        this.descrptionPrerequisite = descrptionPrerequisite;
    }
    
}
