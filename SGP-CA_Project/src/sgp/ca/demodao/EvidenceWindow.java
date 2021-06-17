/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import javafx.scene.Node;
import sgp.ca.domain.Evidence;
import sgp.ca.domain.Integrant;

public interface EvidenceWindow {
    
    public void createWindowAccordingEvidenceType(Evidence evidence, Node graphicElement, Integrant token);
    @Override
    public String toString();
}
