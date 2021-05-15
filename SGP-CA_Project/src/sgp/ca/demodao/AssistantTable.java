/**
 * @author estef
 * Last modification date format: 13-05-2021
 */
package sgp.ca.demodao;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;

public class AssistantTable {
    private String integrantName;
    private RadioButton assistant;
    private RadioButton discussionLeader;
    private RadioButton secretary;
    private RadioButton takerTime;

    public AssistantTable(String integrantName) {
        this.integrantName = integrantName;
        this.assistant = new RadioButton();
        assistant.setOnMouseReleased(new EventHandler(){
            @Override
            public void handle(Event event){
                
            }
        });
        this.discussionLeader = new RadioButton();
        discussionLeader.setOnMouseReleased(new EventHandler(){
            @Override
            public void handle(Event event){
                
            }
        });
        this.secretary = new RadioButton();
        secretary.setOnMouseReleased(new EventHandler(){
            @Override
            public void handle(Event event){
                
            }
        });
        this.takerTime = new RadioButton();
        takerTime.setOnMouseReleased(new EventHandler(){
            @Override
            public void handle(Event event){
                
            }
        });
    }

    public String getIntegrantName() {
        return integrantName;
    }

    public RadioButton getAssistant() {
        return assistant;
    }

    public RadioButton getDiscussionLeader() {
        return discussionLeader;
    }

    public RadioButton getSecretary() {
        return secretary;
    }

    public RadioButton getTakerTime() {
        return takerTime;
    }

    public void setIntegrantName(String integrantName) {
        this.integrantName = integrantName;
    }

    public void setAssistant(RadioButton assistant) {
        this.assistant = assistant;
    }

    public void setDiscussionLeader(RadioButton discussionLeader) {
        this.discussionLeader = discussionLeader;
    }

    public void setSecretary(RadioButton secretary) {
        this.secretary = secretary;
    }

    public void setTakerTime(RadioButton takerTime) {
        this.takerTime = takerTime;
    }
    
    
}
