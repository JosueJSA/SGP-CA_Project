/**
 * @author estef
 * Last modification date format: 13-05-2021
 */
package sgp.ca.demodao;

import javafx.scene.control.RadioButton;

public class AssistantTable{
    private String integrantName;
    private RadioButton assistant;
    private RadioButton discussionLeader;
    private RadioButton secretary;
    private RadioButton takerTime;

    public AssistantTable(String integrantName){
        this.integrantName = integrantName;
        this.assistant = new RadioButton();
        this.discussionLeader = new RadioButton();
        this.secretary = new RadioButton();
        this.takerTime = new RadioButton();
    }

    public String getIntegrantName() {
        return integrantName;
    }

    public void setIntegrantName(String integrantName) {
        this.integrantName = integrantName;
    }

    public RadioButton getAssistant() {
        return assistant;
    }

    public void setAssistant(RadioButton assistant) {
        this.assistant = assistant;
    }

    public RadioButton getDiscussionLeader() {
        return discussionLeader;
    }

    public void setDiscussionLeader(RadioButton discussionLeader) {
        this.discussionLeader = discussionLeader;
    }

    public RadioButton getSecretary() {
        return secretary;
    }

    public void setSecretary(RadioButton secretary) {
        this.secretary = secretary;
    }

    public RadioButton getTakerTime() {
        return takerTime;
    }

    public void setTakerTime(RadioButton takerTime) {
        this.takerTime = takerTime;
    }

    
    
}
