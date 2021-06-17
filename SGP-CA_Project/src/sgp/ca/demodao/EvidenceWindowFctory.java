/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgp.ca.demodao;

import java.util.Arrays;
import java.util.List;
import javafx.scene.Node;
import sgp.ca.domain.Evidence;
import sgp.ca.domain.Integrant;

public class EvidenceWindowFctory {
    
    private static List<EvidenceWindow> listEvidenceView = Arrays.asList(
        new BookController(),
        new ReceptionWorkController(),
        new ArticleController(),
        new PrototypeController()
    );
    
    public static void showSpecificEvidenceWindow(Evidence evidence, Node graphic, Integrant token){
        for(EvidenceWindow evidenceView : listEvidenceView){
            evidenceView.createWindowAccordingEvidenceType(evidence, graphic, token);
        }
    }
    
}
