/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import javafx.scene.control.RadioButton;

public class CollaboratorTable {
    private String collaboratorRfc;
    private String collaboratorName;
    private RadioButton participation;

    public CollaboratorTable(String collaboratorRfc, String collaboratorName){
        this.collaboratorRfc = collaboratorRfc;
        this.collaboratorName = collaboratorName;
        this.participation = new RadioButton();
    }

    public String getCollaboratorName() {
        return collaboratorName;
    }

    public void setCollaboratorName(String collaboratorName) {
        this.collaboratorName = collaboratorName;
    }

    public RadioButton getParticipation() {
        return participation;
    }

    public void setParticipation(RadioButton assistant) {
        this.participation = participation;
    }

    public String getCollaboratorRfc() {
        return collaboratorRfc;
    }

    public void setCollaboratorRfc(String collaboratorRfc) {
        this.collaboratorRfc = collaboratorRfc;
    }
    
}
